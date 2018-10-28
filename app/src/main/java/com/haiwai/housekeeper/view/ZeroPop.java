package com.haiwai.housekeeper.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope006 on 2017/2/27.
 */

public class ZeroPop extends PopupWindow {
    public Context mContext;
    private LayoutInflater mInflater;
    private View popView;
    public LinearLayout ll_pay_cancel, ll_pay_ok;
    public TextView tv_pay_smf, tv_pay_count;
  //  public CheckBox cb_jcf;

    public ZeroPop(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        initView(mContext);
    }

    private void initView(Context context) {
        popView = mInflater.inflate(R.layout.zero_pop_up_layout, null);
        init(popView);
        this.setContentView(popView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
//        ColorDrawable cd = new ColorDrawable(0000000000);
//        this.setBackgroundDrawable(cd);
        this.setAnimationStyle(R.style.AlphaAnimation);
    }

    private void init(View popView) {
        ll_pay_cancel = (LinearLayout) popView.findViewById(R.id.ll_pay_cancel);
        ll_pay_ok = (LinearLayout) popView.findViewById(R.id.ll_pay_ok);
        tv_pay_smf = (TextView) popView.findViewById(R.id.tv_pay_smf);
        //tv_pay_jcf = (TextView) popView.findViewById(R.id.tv_pay_jcf);
        tv_pay_count = (TextView) popView.findViewById(R.id.tv_pay_count);
        //cb_jcf = (CheckBox) popView.findViewById(R.id.cb_jcf);
    }

//    public void setJcf(String str) {
//        tv_pay_jcf.setText(str);
//    }

    public void setSmf(String str) {
        tv_pay_smf.setText(str);
    }

    public void setHj(String str) {
        tv_pay_count.setText(str);
    }

//    public String getJcf() {
//
//        return tv_pay_jcf.getText().toString().trim();
//    }

    public String getSmf() {
        return tv_pay_smf.getText().toString().trim();
    }

    public String getHj() {
        return tv_pay_count.getText().toString().trim();
    }

//    public CheckBox getCb_jcf() {
//        return cb_jcf;
//    }

    public LinearLayout getLlOk() {
        return ll_pay_ok;
    }

    public LinearLayout getLlCancel() {
        return ll_pay_cancel;
    }

    public void setOnLLOKClickListener(View.OnClickListener onClickListener) {
        ll_pay_ok.setOnClickListener(onClickListener);
    }

    public void setOnLLCancelClickListener(View.OnClickListener onClickListener) {
        ll_pay_cancel.setOnClickListener(onClickListener);
    }

    public void showPopUpWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }
}
