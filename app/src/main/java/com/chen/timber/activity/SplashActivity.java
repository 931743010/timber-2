package com.chen.timber.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.chen.timber.R;

public class SplashActivity extends BaseActivity {

	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			startActivity(new Intent(SplashActivity.this,MainActivity.class));

			SplashActivity.this.finish();
		}
	};

	@Override
	protected void initView() {

	}

	@Override
	protected void initData() {
		handler.sendEmptyMessageDelayed(0, 1500);
	}

	@Override
	protected int getContentView() {
		return R.layout.activity_splash;
	}
}
