package com.ningwenqiang.glory.toollibrary.observer

import android.os.Message

/**
create by: 86136
create time: 2020/9/28 14:24
Function description:
 */

class ObserverNwqImpl<T>(val observer: (T) -> Unit) : ObserverNwq<T> {

    override fun observation(t: T) {
        observer(t)
    }
}