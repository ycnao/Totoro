package com.nadia.totoro.widget.codeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.nadia.totoro.R;

/**
 * Author:<a href='https://github.com/kungfubrother'>https://github.com/kungfubrother</a>
 * Date:2017/1/7 21:39
 * Description:数字输入键盘
 */
public class KeyboardView extends FrameLayout implements View.OnClickListener {

	public interface KeyboardListener {

		public void onInput(String s);

		public void onDelete();

	}

	private CodeView codeView;
	private KeyboardListener listener;
	
	public KeyboardView(Context context) {
		super(context);
		init();
	}
	
	public KeyboardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public void setCodeView(CodeView codeView) {
		this.codeView = codeView;
	}
	
	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_pwd_keyboard, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		addView(view);
		view.findViewById(R.id.keyboard_0).setOnClickListener(this);
		view.findViewById(R.id.keyboard_1).setOnClickListener(this);
		view.findViewById(R.id.keyboard_2).setOnClickListener(this);
		view.findViewById(R.id.keyboard_3).setOnClickListener(this);
		view.findViewById(R.id.keyboard_4).setOnClickListener(this);
		view.findViewById(R.id.keyboard_5).setOnClickListener(this);
		view.findViewById(R.id.keyboard_6).setOnClickListener(this);
		view.findViewById(R.id.keyboard_7).setOnClickListener(this);
		view.findViewById(R.id.keyboard_8).setOnClickListener(this);
		view.findViewById(R.id.keyboard_9).setOnClickListener(this);
		view.findViewById(R.id.keyboard_hide).setOnClickListener(this);
		view.findViewById(R.id.keyboard_delete).setOnClickListener(this);
	}
	
	public void hide() {
		setVisibility(GONE);
	}
	
	public void show() {
		setVisibility(VISIBLE);
	}
	
	public void setKeyboardListener(KeyboardListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void onClick(View v) {
		final String tag = (String) v.getTag();
		if (tag != null) {
			switch (tag) {
				case "hide":
					hide();
					break;
				case "delete":
					if (codeView != null) {
						codeView.delete();
					}
					if (listener != null) {
						listener.onDelete();
					}
					break;
				default:
					if (codeView != null) {
						codeView.input(tag);
					}
					if (listener != null) {
						listener.onInput(tag);
					}
					break;
			}
		}
	}
}
