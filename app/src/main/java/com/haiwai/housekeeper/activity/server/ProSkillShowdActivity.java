package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.SchEntity;
import com.haiwai.housekeeper.entity.SkillDetailEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.DoubleDatePickerDialog;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.NewWorkVew;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyj on 2016/9/26.
 */
public class ProSkillShowdActivity extends BaseProActivity {
    private TextView ib_btn_showd, ib_btn_showd_save;
    private TopViewNormalBar top_showd_bar;
    private LinearLayout ll_work_layout;
    private RelativeLayout ib_add_work;
    private NewWorkVew workView;
    private List<SchEntity> schList;
    private List<SchEntity> workSaveList;
    private int count = 1;
    private static final String START = "start";
    private static final String END = "end";
    Bundle cBundle, bBundle;
    User user;
    private String uid;
    private String type;
    private String classStr;
    private String advantage;
    Map<String, String> strMap = new HashMap<>();
    boolean flag = false;
    List<SkillDetailEntity.DataBean.JobBean> jobList = null;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_skill_show_third_layout);
        top_showd_bar = (TopViewNormalBar) findViewById(R.id.top_showd_bar);
        top_showd_bar.setTitle(getString(R.string.skill_adds));
        top_showd_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        intData();
        initView();
    }

    private void intData() {
        flag = getIntent().getBooleanExtra("edit", false);
        if (flag) {
            Bundle bundle = getIntent().getBundleExtra("bundle");
            jobList = (List<SkillDetailEntity.DataBean.JobBean>) bundle.getSerializable("job");
        } else {
            user = AppGlobal.getInstance().getUser();
            if (user != null) {
                uid = user.getUid();
                strMap.put("uid", uid);
            }
            cBundle = getIntent().getBundleExtra("bundle");
            if (cBundle != null) {
                bBundle = cBundle.getBundle("bBundle");
                schList = (List<SchEntity>) cBundle.getSerializable("schList");
                type = bBundle.getString("type");
                classStr = bBundle.getString("class");
                advantage = bBundle.getString("advantage");
                strMap.put("type", type);
                strMap.put("class", classStr);
                strMap.put("advantage", advantage);

            }
        }


    }


    private void initView() {
        ib_btn_showd_save = (TextView) findViewById(R.id.ib_btn_showd_save);
        ib_btn_showd = (TextView) findViewById(R.id.ib_btn_showd);
        ((TextView) findViewById(R.id.ib_btn_showd1)).setText("");
        ib_btn_showd.setText(getString(R.string.tv_fabu));
        findViewById(R.id.rl_set_btn).setOnClickListener(mOnClickListener);
        ib_btn_showd.setOnClickListener(mOnClickListener);
        ib_btn_showd_save.setOnClickListener(mOnClickListener);
        ll_work_layout = (LinearLayout) findViewById(R.id.ll_work_layout);
        if (flag) {
            findViewById(R.id.rl_set_btn).setVisibility(View.GONE);
            ib_btn_showd_save.setVisibility(View.VISIBLE);
            ib_btn_showd.setVisibility(View.GONE);
            if (jobList != null) {
                ll_work_layout.removeAllViews();
                for (int i = 0; i < jobList.size(); i++) {
                    workView = new NewWorkVew(this);
                    workView.setWorkName(jobList.get(i).getName());
                    workView.setWoStr(jobList.get(i).getXue());
                    workView.setStaText(TimeUtils.getStr12Time(jobList.get(i).getStime()));
                    workView.setEndText(TimeUtils.getStr12Time(jobList.get(i).getEtime()));
                    workView.setOnClicklister(mOnClickListener);
                    ll_work_layout.addView(workView);
                }
            }
        } else {
            ll_work_layout.removeAllViews();
            workView = new NewWorkVew(this);
            workView.setOnClicklister(mOnClickListener);
            ll_work_layout.addView(workView);
        }
        for (int i = 0; i < ll_work_layout.getChildCount(); i++) {
            final NewWorkVew ww = (NewWorkVew) ll_work_layout.getChildAt(i);
            ww.getDelView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ll_work_layout.removeView(ww);
                }
            });
        }
        ib_add_work = (RelativeLayout) findViewById(R.id.ib_add_work);
        ib_add_work.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_showd_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.ib_add_work) {
                addWorkLayout();
            }else if(v.getId()==R.id.rl_set_btn){
                checkData();
            } else if (v.getId() == R.id.ib_btn_showd) {
                checkData();
            } else if (v == workView.getStarView()) {
                initDate(workView, START);
            } else if (v == workView.getEndView()) {
                initDate(workView, END);
            } else if (v.getId() == R.id.ib_btn_showd_save) {
                saveData();
            } else if (v == workView.getDelView()) {
                ll_work_layout.removeView(workView);
            }
        }
    };


    private void addWorkLayout() {
        workView = new NewWorkVew(this);
        ll_work_layout.addView(workView);
        for (int i = 0; i < ll_work_layout.getChildCount(); i++) {
            final NewWorkVew mWorkView = (NewWorkVew) ll_work_layout.getChildAt(i);
            mWorkView.setOnClicklister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == mWorkView.getDelView()) {
                        ll_work_layout.removeView(mWorkView);
                    } else if (v == mWorkView.getStarView()) {
                        initDate(mWorkView, START);
                    } else if (v == mWorkView.getEndView()) {
                        initDate(mWorkView, END);
                    }

                }
            });
        }
    }

    private void initDate(final NewWorkVew workView, final String str) {
        Calendar c = Calendar.getInstance();
        // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
        new DoubleDatePickerDialog(ProSkillShowdActivity.this, 0, new DoubleDatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker startDatePicker, int year, int month, int day) {
                String textString = String.format("%d-%d", year,
                        month + 1, day);
                if (START.equals(str)) {
                    workView.setStaText(textString);
                } else if (END.equals(str)) {
                    workView.setEndText(textString);
                }

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
    }

    private void checkData() {
        LoadDialog.showProgressDialog(ProSkillShowdActivity.this);
        int viewCount = ll_work_layout.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            NewWorkVew mwView = (NewWorkVew) ll_work_layout.getChildAt(i);
            String name = mwView.getNameString();
            String xue = mwView.getSchString();
            String stime = mwView.getStartString();
            String etime = mwView.getEndString();
            SchEntity schEntity = new SchEntity();
            schEntity.setType("2");//工作经历2
            schEntity.setName(name);
            schEntity.setXue(xue);
            schEntity.setStime(TimeUtils.getMonthStamp(stime));
            schEntity.setEtime(TimeUtils.getMonthStamp(etime));
            schList.add(schEntity);
        }
        for (int i = 0; i < schList.size(); i++) {
            String key1 = "experience[" + i + "][type]";
            String key2 = "experience[" + i + "][name]";
            String key3 = "experience[" + i + "][xue]";
            String key4 = "experience[" + i + "][stime]";
            String key5 = "experience[" + i + "][etime]";
            String value1 = TextUtils.isEmpty(schList.get(i).getType()) ? "" : schList.get(i).getType();
            String value2 = TextUtils.isEmpty(schList.get(i).getName()) ? "" : schList.get(i).getName();
            String value3 = TextUtils.isEmpty(schList.get(i).getXue()) ? "" : schList.get(i).getXue();
            String value4 = TextUtils.isEmpty(schList.get(i).getStime()) ? "" : schList.get(i).getStime();
            String value5 = TextUtils.isEmpty(schList.get(i).getEtime()) ? "" : schList.get(i).getEtime();
            strMap.put(key1, value1);
            strMap.put(key2, value2);
            strMap.put(key3, value3);
            strMap.put(key4, value4);
            strMap.put(key5, value5);
        }
        strMap.put("secret_key", SPUtils.getString(ProSkillShowdActivity.this, "secret", ""));
        strMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillShowdActivity.this, Contants.skill_add, strMap, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    LoadDialog.closeProgressDialog();
                    Intent intent = new Intent(ProSkillShowdActivity.this, ProSkillShoweActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                } else {
                    ToastUtil.shortToast(ProSkillShowdActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    if(code==1104){
                        Intent intent = new Intent(ProSkillShowdActivity.this, ProSkillShoweActivity.class);
                        intent.putExtra("type", type);
                        startActivity(intent);
                    }
                }
            }
        }));

    }

    private void saveData() {
        workSaveList = new ArrayList<>();
        int viewCount = ll_work_layout.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            NewWorkVew mwView = (NewWorkVew) ll_work_layout.getChildAt(i);
            String name = mwView.getNameString();
            String xue = mwView.getSchString();
            String stime = mwView.getStartString();
            String etime = mwView.getEndString();
            SchEntity schEntity = new SchEntity();
            schEntity.setType("2");//工作经历2
            schEntity.setName(name);
            schEntity.setXue(xue);
            schEntity.setStime(TimeUtils.getMonthStamp(stime));
            schEntity.setEtime(TimeUtils.getMonthStamp(etime));
            workSaveList.add(schEntity);
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putBundle("bundle", bBundle);
        bundle.putSerializable("workSaveList", (Serializable) workSaveList);
        intent.putExtra("bundle", bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

}
