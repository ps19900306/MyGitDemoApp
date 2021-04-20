package com.nwq.code.liferecord.ui.anchor.capture

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.cjt2325.cameralibrary.JCameraView
import com.cjt2325.cameralibrary.JCameraView.BUTTON_STATE_BOTH
import com.cjt2325.cameralibrary.listener.JCameraListener
import com.ningwenqiang.glory.toollibrary.fragment.BasicFragment
import com.ningwenqiang.glory.toollibrary.observer.DataInterface
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwqImpl
import com.ningwenqiang.glory.toollibrary.utils.FileUtils
import com.nwq.code.liferecord.R
import com.nwq.code.liferecord.data_base.bean.anchor.ContentType
import com.nwq.code.liferecord.ui.anchor.AnchorLiveData

class CaptureFragment : BasicFragment() {

    private lateinit var anchorLiveData: DataInterface<AnchorLiveData>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DataInterface<*>) {
            anchorLiveData = context as DataInterface<AnchorLiveData>
        } else {

        }
    }

    private val list = arrayOf(
        "android.permission.FLASHLIGHT",
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_SETTINGS
    )

    val jCameraView: JCameraView by lazy {
        rootView.findViewById<JCameraView>(R.id.jcameraview)
    }

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_capture
    }

    override fun initView(root: View) {
    }

    override fun onResume() {
        super.onResume()
        jCameraView.onResume()

    }

    override fun onPause() {
        super.onPause()
        jCameraView.onPause()
    }

    //  notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
    override fun initData(savedInstanceState: Bundle?) {
        checkPermission(list, ObserverNwqImpl {
            if (it) {
            } else {
            }
        })

        //设置视频保存路径
        jCameraView.setSaveVideoPath(FileUtils.getJCameraRootDirector())

        //设置只能录像或只能拍照或两种都可以（默认两种都可以）
        jCameraView.setFeatures(BUTTON_STATE_BOTH);
        //设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);

        //JCameraView监听
        jCameraView.setJCameraLisenter(object : JCameraListener {
            override fun captureSuccess(bitmap: Bitmap) {
                FileUtils.saveBitmap(bitmap)?.let {
                    anchorLiveData.getData().setContent(it)
                    anchorLiveData.getData().setContentType(ContentType.PICTURE)
                }
            }

            override fun recordSuccess(
                url: String,
                firstFrame: Bitmap
            ) {

                anchorLiveData.getData().setContent(url)
                anchorLiveData.getData().setContentType(ContentType.AUDIO)
            }
        })
    }


}
