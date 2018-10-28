//package com.haiwai.housekeeper.adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.GridView;
//import android.widget.TextView;
//
//import com.haiwai.housekeeper.R;
//import com.haiwai.housekeeper.base.BaseProAdapter;
//import com.haiwai.housekeeper.entity.TitleEntity;
//import com.haiwai.housekeeper.view.MyGridView;
//
//import java.util.List;
//
///**
// * Created by lyj on 2016/9/21.
// */
//public class ProSkillShowaAdapter extends BaseProAdapter<TitleEntity> {
//    private Context mContext;
//    private List<TitleEntity> mTitleList;
//
//    public ProSkillShowaAdapter(Context context, List<TitleEntity> lists) {
//        super(context, lists);
//        this.mContext = context;
//        this.mTitleList = lists;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            convertView = View.inflate(mContext, R.layout.pro_skill_show_zero_layout_item, null);
//            viewHolder = new ViewHolder();
//            viewHolder.tvTite = (TextView) convertView.findViewById(R.id.tv_skill_title);
//            viewHolder.mGridView = (MyGridView) convertView.findViewById(R.id.m_gridview);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        TitleEntity title = mTitleList.get(position);
//        viewHolder.tvTite.setText(title.getName());
//        viewHolder.mGridView.setAdapter(new ProSkillShowaItemAdapter(mContext, title.getItems(),position));
//        return convertView;
//    }
//
//    class ViewHolder {
//        TextView tvTite;
//        MyGridView mGridView;
//    }
//
//}
