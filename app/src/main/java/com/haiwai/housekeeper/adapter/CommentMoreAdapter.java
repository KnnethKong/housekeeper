package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.CommentMoreEntity;
import com.haiwai.housekeeper.view.LabelView;
import com.haiwai.housekeeper.view.WarpLinearLayout;

import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class CommentMoreAdapter extends BaseAdapter {
    private Context context;
    private List<CommentMoreEntity.DataBean> list;
    private Map<String, String> map;

    public CommentMoreAdapter(Context context, List<CommentMoreEntity.DataBean> list, Map<String, String> map) {
        this.context = context;
        this.list = list;
        this.map = map;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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

        String des = list.get(position).getL_id();
        String[] split = des.split(",");
        StringBuffer sb = new StringBuffer();
        TextView tv = vh.getview(R.id.user_comment_tv_title);
        tv.setText(getTitle(Integer.valueOf(list.get(position).getXin())));
        WarpLinearLayout layout = vh.getview(R.id.item_comment_ll_content_label);
        layout.setVisibility(View.VISIBLE);
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
