package com.chen.timber.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.timber.R;

/**
 * Created by chen on 2016/4/4.
 */
public class SongFragment extends BaseFragment{
	private RecyclerView mRecyclerView;
	@Override
	protected View initView() {
		View view = View.inflate(mContext, R.layout.song_fragment_layout, null);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.rcy_song);
		initData();
		return view;
	}

	@Override
	protected void initData() {
		super.initData();

	}
}
