package com.nwq.code.kotlintestapplication.frament

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
create by: 86136
create time: 2020/9/28 13:38
Function description:
 */
abstract class BasicFragment : Fragment() {

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}

