package com.chen.timber.database;

import android.content.Context;

/**
 * Created by chen on 2016/4/13.
 */
public class SongDao {
	private static SongDao songDao;
	public SongDao getInstance(Context context){
		if(songDao ==null){
			synchronized (SongDao.class) {
				songDao = new SongDao();
			}
		}
		return songDao;
	}
}
