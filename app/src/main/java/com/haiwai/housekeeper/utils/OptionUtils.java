package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by ihope006 on 2017/1/9.
 */

public class OptionUtils {
    public static DisplayImageOptions getOptions(Context context, int url) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(url) // URI为空时显示的图片资源ID
                .resetViewBeforeLoading(false)  // default 图片在下载前是否重置,复位
                .delayBeforeLoading(1000) // 图片开始加载前的延时.默认是0
                .cacheOnDisk(false) // default , 是否缓存在硬盘 , 默认不缓存
                .considerExifParams(false) // default , 设置是否考虑JPEG图片的EXIF参数信息,默认不考虑
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) // default , 指定图片缩放的方式,ListView/GridView/Gallery推荐使用此默认值
                .bitmapConfig(Bitmap.Config.RGB_565) // default , 指定图片的质量,默认是 ARGB_8888
//                .decodingOptions(...) // 指定图片的解码方式
//                .displayer(new SimpleBitmapDisplayer()) // default , 设置图片显示的方式,用于自定义
//                .handler(new Handler()) // default ,设置图片显示的方式和ImageLoadingListener的监听, 用于自定义
                .build();
        return options;
    }
}
