package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.TyglEntity;
import com.haiwai.housekeeper.entity.ZYEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.ParseTypesUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SerializableMap;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.OutlineMultiView;
import com.haiwai.housekeeper.view.OutlineSimpleView;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class OutLineActivity extends AppCompatActivity {
    private static final int REQUESTCODE = 1234;
    private LinearLayout ll_wd_layout, ll_bottom_layout;
    private TopViewNormalBar top_outline_bar;
    private Button btn_hous_common_confirm;
    private String type = "0";
    private SerializableMap mSerializableap,wSerializableap;
    boolean isNew = false;
    private List<ZYEntity> list = new ArrayList<>();
    private LinearLayout ll_fwzl_layout, ll_tygl_layout, ll_fwbj_layout;
    private TextView tv_month_money;
    private Map<String, String> jMap = new HashMap<>();
    //-----------------庭院账单------
    View tabView;
    private TextView tv_c_1, tv_c_2, tv_c_3, tv_c_4, tv_c_5, tv_c_6, tv_c_7, tv_c_8, tv_c_9, tv_c_10, tv_c_11, tv_c_12;
    private TextView tv_m_1, tv_m_2, tv_m_3, tv_m_4, tv_m_5, tv_m_6, tv_m_7, tv_m_8, tv_m_9, tv_m_10, tv_m_11, tv_m_12;
    private LinearLayout llnote, ll1yout, ll2yout, ll3yout, ll4yout, ll5yout, ll6yout, ll7yout, ll8yout, ll9yout, ll10yout, ll11yout, ll12yout;
    private Map<String, TyglEntity> y_map = new LinkedHashMap<>();
    private String isZhorEn = "";
    private Map<String, String> reMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_line);
        top_outline_bar = (TopViewNormalBar) findViewById(R.id.top_outline_bar);
        top_outline_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData();
    }

    private void initView() {
        ll_wd_layout = (LinearLayout) findViewById(R.id.ll_wd_layout);
        ll_bottom_layout = (LinearLayout) findViewById(R.id.ll_bottom_layout);
        btn_hous_common_confirm = (Button) findViewById(R.id.btn_hous_common_confirm);
        btn_hous_common_confirm.setOnClickListener(mOnClickListener);
        ll_fwzl_layout = (LinearLayout) findViewById(R.id.ll_fwzl_layout);
        ll_tygl_layout = (LinearLayout) findViewById(R.id.ll_tygl_layout);
        ll_fwbj_layout = (LinearLayout) findViewById(R.id.ll_fwbj_layout);
        tv_month_money = (TextView) findViewById(R.id.tv_month_money);

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        findViewById(R.id.ll_bottom_con).measure(w, h);
        int height = findViewById(R.id.ll_bottom_con).getMeasuredHeight();

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, height + 5);
        findViewById(R.id.sv_confirm_view).setLayoutParams(lp);


    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        initData();
