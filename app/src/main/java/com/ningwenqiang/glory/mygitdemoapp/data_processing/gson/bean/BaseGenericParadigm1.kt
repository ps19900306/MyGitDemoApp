package com.ningwenqiang.glory.mygitdemoapp.data_processing.gson.bean

/**
create by: 86136
create time: 2020/9/16 17:28
Function description:
 */
data class BaseGenericParadigm1<T>(
    var code: Int,

    val text: String,

    val data: T
)