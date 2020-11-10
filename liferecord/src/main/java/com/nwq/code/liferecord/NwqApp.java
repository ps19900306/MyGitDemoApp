package com.nwq.code.liferecord;

import android.database.sqlite.SQLiteDatabase;

import com.ningwenqiang.glory.toollibrary.BasicApp;
import com.nwq.code.liferecord.data_base.automatic.DaoMaster;
import com.nwq.code.liferecord.data_base.automatic.DaoSession;

/**
 * create by: 86136
 * create time: 2020/10/23 15:08
 * Function description:
 */
public class NwqApp extends BasicApp {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    /**
     * greendao数据库初始化
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "zbc_test.db");
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取 DaoSession
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
