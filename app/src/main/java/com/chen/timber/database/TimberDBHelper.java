package com.chen.timber.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chen on 2016/4/13.
 */
public class TimberDBHelper extends SQLiteOpenHelper{
	public static final String DATANAME="timber.db";
	private static final int VERSION=1;
	public TimberDBHelper(Context context) {
		super(context, DATANAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
