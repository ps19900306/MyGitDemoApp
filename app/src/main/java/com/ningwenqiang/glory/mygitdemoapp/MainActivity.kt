package com.ningwenqiang.glory.mygitdemoapp


import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ningwenqiang.glory.mygitdemoapp.ui_function.notify.NotificationFunction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NotificationFunction.createNotificationChannel()
        findViewById<View>(R.id.tv_activity_normal).setOnClickListener {
            //       NotificationFunction.showNotification(R.mipmap.ic_launcher, "nwq", "notification",NormalActivity::class.java)

            NotificationFunction.showNotificationProgress(R.mipmap.ic_launcher, "nwq", "notification")
        }
        findViewById<View>(R.id.tv_activity_full_screen).setOnClickListener {
            val intent =
                Intent("com.example.administrator.myapplication.MyBroadcastReceiver")
            //第一个参数为包的路径，第二个参数为类名
            //第一个参数为包的路径，第二个参数为类名
            intent.component = ComponentName(
                applicationContext,
                "com.ningwenqiang.glory.mygitdemoapp.ui_function.notify.broadcast.MyBroadcastReceiver"
            )
           // intent.addFlags(0x01000000)
            sendBroadcast(intent)
        }
    }

}
