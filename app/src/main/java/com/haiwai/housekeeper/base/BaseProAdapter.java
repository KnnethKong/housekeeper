package com.haiwai.housekeeper.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by lyj on 2016/9/14.
 */
public abstract  class BaseProAdapter<T> extends BaseAdapter {
    private List<T> tList;
    private Context mContext;

    public BaseProAdapter(Context context, List<T> lists) {
        this.mContext = context;
        this.tList = lists;
    }

    @Override
    public int getCount() {
        if (tList == null || tList.size() == 0)
            return 0;
        return tList.size();
    }

    @Override
    public Object getItem(int position) {
        return tList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
