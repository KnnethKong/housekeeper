package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.OrderYesDetailActivity;
import com.haiwai.housekeeper.activity.server.PicFeadActivity;
import com.haiwai.housekeeper.activity.server.ProWorkaFeadBackActivity;
import com.haiwai.housekeeper.adapter.FeadBackCommonAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.VipHouseDesignEntity;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.SquareImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Created by ihope007 on 2016/9/20
 * 自营下单页面——业务更多
 */
public class O2OServeDetailActivity extends BaseActivity {
    //-----------租赁------------
    boolean isZl = false;
    LinearLayout ll_zl_zj, ll_zl_zt, ll_zl_jg;
    EditText et_zj;

    EditText et_fwzt, et_wxwffyzj, et_zfyc;
    RadioButton rb_normal, rb_no_normal;

    EditText et_bz;
    TextView tv_zjsr_c, tv_glfy_c, tv_wfwxfy_c, tv_yrz_c;
    CheckBox cb_count;

    //---------------巡视-----------
    LinearLayout ll_pic_layout;
    private TextView tv_content, tv_content_date;
//    private ImageView siv_1;
//    private ImageView siv_2, siv_3;
    boolean isXs = false;
    boolean isYy = false;
    boolean isJz = false;
    String time = "";
    String step = "";
    ImageLoader mImageLoader;
    private CircleImageView civ_per_img;
    private TextView tv_nickName;
    private TextView tvDis, tv_finish_order;
    private ImageView iv_card, iv_skill;
    private String img="",img1="",img2="";

    private VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX mFeedbackBeanXXX;
    private VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX mFeedbackBeanXXX1;
    private VipHouseDesignEntity.DataBean.BillBean.FeedbackBean mFeedback;
    private List<VipHouseDesignEntity.DataBean.HomeBean.FeedbackBeanX> mFeedback1;
    private List<VipHouseDesignEntity.DataBean.HorticultureBean.FeedbackBeanXX> mFeedback2;
    String avatar = "";
    String nickname = "";
    private String isZhorEn = "";
    private GridView mgridview_report;
    private ArrayList<String> pathList = new ArrayList<>();

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_o2o_detail_serve, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.fwxq), Color.parseColor("#FF3C3C3C"));
        mgridview_report = ((GridView) findViewById(R.id.mgridview_report));

        mgridview_report.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(pathList.size()>0){
                    Intent intent = new Intent(O2OServeDetailActivity.this,PicFeadActivity.class);
                    intent.putStringArrayListExtra("imagePath",pathList);
                    intent.putExtra("ID",i);
                    startActivity(intent);
                }
            }
        });
        civ_per_img = (CircleImageView) findViewById(R.id.civ_per_img);
        civ_per_img.setOnClickListener(this);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        tvDis = (TextView) findViewById(R.id.tv_dis);
        tv_finish_order = (TextView) findViewById(R.id.tv_finish_order);
        iv_card = (ImageView) findViewById(R.id.iv_card);
        iv_skill = (ImageView) findViewById(R.id.iv_skill);
        ll_pic_layout = (LinearLayout) findViewById(R.id.ll_pic_layout);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content_date = (TextView) findViewById(R.id.tv_content_date);
