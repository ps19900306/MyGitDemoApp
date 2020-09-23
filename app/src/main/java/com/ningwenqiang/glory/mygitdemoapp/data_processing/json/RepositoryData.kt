package com.ningwenqiang.glory.mygitdemoapp.data_processing.json

/**
create by: 86136
create time: 2020/9/23 10:04
Function description:
 */
open interface RepositoryData {
    fun isSameData(repositoryData: RepositoryData?): Boolean //判断是不是同一个数据的
}
