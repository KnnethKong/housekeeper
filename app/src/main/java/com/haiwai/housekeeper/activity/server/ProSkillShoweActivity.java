package com.haiwai.housekeeper.activity.server;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.earnings.MyEarningsActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.SkillProItem;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PaseProUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SetUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.SkillSetItemView;
import com.haiwai.housekeeper.view.SkillSetTitleView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyj on 2016/9/26.
 */
public class ProSkillShoweActivity extends BaseProActivity {
    private TopViewNormalBar top_showe_bar;
    private TextView tvBtnIgnore;
    private Button btnConfirm;
    private LinearLayout ll_content_a_skill;
    private Map<String, String> map = new LinkedHashMap<>();
    User user;
    private SkillProItem mSkillProItem;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_skill_show_fourth_layout);
        top_showe_bar = (TopViewNormalBar) findViewById(R.id.top_showe_bar);
        top_showe_bar.setTitle(getString(R.string.skill_adds));
        top_showe_bar.setOnBackListener(mOnClickListener);
        initData();
        initView();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        String type = getIntent().getStringExtra("type");
        if (user != null) {
            map.put("uid", user.getUid());
        }
        mSkillProItem = SetUtils.getSkillProItem(ProSkillShoweActivity.this, Integer.valueOf(type));
        if (mSkillProItem.getProCns().size() == 0 || mSkillProItem.getProEns().size() == 0) {
            Intent intent = new Intent(ProSkillShoweActivity.this, ProMainActivity.class);
            intent.putExtra("skill", "123");
            startActivity(intent);
        }
        map.put("type", type);
    }

    private void initView() {
        tvBtnIgnore = (TextView) findViewById(R.id.tv_btn_ignore);
        btnConfirm = (Button) findViewById(R.id.ib_btn_confirm_show);
        tvBtnIgnore.setOnClickListener(mOnClickListener);
        btnConfirm.setOnClickListener(mOnClickListener);
        ll_content_a_skill = (LinearLayout) findViewById(R.id.ll_content_a_skill);
        if (mSkillProItem != null) {
            if ("zh".equalsIgnoreCase(isZhorEn)) {
                List<SkillProItem.ProCn> proCns = mSkillProItem.getProCns();
                if (proCns != null) {
                    for (int i = 0; i < proCns.size(); i++) {
                        SkillSetTitleView setTitleView = new SkillSetTitleView(ProSkillShoweActivity.this);
                        setTitleView.setTitleText(proCns.get(i).getProtitlecn());
                        ll_content_a_skill.addView(setTitleView);
                        List<String> proanscn = proCns.get(i).getProanscn();
                        if (proanscn != null) {
                            for (int j = 0; j < proanscn.size(); j++) {
                                SkillSetItemView skillView = new SkillSetItemView(ProSkillShoweActivity.this);
                                skillView.setLeftText(proanscn.get(j));
                                ll_content_a_skill.addView(skillView);
                            }
                        }
                    }
                }
            } else if ("en".equalsIgnoreCase(isZhorEn)) {
                List<SkillProItem.ProEn> proEns = mSkillProItem.getProEns();
                mSkillProItem.getProEns();
                if (proEns != null) {
                    for (int i = 0; i < proEns.size(); i++) {
                        SkillSetTitleView setTitleView = new SkillSetTitleView(ProSkillShoweActivity.this);
                        setTitleView.setTitleText(proEns.get(i).getProtitleen());
                        ll_content_a_skill.addView(setTitleView);
                        List<String> proansen = proEns.get(i).getProansen();
                        if (proansen != null) {
                            for (int j = 0; j < proansen.size(); j++) {
                                SkillSetItemView skillView = new SkillSetItemView(ProSkillShoweActivity.this);
                                skillView.setLeftText(proansen.get(j));
                                ll_content_a_skill.addView(skillView);
                            }
                        }
                    }
                }
            }

        }
        for (int i = 0; i < ll_content_a_skill.getChildCount(); i++) {
            if (ll_content_a_skill.getChildAt(i) instanceof SkillSetItemView) {
                SkillSetItemView skillView = (SkillSetItemView) ll_content_a_skill.getChildAt(i);
                final int finalI = i;
                skillView.getView().setChecked(true);
                String str = "wen" + finalI;
                String value = "1";
                map.put(str, value);
                skillView.getView().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            ToastUtil.shortToast(ProSkillShoweActivity.this, getString(R.string.showe_ti_open));
                            String str = "wen" + finalI;
                            String value = "1";
                            map.put(str, value);
                        } else {
                            ToastUtil.shortToast(ProSkillShoweActivity.this, getString(R.string.showe_ti_close));
                            String str = "wen" + finalI;
                            map.remove(str);
                        }
                    }
                });
            }
        }
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_showe_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.tv_btn_ignore) {
                Intent intent = new Intent(ProSkillShoweActivity.this, ProMainActivity.class);
                intent.putExtra("skill", "123");
                startActivity(intent);
            } else if (v.getId() == R.id.ib_btn_confirm_show) {
                LoadDialog.showProgressDialog(ProSkillShoweActivity.this);
                map.put("secret_key", SPUtils.getString(ProSkillShoweActivity.this, "secret", ""));
                map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProSkillShoweActivity.this, Contants.skill_save, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            LoadDialog.closeProgressDialog();
                            ToastUtil.shortToast(ProSkillShoweActivity.this, getString(R.string.showe_ti_set));
                            dialogShow();

                        } else {
                            LoadDialog.closeProgressDialog();
                            ToastUtil.shortToast(ProSkillShoweActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
            }
        }
    };

    private void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProSkillShoweActivity.this);
        LayoutInflater inflater = LayoutInflater.from(ProSkillShoweActivity.this);
        View v = inflater.inflate(R.layout.dialog_add, null);
        TextView content = (TextView) v.findViewById(R.id.dialog_content);
        Button btn_sure = (Button) v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = (Button) v.findViewById(R.id.dialog_btn_cancel);
        //builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        content.setText(R.string.dialog);
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(ProSkillShoweActivity.this, MyEarningsActivity.class);
                intent.putExtra("isJump", "123");
                startActivity(intent);
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                Intent intent = new Intent(ProSkillShoweActivity.this, ProMainActivity.class);
                intent.putExtra("skill", "123");
                startActivity(intent);
                finish();
            }
        });
    }

}
