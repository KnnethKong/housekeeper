package com.haiwai.housekeeper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.haiwai.housekeeper.utils.ActivityManagerUtil;

/**
 * Created by ihope006 on 2016/11/30.
 */

public class BaseProActivity extends AppCompatActivity {
    private ActivityManagerUtil activityManagerUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManagerUtil = ActivityManagerUtil.getInstance();
        activityManagerUtil.pushOneActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityManagerUtil.popOneActivity(this);
    }
}
