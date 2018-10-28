package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/19.
 * 自营弹窗——下单——管理方案
 * 空屋管理--园艺管理--房屋租赁--家政服务
 */
public class SelfSupportManageScheme extends BaseActivity {
    private LinearLayout llkwgl_0, llyygl_0, llfwzl_0, lljzfw_0;//四种类型已选择
    private LinearLayout llkwgl_1, llyygl_1, llfwzl_1, lljzfw_1;//四种类型未选择
    private TextView tvkwgl_del, tvyygl_del, tvfwzl_del, tvjzfw_del;//四种类型删除

    //园艺管理
    private TextView tvWeedingNum;//除草数
    private int weedingNum;//除草数
    private ImageButton ib_jian, ib_jia;
    private ImageButton ibWinterWeeding;
    private boolean isWinter;
    private TextView houserRentPrice;
    private TextView payforMonth;
    private String city = "";
    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_self_support_manage_scheme, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        LogUtil.e("self------h_id", SPUtils.getVIPString(this, "self", "h_id"));
        houserRentPrice = ((TextView) findViewById(R.id.houser_rent_price));
//        payforMonth = ((TextView) findViewById(R.id.money_pay_month));
        setTitle(getString(R.string.manage_scheme), Color.parseColor("#FF3C3C3C"));// TODO: 2016/9/19 将图片更多换成详情
        //四种业务
        llkwgl_0 = (LinearLayout) findViewById(R.id.self_support_manage_scheme_ll_kwgl_0);
        llkwgl_1 = (LinearLayout) findViewById(R.id.self_support_manage_scheme_ll_kwgl_1);
        llyygl_0 = (LinearLayout) findViewById(R.id.self_support_manage_scheme_ll_yygl_0);
        llyygl_1 = (LinearLayout) findViewById(R.id.self_support_manage_scheme_ll_yygl_1);
        llfwzl_0 = (LinearLayout) findViewById(R.id.self_support_manage_scheme_ll_fwzl_0);
        llfwzl_1 = (LinearLayout) findViewById(R.id.self_support_manage_scheme_ll_fwzl_1);
        lljzfw_0 = (LinearLayout) findViewById(R.id.self_support_manage_scheme_ll_jzfw_0);
        lljzfw_1 = (LinearLayout) findViewById(R.id.self_support_manage_scheme_ll_jzfw_1);
        llkwgl_0.setOnClickListener(this);
        llkwgl_1.setOnClickListener(this);
        llyygl_0.setOnClickListener(this);
        llyygl_1.setOnClickListener(this);
        llfwzl_0.setOnClickListener(this);
        llfwzl_1.setOnClickListener(this);
        lljzfw_0.setOnClickListener(this);
        lljzfw_1.setOnClickListener(this);
        hideAll();
        showOne();
        //四种业务删除
        tvkwgl_del = (TextView) findViewById(R.id.self_support_manage_scheme_tv_kwgl_del);
        tvyygl_del = (TextView) findViewById(R.id.self_support_manage_scheme_tv_yygl_del);
        tvfwzl_del = (TextView) findViewById(R.id.self_support_manage_scheme_tv_fwzl_del);
        tvjzfw_del = (TextView) findViewById(R.id.self_support_manage_scheme_tv_jzfw_del);
        tvkwgl_del.setOnClickListener(this);
        tvyygl_del.setOnClickListener(this);
        tvfwzl_del.setOnClickListener(this);
        tvjzfw_del.setOnClickListener(this);

        findViewById(R.id.self_support_manage_scheme_ll_bottom).setOnClickListener(this);//提交
        tvWeedingNum = (TextView) findViewById(R.id.self_support_manage_scheme_tv_num);
        ib_jian = (ImageButton) findViewById(R.id.self_support_manage_scheme_ib_jian);
        ib_jia = (ImageButton) findViewById(R.id.self_support_manage_scheme_ib_jia);
        ib_jian.setOnClickListener(this);
        ib_jia.setOnClickListener(this);
        weedingNum = 1;
        tvWeedingNum.setText(weedingNum + "");

