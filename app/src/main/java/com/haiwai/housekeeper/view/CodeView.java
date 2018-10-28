package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.MyApp;

/**
 * Created by ihope006 on 2016/12/29.
 */

public class CodeView extends LinearLayout {
    public ImageView im0, im1, im2, im22, im3, im33, im4, im44, im5, im55, im6, im66;
    public TextView tvp1, tvp3, tvp5;
    public LinearLayout llcode1, llcode2, llcode3, llcode4;
    public TextView tv_tch, tv_xcqr, tv_fwz, tv_dzf;

    public CodeView(Context context) {
        this(context, null);
    }

    public CodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.code_view_layout, this);
        initView();
    }


    public View getView(){
        return findViewById(R.id.iv_desc);
    }
    public void initView() {
        im0 = (ImageView) findViewById(R.id.im0);
        im1 = (ImageView) findViewById(R.id.im1);
        im2 = (ImageView) findViewById(R.id.im2);
        im22 = (ImageView) findViewById(R.id.im22);
        im3 = (ImageView) findViewById(R.id.im3);
        im33 = (ImageView) findViewById(R.id.im33);
        im4 = (ImageView) findViewById(R.id.im4);
        im44 = (ImageView) findViewById(R.id.im44);
        im5 = (ImageView) findViewById(R.id.im5);
        im55 = (ImageView) findViewById(R.id.im55);
        im6 = (ImageView) findViewById(R.id.im6);
        im66 = (ImageView) findViewById(R.id.im66);
        llcode1 = (LinearLayout) findViewById(R.id.llcode1);
        llcode2 = (LinearLayout) findViewById(R.id.llcode2);
        llcode3 = (LinearLayout) findViewById(R.id.llcode3);
        llcode4 = (LinearLayout) findViewById(R.id.llcode4);


        tv_tch = (TextView) findViewById(R.id.tv_tch);
        tv_xcqr = (TextView) findViewById(R.id.tv_xcqr);
        tv_fwz = (TextView) findViewById(R.id.tv_fwz);
        tv_dzf = (TextView) findViewById(R.id.tv_dzf);
        tvp1 = (TextView) findViewById(R.id.tvp1);
        tvp3 = (TextView) findViewById(R.id.tvp3);
        tvp5 = (TextView) findViewById(R.id.tvp5);



        tv_tch.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_xcqr.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_fwz.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_dzf.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tvp1.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tvp3.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tvp5.setTypeface(MyApp.getTf(), Typeface.NORMAL);



    }

    public TextView getTv_tch() {
        return tv_tch;
    }

    public TextView getTv_xcqr() {
        return tv_xcqr;
    }

    public TextView getTv_fwz() {
        return tv_fwz;
    }

    public TextView getTv_dzf() {
        return tv_dzf;
    }


    public void setNode(String staticx) {
        if ("0".equals(staticx)) {
            llcode1.setVisibility(View.VISIBLE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            im0.setVisibility(View.INVISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.INVISIBLE);
            im22.setVisibility(View.VISIBLE);
            im3.setVisibility(View.INVISIBLE);
            im33.setVisibility(View.VISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
//            tvp1 = (TextView) findViewById(R.id.tvp1);
//            tvp3 = (TextView) findViewById(R.id.tvp3);
//            tvp5 = (TextView) findViewById(R.id.tvp5);
        } else if ("1".equals(staticx)) {
            llcode1.setVisibility(View.VISIBLE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            im0.setVisibility(View.INVISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.INVISIBLE);
            im33.setVisibility(View.VISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
        } else if ("2".equals(staticx)) {
            im0.setVisibility(View.INVISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.VISIBLE);
            im44.setVisibility(View.INVISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
        } else if ("3".equals(staticx)) {
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.VISIBLE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.INVISIBLE);
            im33.setVisibility(View.VISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
            tvp1.setText(R.string.code_str1);
            tvp3.setText(R.string.code_str2);
            tvp3.setPadding(0, 0, 0, 0);
            tvp5.setText(R.string.code_str3);
        } else if ("7".equals(staticx)) {
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.INVISIBLE);
            im22.setVisibility(View.VISIBLE);
            im3.setVisibility(View.INVISIBLE);
            im33.setVisibility(View.VISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.INVISIBLE);
            tvp1.setText(R.string.code_str4);
            tvp1.setPadding(0, 0, 0, 0);
            tvp3.setText(R.string.code_str5);
            tvp5.setText(R.string.code_str6);
        } else if ("8".equals(staticx)) {
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.INVISIBLE);
            im33.setVisibility(View.VISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.INVISIBLE);
            tvp1.setText(R.string.code_str4);
            tvp1.setPadding(0, 0, 0, 0);
            tvp3.setText(R.string.code_str5);
            tvp5.setText(R.string.code_str6);
        }else if("10".equals(staticx)){
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.VISIBLE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.INVISIBLE);
            im33.setVisibility(View.VISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.INVISIBLE);
            tvp1.setText(R.string.code_xqhszj);
            tvp1.setPadding(0, 0, 0, 0);
            tvp3.setText(R.string.code_zjsm);
            tvp5.setText(R.string.str_end_server);
            llcode4.setVisibility(View.GONE);
        } else if ("9".equals(staticx)) {
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.VISIBLE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.INVISIBLE);
            im33.setVisibility(View.VISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.INVISIBLE);
            tvp1.setText(R.string.code_str4);
            tvp1.setPadding(0, 0, 0, 0);
            tvp3.setText(R.string.code_str5);
            tvp5.setText(R.string.code_str6);
        } else if ("11".equals(staticx)) {
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.VISIBLE);
            im44.setVisibility(View.INVISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.INVISIBLE);
            tvp1.setText(R.string.code_str4);
            tvp1.setPadding(0, 0, 0, 0);
            tvp3.setText(R.string.code_str5);
            tvp5.setText(R.string.code_str6);
        } else if ("4".equals(staticx) || "5".equals(staticx) || "6".equals(staticx)) {
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.VISIBLE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.INVISIBLE);
            im33.setVisibility(View.VISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
            tvp1.setText(R.string.code_str1);
            tvp3.setText(R.string.code_str2);
            tvp3.setPadding(0, 0, 0, 0);
            tvp5.setText(R.string.code_str3);
        }
    }
}
