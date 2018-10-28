package com.haiwai.housekeeper.fragment.user.needs;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.WebViewPayActivity;
import com.haiwai.housekeeper.activity.user.EvaluateActivity;
import com.haiwai.housekeeper.activity.user.GoPayActivity;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity3;
import com.haiwai.housekeeper.activity.user.OrderPayActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.activity.user.TakeOrderServeListActivity;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.NeedEntity;
import com.haiwai.housekeeper.entity.Parameter;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.WxEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.HttpManager;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PreferenceUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.xlistview.XListView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.haiwai.housekeeper.widget.RegisterDialog;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haiwai.housekeeper.base.MyApp.context;

//订单 全部 fragment
public class AllFragment extends BaseFragment implements XListView.IXListViewListener {
    private XListView lv_all_listview;
    private TextView tv_empty_msg;
    private User user;
    private String statcx = "0";//全部
    private int page = 1;
    private static final String PAGESIZE = "10";
    private String isZhorEn = "";
    ImageLoader mImageLoader;
    MyAdapter<NeedEntity.DataBean> mMyAdapter;
    ArrayList<NeedEntity.DataBean> mArrayList = new ArrayList<>();
    private Boolean isLoadMore = false;
    RefreshNeedReceiver receiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        boolean isReLogin = PreferenceUtils.getPrefBoolean(getContext(), "IsReLogin", false);
        if (isReLogin) {
            PreferenceUtils.setPrefBoolean(getContext(), "IsReLogin", false);
            isZhorEn = AppGlobal.getInstance().getLagStr();
            page = 1;
            initData(page);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        receiver = new RefreshNeedReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("refresh_need");
        getActivity().registerReceiver(receiver, intentFilter);
        mImageLoader = new ImageLoader(context);
        lv_all_listview = (XListView) view.findViewById(R.id.lv_all_listview);
        lv_all_listview.setXListViewListener(this);
        lv_all_listview.setPullRefreshEnable(true);
        lv_all_listview.setPullLoadEnable(true);
        lv_all_listview.setRefreshTime(System.currentTimeMillis());
        lv_all_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if ("0".equals(mArrayList.get(i - 1).getStaticX()) || "1".equals(mArrayList.get(i - 1).getStaticX())) {
                        NeedEntity.DataBean dataBean = mArrayList.get(i - 1);
                        SPUtils.saveBoolean(getContext(), "isSelectPro", false);
                        Intent intent = new Intent(context, NeedOrderDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", dataBean.getId());
                        bundle.putString("proid", dataBean.getJ_uid());
                        bundle.putString("j_num", dataBean.getJ_num());
                        intent.putExtras(bundle);
                        startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                    } else {
                        Intent intent = new Intent(getActivity(), NeedOrderDetailActivity3.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", mArrayList.get(i - 1).getId());
                        bundle.putString("proid", mArrayList.get(i - 1).getJ_uid());
                        intent.putExtras(bundle);
                        startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                    }
                } catch (Exception e) {

                }
            }
        });
        tv_empty_msg = (TextView) view.findViewById(R.id.tv_empty_msg);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData(page);

    }

    public void refresh() {
        onRefresh();
    }

    public void clearData() {
        if (mMyAdapter != null) {
            mMyAdapter.clear();
        }
    }

    public void initData(int page) {


//        lv_all_listview.setPullRefreshEnable(true);
//        lv_all_listview.setRefreshTime(1000);
//        lv_all_listview.startRefresh();


        if (mMyAdapter != null) {
            mArrayList.clear();
            mMyAdapter.notifyDataSetChanged();
        }
        LoadDialog.showProgressDialog(getContext());
        user = AppGlobal.getInstance().getUser();
        Map<String, String> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("static", statcx);
        map.put("page", String.valueOf(page));
        map.put("pagesize", PAGESIZE);
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.user_order_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    NeedEntity mNeedEntity = new Gson().fromJson(response, NeedEntity.class);
                    bindData(mNeedEntity);
                } else {
                    lv_all_listview.stopRefresh();
                    lv_all_listview.stopLoadMore();
                    ToastUtil.longToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        initData(1);
    }

    private void bindData(NeedEntity needEntity) {
        if (isLoadMore) {
            mArrayList.addAll(needEntity.getData());
            if (needEntity.getData().size() < 10) {
                page--;
            }
        } else {
            mArrayList.clear();
            mArrayList.addAll(needEntity.getData());
        }
        mMyAdapter = new MyAdapter<NeedEntity.DataBean>((ArrayList<NeedEntity.DataBean>) needEntity.getData(), R.layout.needs_listview_common_layout) {
            @Override
            public void bindView(final ViewHolder holder, NeedEntity.DataBean obj) {
                holder.setText(R.id.tv_user_order_date, TimeUtils.getDate2(obj.getCtime()));//日期
                holder.setText(R.id.tv_user_order_skill, AssetsUtils.getSkillName(obj.getType(), isZhorEn));//技能
                holder.setText(R.id.tv_user_order_address, obj.getAddress_info());//地址
                holder.setText(R.id.tv_user_order_code, getString(R.string.str_order_code) + obj.getOrder_id());//订单号
                if (!TextUtils.isEmpty(obj.getAvatar())) {//服务商头像
                    holder.setVisibility(R.id.ll_icon_layout, View.VISIBLE);//已选择服务商的头像和名称 是否显示
                    mImageLoader.DisplayImage(obj.getAvatar(), (ImageView) holder.getView(R.id.iv_server_icon));
                    holder.setText(R.id.tv_server_name, obj.getNickname());//服务商名称
                } else {
                    holder.setVisibility(R.id.ll_icon_layout, View.GONE);
                }
                holder.setOnClickListener(R.id.ll_icon_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), ProDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("oid", mArrayList.get(holder.getItemPosition()).getId());
                        bundle.putString("nickname", mArrayList.get(holder.getItemPosition()).getNickname());
                        bundle.putString("uid", mArrayList.get(holder.getItemPosition()).getJ_uid());
                        bundle.putString("type", mArrayList.get(holder.getItemPosition()).getType());
                        bundle.putString("choose", "1");
                        bundle.putBoolean("isServer", true);
                        intent.putExtra("fromO2O", true);
                        intent.putExtras(bundle);
                        startActivity(intent);
//                        ActivityTools.goNextActivity(getActivity(), ProDetailActivity.class, bundle);
                    }
                });
                if ("0".equals(obj.getStaticX()) || "1".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.sta_zbz));//节点状态
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                    holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.VISIBLE);
                    String str;
                    if (Integer.parseInt(obj.getJ_num()) > 1) {
                        str = obj.getJ_num() + getString(R.string.fr_str1);
                    } else {
                        str = obj.getJ_num() + getString(R.string.fr_str2);
                    }

                    holder.setText(R.id.tv_btn_des, SpanUtil.getNewString(str, 18, 0, str.length() - 3, 14, str.length() - 2, str.length()));//选择服务商的按钮  是否显示
                    holder.setOnClickListener(R.id.tv_btn_des, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            NeedEntity.DataBean dataBean = mArrayList.get(holder.getItemPosition());
                            Intent intent = new Intent(context, TakeOrderServeListActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("id", dataBean.getId());
                            bundle.putString("proid", dataBean.getJ_uid());
                            bundle.putString("lat", dataBean.getLat());
                            bundle.putString("long", dataBean.getLongX());
                            bundle.putString("type", dataBean.getType());
                            bundle.putString("addr", dataBean.getAddress_info());
                            bundle.putString("date", dataBean.getCtime());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
//                holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
//                holder.setText(R.id.tv_btn_status_btn_right, "同意报价并允许开工");//同意报价并允许开工
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                } else if ("2".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.fr_ddjsm));//节点状态
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);//节点状态
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                    holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setText(R.id.tv_btn_des_btn, getString(R.string.all_str1));//选择服务商的按钮  是否显示
                    holder.setOnClickListener(R.id.tv_btn_des_btn, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
                            customBuilder.setMessage(getString(R.string.all_str2)).setPositiveButton(getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            confirmComeHome(mArrayList.get(holder.getItemPosition()).getId());
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton(getString(R.string.message_alert_no),
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            }).create().show();
                        }
                    });
                    holder.setVisibility(R.id.tv_btn_des_btn, View.VISIBLE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
//                holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
//                holder.setText(R.id.tv_btn_status_btn_right, "同意报价并允许开工");//同意报价并允许开工
                } else if ("3".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.all_str3));//节点状态
                    holder.setText(R.id.tv_tv_des, getString(R.string.all_str4));//价格  是否显示
                    holder.setVisibility(R.id.tv_tv_des, View.VISIBLE);
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
//                    holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
//                    holder.setText(R.id.tv_btn_status_btn_right, "同意报价并允许开工");//同意报价并允许开工
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                } else if ("4".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.all_str5));//节点状态
                    String str = getString(R.string.all_counts) + "$" + obj.getZon_price();
                    holder.setText(R.id.tv_tv_des, SpanUtil.getNew2String(str, 18, 3, str.length()));//价格  是否显示
                    holder.setVisibility(R.id.tv_tv_des, View.VISIBLE);
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.VISIBLE);// 底部操作按钮  是否显示
                    holder.setText(R.id.tv_btn_status_btn_left, getString(R.string.all_str8));//不接受报价
                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.VISIBLE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.VISIBLE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                    holder.setOnClickListener(R.id.tv_btn_status_btn_left, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
                            customBuilder.setMessage(context.getResources().getString(R.string.main_need_is_disagree)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestDisAgreeOrder(mArrayList.get(holder.getItemPosition()).getId());
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
                        }
                    });
                    holder.setText(R.id.tv_btn_status_btn_right, getString(R.string.all_str11));//同意报价并允许开工
                    holder.setOnClickListener(R.id.tv_btn_status_btn_right, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
                            customBuilder.setMessage(context.getResources().getString(R.string.main_need_is_agree)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestAgreeOrder(mArrayList.get(holder.getItemPosition()).getId());
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
                        }
                    });
                } else if ("5".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.all_str22));//节点状态
                    String str = getString(R.string.all_str23) + "$" + obj.getZon_price();
                    holder.setText(R.id.tv_tv_des, SpanUtil.getNew2String(str, 18, 3, str.length()));//价格  是否显示
                    holder.setVisibility(R.id.tv_tv_des, View.VISIBLE);
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.VISIBLE);// 底部操作按钮  是否显示
//                    holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);
                    holder.setText(R.id.tv_btn_status_btn_right, getString(R.string.all_str24));//同意报价并允许开工
                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.VISIBLE);
                    holder.setOnClickListener(R.id.tv_btn_status_btn_right, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
                            customBuilder.setMessage(context.getResources().getString(R.string.main_need_is_agree)).setPositiveButton(context.getResources().getString(R.string.message_alert_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestAgreeOrder(mArrayList.get(holder.getItemPosition()).getId());
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
                        }
                    });
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                } else if ("6".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.all_m));//节点状态
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    String str = getString(R.string.all_str23) + "$" + obj.getZon_price();
                    holder.setText(R.id.tv_tv_des, SpanUtil.getNew2String(str, 18, 3, str.length()));//价格  是否显示
                    holder.setVisibility(R.id.tv_tv_des, View.GONE);
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.VISIBLE);
                    holder.setText(R.id.tv_btn_status_btn_right_left, getString(R.string.serv_p9));
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                } else if ("7".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.all_mn));//节点状态
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
//                    holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.GONE);//同意报价并允许开工
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                } else if ("8".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.all_ss));//节点状态
                    holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
