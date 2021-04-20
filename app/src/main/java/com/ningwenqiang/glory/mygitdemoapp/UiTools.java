package com.ningwenqiang.glory.mygitdemoapp;

import android.app.Activity;
import android.content.Context;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

/**
 * 功能：Ui dp px sp转换工具，现在统一用这一个，
 * 后期方便修改这个进行大部分适配
 * 作者：宁文强
 * 日期:2017-11-29 09:58
 *
 *
 */

public class UiTools {


    public static View getSeparateView(Context context) {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
        return view;
    }


    /**
     * convert dp to its equivalent px
     * <p>
     * 将dp转换为px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * convert sp to its equivalent px
     * <p>
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * convert dp to its equivalent px
     * <p>
     * 将dp转换为px
     */
    public static int dp2pxAuto(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * convert sp to its equivalent px
     * <p>
     * 将sp转换为px
     */
    public static int sp2pxAuto(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getSrcenWwidth(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        return width;
    }
}
