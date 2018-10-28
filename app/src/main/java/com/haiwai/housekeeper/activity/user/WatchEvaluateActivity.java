package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.BothEvaluateEntity;
import com.haiwai.housekeeper.entity.StarEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.LabelView;
import com.haiwai.housekeeper.view.WarpLinearLayout;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/5.
 * 查看评价
 */
public class WatchEvaluateActivity extends BaseActivity {
    private String uid;
    private String oid;

    private RatingBar ratingBar1, ratingBar2;
    private TextView tvlable1, tvlabel2;
    private TextView tvcontent1, tvcontent2, tvtitle1, tvtitle2;
    private WarpLinearLayout fl1, fl2;
    Map<String, String> mapLabel;
    private String isZhorEn = "";
    private LinearLayout ll_label_view;
    private LinearLayout ll_label_view1,llStar;


    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_watch_evaluate, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.code_str6), Color.parseColor("#FF3C3C3C"));
        llStar = ((LinearLayout) findViewById(R.id.ll_star_progress));
        ll_label_view = ((LinearLayout) findViewById(R.id.ll_label_view));
        ll_label_view1 = ((LinearLayout) findViewById(R.id.ll_label_view1));
        tvtitle1 = (TextView) findViewById(R.id.title1);
        tvtitle2 = (TextView) findViewById(R.id.title2);
        tvtitle1.setText(Html.fromHtml( "\t<font color='#37A0F4' size='17'>" + getString(R.string.watch_evaluate_give_him) + "</font>\t" ));
        tvtitle2.setText(Html.fromHtml("<font color='#37A0F4' size='17'>" + getString(R.string.watch_evaluate_give_me) + "</font>\t" ));

        ratingBar1 = (RatingBar) findViewById(R.id.watch_evaluate_me);
        ratingBar2 = (RatingBar) findViewById(R.id.watch_evaluate_he);

        tvlable1 = (TextView) findViewById(R.id.watch_evaluate_he_tv_title);
        tvlabel2 = (TextView) findViewById(R.id.watch_evaluate_me_tv_title);

        tvcontent1 = (TextView) findViewById(R.id.watch_evaluate_he_tv_content);
        tvcontent2 = (TextView) findViewById(R.id.watch_evaluate_me_tv_content);

        fl1 = (WarpLinearLayout) findViewById(R.id.watch_evaluate_fl_1);
        fl2 = (WarpLinearLayout) findViewById(R.id.watch_evaluate_fl_2);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        oid = getIntent().getExtras().get("oid").toString();
        uid = getIntent().getExtras().get("uid").toString();
        if (getIntent().getExtras().getBoolean("fromO2O", false)) {
            findViewById(R.id.value_from_o2o).setVisibility(View.VISIBLE);
            findViewById(R.id.value_from_pro).setVisibility(View.GONE);
        }
        if (isNetworkAvailable()) {
            requestLabel();
        }
    }

    private void requestLabel() {
        LoadDialog.showProgressDialog(this);
        Map<String, String> masp = new HashMap<>();
        masp.put("secret_key", SPUtils.getString(WatchEvaluateActivity.this, "secret", ""));
        mRequestQueue.add(new PlatRequest(this, Contants.order_label, masp, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");// TODO: 2016/9/12 标签 更换
                if (code == 200) {
                    StarEntity starEntity = new Gson().fromJson(response, StarEntity.class);
                    mapLabel = JsonUtils.parseEvaluateLabel(starEntity);
                    if (mapLabel != null) {
                        requestComment();
                    }
                } else {
                    ToastUtil.shortToast(WatchEvaluateActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    public void requestComment() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("oid", oid);
        map.put("ouid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(WatchEvaluateActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(WatchEvaluateActivity.this, Contants.order_comment, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    LoadDialog.closeProgressDialog();
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        BothEvaluateEntity entity = new Gson().fromJson(response, BothEvaluateEntity.class);
                        bindDataToView(entity);
                    } else {
                        ToastUtil.shortToast(WatchEvaluateActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void bindDataToView(BothEvaluateEntity entity) {
        List<BothEvaluateEntity.DataBean> data = entity.getData();
        if (data != null && data.size() > 0) {
            findViewById(R.id.value_from_pro).setVisibility(View.GONE);
            findViewById(R.id.value_from_o2o).setVisibility(View.GONE);

            for (int i = 0; i < data.size(); i++) {
                String xin = data.get(i).getXin();
                String label = data.get(i).getL_id();
                String content = data.get(i).getContent();
                if (data.get(i).getType().equals("1")) {
                    findViewById(R.id.value_from_pro).setVisibility(View.GONE);
                    tvtitle1.setText(Html.fromHtml("<font color='#37A0F4' size='17'>" + getString(R.string.watch_evaluate_give_me) + "</font>\t" + getString(R.string.watch_evaluate_de_evaluate)));
                    fl1.removeAllViews();
                    ratingBar2.setProgress(Integer.valueOf(xin));
                    String[] split = label.split(",");
                    tvcontent1.setText(content);
                    if (!label.equals("")) {
                        for (int j = 0; j < split.length; j++) {
                            LabelView labelView = new LabelView(this);
                            labelView.setContent(mapLabel.get(split[j]));
                            fl1.addView(labelView);
                        }
                    } else {
                        fl1.setVisibility(View.GONE);
                    }
                } else {
                    findViewById(R.id.value_from_o2o).setVisibility(View.VISIBLE);
                    tvtitle2.setText(Html.fromHtml( "\t<font color='#37A0F4' size='17'>" + getString(R.string.watch_evaluate_give_him) + "</font>\t" ));
                    fl2.removeAllViews();
                    if(content.equals("")){
                        tvcontent2.setVisibility(View.GONE);
                    }else{
                        tvcontent2.setText(content);
                    }
                    tvlabel2.setText(getStr(Integer.valueOf(xin)));
                    llStar.removeAllViews();
                    for(int x=0;x<Integer.valueOf(xin);x++){
                        ImageView iv = new ImageView(this);
                        iv.setImageResource(R.mipmap.pro_rating_icon);
                        llStar.addView(iv);
                    }
                    String[] split = label.split(",");
                    if (!label.equals("")) {
                        for (int j = 0; j < split.length; j++) {
                            LabelView labelView = new LabelView(this);
                            labelView.setContent(mapLabel.get(split[j]));
                            fl2.addView(labelView);
                        }
                    } else {
                        fl2.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    private String getStr(Integer i) {
        String str = "";
        if (i == 1) {
            str = getString(R.string.star1);
        } else if (i == 2) {
            str = getString(R.string.star2
            );
        } else if (i == 3) {
            str = getString(R.string.star3);
        } else if (i == 4) {
            str = getString(R.string.star4);
        } else if (i == 5) {
            str = getString(R.string.star5);
        }
        return str;
    }

    @Override
    protected void click(View v) {
    }
}
