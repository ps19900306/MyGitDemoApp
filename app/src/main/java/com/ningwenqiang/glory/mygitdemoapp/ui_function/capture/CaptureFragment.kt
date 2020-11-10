package com.ningwenqiang.glory.mygitdemoapp.ui_function.capture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ningwenqiang.glory.mygitdemoapp.R
import com.ningwenqiang.glory.toollibrary.fragment.BasicFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CaptureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CaptureFragment : BasicFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_capture
    }

    override fun initView(root: View) {

    }

    override fun initData(savedInstanceState: Bundle?) {

    }

//    需要用到的权限
//    <uses-permission android:name="android.permission.FLASHLIGHT" />
//    <uses-feature android:name="android.hardware.camera" />
//    <uses-feature android:name="android.hardware.camera.autofocus" />
//    <uses-permission android:name="android.permission.RECORD_AUDIO" />
//    <uses-permission android:name="android.permission.CAMERA" />
//    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
//    <uses-permission android:name="android.permission.WRITE_SETTINGS" />


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CaptureFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}