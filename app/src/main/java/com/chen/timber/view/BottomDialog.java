package com.chen.timber.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chen.timber.R;

/**
 * Created by chen on 2016/5/1.
 */
public class BottomDialog extends Dialog{
	private Context context;
	public BottomDialog(Context context, View view) {
		super(context, R.style.processDialog);

		this.context=context;
		setContentView(view);

		Window window=this.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setWindowAnimations(R.style.bottomDialog);
		window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
	}
}
