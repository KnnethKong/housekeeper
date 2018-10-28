package com.haiwai.housekeeper.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.earnings.ShouruDetailsActivity;
import com.haiwai.housekeeper.activity.user.NewHousActivity;
import com.haiwai.housekeeper.activity.user.VipHouseManageActivity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.entity.ShouruEntity;
import com.haiwai.housekeeper.utils.DateUtil;
import com.haiwai.housekeeper.widget.CustomDialog;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by range on 2018/1/24.
 */

public class ShouruAdapter extends BaseAdapter {
    private Activity context;
    private List<ShouruEntity.DataBean> list;
    private String isZhorEn;

    public ShouruAdapter(Activity context, List<ShouruEntity.DataBean> list, String isZhorEn) {
        this.context = context;
        this.list = list;
        this.isZhorEn = isZhorEn;
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
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.item_shouru, position, convertView);
        //String date = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(list.get(position).getCtime()));
        String mm = TimeStamp2Date(list.get(position).getCtime(), "MM");
        String dd = TimeStamp2Date(list.get(position).getCtime(), "dd");
        if (isZhorEn.equals("en")) {
            switch (mm) {
                case "01":
                    vh.setTextview(R.id.tv_user_order_date, "January " + dd + "th");
                    break;
                case "02":
                    vh.setTextview(R.id.tv_user_order_date, "February " + dd + "th");
                    break;
                case "03":
                    vh.setTextview(R.id.tv_user_order_date, "March " + dd + "th");
                    break;
                case "04":
                    vh.setTextview(R.id.tv_user_order_date, "April " + dd + "th");
                    break;
                case "05":
                    vh.setTextview(R.id.tv_user_order_date, "May " + dd + "th");
                    break;
                case "06":
                    vh.setTextview(R.id.tv_user_order_date, "June " + dd + "th");
                    break;
                case "07":
                    vh.setTextview(R.id.tv_user_order_date, "July " + dd + "th");
                    break;
                case "08":
                    vh.setTextview(R.id.tv_user_order_date, "August " + dd + "th");
                    break;
                case "09":
                    vh.setTextview(R.id.tv_user_order_date, "September " + dd + "th");
                    break;
                case "10":
                    vh.setTextview(R.id.tv_user_order_date, "October " + dd + "th");
                    break;
                case "11":
                    vh.setTextview(R.id.tv_user_order_date, "November " + dd + "th");
                    break;
                case "12":
                    vh.setTextview(R.id.tv_user_order_date, "December " + dd + "th");
                    break;
                default:
            }
            vh.setTextview(R.id.tv_shouru_money, context.getResources().getString(R.string.Accountreceivable) + "  CAD " + list.get(position).getYin_money());
        } else {
            vh.setTextview(R.id.tv_user_order_date, mm + "月" + dd + "日");
            vh.setTextview(R.id.tv_shouru_money, context.getResources().getString(R.string.Accountreceivable) + list.get(position).getYin_money() + "加元");
        }

        switch (list.get(position).getStaticx()) {
            case "0":
                vh.setTextview(R.id.tv_shouru_state, context.getResources().getString(R.string.Awaitingsettled));
                break;
            case "1":
                vh.setTextview(R.id.tv_shouru_state, context.getResources().getString(R.string.Settled));
                break;
            case "2":
                vh.setTextview(R.id.tv_shouru_state, context.getResources().getString(R.string.Inprogress));
                break;
        }
        if (isZhorEn.equals("en")) {
            vh.setTextview(R.id.tv_shouru_dateils, "details");
        } else {
            vh.setTextview(R.id.tv_shouru_dateils, "详情");
        }
        vh.getview(R.id.tv_shouru_dateils).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShouruDetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());
                context.startActivity(intent);
            }
        });


        return vh.getContentView();
    }

    public String TimeStamp2Date(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }
}
