package com.haiwai.housekeeper.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.haiwai.housekeeper.entity.SigGogleEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ihope006 on 2017/2/10.
 */

public class LocationUtils {
    private LocationManager locationManager;
    private String providerName;
    private Location location;
    String latLongString;

    public LocationUtils(Activity context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
             return;
        }else {
            // 获取 Location Provider
            providerName = getProviderName();
            // 获取位置
            location = locationManager.getLastKnownLocation(providerName);
//            location = new Location(providerName);
            updateWithNewLocation(location);
            // 注册监听器 locationListener ，第 2 、 3 个参数可以控制接收 gps 消息的频度以节省电力。第 2 个参数为毫秒，
            // 表示调用 listener 的周期，第 3 个参数为米 , 表示位置移动指定距离后就调用 listener
            locationManager.requestLocationUpdates(providerName, 1000, 10, locationListener);
        }

    }

    private String getProviderName() {
        // 构建位置查询条件
        Criteria criteria = new Criteria();
        // 查询精度：高
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // 是否查询海拨：否
        criteria.setAltitudeRequired(false);
        // 是否查询方位角 : 否
        criteria.setBearingRequired(false);
        // 是否允许付费：是
        criteria.setCostAllowed(true);
        // 电量要求：低
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        // 返回最合适的符合条件的 provider ，第 2 个参数为 true 说明 , 如果只有一个 provider 是有效的 , 则返回当前
        // provider
        return locationManager.getBestProvider(criteria, true);
    }

    private final LocationListener locationListener = new LocationListener() {
        // 位置发生改变后调用
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        // provider 被用户关闭后调用
        public void onProviderDisabled(String provider) {
        }

        // provider 被用户开启后调用
        public void onProviderEnabled(String provider) {
        }

        // provider 状态变化时调用
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
        }
    };

    private void updateWithNewLocation(Location location) {
        if (location != null && !TextUtils.isEmpty(String.valueOf(location.getLatitude())) && !TextUtils.isEmpty(String.valueOf(location.getLongitude()))) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "{lat:" + lat + "," + "lng:" + lng + "}";
            Log.i("LoactionInformation",lat+"--"+lng);
        } else {
            latLongString = "";
        }
    }

    public String getGeoStr() {
        return latLongString;
    }

    public static SigGogleEntity parStr(String str) {
        SigGogleEntity sigGogleEntity = null;
        if (!TextUtils.isEmpty(str)) {
            sigGogleEntity = new SigGogleEntity();
            try {
                JSONObject jsonObject = new JSONObject(str);
                if (jsonObject != null) {
                    sigGogleEntity.setLat(jsonObject.optString("lat"));
                    sigGogleEntity.setLng(jsonObject.optString("lng"));
                    return sigGogleEntity;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

            sigGogleEntity = new SigGogleEntity();
            sigGogleEntity.setLat("");
            sigGogleEntity.setLng("");
        }
        return sigGogleEntity;
    }
    public interface MyLocation{
        public void getMyLocaion(String str);
    }
}
