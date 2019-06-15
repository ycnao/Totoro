package com.nadia.totoro.widget.browser.web;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.nadia.totoro.R;


/**
 * author: Created by 闹闹 on 2018-09-17
 * version: 1.0.0
 */
public class IWebViewFragment extends Fragment {
	
	private String url;
	
	private WebView webView;
	private ImageView webBack;
	private ImageView webForward;
	private ImageView webReload;
	
	/**
	 * 实例化 —— 初始化参数包
	 *
	 * @param url webView加载的网页地址
	 */
	public static IWebViewFragment newInstance(String url) {
		IWebViewFragment fragment = new IWebViewFragment();
		Bundle args = new Bundle();
		args.putString("url", url);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		url = getArguments().getString("url") == null ? "" : getArguments().getString("url");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_web_browser, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		webView = view.findViewById(R.id.web_view);
		webBack = view.findViewById(R.id.web_back);
		webForward = view.findViewById(R.id.web_forward);
		webReload = view.findViewById(R.id.web_reload);
		
		// progressbar = (ProgressBar)view.findViewById(R.id.progressbar);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		new WebViewHelper(getActivity(), webView, url, webForward, webBack, webReload);
	}
}

