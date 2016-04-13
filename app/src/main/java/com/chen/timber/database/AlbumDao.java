package com.chen.timber.database;

import android.content.Context;

/**
 * Created by chen on 2016/4/13.
 */
public class AlbumDao {
	private static AlbumDao albumDao;
	public AlbumDao getInstance(Context context){
		if(albumDao==null){
			synchronized (AlbumDao.class) {
				albumDao = new AlbumDao();
			}
		}
		return albumDao;
	}
}
