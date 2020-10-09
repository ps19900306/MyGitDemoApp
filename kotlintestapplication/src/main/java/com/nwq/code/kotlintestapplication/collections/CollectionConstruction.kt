package com.nwq.code.kotlintestapplication.collections

import java.util.*
import kotlin.collections.HashSet

/**
create by: 86136
create time: 2020/9/28 10:17
Function description:
 */

class CollectionConstruction {

    val numbersSet = setOf("one", "two", "three", "four")

    val emptySet = mutableSetOf<String>()

    val numbersMap1 = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

    val numbersMap2 = mutableMapOf<String, String>().apply { this["one"] = "1"; this["two"] = "2" }

    val empty = emptyList<String>()


    fun test1(){
        val doubled = List(3, { it * 2 })
        println(doubled)
    }

    fun test2(){
        val linkedList = LinkedList<String>(listOf("one", "two", "three"))
        val presizedSet = HashSet<Int>(32)
    }

    fun test3(){
        val sourceList = mutableListOf(1, 2, 3)
        val copyList = sourceList.toMutableList()
        val readOnlyCopyList = sourceList.toList()
        sourceList.add(4)
        println("Copy size: ${copyList.size}")

        //readOnlyCopyList.add(4)             // 编译异常
        println("Read-only copy size: ${readOnlyCopyList.size}")
    }

    fun test4(){
        val numbers = setOf(1, 2, 3)
        println(numbers.map { it * 3 })
        println(numbers.mapIndexed { idx, value -> value * idx })
    }

    fun test5(){
        val numbers = listOf("one", "two", "three", "four")
        println(numbers.associateWith { it.length })
    }


    fun test6(){

    }

    fun test(){

    }
}