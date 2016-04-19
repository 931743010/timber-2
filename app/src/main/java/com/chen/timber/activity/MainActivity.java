package com.chen.timber.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.timber.MusicPlayer;
import com.chen.timber.MusicUtils;
import com.chen.timber.R;
import com.chen.timber.adapter.TimberViewPagerAdapter;
import com.chen.timber.fragment.AlbumFragment;
import com.chen.timber.fragment.BaseFragment;
import com.chen.timber.fragment.NetFragment;
import com.chen.timber.fragment.SongFragment;
import com.chen.timber.moudle.MusicInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener,SongFragment.NotifyUi{
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (musicInfo != null) {
			if(isChecked){
				musicPlayer.play(musicInfo.data);
			}else {
				musicPlayer.pause();
			}
		}

	}

	@Override
	public void notify(MusicInfo musicInfo) {
		this.musicInfo=musicInfo;
		tvSong.setText(musicInfo.musicName);
		tvAblum.setText(musicInfo.artist);
		imSong.setImageBitmap(MusicUtils.getArtwork(this,musicInfo.songId,musicInfo.albumId,true));
	}

	private TabLayout tabLayout;
	private DrawerLayout drawerLayout;
	private Toolbar toolbar;
	private ActionBarDrawerToggle mDrawerToggle;
	private ViewPager viewPager;
	private List<String> titleList;
	private List<BaseFragment> fragmentList;
	private ImageView imSong;
	private TextView tvSong;
	private TextView tvAblum;
	private CheckBox cbPlay;
	private MusicPlayer musicPlayer;
	private MusicInfo musicInfo;
	@Override
	protected void initView() {
		tabLayout = getView(R.id.tablayout);
		drawerLayout = getView(R.id.drawlayout);
		toolbar = getView(R.id.toolbar);
		viewPager = getView(R.id.viewpager);
		imSong = getView(R.id.im_main_song);
		tvAblum = getView(R.id.tv_main_album);
		cbPlay = getView(R.id.cb_main_play);
		tvSong = getView(R.id.tv_main_song);
		cbPlay.setOnCheckedChangeListener(this);
	}

	@Override
	protected void initData() {
		setSupportActionBar(toolbar);
		mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,0,0);
		mDrawerToggle.syncState();
		drawerLayout.addDrawerListener(mDrawerToggle);
		musicPlayer=MusicPlayer.getInstance(this);

		titleList = new ArrayList<>();
		titleList.add("歌曲");
		titleList.add("专辑");
		titleList.add("艺术家");

//		ArrayList<MusicInfo> musicList = getIntent().getParcelableArrayListExtra("musicList");
//		Bundle data = new Bundle();
//		data.putParcelableArrayList("musicList",musicList);
		SongFragment songFragment=new SongFragment();
//		songFragment.setArguments(data);

		fragmentList = new ArrayList<>();

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
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_main;
	}
}
