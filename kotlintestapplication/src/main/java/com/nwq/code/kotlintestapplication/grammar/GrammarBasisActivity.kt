package com.nwq.code.kotlintestapplication.grammar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nwq.code.kotlintestapplication.R

class GrammarBasisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_basis)
        val ss = mutableListOf<Int>(19, 22, 52, 66, 38, 48, 76, 3, 6)
        ss.forEach {
            fun(a: Int) {
                if (a == 52) return
                print(a)
            }
        }
        val j12:Nothing?= null
        var x12: String? = j12
        var x122: String =" null"
        var s1: MutableList<String>? = null
        val list1: List<Nothing>? = null
        val map = mapOf(1 to "A", 2 to "B", 3 to "C")
        val x1 = s1?.size ?: "12"
    }


}