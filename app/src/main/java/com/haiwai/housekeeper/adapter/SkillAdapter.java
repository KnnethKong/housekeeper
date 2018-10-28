package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.ProDetailEntity;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.view.LabelView;
import com.haiwai.housekeeper.view.WarpLinearLayout;

import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class SkillAdapter extends BaseAdapter {
    private Context context;
    private List<ProDetailEntity.DataBean.SkillBean> list;
    private String isZhorEn = "";

    public SkillAdapter(Context context, List<ProDetailEntity.DataBean.SkillBean> list) {
        this.context = context;
        this.list = list;
        isZhorEn = AppGlobal.getInstance().getLagStr();
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.item_skill, position, convertView);
        vh.setTextview(R.id.item_pro_detail_tv_type, AssetsUtils.getSkillName(list.get(position).getType(),isZhorEn));
        vh.setTextview(R.id.item_pro_detail_tv_price, "$" + list.get(position).getMoney_p() + context.getString(R.string.o2o_detail_xie_pingmi));
        vh.setTextview(R.id.item_pro_detail_tv_class, list.get(position).getClassX());
        return vh.getContentView();
    }
}
