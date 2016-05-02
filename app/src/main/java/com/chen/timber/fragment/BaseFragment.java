package com.chen.timber.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.timber.MusicPlayer;
import com.chen.timber.moudle.MusicInfo;

/**
 * Created by chen on 2016/4/4.
 */
public abstract class BaseFragment extends Fragment{

	protected Context mContext;
	protected MusicPlayer musicPlayer;
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext=context;
		musicPlayer = MusicPlayer.getInstance(context);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = initView();
		initData(savedInstanceState);
		return v;
	}

	protected abstract View initView();
	protected void initData(Bundle savedInstanceState) {
	}
	public interface NotifyUi {
		void notifyMusic(MusicInfo musicInfo);
	}

}
