package com.haiwai.housekeeper.activity.server;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.view.TopViewNormalBar;

/**
 * Created by lyj on 2016/9/23.
 */
public class ProOrderFinishDetailActivty extends BaseProActivity {
    private TopViewNormalBar top_finish_messagee_bar;
    private String isZhorEn = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_order_finish_detail_layout);
        top_finish_messagee_bar = (TopViewNormalBar) findViewById(R.id.top_finish_messagee_bar);
        top_finish_messagee_bar.setTitle(getString(R.string.or_detial));
        top_finish_messagee_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }

    View.OnClickListener mOnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == top_finish_messagee_bar.getBackView()){
                finish();
            }
        }
    };
}
