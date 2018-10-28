package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.TitleItem;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/13.
 */
public class PopGvAdapter extends BaseAdapter {
    private Context context;
    private List<TitleItem> list;
    private LayoutInflater linf;

    public PopGvAdapter(Context context, List<TitleItem> list) {
        this.context = context;
        this.list = list;
        linf = LayoutInflater.from(context);
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
        holder.cbxTitle.setBackgroundResource(R.drawable.bg_shape_tv_rect_grey_much_corner);
        holder.cbxTitle.setTextColor(context.getResources().getColor(R.color.text_1));
        holder.cbxTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtil.longToast(context,list.get(position).getType());
                mOnItemsClickListener.itemClick(list.get(position).getType());
            }
        });
        return convertView;
    }


    class ViewHolder {
        TextView cbxTitle;
    }

    public static OnItemsClickListener mOnItemsClickListener;

    public interface OnItemsClickListener {
        void itemClick(String type);
    }

    public void setOnItemsClickListener(OnItemsClickListener onItemsClickListener) {
        mOnItemsClickListener = onItemsClickListener;
    }
}