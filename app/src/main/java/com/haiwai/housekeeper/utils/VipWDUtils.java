package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.entity.NewHousEntity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ihope006 on 2016/12/8.
 */

public class VipWDUtils {
    static Map<String, NewHousEntity.DataBean.DateBean> map = new LinkedHashMap<>();
    static Map<String, String> imgMap = new LinkedHashMap<>();
    public static int mCount = 0;

    public static void ParseData(NewHousEntity zyHousEntity) {
        mCount = zyHousEntity.getData().getDate().size();
        for (int i = 0; i < mCount; i++) {
            int a = i + 1;
            map.put("step" + a, zyHousEntity.getData().getDate().get(i));
        }
        getImgPath(zyHousEntity);
    }

    public static NewHousEntity.DataBean.DateBean getBeans(String step) {
        return map.get(step);
    }

    public static void getImgPath(NewHousEntity zyHousEntity) {
        imgMap.put("picPath", zyHousEntity.getData().getImg());
    }

    public static String getPicPath() {
        return imgMap.get("picPath");
    }
}
