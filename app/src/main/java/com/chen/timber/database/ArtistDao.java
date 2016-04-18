package com.chen.timber.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chen.timber.moudle.ArtistInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/4/13.
 */
public class ArtistDao {
	private static final String TABLE_ARTIST = "artist_info";
	private static ArtistDao artistDao;
	private SQLiteDatabase db;
	private Context context;

	private ArtistDao(Context context) {
		this.context = context;
		db = TimberDBHelper.getInstance(context).getReadableDatabase();
	}

	public static final synchronized ArtistDao getInstance(Context context) {
		if (artistDao == null) {
				artistDao = new ArtistDao(context.getApplicationContext());
			}
		return artistDao;
	}

	public void saveArtistInfo(List<ArtistInfo> list) {
		for (ArtistInfo info : list) {
			ContentValues cv = new ContentValues();
			cv.put("artist_name", info.artist_name);
			cv.put("number_of_tracks", info.number_of_tracks);
			db.insert(TABLE_ARTIST, null, cv);
		}
	}

	public List<ArtistInfo> getArtistInfo() {
		List<ArtistInfo> list = new ArrayList<ArtistInfo>();
		String sql = "select * from " + TABLE_ARTIST;
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			ArtistInfo info = new ArtistInfo();
			info.artist_name = cursor.getString(cursor.getColumnIndex("artist_name"));
			info.number_of_tracks = cursor.getInt(cursor.getColumnIndex("number_of_tracks"));
			list.add(info);
		}
		cursor.close();
		return list;
	}

	/**
	 * 数据库中是否有数据
	 *
	 * @return
	 */
	public boolean hasData() {
		String sql = "select count(*) from " + TABLE_ARTIST;
		Cursor cursor = db.rawQuery(sql, null);
		boolean has = false;
		if (cursor.moveToFirst()) {
			int count = cursor.getInt(0);
			if (count > 0) {
				has = true;
			}
		}
		cursor.close();
		return has;
	}

	public int getDataCount() {
		String sql = "select count(*) from " + TABLE_ARTIST;
		Cursor cursor = db.rawQuery(sql, null);
		int count = 0;
		if (cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		return count;
	}
}
