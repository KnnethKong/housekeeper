package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.HomeEntity;

import java.util.List;

/**
 * Created by ihope007 on 2016/10/9.
 */
public class HomeHotAdapter extends RecyclerView.Adapter<HomeHotAdapter.MyViewHolder> {
    private Context mContext;
    private List<HomeEntity.DataBean.RemBean> list;
    private LayoutInflater inflater;
    private String isZhorEn = "";

    public HomeHotAdapter(Context context, List<HomeEntity.DataBean.RemBean> list) {
        this.mContext = context;
        this.list = list;
        inflater=LayoutInflater. from(mContext);
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_home_hot,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if ("zh".equalsIgnoreCase(isZhorEn))
            holder.tv.setText(list.get(position).getName());
        else
            holder.tv.setText(list.get(position).getYname());

        if( mOnItemClickListener!= null){
            holder. itemView.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder. itemView.setOnLongClickListener( new View.OnLongClickListener() {
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

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=(TextView) itemView.findViewById(R.id.home_hot_tv_name);
        }
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
}
