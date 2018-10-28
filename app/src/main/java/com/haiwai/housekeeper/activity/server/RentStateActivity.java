package com.haiwai.housekeeper.activity.server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.Map;

public class RentStateActivity extends BaseProActivity {
    private TopViewNormalBar top_rent_bar;
    private Button ib_sub;
    private EditText et_fwzt, et_wxwffyzj, et_zfyc;
    private RadioGroup rg_state;
    private RadioButton rb_normal, rb_no_normal;
    Map<String, String> map = new HashMap<>();
    String order_id = "";// 订单号
    String uid = "";//                   发布订单的用户id
    String h_id = "";//                  房产id
    String monthtime = "";//            详情里面的月份

    String wrent = "";//                 租金
    String home_remark = "";//           房屋反馈
    String tenant_static = "";//         1正常2异常
    String tenant_remark = "";//         用户反馈
    boolean isHis = false;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_state);
        top_rent_bar = (TopViewNormalBar) findViewById(R.id.top_rent_bar);
        top_rent_bar.setTitle(getString(R.string.pro_fwjzhzt));
        top_rent_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private String tvStatus;
    private boolean isNormal = false;

    private void initData() {


        if (isHis) {
            wrent = getIntent().getStringExtra("wrent");
            home_remark = getIntent().getStringExtra("home_remark");
            tenant_remark = getIntent().getStringExtra("tenant_remark");
            tenant_static = getIntent().getStringExtra("tenant_static");
            Log.i("stateInformation",home_remark+"--"+tenant_remark+"--"+tenant_static);
            ib_sub.setVisibility(View.GONE);

            et_fwzt.setText(home_remark);
            et_wxwffyzj.setText(wrent);
            if ("1".equals(tenant_static)) {

                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    et_zfyc.setText("Everything is ok");
                }else{
                    et_zfyc.setText("一切正常");
                }
                isNormal = true;
                rb_normal.setChecked(true);
                rb_no_normal.setChecked(false);
            } else if ("2".equals(tenant_static)) {
                isNormal = false;
                et_zfyc.setText(tenant_remark);
                rb_normal.setChecked(false);
                rb_no_normal.setChecked(true);
            }

            et_fwzt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            et_zfyc.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            rb_normal.setClickable(false);
            rb_no_normal.setClickable(false);
            et_wxwffyzj.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });

        } else {
            order_id = getIntent().getStringExtra("order_id");
            uid = getIntent().getStringExtra("uid");
            h_id = getIntent().getStringExtra("h_id");
            monthtime = getIntent().getStringExtra("monthtime");
            map.put("tenant_static", "1");
        }

    }

    private void initView() {
        isHis = getIntent().getBooleanExtra("isHis", false);
        ib_sub = (Button) findViewById(R.id.ib_sub);
        ib_sub.setOnClickListener(mOnClickListener);
        et_fwzt = (EditText) findViewById(R.id.et_fwzt);
        et_wxwffyzj = (EditText) findViewById(R.id.et_wxwffyzj);
        et_zfyc = (EditText) findViewById(R.id.et_zfyc);
        rg_state = (RadioGroup) findViewById(R.id.rg_state);
        rb_normal = (RadioButton) findViewById(R.id.rb_normal);
        rb_no_normal = (RadioButton) findViewById(R.id.rb_no_normal);
        findViewById(R.id.tv_yc_des).setVisibility(View.GONE);
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            et_zfyc.setText("Everything is ok");
        }else{
            et_zfyc.setText("一切正常");
        }
        if(!isHis){
            rg_state.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                    if (radioGroup.getCheckedRadioButtonId() == R.id.rb_normal) {
//                    ToastUtil.shortToast(RentStateActivity.this, "1");
                        map.put("tenant_static", "1");

                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                            et_zfyc.setText("Everything is ok");
                        }else{
                            tvStatus = "一切正常";
                        }
                        et_zfyc.setText(tvStatus);
                        isNormal = true;
                        findViewById(R.id.tv_yc_des).setVisibility(View.GONE);
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_no_normal) {
//                    ToastUtil.shortToast(RentStateActivity.this, "2");
                        map.put("tenant_static", "2");
                        tvStatus = getString(R.string.zhycdes);
                        et_zfyc.setText("");
                        findViewById(R.id.tv_yc_des).setVisibility(View.VISIBLE);
                        et_zfyc.setHint(tvStatus);
                        isNormal = false;
                    }
                }
            });
        }
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_rent_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.ib_sub) {
                home_remark = et_fwzt.getText().toString().trim();
                wrent = et_wxwffyzj.getText().toString().trim();

                if (TextUtils.isEmpty(home_remark)) {
                    ToastUtil.longToast(RentStateActivity.this, getString(R.string.fw_st1));
                    return;
                }
                if (TextUtils.isEmpty(wrent)) {
                    ToastUtil.longToast(RentStateActivity.this, getString(R.string.fw_st2));
                    return;
                }
                if (!isNormal) {
                    if (TextUtils.isEmpty(et_zfyc.getText().toString())) {
                        ToastUtil.longToast(RentStateActivity.this, getString(R.string.fw_st3));
                        return;
                    }
                    tenant_remark = et_zfyc.getText().toString().trim();
                } else {
                    if (AppGlobal.getInstance().getLagStr().equals("zh"))
                        tenant_remark = "一切正常!";
                    else
                        tenant_remark = "Everything is ok!";
                }
                LoadDialog.showProgressDialog(RentStateActivity.this);
                map.put("order_id", order_id);
                map.put("uid", uid);
                map.put("h_id", h_id);
                map.put("monthtime", monthtime);
                map.put("wrent", wrent);
                map.put("home_remark", home_remark);
                map.put("tenant_remark", tenant_remark);
                map.put("secret_key", SPUtils.getString(RentStateActivity.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RentStateActivity.this, Contants.home_remark, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        LoadDialog.closeProgressDialog();
                        if (code == 200) {
                            ToastUtil.longToast(RentStateActivity.this, getString(R.string.ti_ts));
                            finish();
                        } else {
                            ToastUtil.shortToast(RentStateActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            }
        }
    };
}
