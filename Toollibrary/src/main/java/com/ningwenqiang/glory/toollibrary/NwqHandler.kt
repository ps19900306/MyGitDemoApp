package com.ningwenqiang.glory.toollibrary

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwq
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwqImpl
import java.lang.ref.WeakReference
import java.util.*

/**
create by: 86136
create time: 2020/9/28 14:19
Function description:
 */
class NwqHandler : Handler(Looper.getMainLooper()) {

    //这里需要统一存放Key已防止用冲突
    companion object {
        private const val INTERVAL_TIME = 3000L
        private const val INIT_TIME = 0L

        const val COUNT_DOWN: Int = 499
        const val CLEAN_DELAY: Int = 500
        const val TRTC_CALL: Int = 1021
    }

    private val weakReferenceHashMap = HashMap<Int, WeakReference<out ObserverNwq<Message>>>()

    //用于做延迟控制的
    private val lastTimeMaps = HashMap<Int, Long>()


    /**
     * 用于实现延迟点击
     **/
    fun hasInterval(Key: Int): Boolean {
        return hasInterval(Key, true)
    }


    fun hasInterval(Key: Int, saveTime: Boolean): Boolean {
        val time = lastTimeMaps[Key] ?: INIT_TIME
        if (System.currentTimeMillis() > time + INTERVAL_TIME) {
            if (saveTime) lastTimeMaps[Key] = System.currentTimeMillis()
            return true
        }
        return false
    }

    fun clean(Key: Int, delay: Long) {
        postDelayed({
            lastTimeMaps[Key] = INIT_TIME
        }, delay)
    }


    fun clean(Key: Int): Boolean {
        lastTimeMaps[Key].let {
            return if (it == null) {
                false
            } else {
                lastTimeMaps[Key] = INIT_TIME
                true
            }
        }
    }

    /**
     * 用于统一处理消息，减少Handler创建
     **/
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        weakReferenceHashMap[msg.what]?.get()?.observation(msg)
    }


    fun addDataObserver(Key: Int, observer: (msg: Message) -> Unit): Boolean {
        if (weakReferenceHashMap.containsKey(Key))
            return false
        weakReferenceHashMap[Key] = WeakReference(ObserverNwqImpl(observer))
        return true
    }

    fun addDataObserver(Key: Int, observer: ObserverNwq<Message>): Boolean {
        if (weakReferenceHashMap.containsKey(Key))
            return false
        weakReferenceHashMap[Key] = WeakReference(observer)
        return true
    }

    fun hasKey(Key: Int): Boolean {
        weakReferenceHashMap[Key]?.get()?.let { return true }
        return false
    }

    fun remove(Key: Int) {
        weakReferenceHashMap.remove(Key)
    }

    /**
     * 提交运行方法到UI线程
     **/
    fun runUiThread(method: () -> Unit) {
        post(method)
    }


    /**
     * 倒计时操作
     * 只会保持一个倒计时，会清除前面一个
     **/
    fun startCountDown(
        startTime: Int,//每次减少一
        Interval: Int,//
        observer: (nowCount: Int) -> Unit
    ): Boolean {
        if (weakReferenceHashMap.containsKey(COUNT_DOWN)) {
            remove(COUNT_DOWN)
        }
        val messageObserver = object : ObserverNwq<Message> {
            override fun observation(oldMsg: Message) {
                val newMsg = Message()
                newMsg.what = oldMsg.what
                newMsg.arg1 = oldMsg.arg1 - 1
                observer(newMsg.arg1)
                if (newMsg.arg1 > 0) {
                    sendMessageDelayed(newMsg, Interval.toLong())
                }
            }
        }
        weakReferenceHashMap[COUNT_DOWN] = WeakReference(messageObserver)
        val msg = Message()
        msg.what = COUNT_DOWN
        msg.arg1 = startTime
        sendMessageDelayed(msg, Interval.toLong())
        return true
    }

    /**
     * 倒计时操作
     * 只会保持一个倒计时，会清除前面一个
     **/
    fun startCountDown(
        startTime: Int,
        Interval: Int,
        observer: ObserverNwq<Int>
    ): Boolean {
        if (weakReferenceHashMap.containsKey(COUNT_DOWN)) {
            remove(COUNT_DOWN)
        }
        val messageObserver = object : ObserverNwq<Message> {
            override fun observation(oldMsg: Message) {
                val newMsg = Message()
                newMsg.what = oldMsg.what
                newMsg.arg1 = oldMsg.arg1 - 1
                observer.observation(newMsg.arg1)
                if (newMsg.arg1 > 0) {
                    sendMessageDelayed(newMsg, Interval.toLong())
                }
            }
        }
        weakReferenceHashMap[COUNT_DOWN] = WeakReference(messageObserver)
        val msg = Message()
        msg.what = COUNT_DOWN
        msg.arg1 = startTime
        sendMessageDelayed(msg, Interval.toLong())
        return true
    }


}