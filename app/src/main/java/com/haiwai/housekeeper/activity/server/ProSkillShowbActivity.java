package com.haiwai.housekeeper.activity.server;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.entity.TitleItem;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;

/**
 * Created by lyj on 2016/9/26.
 */
public class ProSkillShowbActivity extends BaseProActivity {
    private TextView ib_btn_showb,ib_btn_showb_save;
    private TopViewNormalBar top_showb_bar;
    private TextView tvType;
    private EditText etClass, etAdvantage;
    private String classStr, adavantageStr,type;
    TitleItem titleItem;
    boolean flag = false;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_skill_show_first_layout);
        top_showb_bar = (TopViewNormalBar) findViewById(R.id.top_showb_bar);
        top_showb_bar.setTitle(getString(R.string.skill_adds));
        top_showb_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initView();
    }

    private void initData() {
        flag = getIntent().getBooleanExtra("edit", false);
        if (flag) {
            classStr = getIntent().getStringExtra("classStr");
            adavantageStr = getIntent().getStringExtra("advantageStr");
            type = getIntent().getStringExtra("type");
        } else {
            titleItem = (TitleItem) getIntent().getSerializableExtra("titleItem");
        }

    }

    private void initView() {
        ib_btn_showb_save = (TextView) findViewById(R.id.ib_btn_showb_save);
        ib_btn_showb = (TextView) findViewById(R.id.ib_btn_showb);
        ((TextView) findViewById(R.id.ib_btn_showb1)).setText("");
        ib_btn_showb.setText(getString(R.string.btn_next));
        ib_btn_showb.setOnClickListener(mOnClickListener);
        ib_btn_showb_save.setOnClickListener(mOnClickListener);
        findViewById(R.id.ll_showb_btn).setOnClickListener(mOnClickListener);
        tvType = (TextView) findViewById(R.id.tv_type_id);
        etClass = (EditText) findViewById(R.id.et_class);
        etAdvantage = (EditText) findViewById(R.id.et_advantage);
        if(flag){
            findViewById(R.id.ll_showb_btn).setVisibility(View.GONE);
            etClass.setText(classStr);
            etAdvantage.setText(adavantageStr);
            ib_btn_showb_save.setVisibility(View.VISIBLE);
            ib_btn_showb.setVisibility(View.GONE);
            tvType.setText(AssetsUtils.getSkillName(type,isZhorEn));
        } else {
            tvType.setText(titleItem.getName());
        }
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_showb_bar.getBackView()) {
                finish();
            }else if(v.getId()==R.id.ll_showb_btn){
                checkData();
            } else if (v.getId() == R.id.ib_btn_showb) {
                checkData();
            } else if(v.getId() == R.id.ib_btn_showb_save){//保存设置
                classStr = etClass.getText().toString().trim();
                adavantageStr = etAdvantage.getText().toString().trim();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("classStr",classStr);
                bundle.putString("adavantageStr",adavantageStr);
                intent.putExtra("bundle",bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    };

    private void checkData() {
        classStr = etClass.getText().toString().trim();
        adavantageStr = etAdvantage.getText().toString().trim();
        if (classStr.length() < 20) {
            ToastUtil.shortToast(ProSkillShowbActivity.this, getString(R.string.showa_ti1));
            return;
        }
        if (PlatUtils.getEditStr(etAdvantage)) {
            ToastUtil.shortToast(ProSkillShowbActivity.this, getString(R.string.showa_ti2));
            return;
        }
        Intent intent = new Intent(ProSkillShowbActivity.this, ProSkillShowcActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", titleItem.getType());
        bundle.putString("class", classStr);
        bundle.putString("advantage", adavantageStr);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
}