//        siv_1 = (ImageView) findViewById(R.id.siv_1);
//        siv_2 = (ImageView) findViewById(R.id.siv_2);
//        siv_3 = (ImageView) findViewById(R.id.siv_3);

        ll_zl_zj = (LinearLayout) findViewById(R.id.ll_zl_zj);
        ll_zl_zt = (LinearLayout) findViewById(R.id.ll_zl_zt);
        ll_zl_jg = (LinearLayout) findViewById(R.id.ll_zl_jg);
        et_zj = (EditText) findViewById(R.id.et_zj);

        et_fwzt = (EditText) findViewById(R.id.et_fwzt);
        et_wxwffyzj = (EditText) findViewById(R.id.et_wxwffyzj);
        et_zfyc = (EditText) findViewById(R.id.et_zfyc);
        rb_normal = (RadioButton) findViewById(R.id.rb_normal);
        rb_no_normal = (RadioButton) findViewById(R.id.rb_no_normal);

        et_bz = (EditText) findViewById(R.id.et_bz);
        tv_zjsr_c = (TextView) findViewById(R.id.tv_zjsr_c);
        tv_glfy_c = (TextView) findViewById(R.id.tv_glfy_c);
        tv_wfwxfy_c = (TextView) findViewById(R.id.tv_wfwxfy_c);
        tv_yrz_c = (TextView) findViewById(R.id.tv_yrz_c);
        cb_count = (CheckBox) findViewById(R.id.cb_count);
        findViewById(R.id.user_o2o_detail_iv_msg).setOnClickListener(this);
        findViewById(R.id.user_o2o_detail_iv_tel).setOnClickListener(this);

