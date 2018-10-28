package com.haiwai.housekeeper.adapter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.AddHouseActivity;
import com.haiwai.housekeeper.activity.user.VipHouseChooseActivity;
import com.haiwai.housekeeper.activity.user.VipHouseManageActivity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.utils.ActivityTools;

import java.util.List;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class VipHouseChooseAdapter extends BaseAdapter {
    private VipHouseChooseActivity context;
    private List<HouseListEntity.DataBean> list;
    public VipHouseChooseAdapter(VipHouseChooseActivity context, List<HouseListEntity.DataBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setData(List<HouseListEntity.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.item_house_choose, position, convertView);
        TextView tvAddr=vh.getview(R.id.item_house_manage_tv_addr);
        tvAddr.setText(list.get(position).getAddress_info());
        return vh.getContentView();
    }
}
