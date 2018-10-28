package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.activity.user.TakeOrderServeListActivity;
import com.haiwai.housekeeper.entity.NeedResponseEntity;
import com.haiwai.housekeeper.entity.TakeOrderServeEntity;
import com.haiwai.housekeeper.fragment.user.need.ToResponseFragment;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class TakeOrderServeAdapter extends BaseAdapter {
    private TakeOrderServeListActivity context;
    private List<TakeOrderServeEntity.DataBean> list;
    private Map<Integer,String> strMap = new HashMap<>();

    public TakeOrderServeAdapter(TakeOrderServeListActivity context, List<TakeOrderServeEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.user_item_take_order_serve, position, convertView);
//        if (!TextUtils.isEmpty(list.get(position).getNickname())) {
            vh.setTextview(R.id.item_take_order_serve_tv_name, list.get(position).getNickname());
//        }else{
//
//        }
        if (list.get(position).getKm() != null) {
            vh.setTextview(R.id.item_take_order_serve_tv_distance, list.get(position).getKm().toString() + "km");
        } else {
            vh.setTextview(R.id.item_take_order_serve_tv_distance, "");
        }
        if(Float.valueOf(list.get(position).getPro_xing())!=0&&Integer.valueOf(list.get(position).getPro_onum())!=0){
            if(Integer.parseInt(list.get(position).getPro_onum())>1){
                vh.setTextview(R.id.item_take_order_serve_tv_describe, context.getString(R.string.o2o_detail_has_done) + list.get(position).getPro_onum() + context.getString(R.string.o2o_detail_dan_pingjias) + String.format("%.1f",Float.valueOf(list.get(position).getPro_xing()) / Integer.valueOf(list.get(position).getPro_onum())));
            }else{
                vh.setTextview(R.id.item_take_order_serve_tv_describe, context.getString(R.string.o2o_detail_has_done) + list.get(position).getPro_onum() + context.getString(R.string.o2o_detail_dan_pingjia) + String.format("%.1f",Float.valueOf(list.get(position).getPro_xing()) / Integer.valueOf(list.get(position).getPro_onum())));
            }
        }else{
            if(Integer.parseInt(list.get(position).getPro_onum())>1){
                vh.setTextview(R.id.item_take_order_serve_tv_describe, context.getString(R.string.o2o_detail_has_done) + list.get(position).getPro_onum() + context.getString(R.string.o2o_detail_dan_pingjias) + 0);
            }else{
                vh.setTextview(R.id.item_take_order_serve_tv_describe, context.getString(R.string.o2o_detail_has_done) + list.get(position).getPro_onum() + context.getString(R.string.o2o_detail_dan_pingjia) + 0);
            }

        }
        String serType = list.get(position).getService_type();
        if ("1".equals(serType)) {
            if (TextUtils.isEmpty(list.get(position).getHome_fee()) || "0.00".equals(list.get(position).getHome_fee())) {
                vh.setTextview(R.id.item_take_order_serve_status, context.getString(R.string.mfsm));
            } else {
                vh.setTextview(R.id.item_take_order_serve_status, context.getString(R.string.ysfm));
            }
            vh.setTextview(R.id.item_take_order_serve_tv_price, context.getString(R.string.hour_price));
            ((TextView) vh.getview(R.id.item_take_order_serve_tv_price)).setTextColor(Color.CYAN);
        } else {
            if (TextUtils.isEmpty(list.get(position).getHome_fee()) || "0.00".equals(list.get(position).getHome_fee())) {
                vh.setTextview(R.id.item_take_order_serve_status, context.getString(R.string.mfsm));
            } else {
                vh.setTextview(R.id.item_take_order_serve_status, context.getString(R.string.ysfm));
            }
            if(serType.equals("3")){
                vh.setTextview(R.id.item_take_order_serve_tv_price, context.getString(R.string.jtqk) );
            }else {
                vh.setTextview(R.id.item_take_order_serve_tv_price, context.getString(R.string.jy_dw) + list.get(position).getGeneral());
            }

        }

        CircleImageView icon = vh.getview(R.id.item_take_order_serve_iv_icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("oid", list.get(position).getOid());
                bundle.putString("nickname", list.get(position).getNickname());
                bundle.putString("uid", list.get(position).getUid());
                bundle.putString("type", list.get(position).getType());
                bundle.putString("choose", "1");
                ActivityTools.goNextActivity(context, ProDetailActivity.class, bundle);
            }
        });
        ImageView iv_diamond = vh.getview(R.id.item_take_order_serve_iv_diamond);// TODO: 2016/9/8
        ImageView iv_special = vh.getview(R.id.item_take_order_serve_iv_special);
        ImageView star_line = vh.getview(R.id.item_take_order_serve_iv_star_line);
        iv_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showPopWindow(v);
            }
        });
        ImageLoader.getInstance().displayImage(list.get(position).getAvatar(), icon, ImageLoaderUtils.getAvatarOptions());

        strMap.put(position,list.get(position).getS());

        if (strMap.get(position).equals("1")) {
            final String url = "drawable://" +R.mipmap.v_icon;
            ImageLoader.getInstance().displayImage(url,iv_diamond);
//            map1.get(position).setVisibility(View.VISIBLE);
        } else if (strMap.get(position).equals("2")) {
//            map2.get(position).setVisibility(View.VISIBLE);
            final String url = "drawable://" +R.mipmap.s_icon;
            ImageLoader.getInstance().displayImage(url,iv_diamond);
        }else{
//            map1.get(position).setVisibility(View.GONE);
//            map2.get(position).setVisibility(View.GONE);
            final String url = "";
            ImageLoader.getInstance().displayImage(url,iv_diamond);
        }




        String is_chosen = list.get(position).getAt_uid();//0没选1选
        String uid = list.get(position).getUid();
        if (!uid.equals(is_chosen)) {
            iv_special.setVisibility(View.GONE);
            star_line.setMinimumHeight(32);
        } else {
            iv_special.setVisibility(View.VISIBLE);
            star_line.setMinimumHeight(32);
        }

        String sf_ren = list.get(position).getIs_ren();
        ImageView iv_sf = vh.getview(R.id.item_take_order_serve_iv_sfrz);
        if (sf_ren != null) {
            switch (sf_ren) {
                case "0":
                    iv_sf.setImageResource(R.mipmap.o2o_item_sf_grey);
                    break;
                case "1":
                    iv_sf.setImageResource(R.mipmap.shenfenrenzheng_card);
                    break;
                case "2":
                    iv_sf.setImageResource(R.mipmap.o2o_item_sf_grey);
                    break;
                case "3":
                    iv_sf.setImageResource(R.mipmap.o2o_item_sf_grey);
                    break;

            }
        } else {
            iv_sf.setImageResource(R.mipmap.o2o_item_sf_grey);
        }

        String jn_ren = list.get(position).getIs_skill();// TODO: 2016/9/8
        ImageView iv_jn = vh.getview(R.id.item_take_order_serve_iv_jnrz);
        if (jn_ren != null) {
            switch (jn_ren) {
                case "0":
                    iv_jn.setImageResource(R.mipmap.o2o_item_jn_grey);
                    break;
                case "1":
                    iv_jn.setImageResource(R.mipmap.shenfenrenzheng_jiangbei);
                    break;
                case "2":
                    iv_jn.setImageResource(R.mipmap.o2o_item_jn_grey);
                    break;

            }
        } else {
            iv_jn.setImageResource(R.mipmap.o2o_item_jn_grey);
        }

        return vh.getContentView();
    }
}
