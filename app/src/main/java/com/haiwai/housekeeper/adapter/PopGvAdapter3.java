package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.TitleItem;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/13.
 */
public class PopGvAdapter3 extends BaseAdapter {
    private Context context;
    private List<TitleItem> list;
    private LayoutInflater linf;
    private int index;
    static HashMap<Integer, Integer> ischecked;

    public PopGvAdapter3(Context context, List<TitleItem> list, int index) {
        this.context = context;
        this.list = list;
        this.index = index;
        linf = LayoutInflater.from(context);
        ischecked = new HashMap<Integer, Integer>();
        for (int i = 0; i < list.size(); i++) {
            ischecked.put(i, -1);
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public TitleItem getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void setData(List<TitleItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = linf.inflate(R.layout.all_business_item, null);
            holder.cbxTitle = (TextView) convertView.findViewById(R.id.gvitem_tvname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list.get(position) != null) {
            holder.cbxTitle.setText(list.get(position).getName());
        }

        if (index == position) {
            ischecked.put(position, Integer.valueOf(list.get(position).getType()));
            holder.cbxTitle.setBackgroundResource(R.drawable.bg_shape_tv_rect_theme_much_corner);
            holder.cbxTitle.setTextColor(Color.parseColor("#ffffff"));
        } else {
            ischecked.put(position, -1);
            holder.cbxTitle.setBackgroundResource(R.drawable.bg_shape_tv_rect_grey_much_corner);
            holder.cbxTitle.setTextColor(context.getResources().getColor(R.color.text_1));
        }
        return convertView;
    }

    public HashMap<Integer, Integer> getIschecked() {
        return ischecked;
    }

    class ViewHolder {
        TextView cbxTitle;
    }
}