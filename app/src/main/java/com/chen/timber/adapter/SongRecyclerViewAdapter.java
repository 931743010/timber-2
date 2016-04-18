package com.chen.timber.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.timber.MusicUtils;
import com.chen.timber.R;
import com.chen.timber.moudle.MusicInfo;

import java.util.List;

/**
 * Created by chen on 2016/4/13.
 */
public class SongRecyclerViewAdapter extends RecyclerView.Adapter<SongRecyclerViewAdapter.SongViewHolder>{
	private Context context;
	private List<MusicInfo> musicList;
	public SongRecyclerViewAdapter(Context context,List<MusicInfo> musicList){
		this.context=context;
		this.musicList=musicList;
	}
	@Override
	public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		SongViewHolder holder = new SongViewHolder(LayoutInflater.from(context).inflate(R.layout.item_song, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(SongViewHolder holder, final int position) {
		MusicInfo musicInfo = musicList.get(position);
		holder.tvSongTitle.setText(musicInfo.musicName);
		holder.tvSongAblum.setText(musicInfo.artist);
		holder.imSong.setImageBitmap(MusicUtils.getArtwork(context,musicInfo.songId,musicInfo.albumId,true));
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onRecyclerViewListener != null) {
					onRecyclerViewListener.onClick(v,position);
				}
			}
		});
		holder.imSongPop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onPopClickListener != null) {
					onPopClickListener.onPopClick(position);
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return musicList.size();
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

	public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
		this.onRecyclerViewListener = onRecyclerViewListener;
	}

	//recyclerView条目的点击监听
	public	OnRecyclerViewListener onRecyclerViewListener;
	public  interface OnRecyclerViewListener{
		void onClick(View view,int position);
	}

	public void setOnPopClickListener(SongRecyclerViewAdapter.onPopClickListener onPopClickListener) {
		this.onPopClickListener = onPopClickListener;
	}

	//设置按钮的点击监听
	public onPopClickListener onPopClickListener;
	public interface onPopClickListener{
		void onPopClick(int Position);
	}
}