//                    holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.GONE);//同意报价并允许开工
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                } else if ("9".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.all_n));//节点状态
                    String str = getString(R.string.all_str23) + "$" + obj.getZon_price();
                    holder.setText(R.id.tv_tv_des, SpanUtil.getNew2String(str, 18, 3, str.length()));//价格  是否显示
                    holder.setVisibility(R.id.tv_tv_des, View.VISIBLE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.VISIBLE);// 底部操作按钮  是否显示
//                    holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.VISIBLE);//同意报价并允许开工
                    holder.setText(R.id.tv_btn_status_btn_right, getString(R.string.all_strnd));
                    holder.setOnClickListener(R.id.tv_btn_status_btn_right, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            if (mArrayList.get(holder.getItemPosition()).getZon_price() != 0) {
                                Intent intent = new Intent(getActivity(), OrderPayActivity.class);
                                Bundle ev_bundle = new Bundle();
                                ev_bundle.putString("oid", mArrayList.get(holder.getItemPosition()).getId());
                                ev_bundle.putString("j_uid", mArrayList.get(holder.getItemPosition()).getJ_uid());
                                intent.putExtras(ev_bundle);
                                startActivity(intent);
                                // payfor(mArrayList.get(holder.getItemPosition()).getId(), mArrayList.get(holder.getItemPosition()).getJ_uid());
                            } else {
                                CustomDialog.Builder customBuilder = new CustomDialog.Builder(getContext());
                                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.notice_no_fee)).setPositiveButton(getString(R.string.message_alert_yes),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                completeOrder(mArrayList.get(holder.getItemPosition()).getId(), mArrayList.get(holder.getItemPosition()).getUid());
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


//                            Intent intent = new Intent(getActivity(), GoPayActivity.class);
//                            Bundle ev_bundle = new Bundle();
//                            ev_bundle.putString("oid", mArrayList.get(holder.getItemPosition()).getId());
//                            ev_bundle.putString("j_uid", mArrayList.get(holder.getItemPosition()).getJ_uid());
//                            intent.putExtras(ev_bundle);
//                            startActivity(intent);
                        }
                    });
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                } else if ("10".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.all_st23r));//节点状态
                    holder.setVisibility(R.id.tv_user_state2, View.GONE);
                    holder.setVisibility(R.id.tv_user_state, View.VISIBLE);
                    String str = getString(R.string.all_str23) + "$" + obj.getZon_price();
                    holder.setText(R.id.tv_tv_des, SpanUtil.getNew2String(str, 18, 3, str.length()));//价格  是否显示
                    holder.setVisibility(R.id.tv_tv_des, View.VISIBLE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.VISIBLE);// 底部操作按钮  是否显示
