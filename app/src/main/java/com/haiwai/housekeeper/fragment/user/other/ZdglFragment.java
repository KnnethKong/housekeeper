package com.haiwai.housekeeper.fragment.user.other;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.VipHouseChooseActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.Pickers;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.VipBusinessOrderEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.DateHisPopView;
import com.haiwai.housekeeper.view.DatePicker2PopView;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class ZdglFragment extends BaseProFragment implements View.OnClickListener {
    private TextView tv_fy_zj, tv_ydtxf, tv_yxdsf, tv_gdhf, tv_wsf, tv_wltxf, tv_wyf, tv_rqf, tv_sdf;
    private TextView tv_month_history, tv_home_id, tv_zd_no, tv_zd_chu;
    private LinearLayout ll_add_addr, ll_zd_content_layout, ll_zd_status_layout;
    private String uid = "";
    private String h_id = "";
    private String month = "";
    User user;
    Map<String, String> map;
    private List<Pickers> list = new ArrayList<>();
    private VipBusinessOrderEntity mOrderEntity;
    private LinearLayout llSUm;
    private boolean isSelect = false;

    @Override
    protected void init() {

    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_zdgl, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ll_zd_content_layout = (LinearLayout) view.findViewById(R.id.ll_zd_content_layout);
        ll_zd_status_layout = (LinearLayout) view.findViewById(R.id.ll_zd_status_layout);
        tv_zd_no = (TextView) view.findViewById(R.id.tv_zd_no);
        tv_zd_chu = (TextView) view.findViewById(R.id.tv_zd_chu);
        tv_fy_zj = (TextView) view.findViewById(R.id.tv_fy_zj);
        tv_ydtxf = (TextView) view.findViewById(R.id.tv_ydtxf);
        tv_yxdsf = (TextView) view.findViewById(R.id.tv_yxdsf);
        tv_gdhf = (TextView) view.findViewById(R.id.tv_gdhf);
        tv_wsf = (TextView) view.findViewById(R.id.tv_wsf);
        tv_wltxf = (TextView) view.findViewById(R.id.tv_wltxf);
        tv_wyf = (TextView) view.findViewById(R.id.tv_wyf);
        tv_rqf = (TextView) view.findViewById(R.id.tv_rqf);
        tv_sdf = (TextView) view.findViewById(R.id.tv_sdf);
        tv_month_history = (TextView) view.findViewById(R.id.tv_month_history);
        tv_month_history.setOnClickListener(this);
        tv_home_id = (TextView) view.findViewById(R.id.tv_home_id);
        ll_add_addr = (LinearLayout) view.findViewById(R.id.ll_add_addr);
        ll_add_addr.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData(){
//        month = TimeUtils.getMonthStamp("2017-08-18 17:32:51");
//        Log.e("result----->",month+"");
        if(!isSelect){
            h_id =  SPUtils.getString(getContext(),"fw_h_id","");
        }
        user = AppGlobal.getInstance().getUser();
        uid = user.getUid();
        map = new HashMap<>();
        map.put("uid", uid);
        map.put("h_id", h_id);
        map.put("month", month);
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.utility_bill, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("物业账单-------------》" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    mOrderEntity = new Gson().fromJson(response, VipBusinessOrderEntity.class);

                    mOrderEntity.getData().getData().getH_id();

                    bintDataToView(mOrderEntity,response);
                } else {
                    ToastUtil.longToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }
    private void bintDataToView(VipBusinessOrderEntity orderEntity,String response) {
        VipBusinessOrderEntity.DataBeanX.DataBean data = orderEntity.getData().getData();
        if (data != null) {

            tv_home_id.setText(orderEntity.getData().getData().getAddress_info());
            h_id = orderEntity.getData().getData().getH_id();
            tv_home_id.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            int is_cun =0;
            try{
                is_cun = new JSONObject(response).getJSONObject("data").getJSONObject("data")
                        .getInt("is_cun");
            }catch (Exception e){
                e.printStackTrace();
            }
            if (is_cun==1) {//账单已出，展示
                ll_zd_content_layout.setVisibility(View.VISIBLE);
                ll_zd_status_layout.setVisibility(View.GONE);
                if (0 == data.getWater_jiao()) {
                    view.findViewById(R.id.ll_electrice).setVisibility(View.GONE);
//                    tv_sdf.setText("$" + data.getWater() + getString(R.string.business_order_own_money));
                } else if (1 == data.getWater_jiao()) {
                    tv_sdf.setText("$" + data.getWater());
                }
                if (0 == data.getGas_jiao()) {
                    view.findViewById(R.id.ll_gas).setVisibility(View.GONE);
//                    tv_rqf.setText("$" + data.getGas() + getString(R.string.business_order_own_money));
                } else if (1 == data.getGas_jiao()) {
                    tv_rqf.setText("$" + data.getGas());
                }
                if (0 == data.getProperty_jiao()) {
                    view.findViewById(R.id.ll_wuye).setVisibility(View.GONE);
//                    tv_wyf.setText("$" + data.getProperty() + getString(R.string.business_order_own_money));
                } else if (1 == data.getProperty_jiao()) {
                    tv_wyf.setText("$" + data.getProperty());
                }
                if (0 == data.getNetwork_jiao()) {
                    view.findViewById(R.id.ll_net).setVisibility(View.GONE);
//                    tv_wltxf.setText("$" + data.getNetwork() + getString(R.string.business_order_own_money));
                } else if (1 == data.getNetwork_jiao()) {
                    tv_wltxf.setText("$" + data.getNetwork());
                }
                if (0 == data.getPublic_jiao()) {
                    view.findViewById(R.id.ll_weisheng).setVisibility(View.GONE);
//                    tv_wsf.setText("$" + data.getPublicX() + getString(R.string.business_order_own_money));
                } else if (1 == data.getPublic_jiao()) {
                    tv_wsf.setText("$" + data.getPublicX());
                }
                if (0 == data.getFixed_jiao()) {
                    view.findViewById(R.id.ll_phone_fees).setVisibility(View.GONE);
//                    tv_gdhf.setText("$" + data.getFixed() + getString(R.string.business_order_own_money));
                } else if (1 == data.getFixed_jiao()) {
                    tv_gdhf.setText("$" + data.getFixed());
                }
                if (0 == data.getCable_jiao()) {
                    view.findViewById(R.id.ll_line).setVisibility(View.GONE);
//                    tv_yxdsf.setText("$" + data.getCable() + getString(R.string.business_order_own_money));
                } else if (1 == data.getCable_jiao()) {
                    tv_yxdsf.setText("$" + data.getCable());
                }
                if (0 == data.getMobile_jiao()) {
                    view.findViewById(R.id.ll_mvoe_fees).setVisibility(View.GONE);
//                    tv_ydtxf.setText("$" + data.getMobile() + getString(R.string.business_order_own_money));
                } else if (1 == data.getMobile_jiao()) {
                    tv_ydtxf.setText("$" + data.getMobile());
                }
                int count = data.getWater() + data.getGas() +
                        data.getProperty() + data.getNetwork() +
                        data.getPublicX() + data.getFixed() +
                        data.getCable() + data.getMobile();
                tv_fy_zj.setText("$" + count);
            } else {//账单未出
                if ("1".equals(data.getIs_bill())) {//有未出账单
                    ll_zd_content_layout.setVisibility(View.VISIBLE);
                    ll_zd_status_layout.setVisibility(View.GONE);
                    tv_zd_no.setVisibility(View.GONE);
                    tv_zd_chu.setVisibility(View.VISIBLE);
                } else {//无账单
                    ll_zd_content_layout.setVisibility(View.GONE);
                    ll_zd_status_layout.setVisibility(View.VISIBLE);
                    tv_zd_no.setVisibility(View.VISIBLE);
                    tv_zd_chu.setVisibility(View.GONE);
                }
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            if (resultCode == RESULT_OK) {
                tv_home_id.setText(data.getStringExtra("addr"));
                h_id = data.getStringExtra("h_id");
                isSelect = true;
                tv_home_id.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                getData();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_month_history) {
            initHisMonth(mOrderEntity.getData().getHistry_time());
        } else if (view.getId() == R.id.ll_add_addr) {
            Bundle bundle = new Bundle();
            bundle.putString("flag", "design");
            ActivityTools.goNextActivityForResult(context, VipHouseChooseActivity.class, bundle, 99);
        }
    }

    private void initHisMonth(List<Integer> histry_time) {
        if (histry_time != null && histry_time.size() > 0) {
            list.clear();
            for (int i = 0; i < histry_time.size(); i++) {
                Pickers pic = new Pickers(TimeUtils.getStr12Time(String.valueOf(histry_time.get(i))), i + "");
                list.add(pic);
            }
            DateHisPopView d2p = new DateHisPopView(context);
            d2p.initData(list);
            d2p.openPopWindow(view);
            d2p.setDatePicOnClickListener(new DateHisPopView.DatePicOnClickListener() {
                @Override
                public void datePicker(String str) {
                    month = TimeUtils.getMonthStampMore(str);
                    Log.e("result----->",month+"");
                    initData();
                }
            });
        } else {
            ToastUtil.longToast(context, getString(R.string.no_his));
        }
    }

    private void ParseData(String response) {
        DateHisPopView d2p = new DateHisPopView(context);
        d2p.initData(list);
        d2p.openPopWindow(view);
        d2p.setDatePicOnClickListener(new DateHisPopView.DatePicOnClickListener() {
            @Override
            public void datePicker(String str) {
                month = TimeUtils.getMonthStampMore(str);
                initData();
            }
        });
    }
}
