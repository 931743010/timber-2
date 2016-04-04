package com.chen.timber.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chen on 2016/4/4.
 */
public abstract class BaseFragment extends Fragment{

	protected Context mContext;
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext=context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = initView();
		initData();
		return v;
	}

	protected abstract View initView();
	protected void initData() {
	}
}
