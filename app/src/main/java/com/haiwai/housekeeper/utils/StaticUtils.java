package com.haiwai.housekeeper.utils;

import android.content.Context;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.https.Contants;

/**
 * Created by ihope10 on 2016/11/5.
 */

public class StaticUtils {
    public static String getStaticStr(Context context,String staticStr) {
        String str = "";
        switch (staticStr) {
            case "0"://待接单
                str = context.getString(R.string.zt1);
                break;
            case "1"://待选择pro
                str = context.getString(R.string.zt2);
                break;
            case "2"://已接单
                str = context.getString(R.string.zt3);
                break;
            case "3"://修改报价待确认
                str = context.getString(R.string.zt4);
                break;
            case "4"://驳回
                str = context.getString(R.string.zt5);
                break;
            case "5"://服务中
                str = context.getString(R.string.zt6);
                break;
            case "6"://完成订单
                str = context.getString(R.string.zt7);
                break;
            case "7"://已清零
                str = context.getString(R.string.zt8);
                break;
            case "8"://用户支付
                str = context.getString(R.string.main_need_service);
                break;
            case "9"://自己已取消
                str = context.getString(R.string.zt10);
                break;
            case "10"://服务商取消
                str = context.getString(R.string.zt11);
                break;
            case "11"://服务商取消
                str = context.getString(R.string.zt12);
                break;
            case "12"://服务商取消
                str = context.getString(R.string.zt13);
                break;
            case "13"://服务商取消
                str = context.getString(R.string.zt14);
                break;
        }

        return str;
    }


    public static String getWeekStaticStr(Context context,String staticStr) {
        String str = "";
        switch (staticStr) {
            case "1"://未接单
                str = context.getString(R.string.zt21);
                break;
            case "2"://待选择
                str = context.getString(R.string.zt22);
                break;
            case "3"://已接单
                str = context.getString(R.string.zt23);
                break;
            case "4"://服务中
                str = context.getString(R.string.zt24);
                break;
            case "5"://完成
                str = context.getString(R.string.zt25);
                break;
            case "6"://暂停
                str = context.getString(R.string.zt26);
                break;
        }

        return str;
    }


    /**
     * 自营类型
     *
     * @param type
     * @return
     */
    public static String getWeekTypeStr(String type, String isZhorEn) {
        String str = "";
        switch (type) {
            case "29":
                if ("en".equals(isZhorEn)) {
                    str = "Mails Management";
                } else {
                    str = "信件管理";
                }
                break;
            case "30":
                if ("en".equals(isZhorEn)) {
                    str = "House Cleaning";
                } else {
                    str = "家政服务";
                }
                break;
            case "31":
                if ("en".equals(isZhorEn)) {
                    str = "Rental Management";
                } else {
                    str = "租赁管理";
                }
                break;
            case "32":
                if ("en".equals(isZhorEn)) {
                    str = "Lawn Mowing";
                } else {
                    str = "草坪修剪";
                }
                break;
            case "33":
                if ("en".equals(isZhorEn)) {
                    str = "Snow Plowing";
                } else {
                    str = "冬季扫雪";
                }
                break;
            case "34":
                if ("en".equals(isZhorEn)) {
                    str = "Leaves Cleaning";
                } else {
                    str = "秋季落叶清扫";
                }
                break;
            case "35":
                if ("en".equals(isZhorEn)) {
                    str = "For Renting";
                } else {
                    str = "租赁-招租";
                }
                break;
        }
        return str;
    }
}
