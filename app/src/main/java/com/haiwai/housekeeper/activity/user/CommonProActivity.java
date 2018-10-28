package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.HousAddEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.ParseVipJsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.VipWDUtils;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.Map;

public class CommonProActivity extends AppCompatActivity {
    private TopViewNormalBar top_prob_bar;
    private TextView btn_common_pro_next;
    private String type;
    private boolean isNew = false;
    HousAddEntity.DataBean data;
    private String isZhorEn = "";
    private LinearLayout yincang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_pro);
        top_prob_bar = (TopViewNormalBar) findViewById(R.id.top_prob_bar);
        yincang= (LinearLayout) findViewById(R.id.yincang);
        top_prob_bar.setOnBackListener(mOnClickListener);
        top_prob_bar.setRightText(getString(R.string.tc));
        top_prob_bar.setVisible(true);
        top_prob_bar.setFinishListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initView();
    }

    private void initView() {
        btn_common_pro_next = (TextView) findViewById(R.id.btn_common_pro_next);
        btn_common_pro_next.setOnClickListener(mOnClickListener);
        if( AppGlobal.getInstance().getLagStr().equals("zh")){
            yincang.setVisibility(View.VISIBLE);
        }else {
            yincang.setVisibility(View.GONE);
        }
    }

    private void initData() {
        isNew = getIntent().getBooleanExtra("isNew", false);
        if (isNew) {
            type = IMKitService.proTypeMap.get("type");
            data = (HousAddEntity.DataBean) getIntent().getExtras().getSerializable("data");
            IMKitService.map.put("h_id", data.getId());
            IMKitService.map.put("city", data.getCity() + "");
            IMKitService.addrMap.put("addrInfo", data.getAddress_info());
        } else {
            type = getIntent().getStringExtra("type");
        }

//        top_prob_bar.setTitle(getString(R.string.or_pro));

        if ("2".equals(type)) {
            top_prob_bar.setTitle(getString(R.string.vip_fwbj));
        } else if ("3".equals(type)) {
            top_prob_bar.setTitle(getString(R.string.vip_tygl));
        } else if ("4".equals(type)) {
            top_prob_bar.setTitle(getString(R.string.vip_fwzl));
        } else if ("5".equals(type)) {
           // top_prob_bar.setTitle("Vacant House\nManagement");
            top_prob_bar.setTitle(getString(R.string.vip_kwgl));
        }



        LoadDialog.showProgressDialog(CommonProActivity.this);
        if (!"0".equals(type)) {
            Map<String, String> map = new HashMap<>();
            if(type!=null&&!type.equals("null")){
                map.put("type", type);
            }
            map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            map.put("secret_key", SPUtils.getString(CommonProActivity.this, "secret", ""));
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(CommonProActivity.this, Contants.hous_problem, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    System.out.println(">>>>>>>>>>自营问题>>" + response);
                    if (code == 200) {
                        VipWDUtils.ParseData(ParseVipJsonUtils.parseJson(response));
                        LoadDialog.closeProgressDialog();
                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.longToast(CommonProActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_prob_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.btn_common_pro_next) {
                Intent intent = new Intent(CommonProActivity.this, ConmonSingleActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }else if (view == top_prob_bar.getFinishTextView()) {
                if (AppGlobal.getInstance().getIsHome()) {
                    IMKitService.weekMap.clear();
                    Intent intent = new Intent(CommonProActivity.this, MainActivity.class);
                    intent.putExtra("flag", "ishome");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CommonProActivity.this, EnvelopeActivity.class);
                    intent.putExtra("type", type);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mSerializableap", null);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }
    };
}
