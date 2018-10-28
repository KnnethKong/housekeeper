package com.haiwai.housekeeper.activity.server;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.EvaDataEntity;
import com.haiwai.housekeeper.entity.StarEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CardsLayout;
import com.haiwai.housekeeper.view.StarView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyj on 2016/9/23.
 */
public class ProEvaluateShowActivity extends BaseProActivity {
    private TopViewNormalBar top_evaluate_show_bar;
    private ImageButton ibStar1, ibStar2, ibStar3, ibStar4, ibStar5, ibOStar1, ibOStar2, ibOStar3, ibOStar4, ibOStar5;
    private TextView tvTag, tvOTag, tvContent, tvOContent;
    private CardsLayout tvCardsLayout, tvOCardsLayout;
    private LinearLayout ll_eva_layout;
    private TextView tv_empty;
    private List<StarEntity.DataBean.Str1Bean> str1;
    private List<StarEntity.DataBean.Str2Bean> str2;
    private List<StarEntity.DataBean.Str3Bean> str3;
    private List<StarEntity.DataBean.Str4Bean> str4;
    private List<StarEntity.DataBean.Str5Bean> str5;
    User user;
    private String uid;
    private String oid = "";
    private String ouid = "";
    Map<String, String> map = new HashMap<>();
    private String[] tagArr;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_evaluate_show_layout);
        top_evaluate_show_bar = (TopViewNormalBar) findViewById(R.id.top_evaluate_show_bar);
        top_evaluate_show_bar.setTitle(R.string.status_ep1);
        top_evaluate_show_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initView();
    }

    private void initView() {
        tv_empty = (TextView) findViewById(R.id.tv_empty);
        ((TextView)findViewById(R.id.tv_user)).setText(Html.fromHtml("<font color='#37A0F4' size='18'>" + getString(R.string.watch_evaluate_give_me) + "</font>\t" + getString(R.string.watch_evaluate_de_evaluate)));
        ((TextView)findViewById(R.id.tv_pro)).setText(Html.fromHtml( "\t<font color='#37A0F4' size='18'>" + getString(R.string.watch_evaluate_give_him1) + "</font>\t" + getString(R.string.evaluate_title)));
        ll_eva_layout = (LinearLayout) findViewById(R.id.ll_eva_layout);
        ibStar1 = (ImageButton) findViewById(R.id.ib_star1);
        ibStar2 = (ImageButton) findViewById(R.id.ib_star2);
        ibStar3 = (ImageButton) findViewById(R.id.ib_star3);
        ibStar4 = (ImageButton) findViewById(R.id.ib_star4);
        ibStar5 = (ImageButton) findViewById(R.id.ib_star5);
        ibOStar1 = (ImageButton) findViewById(R.id.ib_o_star1);
        ibOStar2 = (ImageButton) findViewById(R.id.ib_o_star2);
        ibOStar3 = (ImageButton) findViewById(R.id.ib_o_star3);
        ibOStar4 = (ImageButton) findViewById(R.id.ib_o_star4);
        ibOStar5 = (ImageButton) findViewById(R.id.ib_o_star5);
        tvTag = (TextView) findViewById(R.id.tv_tag);
        tvOTag = (TextView) findViewById(R.id.tv_o_tag);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvOContent = (TextView) findViewById(R.id.tv_o_content);
        tvCardsLayout = (CardsLayout) findViewById(R.id.tv_cardLayout);
        tvOCardsLayout = (CardsLayout) findViewById(R.id.tv_o_cardLayout);
    }

    private void initData() {
        LoadDialog.showProgressDialog(ProEvaluateShowActivity.this);
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(ProEvaluateShowActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProEvaluateShowActivity.this, Contants.order_label, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    StarEntity starEntity = new Gson().fromJson(response, StarEntity.class);
                    str1 = starEntity.getData().getStr1();
                    str2 = starEntity.getData().getStr2();
                    str3 = starEntity.getData().getStr3();
                    str4 = starEntity.getData().getStr4();
                    str5 = starEntity.getData().getStr5();
                    intShowData();
                } else {
                    ToastUtil.shortToast(ProEvaluateShowActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
        tagArr = getResources().getStringArray(R.array.star_arr);

    }

    private void intShowData() {
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }
        oid = getIntent().getStringExtra("oid");
        ouid = getIntent().getStringExtra("ouid");
        map.put("uid", uid);
        map.put("oid", oid);
        map.put("ouid", ouid);
        map.put("secret_key", SPUtils.getString(ProEvaluateShowActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProEvaluateShowActivity.this, Contants.order_comment, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>评论内容>>>>>>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    EvaDataEntity evaDataEntity = new Gson().fromJson(response, EvaDataEntity.class);
                    bindDataToView(evaDataEntity);
                    LoadDialog.closeProgressDialog();
                } else {
                    ToastUtil.shortToast(ProEvaluateShowActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    private void bindDataToView(EvaDataEntity evaDataEntity) {
        if (evaDataEntity != null) {
            List<EvaDataEntity.DataBean> data = evaDataEntity.getData();
            if (data.size() == 1) {
                findViewById(R.id.ll_second_comment).setVisibility(View.GONE);
                ll_eva_layout.setVisibility(View.GONE);
                EvaDataEntity.DataBean dataBean = data.get(0);
                if ("1".equals(dataBean.getType())||"2".equals(dataBean.getType())) {//服务商给用户评论
                    if("1".equals(dataBean.getType())){
                        ((TextView)findViewById(R.id.tv_pro)).setText(Html.fromHtml("\t<font color='#37A0F4' size='18'>" + getString(R.string.watch_evaluate_give_him1) + "</font>\t" + getString(R.string.evaluate_title)));
                    }else{
                        ((TextView)findViewById(R.id.tv_pro)).setText(Html.fromHtml("<font color='#37A0F4' size='18'>" + getString(R.string.watch_evaluate_give_me) + "</font>\t" + getString(R.string.watch_evaluate_de_evaluate)));
                    }
                    int xin = Integer.valueOf(dataBean.getXin());
                    showStarNum(xin);
                    tvTag.setText(tagArr[xin - 1]);
                    if(dataBean.getContent().equals("")){
                        tvContent.setVisibility(View.GONE);
                    }else{
                        tvContent.setText(dataBean.getContent());
                    }

                    String str = dataBean.getL_id();
                    if (str.length() > 1) {
                        String[] strs = str.split(",");
                        if (xin == 1) {
                            if (str1 != null) {
                                for (int i = 0; i < strs.length; i++) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str1.size(); j++) {
                                        if (str1.get(j).getId().equals(strs[i])) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                if(str1.get(j).getYname().equals("Unpunctuality or breaking the appointment")){
                                                    starView.setBadText("Unpunctuality or breaking\n the appointment");
                                                }else {
                                                    starView.setBadText(str1.get(j).getYname());
                                                }

                                            }else {
                                                starView.setBadText(str1.get(j).getName());
                                            }

                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            }
                        } else if (xin == 2) {
                            if (str2 != null) {
                                for (int i = 0; i < strs.length; i++) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str2.size(); j++) {
                                        if (str2.get(j).getId().equals(strs[i])) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                starView.setBadText(str2.get(j).getYname());
                                            }else {
                                                starView.setBadText(str2.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            }
                        } else if (xin == 3) {
                            if (str3 != null) {
                                for (int i = 0; i < strs.length; i++) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str3.size(); j++) {
                                        if (str3.get(j).getId().equals(strs[i])) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                starView.setBadText(str3.get(j).getYname());
                                            }else {
                                                starView.setBadText(str3.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            }
                        } else if (xin == 4) {
                            if (str4 != null) {
                                for (int i = 0; i < strs.length; i++) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str4.size(); j++) {
                                        if (str4.get(j).getId().equals(strs[i])) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                starView.setGoodText(str4.get(j).getYname());
                                            }else {
                                                starView.setGoodText(str4.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            }
                        } else if (xin == 5) {
                            if (str4 != null) {
                                for (int i = 0; i < strs.length; i++) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str5.size(); j++) {
                                        if (str5.get(j).getId().equals(strs[i])) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                starView.setGoodText(str5.get(j).getYname());
                                            }else {
                                                starView.setGoodText(str5.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            }
                        }
                    } else {//
                        if (xin == 1) {
                            if (str1 != null) {
                                StarView starView = new StarView(ProEvaluateShowActivity.this);
                                starView.setViewVisible(true);
                                for (int j = 0; j < str1.size(); j++) {
                                    if (str1.get(j).getId().equals(str)) {
                                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                                            if(str1.get(j).getYname().equals("Unpunctuality or breaking the appointment")){
                                                starView.setBadText("Unpunctuality or breaking\n the appointment");
                                            }else {
                                                starView.setBadText(str1.get(j).getYname());
                                            }

                                        }else {
                                            starView.setBadText(str1.get(j).getName());
                                        }
                                    }
                                }
                                tvCardsLayout.addView(starView);
                            }
                        } else if (xin == 2) {
                            if (str2 != null) {
                                StarView starView = new StarView(ProEvaluateShowActivity.this);
                                starView.setViewVisible(true);
                                for (int j = 0; j < str2.size(); j++) {
                                    if (str2.get(j).getId().equals(str)) {
                                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                starView.setBadText(str2.get(j).getYname());
                                        }else {
                                            starView.setBadText(str2.get(j).getName());
                                        }
                                    }
                                }
                                tvCardsLayout.addView(starView);
                            }
                        } else if (xin == 3) {
                            if (str3 != null) {
                                StarView starView = new StarView(ProEvaluateShowActivity.this);
                                starView.setViewVisible(true);
                                for (int j = 0; j < str3.size(); j++) {
                                    if (str3.get(j).getId().equals(str)) {
                                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                starView.setBadText(str3.get(j).getYname());
                                        }else {
                                            starView.setBadText(str3.get(j).getName());
                                        }
                                    }
                                }
                                tvCardsLayout.addView(starView);
                            }
                        } else if (xin == 4) {
                            if (str4 != null) {
                                StarView starView = new StarView(ProEvaluateShowActivity.this);
                                starView.setViewVisible(true);
                                for (int j = 0; j < str4.size(); j++) {
                                    if (str4.get(j).getId().equals(str)) {
                                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                starView.setGoodText(str4.get(j).getYname());
                                        }else {
                                            starView.setGoodText(str4.get(j).getName());
                                        }
                                    }
                                }
                                tvCardsLayout.addView(starView);
                            }
                        } else if (xin == 5) {
                            if (str5 != null) {
                                StarView starView = new StarView(ProEvaluateShowActivity.this);
                                starView.setViewVisible(true);
                                for (int j = 0; j < str5.size(); j++) {
                                    if (str5.get(j).getId().equals(str)) {
                                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                starView.setGoodText(str5.get(j).getYname());
                                        }else {
                                            starView.setGoodText(str5.get(j).getName());
                                        }
                                    }
                                }
                                tvCardsLayout.addView(starView);
                            }
                        }
                    }
                }
            } else if (data.size() == 2) {
                ll_eva_layout.setVisibility(View.VISIBLE);
                tv_empty.setVisibility(View.GONE);
                for (int a = 0; a < 2; a++) {
                    EvaDataEntity.DataBean dataBean = data.get(a);
                    if ("1".equals(dataBean.getType())) {//服务商给用户评论
                        int xin = Integer.valueOf(dataBean.getXin());
                        showStarNum(xin);
                        tvTag.setText(tagArr[xin - 1]);
                        if(dataBean.getContent().equals("")){
                            tvContent.setVisibility(View.GONE);
                        }else{
                            tvContent.setText(dataBean.getContent());
                        }

                        String str = dataBean.getL_id();
                        if (str.length() > 1) {
                            String[] strs = str.split(",");
                            if (xin == 1) {
                                if (str1 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str1.size(); j++) {
                                            if (str1.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    if(str1.get(j).getYname().equals("Unpunctuality or breaking the appointment")){
                                                        starView.setBadText("Unpunctuality or breaking\n the appointment");
                                                    }else {
                                                        starView.setBadText(str1.get(j).getYname());
                                                    }

                                                }else {
                                                    starView.setBadText(str1.get(j).getName());
                                                }
                                            }
                                        }
                                        tvCardsLayout.addView(starView);
                                    }
                                }
                            } else if (xin == 2) {
                                if (str2 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str2.size(); j++) {
                                            if (str2.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                        starView.setBadText(str2.get(j).getYname());
                                                }else {
                                                    starView.setBadText(str2.get(j).getName());
                                                }
                                            }
                                        }
                                        tvCardsLayout.addView(starView);
                                    }
                                }
                            } else if (xin == 3) {
                                if (str3 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str3.size(); j++) {
                                            if (str3.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                        starView.setBadText(str3.get(j).getYname());
                                                }else {
                                                    starView.setBadText(str3.get(j).getName());
                                                }
                                            }
                                        }
                                        tvCardsLayout.addView(starView);
                                    }
                                }
                            } else if (xin == 4) {
                                if (str4 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str4.size(); j++) {
                                            if (str4.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                        starView.setGoodText(str4.get(j).getYname());
                                                }else {
                                                    starView.setGoodText(str4.get(j).getName());
                                                }
                                            }
                                        }
                                        tvCardsLayout.addView(starView);
                                    }
                                }
                            } else if (xin == 5) {
                                if (str5 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str5.size(); j++) {
                                            if (str5.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                        starView.setGoodText(str5.get(j).getYname());
                                                }else {
                                                    starView.setGoodText(str5.get(j).getName());
                                                }
                                            }
                                        }
                                        tvCardsLayout.addView(starView);
                                    }
                                }
                            }
                        } else {
                            if (xin == 1) {
                                if (str1 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str1.size(); j++) {
                                        if (str1.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                if(str1.get(j).getYname().equals("Unpunctuality or breaking the appointment")){
                                                    starView.setBadText("Unpunctuality or breaking\n the appointment");
                                                }else {
                                                    starView.setBadText(str1.get(j).getYname());
                                                }

                                            }else {
                                                starView.setBadText(str1.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            } else if (xin == 2) {
                                if (str2 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str2.size(); j++) {
                                        if (str2.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    starView.setBadText(str2.get(j).getYname());
                                            }else {
                                                starView.setBadText(str2.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            } else if (xin == 3) {
                                if (str3 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str3.size(); j++) {
                                        if (str3.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    starView.setBadText(str3.get(j).getYname());
                                            }else {
                                                starView.setBadText(str3.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            } else if (xin == 4) {
                                if (str4 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str4.size(); j++) {
                                        if (str4.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    starView.setGoodText(str4.get(j).getYname());
                                            }else {
                                                starView.setGoodText(str4.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            } else if (xin == 5) {
                                if (str5 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str5.size(); j++) {
                                        if (str5.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    starView.setGoodText(str5.get(j).getYname());
                                            }else {
                                                starView.setGoodText(str5.get(j).getName());
                                            }
                                        }
                                    }
                                    tvCardsLayout.addView(starView);
                                }
                            }
                        }
                    } else if ("2".equals(dataBean.getType())) {//用户评论服务商
                        int xin = Integer.valueOf(dataBean.getXin());
                        showOStarNum(xin);
                        tvOTag.setText(tagArr[xin - 1]);
                        if(dataBean.getContent().equals("")){
                            tvOContent.setVisibility(View.GONE);
                        }else{
                            tvOContent.setText(dataBean.getContent());
                        }
                        String str = dataBean.getL_id();
                        if (str.length() > 1) {
                            String[] strs = str.split(",");
                            if (xin == 1) {
                                if (str1 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str1.size(); j++) {
                                            if (str1.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    if(str1.get(j).getYname().equals("Unpunctuality or breaking the appointment")){
                                                        starView.setBadText("Unpunctuality or breaking\n the appointment");
                                                    }else {
                                                        starView.setBadText(str1.get(j).getYname());
                                                    }

                                                }else {
                                                    starView.setBadText(str1.get(j).getName());
                                                }
                                            }
                                        }
                                        tvOCardsLayout.addView(starView);
                                    }
                                }
                            } else if (xin == 2) {
                                if (str2 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str2.size(); j++) {
                                            if (str2.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                        starView.setBadText(str2.get(j).getYname());
                                                }else {
                                                    starView.setBadText(str2.get(j).getName());
                                                }
                                            }
                                        }
                                        tvOCardsLayout.addView(starView);
                                    }
                                }
                            } else if (xin == 3) {
                                if (str3 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str3.size(); j++) {
                                            if (str3.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                        starView.setBadText(str3.get(j).getYname());
                                                }else {
                                                    starView.setBadText(str3.get(j).getName());
                                                }
                                            }
                                        }
                                        tvOCardsLayout.addView(starView);
                                    }
                                }
                            } else if (xin == 4) {
                                if (str4 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str4.size(); j++) {
                                            if (str4.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                        starView.setGoodText(str4.get(j).getYname());
                                                }else {
                                                    starView.setGoodText(str4.get(j).getName());
                                                }
                                            }
                                        }
                                        tvOCardsLayout.addView(starView);
                                    }
                                }
                            } else if (xin == 5) {
                                if (str5 != null) {
                                    for (int i = 0; i < strs.length; i++) {
                                        StarView starView = new StarView(ProEvaluateShowActivity.this);
                                        starView.setViewVisible(true);
                                        for (int j = 0; j < str5.size(); j++) {
                                            if (str5.get(j).getId().equals(strs[i])) {
                                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                        starView.setGoodText(str5.get(j).getYname());
                                                }else {
                                                    starView.setGoodText(str5.get(j).getName());
                                                }
                                            }
                                        }
                                        tvOCardsLayout.addView(starView);
                                    }
                                }
                            }
                        } else {
                            if (xin == 1) {
                                if (str1 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str1.size(); j++) {
                                        if (str1.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                if(str1.get(j).getYname().equals("Unpunctuality or breaking the appointment")){
                                                    starView.setBadText("Unpunctuality or breaking\n the appointment");
                                                }else {
                                                    starView.setBadText(str1.get(j).getYname());
                                                }

                                            }else {
                                                starView.setBadText(str1.get(j).getName());
                                            }
                                        }
                                    }
                                    tvOCardsLayout.addView(starView);
                                }
                            } else if (xin == 2) {
                                if (str2 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str2.size(); j++) {
                                        if (str2.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    starView.setBadText(str2.get(j).getYname());
                                            }else {
                                                starView.setBadText(str2.get(j).getName());
                                            }
                                        }
                                    }
                                    tvOCardsLayout.addView(starView);
                                }
                            } else if (xin == 3) {
                                if (str3 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str3.size(); j++) {
                                        if (str3.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    starView.setBadText(str3.get(j).getYname());
                                            }else {
                                                starView.setBadText(str3.get(j).getName());
                                            }
                                        }
                                    }
                                    tvOCardsLayout.addView(starView);
                                }
                            } else if (xin == 4) {
                                if (str4 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str4.size(); j++) {
                                        if (str4.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    starView.setGoodText(str4.get(j).getYname());
                                            }else {
                                                starView.setGoodText(str4.get(j).getName());
                                            }
                                        }
                                    }
                                    tvOCardsLayout.addView(starView);
                                }
                            } else if (xin == 5) {
                                if (str5 != null) {
                                    StarView starView = new StarView(ProEvaluateShowActivity.this);
                                    starView.setViewVisible(true);
                                    for (int j = 0; j < str5.size(); j++) {
                                        if (str5.get(j).getId().equals(str)) {
                                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                                    starView.setGoodText(str5.get(j).getYname());
                                            }else {
                                                starView.setGoodText(str5.get(j).getName());
                                            }
                                        }
                                    }
                                    tvOCardsLayout.addView(starView);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void showStarNum(int xin) {
        switch (xin) {
            case 1:
                ibStar1.setVisibility(View.VISIBLE);
                ibStar2.setVisibility(View.GONE);
                ibStar3.setVisibility(View.GONE);
                ibStar4.setVisibility(View.GONE);
                ibStar5.setVisibility(View.GONE);
                break;
            case 2:
                ibStar1.setVisibility(View.VISIBLE);
                ibStar2.setVisibility(View.VISIBLE);
                ibStar3.setVisibility(View.GONE);
                ibStar4.setVisibility(View.GONE);
                ibStar5.setVisibility(View.GONE);
                break;
            case 3:
                ibStar1.setVisibility(View.VISIBLE);
                ibStar2.setVisibility(View.VISIBLE);
                ibStar3.setVisibility(View.VISIBLE);
                ibStar4.setVisibility(View.GONE);
                ibStar5.setVisibility(View.GONE);
                break;
            case 4:
                ibStar1.setVisibility(View.VISIBLE);
                ibStar2.setVisibility(View.VISIBLE);
                ibStar3.setVisibility(View.VISIBLE);
                ibStar4.setVisibility(View.VISIBLE);
                ibStar5.setVisibility(View.GONE);
                break;
            case 5:
                ibStar1.setVisibility(View.VISIBLE);
                ibStar2.setVisibility(View.VISIBLE);
                ibStar3.setVisibility(View.VISIBLE);
                ibStar4.setVisibility(View.VISIBLE);
                ibStar5.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void showOStarNum(int xin) {
        switch (xin) {
            case 1:
                ibOStar1.setVisibility(View.VISIBLE);
                ibOStar2.setVisibility(View.GONE);
                ibOStar3.setVisibility(View.GONE);
                ibOStar4.setVisibility(View.GONE);
                ibOStar5.setVisibility(View.GONE);
                break;
            case 2:

                ibOStar1.setVisibility(View.VISIBLE);
                ibOStar2.setVisibility(View.VISIBLE);
                ibOStar3.setVisibility(View.GONE);
                ibOStar4.setVisibility(View.GONE);
                ibOStar5.setVisibility(View.GONE);
                break;
            case 3:
                ibOStar1.setVisibility(View.VISIBLE);
                ibOStar2.setVisibility(View.VISIBLE);
                ibOStar3.setVisibility(View.VISIBLE);
                ibOStar4.setVisibility(View.GONE);
                ibOStar5.setVisibility(View.GONE);
                break;
            case 4:
                ibOStar1.setVisibility(View.VISIBLE);
                ibOStar2.setVisibility(View.VISIBLE);
                ibOStar3.setVisibility(View.VISIBLE);
                ibOStar4.setVisibility(View.VISIBLE);
                ibOStar5.setVisibility(View.GONE);
                break;
            case 5:
                ibOStar1.setVisibility(View.VISIBLE);
                ibOStar2.setVisibility(View.VISIBLE);
                ibOStar3.setVisibility(View.VISIBLE);
                ibOStar4.setVisibility(View.VISIBLE);
                ibOStar5.setVisibility(View.VISIBLE);
                break;
        }
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_evaluate_show_bar.getBackView()) {
                finish();
            }
        }
    };
}
