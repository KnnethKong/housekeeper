package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.HomeEntity;
import com.haiwai.housekeeper.utils.OptionUtils;
import com.joooonho.SelectableRoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by ihope007 on 2016/10/9.
 */
public class HomeHomeServiceAdapter extends RecyclerView.Adapter<HomeHomeServiceAdapter.MyViewHolder> {
    private Context mContext;
    private List<HomeEntity.DataBean.JiajBean> list;
    private LayoutInflater inflater;
    private String isZhorEn = "";
    List<Integer> picList;

    public HomeHomeServiceAdapter(Context context, List<HomeEntity.DataBean.JiajBean> list, List<Integer> picLists) {
        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        this.picList = picLists;
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_home_service, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        String url = "drawable://"+picList.get(position);
        ImageLoader.getInstance().displayImage(url, holder.iv_img);
        holder.tv.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        if ("zh".equalsIgnoreCase(isZhorEn))
            holder.tv.setText(list.get(position).getName());
        else
            holder.tv.setText(list.get(position).getYname());

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
        TextView tv;
        ImageView iv_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.home_service_tv_name);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
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
