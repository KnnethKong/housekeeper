package com.haiwai.housekeeper.activity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.DingEntity;
import com.haiwai.housekeeper.entity.EvnEntity;
import com.haiwai.housekeeper.entity.HousAddEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.ZYEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SerializableMap;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.JzView;
import com.haiwai.housekeeper.view.KwView;
import com.haiwai.housekeeper.view.OutlineMultiView;
import com.haiwai.housekeeper.view.OutlineSimpleView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.TyView;
import com.haiwai.housekeeper.view.ZlView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * 信封页
 */
public class EnvelopeActivity extends AppCompatActivity {
    private TopViewNormalBar top_envelope_bar;
    private TextView btn_envelop_confirm;
    private LinearLayout vip_envelope_ll_addr;
    private TextView vip_envelope_tv_addr;
    private TextView tv_xfze;
    private LinearLayout ll_kw_layout, ll_yy_layout, ll_fw_layout, ll_jz_layout;
    private KwView mKwView;
    private TyView mTyView;
    private JzView mJzView;
    private ZlView mZlView;
    private LinearLayout ll_content_env_layout;
    private String type = "";
    private SerializableMap mkSerializableap;//空屋管理5
    private SerializableMap mySerializableap;//庭院管理3
    private SerializableMap mjSerializableap;//家政服务2
    private SerializableMap mzSerializableap;//租赁管理4
    private String uid = "";
    private String h_id = "";
    private String city = "";
    private String k_static = "";//房屋巡视0不选2选择
    private String k_wen1 = ""; //对应答案id(若多选则1,2,3,4)
    private String k_wen2 = "";
    private String k_wen3 = "";
    private String k_wen4 = "";
    private String y_static = "";//庭院管理0/2
    private String y_wen1 = "";
    private String y_wen2 = "";
    private String y_wen3 = "";
    private String y_wen4 = "";
    private String y_wen5 = "";
    private String y_wen6 = "";
    private String j_static = "";//家政管理0/2
    private String j_wen1 = "";
    private String j_wen2 = "";
    private String j_wen3 = "";
    private String z_static = "";//租赁管理0/2
    private String z_wen1 = "";
    private String z_wen2 = "";
    private String z_wen3 = "";
    private String z_wen4 = "";
    private String z_wen5 = "";
    private String address_info = "";
    private Map<String, String> tmpMap = new LinkedHashMap<>();
    boolean isOder = false;
    private String jstatic = "0";
    private String ystatic = "0";
    private String zstatic = "0";
    private String kstatic = "0";
    User user;
    private EvnEntity mEvnEntity;
    private String isZhorEn = "";
    boolean isConfig = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envelope);
        top_envelope_bar = (TopViewNormalBar) findViewById(R.id.top_envelope_bar);
        top_envelope_bar.setTitle(getString(R.string.dz_next_month));
        top_envelope_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initEnveData(){
        Map dingMap = new HashMap();
        if(getIntent().getStringExtra("id")!=null) {
            dingMap.put("id", getIntent().getStringExtra("id"));
            dingMap.put("uid", AppGlobal.getInstance().getUser().getUid());
            dingMap.put("secret_key", SPUtils.getString(EnvelopeActivity.this, "secret", ""));
            dingMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(EnvelopeActivity.this, Contants.ding_support_pirce, dingMap, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("result-->", response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        DingEntity entity = new Gson().fromJson(response, DingEntity.class);
                        bindData(entity);
                    } else {

                    }
                }
            }));
        }
    }

    private void bindData(DingEntity entity){
        if(entity!=null&&entity.getData()!=null&&entity.getData().getDate()!=null&&entity.getData().getDate().size()>0){
            if(!getIntent().getBooleanExtra("fromEnvelope",false)){
                ystatic="";
                zstatic = "";
                kstatic ="";
            }
            Log.e("bbbb--------->",ystatic+"--"+zstatic+"---"+kstatic);
            mJzView.setMPText(entity.getData().getDate().get(0).getJ_month()+"", entity.getData().getDate().get(0).getJ_money()+""
                    , SPUtils.getString(EnvelopeActivity.this,"j_xia","-1"), jstatic);
            mTyView.setMPText(entity.getData().getDate().get(0).getY_month()+"", entity.getData().getDate().get(0).getY_money()+"", SPUtils.getString(EnvelopeActivity.this,"y_xia","-1"), ystatic);
            mKwView.setMPText(entity.getData().getDate().get(0).getK_month()+"", entity.getData().getDate().get(0).getK_money()+"", SPUtils.getString(EnvelopeActivity.this,"k_xia","-1"), kstatic);
            mZlView.setMPText(entity.getData().getDate().get(0).getZ_month()+"", "", SPUtils.getString(EnvelopeActivity.this,"z_xia","-1"), zstatic);
            float countMoney = Float.valueOf(entity.getData().getDate().get(0).getJ_money()+"") + Float.valueOf(entity.getData().getDate().get(0).getY_money()+"") + Float.valueOf(entity.getData().getDate().get(0).getK_money()+"");
            tv_xfze.setText("$" + countMoney);
        }
    }

    private void initData() {

        user = AppGlobal.getInstance().getUser();
        IMKitService.map.put("uid", user.getUid() == null ? "" : user.getUid());
        IMKitService.map.put("secret_key", SPUtils.getString(EnvelopeActivity.this, "secret", ""));
        IMKitService.map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        isOder = getIntent().getBooleanExtra("isOder", false);
        type = getIntent().getStringExtra("type");
        isConfig = getIntent().getBooleanExtra("isConfig", false);
        kstatic=  SPUtils.getString(EnvelopeActivity.this, "kstatic_my", "0");
        zstatic=   SPUtils.getString(EnvelopeActivity.this, "zstatic_my", "0");
        ystatic=  SPUtils.getString(EnvelopeActivity.this, "ystatic_my", "0");
        initEnveData();
        if(getIntent().getBooleanExtra("fromEnvelope",false)){

                kstatic = getIntent().getStringExtra("kstatic");
                zstatic = getIntent().getStringExtra("zstatic");
                ystatic = getIntent().getStringExtra("ystatic");
                Log.e("cccc1--------->",ystatic+"--"+zstatic+"---"+kstatic);

        }
        // Log.e("result-------->",isOder+"");
        Log.e("cccc2--------->",ystatic+"--"+zstatic+"---"+kstatic);
        if (isOder) {//定制--》
            String id = getIntent().getStringExtra("id");
            String h_id = getIntent().getStringExtra("h_id");
            String city = getIntent().getStringExtra("city");
            IMKitService.map.put("id", id);
            IMKitService.map.put("h_id", h_id);
            IMKitService.map.put("city", city);
            jstatic = getIntent().getStringExtra("jstatic");
            ystatic = getIntent().getStringExtra("ystatic");
            zstatic = getIntent().getStringExtra("zstatic");
            kstatic = getIntent().getStringExtra("kstatic");
            Log.e("cccc3--------->",ystatic+"--"+zstatic+"---"+kstatic);
            address_info = getIntent().getStringExtra("address_info");
            IMKitService.addrMap.put("addrInfo", address_info);
            vip_envelope_tv_addr.setText(address_info);
            mjSerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mjSerializableap");
            if (!"0".equals(jstatic)) {
                IMKitService.sjMap.put("sj", mjSerializableap);
            } else {
                IMKitService.sjMap.clear();
            }
            if (mjSerializableap != null) {
                Map<String, ZYEntity> mjMap = mjSerializableap.getMap();
                if (mjMap != null && !"0".equals(jstatic)) {
                    IMKitService.jMap.put("j_static", jstatic);
                    int k = 0;
                    for (Map.Entry<String, ZYEntity> mj : mjMap.entrySet()) {
                        ++k;
                        String strJ = "j_wen" + k;
                        ZYEntity zYEntity = (ZYEntity) mj.getValue();
                        String type2 = zYEntity.getType();
                        if ("1".equals(type2)) {
                            String strValue = zYEntity.getDaList().get(0).getStrId();
                            IMKitService.jMap.put(strJ, strValue);
                        } else if ("3".equals(type2)) {
                            if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                                    sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append("");
                                }
                                if (sb.toString().endsWith(",")) {
                                    sb.deleteCharAt(sb.lastIndexOf(","));
                                }
                                IMKitService.jMap.put(strJ, sb.toString());
                            }
                        }
                    }
                } else {
                    IMKitService.jMap.put("j_static", "0");
                    IMKitService.jMap.put("j_wen1", "");
                    IMKitService.jMap.put("j_wen2", "");
                    IMKitService.jMap.put("j_wen3", "");
                    IMKitService.jMap.put("j_wen4", "");
                }
            }
            mySerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mySerializableap");
            if (!"0".equals(ystatic)) {
                IMKitService.syMap.put("sy", mySerializableap);
            } else {
                IMKitService.syMap.clear();
            }

            if (mySerializableap != null) {
                Map<String, ZYEntity> myMap = mySerializableap.getMap();
                if (myMap != null && !"0".equals(y_static)) {
                    IMKitService.yMap.put("y_static", ystatic);
                    int k = 0;
                    for (Map.Entry<String, ZYEntity> my : myMap.entrySet()) {
                        ++k;

                        ZYEntity zYEntity = (ZYEntity) my.getValue();
                        String strY = "y_wen" + zYEntity.getStep();
                        String type2 = zYEntity.getType();
                        if ("1".equals(type2)) {
                            String strValue = zYEntity.getDaList().get(0).getStrId();
                            IMKitService.yMap.put(strY, strValue);
                        } else if ("3".equals(type2)) {
                            if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                                    sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(",");
                                }
                                if (sb.toString().endsWith(",")) {
                                    sb.deleteCharAt(sb.lastIndexOf(","));
                                }
                                IMKitService.yMap.put(strY, sb.toString());
                            }
                        }
                    }
                } else {
                    IMKitService.yMap.put("y_static", "0");
                    IMKitService.yMap.put("y_wen1", "");
                    IMKitService.yMap.put("y_wen2", "");
                    IMKitService.yMap.put("y_wen3", "");
                    IMKitService.yMap.put("y_wen4", "");
                    IMKitService.yMap.put("y_wen5", "");
                    IMKitService.yMap.put("y_wen6", "");
                }
            }

            mzSerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mzSerializableap");
            if (!"0".equals(zstatic)) {
                IMKitService.szMap.put("sz", mzSerializableap);
            } else {
                IMKitService.szMap.clear();
            }

            if (mzSerializableap != null) {
                Map<String, ZYEntity> mzMap = mzSerializableap.getMap();
                if (mzMap != null && !"0".equals(zstatic)) {
                    IMKitService.zMap.put("z_static", zstatic);
                    int k = 0;
                    for (Map.Entry<String, ZYEntity> mz : mzMap.entrySet()) {
                        ++k;
                        String strZ = "z_wen" + k;
                        ZYEntity zYEntity = (ZYEntity) mz.getValue();
                        String type2 = zYEntity.getType();
                        if ("1".equals(type2)) {
                            String strValue = zYEntity.getDaList().get(0).getStrId();
                            String str = zYEntity.getDaList().get(0).getStr();
                            if (str != null) {
                                Log.i("CADMONTH",str);
                                if (str.contains("CAD$/Month")||str.contains("加元/每月")||str.contains("加元/月")) {
                                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                                        str = "#" + strValue + "#" + str.substring(0, str.length() - 10);
                                    }else{
                                        str = "#" + strValue + "#" + str.substring(0, str.indexOf("加"));
                                    }

                                    IMKitService.zMap.put(strZ, str);
                                } else {
                                    IMKitService.zMap.put(strZ, strValue);
                                }
                            }
                        } else if ("3".equals(type2)) {
                            if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                                    sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(",");
                                }
                                if (sb.toString().endsWith(",")) {
                                    sb.deleteCharAt(sb.lastIndexOf(","));
                                }
                                IMKitService.zMap.put(strZ, sb.toString());
                            }
                        }
                    }
                } else {
                    IMKitService.zMap.put("z_static", "0");
                    IMKitService.zMap.put("z_wen1", "");
                    IMKitService.zMap.put("z_wen2", "");
                    IMKitService.zMap.put("z_wen3", "");
                    IMKitService.zMap.put("z_wen4", "");
                    IMKitService.zMap.put("z_wen5", "");
                }
            }
            mkSerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mkSerializableap");
            if (!"0".equals(kstatic)) {
                IMKitService.skMap.put("sk", mkSerializableap);
            } else {
                IMKitService.skMap.clear();
            }

            if (mkSerializableap != null) {
                Map<String, ZYEntity> mkMap = mkSerializableap.getMap();
                if (mkMap != null && !"0".equals(kstatic)) {
                    IMKitService.kMap.put("k_static", kstatic);
                    int k = 0;
                    for (Map.Entry<String, ZYEntity> mk : mkMap.entrySet()) {
                        ++k;
                        String strK = "k_wen" + k;
                        ZYEntity zYEntity = (ZYEntity) mk.getValue();
                        String type2 = zYEntity.getType();
                        if ("1".equals(type2)) {
                            String strValue = zYEntity.getDaList().get(0).getStrId();
                            IMKitService.kMap.put(strK, strValue);
                        } else if ("3".equals(type2)) {
                            if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                                    sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(",");
                                }
                                if (sb.toString().endsWith(",")) {
                                    sb.deleteCharAt(sb.lastIndexOf(","));
                                }
                                IMKitService.kMap.put(strK, sb.toString());
                            }
                        }
                    }
                } else {
                    IMKitService.kMap.put("k_static", "0");
                    IMKitService.kMap.put("k_wen1", "");
                    IMKitService.kMap.put("k_wen2", "");
                    IMKitService.kMap.put("k_wen3", "");
                    IMKitService.kMap.put("k_wen4", "");
                }
            }
            tmpMap.put("j_static", IMKitService.jMap.get("j_static") != null ? IMKitService.jMap.get("j_static") : "0");
            tmpMap.put("j_wen1", IMKitService.jMap.get("j_wen1") != null ? IMKitService.jMap.get("j_wen1") : "");
            tmpMap.put("j_wen2", IMKitService.jMap.get("j_wen2") != null ? IMKitService.jMap.get("j_wen2") : "");
            tmpMap.put("j_wen3", IMKitService.jMap.get("j_wen3") != null ? IMKitService.jMap.get("j_wen3") : "");
            tmpMap.put("j_wen4", IMKitService.jMap.get("j_wen4") != null ? IMKitService.jMap.get("j_wen4") : "");
            tmpMap.put("y_static", IMKitService.yMap.get("y_static") != null ? IMKitService.yMap.get("y_static") : "0");
            tmpMap.put("y_wen1", IMKitService.yMap.get("y_wen1") != null ? IMKitService.yMap.get("y_wen1") : "");
            tmpMap.put("y_wen2", IMKitService.yMap.get("y_wen2") != null ? IMKitService.yMap.get("y_wen2") : "");
            tmpMap.put("y_wen3", IMKitService.yMap.get("y_wen3") != null ? IMKitService.yMap.get("y_wen3") : "");
            tmpMap.put("y_wen4", IMKitService.yMap.get("y_wen4") != null ? IMKitService.yMap.get("y_wen4") : "");
            tmpMap.put("y_wen5", IMKitService.yMap.get("y_wen5") != null ? IMKitService.yMap.get("y_wen5") : "");
            tmpMap.put("y_wen6", IMKitService.yMap.get("y_wen6") != null ? IMKitService.yMap.get("y_wen6") : "");
            tmpMap.put("k_static", IMKitService.kMap.get("k_static") != null ? IMKitService.kMap.get("k_static") : "0");
            tmpMap.put("k_wen1", IMKitService.kMap.get("k_wen1") != null ? IMKitService.kMap.get("k_wen1") : "");
            tmpMap.put("k_wen2", IMKitService.kMap.get("k_wen2") != null ? IMKitService.kMap.get("k_wen2") : "");
            tmpMap.put("k_wen3", IMKitService.kMap.get("k_wen3") != null ? IMKitService.kMap.get("k_wen3") : "");
            tmpMap.put("k_wen4", IMKitService.kMap.get("k_wen4") != null ? IMKitService.kMap.get("k_wen4") : "");
