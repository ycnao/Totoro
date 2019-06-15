package com.nadia.totoro.widget.listview.helper;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * author: Created by 闹闹 on 2018-09-22
 * version: 1.0.0
 */
public class ViewHelper {
	
	public static void clear(View v) {
		ViewCompat.setAlpha(v, 1);
		ViewCompat.setScaleY(v, 1);
		ViewCompat.setScaleX(v, 1);
		ViewCompat.setTranslationY(v, 0);
		ViewCompat.setTranslationX(v, 0);
		ViewCompat.setRotation(v, 0);
		ViewCompat.setRotationY(v, 0);
		ViewCompat.setRotationX(v, 0);
		ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
		ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
		ViewCompat.animate(v).setInterpolator(null).setStartDelay(0);
	}
}
