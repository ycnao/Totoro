package com.nadia.totoro.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nadia.totoro.R;


/**
 * 界面显示帮助类
 * <p>
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
public class UIHelper {
	
	private UIHelper() {
	
	}
	
	public static void layoutManager(Context context, RecyclerView recyclerView, int orientation) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(context);
		layoutManager.setOrientation(orientation);
		recyclerView.setLayoutManager(layoutManager);
	}
	
	/**
	 * 设置SwipeRefreshLayout下拉刷新的旋转切换颜色。
	 *
	 * @param swipeRefreshLayout SwipeRefreshLayout
	 * @param colorResId         资源id，如R.color.SpringGreen3
	 */
	public static void setSwipeRefreshLayoutColors(SwipeRefreshLayout swipeRefreshLayout, int... colorResId) {
		for (int i : colorResId) {
			swipeRefreshLayout.setColorSchemeResources(i);
		}
	}
	
	/**
	 * 本应用的。
	 *
	 * @param swipeRefreshLayout SwipeRefreshLayout
	 */
	public static void setSwipeRefreshLayoutColors(SwipeRefreshLayout swipeRefreshLayout) {
		swipeRefreshLayout.setColorSchemeResources(R.color.SpringGreen3, R.color.RoyalBlue2, R.color.orange, R.color.grey71);
	}
	
	public static void toastShowShort(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 设置密码可见性
	 *
	 * @param edt EditText
	 * @param flg 1可见，其他不可见
	 */
	public static void setPasswordVisibility(EditText edt, int flg) {
		if (flg == 1) {
			edt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
		} else {
			edt.setTransformationMethod(PasswordTransformationMethod.getInstance());
		}
	}
	
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}
		
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); //列表item根必须是线性布局
			totalHeight += listItem.getMeasuredHeight();
		}
		
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
	
	public static String colorFont(String src, String color) {
		StringBuffer strBuf = new StringBuffer();
		
		strBuf.append("<font color=").append(color).append(">").append(src).append("</font>");
		return strBuf.toString();
	}
	
	/**
	 * 移动输入框光标到特定的位置。
	 *
	 * @param edt      EditText
	 * @param position 位置!text.length()移动到文本尾部
	 */
	public static void moveEdtCursor(EditText edt, int position) {
		CharSequence text = edt.getText();
		//Debug.asserts(text instanceof Spannable);
		if (text instanceof Spannable) {
			Spannable spanText = (Spannable) text;
			Selection.setSelection(spanText, position);
		}
	}
}
