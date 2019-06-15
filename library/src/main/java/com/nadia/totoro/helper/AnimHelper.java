package com.nadia.totoro.helper;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;


/**
 * 动画负责类
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
public class AnimHelper {

    /**
     * 在view上加载动画。注意：在动画资源res中加载插值器
     *
     * @param context
     * @param animResId
     * @param view
     */
    public static void startViewAnim(Context context, int animResId, View view) {
        stopViewAnim(view);
        Animation operatingAnim = AnimationUtils.loadAnimation(context, animResId);
        view.startAnimation(operatingAnim);
    }

    /**
     * view加载动画。代码加载插值器，以上面区别。
     *
     * @param context
     * @param animResId    动画资源res
     * @param view         加载动画的view
     * @param interpolator 插值器。共有：1. LinearInterpolator、2.
     */
    public static void startViewAnim(Context context, int animResId, View view, Interpolator interpolator) {
        stopViewAnim(view);
        Animation operatingAnim = AnimationUtils.loadAnimation(context, animResId);
        operatingAnim.setInterpolator(interpolator);
        view.startAnimation(operatingAnim);
    }

    /**
     * 设置旋转动画。 动画文件示例：anim文件夹下 <?xml version="1.0" encoding="utf-8"?> <!--
     * 360度旋转动画 --> <set
     * xmlns:android="http://schemas.android.com/apk/res/android" >
     * <p>
     * <rotate android:duration="500" android:fromDegrees="0"
     * android:pivotX="50%" android:pivotY="50%" android:repeatCount="-1"
     * android:toDegrees="359" />
     * <p>
     * </set>
     *
     * @param context
     * @param animResId
     * @param view
     */
    public static void startRotateAnim(Context context, int animResId, View view) {
        Animation operatingAnim = AnimationUtils.loadAnimation(context, animResId);

        // 设置旋转速率为匀速
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);

        if (view != null && operatingAnim.hasStarted()) {
            view.clearAnimation();
            view.startAnimation(operatingAnim);
        }
    }

    /**
     * 停止控件上所有动画。
     *
     * @param view
     */
    public static void stopViewAnim(View view) {
        view.clearAnimation();
    }
}
