package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.SkillDetailEntity;
import com.haiwai.housekeeper.utils.TimeUtils;

import java.util.List;

/**
 * Created by ihope007 on 2016/9/2.
 */
public class SkillDetailEduAdapter extends BaseAdapter {
    private Context context;
    private List<SkillDetailEntity.DataBean.EducationBean> list;

    public SkillDetailEduAdapter(Context context, List<SkillDetailEntity.DataBean.EducationBean> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder vh = ViewHolder.get(context, parent, R.layout.item_skill_detail_edu, position, convertView);
        vh.setTextview(R.id.item_skill_detail_tv_time,"教育经历/"+ TimeUtils.getDate4(list.get(position).getStime())+"-"+TimeUtils.getDate4(list.get(position).getEtime()));
        vh.setTextview(R.id.user_pro_skill_detail_tv_name,list.get(position).getName());
        vh.setTextview(R.id.user_pro_skill_detail_tv_xue,list.get(position).getXue());
        return vh.getContentView();
    }
}
