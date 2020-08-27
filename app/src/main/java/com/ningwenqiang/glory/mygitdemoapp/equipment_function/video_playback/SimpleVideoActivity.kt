package com.ningwenqiang.glory.mygitdemoapp.equipment_function.video_playback

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ningwenqiang.glory.mygitdemoapp.R
import com.ningwenqiang.glory.mygitdemoapp.databinding.ActSimpleVideoBinding


class SimpleVideoActivity : AppCompatActivity() {


    private val viewBinding by lazy {
        DataBindingUtil.inflate<ActSimpleVideoBinding>(
            layoutInflater, R.layout.act_simple_video, null, false
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("fdfsdf", "gfdgdf");
        setContentView(viewBinding.root)
        var uri: Uri? = Uri.parse("https://img.quzhao.com/uploads/oss/video/XEaDdxeQWQ.mp4")
        viewBinding.videoView.setMediaController(MediaController(this))
        viewBinding.videoView.setVideoURI(uri)
        viewBinding.videoView.setOnCompletionListener(MyPlayerOnCompletionListener());
        //开始播放视频
        viewBinding.videoView.start();
    }

    internal class MyPlayerOnCompletionListener : OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer?) {

        }

    }

}
