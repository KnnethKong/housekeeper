package com.haiwai.housekeeper.view;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.CustomDialog;

/**
 * Created by ihope006 on 2016/12/17.
 */

public class TyView extends LinearLayout {
    public ImageView iv_empty_icon;
    public TextView tv_mar, tv_pri;
    public LinearLayout ll_yy_layout;
    private String flag;
    private LinearLayout ll_yy_content;

    private TextView tvStatus;

    private TextView tvPause,tvDelete;

    private String y_static = "";

    private MyHorizontalScrollView hsv;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public TyView(Context context) {
        this(context, null);
    }

    public TyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.ty_layout, this);
        initView();
    }

//    public String getY_static() {
//        return y_static;
//    }

    private boolean isScroll=false;

    private void initView() {
        tvPause = ((TextView) findViewById(R.id.tv_ty_pause));

        tvDelete = ((TextView) findViewById(R.id.tv_ty_delete));
        TextView tvName = ((TextView) findViewById(R.id.tv_ty_name));
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            tvName.setText("Landscaping\n＆ Lawn Care");
        }else{
            tvName.setText("庭院管理");
        }
        tvStatus = ((TextView) findViewById(R.id.tv_ty_status));
        iv_empty_icon = (ImageView) findViewById(R.id.iv_empty_icon);
        tv_mar = (TextView) findViewById(R.id.tv_mar);
        tv_pri = (TextView) findViewById(R.id.tv_pri);
        ll_yy_layout = (LinearLayout) findViewById(R.id.ll_yy_layout);

        hsv = ((MyHorizontalScrollView) findViewById(R.id.hsv_ty_scroll_view));
        LinearLayout.LayoutParams params = ((LinearLayout.LayoutParams) ll_yy_layout.getLayoutParams());
        params.width = getResources().getDisplayMetrics().widthPixels-60;
        ll_yy_layout.setLayoutParams(params);
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
                                y_static = "0";
                                IMKitService.yMap.put("y_static",y_static);
                                ll_yy_layout.setBackgroundResource(R.mipmap.bj_yygl_no);
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
                    tvPause.setText("暂停");if(AppGlobal.getInstance().getLagStr().equals("en")){
                        tvPause.setText("Suspend");
                    }else{
                        tvPause.setText("暂停");
                    }
                    y_static = "2";
                    IMKitService.yMap.put("y_static",y_static);
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        tvPause.setText("Activate");
                    }else{
                        tvPause.setText("激活");
                    }
                    y_static = "1";
                    IMKitService.yMap.put("y_static",y_static);
                }
            }
        });


//        ll_yy_content = ((LinearLayout) findViewById(R.id.ll_note_content));
//
//        WindowManager manager = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE));
//        LinearLayout.LayoutParams params  = new LinearLayout.LayoutParams(manager.getDefaultDisplay().getWidth()-32, LayoutParams.WRAP_CONTENT);
//        ll_yy_content.setLayoutParams(params);
    }


//    private float oldX,newX;
//    private long downTime;
//
//    private void initListener(){
//        ll_yy_layout.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch(motionEvent.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        downTime = System.currentTimeMillis();
//                        oldX = view.getX();
//                        ToastUtil.shortToast(getContext(),oldX+"");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        ll_yy_layout.layout((int)(view.getX()-oldX),0,(int)(view.getX()-oldX),0);
//                        if(System.currentTimeMillis()-downTime<100){
//                            return true;
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        ll_yy_layout.layout((int)(view.getX()-oldX),0,(int)(view.getX()-oldX),0);
//                        break;
//                }
//                return false;
//            }
//        });
//    }

    private boolean isVis;

    public void setMbVisible(boolean bool) {
        isVis = bool;
        if (bool) {
            isScroll = true;
            tvStatus.setVisibility(View.GONE);
            iv_empty_icon.setVisibility(View.VISIBLE);
            findViewById(R.id.iv_empty_text).setVisibility(VISIBLE);
            ll_yy_layout.setBackgroundResource(R.mipmap.bj_yygl_no);
        } else {
            iv_empty_icon.setVisibility(View.GONE);
            findViewById(R.id.iv_empty_text).setVisibility(GONE);
            ll_yy_layout.setBackgroundResource(R.mipmap.bj_yygl_yes);
        }
    }

    public void setMPText(String str1, String str2,String xia,String x_static) {
        tv_mar.setText(TimeUtils.getStr12Time(str1));
        tv_pri.setText("$"+String.format("%.2f",Double.valueOf(str2)));
//        Log.i("ty_x_static",x_static);
        if(!isVis){
            tvStatus.setVisibility(View.VISIBLE);
            if(x_static.equals("0")){
                isScroll = true;
                y_static = "0";
                tvStatus.setText("");
            }
            else if(x_static.equals("1")){
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Suspend");
                    tvPause.setText("Activate");
                }else{
                    tvStatus.setText("暂停");
                    tvPause.setText("激活");
                }

                y_static = "1";
            }else if(x_static.equals("2")){
                y_static = "2";
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Endorsed");
                }else{
                    tvStatus.setText("已审核");
                }

            }else if(x_static.equals("")){//推送跳转此界面
                y_static = "2";
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Endorsed");
                }else{
                    tvStatus.setText("已审核");
                }

            }else if(x_static.equals("3")){
                y_static = "0";
            }
            else{
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    tvStatus.setText("Awaiting\nendorsement");
                }else{
                    tvStatus.setText("待审核");
                }

//                tvStatus.setText("待审核");
                y_static = "3";
            }
        }
    }

    public LinearLayout getLayout() {
        return ll_yy_layout;
    }

    public ImageView getImg() {
        return iv_empty_icon;
    }
}
