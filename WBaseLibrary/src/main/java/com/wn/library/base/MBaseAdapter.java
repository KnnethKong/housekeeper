package com.wn.library.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王宁 on 2017/2/16.
 */

public abstract class MBaseAdapter<T> extends BaseAdapter {

    private List<T> list;

    private Context context;

    private LayoutInflater inflater;

    public MBaseAdapter(List<T> list,Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public List<T> getList() {
        return list;
    }

    public Context getContext() {
        return context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    @Override
    public int getCount() {
        if(list==null){
            list = new ArrayList<>();
        }
        return list.size();
    }

    @Override
    public T getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void refresh(List<T> list){
        if(list!=null&&list.size()>0){
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getItemView(i,view,viewGroup);
    }

    public abstract View getItemView(int i,View view,ViewGroup viewGroup);
}
