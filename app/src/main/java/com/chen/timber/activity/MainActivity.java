package com.chen.timber.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.chen.timber.R;
import com.chen.timber.adapter.TimberViewPagerAdapter;
import com.chen.timber.fragment.AlbumFragment;
import com.chen.timber.fragment.BaseFragment;
import com.chen.timber.fragment.NetFragment;
import com.chen.timber.fragment.SongFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

	private TabLayout tabLayout;
	private DrawerLayout drawerLayout;
	private Toolbar toolbar;
	private ActionBarDrawerToggle mDrawerToggle;
	private ViewPager viewPager;
	private List<String> titleList;
	private List<BaseFragment> fragmentList;
	@Override
	protected void initView() {
		tabLayout = getView(R.id.tablayout);
		drawerLayout = getView(R.id.drawlayout);
		toolbar = getView(R.id.toolbar);
		viewPager = getView(R.id.viewpager);


	}

	@Override
	protected void initData() {
		setSupportActionBar(toolbar);
		mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,0,0);
		mDrawerToggle.syncState();
		drawerLayout.addDrawerListener(mDrawerToggle);

		titleList = new ArrayList<>();
		titleList.add("歌曲");
		titleList.add("专辑");
		titleList.add("艺术家");

		fragmentList = new ArrayList<>();
		SongFragment songFragment=new SongFragment();
		AlbumFragment albumFragment=new AlbumFragment();
		NetFragment netFragment=new NetFragment();
		fragmentList.add(songFragment);
		fragmentList.add(albumFragment);
		fragmentList.add(netFragment);

		TimberViewPagerAdapter pagerAdapter = new TimberViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
		tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
		tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
		tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));

		//viewpager缓存两页
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(pagerAdapter);
		tabLayout.setupWithViewPager(viewPager);
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_main;
	}
}
