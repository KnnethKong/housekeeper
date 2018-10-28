package com.haiwai.housekeeper.fragment.server;

import android.content.DialogInterface;
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
import com.haiwai.housekeeper.activity.server.OrderYesDetailActivity;
import com.haiwai.housekeeper.activity.server.OrderYesWeekDetailActivity;
import com.haiwai.housekeeper.activity.server.ProEvaluateActivity;
import com.haiwai.housekeeper.activity.server.ProModifyOfferActivity;
import com.haiwai.housekeeper.activity.server.ProModifyOfferSelfActivity;
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
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderYesFragment extends BaseProFragment implements AdapterView.OnItemClickListener,
        OrderFragment.OnYesOrderListener,
        OrderYesDetailActivity.OnRefreshStatusListener,
        ProModifyOfferActivity.OnPriceChangeListener,
        ProModifyOfferSelfActivity.OnPriceChangeListener,
        ProEvaluateActivity.OnEvasChangeListener, XListView.IXListViewListener {
    private XListView lv_order_yes;
    //装载测试数据
    ArrayList<OrderItemWeekEntity.DataBean> mOrderYesList = new ArrayList<>();
    ArrayList<OrderItemEntry.DataBean> sOrderYesList = new ArrayList<>();
    private User user;
    private String uid;
    private static final String staticStr = "2";
    private List<String> typeList = new ArrayList<>();
    private String km = "";
    private String is_at = "";
    private String sort = "1";
    private String orderFlag = OrderFragment.orderFlag;
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
        OrderFragment.setOnYesOrderListener(this);
        OrderYesDetailActivity.setOnRefreshStatusListener(this);
        ProModifyOfferActivity.setOnPriceChangeListener(this);
        ProModifyOfferSelfActivity.setOnPriceChangeListener(this);
        ProEvaluateActivity.setOnEvasChangeListener(this);
    }

    public View initView() {
        View orderYesView = View.inflate(context, R.layout.pro_fragment_order_yes, null);
        initView(orderYesView);
        return orderYesView;
    }

    private void initView(View orderYesView) {
        lv_order_yes = (XListView) orderYesView.findViewById(R.id.lv_order_yes);
        lv_order_yes.setOnItemClickListener(this);
        lv_order_yes.setXListViewListener(this);
        lv_order_yes.setPullRefreshEnable(true);
        lv_order_yes.setPullLoadEnable(true);
        lv_order_yes.setRefreshTime(System.currentTimeMillis());
//        mProOrderYesListViewAdapter = new ProOrderYesListViewAdapter(context, mOrderYesList, this, orderFlag);
//        lv_order_yes.setAdapter(mProOrderYesListViewAdapter);
//        mProOrderYesListViewAdapter.notifyDataSetChanged();
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


    @Override
    public void onResume() {
        super.onResume();
        initLocationData();
        page = 1;
        isLoadMore = false;
        isLoading = false;
        lv_order_yes.setPullLoadEnable(true);
        initData(orderFlag, km, is_at, sort, page, typeList);
    }

    private void initData(String orderFlag, String km, String is_at, String sort, int page, List<String> typeList) {
        if (isLoading) {
            LoadDialog.showProgressDialog(context);
        }
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }

        if(sOrderYesList!=null&&sigleAdapter!=null&&!isLoadMore){
            sOrderYesList.clear();
            sigleAdapter.notifyDataSetChanged();

        }

        if(mOrderYesList!=null&&multiAdapter!=null&&!isLoadMore){
            mOrderYesList.clear();
            multiAdapter.notifyDataSetChanged();
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("static", staticStr);
        map.put("km", km);
        map.put("is_at", is_at);
        map.put("sort", sort);
        map.put("lat", mSigGogleEntity.getLat());
        map.put("long",  mSigGogleEntity.getLng());
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
            mOrderYesList.clear();
            if (multiAdapter != null) {
                multiAdapter.notifyDataSetChanged();
            }
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.order_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>已接单列表>>>>>>>" + response);
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
            sOrderYesList.clear();
            if (sigleAdapter != null) {
                sigleAdapter.notifyDataSetChanged();
            }
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.self_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>周期已接单列表>>>>>>>" + response);
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
            if(orderItemEntry.getData()!=null&&orderItemEntry.getData().size()!=0){
                mOrderYesList.clear();
            }
            if (!isLoadMore) {

                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    mOrderYesList.add(orderItemEntry.getData().get(i));
                }
            } else {
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    mOrderYesList.add(orderItemEntry.getData().get(i));
                }
                if (orderItemEntry.getData().size() < Integer.valueOf(PAGE_SIZE)) {
                    --page;
                    lv_order_yes.setPullLoadEnable(false);
                }
            }
        }
        lv_order_yes.stopRefresh();
        lv_order_yes.stopLoadMore();
        multiAdapter = new MyAdapter<OrderItemWeekEntity.DataBean>(mOrderYesList, R.layout.pro_order_no_listview_item) {
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
                        bundle.putString("uid", obj.getUid());
                        bundle.putString("oid", obj.getId());
                        bundle.putString("nickname", obj.getNickname());
                        bundle.putString("type", obj.getType());
                        bundle.putString("choose", "1");
                        bundle.putBoolean("isServer", true);
                        ActivityTools.goNextActivity(getActivity(), ProDetailActivity.class, bundle);
                    }
                });
                if("29".equals(obj.getType())){
                    if(isZhorEn.equals("en")){
                        ((TextView) holder.getView(R.id.tv_title_skill)).setText("Vacant Property\nManagement");

                    }else{
                        ((TextView) holder.getView(R.id.tv_title_skill)).setText(StaticUtils.getWeekTypeStr(obj.getType(), isZhorEn));
                    }

                }else{
                    ((TextView) holder.getView(R.id.tv_title_skill)).setText(StaticUtils.getWeekTypeStr(obj.getType(), isZhorEn));

                }
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
                ((TextView) holder.getView(R.id.tv_title_name)).setText(obj.getNickname());
                ((TextView) holder.getView(R.id.tv_title_location)).setText(obj.getAddress_info());

                ((TextView) holder.getView(R.id.tv_title_distance)).setText(obj.getKm() + "km");
                holder.setText(R.id.tv_title_new_time, TimeUtils.getStrTime(obj.getCtime()));
                ((TextView) holder.getView(R.id.iv_order_no_yes)).setText(StaticUtils.getWeekStaticStr(context,obj.getStaticX()));
                ((TextView) holder.getView(R.id.iv_order_no_yes)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner_white);
                ((TextView) holder.getView(R.id.iv_order_no_yes)).setTextColor(getResources().getColor(R.color.black2));
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
        lv_order_yes.setAdapter(multiAdapter);
        multiAdapter.notifyDataSetChanged();
        LoadDialog.closeProgressDialog();
    }

    private void bindData(OrderItemEntry orderItemEntry) {
        if (orderItemEntry != null) {
            if (!isLoadMore) {
                sOrderYesList.clear();
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    sOrderYesList.add(orderItemEntry.getData().get(i));
                }
            } else {
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    sOrderYesList.add(orderItemEntry.getData().get(i));
                }
                if (orderItemEntry.getData().size() < Integer.valueOf(PAGE_SIZE)) {
                    --page;
                    lv_order_yes.setPullLoadEnable(false);
                }
            }
        }
        lv_order_yes.stopRefresh();
        lv_order_yes.stopLoadMore();
