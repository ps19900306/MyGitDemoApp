package com.nwq.code.sqlit3library

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ningwenqiang.glory.toollibrary.activity.ActivityStackManager

/**
create by: 86136
create time: 2020/10/22 17:17
Function description:
 */
class DatabaseHelper(
   val context: Context,
   val name: String,
   val factory: SQLiteDatabase.CursorFactory,
   val version: Int
) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase) {
        val sql = "create table user(name varchar(20))"
        db.execSQL(sql)

    }


    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {


    }



}