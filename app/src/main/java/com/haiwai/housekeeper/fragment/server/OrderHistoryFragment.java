package com.haiwai.housekeeper.fragment.server;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.OrderHisDetailActivity;
import com.haiwai.housekeeper.activity.server.OrderHisWeekDetailActivity;
import com.haiwai.housekeeper.activity.server.ProEvaluateActivity;
import com.haiwai.housekeeper.activity.server.ProEvaluateShowActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.OrderItemEntry;
import com.haiwai.housekeeper.entity.OrderItemWeekEntity;
import com.haiwai.housekeeper.entity.SigGogleEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.AssetsUtils2;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LocationUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.StaticUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.xlistview.XListView;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHistoryFragment extends BaseProFragment implements AdapterView.OnItemClickListener,
        OrderFragment.OnHisOrderListener,
        ProEvaluateActivity.OnEvaChangeListener, XListView.IXListViewListener {
    private XListView lv_order_history;
    //装载测试数据
    ArrayList<OrderItemEntry.DataBean> sOrderHisList = new ArrayList<>();
    ArrayList<OrderItemWeekEntity.DataBean> mOrderHisList = new ArrayList<>();
    private User user;
    private String uid;
    private static final String staticStr = "3";
    private List<String> typeList = new ArrayList<>();
    private String km = "";
    private String is_at = "";
    private String sort = "1";
    private String orderFlag = "single";
    private int page = 1;
    private static final String PAGE_SIZE = "10";
    private boolean isLoadMore = false;
    boolean isLoading = true;
    MyAdapter sigleAdapter, multiAdapter;
    private String isZhorEn = "";
    SigGogleEntity mSigGogleEntity;
    @Override
    protected void init() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        OrderFragment.setOnHisOrderListener(this);
        ProEvaluateActivity.setOnEvaChangeListener(this);
    }


    @Override
    public View initView() {
        View orderHistoryView = View.inflate(context, R.layout.pro_fragment_order_history, null);
        initView(orderHistoryView);
        return orderHistoryView;
    }

    private void initView(View orderYesView) {
        lv_order_history = (XListView) orderYesView.findViewById(R.id.lv_order_history);
        lv_order_history.setOnItemClickListener(this);
        lv_order_history.setXListViewListener(this);
        lv_order_history.setPullRefreshEnable(true);
        lv_order_history.setPullLoadEnable(true);
        lv_order_history.setRefreshTime(System.currentTimeMillis());

    }

    @Override
    protected void initData() {
        initLocationData();
        page = 1;
        isLoading = true;
        initData(orderFlag, km, is_at, sort, page, typeList);
    }

    private void initLocationData() {
        LocationUtils locationUtils = new LocationUtils(getActivity());
        String str = locationUtils.getGeoStr();
        mSigGogleEntity = LocationUtils.parStr(str);
    }

    private void initData(String orderFlag, String km, String is_at, String sort, int page, List<String> typeList) {
        if (isLoading) {
            LoadDialog.showProgressDialog(context);
        }
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }

        if(mOrderHisList!=null&&multiAdapter!=null&&!isLoadMore){
            mOrderHisList.clear();
            multiAdapter.notifyDataSetChanged();
        }

        if(sOrderHisList!=null&&multiAdapter!=null&&!isLoadMore){
            sOrderHisList.clear();
            multiAdapter.notifyDataSetChanged();
        }

        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("static", staticStr);
        map.put("km", km);
        map.put("is_at", is_at);
        map.put("sort", sort);
        map.put("lat",  mSigGogleEntity.getLat());
        map.put("long", mSigGogleEntity.getLng());
        map.put("page", String.valueOf(page));
        map.put("page_size", PAGE_SIZE);
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        if (typeList != null) {
            for (int i = 0; i < typeList.size(); i++) {
                int num = i + 1;
                String strKey = "type[" + num + "]";
                String strValue = typeList.get(i);
                map.put(strKey, strValue);
            }
        }
        if ("single".equals(orderFlag)) {
            mOrderHisList.clear();
            if (multiAdapter != null) {
                multiAdapter.notifyDataSetChanged();
            }
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.order_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>o2o历史列表>>>>>>>" + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        OrderItemEntry orderItemEntry = new Gson().fromJson(response, OrderItemEntry.class);
                        bindData(orderItemEntry);
                    } else {
                        LoadDialog.closeProgressDialog();
                        if (code != 1111&&code!=1114) {
                            ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }
            }));
        } else if ("week".equals(orderFlag)) {
            sOrderHisList.clear();
            if (sigleAdapter != null) {
                sigleAdapter.notifyDataSetChanged();
            }
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.self_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>自营订单历史列表>>>>>>>" + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        OrderItemWeekEntity orderItemEntry = new Gson().fromJson(response, OrderItemWeekEntity.class);
                        bindMData(orderItemEntry);
                    } else {
                        LoadDialog.closeProgressDialog();
                        if (code != 1111&&code!=1114) {
                            ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }
            }));
        }


    }

    private void bindMData(OrderItemWeekEntity orderItemEntry) {
        if (orderItemEntry != null) {
            if (!isLoadMore) {
                mOrderHisList.clear();
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    mOrderHisList.add(orderItemEntry.getData().get(i));
                }
            } else {
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    mOrderHisList.add(orderItemEntry.getData().get(i));
                }
                if (orderItemEntry.getData().size() < Integer.valueOf(PAGE_SIZE)) {
                    --page;
                    lv_order_history.setPullLoadEnable(false);
                }
            }
        }
        lv_order_history.stopRefresh();
        lv_order_history.stopLoadMore();
