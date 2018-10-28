package com.haiwai.housekeeper.fragment.server;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.ProAddSkillActivity;
import com.haiwai.housekeeper.activity.server.ProAddressActivity;
import com.haiwai.housekeeper.activity.server.ProMainActivity;
import com.haiwai.housekeeper.activity.server.ProMoneyStrategyActivity;
import com.haiwai.housekeeper.activity.server.ProSkillDetailActivity;
import com.haiwai.housekeeper.activity.server.ProSkillMessageActivity;
import com.haiwai.housekeeper.activity.server.ProSkillSetActivity;
import com.haiwai.housekeeper.activity.server.ProSkillShowaActivity;
import com.haiwai.housekeeper.activity.server.ProSkillShowdActivity;
import com.haiwai.housekeeper.activity.server.ProSkillShoweActivity;
import com.haiwai.housekeeper.adapter.ProSkillListViewAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.AddressEntity;
import com.haiwai.housekeeper.entity.SkillEntity;
import com.haiwai.housekeeper.entity.SkillItemEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AppCommonts;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.PullToRefreshListView;
import com.haiwai.housekeeper.widget.LoadDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillFragment extends BaseProFragment implements AdapterView.OnItemClickListener, PullToRefreshListView.OnRefreshListener, ProSkillSetActivity.RefreshSkillDataListener {
    private TextView tv_skill_right_button;
    private ImageView iv_skill_right_button;
    private TextView ib_add_btn;
    private List<SkillItemEntity> typeList = new ArrayList<>();
    private PullToRefreshListView lv_skill_mine_skill;
    private TextView tv_skill_mine_skill, tv_skill_mime_message;
    private List<SkillEntity.DataBean> mSkillEntityList = new ArrayList<>();
    private List<SkillEntity.DataBean> mmSkillEntityList = new ArrayList<>();
    private ProSkillListViewAdapter mProSkillListViewAdapter;
    private User mUser;
    private String isZhorEn = "";
    Map<String, String> map = new HashMap<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mProSkillListViewAdapter.notifyDataSetChanged();
                    lv_skill_mine_skill.onRefreshComplete();
                    break;
            }
            super.handleMessage(msg);

        }
    };

    @Override
    protected void init() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        ProSkillSetActivity.setRefreshSkillDataListener(this);
    }

    @Override
    public View initView() {
        View skillView = View.inflate(context, R.layout.pro_fragment_skill, null);
        initView(skillView);
        return skillView;
    }

    @Override
    protected void initData() {

    }

    private void initNetData() {
        LoadDialog.showProgressDialog(getContext());
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.skill_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println("技能列表>>>" + response);
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    SkillEntity skillEntity = new Gson().fromJson(response, SkillEntity.class);
                    initNetData(skillEntity);
                } else {
//                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        mUser = AppGlobal.getInstance().getUser();
        if (mUser != null) {
            map.put("uid", mUser.getUid());
        }
        initNetData();
    }

    private void initNetData(SkillEntity skillEntity) {
        mSkillEntityList.clear();
        mmSkillEntityList.clear();
        typeList.clear();
        for (int i = 0; i < skillEntity.getData().size(); i++) {
            mSkillEntityList.add(skillEntity.getData().get(i));
            if (Integer.valueOf(skillEntity.getData().get(i).getType()) < 29) {
                typeList.add(new SkillItemEntity(skillEntity.getData().get(i).getType(), skillEntity.getData().get(i).getIs_audit()));
                mmSkillEntityList.add(skillEntity.getData().get(i));
            }
        }
        SPUtils.saveList(context, "mList", mmSkillEntityList);
        Message msg = Message.obtain();
        msg.what = 0;
        mHandler.sendMessage(msg);
    }

    private void initView(View skillView) {
        tv_skill_right_button = (TextView) skillView.findViewById(R.id.tv_skill_right_button);
        iv_skill_right_button = (ImageView) skillView.findViewById(R.id.iv_skill_right_button);
        if ("en".equals(isZhorEn)) {
            tv_skill_right_button.setVisibility(View.GONE);
            iv_skill_right_button.setVisibility(View.VISIBLE);
        } else {
            tv_skill_right_button.setVisibility(View.VISIBLE);
            iv_skill_right_button.setVisibility(View.GONE);
        }
        tv_skill_right_button.setOnClickListener(mOnclickListener);
        iv_skill_right_button.setOnClickListener(mOnclickListener);
        tv_skill_mine_skill = (TextView) skillView.findViewById(R.id.tv_skill_mine_skill);
        tv_skill_mine_skill.setSelected(true);
        tv_skill_mime_message = (TextView) skillView.findViewById(R.id.tv_skill_mime_message);
        tv_skill_mine_skill.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tv_skill_mime_message.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tv_skill_mime_message.setOnClickListener(mOnclickListener);
        lv_skill_mine_skill = (PullToRefreshListView) skillView.findViewById(R.id.lv_skill_mine_skill);
        lv_skill_mine_skill.setonRefreshListener(this);
//        View footView = View.inflate(context, R.layout.pro_skill_listview_footview, null);
//        lv_skill_mine_skill.addFooterView(footView);
        ib_add_btn = (TextView) skillView.findViewById(R.id.ib_add_btn1);
        ib_add_btn.setOnClickListener(mOnclickListener);
        lv_skill_mine_skill.setOnItemClickListener(this);
        mProSkillListViewAdapter = new ProSkillListViewAdapter(context, mSkillEntityList, mOnclickListener, isZhorEn);
        lv_skill_mine_skill.setAdapter(mProSkillListViewAdapter);
    }


    View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tv_skill_right_button || v.getId() == R.id.iv_skill_right_button) {
                startActivity(new Intent(context, ProMoneyStrategyActivity.class));//赚钱策略
            } else if (v.getId() == R.id.tv_skill_mime_message) {
                startActivity(new Intent(context, ProSkillMessageActivity.class));
            } else if (v.getId() == R.id.ib_add_btn1) {
                if (SPUtils.getBoolean(getContext(),"isHaveServiceAddress",false)) {
                    Intent intent = new Intent(context, ProSkillShowaActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("typeList", (Serializable) typeList);
                    bundle.putBoolean("isSkill",true);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ProAddressActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("isMine",false);
                    bundle.putSerializable("typeList", (Serializable) typeList);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
//                    ActivityTools.goNextActivity(getActivity(), ProAddressActivity.class);
                }
            } else {//设置页面
//                ImageView ivSkillSet = (ImageView) v;
                int tag = (int) v.getTag();
                SkillEntity.DataBean skDaba = mSkillEntityList.get(tag);
                if(skDaba.getIs_audit().equals("0")){
                    if(AppGlobal.getInstance().getLagStr().equals("zh")){
                        ToastUtil.shortToast(getContext(),"审核后，方能设置技能");
                    }else{
                        ToastUtil.shortToast(getContext(),"Setting after ensorded");
                    }
                }else{
                    Intent intent = new Intent(context, ProSkillSetActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("skDaba", skDaba);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }

            }
        }
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, ProSkillDetailActivity.class);
        SkillEntity.DataBean skDaba = mSkillEntityList.get(position - 1);
        if(Integer.valueOf(skDaba.getType())<29){
            intent.putExtra("type", skDaba.getType());
            intent.putExtra("id", skDaba.getId());
            intent.putExtra("isfromSkill",true);
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        initNetData();
    }

    @Override
    public void changeNewdata() {
        initNetData();
    }

    public void refData() {
        initNetData();
    }


}
