package com.ningwenqiang.glory.toollibrary.activity

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
create by: 86136
create time: 2020/9/28 14:43
Function description:
.000000000000
 */
abstract class BasicActivity : AppCompatActivity(), ActivityStackManager, TripartiteAssemblyManager {

    init {
        initLifecycle()
    }

    private fun initLifecycle() {
        lifecycle.addObserver(this)
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        super<ActivityStackManager>.onStateChanged(source, event)
        super<TripartiteAssemblyManager>.onStateChanged(source, event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}