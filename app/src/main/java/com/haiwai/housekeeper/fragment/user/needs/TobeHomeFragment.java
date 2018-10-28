package com.haiwai.housekeeper.fragment.user.needs;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity3;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.NeedEntity;
import com.haiwai.housekeeper.entity.Parameter;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.HttpManager;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.xlistview.XListView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haiwai.housekeeper.base.MyApp.context;

public class TobeHomeFragment extends BaseFragment implements XListView.IXListViewListener {
    private XListView lv_home_listview;
    private TextView tv_empty_msg;
    private User user;
    private int page = 1;
    private String statcx = "2";//待上门
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
        return inflater.inflate(R.layout.fragment_tobe_home, container, false);
    }
    public void clearData(){
        if(mMyAdapter!=null){
            mMyAdapter.clear();
        }
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mImageLoader = new ImageLoader(context);
        lv_home_listview = (XListView) view.findViewById(R.id.lv_home_listview);
        lv_home_listview.setXListViewListener(this);
        lv_home_listview.setPullRefreshEnable(true);
        lv_home_listview.setPullLoadEnable(true);
        lv_home_listview.setRefreshTime(System.currentTimeMillis());
        lv_home_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), NeedOrderDetailActivity3.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", mArrayList.get(i - 1).getId());
                bundle.putString("proid", mArrayList.get(i - 1).getJ_uid());
                intent.putExtras(bundle);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
            }
        });
        tv_empty_msg = (TextView) view.findViewById(R.id.tv_empty_msg);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData(page);
    }

    public void initData(int page) {
        if(mMyAdapter!=null){
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
                System.out.print(">>>>>>>>上门中>>" + response);
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    NeedEntity mNeedEntity = new Gson().fromJson(response, NeedEntity.class);
                    bindData(mNeedEntity);
                } else {
                    lv_home_listview.stopRefresh();
                    lv_home_listview.stopLoadMore();
                    ToastUtil.longToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
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
                holder.setText(R.id.tv_user_order_code, getString(R.string.str_order_code)  + obj.getOrder_id());//订单号
                holder.setVisibility(R.id.ll_icon_layout, View.VISIBLE);//已选择服务商的头像和名称 是否显示
                holder.setOnClickListener(R.id.ll_icon_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                if (!TextUtils.isEmpty(obj.getAvatar())) {//服务商头像
                    mImageLoader.DisplayImage(obj.getAvatar(), (ImageView) holder.getView(R.id.iv_server_icon));
                } else {
                    holder.setVisibility(R.id.iv_server_icon, View.GONE);
                }
                holder.setText(R.id.tv_server_name, obj.getNickname());//服务商名称
                holder.setOnClickListener(R.id.ll_icon_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(),ProDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("oid", mArrayList.get(holder.getItemPosition()).getId());
                        bundle.putString("nickname", mArrayList.get(holder.getItemPosition()).getNickname());
                        bundle.putString("uid", mArrayList.get(holder.getItemPosition()).getJ_uid());
                        bundle.putString("type", mArrayList.get(holder.getItemPosition()).getType());
                        bundle.putString("choose", "1");
                        bundle.putBoolean("isServer",true);
                        intent.putExtra("fromO2O",true);
                        intent.putExtras(bundle);
                        startActivity(intent);
//                        ActivityTools.goNextActivity(getActivity(), ProDetailActivity.class, bundle);
                    }
                });
                if ("0".equals(obj.getStaticX()) || "1".equals(obj.getStaticX()) || "2".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.home_p1));//节点状态
                }
                holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                holder.setVisibility(R.id.tv_btn_des, View.GONE);//接单人数  是否显示
                holder.setText(R.id.tv_btn_des_btn, getString(R.string.home_p2));//选择服务商的按钮  是否显示
                holder.setOnClickListener(R.id.tv_btn_des_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CustomDialog.Builder customBuilder = new CustomDialog.Builder(getActivity());
                        customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.home_t1)).setPositiveButton(getString(R.string.message_alert_yes),
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
                holder.setVisibility(R.id.rl_btn_layout, View.GONE);// 底部操作按钮  是否显示
//                holder.setText(R.id.tv_btn_status_btn_left, "不接受报价");//不接受报价
//                holder.setText(R.id.tv_btn_status_btn_right, "同意报价并允许开工");//同意报价并允许开工
            }
        };
        lv_home_listview.stopRefresh();
        lv_home_listview.stopLoadMore();
        lv_home_listview.setAdapter(mMyAdapter);
        mMyAdapter.notifyDataSetChanged();
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
}
