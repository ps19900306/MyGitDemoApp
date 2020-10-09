package com.ningwenqiang.glory.toollibrary.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.ningwenqiang.glory.toollibrary.BasicApp
import com.ningwenqiang.glory.toollibrary.log.L
import java.lang.ref.WeakReference
import java.util.*

/**
create by: 86136
create time: 2020/9/28 13:48
Function description:
 */

interface ActivityStackManager : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> addActivity(source as Activity)
            Lifecycle.Event.ON_DESTROY -> removeActivity(source as Activity)
        }
    }


    companion object {

        /**
         * Activity栈
         */
        private val mActivityStack by lazy {
            Stack<WeakReference<Activity>>()
        }

        //其实这个不会为空
        fun getContext(): Context? {
            return getTopActivity() ?: BasicApp.context
        }

        /**
         * 添加Activity到堆栈
         */
        fun addActivity(activity: Activity) {
            L.d("", "addActivity", "ActivityStackManager", "nwq", "2020/9/28");
            mActivityStack.add(WeakReference(activity))
        }

        /**
         * 删除ac
         *
         * @param activity 弱引用的ac
         */
        fun removeActivity(activity: Activity) {
            L.d("", "removeActivity", "ActivityStackManager", "nwq", "2020/9/28");
            mActivityStack.remove(WeakReference(activity))
        }

        /***
         * 获取栈顶Activity（堆栈中最后一个压入的）
         *
         * @return Activity
         */
        fun getTopActivity(): Activity? {
            return mActivityStack.lastElement().get()
        }

        /***
         * 通过class 获取栈顶Activity
         *
         * @param cls
         * @return Activity
         */
        fun getActivityByClass(cls: Class<*>): Activity? {
            for (activity in mActivityStack) {
                activity.get()?.apply {
                    if (this.javaClass == cls)
                        return this
                }
            }
            return null
        }

        /**
         * 结束栈顶Activity（堆栈中最后一个压入的）
         */
        fun killTopActivity() {
            getTopActivity()?.finish()
        }

        /***
         * 结束指定类名的Activity
         *
         * @param cls
         */
        fun killActivityByClass(cls: Class<*>, cleanAll: Boolean = false) {
            getActivityByClass(cls)?.apply {
                this.finish()
                if (cleanAll) {   //这个迭代取清楚
                    killActivityByClass(cls)
                }
            }
        }

        fun killAllActivity() {
            for (activity in mActivityStack) {
                activity.get()?.finish()
            }
        }
    }
}