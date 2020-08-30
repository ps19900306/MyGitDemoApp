package com.ningwenqiang.glory.okhttpdemo

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class ClientConfiguration {

    companion object {
        fun addDefault(builder: OkHttpClient.Builder): OkHttpClient.Builder {
            builder.connectTimeout(30, TimeUnit.SECONDS)
            builder.readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
            builder.writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
            return builder
        }

    }


}