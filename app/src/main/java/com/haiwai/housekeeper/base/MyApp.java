/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */
package com.haiwai.housekeeper.base;

import android.app.ActivityManager;
import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.translate.HttpCallBack;
import com.baidu.translate.RequestUtils;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.base.SampleExtensionModule;
import com.haiwai.housekeeper.activity.base.SealExtensionModule;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.activity.user.TranslateActivity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.receiver.DownLoadCompleteReceiver;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.AssetsUtils2;
import com.haiwai.housekeeper.utils.CrashHandlerUtil;
import com.haiwai.housekeeper.utils.EnOrCnUtils;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.Con2PopView;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * 应用程序对象
 *
 * @author abner
 */
public class MyApp extends MultiDexApplication {

    public static Context context;
    private static MyApp sApp;
    private RequestQueue mRequestQueue;

    //声明mLocationOption对象
//    private AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
//    private AMapLocationClient mLocationClient = null;

    //    private RefWatcher mRrefWatcher;
//    public RefWatcher getRrefWatcher() {
//        return mRrefWatcher;
//    }
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        CrashHandler.getInstance().init(this);
//        mRrefWatcher = LeakCanary.install(this);
        context = getApplicationContext();
        MapboxAccountManager.start(this, Contants.TOKENS);
        initUpdata();
        sApp = this;
        String str = getResources().getConfiguration().locale.getLanguage();
        if (!TextUtils.isEmpty(str)) {
            AppGlobal.getInstance().setLagStr(str);
        }
        //推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //分享
        PlatformConfig.setWeixin("wx352971154270fabc", "62fcdaa74e82753ffcf3f9fc1d4e4754");
        UMShareAPI.get(this);
//        Config.DEBUG = true;
        /**
         * 异常处理
         */


//        CrashHandlerUtil crashHandlerUtil = CrashHandlerUtil.getInstance();
//        crashHandlerUtil.init(this);
//        crashHandlerUtil.setCrashTip("很抱歉，程序出现异常，即将退出！");
        AssetsUtils.parseJson(context);
        AssetsUtils2.parseJson(context);
        //配置ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);//全局初始化此配置
//        initLocation();
        initializeRequest();
        ImageLoaderUtils.initImageCache(context);

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);//融云全局初始化
            RongIM.getInstance().setMessageAttachedUserInfo(true);
            List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();

            IExtensionModule defaultModule = null;
            if (moduleList != null) {
                for (IExtensionModule module : moduleList) {
                    if (module instanceof DefaultExtensionModule) {
                        defaultModule = module;
                        break;
                    }
                }
                if (defaultModule != null) {
                    //移除已注册的默认模块，替换成自定义模块
                    RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                    RongExtensionManager.getInstance().registerExtensionModule(new SealExtensionModule());
                }
            }
//            for(IExtensionModule module:moduleList){
//                RongExtensionManager.getInstance().unregisterExtensionModule(module);
//            }
//            RongExtensionManager.getInstance().registerExtensionModule(new SampleExtensionModule());
        }
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=589acc6f");
    }


    public static ImageLoader getImageLoader() {
        return ImageLoader.getInstance();
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 初始化app更新广播
     */
    private void initUpdata() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        filter.addCategory("android.intent.category.DEFAULT");
        DownLoadCompleteReceiver updatereceiver = new DownLoadCompleteReceiver();
        registerReceiver(updatereceiver, filter);
    }
//    @Override
//    public void onTerminate() {
//        super.onTerminate();
////        mRrefWatcher = LeakCanary.install(this);
//    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public synchronized void initializeRequest() {
        if (mRequestQueue == null) {
            synchronized (context) {
                if (mRequestQueue == null) {
                    mRequestQueue = Volley.newRequestQueue(this);
                }
            }
        }
        mRequestQueue.start();
    }

    /**
     * 获取异步http请求队列对象
     *
     * @return RequestQueue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            throw new RuntimeException("请先初始化mRequestQueue");
        return mRequestQueue;
    }


    /**
     * 直接以静态方法获取应用程序对象
     *
     * @return CatnutApp
     */
    public static MyApp getTingtingApp() {
        return sApp;
    }

    public boolean isLogin() {
        return SPUtils.getBoolean(context, "Login_State", false);
    }

    public void setLoginState(boolean login) {
        SPUtils.saveBoolean(context, "Login_State", login);
    }

    public void setUser_id(String userid) {
        SPUtils.saveString(context, "User_Id", userid);
    }

    public String getUser_id() {
        return SPUtils.getString(context, "User_Id", "0");
    }

    public final String token = "96e1101cacf991bf94f7ce2342e222b8";

