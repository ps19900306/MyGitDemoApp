package com.nwq.code.liferecord.ui.index.ui.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ningwenqiang.glory.toollibrary.fragment.BasicFragment
import com.nwq.code.liferecord.R
import kotlinx.android.synthetic.main.fragment_index.*


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : BasicFragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_index
    }

    override fun initView(root: View) {
        val textView: TextView = root.findViewById(R.id.section_label)
    }

    override fun initData(savedInstanceState: Bundle?) {
        pageViewModel.text.observe(this, Observer<String> {
            section_label.text = it
        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}