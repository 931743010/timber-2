package com.chen.timber.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chen.timber.MusicPlayer;
import com.chen.timber.R;
import com.chen.timber.adapter.SongRecyclerViewAdapter;
import com.chen.timber.moudle.MusicInfo;
import com.chen.timber.view.DividerItemDecoration;

import java.util.List;

/**
 * Created by chen on 2016/4/4.
 */
public class SongFragment extends BaseFragment{
	private RecyclerView mRecyclerView;
	private MusicPlayer musicPlayer;
	@Override
	protected View initView() {
		musicPlayer=MusicPlayer.getInstance();
		musicPlayer.setContext(mContext);
		View view = View.inflate(mContext, R.layout.song_fragment_layout, null);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.rcy_song);
		return view;
	}

	@Override
	protected void initData(Bundle savedInstanceState) {
		super.initData(savedInstanceState);
		Bundle bundle=getArguments();
		final List<MusicInfo> musicList = bundle.getParcelableArrayList("musicList");
		SongRecyclerViewAdapter adapter = new SongRecyclerViewAdapter(mContext, musicList);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		mRecyclerView.setAdapter(adapter);
		mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.HORIZONTAL_LIST));
		adapter.setOnRecyclerViewListener(new SongRecyclerViewAdapter.OnRecyclerViewListener() {
			@Override
			public void onClick(View view, int position) {
					musicPlayer.play(musicList.get(position).data);
			}
		});
		adapter.setOnPopClickListener(new SongRecyclerViewAdapter.onPopClickListener() {
			@Override
			public void onPopClick(int Position) {

			}
		});
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
