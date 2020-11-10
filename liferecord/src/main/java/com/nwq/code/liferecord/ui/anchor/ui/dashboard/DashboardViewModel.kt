package com.nwq.code.liferecord.ui.anchor.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nwq.code.liferecord.utils.DataTimeUtils

import java.lang.StringBuilder

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val stringBuilder=StringBuilder()
        stringBuilder.append(DataTimeUtils.getCurrentTime(DataTimeUtils.DATE_FORMAT_Mi1)+"\n")
        stringBuilder.append(DataTimeUtils.getCurrentTime(DataTimeUtils.DATE_FORMAT_Mi2)+"\n")
        stringBuilder.append(DataTimeUtils.getCurrentTime(DataTimeUtils.DATE_FORMAT_S1)+"\n")
        value=stringBuilder.toString()
    }
    val text: LiveData<String> = _text
}