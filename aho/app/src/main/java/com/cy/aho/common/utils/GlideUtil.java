package com.cy.aho.common.utils;

import android.Manifest;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @创建者 CY
 * @创建时间 2019/12/26 14:14
 * @描述
 */
public class GlideUtil {
    private final static String TAG="GlideUtil";
    public static void load(Context context, String url, ImageView imageView){
        if (context==null||url==null||imageView==null){
            Log.e(TAG,"参数不能为null");
            return;
        }

        if (CheckPermissionUtils.checkPermission(context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}).length==0){
            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
        }else {
            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
        }
    }


}
