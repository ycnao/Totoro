package com.nadia.totoro.watcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by naonao on 2017/1/6.
 * <p>
 * 监听EditText框中的变化
 */
public class NTextWatcher implements TextWatcher {
	
	private Context mContext;
	private CharSequence temp;
	private EditText editTextID;
	
	public NTextWatcher(Context context, EditText editText) {
		mContext = context;
		editTextID = editText;
	}
	
	/**
	 * 文本变化之前
	 *
	 * @param s
	 * @param start
	 * @param count
	 * @param after
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		temp = s;
	}
	
	/**
	 * 文本变化中
	 *
	 * @param s
	 * @param start
	 * @param before
	 * @param count
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
//        tvTotalKm.setText(s + "km");
	}
	
	/**
	 * 文本变化之后
	 *
	 * @param s
	 */
	@Override
	public void afterTextChanged(Editable s) {
		int editStart = editTextID.getSelectionStart();
		int editEnd = editTextID.getSelectionEnd();
		if (temp.length() > 10) {//限制长度
			Toast.makeText(mContext, "输入的字数已经超过了限制！", Toast.LENGTH_SHORT).show();
			s.delete(editStart - 1, editEnd);
			int tempSelection = editStart;
			editTextID.setText(s);
			editTextID.setSelection(tempSelection);
		}
	}
}