//            getNetPriData(tmpMap);
            initEnveData();
        } else {//---->订单
            if ("2".equals(type)) {//家政服务
                mjSerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mSerializableap");
                if (mjSerializableap != null) {
                    IMKitService.sjMap.put("sj", mjSerializableap);
                    Map<String, ZYEntity> mjMap = mjSerializableap.getMap();
                    if (mjMap != null) {
                        IMKitService.jMap.put("j_static", "2");
                        int k = 0;
                        for (Map.Entry<String, ZYEntity> mj : mjMap.entrySet()) {
                            ++k;
                            String strJ = "j_wen" + k;
                            ZYEntity zYEntity = (ZYEntity) mj.getValue();
                            String type2 = zYEntity.getType();
                            if ("1".equals(type2)) {
                                String strValue = zYEntity.getDaList().get(0).getStrId();
                                IMKitService.jMap.put(strJ, strValue);
                            } else if ("3".equals(type2)) {
                                if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                                        sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(",");
                                    }
                                    if (sb.toString().endsWith(",")) {
                                        sb.deleteCharAt(sb.lastIndexOf(","));
                                    }
                                    IMKitService.jMap.put(strJ, sb.toString());
                                }
                            }
                        }
                    } else {
                        IMKitService.jMap.put("j_static", "0");
                        IMKitService.jMap.put("j_wen1", "");
                        IMKitService.jMap.put("j_wen2", "");
                        IMKitService.jMap.put("j_wen3", "");
                        IMKitService.jMap.put("j_wen4", "");
                    }

                }
                initPriData();

            } else if ("3".equals(type)) {//庭院管理
                mySerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mSerializableap");
                if (mySerializableap != null) {
                    IMKitService.syMap.put("sy", mySerializableap);
                    Map<String, ZYEntity> myMap = mySerializableap.getMap();
                    if (myMap != null) {
                        IMKitService.yMap.put("y_static", "2");
                        int k = 0;
                        for (Map.Entry<String, ZYEntity> my : myMap.entrySet()) {
                            ++k;

                            ZYEntity zYEntity = (ZYEntity) my.getValue();
                            String strY = "y_wen" + zYEntity.getStep();
                            String type2 = zYEntity.getType();
                            if ("1".equals(type2)) {
                                String strValue = zYEntity.getDaList().get(0).getStrId();
                                IMKitService.yMap.put(strY, strValue);
                            } else if ("3".equals(type2)) {
                                if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                                        sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(",");
                                    }
                                    if (sb.toString().endsWith(",")) {
                                        sb.deleteCharAt(sb.lastIndexOf(","));
                                    }
                                    IMKitService.yMap.put(strY, sb.toString());
                                }
                            }
                        }
                    } else {
                        IMKitService.yMap.put("y_static", "0");
                        IMKitService.yMap.put("y_wen1", "");
                        IMKitService.yMap.put("y_wen2", "");
                        IMKitService.yMap.put("y_wen3", "");
                        IMKitService.yMap.put("y_wen4", "");
                        IMKitService.yMap.put("y_wen5", "");
                        IMKitService.yMap.put("y_wen6", "");
                    }
                }
                initPriData();
            } else if ("4".equals(type)) {//租赁管理
                mzSerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mSerializableap");
                if (mzSerializableap != null) {
                    IMKitService.szMap.put("sz", mzSerializableap);
                    Map<String, ZYEntity> mzMap = mzSerializableap.getMap();
                    if (mzMap != null) {
                        IMKitService.zMap.put("z_static", "2");
                        int k = 0;
                        for (Map.Entry<String, ZYEntity> mz : mzMap.entrySet()) {
                            ++k;
                            String strZ = "z_wen" + k;
                            ZYEntity zYEntity = (ZYEntity) mz.getValue();
                            String type2 = zYEntity.getType();
                            if ("1".equals(type2)) {
                                String ssid = zYEntity.getTittleId();
                                String strValue = zYEntity.getDaList().get(0).getStrId();
                                String str = zYEntity.getDaList().get(0).getStr();
                                Log.i("strInforamton",str+"--"+strValue+"--"+ssid);
                                if (str.contains("CAD$/Month")||str.contains("加元/每月")||str.contains("加元/月")) {
                                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                                        str = "#" + strValue + "#" + str.substring(0, str.length() - 10);
                                    }else{
                                        str = "#" + strValue + "#" + str.substring(0, str.indexOf("加"));
                                    }

                                    IMKitService.zMap.put(strZ, str);
                                } else {//---------------------------------ddf
                                    if ("68".equals(ssid)) {
                                        IMKitService.zMap.put("z_wen4", strValue);
                                    } else {
                                        IMKitService.zMap.put(strZ, strValue);
                                    }
//                                    IMKitService.zMap.put(strZ, strValue);
                                }

                            } else if ("3".equals(type2)) {
                                if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                                        sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(",");
                                    }
                                    if (sb.toString().endsWith(",")) {
                                        sb.deleteCharAt(sb.lastIndexOf(","));
                                    }
                                    IMKitService.zMap.put(strZ, sb.toString());
                                }
                            }
                        }
                    } else {
                        IMKitService.zMap.put("z_static", "0");
                        IMKitService.zMap.put("z_wen1", "");
                        IMKitService.zMap.put("z_wen2", "");
                        IMKitService.zMap.put("z_wen3", "");
                        IMKitService.zMap.put("z_wen4", "");
                        IMKitService.zMap.put("z_wen5", "");
                    }
                }
                initPriData();
            } else if ("5".equals(type)) {//空屋管理
                mkSerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mSerializableap");
                if (mkSerializableap != null) {
                    IMKitService.skMap.put("sk", mkSerializableap);
                    Map<String, ZYEntity> mkMap = mkSerializableap.getMap();
                    if (mkMap != null) {
                        IMKitService.kMap.put("k_static", "2");
                        int k = 0;
                        for (Map.Entry<String, ZYEntity> mk : mkMap.entrySet()) {
                            ++k;
                            String strK = "k_wen" + k;
                            ZYEntity zYEntity = (ZYEntity) mk.getValue();
                            String type2 = zYEntity.getType();
                            if ("1".equals(type2)) {
                                String strValue = zYEntity.getDaList().get(0).getStrId();
                                IMKitService.kMap.put(strK, strValue);
                            } else if ("3".equals(type2)) {
                                if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                                        sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(",");
                                    }
                                    if (sb.toString().endsWith(",")) {
                                        sb.deleteCharAt(sb.lastIndexOf(","));
                                    }
                                    IMKitService.kMap.put(strK, sb.toString());
                                }
                            }
                        }
                    } else {
                        IMKitService.kMap.put("k_static", "0");
                        IMKitService.kMap.put("k_wen1", "");
                        IMKitService.kMap.put("k_wen2", "");
                        IMKitService.kMap.put("k_wen3", "");
                        IMKitService.kMap.put("k_wen4", "");
                    }
                }
                initPriData();
            }
            vip_envelope_tv_addr.setText(IMKitService.addrMap.get("addrInfo"));
        }
        if (isConfig) {
            HousAddEntity.DataBean dataBean = (HousAddEntity.DataBean) getIntent().getExtras().getSerializable("data");
            address_info = dataBean.getAddress_info();
            IMKitService.map.put("h_id", dataBean.getId());
            IMKitService.map.put("city", dataBean.getCity() + "");
            IMKitService.addrMap.put("addrInfo", dataBean.getAddress_info());
            vip_envelope_tv_addr.setText(address_info);
            AppGlobal.getInstance().setIsFirst(true);
        }
        if (!IMKitService.sjMap.isEmpty()) {//Atrue
            mJzView = new JzView(EnvelopeActivity.this);
            mJzView.setMbVisible(false);
            ll_content_env_layout.addView(mJzView, 0);
            if (!IMKitService.syMap.isEmpty()) {//Btrue
                mTyView = new TyView(EnvelopeActivity.this);
                mTyView.setMbVisible(false);
                ll_content_env_layout.addView(mTyView, 1);
                if (!IMKitService.szMap.isEmpty()) {//Ctrue
                    mZlView = new ZlView(EnvelopeActivity.this);
                    mZlView.setMbVisible(false);
                    ll_content_env_layout.addView(mZlView, 2);
                    if (!IMKitService.skMap.isEmpty()) {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(false);
                        ll_content_env_layout.addView(mKwView, 3);
                    } else {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(true);
                        ll_content_env_layout.addView(mKwView, 3);
                    }
                } else {//Cfalse
                    if (!IMKitService.skMap.isEmpty()) {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(false);
                        ll_content_env_layout.addView(mKwView, 2);
                    } else {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(true);
                        ll_content_env_layout.addView(mKwView, 2);
                    }
                    mZlView = new ZlView(EnvelopeActivity.this);
                    mZlView.setMbVisible(true);
                    ll_content_env_layout.addView(mZlView, 3);
                }
            } else {//Bfalse
                if (!IMKitService.szMap.isEmpty()) {//Ctrue
                    mZlView = new ZlView(EnvelopeActivity.this);
                    mZlView.setMbVisible(false);
                    ll_content_env_layout.addView(mZlView, 1);
                    if (!IMKitService.skMap.isEmpty()) {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(false);
                        ll_content_env_layout.addView(mKwView, 2);
                    } else {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(true);
                        ll_content_env_layout.addView(mKwView, 1);
                    }
                } else {//Cfalse
                    if (!IMKitService.skMap.isEmpty()) {//Dtrue
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(false);
                        ll_content_env_layout.addView(mKwView, 1);
                    } else {//Dfalse
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(true);
                        ll_content_env_layout.addView(mKwView, 1);
                    }
                    mZlView = new ZlView(EnvelopeActivity.this);
                    mZlView.setMbVisible(true);
                    ll_content_env_layout.addView(mZlView, 2);
                }
                mTyView = new TyView(EnvelopeActivity.this);
                mTyView.setMbVisible(true);
                ll_content_env_layout.addView(mTyView, 3);
            }
        } else {//Afalse
            if (!IMKitService.syMap.isEmpty()) {//Btrue
                mTyView = new TyView(EnvelopeActivity.this);
                mTyView.setMbVisible(false);
                ll_content_env_layout.addView(mTyView, 0);
                if (!IMKitService.szMap.isEmpty()) {//Ctrue
                    mZlView = new ZlView(EnvelopeActivity.this);
                    mZlView.setMbVisible(false);
                    ll_content_env_layout.addView(mZlView, 1);
                    if (!IMKitService.skMap.isEmpty()) {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(false);
                        ll_content_env_layout.addView(mKwView, 2);
                    } else {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(true);
                        ll_content_env_layout.addView(mKwView, 2);
                    }
                } else {//Cfalse
                    if (!IMKitService.skMap.isEmpty()) {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(false);
                        ll_content_env_layout.addView(mKwView, 1);
                    } else {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(true);
                        ll_content_env_layout.addView(mKwView, 1);
                    }
                    mZlView = new ZlView(EnvelopeActivity.this);
                    mZlView.setMbVisible(true);
                    ll_content_env_layout.addView(mZlView, 2);
                }
            } else {//Bfalse
                if (!IMKitService.szMap.isEmpty()) {//Ctrue
                    mZlView = new ZlView(EnvelopeActivity.this);
                    mZlView.setMbVisible(false);
                    ll_content_env_layout.addView(mZlView, 0);
                    if (!IMKitService.skMap.isEmpty()) {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(false);
                        ll_content_env_layout.addView(mKwView, 1);
                    } else {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(true);
                        ll_content_env_layout.addView(mKwView, 1);
                    }
                } else {//Cfalse
                    if (!IMKitService.skMap.isEmpty()) {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(false);
                        ll_content_env_layout.addView(mKwView, 0);
                    } else {
                        mKwView = new KwView(EnvelopeActivity.this);
                        mKwView.setMbVisible(true);
                        ll_content_env_layout.addView(mKwView, 0);
                    }
                    mZlView = new ZlView(EnvelopeActivity.this);
                    mZlView.setMbVisible(true);
                    ll_content_env_layout.addView(mZlView, 1);
                }
                mTyView = new TyView(EnvelopeActivity.this);
                mTyView.setMbVisible(true);
                ll_content_env_layout.addView(mTyView, 2);
            }
            mJzView = new JzView(EnvelopeActivity.this);
            mJzView.setMbVisible(true);
//            ll_content_env_layout.addView(mJzView, 3);
        }
        for (int i = 0; i < ll_content_env_layout.getChildCount(); i++) {
            if (ll_content_env_layout.getChildAt(i) instanceof KwView) {
                mKwView = (KwView) ll_content_env_layout.getChildAt(i);
                mKwView.getLayout().setOnClickListener(mOnClickListener);
                mKwView.getImg().setOnClickListener(mOnClickListener);
            } else if (ll_content_env_layout.getChildAt(i) instanceof JzView) {
                mJzView = (JzView) ll_content_env_layout.getChildAt(i);
                mJzView.getLayout().setOnClickListener(mOnClickListener);
                mJzView.getImg().setOnClickListener(mOnClickListener);
            } else if (ll_content_env_layout.getChildAt(i) instanceof ZlView) {
                mZlView = (ZlView) ll_content_env_layout.getChildAt(i);
                mZlView.getLayout().setOnClickListener(mOnClickListener);
                mZlView.getImg().setOnClickListener(mOnClickListener);
            } else if (ll_content_env_layout.getChildAt(i) instanceof TyView) {
                mTyView = (TyView) ll_content_env_layout.getChildAt(i);
                mTyView.getLayout().setOnClickListener(mOnClickListener);
                mTyView.getImg().setOnClickListener(mOnClickListener);
            }
        }
    }

    private void getNetPriData(Map<String, String> tmpMap) {
        tmpMap.put("secret_key", SPUtils.getString(EnvelopeActivity.this, "secret", ""));
        tmpMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(EnvelopeActivity.this, Contants.support_price, tmpMap, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    System.out.println(">>>>>>>>>>>>>价格和也分>>>" + response);
                    mEvnEntity = new Gson().fromJson(response, EvnEntity.class);
                    bindHisView(mEvnEntity);
                } else {
                    ToastUtil.longToast(EnvelopeActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void initPriData() {
        tmpMap.put("j_static", IMKitService.jMap.get("j_static") != null ? IMKitService.jMap.get("j_static") : "0");
        tmpMap.put("j_wen1", IMKitService.jMap.get("j_wen1") != null ? IMKitService.jMap.get("j_wen1") : "");
        tmpMap.put("j_wen2", IMKitService.jMap.get("j_wen2") != null ? IMKitService.jMap.get("j_wen2") : "");
        tmpMap.put("j_wen3", IMKitService.jMap.get("j_wen3") != null ? IMKitService.jMap.get("j_wen3") : "");
        tmpMap.put("j_wen4", IMKitService.jMap.get("j_wen4") != null ? IMKitService.jMap.get("j_wen4") : "");
        tmpMap.put("y_static", IMKitService.yMap.get("y_static") != null ? IMKitService.yMap.get("y_static") : "0");
        tmpMap.put("y_wen1", IMKitService.yMap.get("y_wen1") != null ? IMKitService.yMap.get("y_wen1") : "");
        tmpMap.put("y_wen2", IMKitService.yMap.get("y_wen2") != null ? IMKitService.yMap.get("y_wen2") : "");
        tmpMap.put("y_wen3", IMKitService.yMap.get("y_wen3") != null ? IMKitService.yMap.get("y_wen3") : "");
        tmpMap.put("y_wen4", IMKitService.yMap.get("y_wen4") != null ? IMKitService.yMap.get("y_wen4") : "");
        tmpMap.put("y_wen5", IMKitService.yMap.get("y_wen5") != null ? IMKitService.yMap.get("y_wen5") : "");
        tmpMap.put("y_wen6", IMKitService.yMap.get("y_wen6") != null ? IMKitService.yMap.get("y_wen6") : "");
        tmpMap.put("k_static", IMKitService.kMap.get("k_static") != null ? IMKitService.kMap.get("k_static") : "0");
        tmpMap.put("k_wen1", IMKitService.kMap.get("k_wen1") != null ? IMKitService.kMap.get("k_wen1") : "");
        tmpMap.put("k_wen2", IMKitService.kMap.get("k_wen1") != null ? IMKitService.kMap.get("k_wen1") : "");
        tmpMap.put("k_wen3", IMKitService.kMap.get("k_wen1") != null ? IMKitService.kMap.get("k_wen1") : "");
        tmpMap.put("k_wen4", IMKitService.kMap.get("k_wen1") != null ? IMKitService.kMap.get("k_wen1") : "");
        getNetPriData(tmpMap);
    }

    private void bindHisView(EvnEntity evnEntity) {
        Log.e("aaaa--------->",ystatic+"--"+zstatic+"---"+kstatic);
//        if(!getIntent().getBooleanExtra("fromEnvelope",false)){
//            ystatic="";
//            zstatic = "";
//            kstatic ="";
//        }
        Log.e("aaaa--------->",ystatic+"--"+zstatic+"---"+kstatic);
        mJzView.setMPText(evnEntity.getData().getJ_month(), evnEntity.getData().getJ_money(), SPUtils.getString(EnvelopeActivity.this,"j_xia","-1"), jstatic);
        mTyView.setMPText(evnEntity.getData().getY_month(), evnEntity.getData().getY_money(), SPUtils.getString(EnvelopeActivity.this,"y_xia","-1"), ystatic);
        mKwView.setMPText(evnEntity.getData().getK_month(), evnEntity.getData().getK_money(), SPUtils.getString(EnvelopeActivity.this,"k_xia","-1"), kstatic);
        mZlView.setMPText(evnEntity.getData().getZ_month(), "", SPUtils.getString(EnvelopeActivity.this,"z_xia","-1"), zstatic);
        float countMoney = Float.valueOf(evnEntity.getData().getJ_money()) + Float.valueOf(evnEntity.getData().getY_money()) + Float.valueOf(evnEntity.getData().getK_money());
        tv_xfze.setText("$" + countMoney);

    }


    private void initView() {
        btn_envelop_confirm = (TextView) findViewById(R.id.btn_envelop_confirm);
        btn_envelop_confirm.setOnClickListener(mOnClickListener);
        findViewById(R.id.tv_delete_all_service).setOnClickListener(mOnClickListener);
        vip_envelope_ll_addr = (LinearLayout) findViewById(R.id.vip_envelope_ll_addr);
        vip_envelope_tv_addr = (TextView) findViewById(R.id.vip_envelope_tv_addr);
        vip_envelope_tv_addr.setText(IMKitService.addrMap.get("addrInfo"));
        ll_content_env_layout = (LinearLayout) findViewById(R.id.ll_content_env_layout);
        tv_xfze = (TextView) findViewById(R.id.tv_xfze);


    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_envelope_bar.getBackView()) {
                if (isOder) {
                    finish();
                } else {
                    if (IMKitService.isConfig) {
                        Intent intent = new Intent(EnvelopeActivity.this, OrderConfigActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        if (AppGlobal.getInstance().getIsHome()) {
                            finish();
                        } else {
                            Intent intent = new Intent(EnvelopeActivity.this, MainActivity.class);
                            intent.putExtra("flag", "ishome");
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            } else if (view == mKwView.getLayout() || view == mKwView.getImg()) {//空屋
                SPUtils.saveString(EnvelopeActivity.this, "kstatic_my", kstatic);
                SPUtils.saveString(EnvelopeActivity.this, "zstatic_my", zstatic);
                SPUtils.saveString(EnvelopeActivity.this, "ystatic_my", ystatic);
                if (!TextUtils.isEmpty(IMKitService.kMap.get("k_static"))) {
                    if (!"0".equals(IMKitService.kMap.get("k_static"))) {
                        Intent intent = new Intent(EnvelopeActivity.this, OutLineActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mSerializableap", IMKitService.skMap.get("sk"));
                        intent.putExtras(bundle);
                        intent.putExtra("type", "5");
                        intent.putExtra("isNew", true);
                        intent.putExtra("kstatic",kstatic);
                        intent.putExtra("zstatic",zstatic);
                        intent.putExtra("ystatic",ystatic);
                        if(getIntent().getBooleanExtra("fromEnvelope",false)){

                            intent.putExtra("fromEnvelope",true);
                        }else{
                            intent.putExtra("fromEnvelope",false);
                        }

                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(EnvelopeActivity.this, CommonProActivity.class);
                        AppGlobal.getInstance().setIsHome(false);
                        intent.putExtra("type", "5");
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(EnvelopeActivity.this, CommonProActivity.class);
                    AppGlobal.getInstance().setIsHome(false);
                    intent.putExtra("type", "5");
                    startActivity(intent);
                }
            } else if (view == mTyView.getLayout() || view == mTyView.getImg()) {//园艺
                SPUtils.saveString(EnvelopeActivity.this, "kstatic_my", kstatic);
                SPUtils.saveString(EnvelopeActivity.this, "zstatic_my", zstatic);
                SPUtils.saveString(EnvelopeActivity.this, "ystatic_my", ystatic);
                if (!TextUtils.isEmpty(IMKitService.yMap.get("y_static"))) {
                    if (!"0".equals(IMKitService.yMap.get("y_static"))) {
                        Intent intent = new Intent(EnvelopeActivity.this, OutLineActivity.class);
                        Bundle bundle = new Bundle();
//                    bundle.putSerializable("mSerializableap", mySerializableap);
                        bundle.putSerializable("mSerializableap", IMKitService.syMap.get("sy"));
                        intent.putExtras(bundle);
                        intent.putExtra("type", "3");
                        intent.putExtra("isNew", true);
                        intent.putExtra("kstatic",kstatic);
                        intent.putExtra("zstatic",zstatic);
                        intent.putExtra("ystatic",ystatic);
                        if(getIntent().getBooleanExtra("fromEnvelope",false)){

                            intent.putExtra("fromEnvelope",true);
                        }else{
                            intent.putExtra("fromEnvelope",false);
                        }

                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(EnvelopeActivity.this, CommonProActivity.class);
                        AppGlobal.getInstance().setIsHome(false);
                        intent.putExtra("type", "3");
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(EnvelopeActivity.this, CommonProActivity.class);
                    AppGlobal.getInstance().setIsHome(false);
                    intent.putExtra("type", "3");
                    startActivity(intent);
                }
            } else if (view == mZlView.getLayout() || view == mZlView.getImg()) {//租赁
                SPUtils.saveString(EnvelopeActivity.this, "kstatic_my", kstatic);
                SPUtils.saveString(EnvelopeActivity.this, "zstatic_my", zstatic);
                SPUtils.saveString(EnvelopeActivity.this, "ystatic_my", ystatic);
                if (!TextUtils.isEmpty(IMKitService.zMap.get("z_static"))) {
                    if (!"0".equals(IMKitService.zMap.get("z_static"))) {
                        Intent intent = new Intent(EnvelopeActivity.this, OutLineActivity.class);
                        Bundle bundle = new Bundle();
//                    bundle.putSerializable("mSerializableap", mzSerializableap);
                        bundle.putSerializable("mSerializableap", IMKitService.szMap.get("sz"));
                        intent.putExtras(bundle);
                        intent.putExtra("type", "4");
                        intent.putExtra("isNew", true);
                        intent.putExtra("fromEnvelope",true);
                        intent.putExtra("kstatic",kstatic);
                        intent.putExtra("zstatic",zstatic);
                        intent.putExtra("ystatic",ystatic);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(EnvelopeActivity.this, CommonProActivity.class);
                        AppGlobal.getInstance().setIsHome(false);
                        intent.putExtra("type", "4");
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(EnvelopeActivity.this, CommonProActivity.class);
                    AppGlobal.getInstance().setIsHome(false);
                    intent.putExtra("type", "4");
                    startActivity(intent);
                }
            } else if (view == mJzView.getLayout() || view == mJzView.getImg()) {//家政
                if (!TextUtils.isEmpty(IMKitService.jMap.get("j_static"))) {
                    if (!"0".equals(IMKitService.jMap.get("j_static"))) {
                        Intent intent = new Intent(EnvelopeActivity.this, OutLineActivity.class);
                        Bundle bundle = new Bundle();
//                    bundle.putSerializable("mSerializableap", mjSerializableap);
                        bundle.putSerializable("mSerializableap", IMKitService.sjMap.get("sj"));
                        intent.putExtras(bundle);
                        intent.putExtra("isNew", true);
                        intent.putExtra("type", "2");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(EnvelopeActivity.this, CommonProActivity.class);
                        AppGlobal.getInstance().setIsHome(false);
                        intent.putExtra("type", "2");
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(EnvelopeActivity.this, CommonProActivity.class);
                    AppGlobal.getInstance().setIsHome(false);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                }
            }else if(view.getId()==R.id.tv_delete_all_service){

                CustomDialog.Builder customBuilder = new CustomDialog.Builder(EnvelopeActivity.this);
                customBuilder.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.delete_all_service)).setPositiveButton(getString(R.string.message_alert_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteAllService();
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



            } else if (view.getId() == R.id.btn_envelop_confirm) {
                setDataMap();
                if (AppGlobal.getInstance().getIsFirst()) {
                    LoadDialog.showProgressDialog(EnvelopeActivity.this);
                    Log.e("mapInformation------",IMKitService.map.toString());
                    MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(EnvelopeActivity.this, Contants.self, IMKitService.map, null, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            int code = JsonUtils.getJsonInt(response, "status");
                            if (code == 200) {
                                LoadDialog.closeProgressDialog();
                                if(AppGlobal.getInstance().getLagStr().equals("en")){
                                    ToastUtil.longToast(EnvelopeActivity.this, "The order has been submitted, it is being endorsed.");
                                }else{
                                    ToastUtil.longToast(EnvelopeActivity.this, "订单已提交，请等待后台审核通过");
                                }

                                AppGlobal.getInstance().setIsFirst(false);
                                Intent intent = new Intent(EnvelopeActivity.this, MainActivity.class);
                                intent.putExtra("flag", "dsd");
                                startActivity(intent);
                                finish();
                            } else {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(EnvelopeActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                            }
                        }
                    }));
                } else {
                    LoadDialog.showProgressDialog(EnvelopeActivity.this);
                    MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(EnvelopeActivity.this, Contants.support_save, IMKitService.map, null, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            int code = JsonUtils.getJsonInt(response, "status");
                            if (code == 200) {
                                LoadDialog.closeProgressDialog();
                                if(AppGlobal.getInstance().getLagStr().equals("zh")){
                                    ToastUtil.longToast(EnvelopeActivity.this, "修改成功");
                                }else{
                                    ToastUtil.longToast(EnvelopeActivity.this, "Done");
                                }
                                AppGlobal.getInstance().setIsFirst(true);
//                                startActivity(new Intent(EnvelopeActivity.this, MainActivity.class));
                                Intent intent = new Intent(EnvelopeActivity.this, OrderConfigActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                LoadDialog.closeProgressDialog();
                                ToastUtil.longToast(EnvelopeActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                            }
                        }
                    }));
                }

            }
        }
    };


    private void deleteAllService(){
        setDataMap();
        IMKitService.map.put("k_static","0");
        IMKitService.map.put("y_static","0");
        IMKitService.map.put("z_static","0");
        LoadDialog.showProgressDialog(EnvelopeActivity.this);
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(EnvelopeActivity.this, Contants.support_save, IMKitService.map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    LoadDialog.closeProgressDialog();
                    if(AppGlobal.getInstance().getLagStr().equals("zh")){
                        ToastUtil.longToast(EnvelopeActivity.this, "修改成功");
                    }else{
                        ToastUtil.longToast(EnvelopeActivity.this, "Done");
                    }
                    AppGlobal.getInstance().setIsFirst(true);
//                                startActivity(new Intent(EnvelopeActivity.this, MainActivity.class));
                    Intent intent = new Intent(EnvelopeActivity.this, OrderConfigActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(EnvelopeActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void setDataMap() {
        if (TextUtils.isEmpty(IMKitService.jMap.get("j_static"))) {
            IMKitService.map.put("j_static", "0");
            IMKitService.map.put("j_wen1", "");
            IMKitService.map.put("j_wen2", "");
            IMKitService.map.put("j_wen3", "");
            IMKitService.map.put("j_wen4", "");
        } else {
            setJData();
        }
        if (TextUtils.isEmpty(IMKitService.yMap.get("y_static"))) {
            IMKitService.map.put("y_static", "0");
            IMKitService.map.put("y_wen1", "");
            IMKitService.map.put("y_wen2", "");
            IMKitService.map.put("y_wen3", "");
            IMKitService.map.put("y_wen4", "");
            IMKitService.map.put("y_wen5", "");
            IMKitService.map.put("y_wen6", "");
        } else {
            setYData();
        }

        if (TextUtils.isEmpty(IMKitService.zMap.get("z_static"))) {
            IMKitService.map.put("z_static", "0");
            IMKitService.map.put("z_wen1", "");
            IMKitService.map.put("z_wen2", "");
            IMKitService.map.put("z_wen3", "");
            IMKitService.map.put("z_wen4", "");
            IMKitService.map.put("z_wen5", "");
        } else {
            setZData();
        }

        if (TextUtils.isEmpty(IMKitService.kMap.get("k_static"))) {
            IMKitService.map.put("k_static", "0");
            IMKitService.map.put("k_wen1", "");
            IMKitService.map.put("k_wen2", "");
            IMKitService.map.put("k_wen3", "");
            IMKitService.map.put("k_wen4", "");
        } else {
            setKData();
        }


    }

    private void setKData() {
        //空屋
        if ("0".equals(IMKitService.kMap.get("k_static"))) {
            IMKitService.map.put("k_static", "0");
            IMKitService.map.put("k_wen1", "");
            IMKitService.map.put("k_wen2", "");
            IMKitService.map.put("k_wen3", "");
            IMKitService.map.put("k_wen4", "");
        } else {
            IMKitService.map.put("k_static", IMKitService.kMap.get("k_static"));
            IMKitService.map.put("k_wen1", IMKitService.kMap.get("k_wen1") == null ? "" : IMKitService.kMap.get("k_wen1"));
            IMKitService.map.put("k_wen2", IMKitService.kMap.get("k_wen2") == null ? "" : IMKitService.kMap.get("k_wen2"));
            IMKitService.map.put("k_wen3", IMKitService.kMap.get("k_wen3") == null ? "" : IMKitService.kMap.get("k_wen3"));
            IMKitService.map.put("k_wen4", IMKitService.kMap.get("k_wen4") == null ? "" : IMKitService.kMap.get("k_wen4"));
        }
    }

    private void setZData() {
        //租赁
        if ("0".equals(IMKitService.zMap.get("z_static"))) {
            IMKitService.map.put("z_static", "0");
            IMKitService.map.put("z_wen1", "");
            IMKitService.map.put("z_wen2", "");
            IMKitService.map.put("z_wen3", "");
            IMKitService.map.put("z_wen4", "");
            IMKitService.map.put("z_wen5", "");
        } else {
            IMKitService.map.put("z_static", IMKitService.zMap.get("z_static"));
            IMKitService.map.put("z_wen1", IMKitService.zMap.get("z_wen1") == null ? "" : IMKitService.zMap.get("z_wen1"));
            IMKitService.map.put("z_wen2", IMKitService.zMap.get("z_wen2") == null ? "" : IMKitService.zMap.get("z_wen2"));
            IMKitService.map.put("z_wen3", IMKitService.zMap.get("z_wen3") == null ? "" : IMKitService.zMap.get("z_wen3"));
            IMKitService.map.put("z_wen4", IMKitService.zMap.get("z_wen4") == null ? "" : IMKitService.zMap.get("z_wen4"));
            IMKitService.map.put("z_wen5", IMKitService.zMap.get("z_wen5") == null ? "" : IMKitService.zMap.get("z_wen5"));
        }
    }

    private void setYData() {
        //庭院
        if ("0".equals(IMKitService.yMap.get("y_static"))) {
            IMKitService.map.put("y_static", "0");
            IMKitService.map.put("y_wen1", "");
            IMKitService.map.put("y_wen2", "");
            IMKitService.map.put("y_wen3", "");
            IMKitService.map.put("y_wen4", "");
            IMKitService.map.put("y_wen5", "");
            IMKitService.map.put("y_wen6", "");
        } else {
            IMKitService.map.put("y_static", IMKitService.yMap.get("y_static"));
            IMKitService.map.put("y_wen1", IMKitService.yMap.get("y_wen1") == null ? "" : IMKitService.yMap.get("y_wen1"));
            IMKitService.map.put("y_wen2", IMKitService.yMap.get("y_wen2") == null ? "" : IMKitService.yMap.get("y_wen2"));
            IMKitService.map.put("y_wen3", IMKitService.yMap.get("y_wen3") == null ? "" : IMKitService.yMap.get("y_wen3"));
            IMKitService.map.put("y_wen4", IMKitService.yMap.get("y_wen4") == null ? "" : IMKitService.yMap.get("y_wen4"));
            IMKitService.map.put("y_wen5", IMKitService.yMap.get("y_wen5") == null ? "" : IMKitService.yMap.get("y_wen5"));
            IMKitService.map.put("y_wen6", IMKitService.yMap.get("y_wen6") == null ? "" : IMKitService.yMap.get("y_wen6"));
        }
    }

    private void setJData() {
        //家政
        if ("0".equals(IMKitService.jMap.get("j_static"))) {
            IMKitService.map.put("j_static", "0");
            IMKitService.map.put("j_wen1", "");
            IMKitService.map.put("j_wen2", "");
            IMKitService.map.put("j_wen3", "");
            IMKitService.map.put("j_wen4", "");
        } else {
            IMKitService.map.put("j_static", IMKitService.jMap.get("j_static"));
            IMKitService.map.put("j_wen1", IMKitService.jMap.get("j_wen1") == null ? "" : IMKitService.jMap.get("j_wen1"));
            IMKitService.map.put("j_wen2", IMKitService.jMap.get("j_wen2") == null ? "" : IMKitService.jMap.get("j_wen2"));
            IMKitService.map.put("j_wen3", IMKitService.jMap.get("j_wen3") == null ? "" : IMKitService.jMap.get("j_wen3"));
            IMKitService.map.put("j_wen4", IMKitService.jMap.get("j_wen4") == null ? "" : IMKitService.jMap.get("j_wen4"));
        }
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (isOder) {
                finish();

            } else {
                if (IMKitService.isConfig) {
                    Intent intent = new Intent(EnvelopeActivity.this, OrderConfigActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (AppGlobal.getInstance().getIsHome()) {
                        finish();
                    } else {
                        Intent intent = new Intent(EnvelopeActivity.this, MainActivity.class);
                        intent.putExtra("flag", "ishome");
                        startActivity(intent);
                        finish();
                    }
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
