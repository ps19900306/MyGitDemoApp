package com.ningwenqiang.glory.mygitdemoapp.data_repository

import android.util.Log
import com.ningwenqiang.glory.mygitdemoapp.data_repository.DataRefreshEvent.Companion.DELETE_DATA
import com.ningwenqiang.glory.mygitdemoapp.data_repository.DataRefreshEvent.Companion.INSERT_DATA
import com.ningwenqiang.glory.mygitdemoapp.data_repository.DataRefreshEvent.Companion.NoMoreData
import com.ningwenqiang.glory.mygitdemoapp.data_repository.DataRefreshEvent.Companion.SOURCE_DATA
import com.ningwenqiang.glory.mygitdemoapp.data_repository.DataRefreshEvent.Companion.UPDATE_DATA
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwq
import java.lang.ref.WeakReference

/**
create by: 86136
create time: 2020/9/23 10:20
Function description:
 */

open class RepositoryImpl<T : RepositoryData>(val mKeyStr: String) {

    protected var mValidDataPosition = 0 //查找的有效数据开始位置
    private var mDataVersion = Int.MIN_VALUE //是否需要同步数据的版本号
    private var mDataSource: ArrayList<T> = ArrayList() //数据的存储类
    private lateinit var mRepositoryOperation: RepositoryOperation<T> //数据的实际操作类
    private var mDataObserverList: ArrayList<WeakReference<ObserverNwq<DataRefreshEvent>>> =
        ArrayList()  //这里

    /**
     * 数据初始部分-------------------------------------------------------------
     */
    open fun setRepositoryOperation(repositoryOperation: RepositoryOperation<T>?) {
        if (repositoryOperation != null) {
            mRepositoryOperation = repositoryOperation
            reLoadData()
        }
    }


    //需要实现具体的 建议与RepositoryOperation(实现过程)后续处理数据
    public fun reLoadData() {
        if (::mRepositoryOperation.isInitialized) {
            mRepositoryOperation.LoadData(object : ObserverNwq<List<T>?> {
                override fun observation(t: List<T>?) {
                    setDataSource(t)
                }
            })
        }
    }

    public fun LoadMoreData() {
        if (::mRepositoryOperation.isInitialized) {
            mRepositoryOperation.LoadMoreData(mDataSource[mDataSource.size - 1],
                object : ObserverNwq<List<T>?> {
                    override fun observation(t: List<T>?) {
                        setDataSource(t)
                    }
                })
        }
    }

    public fun setDataSource(list: List<T>?) {
        if (list != null) {
            mDataSource.clear()
            mDataSource.addAll(list)
        }
        notifyDataObserver(NoMoreData, 0, 0)
    }


    /**
     * 数据刷新部分-------------------------------------------------------------
     * -------------------------------------------
     */
    /**
     * 通知数据改变
     */
    protected fun notifyNoMoreData() {
        notifyDataObserver(NoMoreData, 0, 0)
    }


    /**
     * 通知数据改变
     */
    protected fun notifyDataSource() {
        notifyDataObserver(SOURCE_DATA, 0, 0)
    }


    /**
     * 通知数据改变
     */
    protected fun notifyDataObserver(RefreshType: Int, StartPosition: Int) {
        notifyDataObserver(RefreshType, StartPosition, 1)
    }


    /**
     * 通知数据改变
     */
    protected fun notifyDataObserver(RefreshType: Int, StartPosition: Int, itemCount: Int) {
        if (mDataVersion < Int.MAX_VALUE) {
            mDataVersion++
        } else {
            mDataVersion = Int.MIN_VALUE
        }
        val dataRefreshEvent = DataRefreshEvent(mDataVersion, RefreshType, StartPosition, itemCount)
        for (x in mDataObserverList) {
            x?.get()?.observation(dataRefreshEvent)
        }
    }


    public open fun addDataObserver(callBack: ObserverNwq<DataRefreshEvent>?): Boolean {
        var flag = false
        callBack?.let {
            mDataObserverList.add(WeakReference(callBack))
            flag = true
        }
        return flag
    }


    open fun getVersion(): Int {
        return mDataVersion
    }

    open fun getDataSource(): List<T>? {
        return mDataSource
    }

    /**
     * 功能操作类型-------------------------------------------------------------
     * -------------------------------------------
     */

    protected fun deleteDataList(list: List<T>?) {
        if (list.isNullOrEmpty())
            return
        for (t in list) {
            deleteData(t)
        }
    }


