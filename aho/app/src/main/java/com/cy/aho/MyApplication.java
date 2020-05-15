package com.cy.aho;

import android.app.Application;

/**
 * @创建者 CY
 * @创建时间 2020/5/12 11:15
 * @描述
 */
public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }


    public static MyApplication getInstance(){
        return application;
    }

    public synchronized static String getToken(){
        return "";
    }

    public synchronized static void setToken(String token){

    }


}
