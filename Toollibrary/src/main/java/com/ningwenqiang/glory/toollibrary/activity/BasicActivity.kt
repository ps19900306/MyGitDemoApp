package com.ningwenqiang.glory.toollibrary.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
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

//    权限格式的样子
//    private val permission = arrayOf(
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.CAMERA,
//        Manifest.permission.RECORD_AUDIO,
//        Manifest.permission.CAMERA,
//        Manifest.permission.RECORD_AUDIO
//    )

    //若是Activity需要权限则实现此方法便可以了
    protected abstract fun getNeedPermissionList(): Array<String>?

    //检测权限通过则走次方法
    protected abstract fun onCheckPermissionResult(success: Boolean)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission();
    }

    private fun checkPermission() {
        getNeedPermissionList()?.let { array ->
            var hasAllPermission = true
            array.forEach {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val result = ActivityCompat.checkSelfPermission(this, it)
                    if (PackageManager.PERMISSION_GRANTED != result) {
                        AndPermission.with(this)
                            .runtime()
                            .permission(it)
                            .onGranted {
                            }
                            .onDenied {
                                hasAllPermission = false
                            }
                            .start()
                    }
                }
            }
            onCheckPermissionResult(hasAllPermission);
        }
    }


    private fun initLifecycle() {
        lifecycle.addObserver(this)
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        super<ActivityStackManager>.onStateChanged(source, event)
        super<TripartiteAssemblyManager>.onStateChanged(source, event)
    }


}