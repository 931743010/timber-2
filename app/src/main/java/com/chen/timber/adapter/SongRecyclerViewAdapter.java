package com.chen.timber.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.timber.R;

/**
 * Created by chen on 2016/4/13.
 */
public class SongRecyclerViewAdapter extends RecyclerView.Adapter<SongRecyclerViewAdapter.SongViewHolder>{
	private Context context;
	public SongRecyclerViewAdapter(Context context){
		this.context=context;
	}
	@Override
	public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		SongViewHolder holder = new SongViewHolder(LayoutInflater.from(context).inflate(R.layout.item_song, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(SongViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

	class SongViewHolder extends RecyclerView.ViewHolder{
		ImageView imSong;
		TextView tvSongTitle;
		TextView tvSongAblum;
		ImageView imSongPop;
		public SongViewHolder(View view) {
			super(view);
			imSong = (ImageView) view.findViewById(R.id.im_song);
			imSongPop = (ImageView) view.findViewById(R.id.im_pop);
			tvSongTitle = (TextView) view.findViewById(R.id.song_title);
			tvSongAblum = (TextView) view.findViewById(R.id.song_album);
		}
	}
}
