package com.haiwai.housekeeper.widget.pickerview;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王宁 on 2017/3/1.
 */

public class TimeUtils {
    //两个事
    public static double timeApartTime(String date1,String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(format.format(new Date(Long.valueOf(date1))));
            d2 = format.parse(format.format(new Date(Long.valueOf(date2))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long t1 = d1.getTime();
        long t2 = d2.getTime();
        double hour = (double) (t2 - t1) / 3600 / 1000;
        Log.i("hourTime", hour + "");
       return hour;
    }
    //格式化时间
    public static String formatTime(String formate,String date){
        SimpleDateFormat format = new SimpleDateFormat(formate);
        return format.format(new Date(Long.valueOf(date)));
    }
}
