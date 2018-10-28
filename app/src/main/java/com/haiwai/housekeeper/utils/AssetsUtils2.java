package com.haiwai.housekeeper.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.HomeEntity;
import com.haiwai.housekeeper.entity.SkillCertificate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ihope10 on 2016/10/12.
 */
public class AssetsUtils2 {
    private SkillCertificate mCertificate;
    private static Map<String, String> zhMap = new LinkedHashMap<>();
    private static Map<String, String> enMap = new LinkedHashMap<>();

    public static Typeface getTf(Context context) {
        String path = "fonts/fzltcxhjt.TTF";
        return Typeface.createFromAsset(context.getAssets(), path);
    }

    public static Typeface getTfs(Context context) {
        String path = "fonts/fzxytj.ttf";
        return Typeface.createFromAsset(context.getAssets(), path);
    }

//    public static String getCodeJson(Context context) {
//        StringBuilder codeBuilder = new StringBuilder();
//        AssetManager assetManager = context.getAssets();
//        try {
//            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open("codes.json")));
//            String line;
//            while ((line = bf.readLine()) != null) {
//                codeBuilder.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return codeBuilder.toString();
//    }

    public static String getJson(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("skill_type2.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void parseJson(Context context) {
        String json = getJson(context);
        try {
            JSONArray jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    JSONArray cnArray = jsonObject.optJSONArray("hccn");
                    if (cnArray != null) {
                        for (int j = 0; j < cnArray.length(); j++) {
                            JSONObject cnObject = cnArray.optJSONObject(j);
                            zhMap.put(cnObject.optString("type"), cnObject.optString("ty"));
                        }
                    }
                    JSONArray enArray = jsonObject.optJSONArray("hcen");
                    if (enArray != null) {
                        for (int j = 0; j < enArray.length(); j++) {
                            JSONObject enObject = enArray.optJSONObject(j);
                            enMap.put(enObject.optString("type"), enObject.optString("ty"));
                        }
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getSkillName(String type, String isZh) {
        String str = "";
        if ("en".equals(isZh)) {
            str = enMap.get(type);
        } else if ("zh".equals(isZh)) {
            str = zhMap.get(type);
        }
        return str;
    }

    public static String getSkillSingleName(String type, String isZh) {
        String str = "";
        if (Integer.valueOf(type) < 29) {
            if ("en".equalsIgnoreCase(isZh)) {
                str = enMap.get(type);
            } else if ("zh".equalsIgnoreCase(isZh)) {
                str = zhMap.get(type);
            }
        }
        return str;
    }

    public static String getSkillSingleName(String type, String isZh, String orderFlag) {
        String str = "";
        if ("single".equals(orderFlag)) {
            if (Integer.valueOf(type) < 29) {
                if ("en".equalsIgnoreCase(isZh)) {
                    str = enMap.get(type);
                } else if ("zh".equalsIgnoreCase(isZh)) {
                    str = zhMap.get(type);
                }
            }
        } else {
            if (Integer.valueOf(type) > 28) {
                if ("en".equalsIgnoreCase(isZh)) {
                    str = enMap.get(type);
                } else if ("zh".equalsIgnoreCase(isZh)) {
                    str = zhMap.get(type);
                }
            }
        }
        return str;
    }


    public static String getTypeByName(String name, String isZh) {
        String str = "";
        for (int i = 1; i < 29; i++) {
            if (name.equals(getSkillSingleName(i + "", isZh))) {
                str = i + "";
            }
        }
        return str;
    }

    public static String getTypeByName(String name, String isZh, String orderFlag) {
        String str = "";
        if ("single".equals(orderFlag)) {
            for (int i = 1; i < 29; i++) {
                if (name.equals(getSkillSingleName(i + "", isZh, orderFlag))) {
                    str = i + "";
                }
            }
        } else {
            for (int i = 29; i <= 35; i++) {
                if (name.equals(getSkillSingleName(i + "", isZh, orderFlag))) {
                    str = i + "";
                }
            }
        }
        return str;
    }


    public static String getStatus(Context context, String status) {
        if ("0".equals(status)) {
            return context.getString(R.string.zt111);
        } else if ("1".equals(status)) {
            return context.getString(R.string.zt112);
        } else if ("2".equals(status)) {
            return context.getString(R.string.zt113);
        } else {
            return null;
        }
    }


    public static String getSFStatus(Context context, String status) {
        if ("0".equals(status)) {
//            return context.getString(R.string.zt221);
            return context.getString(R.string.zt222);
        } else if ("1".equals(status)) {
            return context.getString(R.string.zt223);
        } else if ("2".equals(status)) {
            return context.getString(R.string.zt223);
        } else if ("3".equals(status)) {
            return context.getString(R.string.zt224);
        } else {
            return null;
        }
    }






    public static String getDis(String str, String isZhorEn) {
        if ("en".equals(isZhorEn)) {
            if ("Not limited".equals(str)) {
                return "0";
            } else if (str.contains("30")) {
                return "30";
            }else if(str.contains("10")){
                return "10";
            } else {
                return "";
            }
        } else {
            if ("不限".equals(str)) {
                return "0";
            }else if (str.contains("30")) {
                return "30";
            }else if(str.contains("10")){
                return "10";
            }else {
                return "";
            }
        }
    }

    public static String getIsAt(String str, String isZhorEn) {
        if ("en".equals(isZhorEn)) {
            if ("Not limited".equals(str)) {
                return "0";
            } else if ("yes".equalsIgnoreCase(str)) {
                return "1";
            } else if ("no".equalsIgnoreCase(str)) {
                return "2";
            } else {
                return "";
            }
        } else {
            if ("不限".equals(str)) {
                return "0";
            } else if ("是".equals(str)) {
                return "1";
            } else if ("否".equals(str)) {
                return "2";
            } else {
                return "";
            }
        }

    }

    public static void initSkillData(HomeEntity entity) {
//        if (entity != null) {
//            int jiajAccount = entity.getData().getJiaj().size();
//            int fanwAccount = entity.getData().getFanw().size();
//            int shenhjAccount = entity.getData().getShenh().size();
//            int ziyAccount = entity.getData().getZiy().size();
//            for (int i = 0; i < jiajAccount; i++) {
//                entity.getData().getJiaj().get(i).getId();
//                entity.getData().getJiaj().get(i).getName();
//            }
//            for (int i = 0; i < fanwAccount; i++) {
//
//            }
//            for (int i = 0; i < shenhjAccount; i++) {
//
//            }
//            for (int i = 0; i < ziyAccount; i++) {
//
//            }
//        }
    }

//    public static ArrayList<CodesEntity> getList(Context context) {
//        ArrayList<CodesEntity> list = new ArrayList<>();
//        list.clear();
//        String json = getCodeJson(context);
//        try {
//            if (!TextUtils.isEmpty(json)) {
//                JSONArray jsonArray = new JSONArray(json);
//                if (jsonArray != null && jsonArray.length() > 0) {
//                    for (int i = 0, count = jsonArray.length(); i < count; i++) {
//                        JSONObject jsonObject = jsonArray.optJSONObject(i);
//                        CodesEntity codesEntity = new CodesEntity();
//                        codesEntity.setCname(jsonObject.optString("cname"));
//                        codesEntity.setEname(jsonObject.optString("ename"));
//                        codesEntity.setCode(jsonObject.optString("code"));
//                        list.add(codesEntity);
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}
