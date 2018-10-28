package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.WalletPayEntity;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.AssetsUtils2;
import com.haiwai.housekeeper.utils.TimeUtils;

import java.util.List;

/**
 * Created by ihope007 on 2016/10/9.
 */
public class WalletPayAdapter extends RecyclerView.Adapter<WalletPayAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<WalletPayEntity.DataBean.DateBean> list;
    private String isZhorEn = "";

    public WalletPayAdapter(Context context, List<WalletPayEntity.DataBean.DateBean> list) {
        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_wallet_pay, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        String title= AssetsUtils.getSkillName(list.get(position).getType(),isZhorEn);
//        holder.tv_type.setText(title);
        getType(holder.tv_type,list.get(position).getType());
        String ordernum=list.get(position).getOrder_id();
        String ctime=list.get(position).getCtime();
        holder.tv_ordernum.setText(mContext.getString(R.string.order_content_code)+ordernum+"/"+TimeUtils.getDate7(ctime));
        holder.tv_money.setText(mContext.getString(R.string.my_deduct)+"$"+list.get(position).getZhi_money());
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

    private void getType(TextView tv,String type){
        String lan = AppGlobal.getInstance().getLagStr();
        if(!type.equals("0")){
            tv.setText(AssetsUtils2.getSkillName(type, lan));
        }else{
            tv.setText(mContext.getString(R.string.off_line));
        }

//        if(lan.equals("zh")){
//            if(type.equals("29")){
//                tv.setText("周期服务-空屋管理");
//            }else if(type.equals("34")){
//                tv.setText("周期服务-冬季清雪");
//            }else if(type.equals("30")){
//                tv.setText("周期服务-家政服务");
//            }else if(type.equals("31")){
//                tv.setText("周期服务-租赁-管理");
//            }else if(type.equals("32")){
//                tv.setText("周期服务-草坪修剪");
//            }else if(type.equals("33")){
//                tv.setText("周期服务-秋季落叶清扫");
//            }else{
//                tv.setText("周期服务-租赁-招租");
//            }
//        }else{
//            if(type.equals("29")){
//                tv.setText("Vacant Property Management");
//            }else if(type.equals("34")){
//                tv.setText("Snow Plowing");
//            }else if(type.equals("30")){
//                tv.setText("Home service");
//            }else if(type.equals("31")){
//                tv.setText("Rental Management");
//            }else if(type.equals("32")){
//                tv.setText("Lawn Mowing");
//            }else if(type.equals("33")){
//                tv.setText("Leaves Cleaning");
//            }else{
//                tv.setText("For Renting");
//            }
//        }
    }
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_type;
        TextView tv_ordernum;
        TextView tv_money;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_type = (TextView) itemView.findViewById(R.id.item_wallet_pay_tv_type);
            tv_ordernum = (TextView) itemView.findViewById(R.id.item_wallet_pay_tv_ordernum);
            tv_money = (TextView) itemView.findViewById(R.id.item_wallet_pay_tv_money);
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
