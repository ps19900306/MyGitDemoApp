package com.nwq.code.liferecord.ui.anchor.text

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.ningwenqiang.glory.toollibrary.fragment.BasicFragment
import com.ningwenqiang.glory.toollibrary.observer.DataInterface
import com.nwq.code.liferecord.R
import com.nwq.code.liferecord.data_base.bean.anchor.ContentType
import com.nwq.code.liferecord.ui.anchor.AnchorLiveData
import kotlinx.android.synthetic.main.fragment_text.*


class TextFragment : BasicFragment() {

    private lateinit var anchorLiveData: DataInterface<AnchorLiveData>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DataInterface<*>) {
            anchorLiveData = context as DataInterface<AnchorLiveData>
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_text
    }

    override fun initView(root: View) {
        anchorLiveData.getData().setContentType(ContentType.TEXT)
        input_edit.doAfterTextChanged {
            anchorLiveData.getData().setContent(it.toString())
        }
        input_edit.requestFocus()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }


}