package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.HousAddEntity;
import com.haiwai.housekeeper.entity.HousDataEntity;
import com.haiwai.housekeeper.entity.NewHousEntity;
import com.haiwai.housekeeper.entity.OrderConfigEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.ZYEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.CityUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.ParseVipJsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SerializableMap;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.VipWDUtils;
import com.haiwai.housekeeper.view.ConfigView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VipNewHouseChooseActivity extends AppCompatActivity {
    private TopViewNormalBar top_house_bar;
    private Spinner mSpinner;
    private TextView tv_add_new_house;
    private Button btn_vip_next;
    private String type = "0";
    Map<String, String> map = null;
    private NewHousEntity mZyHousEntity;
    private CityEntity mCityEntity;
    private HousDataEntity mHousDataEntity;
    MyAdapter<HousDataEntity.DataBean> myAdapter;
    private String h_id;
    private String city;
    private String addrInfo;
    private int is_support;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_new_house_choose);
        top_house_bar = (TopViewNormalBar) findViewById(R.id.top_house_bar);
        top_house_bar.setTitle(getString(R.string.xzfc));
        top_house_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initData();
        initHousData();
        initView();
    }

    private void initHousData() {
        LoadDialog.showProgressDialog(VipNewHouseChooseActivity.this);
        User user = AppGlobal.getInstance().getUser();
        map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("secret_key", SPUtils.getString(VipNewHouseChooseActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(VipNewHouseChooseActivity.this, Contants.hous_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println(">>>>>>>>>>房产列表>>" + response);
                if (code == 200) {
                    mHousDataEntity = new Gson().fromJson(response, HousDataEntity.class);
                    if(mHousDataEntity.getData()!=null&&mHousDataEntity.getData().size()!=0){
                        bindDataListView(mHousDataEntity);
                    }
                    initCityData();
                } else {
                    LoadDialog.closeProgressDialog();
//                    ToastUtil.longToast(VipNewHouseChooseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void bindDataListView(HousDataEntity mNewHousEntity) {
        h_id = mHousDataEntity.getData().get(0).getId();
        myAdapter = new MyAdapter<HousDataEntity.DataBean>((ArrayList<HousDataEntity.DataBean>) mHousDataEntity.getData(), R.layout.hous_adapter_text_item) {
            @Override
            public void bindView(ViewHolder holder, HousDataEntity.DataBean obj) {
                holder.setText(R.id.tv_content, obj.getAddress_info());
            }
        };
        mSpinner.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void initCityData() {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(VipNewHouseChooseActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(VipNewHouseChooseActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println(">>>>>>>>>>城市列表>>" + response);
                if (code == 200) {
//                    mCityEntity = new Gson().fromJson(response, CityEntity.class);
                    mCityEntity = CityUtils.parseCity(response);
                    LoadDialog.closeProgressDialog();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(VipNewHouseChooseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void initData() {
        IMKitService.addrMap.clear();
        IMKitService.map.clear();
        IMKitService.jMap.clear();
        IMKitService.yMap.clear();
        IMKitService.zMap.clear();
        IMKitService.kMap.clear();
        IMKitService.sjMap.clear();
        IMKitService.syMap.clear();
        IMKitService.szMap.clear();
        IMKitService.skMap.clear();
        type = getIntent().getStringExtra("type");
        IMKitService.proTypeMap.put("type", type);
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_house_desc)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                h_id = mHousDataEntity.getData().get(i).getId();
                addrInfo = mHousDataEntity.getData().get(i).getAddress_info();
                city = mHousDataEntity.getData().get(i).getCity();
                is_support = mHousDataEntity.getData().get(i).getIs_support();
//                ToastUtil.longToast(VipNewHouseChooseActivity.this, h_id + ">>>");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tv_add_new_house = (TextView) findViewById(R.id.tv_add_new_house);
        tv_add_new_house.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_add_new_house.setOnClickListener(mOnClickListener);
        btn_vip_next = (Button) findViewById(R.id.btn_vip_next);
        btn_vip_next.setOnClickListener(mOnClickListener);
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_house_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.tv_add_new_house) {//添加新房产
                if(getIntent().getBooleanExtra("isNew",false)){
                    Intent intent = new Intent(VipNewHouseChooseActivity.this, NewHousActivity.class);
                    intent.putExtra("isNew", true);
                    intent.putExtra("isConfig", true);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(VipNewHouseChooseActivity.this, NewHousActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mCityEntity", mCityEntity);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            } else if (view.getId() == R.id.btn_vip_next) {//选择房产
                if(getIntent().getBooleanExtra("isConfig",false)){
                    HousAddEntity.DataBean data = new HousAddEntity.DataBean();
                    data.setId(h_id);
                    data.setAddress_info(addrInfo);
                    data.setCity(Integer.valueOf(city));
                    Intent intent = new Intent(VipNewHouseChooseActivity.this, EnvelopeActivity.class);
                    intent.putExtra("isConfig", true);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", data);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else{
                  //  if (is_support == 1) {
                        httpTiaozhuan();
                     //   return;
//                    } else {
//
//                    }
                }

            }
        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initHousData();
    }
    User user;
    private OrderConfigEntity mOrderConfigEntity;
    private SerializableMap mkSerializableap;//空屋管理5
    private SerializableMap mySerializableap;//庭院管理3
    private SerializableMap mjSerializableap;//家政服务2
    private SerializableMap mzSerializableap;//租赁管理4
    Map<String, ZYEntity> kmap;
    Map<String, ZYEntity> ymap;
    Map<String, ZYEntity> jmap;
    Map<String, ZYEntity> zmap;
    private void httpTiaozhuan(){
        LoadDialog.showProgressDialog(this);
        user = AppGlobal.getInstance().getUser();
        Map<String, String> mawp = new HashMap<>();
        mawp.put("uid", user.getUid());
        mawp.put("secret_key", SPUtils.getString(VipNewHouseChooseActivity.this, "secret", ""));
        mawp.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(VipNewHouseChooseActivity.this, Contants.self_dingzhi, mawp, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.print(">>>>>>>>>>>>>>>>>>" + response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    mOrderConfigEntity = new Gson().fromJson(response, OrderConfigEntity.class);
                    bindDataView(mOrderConfigEntity);
                } else {
                    ToastUtil.longToast(VipNewHouseChooseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }
    private void bindDataView(final OrderConfigEntity orderConfigEntity) {
       // type//3 庭院 4，租赁 5，信件
      //  k_static=0,//没有信件 y_statci=0//没有庭院 z_statci=0//没有租赁

        final List<OrderConfigEntity.DataBean.DateBean> date = orderConfigEntity.getData().getDate();
        int finalI = -1;

        for(int h=0;h<orderConfigEntity.getData().getDate().size();h++){
            Log.e("result-->",orderConfigEntity.getData().getDate().get(h).getAddress_info()+"---"+addrInfo);
                if(addrInfo.equals(orderConfigEntity.getData().getDate().get(h).getAddress_info())){
                   // if(type.equals("3")&&!orderConfigEntity.getData().getDate().get(h).getY_static().equals("0")){
                        finalI=h;
                //    }else if(type.equals("4")&&!orderConfigEntity.getData().getDate().get(h).getZ_static().equals("0")){
                   //     finalI=h;
                 //   }else if(type.equals("5")&&!orderConfigEntity.getData().getDate().get(h).getK_static().equals("0")){
                    //    finalI=h;
                 //   }
                }
            }
        if(finalI==-1){
            IMKitService.map.put("h_id", h_id);
            IMKitService.map.put("city", city);
            IMKitService.addrMap.put("addrInfo", addrInfo);
            Intent intent = new Intent(VipNewHouseChooseActivity.this, CommonProActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
            return;
        }

                    AppGlobal.getInstance().setIsFirst(false);
                    OrderConfigEntity.DataBean.DateBean dateBean = date.get(finalI);
                    String id = dateBean.getId();
                    //家政
                    mjSerializableap = new SerializableMap();
                    jmap = new LinkedHashMap<>();
                    int count1 = dateBean.getJ_proble().size();
                    int a = 0;
                    for (int i = 0; i < count1; i++) {
                        ZYEntity zyEntity = new ZYEntity();
                        zyEntity.setType(dateBean.getJ_proble().get(i).getType2());
                        zyEntity.setTitle(dateBean.getJ_proble().get(i).getValue());
                        zyEntity.setTittleId(dateBean.getJ_proble().get(i).getId());
                        zyEntity.setStep(i);
                        List<ZYEntity.DaBean> daList = new ArrayList<>();
                        ZYEntity.DaBean dataBean = new ZYEntity.DaBean();
                        if (dateBean.getJ_proble().get(i).getProblem() != null && dateBean.getJ_proble().get(i).getProblem().size() > 0) {
                            int num = dateBean.getJ_proble().get(i).getProblem().size();
                            if (num == 1) {
                                dataBean.setStrId(dateBean.getJ_proble().get(i).getProblem().get(0).getId());
                                dataBean.setStr(dateBean.getJ_proble().get(i).getProblem().get(0).getValue());
                            } else {
                                StringBuilder sb1 = new StringBuilder();
                                StringBuilder sb2 = new StringBuilder();
                                for (int k = 0; k < num; k++) {
                                    sb1 = sb1.append(dateBean.getJ_proble().get(i).getProblem().get(k).getId()).append(",");
                                    sb2 = sb2.append(dateBean.getJ_proble().get(i).getProblem().get(k).getValue()).append(",");
                                }
                                if (sb1.toString().endsWith(",")) {
                                    sb1.deleteCharAt(sb1.lastIndexOf(","));
                                }
                                if (sb2.toString().endsWith(",")) {
                                    sb2.deleteCharAt(sb2.lastIndexOf(","));
                                }
                                dataBean.setStrId(sb1.toString());
                                dataBean.setStr(sb2.toString());
                            }

                        }

                        daList.add(dataBean);
                        zyEntity.setDaList(daList);
                        a = i + 1;
                        jmap.put("step" + a, zyEntity);
                    }
                    mjSerializableap.setMap(jmap);
                    //庭院
                    mySerializableap = new SerializableMap();
                    ymap = new LinkedHashMap<>();
                    int count2 = dateBean.getY_proble().size();
                    int b = 0;
                    for (int i = 0; i < count2; i++) {
                        ZYEntity zyEntity = new ZYEntity();
                        zyEntity.setType(dateBean.getY_proble().get(i).getType2());
                        if (AppGlobal.getInstance().getLagStr().equals("en")) {
                            zyEntity.setTitle(dateBean.getY_proble().get(i).getYvalue());
                        } else {
                            zyEntity.setTitle(dateBean.getY_proble().get(i).getValue());
                        }
                        zyEntity.setTittleId(dateBean.getY_proble().get(i).getId());
                        List<ZYEntity.DaBean> daList = new ArrayList<>();
                        ZYEntity.DaBean dataBean = new ZYEntity.DaBean();
                        if (dateBean.getY_proble().get(i).getProblem() != null && dateBean.getY_proble().get(i).getProblem().size() > 0) {
                            int num = dateBean.getY_proble().get(i).getProblem().size();
                            if (num == 1) {
                                String strId = dateBean.getY_proble().get(i).getProblem().get(0).getId();

                                String str = "";
                                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                    str = dateBean.getY_proble().get(i).getProblem().get(0).getYvalue();
                                } else {
                                    str = dateBean.getY_proble().get(i).getProblem().get(0).getValue();
                                }
                                if(str!=null) {
                                    Log.i("strInformation-----", str);

                                    if (str.contains("#xia#")) {
                                        if (AppGlobal.getInstance().getLagStr().equals("zh")) {
                                            str = dateBean.getY_proble().get(i).getProblem().get(0).getZhi() + "平方英尺";
                                        } else {
                                            str = dateBean.getY_proble().get(i).getProblem().get(0).getZhi() + "sq ft";
                                        }
                                        strId = "#" + strId + "#" + dateBean.getY_proble().get(i).getProblem().get(0).getZhi();
                                    } else if (str.contains("[]")) {
                                        String str1 = str.substring(0, str.indexOf("[") + 1);
                                        String str2 = str.substring(str.indexOf("]"), str.length());
                                        String finalStr = dateBean.getY_proble().get(i).getProblem().get(0).getZhi();
                                        str = str1 + finalStr + str2;

                                        strId = "#" + strId + "#" + finalStr;

                                    }
                                    dataBean.setStrId(strId);
                                    dataBean.setStr(str);
                                }
//                                else if(){
//
//                                }
                            } else {
                                StringBuilder sb1 = new StringBuilder();
                                StringBuilder sb2 = new StringBuilder();
                                for (int k = 0; k < num; k++) {
                                    sb1 = sb1.append(dateBean.getY_proble().get(i).getProblem().get(k).getId()).append(",");
                                    String cpValue = "";
                                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                        cpValue = dateBean.getY_proble().get(i).getProblem().get(k).getYvalue();
                                    } else {
                                        cpValue = dateBean.getY_proble().get(i).getProblem().get(k).getValue();
                                    }
                                    if (cpValue != null) {
                                        Log.i("cpvalueInfomation", cpValue);
                                        if (cpValue.contains("#xia#")) {
                                            if (AppGlobal.getInstance().getLagStr().equals("zh")) {
                                                sb2 = sb2.append(dateBean.getY_proble().get(i).getProblem().get(k).getZhi() + "平方英尺").append(",");
                                            } else {
                                                sb2 = sb2.append(dateBean.getY_proble().get(i).getProblem().get(k).getZhi() + "sq ft").append(",");
                                            }
                                        } else if (cpValue.contains("[]")) {
                                            String str1 = cpValue.substring(0, cpValue.indexOf("[") + 1);
                                            String str2 = cpValue.substring(cpValue.indexOf("]"), cpValue.length());
                                            String finalStr = dateBean.getY_proble().get(i).getProblem().get(k).getZhi();
                                            sb2 = sb2.append(str1 + finalStr + str2).append(",");
                                        } else {
                                            sb2 = sb2.append(cpValue).append(",");
                                        }

                                        if (sb1.toString().endsWith(",")) {
                                            sb1.deleteCharAt(sb1.lastIndexOf(","));
                                        }
                                        if (sb2.toString().endsWith(",")) {
                                            sb2.deleteCharAt(sb2.lastIndexOf(","));
                                        }
                                        dataBean.setStrId(sb1.toString());
                                        dataBean.setStr(sb2.toString());
                                    }
                                }

                            }

                        }
//                        dataBean.setStrId(dateBean.getY_proble().get(i).getProblem().get(0).getId());
//                        dataBean.setStr(dateBean.getY_proble().get(i).getProblem().get(0).getValue());
                        daList.add(dataBean);
                        zyEntity.setDaList(daList);
                        b = i + 1;
                        ymap.put("step" + b, zyEntity);
                    }
                    mySerializableap.setMap(ymap);
                    //租赁
                    mzSerializableap = new SerializableMap();
                    zmap = new LinkedHashMap<>();
                    int count3 = dateBean.getZ_proble().size();
                    String str = dateBean.getZ_wen3();//"#66#123"
                    if (str.startsWith("#")) {
                        str = str.substring(4, str.length());
                        IMKitService.str = str;
                    }
                    int c = 0;
                    for (int i = 0; i < count3; i++) {
                        ZYEntity zyEntity = new ZYEntity();
                        zyEntity.setType(dateBean.getZ_proble().get(i).getType2());
                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                            zyEntity.setTitle(dateBean.getZ_proble().get(i).getYvalue());
                        }else{
                            zyEntity.setTitle(dateBean.getZ_proble().get(i).getValue());
                        }

                        zyEntity.setTittleId(dateBean.getZ_proble().get(i).getId());
                        List<ZYEntity.DaBean> daList = new ArrayList<>();
                        ZYEntity.DaBean dataBean = new ZYEntity.DaBean();
                        if (dateBean.getZ_proble().get(i).getProblem() != null && dateBean.getZ_proble().get(i).getProblem().size() > 0) {
                            dataBean.setStrId(dateBean.getZ_proble().get(i).getProblem().get(0).getId());
                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                if ("2".equals(dateBean.getZ_proble().get(i).getProblem().get(0).getType2())) {
                                    dataBean.setStr(str + dateBean.getZ_proble().get(i).getProblem().get(0).getYvalue());
                                } else {
                                    dataBean.setStr(dateBean.getZ_proble().get(i).getProblem().get(0).getYvalue());
                                }
                            }else{
                                if ("2".equals(dateBean.getZ_proble().get(i).getProblem().get(0).getType2())) {
                                    dataBean.setStr(str + dateBean.getZ_proble().get(i).getProblem().get(0).getValue());
                                } else {
                                    dataBean.setStr(dateBean.getZ_proble().get(i).getProblem().get(0).getValue());
                                }
                            }

                        }
                        daList.add(dataBean);
                        zyEntity.setDaList(daList);
                        c = i + 1;
                        zmap.put("step" + c, zyEntity);
                    }
                    mzSerializableap.setMap(zmap);
                    //空屋
                    mkSerializableap = new SerializableMap();
                    kmap = new LinkedHashMap<>();
                    int count4 = dateBean.getK_proble().size();
                    int d = 0;
                    for (int i = 0; i < count4; i++) {
                        ZYEntity zyEntity = new ZYEntity();
                        zyEntity.setType(dateBean.getK_proble().get(i).getType2());
                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                            zyEntity.setTitle(dateBean.getK_proble().get(i).getYvalue());
                        }else{
                            zyEntity.setTitle(dateBean.getK_proble().get(i).getValue());
                        }

                        zyEntity.setTittleId(dateBean.getK_proble().get(i).getId());
                        List<ZYEntity.DaBean> daList = new ArrayList<>();
                        ZYEntity.DaBean dataBean = new ZYEntity.DaBean();
                        if (dateBean.getK_proble().get(i).getProblem() != null && dateBean.getK_proble().get(i).getProblem().size() > 0) {
                            int num = dateBean.getK_proble().get(i).getProblem().size();
                            if (num == 1) {
                                dataBean.setStrId(dateBean.getK_proble().get(i).getProblem().get(0).getId());
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    dataBean.setStr(dateBean.getK_proble().get(i).getProblem().get(0).getYvalue());
                                }else{
                                    dataBean.setStr(dateBean.getK_proble().get(i).getProblem().get(0).getValue());
                                }
                            } else {
                                StringBuilder sb1 = new StringBuilder();
                                StringBuilder sb2 = new StringBuilder();
                                for (int k = 0; k < num; k++) {
                                    sb1 = sb1.append(dateBean.getK_proble().get(i).getProblem().get(k).getId()).append(",");
                                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                                        sb2 = sb2.append(dateBean.getK_proble().get(i).getProblem().get(k).getYvalue()).append(",");
                                    }else{
                                        sb2 = sb2.append(dateBean.getK_proble().get(i).getProblem().get(k).getValue()).append(",");
                                    }

                                }
                                if (sb1.toString().endsWith(",")) {
                                    sb1.deleteCharAt(sb1.lastIndexOf(","));
                                }
                                if (sb2.toString().endsWith(",")) {
                                    sb2.deleteCharAt(sb2.lastIndexOf(","));
                                }
                                dataBean.setStrId(sb1.toString());
                                dataBean.setStr(sb2.toString());
                            }

                        }
//                        dataBean.setStrId(dateBean.getK_proble().get(i).getProblem().get(0).getId());
//                        dataBean.setStr(dateBean.getK_proble().get(i).getProblem().get(0).getValue());
                        daList.add(dataBean);
                        zyEntity.setDaList(daList);
                        d = i + 1;
                        kmap.put("step" + d, zyEntity);
                    }
                    mkSerializableap.setMap(kmap);
                    IMKitService.isConfig = true;
                    Intent intent = new Intent(VipNewHouseChooseActivity.this, EnvelopeActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("fromEnvelope",true);
                    bundle.putSerializable("mjSerializableap", mjSerializableap);
                    bundle.putSerializable("mySerializableap", mySerializableap);
                    bundle.putSerializable("mzSerializableap", mzSerializableap);
                    bundle.putSerializable("mkSerializableap", mkSerializableap);
                    intent.putExtra("address_info", mOrderConfigEntity.getData().getDate().get(finalI).getAddress_info());
                    intent.putExtra("isOder", true);

                    intent.putExtra("k_xia", dateBean.getK_xia());
                    intent.putExtra("j_xia", dateBean.getJ_xia());
                    intent.putExtra("z_xia", dateBean.getZ_xia());
                    intent.putExtra("y_xia", dateBean.getY_xia());


//                    intent.putExtra("k_static",dateBean.getK_static());
//                    intent.putExtra("j_static",dateBean.getJ_static());
//                    intent.putExtra("z_static",dateBean.getK_static());
//                    intent.putExtra("y_static",dateBean.getY_static());


                    intent.putExtra("jstatic", dateBean.getJ_static()+"");
                    intent.putExtra("ystatic", dateBean.getY_static()+"");
                    intent.putExtra("zstatic", dateBean.getZ_static()+"");
                    intent.putExtra("kstatic", dateBean.getK_static()+"");

                    Log.i("K_static",dateBean.getK_static()+"");
                    intent.putExtras(bundle);
                    intent.putExtra("id", id);
                    intent.putExtra("h_id", mOrderConfigEntity.getData().getDate().get(finalI).getH_id());
                    intent.putExtra("city", mOrderConfigEntity.getData().getDate().get(finalI).getCity());
                    startActivity(intent);
                }


}
