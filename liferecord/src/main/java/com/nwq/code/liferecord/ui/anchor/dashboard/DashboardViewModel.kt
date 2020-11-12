package com.nwq.code.liferecord.ui.anchor.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import java.lang.StringBuilder

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val stringBuilder = StringBuilder()
        value = stringBuilder.toString()
    }
    val text: LiveData<String> = _text
}