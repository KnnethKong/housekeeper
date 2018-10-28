package com.haiwai.housekeeper.view;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
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

public class ZlView extends LinearLayout {
    public ImageView iv_empty_icon;
    public TextView tv_mar, tv_pri;
    public LinearLayout ll_zl_layout;
    private String flag;

    private TextView tvStatus;

    private String z_static = "";

    private Context context;
    private TextView tvPause, tvDelete;

    public String getZ_static() {
        return z_static;
    }

    private MyHorizontalScrollView hsv;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ZlView(Context context) {
        this(context, null);
        this.context = context;
    }

    public ZlView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.zl_layout, this);
        initView();
    }

    public void setMPText(String str1, String str2, String xia, String x_static) {
        tv_mar.setText(TimeUtils.getStr12Time(str1));
        tv_pri.setVisibility(View.GONE);
        Log.i("zl_x_static",x_static);
        if (!isVis) {
            tvStatus.setVisibility(View.VISIBLE);
            if (x_static.equals("0")) {
                isScroll = true;
                z_static = "0";
                tvStatus.setText("");
            } else if (x_static.equals("1")) {
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Suspend");
                    tvPause.setText("Activate");
                }else{
                    tvStatus.setText("暂停");
                    tvPause.setText("激活");
                }
                z_static = "1";
            } else if (x_static.equals("2")) {
//                if(AppGlobal.getInstance().getLagStr().equals("en")){
//                    tvStatus.setText("Running");
//                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        tvStatus.setText("Endorsed");
                    }else{
                        tvStatus.setText("已审核");
                    }

//                    tvStatus.setText("进行中");
//                }
                z_static = "2";
            }else if(x_static.equals("4")){
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Endorsed");
                }else{
                    tvStatus.setText("进行中");
                }
                z_static = "3";
            }else if(x_static.equals("")){
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Endorsed");
                }else{
                    tvStatus.setText("已审核");
                }

//                    tvStatus.setText("进行中");
//                }
                z_static = "2";
            } else {
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Awaiting\nEndorsement");
                }else{
                    tvStatus.setText("待审核");
                }


//                if (AppGlobal.getInstance().getLagStr().equals("en")) {
//                    tvStatus.setText("Awaiting");
//                } else {
//                    tvStatus.setText("待审核");
//                }
                z_static = "3";
            }
        }
    }

    private boolean isScroll = false;

    private void initView() {
        tvPause = ((TextView) findViewById(R.id.tv_zl_pause));
        TextView tvName = ((TextView) findViewById(R.id.tv_service_name));
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            tvName.setText("Rental\nManagement");
        }else{
            tvName.setText("房屋租赁");
        }
        tvDelete = ((TextView) findViewById(R.id.tv_zl_delete));
        tvStatus = ((TextView) findViewById(R.id.tv_zl_status));
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            tvPause.setText("Suspend");
        }else{
            tvPause.setText("暂停");

        }
        iv_empty_icon = (ImageView) findViewById(R.id.iv_empty_icon);
        tv_mar = (TextView) findViewById(R.id.tv_mar);
        tv_pri = (TextView) findViewById(R.id.tv_pri);
        ll_zl_layout = (LinearLayout) findViewById(R.id.ll_zl_layout);
        hsv = ((MyHorizontalScrollView) findViewById(R.id.hsv_zl_scroll_view));
        LinearLayout.LayoutParams params = ((LinearLayout.LayoutParams) ll_zl_layout.getLayoutParams());
        params.width = getResources().getDisplayMetrics().widthPixels - 60;
        ll_zl_layout.setLayoutParams(params);
        hsv.setOnScrollListener(new MyHorizontalScrollView.AddScrollListener() {
            @Override
            public void scrollChange(int i, int j, int k, int x) {
                if (isScroll) {
                    if (getScrollX() <= 0) {
                        hsv.smoothScrollTo(0, 0);
                    }
                }
            }
        });

        tvDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setTitle(getContext().getString(R.string.app_tip)).setMessage(getContext().getString(R.string.zl_de_desc))
                        .setPositiveButton(context.getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tvStatus.setVisibility(View.GONE);
                                isScroll = true;
                                z_static = "0";
                                IMKitService.zMap.put("z_static",z_static);
                                ll_zl_layout.setBackgroundResource(R.mipmap.bj_fwzl_no);
                                iv_empty_icon.setVisibility(View.VISIBLE);
                                findViewById(R.id.iv_empty_text).setVisibility(VISIBLE);
                                hsv.smoothScrollTo(0, 0);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton(context.getString(R.string.message_alert_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        hsv.smoothScrollTo(0, 0);
                    }
                }).create().show();

            }
        });

        tvPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.Builder builder = new CustomDialog.Builder(context);

                if ("激活".equals(tvPause.getText().toString()) || "Activate".equals(tvPause.getText().toString())) {
                    builder.setTitle(getContext().getString(R.string.app_tip)).setMessage(getContext().getString(R.string.zl_jh_desc))
                            .setPositiveButton(context.getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                        tvPause.setText("Suspend");
                                    } else {
                                        tvPause.setText("暂停");
                                    }
                                    z_static = "2";
                                    IMKitService.zMap.put("z_static",z_static);
                                    tvStatus.setVisibility(View.VISIBLE);
//                                    tvStatus.setText("待审核");
                                    hsv.smoothScrollTo(0, 0);
                                    dialogInterface.dismiss();
                                }
                            }).setNegativeButton(context.getString(R.string.message_alert_no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            hsv.smoothScrollTo(0, 0);
                        }
                    }).create().show();

                } else {
                    builder.setTitle(getContext().getString(R.string.app_tip)).setMessage(getContext().getString(R.string.zl_stop_desc))
                            .setPositiveButton(context.getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                        tvPause.setText("Activate");
                                        tvStatus.setText("Awaiting");
                                    } else {
                                        tvPause.setText("激活");
                                        tvStatus.setText("待审核");
                                    }
                                    z_static = "1";
                                    IMKitService.zMap.put("z_static",z_static);
                                    tvStatus.setVisibility(View.VISIBLE);

                                    dialogInterface.dismiss();
                                }
                            }).setNegativeButton(context.getString(R.string.message_alert_no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();


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
            ll_zl_layout.setBackgroundResource(R.mipmap.bj_fwzl_no);
        } else {
            iv_empty_icon.setVisibility(View.GONE);
            findViewById(R.id.iv_empty_text).setVisibility(GONE);
            ll_zl_layout.setBackgroundResource(R.mipmap.bj_fwzl_yes);
        }
    }

    public LinearLayout getLayout() {
        return ll_zl_layout;
    }

    public ImageView getImg() {
        return iv_empty_icon;
    }
}
