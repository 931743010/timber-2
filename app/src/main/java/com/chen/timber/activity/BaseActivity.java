package com.chen.timber.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by chen on 2016/4/4.
 */
public abstract class BaseActivity extends AppCompatActivity{

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(getContentView());
		initView();
		initData();
	}
	protected abstract void initView();

	protected abstract void initData();

	protected abstract int getContentView();

	protected <T extends View> T getView(int id){
		return (T)findViewById(id);
	}

	protected void showToast(String text) {
		Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
	}
}
