package com.haiwai.housekeeper.activity.server;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.ProServiceView;
import com.haiwai.housekeeper.view.TopViewNormalBar;

/**
 * Created by lyj on 2016/9/23.
 */
public class ProServiceDetailActivity extends BaseProActivity {
    ProServiceView mProServiceViewa, mProServiceViewb, mProServiceViewc, mProServiceViewd;
    private TopViewNormalBar top_ser_bar;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_week_work_common_service_layout);
        top_ser_bar = (TopViewNormalBar) findViewById(R.id.top_ser_bar);
        top_ser_bar.setTitle(R.string.need_order_detail);
        top_ser_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
    }

    private void initView() {
        mProServiceViewa = (ProServiceView) findViewById(R.id.pro_one_view);
        mProServiceViewa.showDemo("ONE", "第一次服务", "", "2016-09-23", "");

        mProServiceViewb = (ProServiceView) findViewById(R.id.pro_two_view);
        mProServiceViewb.showDemo("TWO", "第二次服务", "请于09.23-09.30完成", "", "");
        mProServiceViewb.setBtnOnClickListener(mOnClickListener);

        mProServiceViewc = (ProServiceView) findViewById(R.id.pro_three_view);
        mProServiceViewc.showDemo("THREE", "第三次服务", "", "", "2016-09-23");

        mProServiceViewd = (ProServiceView) findViewById(R.id.pro_three_a_view);
        mProServiceViewd.showDemo("THREE", "第四次服务", "", "", "2016-09-23");
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_ser_bar.getBackView()) {
                finish();
            } else if (v == mProServiceViewb.getClickView()) {
                ToastUtil.shortToast(getApplicationContext(), "确认并提交");
            }
        }
    };
}