        ibWinterWeeding = (ImageButton) findViewById(R.id.self_support_manage_scheme_ib_winter);
        ibWinterWeeding.setOnClickListener(this);
        isWinter = false;
        ibWinterWeeding.setSelected(false);

    }

    public boolean checkOnlyOne() {
        int i = 0;
        if (llkwgl_0.getVisibility() == View.GONE) {
            i++;
        }
        if (llyygl_0.getVisibility() == View.GONE) {
            i++;
        }
        if (llfwzl_0.getVisibility() == View.GONE) {
            i++;
        }
        if (lljzfw_0.getVisibility() == View.GONE) {
            i++;
        }
        if (i == 3) {
            return true;
        } else {
            return false;
        }
    }

    private void showOne() {
        showBottom();
        String flag = getIntent().getExtras().get("flag").toString();//房屋管理fwgl
        switch (flag) {
            case "kwgl":
                llkwgl_0.setVisibility(View.VISIBLE);
                llkwgl_1.setVisibility(View.GONE);
                break;
            case "yygl":
                llyygl_0.setVisibility(View.VISIBLE);
                llyygl_1.setVisibility(View.GONE);
                break;
            case "fwzl":
                llfwzl_0.setVisibility(View.VISIBLE);
                llfwzl_1.setVisibility(View.GONE);
//                String rentPrice = SPUtils.getVIPString(SelfSupportManageScheme.this,"HOURSE_RENT","rent_price");
//                houserRentPrice.setText("房屋租赁($"+rentPrice+"/ 月)");
//                payforMonth.setText("$"+rentPrice);
                break;
            case "jzfw":
                lljzfw_0.setVisibility(View.VISIBLE);
                lljzfw_1.setVisibility(View.GONE);
                break;
        }
    }

    private void hideAll() {
        llkwgl_0.setVisibility(View.GONE);
        llkwgl_1.setVisibility(View.GONE);
        llyygl_0.setVisibility(View.GONE);
        llyygl_1.setVisibility(View.GONE);
        llfwzl_0.setVisibility(View.GONE);
        llfwzl_1.setVisibility(View.GONE);
        lljzfw_0.setVisibility(View.GONE);
        lljzfw_1.setVisibility(View.GONE);
    }

    private void showBottom() {
        llkwgl_1.setVisibility(View.VISIBLE);
        llyygl_1.setVisibility(View.VISIBLE);
        llfwzl_1.setVisibility(View.VISIBLE);
        lljzfw_1.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }

    /**
     * 自营下单
     */
    private void requestSelfOrder() {
        String h_id = SPUtils.getVIPString(this, "self", "h_id");
        String city = SPUtils.getVIPString(this, "self", "city");
        String k_static = SPUtils.getVIPString(this, "self", "k_static");
        String y_area = SPUtils.getVIPString(this, "self", "y_area");
        String y_ci = SPUtils.getVIPString(this, "self", "y_ci");
        String y_static = SPUtils.getVIPString(this, "self", "y_static");
        String j_area = SPUtils.getVIPString(this, "self", "j_area");
        String j_ci = SPUtils.getVIPString(this, "self", "j_ci");
        String j_static = SPUtils.getVIPString(this, "self", "j_static");
        String is_zhao = SPUtils.getVIPString(this, "self", "is_zhao");
        String is_guan = SPUtils.getVIPString(this, "self", "is_guan");
        String z_city = SPUtils.getVIPString(this, "self", "z_city");
        String z_type = SPUtils.getVIPString(this, "self", "z_type");
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("h_id", h_id);
        map.put("city", city);
        map.put("k_static", k_static);
        map.put("y_area", y_area);
        map.put("y_ci", y_ci);
        map.put("y_static", y_static);
        map.put("j_area", j_area);
        map.put("j_ci", j_ci);
        map.put("j_static", j_static);
        map.put("is_zhao", is_zhao);
        map.put("is_guan", is_guan);
        map.put("z_city", z_city);
        map.put("z_type", z_type);
        map.put("secret_key", SPUtils.getString(SelfSupportManageScheme.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        mRequestQueue.add(new PlatRequest(this, Contants.self, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ActivityTools.goNextActivity(SelfSupportManageScheme.this, RechargeActivity.class);
                } else {
                    ToastUtil.shortToast(SelfSupportManageScheme.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    /**
     * 管理方案修改
     */
    private void requestEditScheme() {
//        String h_id=SPUtils.getVIPString(this,"self","h_id");
//        String city=SPUtils.getVIPString(this,"self","city");
//        String k_static=SPUtils.getVIPString(this,"self","k_static");
//        String y_area=SPUtils.getVIPString(this,"self","y_area");
//        String y_ci=SPUtils.getVIPString(this,"self","y_ci");
//        String y_static=SPUtils.getVIPString(this,"self","y_static");
//        String j_area=SPUtils.getVIPString(this,"self","j_area");
//        String j_ci=SPUtils.getVIPString(this,"self","j_ci");
//        String j_static=SPUtils.getVIPString(this,"self","j_static");
//        String is_zhao=SPUtils.getVIPString(this,"self","is_zhao");
//        String is_guan=SPUtils.getVIPString(this,"self","is_guan");
//        String z_city=SPUtils.getVIPString(this,"self","z_city");
//        String z_type=SPUtils.getVIPString(this,"self","z_type");
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(SelfSupportManageScheme.this, "secret", ""));
//        map.put("uid", AppGlobal.getInstance().getUser().getUid());
//        map.put("h_id",h_id);
//        map.put("city",city);
//        map.put("k_static",k_static);
//        map.put("y_area",y_area);
//        map.put("y_ci",y_ci);
//        map.put("y_static",y_static);
//        map.put("j_area",j_area);
//        map.put("j_ci",j_ci);
//        map.put("j_static",j_static);
//        map.put("is_zhao",is_zhao);
//        map.put("is_guan",is_guan);
//        map.put("z_city",z_city);
//        map.put("z_type",z_type);
        LogUtil.e("map", map + "");
        mRequestQueue.add(new PlatRequest(this, Contants.support_save, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                } else {
                    ToastUtil.shortToast(SelfSupportManageScheme.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.self_support_manage_scheme_ll_bottom://提交
                // TODO: 2016/9/19 如果余额不足 跳转到充值界面
                requestSelfOrder();
                break;
            case R.id.self_support_manage_scheme_ib_jian://-按钮
                weedingNum = Integer.valueOf(tvWeedingNum.getText().toString().trim());
                if (weedingNum > 0 && weedingNum != 1) {
                    weedingNum -= 1;
                    tvWeedingNum.setText(weedingNum + "");
                    ib_jian.setImageResource(R.mipmap.icon_jian_red);
                    if (weedingNum == 1)
                        ib_jian.setImageResource(R.mipmap.icon_jian_grey);
                }
                break;
            case R.id.self_support_manage_scheme_ib_jia://+按钮
                weedingNum = Integer.valueOf(tvWeedingNum.getText().toString().trim());
                if (weedingNum > 0) {
                    weedingNum += 1;
                    tvWeedingNum.setText(weedingNum + "");
                    ib_jian.setImageResource(R.mipmap.icon_jian_red);
                }
                break;
            case R.id.self_support_manage_scheme_ib_winter:
                if (!isWinter) {
                    isWinter = true;
                    ibWinterWeeding.setSelected(true);
                } else {
                    isWinter = false;
                    ibWinterWeeding.setSelected(false);
                }
                break;
            case R.id.self_support_manage_scheme_ll_kwgl_0:
                ActivityTools.goNextActivity(this, O2OServeDetailActivity.class);
                break;
            case R.id.self_support_manage_scheme_ll_kwgl_1:
                Bundle kwgl_bundle = new Bundle();
                kwgl_bundle.putString("pos", "29");
                kwgl_bundle.putString("from", "scheme");
                ActivityTools.goNextActivityForResult(this, SelfSupportDetailActivity.class, kwgl_bundle, 100);
                break;
            case R.id.self_support_manage_scheme_ll_yygl_0:
                ActivityTools.goNextActivity(this, O2OServeDetailActivity.class);
                break;
            case R.id.self_support_manage_scheme_ll_yygl_1:
                Bundle yygl_bundle = new Bundle();
                yygl_bundle.putString("pos", "30");
                yygl_bundle.putString("from", "scheme");
                ActivityTools.goNextActivityForResult(this, SelfSupportDetailActivity.class, yygl_bundle, 200);
                break;
            case R.id.self_support_manage_scheme_ll_fwzl_0:
                ActivityTools.goNextActivity(this, O2OServeDetailActivity.class);
                break;
            case R.id.self_support_manage_scheme_ll_fwzl_1:
                Bundle fwzl_bundle = new Bundle();
                fwzl_bundle.putString("pos", "32");
                fwzl_bundle.putString("from", "scheme");
                ActivityTools.goNextActivityForResult(this, SelfSupportDetailActivity.class, fwzl_bundle, 300);
                break;
            case R.id.self_support_manage_scheme_ll_jzfw_0:
                ActivityTools.goNextActivity(this, O2OServeDetailActivity.class);
                break;
            case R.id.self_support_manage_scheme_ll_jzfw_1:
                Bundle jzfw_bundle = new Bundle();
                jzfw_bundle.putString("pos", "31");
                jzfw_bundle.putString("from", "scheme");
                ActivityTools.goNextActivityForResult(this, SelfSupportDetailActivity.class, jzfw_bundle, 400);
                break;
            case R.id.self_support_manage_scheme_tv_kwgl_del:
                if (checkOnlyOne()) {
                    ToastUtil.shortToast(this, "至少选择一项");
                    return;
                }
                llkwgl_0.setVisibility(View.GONE);
                llkwgl_1.setVisibility(View.VISIBLE);
                SPUtils.saveVIPString(this, "self", "k_static", "0");
                break;
            case R.id.self_support_manage_scheme_tv_yygl_del:
                if (checkOnlyOne()) {
                    ToastUtil.shortToast(this, "至少选择一项");
                    return;
                }
                llyygl_0.setVisibility(View.GONE);
                llyygl_1.setVisibility(View.VISIBLE);
                SPUtils.saveVIPString(this, "self", "y_area", "");
                SPUtils.saveVIPString(this, "self", "y_ci", "");
                SPUtils.saveVIPString(this, "self", "y_static", "0");
                break;
            case R.id.self_support_manage_scheme_tv_fwzl_del:
                if (checkOnlyOne()) {
                    ToastUtil.shortToast(this, "至少选择一项");
                    return;
                }
                llfwzl_0.setVisibility(View.GONE);
                llfwzl_1.setVisibility(View.VISIBLE);
                SPUtils.saveVIPString(this, "self", "is_zhao", "0");
                SPUtils.saveVIPString(this, "self", "is_guan", "0");
                SPUtils.saveVIPString(this, "self", "z_city", "");
//                SPUtils.saveVIPString(this,"self","z_type","");
                break;
            case R.id.self_support_manage_scheme_tv_jzfw_del:
                if (checkOnlyOne()) {
                    ToastUtil.shortToast(this, "至少选择一项");
                    return;
                }
                lljzfw_0.setVisibility(View.GONE);
                lljzfw_1.setVisibility(View.VISIBLE);
                SPUtils.saveVIPString(this, "self", "j_area", "");
                SPUtils.saveVIPString(this, "self", "j_ci", "");
                SPUtils.saveVIPString(this, "self", "j_static", "0");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                llkwgl_1.setVisibility(View.GONE);
                llkwgl_0.setVisibility(View.VISIBLE);
                ToastUtil.shortToast(this, "信件管理data");
            }
        } else if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                llyygl_1.setVisibility(View.GONE);
                llyygl_0.setVisibility(View.VISIBLE);
                ToastUtil.shortToast(this, "园艺管理data");
            }
        } else if (requestCode == 300) {
            if (resultCode == RESULT_OK) {
                llfwzl_1.setVisibility(View.GONE);
                llfwzl_0.setVisibility(View.VISIBLE);
                ToastUtil.shortToast(this, "房屋租赁data");
            }
        } else if (requestCode == 400) {
            if (resultCode == RESULT_OK) {
                lljzfw_1.setVisibility(View.GONE);
                lljzfw_0.setVisibility(View.VISIBLE);
                ToastUtil.shortToast(this, "家政服务data");
            }
        }
    }
}
