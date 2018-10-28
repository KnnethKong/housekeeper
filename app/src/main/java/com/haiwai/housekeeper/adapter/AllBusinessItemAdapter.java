package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProAdapter;
import com.haiwai.housekeeper.entity.TitleItem;

import java.util.List;

/**
 * Created by lyj on 2016/9/21.
 */
public class AllBusinessItemAdapter extends BaseProAdapter<TitleItem> {
    private Context mContext;
    private List<TitleItem> mTitleList;

    private OnItemSelectedListener mOnItemSelectedListener;

    public  void setOnItemSelectedListener(OnItemSelectedListener mmOnItemSelectedListener) {
        this.mOnItemSelectedListener = mmOnItemSelectedListener;
    }

    public AllBusinessItemAdapter(Context context, List<TitleItem> lists) {
        super(context, lists);
        this.mContext = context;
        this.mTitleList = lists;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.all_business_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTite = (TextView) convertView.findViewById(R.id.gvitem_tvname);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final TitleItem title = mTitleList.get(position);
        if(Integer.valueOf(title.getType())<29){
            viewHolder.tvTite.setText(title.getName());
        }else{
            viewHolder.tvTite.setVisibility(View.GONE);
        }
        if(AppGlobal.getInstance().getLagStr().equals("en")){
            viewHolder.tvTite.setTextSize(10);
        }
        viewHolder.tvTite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemSelectedListener.onItemSelect(position);
//                if (!title.isSelected()) {
////                    title.setIsSelected(true);
//                    title.setSelected(true);
//                    viewHolder.tvTite.setTextColor(Color.parseColor("#FFFFFF"));
//                    viewHolder.tvTite.setBackground(mContext.getResources().getDrawable(R.mipmap.pro_skill_bj_selected));
//                } else {
////                    title.setIsSelected(false);
//                    title.setSelected(false);
//                    viewHolder.tvTite.setTextColor(Color.parseColor("#494949"));
//                    viewHolder.tvTite.setBackground(mContext.getResources().getDrawable(R.mipmap.pro_skill_bj_normal));
//                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvTite;
    }

    public interface OnItemSelectedListener {
        void onItemSelect(int childPosition);
    }
}
