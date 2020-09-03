package com.ningwenqiang.glory.okhttpdemo.log

import com.ningwenqiang.glory.toollibrary.log.L
import okhttp3.logging.HttpLoggingInterceptor

class NwqHttpLogger : HttpLoggingInterceptor.Logger{

    override fun log(s: String) {
        L.d(s, "NwqHttpLogger", "log", "", "")
    }
}
