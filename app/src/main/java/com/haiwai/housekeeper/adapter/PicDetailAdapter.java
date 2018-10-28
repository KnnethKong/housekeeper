package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.ImageListEntity;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.view.SquareImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/13.
 */
public class PicDetailAdapter extends BaseAdapter {
    private Context context;
    private List<ImageListEntity.DataBean> list;
    private LayoutInflater linf;
    static HashMap<Integer, String> ischecked;
    public PicDetailAdapter(Context context,List<ImageListEntity.DataBean> list ){
        this.context = context;
        this.list=list;
        linf = LayoutInflater.from(context);
        ischecked = new HashMap<Integer, String>();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Integer getItem(int arg0) {
        return null;
    }

    public HashMap<Integer, String> getMap() {
        return ischecked;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

//    public void setData(List<String> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = linf.inflate(R.layout.item_pic_detail, null);
            holder.image = (SquareImageView) convertView.findViewById(R.id.item_pic_detail_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getImg(),holder.image);
        return convertView;
    }

    class ViewHolder {
        SquareImageView image;
    }
}