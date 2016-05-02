package com.chen.timber;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import com.chen.timber.Service.MusicService;
import com.chen.timber.activity.SongDetailActivity;
import com.chen.timber.database.MusicDao;
import com.chen.timber.intef.MusicInfc;
import com.chen.timber.moudle.MusicInfo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chen on 2016/4/17.
 */
public class MusicPlayer {
	private static MusicPlayer musicPlayer;
	private MusicInfc musicInfc;
	private Context context;
	private Intent intent;
	private MyConnection conn;
	private MusicDao musicDao;
	private Timer timer;
	private MusicPlayer(Context context) {
		this.context = context.getApplicationContext();
		musicDao = MusicDao.getInstance(context);
		intent = new Intent(context, MusicService.class);
		this.context.startService(intent);
		conn = new MyConnection();
		this.context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}

	public static MusicPlayer getInstance(Context context) {
		if (musicPlayer == null) {
			synchronized (MusicPlayer.class) {
				musicPlayer = new MusicPlayer(context);
			}
		}
		return musicPlayer;
	}

	public void play(MusicInfo musicInfo) {
		if(musicInfo.data!=null){
			musicInfc.play(musicInfo.data);
		}

	}

	public void pause() {
		musicInfc.pause();
	}

	public void next() {
	}

	public void last(int id) {

	}

	public void seekTo(int progress) {
		musicInfc.seekTo(progress);
	}

	public void addTimer() {
		if (timer == null) {
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					int duration = musicInfc.getDuration();
					int currentPosition = musicInfc.getCurrentPosition();
					Message msg= SongDetailActivity.mHandler.obtainMessage();
					Bundle data = new Bundle();
					data.putInt("duration", duration);
					data.putInt("currentPosition",currentPosition);
					msg.what=0x07;
					msg.obj=data;
					SongDetailActivity.mHandler.sendMessage(msg);
				}
			}, 0, 1000);
		}
	}
	public boolean isPlay() {

		return musicInfc.isPlay();
	}
	public void unBindServer() {
		if (timer != null) {
			timer.cancel();
			timer=null;
		}
		context.stopService(intent);
		context.unbindService(conn);
	}
	class MyConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			musicInfc = (MusicInfc) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	}
}
