package com.haiwai.housekeeper.receiver;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.haiwai.housekeeper.utils.ToastUtil;

import java.io.File;

/**
 * Created by range on 2017/9/23.
 */

public class DownLoadCompleteReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            installApk(context, id);
        } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
        }
    }

    /**
     * 下载完后安装apk
     *
     * @param
     */

    // 安装Apk
    private void installApk(Context context, long downloadApkId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("downloadapp", Activity.MODE_PRIVATE);
        long id = sharedPreferences.getLong("downloadid", 0);

        // 获取存储ID
        if (downloadApkId == id) {
            DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

            Uri downloadFileUri = dManager.getUriForDownloadedFile(downloadApkId);
            if (downloadFileUri != null) {
                Intent install = new Intent(Intent.ACTION_VIEW);
                File apkFile = context.getExternalFilesDir("Download/downapp"+getAppVersionCode(context));
                //对Android 版本判断
                if (Build.VERSION.SDK_INT >= 24) {
                    install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    // context.getPackageName() + ".fileprovider"  是配置中的authorities
                    Uri contentUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", apkFile);
                    install.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
                }
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(install);
            } else {
                ToastUtil.shortToast(context,"下载失败");
            }
        }
    }
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
}
