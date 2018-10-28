package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.CommnetEntity;
import com.haiwai.housekeeper.entity.ProDetailEntity;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.view.LabelView;
import com.haiwai.housekeeper.view.WarpLinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommnetEntity.DataBean> list = new ArrayList<>();
    private Map<String, String> map;
    private String isZhorEn = "";

    public CommentAdapter(Context context, List<CommnetEntity.DataBean> list, Map<String, String> map) {
        this.context = context;
        this.list = list;
        this.map = map;
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        } else {
           return list.size();
        }
    }
    public void setListData(List<CommnetEntity.DataBean> mList,Map<String, String> map){
        this.map = map;
        if(mList.size()!=0){
            list.addAll(mList);
            notifyDataSetChanged();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.item_comment, position, convertView);
        RatingBar rb = vh.getview(R.id.item_comment_rb);
        rb.setProgress(Integer.valueOf(list.get(position).getXin()));
        vh.setTextview(R.id.user_comment_tv_content, list.get(position).getContent());
        TextView tv = vh.getview(R.id.user_comment_tv_title);
        if (Integer.valueOf(list.get(position).getXin()) > 0) {
            tv.setText(getTitle(Integer.valueOf(list.get(position).getXin())));
        }
        Log.i("dateInformation-->",list.get(position).getCtime());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        String date = format.format(new Date(Long.valueOf(list.get(position).getCtime()+"000")));
        String date = TimeUtils.getDate10(list.get(position).getCtime());
        vh.setTextview(R.id.tv_comment_time,date);
        WarpLinearLayout layout = vh.getview(R.id.item_comment_ll_content_label);
        String des = list.get(position).getL_id();
        String[] split = des.split(",");
        layout.setVisibility(View.VISIBLE);
        if (split.length > 0) {
            layout.removeAllViews();
            for (int j = 0; j < split.length; j++) {
                LabelView labelView = new LabelView(context);
                if (TextUtils.isEmpty(map.get(split[j]))) {
                    layout.setVisibility(View.GONE);
                } else {
                    layout.setVisibility(View.VISIBLE);
                    labelView.setContent(map.get(split[j]));
                    layout.addView(labelView);
                }
            }
        }
        return vh.getContentView();
    }

    public String getTitle(int num) {
        String str = "";
        switch (num) {
            case 1:
                str = context.getString(R.string.star1);
                break;
            case 2:
                str = context.getString(R.string.star2);
                break;
            case 3:
                str = context.getString(R.string.star3);
                break;
            case 4:
                str = context.getString(R.string.star4);
                break;
            case 5:
                str = context.getString(R.string.star5);
                break;
        }
        return str;

    }
}
