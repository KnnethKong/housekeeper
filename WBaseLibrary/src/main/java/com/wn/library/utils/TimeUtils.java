package com.wn.library.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王宁 on 2017/3/1.
 */

public class TimeUtils {

    enum dateType{
        ymd,hms,ymd_hms
    }
    //格式化 时间戳成特定的时间格式
    public static String formatTime(String date,dateType type){

        SimpleDateFormat format = new SimpleDateFormat(getTimeType(type));

        if(date.length()==13){//系统时间
            return format.format(new Date(Long.valueOf(date)));
        }else{//通过后台获取的Unix时间
            return format.format(new Date(Long.valueOf(date)*1000L));
        }
    }

    //获取格式时间类型

    private static String getTimeType(dateType type){
        String typeTime = "";
        switch (type){
            case ymd:
                typeTime = "yyyy-MM-dd";
                break;
            case hms:
                typeTime = "hh:mm:ss";
                break;
            case ymd_hms:
                typeTime = "yyyy-MM-dd hh:mm:ss";
                break;
        }
        return typeTime;
    }

    //计算两个时间戳相差的时间 返回是以小时为单位

    public static Long timeApartTime(String newDate,String oldDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String newTimeStr ;
        String oldTimeStr;


        if(newDate.length()==13){//系统时间
            newTimeStr = format.format(new Date(Long.valueOf(newDate)));
        }else{//通过后台获取的Unix时间
            newTimeStr = format.format(new Date(Long.valueOf(newDate)*1000L));
        }

        if(oldDate.length()==13){//通过后台获取的Unix时间
            oldTimeStr = format.format(new Date(Long.valueOf(oldDate)));
        }else{//通过后台获取的Unix时间
            oldTimeStr = format.format(new Date(Long.valueOf(oldDate)*1000L));
        }

        Date newTime = null;
        Date oldTime = null;

        try {
            newTime = format.parse(newTimeStr);
            oldTime  =format.parse(oldTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long aprt = (newTime.getTime()-oldTime.getTime())/3600/1000;

        return Math.abs(aprt);
    }
}
