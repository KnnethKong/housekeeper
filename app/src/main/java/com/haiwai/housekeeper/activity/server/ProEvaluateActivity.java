package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.OrderDetailEntity;
import com.haiwai.housekeeper.entity.OrderItemEntry;
import com.haiwai.housekeeper.entity.ProDetailEntity;
import com.haiwai.housekeeper.entity.StarEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CardsLayout;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.StarView;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyj on 2016/9/23.
 */
public class ProEvaluateActivity extends BaseProActivity {
    private TopViewNormalBar top_evaluate_bar;
    private EditText et_input_fadback_content;
    private RatingBar rating_bar;
    //    private CardsLayout cardsLayout;
    private TextView tv_eva_name, tv_eva_location;
    private TextView tv_label_tag;
    private CircleImageView circle_eva_icon;
    private TextView ib_submit;
    //    private List<StarEntity.DataBean.Str1Bean> str1;
//    private List<StarEntity.DataBean.Str2Bean> str2;
//    private List<StarEntity.DataBean.Str3Bean> str3;
//    private List<StarEntity.DataBean.Str4Bean> str4;
//    private List<StarEntity.DataBean.Str5Bean> str5;
    Map<Integer, Boolean> mapBoolen = new LinkedHashTreeMap<>();
    Map<Integer, Integer> intMap = new LinkedHashTreeMap<>();
    User user;
    private String uid = "";
    private String content = "";//反馈内容
    private String xin="5";
    private String l_id = "";
    private String oid;//订单id
    private String ouid;//发布订单的人id
    private String avatar;
    private String nickname;
    private String address_info;
    private static final String TYPTSTR = "1";//服务商对订单的评论
    private boolean flag = false;
    private String isZhorEn = "";
    ImageLoader imageLoader;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mOnsEvaChangeListener.changesStatus();
                finish();
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_evaluate_new_layout);
        top_evaluate_bar = (TopViewNormalBar) findViewById(R.id.top_evaluate_bar);
        top_evaluate_bar.setTitle(R.string.status_ep1);
        top_evaluate_bar.setOnBackListener(mOnClickListener);
        imageLoader = new ImageLoader(this);
        initData();
        initView();
    }

    private void initView() {
        circle_eva_icon = (CircleImageView) findViewById(R.id.circle_eva_icon);
        tv_label_tag = (TextView) findViewById(R.id.tv_label_tag);
        if (!TextUtils.isEmpty(avatar)) {
            imageLoader.DisplayImage(avatar, circle_eva_icon);
        }
        tv_eva_name = (TextView) findViewById(R.id.tv_eva_name);
        tv_eva_name.setText(nickname);
        tv_eva_location = (TextView) findViewById(R.id.tv_eva_location);
        tv_eva_location.setText(address_info);
        ib_submit = (TextView) findViewById(R.id.ib_submit);
        ib_submit.setOnClickListener(mOnClickListener);
        et_input_fadback_content = (EditText) findViewById(R.id.et_input_fadback_content);
        rating_bar = (RatingBar) findViewById(R.id.rating_bar);
        rating_bar.setProgress(5);
        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                initLabelView(v);
            }
        });
