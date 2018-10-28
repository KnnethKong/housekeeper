package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.NewHousEntity;
import com.haiwai.housekeeper.entity.ZYEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.ParseVipJsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SerializableMap;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.VipWDUtils;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommonMultiActivity1 extends AppCompatActivity {
    private TopViewNormalBar top_multi_bar;
    private TextView stv1, stv2, stv3, stv4, stv5, stv6, stv7, stv8, stv9, stv10;
    private TextView mtv1, mtv2, mtv3, mtv4, mtv5, mtv6, mtv7, mtv8, mtv9, mtv10;
    private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
    private boolean isb1 = false, isb2 = false, isb3 = false, isb4 = false,
            isb5 = false, isb6 = false, isb7 = false, isb8 = false, isb9 = false, isb10 = false;
    private TextView tv_title, tv_remark;
    private TextView tv_mtitle, tv_mremark;
    private LinearLayout ll_type2_layout;
    private LinearLayout ll_layout_single;
    private TextView tv_title_a, tv_unit, tv_stitle;
    private EditText et_single_input_price;
    private TextView tv_title_b;
    private View sigleView;
    private View multiView;
    private List<TextView> sigleList = new ArrayList<>();
    private List<TextView> multiList = new ArrayList<>();
    private Button btn_mutli_next;
    private String type = "0";
    private int step = -1;
    private Map<String, ZYEntity> map = new LinkedHashMap<>();
    private List<NewHousEntity.DataBean.DateBean.ProblemBean> problem;
    private boolean proTyp2 = false;
    private boolean tag = false;
    private String isZhorEn = "";
    private ZYEntity data;
    boolean isS = false;
    private ZYEntity mZYEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_multi);
        top_multi_bar = (TopViewNormalBar) findViewById(R.id.top_multi_bar);
        top_multi_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initData() {
        type = getIntent().getStringExtra("type");
        step = getIntent().getIntExtra("step", -1);
        Log.i("stepInformation", step + "--"+type+"-+");
        mZYEntity = (ZYEntity) getIntent().getSerializableExtra("data");
        step = mZYEntity.getStep();

        if ("4".equals(type) && step == 2) {
            if ("68".equals(mZYEntity.getTittleId())) {
                isS = true;
                step = step + 2;
            }
        }
//        else if ("3".equals(type) && step == 3) {
//            if ("45".equals(data.getTittleId())) {
//                // isS = true;
//                step = 4;
//            }
//        }
        Log.i("stepInformation", step + "");

        if (!"0".equals(type)) {
            LoadDialog.showProgressDialog(this);
            Map<String, String> map = new HashMap<>();
            map.put("type", type);
            map.put("secret_key", SPUtils.getString(CommonMultiActivity1.this, "secret", ""));
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(CommonMultiActivity1.this, Contants.hous_problem, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int code = JsonUtils.getJsonInt(response, "status");
                    System.out.println(">>>>>>>>>>自营问题>>" + response);
                    LoadDialog.closeProgressDialog();
                    if (code == 200) {
                        VipWDUtils.ParseData(ParseVipJsonUtils.parseJson(response));
                        bindDataView();
                    } else {
                        ToastUtil.longToast(CommonMultiActivity1.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }
    }



    private void bindDataView() {
        Bundle bundle = getIntent().getExtras();
       // mZYEntity = (ZYEntity) bundle.getSerializable("data");
        Log.i("mzyEntityInformation", mZYEntity.getTitle() + "---" + mZYEntity.getStep());
        String type2 = mZYEntity.getType();
        Log.i("hghInformation22222",
                        "--" + VipWDUtils.getBeans("step" + step).getProblem().get(0).getType2()
                        + "--" + type2 + "--" + mZYEntity.getDaList().get(0).getStrId());
        int count = 0;
        count = VipWDUtils.getBeans("step" + step).getProblem().size();
        Log.i("hghInformation", count + "---" + type + "--" + step + "--" + type2 + "--" + mZYEntity.getDaList().get(0).getStrId());
        if ("1".equals(VipWDUtils.getBeans("step" + step).getProblem().get(0).getType2()) && "1".equals(VipWDUtils.getBeans("step" + step).getType2())) {
            ll_type2_layout.setVisibility(View.GONE);
            sigleView.setVisibility(View.VISIBLE);
            multiView.setVisibility(View.GONE);

            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                tv_title.setText(VipWDUtils.getBeans("step" + step).getYvalue());
            } else {
                tv_title.setText(VipWDUtils.getBeans("step" + step).getValue());
            }

            if (TextUtils.isEmpty(VipWDUtils.getBeans("step" + step).getRemark())) {
                tv_remark.setVisibility(View.GONE);
            } else {
                tv_remark.setText(VipWDUtils.getBeans("step" + step).getRemark());
            }
            setSingleNum(count, VipWDUtils.getBeans("step" + step).getProblem(), mZYEntity.getDaList().get(0).getStrId());
        } else if ("3".equals(VipWDUtils.getBeans("step" + step).getType2()) && "1".equals(VipWDUtils.getBeans("step" + step).getProblem().get(0).getType2())) {
            ll_type2_layout.setVisibility(View.GONE);
            sigleView.setVisibility(View.GONE);
            multiView.setVisibility(View.VISIBLE);

            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                tv_mtitle.setText(VipWDUtils.getBeans("step" + step).getYvalue());
            } else {
                tv_mtitle.setText(VipWDUtils.getBeans("step" + step).getValue());
            }


            if (TextUtils.isEmpty(VipWDUtils.getBeans("step" + step).getRemark())) {
                tv_mremark.setVisibility(View.GONE);
            } else {
                tv_mremark.setText(VipWDUtils.getBeans("step" + step).getRemark());
            }
            setMultiNum(count, VipWDUtils.getBeans("step" + step).getProblem(), mZYEntity.getDaList());
        } else if ("1".equals(VipWDUtils.getBeans("step" + step).getType2()) && "2".equals(VipWDUtils.getBeans("step" + step).getProblem().get(0).getType2())) {
            ll_type2_layout.setVisibility(View.VISIBLE);

            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                tv_stitle.setText(VipWDUtils.getBeans("step" + step).getYvalue());
            } else {
                tv_stitle.setText(VipWDUtils.getBeans("step" + step).getValue());
            }
            if ("66".equals(mZYEntity.getDaList().get(0).getStrId())) {
                proTyp2 = true;
                tag = false;
                tv_title_a.setSelected(true);
                tv_title_b.setSelected(false);
                et_single_input_price.setText(SPUtils.getString(CommonMultiActivity1.this, "want_money", ""));

                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    tv_title_b.setText(VipWDUtils.getBeans("step" + step).getProblem().get(1).getYvalue());

                } else {
                    tv_title_b.setText(VipWDUtils.getBeans("step" + step).getProblem().get(1).getValue());

                }
            } else {
                proTyp2 = true;
                tag = true;
                tv_title_a.setSelected(false);
                et_single_input_price.setText("");
                et_single_input_price.setVisibility(View.GONE);
                tv_title_b.setSelected(true);
            }
        }
    }

    private void setMultiNum(int count, List<NewHousEntity.DataBean.DateBean.ProblemBean> problem, List<ZYEntity.DaBean> daList) {
        this.problem = problem;
        for (int i = 0; i < count; i++) {
            multiList.get(i).setVisibility(View.VISIBLE);
            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                multiList.get(i).setText(problem.get(i).getYvalue());
            } else {
                multiList.get(i).setText(problem.get(i).getValue());
            }
            if (daList != null && daList.size() > 0) {
                for (int j = 0; j < daList.size(); j++) {
                    Log.i("multiListInfor",daList.get(j).getStrId());
                    String mItem = daList.get(j).getStrId();
                    String[] mSplit = null;
                    if(mItem.contains(",")){
                        mSplit = mItem.split(",");
                    }
                    if(mSplit!=null){
                        for(int m=0;m<mSplit.length;m++){
                            if (mSplit[m].equals(problem.get(i).getId())) {
                                multiList.get(i).setSelected(true);
                            }
                        }
                    }else{
                        if (daList.get(j).getStrId().equals(problem.get(i).getId())) {
                            multiList.get(i).setSelected(true);
                        }
                    }
                }
            }

        }
    }

    private void setSingleNum(int count, List<NewHousEntity.DataBean.DateBean.ProblemBean> problem, String strId) {
        this.problem = problem;
        for (int i = 0; i < count; i++) {
            sigleList.get(i).setTag(problem.get(i).getValue());
            sigleList.get(i).setVisibility(View.VISIBLE);
            if (problem.get(i).getValue().contains("[]")) {
                String content = "";
                if (AppGlobal.getInstance().getLagStr().equals("zh")) {
                    content = problem.get(i).getValue();
                } else {
                    content = problem.get(i).getYvalue();
                }
                String content1 = content.substring(0, content.indexOf("["));
                String content2 = content.substring(content.indexOf("]") + 1, content.length());
                sigleList.get(i).setText(content1);
                ((TextView) findViewById(R.id.tvY)).setText(content2);
                findViewById(R.id.tvY).setVisibility(View.VISIBLE);
            } else if (problem.get(i).getValue().contains("#xia#")) {
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    sigleList.get(i).setText(problem.get(i).getYvalue().substring(0, problem.get(i).getYvalue().indexOf("#")));
                } else {
                    sigleList.get(i).setText(problem.get(i).getValue().substring(0, problem.get(i).getValue().indexOf("#")));
                }
            } else {
                if (AppGlobal.getInstance().getLagStr().equals("zh")) {
                    sigleList.get(i).setText(problem.get(i).getValue());
                } else {
                    sigleList.get(i).setText(problem.get(i).getYvalue());
                }
            }
            Log.i("#information--", strId.substring(0, 1) + "---" + problem.get(i).getValue());
            if ("#".equals(strId.substring(0, 1))) {
                String[] strs = strId.split("#");
                if (strs.length > 1) {
                    if (!TextUtils.isEmpty(strs[1])) {
                        if (strs[1].equals(problem.get(i).getId())) {
                            sigleList.get(i).setSelected(true);
                            setShowView(i + 1, strs[2]);
                        }
                    }
                }
            } else {
                if (!TextUtils.isEmpty(strId)) {
                    if (strId.equals(problem.get(i).getId())) {
                        sigleList.get(i).setSelected(true);
                    }
                }
                if (problem.get(i).getValue().contains("#xia#")) {
                    et7.setVisibility(View.VISIBLE);
                }
                if (problem.get(i).getValue().contains("[]")) {
                    et3.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private List<EditText> etList = new ArrayList<>();

    private void initView() {
        if ("2".equals(type)) {
            top_multi_bar.setTitle(getString(R.string.vip_fwbj));
        } else if ("3".equals(type)) {
            top_multi_bar.setTitle(getString(R.string.vip_tygl));
        } else if ("4".equals(type)) {
            top_multi_bar.setTitle(getString(R.string.vip_fwzl));
        } else if ("5".equals(type)) {
            top_multi_bar.setTitle(getString(R.string.vip_kwgl));
        }

        btn_mutli_next = (Button) findViewById(R.id.btn_mutli_next);
        btn_mutli_next.setOnClickListener(mOnClickListener);
        ll_type2_layout = (LinearLayout) findViewById(R.id.ll_type2_layout);
        ll_layout_single = (LinearLayout) findViewById(R.id.ll_layout_single);
        tv_title_a = (TextView) findViewById(R.id.tv_title_a);
        tv_title_a.setOnClickListener(mOnClickListener);
        tv_unit = (TextView) findViewById(R.id.tv_unit);
        et_single_input_price = (EditText) findViewById(R.id.et_single_input_price);
        tv_title_b = (TextView) findViewById(R.id.tv_title_b);
        tv_title_b.setOnClickListener(mOnClickListener);
        tv_stitle = (TextView) findViewById(R.id.tv_stitle);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);
        et7 = (EditText) findViewById(R.id.et7);
        et8 = (EditText) findViewById(R.id.et8);
        et9 = (EditText) findViewById(R.id.et9);
        et10 = (EditText) findViewById(R.id.et10);
        etList.add(et1);
        etList.add(et2);
        etList.add(et3);
        etList.add(et4);
        etList.add(et5);
        etList.add(et6);
        etList.add(et7);
        etList.add(et8);
        etList.add(et9);
        etList.add(et10);
        sigleView = findViewById(R.id.layout_single);
        initSigleView(sigleView);
        multiView = findViewById(R.id.layout_multi);
        initMultiView(multiView);
    }

    private void initMultiView(View multiView) {
        tv_mtitle = (TextView) multiView.findViewById(R.id.tv_title);
        tv_mremark = (TextView) multiView.findViewById(R.id.tv_mremark);
        mtv1 = (TextView) multiView.findViewById(R.id.tv1);
        mtv2 = (TextView) multiView.findViewById(R.id.tv2);
        mtv3 = (TextView) multiView.findViewById(R.id.tv3);
        mtv4 = (TextView) multiView.findViewById(R.id.tv4);
        mtv5 = (TextView) multiView.findViewById(R.id.tv5);
        mtv6 = (TextView) multiView.findViewById(R.id.tv6);
        mtv7 = (TextView) multiView.findViewById(R.id.tv7);
        mtv8 = (TextView) multiView.findViewById(R.id.tv8);
        mtv9 = (TextView) multiView.findViewById(R.id.tv9);
        mtv10 = (TextView) multiView.findViewById(R.id.tv10);
        multiList.add(mtv1);
        multiList.add(mtv2);
        multiList.add(mtv3);
        multiList.add(mtv4);
        multiList.add(mtv5);
        multiList.add(mtv6);
        multiList.add(mtv7);
        multiList.add(mtv8);
        multiList.add(mtv9);
        multiList.add(mtv10);
        for (TextView mtv : multiList) {
            mtv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.tv1:
                            if (!isb1) {
                                isb1 = true;
                                multiList.get(0).setSelected(true);
                            } else {
                                isb1 = false;
                                multiList.get(0).setSelected(false);
                            }

                            break;
                        case R.id.tv2:
                            if (!isb2) {
                                isb2 = true;
                                multiList.get(1).setSelected(true);
                            } else {
                                isb2 = false;
                                multiList.get(1).setSelected(false);
                            }
                            break;
                        case R.id.tv3:
                            if (!isb3) {
                                isb3 = true;
                                multiList.get(2).setSelected(true);
                            } else {
                                isb3 = false;
                                multiList.get(2).setSelected(false);
                            }
                            break;
                        case R.id.tv4:
                            if (!isb4) {
                                isb4 = true;
                                multiList.get(3).setSelected(true);
                            } else {
                                isb4 = false;
                                multiList.get(3).setSelected(false);
                            }
                            break;
                        case R.id.tv5:
                            if (!isb5) {
                                isb5 = true;
                                multiList.get(4).setSelected(true);
                            } else {
                                isb5 = false;
                                multiList.get(4).setSelected(false);
                            }
                            break;
                        case R.id.tv6:
                            if (!isb6) {
                                isb6 = true;
                                multiList.get(5).setSelected(true);
                            } else {
                                isb6 = false;
                                multiList.get(5).setSelected(false);
                            }
                            break;
                        case R.id.tv7:
                            if (!isb7) {
                                isb7 = true;
                                multiList.get(6).setSelected(true);
                            } else {
                                isb7 = false;
                                multiList.get(6).setSelected(false);
                            }
                            break;
                        case R.id.tv8:
                            if (!isb8) {
                                isb8 = true;
                                multiList.get(7).setSelected(true);
                            } else {
                                isb8 = false;
                                multiList.get(7).setSelected(false);
                            }
                            break;
                        case R.id.tv9:
                            if (!isb9) {
                                isb9 = true;
                                multiList.get(8).setSelected(true);
                            } else {
                                isb9 = false;
                                multiList.get(8).setSelected(false);
                            }
                            break;
                        case R.id.tv10:
                            if (!isb10) {
                                isb10 = true;
                                multiList.get(9).setSelected(true);
                            } else {
                                isb10 = false;
                                multiList.get(9).setSelected(false);
                            }
                            break;
                    }
                }
            });
        }
    }

    private void initSigleView(View sigleView) {
        tv_title = (TextView) sigleView.findViewById(R.id.tv_title);
        tv_remark = (TextView) sigleView.findViewById(R.id.tv_remark);
        stv1 = (TextView) sigleView.findViewById(R.id.tv1);
        stv2 = (TextView) sigleView.findViewById(R.id.tv2);
        stv3 = (TextView) sigleView.findViewById(R.id.tv3);
        stv4 = (TextView) sigleView.findViewById(R.id.tv4);
        stv5 = (TextView) sigleView.findViewById(R.id.tv5);
        stv6 = (TextView) sigleView.findViewById(R.id.tv6);
        stv7 = (TextView) sigleView.findViewById(R.id.tv7);
        stv8 = (TextView) sigleView.findViewById(R.id.tv8);
        stv9 = (TextView) sigleView.findViewById(R.id.tv9);
        stv10 = (TextView) sigleView.findViewById(R.id.tv10);
        sigleList.add(stv1);
        sigleList.add(stv2);
        sigleList.add(stv3);
        sigleList.add(stv4);
        sigleList.add(stv5);
        sigleList.add(stv6);
        sigleList.add(stv7);
        sigleList.add(stv8);
        sigleList.add(stv9);
        sigleList.add(stv10);
        et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (TextView tv : sigleList) {
                    tv.setSelected(false);
                }
                stv3.setSelected(true);
            }
        });
        et7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (TextView tv : sigleList) {
                    tv.setSelected(false);
                }
                stv7.setSelected(true);
            }
        });
        for (TextView tv : sigleList) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.tv1:
                            setSelected(0);
                            break;
                        case R.id.tv2:
                            setSelected(1);
                            break;
                        case R.id.tv3:
                            setSelected(2);
                            break;
                        case R.id.tv4:
                            setSelected(3);
                            break;
                        case R.id.tv5:
                            setSelected(4);
                            break;
                        case R.id.tv6:
                            setSelected(5);
                            break;
                        case R.id.tv7:
                            setSelected(6);
                            break;
                        case R.id.tv8:
                            setSelected(7);
                            break;
                        case R.id.tv9:
                            setSelected(8);
                            break;
                        case R.id.tv10:
                            setSelected(9);
                            break;
                    }
                }
            });
        }
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_multi_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.tv_title_a) {
                tag = false;
                tv_title_a.setSelected(true);
                tv_title_b.setSelected(false);
                et_single_input_price.setVisibility(View.VISIBLE);
            } else if (view.getId() == R.id.tv_title_b) {
                tag = true;
                tv_title_a.setSelected(false);
                et_single_input_price.setText("");
                et_single_input_price.setVisibility(View.GONE);
                tv_title_b.setSelected(true);
            } else if (view.getId() == R.id.btn_mutli_next) {
                ZYEntity zyEntity = new ZYEntity();
                zyEntity.setStep( mZYEntity.getStep());
                if (proTyp2) {
                    zyEntity.setType("1");
                    if (!AppGlobal.getInstance().getLagStr().equals("en")) {
                        zyEntity.setTitle("您意向的租金价格是多少？");
                    } else {
                        zyEntity.setTitle("What is your goal of monthly rental？");
                    }

                    zyEntity.setTittleId("65");
                    List<ZYEntity.DaBean> daList = new ArrayList<>();
                    ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                    Log.i("tagInformation", tag + "--" + et_single_input_price.getText().toString().trim());
                    if (!tag) {
                        daBean.setStr(et_single_input_price.getText().toString().trim() + getString(R.string.zf_jy_month));
//                        IMKitService.str = et_single_input_price.getText().toString().trim();
                        SPUtils.saveString(CommonMultiActivity1.this, "want_money", et_single_input_price.getText().toString().trim());

                        daBean.setStrId("66");
                        daList.add(daBean);
                    } else {
                        if (!AppGlobal.getInstance().getLagStr().equals("en")) {
                            daBean.setStr("我不确定");
                        } else {
                            daBean.setStr("I am not sure");
                        }
                        daBean.setStr("我不确定");
                        daBean.setStrId("67");
                        daList.add(daBean);
                    }
                    zyEntity.setDaList(daList);
                    map.put("2", zyEntity);
                } else {
                    zyEntity.setType(VipWDUtils.getBeans("step" + step).getType2());
                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                        zyEntity.setTitle(VipWDUtils.getBeans("step" + step).getYvalue());
                    } else {
                        zyEntity.setTitle(VipWDUtils.getBeans("step" + step).getValue());
                    }
                    zyEntity.setTittleId(VipWDUtils.getBeans("step" + step).getId());
                    List<ZYEntity.DaBean> daList = new ArrayList<>();
                    if (getSelectIdList() != null && getSelectIdList().size() > 0) {
                        for (int i = 0; i < getSelectIdList().size(); i++) {
                            ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                            daBean.setStrId(getSelectIdList().get(i));
                            daBean.setStr(getSelectList().get(i));
                            daList.add(daBean);
                        }
                    } else {
                        ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                        daBean.setStrId(getSelectItemId());

                        if(!mZYEntity.getDaList().get(0).getStrId().equals("39")&&
                                !zyEntity.getType().equals("3")&&
                                !type.equals("3")){
                            if (getSelectItem().equals(""))
                                return;
                        }

                        if(!mZYEntity.getDaList().get(0).getStrId().equals("44")&&
                                !zyEntity.getType().equals("3")&&
                                !type.equals("3")){
                            if (getSelectItem().equals(""))
                                return;
                        }

                        daBean.setStr(getSelectItem());
                        daList.add(daBean);
                    }
                    zyEntity.setDaList(daList);
                    if (isS) {
                        step = step - 2;
                    }
                    map.put(getIntent().getIntExtra("step", -1)+ "", zyEntity);
                    Log.e("result---->?>?>?",zyEntity.getStep()+"asd");
                }
                SerializableMap smMap = new SerializableMap();
                smMap.setMap(map);
                Bundle bundle = new Bundle();
                bundle.putSerializable("smap", smMap);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    };

    //-------------------单选-------------------
    public void setSelected(int i) {
        setAllSelectFalse();
        if (!sigleList.get(i).isSelected()) {
            sigleList.get(i).setSelected(true);
            if ("4".equals(problem.get(i).getType2())) {
                setShowView(i + 1, "");
            } else {
                setHideView();
            }
            return;
        }
    }

    public void setAllSelectFalse() {
        for (int i = 0; i < sigleList.size(); i++) {
            sigleList.get(i).setSelected(false);
        }
    }

    public String getSelectItem() {
        for (TextView tv : sigleList) {
            if (tv.isSelected()) {
                if (tv.getTag().toString().contains("[")) {
                    String content = tv.getText().toString();
                    String content1 = ((TextView) findViewById(R.id.tvY)).getText().toString().substring(0, ((TextView) findViewById(R.id.tvY)).getText().toString().length());
                    return content + "[" + et3.getText().toString() + "]" + content1;
                } else if (tv.getTag().toString().contains("#xia#")) {
                    if (et7.getText() == null || et7.getText().toString().equals("")) {
                        Toast.makeText(CommonMultiActivity1.this, getString(R.string.enter_num), Toast.LENGTH_SHORT).show();
                        return "";
                    }
                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                        return et7.getText().toString() + "sq ft";
                    } else {
                        return et7.getText().toString() + "平方英尺";
                    }
                } else {
                    return tv.getText().toString();
                }
            }
        }
        return "";
    }

    public String getSelectItemId() {
        int i = 0;
        for (TextView tv : sigleList) {
            if (tv.isSelected()) {

                if (TextUtils.isEmpty(etList.get(i).getText().toString().trim())) {
                    return problem.get(i).getId();
                } else {
                    return "#" + problem.get(i).getId() + "#" + etList.get(i).getText().toString().trim();
                }
            }
            ++i;
        }
        return "";
    }

    //-------------------多选--------------------------------
    public List<String> getSelectList() {
        List<String> list = new ArrayList<String>();
        for (TextView tv : multiList) {
            if (tv.isSelected()) {
                list.add(tv.getText().toString());
            }
        }
        return list;
    }

    public List<String> getSelectIdList() {
        List<String> list = new ArrayList<String>();
        int i = 0;
        for (TextView tv : multiList) {
            if (tv.isSelected()) {
                list.add(problem.get(i).getId());
            }
            ++i;
        }
        return list;
    }

    private void setHideView() {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
        et9.setText("");
        et10.setText("");
        et1.setVisibility(View.GONE);
        et2.setVisibility(View.GONE);
//        et3.setVisibility(View.GONE);
        et4.setVisibility(View.GONE);
        et5.setVisibility(View.GONE);
        et6.setVisibility(View.GONE);
//        et7.setVisibility(View.GONE);
        et8.setVisibility(View.GONE);
        et9.setVisibility(View.GONE);
        et10.setVisibility(View.GONE);
    }

    private void setShowView(int i, String str) {
        if (i == 1) {
            et1.setVisibility(View.VISIBLE);
            et1.setText(str);
        } else if (i == 2) {
            et2.setVisibility(View.VISIBLE);
            et2.setText(str);
        } else if (i == 3) {
            et3.setVisibility(View.VISIBLE);
            et3.setText(str);
        } else if (i == 4) {
            et4.setVisibility(View.VISIBLE);
            et4.setText(str);
        } else if (i == 5) {
            et5.setVisibility(View.VISIBLE);
            et5.setText(str);
        } else if (i == 6) {
            et6.setVisibility(View.VISIBLE);
            et6.setText(str);
        } else if (i == 7) {
            et7.setVisibility(View.VISIBLE);
            et7.setText(str);
        } else if (i == 8) {
            et8.setVisibility(View.VISIBLE);
            et8.setText(str);
        } else if (i == 9) {
            et9.setVisibility(View.VISIBLE);
            et9.setText(str);
        } else if (i == 10) {
            et10.setVisibility(View.VISIBLE);
            et10.setText(str);
        }
    }

}
