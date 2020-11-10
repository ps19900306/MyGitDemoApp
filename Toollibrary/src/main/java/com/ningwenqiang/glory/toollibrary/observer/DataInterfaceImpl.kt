package com.ningwenqiang.glory.toollibrary.observer

import android.os.Message

/**
create by: 86136
create time: 2020/9/28 14:24
Function description:
 */

class DataInterfaceImpl<T>(val observer: () -> T) : DataInterface<T> {

    override fun getData(): T {
        return observer()
    }

}