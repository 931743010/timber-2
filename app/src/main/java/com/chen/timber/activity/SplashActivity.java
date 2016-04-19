package com.chen.timber.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.chen.timber.MusicUtils;
import com.chen.timber.R;
import com.chen.timber.database.MusicDao;
import com.chen.timber.moudle.MusicInfo;

import java.util.ArrayList;

public class SplashActivity extends BaseActivity {

	private ArrayList<MusicInfo> musicList;
	private MusicDao musicDao;
	private MusicUtils musicUtils;
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Intent intent = new Intent(SplashActivity.this,MainActivity.class);
			intent.putParcelableArrayListExtra("musicList", musicList);
			startActivity(intent);

			SplashActivity.this.finish();
		}
	};

	@Override
	protected void initView() {

	}

	@Override
	protected void initData() {
		musicDao=MusicDao.getInstance(this);
		if(musicDao.hasData()){
			handler.sendEmptyMessage(0);
		}else {
			final long startTime = System.currentTimeMillis();
			new Thread(new Runnable() {
				@Override
				public void run() {
					musicUtils = new MusicUtils(SplashActivity.this);
					musicList = (ArrayList<MusicInfo>) musicUtils.getMusic();
					if(musicList==null){

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								showToast("没有找到音乐");
								finish();
							}
						});

					}else {
						musicDao.saveMusicInfo(musicList);
						long endTime=System.currentTimeMillis();
						if(endTime-startTime<1500){
							handler.sendEmptyMessageDelayed(0,startTime+200-endTime);
						}else {
							handler.sendEmptyMessage(0);
						}
					}


				}
			}).start();

		}


	}

	@Override
	protected int getContentView() {
		return R.layout.activity_splash;
	}
}
