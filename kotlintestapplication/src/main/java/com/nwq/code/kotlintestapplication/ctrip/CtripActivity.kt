package com.nwq.code.kotlintestapplication.ctrip



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.ningwenqiang.glory.toollibrary.activity.BasicActivity
import com.ningwenqiang.glory.toollibrary.log.L
import com.nwq.code.kotlintestapplication.R
import com.nwq.code.kotlintestapplication.coroutines.CoroutinesBasic
import com.nwq.code.kotlintestapplication.grammar.ControlFlow
import kotlinx.coroutines.*

class CtripActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ctrip)
        val cc=CoroutinesBasic();
        cc.test14()

      }

    private fun test() = runBlocking {//启动的协程任务会阻断当前线程，直到该协程执行结束。当协程执行结束之后，页面才会被显示出来。

        repeat(8) {
            L.d(
                "协程执行$it 线程id：${Thread.currentThread().id}",
                "test",
                "CtripActivity",
                "nwq",
                "2020/9/21"
            );
            delay(500)
        }
    }

    private fun test1() = GlobalScope.launch {//从执行结果看出，launch不会阻断主线程。
        repeat(8) {
            L.d(
                "协程执行$it 线程id：${Thread.currentThread().id}",
                "test",
                "CtripActivity",
                "nwq",
                "2020/9/21"
            );
            delay(1000)
        }
    }

    private fun test2() = GlobalScope.launch {//从执行结果看出，launch不会阻断主线程。
        val token = getToken()
        val userInfo = getUserInfo(token)
        setUserInfo(userInfo)
    }

    private fun setUserInfo(userInfo: String) {
        L.d(userInfo, "setUserInfo", "CtripActivity", "nwq", "2020/9/21");
    }

    private suspend fun getToken(): String {
        delay(2000)
        return "token"
    }

    private suspend fun getUserInfo(token: String): String {
        delay(2000)
        return "$token - userInfo"
    }

    private fun test3() = GlobalScope.launch {//从执行结果看出，launch不会阻断主线程。
        val result1 = GlobalScope.async {
            getResult1()
        }
        val result2 = GlobalScope.async {
            getResult2()
        }
        val result = result1.await() + result2.await()
        L.d("result = $result", "test3", "CtripActivity", "nwq", "2020/9/21");
    }

    private suspend fun getResult1(): Int {
        delay(3000)
        return 1
    }

    private suspend fun getResult2(): Int {
        delay(4000)
        return 2
    }

    fun test4() {

        /**
        在 Java 平台数字是物理存储为 JVM 的原生类型，除非我们需要一个可空的引用（如 Int?）或泛型。 后者情况下会把数字装箱。
        注意数字装箱不一定保留同一性:
         */
        val a: Int = 10000
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a

        val b: Int = 1
        val boxedB: Int? = b
        val anotherBoxedB: Int? = b

        println(boxedA === anotherBoxedA) // true
        println(boxedB === anotherBoxedB) // false

//       另一方面，它保留了相等性:
//         val a: Int = 10000
//         println(a == a) // 输出“true”
//         val boxedA: Int? = a
//         val anotherBoxedA: Int? = a
//         println(boxedA == anotherBoxedA) // 输出“true”

    }

    //由于不同的表示方式，较小类型并不是较大类型的子类型。 如果它们是的话，就会出现下述问题：
    fun test5() {
//        // 假想的代码，实际上并不能编译：
        val a = 1 // 一个装箱的 Int (java.lang.Integer)
        val b: Long = a.toLong() // 隐式转换产生一个装箱的 Long (java.lang.Long)
        print(b.equals(a)) // 惊！这将输出“false”鉴于 Long 的 equals() 会检测另一个是否也为 Long
    }

    fun test6() {
        val x = 5 / 2
        //println(x == 2.5) // ERROR: Operator '==' cannot be applied to 'Int' and 'Double'
        println(x == 2)

        val x1 = 5L / 2
        println("x1 == 2L : " + (x1 == 2L))
        println("x1 == 2.5 : " + (x1.toDouble() == 2.5))
        val x2 = 5 / 2.toDouble()
        println(x2 == 2.5)
    }

    fun test7() {
        val text = """
        for (c in "foo")
        print(c)
        """
        println(text)
    }

    //你可以通过 trimMargin() 函数去除前导空格
    fun test8() {
        val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    }

    fun test9() {
        val i = 10
        println("i = $i") // 输出“i = 10”
        val s = "abc"
        println("$s.length is ${s.length}") // 输出“abc.length is 3”
        val price = """
         ${'$'}9.99
         """
        println(price) // 输出“abc.length is 3”
    }

}