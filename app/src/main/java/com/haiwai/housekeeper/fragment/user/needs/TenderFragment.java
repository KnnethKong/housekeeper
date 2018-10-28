package com.haiwai.housekeeper.fragment.user.needs;


import android.app.Activity;
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
import com.haiwai.housekeeper.activity.user.NeedOrderDetailActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.activity.user.TakeOrderServeListActivity;
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
import com.haiwai.housekeeper.utils.AssetsUtils2;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.xlistview.XListView;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.haiwai.housekeeper.base.MyApp.context;
import static com.haiwai.housekeeper.base.MyApp.getContext;

/**
 * 招标中
 */
public class TenderFragment extends BaseFragment implements XListView.IXListViewListener {
    private XListView lv_tender_listview;
    private TextView tv_empty_msg;
    private User user;
    private int page = 1;
    private String statcx = "1";//招标中
    private static final String PAGESIZE = "10";
    private String isZhorEn = "";
    ImageLoader mImageLoader;
    MyAdapter<NeedEntity.DataBean> mMyAdapter;
    ArrayList<NeedEntity.DataBean> mArrayList = new ArrayList<>();
    private Boolean isLoadMore = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tender, container, false);
    }
    public void clearData(){
        if(mMyAdapter!=null){
            mMyAdapter.clear();
        }
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mImageLoader = new ImageLoader(context);
        lv_tender_listview = (XListView) view.findViewById(R.id.lv_tender_listview);
        lv_tender_listview.setXListViewListener(this);
        lv_tender_listview.setPullRefreshEnable(true);
        lv_tender_listview.setPullLoadEnable(true);
        lv_tender_listview.setRefreshTime(System.currentTimeMillis());
        lv_tender_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SPUtils.saveBoolean(getContext(),"isSelectPro",false);
                NeedEntity.DataBean dataBean = mArrayList.get(i - 1);
                Intent intent = new Intent(context, NeedOrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", dataBean.getId());
                bundle.putString("proid",dataBean.getJ_uid());
                bundle.putString("j_num", dataBean.getJ_num());
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
    protected void click(View v) {

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
                System.out.print(">>>>>>>>招标中>>" + response);
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    NeedEntity mNeedEntity = new Gson().fromJson(response, NeedEntity.class);
                    bindData(mNeedEntity);
                } else {
                    lv_tender_listview.stopRefresh();
                    lv_tender_listview.stopLoadMore();
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
                holder.setText(R.id.tv_user_order_skill, AssetsUtils2.getSkillName(obj.getType(), isZhorEn));//技能
                holder.setText(R.id.tv_user_order_address, obj.getAddress_info());//地址
                holder.setText(R.id.tv_user_order_code, getString(R.string.str_order_code)  + obj.getOrder_id());//订单号
                holder.setVisibility(R.id.ll_icon_layout, View.VISIBLE);//已选择服务商的头像和名称 是否显示
                if (!TextUtils.isEmpty(obj.getAvatar())) {//服务商头像
                    mImageLoader.DisplayImage(obj.getAvatar(), (ImageView) holder.getView(R.id.iv_server_icon));
                } else {
                    holder.setVisibility(R.id.ll_icon_layout, View.GONE);
                }
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
                holder.setText(R.id.tv_server_name, obj.getNickname());//服务商名称
                if ("0".equals(obj.getStaticX()) || "1".equals(obj.getStaticX()) || "2".equals(obj.getStaticX())) {
                    holder.setText(R.id.tv_user_state, getString(R.string.ten_p1));//节点状态
                }
                holder.setVisibility(R.id.tv_tv_des, View.GONE);//价格  是否显示
                String str;
                if(Integer.parseInt(obj.getJ_num())>1){
                    str  = obj.getJ_num() + getString(R.string.fr_str1);
                }else {
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
                        bundle.putString("proid",dataBean.getJ_uid());
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
            }
        };
        lv_tender_listview.stopRefresh();
        lv_tender_listview.stopLoadMore();
        lv_tender_listview.setAdapter(mMyAdapter);
        mMyAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData(page);
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

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.tv_btn_des) {

            }

        }
    };

    public void setRefData() {
        onRefresh();
    }
}
