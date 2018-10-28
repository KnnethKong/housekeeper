package com.haiwai.housekeeper.activity.server;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;

public class ProOrderedDetailActivity extends BaseProActivity {
    private TopViewNormalBar top_ordered_bar;
    private TextView tvBtnCancelOrder, tvBtnLastPrice;
    //-------------身份部分----------------------
    private View cardView;
    //-------------价格部分----------------------
    private View priceView;
    private ImageButton priceModifyBtn;
    //-------------内容部分----------------------
    private View contentView;
    private ImageButton contentStretchBtn;
    private LinearLayout contentStretchLayout;

    private boolean isStretch = false;

    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_ordered_detail);
        top_ordered_bar = (TopViewNormalBar) findViewById(R.id.top_ordered_bar);
        top_ordered_bar.setTitle(getString(R.string.or_detial));
        top_ordered_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
    }

    private void initView() {
        cardView = findViewById(R.id.include_cardView);
        priceView = findViewById(R.id.include_priceView);
        contentView = findViewById(R.id.include_contentView);
        initCardView(cardView);
        initPriceView(priceView);
        initContentView(contentView);
        tvBtnCancelOrder = (TextView) findViewById(R.id.tv_btn_cancel_order);
        tvBtnCancelOrder.setOnClickListener(mOnClickListener);
        tvBtnLastPrice = (TextView) findViewById(R.id.tv_btn_last_price);
        tvBtnLastPrice.setOnClickListener(mOnClickListener);

    }

    private void initCardView(View cardView) {

    }

    private void initPriceView(View priceView) {
        priceModifyBtn = (ImageButton) priceView.findViewById(R.id.ib_modify_price_btn);
        priceModifyBtn.setOnClickListener(mOnClickListener);
    }

    private void initContentView(View contentView) {
        contentStretchBtn = (ImageButton) contentView.findViewById(R.id.ib_btn_stretch);
        contentStretchBtn.setOnClickListener(mOnClickListener);
        contentStretchLayout = (LinearLayout) contentView.findViewById(R.id.ll_layout_stretch);

    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_ordered_bar.getBackView()) {
                finish();
            } else if (v.getId() == R.id.tv_btn_cancel_order) {
                ToastUtil.shortToast(getApplicationContext(), getString(R.string.cancel_order));
            } else if (v.getId() == R.id.tv_btn_last_price) {
                ToastUtil.shortToast(getApplicationContext(), getString(R.string.finish_price));
            }
            //价格部分事件处理
            else if (v.getId() == R.id.ib_modify_price_btn) {
                ToastUtil.shortToast(getApplicationContext(), getString(R.string.modi_price));
            }
            //内容事件处理
            else if (v.getId() == R.id.ib_btn_stretch) {
                if (!isStretch) {
                    isStretch = true;
                    contentStretchLayout.setVisibility(View.GONE);
                } else {
                    isStretch = false;
                    contentStretchLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    };

}
