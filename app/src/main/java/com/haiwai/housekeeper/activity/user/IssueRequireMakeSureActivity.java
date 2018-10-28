package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.IssueMakeSureAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.Question;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by ihope007 on 2016/8/25.
 * 下单最后一步确认所有问题  单项跳转  请求接口
 */
public class IssueRequireMakeSureActivity extends BaseActivity {
    private ListView recyclerView;
    private IssueMakeSureAdapter adapter;
    private String id;
    private List<Question> questionList;
    private String addr;
    private String lat = "";
    private String lng = "";
    private String h_id = "";
    private String at_uid = "";
    private String isZhorEn = "";
    View footView;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_issue_make_sure, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.o2o_order_make_sure_title), Color.parseColor("#FF3C3C3C"));
        recyclerView = (ListView) findViewById(R.id.issue_make_sure_rv);
        findViewById(R.id.issue_make_sure_ll_next).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
        id = getIntent().getExtras().get("id").toString();
        addr = getIntent().getExtras().get("addr").toString();
        lat = getIntent().getExtras().get("lat").toString();
        lng = getIntent().getExtras().get("lng").toString();
        h_id = getIntent().getExtras().get("h_id").toString();
        at_uid = getIntent().getExtras().get("at_uid").toString();
        adapter = new IssueMakeSureAdapter(this, questionList, Integer.valueOf(id), addr);
        setFootView(recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new IssueMakeSureAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Bundle bundle;
                List<Question> emptyList = new ArrayList<Question>();
                if (position == 0) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(0).getAnswer());
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireAActivity.class, bundle, 100);
                } else if (position == 1) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(1).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireBActivity.class, bundle, 200);
                } else if (position == 2) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(2).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireCActivity.class, bundle, 300);
                } else if (position == 3) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(3).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireDActivity.class, bundle, 400);
                } else if (position == 4) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(4).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireEActivity.class, bundle, 500);
                } else if (position == 5) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(5).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireFActivity.class, bundle, 600);
                } else if (position == 6) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(6).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireGActivity.class, bundle, 700);
                } else if (position == 7) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(7).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireHActivity.class, bundle, 800);
                } else if (position == 8) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(8).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireIActivity.class, bundle, 900);
                } else if (position == 9) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(9).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireJActivity.class, bundle, 1000);
                } else if (position == 10) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(10).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireKActivity.class, bundle, 1100);
                } else if (position == 11) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(11).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireLActivity.class, bundle, 1200);
                } else if (position == 12) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(12).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireMActivity.class, bundle, 1300);
                } else if (position == 13) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(13).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireNActivity.class, bundle, 1400);
                } else if (position == 14) {
                    bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("answer", questionList.get(14).getAnswer());
                    bundle.putSerializable("bundle", (Serializable) emptyList);
                    bundle.putString("make_sure", "1");
                    ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireOActivity.class, bundle, 1500);
                }
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);


    }

    private void setFootView(ListView view) {
        footView = View.inflate(IssueRequireMakeSureActivity.this, R.layout.item_issue_make_sure, null);
        footView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("at_uid", at_uid);
                bundle.putString("addr", addr);
                bundle.putString("h_id", h_id);
                bundle.putString("lat", lat);
                bundle.putString("lng", lng);
                bundle.putBoolean("isEdit", true);
                bundle.putSerializable("bundle", (Serializable) questionList);
                ActivityTools.goNextActivityForResult(IssueRequireMakeSureActivity.this, IssueRequireAddrActivity.class, bundle, 1500);
            }
        });
        ((TextView) footView.findViewById(R.id.item_issue_make_sure_tv_question)).setText(R.string.o2oaddr);
        ((TextView) footView.findViewById(R.id.item_issue_make_sure_tv_answer)).setText(addr);
        view.addFooterView(footView);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.issue_make_sure_ll_next://下单
                LoadDialog.showProgressDialog(IssueRequireMakeSureActivity.this);
                if ("1".equals(id)) {
                    requestFWBJOrder();
                } else if ("2".equals(id)) {
                    requestCPXJOrder();
                } else if ("3".equals(id)) {
                    requestDTQXOrder();
                } else if ("4".equals(id)) {
                    requestGYQXOrder();
                } else if ("5".equals(id)) {
                    requestLJCLOrder();
                } else if ("6".equals(id)) {
                    requestBYBJOrder();
                } else if ("7".equals(id)) {
                    requestABXTOrder();
                } else if ("8".equals(id)) {
                    requestCHXDOrder();
                } else if ("9".equals(id)) {
                    requestWXJGOrder();
                } else if ("10".equals(id)) {
                    requestFWWXOrder();
                } else if ("11".equals(id)) {
                    requestYYWXOrder();
                } else if ("12".equals(id)) {
                    requestJDXDOrder();
                } else if ("13".equals(id)) {
                    requestGJFXOrder();
                } else if ("14".equals(id)) {
                    requestSNFSOrder();
                } else if ("15".equals(id)) {
                    requestSWFSOrder();
                } else if ("16".equals(id)) {
                    requestHSXDOrder();
                } else if ("17".equals(id)) {
                    requestDGXDOrder();
                } else if ("18".equals(id)) {
                    requestGLWXOrder();
                } else if ("19".equals(id)) {
                    requestJJYCOrder();
                } else if ("20".equals(id)) {
                    requestPTDBOrder();
                } else if ("21".equals(id)) {
                    requestBXXDOrder();
                } else if ("22".equals(id)) {
                    requestYMZXOrder();
                } else if ("23".equals(id)) {
                    requestSWFWOrder();
                } else if ("24".equals(id)) {
                    requestJSJLOrder();
                } else if ("25".equals(id)) {
                    requestFYGZOrder();
                } else if ("26".equals(id)) {
                    requestFLZXOrder();
                } else if ("27".equals(id)) {
                    requestFCMMOrder();
                } else if ("28".equals(id)) {
                    requestLYDLOrder();
                }
                break;

        }
    }

    public void jumpMain() {
        Bundle bundle = new Bundle();
        bundle.putString("flag", "order_success");
        ActivityTools.goNextActivity(IssueRequireMakeSureActivity.this, MainActivity.class, bundle);
        finish();
    }

    /**
     * 房屋保洁
     */
    private void requestFWBJOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "5".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));
        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", questionList.get(1).getAnswer().substring(0, 1));
        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", questionList.get(2).getAnswer().substring(0, 1).equals("6") ? "str" + questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length())
                : questionList.get(2).getAnswer().substring(0, 1));
        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", questionList.get(3).getAnswer().substring(0, 1).equals("6") ?
                "str" + questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length())
                : questionList.get(3).getAnswer().substring(0, 1));
        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", questionList.get(4).getAnswer().substring(0, 1).equals("7") ? "str" + questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length())
                : questionList.get(4).getAnswer().substring(0, 1));
        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", questionList.get(5).getAnswer().substring(0, 1));
        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7[1]", questionList.get(6).getAnswer().substring(0, 1));
        map.put("da7[2]", questionList.get(6).getAnswer().substring(1, 2));
        map.put("da7[3]", questionList.get(6).getAnswer().substring(2, 3));
        map.put("da7[4]", questionList.get(6).getAnswer().substring(3, 4));
        map.put("da7[5]", questionList.get(6).getAnswer().substring(4, 5));
        map.put("da7[6]", questionList.get(6).getAnswer().substring(5, 6));
        map.put("wen8", questionList.get(7).getQuestion());
        String type = questionList.get(7).getAnswer().substring(0, 1);

        Log.i("type--Inforamtion", type + "---" + questionList.get(7).getAnswer());

        if ("4".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "str" + "");
            map.put("da8[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("5".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(7).getAnswer();
            map.put("da8[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da8[2]", "str" + mDate);
            map.put("da8[3]", "str" + mTime);
            map.put("da8[4]", mLength);
        } else {
            map.put("da8[type]", type);
            map.put("da8[2]", "");
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        }
        map.put("wen9", questionList.get(8).getQuestion());
        String[] split = questionList.get(8).getAnswer().split("\\|");
        map.put("da9", "1".equals(questionList.get(8).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 1, 9);
        else
            question = JsonUtils.getENQuestion(this, 1, 9);
        map.put("wen10", question);
        map.put("da10", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        Log.i("Information-Map", map.toString());

        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String res = response.body().string();
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(res, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });


//        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(IssueRequireMakeSureActivity.this, Contants.down_order, map, null, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }));
    }

    /**
     * 地毯清洗下单
     */
    private void requestDTQXOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "5".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));
        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "6".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                "str" + questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));
        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", questionList.get(2).getAnswer().substring(0, 1).equals("4") ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

