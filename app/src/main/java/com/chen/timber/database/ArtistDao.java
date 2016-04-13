package com.chen.timber.database;

import android.content.Context;

/**
 * Created by chen on 2016/4/13.
 */
public class ArtistDao {
	private static ArtistDao artistDao;
	public ArtistDao getInstance(Context context){
		if(artistDao==null){
			synchronized (FavoriteDao.class) {
				artistDao = new ArtistDao();
			}
		}
		return artistDao;
	}
}
