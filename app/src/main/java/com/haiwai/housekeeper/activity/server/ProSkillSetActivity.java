package com.haiwai.housekeeper.activity.server;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.SchEntity;
import com.haiwai.housekeeper.entity.SkillDetailEntity;
import com.haiwai.housekeeper.entity.SkillEntity;
import com.haiwai.housekeeper.entity.SkillProItem;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PaseProUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SetUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.SkillSetItemView;
import com.haiwai.housekeeper.view.SkillSetTitleView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProSkillSetActivity extends BaseProActivity {
    private TopViewNormalBar mSkillSetBar;
    private ToggleButton tb_is_open;
    private LinearLayout ib_show_of_no;
    private LinearLayout ll_content_skill;
    SkillEntity.DataBean skDaba;
    Map<String, String> map = new LinkedHashTreeMap<>();
    User user;
    private String uid;
    private String type;
    private String id;
    private String classStr;
    private String advantageStr;
    private boolean isShow = true;
    private TextView tv_class_str, tv_adv_str, tv_set_del;
    private Button btn_set_save;
    private TextView ib_skill_edit, ib_edu_edit, ib_work_edit;
    private static final int JNREQUSETCODE = 100;
    private static final int EDUREQUSETCODE = 101;
    private static final int WORKREQUSETCODE = 102;
    private SkillProItem mSkillProItem;
    SkillDetailEntity skillDetailEntity;
    private List<SchEntity> list = new ArrayList<>();
    private List<SchEntity> eduSaveList = new ArrayList<>();
    private List<SchEntity> workSaveList = new ArrayList<>();
    private Map<String, String> mKeyMap;
    private String isZhorEn = "";
    private String is_ji = "";//0审核中，1已激活，2已关闭

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://保存成功
                    LoadDialog.closeProgressDialog();
                    mRefreshSkillDataListener.changeNewdata();
                    finish();
                    break;
                case 1://删除成功
                    LoadDialog.closeProgressDialog();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_skill_set);
        mSkillSetBar = (TopViewNormalBar) findViewById(R.id.top_skillSet_bar);
        mSkillSetBar.setTitle(getString(R.string.skill_set));
        mSkillSetBar.setOnBackListener(mOnClickListener);
        initData();
        initView();
    }

    private void initView() {
        tv_class_str = (TextView) findViewById(R.id.tv_class_str);
        tv_class_str.setText(classStr);
        tv_adv_str = (TextView) findViewById(R.id.tv_adv_str);
        tv_adv_str.setText(advantageStr);
        tv_set_del = (TextView) findViewById(R.id.tv_set_del);
        tv_set_del.setOnClickListener(mOnClickListener);
        btn_set_save = (Button) findViewById(R.id.btn_set_save);
        if(AppGlobal.getInstance().getLagStr().equals("zh")){
                    btn_set_save.setText(SpanUtil.getNewString(getResources().getString(R.string.btn_set_save), 14, 0, 2, 18, 2, 6));
        }else{
            btn_set_save.setText(getString(R.string.btn_set_btn_save));

        }
        btn_set_save.setOnClickListener(mOnClickListener);
        ib_skill_edit = (TextView) findViewById(R.id.ib_skill_edit);
        ib_skill_edit.setOnClickListener(mOnClickListener);
        ib_edu_edit = (TextView) findViewById(R.id.ib_edu_edit);
        ib_edu_edit.setOnClickListener(mOnClickListener);
        ib_work_edit = (TextView) findViewById(R.id.ib_work_edit);
        ib_work_edit.setOnClickListener(mOnClickListener);
        tb_is_open = (ToggleButton) findViewById(R.id.tb_is_open);
        if ("1".equals(is_ji)) {
            tb_is_open.setChecked(true);
        } else {
            tb_is_open.setChecked(false);
        }
        tb_is_open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if ("1".equals(is_ji)) {
                    if (b) {
                        map = new LinkedHashMap<>();
                        map.put("id", skDaba.getId());
                        map.put("secret_key", SPUtils.getString(ProSkillSetActivity.this, "secret", ""));
                        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                        NetSkill(map, "open");
                    } else {
                        map = new LinkedHashMap<>();
                        map.put("id", skDaba.getId());
                        map.put("secret_key", SPUtils.getString(ProSkillSetActivity.this, "secret", ""));
                        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                        NetSkill(map, "off");
                    }
//                } else {
//                    ToastUtil.longToast(ProSkillSetActivity.this, getString(R.string.skill_ti1));
//                    tb_is_open.setChecked(false);
//                }

            }
        });
        ib_show_of_no = (LinearLayout) findViewById(R.id.ib_show_of_no);
        ib_show_of_no.setOnClickListener(mOnClickListener);
        ll_content_skill = (LinearLayout) findViewById(R.id.ll_content_skill);
        if (mSkillProItem != null) {
            if ("zh".equalsIgnoreCase(isZhorEn)) {
                List<SkillProItem.ProCn> proCns = mSkillProItem.getProCns();
                if (proCns != null) {
                    for (int i = 0; i < proCns.size(); i++) {
                        SkillSetTitleView setTitleView = new SkillSetTitleView(ProSkillSetActivity.this);
                        setTitleView.setTitleText(proCns.get(i).getProtitlecn());
                        ll_content_skill.addView(setTitleView);
                        List<String> proanscn = proCns.get(i).getProanscn();
                        if (proanscn != null) {
                            for (int j = 0; j < proanscn.size(); j++) {
                                SkillSetItemView skillView = new SkillSetItemView(ProSkillSetActivity.this);
                                skillView.setLeftText(proanscn.get(j));
                                ll_content_skill.addView(skillView);
                            }
                        }
                    }
                }
            } else if ("en".equalsIgnoreCase(isZhorEn)) {
                List<SkillProItem.ProEn> proEns = mSkillProItem.getProEns();
                if (proEns != null) {
                    for (int i = 0; i < proEns.size(); i++) {
                        SkillSetTitleView setTitleView = new SkillSetTitleView(ProSkillSetActivity.this);
                        setTitleView.setTitleText(proEns.get(i).getProtitleen());
                        ll_content_skill.addView(setTitleView);
                        List<String> proansen = proEns.get(i).getProansen();
                        if (proansen != null) {
                            for (int j = 0; j < proansen.size(); j++) {
                                SkillSetItemView skillView = new SkillSetItemView(ProSkillSetActivity.this);
                                skillView.setLeftText(proansen.get(j));
                                ll_content_skill.addView(skillView);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < ll_content_skill.getChildCount(); i++) {
            if (ll_content_skill.getChildAt(i) instanceof SkillSetItemView) {
                SkillSetItemView skillView = (SkillSetItemView) ll_content_skill.getChildAt(i);
                final int finalI = i;
                String str = "wen" + finalI;
                Log.i("strInformation--",mKeyMap.get(str));
                if ("1".equals(mKeyMap.get(str))) {
                    skillView.getView().setChecked(true);
                }
                skillView.getView().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            String str = "wen" + finalI;
                            String value = "1";
                            map.put(str, value);
                        } else {
                            String str = "wen" + finalI;
                            String value = "0";
                            map.put(str, value);
                        }
                    }
                });
            }
        }
    }


    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        uid = user.getUid();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            skDaba = (SkillEntity.DataBean) bundle.getSerializable("skDaba");
            type = skDaba.getType();
            id = skDaba.getId();
            is_ji = skDaba.getIs_ji();
            classStr = skDaba.getClassX();
            advantageStr = skDaba.getAdvantage();
        }
        mSkillProItem = SetUtils.getSkillProItem(ProSkillSetActivity.this, Integer.valueOf(type));
        mKeyMap = PaseProUtils.getMap(skDaba);
        initDetailData(uid, type, id);
    }

    private List<SkillDetailEntity.DataBean.EducationBean> listEdu = new ArrayList<>();
    private List<SkillDetailEntity.DataBean.JobBean> listJob = new ArrayList<>();


    private void initDetailData(String uid, String type, String id) {
        map = new LinkedHashMap<>();
        map.put("uid", uid);
        map.put("type", type);
        map.put("id", id);
        map.put("secret_key", SPUtils.getString(ProSkillSetActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillSetActivity.this, Contants.skill_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    skillDetailEntity = new Gson().fromJson(response, SkillDetailEntity.class);
                    listEdu.clear();
                    listJob.clear();
                    listEdu.addAll(skillDetailEntity.getData().getEducation());

                    listJob.addAll(skillDetailEntity.getData().getJob());
                } else {
                    ToastUtil.shortToast(ProSkillSetActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mSkillSetBar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.ib_show_of_no) {//展开与隐藏
                if (!isShow) {
                    isShow = true;
                    ll_content_skill.setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.tv_expand_word)).setText(getString(R.string.need_order_hide));
                    ((TextView) findViewById(R.id.tv_expand_word)).setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.mipmap.arrow_up_white),null);
                } else {
                    isShow = false;
                    ll_content_skill.setVisibility(View.GONE);
                    ((TextView) findViewById(R.id.tv_expand_word)).setText(getString(R.string.need_order_expand));
                    ((TextView) findViewById(R.id.tv_expand_word)).setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.mipmap.arrow_down_white),null);
                }
            } else if (v.getId() == R.id.ib_skill_edit) {//服务编辑
                Intent intent = new Intent(ProSkillSetActivity.this, ProSkillShowbActivity.class);
                intent.putExtra("edit", true);
                intent.putExtra("type", type);
                intent.putExtra("classStr", classStr);
                intent.putExtra("advantageStr", advantageStr);
                startActivityForResult(intent, JNREQUSETCODE);
            } else if (v.getId() == R.id.ib_edu_edit) {//教育编辑
                Intent intent = new Intent(ProSkillSetActivity.this, ProSkillShowcActivity.class);
                intent.putExtra("edit", true);
                Bundle bundle = new Bundle();
                bundle.putSerializable("edu", (Serializable) listEdu);
                intent.putExtra("bundle", bundle);
                startActivityForResult(intent, EDUREQUSETCODE);
            } else if (v.getId() == R.id.ib_work_edit) {//工作编辑
                Intent intent = new Intent(ProSkillSetActivity.this, ProSkillShowdActivity.class);
                intent.putExtra("edit", true);
                Bundle bundle = new Bundle();
                bundle.putSerializable("job", (Serializable) listJob);
                intent.putExtra("bundle", bundle);
                startActivityForResult(intent, WORKREQUSETCODE);
            } else if (v.getId() == R.id.btn_set_save) {//保存
                submitData();
            } else if (v.getId() == R.id.tv_set_del) {//删除

                CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProSkillSetActivity.this);
                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.delete_skill)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteData();
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


    private void NetSkill(Map<String, String> map, String status) {
        if ("open".equals(status)) {
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillSetActivity.this, Contants.skill_on, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        ToastUtil.shortToast(ProSkillSetActivity.this, getString(R.string.skill_ti2));
                        Message msg = Message.obtain();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } else {
                        ToastUtil.shortToast(ProSkillSetActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        } else if ("off".equals(status)) {
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillSetActivity.this, Contants.skill_off, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        ToastUtil.shortToast(ProSkillSetActivity.this, getString(R.string.skill_ti3));
                        Message msg = Message.obtain();
                        msg.what = 1;
                        mHandler.sendMessage(msg);

                    } else {
                        ToastUtil.shortToast(ProSkillSetActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case JNREQUSETCODE:
                    Bundle bundle = data.getBundleExtra("bundle");
                    classStr = bundle.getString("classStr");
                    advantageStr = bundle.getString("adavantageStr");
                    tv_class_str.setText(classStr);
                    tv_adv_str.setText(advantageStr);
                    break;
                case EDUREQUSETCODE:
                    bundle = data.getBundleExtra("bundle");
                    eduSaveList.clear();
                    listEdu.clear();
                    eduSaveList.addAll((java.util.List<SchEntity>) bundle.getSerializable("schSaveList"));
                    for(int i=0;i<eduSaveList.size();i++){
                        SchEntity mEntity  = eduSaveList.get(i);

//                        private String type;
//                        private String name;
//                        private String xue;
//                        private String stime;
//                        private String etime;

                        SkillDetailEntity.DataBean.EducationBean mBean = new SkillDetailEntity.DataBean.EducationBean();
                        mBean.setEtime(mEntity.getEtime());
                        mBean.setName(mEntity.getName());
                        mBean.setStime(mEntity.getStime());
                        mBean.setType(mEntity.getType());
                        mBean.setXue(mEntity.getXue());
                        listEdu.add(mBean);
                    }
                    break;
                case WORKREQUSETCODE:
                    bundle = data.getBundleExtra("bundle");
                    workSaveList.clear();
                    listJob.clear();

                    workSaveList.addAll((java.util.List<SchEntity>) bundle.getSerializable("workSaveList"));
                    for(int i=0;i<workSaveList.size();i++){
                        SchEntity mEntity = workSaveList.get(i);
                        SkillDetailEntity.DataBean.JobBean mJob = new SkillDetailEntity.DataBean.JobBean();
                        mJob.setEtime(mEntity.getEtime());
                        mJob.setName(mEntity.getName());
                        mJob.setStime(mEntity.getStime());
                        mJob.setType(mEntity.getType());
                        mJob.setXue(mEntity.getXue());
                        listJob.add(mJob);
                    }
                    break;
            }
        }
    }


    private void submitData() {
        LoadDialog.showProgressDialog(ProSkillSetActivity.this);
        map.put("id", id);
        map.put("uid", uid);
        map.put("type", type);
        map.put("class", classStr);
        map.put("advantage", advantageStr);
        map.put("secret_key", SPUtils.getString(ProSkillSetActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        list.clear();
        if (eduSaveList.size() == 0) {
            if (skillDetailEntity != null) {
                List<SkillDetailEntity.DataBean.EducationBean> education = skillDetailEntity.getData().getEducation();
                List<SkillDetailEntity.DataBean.JobBean> job = skillDetailEntity.getData().getJob();
                if (education != null) {
                    for (int i = 0; i < education.size(); i++) {
                        SchEntity eduEntity = new SchEntity();
                        eduEntity.setType(education.get(i).getType());
                        eduEntity.setName(education.get(i).getName());
                        eduEntity.setXue(education.get(i).getXue());
                        eduEntity.setStime(education.get(i).getStime());
                        eduEntity.setEtime(education.get(i).getEtime());
                        eduSaveList.add(eduEntity);
                    }
                }
            }
        }
        list.addAll(eduSaveList);
        if (workSaveList.size() == 0) {
            if (skillDetailEntity != null) {
                List<SkillDetailEntity.DataBean.EducationBean> education = skillDetailEntity.getData().getEducation();
                List<SkillDetailEntity.DataBean.JobBean> job = skillDetailEntity.getData().getJob();
                if (job != null) {
                    workSaveList = new ArrayList<>();
                    for (int i = 0; i < job.size(); i++) {
                        SchEntity jobEntity = new SchEntity();
                        jobEntity.setType(job.get(i).getType());
                        jobEntity.setName(job.get(i).getName());
                        jobEntity.setXue(job.get(i).getXue());
                        jobEntity.setStime(job.get(i).getStime());
                        jobEntity.setEtime(job.get(i).getEtime());
                        workSaveList.add(jobEntity);
                    }
                }
            }
        }
        list.addAll(workSaveList);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                String key1 = "experience[" + i + "][type]";
                String key2 = "experience[" + i + "][name]";
                String key3 = "experience[" + i + "][xue]";
                String key4 = "experience[" + i + "][stime]";
                String key5 = "experience[" + i + "][etime]";
                String value1 = list.get(i).getType();
                String value2 = list.get(i).getName();
                String value3 = list.get(i).getXue();
                String value4 = list.get(i).getStime();
                String value5 = list.get(i).getEtime();
                map.put(key1, value1);
                map.put(key2, value2);
                map.put(key3, value3);
                map.put(key4, value4);
                map.put(key5, value5);
            }
        }
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillSetActivity.this, Contants.skill_eduit, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(ProSkillSetActivity.this, getString(R.string.skill_ti4));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    ToastUtil.shortToast(ProSkillSetActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void deleteData() {
        map = new LinkedHashMap<>();
        map.put("uid", uid);
        map.put("type", type);
        map.put("secret_key", SPUtils.getString(ProSkillSetActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillSetActivity.this, Contants.skill_del, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(ProSkillSetActivity.this, getString(R.string.skill_ti5));
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } else {
                    ToastUtil.shortToast(ProSkillSetActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    public static RefreshSkillDataListener mRefreshSkillDataListener;

    public interface RefreshSkillDataListener {
        void changeNewdata();
    }

    public static void setRefreshSkillDataListener(RefreshSkillDataListener refreshSkillDataListener) {
        mRefreshSkillDataListener = refreshSkillDataListener;
    }
}
