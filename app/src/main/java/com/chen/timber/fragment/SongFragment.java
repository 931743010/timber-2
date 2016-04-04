package com.chen.timber.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chen on 2016/4/4.
 */
public class SongFragment extends BaseFragment{
	@Override
	protected View initView() {
		TextView tv=new TextView(mContext);
		tv.setGravity(Gravity.CENTER);
		tv.setText("歌曲");
		tv.setTextSize(30);
		tv.setTextColor(Color.CYAN);
		return tv;
	}

}
