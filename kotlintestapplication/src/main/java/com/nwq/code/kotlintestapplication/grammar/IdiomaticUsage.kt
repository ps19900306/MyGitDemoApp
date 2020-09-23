package com.nwq.code.kotlintestapplication.grammar

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

    /**
     *
    如果一个声明有多个修饰符，请始终按照以下顺序安放：
    public / protected / private / internal
    expect / actual
    final / open / abstract / sealed / const
    external
    override
    lateinit
    tailrec
    vararg
    suspend
    inner
    enum / annotation / fun // 在 `fun interface` 中是修饰符
    companion
    inline
    infix
    operator
    data
     */

    fun test1() {
        //对于位运算，没有特殊字符来表示，而只可用中缀方式调用具名函数，例如:
        val x = (1 shl 2) and 0x000FF000
        /**
         * 这是完整的位运算列表（只用于 Int 与 Long）：
        shl(bits) – 有符号左移
        shr(bits) – 有符号右移
        ushr(bits) – 无符号右移
        and(bits) – 位与
        or(bits) – 位或
        xor(bits) – 位异或
        inv() – 位非
         */
    }

    /**
     * 字符字面值用单引号括起来: '1'。 特殊字符可以用反斜杠转义
     * 支持这几个转义序列：\t、 \b、\n、\r、\'、\"、\\ 与 \$
     * 编码其他字符要用 Unicode 转义序列语法：'\uFF00'。
     */

    fun test2() {
        val text = """
        for (c in "foo")
        print(c)
        """
    }
}