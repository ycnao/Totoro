package com.nadia.totoro.widget.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nadia.totoro.R;


/**
 * 含有确定，取消按钮，没有标题，对话框
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
public class NOptionalDialog extends DialogFragment implements OnClickListener {
	
	public interface MuOptionalDialogCallback {
		
		void cancel(View view);
		
		void oK(View view);
		
	}
	
	private static NOptionalDialog dialog;
	
	private Button okBtn;
	private Button cancelBtn;
	
	private TextView contentTv;
	
	private static MuOptionalDialogCallback callback;
	
	private static String titleStr;
	private static String contentStr;
	private static String okBtnStr;
	private static String cancelBtnStr;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.n_optional_dialog, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		contentTv = view.findViewById(R.id.contentTv);
		okBtn = view.findViewById(R.id.okBtn);
		cancelBtn = view.findViewById(R.id.cancelBtn);
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		
		okBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		
		if (!TextUtils.isEmpty(contentStr)) {
			contentTv.setText(contentStr);
		}
		if (!TextUtils.isEmpty(okBtnStr)) {
			okBtn.setText(okBtnStr);
		}
		if (!TextUtils.isEmpty(cancelBtnStr)) {
			cancelBtn.setText(cancelBtnStr);
		}
	}
	
	
	/**
	 * 创建并显示对话框
	 *
	 * @param act           上下文
	 * @param mContentStr   内容
	 * @param mokBtnStr     确定按钮
	 * @param mCancelBtnStr 取消按钮
	 * @param mCallback     回调
	 */
	public static NOptionalDialog show(FragmentActivity act, String mContentStr, String mokBtnStr, String mCancelBtnStr, MuOptionalDialogCallback mCallback) {
		contentStr = mContentStr;
		okBtnStr = mokBtnStr;
		cancelBtnStr = mCancelBtnStr;
		
		FragmentTransaction ft = act.getSupportFragmentManager().beginTransaction();
		Fragment prev = act.getSupportFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);
		// Create the dialog.
		dialog = new NOptionalDialog();
		dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog_Them);
		
		dialog.show(ft, "dialog");
		callback = mCallback;
		return dialog;
	}
	
	/**
	 * 创建并显示对话框
	 *
	 * @param act         上下文
	 * @param mContentStr 内容
	 * @param mCallback   回调
	 */
	public static NOptionalDialog show(FragmentActivity act, String mContentStr, MuOptionalDialogCallback mCallback) {
		contentStr = mContentStr;
		
		FragmentTransaction ft = act.getSupportFragmentManager().beginTransaction();
		Fragment prev = act.getSupportFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);
		// Create the dialog.
		dialog = new NOptionalDialog();
		dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog_Them);
		
		dialog.show(ft, "dialog");
		callback = mCallback;
		return dialog;
	}
	
	@Override
	public void onClick(View v) {
		if (v == okBtn) {
			callback.oK(okBtn);
			dialog.dismiss();
		} else if (v == cancelBtn) {
			callback.cancel(v);
			dialog.dismiss();
		}
	}
}
