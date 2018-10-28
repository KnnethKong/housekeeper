package com.haiwai.housekeeper.utils;

import android.text.TextUtils;

import com.haiwai.housekeeper.entity.NeedResponseDetailEntity;
import com.haiwai.housekeeper.entity.OrderDetailEntity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ihope10 on 2016/10/31.
 */

public class WDUtils2 {
    static Map<String, String> wenMap = null;
    static Map<String, String> daMap = null;
    public static int count = 0;


    public static void getWDMap(NeedResponseDetailEntity.DataBean.DateBean date) {
        if(!date.getType().equals("9")){
            wenMap = new LinkedHashMap<>();
            daMap = new LinkedHashMap<>();
            count = 0;
            if (!TextUtils.isEmpty(date.getWen1())) {
                ++count;
                wenMap.put("wen1", date.getWen1());
                daMap.put("da1", date.getDa1());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen2())) {
                ++count;
                wenMap.put("wen2", date.getWen2());
                daMap.put("da2", date.getDa2());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen3())) {
                ++count;
                wenMap.put("wen3", date.getWen3());
                daMap.put("da3", date.getDa3());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen4())) {
                ++count;
                wenMap.put("wen4", date.getWen4());
                daMap.put("da4", date.getDa4());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen5())) {
                ++count;
                wenMap.put("wen5", date.getWen5());
                daMap.put("da5", date.getDa5());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen6())) {
                ++count;
                wenMap.put("wen6", date.getWen6());
                daMap.put("da6", date.getDa6());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen7())) {
                ++count;
                wenMap.put("wen7", date.getWen7());
                daMap.put("da7", date.getDa7());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen8())) {
                ++count;
                wenMap.put("wen8", date.getWen8());
                daMap.put("da8", date.getDa8());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen9())) {
                ++count;
                wenMap.put("wen9", date.getWen9());
                daMap.put("da9", date.getDa9());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen10())) {
                ++count;
                wenMap.put("wen10", date.getWen10());
                daMap.put("da10", date.getDa10());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen11())) {
                ++count;
                wenMap.put("wen11", date.getWen11());
                daMap.put("da11", date.getDa11());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen12())) {
                ++count;
                wenMap.put("wen12", date.getWen12());
                daMap.put("da12", date.getDa12());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen13())) {
                ++count;
                wenMap.put("wen13", date.getWen13());
                daMap.put("da13", date.getDa13());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen14())) {
                ++count;
                wenMap.put("wen14", date.getWen14());
                daMap.put("da14", date.getDa14());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen15())) {
                ++count;
                wenMap.put("wen15", date.getWen15());
                daMap.put("da15", date.getDa15());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen16())) {
                ++count;
                wenMap.put("wen16", date.getWen16());
                daMap.put("da16", date.getDa16());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen17())) {
                ++count;
                wenMap.put("wen17", date.getWen17());
                daMap.put("da17", date.getDa17());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen18())) {
                ++count;
                wenMap.put("wen18", date.getWen18());
                daMap.put("da18", date.getDa18());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen19())) {
                ++count;
                wenMap.put("wen19", date.getWen19());
                daMap.put("da19", date.getDa19());
            } else {
                ++count;
            }
        }else{
            wenMap = new LinkedHashMap<>();
            daMap = new LinkedHashMap<>();
            count = 0;
            if (!TextUtils.isEmpty(date.getWen1())) {
                ++count;
                wenMap.put("wen1", date.getWen1());
                daMap.put("da1", date.getDa1());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen2())) {
                ++count;
                wenMap.put("wen2", date.getWen2());
                daMap.put("da2", date.getDa2());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen3())) {
                ++count;
                wenMap.put("wen3", date.getWen3());
                daMap.put("da3", date.getDa4());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen4())) {
                ++count;
                wenMap.put("wen4", date.getWen4());
                daMap.put("da4", date.getDa3());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen5())) {
                ++count;
                wenMap.put("wen5", date.getWen5());
                daMap.put("da5", date.getDa6());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen6())) {
                ++count;
                wenMap.put("wen6", date.getWen6());
                daMap.put("da6", date.getDa5());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen7())) {
                ++count;
                wenMap.put("wen7", date.getWen7());
                daMap.put("da7", date.getDa7());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen8())) {
                ++count;
                wenMap.put("wen8", date.getWen8());
                daMap.put("da8", date.getDa8());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen9())) {
                ++count;
                wenMap.put("wen9", date.getWen9());
                daMap.put("da9", date.getDa9());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen10())) {
                ++count;
                wenMap.put("wen10", date.getWen10());
                daMap.put("da10", date.getDa10());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen11())) {
                ++count;
                wenMap.put("wen11", date.getWen11());
                daMap.put("da11", date.getDa11());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen12())) {
                ++count;
                wenMap.put("wen12", date.getWen12());
                daMap.put("da12", date.getDa12());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen13())) {
                ++count;
                wenMap.put("wen13", date.getWen13());
                daMap.put("da13", date.getDa13());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen14())) {
                ++count;
                wenMap.put("wen14", date.getWen14());
                daMap.put("da14", date.getDa14());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen15())) {
                ++count;
                wenMap.put("wen15", date.getWen15());
                daMap.put("da15", date.getDa15());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen16())) {
                ++count;
                wenMap.put("wen16", date.getWen16());
                daMap.put("da16", date.getDa16());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen17())) {
                ++count;
                wenMap.put("wen17", date.getWen17());
                daMap.put("da17", date.getDa17());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen18())) {
                ++count;
                wenMap.put("wen18", date.getWen18());
                daMap.put("da18", date.getDa18());
            } else {
                ++count;
            }
            if (!TextUtils.isEmpty(date.getWen19())) {
                ++count;
                wenMap.put("wen19", date.getWen19());
                daMap.put("da19", date.getDa19());
            } else {
                ++count;
            }
        }

    }

    public static String getWenStr(String strKey) {
        if (wenMap != null) {
            return wenMap.get(strKey);
        } else {
            return null;
        }
    }

    public static String getDaStr(String strKey) {
        if (daMap != null) {
            return daMap.get(strKey);
        } else {
            return null;
        }
    }
}
