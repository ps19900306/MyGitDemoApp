package com.nwq.code.kotlintestapplication.coroutines

import com.ningwenqiang.glory.toollibrary.log.L
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
create by: 86136
create time: 2020/9/28 16:42
Function description:
 */
class CoroutinesBasic {

    fun main() = runBlocking<Unit> { // this: CoroutineScope
        launch {
            delay(200L)
            println("Task from runBlocking")//2
        }

        coroutineScope { // 创建一个协程作用域
            launch(Dispatchers.Main) {
                delay(500L)
                println("Task from nested launch")//3
            }

            delay(100L)
            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出1
        }

        println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出4
    }

    fun test3() = runBlocking {
        L.d("$coroutineContext", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
        launch {
            delay(100L)
            Thread.currentThread()
            L.d("$coroutineContext", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
            L.d("launch", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
        }
        GlobalScope.launch() {
            delay(100L)
            L.d("$coroutineContext", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
            L.d("GlobalScope.launch", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
        }

        coroutineScope {
            launch {
                delay(300L)
                L.d("$coroutineContext", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
                L.d("coroutineScope.launch", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
            }
            delay(500L)
            L.d("$coroutineContext", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
            L.d("coroutineScope", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
        }
        launch {
            delay(100L)
            L.d("$coroutineContext", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
            L.d("launch2", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
        }

        L.d("$coroutineContext", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
        L.d("Coroutine scope is over", "test3", "CoroutinesBasic", "nwq", "2020/9/29");
    }


    fun test1() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程已在等待时主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    }

    fun test2() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L)
            println("World!")
        }
        println("Hello,") // 主线程中的代码会立即执行
        runBlocking {     // 但是这个表达式阻塞了主线程
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
        }
    }


    suspend fun test4() {
        val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        job.join() // 等待直到子协程执行结束
    }

    fun test5() {
        GlobalScope.launch {
            val job = launch {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // 延迟一段时间
            println("main: I'm tired of waiting!")
            job.cancel() // 取消该作业
            job.join() // 等待作业执行结束
            println("main: Now I can quit.")
        }
    }

    fun test6() {
        GlobalScope.launch(Dispatchers.IO) {
            val result = withTimeoutOrNull(1300L) {
                repeat(1000) {
                    it
                    println("I'm sleeping $it ...")
                    delay(500L)
                }
                "Done" // 在它运行得到结果之前取消它
            }
            println("Result is $result")
        }
        return
    }


    fun test7() = runBlocking<Unit> {
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("The answer is ${one + two}")
        }
        println("Completed in $time ms")
    }

    fun test8() = runBlocking<Unit> {
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }


    fun test9() = runBlocking<Unit> {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
            val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
            // some computation
            one.start() // start the first one
            two.start() // start the second one
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }




    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        return 29
    }

    fun somethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

    // somethingUsefulTwoAsync 函数的返回值类型是 Deferred<Int>
    fun somethingUsefulTwoAsync() = GlobalScope.async {
        doSomethingUsefulTwo()
    }

    // 注意，在这个示例中我们在 `main` 函数的右边没有加上 `runBlocking`
    fun test10() {
        val time = measureTimeMillis {
            // 我们可以在协程外面启动异步执行
            val one = somethingUsefulOneAsync()
            val two = somethingUsefulTwoAsync()
            // 但是等待结果必须调用其它的挂起或者阻塞
            // 当我们等待结果的时候，这里我们使用 `runBlocking { …… }` 来阻塞主线程
            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        }
        println("Completed in $time ms")
    }

    fun test11() = runBlocking<Unit> {
        try {
            failedConcurrentSum()
        } catch(e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    suspend fun failedConcurrentSum(): Int = coroutineScope {
        val one = async<Int> {
            try {
                delay(Long.MAX_VALUE) // Emulates very long computation
                42
            } finally {
                println("First child was cancelled")
            }
        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }

    fun test13()= runBlocking<Unit> {
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }


    fun  test14() = runBlocking<Unit> {
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
        launch() { // context of the parent, main runBlocking coroutine
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
    }

    fun test15() {
        newSingleThreadContext("Ctx1").use { ctx1 ->
            newSingleThreadContext("Ctx2").use { ctx2 ->
                runBlocking(ctx1) {
                    L.d("Started in ctx1", "test15", "CoroutinesBasic", "nwq", "2020/9/29");
                    withContext(ctx2) {
                        L.d("Working in ctx2", "test15", "CoroutinesBasic", "nwq", "2020/9/29");
                    }
                    L.d("Back to ctx1", "test15", "CoroutinesBasic", "nwq", "2020/9/29");
                }
            }
        }
    }

    fun test16() = runBlocking<Unit> {
        println("My job is ${coroutineContext[Job]}")
    }

    fun test17() = runBlocking<Unit> {
        // launch a coroutine to process some kind of incoming request
        val request = launch {
            // it spawns two other jobs, one with GlobalScope
            GlobalScope.launch {
                println("job1: I run in GlobalScope and execute independently!")
                delay(1000)
                println("job1: I am not affected by cancellation of the request")
            }
            // and the other inherits the parent context
            launch {
                delay(100)
                println("job2: I am a child of the request coroutine")
                delay(1000)
                println("job2: I will not execute this line if my parent request is cancelled")
            }
        }
        delay(500)
        request.cancel() // cancel processing of the request
        delay(1000) // delay a second to see what happens
        println("main: Who has survived request cancellation?")
    }

    fun test() {

    }

}