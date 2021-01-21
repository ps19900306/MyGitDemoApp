package com.ningwenqiang.glory.toollibrary.executor

import java.lang.Exception
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock

/**
create by: 86136
create time: 2021/1/4 10:54
Function description:
 */

class RunnableTimeLimit(
    val executeTimeOut: Int,
    val lock: ReentrantLock,
    val observer: () -> Unit
) : Runnable {

    val condition = lock.newCondition()

    override fun run() {
        lock.isLocked
        try {
            condition.await(30, TimeUnit.SECONDS)

        } catch (e: Exception) {

        }


    }


}