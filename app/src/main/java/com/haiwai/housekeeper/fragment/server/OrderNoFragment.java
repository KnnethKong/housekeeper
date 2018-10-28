package com.haiwai.housekeeper.fragment.server;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.OrderDetailActivity;
import com.haiwai.housekeeper.activity.server.OrderWeekDetailActivity;
import com.haiwai.housekeeper.activity.server.ProMainActivity;
import com.haiwai.housekeeper.activity.server.ProOfferActivity;
import com.haiwai.housekeeper.activity.server.RecommendedFeesActivity;
import com.haiwai.housekeeper.activity.user.BindPayPalAccount;
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
import com.haiwai.housekeeper.utils.Event;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haiwai.housekeeper.base.MyApp.context;
import static com.haiwai.housekeeper.base.MyApp.getContext;

public class OrderNoFragment extends BaseProFragment implements ProOfferActivity.OrderRefreshDataListener,
        AdapterView.OnItemClickListener,
        OrderFragment.OnNoOrderListener,
        OrderWeekDetailActivity.OrderWeekRefreshDataListener, XListView.IXListViewListener {
    private XListView lv_order_no, lv_order_no1;
    //装载测试数据
    ArrayList<OrderItemEntry.DataBean> sOrderNoList = new ArrayList<>();
    ArrayList<OrderItemWeekEntity.DataBean> mOrderNoList = new ArrayList<>();
    //    private ProOrderNoListViewAdapter mProOrderNoListViewAdapter;
    private User user;
    private String uid;
    private static final String staticStr = "1";
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
    //------------------------
    private String isZhorEn = "";
    SigGogleEntity mSigGogleEntity;


    private int olderSingleCount = 1;
    private int olderMusingleCount = 1;

    private boolean isSingleLoadmore = false;
    private boolean isDouobleLoadmore = false;

    @Override
    protected void init() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        OrderFragment.setOnNoOrderListener(this);
        ProOfferActivity.setOrderRefreshDataListener(this);
        OrderWeekDetailActivity.setOrderWeekRefreshDataListener(this);
    }

    @Override
    public View initView() {
        de.greenrobot.event.EventBus.getDefault().register(this);
        View orderNoView = View.inflate(context, R.layout.pro_fragment_order_no, null);
        initView(orderNoView);
        return orderNoView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        de.greenrobot.event.EventBus.getDefault().unregister(this);
    }

    public void onEvent(Event event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("harvic", msg);

        if (event.getMsg().equals("pay")) {
            isLoading = false;
            isLoadMore = false;
            lv_order_no.setPullLoadEnable(true);
            isSingleLoadmore = false;
            isDouobleLoadmore = false;

            initData(orderFlag, km, is_at, sort, page, typeList);
        }
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

    private void initData(final String orderFlag, String km, String is_at, String sort, int page, List<String> typeList) {
        if (isLoading) {
            LoadDialog.showProgressDialog(context);
        }
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            uid = user.getUid();
        }
        if (sOrderNoList != null && sigleAdapter != null && !isLoadMore) {
            sOrderNoList.clear();
            sigleAdapter.notifyDataSetChanged();

        }

        if (mOrderNoList != null && multiAdapter != null && !isLoadMore) {
            mOrderNoList.clear();
            multiAdapter.notifyDataSetChanged();
        }
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("static", staticStr);
        map.put("km", km);
        map.put("is_at", is_at);
        map.put("sort", sort);
        map.put("lat", mSigGogleEntity.getLat());
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
                Log.i("strValue", strValue);
                map.put(strKey, strValue);
            }
        }
        if ("single".equals(orderFlag)) {
            mOrderNoList.clear();
            if (multiAdapter != null) {
                multiAdapter.notifyDataSetChanged();
            }

            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.order_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>o2o列表>>>>>>>" + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        OrderItemEntry orderItemEntry = new Gson().fromJson(response, OrderItemEntry.class);
                        bindData(orderItemEntry);
                    } else if (code == 1111) {
                        LoadDialog.closeProgressDialog();
                        lv_order_no.stopRefresh();
                        lv_order_no.stopLoadMore();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(R.string.no_order_str)
                                .setNegativeButton(R.string.order_ti2, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                        if (getActivity() instanceof ProMainActivity) {
                                            ((ProMainActivity) getActivity()).setFragment();
                                        }
                                    }
                                })
                                .setPositiveButton(R.string.order_ti1, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();

                                    }
                                }).create().show();
                    } else if (code == 1114) {
                        LoadDialog.closeProgressDialog();
                        lv_order_no.stopRefresh();
                        lv_order_no.stopLoadMore();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(R.string.order_ti3)
                                .setNegativeButton(R.string.order_ti4, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                                .setPositiveButton(R.string.order_ti5, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                        if (getActivity() instanceof ProMainActivity) {
                                            ((ProMainActivity) getActivity()).setFragment();
                                        }
                                    }
                                }).create().show();
                    } else {
                        lv_order_no.stopRefresh();
                        lv_order_no.stopLoadMore();
                        LoadDialog.closeProgressDialog();
//                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        } else if ("week".equals(orderFlag)) {
            sOrderNoList.clear();
            if (sigleAdapter != null) {
                sigleAdapter.notifyDataSetChanged();
            }

            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.self_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>周期订单列表>>>>>>>" + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        OrderItemWeekEntity orderItemEntry = new Gson().fromJson(response, OrderItemWeekEntity.class);
                        bindMData(orderItemEntry);
                    } else {
                        LoadDialog.closeProgressDialog();
                        lv_order_no.stopRefresh();
                        lv_order_no.stopLoadMore();
                        if (sigleAdapter != null) {
                            sigleAdapter.clear();
                            sigleAdapter.notifyDataSetChanged();
                        }
                        if (code != 1302 && code != 1114) {
                            ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                        } else {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle(R.string.app_tip).setMessage(R.string.no_self_skill)
                                    .setNegativeButton(R.string.message_alert_no, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    })
                                    .setPositiveButton(R.string.customer_service, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(Intent.ACTION_DIAL);
                                            Uri data = Uri.parse("tel:" + "950-45580098");
                                            intent.setData(data);
                                            startActivity(intent);
                                            dialogInterface.dismiss();

                                        }
                                    }).create().show();
                        }
                    }
                }
            }));
        }
    }

    private void bindMData(OrderItemWeekEntity orderItemEntry) {
        if (orderItemEntry != null) {
            if (!isLoadMore) {
                //刷新
                mOrderNoList.clear();
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    mOrderNoList.add(orderItemEntry.getData().get(i));
                }
            } else {
                //加载
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    mOrderNoList.add(orderItemEntry.getData().get(i));
                }
                if (orderItemEntry.getData().size() < Integer.valueOf(PAGE_SIZE)) {
                    --page;
                    lv_order_no.setPullLoadEnable(false);
                }
            }
        }
        lv_order_no.stopRefresh();
        lv_order_no.stopLoadMore();
        multiAdapter = new MyAdapter<OrderItemWeekEntity.DataBean>(mOrderNoList, R.layout.pro_order_no_listview_item) {
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
                ((TextView) holder.getView(R.id.tv_title_name)).setText(obj.getNickname());
                if ("29".equals(obj.getType())) {
                    if (isZhorEn.equals("en")) {
                        ((TextView) holder.getView(R.id.tv_title_skill)).setText("Vacant Property\nManagement");
                        if (obj.getNum().equals("0")) {//--0123次/月 分别对应 0/month ;Once/month；Twice/month；Three times/month
                            ((TextView) holder.getView(R.id.tv_title_week_time)).setText("0" + "/" + getString(R.string.order_ti11));

                        } else if (obj.getNum().equals("1")) {
                            ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Once" + "/" + getString(R.string.order_ti11));

                        } else if (obj.getNum().equals("2")) {
                            ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Twice" + "/" + getString(R.string.order_ti11));

                        } else if (obj.getNum().equals("3")) {
                            ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Three " + getString(R.string.orer_ti2) + "/" + getString(R.string.order_ti11));

                        }

                    } else {
                        ((TextView) holder.getView(R.id.tv_title_skill)).setText(StaticUtils.getWeekTypeStr(obj.getType(), isZhorEn));
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText(obj.getNum() + getString(R.string.orer_ti2) + "/" + getString(R.string.order_ti11));

                    }

                } else {
                    ((TextView) holder.getView(R.id.tv_title_skill)).setText(StaticUtils.getWeekTypeStr(obj.getType(), isZhorEn));

                }
                if (isZhorEn.equals("en")) {
                    if (obj.getNum().equals("0")) {//--0123次/月 分别对应 0/month ;Once/month；Twice/month；Three times/month
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText("0" + "/" + getString(R.string.order_ti11));

                    } else if (obj.getNum().equals("1")) {
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Once" + "/" + getString(R.string.order_ti11));

                    } else if (obj.getNum().equals("2")) {
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Twice" + "/" + getString(R.string.order_ti11));

                    } else if (obj.getNum().equals("3")) {
                        ((TextView) holder.getView(R.id.tv_title_week_time)).setText("Three " + getString(R.string.orer_ti2) + "/" + getString(R.string.order_ti11));

                    }
                } else {
                    ((TextView) holder.getView(R.id.tv_title_week_time)).setText(obj.getNum() + getString(R.string.orer_ti2) + "/" + getString(R.string.order_ti11));

                }
                ((TextView) holder.getView(R.id.tv_title_location)).setText(obj.getAddress_info());
                ((TextView) holder.getView(R.id.tv_title_distance)).setText(obj.getKm() + "km");
                holder.setText(R.id.tv_title_new_time, TimeUtils.getStrTime(obj.getCtime()));
//                if (uid.equals(obj.getAt_uid())) {
//                    holder.setVisibility(R.id.img_ty, View.VISIBLE);
//                } else {
//                    holder.setVisibility(R.id.img_ty, View.GONE);
//                }
                int is_jie = obj.getIs_jie();//是否报价
                if (is_jie == 1) {
                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setText(R.string.status_1);
                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else {
                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setText(getString(R.string.week_order));
                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
                }
                holder.setOnClickListener(R.id.iv_order_no_yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoadDialog.showProgressDialog(getContext());
                        Map<String, String> map = new HashMap<>();
                        map.put("oid", mOrderNoList.get(holder.getItemPosition()).getId());
                        map.put("uid", uid);
                        map.put("message", "");
                        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
                        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.self_offer, map, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(">>>周期接单>>>>>>" + response);
                                LoadDialog.closeProgressDialog();
                                int code = JsonUtils.getJsonInt(response, "status");
                                if (code == 200) {
                                    initData();
                                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setText(R.string.status_1);
                                    ((TextView) holder.getView(R.id.iv_order_no_yes)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                                } else {
                                    LoadDialog.closeProgressDialog();
                                    if (code != 1114) {
                                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                                    }
                                }
                            }
                        }));
                    }
                });
            }
        };
        lv_order_no.setAdapter(multiAdapter);
        multiAdapter.notifyDataSetChanged();
        if (isDouobleLoadmore) {
            lv_order_no.setSelection(olderMusingleCount - 1);
        }

        LoadDialog.closeProgressDialog();
    }

    private void bindData(OrderItemEntry orderItemEntry) {
        if (orderItemEntry != null) {
            if (!isLoadMore) {
                sOrderNoList.clear();
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    sOrderNoList.add(orderItemEntry.getData().get(i));
                    if (orderItemEntry.getData().get(i).getId().equals(SPUtils.getString(getActivity(),"proofferprice",""))){

                    }
                }
            } else {
                for (int i = 0; i < orderItemEntry.getData().size(); i++) {
                    sOrderNoList.add(orderItemEntry.getData().get(i));
                }
                if (orderItemEntry.getData().size() < Integer.valueOf(PAGE_SIZE)) {
                    --page;
                    lv_order_no.setPullLoadEnable(false);
                }
            }
        }
        lv_order_no.stopRefresh();
        lv_order_no.stopLoadMore();
        sigleAdapter = new MyAdapter<OrderItemEntry.DataBean>(sOrderNoList, R.layout.pro_order_no_listview_single_item) {
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
                String str = AssetsUtils.getSkillName(obj.getType(), isZhorEn);
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    String[] strS = str.split(" ");
                    str = "";
                    if (strS.length == 2) {
                        str = strS[0] + "\n" + strS[1];
                    } else if (strS.length == 3) {
                        str = strS[0] + "\n" + strS[1] + " " + strS[2];
                    } else if (strS.length >= 4) {
                        for (int i = 0; i < strS.length; i++) {
                            if (i == 1) {
                                str = str + " " + strS[i] + "\n";
                            } else {
                                str = str + " " + strS[i];
                            }

                        }
                    }
                }
                ((TextView) holder.getView(R.id.tv_title_skill_a)).setText(str);
                ((TextView) holder.getView(R.id.tv_title_location_a)).setText(obj.getAddress_info());
                ((TextView) holder.getView(R.id.tv_title_time_a)).setText(TimeUtils.getStrTime(obj.getCtime()));
                ((TextView) holder.getView(R.id.tv_title_distance_a)).setText(obj.getKm() + "km");
                if (uid.equals(obj.getAt_uid())) {
                    holder.setVisibility(R.id.img_ty, View.VISIBLE);
                } else {
                    holder.setVisibility(R.id.img_ty, View.GONE);
                }
                if (Integer.valueOf(sOrderNoList.get(holder.getItemPosition()).getType()) > 18) {
                    int is_jie = obj.getIs_jie();//是否报价
                    if (is_jie == 1) {
                        ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_3);//----------------状态显示
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.main_need_response);
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                return true;
                            }
                        });
                    } else if (is_jie == 0) {
                        ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_5);//----------------状态显示
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.fr_order);
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                return false;
                            }
                        });
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
                    }
                } else {
                    int is_jie = obj.getIs_jie();//是否报价
                    if (is_jie == 1) {
                        ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_6);//----------------状态显示
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.main_need_response);
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_gray_rect_theme_fill_corner);
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                return true;
                            }
                        });
                    } else if (is_jie == 0) {
                        ((TextView) holder.getView(R.id.tv_title_week_time_a)).setText(R.string.status_8);//----------------状态显示
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setText(R.string.fr_order);
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                return false;
                            }
                        });
                        ((TextView) holder.getView(R.id.iv_order_no_yes_a)).setBackgroundResource(R.drawable.pro_bg_shape_tv_rect_theme_fill_corner);
                    }
                }

                holder.setOnClickListener(R.id.iv_order_no_yes_a, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        LoadDialog.showProgressDialog(getContext());
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("uid", AppGlobal.getInstance().getUser().getUid());
                        map.put("secret_key", SPUtils.getString(getContext(), "secret", ""));
                        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
                        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(getContext(), Contants.get_paypal, map, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                LoadDialog.closeProgressDialog();
                                int code = JsonUtils.getJsonInt(response, "status");
                                if (code == 200) {
                                    try {
                                        String paypal = new JSONObject(response).getJSONObject("data").getString("paypal_account");
//                                        if (paypal == null || paypal.equals("")) {
//                                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
//                                            customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.bind_account_word)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
//                                                    new DialogInterface.OnClickListener() {
//                                                        public void onClick(DialogInterface dialog, int which) {
//                                                            Intent intent = new Intent(getContext(), BindPayPalAccount.class);
//                                                            intent.putExtra("isMine", true);
//                                                            startActivity(intent);
//                                                            dialog.dismiss();
//                                                        }
//                                                    })
//                                                    .setNegativeButton(context.getResources().getString(R.string.message_alert_no),
//                                                            new DialogInterface.OnClickListener() {
//                                                                public void onClick(DialogInterface dialog, int which) {
//                                                                    dialog.dismiss();
//                                                                }
//                                                            })
//                                                    .create().show();
//                                        } else {
                                        if (Integer.valueOf(sOrderNoList.get(holder.getItemPosition()).getType()) > 18) {
                                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
                                            customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.order_sss)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {

                                                            Intent intent = new Intent();
                                                            intent.putExtra("oid", sOrderNoList.get(holder.getItemPosition()).getId());
                                                            intent.putExtra("type", sOrderNoList.get(holder.getItemPosition()).getType());
                                                            intent.setClass(context, RecommendedFeesActivity.class);
                                                            startActivity(intent);
                                                            dialog.dismiss();
                                                        }
                                                    })
                                                    .setNegativeButton(context.getResources().getString(R.string.message_alert_no),
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.dismiss();
                                                                }
                                                            })
                                                    .create().show();

                                        } else {
                                            Intent intent = new Intent(context, ProOfferActivity.class);
                                            intent.putExtra("oid", sOrderNoList.get(holder.getItemPosition()).getId());
                                            startActivity(intent);
                                        }
                                        //   }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }));


                    }
                });
            }
        };
        lv_order_no.setAdapter(sigleAdapter);
        lv_order_no.setAdapter(sigleAdapter);
        sigleAdapter.notifyDataSetChanged();
        if (isSingleLoadmore) {
            lv_order_no.setSelection(olderSingleCount - 1);
        }
        LoadDialog.closeProgressDialog();
    }

    private void initView(View orderNoView) {
        lv_order_no = (XListView) orderNoView.findViewById(R.id.lv_order_no);
        lv_order_no.setXListViewListener(this);
        lv_order_no.setPullRefreshEnable(true);
        lv_order_no.setPullLoadEnable(true);
        lv_order_no.setRefreshTime(System.currentTimeMillis());
        lv_order_no.setOnItemClickListener(this);
//        if ("week".equals(orderFlag)) {
//            mProOrderNoListViewAdapter = new ProOrderNoListViewAdapter(context, mOrderNoList, this, orderFlag);
//            lv_order_no.setAdapter(mProOrderNoListViewAdapter);
//            mProOrderNoListViewAdapter.notifyDataSetChanged();
//        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ("single".equals(orderFlag)) {
            OrderItemEntry.DataBean dtaBean = sOrderNoList.get(position - 1);
            Intent intent = new Intent();
            intent.putExtra("id", dtaBean.getId());
            intent.putExtra("type", dtaBean.getType());
            intent.putExtra("is_jie", dtaBean.getIs_jie());
            intent.setClass(context, OrderDetailActivity.class);
            startActivity(intent);
        } else if ("week".equals(orderFlag)) {
            OrderItemWeekEntity.DataBean dtaBean = mOrderNoList.get(position - 1);
            Intent intent = new Intent();
            intent.putExtra("id", dtaBean.getId());
            intent.putExtra("is_jie", dtaBean.getIs_jie());
            intent.setClass(context, OrderWeekDetailActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onNoTerm(String orderFlag, String km, String is_at, String
            sort, List<String> strList) {
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
    }

    @Override
    public void orderPriceRefresh() {
        initData();
    }

    @Override
    public void changeWeekData() {
        orderFlag = "week";
        initData();
    }

    @Override
    public void onRefresh() {
        page = 1;
        isLoading = false;
        isLoadMore = false;
        lv_order_no.setPullLoadEnable(true);
//        if(sigleAdapter!=null){
//
//            olderSingleCount = sigleAdapter.getCount();
//
//        }
//
//        if(multiAdapter!=null){
//            olderMusingleCount = multiAdapter.getCount();
//        }
        isSingleLoadmore = false;
        isDouobleLoadmore = false;

        initData(orderFlag, km, is_at, sort, page, typeList);
    }

    @Override
    public void onLoadMore() {
        page++;
        isLoading = false;
        isLoadMore = true;
        if (sigleAdapter != null) {
            isSingleLoadmore = true;
            olderSingleCount = sigleAdapter.getCount();
        }

        if (multiAdapter != null) {
            isDouobleLoadmore = true;
            olderMusingleCount = multiAdapter.getCount();
        }

        initData(orderFlag, km, is_at, sort, page, typeList);
    }
}
