package com.ningwenqiang.glory.mygitdemoapp.data_repository

import com.ningwenqiang.glory.toollibrary.observer.ObserverNwq

interface RepositoryOperation<T : RepositoryData> {

    fun LoadData(callBack: ObserverNwq<List<T>?>?) //刚开始时候的加载数据


    fun LoadMoreData(flag: Any?, callBack: ObserverNwq<List<T>?>?) //加载更多数据

}