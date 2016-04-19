package com.chen.timber.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.chen.timber.intef.MusicInfc;
import com.chen.timber.util.ToastUtils;

import java.io.IOException;

/**
 * Created by chen on 2016/4/18.
 */
public class MusicService extends Service{

	private MediaPlayer mPlayer;
	@Override
	public void onCreate() {
		super.onCreate();
		mPlayer=new MediaPlayer();
	}

	public void play(String path) {
		mPlayer.reset();
		try {
			mPlayer.setDataSource(path);
			mPlayer.prepareAsync();
			mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					mp.start();
				}
			});
		}catch (IOException e){
			ToastUtils.showToast(this,"文件损坏");
		}

	}

	public void pause() {
		mPlayer.pause();
	}
	public void next() {

	}

	public void last() {

	}
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return new MusicInfcBinder();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer=null;
		}
	}
	class MusicInfcBinder extends Binder implements MusicInfc {
		@Override
		public void play(String path) {
			MusicService.this.play(path);
		}

		@Override
		public void pause() {
			MusicService.this.pause();
		}

		@Override
		public void next(String path) {
			MusicService.this.next();
		}

		@Override
		public void last(String path) {
			MusicService.this.last();
		}
	}

}
