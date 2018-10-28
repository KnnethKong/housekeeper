package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.AboutUsActivity;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.service.polling.PollingService;
import com.haiwai.housekeeper.service.polling.PollingUtils;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.SPKey;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.sobot.chat.SobotApi;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ihope007 on 2016/9/7.
 */
public class SetActivity extends BaseActivity {
    private ImageButton ivMsgPush;
    private TextView tv_version_info;
    private boolean jpush_switch;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_set, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.set_title), Color.parseColor("#FF3C3C3C"));
        ivMsgPush = (ImageButton) findViewById(R.id.user_set_ib_msg_push);
        ivMsgPush.setOnClickListener(this);
        jpush_switch = SPUtils.getBoolean(this, SPKey.JPush_Switch, true);
        if (jpush_switch) {
            ivMsgPush.setSelected(true);
        } else {
            ivMsgPush.setSelected(false);
        }
        tv_version_info = (TextView) findViewById(R.id.tv_version_info);
        findViewById(R.id.user_set_tv_yjfk).setOnClickListener(this);
        findViewById(R.id.user_set_tv_gywm).setOnClickListener(this);
        findViewById(R.id.user_set_tv_safe_quit).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tv_version_info.setText(getAppInfo());
    }

    private String getAppInfo() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
//            int versionCode = this.getPackageManager()
//                    .getPackageInfo(pkName, 0).versionCode;
//            return pkName + "   " + versionName + "  " + versionCode;
            return versionName;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.user_set_ib_msg_push:
                if (jpush_switch) {
                    jpush_switch = false;
                    ivMsgPush.setSelected(false);
                    if (!JPushInterface.isPushStopped(this))
                        JPushInterface.stopPush(this);
                } else {
                    jpush_switch = true;
                    ivMsgPush.setSelected(true);
                    if (JPushInterface.isPushStopped(this))
                        JPushInterface.resumePush(this);
                }
                SPUtils.saveBoolean(this, SPKey.JPush_Switch, jpush_switch);
                break;
            case R.id.user_set_tv_yjfk://意见反馈
                ActivityTools.goNextActivity(this, AdviceFeedbackActivity.class);
                break;
            case R.id.user_set_tv_gywm://关于我们
                ActivityTools.goNextActivity(this, AboutUsActivity.class);
                break;
            case R.id.user_set_tv_safe_quit://安全退出
//                Intent intent = new Intent();
//                intent.setAction("jpush");
//                intent.putExtra("out",true);
//                sendBroadcast(intent);
                mApp.setLoginState(false);
                SobotApi.disSobotChannel(this);
//                mApp.isLogout = true;
                Bundle bundle = new Bundle();
                bundle.putString("flag", "set");
                ActivityTools.goNextActivity(this, MainActivity.class, bundle);
                PollingUtils.stopPollingService(SetActivity.this, PollingService.class, PollingService.ACTION);
                ToastUtil.shortToast(this, getString(R.string.set_safe_toast));
                // TODO: 2016/9/28
                //是否用一个空的user保存  以清除上一个用户的数据

                break;
        }
    }
}
