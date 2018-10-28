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

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.entity.SchEntity;
import com.haiwai.housekeeper.entity.SkillDetailEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.utils.DoubleDatePickerDialog;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.NewSchoolVew;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lyj on 2016/9/26.
 */
public class ProSkillShowcActivity extends BaseProActivity {
    private TextView ib_btn_showc, ib_btn_showc_save;
    private TopViewNormalBar top_showc_bar;
    private LinearLayout ll_school_layout;
    private RelativeLayout ib_add_school;
    private NewSchoolVew schoolVew;
    private List<SchEntity> schList = new ArrayList<>();
    private List<SchEntity> schSaveList = new ArrayList<>();
    private static final String START = "start";
    private static final String END = "end";
    Bundle bBundle = null;
    boolean flag = false;
    private List<SkillDetailEntity.DataBean.EducationBean> mEduList;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_skill_show_second_layout);
        top_showc_bar = (TopViewNormalBar) findViewById(R.id.top_showc_bar);
        top_showc_bar.setTitle(getString(R.string.skill_adds));
        top_showc_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initView();
    }

    private void initData() {
        flag = getIntent().getBooleanExtra("edit", false);
        if (flag) {
            Bundle bundle = getIntent().getBundleExtra("bundle");
            mEduList = (List<SkillDetailEntity.DataBean.EducationBean>) bundle.getSerializable("edu");
        } else {
            bBundle = getIntent().getBundleExtra("bundle");
        }
    }

    private void initView() {
        ib_btn_showc_save = (TextView) findViewById(R.id.ib_btn_showc_save);
        ib_btn_showc = (TextView) findViewById(R.id.ib_btn_showc);
        ((TextView) findViewById(R.id.ib_btn_showc1)).setText("");
        ib_btn_showc.setText(getString(R.string.tv_next_step));
        findViewById(R.id.rl_ede_btn).setOnClickListener(mOnClickListener);
        ib_btn_showc.setOnClickListener(mOnClickListener);
        ib_btn_showc_save.setOnClickListener(mOnClickListener);
        ll_school_layout = (LinearLayout) findViewById(R.id.ll_school_layout);
        if (flag) {
            findViewById(R.id.rl_ede_btn).setVisibility(View.GONE);
            ib_btn_showc_save.setVisibility(View.VISIBLE);
            ib_btn_showc.setVisibility(View.GONE);
            if (mEduList != null) {
                ll_school_layout.removeAllViews();
                for (int i = 0; i < mEduList.size(); i++) {
                    schoolVew = new NewSchoolVew(this);
                    schoolVew.getDelView().setVisibility(View.GONE);
                    schoolVew.getDelView().setVisibility(View.GONE);
                    schoolVew.setSchName(mEduList.get(i).getName());
                    schoolVew.setEduName(mEduList.get(i).getXue());
                    schoolVew.setStrSting(TimeUtils.getStr12Time(mEduList.get(i).getStime()));
                    schoolVew.setEndString(TimeUtils.getStr12Time(mEduList.get(i).getEtime()));
                    schoolVew.setOnClicklister(mOnClickListener);
                    ll_school_layout.addView(schoolVew);
                }
            }
        } else {
            ll_school_layout.removeAllViews();
            schoolVew = new NewSchoolVew(this);
            schoolVew.setOnClicklister(mOnClickListener);
            ll_school_layout.addView(schoolVew);
        }
        for (int i = 0; i < ll_school_layout.getChildCount(); i++) {
            final NewSchoolVew sv = (NewSchoolVew) ll_school_layout.getChildAt(i);
            sv.getDelView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ll_school_layout.removeView(sv);
                }
            });
        }
        ib_add_school = (RelativeLayout) findViewById(R.id.ib_add_school);
        ib_add_school.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_showc_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.ib_add_school) {
                addViewToLayout();
            }else if(v.getId()==R.id.rl_ede_btn){
                checkData();
            } else if (v.getId() == R.id.ib_btn_showc) {
                checkData();
            } else if (v == schoolVew.getStarView()) {
                initDate(schoolVew, START);
            } else if (v == schoolVew.getEndView()) {
                initDate(schoolVew, END);
            } else if (v.getId() == R.id.ib_btn_showc_save) {//保存
                saveData();
            }
        }
    };


    private void addViewToLayout() {
        schoolVew = new NewSchoolVew(this);
        schoolVew.setOnClicklister(mOnClickListener);
        ll_school_layout.addView(schoolVew);
        for (int i = 0; i < ll_school_layout.getChildCount(); i++) {
            final NewSchoolVew sschoolVew = (NewSchoolVew) ll_school_layout.getChildAt(i);
            sschoolVew.setOnClicklister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == sschoolVew.getDelView()) {
                        ll_school_layout.removeView(sschoolVew);
                    } else if (v == sschoolVew.getStarView()) {
                        initDate(sschoolVew, START);
                    } else if (v == sschoolVew.getEndView()) {
                        initDate(sschoolVew, END);
                    }
                }
            });
        }
    }

    private void initDate(final NewSchoolVew sschoolVew, final String str) {
        Calendar c = Calendar.getInstance();
        // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
        new DoubleDatePickerDialog(ProSkillShowcActivity.this, 0, new DoubleDatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker startDatePicker, int year, int month, int day) {
                String textString = String.format("%d-%d", year,
                        month + 1, day);
                if (START.equals(str)) {
                    sschoolVew.setStrSting(textString);
                } else if (END.equals(str)) {
                    sschoolVew.setEndString(textString);
                }

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
    }

    private void checkData() {
        int viewCount = ll_school_layout.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            NewSchoolVew mView = (NewSchoolVew) ll_school_layout.getChildAt(i);
            String name = mView.getNameString();
            String xue = mView.getSchString();
            String stime = mView.getStartString();
            String etime = mView.getEndString();
            SchEntity schEntity = new SchEntity();
            schEntity.setType("1");//学校经历1
            schEntity.setName(name);
            schEntity.setXue(xue);
            schEntity.setStime(TimeUtils.getMonthStamp(stime));
            schEntity.setEtime(TimeUtils.getMonthStamp(etime));
            schList.add(schEntity);
        }
        Intent intent = new Intent(ProSkillShowcActivity.this, ProSkillShowdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBundle("bBundle", bBundle);
        bundle.putSerializable("schList", (Serializable) schList);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }


    private void saveData() {
        schSaveList.clear();
        int viewCount = ll_school_layout.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            NewSchoolVew mView = (NewSchoolVew) ll_school_layout.getChildAt(i);
            String name = mView.getNameString();
            String xue = mView.getSchString();
            String stime = mView.getStartString();
            String etime = mView.getEndString();
            SchEntity schEntity = new SchEntity();
            schEntity.setType("1");//学校经历1
            schEntity.setName(name);
            schEntity.setXue(xue);
            schEntity.setStime(TimeUtils.getMonthStamp(stime));
            schEntity.setEtime(TimeUtils.getMonthStamp(etime));
            schSaveList.add(schEntity);
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putBundle("bundle", bBundle);
        bundle.putSerializable("schSaveList", (Serializable) schSaveList);
        intent.putExtra("bundle", bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
