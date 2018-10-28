package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

/**
 * Created by ihope006 on 2016/12/17.
 */

public class JzView extends LinearLayout {
    public ImageView iv_empty_icon;
    public TextView tv_mar, tv_pri;
    public LinearLayout ll_jz_layout;
    private String flag;

    private TextView tvStatus;
    private TextView tvPause,tvDelete;

    private String j_static = "";

    private MyHorizontalScrollView hsv;
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public JzView(Context context) {
        this(context, null);
    }

    public JzView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JzView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.jz_layout, this);
        initView();
    }

    public String getJ_static() {
        return j_static;
    }

    private boolean isScroll=false;

    private void initView() {
        tvPause = ((TextView) findViewById(R.id.tv_jz_pause));

        tvDelete = ((TextView) findViewById(R.id.tv_jz_delete));

        tvStatus = ((TextView) findViewById(R.id.tv_jz_status));

        iv_empty_icon = (ImageView) findViewById(R.id.iv_empty_icon);
        tv_mar = (TextView) findViewById(R.id.tv_mar);
        tv_pri = (TextView) findViewById(R.id.tv_pri);
        ll_jz_layout = (LinearLayout) findViewById(R.id.ll_jz_layout);

        hsv = ((MyHorizontalScrollView) findViewById(R.id.hsv_jz_scroll_view));
        LinearLayout.LayoutParams params = ((LinearLayout.LayoutParams) ll_jz_layout.getLayoutParams());
        params.width = getResources().getDisplayMetrics().widthPixels-60;
        ll_jz_layout.setLayoutParams(params);
        hsv.setOnScrollListener(new MyHorizontalScrollView.AddScrollListener() {
                @Override
                public void scrollChange(int i, int j, int k, int x) {
                    if(isScroll){
                        if(getScrollX()<=0){
                            hsv.smoothScrollTo(0,0);
                        }
                    }
                }
        });
        tvDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tvStatus.setVisibility(View.GONE);
                hsv.smoothScrollTo(0,0);
                isScroll = true;
                j_static = "0";
                ll_jz_layout.setBackgroundResource(R.mipmap.bj_jzfw_no);
                iv_empty_icon.setVisibility(View.VISIBLE);
                findViewById(R.id.iv_empty_text).setVisibility(VISIBLE);
            }
        });

        tvPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if("激活".equals(tvPause.getText().toString())){
                    tvPause.setText("暂停");
                    j_static = "2";
                }else{
                    tvPause.setText("激活");
                    j_static = "1";
                }
            }
        });

    }

    private boolean isVis;

    public void setMbVisible(boolean bool) {
        isVis = bool;
        if (bool) {
            isScroll = true;
            tvStatus.setVisibility(View.GONE);
            iv_empty_icon.setVisibility(View.VISIBLE);
            findViewById(R.id.iv_empty_text).setVisibility(VISIBLE);
            ll_jz_layout.setBackgroundResource(R.mipmap.bj_jzfw_no);
        } else {
            iv_empty_icon.setVisibility(View.GONE);
            findViewById(R.id.iv_empty_text).setVisibility(GONE);
            ll_jz_layout.setBackgroundResource(R.mipmap.bj_jzfw_yes);
        }
    }

    public void setMPText(String str1, String str2,String xia,String x_static) {
        tv_mar.setText(TimeUtils.getStr12Time(str1));
        tv_pri.setText("$"+String.format("%.2f",Double.valueOf(str2)));
        if(!isVis){
            tvStatus.setVisibility(View.VISIBLE);
            if(x_static.equals("0")){
                isScroll = true;
                j_static = "0";
                tvStatus.setText("未审核");
            }else if(x_static.equals("1")){
                tvStatus.setText("暂停");
                j_static = "1";
            }else if(x_static.equals("2")){
                tvStatus.setText("进行中");
                j_static = "2";
            }else{
                tvStatus.setText("待审核");
                j_static = "3";
            }
        }
    }

    public LinearLayout getLayout() {
        return ll_jz_layout;
    }

    public ImageView getImg() {
        return iv_empty_icon;
    }

}
