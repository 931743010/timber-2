package com.chen.timber;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.chen.timber.Service.MusicService;
import com.chen.timber.intef.MusicInfc;

/**
 * Created by chen on 2016/4/17.
 */
public class MusicPlayer {
	private static MusicPlayer musicPlayer;
	private MusicInfc musicInfc;
	private Context context;

	private MusicPlayer(Context context) {
		this.context = context;
		MusicService musicService = new MusicService();
		Intent intent = new Intent(context, MusicService.class);
		((Activity) context).startService(intent);
		MyConnection conn = new MyConnection();
		((Activity) context).bindService(intent, conn, Context.BIND_AUTO_CREATE);
		Log.i("MusicPlayer", "MusicPlayer: oncreate执行了");
	}

	public static MusicPlayer getInstance(Context context) {
		if (musicPlayer == null) {
			synchronized (MusicPlayer.class) {
				musicPlayer = new MusicPlayer(context);
			}
		}
		return musicPlayer;
	}

	public void play(String path) {
		musicInfc.play(path);
	}

	public void pause() {
		musicInfc.pause();
	}

	public void next(int id) {

	}

	public void last(int id) {

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
