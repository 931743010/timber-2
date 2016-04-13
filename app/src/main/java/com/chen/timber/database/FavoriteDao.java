package com.chen.timber.database;

import android.content.Context;

/**
 * Created by chen on 2016/4/13.
 */
public class FavoriteDao {
	private static FavoriteDao favoriteDao;
	public FavoriteDao getInstance(Context context){
		if(favoriteDao==null){
			synchronized (FavoriteDao.class) {
				favoriteDao = new FavoriteDao();
			}
		}
		return favoriteDao;
	}
}
