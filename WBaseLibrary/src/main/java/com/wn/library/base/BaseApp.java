package com.wn.library.base;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.wn.library.R;

import okhttp3.OkHttpClient;

/**
 * Created by 王宁 on 2017/2/16.
 */

public class BaseApp extends Application{

    private DisplayImageOptions roundOptions,circleOptions;

    private final OkHttpClient okHttpClient = new OkHttpClient();

    private static BaseApp baseApp;

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public DisplayImageOptions getRoundOptions() {
        return roundOptions;
    }

    public DisplayImageOptions getCircleOptions() {
        return circleOptions;
    }

    public static BaseApp getBaseApp() {
        return baseApp;
    }

    @Override
    public void onCreate() {
        this.baseApp = this;
        super.onCreate();
        initSetting();
    }

    private void initSetting() {


        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        ImageLoader.getInstance().init(config.build());

        roundOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.no_img)
                .showImageForEmptyUri(R.mipmap.no_img).showImageOnFail(R.mipmap.no_img)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new RoundedBitmapDisplayer(0)).build();

        circleOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.no_img)
                .showImageForEmptyUri(R.mipmap.no_img).showImageOnFail(R.mipmap.no_img)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new CircleBitmapDisplayer()).build();

    }
}
