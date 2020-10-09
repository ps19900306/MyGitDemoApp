package com.ningwenqiang.glory.toollibrary.mvvm.base

/**
create by: 86136
create time: 2020/9/30 9:08
Function description:
 */
/**
 * Created by goldze on 2017/6/15.
 */
interface IModel {
    /**
     * ViewModel销毁时清除Model，与ViewModel共消亡。Model层同样不能持有长生命周期对象
     */
    fun onCleared()
}