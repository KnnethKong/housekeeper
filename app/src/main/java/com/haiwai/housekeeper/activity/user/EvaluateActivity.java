package com.haiwai.housekeeper.activity.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.EvnEntity;
import com.haiwai.housekeeper.entity.ProDetailEntity;
import com.haiwai.housekeeper.entity.SigGogleEntity;
import com.haiwai.housekeeper.entity.StarEntity;
import com.haiwai.housekeeper.entity.UserEvalueEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LocationUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CardsLayout;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.ProGradeView;
import com.haiwai.housekeeper.view.UserStarView;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.haiwai.housekeeper.widget.RegisterDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/5.
 * 评价
 */
public class EvaluateActivity extends BaseActivity {
    private EditText etcontent;
    private RatingBar rating_bar;
    private CardsLayout cardsLayout;

    private List<StarEntity.DataBean.Str1Bean> str1;
    private List<StarEntity.DataBean.Str2Bean> str2;
    private List<StarEntity.DataBean.Str3Bean> str3;
    private List<StarEntity.DataBean.Str4Bean> str4;
    private List<StarEntity.DataBean.Str5Bean> str5;
    Map<Integer, Boolean> mapBoolen = new LinkedHashTreeMap<>();
    Map<Integer, Integer> intMap = new LinkedHashTreeMap<>();

    private String type;
    private String xin;
    private String l_id;
    private String oid;
    private String uid;

    ProDetailEntity entity;
    private String isZhorEn = "";
    SigGogleEntity mSigGogleEntity;
    private TextView tvEvalueWord;
    private ImageView tv_user_degree;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_evaluate, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvEvalueWord = ((TextView) findViewById(R.id.tv_evaluate_word));
        type = getIntent().getExtras().get("type").toString();
        oid = getIntent().getExtras().get("oid").toString();
        uid = getIntent().getExtras().get("uid").toString();
        tv_user_degree = ((ImageView) findViewById(R.id.tv_user_degree));
        initLocationData();
        if (isNetworkAvailable()) {
            requestLabel();
            requestPrDetail(type, uid);
        }

        setTitle(getString(R.string.code_str6), Color.parseColor("#FF3C3C3C"));
        etcontent = (EditText) findViewById(R.id.evaluate_et_content);
        etcontent.setCursorVisible(false);
        etcontent.setOnClickListener(this);

