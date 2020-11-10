package com.ningwenqiang.glory.mygitdemoapp.ui_function.notify

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Environment
import android.os.IBinder
import com.ningwenqiang.glory.toollibrary.log.L
import java.io.IOException

class MediaService : Service() {



    private val mBinder by lazy {
        Binder()
    }


    private val mMediaPlayer by lazy {
        MediaPlayer()
    }

    private var i = 0

    //歌曲路径
    private val musicPath = arrayOf<String>(
        Environment.getExternalStorageDirectory().toString() + "/Sounds/a1.mp3",
        Environment.getExternalStorageDirectory().toString() + "/Sounds/a2.mp3",
        Environment.getExternalStorageDirectory().toString() + "/Sounds/a3.mp3",
        Environment.getExternalStorageDirectory().toString() + "/Sounds/a4.mp3"
    )


    override fun onCreate() {
        super.onCreate()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        L.i(intent?.getIntExtra("x1x1",15).toString(), "onStartCommand", "MediaService", "nwq", "2020/10/29");
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }


    inner class MyBinder : Binder() {
        /**
         * 获取歌曲长度
         */
        fun getProgress(): Int {
            return mMediaPlayer.duration
        }

        /**
         * 获取播放位置
         */
        fun getPlayPosition(): Int {
            return mMediaPlayer.currentPosition
        }

        /**
         * 播放指定位置
         */
        fun seekToPosition(msec: Int) {
            mMediaPlayer.seekTo(msec)
        }

        /**
         * 播放音乐
         */
        fun playMusic() {
            if (!mMediaPlayer.isPlaying) {
                //如果还没开始播放，就开始
                mMediaPlayer.start()
            }
        }

        /**
         * 暂停播放
         */
        fun pauseMusic() {
            if (mMediaPlayer.isPlaying) {
                //如果还没开始播放，就开始
                mMediaPlayer.pause()
            }
        }


        /**
         * 下一首
         */
        fun nextMusic() {
            if (mMediaPlayer != null && i < 4 && i >= 0) {
                //切换歌曲reset()很重要很重要很重要，没有会报IllegalStateException
                mMediaPlayer.reset()
                iniMediaPlayerFile(i + 1)
                //这里的if只要是为了不让歌曲的序号越界，因为只有4首歌
                if (i == 2) {
                } else {
                    i = i + 1
                }
                playMusic()
            }
        }

        /**
         * 上一首
         */
        fun preciousMusic() {
            if (mMediaPlayer != null && i < 4 && i > 0) {
                mMediaPlayer.reset()
                iniMediaPlayerFile(i - 1)
                if (i == 1) {
                } else {
                    i = i - 1
                }
                playMusic()
            }
        }


        /**
         * 关闭播放器
         */
        fun closeMedia() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop()
                mMediaPlayer.release()
            }
        }

    }

    /**
     * 添加file文件到MediaPlayer对象并且准备播放音频
     */
    private fun iniMediaPlayerFile(dex: Int) {
        //获取文件路径
        try {
            //此处的两个方法需要捕获IO异常
            //设置音频文件到MediaPlayer对象中
            mMediaPlayer.setDataSource(musicPath[dex])
            //让MediaPlayer对象准备
            mMediaPlayer.prepare()
        } catch (e: IOException) {
            L.e("设置资源，准备阶段出错", "iniMediaPlayerFile", "MediaService", "nwq", "2020/10/29")
            e.printStackTrace()
        }
    }


}
