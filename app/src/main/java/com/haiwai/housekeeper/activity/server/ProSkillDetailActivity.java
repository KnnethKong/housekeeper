package com.haiwai.housekeeper.activity.server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.SkillDetailEntity;
import com.haiwai.housekeeper.entity.SkillEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.AssetsUtils2;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.EduView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.WorkView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProSkillDetailActivity extends BaseProActivity {
    private ImageView iv_back;
    SkillEntity.DataBean skDataBean = null;
    User user;
    SkillDetailEntity skillDetailEntity;
    private LinearLayout llEduLayout, llWorkLayout;
    private TextView tvJnmc, tvJnrz, tvFwsf, tvFwjs;
    private String isZhorEn = "";
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_skill_detail);
        initData();
        initView();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        Map<String, String> map = new HashMap<>();
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            map.put("uid", user.getUid());
        }
        String id = getIntent().getStringExtra("id");
        String type = getIntent().getStringExtra("type");
        map.put("id", id);
        map.put("type", type);
        map.put("secret_key", SPUtils.getString(ProSkillDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillDetailActivity.this, Contants.skill_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    System.out.println(">>>>>>><<<<<<<<<<<<<<<<<<<<<,,," + response);
                    skillDetailEntity = new Gson().fromJson(response, SkillDetailEntity.class);
                    setImg(skillDetailEntity);
                    bindsView(skillDetailEntity);
                } else {
                    ToastUtil.shortToast(ProSkillDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }



    private void initView() {
        iv = ((ImageView) findViewById(R.id.iv_icon));
        tvJnmc = (TextView) findViewById(R.id.tv_jnmc);
        tvJnrz = (TextView) findViewById(R.id.tv_jnrz);
        tvFwsf = (TextView) findViewById(R.id.tv_fwsf);
        tvFwjs = (TextView) findViewById(R.id.tv_fwjs);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(mOnclickListener);
        llEduLayout = (LinearLayout) findViewById(R.id.ll_edu_layout);
        llWorkLayout = (LinearLayout) findViewById(R.id.ll_work_layout);

        if(getIntent().getBooleanExtra("isfromSkill",false)){
            findViewById(R.id.skill_fee_stand).setVisibility(View.GONE);
            findViewById(R.id.tv_fee_line).setVisibility(View.GONE);
        }
    }

    private void bindsView(SkillDetailEntity skillDetailEntity) {
        SkillDetailEntity.DataBean.DateBean mDateBean = skillDetailEntity.getData().getDate().get(0);
        tvJnmc.setText(AssetsUtils2.getSkillName(getIntent().getStringExtra("type"), isZhorEn));
        String is_ren = mDateBean.getIs_ren();
        if ("0".equals(is_ren)) {
            tvJnrz.setText(R.string.skill_status_r1);
        } else if ("1".equals(is_ren)) {
            tvJnrz.setText(R.string.skill_status_r2);
        } else if ("2".equals(is_ren)) {
            tvJnrz.setText(R.string.skill_status_r3);
        }
        int money_p = mDateBean.getMoney_p();
        if (0 == money_p) {
            tvFwsf.setText(R.string.skill_msg);
        } else {
            tvFwsf.setText(getString(R.string.skill_msg) + money_p + "");
        }
        tvFwjs.setText(mDateBean.getClassX());
        tvFwjs.setText(mDateBean.getClassX());
        if (skillDetailEntity.getData().getEducation().size() > 0 && !TextUtils.isEmpty(skillDetailEntity.getData().getEducation().get(0).getName())) {
            for (int i = 0; i < skillDetailEntity.getData().getEducation().size(); i++) {
                SkillDetailEntity.DataBean.EducationBean eduBean = skillDetailEntity.getData().getEducation().get(i);
                EduView eduView = new EduView(ProSkillDetailActivity.this);
                eduView.setEduSch(eduBean.getName());
                eduView.setEduState(eduBean.getXue());
                eduView.setEduTime(getString(R.string.skill_edu_d) + TimeUtils.getShort12Time(eduBean.getStime()) + "-" + TimeUtils.getShort12Time(eduBean.getEtime()));
                llEduLayout.addView(eduView);
            }
        }
        if (skillDetailEntity.getData().getJob().size() > 0 && !TextUtils.isEmpty(skillDetailEntity.getData().getJob().get(0).getName())) {
            for (int i = 0; i < skillDetailEntity.getData().getJob().size(); i++) {
                SkillDetailEntity.DataBean.JobBean jobBean = skillDetailEntity.getData().getJob().get(i);
                WorkView worView = new WorkView(ProSkillDetailActivity.this);
                worView.setWorSch(jobBean.getName());
                worView.setWorState(jobBean.getXue());
                worView.setWorTime(getString(R.string.skill_work_d) + TimeUtils.getShort12Time(jobBean.getStime()) + "-" + TimeUtils.getShort12Time(jobBean.getEtime()));
                llWorkLayout.addView(worView);
            }
        }


    }

    View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.iv_back) {
                finish();
            }
        }
    };
    private void setImg(SkillDetailEntity skill) {
        int skillType = Integer.valueOf(getIntent().getStringExtra("type"));
        switch (skillType) {
            case 1:
                iv.setImageResource(R.mipmap.pic_skill_fwbj);
                break;
            case 2:
                iv.setImageResource(R.mipmap.pic_skill_cpxj);
                break;
            case 3:
                iv.setImageResource(R.mipmap.pic_skill_dtqx);
                break;
            case 4:
                iv.setImageResource(R.mipmap.pic_skill_gyqx);
                break;
            case 5:
                iv.setImageResource(R.mipmap.pic_skill_ljcl);
                break;
            case 6:
                iv.setImageResource(R.mipmap.pic_skill_bybj);
                break;
            case 7:
                iv.setImageResource(R.mipmap.pic_skill_abxt);
                break;
            case 8:
                iv.setImageResource(R.mipmap.pic_skill_ch);
                break;
            case 9:
                iv.setImageResource(R.mipmap.pic_skill_wxjg);
                break;
            case 10:
                iv.setImageResource(R.mipmap.pic_skill_wdwx);
                break;
            case 11:
                iv.setImageResource(R.mipmap.pic_skill_yyxj);
                break;
            case 12:
                iv.setImageResource(R.mipmap.pic_skill_jd);
                break;
            case 13:
                iv.setImageResource(R.mipmap.pic_skill_gjfx);
                break;
            case 14:
                iv.setImageResource(R.mipmap.pic_skill_snfs);
                break;
            case 15:
                iv.setImageResource(R.mipmap.pic_skill_swfs);
                break;
            case 16:
                iv.setImageResource(R.mipmap.pic_skill_hs);
                break;
            case 17:
                iv.setImageResource(R.mipmap.pic_skill_dg);
                break;
            case 18:
                iv.setImageResource(R.mipmap.pic_skill_lry);
                break;
            case 19:
                iv.setImageResource(R.mipmap.pic_skill_jjyc);
                break;
            case 20:
                iv.setImageResource(R.mipmap.pic_skill_ptdb);
                break;
            case 21:
                iv.setImageResource(R.mipmap.pic_skill_bx);
                break;
            case 22:
                iv.setImageResource(R.mipmap.pic_skill_l_fygz);
                break;
            case 23:
                iv.setImageResource(R.mipmap.pic_skill_swfs);
                break;
            case 24:
                iv.setImageResource(R.mipmap.pic_skill_jsjl);
                break;
            case 25:
                iv.setImageResource(R.mipmap.pic_skill_fygz);
                break;
            case 26:
                iv.setImageResource(R.mipmap.pic_skill_flzx);
                break;
            case 27:
                iv.setImageResource(R.mipmap.pic_skill_fcmm);
                break;
            case 28:
                iv.setImageResource(R.mipmap.pic_skill_lydl);
                break;

        }

    }
}
