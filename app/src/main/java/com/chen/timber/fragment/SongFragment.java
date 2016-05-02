package com.chen.timber.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chen.timber.R;
import com.chen.timber.adapter.SongRecyclerViewAdapter;
import com.chen.timber.database.MusicDao;
import com.chen.timber.moudle.MusicInfo;
import com.chen.timber.view.DividerItemDecoration;
import com.cocosw.bottomsheet.BottomSheet;

import java.util.List;

/**
 * Created by chen on 2016/4/4.
 */
public class SongFragment extends BaseFragment {
	private RecyclerView mRecyclerView;
	private MusicDao musicDao;
	private List<MusicInfo> musicList;
	private MusicInfo currentMusicInfo;

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
		musicList = musicDao.getMusicInfo();
		SongRecyclerViewAdapter adapter = new SongRecyclerViewAdapter(mContext, musicList);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL_LIST));
		mRecyclerView.setAdapter(adapter);
		adapter.setOnRecyclerViewListener(new SongRecyclerViewAdapter.OnRecyclerViewListener() {
			@Override
			public void onClick(View view, int position) {
				currentMusicInfo = musicList.get(position);
				((NotifyUi) mContext).notifyMusic(currentMusicInfo);
			}
		});
		adapter.setOnPopClickListener(new SongRecyclerViewAdapter.onPopClickListener() {
			@Override
			public void onPopClick(final int position) {
				new BottomSheet.Builder((Activity) mContext).title(musicList.get(position).musicName).sheet(R.menu.menu_list_layout).listener(new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
							case R.id.menu_love:
								musicDao.setFavoriteStateById(musicList.get(position)._id,1);
								break;
							case R.id.menu_delete:
								break;
							case R.id.menu_share:
								break;
							case R.id.menu_ring:
								break;
						}
					}
				}).show();

			}
		});
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}


}
