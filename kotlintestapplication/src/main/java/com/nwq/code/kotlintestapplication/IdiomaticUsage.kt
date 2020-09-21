package com.nwq.code.kotlintestapplication

/**
create by: 86136
create time: 2020/9/19 14:39
Function description:
 */
class IdiomaticUsage {

    object IdiomaticUsage {
        val name = "Name"
    }


    fun foo(a: Int = 0, b: String = "") {

    }

    //for (i in 1..100) { …… }  // 闭区间：包含 100
    //for (i in 1 until 100) { …… } // 半开区间：不包含 100
    //for (x in 2..10 step 2) { …… }
    //for (x in 10 downTo 1) { …… }
    //if (x in 1..10) { …… }
    val list = listOf("a", "b", "c")
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)


    val p: SimpleGrammarBasis by lazy {
        //当第一获取这个字段的值的时候才会取加载这个方法
        SimpleGrammarBasis()
    }


    fun SimpleGrammarBasis.spaceToCamelCase(ss: Int) {

    }


}