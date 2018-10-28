package com.haiwai.housekeeper.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope006 on 2017/1/6.
 */

public class ProCodeView extends LinearLayout {
    public ImageView im0, im1, im2, im22, im3, im33, im4, im44, im5, im55, im6, im66;
    public TextView tvp1, tvp3, tvp5;
    public LinearLayout llcode00, llcode1, llcode2, llcode3, llcode4, llcode5;
    private Context context;

    public ProCodeView(Context context) {
        this(context, null);
        this.context = context;
    }

    public ProCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public ProCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View.inflate(context, R.layout.pro_code_text_view_layout, this);
        initView();
    }

    public View getNotice() {
        return findViewById(R.id.tv_jd_notice_word);
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
        tvp1 = (TextView) findViewById(R.id.tvp1);
        tvp3 = (TextView) findViewById(R.id.tvp3);
        tvp5 = (TextView) findViewById(R.id.tvp5);

        llcode00 = (LinearLayout) findViewById(R.id.llcode00);
        llcode1 = (LinearLayout) findViewById(R.id.llcode1);
        llcode2 = (LinearLayout) findViewById(R.id.llcode2);
        llcode3 = (LinearLayout) findViewById(R.id.llcode3);
        llcode4 = (LinearLayout) findViewById(R.id.llcode4);
        llcode5 = (LinearLayout) findViewById(R.id.llcode5);
        findViewById(R.id.tv_on_since).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final ConPopBig9View v = new ConPopBig9View(((Activity) getContext()), "");
                v.getIv_diss().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        v.dismiss();
                    }
                });
                v.showPopUpWindow(view);
            }
        });

        findViewById(R.id.tv_visit_door).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final ConPopBig10View view10 = new ConPopBig10View(((Activity) getContext()), "");
                view10.getIv_diss().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        view10.dismiss();
                    }
                });
                view10.showPopUpWindow(view);
            }
        });

    }

    public void setNode(String staticx) {
        if ("0".equals(staticx)) {
            llcode00.setVisibility(GONE);
            llcode1.setVisibility(View.VISIBLE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            llcode5.setVisibility(View.GONE);
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
        } else if ("1".equals(staticx)) {
            llcode00.setVisibility(GONE);
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.VISIBLE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            llcode5.setVisibility(View.GONE);
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
        } else if ("2".equals(staticx)) {
            llcode00.setVisibility(GONE);
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            llcode5.setVisibility(View.VISIBLE);
            im0.setVisibility(View.INVISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
        } else if ("3".equals(staticx)) {
            llcode00.setVisibility(GONE);
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.VISIBLE);
            llcode5.setVisibility(View.GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
            tvp1.setText(R.string.pro_codes_yzb);
            tvp3.setText(R.string.pro_codes_sm);
            tvp5.setText(R.string.pro_codes_fhjg);
        } else if ("7".equals(staticx)) {
            llcode00.setVisibility(GONE);
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.VISIBLE);
            llcode4.setVisibility(View.GONE);
            llcode5.setVisibility(View.GONE);
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
            tvp1.setText(R.string.pro_codes_sm);
            tvp3.setText(R.string.pro_codes_fhjg);
            tvp5.setText(R.string.pro_codes_qrwg);
        } else if ("8".equals(staticx)) {
            llcode00.setVisibility(GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
            tvp1.setText(R.string.pro_codes_sm);
            tvp3.setText(R.string.pro_codes_fhjg);
            tvp5.setText(R.string.pro_codes_qrwg);
            llcode3.setVisibility(View.VISIBLE);
        } else if ("10".equals(staticx)) {
            llcode00.setVisibility(GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
            tvp1.setText(R.string.pro_codes_yzb);
            tvp3.setText(R.string.pro_codes_sm);
            tvp5.setText(R.string.str_end_server);
            findViewById(R.id.llcode134).setVisibility(View.VISIBLE);
        } else if ("9".equals(staticx)) {
            llcode00.setVisibility(GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
            tvp1.setText(R.string.pro_codes_fhjg);
            tvp3.setText(R.string.pro_codes_qrwg);
            tvp5.setText(R.string.pro_codes_dzf);
            findViewById(R.id.llcode112).setVisibility(View.VISIBLE);
        } else if ("11".equals(staticx)) {
            llcode00.setVisibility(GONE);
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
            tvp1.setText(R.string.pro_codes_qrwg);
            tvp3.setText(R.string.pro_codes_dzf);
            tvp5.setText(R.string.pro_codes_dpj);
            findViewById(R.id.llcode113).setVisibility(View.VISIBLE);
        } else if ("4".equals(staticx) || "5".equals(staticx) || "6".equals(staticx)) {
            llcode00.setVisibility(GONE);
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.VISIBLE);
            llcode5.setVisibility(View.GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.INVISIBLE);
            im44.setVisibility(View.VISIBLE);
            im5.setVisibility(View.INVISIBLE);
            im55.setVisibility(View.VISIBLE);
            im6.setVisibility(View.INVISIBLE);
            im66.setVisibility(View.VISIBLE);
            tvp1.setText(R.string.pro_codes_yzb);
            tvp3.setText(R.string.pro_codes_sm);
            tvp5.setText(R.string.pro_codes_fhjg);
        } else if ("101".equals(staticx)) {
            llcode00.setVisibility(VISIBLE);
            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            llcode4.setVisibility(View.GONE);
            llcode5.setVisibility(View.GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);
            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            im4.setVisibility(View.VISIBLE);
            im44.setVisibility(View.INVISIBLE);
            im5.setVisibility(View.VISIBLE);
            im55.setVisibility(View.INVISIBLE);
            im6.setVisibility(View.VISIBLE);
            im66.setVisibility(View.INVISIBLE);
            tvp1.setText(R.string.pro_codes_qrwg);
            tvp3.setText(R.string.pro_codes_dzf);
            tvp5.setText(R.string.pro_codes_re);
        } else if ("102".equals(staticx)) {
            llcode00.setVisibility(VISIBLE);

            llcode1.setVisibility(View.GONE);
            llcode2.setVisibility(View.GONE);
            llcode3.setVisibility(View.GONE);
            im0.setVisibility(View.VISIBLE);
            im1.setVisibility(View.VISIBLE);
            im2.setVisibility(View.VISIBLE);
            im22.setVisibility(View.INVISIBLE);

            im3.setVisibility(View.VISIBLE);
            im33.setVisibility(View.INVISIBLE);
            tvp1.setText(R.string.pro_codes_qrwg);
            tvp3.setText(R.string.status_none);
            tvp5.setVisibility(INVISIBLE);
            im4.setVisibility(INVISIBLE);
            im44.setVisibility(INVISIBLE);
            im55.setVisibility(INVISIBLE);
            im5.setVisibility(INVISIBLE);
            im6.setVisibility(INVISIBLE);
            im66.setVisibility(INVISIBLE);

            TextView txtStatus = (TextView) llcode00.getChildAt(0);
            txtStatus.setText(R.string.status_none);
        }
    }
}
