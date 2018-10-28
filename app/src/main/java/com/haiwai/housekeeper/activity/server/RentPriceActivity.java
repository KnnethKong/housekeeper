package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.Parameter;
import com.haiwai.housekeeper.entity.ZlOrderEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.HttpManager;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentPriceActivity extends BaseProActivity {
    private TopViewNormalBar top_rent_price_bar;
    private Button ib_sub_content;
    private EditText et_bz;
    private TextView tv_zjsr_c, tv_glfy_c, tv_wfwxfy_c, tv_yrz_c;
    private CheckBox cb_count;
    Map<String, String> map = new HashMap<>();
    String order_id = "";              //订单号
    String uid = "";                   //发布订单的用户id
    String h_id = "";                 // 房产id
    String monthtime = "";            //详情里面的月份
    String price_remark = "";         //  房屋反馈
    String is_da = "";                // 1确认打款给用户了
    String rent = "", grent = "", wrent = "";
    float rentCount;
    ZlOrderEntity.DataBean.DateBean mDate1;
    ZlOrderEntity.DataBean.BillBean mBill;
    boolean isHis = false;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_price);
        top_rent_price_bar = (TopViewNormalBar) findViewById(R.id.top_rent_price_bar);
        top_rent_price_bar.setTitle(getString(R.string.jgqd));
        top_rent_price_bar.setOnBackListener(mOnClickListener);
        initView();
        initData();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        mBill = (ZlOrderEntity.DataBean.BillBean) bundle.getSerializable("mBill");
        rent = mBill.getRent();
        grent = mBill.getGrent();
        wrent = mBill.getWrent();
        rentCount = Float.valueOf(rent) - Float.valueOf(grent) - Float.valueOf(wrent);
        order_id = mBill.getOrder_id();
        uid = mBill.getUid();
        h_id = mBill.getH_id();
        monthtime = mBill.getMonthtime();
        map.put("is_da", "0");
        isHis = getIntent().getBooleanExtra("isHis", false);
        if (isHis) {
            et_bz.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            cb_count.setClickable(false);
            is_da = mBill.getIs_da();
            et_bz.setText(mBill.getPrice_remark());
            ib_sub_content.setVisibility(View.GONE);
            if ("1".equals(is_da)) {
                cb_count.setChecked(true);
            }
        }
        tv_zjsr_c.setText("$" + rent);
        tv_glfy_c.setText("$" + grent);
        tv_wfwxfy_c.setText("$" + wrent);
        tv_yrz_c.setText("$" + rentCount);
    }

    private void initView() {
        ib_sub_content = (Button) findViewById(R.id.ib_sub_content);
        ib_sub_content.setOnClickListener(mOnClickListener);
        et_bz = (EditText) findViewById(R.id.et_bz);
        tv_zjsr_c = (TextView) findViewById(R.id.tv_zjsr_c);
        tv_glfy_c = (TextView) findViewById(R.id.tv_glfy_c);
        tv_wfwxfy_c = (TextView) findViewById(R.id.tv_wfwxfy_c);
        tv_yrz_c = (TextView) findViewById(R.id.tv_yrz_c);
        cb_count = (CheckBox) findViewById(R.id.cb_count);
        cb_count.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    map.put("is_da", "1");
                } else {
                    map.put("is_da", "0");
                }
            }
        });
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_rent_price_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.ib_sub_content) {
                if(!cb_count.isChecked()){
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ToastUtil.shortToast(RentPriceActivity.this,"Please confirm");
                    }else{
                        ToastUtil.shortToast(RentPriceActivity.this,"请确认");
                    }
                    return;
                }

                price_remark = et_bz.getText().toString().trim();
                if (TextUtils.isEmpty(price_remark)) {
                    price_remark="财务一切正常";
                }
                LoadDialog.showProgressDialog(RentPriceActivity.this);

                List<Parameter> list = new ArrayList<>();
                list.add(new Parameter("order_id", order_id));
                list.add(new Parameter("uid",uid));
                list.add(new Parameter("h_id",h_id));
                list.add(new Parameter("monthtime", monthtime));
                list.add(new Parameter("price_remark", price_remark));
                list.add(new Parameter("secret_key", SPUtils.getString(RentPriceActivity.this, "secret", "")));
                list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
                list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
                HttpManager.getInstance().post(list, Contants.price_remark, 100, new HttpManager.OnHttpResponseListener() {
                    @Override
                    public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                        int code = JsonUtils.getJsonInt(resultJson, "status");
                        LoadDialog.closeProgressDialog();
                        if (code == 200) {
                            ToastUtil.longToast(RentPriceActivity.this, getString(R.string.ti_ts));
                            finish();
                        } else {
                            ToastUtil.shortToast(RentPriceActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                    @Override
                    public void onHttpRequestError(int requestCode, Exception exception) {

                    }
                });

            }
        }
    };
}