    protected fun addOrUpdateList(list: List<T>?) {
        if (list.isNullOrEmpty())
            return
        var itemCount = 0
        for (t in list) {
            val i = findDataPositionS(t)
            if (i == -1) {
                addOrUpdate(t)
            } else {
                addDataS(t)
                itemCount++
            }
        }
        notifyDataObserver(INSERT_DATA, mDataSource.size - itemCount, itemCount)
    }

    protected fun updateData(t: T?): Boolean {
        if (t == null) return false
        val i = findDataPositionS(t)
        if (i != -1) {
            mDataSource.removeAt(i)
            mDataSource.add(i, t)
            notifyDataObserver(UPDATE_DATA, i)
            return true
        }
        return false
    }

    protected fun addDataList(list: List<T>?) {
        //插入列表的时候即便是空也会进行通知，让接收者结束更多加载状态
        if (list == null) {
            return
        }
        var itemCount = 0
        for (t in list) {
            val i = findDataPositionS(t)
            if (i == -1) {
                addDataS(t)
                itemCount++
            }
        }
        notifyDataObserver(INSERT_DATA, mDataSource.size - 1, itemCount)
    }

    protected fun addDataList(list: List<T>?, position: Int) {
        //插入列表的时候即便是空也会进行通知，让接收者结束更多加载状态
        if (list == null) {
            return
        }
        var insertPosition = position
        if (mDataSource.size < position) return
        for (t in list) {
            val i = findDataPositionS(t)
            if (i == -1) {
                addDataS(t, insertPosition)
                insertPosition++
            }
        }
        notifyDataObserver(INSERT_DATA, position, insertPosition - position)
    }


    /**
     * @param t        需要插入的数据
     * @param position 位置
     * @return
     */
    protected fun addData(t: T?, position: Int): Boolean {
        if (t == null || position > mDataSource.size) return false
        val i = findDataPositionS(t)
        if (i == -1) {
            addDataS(t, position)
            notifyDataObserver(INSERT_DATA, position)
            return true
        }
        return false
    }


    protected fun addOrUpdate(t: T?) {
        if (t == null) return
        val position = findDataPositionS(t)
        if (position == -1) {
            addDataS(t)
            notifyDataObserver(INSERT_DATA, mDataSource.size - 1)
        } else {
            deleteDataS(position)
            addDataS(t, position)
            notifyDataObserver(UPDATE_DATA, position)
        }
    }

    protected fun addOrUpdate(t: T?, insertPosition: Int) {
        if (t == null || insertPosition > mDataSource.size) return
        val position = findDataPositionS(t)
        if (position == -1) {
            addDataS(t, insertPosition)
            notifyDataObserver(INSERT_DATA, insertPosition)
        } else if (position == insertPosition) {
            updateData(t)
        } else {
            deleteData(t)
            addData(t, insertPosition)
        }
    }

    /**
     * @param t 需要插入的数据
     * @return
     */
    protected fun addData(t: T?): Boolean {
        if (t == null) return false
        val i = findDataPositionS(t)
        if (i == -1) {
            try {
                addDataS(t)
                notifyDataObserver(INSERT_DATA, mDataSource.size - 1)
            } catch (e: Exception) {
                Log.e("addData", e.toString())
            }
            return true
        }
        return false
    }


    /**
     * @param t
     * @return
     */
    protected fun deleteData(t: T?): Boolean {
        if (t == null) return false
        val i = findDataPositionS(t)
        if (i != -1) {
            deleteDataS(i)
            notifyDataObserver(DELETE_DATA, i)
            return true
        }
        return false
    }

    /**
     * 功能操作类型-------------------------------------------------------------
     * -------------------------------------------
     */
    //以S结尾的方法都是不进行校验，直接执行逻辑的
    protected fun findDataPositionS(t: T): Int {
        for (i in mValidDataPosition until mDataSource.size) {
            if (t.isSameData(mDataSource[i])) return i
        }
        return -1
    }


    //以S结尾的方法都是不进行校验，直接执行逻辑的
    protected fun findDataObjectS(t: T): T? {
        for (i in mValidDataPosition until mDataSource.size) {
            if (t.isSameData(mDataSource[i])) return mDataSource[i]
        }
        return null
    }

    protected fun addDataS(t: T, position: Int) {
        mDataSource.add(position, t)
    }


    protected fun addDataS(t: T): Boolean {
        return mDataSource.add(t)
    }


    protected fun deleteDataS(position: Int) {
        mDataSource.removeAt(position)
    }
}