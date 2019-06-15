package com.nadia.totoro.sample;

import android.app.Application;
import android.content.Context;
import com.nadia.totoro.data.PreferencesUtil;
import com.nadia.totoro.sample.common.app.Constants;
import com.squareup.picasso.Picasso;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

public class MyApplication extends Application {
	
	public static Context applicationContext;
	private static MyApplication instance;
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext = this;
		instance = this;

		//图片
//		configPicasso();
		
		//禁止弹窗
		disableAPIDialog();
	}
	
	
	/**
	 * 反射 禁止弹窗
	 */
	private void disableAPIDialog() {
		try {
			Class clazz = Class.forName("android.app.ActivityThread");
			Method currentActivityThread = clazz.getDeclaredMethod("currentActivityThread");
			currentActivityThread.setAccessible(true);
			Object activityThread = currentActivityThread.invoke(null);
			Field mHiddenApiWarningShown = clazz.getDeclaredField("mHiddenApiWarningShown");
			mHiddenApiWarningShown.setAccessible(true);
			mHiddenApiWarningShown.setBoolean(activityThread, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 配置 Picasso
	 */
//	private void configPicasso() {
//		OkHttpClient.Builder builder = new OkHttpClient.Builder();
//		OkHttpClient client = null;
//		try {
//			client = builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//					.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), new SSLTrustAllManager())
//					.protocols(Collections.singletonList(Protocol.HTTP_1_1)).build();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		final Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(client)).build();
//		Picasso.setSingletonInstance(picasso);
//	}
	
	public static MyApplication getInstance() {
		return instance;
	}
	
	
	// 是否已经登录。登录了返回true，否则返回false
	public boolean isLogin() {
		return PreferencesUtil.getBoolean(this, Constants.preferencesKey.IS_LOGIN_KEY, false);
	}
	
	public boolean isWelcome() {
		return PreferencesUtil.getBoolean(this, Constants.preferencesKey.IS_WELCOME_KEY, false);
	}
	
	public boolean isSelectCompany() {
		return PreferencesUtil.getBoolean(this, Constants.preferencesKey.IS_SELECT_COMPANY_KEY, false);
	}
	
	// 获取登录用户id
	public String getUserId() {
		return PreferencesUtil.getString(this, Constants.preferencesKey.LOGIN_USER_ID_KEY, "");
	}
	
	public String getUserDetailsId() {
		return PreferencesUtil.getString(this, Constants.preferencesKey.USER_DETAILS_ID_KEY, "");
	}
	
	public String getPlatformType() {
		return PreferencesUtil.getString(this, Constants.preferencesKey.PLATFORM_TYPE_KEY, "");
	}
	
	public String getMobile() {
		return PreferencesUtil.getString(this, Constants.preferencesKey.LOGIN_MOBILE_KEY, "");
	}
}
