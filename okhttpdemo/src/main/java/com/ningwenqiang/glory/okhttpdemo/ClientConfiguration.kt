package com.ningwenqiang.glory.okhttpdemo

import com.ningwenqiang.glory.okhttpdemo.log.LogInterceptor
import com.ningwenqiang.glory.okhttpdemo.log.NwqHttpLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class ClientConfiguration {


    //  logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    companion object {
        val logInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor(NwqHttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY)

        fun addDefault(builder: OkHttpClient.Builder): OkHttpClient.Builder {
            builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .addNetworkInterceptor(logInterceptor)  //设置打印拦截日志
                .addInterceptor(LogInterceptor()) //自定义的拦截日志，拦截简单东西
            return builder
        }
    }


}