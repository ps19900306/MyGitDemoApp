package com.ningwenqiang.glory.toollibrary.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwq
import com.ningwenqiang.glory.toollibrary.utils.PermissionUtils
import com.yanzhenjie.permission.AndPermission


/**
create by: 86136
create time: 2020/9/28 14:43
Function description:
.000000000000
 */
abstract class BasicActivity : AppCompatActivity(), ActivityStackManager,
    TripartiteAssemblyManager {

    init {
        initLifecycle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLifecycle();
    }

    private fun checkPermission(permissions: Array<String>, observer: ObserverNwq<Boolean>) {
        PermissionUtils.checkPermission(this, permissions, observer)
    }


    private fun initLifecycle() {
        lifecycle.addObserver(this)
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        super<ActivityStackManager>.onStateChanged(source, event)
        super<TripartiteAssemblyManager>.onStateChanged(source, event)
    }


}