//    }

    private void initData() {
        tabView = findViewById(R.id.tab_layout);
        initTabView(tabView);
        type = getIntent().getStringExtra("type");
        isNew = getIntent().getBooleanExtra("isNew", false);
        if (isNew) {
            mSerializableap = (SerializableMap) getIntent().getExtras().getSerializable("mSerializableap");
            Iterator<String> iter = mSerializableap.getMap().keySet().iterator();
            Map<String,ZYEntity> zxEnMap = new HashMap<>();
            while (iter.hasNext()){
                String mKey = iter.next();
                mSerializableap.getMap().get(mKey).setStep(Integer.parseInt(mKey.substring(4,mKey.length())));
                zxEnMap.put(mKey,mSerializableap.getMap().get(mKey));
                Log.e("result--->",mKey+"----adsdfsff"+mSerializableap.getMap().get(mKey).getStep());
            }
            wSerializableap = new SerializableMap();
            wSerializableap.setMap(zxEnMap);
        } else {
            Bundle bundle = getIntent().getExtras();
            mSerializableap = (SerializableMap) bundle.get("map");
        }
        top_outline_bar.setTitle(getString(R.string.o2o_order_make_sure_title));
        if ("2".equals(type)) {
//            top_outline_bar.setTitle("房屋保洁概要");
            ll_fwzl_layout.setVisibility(View.GONE);
            ll_tygl_layout.setVisibility(View.GONE);
            ll_fwbj_layout.setVisibility(View.VISIBLE);
        } else if ("3".equals(type)) {
//            top_outline_bar.setTitle("庭院管理概要");
            ll_fwzl_layout.setVisibility(View.GONE);
            ll_tygl_layout.setVisibility(View.VISIBLE);
            ll_fwbj_layout.setVisibility(View.GONE);
            tabView.setVisibility(View.VISIBLE);

            Map<String, ZYEntity> map = mSerializableap.getMap();
            int c = 0;
            Map<String, String> ymap = new LinkedHashMap<>();
            for (Map.Entry mp : map.entrySet()) {
                ZYEntity zYEntity = (ZYEntity) mp.getValue();
                list.add(zYEntity);
                c++;
                String type2 = zYEntity.getType();
                if ("1".equals(type2)) {
                    ymap.put("y_wen" + zYEntity.getStep(), zYEntity.getDaList().get(0).getStrId());
                } else if ("3".equals(type2)) {
                    StringBuilder sb = new StringBuilder();
                    if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                        for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                            sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(',');
                        }
                        if (sb.length() > 0 && sb.toString().endsWith(",")) {
                            sb = sb.deleteCharAt(sb.lastIndexOf(","));
                        }
                        ymap.put("y_wen" + zYEntity.getStep(), sb.toString());
                    }
                }
            }
            ymap.put("secret_key", SPUtils.getString(OutLineActivity.this, "secret", ""));
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OutLineActivity.this, Contants.yard_management, ymap, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("庭院概要>>>>" + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        ParseTyglData(response);
                    } else {
                        ToastUtil.longToast(OutLineActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        } else if ("4".equals(type)) {
//            top_outline_bar.setTitle("房屋租赁概要");
            ll_fwzl_layout.setVisibility(View.VISIBLE);
            ll_tygl_layout.setVisibility(View.GONE);
            ll_fwbj_layout.setVisibility(View.GONE);
        } else if ("5".equals(type)) {
//            top_outline_bar.setTitle("空屋管理概要");
            ll_fwzl_layout.setVisibility(View.GONE);
            ll_tygl_layout.setVisibility(View.GONE);
            ll_fwbj_layout.setVisibility(View.GONE);
        }

        Map<String, ZYEntity> map = mSerializableap.getMap();
        int c = 0;
        for (Map.Entry mp : map.entrySet()) {
            ZYEntity zYEntity = (ZYEntity) mp.getValue();
            if (zYEntity.getDaList().get(0).getStr() != null) {
                if (!zYEntity.getDaList().get(0).getStr().equals("")) {
                    list.add(zYEntity);
                }
            }
            String type2 = zYEntity.getType();
            Log.i("zcyInformation", type2 + "--" + zYEntity.getTitle()+"--"+zYEntity.getDaList().get(0).getStr());
            if ("1".equals(type2)) {
                if ("2".equals(type)) {
                    ++c;
                    jMap.put("j_wen" + c, zYEntity.getDaList().get(0).getStrId() == null ? "" : zYEntity.getDaList().get(0).getStrId());
                }
                OutlineSimpleView osView = new OutlineSimpleView(this);
                if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                    osView.setTitle(zYEntity.getTitle());
                    osView.setContent(zYEntity.getDaList().get(0).getStr());
                    osView.setVisibility(View.GONE);
                    if (zYEntity.getDaList().get(0).getStr() != null) {
                        if (!zYEntity.getDaList().get(0).getStr().equals("")) {
                            osView.setVisibility(View.VISIBLE);
                        }
                    }
                    ll_wd_layout.addView(osView);
                }

            } else if ("3".equals(type2)) {
                OutlineMultiView omView = new OutlineMultiView(this);
                omView.setTitle(zYEntity.getTitle());
                if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                    omView.setContents(zYEntity.getDaList());
                    omView.setVisibility(View.GONE);
                    if(zYEntity.getDaList().get(0).getStr()!=null){
                        if(!zYEntity.getDaList().get(0).getStr().equals("")){
                            omView.setVisibility(View.VISIBLE);
                        }
                    }
                    ll_wd_layout.addView(omView);
                }

            }
        }
        if ("2".equals(type)) {
            initJPriceData(jMap);
        }
        for (int i = 0; i < ll_wd_layout.getChildCount(); i++) {
            String tag = (String) ll_wd_layout.getTag();
            if (ll_wd_layout.getChildAt(i) instanceof OutlineSimpleView) {
                OutlineSimpleView osView = (OutlineSimpleView) ll_wd_layout.getChildAt(i);
                final int finalI = i;
                osView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = null;
                        if(isNew){
                            intent = new Intent(OutLineActivity.this, CommonMultiActivity1.class);
                        }else{
                            intent = new Intent(OutLineActivity.this, CommonMultiActivity.class);
                        }

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", list.get(finalI));
                        intent.putExtra("step", finalI);
                        intent.putExtras(bundle);
                        intent.putExtra("type", type);
                        startActivityForResult(intent, REQUESTCODE);
                    }
                });
            } else if (ll_wd_layout.getChildAt(i) instanceof OutlineMultiView) {
                OutlineMultiView omView = (OutlineMultiView) ll_wd_layout.getChildAt(i);
                final int finalI1 = i;
                omView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = null;
                        if(isNew){
                            intent = new Intent(OutLineActivity.this, CommonMultiActivity1.class);
                        }else{
                            intent = new Intent(OutLineActivity.this, CommonMultiActivity.class);
                        }
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", list.get(finalI1));
                        intent.putExtra("step", finalI1);
                        intent.putExtras(bundle);
                        intent.putExtra("type", type);
                        startActivityForResult(intent, REQUESTCODE);
                    }
                });

            }
        }
    }

    private void ParseTyglData(String response) {
        try {
            Log.i("ty_response", response);

            JSONObject jsonObject = new JSONObject(response);
            int status = jsonObject.optInt("status");
            if (status == 200) {
                JSONObject dataObject = jsonObject.optJSONObject("data");
                if (dataObject != null) {
                    JSONObject m1Object = dataObject.optJSONObject("1");
                    if (m1Object != null) {
                        bindData(m1Object, "m1Object");
                    }
                    JSONObject m2Object = dataObject.optJSONObject("2");
                    if (m2Object != null) {
                        bindData(m2Object, "m2Object");
                    }
                    JSONObject m3Object = dataObject.optJSONObject("3");
                    if (m3Object != null) {
                        bindData(m3Object, "m3Object");
                    }
                    JSONObject m4Object = dataObject.optJSONObject("4");
                    if (m4Object != null) {
                        bindData(m4Object, "m4Object");
                    }
                    JSONObject m5Object = dataObject.optJSONObject("5");
                    if (m5Object != null) {
                        bindData(m5Object, "m5Object");
                    }
                    JSONObject m6Object = dataObject.optJSONObject("6");
                    if (m6Object != null) {
                        bindData(m6Object, "m6Object");
                    }
                    JSONObject m7Object = dataObject.optJSONObject("7");
                    if (m7Object != null) {
                        bindData(m7Object, "m7Object");
                    }
                    JSONObject m8Object = dataObject.optJSONObject("8");
                    if (m8Object != null) {
                        bindData(m8Object, "m8Object");
                    }
                    JSONObject m9Object = dataObject.optJSONObject("9");
                    if (m9Object != null) {
                        bindData(m9Object, "m9Object");
                    }
                    JSONObject m10Object = dataObject.optJSONObject("10");
                    if (m10Object != null) {
                        bindData(m10Object, "m10Object");
                    }
                    JSONObject m11Object = dataObject.optJSONObject("11");
                    if (m11Object != null) {
                        bindData(m11Object, "m11Object");
                    }
                    JSONObject m12Object = dataObject.optJSONObject("12");
                    if (m12Object != null) {
                        bindData(m12Object, "m12Object");
                    }
                }

            }
            bindDataView(y_map);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void bindData(JSONObject mObject, String str) {
        TyglEntity tyglEntity = new TyglEntity();
        tyglEntity.setNum(mObject.optString("num"));
        tyglEntity.setPrice(mObject.optString("price"));
        tyglEntity.setType(mObject.optString("type"));
        y_map.put(str, tyglEntity);
    }

    private void bindDataView(Map<String, TyglEntity> ymap) {
        TyglEntity t1 = ymap.get("m1Object");
        TyglEntity t2 = ymap.get("m2Object");
        TyglEntity t3 = ymap.get("m3Object");
        TyglEntity t4 = ymap.get("m4Object");
        TyglEntity t5 = ymap.get("m5Object");
        TyglEntity t6 = ymap.get("m6Object");
        TyglEntity t7 = ymap.get("m7Object");
        TyglEntity t8 = ymap.get("m8Object");
        TyglEntity t9 = ymap.get("m9Object");
        TyglEntity t10 = ymap.get("m10Object");
        TyglEntity t11 = ymap.get("m11Object");
        TyglEntity t12 = ymap.get("m12Object");
        if (t1 != null || t2 != null || t3 != null || t4 != null || t5 != null ||
                t6 != null || t7 != null || t8 != null || t9 != null || t10 != null ||
                t11 != null || t12 != null) {
            llnote.setVisibility(View.VISIBLE);
        }
        if (t1 != null) {
            StringBuilder sb1 = new StringBuilder();
            ll1yout.setVisibility(View.VISIBLE);
            String[] nums1 = t1.getNum().split(",");
            String price1 = t1.getPrice();
            String[] types1 = t1.getType().split(",");
            for (int i = 0; i < types1.length; i++) {
                if(nums1[i].equals("1")||nums1.equals("0")||nums1[i].equals("")){
                    sb1 = sb1.append(ParseTypesUtils.getOutlineTyglType(types1[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb1 = sb1.append(ParseTypesUtils.getOutlineTyglType(types1[i])).append("("+nums1[i]+"times)").append("\n");
                    }else{
                        sb1 = sb1.append(ParseTypesUtils.getOutlineTyglType(types1[i])).append("("+nums1[i]+"次)").append("\n");
                    }

                }

            }
            if (sb1.toString().endsWith("\n")) {
                sb1 = sb1.deleteCharAt(sb1.lastIndexOf("\n"));
                tv_c_1.setText(sb1.toString());
            }
            tv_m_1.setText("$" + price1);
        } else {
            ll1yout.setVisibility(View.GONE);
        }
        if (t2 != null) {
            StringBuilder sb2 = new StringBuilder();
            ll2yout.setVisibility(View.VISIBLE);
            String[] nums2 = t2.getNum().split(",");
            String price2 = t2.getPrice();
            String[] types2 = t2.getType().split(",");
            for (int i = 0; i < types2.length; i++) {

                if(nums2[i].equals("1")||nums2[i].equals("0")||nums2[i].equals("")){
                    sb2 = sb2.append(ParseTypesUtils.getOutlineTyglType(types2[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb2 = sb2.append(ParseTypesUtils.getOutlineTyglType(types2[i])).append("("+nums2[i]+"times)").append("\n");
                    }else{
                        sb2 = sb2.append(ParseTypesUtils.getOutlineTyglType(types2[i])).append("("+nums2[i]+"次)").append("\n");
                    }

                }


//                sb2 = sb2.append(ParseTypesUtils.getOutlineTyglType(types2[i])).append(nums2[i]).append("\n");
            }
            if (sb2.toString().endsWith("\n")) {
                sb2 = sb2.deleteCharAt(sb2.lastIndexOf("\n"));
                tv_c_2.setText(sb2.toString());
            }
            tv_m_2.setText("$" + price2);
        } else {
            ll2yout.setVisibility(View.GONE);
        }
        if (t3 != null) {
            StringBuilder sb3 = new StringBuilder();
            ll3yout.setVisibility(View.VISIBLE);
            String[] nums3 = t3.getNum().split(",");
            String price3 = t3.getPrice();
            String[] types3 = t3.getType().split(",");
            for (int i = 0; i < types3.length; i++) {

                if(nums3[i].equals("1")||nums3[i].equals("0")||nums3[i].equals("")){
                    sb3 = sb3.append(ParseTypesUtils.getOutlineTyglType(types3[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb3 = sb3.append(ParseTypesUtils.getOutlineTyglType(types3[i])).append("("+nums3[i]+"times)").append("\n");
                    }else{
                        sb3 = sb3.append(ParseTypesUtils.getOutlineTyglType(types3[i])).append("("+nums3[i]+"次)").append("\n");
                    }

                }

//                sb3 = sb3.append(ParseTypesUtils.getOutlineTyglType(types3[i])).append(nums3[i]).append("\n");
            }
            if (sb3.toString().endsWith("\n")) {
                sb3 = sb3.deleteCharAt(sb3.lastIndexOf("\n"));
                tv_c_3.setText(sb3.toString());
            }
            tv_m_3.setText("$" + price3);
        } else {
            ll3yout.setVisibility(View.GONE);
        }
        if (t4 != null) {
            StringBuilder sb4 = new StringBuilder();
            ll4yout.setVisibility(View.VISIBLE);
            String[] nums4 = t4.getNum().split(",");
            String price4 = t4.getPrice();
            String[] types4 = t4.getType().split(",");
            for (int i = 0; i < types4.length; i++) {

                if(nums4[i].equals("1")||nums4[i].equals("0")||nums4[i].equals("")){
                    sb4 = sb4.append(ParseTypesUtils.getOutlineTyglType(types4[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb4 = sb4.append(ParseTypesUtils.getOutlineTyglType(types4[i])).append("("+nums4[i]+"times)").append("\n");
                    }else{
                        sb4 = sb4.append(ParseTypesUtils.getOutlineTyglType(types4[i])).append("("+nums4[i]+"次)").append("\n");
                    }

                }

//                sb4 = sb4.append(ParseTypesUtils.getOutlineTyglType(types4[i])).append(nums4[i]).append("\n");
            }
            if (sb4.toString().endsWith("\n")) {
                sb4 = sb4.deleteCharAt(sb4.lastIndexOf("\n"));
                tv_c_4.setText(sb4.toString());
            }
            tv_m_4.setText("$" + price4);
        } else {
            ll4yout.setVisibility(View.GONE);
        }
        if (t5 != null) {
            StringBuilder sb5 = new StringBuilder();
            ll5yout.setVisibility(View.VISIBLE);
            String[] nums5 = t5.getNum().split(",");
            String price5 = t5.getPrice();
            String[] types5 = t5.getType().split(",");
            for (int i = 0; i < types5.length; i++) {
                if(nums5[i].equals("1")||nums5[i].equals("0")||nums5[i].equals("")){
                    sb5 = sb5.append(ParseTypesUtils.getOutlineTyglType(types5[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb5 = sb5.append(ParseTypesUtils.getOutlineTyglType(types5[i])).append("("+nums5[i]+"times)").append("\n");
                    }else{
                        sb5 = sb5.append(ParseTypesUtils.getOutlineTyglType(types5[i])).append("("+nums5[i]+"次)").append("\n");
                    }

                }

//                sb5 = sb5.append(ParseTypesUtils.getOutlineTyglType(types5[i])).append(nums5[i]).append("\n");
            }
            if (sb5.toString().endsWith("\n")) {
                sb5 = sb5.deleteCharAt(sb5.lastIndexOf("\n"));
                tv_c_5.setText(sb5.toString());
            }
            tv_m_5.setText("$" + price5);
        } else {
            ll5yout.setVisibility(View.GONE);
        }
        if (t6 != null) {
            StringBuilder sb6 = new StringBuilder();
            ll6yout.setVisibility(View.VISIBLE);
            String[] nums6 = t6.getNum().split(",");
            String price6 = t6.getPrice();
            String[] types6 = t6.getType().split(",");
            for (int i = 0; i < types6.length; i++) {

                if(nums6[i].equals("1")||nums6[i].equals("0")||nums6[i].equals("")){
                    sb6 = sb6.append(ParseTypesUtils.getOutlineTyglType(types6[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb6 = sb6.append(ParseTypesUtils.getOutlineTyglType(types6[i])).append("("+nums6[i]+"times)").append("\n");
                    }else{
                        sb6 = sb6.append(ParseTypesUtils.getOutlineTyglType(types6[i])).append("("+nums6[i]+"次)").append("\n");
                    }

                }

//                sb6 = sb6.append(ParseTypesUtils.getOutlineTyglType(types6[i])).append(nums6[i]).append("\n");
            }
            if (sb6.toString().endsWith("\n")) {
                sb6 = sb6.deleteCharAt(sb6.lastIndexOf("\n"));
                tv_c_6.setText(sb6.toString());
            }
            tv_m_6.setText("$" + price6);
        } else {
            ll6yout.setVisibility(View.GONE);
        }
        if (t7 != null) {
            StringBuilder sb7 = new StringBuilder();
            ll7yout.setVisibility(View.VISIBLE);
            String[] nums7 = t7.getNum().split(",");
            String price = t7.getPrice();
            String[] types7 = t7.getType().split(",");
            for (int i = 0; i < types7.length; i++) {

                if(nums7[i].equals("1")||nums7[i].equals("0")||nums7[i].equals("")){
                    sb7 = sb7.append(ParseTypesUtils.getOutlineTyglType(types7[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb7 = sb7.append(ParseTypesUtils.getOutlineTyglType(types7[i])).append("("+nums7[i]+"times)").append("\n");
                    }else{
                        sb7 = sb7.append(ParseTypesUtils.getOutlineTyglType(types7[i])).append("("+nums7[i]+"次)").append("\n");
                    }

                }

//                sb7 = sb7.append(ParseTypesUtils.getOutlineTyglType(types7[i])).append(nums7[i]).append("\n");
            }
            if (sb7.toString().endsWith("\n")) {
                sb7 = sb7.deleteCharAt(sb7.lastIndexOf("\n"));
                tv_c_7.setText(sb7.toString());
            }
            tv_m_7.setText("$" + price);
        } else {
            ll7yout.setVisibility(View.GONE);
        }
        if (t8 != null) {
            StringBuilder sb8 = new StringBuilder();
            ll8yout.setVisibility(View.VISIBLE);
            String[] nums8 = t8.getNum().split(",");
            String price8 = t8.getPrice();
            String[] types8 = t8.getType().split(",");
            for (int i = 0; i < types8.length; i++) {

                if(nums8[i].equals("1")||nums8[i].equals("0")||nums8[i].equals("")){
                    sb8 = sb8.append(ParseTypesUtils.getOutlineTyglType(types8[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb8 = sb8.append(ParseTypesUtils.getOutlineTyglType(types8[i])).append("("+nums8[i]+"times)").append("\n");
                    }else{
                        sb8 = sb8.append(ParseTypesUtils.getOutlineTyglType(types8[i])).append("("+nums8[i]+"次)").append("\n");
                    }

                }


//                sb8 = sb8.append(ParseTypesUtils.getOutlineTyglType(types8[i])).append(nums8[i]).append("\n");
            }
            if (sb8.toString().endsWith("\n")) {
                sb8 = sb8.deleteCharAt(sb8.lastIndexOf("\n"));
                tv_c_8.setText(sb8.toString());
            }
            tv_m_8.setText("$" + price8);
        } else {
            ll8yout.setVisibility(View.GONE);
        }
        if (t9 != null) {
            StringBuilder sb9 = new StringBuilder();
            ll9yout.setVisibility(View.VISIBLE);
            String[] nums9 = t9.getNum().split(",");
            String price9 = t9.getPrice();
            String[] types9 = t9.getType().split(",");
            for (int i = 0; i < types9.length; i++) {

                if(nums9[i].equals("1")||nums9[i].equals("0")||nums9[i].equals("")){
                    sb9 = sb9.append(ParseTypesUtils.getOutlineTyglType(types9[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb9 = sb9.append(ParseTypesUtils.getOutlineTyglType(types9[i])).append("("+nums9[i]+"times)").append("\n");
                    }else{
                        sb9 = sb9.append(ParseTypesUtils.getOutlineTyglType(types9[i])).append("("+nums9[i]+"次)").append("\n");
                    }

                }

//                sb9 = sb9.append(ParseTypesUtils.getOutlineTyglType(types9[i])).append(nums9[i]).append("\n");
            }
            if (sb9.toString().endsWith("\n")) {
                sb9 = sb9.deleteCharAt(sb9.lastIndexOf("\n"));
                tv_c_9.setText(sb9.toString());
            }
            tv_m_9.setText("$" + price9);
        } else {
            ll9yout.setVisibility(View.GONE);
        }
        if (t10 != null) {
            StringBuilder sb10 = new StringBuilder();
            ll10yout.setVisibility(View.VISIBLE);
            String[] nums10 = t10.getNum().split(",");
            String price10 = t10.getPrice();
            String[] types10 = t10.getType().split(",");
            for (int i = 0; i < types10.length; i++) {

                if(nums10[i].equals("1")||nums10[i].equals("0")||nums10[i].equals("")){
                    sb10 = sb10.append(ParseTypesUtils.getOutlineTyglType(types10[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb10 = sb10.append(ParseTypesUtils.getOutlineTyglType(types10[i])).append("("+nums10[i]+"times)").append("\n");
                    }else{
                        sb10 = sb10.append(ParseTypesUtils.getOutlineTyglType(types10[i])).append("("+nums10[i]+"次)").append("\n");
                    }

                }

//                sb10 = sb10.append(ParseTypesUtils.getOutlineTyglType(types10[i])).append(nums10[i]).append("\n");
            }
            if (sb10.toString().endsWith("\n")) {
                sb10 = sb10.deleteCharAt(sb10.lastIndexOf("\n"));
                tv_c_10.setText(sb10.toString());
            }
            tv_m_10.setText("$" + price10);
        } else {
            ll10yout.setVisibility(View.GONE);
        }
        if (t11 != null) {
            StringBuilder sb11 = new StringBuilder();
            ll11yout.setVisibility(View.VISIBLE);
            String[] nums11 = t11.getNum().split(",");
            String price11 = t11.getPrice();
            String[] types11 = t11.getType().split(",");
            for (int i = 0; i < types11.length; i++) {

                if(nums11[i].equals("1")||nums11[i].equals("0")||nums11[i].equals("")){
                    sb11 = sb11.append(ParseTypesUtils.getOutlineTyglType(types11[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb11 = sb11.append(ParseTypesUtils.getOutlineTyglType(types11[i])).append("("+nums11[i]+"times)").append("\n");
                    }else{
                        sb11 = sb11.append(ParseTypesUtils.getOutlineTyglType(types11[i])).append("("+nums11[i]+"次)").append("\n");
                    }

                }

//                sb11 = sb11.append(ParseTypesUtils.getOutlineTyglType(types11[i])).append(nums11[i]).append("\n");
            }
            if (sb11.toString().endsWith("\n")) {
                sb11 = sb11.deleteCharAt(sb11.lastIndexOf("\n"));
                tv_c_11.setText(sb11.toString());
            }
            tv_m_11.setText("$" + price11);
        } else {
            ll11yout.setVisibility(View.GONE);
        }
        if (t12 != null) {
            StringBuilder sb12 = new StringBuilder();
            ll12yout.setVisibility(View.VISIBLE);
            String[] nums12 = t12.getNum().split(",");
            String price12 = t12.getPrice();
            String[] types12 = t12.getType().split(",");
            Log.e("result--->",types12+"");
            for (int i = 0; i < types12.length; i++) {
                if(nums12[i].equals("1")||nums12[i].equals("0")||nums12[i].equals("")){
                    sb12 = sb12.append(ParseTypesUtils.getOutlineTyglType(types12[i])).append("\n");
                }else{
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        sb12 = sb12.append(ParseTypesUtils.getOutlineTyglType(types12[i])).append("("+nums12[i]+"times)").append("\n");
                    }else{
                        sb12 = sb12.append(ParseTypesUtils.getOutlineTyglType(types12[i])).append("("+nums12[i]+"次)").append("\n");
                    }

                }
//                sb12 = sb12.append(ParseTypesUtils.getOutlineTyglType(types12[i])).append(nums12[i]).append("\n");
            }
            if (sb12.toString().endsWith("\n")) {
                sb12 = sb12.deleteCharAt(sb12.lastIndexOf("\n"));
                tv_c_12.setText(sb12.toString());
            }
            tv_m_12.setText("$" + price12);
        } else {
            ll12yout.setVisibility(View.GONE);
        }
    }

    private void initTabView(View tabView) {
        tv_c_1 = (TextView) tabView.findViewById(R.id.tv_c_1);
        tv_c_2 = (TextView) tabView.findViewById(R.id.tv_c_2);
        tv_c_3 = (TextView) tabView.findViewById(R.id.tv_c_3);
        tv_c_4 = (TextView) tabView.findViewById(R.id.tv_c_4);
        tv_c_5 = (TextView) tabView.findViewById(R.id.tv_c_5);
        tv_c_6 = (TextView) tabView.findViewById(R.id.tv_c_6);
        tv_c_7 = (TextView) tabView.findViewById(R.id.tv_c_7);
        tv_c_8 = (TextView) tabView.findViewById(R.id.tv_c_8);
        tv_c_9 = (TextView) tabView.findViewById(R.id.tv_c_9);
        tv_c_10 = (TextView) tabView.findViewById(R.id.tv_c_10);
        tv_c_11 = (TextView) tabView.findViewById(R.id.tv_c_11);
        tv_c_12 = (TextView) tabView.findViewById(R.id.tv_c_12);

        tv_m_1 = (TextView) tabView.findViewById(R.id.tv_m_1);
        tv_m_2 = (TextView) tabView.findViewById(R.id.tv_m_2);
        tv_m_3 = (TextView) tabView.findViewById(R.id.tv_m_3);
        tv_m_4 = (TextView) tabView.findViewById(R.id.tv_m_4);
        tv_m_5 = (TextView) tabView.findViewById(R.id.tv_m_5);
        tv_m_6 = (TextView) tabView.findViewById(R.id.tv_m_6);
        tv_m_7 = (TextView) tabView.findViewById(R.id.tv_m_7);
        tv_m_8 = (TextView) tabView.findViewById(R.id.tv_m_8);
        tv_m_9 = (TextView) tabView.findViewById(R.id.tv_m_9);
        tv_m_10 = (TextView) tabView.findViewById(R.id.tv_m_10);
        tv_m_11 = (TextView) tabView.findViewById(R.id.tv_m_11);
        tv_m_12 = (TextView) tabView.findViewById(R.id.tv_m_12);


        llnote = ((LinearLayout) tabView.findViewById(R.id.ll_table_note));
        ll1yout = (LinearLayout) tabView.findViewById(R.id.ll1yout);
        ll2yout = (LinearLayout) tabView.findViewById(R.id.ll2yout);
        ll3yout = (LinearLayout) tabView.findViewById(R.id.ll3yout);
        ll4yout = (LinearLayout) tabView.findViewById(R.id.ll4yout);
        ll5yout = (LinearLayout) tabView.findViewById(R.id.ll5yout);
        ll6yout = (LinearLayout) tabView.findViewById(R.id.ll6yout);
        ll7yout = (LinearLayout) tabView.findViewById(R.id.ll7yout);
        ll8yout = (LinearLayout) tabView.findViewById(R.id.ll8yout);
        ll9yout = (LinearLayout) tabView.findViewById(R.id.ll9yout);
        ll10yout = (LinearLayout) tabView.findViewById(R.id.ll10yout);
        ll11yout = (LinearLayout) tabView.findViewById(R.id.ll11yout);
        ll12yout = (LinearLayout) tabView.findViewById(R.id.ll12yout);
    }

    private void initJPriceData(Map<String, String> jMap) {
        jMap.put("secret_key", SPUtils.getString(OutLineActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OutLineActivity.this, Contants.house_clean, jMap, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>价格" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    int data = JsonUtils.getJsonInt(response, "data");
                    bindData(data);
                    ParseTyglData(response);
                } else {
                    ToastUtil.longToast(OutLineActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void getNewData(Map<String, ZYEntity> map) {
        Map<String, String> ymap = new LinkedHashMap<>();
        int c = 0;
        for (Map.Entry mp : map.entrySet()) {
            ZYEntity zYEntity = (ZYEntity) mp.getValue();
            list.add(zYEntity);
            c++;
            String type2 = zYEntity.getType();
            if ("1".equals(type2)) {
                ymap.put("y_wen" + zYEntity.getStep(), zYEntity.getDaList().get(0).getStrId());
            } else if ("3".equals(type2)) {
                StringBuilder sb = new StringBuilder();
                if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                    for (int i = 0; i < zYEntity.getDaList().size(); i++) {
                        sb = sb.append(zYEntity.getDaList().get(i).getStrId()).append(',');
                    }
                    if (sb.length() > 0 && sb.toString().endsWith(",")) {
                        sb = sb.deleteCharAt(sb.lastIndexOf(","));
                    }
                    ymap.put("y_wen" + zYEntity.getStep(), sb.toString());
                }
            }
        }
        ymap.put("secret_key", SPUtils.getString(OutLineActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OutLineActivity.this, Contants.yard_management, ymap, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("庭院概要>>>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ParseTyglData(response);
                } else {
                    ToastUtil.longToast(OutLineActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void initForm(){

    }

    private void bindData(int data) {
        tv_month_money.setText("$" + data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        ToastUtil.longToast(OutLineActivity.this, "回调数据");
        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                Map<String, ZYEntity> map = ((SerializableMap) bundle.getSerializable("smap")).getMap();
                int b = 0;
                ZYEntity zYEntity = null;

                for (Map.Entry sp : map.entrySet()) {
                    b = Integer.valueOf((String) sp.getKey());
                    zYEntity = (ZYEntity) sp.getValue();
                }

                ll_wd_layout.removeViewAt(b);

                String type2 = zYEntity.getType();
                Log.i("type2Information", type2 + "---" + b+"++++"+zYEntity.getStep()+"--"+zYEntity.getDaList().get(0).getStr());
                if ("1".equals(type2)) {
                    OutlineSimpleView osView = new OutlineSimpleView(this);
                    osView.setTitle(zYEntity.getTitle());
                    osView.setContent(zYEntity.getDaList().get(0).getStr());

                    mSerializableap.getMap().put("step" + zYEntity.getStep(), zYEntity);
                    ll_wd_layout.addView(osView, b);

                } else if ("3".equals(type2)) {
                    OutlineMultiView omView = new OutlineMultiView(this);
                    omView.setTitle(zYEntity.getTitle());
                    if (zYEntity.getDaList() != null && zYEntity.getDaList().size() > 0) {
                        omView.setContents(zYEntity.getDaList());
                    }
//                    if(type.equals("3")){
//                        mSerializableap.getMap().put("step" + zYEntity.getStep(), zYEntity);
//                    }else{
                        mSerializableap.getMap().put("step" + zYEntity.getStep(), zYEntity);
//                    }
                    if ("2".equals(type)) {
                        jMap.put("j_wen" + b, zYEntity.getDaList().get(0).getStrId() == null ? "" : zYEntity.getDaList().get(0).getStrId());
                        initJPriceData(jMap);
                    }
                    ll_wd_layout.addView(omView, b);
                }

                if (ll_wd_layout.getChildAt(b) instanceof OutlineSimpleView) {
                    OutlineSimpleView osView = (OutlineSimpleView) ll_wd_layout.getChildAt(b);
                    final int finalI = b;
                    final ZYEntity finalZYEntity = zYEntity;
                    osView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            ToastUtil.longToast(OutLineActivity.this, finalI + "single");
                            Intent intent = null;
                            if(isNew){
                                intent = new Intent(OutLineActivity.this, CommonMultiActivity1.class);
                            }else{
                                intent = new Intent(OutLineActivity.this, CommonMultiActivity.class);
                            }
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("data", finalZYEntity);
                            intent.putExtra("step", finalI);
                            intent.putExtras(bundle);
                            intent.putExtra("type", type);
                            startActivityForResult(intent, REQUESTCODE);
                        }
                    });
                } else if (ll_wd_layout.getChildAt(b) instanceof OutlineMultiView) {
                    OutlineMultiView omView = (OutlineMultiView) ll_wd_layout.getChildAt(b);
                    final int finalI1 = b;
                    final ZYEntity finalZYEntity1 = zYEntity;
                    omView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            ToastUtil.longToast(OutLineActivity.this, finalI1 + "multi");
                            Intent intent = null;
                            if(isNew){
                                intent = new Intent(OutLineActivity.this, CommonMultiActivity1.class);
                            }else{
                                intent = new Intent(OutLineActivity.this, CommonMultiActivity.class);
                            }
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("data", finalZYEntity1);
                            intent.putExtra("step", finalI1);
                            intent.putExtras(bundle);
                            intent.putExtra("type", type);
                            startActivityForResult(intent, REQUESTCODE);
                        }
                    });
                }

                getNewData(mSerializableap.getMap());

            }
        }
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_outline_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.btn_hous_common_confirm) {

                String ystatic= SPUtils.getString(OutLineActivity.this, "ystatic_my", "");
                String zstatic= SPUtils.getString(OutLineActivity.this, "zstatic_my", "");
                String kystatic= SPUtils.getString(OutLineActivity.this, "kstatic_my", "");
                Log.i("isChangeaaaaaaaaa","--"+ystatic+"---"+getIntent().getStringExtra("zstatic")
                        +"----"+getIntent().getStringExtra("kstatic"));

                Intent intent = new Intent(OutLineActivity.this, EnvelopeActivity.class);
                intent.putExtra("type", type);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mSerializableap", mSerializableap);
                if (getIntent().getBooleanExtra("fromEnvelope", false)) {
                    intent.putExtra("fromEnvelope", true);

                    boolean isChange = isChange();
                    if(type.equals("3")){//庭院
                        if(isChange){
                            intent.putExtra("ystatic", "3");
                        }else{
                            intent.putExtra("ystatic", getIntent().getStringExtra("ystatic"));
                        }
                        intent.putExtra("zstatic", getIntent().getStringExtra("zstatic"));
                        intent.putExtra("kstatic", getIntent().getStringExtra("kstatic"));
                    }else if(type.equals("4")){//租赁
                        if(isChange){
                            intent.putExtra("zstatic", "4");
                        }else{
                            intent.putExtra("zstatic", getIntent().getStringExtra("zstatic"));
                        }
                        intent.putExtra("ystatic", getIntent().getStringExtra("ystatic"));
                        intent.putExtra("kstatic", getIntent().getStringExtra("kstatic"));
                    }else{
                        if(isChange){
                            intent.putExtra("kstatic", "3");
                        }else{
                            intent.putExtra("kstatic", getIntent().getStringExtra("kstatic"));
                        }
                        intent.putExtra("zstatic", getIntent().getStringExtra("zstatic"));
                        intent.putExtra("ystatic", getIntent().getStringExtra("ystatic"));
                    }

                }
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        }
    };

    private boolean isChange(){
        Map<String,ZYEntity> zyMap = wSerializableap.getMap();
        Map<String,ZYEntity> zwMap = mSerializableap.getMap();
        Iterator<String> it = zyMap.keySet().iterator();
        boolean isChage = false;
        while(it.hasNext()){
            String key = it.next();
            ZYEntity entity = zyMap.get(key);
            ZYEntity mEntity = zwMap.get(key);
//            Log.i("entityInformation",entity.toString()+"---"+mEntity.toString());
            if(!entity.toString().equals(mEntity.toString())){
                isChage = true;
                break;
            }
        }
        return isChage;
    }


}
