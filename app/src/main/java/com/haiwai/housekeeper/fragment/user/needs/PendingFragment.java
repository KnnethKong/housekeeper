package com.haiwai.housekeeper.fragment.user.needs;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
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
import com.haiwai.housekeeper.activity.base.WebViewActivity;
import com.haiwai.housekeeper.activity.base.WebViewPayActivity;
import com.haiwai.housekeeper.activity.user.GoPayActivity;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity3;
import com.haiwai.housekeeper.activity.user.OrderPayActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.NeedEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.xlistview.XListView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.haiwai.housekeeper.widget.RegisterDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.haiwai.housekeeper.base.MyApp.context;
import static com.haiwai.housekeeper.base.MyApp.getContext;

public class PendingFragment extends BaseFragment implements XListView.IXListViewListener {
    private XListView lv_pend_listview;
    private TextView tv_empty_msg;
    private User user;
    private int page = 1;
    private String statcx = "4";//待支付
    private static final String PAGESIZE = "10";
    private String isZhorEn = "";
    private String id = "";
    ImageLoader mImageLoader;
    MyAdapter<NeedEntity.DataBean> mMyAdapter;
    ArrayList<NeedEntity.DataBean> mArrayList = new ArrayList<>();
    private Boolean isLoadMore = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pending, container, false);
    }

    public void clearData() {
        if (mMyAdapter != null) {
            mMyAdapter.clear();
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mImageLoader = new ImageLoader(context);
        lv_pend_listview = (XListView) view.findViewById(R.id.lv_pend_listview);
        lv_pend_listview.setXListViewListener(this);
        lv_pend_listview.setPullRefreshEnable(true);
        lv_pend_listview.setPullLoadEnable(true);
        lv_pend_listview.setRefreshTime(System.currentTimeMillis());
        lv_pend_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NeedEntity.DataBean dataBean = mArrayList.get(i - 1);
                Intent intent = new Intent(context, NeedOrderDetailActivity3.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", dataBean.getId());
                bundle.putString("proid", dataBean.getJ_uid());
                bundle.putString("j_num", dataBean.getJ_num());
                bundle.putFloat("zon_price", dataBean.getZon_price());
                intent.putExtras(bundle);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
            }
        });
        tv_empty_msg = (TextView) view.findViewById(R.id.tv_empty_msg);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData(page);
    }

    public void initData(int page) {
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
                System.out.print(">>>>>>>>支付中>>" + response);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadDialog.closeProgressDialog();
                    }
                }, 100);

                if (code == 200) {
                    NeedEntity mNeedEntity = new Gson().fromJson(response, NeedEntity.class);
                    bindData(mNeedEntity);
                } else {
                    lv_pend_listview.stopRefresh();
                    lv_pend_listview.stopLoadMore();
                    ToastUtil.longToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    @Override
    protected void click(View v) {

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
        mMyAdapter = new MyAdapter<NeedEntity.DataBean>(mArrayList, R.layout.needs_listview_common_layout) {
            @Override
            public void bindView(final ViewHolder holder, NeedEntity.DataBean obj) {
                holder.setText(R.id.tv_user_order_date, TimeUtils.getDate2(obj.getCtime()));//日期
                holder.setText(R.id.tv_user_order_skill, AssetsUtils.getSkillName(obj.getType(), isZhorEn));//技能
                holder.setText(R.id.tv_user_order_address, obj.getAddress_info());//地址
                holder.setText(R.id.tv_user_order_code, getString(R.string.str_order_code) + obj.getOrder_id());//订单号
                holder.setVisibility(R.id.ll_icon_layout, View.VISIBLE);//已选择服务商的头像和名称 是否显示
                if (!TextUtils.isEmpty(obj.getAvatar())) {//服务商头像
                    mImageLoader.DisplayImage(obj.getAvatar(), (ImageView) holder.getView(R.id.iv_server_icon));
                } else {
                    holder.setVisibility(R.id.iv_server_icon, View.GONE);
                }
                holder.setText(R.id.tv_server_name, obj.getNickname());//服务商名称
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
                if ("9".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.status_u1));//节点状态
                    if ("en".equals(isZhorEn)) {
                        holder.setText(R.id.tv_tv_des, "Total " + "$" + obj.getZon_price());//价格  是否显示
                    } else {
                        String str = getString(R.string.all_counts) + "$" + obj.getZon_price();
                        holder.setText(R.id.tv_tv_des, str);//价格  是否显示
                    }
//                    holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.VISIBLE);// 底部操作按钮  是否显示
//                    holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.VISIBLE);//同意报价并允许开工
                    holder.setText(R.id.tv_btn_status_btn_right, getString(R.string.pay_fees));
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
                           //     payfor(mArrayList.get(holder.getItemPosition()).getId(), mArrayList.get(holder.getItemPosition()).getJ_uid());
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
                } else if ("10".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.orde_zero));//节点状态
                    if ("en".equals(isZhorEn)) {
                        holder.setText(R.id.tv_tv_des, "Total $" + obj.getZon_price());//价格  是否显示
                    } else {
                        String str = getString(R.string.all_counts) + "$" + obj.getZon_price();
                        holder.setText(R.id.tv_tv_des, SpanUtil.getNew2String(str, 18, 3, str.length()));//价格  是否显示
                    }
//                    holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                    holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                    holder.setVisibility(R.id.tv_btn_des_btn, View.GONE);//选择服务商的按钮  是否显示
                    holder.setVisibility(R.id.rl_btn_layout, View.VISIBLE);// 底部操作按钮  是否显示
//                    holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
                    holder.setVisibility(R.id.tv_btn_status_btn_left, View.GONE);
                    holder.setVisibility(R.id.tv_btn_status_btn_right, View.VISIBLE);//同意报价并允许开工
                    holder.setText(R.id.tv_btn_status_btn_right, getString(R.string.pay_fees));
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
                                payfor(mArrayList.get(holder.getItemPosition()).getId(), mArrayList.get(holder.getItemPosition()).getJ_uid());
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
                }
            }
        };
        lv_pend_listview.stopRefresh();
        lv_pend_listview.stopLoadMore();
        lv_pend_listview.setAdapter(mMyAdapter);
        mMyAdapter.notifyDataSetChanged();
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
}
