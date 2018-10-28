package com.haiwai.housekeeper.activity.user;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.Parameter;
import com.haiwai.housekeeper.entity.TakeOrderServeEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.HttpManager;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.ConPopBig7View;
import com.haiwai.housekeeper.view.ConPopView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haiwai.housekeeper.base.MyApp.context;

public class UserProActivity extends AppCompatActivity {
    private TopViewNormalBar top_barrr;
    TakeOrderServeEntity.DataBean dataBean;
    private CircleImageView need_doing_order_detail_iv_head;
    private TextView need_doing_order_detail_tv_name, need_doing_order_detail_tv_phone,
            need_doing_order_detail_tv_addr, tv_all_moneys, tv_hour_money, tv_hour, tv_debj, tv_sfm;
    private LinearLayout ll_hour_layout, user_pro_detail_ll_bottom;
    private ImageView need_doing_order_detail_iv_sfrz, need_doing_order_detail_iv_jnrz;
    private RelativeLayout rl_all_layout;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pro);
        top_barrr = (TopViewNormalBar) findViewById(R.id.top_barrr);
        top_barrr.setTitle(getString(R.string.bjxq));
        top_barrr.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initData() {
        dataBean = (TakeOrderServeEntity.DataBean) getIntent().getExtras().getSerializable("dataBean");
        if (dataBean != null) {

            if (Integer.valueOf(dataBean.getType()) >= 19 && Integer.valueOf(dataBean.getType()) <= 28) {
                ImageLoader.getInstance().displayImage(dataBean.getAvatar(), need_doing_order_detail_iv_head, ImageLoaderUtils.getAvatarOptions());
                findViewById(R.id.ll_shh_view).setVisibility(View.VISIBLE);
                findViewById(R.id.ll_other_view).setVisibility(View.GONE);
                need_doing_order_detail_tv_addr.setVisibility(View.GONE);

                if (dataBean.getService_type().equals("2")) {
                    ((TextView) findViewById(R.id.tv_service_type)).setText(getString(R.string.zjss));
                    ((TextView) findViewById(R.id.tv_shh_money)).setText(dataBean.getGeneral());
                } else if (dataBean.getService_type().equals("1")) {
                    ((TextView) findViewById(R.id.tv_service_type)).setText(getString(R.string.gsfss));
                    ((TextView) findViewById(R.id.textView2)).setText(getString(R.string.gsfss));
                    ((TextView) findViewById(R.id.tv_shh_money)).setText(dataBean.getHourly());
                } else {
                    ((TextView) findViewById(R.id.tv_service_type)).setText(getString(R.string.jtqk));
                    findViewById(R.id.is_vis_a).setVisibility(View.GONE);
                }

                ((TextView) findViewById(R.id.my_you_shi)).setText(dataBean.getMessage());
                need_doing_order_detail_tv_name.setText(dataBean.getNickname());
                need_doing_order_detail_tv_phone.setText(dataBean.getMobile());
            } else {
                ImageLoader.getInstance().displayImage(dataBean.getAvatar(), need_doing_order_detail_iv_head, ImageLoaderUtils.getAvatarOptions());
                need_doing_order_detail_tv_name.setText(dataBean.getNickname());
                need_doing_order_detail_tv_phone.setText(dataBean.getMobile());
                need_doing_order_detail_tv_addr.setText(dataBean.getAddress());
                if ("2".equals(dataBean.getService_type())) {//定额
                    ll_hour_layout.setVisibility(View.GONE);
                    rl_all_layout.setVisibility(View.VISIBLE);
                    tv_debj.setText(getString(R.string.jy_dw) + dataBean.getGeneral());
                    tv_all_moneys.setText(getString(R.string.jy_dw) + dataBean.getGeneral());
                    tv_sfm.setText(getString(R.string.jy_dw) + dataBean.getHome_fee());
                    //  tv_jcf.setText(getString(R.string.jy_dw)+ dataBean.getInspection());
                } else if ("1".equals(dataBean.getService_type())) {//时薪
                    ll_hour_layout.setVisibility(View.VISIBLE);
                    rl_all_layout.setVisibility(View.GONE);
                    tv_hour_money.setText(getString(R.string.jy_dw) + dataBean.getHourly());
                    if (TextUtils.isEmpty(dataBean.getHour())) {
                        tv_hour.setText(getString(R.string.daiding));

                    } else {
                        tv_hour.setText(dataBean.getHour());
                    }
                    tv_all_moneys.setText(getString(R.string.daiding));
                    tv_sfm.setText(getString(R.string.jy_dw) + dataBean.getHome_fee());
                    //   tv_jcf.setText(getString(R.string.jy_dw) + dataBean.getInspection());
                } else {
                    ll_hour_layout.setVisibility(View.GONE);
                    rl_all_layout.setVisibility(View.GONE);
                    tv_all_moneys.setText("看具体情况而定");
                }

            }


        }
        String is_ren = dataBean.getIs_ren();
        if (is_ren != null) {
            switch (is_ren) {
                case "0":
                    need_doing_order_detail_iv_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
                    break;
                case "1":
                    need_doing_order_detail_iv_sfrz.setImageResource(R.mipmap.shenfenrenzheng_card);
                    break;
                case "2":
                    need_doing_order_detail_iv_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
                    break;
                case "3":
                    need_doing_order_detail_iv_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
                    break;

            }
        } else {
            need_doing_order_detail_iv_sfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
        }
        String is_skill = dataBean.getIs_skill();
        if (is_skill != null) {
            switch (is_skill) {
                case "0":
                    need_doing_order_detail_iv_jnrz.setImageResource(R.mipmap.o2o_item_jn_grey);
                    break;
                case "1":
                    need_doing_order_detail_iv_jnrz.setImageResource(R.mipmap.shenfenrenzheng_jiangbei);
                    break;
                case "2":
                    need_doing_order_detail_iv_jnrz.setImageResource(R.mipmap.o2o_item_jn_grey);
                    break;
            }
        } else {
            need_doing_order_detail_iv_jnrz.setImageResource(R.mipmap.o2o_item_jn_grey);
        }

        ImageView iv_diamond = (ImageView) findViewById(R.id.item_take_order_serve_iv_diamond);// TODO: 2016/9/8
        if (dataBean.getS().equals("1")) {
            final String url = "drawable://" + R.mipmap.v_icon;
            ImageLoader.getInstance().displayImage(url, iv_diamond);
//            map1.get(position).setVisibility(View.VISIBLE);
        } else if (dataBean.getS().equals("2")) {
//            map2.get(position).setVisibility(View.VISIBLE);
            final String url = "drawable://" + R.mipmap.s_icon;
            ImageLoader.getInstance().displayImage(url, iv_diamond);
        } else {
//            map1.get(position).setVisibility(View.GONE);
//            map2.get(position).setVisibility(View.GONE);
            final String url = "";
            ImageLoader.getInstance().displayImage(url, iv_diamond);
        }
    }

    private void initView() {
        need_doing_order_detail_iv_head = (CircleImageView) findViewById(R.id.need_doing_order_detail_iv_head);
        need_doing_order_detail_tv_name = (TextView) findViewById(R.id.need_doing_order_detail_tv_name);
        need_doing_order_detail_tv_phone = (TextView) findViewById(R.id.need_doing_order_detail_tv_phone);
        need_doing_order_detail_tv_addr = (TextView) findViewById(R.id.need_doing_order_detail_tv_addr);
        need_doing_order_detail_iv_sfrz = (ImageView) findViewById(R.id.need_doing_order_detail_iv_sfrz);
        need_doing_order_detail_iv_jnrz = (ImageView) findViewById(R.id.need_doing_order_detail_iv_jnrz);
        tv_all_moneys = (TextView) findViewById(R.id.tv_all_moneys);
        tv_hour_money = (TextView) findViewById(R.id.tv_hour_money);
        tv_hour = (TextView) findViewById(R.id.tv_hour);
        tv_debj = (TextView) findViewById(R.id.tv_debj);
        tv_sfm = (TextView) findViewById(R.id.tv_sfm);
        //  tv_jcf = (TextView) findViewById(R.id.tv_jcf);
        ll_hour_layout = (LinearLayout) findViewById(R.id.ll_hour_layout);
        rl_all_layout = (RelativeLayout) findViewById(R.id.rl_all_layout);
        user_pro_detail_ll_bottom = (LinearLayout) findViewById(R.id.user_pro_detail_ll_bottom);


//        need_doing_order_detail_tv_name.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        need_doing_order_detail_tv_phone.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        need_doing_order_detail_tv_addr.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        tv_hour.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        tv_debj.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        tv_sfm.setTypeface(MyApp.getTf(), Typeface.NORMAL);
//        tv_jcf.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        if (AppGlobal.getInstance().getLagStr().equals("en")) {
            findViewById(R.id.tv_tap).setVisibility(View.GONE);
        } else {
            findViewById(R.id.tv_tap).setVisibility(View.VISIBLE);
        }
        ((TextView) findViewById(R.id.o2o_detail_tv_bottom)).setTypeface(MyApp.getTf(), Typeface.NORMAL);


        user_pro_detail_ll_bottom.setOnClickListener(mOnClickListener);
        findViewById(R.id.tv_contain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ConPopBig7View big = new ConPopBig7View(UserProActivity.this, "");
                big.getIv_diss().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        big.dismiss();
                    }
                });
                big.showPopUpWindow(view);
            }
        });

        need_doing_order_detail_iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProActivity.this, ProDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("uid", dataBean.getUid());
                bundle.putString("nickname", dataBean.getNickname());
                bundle.putString("type", dataBean.getType());
                bundle.putString("choose", "0");
                bundle.putString("oid", "");
                intent.putExtra("fromO2O", true);
                intent.putExtras(bundle);
                startActivity(intent);
