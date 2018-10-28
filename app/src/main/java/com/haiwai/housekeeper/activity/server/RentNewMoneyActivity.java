package com.haiwai.housekeeper.activity.server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class RentNewMoneyActivity extends BaseProActivity {
    private TopViewNormalBar top_zj_bar;
    private EditText et_zj;
    private TextView tv_sub_zj;
    private String order_id = "";
    private String uid = "";
    private String h_id = "";
    private String monthtime = "";
    private String rent = "";
    Map<String, String> map = new HashMap<>();
    boolean isHis = false;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_new_money);
        top_zj_bar = (TopViewNormalBar) findViewById(R.id.top_zj_bar);
        top_zj_bar.setTitle(getString(R.string.zj));
        top_zj_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initData() {
        isHis = getIntent().getBooleanExtra("isHis", false);
        if (isHis) {
            rent = getIntent().getStringExtra("moy");
            et_zj.setText(rent);
            tv_sub_zj.setVisibility(View.GONE);
            et_zj.setOnTouchListener(new View.OnTouchListener() {
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
        }

    }

    private void initView() {
        et_zj = (EditText) findViewById(R.id.et_zj);
        tv_sub_zj = (TextView) findViewById(R.id.tv_sub_zj);
        tv_sub_zj.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_zj_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.tv_sub_zj) {
                rent = et_zj.getText().toString().trim();
                if (TextUtils.isEmpty(rent)) {
                    ToastUtil.longToast(RentNewMoneyActivity.this, getString(R.string.zj_ti));
                    return;
                }
                LoadDialog.showProgressDialog(RentNewMoneyActivity.this);
                map.put("order_id", order_id);
                map.put("uid", uid);
                map.put("h_id", h_id);
                map.put("monthtime", monthtime);
                map.put("rent", rent);
                map.put("secret_key", SPUtils.getString(RentNewMoneyActivity.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RentNewMoneyActivity.this, Contants.rent_que, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        LoadDialog.closeProgressDialog();
                        if (code == 200) {
                            ToastUtil.longToast(RentNewMoneyActivity.this, getString(R.string.zuj_ti2));
                            finish();
                            tv_sub_zj.setVisibility(View.GONE);
                        } else {
                            ToastUtil.shortToast(RentNewMoneyActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            }
        }
    };
}
