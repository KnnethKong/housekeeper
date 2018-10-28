package com.haiwai.housekeeper.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.service.polling.PollingService;
import com.haiwai.housekeeper.service.polling.PollingUtils;
import com.sobot.chat.SobotApi;

import java.io.File;

/**
 * Created by range on 2017/9/23.
 */

public class UpdateUtils {
    /**
     * 获取版本号
     */
    public static int getAppVersionCode(Context context) {
        int versioncode = -1;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }
    /**
     * 检测版本更新
     *
     * @param context
     * @param isForceCheck 是否强制检测更新
     *                     true强制 - 无论什么网络环境都会提示更新
     *                     false非强制 - WiFi情况下才提示更新
     *                     newVersionCode- 后台请求的版本号
     *                     updataInfo- 更新的内容
     */
    public static void checkUpdate(final Context context, final boolean isForceCheck,int newVersionCode,String updataInfo,String url) {
        if (!isNetworkAvailable(context)) {
            // 无网络时
            if (isForceCheck) {
                // 手动强制检测更新时，提示文字
                ToastUtil.shortToast(context, "请检查网络连接");
            } else {
                // 非强制不做操作
            }
            return;
        }
        // 开始检测更新

        if(newVersionCode>getAppVersionCode(context)){
            showUpdateConfirmDialog(context,updataInfo,url);
        }

    }

    /**
     * 显示更新对话框,包含版本相关信息
     */
    private static void showUpdateConfirmDialog(final Context context, final String updateInfo,final String url) {
        new AlertDialog.Builder(context)
                .setTitle("发现新版本")
                .setMessage(updateInfo)
                .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isWifi(context)) {
                            downLoadApp(context,url);
                        } else {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                            dialogBuilder.setTitle(R.string.tstitle);
                            dialogBuilder.setMessage(R.string.isupdatecontent);
                            dialogBuilder.setCancelable(false);
                            dialogBuilder.setPositiveButton(context.getString(R.string.done), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    downLoadApp(context,url);
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = dialogBuilder.create();
                            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            try {
                                alertDialog.show();
                            }catch (Exception e){

                            }
                        }
                    }
                })
                .setNegativeButton("以后再说", null)
                .show();
    }
    /**
     * 下载文件
     * @param context
     */
    public static void downLoadApp(Context context,String url){
        //downurl:下载app的后台地址
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //  下载时的网络状态，默认是wifi和移动网络都可以下载，如果选择一个，只能在选中的状态下进行下载
        //request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("app");
        request.setDescription("app正在下载");
        request.setAllowedOverRoaming(false);
        //设置文件存放目录
        //判断文件是否存在，保证其唯一性
        File file = context.getExternalFilesDir("Download/downapp"+getAppVersionCode(context));
        if(file.exists()){
            file.delete();
        }
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "downapp"+getAppVersionCode(context));
        DownloadManager downManager = (DownloadManager)context.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        long id = downManager.enqueue(request);
        // 存储下载Key
        SharedPreferences sharedPreferences = context.getSharedPreferences("downloadapp", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("downloadid",id);
        editor.commit();
    }
    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == cm) {
            return false;
        }

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (null != info) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;

    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用来判断网络连接
             //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
