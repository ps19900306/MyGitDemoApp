package com.nwq.code.liferecord.data_base

import android.app.Activity
import com.ningwenqiang.glory.toollibrary.activity.ActivityStackManager
import com.nwq.code.liferecord.NwqApp
import com.nwq.code.liferecord.data_base.automatic.DaoSession

/**
create by: 86136
create time: 2020/11/6 15:18
Function description:
 */

interface GetDaoSession {

    fun getDaoSession(): DaoSession {
        return if (this is Activity) {
            (this.application as NwqApp).daoSession
        } else {
            (ActivityStackManager.getContext()?.applicationContext as NwqApp).daoSession
        }
    }
}