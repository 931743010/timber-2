package com.chen.timber;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.chen.timber.Service.MusicService;
import com.chen.timber.intef.MusicInfc;
import com.chen.timber.moudle.MusicInfo;

/**
 * Created by chen on 2016/4/17.
 */
public class MusicPlayer {
	private static MusicPlayer musicPlayer;
	private MusicInfc musicInfc;
	private Context context;
	private Intent intent;
	private MyConnection conn;
	private MusicInfo mCurrentMusic;//当前播放的歌曲
	private MusicPlayer(Context context) {
		this.context = context.getApplicationContext();
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

	public void next(int id) {

	}

	public void last(int id) {

	}

	public void unBindServer() {
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