//        cardsLayout = (CardsLayout) findViewById(R.id.ll_content_label);
    }

    private void initLabelView(float v) {
//        ToastUtil.shortToast(ProEvaluateActivity.this, "" + v);
//        cardsLayout.removeAllViews();
        if (v == 1) {
            xin = "1";
            tv_label_tag.setText(R.string.star1);
//            if (str1 != null) {
//                for (int i = 0; i < str1.size(); i++) {
//                    StarView starView = new StarView(ProEvaluateActivity.this);
//                    starView.setViewVisible(false);
//                    starView.setBadText(str1.get(i).getName());
//                    cardsLayout.addView(starView);
//                }
//            }
        } else if (v == 2) {
            xin = "2";
            tv_label_tag.setText(R.string.star2);
//            if (str2 != null) {
//                for (int i = 0; i < str2.size(); i++) {
//                    StarView starView = new StarView(ProEvaluateActivity.this);
//                    starView.setViewVisible(false);
//                    starView.setBadText(str2.get(i).getName());
//                    cardsLayout.addView(starView);
//                }
//            }

        } else if (v == 3) {
            xin = "3";
            tv_label_tag.setText(R.string.star3);
//            if (str3 != null) {
//                for (int i = 0; i < str3.size(); i++) {
//                    StarView starView = new StarView(ProEvaluateActivity.this);
//                    starView.setViewVisible(false);
//                    starView.setBadText(str3.get(i).getName());
//                    cardsLayout.addView(starView);
//                }
//            }
        } else if (v == 4) {
            xin = "4";
            tv_label_tag.setText(R.string.star4);
//            if (str4 != null) {
//                for (int i = 0; i < str4.size(); i++) {
//                    StarView starView = new StarView(ProEvaluateActivity.this);
//                    starView.setViewVisible(true);
//                    starView.setGoodText(str4.get(i).getName());
//                    cardsLayout.addView(starView);
//                }
//            }
        } else if (v == 5) {
            xin = "5";
            tv_label_tag.setText(R.string.star5);
//            if (str5 != null) {
//                for (int i = 0; i < str5.size(); i++) {
//                    StarView starView = new StarView(ProEvaluateActivity.this);
//                    starView.setViewVisible(true);
//                    starView.setGoodText(str5.get(i).getName());
//                    cardsLayout.addView(starView);
//                }
//            }
        }
//        int count = cardsLayout.getChildCount();
//        for (int i = 0; i < count; i++) {
//            mapBoolen.put(i, false);
//            StarView starView = (StarView) cardsLayout.getChildAt(i);
//            final TextView tView = starView.getTvBadorGood((int) v);
//            final int finalI = i;
//            tView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (!mapBoolen.get(finalI)) {
//                        mapBoolen.put(finalI, true);
//                        tView.setSelected(true);
//                        intMap.put(finalI, 1);
//                    } else {
//                        mapBoolen.put(finalI, false);
//                        tView.setSelected(false);
//                        intMap.put(finalI, 0);
//                    }
//                }
//            });
//        }
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        flag = getIntent().getBooleanExtra("flag", false);
        if (flag) {
            ouid = getIntent().getStringExtra("ouid");
            Bundle bundle = getIntent().getBundleExtra("bundle");
            OrderItemEntry.DataBean dataBean = (OrderItemEntry.DataBean) bundle.getSerializable("dataBean");
            if (dataBean != null) {
                oid = dataBean.getId();
                ouid = dataBean.getUid();
                avatar = dataBean.getAvatar();
                nickname = dataBean.getNickname();
                address_info = dataBean.getAddress_info();
            }
        } else {
            oid = getIntent().getStringExtra("oid");
            ouid = getIntent().getStringExtra("ouid");
            avatar = getIntent().getStringExtra("avatar");
            nickname = getIntent().getStringExtra("nickname");
            address_info = getIntent().getStringExtra("address_info");
        }

        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }
//        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProEvaluateActivity.this, Contants.order_label, null, null, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                int code = JsonUtils.getJsonInt(response, "status");
//                if (code == 200) {
//                    StarEntity starEntity = new Gson().fromJson(response, StarEntity.class);
//                    bindDataToView(starEntity);
//                } else {
//                    ToastUtil.shortToast(ProEvaluateActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
//                }
//            }
//        }));
    }

//    private void bindDataToView(StarEntity starEntity) {
//        str1 = starEntity.getData().getStr1();
//        str2 = starEntity.getData().getStr2();
//        str3 = starEntity.getData().getStr3();
//        str4 = starEntity.getData().getStr4();
//        str5 = starEntity.getData().getStr5();
//    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_evaluate_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.ib_submit) {//提交评论
              //  ToastUtil.shortToast(ProEvaluateActivity.this, "pinglun"+xin);
                content = et_input_fadback_content.getText().toString().trim();
                Map<String, String> map = new HashMap<>();
                map.put("type", TYPTSTR);
                map.put("uid", uid);
                map.put("oid", oid);
                map.put("ouid", ouid);
                map.put("content", content);
                map.put("xin", xin);
                String xin = map.get("xin");
