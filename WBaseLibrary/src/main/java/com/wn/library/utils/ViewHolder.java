package com.wn.library.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 王宁 on 2017/3/1.
 */

public class ViewHolder {

    public View mView;

    public View getMainView(){
        return mView;
    }

    private ViewHolder(View view){
        this.mView  = view;
    }

    public static ViewHolder getHolder(Context context, int layoutId, View view){
        if(view==null){
            view = LayoutInflater.from(context).inflate(layoutId,null);
            ViewHolder holder = new ViewHolder(view);
            view.setTag(holder);
            return holder;
        }else{
            return ((ViewHolder) view.getTag());
        }
    }


    public void setTextView(int tvId,String content){
        ((TextView) mView.findViewById(tvId)).setText(content);
    }

    public View getView(int viewId){
        return mView.findViewById(viewId);
    }

    public ImageView getImageView(int imgViewId){
        return ((ImageView) mView.findViewById(imgViewId));
    }
}
