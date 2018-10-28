package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.IssueMakeSureAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.entity.Question;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.View_FWBJ_9;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/18.
 * 点击pro列表底部——发布需求选择地址
 */
public class IssueRequireAddrActivity extends BaseActivity {
    private LinearLayout llcontent;
    private SeekBar seekBar;
    private TextView tvprogress;
    private String id = "";
    private String at_uid = "";
    private List<Question> questionList;
    private String addr;

    private TextView tvhint;
    private TextView tvtitle;
    private Spinner spinner;

    private String lat = "";
    private String lng = "";
    private String h_id = "";
    private String isZhorEn = "";
    //回显处理
    private boolean isEdit = false;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_issue_addr, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setRightVisible(true);
        getRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("isFind".equals(IMKitService.lagMap.get("isGo"))) {
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", IMKitService.lagMap.get("uid"));
                    bundle.putString("nickname", IMKitService.lagMap.get("nickname"));
                    bundle.putString("type", IMKitService.lagMap.get("type"));
                    bundle.putString("choose", IMKitService.lagMap.get("choose"));
                    bundle.putString("oid", IMKitService.lagMap.get("oid"));
                    ActivityTools.goNextActivity(IssueRequireAddrActivity.this, ProDetailActivity.class, bundle);
                } else if ("isHome".equals(IMKitService.lagMap.get("isGo"))) {
                    if(Integer.valueOf(id)>=19){
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireAddrActivity.this, O2ODetailActivity2.class, bundle);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireAddrActivity.this, O2ODetailActivity.class, bundle);
                    }

                }
            }
        });
        isZhorEn = AppGlobal.getInstance().getLagStr();
        id = getIntent().getExtras().get("id").toString();
        at_uid = getIntent().getExtras().get("at_uid").toString();
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (isEdit) {
            addr = getIntent().getExtras().get("addr").toString();
            h_id = getIntent().getExtras().get("h_id").toString();
            lat = getIntent().getExtras().get("lat").toString();
            lng = getIntent().getExtras().get("lng").toString();
        }
        llcontent = (LinearLayout) findViewById(R.id.issue_addr_ll_content);

        findViewById(R.id.issue_addr_tv_before).setOnClickListener(this);
        seekBar = (SeekBar) findViewById(R.id.issue_addr_sb);
        tvprogress = (TextView) findViewById(R.id.issue_addr_tv_progress);
        //content
        tvhint = (TextView) findViewById(R.id.issue_addr_tv_hint);
        tvhint.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.issue_addr_sp);
        tvtitle = (TextView) findViewById(R.id.issue_addr_tv_title);

        seekBar.setMax(100);
        seekBar.setProgress(100);
        tvprogress.setText(100 + "%");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.rightMargin = EvmUtil.dip2px(this, 30);
        tvprogress.setLayoutParams(params);
        tvprogress.setGravity(Gravity.RIGHT);
        tvprogress.setPadding(0, 5, 0, 0);
        tvprogress.setTextColor(getResources().getColor(R.color.theme));

        setTitle(AssetsUtils.getSkillName(id, isZhorEn), Color.parseColor("#FF3C3C3C"));
        findViewById(R.id.issue_addr_ll_next).setOnClickListener(this);
        findViewById(R.id.issue_addr_add_addr).setOnClickListener(this);

        llcontent.setVisibility(View.VISIBLE);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 1, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 1, 9);
            tvtitle.setText(question);
            tvhint.setText(answerList.get(0));
        } else {
            String question = JsonUtils.getENQuestion(this, 1, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 1, 9);
            tvtitle.setText(question);
            tvhint.setText(answerList.get(0));
        }
        if (isNetworkAvailable()) {
            requestHouseList();
        }


    }

    @Override
    protected void initData() {
        questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
        LogUtil.e("addr-getdata", questionList + "");
    }

    public void requestHouseList() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("secret_key", SPUtils.getString(IssueRequireAddrActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.hous_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("zc", response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    final HouseListEntity entity = new Gson().fromJson(response, HouseListEntity.class);
                    final String[] addrs = new String[entity.getData().size()];
                    for (int i = 0; i < entity.getData().size(); i++) {
                        addrs[i] = entity.getData().get(i).street+entity.getData().get(i).house_number;
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(IssueRequireAddrActivity.this, android.R.layout.simple_spinner_item, addrs);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tvhint.setText(addrs[position]);
                            h_id = entity.getData().get(position).getId();
                            lat = entity.getData().get(position).getLat();
                            lng = entity.getData().get(position).getLongX();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    if (isEdit) {
                        setSpinnerItemSelectedByValue(spinner, addr);
                    }
                } else {
                    ToastUtil.shortToast(IssueRequireAddrActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                requestHouseList();
            }
        }
    }

    @Override
    protected void click(View v) {
        Bundle bundle;
        switch (v.getId()) {
            case R.id.issue_addr_ll_next:
                addr = tvhint.getText().toString();
                if(addr.contains("Please select")||addr.contains("选择您")){
                    ToastUtil.shortToast(IssueRequireAddrActivity.this,getString(R.string.issue_addr_choose_house));
                    return;
                }
                questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
                bundle = new Bundle();
                bundle.putSerializable("bundle", (Serializable) questionList);
                bundle.putString("id", id);
                bundle.putString("addr", addr);
                bundle.putString("h_id", h_id);
                bundle.putString("lat", lat);
                bundle.putString("lng", lng);
                bundle.putString("at_uid", at_uid);
                ActivityTools.goNextActivity(this, IssueRequireMakeSureActivity.class, bundle);
                break;
            case R.id.issue_addr_tv_before:
                finish();
                break;
            case R.id.issue_addr_tv_hint:
                spinner.performClick();
                break;
            case R.id.issue_addr_add_addr:
//                bundle = new Bundle();
//                bundle.putString("flag", "add");
//                ActivityTools.goNextActivityForResult(this, AddHouseActivity.class, bundle, 100);
                Intent intent = new Intent(this, NewHousActivity.class);
                intent.putExtra("isNew", true);
                intent.putExtra("iso2o", true);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        requestHouseList();
    }

    public static void setSpinnerItemSelectedByValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
                break;
            }
        }
    }
}