//        mProOrderHistoryListViewAdapter.refresh(context, mOrderHisList, this, orderFlag);
        multiAdapter = new MyAdapter<OrderItemWeekEntity.DataBean>(mOrderHisList, R.layout.pro_order_no_listview_item) {
            @Override
            public void bindView(final ViewHolder holder, final OrderItemWeekEntity.DataBean obj) {
                if (TextUtils.isEmpty(obj.getAvatar())) {
                    ((CircleImageView) holder.getView(R.id.civ_img)).setImageResource(R.mipmap.moren_head);
                } else {
                    ImageLoader.getInstance().displayImage(obj.getAvatar(), (CircleImageView) holder.getView(R.id.civ_img), ImageLoaderUtils.getAvatarOptions());
                }
                holder.getView(R.id.civ_img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("oid", obj.getId());
                        bundle.putString("nickname", obj.getNickname());
                        bundle.putString("type", obj.getType());
                        bundle.putString("choose", "1");
                        bundle.putBoolean("isServer", true);
                        ActivityTools.goNextActivity(getActivity(), ProDetailActivity.class, bundle);
                    }
                });
                ((TextView) holder.getView(R.id.tv_title_name)).setText(obj.getNickname());
                if("29".equals(obj.getType())){
                    if(isZhorEn.equals("en")){
                        ((TextView) holder.getView(R.id.tv_title_skill)).setText("Vacant Property\nManagement");
                    }else{
                        ((TextView) holder.getView(R.id.tv_title_skill)).setText(StaticUtils.getWeekTypeStr(obj.getType(), isZhorEn));
                    }
                }else{
                    ((TextView) holder.getView(R.id.tv_title_skill)).setText(StaticUtils.getWeekTypeStr(obj.getType(), isZhorEn));

                }
                ((TextView) holder.getView(R.id.tv_title_location)).setText(obj.getAddress_info());
                if (isZhorEn.equals("en")) {
                    if(obj.getNum().equals("0")){//--0123次/月 分别对应 0/month ;Once/month；Twice/month；Three times/month
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText("0" + "/" + getString(R.string.order_ti11));

                    }else if(obj.getNum().equals("1")){
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Once" + "/" + getString(R.string.order_ti11));

                    }else if(obj.getNum().equals("2")){
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Twice" + "/" + getString(R.string.order_ti11));

                    }else if(obj.getNum().equals("3")){
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Three " + getString(R.string.orer_ti2) + "/" + getString(R.string.order_ti11));

                    }
                } else {
                    ((TextView) holder.getView(R.id.tv_title_week_time)).setText(obj.getNum() + getString(R.string.orer_ti2) + "/" + getString(R.string.order_ti11));

                }
                ((TextView) holder.getView(R.id.tv_title_distance)).setText(obj.getKm() + "km");
                holder.setText(R.id.tv_title_new_time, TimeUtils.getStrTime(obj.getCtime()));
                ((TextView) holder.getView(R.id.iv_order_no_yes)).setText(StaticUtils.getWeekStaticStr(context,obj.getStaticX()));
                ((TextView) holder.getView(R.id.iv_order_no_yes)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                ((TextView) holder.getView(R.id.iv_order_no_yes)).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
//                if (uid.equals(obj.getAt_uid())) {
//                    holder.setVisibility(R.id.img_ty, View.VISIBLE);
//                } else {
//                    holder.setVisibility(R.id.img_ty, View.GONE);
//                }
//                int is_jie = obj.getIs_jie();//是否报价
//                if (is_jie == 1) {
//                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setText("待响应");
//                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
//                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View view, MotionEvent motionEvent) {
//                            return true;
//                        }
//                    });
//                } else if (is_jie == 0) {
//                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setText("抢 单");
//                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View view, MotionEvent motionEvent) {
//                            return false;
//                        }
//                    });
//                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
//                }

            }
        };
        lv_order_history.setAdapter(multiAdapter);
        multiAdapter.notifyDataSetChanged();
        LoadDialog.closeProgressDialog();
    }

    private void bindData(OrderItemEntry orderItemEntry) {
        if (orderItemEntry != null) {
            if (!isLoadMore) {
                sOrderHisList.clear();
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    sOrderHisList.add(orderItemEntry.getData().get(i));
                }
            } else {
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    sOrderHisList.add(orderItemEntry.getData().get(i));
                }
                if (orderItemEntry.getData().size() < Integer.valueOf(PAGE_SIZE)) {
                    --page;
                    lv_order_history.setPullLoadEnable(false);
                }
            }
        }
        lv_order_history.stopRefresh();
        lv_order_history.stopLoadMore();
