package com.ningwenqiang.glory.toollibrary

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.ningwenqiang.glory.toollibrary.activity.ActivityStackManager
import java.io.FileInputStream

/**
create by: 86136
create time: 2020/9/28 13:46
Function description:
 */
open class BasicApp : Application() {

    companion object {
        lateinit var context: Context
        val mHandler by lazy {
            NwqHandler()
        }

        //优先传Activity没有就传递BasicApp
        fun getContextActivity(): Context {
            return if (ActivityStackManager.getTopActivity() == null) context
            else ActivityStackManager.getTopActivity()!!
        }

        fun getString(id: Int): String {
            return getContextActivity().getString(id)
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}