//    public List<ProvinceEntity.Data> provinceList;

//    public String provinceId = "100000";
//    public String province = "全国";

//    private void initLocation() {
//        mLocationClient = new AMapLocationClient(getApplicationContext());
////初始化定位
//        mLocationClient = new AMapLocationClient(this);
////初始化定位参数
//        mLocationOption = new AMapLocationClientOption();
////设置定位监听//设置定位回调监听
//        mLocationClient.setLocationListener(mLocationListener);
////设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
////设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(2000);
////设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
//// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
//// 在定位结束后，在合适的生命周期调用onDestroy()方法
//// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
////启动定位
//        mLocationClient.startLocation();
//    }


//    //声明定位回调监听器
//    public AMapLocationListener mLocationListener = new AMapLocationListener() {
//        @Override
//        public void onLocationChanged(AMapLocation aMapLocation) {
//            if (aMapLocation != null) {
//                if (aMapLocation.getErrorCode() == 0) {
//                    //定位成功回调信息，设置相关消息
////                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
////                    aMapLocation.getLatitude();//获取纬度
////                    aMapLocation.getLongitude();//获取经度
////                    aMapLocation.getAccuracy();//获取精度信息
////                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////                    Date date = new Date(aMapLocation.getTime());
////                    df.format(date);//定位时间
//
//                    LocationEntity locationEntity = new LocationEntity();
//                    locationEntity.locationtype = aMapLocation.getLocationType();
//                    locationEntity.latitude = aMapLocation.getLatitude();
//                    locationEntity.longitude = aMapLocation.getLongitude();
//                    locationEntity.country = aMapLocation.getCountry();
//                    locationEntity.city = aMapLocation.getCity();
//                    locationEntity.address = aMapLocation.getAddress();
//                    locationEntity.district = aMapLocation.getDistrict();
//                    locationEntity.street = aMapLocation.getStreet();
//                    locationEntity.province = aMapLocation.getProvince();
//                    locationEntity.accuracy = aMapLocation.getAccuracy();
//                    locationEntity.setLocation(locationEntity);
//
//                } else {
//                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
//                    LogUtil.e("AmapError", "location Error, ErrCode:"
//                            + aMapLocation.getErrorCode() + ", errInfo:"
//                            + aMapLocation.getErrorInfo());
//                }
//            }
//            mLocationClient.stopLocation();
//        }
//    };
//    /**
//     * 激活定位
//     */
//    @Override
//    public void activate(OnLocationChangedListener onLocationChangedListener) {
//        mListener = onLocationChangedListener;
//        if (mLocationClient == null) {
//            mLocationClient = new AMapLocationClient(this);
//            mLocationOption = new AMapLocationClientOption();
//            //设置定位监听
//            mLocationClient.setLocationListener(mLocationListener);
//            //设置为高精度定位模式
//            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//            //设置定位参数
//            mLocationClient.setLocationOption(mLocationOption);
//            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
//            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
//            // 在定位结束后，在合适的生命周期调用onDestroy()方法
//            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//            mLocationClient.startLocation();
//        }
//    }
//    /**
//     * 停止定位
//     */
//    @Override
//    public void deactivate() {
//        mListener = null;
//        if (mLocationClient != null) {
//            mLocationClient.stopLocation();
//            mLocationClient.onDestroy();
//        }
//        mLocationClient = null;
//    }

    public boolean isLogout;

    //add by liu_yjie 2016/0927
    public static Context getContext() {
        return context;
    }

    public static Typeface getTf() {
        return AssetsUtils.getTfs(context);
    }


}