//        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
//        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
//        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));
//        map.put("da3[4]", "1".equals(questionList.get(2).getAnswer().substring(3, 4)) ?
//                "str" + questionList.get(2).getAnswer().substring(4, questionList.get(2).getAnswer().length()) : "");
        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4[1]", questionList.get(3).getAnswer().substring(0, 1));
        map.put("da4[2]", questionList.get(3).getAnswer().substring(1, 2));
        map.put("da4[3]", questionList.get(3).getAnswer().substring(2, 3));
        map.put("da4[4]", questionList.get(3).getAnswer().substring(3, 4));
        map.put("da4[5]", "1".equals(questionList.get(3).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(3).getAnswer().substring(5, questionList.get(3).getAnswer().length()) : "");

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "5".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "7".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7", "3".equals(questionList.get(6).getAnswer().substring(0, 1)) ?
                questionList.get(6).getAnswer().substring(2, questionList.get(6).getAnswer().length()) :
                questionList.get(6).getAnswer().substring(0, 1));

        map.put("wen8", questionList.get(7).getQuestion());
        map.put("da8[1]", questionList.get(7).getAnswer().substring(0, 1));
        map.put("da8[2]", questionList.get(7).getAnswer().substring(1, 2));
        map.put("da8[3]", questionList.get(7).getAnswer().substring(2, 3));
        map.put("da8[4]", "1".equals(questionList.get(7).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(7).getAnswer().substring(4, questionList.get(7).getAnswer().length())
                : "");
        map.put("wen9", questionList.get(8).getQuestion());
        map.put("da9", questionList.get(8).getAnswer().substring(0, 1));

        map.put("wen10", questionList.get(9).getQuestion());
        map.put("da10[1]", questionList.get(9).getAnswer().substring(0, 1));
        map.put("da10[2]", questionList.get(9).getAnswer().substring(1, 2));
        map.put("da10[3]", questionList.get(9).getAnswer().substring(2, 3));
        map.put("wen11", questionList.get(10).getQuestion());
        map.put("da11", questionList.get(10).getAnswer().substring(0, 1));
        map.put("wen12", questionList.get(11).getQuestion());
        String answer12 = questionList.get(11).getAnswer();
        String[] split1 = answer12.split("\\|");
        map.put("da12[type]", split1[0]);
        Log.i("dt_mapInforamtion", map.toString());
        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da12img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen13", questionList.get(12).getQuestion());
        map.put("da13[1]", questionList.get(12).getAnswer().substring(0, 1));
        map.put("da13[2]", questionList.get(12).getAnswer().substring(1, 2));
        map.put("da13[3]", questionList.get(12).getAnswer().substring(2, 3));
        map.put("da13[4]", questionList.get(12).getAnswer().substring(3, 4));
        map.put("da13[5]", questionList.get(12).getAnswer().substring(4, 5));

        map.put("wen14", questionList.get(13).getQuestion());
        String type = questionList.get(13).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(13).getAnswer().split("\\|");
            map.put("da14[type]", split[0]);
            map.put("da14[2]", "str" + split[1]);
            map.put("da14[3]", "str" + "");
            map.put("da14[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(13).getAnswer();
            map.put("da14[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da14[2]", "str" + mDate);
            map.put("da14[3]", "str" + mTime);
            map.put("da14[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(13).getAnswer().split("\\|");
            map.put("da14[type]", split[0]);
            map.put("da14[2]", "str" + split[1]);
            map.put("da14[3]", "");
            map.put("da14[4]", "");
        } else {
            map.put("da14[type]", type);
            map.put("da14[2]", "");
            map.put("da14[3]", "");
            map.put("da14[4]", "");
        }
        map.put("wen15", questionList.get(14).getQuestion());
        String[] split = questionList.get(14).getAnswer().split("\\|");
        map.put("da15", "1".equals(questionList.get(14).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 3, 15);
        else
            question = JsonUtils.getENQuestion(this, 3, 15);
        map.put("wen16", question);
        map.put("da16", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 草坪修剪下单
     */
    private void requestCPXJOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", "1".equals(questionList.get(0).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(0).getAnswer().substring(4, questionList.get(0).getAnswer().length()) : "");
        map.put("wen2", questionList.get(1).getQuestion());

        Log.i("da2Information--", questionList.get(1).getAnswer());
        if (AppGlobal.getInstance().getLagStr().equals("en")) {
            map.put("da2", "str" + questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) + "sq ft");
        } else {
            map.put("da2", "str" + questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) + "平方英尺");
        }
        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", questionList.get(2).getAnswer().substring(0, 1));
        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", questionList.get(3).getAnswer().substring(0, 1));
        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", questionList.get(4).getAnswer().substring(0, 1));
        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "5".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7[1]", questionList.get(6).getAnswer().substring(0, 1));
        map.put("da7[2]", questionList.get(6).getAnswer().substring(1, 2));
        map.put("da7[3]", questionList.get(6).getAnswer().substring(2, 3));
        map.put("da7[4]", questionList.get(6).getAnswer().substring(3, 4));
        map.put("da7[5]", questionList.get(6).getAnswer().substring(4, 5));
        map.put("da7[6]", questionList.get(6).getAnswer().substring(5, 6));
        map.put("da7[7]", questionList.get(6).getAnswer().substring(6, 7));
        map.put("wen8", questionList.get(7).getQuestion());
        map.put("da8[1]", questionList.get(7).getAnswer().substring(0, 1));
        map.put("da8[2]", questionList.get(7).getAnswer().substring(1, 2));
        map.put("da8[3]", questionList.get(7).getAnswer().substring(2, 3));
        map.put("da8[4]", questionList.get(7).getAnswer().substring(3, 4));
        map.put("da8[5]", questionList.get(7).getAnswer().substring(4, 5));
        map.put("wen9", questionList.get(8).getQuestion());
        map.put("da9", questionList.get(8).getAnswer().substring(0, 1));

        map.put("wen10", questionList.get(9).getQuestion());
        String answer10 = questionList.get(9).getAnswer();
        String[] split1 = answer10.split("\\|");
        map.put("da10[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da10img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");


        map.put("wen11", questionList.get(10).getQuestion());
        String type = questionList.get(10).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(10).getAnswer().split("\\|");
            map.put("da11[type]", split[0]);
            map.put("da11[2]", "str" + split[1]);
            map.put("da11[3]", "str" + "");
            map.put("da11[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(10).getAnswer();
            map.put("da11[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da11[2]", "str" + mDate);
            map.put("da11[3]", "str" + mTime);
            map.put("da11[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(10).getAnswer().split("\\|");
            map.put("da11[type]", split[0]);
            map.put("da11[2]", "str" + split[1]);
            map.put("da11[3]", "");
            map.put("da11[4]", "");
        } else {
            map.put("da11[type]", type);
            map.put("da11[2]", "");
            map.put("da11[3]", "");
            map.put("da11[4]", "");
        }
        Log.i("cp_mapInformation", map.toString());
        map.put("wen12", questionList.get(11).getQuestion());
        String[] split = questionList.get(11).getAnswer().split("\\|");
        map.put("da12", "1".equals(questionList.get(11).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 2, 12);
        else
            question = JsonUtils.getENQuestion(this, 2, 12);
        map.put("wen13", question);
        map.put("da13", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("response", response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 高压清洗下单
     */
    private void requestGYQXOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", questionList.get(0).getAnswer().substring(4, 5));
        map.put("da1[6]", "1".equals(questionList.get(0).getAnswer().substring(5, 6)) ?
                "str" + questionList.get(0).getAnswer().substring(6, questionList.get(0).getAnswer().length()) : "");
        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "6".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));
        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "5".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));
        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "5".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));
        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "4".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));
        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6[1]", questionList.get(5).getAnswer().substring(0, 1));
        map.put("da6[2]", questionList.get(5).getAnswer().substring(1, 2));
        map.put("da6[3]", questionList.get(5).getAnswer().substring(2, 3));
        map.put("da6[4]", questionList.get(5).getAnswer().substring(3, 4));
        map.put("da6[5]", questionList.get(5).getAnswer().substring(4, 5));
        map.put("wen7", questionList.get(6).getQuestion());

        String answer7 = questionList.get(6).getAnswer();
        String[] split1 = answer7.split("\\|");
        map.put("da7[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da7img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen8", questionList.get(7).getQuestion());
        String type = questionList.get(7).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "str" + "");
            map.put("da8[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(7).getAnswer();
            map.put("da8[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da8[2]", "str" + mDate);
            map.put("da8[3]", "str" + mTime);
            map.put("da8[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        } else {
            map.put("da8[type]", type);
            map.put("da8[2]", "");
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        }
        map.put("wen9", questionList.get(8).getQuestion());
        String[] split = questionList.get(8).getAnswer().split("\\|");
        map.put("da9", "1".equals(questionList.get(8).getAnswer().substring(0, 1)) ? "1" : split[1]);

        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 4, 9);
        else
            question = JsonUtils.getENQuestion(this, 4, 9);
        map.put("wen10", question);
        map.put("da10", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 垃圾处理
     */
    private void requestLJCLOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "5".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));
        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", questionList.get(1).getAnswer().substring(1, 2));
        map.put("da2[3]", questionList.get(1).getAnswer().substring(2, 3));
        map.put("da2[4]", questionList.get(1).getAnswer().substring(3, 4));
        map.put("da2[5]", questionList.get(1).getAnswer().substring(4, 5));
        map.put("da2[6]", questionList.get(1).getAnswer().substring(5, 6));
        map.put("da2[7]", "1".equals(questionList.get(1).getAnswer().substring(6, 7)) ?
                "str" + questionList.get(1).getAnswer().substring(7, questionList.get(1).getAnswer().length()) : "");
        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "5".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));
        map.put("wen4", questionList.get(3).getQuestion());

        String answer4 = questionList.get(3).getAnswer();
        String[] split1 = answer4.split("\\|");
        map.put("da4[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da4img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen5", questionList.get(4).getQuestion());
        String type = questionList.get(4).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(4).getAnswer().split("\\|");
            map.put("da5[type]", split[0]);
            map.put("da5[2]", "str" + split[1]);
            map.put("da5[3]", "str" + "");
            map.put("da5[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(4).getAnswer();
            map.put("da5[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da5[2]", "str" + mDate);
            map.put("da5[3]", "str" + mTime);
            map.put("da5[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(4).getAnswer().split("\\|");
            map.put("da5[type]", split[0]);
            map.put("da5[2]", "str" + split[1]);
            map.put("da5[3]", "");
            map.put("da5[4]", "");
        } else {
            map.put("da5[type]", type);
            map.put("da5[2]", "");
            map.put("da5[3]", "");
            map.put("da5[4]", "");
        }
        map.put("wen6", questionList.get(5).getQuestion());
        String[] split = questionList.get(5).getAnswer().split("\\|");
        map.put("da6", "1".equals(questionList.get(5).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 5, 6);
        else
            question = JsonUtils.getENQuestion(this, 5, 6);
        map.put("wen7", question);
        map.put("da7", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 搬运搬家下单
     */
    private void requestBYBJOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "6".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "5".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "6".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5[1]", questionList.get(4).getAnswer().substring(0, 1));
        map.put("da5[2]", questionList.get(4).getAnswer().substring(1, 2));
        map.put("da5[3]", questionList.get(4).getAnswer().substring(2, 3));
        map.put("da5[4]", questionList.get(4).getAnswer().substring(3, 4));
        map.put("da5[5]", "1".equals(questionList.get(4).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(4).getAnswer().substring(5, questionList.get(4).getAnswer().length()) : "");

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7", "3".equals(questionList.get(6).getAnswer().substring(0, 1)) ?
                "str" + questionList.get(6).getAnswer().substring(2, questionList.get(6).getAnswer().length()) :
                questionList.get(6).getAnswer().substring(0,1));

        map.put("wen8", questionList.get(7).getQuestion());
        map.put("da8", "3".equals(questionList.get(7).getAnswer().substring(0, 1)) ?
                "str" + questionList.get(7).getAnswer().substring(2, questionList.get(7).getAnswer().length()) :
                questionList.get(7).getAnswer().substring(0,1));

        map.put("wen9", questionList.get(8).getQuestion());
        map.put("da9", questionList.get(8).getAnswer().substring(0, 1));

        map.put("wen10", questionList.get(9).getQuestion());
        String answer10 = questionList.get(9).getAnswer();
        String[] split1 = answer10.split("\\|");
        map.put("da10[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da10img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");


        map.put("wen11", questionList.get(10).getQuestion());
        String type = questionList.get(10).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(10).getAnswer().split("\\|");
            map.put("da11[type]", split[0]);
            map.put("da11[2]", "str" + split[1]);
            map.put("da11[3]", "str" + "");
            map.put("da11[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(10).getAnswer();
            map.put("da11[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da11[2]", "str" + mDate);
            map.put("da11[3]", "str" + mTime);
            map.put("da11[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(10).getAnswer().split("\\|");
            map.put("da11[type]", split[0]);
            map.put("da11[2]", "str" + split[1]);
            map.put("da11[3]", "");
            map.put("da11[4]", "");
        } else {
            map.put("da11[type]", type);
            map.put("da11[2]", "");
            map.put("da11[3]", "");
            map.put("da11[4]", "");
        }
        map.put("wen12", questionList.get(11).getQuestion());
        String[] split = questionList.get(11).getAnswer().split("\\|");
        map.put("da12", "1".equals(questionList.get(11).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 2, 12);
        else
            question = JsonUtils.getENQuestion(this, 2, 12);
        map.put("wen13", question);
        map.put("da13", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("response", response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 安保系统下单
     */
    private void requestABXTOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", questionList.get(0).getAnswer().substring(4, 5));
        map.put("da1[6]", questionList.get(0).getAnswer().substring(5, 6));
        map.put("da1[7]", questionList.get(0).getAnswer().substring(6, 7));
        map.put("da1[8]", questionList.get(0).getAnswer().substring(7, 8));
        map.put("da1[9]", questionList.get(0).getAnswer().substring(8, 9));
        map.put("da1[10]", "1".equals(questionList.get(0).getAnswer().substring(9, 10)) ?
                "str" + questionList.get(0).getAnswer().substring(10, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "8".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "7".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "7".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "7".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "5".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7", "5".equals(questionList.get(6).getAnswer().substring(0, 1)) ?
                questionList.get(6).getAnswer().substring(2, questionList.get(6).getAnswer().length()) :
                questionList.get(6).getAnswer().substring(0, 1));

        map.put("wen8", questionList.get(7).getQuestion());
        String answer8 = questionList.get(7).getAnswer();
        String[] split1 = answer8.split("\\|");
        map.put("da8[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da8img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen9", questionList.get(8).getQuestion());
        String type = questionList.get(8).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(8).getAnswer().split("\\|");
            map.put("da9[type]", split[0]);
            map.put("da9[2]", "str" + split[1]);
            map.put("da9[3]", "str" + "");
            map.put("da9[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8_1 = questionList.get(8).getAnswer();
            map.put("da9[type]", answer8_1.substring(0, 1));
            String[] da = answer8_1.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da9[2]", "str" + mDate);
            map.put("da9[3]", "str" + mTime);
            map.put("da9[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(8).getAnswer().split("\\|");
            map.put("da9[type]", split[0]);
            map.put("da9[2]", "str" + split[1]);
            map.put("da9[3]", "");
            map.put("da9[4]", "");
        } else {
            map.put("da9[type]", type);
            map.put("da9[2]", "");
            map.put("da9[3]", "");
            map.put("da9[4]", "");
        }

        map.put("wen10", questionList.get(9).getQuestion());
        String[] split = questionList.get(9).getAnswer().split("\\|");
        map.put("da10", "1".equals(questionList.get(9).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 7, 10);
        else
            question = JsonUtils.getENQuestion(this, 7, 10);
        map.put("wen11", question);
        map.put("da11", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 除害下单
     */
    private void requestCHXDOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", questionList.get(0).getAnswer().substring(4, 5));
        map.put("da1[6]", questionList.get(0).getAnswer().substring(5, 6));
        map.put("da1[7]", questionList.get(0).getAnswer().substring(6, 7));
        map.put("da1[8]", questionList.get(0).getAnswer().substring(7, 8));
        map.put("da1[9]", questionList.get(0).getAnswer().substring(8, 9));
        map.put("da1[10]", "1".equals(questionList.get(0).getAnswer().substring(9, 10)) ?
                "str" + questionList.get(0).getAnswer().substring(10, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", questionList.get(1).getAnswer().substring(1, 2));
        map.put("da2[3]", questionList.get(1).getAnswer().substring(2, 3));
        map.put("da2[4]", questionList.get(1).getAnswer().substring(3, 4));
        map.put("da2[5]", "1".equals(questionList.get(1).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(1).getAnswer().substring(5, questionList.get(1).getAnswer().length()) : "");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "5".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "5".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());

        String answer5 = questionList.get(4).getAnswer();
        String[] split1 = answer5.split("\\|");
        map.put("da5[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da5img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen6", questionList.get(5).getQuestion());
        String type = questionList.get(5).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(5).getAnswer().split("\\|");
            map.put("da6[type]", split[0]);
            map.put("da6[2]", "str" + split[1]);
            map.put("da6[3]", "str" + "");
            map.put("da6[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(5).getAnswer();
            map.put("da6[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da6[2]", "str" + mDate);
            map.put("da6[3]", "str" + mTime);
            map.put("da6[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(5).getAnswer().split("\\|");
            map.put("da6[type]", split[0]);
            map.put("da6[2]", "str" + split[1]);
            map.put("da6[3]", "");
            map.put("da6[4]", "");
        } else {
            map.put("da6[type]", type);
            map.put("da6[2]", "");
            map.put("da6[3]", "");
            map.put("da6[4]", "");
        }
        map.put("wen7", questionList.get(6).getQuestion());
        String[] split = questionList.get(6).getAnswer().split("\\|");
        map.put("da7", "1".equals(questionList.get(6).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 8, 7);
        else
            question = JsonUtils.getENQuestion(this, 8, 7);
        map.put("wen8", question);
        map.put("da8", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {

                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 维修技工下单
     */
    private void requestWXJGOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", questionList.get(0).getAnswer().substring(4, 5));
        map.put("da1[6]", questionList.get(0).getAnswer().substring(5, 6));
        map.put("da1[7]", "1".equals(questionList.get(0).getAnswer().substring(6, 7)) ?
                "str" + questionList.get(0).getAnswer().substring(7, questionList.get(0).getAnswer().length()) : "");


        for (int i = 0; i < questionList.size(); i++) {
            Log.i("answer" + (i + 1), questionList.get(i).getQuestion() + "----" + questionList.get(i).getAnswer());
        }
        map.put("wen2", questionList.get(1).getQuestion());
        String answer2 = questionList.get(1).getAnswer();
        for (int i = 0; i < 16; i++) {
            map.put("da2[" + (i + 1) + "]", answer2.substring(i, i + 1));
        }
        map.put("da2[17]", "1".equals(answer2.substring(16, 17)) ?
                "str" + answer2.substring(17, answer2.length()) : "");


        map.put("wen3", questionList.get(2).getQuestion());
        String answer4 = questionList.get(2).getAnswer();
        for (int i = 0; i < 9; i++) {
            map.put("da3[" + (i + 1) + "]", answer4.substring(i, i + 1));
        }
        map.put("da3[10]", answer4.substring(9, 10).equals("1") ?
                "str" + answer4.substring(10, answer4.length()) : "");


        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", questionList.get(3).getAnswer().equals("") ? "str" : "str" + questionList.get(3).getAnswer());


//
//
//        map.put("wen4", questionList.get(3).getQuestion());
//        map.put("da3", "".equals(questionList.get(2).getAnswer()) ? "" : "str" + questionList.get(2).getAnswer());
//
////        if(questionList.get(3).getAnswer().length()>0){
////            map.put("da4[1]", questionList.get(3).getAnswer().substring(0, 1));
////            map.put("da4[2]", questionList.get(3).getAnswer().substring(1, 2));
////            map.put("da4[3]", questionList.get(3).getAnswer().substring(2, 3));
////            map.put("da4[4]", questionList.get(3).getAnswer().substring(3, 4));
////            map.put("da4[5]", questionList.get(3).getAnswer().substring(4, 5));
////            map.put("da4[6]", questionList.get(3).getAnswer().substring(5, 6));
////            map.put("da4[7]", questionList.get(3).getAnswer().substring(6, 7));
////            map.put("da4[8]", questionList.get(3).getAnswer().substring(7, 8));
////            map.put("da4[9]", questionList.get(3).getAnswer().substring(8, 9));
////            map.put("da4[10]", "1".equals(questionList.get(3).getAnswer().substring(9, 10)) ?
////                    "str" + questionList.get(3).getAnswer().substring(10, questionList.get(3).getAnswer().length()) : "");
////        }else{
////            map.put("da4[1]", "");
////            map.put("da4[2]", "");
////            map.put("da4[3]", "");
////            map.put("da4[4]", "");
////            map.put("da4[5]", "");
////            map.put("da4[6]", "");
////            map.put("da4[7]", "");
////            map.put("da4[8]", "");
////            map.put("da4[9]", "");
////            map.put("da4[10]", "");
////        }
        Log.i("answerInformation",questionList.get(3).getAnswer()+"---"+
                questionList.get(4).getAnswer()+"---"+
                questionList.get(5).getAnswer());

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5",  questionList.get(4).getAnswer().substring(0,1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", questionList.get(5).getAnswer().substring(0, 1).equals("5") ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());

        String answer7 = questionList.get(6).getAnswer();
        String[] split1 = answer7.split("\\|");
        map.put("da7[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da7img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen8", questionList.get(7).getQuestion());
        String type = questionList.get(7).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "str" + "");
            map.put("da8[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(7).getAnswer();
            map.put("da8[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da8[2]", "str" + mDate);
            map.put("da8[3]", "str" + mTime);
            map.put("da8[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        } else {
            map.put("da8[type]", type);
            map.put("da8[2]", "");
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        }
        map.put("wen9", questionList.get(8).getQuestion());
        String[] split = questionList.get(8).getAnswer().split("\\|");
        map.put("da9", "1".equals(questionList.get(8).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 9, 9);
        else
            question = JsonUtils.getENQuestion(this, 9, 9);
        map.put("wen10", question);
        map.put("da10", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 房屋维修下单
     */
    private void requestFWWXOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", "1".equals(questionList.get(0).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(0).getAnswer().substring(4, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "5".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "6".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "7".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "6".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7", "5".equals(questionList.get(6).getAnswer().substring(0, 1)) ?
                questionList.get(6).getAnswer().substring(2, questionList.get(6).getAnswer().length()) :
                questionList.get(6).getAnswer().substring(0, 1));

        map.put("wen8", questionList.get(7).getQuestion());
        map.put("da8", "4".equals(questionList.get(7).getAnswer().substring(0, 1)) ?
                questionList.get(7).getAnswer().substring(2, questionList.get(7).getAnswer().length()) :
                questionList.get(7).getAnswer().substring(0, 1));

        map.put("wen9", questionList.get(8).getQuestion());
        String answer9 = questionList.get(8).getAnswer();
        String[] split1 = answer9.split("\\|");
        map.put("da9[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da9img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");


        map.put("wen10", questionList.get(9).getQuestion());
        String type = questionList.get(9).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(9).getAnswer().split("\\|");
            map.put("da10[type]", split[0]);
            map.put("da10[2]", "str" + split[1]);
            map.put("da10[3]", "str" + "");
            map.put("da10[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(9).getAnswer();
            map.put("da10[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da10[2]", "str" + mDate);
            map.put("da10[3]", "str" + mTime);
            map.put("da10[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(9).getAnswer().split("\\|");
            map.put("da10[type]", split[0]);
            map.put("da10[2]", "str" + split[1]);
            map.put("da10[3]", "");
            map.put("da10[4]", "");
        } else {
            map.put("da10[type]", type);
            map.put("da10[2]", "");
            map.put("da10[3]", "");
            map.put("da10[4]", "");
        }
        map.put("wen11", questionList.get(10).getQuestion());
        String[] split = questionList.get(10).getAnswer().split("\\|");
        map.put("da11", "1".equals(questionList.get(10).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 10, 11);
        else
            question = JsonUtils.getENQuestion(this, 10, 11);
        map.put("wen12", question);
        map.put("da12", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("response", response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 园艺维修下单
     */
    private void requestYYWXOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", "1".equals(questionList.get(0).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(0).getAnswer().substring(5, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "5".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "4".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "4".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "8".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        String answer6 = questionList.get(5).getAnswer();
        for (int i = 0; i < 11; i++) {
            map.put("da6[" + (i + 1) + "]", answer6.substring(i, i + 1));
        }
        map.put("da6[12]", "1".equals(answer6.substring(11, 12)) ?
                "str" + answer6.substring(12, answer6.length()) : "");

        map.put("wen7", questionList.get(6).getQuestion());
        String answer7 = questionList.get(6).getAnswer();
        for (int i = 0; i < 9; i++) {
            map.put("da7[" + (i + 1) + "]", answer7.substring(i, i + 1));
        }
        map.put("da7[10]", "1".equals(answer7.substring(9, 10)) ?
                "str" + answer7.substring(10, answer7.length()) : "");

        map.put("wen8", questionList.get(7).getQuestion());
        map.put("da8", "3".equals(questionList.get(7).getAnswer().substring(0, 1)) ?
                questionList.get(7).getAnswer().substring(2, questionList.get(7).getAnswer().length()) :
                questionList.get(7).getAnswer().substring(0, 1));

        map.put("wen9", questionList.get(8).getQuestion());
        String answer9 = questionList.get(8).getAnswer();
        String[] split1 = answer9.split("\\|");
        map.put("da9[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da9img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");


        map.put("wen10", questionList.get(9).getQuestion());
        String type = questionList.get(9).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(9).getAnswer().split("\\|");
            map.put("da10[type]", split[0]);
            map.put("da10[2]", "str" + split[1]);
            map.put("da10[3]", "str" + "");
            map.put("da10[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(9).getAnswer();
            map.put("da10[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da10[2]", "str" + mDate);
            map.put("da10[3]", "str" + mTime);
            map.put("da10[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(9).getAnswer().split("\\|");
            map.put("da10[type]", split[0]);
            map.put("da10[2]", "str" + split[1]);
            map.put("da10[3]", "");
            map.put("da10[4]", "");
        } else {
            map.put("da10[type]", type);
            map.put("da10[2]", "");
            map.put("da10[3]", "");
            map.put("da10[4]", "");
        }
        map.put("wen11", questionList.get(10).getQuestion());
        String[] split = questionList.get(10).getAnswer().split("\\|");
        map.put("da11", "1".equals(questionList.get(10).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 11, 11);
        else
            question = JsonUtils.getENQuestion(this, 11, 11);
        map.put("wen12", question);
        map.put("da12", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("response", response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 家电下单
     */
    private void requestJDXDOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", questionList.get(1).getAnswer().substring(1, 2));
        map.put("da2[3]", questionList.get(1).getAnswer().substring(2, 3));
        map.put("da2[4]", questionList.get(1).getAnswer().substring(3, 4));
        map.put("da2[5]", questionList.get(1).getAnswer().substring(4, 5));
        map.put("da2[6]", questionList.get(1).getAnswer().substring(5, 6));
        map.put("da2[7]", questionList.get(1).getAnswer().substring(6, 7));
        map.put("da2[8]", "1".equals(questionList.get(1).getAnswer().substring(7, 8)) ?
                "str" + questionList.get(1).getAnswer().substring(8, questionList.get(1).getAnswer().length()) : "");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));
        map.put("da3[4]", "1".equals(questionList.get(2).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(2).getAnswer().substring(4, questionList.get(2).getAnswer().length()) : "");

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "".equals(questionList.get(3).getAnswer()) ? "" : "str" + questionList.get(3).getAnswer());

        map.put("wen5", questionList.get(4).getQuestion());

        String answer5 = questionList.get(4).getAnswer();
        String[] split1 = answer5.split("\\|");
        map.put("da5[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da5img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen6", questionList.get(5).getQuestion());
        String type = questionList.get(5).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(5).getAnswer().split("\\|");
            map.put("da6[type]", split[0]);
            map.put("da6[2]", "str" + split[1]);
            map.put("da6[3]", "str" + "");
            map.put("da6[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(5).getAnswer();
            map.put("da6[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da6[2]", "str" + mDate);
            map.put("da6[3]", "str" + mTime);
            map.put("da6[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(5).getAnswer().split("\\|");
            map.put("da6[type]", split[0]);
            map.put("da6[2]", "str" + split[1]);
            map.put("da6[3]", "");
            map.put("da6[4]", "");
        } else {
            map.put("da6[type]", type);
            map.put("da6[2]", "");
            map.put("da6[3]", "");
            map.put("da6[4]", "");
        }
        map.put("wen7", questionList.get(6).getQuestion());
        String[] split = questionList.get(6).getAnswer().split("\\|");
        map.put("da7", "1".equals(questionList.get(6).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 12, 7);
        else
            question = JsonUtils.getENQuestion(this, 12, 7);
        map.put("wen8", question);
        map.put("da8", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 改建翻新下单
     */
    private void requestGJFXOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "5".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        String answer2 = questionList.get(1).getAnswer();
        String[] splitb = answer2.split("\\|");
        String et1 = splitb[1];
        String et2 = splitb[2];
        String et3 = splitb[3];
        map.put("da2[1]", answer2.substring(0, 1));
        map.put("da2[2]", answer2.substring(1, 2));
        map.put("da2[3]", answer2.substring(2, 3));
        map.put("da2[4]", answer2.substring(3, 4));
        map.put("da2[5]", answer2.substring(4, 5));
        map.put("da2[6]", "1".equals(answer2.substring(5, 6)) ?
                et1 : "");
        map.put("da2[7]", answer2.substring(6, 7));
        map.put("da2[8]", "1".equals(answer2.substring(7, 8)) ?
                et2 : "");
        map.put("da2[9]", answer2.substring(8, 9));
        map.put("da2[10]", answer2.substring(9, 10));
        map.put("da2[11]", answer2.substring(10, 11));
        map.put("da2[12]", answer2.substring(11, 12));
        map.put("da2[13]", "1".equals(answer2.substring(12, 13)) ?
                et3 : "");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "6".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "5".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "".equals(questionList.get(5).getAnswer()) ? "" : "str" + questionList.get(5).getAnswer());

        map.put("wen7", questionList.get(6).getQuestion());

        String answer7 = questionList.get(6).getAnswer();
        String[] split1 = answer7.split("\\|");
        map.put("da7[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da7img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen8", questionList.get(7).getQuestion());
        String type = questionList.get(7).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "str" + "");
            map.put("da8[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(7).getAnswer();
            map.put("da8[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da8[2]", "str" + mDate);
            map.put("da8[3]", "str" + mTime);
            map.put("da8[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        } else {
            map.put("da8[type]", type);
            map.put("da8[2]", "");
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        }
        map.put("wen9", questionList.get(8).getQuestion());
        String[] split = questionList.get(8).getAnswer().split("\\|");
        map.put("da9", "1".equals(questionList.get(8).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 13, 9);
        else
            question = JsonUtils.getENQuestion(this, 13, 9);
        map.put("wen10", question);
        map.put("da10", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 室内粉刷下单
     */
    private void requestSNFSOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "5".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        String answer2 = questionList.get(1).getAnswer();
        for (int i = 0; i < 10; i++) {
            map.put("da2[" + (i + 1) + "]", answer2.substring(i, i + 1));
        }
        map.put("da2[11]", "1".equals(answer2.substring(10, 11)) ?
                "str" + answer2.substring(11, answer2.length()) : "");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));
        map.put("da3[4]", questionList.get(2).getAnswer().substring(3, 4));
        map.put("da3[5]", questionList.get(2).getAnswer().substring(4, 5));
        map.put("da3[6]", questionList.get(2).getAnswer().substring(5, 6).equals("0") ? "" : "str" + questionList.get(2).getAnswer().substring(6, questionList.get(2).getAnswer().length()));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "7".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6[1]", questionList.get(5).getAnswer().substring(0, 1));
        map.put("da6[2]", questionList.get(5).getAnswer().substring(1, 2));
        map.put("da6[3]", questionList.get(5).getAnswer().substring(2, 3));
        map.put("da6[4]", questionList.get(5).getAnswer().substring(3, 4));
        map.put("da6[5]", questionList.get(5).getAnswer().substring(4, 5));
        map.put("da6[6]", questionList.get(5).getAnswer().substring(5, 6));
        map.put("da6[7]", questionList.get(5).getAnswer().substring(6, 7).equals("1") ?
                "str" + questionList.get(5).getAnswer().substring(7, questionList.get(5).getAnswer().length()) : "");

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7", "4".equals(questionList.get(6).getAnswer().substring(0, 1)) ?
                questionList.get(6).getAnswer().substring(2, questionList.get(6).getAnswer().length()) :
                questionList.get(6).getAnswer().substring(0, 1));

        map.put("wen8", questionList.get(7).getQuestion());
        map.put("da8", questionList.get(7).getAnswer().substring(0, 1));

        map.put("wen9", questionList.get(8).getQuestion());
        map.put("da9", questionList.get(8).getAnswer().substring(0, 1));

        map.put("wen10", questionList.get(9).getQuestion());
        map.put("da10", questionList.get(9).getAnswer().substring(0, 1));

        map.put("wen11", questionList.get(10).getQuestion());
        String answer11 = questionList.get(10).getAnswer();
        String[] split1 = answer11.split("\\|");
        map.put("da11[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da11img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");


        map.put("wen12", questionList.get(11).getQuestion());
        String type = questionList.get(11).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(11).getAnswer().split("\\|");
            map.put("da12[type]", split[0]);
            map.put("da12[2]", "str" + split[1]);
            map.put("da12[3]", "str" + "");
            map.put("da12[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(11).getAnswer();
            map.put("da12[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da12[2]", "str" + mDate);
            map.put("da12[3]", "str" + mTime);
            map.put("da12[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(11).getAnswer().split("\\|");
            map.put("da12[type]", split[0]);
            map.put("da12[2]", "str" + split[1]);
            map.put("da12[3]", "");
            map.put("da12[4]", "");
        } else {
            map.put("da12[type]", type);
            map.put("da12[2]", "");
            map.put("da12[3]", "");
            map.put("da12[4]", "");
        }
        map.put("wen13", questionList.get(12).getQuestion());
        String[] split = questionList.get(12).getAnswer().split("\\|");
        map.put("da13", "1".equals(questionList.get(12).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 14, 13);
        else
            question = JsonUtils.getENQuestion(this, 14, 13);
        map.put("wen14", question);
        map.put("da14", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                LogUtil.e("response", response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 室外粉刷下单
     */
    private void requestSWFSOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "5".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "4".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "5".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                "str" + questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4[1]", questionList.get(3).getAnswer().substring(0, 1));
        map.put("da4[2]", questionList.get(3).getAnswer().substring(1, 2));
        map.put("da4[3]", questionList.get(3).getAnswer().substring(2, 3));
        map.put("da4[4]", questionList.get(3).getAnswer().substring(3, 4));
        map.put("da4[5]", "1".equals(questionList.get(3).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(3).getAnswer().substring(5, questionList.get(3).getAnswer().length()) : "");

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "4".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "7".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7[1]", questionList.get(6).getAnswer().substring(0, 1));
        map.put("da7[2]", questionList.get(6).getAnswer().substring(1, 2));
        map.put("da7[3]", questionList.get(6).getAnswer().substring(2, 3));
        map.put("da7[4]", questionList.get(6).getAnswer().substring(3, 4));
        map.put("da7[5]", questionList.get(6).getAnswer().substring(4, 5));
        map.put("da7[6]", questionList.get(6).getAnswer().substring(5, 6));
        map.put("da7[7]", questionList.get(6).getAnswer().substring(6, 7));
        map.put("da7[8]", "1".equals(questionList.get(6).getAnswer().substring(7, 8)) ?
                "str" + questionList.get(6).getAnswer().substring(8, questionList.get(6).getAnswer().length()) : "");

        map.put("wen8", questionList.get(7).getQuestion());
        map.put("da8", questionList.get(7).getAnswer().substring(0, 1));

        map.put("wen9", questionList.get(8).getQuestion());
        map.put("da9[1]", questionList.get(8).getAnswer().substring(0, 1));
        map.put("da9[2]", questionList.get(8).getAnswer().substring(1, 2));
        map.put("da9[3]", questionList.get(8).getAnswer().substring(2, 3));
        map.put("da9[4]", questionList.get(8).getAnswer().substring(3, 4));
        map.put("da9[5]", questionList.get(8).getAnswer().substring(4, 5));
        map.put("da9[6]", questionList.get(8).getAnswer().substring(5, 6));
        map.put("da9[7]", questionList.get(8).getAnswer().substring(6, 7));
        map.put("da9[8]", "1".equals(questionList.get(8).getAnswer().substring(7, 8)) ?
                "str" + questionList.get(8).getAnswer().substring(8, questionList.get(8).getAnswer().length()) : "");

        map.put("wen10", questionList.get(9).getQuestion());
        String answer10 = questionList.get(9).getAnswer();
        String[] split1 = answer10.split("\\|");
        map.put("da10[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da10img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");


        map.put("wen11", questionList.get(10).getQuestion());
        String type = questionList.get(10).getAnswer().substring(0, 1);

        Log.i("fjdlkjfsd", type);

        if ("4".equals(type)) {
            String[] split = questionList.get(10).getAnswer().split("\\|");
            map.put("da11[type]", split[0]);
            map.put("da11[2]", "str" + split[1]);
            map.put("da11[3]", "str" + "");
            map.put("da11[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(10).getAnswer();
            map.put("da11[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da11[2]", "str" + mDate);
            map.put("da11[3]", "str" + mTime);
            map.put("da11[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(10).getAnswer().split("\\|");
            map.put("da11[type]", split[0]);
            map.put("da11[2]", "str" + split[1]);
            map.put("da11[3]", "");
            map.put("da11[4]", "");
        } else {
            map.put("da11[type]", type);
            map.put("da11[2]", "");
            map.put("da11[3]", "");
            map.put("da11[4]", "");
        }
        map.put("wen12", questionList.get(11).getQuestion());
        String[] split = questionList.get(11).getAnswer().split("\\|");
        map.put("da12", "1".equals(questionList.get(11).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 15, 12);
        else
            question = JsonUtils.getENQuestion(this, 15, 12);
        map.put("wen13", question);
        map.put("da13", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("response", response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 换锁下单
     */
    private void requestHSXDOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "6".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", questionList.get(1).getAnswer().substring(1, 2));
        map.put("da2[3]", questionList.get(1).getAnswer().substring(2, 3));
        map.put("da2[4]", questionList.get(1).getAnswer().substring(3, 4));
        map.put("da2[5]", questionList.get(1).getAnswer().substring(4, 5));
        map.put("da2[6]", questionList.get(1).getAnswer().substring(5, 6));
        map.put("da2[7]", "1".equals(questionList.get(1).getAnswer().substring(6, 7)) ?
                "str" + questionList.get(1).getAnswer().substring(7, questionList.get(1).getAnswer().length()) : "");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));
        map.put("da3[4]", questionList.get(2).getAnswer().substring(3, 4));
        map.put("da3[5]", questionList.get(2).getAnswer().substring(4, 5));
        map.put("da3[6]", "1".equals(questionList.get(2).getAnswer().substring(5, 6)) ?
                "str" + questionList.get(2).getAnswer().substring(6, questionList.get(2).getAnswer().length()) : "");

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "3".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());

        String answer5 = questionList.get(4).getAnswer();
        String[] split1 = answer5.split("\\|");
        map.put("da5[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da5img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen6", questionList.get(5).getQuestion());
        String type = questionList.get(5).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(5).getAnswer().split("\\|");
            map.put("da6[type]", split[0]);
            map.put("da6[2]", "str" + split[1]);
            map.put("da6[3]", "str" + "");
            map.put("da6[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(5).getAnswer();
            map.put("da6[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da6[2]", "str" + mDate);
            map.put("da6[3]", "str" + mTime);
            map.put("da6[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(5).getAnswer().split("\\|");
            map.put("da6[type]", split[0]);
            map.put("da6[2]", "str" + split[1]);
            map.put("da6[3]", "");
            map.put("da6[4]", "");
        } else {
            map.put("da6[type]", type);
            map.put("da6[2]", "");
            map.put("da6[3]", "");
            map.put("da6[4]", "");
        }
        map.put("wen7", questionList.get(6).getQuestion());
        String[] split = questionList.get(6).getAnswer().split("\\|");
        map.put("da7", "1".equals(questionList.get(6).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 16, 7);
        else
            question = JsonUtils.getENQuestion(this, 16, 7);
        map.put("wen8", question);
        map.put("da8", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 电工下单下单
     */
    private void requestDGXDOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", "1".equals(questionList.get(0).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(0).getAnswer().substring(5, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "7".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));
        map.put("da3[4]", questionList.get(2).getAnswer().substring(3, 4));
        map.put("da3[5]", questionList.get(2).getAnswer().substring(4, 5));
        map.put("da3[6]", questionList.get(2).getAnswer().substring(5, 6));
        map.put("da3[7]", "1".equals(questionList.get(2).getAnswer().substring(6, 7)) ?
                "str" + questionList.get(2).getAnswer().substring(7, questionList.get(2).getAnswer().length()) : "");

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "4".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "5".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());

        String answer7 = questionList.get(6).getAnswer();
        String[] split1 = answer7.split("\\|");
        map.put("da7[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da7img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen8", questionList.get(7).getQuestion());
        String type = questionList.get(7).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "str" + "");
            map.put("da8[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(7).getAnswer();
            map.put("da8[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da8[2]", "str" + mDate);
            map.put("da8[3]", "str" + mTime);
            map.put("da8[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        } else {
            map.put("da8[type]", type);
            map.put("da8[2]", "");
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        }
        map.put("wen9", questionList.get(8).getQuestion());
        String[] split = questionList.get(8).getAnswer().split("\\|");
        map.put("da9", "1".equals(questionList.get(8).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 17, 9);
        else
            question = JsonUtils.getENQuestion(this, 17, 9);
        map.put("wen10", question);
        map.put("da10", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 锅炉维修
     */
    private void requestGLWXOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", questionList.get(0).getAnswer().substring(4, 5));
        map.put("da1[6]", questionList.get(0).getAnswer().substring(5, 6));
        map.put("da1[7]", "1".equals(questionList.get(0).getAnswer().substring(6, 7)) ?
                "str" + questionList.get(0).getAnswer().substring(7, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "4".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));
        map.put("da3[4]", "1".equals(questionList.get(2).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(2).getAnswer().substring(4, questionList.get(2).getAnswer().length()) : "");

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "5".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "5".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "4".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7", "5".equals(questionList.get(6).getAnswer().substring(0, 1)) ?
                questionList.get(6).getAnswer().substring(2, questionList.get(6).getAnswer().length()) :
                questionList.get(6).getAnswer().substring(0, 1));

        map.put("wen8", questionList.get(7).getQuestion());
        String answer8 = questionList.get(7).getAnswer();
        String[] split1 = answer8.split("\\|");
        map.put("da8[type]", split1[0]);

        Map<String, String> mapFile = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            if (i == 0) {
                continue;
            }
            if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                list.add(split1[i]);
            }
        }
        LogUtil.e("List", list + "");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                mapFile.put("da8img[" + i + "]", list.get(i));
            }
        }
        LogUtil.e("mapFile", mapFile + "");

        map.put("wen9", questionList.get(8).getQuestion());
        String type = questionList.get(8).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(8).getAnswer().split("\\|");
            map.put("da9[type]", split[0]);
            map.put("da9[2]", "str" + split[1]);
            map.put("da9[3]", "str" + "");
            map.put("da9[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8_1 = questionList.get(8).getAnswer();
            map.put("da9[type]", answer8.substring(0, 1));
            String[] da = answer8_1.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da9[2]", "str" + mDate);
            map.put("da9[3]", "str" + mTime);
            map.put("da9[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(8).getAnswer().split("\\|");
            map.put("da9[type]", split[0]);
            map.put("da9[2]", "str" + split[1]);
            map.put("da9[3]", "");
            map.put("da9[4]", "");
        } else {
            map.put("da9[type]", type);
            map.put("da9[2]", "");
            map.put("da9[3]", "");
            map.put("da9[4]", "");
        }

        map.put("wen10", questionList.get(9).getQuestion());
        String[] split = questionList.get(9).getAnswer().split("\\|");
        map.put("da10", "1".equals(questionList.get(9).getAnswer().substring(0, 1)) ? "1" : split[1]);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
//        map.put("wen11", questionList.get(10).getQuestion());
//        map.put("da11", questionList.get(10).getAnswer().substring(2, questionList.get(10).getAnswer().length()));
//
//        map.put("wen12", questionList.get(11).getQuestion());
//        String answer12 = questionList.get(11).getAnswer();
//        String[] split2 = answer12.split("\\|");
//        if ("1".equals(split2[0].substring(0, 1))) {
//            map.put("da12[type]", "1");
//            if ("1".equals(split2[0].substring(1, 2))) {
//                map.put("da12[2]", "1");
//            } else {
//                map.put("da12[2]", "0");
//            }
//            map.put("da12[email]", "str" + split2[1]);
//            map.put("da12[phone]", "");
//        } else {
//            map.put("da12[type]", "2");
//            if ("1".equals(split2[0].substring(1, 2))) {
//                map.put("da12[2]", "1");
//            } else {
//                map.put("da12[2]", "0");
//            }
//            map.put("da12[email]", "str" + split2[1]);
//            map.put("da12[phone]", "str" + split2[2]);
//        }

//        map.put("wen11", questionList.get(10).getQuestion());
//        map.put("da11", "".equals(questionList.get(10).getAnswer()) ? "" : "str" + questionList.get(10).getAnswer());
//        String question = "";
//        if ("zh".equalsIgnoreCase(isZhorEn))
//            question = JsonUtils.getZhQuestion(this, 18, 10);
//        else
//            question = JsonUtils.getENQuestion(this, 18, 10);
//        map.put("wen11", question);
//        map.put("da11", "str" + addr);
//        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, mapFile);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("response", response);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoadDialog.closeProgressDialog();
                    }
                });

                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 接机用车
     */
    private void requestJJYCOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "5".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "5".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "3".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "2".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "2".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "4".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        String type = questionList.get(6).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(6).getAnswer().split("\\|");
            map.put("da7[type]", split[0]);
            map.put("da7[2]", "str" + split[1]);
            map.put("da7[3]", "str" + "");
            map.put("da7[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(6).getAnswer();
            map.put("da7[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da7[2]", "str" + mDate);
            map.put("da7[3]", "str" + mTime);
            map.put("da7[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(6).getAnswer().split("\\|");
            map.put("da7[type]", split[0]);
            map.put("da7[2]", "str" + split[1]);
            map.put("da7[3]", "");
            map.put("da7[4]", "");
        } else {
            map.put("da7[type]", type);
            map.put("da7[2]", "");
            map.put("da7[3]", "");
            map.put("da7[4]", "");
        }

        map.put("wen8", questionList.get(7).getQuestion());
        String[] split = questionList.get(7).getAnswer().split("\\|");
        map.put("da8", "1".equals(questionList.get(7).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 19, 8);
        else
            question = JsonUtils.getENQuestion(this, 19, 8);
        map.put("wen9", question);
        map.put("da9", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 陪同代办
     */
    private void requestPTDBOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", "1".equals(questionList.get(0).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(0).getAnswer().substring(4, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "6".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));


        map.put("wen4", questionList.get(3).getQuestion());
        String type = questionList.get(3).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "str" + "");
            map.put("da4[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(3).getAnswer();
            map.put("da4[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da4[2]", "str" + mDate);
            map.put("da4[3]", "str" + mTime);
            map.put("da4[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        } else {
            map.put("da4[type]", type);
            map.put("da4[2]", "");
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        }


        map.put("wen5", questionList.get(4).getQuestion());
        String[] split = questionList.get(4).getAnswer().split("\\|");
        map.put("da5", "1".equals(questionList.get(4).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 20, 5);
        else
            question = JsonUtils.getENQuestion(this, 20, 5);
        map.put("wen6", question);
        map.put("da6", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 保险下单
     */
    private void requestBXXDOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", questionList.get(0).getAnswer().substring(4, 5));
        map.put("da1[6]", questionList.get(0).getAnswer().substring(5, 6));
        map.put("da1[7]", questionList.get(0).getAnswer().substring(6, 7));
//        map.put("da1[8]", questionList.get(0).getAnswer().substring(7, 8));
        map.put("da1[8]", "1".equals(questionList.get(0).getAnswer().substring(7, 8)) ?
                "str" + questionList.get(0).getAnswer().substring(8, questionList.get(0).getAnswer().length()) : "");


        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", questionList.get(1).getAnswer().substring(1, 2));
        map.put("da2[3]", questionList.get(1).getAnswer().substring(2, 3));
        map.put("da2[4]", "1".equals(questionList.get(1).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(1).getAnswer().substring(4, questionList.get(1).getAnswer().length()) : "");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", "1".equals(questionList.get(2).getAnswer().substring(0, 1)) ? "str"
                + questionList.get(2).getAnswer().substring(3, questionList.get(2).getAnswer().length())
                : "");
        map.put("da3[3]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[4]", questionList.get(2).getAnswer().substring(2, 3));


        map.put("wen4", questionList.get(3).getQuestion());
        String type = questionList.get(3).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "str" + "");
            map.put("da4[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(3).getAnswer();
            map.put("da4[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da4[2]", "str" + mDate);
            map.put("da4[3]", "str" + mTime);
            map.put("da4[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        } else {
            map.put("da4[type]", type);
            map.put("da4[2]", "");
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        }


        map.put("wen5", questionList.get(4).getQuestion());
        String[] split = questionList.get(4).getAnswer().split("\\|");
        map.put("da5", "1".equals(questionList.get(4).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 20, 5);
        else
            question = JsonUtils.getENQuestion(this, 20, 5);
        map.put("wen6", question);
        map.put("da6", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 移民咨询
     */
    private void requestYMZXOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "7".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", "9".equals(questionList.get(1).getAnswer().substring(0, 1)) ?
                questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()) :
                questionList.get(1).getAnswer().substring(0, 1));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", "1".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                "str" + questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length())
                : "");
        map.put("da3[3]", questionList.get(2).getAnswer().substring(1, 2));

        map.put("wen4", questionList.get(3).getQuestion());
        String type = questionList.get(3).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "str" + "");
            map.put("da4[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(3).getAnswer();
            map.put("da4[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da4[2]", "str" + mDate);
            map.put("da4[3]", "str" + mTime);
            map.put("da4[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        } else {
            map.put("da4[type]", type);
            map.put("da4[2]", "");
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        }


        map.put("wen5", questionList.get(4).getQuestion());
        String[] split = questionList.get(4).getAnswer().split("\\|");
        map.put("da5", "1".equals(questionList.get(4).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 20, 5);
        else
            question = JsonUtils.getENQuestion(this, 20, 5);
        map.put("wen6", question);
        map.put("da6", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 税务服务
     */
    private void requestSWFWOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", "1".equals(questionList.get(0).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(0).getAnswer().substring(4, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", questionList.get(1).getAnswer().substring(1, 2));
        map.put("da2[3]", "1".equals(questionList.get(1).getAnswer().substring(2, 3)) ?
                "str" + questionList.get(1).getAnswer().substring(3, questionList.get(1).getAnswer().length()) : "");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", "1".equals(questionList.get(2).getAnswer().substring(0, 1)) ? "str"
                + questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length())
                : "");
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));

        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));


        map.put("wen4", questionList.get(3).getQuestion());
        String type = questionList.get(3).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "str" + "");
            map.put("da4[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(3).getAnswer();
            map.put("da4[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da4[2]", "str" + mDate);
            map.put("da4[3]", "str" + mTime);
            map.put("da4[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        } else {
            map.put("da4[type]", type);
            map.put("da4[2]", "");
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        }


        map.put("wen5", questionList.get(4).getQuestion());
        String[] split = questionList.get(4).getAnswer().split("\\|");
        map.put("da5", "1".equals(questionList.get(4).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 20, 5);
        else
            question = JsonUtils.getENQuestion(this, 20, 5);
        map.put("wen6", question);
        map.put("da6", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 驾驶教练
     */
    private void requestJSJLOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);
        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", questionList.get(0).getAnswer().substring(3, 4));
        map.put("da1[5]", "1".equals(questionList.get(0).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(0).getAnswer().substring(5, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        String answer2 = questionList.get(1).getAnswer();
        map.put("da2[1]", "1".equals(answer2.substring(0, 1)) ? "1" : "0");
        map.put("da2[2]", "2".equals(answer2.substring(0, 1)) ? "1" : "0");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));
        map.put("da3[4]", "1".equals(questionList.get(2).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(2).getAnswer().substring(4, questionList.get(2).getAnswer().length()) : "");

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4[1]", questionList.get(3).getAnswer().substring(0, 1));
        map.put("da4[2]", questionList.get(3).getAnswer().substring(1, 2));
        map.put("da4[3]", questionList.get(3).getAnswer().substring(2, 3));
        map.put("da4[4]", "1".equals(questionList.get(3).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(3).getAnswer().substring(4, questionList.get(3).getAnswer().length()) : "");

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "".equals(questionList.get(4).getAnswer()) ? "" : "str" + questionList.get(4).getAnswer());

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6[1]", questionList.get(5).getAnswer().substring(0, 1));
        map.put("da6[2]", questionList.get(5).getAnswer().substring(1, 2));
        map.put("da6[3]", questionList.get(5).getAnswer().substring(2, 3));
        map.put("da6[4]", questionList.get(5).getAnswer().substring(3, 4));
        map.put("da6[5]", questionList.get(5).getAnswer().substring(4, 5));
        map.put("da6[6]", "1".equals(questionList.get(5).getAnswer().substring(5, 6)) ?
                "str" + questionList.get(5).getAnswer().substring(6, questionList.get(5).getAnswer().length()) : "");

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7", "4".equals(questionList.get(6).getAnswer().substring(0, 1)) ?
                questionList.get(6).getAnswer().substring(2, questionList.get(6).getAnswer().length()) :
                questionList.get(6).getAnswer().substring(0, 1));

        map.put("wen8", questionList.get(7).getQuestion());
        map.put("da8", questionList.get(7).getAnswer().substring(0, 1));

        map.put("wen9", questionList.get(8).getQuestion());
        map.put("da9[1]", questionList.get(8).getAnswer().substring(0, 1));
        map.put("da9[2]", questionList.get(8).getAnswer().substring(1, 2));
        map.put("da9[3]", questionList.get(8).getAnswer().substring(2, 3));
        map.put("da9[4]", questionList.get(8).getAnswer().substring(3, 4));
        map.put("da9[5]", questionList.get(8).getAnswer().substring(4, 5));
        map.put("da9[6]", questionList.get(8).getAnswer().substring(5, 6));
        map.put("da9[7]", questionList.get(8).getAnswer().substring(6, 7));

        map.put("wen10", questionList.get(9).getQuestion());
        map.put("da10[1]", questionList.get(9).getAnswer().substring(0, 1));
        map.put("da10[2]", questionList.get(9).getAnswer().substring(1, 2));
        map.put("da10[3]", questionList.get(9).getAnswer().substring(2, 3));
        map.put("da10[4]", questionList.get(9).getAnswer().substring(3, 4));
        map.put("da10[5]", questionList.get(9).getAnswer().substring(4, 5));

        map.put("wen11", questionList.get(10).getQuestion());
        String answer11 = questionList.get(10).getAnswer();

        map.put("da11[1]", "1".equals(answer11.substring(0, 1)) ? "1" : "0");
        map.put("da11[2]", "2".equals(answer11.substring(0, 1)) ? "1" : "0");
        map.put("da11[3]", "3".equals(answer11.substring(0, 1)) ? "1" : "0");
        map.put("da11[4]", "4".equals(answer11.substring(0, 1)) ? answer11.substring(2, answer11.length()) : "");
        map.put("da11[5]", "5".equals(answer11.substring(0, 1)) ? answer11.substring(2, answer11.length()) : "");

        map.put("wen12", questionList.get(11).getQuestion());
        map.put("da12", "4".equals(questionList.get(11).getAnswer().substring(0, 1)) ?
                questionList.get(11).getAnswer().substring(2, questionList.get(11).getAnswer().length()) :
                questionList.get(11).getAnswer().substring(0, 1));

        map.put("wen13", questionList.get(12).getQuestion());
        map.put("da13[1]", questionList.get(12).getAnswer().substring(0, 1));
        map.put("da13[2]", "1".equals(questionList.get(12).getAnswer().substring(0, 1)) ? "str"
                + questionList.get(12).getAnswer().substring(2, questionList.get(12).getAnswer().length())
                : "");
        map.put("da13[3]", questionList.get(12).getAnswer().substring(1, 2));

        map.put("wen14", questionList.get(13).getQuestion());
        String[] split = questionList.get(13).getAnswer().split("\\|");
        map.put("da14", "1".equals(questionList.get(13).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 24, 14);
        else
            question = JsonUtils.getENQuestion(this, 24, 14);
        map.put("wen15", question);
        map.put("da15", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 翻译公证
     */
    private void requestFYGZOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));
        map.put("da1[4]", "1".equals(questionList.get(0).getAnswer().substring(3, 4)) ?
                "str" + questionList.get(0).getAnswer().substring(4, questionList.get(0).getAnswer().length()) : "");

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", "1".equals(questionList.get(1).getAnswer().substring(0, 1)) ? "str"
                + questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length())
                : "");
        map.put("da2[3]", questionList.get(1).getAnswer().substring(1, 2));

        map.put("wen3", questionList.get(2).getQuestion());
        String type = questionList.get(2).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(2).getAnswer().split("\\|");
            map.put("da3[type]", split[0]);
            map.put("da3[2]", "str" + split[1]);
            map.put("da3[3]", "str" + "");
            map.put("da3[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(2).getAnswer();
            map.put("da3[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da3[2]", "str" + mDate);
            map.put("da3[3]", "str" + mTime);
            map.put("da3[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(2).getAnswer().split("\\|");
            map.put("da3[type]", split[0]);
            map.put("da3[2]", "str" + split[1]);
            map.put("da3[3]", "");
            map.put("da3[4]", "");
        } else {
            map.put("da3[type]", type);
            map.put("da3[2]", "");
            map.put("da3[3]", "");
            map.put("da3[4]", "");
        }

        map.put("wen4", questionList.get(3).getQuestion());
        String[] split = questionList.get(3).getAnswer().split("\\|");
        map.put("da4", "1".equals(questionList.get(3).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 25, 4);
        else
            question = JsonUtils.getENQuestion(this, 25, 4);
        map.put("wen5", question);
        map.put("da5", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 法律咨询
     */
    private void requestFLZXOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        String answer1 = questionList.get(0).getAnswer();
        for (int i = 0; i < 18; i++) {
            map.put("da1[" + (i + 1) + "]", answer1.substring(i, i + 1));
        }

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", questionList.get(1).getAnswer().substring(1, 2));
        map.put("da2[3]", "1".equals(questionList.get(1).getAnswer().substring(2, 3)) ?
                "str" + questionList.get(1).getAnswer().substring(3, questionList.get(1).getAnswer().length()) : "");

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", "1".equals(questionList.get(2).getAnswer().substring(0, 1)) ? "str"
                + questionList.get(2).getAnswer().substring(3, questionList.get(2).getAnswer().length())
                : "");
        map.put("da3[3]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[4]", questionList.get(2).getAnswer().substring(2, 3));

        map.put("wen4", questionList.get(3).getQuestion());
        String type = questionList.get(3).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "str" + "");
            map.put("da4[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(3).getAnswer();
            map.put("da4[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da4[2]", "str" + mDate);
            map.put("da4[3]", "str" + mTime);
            map.put("da4[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(3).getAnswer().split("\\|");
            map.put("da4[type]", split[0]);
            map.put("da4[2]", "str" + split[1]);
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        } else {
            map.put("da4[type]", type);
            map.put("da4[2]", "");
            map.put("da4[3]", "");
            map.put("da4[4]", "");
        }

        map.put("wen5", questionList.get(4).getQuestion());
        String[] split = questionList.get(4).getAnswer().split("\\|");
        map.put("da5", "1".equals(questionList.get(4).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 26, 5);
        else
            question = JsonUtils.getENQuestion(this, 26, 5);
        map.put("wen6", question);
        map.put("da6", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 房产买卖
     */
    private void requestFCMMOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1[1]", questionList.get(0).getAnswer().substring(0, 1));
        map.put("da1[2]", questionList.get(0).getAnswer().substring(1, 2));
        map.put("da1[3]", questionList.get(0).getAnswer().substring(2, 3));

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2", questionList.get(1).getAnswer().substring(2, questionList.get(1).getAnswer().length()));

        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3[1]", questionList.get(2).getAnswer().substring(0, 1));
        map.put("da3[2]", questionList.get(2).getAnswer().substring(1, 2));
        map.put("da3[3]", questionList.get(2).getAnswer().substring(2, 3));
        map.put("da3[4]", questionList.get(2).getAnswer().substring(3, 4));
        map.put("da3[5]", "1".equals(questionList.get(2).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(2).getAnswer().substring(5, questionList.get(2).getAnswer().length()) : "");

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "6".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "6".equals(questionList.get(4).getAnswer().substring(0, 1)) ?
                questionList.get(4).getAnswer().substring(2, questionList.get(4).getAnswer().length()) :
                questionList.get(4).getAnswer().substring(0, 1));

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "0".equals(questionList.get(5).getAnswer().substring(0, 1)) ?
                questionList.get(5).getAnswer().substring(2, questionList.get(5).getAnswer().length()) :
                questionList.get(5).getAnswer().substring(0, 1));

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7[1]", questionList.get(6).getAnswer().substring(0, 1));
        map.put("da7[2]", "1".equals(questionList.get(6).getAnswer().substring(0, 1)) ? "str"
                + questionList.get(6).getAnswer().substring(3, questionList.get(6).getAnswer().length())
                : "");
        map.put("da7[3]", questionList.get(6).getAnswer().substring(1, 2));
        map.put("da7[4]", questionList.get(6).getAnswer().substring(2, 3));

        map.put("wen8", questionList.get(7).getQuestion());
        String type = questionList.get(7).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "str" + "");
            map.put("da8[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(7).getAnswer();
            map.put("da8[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da8[2]", "str" + mDate);
            map.put("da8[3]", "str" + mTime);
            map.put("da8[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        } else {
            map.put("da8[type]", type);
            map.put("da8[2]", "");
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        }
        map.put("wen9", questionList.get(8).getQuestion());
        String[] split = questionList.get(8).getAnswer().split("\\|");
        map.put("da9", "1".equals(questionList.get(8).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 27, 9);
        else
            question = JsonUtils.getENQuestion(this, 27, 9);
        map.put("wen10", question);
        map.put("da10", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    /**
     * 旅游代理
     */
    private void requestLYDLOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("type", id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("at_uid", at_uid);
        map.put("h_id", h_id);
        map.put("lat", lat);
        map.put("long", lng);

        map.put("wen1", questionList.get(0).getQuestion());
        map.put("da1", "5".equals(questionList.get(0).getAnswer().substring(0, 1)) ?
                questionList.get(0).getAnswer().substring(2, questionList.get(0).getAnswer().length()) :
                questionList.get(0).getAnswer().substring(0, 1));

        map.put("wen2", questionList.get(1).getQuestion());
        map.put("da2[1]", questionList.get(1).getAnswer().substring(0, 1));
        map.put("da2[2]", questionList.get(1).getAnswer().substring(1, 2));
        map.put("da2[3]", questionList.get(1).getAnswer().substring(2, 3));
        map.put("da2[4]", questionList.get(1).getAnswer().substring(3, 4));
        map.put("da2[5]", "1".equals(questionList.get(1).getAnswer().substring(4, 5)) ?
                "str" + questionList.get(1).getAnswer().substring(5, questionList.get(1).getAnswer().length()) : "");


        map.put("wen3", questionList.get(2).getQuestion());
        map.put("da3", "3".equals(questionList.get(2).getAnswer().substring(0, 1)) ?
                questionList.get(2).getAnswer().substring(2, questionList.get(2).getAnswer().length()) :
                questionList.get(2).getAnswer().substring(0, 1));

        map.put("wen4", questionList.get(3).getQuestion());
        map.put("da4", "5".equals(questionList.get(3).getAnswer().substring(0, 1)) ?
                questionList.get(3).getAnswer().substring(2, questionList.get(3).getAnswer().length()) :
                questionList.get(3).getAnswer().substring(0, 1));

        map.put("wen5", questionList.get(4).getQuestion());
        map.put("da5", "".equals(questionList.get(4).getAnswer()) ? "" : "str" + questionList.get(4).getAnswer());

        map.put("wen6", questionList.get(5).getQuestion());
        map.put("da6", "".equals(questionList.get(5).getAnswer()) ? "" : "str" + questionList.get(5).getAnswer());

        map.put("wen7", questionList.get(6).getQuestion());
        map.put("da7[1]", questionList.get(6).getAnswer().substring(0, 1));
        map.put("da7[2]", "1".equals(questionList.get(6).getAnswer().substring(0, 1)) ? "str"
                + questionList.get(6).getAnswer().substring(3, questionList.get(6).getAnswer().length())
                : "");
        map.put("da7[3]", questionList.get(6).getAnswer().substring(1, 2));
        map.put("da7[4]", questionList.get(6).getAnswer().substring(2, 3));

        map.put("wen8", questionList.get(7).getQuestion());
        String type = questionList.get(7).getAnswer().substring(0, 1);
        if ("4".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "str" + "");
            map.put("da8[4]", split.length > 3 ? "str" + split[3] : "");
        } else if ("3".equals(type)) {
            String answer8 = questionList.get(7).getAnswer();
            map.put("da8[type]", answer8.substring(0, 1));
            String[] da = answer8.split("\n");
            String mDate = TimeUtils.getTimeStamp(da[1].substring(da[1].indexOf(":") + 1, da[1].length()), "yyyy-MM-dd");
            String mTime = TimeUtils.getTimeStamp(da[2].substring(da[2].indexOf(":") + 1, da[2].length()), "hh:mm");
            String mLength = da[3].substring(da[3].indexOf(":") + 1, da[3].length());
            Log.i("da8_information", mDate + "--" + mTime + "--" + mLength);
            map.put("da8[2]", "str" + mDate);
            map.put("da8[3]", "str" + mTime);
            map.put("da8[4]", mLength);
        } else if ("5".equals(type)) {
            String[] split = questionList.get(7).getAnswer().split("\\|");
            map.put("da8[type]", split[0]);
            map.put("da8[2]", "str" + split[1]);
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        } else {
            map.put("da8[type]", type);
            map.put("da8[2]", "");
            map.put("da8[3]", "");
            map.put("da8[4]", "");
        }

        map.put("wen9", questionList.get(8).getQuestion());
        String[] split = questionList.get(8).getAnswer().split("\\|");
        map.put("da9", "1".equals(questionList.get(8).getAnswer().substring(0, 1)) ? "1" : split[1]);
        String question = "";
        if ("zh".equalsIgnoreCase(isZhorEn))
            question = JsonUtils.getZhQuestion(this, 28, 9);
        else
            question = JsonUtils.getENQuestion(this, 28, 9);
        map.put("wen10", question);
        map.put("da10", "str" + addr);
        map.put("secret_key", SPUtils.getString(IssueRequireMakeSureActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        LogUtil.e("map", map + "");
        RequestBody body = OkHttpUtils.getInstance().SetFileRequestBody(IssueRequireMakeSureActivity.this,map, null);
        Request request = new Request.Builder()
                .url(Contants.down_order)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(this, request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("response", response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, getResources().getString(R.string.commit_success));
                    jumpMain();
                } else {
                    ToastUtil.shortToast(IssueRequireMakeSureActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                questionList.remove(0);
                questionList.add(0, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                questionList.remove(1);
                questionList.add(1, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 300) {
            if (resultCode == RESULT_OK) {
                questionList.remove(2);
                questionList.add(2, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 400) {
            if (resultCode == RESULT_OK) {
                questionList.remove(3);
                questionList.add(3, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 500) {
            if (resultCode == RESULT_OK) {
                questionList.remove(4);
                questionList.add(4, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 600) {
            if (resultCode == RESULT_OK) {
                questionList.remove(5);
                questionList.add(5, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 700) {
            if (resultCode == RESULT_OK) {
                questionList.remove(6);
                questionList.add(6, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 800) {
            if (resultCode == RESULT_OK) {
                questionList.remove(7);
                questionList.add(7, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 900) {
            if (resultCode == RESULT_OK) {
                questionList.remove(8);
                questionList.add(8, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                questionList.remove(9);
                questionList.add(9, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 1100) {
            if (resultCode == RESULT_OK) {
                questionList.remove(10);
                questionList.add(10, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 1200) {
            if (resultCode == RESULT_OK) {
                questionList.remove(11);
                questionList.add(11, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 1300) {
            if (resultCode == RESULT_OK) {
                questionList.remove(12);
                questionList.add(12, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 1400) {
            if (resultCode == RESULT_OK) {
                questionList.remove(13);
                questionList.add(13, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        } else if (requestCode == 1500) {
            if (resultCode == RESULT_OK) {
                questionList.remove(14);
                questionList.add(14, (Question) data.getSerializableExtra("question"));
//                LogUtil.e("questionlist---get", questionList + "");
            }
        }
        adapter.notifyDataSetChanged();
    }
}
