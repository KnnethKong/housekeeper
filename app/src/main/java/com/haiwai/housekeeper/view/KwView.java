package com.haiwai.housekeeper.view;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.widget.CustomDialog;

/**
 * Created by ihope006 on 2016/12/17.
 */

public class KwView extends LinearLayout {
    public ImageView iv_empty_icon;
    public TextView tv_mar, tv_pri;
    public LinearLayout ll_kw_layout,yuanjia;
    private String flag;

    private MyHorizontalScrollView hsv;
    private TextView tvStatus;

    private TextView tvPause,tvDelete;
    private boolean isScroll=false;

    private String k_static = "";

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public KwView(Context context) {
        this(context, null);
    }

    public KwView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KwView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.kw_layout, this);
        initView();
    }

    public void setMPText(String str1, String str2,String xia,String x_static) {
        tv_mar.setText(TimeUtils.getStr12Time(str1));
        tv_pri.setText("$"+String.format("%.2f",Double.valueOf(str2)));
        if(!isVis){
            tvStatus.setVisibility(View.VISIBLE);
            if(x_static.equals("0")){
                isScroll = true;
                k_static = "0";
                tvStatus.setText("");
            }else if(x_static.equals("1")){
                yuanjia.setVisibility(VISIBLE);
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Suspend");
                    tvPause.setText("Activate");
                }else{
                    tvStatus.setText("暂停");
                    tvPause.setText("激活");
                }
                k_static = "1";
            }else if(x_static.equals("2")){
                yuanjia.setVisibility(VISIBLE);
                k_static = "2";
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Endorsed");
                }else{
                    tvStatus.setText("已审核");
                }

//                tvStatus.setText("进行中");
            }else if(x_static.equals("")){
                yuanjia.setVisibility(VISIBLE);
                k_static = "2";
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Endorsed");
                }else{
                    tvStatus.setText("已审核");
                }
            }else{
                yuanjia.setVisibility(VISIBLE);
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Awaiting\nendorsement");
                }else{
                    tvStatus.setText("待审核");
                }

                k_static = "3";
            }
        }
    }

    public String getK_static() {
        return k_static;
    }

    private void initView() {

        tvPause = ((TextView) findViewById(R.id.tv_kw_pause));

        tvDelete = ((TextView) findViewById(R.id.tv_kw_delete));


        tvStatus = ((TextView) findViewById(R.id.tv_kw_status));
        iv_empty_icon = (ImageView) findViewById(R.id.iv_empty_icon);
        tv_mar = (TextView) findViewById(R.id.tv_mar);
        tv_pri = (TextView) findViewById(R.id.tv_pri);
        yuanjia= (LinearLayout) findViewById(R.id.yuanjia);
        ll_kw_layout = (LinearLayout) findViewById(R.id.ll_kw_layout);

        hsv = ((MyHorizontalScrollView) findViewById(R.id.hsv_kw_scroll_view));
        LinearLayout.LayoutParams params = ((LinearLayout.LayoutParams) ll_kw_layout.getLayoutParams());
        params.width = getResources().getDisplayMetrics().widthPixels-60;
        ll_kw_layout.setLayoutParams(params);
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


                CustomDialog.Builder customBuilder = new CustomDialog.Builder(getContext());
                customBuilder.setTitle(getContext().getString(R.string.app_tip)).setMessage(getContext().getString(R.string.delete_service_desc)).setPositiveButton(getContext().getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                tvStatus.setVisibility(View.GONE);
                                hsv.smoothScrollTo(0,0);
                                isScroll = true;
                                k_static = "0";
                                IMKitService.kMap.put("k_static",k_static);
                                ll_kw_layout.setBackgroundResource(R.mipmap.bj_kwgl_no);
                                iv_empty_icon.setVisibility(View.VISIBLE);
                                findViewById(R.id.iv_empty_text).setVisibility(VISIBLE);
                            }
                        })
                        .setNegativeButton(getContext().getString(R.string.message_alert_no),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .create().show();


            }
        });
        tvPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if("激活".equals(tvPause.getText().toString())||"Activate".equals(tvPause.getText().toString())){
                    tvPause.setText("暂停");tvPause.setText("暂停");
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        tvPause.setText("Suspend");
                    }else{
                        tvPause.setText("暂停");
                    }
                    k_static = "2";
                    IMKitService.kMap.put("k_static",k_static);
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        tvPause.setText("Activate");
                    }else{
                        tvPause.setText("激活");
                    }

                    k_static = "1";
                    IMKitService.kMap.put("k_static",k_static);
                }
            }
        });
    }

    public TextView getPauseView(){
        return tvPause;
    }

    private boolean isVis;
    public void  setMbVisible(boolean bool) {
        isVis =bool;
        if (bool) {
            isScroll = true;
            tvStatus.setVisibility(View.GONE);
            iv_empty_icon.setVisibility(View.VISIBLE);
            findViewById(R.id.iv_empty_text).setVisibility(VISIBLE);
            ll_kw_layout.setBackgroundResource(R.mipmap.bj_kwgl_no);
        } else {
            iv_empty_icon.setVisibility(View.GONE);
            findViewById(R.id.iv_empty_text).setVisibility(GONE);
            ll_kw_layout.setBackgroundResource(R.mipmap.bj_kwgl_yes);
        }
    }

    public LinearLayout getLayout() {
        return ll_kw_layout;
    }

    public ImageView getImg() {
        return iv_empty_icon;
    }

}
