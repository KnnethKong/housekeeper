package com.haiwai.housekeeper.utils;

import android.util.DebugUtils;
import android.util.Log;

import com.haiwai.housekeeper.BuildConfig;


/**
 * 日志统一管理
 * @author abner
 *
 */
public class LogUtil {

	private final static boolean isLog = BuildConfig.LOG_DEBUG;

	public static void d(String TAG, String msg) {
		if (isLog) {
			Log.d(TAG, msg);
		}
	}

	public static void e(String TAG, String msg) {
		if (isLog) {
			Log.e(TAG, msg);
		}
	}

	public static void i(String TAG, String msg) {
		if (isLog) {
			Log.i(TAG, msg);
		}
	}

}
