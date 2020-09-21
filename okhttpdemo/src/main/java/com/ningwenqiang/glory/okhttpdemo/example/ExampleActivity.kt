package com.ningwenqiang.glory.okhttpdemo.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ningwenqiang.glory.okhttpdemo.R
import okhttp3.OkHttpClient
//OkHttp简单例子
class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        val builder = OkHttpClient()
    }



}