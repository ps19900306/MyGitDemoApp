package com.nwq.code.kotlintestapplication.class_type

import com.nwq.code.kotlintestapplication.grammar.ControlFlow
import kotlin.reflect.KProperty

/**
create by: 86136
create time: 2020/9/25 10:45
Function description:
 */

data class DataClassType(var age: Int, var name: String) {
    // 这个是data类型自动生成了
    //   fun copy(name: String = this.name, age: Int = this.age) = DataClassType(name, age)
    fun test1() {
        val jack = DataClassType(name = "Jack", age = 1)
        val olderJack = jack.copy(age = 2)
    }

    fun test2() {
        val ints: Array<Int> = arrayOf(1, 2, 3)
        val any = Array<Any>(3) { "" }
        copy(ints, any)
    }

    fun copy(from: Array<out Any>, to: Array<Any>) {

    }


    var p: String by Delegate()
    var x1: ControlFlow by Delegate1()

    class Delegate {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${property.name}' to me!"
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("$value has been assigned to '${property.name}' in $thisRef.")
        }
    }

    class Delegate1 {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): ControlFlow {
            return ControlFlow()
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: ControlFlow) {
            println("$value has been assigned to '${property.name}' in $thisRef.")
        }
    }
}

