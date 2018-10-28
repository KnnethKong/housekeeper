package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;


import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.CommonConfig;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * 配置ImageLoader
 */
public class ImageLoaderUtils {

    public static void initImageCache(Context mContext) {

        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            // 保存到sd卡
            cacheDir = new File(CommonConfig.SD_Path);
        } else {
            // 保存到file目录
            cacheDir = new File(mContext.getFilesDir().getAbsolutePath()
                    + File.separator + "haiwai/download/image/");
        }

        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        Log.e("这个缓存file值是什么路径？？", cacheDir + "");
        ImageLoaderConfiguration configuration;// 开始构建
        configuration = new ImageLoaderConfiguration.Builder(
                mContext)
                // maxwidth, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(1080, 1920)
                // 线程池内加载的数量
                .threadPoolSize(3)
                // 线程优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                // implementation/你可以通过自己的内存缓存实现
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                // 硬盘缓存50MB
                .diskCacheSize(50 * 1024 * 1024)
                .memoryCacheSize(6 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // 自定义缓存路径
                .diskCache(new UnlimitedDiskCache(cacheDir))
                // connectTimeout (5 s), readTimeout (30 s)超时时间
//				.imageDownloader(
//						new BaseImageDownloader(mContext, 5 * 1000, 30 * 1000))
                .imageDownloader(new BaseImageDownloader(mContext, 5 * 1000, 30 * 1000))
                .writeDebugLogs() // Remove for releaseapp
                .build();
        ImageLoader.getInstance().init(configuration);

    }

    /**
     * 非圆角
     */
    public static DisplayImageOptions getOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.timg)
                // 加载图片时的图片
                .showImageForEmptyUri(R.mipmap.timg)
                // 没有图片资源时的默认图片
                .showImageOnFail(R.mipmap.timg)
                // 加载失败时的图片
                // 设置下载的图片是否缓存在内存中
                .cacheInMemory(true)
                // 启用外存缓存
                .cacheOnDisk(true)
                // 保留Exif信息
                .considerExifParams(true)
                // 设置图片以如何的编码方式显示
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                // 设置图片的解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                // 设置图片的解码配置
                .considerExifParams(true)
                // 设置图片下载前的延迟
                .delayBeforeLoading(50).resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
//				.displayer(new FadeInBitmapDisplayer(100))// 淡入
                .displayer(new SimpleBitmapDisplayer())//
                .build();
        return options;
    }

    public static DisplayImageOptions getAvatarOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.moren_head)
                // 加载图片时的图片
                .showImageForEmptyUri(R.mipmap.moren_head)
                // 没有图片资源时的默认图片
                .showImageOnFail(R.mipmap.moren_head)
                // 加载失败时的图片
                // 设置下载的图片是否缓存在内存中
                .cacheInMemory(true)
                // 启用外存缓存
                .cacheOnDisk(true)
                // 保留Exif信息
                .considerExifParams(true)
                // 设置图片以如何的编码方式显示
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                // 设置图片的解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                // 设置图片的解码配置
                .considerExifParams(true)
                // 设置图片下载前的延迟
                .delayBeforeLoading(100).resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
//		.displayer(new FadeInBitmapDisplayer(100))// 淡入
                .displayer(new SimpleBitmapDisplayer())//
                .build();
        return options;
    }

    public static DisplayImageOptions getNewAvatarOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.person_page_icon)  // empty URI时显示的图片
                .showImageOnFail(R.mipmap.person_page_icon)       // 不是图片文件 显示图片
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(false)           // default 不缓存至内存
                .cacheOnDisc(false)             // default 不缓存至手机SDCard
                .bitmapConfig(Bitmap.Config.ARGB_8888)              // default
                .build();
        return options;
    }


    public static DisplayImageOptions getDefOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.pic_add)
                // 加载图片时的图片
                .showImageForEmptyUri(R.mipmap.pic_add)
                // 没有图片资源时的默认图片
                .showImageOnFail(R.mipmap.pic_add)
                // 加载失败时的图片
                // 设置下载的图片是否缓存在内存中
                .cacheInMemory(true)
                // 启用外存缓存
                .cacheOnDisk(true)
                // 保留Exif信息
                .considerExifParams(true)
                // 设置图片以如何的编码方式显示
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                // 设置图片的解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                // 设置图片的解码配置
                .considerExifParams(true)
                // 设置图片下载前的延迟
                .delayBeforeLoading(100).resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
//		.displayer(new FadeInBitmapDisplayer(100))// 淡入
                .displayer(new SimpleBitmapDisplayer())//
                .build();
        return options;
    }

}
