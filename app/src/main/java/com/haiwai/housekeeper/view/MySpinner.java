package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.MyAdapter;

/**
 * Created by ihope006 on 2017/2/7.
 */

public class MySpinner extends TextView {
    private Context mContext;
    private MyAdapter adapter;
    private ListView popContentView;
    private AdapterView.OnItemClickListener onItemClickListener;
    private PopupWindow mDropView;

    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        LinearLayout container = (LinearLayout) inflater.inflate(R.layout.spinner_content, null);
        popContentView = (ListView) container.findViewById(R.id.spinner_content);
        mDropView = new PopupWindow(container, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mDropView.setBackgroundDrawable(new BitmapDrawable());
        mDropView.setFocusable(true);
        mDropView.setOutsideTouchable(true);
        mDropView.setOutsideTouchable(true);
        mDropView.setTouchable(true);
        container.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissPop();
            }
        });
        this.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mDropView.isShowing()){
                    dismissPop();
                }else{
                    showPop();
                }
            }
        });
        mDropView.update();
    }

    public void setHint(String hint){
        this.setText(hint);
    }

    public void setAdapter(MyAdapter adapter){
        if(adapter != null){
            this.adapter = adapter;
            popContentView.setAdapter(this.adapter);
        }

    }

    public void setOnItemSelectedListener(AdapterView.OnItemClickListener listener){
        if(listener != null){
            this.onItemClickListener = listener;
            popContentView.setOnItemClickListener(listener);
        }

    }

    public void dismissPop(){
        if(mDropView.isShowing()){
            mDropView.dismiss();
        }
    }

    public void showPop(){
        mDropView.showAsDropDown(this);
    }

}

