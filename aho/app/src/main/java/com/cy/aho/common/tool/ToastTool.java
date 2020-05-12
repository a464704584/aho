package com.cy.aho.common.tool;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * @创建者 CY
 * @创建时间 2020/4/27 16:07
 * @描述 toast工具
 */
public class ToastTool {

    private static Toast sToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());


    /**
     * 显示短时
     * @param context 上下文
     * @param text    文本
     */
    public static void showShortToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void showShortToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void showShortToast(Context context, int resId, Object... args) {
        showToast(context, resId, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示短时
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void showShortToast(Context context, String format, Object... args) {
        showToast(context, format, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示长时
     * @param context 上下文
     * @param text    文本
     */
    public static void showLongToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时
     * @param context 上下文
     * @param resId   资源Id
     */
    public static void showLongToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时
     * @param context 上下文
     * @param resId   资源Id
     * @param args    参数
     */
    public static void showLongToast(Context context, int resId, Object... args) {
        showToast(context, resId, Toast.LENGTH_LONG, args);
    }

    /**
     * 显示长时吐司
     *
     * @param context 上下文
     * @param format  格式
     * @param args    参数
     */
    public static void showLongToast(Context context, String format, Object... args) {
        showToast(context, format, Toast.LENGTH_LONG, args);
    }

    /**
     * 显示
     * @param context  上下文
     * @param text     文本
     * @param duration 显示时长
     */
    private synchronized static void showToast(final Context context, final CharSequence text, final int duration) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                if (sToast != null) {
                    sToast.cancel();
                }
                sToast = Toast.makeText(context, text, duration);
                sToast.show();

            }
        });

    }

    /**
     * 显示
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示
     * @param context  上下文
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void showToast(Context context, int resId, int duration, Object... args) {
        showToast(context, String.format(context.getResources().getString(resId), args), duration);
    }

    /**
     * 显示
     * @param context  上下文
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void showToast(Context context, String format, int duration, Object... args) {
        showToast(context, String.format(format, args), duration);
    }

}
