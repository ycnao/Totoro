package com.nadia.totoro.widget.browser.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.nadia.totoro.R;
import com.nadia.totoro.helper.AnimHelper;


/**
 * author: Created by 闹闹 on 2018-09-17
 * version: 1.0.0
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebViewHelper {
	
	private Context mContext;
	
	private WebView webView;
	private String url;
	
	private ImageView reload;
	private ImageView goForward;
	private ImageView web_back;
	
	/**
	 * 构造函数
	 */
	public WebViewHelper(Context mContext, WebView webView, String url, ImageView forward, ImageView back, ImageView reload) {
		this.mContext = mContext;
		this.webView = webView;
		this.url = url;
		
		this.reload = reload;
		goForward = forward;
		web_back = back;
		
		setWebView();
	}
	
	private void setWebView() {
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setDomStorageEnabled(true);
		// 不显示webview缩放按钮
		webSettings.setDisplayZoomControls(false);
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		
		webView.setWebViewClient(new WebViewClient() { // 通过webView打开链接，不调用系统浏览器
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url == null) return false;
				
				try {
					if (url.startsWith("weixin://") //微信
							|| url.startsWith("alipays://") //支付宝
							|| url.startsWith("mailto://") //邮件
							|| url.startsWith("tel://")//电话
							|| url.startsWith("dianping://")
							|| url.startsWith("ctrip://")//携程
							|| url.startsWith("baidumap://")) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
						mContext.startActivity(intent);
						return true;
					}
				} catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
					return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
				}
				
				loadUrl(view, url);
				return true;
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				// 下载完，关闭进度条
				AnimHelper.stopViewAnim(reload);
			}
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}
			
			@Override
			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
				} else {
					handler.proceed();
				}
			}
		});
		
		loadUrl(webView, url);
		
		web_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (webView.canGoBack()) {
					webView.goBack();
				}
			}
		});
		
		goForward.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				webView.goForward();
			}
		});
		
		reload.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AnimHelper.startRotateAnim(mContext, R.anim.loading_browser_rotate_anim, reload);
				webView.reload();
			}
		});
	}
	
	private void loadUrl(WebView webView, String url) {
		if (null != url) {
			webView.loadUrl(url);
			// 出现进度条
			AnimHelper.startViewAnim(mContext, R.anim.loading_browser_rotate_anim, reload);
		}
	}
}

