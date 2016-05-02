package com.chen.timber.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chen.timber.MusicPlayer;
import com.chen.timber.MusicUtils;
import com.chen.timber.R;
import com.chen.timber.adapter.TimberViewPagerAdapter;
import com.chen.timber.database.MusicDao;
import com.chen.timber.fragment.FavFragment;
import com.chen.timber.fragment.BaseFragment;
import com.chen.timber.fragment.NetFragment;
import com.chen.timber.fragment.SongFragment;
import com.chen.timber.moudle.MusicInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, SongFragment.NotifyUi,View.OnClickListener {


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
	private NavigationView mNavigationView;
	private ImageButton imNext;
	private SharedPreferences sp;
	private MusicDao musicDao;
	private LinearLayout mLinearLayout;
	private static final int REQUEST_CODE=0x03;
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
		imNext = getView(R.id.im_main_next);
		imNext.setOnClickListener(this);
		mLinearLayout = getView(R.id.ll_main);
		mLinearLayout.setOnClickListener(this);
		cbPlay.setOnCheckedChangeListener(this);
		mNavigationView = getView(R.id.navigationview);
		mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				drawerLayout.closeDrawer(Gravity.LEFT);

				switch (item.getItemId()) {
					case R.id.item_music:
						viewPager.setCurrentItem(0);
						break;
					case R.id.item_fav:
						viewPager.setCurrentItem(1);
						break;
					case R.id.item_settings:
						break;
					case R.id.item_about_me:
						startActivity(new Intent(MainActivity.this, AboutMeActivity.class));
						break;
					case R.id.item_exit:
						finish();
						break;
				}
				return true;
			}
		});
	}

	@Override
	protected void initData() {
		setSupportActionBar(toolbar);
		mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
		mDrawerToggle.syncState();
		drawerLayout.addDrawerListener(mDrawerToggle);
		musicPlayer = MusicPlayer.getInstance(this);

		musicDao = MusicDao.getInstance(this);
		titleList = new ArrayList<>();
		titleList.add("歌曲");
		titleList.add("喜欢");
		titleList.add("专辑");

		SongFragment songFragment = new SongFragment();

		fragmentList = new ArrayList<>();

		FavFragment favFragment = new FavFragment();
		NetFragment netFragment = new NetFragment();
		fragmentList.add(songFragment);
		fragmentList.add(favFragment);
		fragmentList.add(netFragment);

		TimberViewPagerAdapter pagerAdapter = new TimberViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
		tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
		tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
		tabLayout.addTab(tabLayout.newTab().setText(titleList.get(2)));

		//viewpager缓存两页
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(pagerAdapter);
		tabLayout.setupWithViewPager(viewPager);
		displayUpSong();
	}

	private void displayUpSong() {
		MusicDao musicDao = MusicDao.getInstance(this);
		sp= getSharedPreferences("music", Context.MODE_PRIVATE);
		int upId = sp.getInt("up_song", 0);
		if (upId != 0) {
			musicInfo = musicDao.getMusicSongById(upId);

		}else {
			musicInfo = musicDao.getMusicSongById(01);
		}

		if (musicInfo != null) {
			tvSong.setText(musicInfo.musicName);
			tvAblum.setText(musicInfo.artist);
			imSong.setImageBitmap(MusicUtils.getArtwork(this, musicInfo.songId, musicInfo.albumId, true));
		}
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (musicInfo != null) {
			if (isChecked) {
				playMusic();
			} else {
				musicPlayer.pause();
			}
		}
	}

	private void playMusic() {
		if(cbPlay.isChecked()){
			musicPlayer.play(musicInfo);
		}
		tvSong.setText(musicInfo.musicName);
		tvAblum.setText(musicInfo.artist);
		imSong.setImageBitmap(MusicUtils.getArtwork(this, musicInfo.songId, musicInfo.albumId, true));
	}

	@Override
	public void notifyMusic(MusicInfo musicInfo) {

		if (this.musicInfo != null) {

			if (cbPlay.isChecked()) {
				if (this.musicInfo._id != musicInfo._id) {
					this.musicInfo=musicInfo;
					playMusic();
				}
			} else {
				if(this.musicInfo._id!=musicInfo._id){
					this.musicInfo=musicInfo;
					cbPlay.setChecked(true);
				}

			}
		}else {
			this.musicInfo=musicInfo;
			cbPlay.setChecked(true);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		sp.edit().putInt("up_song", musicInfo._id).commit();
		musicPlayer.unBindServer();
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_main;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.im_main_next:
				if(musicInfo._id<musicDao.getDataCount()){
					musicInfo = musicDao.getMusicSongById(musicInfo._id+1);
					playMusic();
				}
				  break;
			case R.id.ll_main:
				Intent intent = new Intent(this, SongDetailActivity.class);
				intent.putExtra("type", 0);
				intent.putExtra("music", musicInfo);
				startActivityForResult(intent,REQUEST_CODE);
				break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE&&resultCode==SongDetailActivity.RESULT_OK) {
			if (data.getParcelableExtra("music") != null) {
				musicInfo = data.getParcelableExtra("music");
				playMusic();
			}

		}
	}
}