//                    holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.VISIBLE);//同意报价并允许开工
                    holder.setText(R.id.tv_btn_status_btn_right, getString(R.string.all_str234));
                    holder.setOnClickListener(R.id.tv_btn_status_btn_right, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            Intent intent = new Intent(getActivity(), GoPayActivity.class);
//                            Bundle ev_bundle = new Bundle();
//                            ev_bundle.putString("oid", mArrayList.get(holder.getItemPosition()).getId());
//                            ev_bundle.putString("j_uid", mArrayList.get(holder.getItemPosition()).getJ_uid());
//                            intent.putExtras(ev_bundle);
//                            startActivity(intent);

                            if (mArrayList.get(holder.getItemPosition()).getZon_price() != 0) {
                                Intent intent = new Intent(getActivity(), OrderPayActivity.class);
                                Bundle ev_bundle = new Bundle();
                                ev_bundle.putString("oid", mArrayList.get(holder.getItemPosition()).getOrder_id());
                                ev_bundle.putString("j_uid", mArrayList.get(holder.getItemPosition()).getJ_uid());
                                intent.putExtras(ev_bundle);
                                startActivity(intent);
                                //  payfor(mArrayList.get(holder.getItemPosition()).getId(), mArrayList.get(holder.getItemPosition()).getJ_uid());
                            } else {
                                CustomDialog.Builder customBuilder = new CustomDialog.Builder(getContext());
                                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.notice_no_fee)).setPositiveButton(getString(R.string.message_alert_yes),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                completeOrder(mArrayList.get(holder.getItemPosition()).getId(), mArrayList.get(holder.getItemPosition()).getUid());
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


                        }
                    });
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                } else if ("11".equals(obj.getStaticX())) {
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);
                    if ("1".equals(obj.getIs_ypin())) {
                        holder.setVisibility(R.id.tv_user_state, View.GONE);
                        holder.setVisibility(R.id.tv_user_state2, View.VISIBLE);
                        holder.setText(R.id.tv_user_state2, getString(R.string.all_fini));//节点状态
                        holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                        holder.setVisibility(R.id.tv_btn_des, View.GONE);//选择服务商的按钮  是否显示
                        holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.VISIBLE);
                        holder.setText(R.id.tv_btn_status_btn_right_left, getString(R.string.all_str34));
                        holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
