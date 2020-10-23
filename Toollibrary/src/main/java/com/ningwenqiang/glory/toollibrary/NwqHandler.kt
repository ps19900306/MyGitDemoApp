package com.ningwenqiang.glory.toollibrary

import android.os.Handler
import android.os.Message
import com.ningwenqiang.glory.toollibrary.log.L
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwq
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwqImpl
import java.lang.ref.WeakReference
import java.util.HashMap

/**
create by: 86136
create time: 2020/9/28 14:19
Function description:
 */
class NwqHandler : Handler() {

    private val weakReferenceHashMap = HashMap<Int, WeakReference<out ObserverNwq<Message>>>()
    //用于做延迟控制的
    private val lastTimeMaps = HashMap<Int, Long>()

    //这里需要统一存放Key已防止用冲突
    companion object {
        private const val INTERVAL_TIME = 3000L
        private const val INIT_TIME = 0L
        const val  CLEAN_DELAY = 500
        const val TRTC_CALL = 1021
    }


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



}