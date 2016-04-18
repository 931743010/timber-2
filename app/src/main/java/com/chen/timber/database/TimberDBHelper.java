package com.chen.timber.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chen on 2016/4/13.
 */
public class TimberDBHelper extends SQLiteOpenHelper {

	private static final String TABLE_ALBUM = "album_info";
	private static final String TABLE_ARTIST = "artist_info";
	private static final String TABLE_MUSIC = "music_info";
	private static final String TABLE_FOLDER = "folder_info";
	private static final String TABLE_FAVORITE = "favorite_info";
	public static final String DATANAME = "timber.db";
	private static SQLiteDatabase timberDB;
	private static TimberDBHelper helper;
	private static final int VERSION = 1;

	public TimberDBHelper(Context context) {
		super(context, DATANAME, null, VERSION);
	}

	public static TimberDBHelper getInstance(Context context) {
		if (helper == null) {
			synchronized (TimberDBHelper.class) {
				if (helper == null) {
					helper = new TimberDBHelper(context);
				}
			}
		}
		return helper;
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table"
				+ TABLE_MUSIC
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " songid integer, albumid integer, duration integer, musicname varchar(10), "
				+ "artist char, data char, folder char, musicnamekey char, artistkey char, favorite integer)");

		db.execSQL("create table"
				+ TABLE_ALBUM
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "album_name char, album_id integer, number_of_songs integer, album_art char)");

		db.execSQL("create table"
				+ TABLE_ARTIST
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, artist_name char, number_of_tracks integer)");

		db.execSQL("create table"
				+ TABLE_FAVORITE
				+ " (_id integer,"
				+ " songid integer, albumid integer, duration integer, musicname varchar(10), "
				+ "artist char, data char, folder char, musicnamekey char, artistkey char, favorite integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
