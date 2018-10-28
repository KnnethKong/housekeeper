package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.haiwai.housekeeper.base.CommonConfig;

/**
 * Created by ihope10 on 2016/9/27.
 */
public class NetUtil {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (null != connectivity) {
//            NetworkInfo info = connectivity.getActiveNetworkInfo();
//            if (null != info && info.isConnected()) {
//                if (info.getState() == NetworkInfo.State.CONNECTED) {
//                    return true;
//                }
//            }
//        }
//        ToastUtil.shortToast(this, CommonConfig.NO_NETWORK);
//        return false;
        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return true;
        }
        ToastUtil.shortToast(context, CommonConfig.NO_NETWORK);
        return false;
    }
}
