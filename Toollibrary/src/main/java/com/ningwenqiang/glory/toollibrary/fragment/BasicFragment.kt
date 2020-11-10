package com.ningwenqiang.glory.toollibrary.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwq
import com.ningwenqiang.glory.toollibrary.utils.PermissionUtils

/**
create by: 86136
create time: 2020/9/29 15:16
Function description:
 */
abstract class BasicFragment : Fragment() {

    protected lateinit var rootView: View
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(getLayoutId(), container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData(savedInstanceState)
    }


    abstract fun getLayoutId(): Int


    abstract fun initView(root: View)

    abstract fun initData(savedInstanceState: Bundle?)

    protected fun checkPermission(permissions: Array<String>, observer: ObserverNwq<Boolean>) {
        activity?.let { PermissionUtils.checkPermission(it, permissions, observer) }
    }

}

