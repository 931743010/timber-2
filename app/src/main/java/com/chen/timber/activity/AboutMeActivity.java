package com.chen.timber.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chen.timber.R;

public class AboutMeActivity extends BaseActivity {
	private Toolbar mToolbar;
	@Override
	protected void initView() {
		mToolbar = getView(R.id.tr_about_me);
		setSupportActionBar(mToolbar);
		ActionBar supActionBar = getSupportActionBar();
		supActionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected int getContentView() {
		return R.layout.activity_about_me;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		return true;
	}
}
