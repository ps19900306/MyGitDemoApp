package com.ningwenqiang.glory.toollibrary.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
create by: 86136
create time: 2020/10/21 16:51
Function description:
 */

abstract class JsonUtil {

    companion object {
        val gson = Gson()

        fun objectToString(obj: DataSign): String {
            return gson.toJson(obj)
        }

        inline fun <reified T> toJsonObject(str: String): T {
            val token = object : TypeToken<T>() {}.type
            return gson.fromJson<T>(str, token)
        }
    }


}