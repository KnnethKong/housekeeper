package com.haiwai.housekeeper.activity.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.NewHousEntity;
import com.haiwai.housekeeper.entity.ZYEntity;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SerializableMap;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.utils.VipWDUtils;
import com.haiwai.housekeeper.utils.WDUtils;
import com.haiwai.housekeeper.view.MultiItemView;
import com.haiwai.housekeeper.view.SingleItemView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.CustomDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConmonSingleActivity extends AppCompatActivity {
    private TopViewNormalBar top_single_bar;
    private SingleItemView sigle_common_view;
    private MultiItemView multi_common_view;
    private Button btn_hous_common_next;
    private TextView id_pre_step_hous;
    private LinearLayout ll_common_next_layou;
    private String type = "";
    private NewHousEntity mZyHousEntity;
    private SeekBar issue_addr_sb;
    private TextView issue_addr_tv_progress;
    private int mCount;
    int step = 1;
    private LinearLayout ll_type2_layout, ll_layout_single;
    private TextView tv_title_a, tv_unit, tv_title_b;
    private EditText et_single_input_price;
    private List<String> strList = new ArrayList<>();//多选
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conmon_single);
        top_single_bar = (TopViewNormalBar) findViewById(R.id.top_single_bar);
        top_single_bar.setOnBackListener(mOnClickListener);
        top_single_bar.setRightText(getString(R.string.tc));
        top_single_bar.setVisible(true);
        top_single_bar.setFinishListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        initView();
        initData(step);
    }

    private void initView() {
        type = getIntent().getStringExtra("type");
        if ("2".equals(type)) {
            top_single_bar.setTitle(getString(R.string.vip_fwbj));
        } else if ("3".equals(type)) {
            top_single_bar.setTitle(getString(R.string.vip_tygl));
        } else if ("4".equals(type)) {
            top_single_bar.setTitle(getString(R.string.vip_fwzl));
        } else if ("5".equals(type)) {
            top_single_bar.setTitle(getString(R.string.vip_kwgl));
        }

        ll_common_next_layou = (LinearLayout) findViewById(R.id.ll_common_next_layou);
        sigle_common_view = (SingleItemView) findViewById(R.id.sigle_common_view);
        multi_common_view = (MultiItemView) findViewById(R.id.multi_common_view);
        btn_hous_common_next = (Button) findViewById(R.id.btn_hous_common_next);
        btn_hous_common_next.setOnClickListener(mOnClickListener);
        id_pre_step_hous = (TextView) findViewById(R.id.id_pre_step_hous);
        id_pre_step_hous.setOnClickListener(mOnClickListener);
        issue_addr_sb = (SeekBar) findViewById(R.id.issue_addr_sb);
        issue_addr_tv_progress = (TextView) findViewById(R.id.issue_addr_tv_progress);

        ll_layout_single = (LinearLayout) findViewById(R.id.ll_layout_single);
        ll_layout_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_title_a.setSelected(true);
                tv_title_b.setSelected(false);
                et_single_input_price.setVisibility(View.VISIBLE);
                et_single_input_price.setFocusable(true);
                et_single_input_price.setFocusableInTouchMode(true);
                et_single_input_price.requestFocus();
                et_single_input_price.findFocus();


            }
        });
        ll_type2_layout = (LinearLayout) findViewById(R.id.ll_type2_layout);
        tv_title_a = (TextView) findViewById(R.id.tv_title_a);
        tv_unit = (TextView) findViewById(R.id.tv_unit);
        et_single_input_price = (EditText) findViewById(R.id.et_single_input_price);
        tv_title_b = (TextView) findViewById(R.id.tv_title_b);
        tv_title_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_title_a.setSelected(false);
                tv_title_b.setSelected(true);
                et_single_input_price.setText("");
                et_single_input_price.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        et_single_input_price = (EditText) findViewById(R.id.et_single_input_price);
    }

    private void initData(int i) {
        IMKitService.weekMap.clear();
//        Log.i("information", VipWDUtils.getBeans("step" + i).getProblem().get(0).getType2() + "--" + VipWDUtils.getBeans("step" + i).getType2());

        ll_common_next_layou.setVisibility(View.VISIBLE);
        issue_addr_sb.setMax(VipWDUtils.mCount);
        issue_addr_sb.setProgress(i);
        issue_addr_tv_progress.setText((int) (i * 10 / VipWDUtils.mCount) * 10 + "%");
      //  Log.i("type", VipWDUtils.getBeans("step" + i).getProblem().get(0).getType2() + "---" + VipWDUtils.getBeans("step" + i).getType2());
        if ("1".equals(VipWDUtils.getBeans("step" + i).getProblem().get(0).getType2()) && "1".equals(VipWDUtils.getBeans("step" + i).getType2())) {//单选
            sigle_common_view.setVisibility(View.VISIBLE);
            sigle_common_view.setAllSelectFalse();
            multi_common_view.setVisibility(View.GONE);
            ll_type2_layout.setVisibility(View.GONE);
            String desc = "招租是一次性服务，在确认需求后协助您通过最权威的渠道找到合适的租户(招租取费为1个月房租，在租赁协议签订后收取)";
//            String enDesc =
            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                sigle_common_view.setTitleName(VipWDUtils.getBeans("step" + i).getYvalue());
            } else {
                if("4".equals(type)){
                    sigle_common_view.setTitleName(VipWDUtils.getBeans("step" + i).getValue()+"\n"+desc);
                }else{
                    sigle_common_view.setTitleName(VipWDUtils.getBeans("step" + i).getValue());
                }
            }
            if (TextUtils.isEmpty(VipWDUtils.getBeans("step" + i).getRemark())) {
                sigle_common_view.setRemark("", false);
            } else {
                sigle_common_view.setRemark("", false);
//                sigle_common_view.setRemark(VipWDUtils.getBeans("step" + i).getRemark(), true);
            }
            sigle_common_view.setVisibleNum(VipWDUtils.getBeans("step" + i).getProblem().size());
            sigle_common_view.setItemName(VipWDUtils.getBeans("step" + i).getProblem(), isZhorEn);
        } else if ("3".equals(VipWDUtils.getBeans("step" + i).getType2()) && "1".equals(VipWDUtils.getBeans("step" + i).getProblem().get(0).getType2())) {//多选
            sigle_common_view.setVisibility(View.GONE);
            multi_common_view.setVisibility(View.VISIBLE);
            multi_common_view.setAllSelectFalse();
            ll_type2_layout.setVisibility(View.GONE);
            if (AppGlobal.getInstance().getLagStr().equals("en")) {
                multi_common_view.setTitleName(VipWDUtils.getBeans("step" + i).getYvalue());

            } else {
                multi_common_view.setTitleName(VipWDUtils.getBeans("step" + i).getValue());

            }
            if ("3".equals(type)) {
                if (i == 3) {
                    multi_common_view.setImVisible(true);
                    multi_common_view.getIv_th_red().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToastUtil.longToast(ConmonSingleActivity.this, "弹出计算表格");
                        }
                    });
                } else {
                    multi_common_view.setImVisible(false);
                }
            } else {
                multi_common_view.setImVisible(false);
            }
            if (TextUtils.isEmpty(VipWDUtils.getBeans("step" + i).getRemark())) {
                multi_common_view.setRemark("", false);
            } else {
                multi_common_view.setRemark("", false);
//                multi_common_view.setRemark(VipWDUtils.getBeans("step" + i).getRemark(), true);
            }
            multi_common_view.setVisibleNum(VipWDUtils.getBeans("step" + i).getProblem(), VipWDUtils.getBeans("step" + i).getProblem().size());
            multi_common_view.setItemName(VipWDUtils.getBeans("step" + i).getProblem(), isZhorEn);
        } else if ("2".equals(VipWDUtils.getBeans("step" + i).getProblem().get(0).getType2()) && "1".equals(VipWDUtils.getBeans("step" + i).getType2())) {//单选输入
            sigle_common_view.setVisibility(View.GONE);
            multi_common_view.setVisibility(View.GONE);
            ll_type2_layout.setVisibility(View.VISIBLE);
            tv_unit.setText(VipWDUtils.getBeans("step" + i).getProblem().get(0).getValue() + "]");
            tv_title_b.setText(VipWDUtils.getBeans("step" + i).getProblem().get(1).getValue());
        }

    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_single_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.btn_hous_common_next) {
                ZYEntity zyEntity = new ZYEntity();
                zyEntity.setType(VipWDUtils.getBeans("step" + step).getType2());
                zyEntity.setStep(1);
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    zyEntity.setTitle(VipWDUtils.getBeans("step" + step).getYvalue());
                } else {
                    zyEntity.setTitle(VipWDUtils.getBeans("step" + step).getValue());
                }

                zyEntity.setTittleId(VipWDUtils.getBeans("step" + step).getId());

                if ("3".equals(type)) {//庭院管理--含有多选
                    List<ZYEntity.DaBean> daList = new ArrayList<>();
                    if (multi_common_view.getSelectIdList() != null && multi_common_view.getSelectIdList().size() > 0) {
                        for (int i = 0; i < multi_common_view.getSelectIdList().size(); i++) {
                            ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                            daBean.setStrId(multi_common_view.getSelectIdList().get(i));
                            daBean.setStr(multi_common_view.getSelectList().get(i));
                            daList.add(daBean);
                        }
                    } else {
                        ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                        daBean.setStrId(sigle_common_view.getSelectItemId());
                        daBean.setStr(sigle_common_view.getSelectItem());
                        daList.add(daBean);
                    }
                    zyEntity.setDaList(daList);
                } else if ("4".equals(type)) {
                    List<ZYEntity.DaBean> daList = new ArrayList<>();
                    ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                    if (!TextUtils.isEmpty(et_single_input_price.getText().toString().trim())) {
                        SPUtils.saveString(ConmonSingleActivity.this, "want_money", et_single_input_price.getText().toString().trim());
                    }
                    if ("2".equals(VipWDUtils.getBeans("step" + step).getProblem().get(0).getType2())) {
                        if (!TextUtils.isEmpty(et_single_input_price.getText().toString().trim())) {
                            daBean.setStrId(VipWDUtils.getBeans("step" + step).getProblem().get(0).getId());
                            daBean.setStr(et_single_input_price.getText().toString().trim() + VipWDUtils.getBeans("step" + step).getProblem().get(0).getValue());
                        } else {
                            daBean.setStrId(VipWDUtils.getBeans("step" + step).getProblem().get(1).getId());
                            daBean.setStr(VipWDUtils.getBeans("step" + step).getProblem().get(1).getValue());
                        }
                    } else {
                        daBean.setStrId(sigle_common_view.getSelectItemId());
                        daBean.setStr(sigle_common_view.getSelectItem());
                    }
                    daList.add(daBean);
                    zyEntity.setDaList(daList);
                } else if ("5".equals(type)) {
                    List<ZYEntity.DaBean> daList = new ArrayList<>();
                    if (multi_common_view.getSelectIdList() != null && multi_common_view.getSelectIdList().size() > 0 && step == 4) {
                        for (int i = 0; i < multi_common_view.getSelectIdList().size(); i++) {
                            ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                            daBean.setStrId(multi_common_view.getSelectIdList().get(i));
                            daBean.setStr(multi_common_view.getSelectList().get(i));
                            daList.add(daBean);
                        }
                    } else {
                        ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                        daBean.setStrId(sigle_common_view.getSelectItemId());
                        daBean.setStr(sigle_common_view.getSelectItem());
                        daList.add(daBean);
                    }
                    zyEntity.setDaList(daList);
                } else {
                    List<ZYEntity.DaBean> daList = new ArrayList<>();
                    ZYEntity.DaBean daBean = new ZYEntity.DaBean();
                    daBean.setStrId(sigle_common_view.getSelectItemId());
                    daBean.setStr(sigle_common_view.getSelectItem());
                    daList.add(daBean);
                    zyEntity.setDaList(daList);
                }
                if (sigle_common_view.getSelectItemId().equals("") && multi_common_view.getSelectItemId().equals("")) {
                    ToastUtil.shortToast(ConmonSingleActivity.this, getString(R.string.select_at_last_one));
                    return;
                }
                if ("3".equals(type)) {
                    IMKitService.weekMap.put("step" + 1, zyEntity);
                } else {
                    IMKitService.weekMap.put("step" + step, zyEntity);
                }

                if ("4".equals(type)) {//房屋租赁or//庭院管理
                    if ("55".equals(IMKitService.weekMap.get("step1").getDaList().get(0).getStrId())) {
                        Intent intent = new Intent(ConmonSingleActivity.this, ConmonSingleBActivity.class);
                        intent.putExtra("type", type);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ConmonSingleActivity.this, ConmonSingleDActivity.class);
                        intent.putExtra("type", type);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(ConmonSingleActivity.this, ConmonSingleBActivity.class);
                    intent.putExtra("type", type);
                    startActivity(intent);
                }


            } else if (view.getId() == R.id.id_pre_step_hous) {//跳过
                SerializableMap sMap = new SerializableMap();
                for (int i = 0; i < VipWDUtils.mCount; i++) {
                    List<ZYEntity.DaBean> daList = new ArrayList<>();
                    ZYEntity zyEntity = new ZYEntity();
                    ZYEntity.DaBean bean = new ZYEntity.DaBean();
                    bean.setStrId(VipWDUtils.getBeans("step" + (i + 1)).getProblem().get(0).getId());
                    zyEntity.setType(VipWDUtils.getBeans("step" + (i + 1)).getType2());
                    if (AppGlobal.getInstance().getLagStr().equals("en")) {
                        zyEntity.setTitle(VipWDUtils.getBeans("step" + (i + 1)).getYvalue());
                        bean.setStr("Skipped");
                    } else {
                        zyEntity.setTitle(VipWDUtils.getBeans("step" + (i + 1)).getValue());
                        bean.setStr("已跳过");
                    }
                    zyEntity.setTittleId(VipWDUtils.getBeans("step" + (i + 1)).getId());
                    daList.add(bean);
                    zyEntity.setDaList(daList);
                    IMKitService.weekMap.put("step" + (i + 1), zyEntity);
                }
                sMap.setMap(IMKitService.weekMap);
                final Intent intent = new Intent(ConmonSingleActivity.this, OutLineActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", sMap);
                intent.putExtras(bundle);
                intent.putExtra("type", type);

                CustomDialog.Builder dialog = new CustomDialog.Builder(ConmonSingleActivity.this);
                dialog.setTitle(getString(R.string.app_tip)).setMessage(getString(R.string.skip_question))
                        .setPositiveButton(getString(R.string.message_alert_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                startActivity(intent);
                            }
                        }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
            } else if (view == top_single_bar.getFinishTextView()) {
                if (AppGlobal.getInstance().getIsHome()) {
                    IMKitService.weekMap.clear();
                    Intent intent = new Intent(ConmonSingleActivity.this, MainActivity.class);
                    intent.putExtra("flag", "ishome");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ConmonSingleActivity.this, EnvelopeActivity.class);
                    intent.putExtra("type", type);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mSerializableap", null);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }
    };
}
