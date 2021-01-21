package com.ningwenqiang.glory.mygitdemoapp


import android.content.ComponentName
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ningwenqiang.glory.mygitdemoapp.ui_function.notify.NotificationFunction
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NotificationFunction.createNotificationChannel()
        findViewById<View>(R.id.tv_activity_normal).setOnClickListener {
            //NotificationFunction.showNotification(R.mipmap.ic_launcher, "nwq", "notification",NormalActivity::class.java)
            NotificationFunction.showNotificationActivityMedia(
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                BitmapFactory.decodeResource(resources, R.mipmap.chat_group)
            )
        }
        tv_activity_normal.text = formatCookMethods1(str,"Nothing")
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

    val str ="http://198.18.37.197:9898/file/download?session=HS_HBCbycldwXlxZo4hFOBfb8yZrWZfuH&share_path_type=2&path=%2FARDOWNLOAD%2FOne.Last.Deal.2018.FINNISH.1080p.BluRay.x264.DTS-CHD%2FOne.Last.Deal.2018.FINNISH.1080p.BluRay.x264.DTS-CHD.mkv"

    private fun formatCookMethods(cookMethodRawString: String,newIp:String): String {
       // val ipRegex = Regex("""((25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)\.){3}(25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)""")
        val sessionRegex = Regex("""session=(\S)share_path_type""")
        val foundMatches = sessionRegex.findAll(cookMethodRawString)
        var formatCookMethods: String = cookMethodRawString
        foundMatches.forEach { result ->
            formatCookMethods = formatCookMethods.replace(result.value,newIp)
        }
        return formatCookMethods
    }

    private fun formatCookMethods1(cookMethodRawString: String,session:String): String {
        val strs= cookMethodRawString.split("?")
        val params= strs[1].split("&")
        val strBuilder= StringBuilder()
        strBuilder.append(strs[0]+"?")
        strBuilder.append("session=$session")
        params.forEach {
            if(!it.contains("session"))
            {
                strBuilder.append("&$it")
            }
        }
        return strBuilder.toString()
    }
}
