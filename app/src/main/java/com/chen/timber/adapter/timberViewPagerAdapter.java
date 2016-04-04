package com.chen.timber.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chen.timber.fragment.BaseFragment;

import java.util.List;

/**
 * viewpager的适配器
 * Created by chen on 2016/4/4.
 */
public class TimberViewPagerAdapter extends FragmentStatePagerAdapter{

	private List<String> titleList;
	private List<BaseFragment> fragmentList;
	public TimberViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<String> titleList) {
		super(fm);
		this.titleList=titleList;
		this.fragmentList=fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return titleList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleList.get(position);
	}
}
