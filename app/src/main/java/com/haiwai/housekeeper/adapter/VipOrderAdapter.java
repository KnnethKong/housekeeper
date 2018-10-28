package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.RechargeActivity;
import com.haiwai.housekeeper.entity.VipOrderEntity;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.TimeUtils;

import java.util.List;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class VipOrderAdapter extends BaseAdapter {
    private Context context;
    private List<VipOrderEntity.DataBean> list;

    public VipOrderAdapter(Context context, List<VipOrderEntity.DataBean> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.item_vip_order, position, convertView);
        if (!TextUtils.isEmpty(list.get(position).getAddress_info()) && !"null".equals(list.get(position).getAddress_info())) {
            vh.setTextview(R.id.item_vip_order_tv_addr,list.get(position).getAddress_info() );
        } else {
            vh.setTextview(R.id.item_vip_order_tv_addr, "null");
        }
        if (!TextUtils.isEmpty(list.get(position).getCtime()) && !"null".equals(list.get(position).getCtime())) {
            vh.setTextview(R.id.item_vip_order_tv_order_time, TimeUtils.getDate6(list.get(position).getCtime()));
        } else {
            vh.setTextview(R.id.item_vip_order_tv_order_time, "null");
        }
        TextView tvstatic = vh.getview(R.id.item_vip_order_tv_static);
        LinearLayout llrecharge=vh.getview(R.id.item_vip_order_ll_recharge);
        ImageView ivrecharge=vh.getview(R.id.item_vip_order_iv_recharge);
        ivrecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTools.goNextActivity(context, RechargeActivity.class);
            }
        });
        switch (list.get(position).getStaticX()) {
            case "0":
                tvstatic.setText("null");
                tvstatic.setBackgroundResource(R.drawable.shape_solid_grey_radius_little);
                break;
            case "1":
                tvstatic.setText("null");
                tvstatic.setBackgroundResource(R.drawable.shape_solid_grey_radius_little);
                break;
            case "2":
                tvstatic.setText("null");
                tvstatic.setBackgroundResource(R.drawable.shape_solid_grey_radius_little);
                break;
            case "3":
                tvstatic.setText("null");
                tvstatic.setBackgroundResource(R.drawable.shape_solid_grey_radius_little);
                break;
        }

        return vh.getContentView();
    }
}
