package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.ZYEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope006 on 2016/12/9.
 */

public class OutlineMultiView extends LinearLayout {
    private TextView tv_out_m_title, tv_out_m_con1, tv_out_m_con2, tv_out_m_con3, tv_out_m_con4, tv_out_m_con5, tv_out_m_con6;
    private List<TextView> tvList = new ArrayList<>();

    public OutlineMultiView(Context context) {
        this(context, null);
    }

    public OutlineMultiView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OutlineMultiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.outline_multi_view_layout, this);
        initView();
    }

    private void initView() {
        tv_out_m_title = (TextView) findViewById(R.id.tv_out_m_title);
        tv_out_m_con1 = (TextView) findViewById(R.id.tv_out_m_con1);
        tv_out_m_con2 = (TextView) findViewById(R.id.tv_out_m_con2);
        tv_out_m_con3 = (TextView) findViewById(R.id.tv_out_m_con3);
        tv_out_m_con4 = (TextView) findViewById(R.id.tv_out_m_con4);
        tv_out_m_con5 = (TextView) findViewById(R.id.tv_out_m_con5);
        tv_out_m_con6 = (TextView) findViewById(R.id.tv_out_m_con6);
        tvList.add(tv_out_m_con1);
        tvList.add(tv_out_m_con2);
        tvList.add(tv_out_m_con3);
        tvList.add(tv_out_m_con4);
        tvList.add(tv_out_m_con5);
        tvList.add(tv_out_m_con6);
    }

    public void setTitle(String title) {
        tv_out_m_title.setText(title);
    }

    public void setContents(List<ZYEntity.DaBean> strs) {
        if (strs != null && strs.size() > 0) {
            for (int i = 0; i < strs.size(); i++) {
                tvList.get(i).setVisibility(View.VISIBLE);
                String strValue = strs.get(i).getStr();
                if(strValue!=null&&strValue.contains(",")){
                    String str = "";
                    for(int j=0;j<strValue.split(",").length;j++){
                        str = str+ strValue.split(",")[j]+"\n";
                    }
                    tvList.get(i).setText(str.substring(0,str.length()-1));
                }else{
                    tvList.get(i).setText(strValue);
                }

            }
        }
    }

    public String getTitle(){
        return tv_out_m_title.getText().toString().trim();
    }
}
