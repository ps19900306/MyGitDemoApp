package com.ningwenqiang.glory.okhttpdemo.log

import com.ningwenqiang.glory.toollibrary.log.L
import okhttp3.*



class LogInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        val mediaType = response.body!!.contentType()
        val content = response.body!!.string()
        L.d(request.toString(), "Start", "LogInterceptor","" , "");
        val method: String = request.method
        if ("POST" == method) {
            val sb = StringBuilder()
            if (request.body is FormBody) {
                val body = request.body as FormBody
                for (i in 0 until body.size) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",")
                }
                sb.delete(sb.length - 1, sb.length)
                L.d("| RequestParams:{$sb}", "Ing", "LogInterceptor", "nwq", "");
            }
        }
        L.d(content, "Ing", "LogInterceptor", "nwq", "");
        L.d( "----------: $duration 毫秒----------", "End", "LogInterceptor", "nwq", "");
        return response.newBuilder()
            .body(ResponseBody.create(mediaType, content))
            .build()
    }
}