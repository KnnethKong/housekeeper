package com.haiwai.housekeeper.activity.server;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.Parameter;
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

public class RentMoneyActivity extends BaseProActivity {
    private TopViewNormalBar top_zhzj_bar;
    private EditText et_zfmc, et_zhzj;
    private TextView tv_subs_zhzj;
    String order_id = "";
    String uid = "";
    String h_id = "";
    String ren_nickname = "";
    String rent = "";
    private String isZhorEn = "";
    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_money);
        top_zhzj_bar = (TopViewNormalBar) findViewById(R.id.top_zhzj_bar);
        top_zhzj_bar.setTitle(getString(R.string.for_rent));
        top_zhzj_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initView();
    }

    private void initData() {



        order_id = getIntent().getStringExtra("order_id");
        uid = getIntent().getStringExtra("uid");
        h_id = getIntent().getStringExtra("h_id");
    }

    private void initView() {
        et_zfmc = (EditText) findViewById(R.id.et_zfmc);
        et_zhzj = (EditText) findViewById(R.id.et_zhzj);
        tv_subs_zhzj = (TextView) findViewById(R.id.tv_subs_zhzj);
        tv_subs_zhzj.setOnClickListener(mOnClickListener);

        if(AppGlobal.getInstance().getLagStr().equals("en")){
            ((TextView) findViewById(R.id.tv_money_type)).setText("$");
        }else{
            ((TextView) findViewById(R.id.tv_money_type)).setText("$");
        }

        if(getIntent().getStringExtra("name")!=null){
            et_zfmc.setText(getIntent().getStringExtra("name")+"");
            et_zhzj.setText(getIntent().getStringExtra("money")+"");
            tv_subs_zhzj.setVisibility(View.GONE);
            et_zfmc.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            et_zhzj.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_zhzj_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.tv_subs_zhzj) {
                LoadDialog.showProgressDialog(RentMoneyActivity.this);
                ren_nickname = et_zfmc.getText().toString().trim();
                rent = et_zhzj.getText().toString().trim();


                List<Parameter> list = new ArrayList<>();
                list.add(new Parameter("order_id", order_id));
                list.add(new Parameter("uid",uid));
                list.add(new Parameter("h_id",h_id));
                list.add(new Parameter("ren_nickname", ren_nickname));
                list.add(new Parameter("rent", rent));
                list.add(new Parameter("secret_key", SPUtils.getString(RentMoneyActivity.this, "secret", "")));
                list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
                list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
                HttpManager.getInstance().post(list, Contants.zhao_que, 100, new HttpManager.OnHttpResponseListener() {
                    @Override
                    public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                        int code = JsonUtils.getJsonInt(resultJson, "status");
                        LoadDialog.closeProgressDialog();
                        if (code == 200) {
                            ToastUtil.longToast(RentMoneyActivity.this, getString(R.string.zhcg_t));
                            Intent intent = new Intent();
                            intent.putExtra("rent",rent);
                            intent.putExtra("name",ren_nickname);
                            tv_subs_zhzj.setVisibility(View.GONE);
                            setResult(RESULT_OK,intent);
                            finish();
                        } else {
                            ToastUtil.shortToast(RentMoneyActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
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
