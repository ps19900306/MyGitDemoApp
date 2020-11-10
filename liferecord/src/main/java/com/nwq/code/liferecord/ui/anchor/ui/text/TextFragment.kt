package com.nwq.code.liferecord.ui.anchor.ui.text

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ningwenqiang.glory.toollibrary.fragment.BasicFragment
import com.ningwenqiang.glory.toollibrary.observer.DataInterface
import com.nwq.code.liferecord.R
import com.nwq.code.liferecord.ui.anchor.ui.AnchorLiveData
import kotlinx.android.synthetic.main.fragment_text.*


class TextFragment : BasicFragment() {

    private lateinit var anchorLiveData: DataInterface<AnchorLiveData>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DataInterface<*>) {
            anchorLiveData = context as DataInterface<AnchorLiveData>
        } else {

            //TODO 需要做异常处理
        }
    }



    override fun getLayoutId(): Int {
      return  R.layout.fragment_text
    }

    override fun initView(root: View) {

    }

    override fun initData(savedInstanceState: Bundle?) {

    }


}