package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.ProSkillShowaActivity;
import com.haiwai.housekeeper.base.BaseProAdapter;
import com.haiwai.housekeeper.entity.TitleItem;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.util.List;

/**
 * Created by lyj on 2016/9/21.
 */
public class ProSkillShowaItemAdapter extends BaseProAdapter<TitleItem> {
    private Context mContext;
    private List<TitleItem> mTitleList;
    private OnItemSelectedListener mOnItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener mmOnItemSelectedListener) {
        this.mOnItemSelectedListener = mmOnItemSelectedListener;
    }

    public ProSkillShowaItemAdapter(Context context, List<TitleItem> lists) {
        super(context, lists);
        this.mContext = context;
        this.mTitleList = lists;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.pro_skill_show_zero_layout_gridview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTite = (TextView) convertView.findViewById(R.id.lv_skill_item_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final TitleItem title = mTitleList.get(position);
        viewHolder.tvTite.setText(title.getName());
        if (title.isSelected()) {
            if ("0".equals(title.getStatus())) {
                viewHolder.tvTite.setTextColor(Color.parseColor("#01D1C1"));
                viewHolder.tvTite.setBackground(mContext.getResources().getDrawable(R.mipmap.skill_running));
            } else if ("1".equals(title.getStatus())) {
                viewHolder.tvTite.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.tvTite.setBackground(mContext.getResources().getDrawable(R.mipmap.skill_pass));
            } else if ("2".equals(title.getStatus())) {
                viewHolder.tvTite.setTextColor(Color.parseColor("#FF595F"));
                viewHolder.tvTite.setBackground(mContext.getResources().getDrawable(R.mipmap.skill_refuse));
            }
        }
        viewHolder.tvTite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemSelectedListener.onItemSelect(position);
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
