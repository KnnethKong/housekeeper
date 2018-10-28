package com.haiwai.housekeeper.activity.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.adapter.SkillDetailEduAdapter;
import com.haiwai.housekeeper.adapter.SkillDetailJobAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.SkillDetailEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.MyListView;
import com.haiwai.housekeeper.view.ObservableScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/13.
 * 技能详情
 */
public class ProSkillActivity extends BaseActivity {
    private ImageView ivmsg, ivlike;
    private ObservableScrollView observableScrollView;
    private LinearLayout llFloat;
    private LinearLayout llbottom;

    //传过来的值
    private String id;
    private String uid;
    private String type;

    private String isZhorEn = "";
    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_pro_skill_detail, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitlebarHide(true);
        RelativeLayout rl_bg = (RelativeLayout) findViewById(R.id.user_pro_detail_title);
        rl_bg.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, PlatUtils.getImageRario(this, 7, 16)));
        findViewById(R.id.pro_detail_iv_back).setOnClickListener(this);
        observableScrollView = (ObservableScrollView) findViewById(R.id.pro_skill_detail_obscrl);

        llbottom = (LinearLayout) findViewById(R.id.user_pro_skill_ll_bottom);
        llbottom.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        id = getIntent().getExtras().get("id").toString();
        uid = getIntent().getExtras().get("uid").toString();
        type = getIntent().getExtras().get("type").toString();
        uid = getIntent().getExtras().get("uid").toString();
        isZhorEn = AppGlobal.getInstance().getLagStr();
        if (mApp.isLogin()) {
            if (uid.equals(AppGlobal.getInstance().getUser().getUid())) {
                llbottom.setVisibility(View.GONE);
            }
        }
        if (isNetworkAvailable())
            requestSkillDetail();
    }

    public void requestSkillDetail() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", type);
        map.put("id", id);
        map.put("secret_key", SPUtils.getString(ProSkillActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(ProSkillActivity.this, Contants.skill_detail, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        SkillDetailEntity entity = new Gson().fromJson(response, SkillDetailEntity.class);
                        bindDataToView(entity);
                    } else {
                        ToastUtil.shortToast(ProSkillActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void bindDataToView(SkillDetailEntity entity) {
        ImageView ivskill = (ImageView) findViewById(R.id.user_pro_skill_detail_iv_head);
        switch (type) {// TODO: 2016/11/18 修改技能图标
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
            case "11":
            case "12":
            case "13":
            case "14":
            case "15":
            case "16":
            case "17":
            case "18":
            case "19":
            case "20":
            case "21":
            case "22":
            case "23":
            case "24":
            case "25":
            case "26":
            case "27":
            case "28":
            case "33":
                ivskill.setImageResource(R.mipmap.pro_skill_detail_head);
                break;
        }
        ((TextView) findViewById(R.id.user_pro_skill_detail_tv_title)).setText(AssetsUtils.getSkillName(type,isZhorEn));
        TextView tvskillstate = (TextView) findViewById(R.id.user_pro_skill_detail_tv_skill_state);
        if ("2".equals(entity.getData().getDate().get(0).getIs_ren())) {
            tvskillstate.setText(getString(R.string.pro_skill_has_authenticate));
        } else {
            tvskillstate.setText(getString(R.string.pro_skill_no_authenticate));
        }
        ((TextView) findViewById(R.id.user_pro_skill_detail_tv_moneyp)).setText("$" + entity.getData().getDate().get(0).getMoney_p() + getString(R.string.o2o_detail_xie_pingmi));
        final TextView tvclass = (TextView) findViewById(R.id.user_pro_skill_detail_tv_class);
        if (!TextUtils.isEmpty(entity.getData().getDate().get(0).getClassX())) {
            tvclass.setText(entity.getData().getDate().get(0).getClassX());
        } else {
            // TODO: 2016/11/18 没有价格显示什么?
        }
        MyListView mlvedu = (MyListView) findViewById(R.id.user_pro_skill_detail_ml_edu);
        MyListView mlvjob = (MyListView) findViewById(R.id.user_pro_skill_detail_ml_job);
        mlvedu.setDivider(null);
        mlvjob.setDivider(null);
        mlvedu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mlvjob.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mlvedu.setAdapter(new SkillDetailEduAdapter(this, entity.getData().getEducation()));
        mlvjob.setAdapter(new SkillDetailJobAdapter(this, entity.getData().getJob()));

    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.pro_detail_iv_back:
                finish();
                break;
            case R.id.user_pro_skill_ll_bottom:
                if (mApp.isLogin()) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("id", type);
                    bundle2.putString("make_sure", "0");
                    bundle2.putString("at_uid", uid);
                    ActivityTools.goNextActivity(this, IssueRequireAActivity.class, bundle2);
                } else {
                    ActivityTools.goNextActivity(this, LoginActivity.class);
                }
                break;
        }
    }
}
