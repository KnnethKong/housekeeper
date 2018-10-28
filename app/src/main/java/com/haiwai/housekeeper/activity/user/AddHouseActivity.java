package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.CommonConfig;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.HomeEntity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/19.
 * 添加房产
 */
public class AddHouseActivity extends BaseActivity {
    private TextView tvtitle;
    private Button tvdone;
    private EditText etcity, etaddr, ethousetype, etlandarea, etbuiltarea, etgreenarea, etcementarea, etalternatecontact;
    PopupWindow popuxiala;
    private String[] cityArr = new String[]{};
    private String[] YcityArr = new String[]{};
    private Map<String, String> idmap = new HashMap<>();
    private Map<String, String> yidmap = new HashMap<>();

    private String flag = "";
    private String house_id;
    private HouseListEntity.DataBean dataBean;
    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_add_house, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.add_house_title), Color.parseColor("#FF3C3C3C"));
        tvtitle = (TextView) findViewById(R.id.add_house_tv_title);
        tvtitle.setText(Html.fromHtml(getString(R.string.add_house_title_1) + "<font color='#ff0000' size='17'>" + getString(R.string.add_house_title_2) + "</font>"));

        etcity = (EditText) findViewById(R.id.add_house_et_city);
        etaddr = (EditText) findViewById(R.id.add_house_et_addr);
        ethousetype = (EditText) findViewById(R.id.add_house_et_house_type);
        etlandarea = (EditText) findViewById(R.id.add_house_et_land_area);
        etbuiltarea = (EditText) findViewById(R.id.add_house_et_built_area);
        etgreenarea = (EditText) findViewById(R.id.add_house_et_green_area);
        etcementarea = (EditText) findViewById(R.id.add_house_et_cement_area);
        etalternatecontact = (EditText) findViewById(R.id.add_house_et_alternate_contact);

        etcity.setInputType(InputType.TYPE_NULL);
        etcity.requestFocus();

        etcity.setOnClickListener(this);
        tvdone = (Button) findViewById(R.id.add_house_tv_done);
        tvdone.setOnClickListener(this);

        flag = getIntent().getExtras().get("flag").toString();

        if ("edit".equals(flag)) {
            setTitle(getString(R.string.add_house_edit_title), Color.parseColor("#FF3C3C3C"));
            tvdone.setText(getString(R.string.save));
            house_id = getIntent().getExtras().get("house_id").toString();
            dataBean = (HouseListEntity.DataBean) getIntent().getSerializableExtra("bean");
            if (dataBean != null) {
                requestCity(0);
                initEdit();
            }
        } else if ("add".equals(flag)) {
            tvdone.setText(getString(R.string.done));
            requestCity(1);
        }
    }

    private void initEdit() {
//        etaddr.setText(dataBean.getAddress_info());
//        ethousetype.setText(dataBean.getHousing_type());
//        etlandarea.setText(dataBean.getLand_area());
//        etbuiltarea.setText(dataBean.getBuilt_area());
//        etgreenarea.setText(dataBean.getGreen_area());
//        etcementarea.setText(dataBean.getCement_area());
//        etalternatecontact.setText(dataBean.getAlternate_contact());
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
    }

    /*
    *下拉框弹出菜单
     */
    private void popuwindow_xiala(View v, final String[] da, final EditText text) {
        TextView quxiao, queren;
        final NumberPicker picker;
        View contentView = getLayoutInflater().from(getApplicationContext()).inflate(R.layout.xiala_popu, null);
        quxiao = (TextView) contentView.findViewById(R.id.popu_quxiao);
        queren = (TextView) contentView.findViewById(R.id.popu_queren);
        picker = (NumberPicker) contentView.findViewById(R.id.picker);
        popuxiala = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        int height = this.getWindowManager().getDefaultDisplay().getHeight();
        popuxiala.setHeight(height * 3 / 10);
        popuxiala.setTouchable(true);
        popuxiala.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.i("mengdd", "onTouch : ");
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuxiala.dismiss();
            }
        });
        picker.setDisplayedValues(da);
        picker.setMinValue(0);
        picker.setMaxValue(da.length - 1);
        picker.setValue(0);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(this.getResources().getColor(R.color.light_gray)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

//        picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text.setText(da[picker.getValue()].toString());
//                popuxiala.dismiss();
//            }
//        });
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(da[picker.getValue()].toString());
//                if (!PlatUtils.getTextViewStr(text)) {
//                    if (text.getId() == R.id.change_user_nation) {
//                        Map<String, String> map = new HashMap<>();
//                        map.put("token", "96e1101cacf991bf94f7ce2342e222b8");
//                        map.put("user_id", mApp.getUser_id());
//                        map.put("para_name", "nation");
//                        map.put("para_val", text.getText().toString().trim());
//                        mRequestQueue.add(new PlatRequest(ChangeInfoActivity.this, Contants.changeMyInfo, map, null, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                if (JsonUtils.getJsonInt(response, "code") == 0) {
//                                    ToastUtil.shortToast(ChangeInfoActivity.this, "保存成功");
//                                } else {
//                                    ToastUtil.shortToast(ChangeInfoActivity.this, JsonUtils.getJsonStr(response, "desc"));
//                                }
//                            }
//                        }));
//                    }
//                }
                popuxiala.dismiss();
            }
        });


        popuxiala.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        // 设置好参数之后再show
        popuxiala.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.6f;
        this.getWindow().setAttributes(lp);
        popuxiala.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

    }

    private CityEntity cityEntity;

    /**
     * 城市列表
     */
    private void requestCity(final int type) {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(AddHouseActivity.this, "secret", ""));
        mRequestQueue.add(new PlatRequest(this, Contants.city_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    cityEntity = new Gson().fromJson(response, CityEntity.class);
//                    List<CityEntity.DataBean> data = cityEntity.getData();
//                    List<String> cityList = new ArrayList<>();
//                    List<String> YcityList = new ArrayList<>();
//                    for (int i = 0; i < data.size(); i++) {
//                        cityList.add(data.get(i).getName());
//                        YcityList.add(data.get(i).getYname());
//                        idmap.put(data.get(i).getId(), data.get(i).getName());
//                        yidmap.put(data.get(i).getId(), data.get(i).getYname());
//                    }
//                    cityArr = new String[cityList.size()];
//                    YcityArr = new String[YcityList.size()];
//                    cityList.toArray(cityArr);
//                    YcityList.toArray(YcityArr);
//                    popuwindow_xiala(v, cityArr, etcity);
                    if (type == 0)
                        etcity.setText(JsonUtils.getCityZhName(cityEntity, dataBean.getCity()));
                } else {
                    ToastUtil.shortToast(AddHouseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    /**
     * 添加房产
     */
    private void requestAddHouse() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        //// TODO: 2016/11/24 判断中英文语言环境
        map.put("city", JsonUtils.selectZhCityid(idmap, etcity.getText().toString().trim()));
        map.put("housing_type", ethousetype.getText().toString().trim());
        map.put("address_info", etaddr.getText().toString().trim());
        map.put("lat", "116.4");
        map.put("long", "39.9");
        map.put("land_area", etlandarea.getText().toString().trim());
        map.put("built_area", etbuiltarea.getText().toString().trim());
        map.put("green_area", etgreenarea.getText().toString().trim());
        map.put("cement_area", etcementarea.getText().toString().trim());
        map.put("alternate_contact", etalternatecontact.getText().toString().trim());
        map.put("secret_key", SPUtils.getString(AddHouseActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.hous_add, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtil.e("zc", response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.commit_success));
                    setResult(RESULT_OK);
                    finish();
                } else {
                    ToastUtil.shortToast(AddHouseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    /**
     * 修改房产
     */
    private void requestEditHouse() {
        Map<String, String> map = new HashMap<>();
        map.put("id", house_id);
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        //// TODO: 2016/11/24 判断中英文语言环境
        map.put("city", JsonUtils.selectZhCityid(idmap, etcity.getText().toString().trim()));
        map.put("housing_type", ethousetype.getText().toString().trim());
        map.put("address_info", etaddr.getText().toString().trim());
        map.put("lat", "39.9");
        map.put("long", "116.4");

        map.put("land_area", etlandarea.getText().toString().trim());
        map.put("built_area", etbuiltarea.getText().toString().trim());
        map.put("green_area", etgreenarea.getText().toString().trim());
        map.put("cement_area", etcementarea.getText().toString().trim());
        map.put("alternate_contact", etalternatecontact.getText().toString().trim());
        map.put("secret_key", SPUtils.getString(AddHouseActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.hous_save, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.commit_success));
                    setResult(RESULT_OK);
                    finish();
                } else {
                    ToastUtil.shortToast(AddHouseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    @Override
    protected void click(final View v) {
        switch (v.getId()) {
            case R.id.add_house_tv_done://确定
                if (!isNetworkAvailable()) {
                    ToastUtil.shortToast(AddHouseActivity.this, CommonConfig.NO_NETWORK);
                    return;
                }
                if (PlatUtils.getEditStr(etcity)) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.add_house_hint_1));
                    return;
                }

                if (PlatUtils.getEditStr(etaddr)) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.add_house_hint_2));
                    return;
                }
                if (PlatUtils.getEditStr(ethousetype)) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.add_house_hint_3));
                    return;
                }
                if (PlatUtils.getEditStr(etlandarea)) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.add_house_hint_4));
                    return;
                }
                if (PlatUtils.getEditStr(etbuiltarea)) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.add_house_hint_5));
                    return;
                }
                if (PlatUtils.getEditStr(etgreenarea)) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.add_house_hint_6));
                    return;
                }
                if (PlatUtils.getEditStr(etcementarea)) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.add_house_hint_7));
                    return;
                }
                if (PlatUtils.getEditStr(etalternatecontact)) {
                    ToastUtil.shortToast(AddHouseActivity.this, getString(R.string.add_house_hint_8));
                    return;
                }
                if ("add".equals(flag)) {
                    requestAddHouse();
                } else {
                    requestEditHouse();
                }
                break;
            case R.id.add_house_et_city:
                if (cityArr.length > 0)
////                    /**
////                     * 城市列表
////                     */
//                    Map<String, String> map = new HashMap<>();
//                    mRequestQueue.add(new PlatRequest(this, Contants.city_lst, map, null, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            int code = JsonUtils.getJsonInt(response, "status");
//                            if (code == 200) {
//                                CityEntity cityEntity = new Gson().fromJson(response, CityEntity.class);
//                                List<CityEntity.DataBean> data = cityEntity.getData();
//                                List<String> cityList = new ArrayList<String>();
//                                List<String> YcityList = new ArrayList<String>();
//                                for (int i = 0; i < data.size(); i++) {
//                                    cityList.add(data.get(i).getName());
//                                    YcityList.add(data.get(i).getYname());
//                                }
//                                cityArr = new String[cityList.size()];
//                                YcityArr = new String[YcityList.size()];
//                                cityList.toArray(cityArr);
//                                YcityList.toArray(YcityArr);
//                                popuwindow_xiala(v, cityArr, etcity);
//                            } else {
//                                ToastUtil.shortToast(AddHouseActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
//                            }
//                        }
//                    }));
//                } else {
                    popuwindow_xiala(v, cityArr, etcity);
//                }
                break;
        }
    }


}
