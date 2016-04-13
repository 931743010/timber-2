package com.chen.timber.database;

import android.content.Context;

/**
 * Created by chen on 2016/4/13.
 */
public class PlayListDao {
	private static PlayListDao playListDao;
	public PlayListDao getInstance(Context context){
		if(playListDao==null){
			synchronized (PlayListDao.class){
				playListDao=new PlayListDao();
			}
		}
		return playListDao;
	}
}