//                if ("1".equals(xin)) {
//                    StringBuilder str = new StringBuilder();
//                    for (Map.Entry mp : mapBoolen.entrySet()) {
//                        boolean value = (boolean) mp.getValue();
//                        if (value) {
//                            str = str.append(str1.get((int) mp.getKey()).getId()).append(",");
//                        }
//                    }
//                    if (str.toString().endsWith(",")) {
//                        str.deleteCharAt(str.lastIndexOf(","));
//                    }
//                    l_id = str.toString();
//                } else if ("2".equals(xin)) {
//                    StringBuilder str = new StringBuilder();
//                    for (Map.Entry mp : mapBoolen.entrySet()) {
//                        boolean value = (boolean) mp.getValue();
//                        if (value) {
//                            str = str.append(str2.get((int) mp.getKey()).getId()).append(",");
//                        }
//                    }
//                    if (str.toString().endsWith(",")) {
//                        str.deleteCharAt(str.lastIndexOf(","));
//                    }
//                    l_id = str.toString();
//                } else if ("3".equals(xin)) {
//                    StringBuilder str = new StringBuilder();
//                    for (Map.Entry mp : mapBoolen.entrySet()) {
//                        boolean value = (boolean) mp.getValue();
//                        if (value) {
//                            str = str.append(str3.get((int) mp.getKey()).getId()).append(",");
//                        }
//                    }
//                    if (str.toString().endsWith(",")) {
//                        str.deleteCharAt(str.lastIndexOf(","));
//                    }
//                    l_id = str.toString();
//                } else if ("4".equals(xin)) {
//                    StringBuilder str = new StringBuilder();
//                    for (Map.Entry mp : mapBoolen.entrySet()) {
//                        boolean value = (boolean) mp.getValue();
//                        if (value) {
//                            str = str.append(str4.get((int) mp.getKey()).getId()).append(",");
//                        }
//                    }
//                    if (str.toString().endsWith(",")) {
//                        str.deleteCharAt(str.lastIndexOf(","));
//                    }
//                    l_id = str.toString();
//                } else if ("5".equals(xin)) {
//                    StringBuilder str = new StringBuilder();
//                    for (Map.Entry mp : mapBoolen.entrySet()) {
//                        boolean value = (boolean) mp.getValue();
//                        if (value) {
//                            str = str.append(str5.get((int) mp.getKey()).getId()).append(",");
//                        }
//                    }
//                    if (str.toString().endsWith(",")) {
//                        str.deleteCharAt(str.lastIndexOf(","));
//                    }
//                    l_id = str.toString();
//                }
                map.put("l_id", l_id);
                map.put("secret_key", SPUtils.getString(ProEvaluateActivity.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProEvaluateActivity.this, Contants.comment, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            Intent mIn = new Intent();
                            mIn.setAction("evalue");
                            sendBroadcast(mIn);
//                            mOnsEvaChangeListener.changesStatus();
                            ToastUtil.shortToast(ProEvaluateActivity.this, getString(R.string.pr_ev_ti));
                            Intent intent = new Intent();
                            intent.putExtra("id", oid);
                            intent.setClass(ProEvaluateActivity.this, OrderHisDetailActivity.class);
                            startActivity(intent);
                            finish();
//                            Message msg = Message.obtain();
//                            msg.what = 0;
//                            mHandler.sendMessage(msg);
                        } else {
                            ToastUtil.shortToast(ProEvaluateActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            }
        }
    };

    public static OnEvaChangeListener mOnEvaChangeListener;

    public interface OnEvaChangeListener {
        void changeStatus();
    }

    public static void setOnEvaChangeListener(OnEvaChangeListener onEvaChangeListener) {
        mOnEvaChangeListener = onEvaChangeListener;
    }

    public static OnEvasChangeListener mOnsEvaChangeListener;

    public interface OnEvasChangeListener {
        void changesStatus();
    }

    public static void setOnEvasChangeListener(OnEvasChangeListener onEvasChangeListener) {
        mOnsEvaChangeListener = onEvasChangeListener;
    }


}
