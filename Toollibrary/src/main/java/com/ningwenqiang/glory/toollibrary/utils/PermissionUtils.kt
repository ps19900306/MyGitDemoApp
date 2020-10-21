package com.ningwenqiang.glory.toollibrary.utils

import android.content.Context
import com.ningwenqiang.glory.toollibrary.activity.ActivityStackManager
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwq
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwqImpl
import com.yanzhenjie.permission.AndPermission

/**
create by: 86136
create time: 2020/10/19 10:40
Function description:
 */
class PermissionUtils {

    companion object {

        fun addDataObserver(
            permissions: Array<String>,
            observer: (result: Boolean) -> Unit
        ) {
            addDataObserver(permissions, ObserverNwqImpl(observer))
        }

        fun addDataObserver(
            permissions: Array<String>,
            observer: ObserverNwq<Boolean>
        ) {
            val activity = ActivityStackManager.getTopActivity()
            if (activity != null) {
                addDataObserver(activity, permissions, observer)
            } else {
                observer.observation(false)
            }
        }


        fun addDataObserver(
            context: Context,
            permissions: Array<String>,
            observer: (result: Boolean) -> Unit
        ) {
            addDataObserver(context, permissions, ObserverNwqImpl(observer))
        }


        fun addDataObserver(
            context: Context,
            permissions: Array<String>, observer: ObserverNwq<Boolean>
        ) {
            if (!AndPermission.hasPermissions(context, permissions)) {
                AndPermission.with(context)
                    .runtime()
                    .permission(permissions)
                    .onGranted {
                        observer.observation(true)
                    }
                    .onDenied {
                        observer.observation(false)
                    }
                    .start()
            }
        }
    }


}