package com.nadia.totoro.widget.spotsdialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.nadia.totoro.R;


/**
 * Created by Maxim Dybarsky | maxim.dybarskyy@gmail.com on 13.01.15 at 14:22
 */
public class SpotsDialog extends AlertDialog {
	
	private static final int DELAY = 150;
	
	private static final int DURATION = 1500;
	
	private String titleStr;
	
	private int size;
	
	private AnimatedView[] spots;
	
	private AnimatorPlayer animator;
	
	@Override
	public void setCancelable(boolean flag) {
		super.setCancelable(flag);
	}
	
	public SpotsDialog(Context context) {
		this(context, R.style.SpotsDialogDefault);
	}
	
	public SpotsDialog(Context context, String title) {
		this(context, R.style.SpotsDialogDefault);
		titleStr = title;
	}
	
	public SpotsDialog(Context context, int theme) {
		super(context, theme);
	}
	
	public SpotsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spots_dialog);
//		setCanceledOnTouchOutside(false);
		
		TextView tv = findViewById(R.id.title);
		titleStr = TextUtils.isEmpty(titleStr) ? "努力加载中……" : titleStr;
		tv.setText(titleStr);
		
		initProgress();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		animator = new AnimatorPlayer(createAnimations());
		animator.play();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		animator.stop();
	}
	
	private void initProgress() {
		ProgressLayout progress = findViewById(R.id.progress);
		size = progress.getSpotsCount();
		
		spots = new AnimatedView[size];
		int size = getContext().getResources().getDimensionPixelSize(R.dimen.spot_size);
		int progressWidth = getContext().getResources().getDimensionPixelSize(R.dimen.progress_width);
		for (int i = 0; i < spots.length; i++) {
			AnimatedView v = new AnimatedView(getContext());
			v.setBackgroundResource(R.drawable.spot_shape);
			v.setTarget(progressWidth);
			v.setXFactor(-1f);
			progress.addView(v, size, size);
			spots[i] = v;
		}
	}
	
	private Animator[] createAnimations() {
		Animator[] animators = new Animator[size];
		for (int i = 0; i < spots.length; i++) {
			Animator move = ObjectAnimator.ofFloat(spots[i], "xFactor", 0, 1);
			move.setDuration(DURATION);
			move.setInterpolator(new HesitateInterpolator());
			move.setStartDelay(DELAY * i);
			animators[i] = move;
		}
		return animators;
	}
}
