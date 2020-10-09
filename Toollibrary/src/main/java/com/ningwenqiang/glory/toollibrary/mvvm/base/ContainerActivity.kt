package com.ningwenqiang.glory.toollibrary.mvvm.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

/**
create by: 86136
create time: 2020/9/30 14:43
Function description:
 */
class ContainerActivity : AppCompatActivity() {
    companion object {
        private val FRAGMENT_TAG = "content_fragment_tag"
        val FRAGMENT = "fragment"
        val BUNDLE = "bundle"
    }

    protected var mFragment: WeakReference<Fragment>? = null


}