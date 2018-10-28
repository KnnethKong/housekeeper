package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.AddHouseActivity;
import com.haiwai.housekeeper.activity.user.EvaluateActivity;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity3;
import com.haiwai.housekeeper.activity.user.NewHousActivity;
import com.haiwai.housekeeper.activity.user.VipHouseDesignActivity;
import com.haiwai.housekeeper.activity.user.VipHouseManageActivity;
import com.haiwai.housekeeper.activity.user.WatchEvaluateActivity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.widget.CustomDialog;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class VipHouseManageAdapter extends BaseAdapter {
    private VipHouseManageActivity context;
    private List<HouseListEntity.DataBean> list;
    private String isZhorEn;

    public VipHouseManageAdapter(VipHouseManageActivity context, List<HouseListEntity.DataBean> list, String isZhorEn) {
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
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.item_house_manage, position, convertView);
        TextView tvAddr = vh.getview(R.id.item_house_manage_tv_addr);
        TextView tvDetailAdddr = vh.getview(R.id.item_house_detail_tv_addr);
        if ("en".equals(isZhorEn)) {
            tvAddr.setText(list.get(position).getCountry_yname() + list.get(position).getProvince_yname() + list.get(position).getCity_yname());
        } else {
            tvAddr.setText(list.get(position).getCountry_name() + list.get(position).getProvince_name() + list.get(position).getCity_name());
        }
        tvDetailAdddr.setText(list.get(position).street+list.get(position).house_number);
        LinearLayout lledit = vh.getview(R.id.item_house_manage_ll_edit);
        lledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewHousActivity.class);
                intent.putExtra("isNew", true);
                intent.putExtra("isEdit", true);
                Bundle bundle = new Bundle();
                bundle.putInt("fromEdit",1);
                bundle.putSerializable("bean", (Serializable) (list.get(position)));
                intent.putExtra("bundle", bundle);
                context.startActivity(intent);
            }
        });

        LinearLayout lldelete = vh.getview(R.id.item_house_manage_ll_delete);
        lldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CustomDialog.Builder dialog = new CustomDialog.Builder(context);
                dialog.setMessage(context.getString(R.string.confirm_detele))
                        .setPositiveButton(context.getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String house_type = list.get(position).getHousing_type();
                                String city = list.get(position).getCity();
                                String house_id = list.get(position).getId();
                                int isWeek = list.get(position).getIs_support();
                                context.requestDeleteHouse(house_id, city, house_type, isWeek);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton(context.getString(R.string.message_alert_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();

            }
        });
        return vh.getContentView();
    }
}
