package com.chen.timber;

import android.content.Context;

/**
 * Created by chen on 2016/4/17.
 */
public class MusicPlayer {
	private static MusicPlayer musicPlayer;
	private Context context;
	public static MusicPlayer getInstance() {
		if (musicPlayer == null) {
			synchronized (MusicPlayer.class) {
				musicPlayer=new MusicPlayer();
			}
		}
		return musicPlayer;
	}

	public void setContext(Context context){
		this.context=context;
	}
	public void play(String path) {

	}

	public void pause(int id) {

	}

	public void next(int id) {

	}

	public void last(int id) {

	}
}
