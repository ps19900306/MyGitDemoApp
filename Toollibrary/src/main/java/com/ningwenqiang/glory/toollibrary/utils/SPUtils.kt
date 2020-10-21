package com.ningwenqiang.glory.toollibrary.utils

import android.content.Context
import com.ningwenqiang.glory.toollibrary.BasicApp
import com.ningwenqiang.glory.toollibrary.data.DataSign
import com.ningwenqiang.glory.toollibrary.data.JsonUtil

/**
create by: 86136
create time: 2020/10/21 14:38
Function description:
 */
class SPUtils(context: Context, name: String) {

    companion object {
        //这个里面用SP_NAME
    }

    val mSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    val mEditor = mSharedPreferences.edit()

    /**
     * 存储
     */
    fun <T> put(key: String, value: T) {
        when (value) {
            is String -> {
                mEditor.putString(key, value)
            }
            is Boolean -> {
                mEditor.putBoolean(key, value)
            }
            is Float -> {
                mEditor.putFloat(key, value)
            }
            is Int -> {
                mEditor.putInt(key, value)
            }
            is Long -> {
                mEditor.putLong(key, value)
            }
            is DataSign -> {
                mEditor.putString(key, JsonUtil.objectToString(value))
            }
        }
        mEditor.apply()
    }

    /**
     * 获取保存的数据
     */
    fun <T> get(key: String, defaultValue: T): T {
        when (defaultValue) {
            is String -> {
                return mSharedPreferences.getString(key, defaultValue) as T
            }
            is Boolean -> {
                return mSharedPreferences.getBoolean(key, defaultValue) as T
            }
            is Float -> {
                return mSharedPreferences.getFloat(key, defaultValue) as T
            }
            is Int -> {
                return mSharedPreferences.getInt(key, defaultValue) as T
            }
            is Long -> {
                return mSharedPreferences.getLong(key, defaultValue) as T
            }
            else ->
                return defaultValue
        }
    }


    /**
     * //这个支持对象的
     * 获取保存的数据
     */
    inline fun <reified T> getSupportDataSign(key: String, defaultValue: T): T {
        when (defaultValue) {
            is String -> {
                return mSharedPreferences.getString(key, defaultValue) as T
            }
            is Boolean -> {
                return mSharedPreferences.getBoolean(key, defaultValue) as T
            }
            is Float -> {
                return mSharedPreferences.getFloat(key, defaultValue) as T
            }
            is Int -> {
                return mSharedPreferences.getInt(key, defaultValue) as T
            }
            is Long -> {
                return mSharedPreferences.getLong(key, defaultValue) as T
            }
            is DataSign -> {
                val dataStr = mSharedPreferences.getString(key, "")
                return if (!dataStr.isNullOrEmpty()) {
                    val t: T = JsonUtil.toJsonObject(dataStr)
                    t
                } else {
                    defaultValue
                }
            }
            else ->
                return defaultValue
        }
    }

    constructor() : this(BasicApp.context!!, BasicApp.context!!.packageName) {

    }

    constructor(name: String) : this(BasicApp.context!!, name) {

    }


}