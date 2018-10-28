package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope10 on 2016/10/10.
 */
public class SkillCertificateView extends LinearLayout {
    public TextView tvSkillName, tvState;
    public ImageView imgArrow;
    public TextView imgBtn;
    public LinearLayout llSkillLayout;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SkillCertificateView(Context context) {
        this(context, null);
    }

    public SkillCertificateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkillCertificateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.pro_skill_certificate_layout, this);
        initView();
    }

    private void initView() {
        tvSkillName = (TextView) findViewById(R.id.tv_skill_name);
        tvState = (TextView) findViewById(R.id.tv_state);
        imgArrow = (ImageView) findViewById(R.id.iv_arrow);
        imgBtn = (TextView) findViewById(R.id.btn_add_skill);
        llSkillLayout = (LinearLayout) findViewById(R.id.skills_content_layout);
    }

    public void setOnContentClickListener(OnClickListener mOnClickListener) {
        imgArrow.setOnClickListener(mOnClickListener);
        imgBtn.setOnClickListener(mOnClickListener);
    }

    public ImageView getArrow(){
        return imgArrow;
    }

    public TextView getAddBtn(){
        return imgBtn;
    }

    public void switchState(boolean flag) {//true，内容显示
        if (flag) {
            imgArrow.setImageResource(R.mipmap.pro_oder_arrow_up);
            llSkillLayout.setVisibility(View.VISIBLE);
            imgBtn.setVisibility(View.VISIBLE);
        } else {
            imgArrow.setImageResource(R.mipmap.pro_order_arrow_down);
            llSkillLayout.setVisibility(View.GONE);
            imgBtn.setVisibility(View.GONE);
        }
    }

    public void setTxtName(String str) {
        tvSkillName.setText(str);
    }

    public void setTxtState(String str) {
        tvState.setText(str);
    }

    public LinearLayout getContentLayout() {
        return llSkillLayout;
    }
}