//                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);//不接受报价
//                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.GONE);//同意报价并允许开工
                    } else {
                        holder.setVisibility(R.id.tv_user_state, View.GONE);
                        holder.setVisibility(R.id.tv_user_state2, View.VISIBLE);
                        holder.setVisibility(R.id.tv_btn_des_btn, View.VISIBLE);
                        holder.setText(R.id.tv_user_state2, getString(R.string.all_fini));//节点状态
                        holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                        holder.setVisibility(R.id.tv_btn_des, View.GONE);//选择服务商的按钮  是否显示
                        holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
                        holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);
                        holder.setVisibility(R.id.tv_btn_status_btn_right, View.GONE);//同意报价并允许开工
                        holder.setText(R.id.tv_btn_des_btn, getString(R.string.main_need_evaluate_order));
                        holder.setOnClickListener(R.id.tv_btn_des_btn, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), EvaluateActivity.class);
                                Bundle ev_bundle = new Bundle();
                                ev_bundle.putString("oid", mArrayList.get(holder.getItemPosition()).getId());
                                ev_bundle.putString("uid", mArrayList.get(holder.getItemPosition()).getJ_uid());
                                ev_bundle.putString("type", mArrayList.get(holder.getItemPosition()).getType());
                                intent.putExtras(ev_bundle);
                                startActivity(intent);
                            }
                        });
                        holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                    }
                } else if ("12".equals(obj.getStaticX())) {
                    holder.setVisibility(R.id.tv_user_state, View.GONE);
                    holder.setVisibility(R.id.tv_user_state2, View.VISIBLE);
                    holder.setText(R.id.tv_user_state2, getString(R.string.all_str124));//节点状态
                    holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                    holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
                } else if ("13".equals(obj.getStaticX())) {
                    holder.setVisibility(R.id.tv_user_state, View.GONE);
                    holder.setVisibility(R.id.tv_user_state2, View.VISIBLE);
                    holder.setText(R.id.tv_user_state2, getString(R.string.all_str123));//节点状态
                    holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right_left, View.GONE);
                    holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
                }

            }
        };
        lv_all_listview.stopRefresh();
        lv_all_listview.stopLoadMore();
        lv_all_listview.setAdapter(mMyAdapter);
        mMyAdapter.notifyDataSetChanged();
    }


    private void completeOrder(String oid, String mUid) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", mUid);
        map.put("oid", oid);
        map.put("secret_key", SPUtils.getString(getContext(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(getContext(), Contants.pay_for, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        initData(1);
                    } else {
                        ToastUtil.shortToast(getContext(), ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }


    private void payfor(final String oid, String j_uid) {
        LoadDialog.showProgressDialog(getContext());
        Map<String, String> map = new HashMap<>();
        map.put("j_uid", j_uid);
        map.put("oid", oid);
        map.put("secret_key", SPUtils.getString(getContext(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(getContext(), Contants.order_paymoney, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {//{"status":200,"data":44}
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        paypalPay(jsonObject.optString("data"), oid);
                    } catch (org.json.JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(getContext(), ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    private void paypalPay(String money, String oid) {
        Map<String, String> map = new HashMap<>();
        map.put("money", money + "");
        map.put("oid", oid);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(getContext(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        PlatRequest request = new PlatRequest(getContext(), Contants.paypalzhifu, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    String uri = JsonUtils.getJsonStr(response, "data");
                    Intent intent = new Intent(getContext(), WebViewPayActivity.class);
                    intent.putExtra("url", uri);
                    startActivity(intent);

//                    startActivity(WebViewPayActivity.createIntent(getActivity(),"PayPay",uri));

                } else if (code == 1412) {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(getContext(), getString(R.string.is_bind_paypal));
                } else if (code == 1411) {
                    LoadDialog.closeProgressDialog();

                    ToastUtil.shortToast(getContext(), getString(R.string.no_bind_paypal));
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(getContext(), ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        MyApp.getTingtingApp().getRequestQueue().add(request);
    }


    @Override
    protected void click(View v) {

    }

    @Override
    public void onRefresh() {
        page = 1;
        isLoadMore = false;
        initData(page);
    }

    @Override
    public void onLoadMore() {
        ++page;
        isLoadMore = true;
        initData(page);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_FIRST_USER) {
            if (resultCode == -1) {
                onRefresh();
            }
        }
    }

    public void requestAgreeOrder(String id) {//同意报价

        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("oid", id));
        list.add(new Parameter("uid", AppGlobal.getInstance().getUser().getUid()));
        list.add(new Parameter("secret_key", SPUtils.getString(getActivity(), "secret", "")));
        list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
        list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
        HttpManager.getInstance().post(list, Contants.offer_saveque, 100, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                int code = JsonUtils.getJsonInt(resultJson, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    onRefresh();
                    System.out.println(">>>>>..接受报价>>>" + resultJson);
                } else {
                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }

            @Override
            public void onHttpRequestError(int requestCode, Exception exception) {

            }
        });
    }

    public class RefreshNeedReceiver extends BroadcastReceiver {
        public RefreshNeedReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("refresh_need".equals(intent.getAction())) {
                if ("1".equals(intent.getStringExtra("need"))) {
                    onRefresh();
                }
            }
        }
    }

    private void confirmComeHome(String id) {

        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("oid", id));

        list.add(new Parameter("secret_key", SPUtils.getString(getActivity(), "secret", "")));
        list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
        list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
        HttpManager.getInstance().post(list, Contants.que_sm, 100, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                int code = JsonUtils.getJsonInt(resultJson, "status");
                if (code == 200) {
                    onRefresh();
                } else {
                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }

            @Override
            public void onHttpRequestError(int requestCode, Exception exception) {
            }
        });
    }

    public void requestDisAgreeOrder(String id) {//驳回
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("oid", id);
        map.put("secret_key", SPUtils.getString(getActivity(), "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(context, Contants.offer_bh, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        System.out.println(">>>>.已驳回.." + response);
                    } else {
                        ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    /**
     * 微信支付
     */
    private void weixinPay(WxEntity.DataBean data) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        IWXAPI api = WXAPIFactory.createWXAPI(getActivity(), data.getAppid());
        // 将该app注册到微信
        api.registerApp(data.getAppid());
        PayReq req = new PayReq();
        req.appId = data.getAppid();
        req.partnerId = data.getPartnerid();
        req.prepayId = data.getPrepayid();
        req.nonceStr = data.getNoncestr();
        req.timeStamp = data.getTimestamp();
        req.packageValue = data.getPackageX();
        req.sign = data.getSign();
        req.extData = "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }
}
