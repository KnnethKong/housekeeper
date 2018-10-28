package com.wn.library.utils;

import android.util.Log;

/**
 * Created by 王宁 on 2017/3/2.
 */

public class LogUtils {

    private static final String TAG = "TAG";//同意的tag

    private static boolean isLog = true;//是否打印

    public static void e(String str) {
        if (str.isEmpty()) {
            return;
        }
        if (!isLog) {
            return;
        }
        Log.e(TAG, str);
    }

    public static void i(String str) {
        if (str.isEmpty()) {
            return;
        }
        if (!isLog) {
            return;
        }
        Log.e(TAG, str);
    }

    public static void d(String str) {
        if (str.isEmpty()) {
            return;
        }
        if (!isLog) {
            return;
        }
        Log.e(TAG, str);
    }

    public static void w(String str) {
        if (str.isEmpty()) {
            return;
        }
        if (!isLog) {
            return;
        }
        Log.e(TAG, str);
    }

}
