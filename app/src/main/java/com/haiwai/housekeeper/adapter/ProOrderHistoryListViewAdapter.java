//package com.haiwai.housekeeper.adapter;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.text.TextUtils;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.haiwai.housekeeper.R;
//import com.haiwai.housekeeper.base.BaseProAdapter;
//import com.haiwai.housekeeper.entity.OrderItemEntry;
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
//public class ProOrderHistoryListViewAdapter extends BaseProAdapter<OrderItemEntry.DataBean> {
//    private Context mContext;
//    private List<OrderItemEntry.DataBean> mOrderHisList;
//    private View.OnClickListener mOnClickListener;
//    private String orderFlag;
//    private ImageLoader mImageLoader;
//
//    public ProOrderHistoryListViewAdapter(Context context, List<OrderItemEntry.DataBean> lists, View.OnClickListener mOnClickListener, String orderFlag) {
//        super(context, lists);
//        this.mContext = context;
//        mImageLoader = new ImageLoader(mContext);
//        this.mOrderHisList = lists;
//        this.orderFlag = orderFlag;
//        this.mOnClickListener = mOnClickListener;
//    }
//
//    public void refresh(Context context, List<OrderItemEntry.DataBean> lists, View.OnClickListener mOnClickListener, String orderFlag) {
//        this.mContext = context;
//        mImageLoader = new ImageLoader(mContext);
//        this.mOrderHisList = lists;
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
//        OrderItemEntry.DataBean dataBean = mOrderHisList.get(position);
//        if (!TextUtils.isEmpty(dataBean.getAvatar())) {
//            mImageLoader.DisplayImage(dataBean.getAvatar(), viewHolder.mCircleImageView);
//        } else {
//            viewHolder.mCircleImageView.setImageResource(R.mipmap.img_default);
//        }
//        viewHolder.tvTitle.setText(dataBean.getNickname());
//        viewHolder.tvDistance.setText(dataBean.getKm() + "km");
//        viewHolder.tvLocation.setText(dataBean.getAddress_info());
//        viewHolder.tvTime.setText(TimeUtils.getStrTime(dataBean.getCtime()));
//        if ("single".equals(orderFlag)){
//            viewHolder.tvSkill.setText(AssetsUtils.getSkillName(dataBean.getType(),"zh"));
//            if ("1".equals(dataBean.getIs_fpin())) {
//                viewHolder.ivButton.setText("查看评价");
//                viewHolder.ivButton.setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
//                viewHolder.ivButton.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                        return false;
//                    }
//                });
//            } else {
//                viewHolder.ivButton.setText(StaticUtils.getStaticStr(dataBean.getStaticX()));
//                viewHolder.ivButton.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
//                viewHolder.ivButton.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                        return true;
//                    }
//                });
//            }
//        } else if("week".equals(orderFlag)){//周期订单
//            viewHolder.tvSkill.setText(StaticUtils.getWeekTypeStr(dataBean.getType(),is));
//            viewHolder.ivButton.setText(StaticUtils.getWeekStaticStr(dataBean.getStaticX()));
//            viewHolder.ivButton.setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
//            viewHolder.ivButton.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    return true;
//                }
//            });
//        }
//        viewHolder.ivButton.setTag(position);
//        viewHolder.ivButton.setOnClickListener(mOnClickListener);
//        return convertView;
//    }
//
//    class ViewHolder {
//        CircleImageView mCircleImageView;
//        TextView tvTitle, tvSkill, tvDistance, tvLocation, tvTime;
//        TextView ivButton;
//    }
//}