//                ActivityTools.goNextActivity(UserProActivity.this, ProDetailActivity.class, bundle);
            }
        });
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_barrr.getBackView()) {
                finish();
            } else if (view.getId() == R.id.user_pro_detail_ll_bottom) {
                CustomDialog.Builder customBuilder = new CustomDialog.Builder(UserProActivity.this);
                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.selelct_this_pro)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestChooseServe();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.message_alert_no),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .create().show();

            }
        }
    };


    public void requestChooseServe() {
//        LogUtil.e("oid", type);
        LoadDialog.showProgressDialog(this);
        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("oid", dataBean.getOid()));
        list.add(new Parameter("uid", AppGlobal.getInstance().getUser().getUid()));
        list.add(new Parameter("j_uid", dataBean.getUid()));
        list.add(new Parameter("secret_key", SPUtils.getString(UserProActivity.this, "secret", "")));
        list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
        list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
        HttpManager.getInstance().post(list, Contants.order_choice, 100, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                int code = JsonUtils.getJsonInt(resultJson, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    if (AppGlobal.getInstance().getLagStr().equals("zh")) {
                        ToastUtil.shortToast(UserProActivity.this, "选择成功");
                    } else {
                        ToastUtil.shortToast(UserProActivity.this, "Done");
                    }
                    SPUtils.saveBoolean(UserProActivity.this, "isSelectPro", true);
                    Intent intent = new Intent(UserProActivity.this, NeedOrderDetailActivity3.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", getIntent().getExtras().get("id").toString());
                    bundle.putString("proid", getIntent().getExtras().get("proid").toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    setResult(RESULT_OK);
                    finish();

                } else {
                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }

            @Override
            public void onHttpRequestError(int requestCode, Exception exception) {

            }
        });
    }
}
