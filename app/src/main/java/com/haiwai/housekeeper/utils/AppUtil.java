/**
 *
 */
package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.WindowManager;

import com.haiwai.housekeeper.base.MyApp;

public class AppUtil {

    /**
     * 获取屏幕分辨率
     *
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = {width, height};
        return result;
    }

    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            return info.versionCode;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 获取当前app的应用名字
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
//			Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static final boolean HAS_FROYO;
    public static final boolean HAS_GINGERBREAD;
    public static final boolean HAS_HONEYCOMB;
    public static final boolean HAS_KITKAT;
    // public static final boolean HAS_LOLLIPOP;
    public static final boolean HAS_JELLY_BEAN;

    static {
        int version = Build.VERSION.SDK_INT;
        HAS_FROYO = version >= Build.VERSION_CODES.FROYO;
        HAS_GINGERBREAD = version >= Build.VERSION_CODES.GINGERBREAD;
        HAS_HONEYCOMB = version >= Build.VERSION_CODES.HONEYCOMB;
        HAS_KITKAT = version >= Build.VERSION_CODES.KITKAT;
        // HAS_LOLLIPOP = version >= Build.VERSION_CODES.LOLLIPOP;
        HAS_JELLY_BEAN = version >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * 2.2
     */
    public static boolean hasFroyo() {
        return HAS_FROYO;
    }

    /**
     * 2.3
     */
    public static boolean hasGingerbread() {
        return HAS_GINGERBREAD;
    }

    /**
     * 3.0
     */
    public static boolean hasHoneycomb() {
        return HAS_HONEYCOMB;
    }

    public static boolean hasJellyBean() {
        return HAS_JELLY_BEAN;
    }

    /**
     * 4.4.2
     */
    public static boolean hasKitkat() {
        return HAS_KITKAT;
    }


    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = MyApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */

    public static int px2dip(int px) {
        final float scale = MyApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
