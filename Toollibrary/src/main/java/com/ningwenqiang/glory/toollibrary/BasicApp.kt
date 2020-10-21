package com.ningwenqiang.glory.toollibrary

import android.app.Application
import android.content.Context

/**
create by: 86136
create time: 2020/9/28 13:46
Function description:
 */
class BasicApp : Application() {


    companion object {
        var context: Context? = null
        val mHandler by lazy {
            NwqHandler()
        }
    }


    override fun onCreate() {
        super.onCreate()
        context = this

    }
}