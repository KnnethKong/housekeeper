package com.haiwai.housekeeper.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import cn.jiguang.net.HttpResponse;

/**
 * Created by ihope006 on 2017/2/8.
 */

public class MapUtility {
//    public static JSONObject getLocationInfo(String address) {
//
//        HttpGet httpGet = new HttpGet("http://maps.google."
//
//                + "com/maps/api/geocode/json?address=" + address
//
//                + "&sensor=false");
//
//        HttpClient client = new DefaultHttpClient();
//
//        HttpResponse response;
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        try {
//
//            response = client.execute(httpGet);
//
//            HttpEntity entity = response.getEntity();
//
//            InputStream stream = entity.getContent();
//
//            int b;
//
//            while ((b = stream.read()) != -1) {
//
//                stringBuilder.append((char) b);
//
//            }
//
//        } catch (ClientProtocolException e) {
//
//            e.printStackTrace();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        }
//
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//
//            jsonObject = new JSONObject(stringBuilder.toString());
//
//        } catch (JSONException e) {
//
//            e.printStackTrace();
//
//        }
//
//        return jsonObject;
//
//    }

}