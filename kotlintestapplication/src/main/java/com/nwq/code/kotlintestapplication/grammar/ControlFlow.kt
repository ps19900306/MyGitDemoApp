package com.nwq.code.kotlintestapplication.grammar

/**
create by: 86136
create time: 2020/9/22 10:54
Function description:
 */
class ControlFlow {

    fun test1(a: Int, b: Int) {

        // 传统用法
        var max = a
        if (a < b) max = b

        // With else
        if (a > b) {
            max = a
        } else {
            max = b
        }
        // 作为表达式
        max = if (a > b) a else b
    }

    fun test2(a: Int, b: Int) {
        val max = if (a > b) {
            print("Choose a")
            a
        } else {
            print("Choose b")
            b
        }
    }

    fun test3(x: Int) {
        when (x) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            else -> { // 注意这个块
                print("x is neither 1 nor 2")
            }
        }
    }

    fun test4() {
        loop@ for (i in 1..100) {
            for (j in 1..100) {
                //            if (……) break@loop
            }
        }
    }


    fun test5() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // 非局部直接返回到 test5() 的调用者
            print(it)
        }
        println("this point is unreachable")
    }

    fun test6() {
        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
            print(it)
        }
        print(" done with explicit label")
    }

    fun test7() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
            print(it)
        }
        print(" done with implicit label")
    }


    open class Rectangle {
        open fun draw() { /* …… */ }
    }

    interface Polygon {
        fun draw() { /* …… */ } // 接口成员默认就是“open”的
    }

    class Square() : Rectangle(), Polygon {
        // 编译器要求覆盖 draw()：
        override fun draw() {
            super<Rectangle>.draw() // 调用 Rectangle.draw()
            super<Polygon>.draw() // 调用 Polygon.draw()
        }
    }

    /**
     *
     一般地，属性声明为非空类型必须在构造函数中初始化。
     然而，这经常不方便。例如：属性可以通过依赖注入来初始化， 或者在单元测试的 setup 方法中初始化。
      这种情况下，你不能在构造函数内提供一个非空初始器。 但你仍然想在类体中引用该属性时避免空检测。
    为处理这种情况，你可以用 lateinit 修饰符标记该属性：
     */
    lateinit var subject:IdiomaticUsage
//    @SetUp fun setup() {
//        subject = IdiomaticUsage()
//    }
//
//    @Test fun test() {
//        subject.test1() // 直接解引用
//    }

    //要检测一个 lateinit var 是否已经初始化过，请在该属性的引用上使用 .isInitialized：
//    if (foo::bar.isInitialized) {
//        println(foo.bar)
//    }
}