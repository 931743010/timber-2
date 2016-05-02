package com.chen.timber.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chen.timber.MusicPlayer;
import com.chen.timber.MusicUtils;
import com.chen.timber.R;
import com.chen.timber.database.AlbumDao;
import com.chen.timber.database.MusicDao;
import com.chen.timber.moudle.MusicInfo;

public class SongDetailActivity extends BaseActivity implements View.OnClickListener{
	private ImageView imBack;
	private ImageView imShare;
	private TextView tvSong;
	private TextView tvArtist;
	private ImageView imAblum;
	private static SeekBar mSeekBar;
	private ImageView imLoop;
	private ImageView imPrev;
	private CheckBox cbPlay;
	private ImageView imNext;
	private MusicInfo musicInfo;
	private AlbumDao albumDao;
	private MusicDao musicDao;
	private int type;
	private MusicPlayer musicPlayer;
	public static final int RESULT_OK=0x04;

	public static Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x07) {
				Bundle data= (Bundle) msg.obj;
				mSeekBar.setMax(data.getInt("duration"));
				int a=data.getInt("currentPosition");
				mSeekBar.setProgress(a);
			}
		}
	};
	@Override
	protected void initView() {
		imBack = getView(R.id.im_back);
		imBack.setOnClickListener(this);
		imShare = getView(R.id.im_detail_share);
		imShare.setOnClickListener(this);
		tvSong = getView(R.id.tv_detail_song);
		tvArtist = getView(R.id.tv_detail_artist);
		imAblum = getView(R.id.im_detail_album);
		imLoop = getView(R.id.ic_detail_loop);
		imLoop.setOnClickListener(this);
		imPrev = getView(R.id.im_detail_previous);
		imPrev.setOnClickListener(this);
		mSeekBar = getView(R.id.seekbar);
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				musicPlayer.seekTo(seekBar.getProgress());
			}
		});
		cbPlay = getView(R.id.cb_detail_play);
		cbPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					if(!musicPlayer.isPlay()){
						musicPlayer.play(musicInfo);
					}
				}else {
					if(musicPlayer.isPlay()){
						musicPlayer.pause();
					}
				}
			}
		});
		imNext = getView(R.id.im_detail_next);
		imNext.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		musicPlayer = MusicPlayer.getInstance(this);
		musicPlayer.addTimer();
		if(musicPlayer.isPlay()){
			cbPlay.setChecked(true);
		}
		musicPlayer = MusicPlayer.getInstance(this);
		type = getIntent().getExtras().getInt("type");
		musicInfo = getIntent().getParcelableExtra("music");
		if (musicInfo != null) {
			updateUi();
		}
		if (type == 0) {
			musicDao = MusicDao.getInstance(this);
		}
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_song_detail;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.im_back:
				Intent intent = new Intent();
				intent.putExtra("music", musicInfo);
				setResult(RESULT_OK,intent);
				finish();
				break;
			case R.id.im_detail_share:
				break;
			case R.id.im_detail_previous:
				if(musicInfo._id>1){
					musicInfo=musicDao.getMusicSongById(musicInfo._id-1);
					updateUi();
					if(cbPlay.isChecked()){
						musicPlayer.play(musicInfo);
					}
				}

				break;
			case R.id.im_detail_next:
				if(musicInfo._id<musicDao.getDataCount()){
					musicInfo = musicDao.getMusicSongById(musicInfo._id + 1);
					updateUi();
					if (cbPlay.isChecked()) {
						musicPlayer.play(musicInfo);
					}
				}
				break;
		}
	}

	private void updateUi() {
		tvSong.setText(musicInfo.musicName);
		tvArtist.setText(musicInfo.artist);
		imAblum.setImageBitmap(MusicUtils.getArtwork(this,musicInfo._id,musicInfo.albumId,true));
	}

}
