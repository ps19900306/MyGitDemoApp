package com.ningwenqiang.glory.toollibrary.activity

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.ningwenqiang.glory.toollibrary.log.L


//第三方控件初始化走此流程
interface TripartiteAssemblyManager : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> L.d("Lifecycle.Event.ON_CREATE", "onStateChanged", "TripartiteAssemblyManager", "nwq", "2020/9/28")
            Lifecycle.Event.ON_DESTROY ->L.d(" Lifecycle.Event.ON_DESTROY", "onStateChanged", "TripartiteAssemblyManager", "nwq", "2020/9/28")
        }
    }
}