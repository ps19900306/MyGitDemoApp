package com.nwq.code.kotlintestapplication

import androidx.constraintlayout.solver.widgets.Rectangle
import java.lang.Integer.parseInt

/**
create by: 86136
create time: 2020/9/19 10:27
Function description:
 */
class SimpleGrammarBasis {


    //顶层变量：
    val PI = 3.14
    var x = 0
    fun incrementX() {
        x += 1
    }


    fun main() {
        println("Hello world!")
    }

    //带有两个 Int 参数、返回 Int 的函数：
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //将表达式作为函数体、返回值类型自动推断的函数
    fun sum1(a: Int, b: Int) = a + b

    //函数返回无意义的值：
    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    fun testVal() {
        val a: Int = 1  // 立即赋值
        val b = 2   // 自动推断出 `Int` 类型
        val c: Int  // 如果没有初始值类型不能省略
        c = 3       // 明确赋值
    }

    fun testVar() {
        var x = 5 // 自动推断出 `Int` 类型
        x += 1
    }


    fun stringTemplate() {
        var a = 1
        // 模板中的简单名称：
        val s1 = "a is $a"//a is 1
        a = 2
        // 模板中的任意表达式：
        val s2 = "${s1.replace("is", "was")}, but now is $a"// a was 1,but now is 2
    }

    fun maxOf1(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    fun maxOf2(a: Int, b: Int) = if (a > b) a else b


    fun printProduct(arg1: String, arg2: String) {
        val x = parseInt(arg1)
        val y = parseInt(arg2)

        // 直接使用 `x * y` 会导致编译错误，因为它们可能为 null
        if (x != null && y != null) {
            // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
            println(x * y)
        } else {
            println("'$arg1' or '$arg2' is not a number")
        }
    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // `obj` 在该条件分支内自动转换成 `String`
            return obj.length
        }

        // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
        return null
    }

    fun getStringLength1(obj: Any): Int? {

        if (obj !is String) return null
        // `obj` 在这一分支自动转换为 `String`
        return obj.length
    }

    fun getStringLength2(obj: Any): Int? {
        // `obj` 在 `&&` 右边自动转换成 `String` 类型
        if (obj is String && obj.length > 0) {
            return obj.length
        }
        return null
    }

    //for 循环
    fun testFor() {

        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }

        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }

        //while 循环
        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }
    }


    fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

    fun testRange(){
        //区间迭代:
        for (y in 1..5) {
            print(y)
        }

        //或数列迭代：
        for (y in 1..10 step 2) {
            print(y)
        }


        //使用 in 运算符来检测某个数字是否在指定区间内：
        val x = 1
        val y = 9
        if (x in 1..y+1) {
            println("fits in range")
        }

       //检测某个数字是否在指定区间外:
        val list = listOf("a", "b", "c")
        if (-1 !in 0..list.lastIndex) {
            println("-1 is out of range")
        }
        if (list.size !in list.indices) {
            println("list size is out of valid list indices range, too")
        }

    }
   //  对集合进行迭代:
//    for (item in items) {
//        println(item)
//    }

//    when {
//        "orange" in items -> println("juicy")
//        "apple" in items -> println("apple is fine too")
//    }
    //使用 lambda 表达式来过滤（filter）与映射（map）集合：
    fun  testLamda(){
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
     //创建基本类及其实例
     //    val rectangle = Rectangle(5.0, 2.0)
     //   val triangle = Triangle(3.0, 4.0, 5.0)
    }
}

