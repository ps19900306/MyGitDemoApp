package com.nwq.code.kotlintestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nwq.code.kotlintestapplication.grammar.IdiomaticUsage

class MainActivity : AppCompatActivity() {

    suspend inline fun awaitBoolean(): Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val idiomaticUsage =
            IdiomaticUsage()
        idiomaticUsage.foo(b = "dd")
    }
}
