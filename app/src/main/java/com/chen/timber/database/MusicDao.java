package com.chen.timber.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chen.timber.Const;
import com.chen.timber.moudle.MusicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/4/13.
 */
public class MusicDao {
	private static MusicDao musicDao=null;
	private TimberDBHelper helper=null;
	private SQLiteDatabase db;
	private Context context;
	public MusicDao(Context context){
		this.context=context;
		helper = TimberDBHelper.getInstance(context);
		db=helper.getWritableDatabase();
	}

	public static final synchronized MusicDao getInstance(final Context context){
		if(musicDao==null){
			musicDao = new MusicDao(context.getApplicationContext());
		}
		return musicDao;
	}
	public void saveMusicInfo(List<MusicInfo> list) {
		for (MusicInfo music : list) {
			ContentValues cv = new ContentValues();
			cv.put("songid", music.songId);
			cv.put("albumid", music.albumId);
			cv.put("duration", music.duration);
			cv.put("musicname", music.musicName);
			cv.put("artist", music.artist);
			cv.put("data",music.data);
			cv.put("folder", music.folder);
			cv.put("musicnamekey", music.musicNameKey);
			cv.put("artistkey", music.artistKey);
			cv.put("favorite", music.favorite);
			db.insert(Const.MuSIC_DAO, null, cv);
		}
	}

	public List<MusicInfo> getMusicInfo() {
		String sql = "select * from " + Const.MuSIC_DAO;

		return parseCursor(db.rawQuery(sql, null));
	}

	private List<MusicInfo> parseCursor(Cursor cursor) {
		List<MusicInfo> list = new ArrayList<MusicInfo>();
		while(cursor.moveToNext()) {
			MusicInfo music = new MusicInfo();
			music._id = cursor.getInt(cursor.getColumnIndex("_id"));
			music.songId = cursor.getInt(cursor.getColumnIndex("songid"));
			music.albumId = cursor.getInt(cursor.getColumnIndex("albumid"));
			music.duration = cursor.getInt(cursor.getColumnIndex("duration"));
			music.musicName = cursor.getString(cursor.getColumnIndex("musicname"));
			music.artist = cursor.getString(cursor.getColumnIndex("artist"));
			music.data = cursor.getString(cursor.getColumnIndex("data"));
			music.folder = cursor.getString(cursor.getColumnIndex("folder"));
			music.musicNameKey = cursor.getString(cursor.getColumnIndex("musicnamekey"));
			music.artistKey = cursor.getString(cursor.getColumnIndex("artistkey"));
			music.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
			list.add(music);
		}
		cursor.close();
		return list;
	}

	public List<MusicInfo> getMusicInfoByType(String selection, int type) {
		String sql = "";
//		if(type == START_FROM_ARTIST) {
//			sql = "select * from " + TABLE_MUSIC + " where artist = ?";
//		} else if(type == START_FROM_ALBUM) {
//			sql = "select * from " + TABLE_MUSIC + " where albumid = ?";
//		} else if(type == START_FROM_FOLDER) {
//			sql = "select * from " + TABLE_MUSIC + " where folder = ?";
//		}
		return parseCursor(db.rawQuery(sql, new String[]{ selection }));
	}

	public void setFavoriteStateById(int id, int favorite) {
		String sql = "update " + Const.MuSIC_DAO + " set favorite = " + favorite + " where _id = " + id;
		db.execSQL(sql);
	}


	public MusicInfo getMusicSongById(int id) {
		Cursor cursor = db.rawQuery("select * from music_info where _id=?", new String[]{String.valueOf(id)});
		MusicInfo music=null;
		if(cursor.moveToNext()){
			music = new MusicInfo();
			music._id = cursor.getInt(cursor.getColumnIndex("_id"));
			music.songId = cursor.getInt(cursor.getColumnIndex("songid"));
			music.albumId = cursor.getInt(cursor.getColumnIndex("albumid"));
			music.duration = cursor.getInt(cursor.getColumnIndex("duration"));
			music.musicName = cursor.getString(cursor.getColumnIndex("musicname"));
			music.artist = cursor.getString(cursor.getColumnIndex("artist"));
			music.data = cursor.getString(cursor.getColumnIndex("data"));
			music.folder = cursor.getString(cursor.getColumnIndex("folder"));
			music.musicNameKey = cursor.getString(cursor.getColumnIndex("musicnamekey"));
			music.artistKey = cursor.getString(cursor.getColumnIndex("artistkey"));
			music.favorite = cursor.getInt(cursor.getColumnIndex("favorite"));
		}
		cursor.close();
		return  music;
	}
	public List<MusicInfo> getFavMusicList(){
		Cursor cursor = db.rawQuery("select * from music_info where favorite=?", new String[]{"1"});
		return parseCursor(cursor);
	}
	/**
	 * 数据库中是否有数据
	 * @return
	 */
	public boolean hasData() {
		String sql = "select count(*) from " + Const.MuSIC_DAO;
		Cursor cursor = db.rawQuery(sql, null);
		boolean has = false;
		if(cursor.moveToFirst()) {
			int count = cursor.getInt(0);
			if(count > 0) {
				has = true;
			}
		}
		cursor.close();
		return has;
	}

	public int getDataCount() {
		String sql = "select count(*) from " + Const.MuSIC_DAO;
		Cursor cursor = db.rawQuery(sql, null);
		int count = 0;
		if(cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		return count;
	}
}
