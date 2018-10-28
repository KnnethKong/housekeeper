//package com.haiwai.housekeeper.adapter;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.text.TextUtils;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.haiwai.housekeeper.R;
//import com.haiwai.housekeeper.base.BaseProAdapter;
//import com.haiwai.housekeeper.entity.OrderItemEntry;
//import com.haiwai.housekeeper.entity.OrderNo;
//import com.haiwai.housekeeper.imageloader.ImageLoader;
//import com.haiwai.housekeeper.utils.AssetsUtils;
//import com.haiwai.housekeeper.utils.StaticUtils;
//import com.haiwai.housekeeper.utils.TimeUtils;
//import com.haiwai.housekeeper.view.CircleImageView;
//
//import java.util.List;
//
///**
// * Created by lyj on 2016/9/14.
// */
//public class ProOrderYesListViewAdapter extends BaseProAdapter<OrderItemEntry.DataBean> {
//    private Context mContext;
//    private List<OrderItemEntry.DataBean> mOrderNoList;
//    private View.OnClickListener mOnClickListener;
//    private String orderFlag;
//    ImageLoader mImageLoader;
//
//    public ProOrderYesListViewAdapter(Context context, List<OrderItemEntry.DataBean> lists, View.OnClickListener mOnClickListener, String orderFlag) {
//        super(context, lists);
//        this.mContext = context;
//        mImageLoader = new ImageLoader(mContext);
//        this.mOrderNoList = lists;
//        this.mOnClickListener = mOnClickListener;
//        this.orderFlag = orderFlag;
//    }
//
//    public void refresh(Context context, List<OrderItemEntry.DataBean> lists, View.OnClickListener mOnClickListener, String orderFlag) {
//        this.mContext = context;
//        mImageLoader = new ImageLoader(mContext);
//        this.mOrderNoList = lists;
//        this.orderFlag = orderFlag;
//        this.mOnClickListener = mOnClickListener;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            convertView = View.inflate(mContext, R.layout.pro_order_no_listview_item, null);
//            viewHolder = new ViewHolder();
//            viewHolder.mCircleImageView = (CircleImageView) convertView.findViewById(R.id.civ_img);
//            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_name);
//            viewHolder.tvTitle.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
//            viewHolder.tvSkill = (TextView) convertView.findViewById(R.id.tv_title_skill);
//            viewHolder.tvDistance = (TextView) convertView.findViewById(R.id.tv_title_distance);
//            viewHolder.tvLocation = (TextView) convertView.findViewById(R.id.tv_title_location);
//            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_title_time);
//            viewHolder.ivButton = (TextView) convertView.findViewById(R.id.iv_order_no_yes);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        OrderItemEntry.DataBean dataBean = mOrderNoList.get(position);
//        if (!TextUtils.isEmpty(dataBean.getAvatar())) {
//            mImageLoader.DisplayImage(dataBean.getAvatar(), viewHolder.mCircleImageView);
//        } else {
//            viewHolder.mCircleImageView.setImageResource(R.mipmap.img_default);
//        }
//        viewHolder.tvTitle.setText(dataBean.getNickname());
//        viewHolder.tvDistance.setText(dataBean.getKm() + "km");
//        viewHolder.tvLocation.setText(dataBean.getAddress_info());
//        viewHolder.tvTime.setText(TimeUtils.getStrTime(dataBean.getCtime()));
//        viewHolder.ivButton.setTag(position);
//        viewHolder.ivButton.setOnClickListener(mOnClickListener);
//        if ("single".equals(orderFlag)) {
//            viewHolder.tvSkill.setText(AssetsUtils.getSkillName(dataBean.getType(), "zh"));
//            if ("2".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("被选中，准备上门");
//            } else if ("3".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("修改");
//            } else if ("4".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("修改报价等待确认");
//            } else if ("5".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("原价不变等待确认");
//            } else if ("6".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("驳回修改");
//            } else if ("7".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("确认开工");
//            } else if ("8".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("完成");
//            } else if ("9".equals(dataBean.getStaticX()) || "10".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("待支付");
//            } else if ("11".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setText("去评价");
//            }
//            if ("2".equals(dataBean.getStaticX()) || "4".equals(dataBean.getStaticX()) || "5".equals(dataBean.getStaticX()) || "9".equals(dataBean.getStaticX()) || "10".equals(dataBean.getStaticX())) {
//                viewHolder.ivButton.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
//                viewHolder.ivButton.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                        return true;
//                    }
//                });
//            } else {
//                viewHolder.ivButton.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
//                viewHolder.ivButton.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                        return false;
//                    }
//                });
//                viewHolder.ivButton.setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
//            }
////            if (|| "8".equals(dataBean.getStaticX())) {
////                viewHolder.ivButton.setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
////                viewHolder.ivButton.setOnTouchListener(new View.OnTouchListener() {
////                    @Override
////                    public boolean onTouch(View view, MotionEvent motionEvent) {
////                        return false;
////                    }
////                });
////            } else {
////                viewHolder.ivButton.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
////                viewHolder.ivButton.setOnTouchListener(new View.OnTouchListener() {
////                    @Override
////                    public boolean onTouch(View view, MotionEvent motionEvent) {
////                        return true;
////                    }
////                });
////            }
//        } else if ("week".equals(orderFlag)) {
//            viewHolder.tvSkill.setText(StaticUtils.getWeekTypeStr(dataBean.getType()));
//            viewHolder.ivButton.setText(StaticUtils.getWeekStaticStr(dataBean.getStaticX()));
//        }
//        return convertView;
//    }
//
//    class ViewHolder {
//        CircleImageView mCircleImageView;
//        TextView tvTitle, tvSkill, tvDistance, tvLocation, tvTime;
//        TextView ivButton;
//    }
//}
