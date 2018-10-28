package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProAdapter;
import com.haiwai.housekeeper.entity.SkillEntity;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.AssetsUtils2;
import com.haiwai.housekeeper.utils.TimeUtils;

import java.util.List;

/**
 * Created by lyj on 2016/9/14.
 */
public class ProSkillListViewAdapter extends BaseProAdapter<SkillEntity.DataBean> {
    private List<SkillEntity.DataBean> mSkillEntityList;
    private Context mContext;
    private View.OnClickListener mOnClickListener;
    private String isZhorEn = "";

    public ProSkillListViewAdapter(Context context, List<SkillEntity.DataBean> lists, View.OnClickListener mOnClickListener, String isZhorEn) {
        super(context, lists);
        this.mContext = context;
        this.mSkillEntityList = lists;
        this.mOnClickListener = mOnClickListener;
        this.isZhorEn = isZhorEn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.pro_skill_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_skill_item_title);
            viewHolder.tvTitle.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
            viewHolder.tvCount = (TextView) convertView.findViewById(R.id.tv_skill_item_attend);
            viewHolder.tvCount.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
            viewHolder.tvDesc = (TextView) convertView.findViewById(R.id.tv_skill_item_desc);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_skill_title_time);
            viewHolder.ivStatus = (ImageView) convertView.findViewById(R.id.iv_skill_status);
            viewHolder.ivSkillSet = (TextView) convertView.findViewById(R.id.iv_skill_set);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SkillEntity.DataBean dataBean = mSkillEntityList.get(position);
        Log.i("dataBean-type",dataBean.getType());
//        if(isZhorEn.equals("zh")){
//            if(dataBean.getType().equals("34")){
//                viewHolder.tvTitle.setText("周期服务-冬季清雪");
//            }else if(dataBean.getType().equals("30")){
//                viewHolder.tvTitle.setText("周期服务-家政服务");
//            }else if(dataBean.getType().equals("32")){
//                viewHolder.tvTitle.setText("周期服务-草坪修剪");
//            }else if(dataBean.getType().equals("33")){
//                viewHolder.tvTitle.setText("周期服务-秋天落叶清扫");
//            }else if(dataBean.getType().equals("35")){
//                viewHolder.tvTitle.setText("周期服务-租赁招租");
//            }else{
                viewHolder.tvTitle.setText(AssetsUtils2.getSkillName(dataBean.getType(), isZhorEn));
//            }
//        }else{
//            if(dataBean.getType().equals("34")){
//                viewHolder.tvTitle.setText("Snow Plowing");
//            }else if(dataBean.getType().equals("30")){
//                viewHolder.tvTitle.setText("House Cleaning");
//            }else if(dataBean.getType().equals("32")){
//                viewHolder.tvTitle.setText("Lawn Mowing");
//            }else if(dataBean.getType().equals("33")){
//                viewHolder.tvTitle.setText("Lawn Mowing");
//            }else if(dataBean.getType().equals("35")){
//                viewHolder.tvTitle.setText("Rental-For renting");
//            }else{
//                viewHolder.tvTitle.setText(AssetsUtils.getSkillName(dataBean.getType(), isZhorEn));
//            }
//        }


        viewHolder.tvCount.setText(mContext.getString(R.string.reg_ph5) + " "+dataBean.getOrder_num() +" "+ mContext.getString(R.string.ss));
        viewHolder.tvDesc.setText(dataBean.getClassX());
        viewHolder.tvTime.setText(mContext.getString(R.string.pro_fbsj) + TimeUtils.getStrTime(dataBean.getCtime()));
        String is_ji = dataBean.getIs_ji();

        String is_aduit = dataBean.getIs_audit();
        Log.i("is_ji------",is_aduit);

        if(AppGlobal.getInstance().getLagStr().equals("en")){
            if(is_aduit.equals("0")){
                viewHolder.ivStatus.setImageResource(R.mipmap.skill_running_en);
            }else if(is_aduit.equals("1")){
                if(is_ji.equals("1")){
                    viewHolder.ivStatus.setImageResource(R.mipmap.skill_userd_en);
                }else if(is_ji.equals("2")){
                    viewHolder.ivStatus.setImageResource(R.mipmap.skill_end_en);
                }else{
                    viewHolder.ivStatus.setImageResource(R.mipmap.unactivited);
                }
            }else{
                viewHolder.ivStatus.setImageResource(R.mipmap.unendorsed);
            }
        }else{
            if(is_aduit.equals("0")){
                viewHolder.ivStatus.setImageResource(R.mipmap.pro_skill_status_running);
            }else if(is_aduit.equals("1")){
                if(is_ji.equals("1")){
                    viewHolder.ivStatus.setImageResource(R.mipmap.pro_skill_status_used);
                }else if(is_ji.equals("2")){
                    viewHolder.ivStatus.setImageResource(R.mipmap.pro_skill_status_end);
                }else{
                    viewHolder.ivStatus.setImageResource(R.mipmap.pro_skill_status_end);
                }
            }else{
                viewHolder.ivStatus.setImageResource(R.mipmap.unedorsed_zh);
            }
        }



//        if(AppGlobal.getInstance().getLagStr().equals("en")){
//            if ("0".equals(is_ji)) {
//                viewHolder.ivStatus.setImageResource(R.mipmap.skill_running_en);
//            } else if ("1".equals(is_ji)) {
//                viewHolder.ivStatus.setImageResource(R.mipmap.skill_userd_en);
//            } else if ("2".equals(is_ji)) {
//                viewHolder.ivStatus.setImageResource(R.mipmap.skill_end_en);
//            }
//        }else{
//            if ("0".equals(is_ji)) {
//                viewHolder.ivStatus.setImageResource(R.mipmap.pro_skill_status_running);
//            } else if ("1".equals(is_ji)) {
//                viewHolder.ivStatus.setImageResource(R.mipmap.pro_skill_status_used);
//            } else if ("2".equals(is_ji)) {
//                viewHolder.ivStatus.setImageResource(R.mipmap.pro_skill_status_end);
//            }
//        }

        viewHolder.ivSkillSet.setTag(position);
        viewHolder.ivSkillSet.setOnClickListener(mOnClickListener);
        return convertView;
    }

    class ViewHolder {
        TextView tvTitle, tvCount, tvDesc, tvTime,ivSkillSet;
        ImageView ivStatus;
    }
}
