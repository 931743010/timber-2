package com.chen.timber.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by chen on 2016/4/4.
 */
public class NetFragment extends BaseFragment{
	@Override
	protected View initView() {
		TextView tv=new TextView(mContext);
		tv.setGravity(Gravity.CENTER);
		tv.setText("艺术家");
		tv.setTextSize(30);
		tv.setTextColor(Color.CYAN);
		return tv;
	}

}
