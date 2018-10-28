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
import com.haiwai.housekeeper.entity.WalletRechargeEntity;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.TimeUtils;

import java.util.List;

/**
 * Created by ihope007 on 2016/10/9.
 */
public class WalletRechargeAdapter extends RecyclerView.Adapter<WalletRechargeAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<WalletRechargeEntity.DataBean.DateBean> list;
    private String isZhorEn = "";

    public WalletRechargeAdapter(Context context, List<WalletRechargeEntity.DataBean.DateBean> list) {
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
//        String title= list.get(position).getToken();
//        holder.tv_type.setText(title);
        getType(holder.tv_type,list.get(position).getType());
        String ordernum=list.get(position).getOrderid();
        String ctime=list.get(position).getCtime();
        holder.tv_ordernum.setText(mContext.getString(R.string.order_content_code)+ordernum+"/"+TimeUtils.getDate7(ctime));
        holder.tv_money.setText(mContext.getString(R.string.my_recharge)+"$"+list.get(position).getMoney());
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
        if(AppGlobal.getInstance().getLagStr().equals("zh")){
            if(type.equals("1")){
                tv.setText("PayPal");
            }else if(type.equals("2")){
                tv.setText("微信");
            }else if(type.equals("3")){
                tv.setText("支付宝");
            }else{
                tv.setText(mContext.getString(R.string.off_line));
            }
        }else{
            if(type.equals("1")){
                tv.setText("PayPal");
            }else if(type.equals("2")){
                tv.setText("AliPay");
            }else if(type.equals("3")){
                tv.setText("AliPay");
            }else{
                tv.setText(mContext.getString(R.string.off_line));
            }
        }
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
