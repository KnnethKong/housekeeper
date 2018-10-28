package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.HomeEntity;
import com.haiwai.housekeeper.entity.ZiYBean;
import com.haiwai.housekeeper.utils.OptionUtils;
import com.joooonho.SelectableRoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by ihope007 on 2016/10/9.
 */
public class HomeSelfServiceAdapter extends RecyclerView.Adapter<HomeSelfServiceAdapter.MyViewHolder> {
    private Context mContext;
    private List<ZiYBean> list;
    private LayoutInflater inflater;
    private String isZhorEn = "";

    public HomeSelfServiceAdapter(Context context, List<ZiYBean> list) {
        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_self_service, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(mContext).load( list.get(position).getUrl()).into( holder.self_support_iv_main);
        //ImageLoader.getInstance().displayImage(null, holder.self_support_iv_main, OptionUtils.getOptions(mContext, list.get(position).getUrl()));
//        if ("zh".equalsIgnoreCase(isZhorEn))
//            holder.tv.setText(list.get(position).getName());
//        else
//            holder.tv.setText(list.get(position).getYname());

        if(position==0){
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                holder.tvDes.setImageResource(R.mipmap.xjgl_english_a);
            }else{
                holder.tvDes.setImageResource(R.mipmap.xjgl_a);
            }

        }else if(position==1){
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                holder.tvDes.setImageResource(R.mipmap.zrgl_english_a);
            }else{
                holder.tvDes.setImageResource(R.mipmap.zrgl_a);
            }

        }else if(position==2){
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                holder.tvDes.setImageResource(R.mipmap.tygl_english_a);
            }else{
                holder.tvDes.setImageResource(R.mipmap.tygl_a);
            }

        }
        holder.tvHomeDesc.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        if(position==0){
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                holder.tvHomeDesc.setText("Regular Property Inspection,Mail\nManagement,and Timely Feedback");
            }else{
                holder.tvHomeDesc.setText("定期巡视您的房屋，收信处理并及时汇报");
            }
        }else if(position==1){
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                holder.tvHomeDesc.setText("Rental Management and Monthly Report");
            }else{
                holder.tvHomeDesc.setText("招租、收租管理一站服务，形成月报");
            }


        }else if(position==2){

            if(AppGlobal.getInstance().getLagStr().equals("en")){
                holder.tvHomeDesc.setText("Let Your Yard Shine with Professional\nLandscape Plan");
            }else{
                holder.tvHomeDesc.setText("灵活配置园艺方案以造四时之景");
            }

        }else {
            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                holder.tvHomeDesc.setText("Regular Property Inspection,Mail\nManagement,and Timely Feedback");
            } else {
                holder.tvHomeDesc.setText("定期巡视您的房屋，收信处理并及时汇报");
            }

        }
            if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView tvDes;
        ImageView self_support_iv_main;
        TextView tvHomeDesc;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvHomeDesc = ((TextView) itemView.findViewById(R.id.home_self_desc));
            tvDes = ((ImageView) itemView.findViewById(R.id.tv_ziying_decrible));
            self_support_iv_main = (ImageView) itemView.findViewById(R.id.self_support_iv_main);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
