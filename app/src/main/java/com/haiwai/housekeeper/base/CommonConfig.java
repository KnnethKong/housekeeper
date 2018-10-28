package com.haiwai.housekeeper.base;

import android.os.Environment;

/**
 * Created by ihope006 on 2016/6/28.
 */
public class CommonConfig {

//    public static final String BASE_URL = "http://hwgj.zai0312.com/index.php";
//    public static final String BASE_URL = ""

//    public static final String IMG_URL = "http://img.zai0312.com/";

    //public static final String BASE_URL = "http://app.landingbook.net/index.php";//正式环境
  public static final String BASE_URL = "http://hwgj.zai0312.com/index.php";//测试环境
    //http://hwgj.zai0312.com

//     public static final String BASE_URL = "http://sanbox.landingbook.net/index.php";//测试环境



    public static final String IP = "http://52.60.130.165";
//
//    public final static String SD_Cache
//            = Environment.getExternalStorageDirectory() + "/bangbang/";
    public final static String SD_Path
            = Environment.getExternalStorageDirectory() + "/haiwai/download/image/";// 保持在用户外部存储上的，并非cache的
    public final static String SD_Path_Upload
            = Environment.getExternalStorageDirectory() + "/haiwai/upload/image/";
////    public final static String SD_Path_VIDEO
////            = Environment.getExternalStorageDirectory() + "/bangbang/upload/video/";
//
    public static final String NO_NETWORK = "网络不给力";
//
//    public static final String NO_NEXTPAGE = "没有下一页数据";
//
//    public static final String SHARE_IMAGE = "share.png";
//
//
//    public static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
//    public static final String accessKeyId = "SMuHd0n9G3ggtAkP";
//    public static final String accessKeySecret = "2bfuLmg1IRgMoYPJ3CARDVY3VQt1AX";
//    public static final String bucket = "sybuck";


}
