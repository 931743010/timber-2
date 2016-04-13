package com.chen.timber.database;

import android.content.Context;

/**
 * Created by chen on 2016/4/13.
 */
public class SearchHistoryDao {

	private static SearchHistoryDao searchHistoryDao;
	public SearchHistoryDao getInstance(Context context){
		if(searchHistoryDao==null){
			synchronized (SearchHistoryDao.class){
				searchHistoryDao=new SearchHistoryDao();
			}
		}
		return searchHistoryDao;
	}
}
