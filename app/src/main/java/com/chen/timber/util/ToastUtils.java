package com.chen.timber.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chen on 2016/4/14.
 */
public class ToastUtils {
	public static void showToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