//        mProOrderHistoryListViewAdapter.refresh(context, sOrderHisList, this, orderFlag);
        sigleAdapter = new MyAdapter<OrderItemEntry.DataBean>(sOrderHisList, R.layout.pro_order_no_listview_single_item) {
            @Override
            public void bindView(final ViewHolder holder, final OrderItemEntry.DataBean obj) {
//                private CircleImageView civ_img_a;
//                private TextView tv_title_name_a,tv_title_skill_a,tv_title_location_a,tv_title_time_a,tv_title_distance_a,tv_title_week_time_a,iv_order_no_yes_a;
                if (TextUtils.isEmpty(obj.getAvatar())) {
                    ((CircleImageView) holder.getView(R.id.civ_img_a)).setImageResource(R.mipmap.moren_head);
                } else {
                    ImageLoader.getInstance().displayImage(obj.getAvatar(), (CircleImageView) holder.getView(R.id.civ_img_a), ImageLoaderUtils.getAvatarOptions());
                }
                holder.getView(R.id.civ_img_a).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("uid", obj.getUid());
                        bundle.putString("oid", obj.getId());
                        bundle.putString("nickname", obj.getNickname());
                        bundle.putString("type", obj.getType());
                        bundle.putString("choose", "1");
                        bundle.putBoolean("isServer", true);
                        ActivityTools.goNextActivity(getActivity(), ProDetailActivity.class, bundle);
                    }
                });
                ((TextView) holder.getView(R.id.tv_title_name_a)).setText(obj.getNickname());
                ((TextView) holder.getView(R.id.tv_title_skill_a)).setText(AssetsUtils.getSkillName(obj.getType(), isZhorEn));
                ((TextView) holder.getView(R.id.tv_title_location_a)).setText(obj.getAddress_info());
                ((TextView) holder.getView(R.id.tv_title_time_a)).setText(TimeUtils.getStrTime(obj.getCtime()));
                ((TextView) holder.getView(R.id.tv_title_distance_a)).setText(obj.getKm() + "km");
                if (uid.equals(obj.getAt_uid())) {
                    holder.setVisibility(R.id.img_ty, View.VISIBLE);
                } else {
                    holder.setVisibility(R.id.img_ty, View.GONE);
                }

                String staticX = obj.getStaticX();//是否报价
                if ("11".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_h1);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_h2);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setTextColor(Color.parseColor("#B5B5B5"));
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.evalue_no_slide_shape);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getContext(),ProEvaluateShowActivity.class);
                            intent.putExtra("ouid",obj.getUid());
                            intent.putExtra("oid",obj.getId());
                            startActivity(intent);
                        }
                    });
                } else if ("12".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_h3);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_h4);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else if ("13".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_h5);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_h6);//--------------状态显示
//                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(obj.getNickname());//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_h7);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_h8);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                }
            }
        };
        lv_order_history.setAdapter(sigleAdapter);
        sigleAdapter.notifyDataSetChanged();
        LoadDialog.closeProgressDialog();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ("single".equals(orderFlag)) {
            OrderItemEntry.DataBean dataBean = sOrderHisList.get(position - 1);
            Intent intent = new Intent();
            intent.putExtra("id", dataBean.getId());
            intent.putExtra("uid",dataBean.getUid());

            intent.setClass(context, OrderHisDetailActivity.class);
            startActivity(intent);
        } else if ("week".equals(orderFlag)) {
            OrderItemWeekEntity.DataBean dataBean = mOrderHisList.get(position - 1);
            Intent intent = new Intent();
            intent.putExtra("id", dataBean.getId());
            intent.putExtra("type", dataBean.getType());
            intent.setClass(context, OrderHisWeekDetailActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public void onHisTerm(String orderFlag, String km, String is_at, String sort, List<String> strList) {
        this.orderFlag = orderFlag;
        this.km = km;
        this.is_at = is_at;
        this.sort = sort;
        if (strList != null) {
            typeList.clear();
            for (int i = 0; i < strList.size(); i++) {
                typeList.add(AssetsUtils2.getTypeByName(strList.get(i), isZhorEn,orderFlag));
            }
        }
        page = 1;
        isLoading = true;
        initData(orderFlag, km, is_at, sort, page, typeList);
    }

    @Override
    public void changeStatus() {
        initData();
    }

    @Override
    public void onRefresh() {
        page = 1;
        isLoadMore = false;
        isLoading = false;
        lv_order_history.setPullLoadEnable(true);
        initData(orderFlag, km, is_at, sort, page, typeList);
    }

    @Override
    public void onLoadMore() {
        page++;
        isLoadMore = true;
        isLoading = false;
        initData(orderFlag, km, is_at, sort, page, typeList);
    }
}
