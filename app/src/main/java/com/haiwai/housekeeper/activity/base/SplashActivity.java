package com.haiwai.housekeeper.activity.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.RyTokenEntity;
import com.haiwai.housekeeper.entity.SecretEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityManagerUtil;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.Event;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PreferenceUtils;
import com.haiwai.housekeeper.utils.SPKey;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.greenrobot.event.EventBus;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SplashActivity extends Activity {
    private static final int GOTO_MAIN_ACTIVITY = 0;
    private static final int GOTO_GUIDE_ACTIVITY = 1;
    private LocationManager locationManager;
    private String providerName;
    private Location currentBestLocation;
    private Location location;
    private ActivityManagerUtil activityManagerUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstRun = PreferenceUtils.getPrefBoolean(SplashActivity.this, SPKey.FIRST_OPEN, false);
        activityManagerUtil = ActivityManagerUtil.getInstance();
        activityManagerUtil.pushOneActivity(this);

        initSecretData();
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstRun) {
            Intent intent = new Intent(this, GuidActivity.class);

            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);
    }

    private void initSecretData() {
        Map<String,String> map =new HashMap<>();
        map.put("secret_key", SPUtils.getString(SplashActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(SplashActivity.this, Contants.secret_key, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    SecretEntity secretEntity = new Gson().fromJson(response, SecretEntity.class);
                    SPUtils.saveString(SplashActivity.this, "secret", secretEntity.getData().getKey());
                }
            }
        }));

    }

    private void enterHomeActivity() {
        Intent intent = new Intent(SplashActivity.this,
                MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("flag", "splash");
        intent.putExtras(bundle);
        startActivity(intent);
            EventBus.getDefault().post(
                    new Event("im_token",SPUtils.getString(SplashActivity.this, "IM_Token","")));
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mHandler.getLooper().quit();
        activityManagerUtil.popOneActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        JPushInterface.onPause(this);
    }
}
