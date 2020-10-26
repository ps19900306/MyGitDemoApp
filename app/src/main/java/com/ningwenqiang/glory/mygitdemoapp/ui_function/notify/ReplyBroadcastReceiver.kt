package com.ningwenqiang.glory.mygitdemoapp.ui_function.notify

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.RemoteInput
import com.ningwenqiang.glory.toollibrary.log.L
import com.ningwenqiang.glory.toollibrary.toast.ToastUtil

/**
create by: 86136
create time: 2020/10/26 10:00
Function description:
 */
class ReplyBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(p0: Context, intent: Intent) {
        L.i("收到日志", "onReceive", "ReplyBroadcastReceiver", "nwq", "2020/10/26");
        getMessageText(intent)?.let {
            ToastUtil.showToastLong(it)
        }
    }

    private fun getMessageText(intent: Intent): CharSequence? {
        return RemoteInput.getResultsFromIntent(intent)
            ?.getCharSequence(NotificationFunction.KEY_TEXT_REPLY)
    }
}