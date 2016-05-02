package com.chen.timber.intef;

/**
 * Created by chen on 2016/4/18.
 */
public interface MusicInfc {
	void play(String path);

	void pause();

	void next(String path);

	void last(String path);

	boolean isPlay();

	int getDuration();

	int getCurrentPosition();

	void seekTo(int progress);
}
