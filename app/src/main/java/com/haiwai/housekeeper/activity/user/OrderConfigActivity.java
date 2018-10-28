package com.haiwai.housekeeper.activity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.OrderConfigEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.entity.ZYEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SerializableMap;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.ConfigView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.view.UiSeeKBar;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderConfigActivity extends AppCompatActivity {
    private TopViewNormalBar topCinfigBar;
    private LinearLayout vip_house_design_ll_addr;
    private LinearLayout ll_order_layout;
    private TextView iv_cz;
    private TextView tv_fwfzj, tv_dqyebz;
    private TextView tv_qbye, tv_month_zje;
    private EditText tv_zje;
    private UiSeeKBar ui_seek_bar;
    private SeekBar sb_fwxf;
    private List<Map<String, String>> strList = new ArrayList<>();
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
    private String uid = "";
    float count = 0;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_config);
        topCinfigBar = (TopViewNormalBar) findViewById(R.id.top_config_bar);
        topCinfigBar.setTitle(getString(R.string.ding_service));
        topCinfigBar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        nextMoney = 0;
        thisMoney = 0;
        initData();
    }

    private void initData() {
        LoadDialog.showProgressDialog(this);
        user = AppGlobal.getInstance().getUser();
        Map<String, String> mawp = new HashMap<>();
        mawp.put("uid", user.getUid());
        mawp.put("secret_key", SPUtils.getString(OrderConfigActivity.this, "secret", ""));
        mawp.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(OrderConfigActivity.this, Contants.self_dingzhi, mawp, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.print(">>>>>>>>>>>>>>>>>>" + response);
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    mOrderConfigEntity = new Gson().fromJson(response, OrderConfigEntity.class);
                    bindDataView(mOrderConfigEntity);
                } else {
                    ToastUtil.longToast(OrderConfigActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private boolean isDayMonth() {
        if (Calendar.DAY_OF_MONTH == 24) {
            CustomDialog.Builder builder = new CustomDialog.Builder(OrderConfigActivity.this);
            builder.setTitle(getString(R.string.tstitle)).setMessage(getString(R.string.can_not_change))
                    .setPositiveButton(getString(R.string.pop_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (MyApp.getTingtingApp().isLogin()) {
                                Information info = new Information();
                                info.setSysNum(Contants.SYSNUM);/* 必填 */
                                info.setInitModeType(1);//*****************************暂时之调用机器人-1
                                info.setArtificialIntelligence(false);//转为人工true
                                info.setArtificialIntelligenceNum(10);//只有人工true时起作用
                                info.setUseVoice(true);//使用语音
                                info.setColor("#FF0000");/* 选填，默认为"#09aeb0". 可以设置头部背景，提交评价背景，相似问题字体颜色和富文本类型中“阅读全文”字体颜色*/
                                info.setUid(AppGlobal.getInstance().getUser().getUid());/* 选填，设置用户唯一标识 */
                                info.setArtificialIntelligence(false);/* 智能转人工按钮，选填. 默认为false. 机器人客服优先模式时, false:显示转人工按钮；true:机器人有未知问题、引导回答时才显示转人工按钮 */
                                SobotApi.startSobotChat(OrderConfigActivity.this, info);
                            } else {
                                ActivityTools.goNextActivity(OrderConfigActivity.this, LoginActivity.class);
                                OrderConfigActivity.this.overridePendingTransition(R.anim.push_up_in,
                                        R.anim.push_up_out);
                            }
                        }
                    }).setNegativeButton(getString(R.string.pop_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
            return false;
        } else {
            return true;
        }
    }

    private double nextMoney = 0;
    private double thisMoney = 0;

    private void bindDataView(final OrderConfigEntity orderConfigEntity) {
        final List<OrderConfigEntity.DataBean.DateBean> date = orderConfigEntity.getData().getDate();
        StringBuilder sb = new StringBuilder();
        if (date != null && date.size() > 0) {
            ll_order_layout.removeAllViews();
            for (int i = 0; i < date.size(); i++) {
                ConfigView configView = new ConfigView(OrderConfigActivity.this);
                configView.setTexts(date.get(i).getAddress_info(), date.get(i).ben_money);
                configView.setState(date.get(i).getStaticX(), isZhorEn);
                if (date.get(i).is_xia.equals("1")) {
                    configView.setVisView(true);
                    nextMoney += Double.valueOf(date.get(i).getMoney());
                } else {
                    configView.setVisView(false);
                    thisMoney += Double.valueOf(date.get(i).ben_money);
                    if (date.get(i).is_que!=null) {
                        if(date.get(i).is_que.equals("1")) {
                            configView.setState(getString(R.string.enough_not_money));
                        }
                    } else {
                        if (date.get(i).getJ_static().equals("2") &&
                                date.get(i).getK_static().equals("2") &&
                                date.get(i).getY_static().equals("2") &&
                                date.get(i).getZ_static().equals("2")) {
                            configView.setState("执行中");
                        } else if (date.get(i).getJ_static().equals("1") &&
                                date.get(i).getK_static().equals("1") &&
                                date.get(i).getY_static().equals("1") &&
                                date.get(i).getZ_static().equals("1")) {
                            configView.setState("已暂停");
                        } else {
                            configView.setState("执行中，部分暂停");
                        }
                    }
                }
                final String h_id = date.get(i).getH_id();
                ll_order_layout.addView(configView);
                configView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SPUtils.saveBoolean(OrderConfigActivity.this,"is_h_id",true);
                        SPUtils.saveString(OrderConfigActivity.this,"h_id_str",h_id);
                        startActivity(new Intent(OrderConfigActivity.this, FwZdActivity.class));
                    }
                });
//                sb.append(date.get(i).getMoney()).append(",");
            }
            tv_fwfzj.setText("$" + String.format("%.2f", Double.parseDouble(date.get(0).getBen_money())));
            tv_next_fwfzj.setText("$" + String.format("%.2f", Double.parseDouble(date.get(0).getMoney())));

//            if (sb.toString().endsWith(",")) {
//                sb = sb.deleteCharAt(sb.lastIndexOf(","));
//            }
//            if (sb.toString().contains(",")) {
//                String[] strs = sb.toString().split(",");
//                if (strs.length > 0) {
//                    for (int i = 0; i < strs.length; i++) {
//                        count += Float.valueOf(strs[i]);
//                    }
//                }
////                tv_fwfzj.setText("$" + count);
//            } else {
//                count = Float.valueOf(sb.toString());
////                tv_fwfzj.setText("$" + count);
//            }
            tv_zje.setText(String.format("%.2f", nextMoney));
            tv_month_zje.setText("$" + String.format("%.2f", nextMoney));
        } else {
            ToastUtil.shortToast(OrderConfigActivity.this, getString(R.string.no_edit_view));
        }

        int num = 0;
        if (Float.valueOf(count) != 0) {
            num = (int) (Float.valueOf(orderConfigEntity.getData().getBalance()) / Float.valueOf(count));
        }
        int mMonth = (int) (Double.valueOf(orderConfigEntity.getData().getBalance()) / nextMoney);
        tv_qbye.setText("$" + orderConfigEntity.getData().getBalance() + "");
        ui_seek_bar.setProgress(mMonth);
        ui_seek_bar.setNumText(orderConfigEntity.getData().getBalance());
//        ui_seek_bar.setNumbackground(R.mipmap.seekbar_thumb2);
        if (mMonth == 0) {
            tv_dqyebz.setText(getString(R.string.tv_ye_des));
        } else {
            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                if (mMonth >= 12) {
                    tv_dqyebz.setText("The current balance is enough to cover the service over" + 1 + " year");
                } else {
                    tv_dqyebz.setText("The current balance is enough to cover the service" + mMonth + "month");
                }

            } else {
                if (mMonth >= 12) {
                    tv_dqyebz.setText("当前月余额可以支付超过1年的服务费用");

                } else {
                    tv_dqyebz.setText("当前月余额可以支付超过" + mMonth + "个月的服务费用");

                }
            }

        }

        for (int i = 0; i < ll_order_layout.getChildCount(); i++) {
            ConfigView configView = (ConfigView) ll_order_layout.getChildAt(i);
            final int finalI = i;
            configView.getImgBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isDayMonth()) {
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
                    Intent intent = new Intent(OrderConfigActivity.this, EnvelopeActivity.class);
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
            });
        }
    }

    private TextView tv_next_fwfzj;
    private void initView() {
        vip_house_design_ll_addr = (LinearLayout) findViewById(R.id.vip_house_design_ll_addr);
        vip_house_design_ll_addr.setOnClickListener(mOnClickListener);
        ll_order_layout = (LinearLayout) findViewById(R.id.ll_order_layout);
        iv_cz = (TextView) findViewById(R.id.iv_cz);
        iv_cz.setOnClickListener(mOnClickListener);
        tv_next_fwfzj = ((TextView) findViewById(R.id.tv_next_fwfzj));
        tv_fwfzj = (TextView) findViewById(R.id.tv_fwfzj);
        tv_qbye = (TextView) findViewById(R.id.tv_qbye);
        tv_zje = (EditText) findViewById(R.id.tv_zje);
        sb_fwxf = (SeekBar) findViewById(R.id.sb_fwxf);


        ((TextView) findViewById(R.id.tv_service_word)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_word_this_month_service)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_word_next_month_service)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_balance_word)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_recharge_word)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        iv_cz.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_fwfzj.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_qbye.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        tv_next_fwfzj.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_ye_word)).setTypeface(MyApp.getTf(), Typeface.NORMAL);
        ((TextView) findViewById(R.id.tv_xu_word)).setTypeface(MyApp.getTf(), Typeface.NORMAL);


        tv_month_zje = (TextView) findViewById(R.id.tv_month_zje);
        ui_seek_bar = (UiSeeKBar) findViewById(R.id.ui_seek_bar);
        tv_dqyebz = (TextView) findViewById(R.id.tv_dqyebz);

        tv_dqyebz.setTypeface(MyApp.getTf(), Typeface.NORMAL);
        sb_fwxf.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_zje.setText(String.format("%.2f", nextMoney * i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == topCinfigBar.getBackView()) {
                if (IMKitService.isConfig) {
                    Intent intent = new Intent(OrderConfigActivity.this, MainActivity.class);
                    intent.putExtra("flag", "isconfig");
                    startActivity(intent);
                    finish();
                } else {
                    finish();
                }
            } else if (view.getId() == R.id.vip_house_design_ll_addr) {
                if (!isDayMonth()) {
                    return;
                }
//                Intent intent = new Intent(OrderConfigActivity.this, NewHousActivity.class);
                Intent intent = new Intent(OrderConfigActivity.this, VipNewHouseChooseActivity.class);
                intent.putExtra("isNew", true);
                intent.putExtra("isConfig", true);
                startActivity(intent);
            } else if (view.getId() == R.id.iv_cz) {
                Intent intent = new Intent(OrderConfigActivity.this, GoPayActivity.class);
                if(tv_zje.getText().toString().trim().replace("$", "").equals("")){
                    ToastUtil.shortToast(OrderConfigActivity.this, getString(R.string.no_money));
                    return;
                }
                intent.putExtra("money", tv_zje.getText().toString().toString());
                intent.putExtra("isX", true);
                startActivity(intent);
            }
        }
    };


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
            if (IMKitService.isConfig) {
                Intent intent = new Intent(OrderConfigActivity.this, MainActivity.class);
                intent.putExtra("flag", "isconfig");
                startActivity(intent);
                finish();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
