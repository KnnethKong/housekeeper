package com.haiwai.housekeeper.activity.server;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.view.TopViewNormalBar;

public class ProMoneyStrategyActivity extends BaseProActivity implements View.OnClickListener {
    private TopViewNormalBar mStrategyBar;
    private LinearLayout ibBtnSelect;
    private boolean isShow = false;
    private LinearLayout ll_content_layout;
    TextView tv1a, tv1c, tv2a, tv2c, tv3a, tv3c, tv4a, tv4c, tv5a, tv5c, tv6a, tv6c, tv7a, tv7c, tv8a, tv8c, tv9a, tv9c, tv10a, tv10c, tv11a, tv11c;
    boolean is1 = false, is2 = false, is3 = false, is4 = false, is5 = false, is6 = false, is7 = false, is8 = false, is9 = false, is10 = false, is11 = false;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_money_strategy);
        mStrategyBar = (TopViewNormalBar) findViewById(R.id.top_strategy_bar);
        mStrategyBar.setTitle(getString(R.string.skill_right_name));
        mStrategyBar.setOnBackListener(this);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
    }

    private void initView() {
        ll_content_layout = (LinearLayout) findViewById(R.id.ll_content_layout);
        ibBtnSelect = (LinearLayout) findViewById(R.id.ib_btn_select);
        ibBtnSelect.setOnClickListener(this);
        tv1c = (TextView) findViewById(R.id.tv_content_1);
        tv1a = (TextView) findViewById(R.id.tv_title_arrow_1);
        tv1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is1) {
                    is1 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv1a.setCompoundDrawables(null, null, drawable, null);
                    tv1c.setVisibility(View.GONE);
                } else {
                    is1 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv1a.setCompoundDrawables(null, null, drawable, null);
                    tv1c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv2c = (TextView) findViewById(R.id.tv_content_2);
        tv2a = (TextView) findViewById(R.id.tv_title_arrow_2);
        tv2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is2) {
                    is2 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv2a.setCompoundDrawables(null, null, drawable, null);
                    tv2c.setVisibility(View.GONE);
                } else {
                    is2 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv2a.setCompoundDrawables(null, null, drawable, null);
                    tv2c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv3c = (TextView) findViewById(R.id.tv_content_3);
        tv3a = (TextView) findViewById(R.id.tv_title_arrow_3);
        tv3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is3) {
                    is3 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv3a.setCompoundDrawables(null, null, drawable, null);
                    tv3c.setVisibility(View.GONE);
                } else {
                    is3 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv3a.setCompoundDrawables(null, null, drawable, null);
                    tv3c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv4c = (TextView) findViewById(R.id.tv_content_4);
        tv4a = (TextView) findViewById(R.id.tv_title_arrow_4);
        tv4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is4) {
                    is4 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv4a.setCompoundDrawables(null, null, drawable, null);
                    tv4c.setVisibility(View.GONE);
                } else {
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);
                    is4 = true;

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv4a.setCompoundDrawables(null, null, drawable, null);
                    tv4c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv5c = (TextView) findViewById(R.id.tv_content_5);
        tv5a = (TextView) findViewById(R.id.tv_title_arrow_5);
        tv5a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is5) {
                    is5 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv5a.setCompoundDrawables(null, null, drawable, null);
                    tv5c.setVisibility(View.GONE);
                } else {
                    is5 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv5a.setCompoundDrawables(null, null, drawable, null);
                    tv5c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv6c = (TextView) findViewById(R.id.tv_content_6);
        tv6a = (TextView) findViewById(R.id.tv_title_arrow_6);
        tv6a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is6) {
                    is6 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv6a.setCompoundDrawables(null, null, drawable, null);
                    tv6c.setVisibility(View.GONE);
                } else {
                    is6 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv6a.setCompoundDrawables(null, null, drawable, null);
                    tv6c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv7c = (TextView) findViewById(R.id.tv_content_7);
        tv7a = (TextView) findViewById(R.id.tv_title_arrow_7);
        tv7a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is7) {
                    is7 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv7a.setCompoundDrawables(null, null, drawable, null);
                    tv7c.setVisibility(View.GONE);
                } else {
                    is7 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv7a.setCompoundDrawables(null, null, drawable, null);
                    tv7c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv8c = (TextView) findViewById(R.id.tv_content_8);
        tv8a = (TextView) findViewById(R.id.tv_title_arrow_8);
        tv8a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is8) {
                    is8 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv8a.setCompoundDrawables(null, null, drawable, null);
                    tv8c.setVisibility(View.GONE);
                } else {
                    is8 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv8a.setCompoundDrawables(null, null, drawable, null);
                    tv8c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv9c = (TextView) findViewById(R.id.tv_content_9);
        tv9a = (TextView) findViewById(R.id.tv_title_arrow_9);
        tv9a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is9) {
                    is9 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv9a.setCompoundDrawables(null, null, drawable, null);
                    tv9c.setVisibility(View.GONE);
                } else {
                    is9 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv9a.setCompoundDrawables(null, null, drawable, null);
                    tv9c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv10c = (TextView) findViewById(R.id.tv_content_10);
        tv10a = (TextView) findViewById(R.id.tv_title_arrow_10);
        tv10a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is10) {
                    is10 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv10a.setCompoundDrawables(null, null, drawable, null);
                    tv10c.setVisibility(View.GONE);
                } else {
                    is10 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv10a.setCompoundDrawables(null, null, drawable, null);
                    tv10c.setVisibility(View.VISIBLE);
                }
            }
        });
        tv11c = (TextView) findViewById(R.id.tv_content_11);
        tv11a = (TextView) findViewById(R.id.tv_title_arrow_11);
        tv11a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is11) {
                    is11 = false;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv11a.setCompoundDrawables(null, null, drawable, null);
                    tv11c.setVisibility(View.GONE);
                } else {
                    is11 = true;
                    Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv11a.setCompoundDrawables(null, null, drawable, null);
                    tv11c.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == mStrategyBar.getBackView()) {
            finish();
        } else if (v.getId() == R.id.ib_btn_select) {
            if (!isShow) {
                isShow = true;
                ((TextView) findViewById(R.id.tv_expand)).setText(getString(R.string.need_order_hide));
                ((TextView) findViewById(R.id.tv_expand)).setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.mipmap.arrow_up_white),null);
                is1 = true;
                is2 = true;
                is3 = true;
                is4 = true;
                is5 = true;
                is6 = true;
                is7 = true;
                is8 = true;
                is9 = true;
                is10 = true;
                is11 = true;
                Drawable drawable = getResources().getDrawable(R.mipmap.pro_oder_arrow_up);

                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv1a.setCompoundDrawables(null, null, drawable, null);
                tv2a.setCompoundDrawables(null, null, drawable, null);
                tv3a.setCompoundDrawables(null, null, drawable, null);
                tv4a.setCompoundDrawables(null, null, drawable, null);
                tv5a.setCompoundDrawables(null, null, drawable, null);
                tv6a.setCompoundDrawables(null, null, drawable, null);
                tv7a.setCompoundDrawables(null, null, drawable, null);
                tv8a.setCompoundDrawables(null, null, drawable, null);
                tv9a.setCompoundDrawables(null, null, drawable, null);
                tv10a.setCompoundDrawables(null, null, drawable, null);
                tv11a.setCompoundDrawables(null, null, drawable, null);
                tv1c.setVisibility(View.VISIBLE);
                tv2c.setVisibility(View.VISIBLE);
                tv3c.setVisibility(View.VISIBLE);
                tv4c.setVisibility(View.VISIBLE);
                tv5c.setVisibility(View.VISIBLE);
                tv6c.setVisibility(View.VISIBLE);
                tv7c.setVisibility(View.VISIBLE);
                tv8c.setVisibility(View.VISIBLE);
                tv9c.setVisibility(View.VISIBLE);
                tv10c.setVisibility(View.VISIBLE);
                tv11c.setVisibility(View.VISIBLE);
            } else {
                isShow = false;
                ((TextView) findViewById(R.id.tv_expand)).setText(getString(R.string.need_order_expand));
                ((TextView) findViewById(R.id.tv_expand)).setCompoundDrawablesWithIntrinsicBounds(null,null,getResources().getDrawable(R.mipmap.arrow_down_white),null);
                is1 = false;
                is2 = false;
                is3 = false;
                is4 = false;
                is5 = false;
                is6 = false;
                is7 = false;
                is8 = false;
                is9 = false;
                is10 = false;
                is11 = false;
                Drawable drawable = getResources().getDrawable(R.mipmap.pro_order_arrow_down);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv1a.setCompoundDrawables(null, null, drawable, null);
                tv2a.setCompoundDrawables(null, null, drawable, null);
                tv3a.setCompoundDrawables(null, null, drawable, null);
                tv4a.setCompoundDrawables(null, null, drawable, null);
                tv5a.setCompoundDrawables(null, null, drawable, null);
                tv6a.setCompoundDrawables(null, null, drawable, null);
                tv7a.setCompoundDrawables(null, null, drawable, null);
                tv8a.setCompoundDrawables(null, null, drawable, null);
                tv9a.setCompoundDrawables(null, null, drawable, null);
                tv10a.setCompoundDrawables(null, null, drawable, null);
                tv11a.setCompoundDrawables(null, null, drawable, null);
                tv1c.setVisibility(View.GONE);
                tv2c.setVisibility(View.GONE);
                tv3c.setVisibility(View.GONE);
                tv4c.setVisibility(View.GONE);
                tv5c.setVisibility(View.GONE);
                tv6c.setVisibility(View.GONE);
                tv7c.setVisibility(View.GONE);
                tv8c.setVisibility(View.GONE);
                tv9c.setVisibility(View.GONE);
                tv10c.setVisibility(View.GONE);
                tv11c.setVisibility(View.GONE);
            }
        }
    }
}
