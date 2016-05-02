package com.chen.timber.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chen.timber.R;
import com.chen.timber.adapter.FavRecyclerViewAdapter;
import com.chen.timber.database.MusicDao;
import com.chen.timber.moudle.MusicInfo;
import com.chen.timber.view.DividerItemDecoration;

import java.util.List;

/**
 * Created by chen on 2016/4/4.
 */
public class FavFragment extends BaseFragment{
	private RecyclerView mRecyclerView;
	private MusicDao musicDao;
	private List<MusicInfo> musicList;
	private MusicInfo currentMusicInfo;
	private FavRecyclerViewAdapter favRecyclerViewAdapter;
	private boolean isFirst=true;
	@Override
	protected View initView() {
		View view = View.inflate(mContext, R.layout.song_fragment_layout, null);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.rcy_song);
		return view;
	}

	@Override
	protected void initData(Bundle savedInstanceState) {
		super.initData(savedInstanceState);
		musicDao = MusicDao.getInstance(mContext);
		musicList=musicDao.getFavMusicList();
		if(musicList.size()==0){
			mRecyclerView.setVisibility(View.GONE);
		}else {
			favRecyclerViewAdapter = new FavRecyclerViewAdapter(mContext, musicList);
			mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
			mRecyclerView.setItemAnimator(new DefaultItemAnimator());
			mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.HORIZONTAL_LIST));
			mRecyclerView.setAdapter(favRecyclerViewAdapter);
			isFirst=false;
			favRecyclerViewAdapter.setOnPopClickListener(new FavRecyclerViewAdapter.onPopClickListener() {
				@Override
				public void onPopClick(int Position) {
					musicDao.setFavoriteStateById(musicList.get(Position)._id,0);
					Toast.makeText(mContext,"取消收藏成功！",Toast.LENGTH_SHORT).show();
					musicList.remove(Position);
					favRecyclerViewAdapter.notifyDataSetChanged();
				}
			});

			favRecyclerViewAdapter.setOnRecyclerViewListener(new FavRecyclerViewAdapter.OnRecyclerViewListener() {
				@Override
				public void onClick(View view, int position) {
					currentMusicInfo = musicList.get(position);
					((NotifyUi) mContext).notifyMusic(currentMusicInfo);
				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();
//		if (!isFirst) {
//			musicList=musicDao.getFavMusicList();
//			favRecyclerViewAdapter.notifyDataSetChanged();
//		}
	}
}
