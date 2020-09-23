package com.ningwenqiang.glory.mygitdemoapp.data_repository

/**
create by: 86136
create time: 2020/9/23 10:17
Function description:
 */

data class DataRefreshEvent(
    val dataVersion: Int,
    var refreshType: Int,
    val startPosition: Int,
    val itemCount: Int
) {

    companion object {
        const val NoMoreData = -1
        //这四种是刷新类型
        const val SOURCE_DATA = 1
        const val UPDATE_DATA = 2
        const val INSERT_DATA = 3
        const val DELETE_DATA = 4
    }


}