//        siv_1.setOnClickListener(this);
//        siv_2.setOnClickListener(this);
//        siv_3.setOnClickListener(this);

        if(getIntent().getStringExtra("is_new")!=null){
            if(!getIntent().getStringExtra("is_new").equals("2")){
                ((CheckBox) findViewById(R.id.cb_fwqk)).setChecked(true);
            }else{
                findViewById(R.id.cb_fwqk).setVisibility(View.GONE);
            }
        }else{
            findViewById(R.id.cb_fwqk).setVisibility(View.GONE);
        }
        if(getIntent().getStringExtra("is_special")!=null){
            if(!getIntent().getStringExtra("is_special").equals("2")){
                ((CheckBox) findViewById(R.id.cb_xjqk)).setChecked(true);
            }else{
                findViewById(R.id.cb_xjqk).setVisibility(View.GONE);
            }
        }else{
            findViewById(R.id.cb_xjqk).setVisibility(View.GONE);
        }
    }



    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        avatar = getIntent().getStringExtra("avatar");
        nickname = getIntent().getStringExtra("nickname");
        if(!avatar.equals("")){
            MyApp.getImageLoader().displayImage(avatar, civ_per_img);
        }
        tv_nickName.setText(nickname);
        String user_xing = getIntent().getStringExtra("xing");
        String user_onum = getIntent().getStringExtra("user_onum");
        if(Float.valueOf(user_onum)!=0){
            tv_finish_order.setText(getString(R.string.evaluate_title)+String.format("%.1f",Float.valueOf(user_xing)/Float.valueOf(user_onum)));
        }else{
            tv_finish_order.setText(getString(R.string.evaluate_title)+"0.0");
        }


        if(getIntent().getStringExtra("s")!=null){
            if(getIntent().getStringExtra("s").equals("1")){
                final String url = "drawable://" + R.mipmap.v_icon;
                ImageLoader.getInstance().displayImage(url, ((ImageView) findViewById(R.id.iv_user_degree)));
            }else if(getIntent().getStringExtra("s").equals("2")){
                final String url = "drawable://" + R.mipmap.s_icon;
                ImageLoader.getInstance().displayImage(url, ((ImageView) findViewById(R.id.iv_user_degree)));
            }else{
                findViewById(R.id.iv_user_degree).setVisibility(View.GONE);
                findViewById(R.id.iv_red_user_degree).setVisibility(View.VISIBLE);
            }
        }else{
            findViewById(R.id.iv_user_degree).setVisibility(View.GONE);
            findViewById(R.id.iv_red_user_degree).setVisibility(View.VISIBLE);
        }



        if(getIntent().getStringExtra("is_ren").equals("1")){
            iv_skill.setImageResource(R.mipmap.shenfenrenzheng_jiangbei);
        }else{
            iv_skill.setImageResource(R.mipmap.o2o_item_jn_grey);
        }

        if(getIntent().getStringExtra("is_hou").equals("1")){
            iv_card.setImageResource(R.mipmap.shenfenrenzheng_card);
        }else{
            iv_card.setImageResource(R.mipmap.o2o_item_sf_grey);
        }

        isJz = getIntent().getBooleanExtra("isJz", false);
        if (isJz) {
            ll_pic_layout.setVisibility(View.VISIBLE);
            ll_zl_zj.setVisibility(View.GONE);
            ll_zl_zt.setVisibility(View.GONE);
            ll_zl_jg.setVisibility(View.GONE);
            Bundle bundle = getIntent().getBundleExtra("bundle");
            step = getIntent().getStringExtra("step");
            mFeedback1 = (List<VipHouseDesignEntity.DataBean.HomeBean.FeedbackBeanX>) bundle.getSerializable("mFeedback1");
            tv_content.setText(mFeedback1.get(Integer.valueOf(step)).getContent1());
            tv_content_date.setText(TimeUtils.getDate(mFeedback1.get(Integer.valueOf(step)).getWtime1()));
            try {
                final JSONArray array = new JSONArray(mFeedback1.get(Integer.valueOf(step)).getImages1());

                if(array!=null){
                    for(int x=0;x<array.length();x++){
                        pathList.add(array.optString(x));
                    }

                    FeadBackCommonAdapter mMFeadHistAdapter = new FeadBackCommonAdapter(O2OServeDetailActivity.this, pathList);

                    mgridview_report.setAdapter(mMFeadHistAdapter);

                }

//                if (array != null) {
//                    if (!TextUtils.isEmpty(array.optString(0))) {
//                        MyApp.getImageLoader().displayImage(array.optString(0), siv_1);
//                        img = array.optString(0);
//                    }
//                    if (!TextUtils.isEmpty(array.optString(1))) {
//                        MyApp.getImageLoader().displayImage(array.optString(1), siv_2);
//                        img1 = array.optString(1);
//                    }
//                    if (!TextUtils.isEmpty(array.optString(2))) {
//                        MyApp.getImageLoader().displayImage(array.optString(1), siv_3);
//                        img2 = array.optString(2);
//                    }
//                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        isYy = getIntent().getBooleanExtra("isYy", false);
        if (isYy) {
            ll_pic_layout.setVisibility(View.VISIBLE);
            ll_zl_zj.setVisibility(View.GONE);
            ll_zl_zt.setVisibility(View.GONE);
            ll_zl_jg.setVisibility(View.GONE);
            Bundle bundle = getIntent().getBundleExtra("bundle");
            step = getIntent().getStringExtra("step");
            mFeedback2 = (List<VipHouseDesignEntity.DataBean.HorticultureBean.FeedbackBeanXX>) bundle.getSerializable("mFeedback2");
            tv_content.setText(mFeedback2.get(Integer.valueOf(step)).getContent1());
            tv_content_date.setText(TimeUtils.getDate(mFeedback2.get(Integer.valueOf(step)).getWtime1()));
            try {
                final JSONArray array = new JSONArray(mFeedback2.get(Integer.valueOf(step)).getImages1());

                if(array!=null){
                    for(int x=0;x<array.length();x++){
                        pathList.add(array.optString(x));
                    }

                    FeadBackCommonAdapter mMFeadHistAdapter = new FeadBackCommonAdapter(O2OServeDetailActivity.this, pathList);

                    mgridview_report.setAdapter(mMFeadHistAdapter);

                }

//                if (array != null) {
//                    if (!TextUtils.isEmpty(array.optString(0))) {
//                        MyApp.getImageLoader().displayImage(array.optString(0), siv_1);
//                        img = array.optString(0);
//                    }
//                    if (!TextUtils.isEmpty(array.optString(1))) {
//                        MyApp.getImageLoader().displayImage(array.optString(1), siv_2);
//                        img1 = array.optString(1);
//                    }
//                    if (!TextUtils.isEmpty(array.optString(2))) {
//                        MyApp.getImageLoader().displayImage(array.optString(2), siv_3);
//                        img2 = array.optString(2);
//                    }
//                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        isZl = getIntent().getBooleanExtra("isZl", false);
        if (isZl) {
            Bundle bundle = getIntent().getBundleExtra("bundle");
            mFeedback = (VipHouseDesignEntity.DataBean.BillBean.FeedbackBean) bundle.getSerializable("mFeedback");
            ll_pic_layout.setVisibility(View.GONE);
            step = getIntent().getStringExtra("step");
            if ("A".equals(step)) {
                ll_zl_zj.setVisibility(View.VISIBLE);
                ll_zl_zt.setVisibility(View.GONE);
                ll_zl_jg.setVisibility(View.GONE);
                et_zj.setText("$"+mFeedback.getRent());
            } else if ("B".equals(step)) {
                ll_zl_zj.setVisibility(View.GONE);
                ll_zl_zt.setVisibility(View.VISIBLE);
                ll_zl_jg.setVisibility(View.GONE);
                et_fwzt.setText(mFeedback.getHome_remark());
                et_wxwffyzj.setText("$"+mFeedback.getWrent());
                et_zfyc.setText(mFeedback.getTenant_remark());
                if ("1".equals(mFeedback.getTenant_static())) {
                    rb_normal.setChecked(true);
                    findViewById(R.id.left_desc_status).setVisibility(View.GONE);
                } else if ("2".equals(mFeedback.getTenant_static())) {
                    rb_no_normal.setChecked(true);
                }

            } else if ("C".equals(step)) {
                ll_zl_zj.setVisibility(View.GONE);
                ll_zl_zt.setVisibility(View.GONE);
                ll_zl_jg.setVisibility(View.VISIBLE);
                et_bz.setText(mFeedback.getPrice_remark());
                tv_zjsr_c.setText("$" + mFeedback.getRent());
                tv_glfy_c.setText("$" + mFeedback.getGrent());
                tv_wfwxfy_c.setText("$" + mFeedback.getWrent());
                float rentCount = Float.valueOf(mFeedback.getRent()) - Float.valueOf(mFeedback.getGrent()) - Float.valueOf(mFeedback.getWrent());
                tv_yrz_c.setText("$" + rentCount);
                if ("1".equals(mFeedback.getIs_da())) {
                    cb_count.setChecked(true);
                }
            }
        }
        isXs = getIntent().getBooleanExtra("isXs", false);
        if (isXs) {
            ll_pic_layout.setVisibility(View.VISIBLE);
            ll_zl_zj.setVisibility(View.GONE);
            ll_zl_zt.setVisibility(View.GONE);
            ll_zl_jg.setVisibility(View.GONE);
            time = getIntent().getStringExtra("time");
            step = getIntent().getStringExtra("step");
            Bundle bundle = getIntent().getBundleExtra("bundle");
            mFeedbackBeanXXX = (VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX) bundle.getSerializable("mFeedbackBeanXXX");
            mFeedbackBeanXXX1 = (VipHouseDesignEntity.DataBean.VacancyBean.FeedbackBeanXXX) bundle.getSerializable("mFeedbackBeanXXX1");
            if ("1".equals(time) && "A".equals(step)) {
                tv_content.setText(mFeedbackBeanXXX.getContent1());
                tv_content_date.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime1()));
                try {
                    final JSONArray array = new JSONArray(mFeedbackBeanXXX.getImages1());

                    if(array!=null){

                        for(int x=0;x<array.length();x++){
                            pathList.add(array.optString(x));
                        }

                        FeadBackCommonAdapter mMFeadHistAdapter = new FeadBackCommonAdapter(O2OServeDetailActivity.this, pathList);

                        mgridview_report.setAdapter(mMFeadHistAdapter);

                    }

//                    if (array != null) {
//                        if (!TextUtils.isEmpty(array.optString(0))) {
//                            MyApp.getImageLoader().displayImage(array.optString(0), siv_1);
//                            img = array.optString(0);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(1))) {
//                            MyApp.getImageLoader().displayImage(array.optString(1), siv_2);
//                            img1 = array.optString(1);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(2))) {
//                            MyApp.getImageLoader().displayImage(array.optString(2), siv_3);
//                            img2 = array.optString(2);
//                        }
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if ("1".equals(time) && "B".equals(step)) {
                tv_content.setText(mFeedbackBeanXXX.getContent2());
                tv_content_date.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime2()));
                try {
                    final JSONArray array = new JSONArray(mFeedbackBeanXXX.getImages2());

                    if(array!=null){
                        for(int x=0;x<array.length();x++){
                            pathList.add(array.optString(x));
                        }

                        FeadBackCommonAdapter mMFeadHistAdapter = new FeadBackCommonAdapter(O2OServeDetailActivity.this, pathList);

                        mgridview_report.setAdapter(mMFeadHistAdapter);

                    }


//                    if (array != null) {
//                        if (!TextUtils.isEmpty(array.optString(0))) {
//                            MyApp.getImageLoader().displayImage(array.optString(0), siv_1);
//                            img = array.optString(0);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(1))) {
//                            MyApp.getImageLoader().displayImage(array.optString(1), siv_2);
//                            img1 = array.optString(1);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(2))) {
//                            MyApp.getImageLoader().displayImage(array.optString(2), siv_3);
//                            img2 = array.optString(2);
//                        }
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if ("1".equals(time) && "C".equals(step)) {
                tv_content.setText(mFeedbackBeanXXX.getContent3());
                tv_content_date.setText(TimeUtils.getDate(mFeedbackBeanXXX.getWtime3()));
                try {
                    final JSONArray array = new JSONArray(mFeedbackBeanXXX.getImages3());


                    if(array!=null){
                        for(int x=0;x<array.length();x++){
                            pathList.add(array.optString(x));
                        }

                        FeadBackCommonAdapter mMFeadHistAdapter = new FeadBackCommonAdapter(O2OServeDetailActivity.this, pathList);

                        mgridview_report.setAdapter(mMFeadHistAdapter);

                    }

//                    if (array != null) {
//                        if (!TextUtils.isEmpty(array.optString(0))) {
//                            MyApp.getImageLoader().displayImage(array.optString(0), siv_1);
//                            img = array.optString(0);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(1))) {
//                            MyApp.getImageLoader().displayImage(array.optString(1), siv_2);
//                            img1 = array.optString(1);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(2))) {
//                            MyApp.getImageLoader().displayImage(array.optString(2), siv_3);
//                            img2 = array.optString(2);
//                        }
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if ("2".equals(time) && "A".equals(step)) {
                tv_content.setText(mFeedbackBeanXXX1.getContent1());
                tv_content_date.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime1()));
                try {
                    final JSONArray array = new JSONArray(mFeedbackBeanXXX1.getImages1());


                    if(array!=null){
                        for(int x=0;x<array.length();x++){
                            pathList.add(array.optString(x));
                        }

                        FeadBackCommonAdapter mMFeadHistAdapter = new FeadBackCommonAdapter(O2OServeDetailActivity.this, pathList);

                        mgridview_report.setAdapter(mMFeadHistAdapter);

                    }

//                    if (array != null) {
//                        if (!TextUtils.isEmpty(array.optString(0))) {
//                            MyApp.getImageLoader().displayImage(array.optString(0), siv_1);
//                            img = array.optString(0);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(1))) {
//                            MyApp.getImageLoader().displayImage(array.optString(1), siv_2);
//                            img1 = array.optString(1);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(2))) {
//                            MyApp.getImageLoader().displayImage(array.optString(2), siv_3);
//                            img2 = array.optString(2);
//                        }
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if ("2".equals(time) && "B".equals(step)) {
                tv_content.setText(mFeedbackBeanXXX1.getContent2());
                tv_content_date.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime2()));
                try {
                    final JSONArray array = new JSONArray(mFeedbackBeanXXX1.getImages2());

                    if(array!=null){
                        for(int x=0;x<array.length();x++){
                            pathList.add(array.optString(x));
                        }

                        FeadBackCommonAdapter mMFeadHistAdapter = new FeadBackCommonAdapter(O2OServeDetailActivity.this, pathList);

                        mgridview_report.setAdapter(mMFeadHistAdapter);

                    }

//                    if (array != null) {
//                        if (!TextUtils.isEmpty(array.optString(0))) {
//                            MyApp.getImageLoader().displayImage(array.optString(0), siv_1);
//                            img = array.optString(0);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(1))) {
//                            MyApp.getImageLoader().displayImage(array.optString(1), siv_2);
//                            img1 = array.optString(1);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(2))) {
//                            MyApp.getImageLoader().displayImage(array.optString(2), siv_3);
//                            img2 = array.optString(2);
//                        }
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if ("2".equals(time) && "C".equals(step)) {
                tv_content.setText(mFeedbackBeanXXX1.getContent3());
                tv_content_date.setText(TimeUtils.getDate(mFeedbackBeanXXX1.getWtime3()));
                try {
                    final JSONArray array = new JSONArray(mFeedbackBeanXXX1.getImages3());

                    if(array!=null){
                        for(int x=0;x<array.length();x++){
                            pathList.add(array.optString(x));
                        }

                        FeadBackCommonAdapter mMFeadHistAdapter = new FeadBackCommonAdapter(O2OServeDetailActivity.this, pathList);

                        mgridview_report.setAdapter(mMFeadHistAdapter);

                    }


//                    if (array != null) {
//                        if (!TextUtils.isEmpty(array.optString(0))) {
//                            MyApp.getImageLoader().displayImage(array.optString(0), siv_1);
//                            img = array.optString(0);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(1))) {
//                            MyApp.getImageLoader().displayImage(array.optString(1), siv_2);
//                            img1 = array.optString(1);
//                        }
//                        if (!TextUtils.isEmpty(array.optString(2))) {
//                            MyApp.getImageLoader().displayImage(array.optString(2), siv_3);
//                            img2 = array.optString(2);
//                        }
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void click(View v) {
        switch(v.getId()){
            case R.id.civ_per_img:
                Intent in1 = new Intent(O2OServeDetailActivity.this, ProDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("uid", getIntent().getStringExtra("uid"));
                bundle.putString("nickname", nickname);
                bundle.putString("type", getIntent().getStringExtra("type"));
                bundle.putString("choose", "");
                bundle.putString("oid", "");//订单id
                bundle.putBoolean("isServer", true);
                in1.putExtra("fromO2O", true);
                in1.putExtras(bundle);
                startActivity(in1);
                break;
            case R.id.user_o2o_detail_iv_msg:
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getIntent().getStringExtra("mobile")));
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);

                break;
            case R.id.user_o2o_detail_iv_tel:
                String targetId = getIntent().getStringExtra("uid");
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(O2OServeDetailActivity.this, targetId, nickname);
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, nickname, Uri.parse(avatar)));
                }
                break;
//            case R.id.siv_1:
//                if(!img.equals("")){
//                    Intent intent = new Intent(O2OServeDetailActivity.this,ImgViewActivity.class);
//                    intent.putExtra("img",img);
//                    startActivity(intent);
//                }
//                break;
//            case R.id.siv_2:
//                if(!img1.equals("")){
//                    Intent intent = new Intent(O2OServeDetailActivity.this,ImgViewActivity.class);
//                    intent.putExtra("img",img1);
//                    startActivity(intent);
//                }
//                break;
//            case R.id.siv_3:
//                if(!img2.equals("")){
//                    Intent intent = new Intent(O2OServeDetailActivity.this,ImgViewActivity.class);
//                    intent.putExtra("img",img2);
//                    startActivity(intent);
//                }
//                break;

        }
    }
}
