package com.cy.aho.common.db;

import androidx.room.Room;

import com.cy.aho.MyApplication;

/**
 * @创建者 CY
 * @创建时间 2020/5/14 11:14
 * @描述
 */
public class AppDatabaseUtil {
    private static MyDataBase database;

    public static MyDataBase getDatabase(){
        if (database==null){
            synchronized (AppDatabaseUtil.class){
                if (database==null){
                    database= Room.databaseBuilder(MyApplication.getInstance(),
                            MyDataBase.class, "mdb").build();
                    return database;
                }else {
                    return database;
                }
            }
        }else {
            return database;
        }
    }
}
