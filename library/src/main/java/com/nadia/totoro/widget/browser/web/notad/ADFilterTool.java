package com.nadia.totoro.widget.browser.web.notad;

import android.content.Context;
import android.content.res.Resources;

import com.nadia.totoro.R;


/**
 * author: Created by 闹闹 on 2018-10-17
 * version: 1.0.0
 */
public class ADFilterTool {
	
	public static boolean hasAd(Context context, String url) {
		Resources res = context.getResources();
		String[] adUrls = res.getStringArray(R.array.adBlockUrl);
		for (String adUrl : adUrls) {
			if (url.contains(adUrl)) {
				return true;
			}
		}
		return false;
	}
}
