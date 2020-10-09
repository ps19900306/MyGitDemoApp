package com.ningwenqiang.glory.okhttpdemo.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ningwenqiang.glory.okhttpdemo.R
import com.ningwenqiang.glory.toollibrary.log.L
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

//OkHttp简单例子
class ExampleActivity : AppCompatActivity() {


    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    lateinit var handler: CoroutineExceptionHandler

    companion object {
        val TAG = "OkhttpActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        handler = CoroutineExceptionHandler { _, exception ->
            run {
                L.e(
                    "exception" + exception.message,
                    "onCreate",
                    "ExampleActivity",
                    "nwq",
                    "2020/9/22"
                );
            }

        }
    }

    fun test1() {
        //创建OkHttpClient对象，这里使用的是Builder设计模式的创建方式
        var client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()
        //创建Request对象
        var request =
            Request.Builder().url("https://www.wanandroid.com/article/list/0/json").build()
        ioScope.launch(handler)
        {
            //同步请求
            var response = client.newCall(request).execute()
        }

    }

    var  x=1
    fun test2() {
        ::x.name
        ::x.get()

        val numberRegex = "\\d+".toRegex()
        println(numberRegex.matches("29"))

        val isNumber = numberRegex::matches
        println(isNumber("29"))

    }
    val client = OkHttpClient()
    val JSON: MediaType = "application/json".toMediaType()


}