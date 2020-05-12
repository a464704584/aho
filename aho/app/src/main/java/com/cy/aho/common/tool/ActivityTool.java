package com.cy.aho.common.tool;

import android.app.Activity;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @创建者 CY
 * @创建时间 2020/4/27 18:04
 * @描述
 */
public class ActivityTool {

    public static void noActionBar(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    public static void noActionBar(AppCompatActivity activity){
        activity.getSupportActionBar().hide();
    }



}
