package com.ningwenqiang.glory.toollibrary.executor

import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor

/**
create by: 86136
create time: 2021/1/4 10:08
Function description:
 */

class AppExecutors(
    private val diskIO: Executor,
    private val networkIO: ThreadPoolExecutor,
    private val sing: ThreadPoolExecutor,
    private val mainThread: Executor)
{
    fun ss(){
        networkIO.execute {


        }
    }

}