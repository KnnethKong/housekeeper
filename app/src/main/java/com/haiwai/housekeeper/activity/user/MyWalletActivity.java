package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.WalletPagerAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.fragment.user.wallet.PayFragment;
import com.haiwai.housekeeper.fragment.user.wallet.RechargeFragment;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.MyViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/6.
 * 我的——我的钱包
 */
public class MyWalletActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvback;
    //    private TextView tvPay, tvRecharge;
    private PayFragment payFragment;
    private RechargeFragment rechargeFragment;
    //当前页面
//    private int currentIndex;
    private TabLayout tabLayout;                            //定义TabLayout
    private MyViewPager viewPager;                             //定义viewPager
    private WalletPagerAdapter walletPagerAdapter;                          //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private TextView tvmoneyleft;
    User user;
    private String isZhorEn = "";
    private String balanceMoney = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_my_wallet);

        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
//        mCollapsingToolbarLayout.setTitle("我的钱包");
        //通过CollapsingToolbarLayout修改字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
        findViewById(R.id.my_wallet_tv_back).setOnClickListener(this);
        findViewById(R.id.user_my_wallet_tv_tixian).setOnClickListener(this);
        findViewById(R.id.my_wallet_tv_recharge).setOnClickListener(this);

        tvmoneyleft = (TextView) findViewById(R.id.my_wallet_tv_left);
        tvmoneyleft.setText(AppGlobal.getInstance().getUser().getBalance());

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (MyViewPager) findViewById(R.id.viewpager);
        viewPager.setScrollble(false);

        payFragment = new PayFragment();
        rechargeFragment = new RechargeFragment();

        list_fragment = new ArrayList<>();
        list_fragment.add(payFragment);
        list_fragment.add(rechargeFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add(getString(R.string.zfjl));
//        list_title.add(getString(R.string.zfjl));
        list_title.add(getString(R.string.czjl));

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));

        walletPagerAdapter = new WalletPagerAdapter(getSupportFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        viewPager.setAdapter(walletPagerAdapter);
//        tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);

        initData();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        Map<String, String> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("secret_key", SPUtils.getString(MyWalletActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(MyWalletActivity.this, Contants.balance, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {//{"status":200,"data":{"balance":"101065.78"}}
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject dataObject = jsonObject.optJSONObject("data");
                        if(dataObject!=null){
                            bindData(dataObject.optString("balance"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtil.longToast(MyWalletActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void bindData(String balance) {
        balanceMoney = balance;
        tvmoneyleft.setText(balance);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_wallet_tv_back:
                finish();
                break;
            case R.id.user_my_wallet_tv_tixian://提现
                if(Double.valueOf(balanceMoney)>0){
                    Intent intent  = new Intent(this,TakeMoneyActivity.class);
                    intent.putExtra("money",tvmoneyleft.getText().toString());
                    startActivity(intent);
//                    ActivityTools.goNextActivity(this, TakeMoneyActivity.class);
                } else{
                    ToastUtil.longToast(MyWalletActivity.this,getString(R.string.je_tx));
                }

                break;
            case R.id.my_wallet_tv_recharge:
                Intent intent = new Intent(MyWalletActivity.this,RechargeActivity.class);
                intent.putExtra("moneyLeft",tvmoneyleft.getText().toString().trim());
                startActivity(intent);
//;                ActivityTools.goNextActivity(this, RechargeActivity.class);
                break;
        }
    }

//    @Override
//    protected void initView() {
//

//        payFragment=new PayFragment();
//        rechargeFragment=new RechargeFragment();
//        FragmentManager fm=getSupportFragmentManager();
//        fm.beginTransaction().add(R.id.framelayout,payFragment).commit();
//        fm.beginTransaction().show(payFragment);

//        findViewById(R.id.my_wallet_tv_recharge).setOnClickListener(this);


    //tab_FindFragment_title.set


//        tvback = (TextView) findViewById(R.id.bar_left_tv_back);
//        tvback.setText(getString(R.string.my_wallet));
//        tvback.setOnClickListener(this);
//        RelativeLayout rl_bg = (RelativeLayout) findViewById(R.id.my_wallet_rl_title);
//        rl_bg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, PlatUtils.getImageRario(this, 10, 11)));

//        tvPay = (TextView) findViewById(R.id.wallet_tv_pay);
//        tvRecharge = (TextView) findViewById(R.id.wallet_tv_recharge);
//        tvPay.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
//        tvRecharge.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
//        findViewById(R.id.wallet_ll_pay).setOnClickListener(this);
//        findViewById(R.id.wallet_ll_recharge).setOnClickListener(this);

//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        setFragment(0, fragmentTransaction);

//        findViewById(R.id.user_my_wallet_tv_tixian).setOnClickListener(this);
//    }

//    private void setFragment(int i, FragmentTransaction fragmentTransaction) {
//        currentIndex = i;
//        switch (i) {
//            case 0:
//                if (payFragment == null) {
//                    payFragment = new PayFragment();
//                }
//                fragmentTransaction.replace(R.id.user_my_wallet_fl_content, payFragment)
//                        .commit();
//                changeTabsState(currentIndex);
//                break;
//            case 1:
//                if (rechargeFragment == null) {
//                    rechargeFragment = new RechargeFragment();
//                }
//                fragmentTransaction.replace(R.id.user_my_wallet_fl_content, rechargeFragment)
//                        .commit();
//                changeTabsState(currentIndex);
//                break;
//        }
//    }

//    private void changeTabsState(int i) {
//        tvPay.setSelected(i == 0 ? true : false);
//        tvRecharge.setSelected(i == 1 ? true : false);
//    }

//    @Override
//    protected void initData() {
//
//    }

//    @Override
//    protected void click(View v) {
////        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        switch (v.getId()) {
//            case R.id.bar_left_tv_back:
//                finish();
//                break;
//            case R.id.wallet_ll_pay:
//                if (currentIndex != 0) {
//                    setFragment(0, fragmentTransaction);
//                }
//                break;
//            case R.id.wallet_ll_recharge:
//                if (currentIndex != 1) {
//                    setFragment(1, fragmentTransaction);
//                }
//                break;
//            case R.id.user_my_wallet_tv_tixian://提现
//                ActivityTools.goNextActivity(this, TakeMoneyActivity.class);
//                break;
//            case R.id.my_wallet_tv_recharge:
//                ActivityTools.goNextActivity(this,RechargeActivity.class);
//                break;
//        }
//    }
}
