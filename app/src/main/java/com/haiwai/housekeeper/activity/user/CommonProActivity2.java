package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.HousAddEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils2;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.ParseVipJsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.VipWDUtils;
import com.haiwai.housekeeper.utils.WDUtils2;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.Map;

public class CommonProActivity2 extends AppCompatActivity {
    private TopViewNormalBar top_prob_bar;
    private TextView btn_common_pro_next;
    private String type;
    private boolean isNew = false;
    HousAddEntity.DataBean data;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_pro1);
        top_prob_bar = (TopViewNormalBar) findViewById(R.id.top_prob_bar);

        top_prob_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
    }

    private void initView() {
        top_prob_bar.setTitle(AssetsUtils2.getSkillName(getIntent().getStringExtra("id"),AppGlobal.getInstance().getLagStr()));
        btn_common_pro_next = (TextView) findViewById(R.id.btn_common_pro_next);
        btn_common_pro_next.setOnClickListener(mOnClickListener);
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_prob_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.btn_common_pro_next) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("id", getIntent().getStringExtra("id"));
                bundle2.putString("make_sure", "0");
                bundle2.putString("at_uid", "");
                IMKitService.lagMap.put("isGo", "isHome");
                ActivityTools.goNextActivity(CommonProActivity2.this, IssueRequireAActivity.class, bundle2);
            }
        }
    };
}
