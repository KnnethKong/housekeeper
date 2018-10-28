package com.wn.library.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by 王宁 on 2017/3/1.
 */

public class StrFormatUtils {

    public enum numEnum{
        noPoint,point
    }
    //MD5加密
    public static String StringToHexString(String str) {
        StringBuilder sb = new StringBuilder("");
        try {
            char[] chars = "0123456789ABCDEF".toCharArray();
            byte[] bs = str.getBytes();
            int bit;
            for (int i = 0; i < bs.length; i++) {
                bit = (bs[i] & 0x0f0) >> 4;
                sb.append(chars[bit]);
                bit = bs[i] & 0x0f;
                sb.append(chars[bit]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString().trim();
    }


    //数字格式
    public static String  numFormat(String num,numEnum numEnum){
        return new DecimalFormat(getNumType(numEnum)).format(getDoubleNum(num));
    }

    //数字格式
    private static long getDoubleNum(String num){
        return BigDecimal.valueOf(Long.valueOf(num)).longValue();
    }

    //格式化格式
    private static String getNumType(numEnum numEnum){
        String type = "";
        switch (numEnum){
            case noPoint:
                type = "#,###";
                break;
            case point:
                type = "#,###.00";
                break;
        }

        return type;
    }
}
