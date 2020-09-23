package com.nwq.code.kotlintestapplication.parameter

/**
create by: 86136
create time: 2020/9/21 17:48
Function description:
 */
class NormalParameter {


    val oneMillion:Int = 1_000_000

    object Unit {
        // Unit类型是一个object对象类型
        override fun toString() = "kotlin.Unit" // toString函数返回值
    }

    fun unitExample(): kotlin.Unit {
        println("test,Unit")
    }

    fun unitExample1(): kotlin.Unit {
        println("test,Unit")
    }

    fun unitExample2() {
        println("test,Unit")
        return kotlin.Unit
    }

    fun unitExample3() {
        println("test,Unit")
    }


}