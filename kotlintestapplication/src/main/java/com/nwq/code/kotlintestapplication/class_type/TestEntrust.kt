package com.nwq.code.kotlintestapplication.class_type

import kotlin.properties.Delegates

/**
create by: 86136
create time: 2020/9/25 17:05
Function description:
 */
interface Base {
    fun printMessage()
    fun printMessageLine()
}

class BaseImpl(val x: Int) : Base {
    override fun printMessage() { print(x) }
    override fun printMessageLine() { println(x) }
}

class Derived(b: Base) : Base by b {
    override fun printMessage() { print("abc") }
}

class User {
    var name: String by Delegates.observable("<no name>") {
            prop, old, new ->
        println("$old -> $new")
    }
}

fun main() {
    val user = User()
    user.name = "first"
    user.name = "second"
}