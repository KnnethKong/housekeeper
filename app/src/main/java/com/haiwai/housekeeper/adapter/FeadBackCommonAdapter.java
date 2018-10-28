package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haiwai.housekeeper.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope006 on 2016/11/24.
 */

public class FeadBackCommonAdapter extends BaseAdapter {
    Context mContext;
    List<String> pathList;

    private Map<Integer, String> map = new HashMap<>();


    public FeadBackCommonAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.pathList = list;
    }

    @Override
    public int getCount() {
        if (pathList != null && pathList.size() > 0) {
            return pathList.size();
        } else {
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {
        return pathList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_published_grida,
                    parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            holder.iv_del = (ImageView) convertView.findViewById(R.id.iv_del);
            holder.iv_del.setVisibility(View.GONE);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        final int x = position;

//        if (map.get(position) == null) {
//            holder.image.setTag(pathList.get(position));
//            map.put(x, pathList.get(position));
//
//        } else {
//            ImageLoader.getInstance().displayImage(map.get(x), holder.image);
//        }
        holder.image.setImageResource(R.mipmap.home_logo_grey);
        //ImageLoader.getInstance().displayImage(pathList.get(position), holder.image);
        Glide.with(mContext).load(pathList.get(position)).into(holder.image);
        return convertView;
    }

    public class ViewHolder {
        public ImageView image, iv_del;
    }
}