//        mProOrderYesListViewAdapter.refresh(context, sOrderYesList, this, orderFlag);
        sigleAdapter = new MyAdapter<OrderItemEntry.DataBean>(sOrderYesList, R.layout.pro_order_no_listview_single_item) {
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
                String str = AssetsUtils.getSkillName(obj.getType(),isZhorEn);
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    String[] strS= str.split(" ");
                    str = "";
                    if(strS.length==2){
                        str = strS[0]+"\n"+strS[1];
                    }else if(strS.length==3){
                        str = strS[0]+"\n"+strS[1]+" "+strS[2];
                    }else if(strS.length>=4){
                        for(int i=0;i<strS.length;i++){
                            if(i==1){
                                str = str+" "+strS[i] +"\n";
                            }else{
                                str = str+" "+strS[i];
                            }

                        }
                    }
                }
                ((TextView) holder.getView(R.id.tv_title_name_a)).setText(obj.getNickname());
                ((TextView) holder.getView(R.id.tv_title_skill_a)).setText(str);
                ((TextView) holder.getView(R.id.tv_title_location_a)).setText(obj.getAddress_info());
                ((TextView) holder.getView(R.id.tv_title_time_a)).setText(TimeUtils.getStrTime(obj.getCtime()));
                ((TextView) holder.getView(R.id.tv_title_distance_a)).setText(obj.getKm() + "km");
                if (uid.equals(obj.getAt_uid())) {
                    holder.setVisibility(R.id.img_ty, View.VISIBLE);
                } else {
                    holder.setVisibility(R.id.img_ty, View.GONE);
                }

                String staticX = obj.getStaticX();//是否报价
                if ("2".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_y1);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setTextColor(Color.parseColor("#B5B5B5"));
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_y3);//--------------状态显示
//                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(android.R.color.transparent);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else if ("3".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_y2);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_y4);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
//                    holder.setOnClickListener(R.id.iv_order_no_yes_a, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            modifyPrice(obj);
//                        }
//                    });
                } else if ("4".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_y5);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_y6);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else if ("5".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_y7);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_y8);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else if ("6".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_y9);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setTextColor(Color.parseColor("#ffffff"));
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_y10);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
                    holder.setOnClickListener(R.id.iv_order_no_yes_a, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            modifyPrice(obj);
                        }
                    });
                } else if ("7".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_y11);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_y12);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
                    holder.setOnClickListener(R.id.iv_order_no_yes_a, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
                            customBuilder.setMessage(getString(R.string.con_ti1)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            submitKgOrder(obj);
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton(getString(R.string.message_alert_no),
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                    .create().show();
                        }
                    });
                } else if ("8".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_y13);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_y14);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
                    holder.setOnClickListener(R.id.iv_order_no_yes_a, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
                            customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.con_sti1)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finishOrder(obj);
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton(getString(R.string.message_alert_no),
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                    .create().show();
                        }
                    });
                } else if ("9".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_w1);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_p1);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else if ("10".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_9);
                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_p1);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else if ("11".equals(staticX)) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.status_ep1);
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setTextColor(Color.parseColor("#ffffff"));

                    ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_ep2);//--------------状态显示
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
                    holder.setOnClickListener(R.id.iv_order_no_yes_a, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, ProEvaluateActivity.class);
                            intent.putExtra("flag", true);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("dataBean", obj);
                            intent.putExtra("oid", obj.getId());
                            intent.putExtra("ouid", obj.getUid());//发布人id
                            intent.putExtra("bundle", bundle);
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        lv_order_yes.setAdapter(sigleAdapter);
        sigleAdapter.notifyDataSetChanged();
        LoadDialog.closeProgressDialog();
    }

    private void modifyPrice(OrderItemEntry.DataBean obj) {
        if ("1".equals(obj.getService_type())) {
            Intent intent = new Intent(context, ProModifyOfferSelfActivity.class);
            intent.putExtra("flag", true);
            intent.putExtra("id", obj.getId());
            intent.putExtra("uid", uid);
            startActivity(intent);
        } else if ("2".equals(obj.getService_type())) {
            Intent intent = new Intent(context, ProModifyOfferActivity.class);
            intent.putExtra("flag", true);
            intent.putExtra("id", obj.getId());
            intent.putExtra("uid", uid);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ("single".equals(orderFlag)) {
            OrderItemEntry.DataBean dataBean = sOrderYesList.get(position - 1);
            Intent intent = new Intent();
            intent.putExtra("id", dataBean.getId());
            intent.setClass(context, OrderYesDetailActivity.class);
            startActivity(intent);
        } else if ("week".equals(orderFlag)) {
            OrderItemWeekEntity.DataBean dataBean = mOrderYesList.get(position - 1);
            Intent intent = new Intent();
            intent.putExtra("id", dataBean.getId());
            intent.putExtra("static",dataBean.getStaticX());
            intent.putExtra("type", dataBean.getType());//1、房屋巡视2、家政服务3、园艺管理、4、租赁-招租、5、租赁-管理
            intent.setClass(context, OrderYesWeekDetailActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onYesTerm(String orderFlag, String km, String is_at, String sort, List<String> strList) {

        this.orderFlag = orderFlag;
        this.km = km;
        this.is_at = is_at;
        this.sort = sort;
        if (strList != null) {
            typeList.clear();
            for (int i = 0; i < strList.size(); i++) {
                typeList.add(AssetsUtils2.getTypeByName(strList.get(i), isZhorEn, orderFlag));
            }
        }
        isLoading = true;
        page = 1;
        initData(orderFlag, km, is_at, sort, page, typeList);



//        this.orderFlag = orderFlag;
//        this.km = km;
//        this.is_at = is_at;
//        this.sort = sort;
//        if (strList != null) {
//            typeList.clear();
//            for (int i = 0; i < strList.size(); i++) {
//                typeList.add(AssetsUtils2.getTypeByName(strList.get(i), isZhorEn, orderFlag));
//            }
//        }
//        page = 1;
//        isLoading = true;
//        initData(orderFlag, km, is_at, sort, page, typeList);
    }

    @Override
    public void onNewDada() {
        initData();
    }

    @Override
    public void changeDatas() {
        initData();
    }

    @Override
    public void changeData() {
        initData();
    }

    @Override
    public void changesStatus() {
        initData();
    }

    @Override
    public void onRefresh() {
        page = 1;
        isLoadMore = false;
        isLoading = false;
        lv_order_yes.setPullLoadEnable(true);
        initData(orderFlag, km, is_at, sort, page, typeList);
    }

    @Override
    public void onLoadMore() {
        page++;
        isLoadMore = true;
        isLoading = false;
        initData(orderFlag, km, is_at, sort, page, typeList);
    }

    private void submitKgOrder(OrderItemEntry.DataBean dataBean) {
        Map<String, String> map = new HashMap<>();
        map.put("oid", dataBean.getId());
        map.put("ouid", dataBean.getUid());
        map.put("uid", uid);
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.startg, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    onRefresh();
                } else {
                    if(code!=1114){
                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }
        }));
    }

    private void finishOrder(OrderItemEntry.DataBean dataBean) {
        Map<String, String> map = new HashMap<>();
        map.put("oid", dataBean.getId());
        map.put("uid", dataBean.getUid());
        map.put("j_uid", uid);
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.order_complete, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    onRefresh();
                } else {
                    if(code!=1114){
                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }
        }));
    }

    public void setRefData() {
        onRefresh();
    }
}