        findViewById(R.id.user_evaluate_tv_commit).setOnClickListener(this);
        rating_bar = (RatingBar) findViewById(R.id.user_evaluate_rb);
        rating_bar.setProgress(5);
        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                initLabelView(v);
            }
        });
        cardsLayout = (CardsLayout) findViewById(R.id.user_ll_content_label);
    }

    private void initLocationData() {
        LocationUtils locationUtils = new LocationUtils(EvaluateActivity.this);
        String str = locationUtils.getGeoStr();
        mSigGogleEntity = LocationUtils.parStr(str);
    }

    private void requestLabel() {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(EvaluateActivity.this, "secret", ""));
        mRequestQueue.add(new PlatRequest(this, Contants.order_label, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");// TODO: 2016/9/12 标签 更换
                if (code == 200) {
                    StarEntity starEntity = new Gson().fromJson(response, StarEntity.class);
                    bindDataToView(starEntity);
                } else {
                    ToastUtil.shortToast(EvaluateActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    public void requestPrDetail(String type, String uid) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", type);
        map.put("lat", mSigGogleEntity.getLat());
        map.put("long", mSigGogleEntity.getLng());
        map.put("secret_key", SPUtils.getString(EvaluateActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(EvaluateActivity.this, Contants.pro_detail, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
//                        ProDetailSkillEntity proSkill = JsonUtils.getProSkill(data);
//                        setSkill(proSkill);
                        entity = JsonUtils.parseProDetail(response);
                        initEntity(response);
                    } else {
                        ToastUtil.shortToast(EvaluateActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void initEntity(String response) {
        //info
        ProDetailEntity.DataBean.InfoBean info = entity.getData().getInfo();
        CircleImageView ivhead = (CircleImageView) findViewById(R.id.evaluate_iv_head);
        ImageLoader.getInstance().displayImage(info.getAvatar(), ivhead, ImageLoaderUtils.getAvatarOptions());
        if (!TextUtils.isEmpty(info.getNickname()))
            ((TextView) findViewById(R.id.evaluate_tv_name)).setText(info.getNickname());
        if (info.getKm() != null) {
            if ("0".equals(info.getKm()))
                ((TextView) findViewById(R.id.evaluate_tv_km)).setText("0.0km");
            else
                ((TextView) findViewById(R.id.evaluate_tv_km)).setText(new DecimalFormat("###.0").format(Double.valueOf(info.getKm())) + "km");
        } else {
            ((TextView) findViewById(R.id.evaluate_tv_km)).setText("0.0km");
        }
        ImageView ivsfrz = (ImageView) findViewById(R.id.evaluate_iv_sfrz);
        if ("1".equals(info.getIs_ren())) {
            ivsfrz.setImageResource(R.mipmap.shenfenrenzheng_card);
        } else {
            ivsfrz.setImageResource(R.mipmap.o2o_item_sf_grey);
        }
        ProGradeView proGradeView = (ProGradeView) findViewById(R.id.item_o2o_detail_v_prograde);
//        proGradeView.setNum(300);
        proGradeView.setNum(Integer.valueOf(info.getPro_score()));


        //skill
        UserEvalueEntity userEntity = new Gson().fromJson(response,UserEvalueEntity.class);
        if(userEntity.getData().getInfo().getS().equals("1")){
            final String url = "drawable://" +R.mipmap.v_icon;
            ImageLoader.getInstance().displayImage(url,tv_user_degree);
        }else if(userEntity.getData().getInfo().getS().equals("2")){
            final String url = "drawable://" +R.mipmap.s_icon;
            ImageLoader.getInstance().displayImage(url,tv_user_degree);
        }
        ImageView ivjnrz = (ImageView) findViewById(R.id.evaluate_iv_jnrz);
            String skillIsren = userEntity.getData().getSkill().get(0).getIs_ren();
            String xin = userEntity.getData().getInfo().getPro_xing();
            String orderNum = userEntity.getData().getInfo().getPro_onum();
            if ("1".equals(skillIsren)) {
                ivjnrz.setImageResource(R.mipmap.shenfenrenzheng_jiangbei);
            } else {
                ivjnrz.setImageResource(R.mipmap.o2o_item_jn_grey);
            }
            TextView tvdanping = (TextView) findViewById(R.id.evaluate_tv_describe);
            if (Float.valueOf(orderNum) == 0){
                tvdanping.setText(getString(R.string.o2o_detail_has_done) + "0" + getString(R.string.o2o_detail_dan_pingjia) + "0");

            }
            else{
                if(Float.valueOf(orderNum)>1){
                    tvdanping.setText(getString(R.string.o2o_detail_has_done) + orderNum + getString(R.string.o2o_detail_dan_pingjias) + Float.valueOf(xin) / Float.valueOf(orderNum));
                }else {
                    tvdanping.setText(getString(R.string.o2o_detail_has_done) + orderNum + getString(R.string.o2o_detail_dan_pingjia) + Float.valueOf(xin) / Float.valueOf(orderNum));

                }

            }

    }

//    private void setSkill(ProDetailSkillEntity proSkill) {
//    }
//

    private void bindDataToView(StarEntity starEntity) {
        str1 = starEntity.getData().getStr1();
        str2 = starEntity.getData().getStr2();
        str3 = starEntity.getData().getStr3();
        str4 = starEntity.getData().getStr4();
        str5 = starEntity.getData().getStr5();
        initLabelView(5);
    }

    private void initLabelView(float v) {
//        ToastUtil.shortToast(EvaluateActivity.this, "" + v);
        cardsLayout.removeAllViews();
        if (v == 1) {
            xin = "1";
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                tvEvalueWord.setText("Very dissatisfied");
            }else{
                tvEvalueWord.setText("极差");
            }

            if (str1 != null) {
                for (int i = 0; i < str1.size(); i++) {
                    UserStarView starView = new UserStarView(EvaluateActivity.this);
                    starView.setViewVisible(false);
                    if ("en".equals(AppGlobal.getInstance().getLagStr())) {
                        if(str1.get(i).getYname().equals("Unpunctuality or breaking the appointment")){
                            starView.setBadText("Unpunctuality or breaking\n the appointment");
                        }else {
                            starView.setBadText(str1.get(i).getYname());
                        }


                    } else if ("zh".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setBadText(str1.get(i).getName());
                    }

                    cardsLayout.addView(starView);
                }
            }
        } else if (v == 2) {
            xin = "2";
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                tvEvalueWord.setText("Dissatisfied");
            }else{
                tvEvalueWord.setText("差");
            }
            if (str2 != null) {
                for (int i = 0; i < str2.size(); i++) {
                    UserStarView starView = new UserStarView(EvaluateActivity.this);
                    starView.setViewVisible(false);
                    if ("en".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setBadText(str2.get(i).getYname());
                    } else if ("zh".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setBadText(str2.get(i).getName());
                    }
                    cardsLayout.addView(starView);
                }
            }

        } else if (v == 3) {
            xin = "3";
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                tvEvalueWord.setText("Neither dissatisfied nor satisfied");
            }else{
                tvEvalueWord.setText("一般");
            }

            if (str3 != null) {
                for (int i = 0; i < str3.size(); i++) {
                    UserStarView starView = new UserStarView(EvaluateActivity.this);
                    starView.setViewVisible(false);
                    if ("en".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setBadText(str3.get(i).getYname());
                    } else if ("zh".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setBadText(str3.get(i).getName());
                    }

                    cardsLayout.addView(starView);
                }
            }
        } else if (v == 4) {
            xin = "4";
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                tvEvalueWord.setText("Satisfied");
            }else{
                tvEvalueWord.setText("比较满意");
            }

            if (str4 != null) {
                for (int i = 0; i < str4.size(); i++) {
                    UserStarView starView = new UserStarView(EvaluateActivity.this);
                    starView.setViewVisible(true);
                    if ("en".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setGoodText(str4.get(i).getYname());
                    } else if ("zh".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setGoodText(str4.get(i).getName());
                    }
                    cardsLayout.addView(starView);
                }
            }
        } else if (v == 5) {
            xin = "5";
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                tvEvalueWord.setText("Very satisfied");
            }else{
                tvEvalueWord.setText("非常满意，无可挑剔");
            }

            if (str5 != null) {
                for (int i = 0; i < str5.size(); i++) {
                    UserStarView starView = new UserStarView(EvaluateActivity.this);
                    starView.setViewVisible(true);
                    if ("en".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setGoodText(str5.get(i).getYname());
                    } else if ("zh".equals(AppGlobal.getInstance().getLagStr())) {
                        starView.setGoodText(str5.get(i).getName());
                    }
                    cardsLayout.addView(starView);
                }
            }
        }
        int count = cardsLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            mapBoolen.put(i, false);
            UserStarView starView = (UserStarView) cardsLayout.getChildAt(i);
            final TextView tView = starView.getTvBadorGood((int) v);
            final int finalI = i;
            tView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!mapBoolen.get(finalI)) {
                        mapBoolen.put(finalI, true);
                        tView.setSelected(true);
                        intMap.put(finalI, 1);
                    } else {
                        mapBoolen.put(finalI, false);
                        tView.setSelected(false);
                        intMap.put(finalI, 0);
                    }
                }
            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        etcontent.setCursorVisible(false);
        return super.onTouchEvent(event);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        LocationUtils locationUtils = new LocationUtils(EvaluateActivity.this);
        String str = locationUtils.getGeoStr();
        mSigGogleEntity = LocationUtils.parStr(str);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.evaluate_et_content:
                etcontent.setCursorVisible(true);
                break;
            case R.id.user_evaluate_tv_commit://提交
                String content = etcontent.getText().toString().trim();
                Map<String, String> map = new HashMap<>();
                map.put("type", "2");
                map.put("uid", uid);//服务商id
                map.put("oid", oid);//订单id
                map.put("ouid", AppGlobal.getInstance().getUser().getUid() == null ? "" : AppGlobal.getInstance().getUser().getUid());//我的id
                map.put("content", content);
                map.put("xin", xin);
                String xin = map.get("xin");
                if ("1".equals(xin)) {
                    StringBuilder str = new StringBuilder();
                    for (Map.Entry mp : mapBoolen.entrySet()) {
                        boolean value = (boolean) mp.getValue();
                        if (value) {
                            str = str.append(str1.get((int) mp.getKey()).getId()).append(",");
                        }
                    }
                    if (str.toString().endsWith(",")) {
                        str.deleteCharAt(str.lastIndexOf(","));
                    }
                    l_id = str.toString();
                } else if ("2".equals(xin)) {
                    StringBuilder str = new StringBuilder();
                    for (Map.Entry mp : mapBoolen.entrySet()) {
                        boolean value = (boolean) mp.getValue();
                        if (value) {
                            str = str.append(str2.get((int) mp.getKey()).getId()).append(",");
                        }
                    }
                    if (str.toString().endsWith(",")) {
                        str.deleteCharAt(str.lastIndexOf(","));
                    }
                    l_id = str.toString();
                } else if ("3".equals(xin)) {
                    StringBuilder str = new StringBuilder();
                    for (Map.Entry mp : mapBoolen.entrySet()) {
                        boolean value = (boolean) mp.getValue();
                        if (value) {
                            str = str.append(str3.get((int) mp.getKey()).getId()).append(",");
                        }
                    }
                    if (str.toString().endsWith(",")) {
                        str.deleteCharAt(str.lastIndexOf(","));
                    }
                    l_id = str.toString();
                } else if ("4".equals(xin)) {
                    StringBuilder str = new StringBuilder();
                    for (Map.Entry mp : mapBoolen.entrySet()) {
                        boolean value = (boolean) mp.getValue();
                        if (value) {
                            str = str.append(str4.get((int) mp.getKey()).getId()).append(",");
                        }
                    }
                    if (str.toString().endsWith(",")) {
                        str.deleteCharAt(str.lastIndexOf(","));
                    }
                    l_id = str.toString();
                } else if ("5".equals(xin)) {
                    StringBuilder str = new StringBuilder();
                    for (Map.Entry mp : mapBoolen.entrySet()) {
                        boolean value = (boolean) mp.getValue();
                        if (value) {
                            str = str.append(str5.get((int) mp.getKey()).getId()).append(",");
                        }
                    }
                    if (str.toString().endsWith(",")) {
                        str.deleteCharAt(str.lastIndexOf(","));
                    }
                    l_id = str.toString();
                }
                if (TextUtils.isEmpty(l_id)) {
                    ToastUtil.longToast(EvaluateActivity.this, getString(R.string.xb));
                    return;
                }
                LoadDialog.showProgressDialog(EvaluateActivity.this);
                map.put("l_id", l_id);
                map.put("secret_key", SPUtils.getString(EvaluateActivity.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                mRequestQueue.add(new PlatRequest(this, Contants.comment, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        LoadDialog.closeProgressDialog();
                        if (code == 200) {
                            ToastUtil.shortToast(EvaluateActivity.this, getString(R.string.commit_success));
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            ToastUtil.shortToast(EvaluateActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
                break;
        }
    }
}
