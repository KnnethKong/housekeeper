package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.VoucherEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.StaticUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/6.
 * 红包福利
 */
public class RedWalletActivity extends BaseActivity {
    // TODO: 2016/9/6 代金券白色的有效期drawable left
    User user;
    private Map<String, String> map = new HashMap<>();
    private ListView mListView;
    private String isZhorEn = "";
    private TextView tvRed;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_red_wallet, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.red_wallet_title), Color.parseColor("#FF3C3C3C"));
        mListView = (ListView) findViewById(R.id.lv_quan);
        tvRed = ((TextView) findViewById(R.id.tv_red_desc));
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        map.put("uid", user.getUid());
        map.put("secret_key", SPUtils.getString(RedWalletActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RedWalletActivity.this, Contants.coupon_userlst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println(">>>>>>>>>>接单券》》" + response);
                if (code == 200) {
                    VoucherEntity voucherEntity = new Gson().fromJson(response, VoucherEntity.class);
                    bindDataView(voucherEntity);
                } else {
                    ToastUtil.longToast(RedWalletActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void bindDataView(VoucherEntity voucherEntity) {
        if(voucherEntity==null||voucherEntity.getData()==null||voucherEntity.getData().size()==0){
            tvRed.setText(getString(R.string.red_desc));
        }
        MyAdapter myAdapter = new MyAdapter((ArrayList) voucherEntity.getData(), R.layout.red_wallet_item_layout) {
            @Override
            public void bindView(ViewHolder holder, Object obj) {
                ((TextView) holder.getView(R.id.tv_quan_title)).setText(AssetsUtils.getSkillName(((VoucherEntity.DataBean) obj).getType(), isZhorEn));
                ((TextView) holder.getView(R.id.tv_quan_time)).setText(TimeUtils.getDate(((VoucherEntity.DataBean) obj).getEtime()));
            }
        };
        mListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void click(View v) {

    }
}
