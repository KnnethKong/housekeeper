package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.VipPriceEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.SpanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/14.
 * 自营
 */
public class SelfSupportDetailActivity extends BaseActivity {
    private TextView tvcommit_order, tvcommit_garden, tvcommit_rent, tvcommit_house;
    private PopupWindow pop_order, pop_garden, pop_rent, pop_house;
    private ImageButton ib_jia_garden, ib_jian_garden, ib_jian_house, ib_jia_house;
    private TextView tvGardenNum, tvHouseNum;
    private int gardenNum = 1;
    private int houseNum = 1;
    private CheckBox tvrent_rent, tvmanage_rent;
    private TextView tvtitle, tvbottom;

    private String pos = "";
    private String from = "";

    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_sef_support_detail, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitlebarHide(true);
        RelativeLayout rl_bg = (RelativeLayout) findViewById(R.id.self_support_detail_rl_title_container);
        rl_bg.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, PlatUtils.getImageRario(this, 13, 16)));
        findViewById(R.id.self_support_detail_iv_back).setOnClickListener(this);
        findViewById(R.id.user_self_support_detail_ll_bottom).setOnClickListener(this);

        tvtitle = (TextView) findViewById(R.id.self_support_tv_title1);
        tvbottom = (TextView) findViewById(R.id.self_support_tv_bottom);
    }

    /**
     * 空屋管理
     */
    private void showPopWindowOrder() {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.self_support_pop_order, null);
        v.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_bottom_in));
        tvcommit_order = (TextView) v.findViewById(R.id.self_support_pop_order_commit);

        TextView tvtitle = (TextView) v.findViewById(R.id.pop_order_tv_title);
        // TODO: 2016/11/24 判断中英文
        if (!TextUtils.isEmpty(SPUtils.getVIPString(this, "self", "empty_price")))
            tvtitle.setText(getString(R.string.self_support_detail_kwgl) + SPUtils.getVIPString(this, "self", "empty_price") + getString(R.string.self_support_detail_mei_yue));
//        Spinner mSpinner = ((Spinner) v.findViewById(R.id.pop_order_sp_city));
//        Spinner builddingSpinner = ((Spinner) v.findViewById(R.id.pop_order_sp_build));
        TextView tvcity = (TextView) v.findViewById(R.id.pop_order_tv_city);
        TextView tvbuildstyle = (TextView) v.findViewById(R.id.pop_order_tv_build_style);

        String cityname = SPUtils.getVIPString(this, "self", "city_name");
        final String house_type = SPUtils.getVIPString(this, "self", "house_type");
        if (null != cityname)
            tvcity.setText(cityname);
        if (null != house_type)
            tvbuildstyle.setText(house_type);

        pop_order = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_order.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.4f);
        pop_order.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        pop_order.setOnDismissListener(new poponDismissListener());
//        getCityList(mSpinner, 2);
//        getHourseTypeList(builddingSpinner, 1);
        tvcommit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "k_static", "2");
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "z_type", house_type);
                if ("choose".equals(from)) {
                    Bundle kwgl_bundle = new Bundle();
                    kwgl_bundle.putString("flag", "kwgl");
                    ActivityTools.goNextActivity(SelfSupportDetailActivity.this, SelfSupportManageScheme.class, kwgl_bundle);
                } else {
                    Intent data = new Intent();
                    //携带的数据
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }

    /**
     * 园艺
     */
    private CheckBox pop_cb_snow_winter;

    private void showPopWindowGarden() {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.self_support_pop_garden, null);
        v.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_bottom_in));
        v.findViewById(R.id.pop_is_snow_winter).setOnClickListener(this);
        pop_cb_snow_winter = ((CheckBox) v.findViewById(R.id.pop__cb_is_snow_winter));
        tvcommit_garden = (TextView) v.findViewById(R.id.self_support_pop_garden_tv_commit);
        tvGardenNum = (TextView) v.findViewById(R.id.self_support_pop_garden_tv_num);
        ib_jian_garden = (ImageButton) v.findViewById(R.id.self_support_pop_garden_iv_jian);
        ib_jia_garden = (ImageButton) v.findViewById(R.id.self_support_pop_garden_iv_jia);
//        Spinner mSpinner = ((Spinner) v.findViewById(R.id.pop_order_iv_1));
//        Spinner sizeSpinner = ((Spinner) v.findViewById(R.id.pop_order_size_1));
        TextView tvtitle = (TextView) v.findViewById(R.id.pop_garden_tv_title);
        // TODO: 2016/11/24 判断中英文
        if (!TextUtils.isEmpty(SPUtils.getVIPString(this, "self", "garden_price")))
            tvtitle.setText(getString(R.string.self_support_detail_yygl) + SPUtils.getVIPString(this, "self", "garden_price") + getString(R.string.self_support_detail_mei_yue));

        TextView tvcity = (TextView) v.findViewById(R.id.pop_garden_tv_city);
        TextView tvarea = (TextView) v.findViewById(R.id.pop_garden_tv_area);

        String cityname = SPUtils.getVIPString(this, "self", "city_name");
        final String area = SPUtils.getVIPString(this, "self", "land_area");
        if (null != cityname)
            tvcity.setText(cityname);
        if (null != area)
            tvarea.setText(area + "㎡");
        ib_jia_garden.setOnClickListener(this);
        ib_jian_garden.setOnClickListener(this);

        gardenNum = 1;
        tvGardenNum.setText(gardenNum + "");
        pop_garden = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_garden.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.4f);
        pop_garden.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        pop_garden.setOnDismissListener(new poponDismissListener());
//        getCityList(mSpinner, 1);
//        getAreaSizeList(sizeSpinner, 0);
        tvcommit_garden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "y_static", "2");
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "y_ci", gardenNum + "");
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "y_area", area);
                if ("choose".equals(from)) {
                    Bundle yygl_bundle = new Bundle();
                    yygl_bundle.putString("flag", "yygl");
                    ActivityTools.goNextActivity(SelfSupportDetailActivity.this, SelfSupportManageScheme.class, yygl_bundle);
                } else {
                    Intent data = new Intent();
                    //携带的数据
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }

    /**
     * 家政管理
     */
    private void showPopWindowHouse() {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.self_support_pop_house, null);
        v.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_bottom_in));
        tvcommit_house = (TextView) v.findViewById(R.id.self_support_pop_house_tv_commit);
        tvHouseNum = (TextView) v.findViewById(R.id.self_support_pop_house_tv_num);
//        Spinner mSpinner = ((Spinner) v.findViewById(R.id.pop_order_iv_1));
//        Spinner sizeSpinner = ((Spinner) v.findViewById(R.id.pop_order_size_1));
        TextView tvtitle = (TextView) v.findViewById(R.id.pop_house_tv_title);
        // TODO: 2016/11/24 判断中英文
        if (!TextUtils.isEmpty(SPUtils.getVIPString(this, "self", "house_price")))
            tvtitle.setText(getString(R.string.self_support_detail_jzgl) + SPUtils.getVIPString(this, "self", "house_price") + getString(R.string.self_support_detail_mei_yue));

        TextView tvcity = (TextView) v.findViewById(R.id.pop_house_tv_city);
        TextView tvarea = (TextView) v.findViewById(R.id.pop_house_tv_area);

        String cityname = SPUtils.getVIPString(this, "self", "city_name");
        final String area = SPUtils.getVIPString(this, "self", "built_area");
        if (null != cityname)
            tvcity.setText(cityname);
        if (null != area)
            tvarea.setText(area + "㎡");

        ib_jian_house = (ImageButton) v.findViewById(R.id.self_support_pop_house_iv_jian);
        ib_jia_house = (ImageButton) v.findViewById(R.id.self_support_pop_house_iv_jia);
        ib_jian_house.setOnClickListener(this);
        ib_jia_house.setOnClickListener(this);
        tvcommit_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "j_static", "2");
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "j_ci", houseNum + "");
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "j_area", area);
                if ("choose".equals(from)) {
                    Bundle jzfw_bundle = new Bundle();
                    jzfw_bundle.putString("flag", "jzfw");
                    ActivityTools.goNextActivity(SelfSupportDetailActivity.this, SelfSupportManageScheme.class, jzfw_bundle);
                } else {
                    Intent data = new Intent();
                    //携带的数据
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
        houseNum = 1;
        tvHouseNum.setText(houseNum + "");
        pop_house = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_house.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.4f);
        pop_house.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        pop_house.setOnDismissListener(new poponDismissListener());
//        getCityList(mSpinn//er, 3);
//        getAreaSizeList(sizeSpinner, 1);
    }

    /**
     * 租赁
     */
    private void showPopWindowRent() {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.self_support_pop_rent, null);
        v.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_bottom_in));
        v.findViewById(R.id.self_support_pop_rent_ll_rent).setOnClickListener(this);
        v.findViewById(R.id.self_support_pop_rent_ll_manage).setOnClickListener(this);
        tvrent_rent = (CheckBox) v.findViewById(R.id.self_support_pop_rent_tv_rent);
        tvmanage_rent = (CheckBox) v.findViewById(R.id.self_support_pop_rent_tv_manage);
        tvcommit_rent = (TextView) v.findViewById(R.id.self_support_pop_rent_tv_commit);
//        Spinner mSpinner = (Spinner) v.findViewById(R.id.pop_order_iv_1);
//        Spinner typeSpinner = ((Spinner) v.findViewById(R.id.vip_rent_sp_type));
        TextView tvtitle = (TextView) v.findViewById(R.id.pop_rent_tv_title);
        // TODO: 2016/11/24 判断中英文
        if (!TextUtils.isEmpty(SPUtils.getVIPString(this, "self", "rent_price")))
            tvtitle.setText(getString(R.string.self_support_detail_zlgl) + SPUtils.getVIPString(this, "self", "rent_price") + getString(R.string.self_support_detail_mei_yue));

        TextView tvcity = (TextView) v.findViewById(R.id.pop_rent_tv_city);
        TextView tvbuildstyle = (TextView) v.findViewById(R.id.pop_rent_tv_build_style);

        final String cityname = SPUtils.getVIPString(this, "self", "city_name");
        final String house_type = SPUtils.getVIPString(this, "self", "house_type");
        if (null != cityname)
            tvcity.setText(cityname);
        if (null != house_type)
            tvbuildstyle.setText(house_type);

        tvcommit_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String is_zhao = tvrent_rent.isChecked() == true ? "1" : "0";
                String is_guan = tvmanage_rent.isChecked() == true ? "1" : "0";
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "is_zhao", is_zhao);
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "is_guan", is_guan);
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "z_type", house_type);
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "z_city", SPUtils.getVIPString(SelfSupportDetailActivity.this, "self", "city"));
                if ("choose".equals(from)) {
                    Bundle fwzl_bundle = new Bundle();
                    fwzl_bundle.putString("flag", "fwzl");
                    ActivityTools.goNextActivity(SelfSupportDetailActivity.this, SelfSupportManageScheme.class, fwzl_bundle);
                } else {
                    Intent data = new Intent();
                    //携带的数据
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
        initRent(1);
        pop_rent = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_rent.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.4f);
        pop_rent.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        pop_rent.setOnDismissListener(new poponDismissListener());
//        getCityList(mSpinner, 0);
//        getHourseTypeList(typeSpinner, 0);
    }

//    /**
//     * 获得房屋类型列表
//     * 0-租赁，1-房屋管理
//     *
//     * @param spinner
//     * @param from
//     */
//
//    public void getHourseTypeList(Spinner spinner, final int from) {
//        final String[] type = new String[]{"公寓", "别墅", "小平房", "土房"};
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                TextView tv = ((TextView) view);
//                tv.setTextColor(Color.parseColor("#2AD7C9"));
//                tv.setTextSize(16);
//                if (from == 0) {
//                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "z_type", type[position]);
//                } else {
//                    //from=1  空屋管理
//                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "z_type", type[position]);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        spinner.setAdapter(new ArrayAdapter<>(SelfSupportDetailActivity.this, android.R.layout.simple_list_item_1, type));
//    }

//    /**
//     * 活动房屋面积大小的列表
//     * 0-园艺管理，1-家政服务
//     *
//     * @param spinner
//     * @param from
//     */
//
//    public void getAreaSizeList(final Spinner spinner, final int from) {
//        final String[] area = new String[]{"100㎡", "200㎡", "300㎡", "400㎡"};
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                TextView tv = ((TextView) view);
//                tv.setTextColor(Color.parseColor("#2AD7C9"));
//                tv.setTextSize(16);
//                if (from == 0) {
//                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "y_area", area[position]);
//                } else {
//                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "j_area", area[position]);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
////                parent.setSelection(0);
//            }
//        });
//
//        spinner.setAdapter(new ArrayAdapter<>(SelfSupportDetailActivity.this, android.R.layout.simple_list_item_1, area));
//    }

//    /**
//     * 获得城区列表
//     * 0-租赁，1-园艺管理，2-房屋管理，3-家政服务
//     *
//     * @param spinner
//     */
//    private CityEntity cityEntity;
//
//    public void getCityList(final Spinner spinner, final int from) {
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                TextView tv = ((TextView) view);
//                tv.setTextColor(Color.parseColor("#2AD7C9"));
//                tv.setTextSize(16);
//                if (from == 0) {
//                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "z_city", cityEntity.getData().get(position).getId());
//                } else if (from == 1) {
////                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "city", cityEntity.getData().get(position).getId());
//                } else if (from == 2) {
//                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "city", cityEntity.getData().get(position).getId());
//                } else {
//                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "HOUSER_MANAGER", "garden_manager_area", cityEntity.getData().get(position).getName());
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        mRequestQueue.add(new PlatRequest(SelfSupportDetailActivity.this, Contants.city_lst, null, null, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response != null) {
//                    cityEntity = new Gson().fromJson(response, CityEntity.class);
//                    String code = cityEntity.getStatus() + "";
//                    if ("200".equals(code)) {
//                        List<String> strCity = new ArrayList<>();
//                        for (int i = 0; i < cityEntity.getData().size(); i++) {
//                            strCity.add(cityEntity.getData().get(i).getName());
//                        }
//                        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(SelfSupportDetailActivity.this, android.R.layout.simple_list_item_1, strCity);
//                        spinner.setAdapter(cityAdapter);
//                    }
//                }
//            }
//        }));
//    }

    private void initRent(int i) {
        tvrent_rent.setSelected(i == 1 ? true : false);
        tvmanage_rent.setSelected(i == 2 ? true : false);
    }

    @Override
    protected void initData() {
        LogUtil.e("id", SPUtils.getVIPString(this, "self", "h_id"));
        pos = getIntent().getExtras().getString("pos");
        isZhorEn = AppGlobal.getInstance().getLagStr();
        tvtitle.setText(AssetsUtils.getSkillName(pos, isZhorEn));
        tvbottom.setText(AssetsUtils.getSkillName(pos, isZhorEn));
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(SelfSupportDetailActivity.this, "secret", ""));
        mRequestQueue.add(new PlatRequest(SelfSupportDetailActivity.this, Contants.self_money, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if ("200".equals(JsonUtils.getJsonStr(response, "status"))) {
                    VipPriceEntity priceEntity = new Gson().fromJson(response, VipPriceEntity.class);
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "garden_price", priceEntity.getData().getHorticulture());
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "rent_price", priceEntity.getData().getBai());
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "empty_price", priceEntity.getData().getVacancy());
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "self", "house_price", priceEntity.getData().getHome());
                    TextView tvprice = (TextView) findViewById(R.id.self_support_tv_bottom_price);
                    switch (pos) {
                        case "29"://空屋管理
                            tvprice.setText("$" + priceEntity.getData().getVacancy() + "/月");
                            break;
                        case "30"://园艺管理
                            tvprice.setText("$" + priceEntity.getData().getHorticulture() + "/月");
                            break;
                        case "31"://家政服务
                            tvprice.setText("$" + priceEntity.getData().getHome() + "/月");
                            break;
                        case "32"://租赁
                            tvprice.setText("$" + priceEntity.getData().getBai() + "/月");
                            break;
                    }
                }
            }
        }));
    }

    @Override
    protected void click(View v) {
        from = getIntent().getExtras().getString("from");
        switch (v.getId()) {
            case R.id.pop_is_snow_winter:
                if (pop_cb_snow_winter.isSelected()) {
                    LogUtil.i("pop_ll_snow_winter", pop_cb_snow_winter.isSelected() + "");
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "GARDEN_MANAGER", "garden_snow", "true");
                } else {
                    LogUtil.i("pop_ll_snow_winter", pop_cb_snow_winter.isSelected() + "");
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "GARDEN_MANAGER", "garden_snow", "false");
                }
                break;
            case R.id.self_support_detail_iv_back:
                finish();
                break;
            case R.id.user_self_support_detail_ll_bottom:
                if (mApp.isLogin()) {
                    switch (pos) {
                        case "29"://空屋管理
                            showPopWindowOrder();
                            break;
                        case "30"://园艺管理
                            showPopWindowGarden();
                            break;
                        case "31"://家政服务
                            showPopWindowHouse();
                            break;
                        case "32"://租赁
                            showPopWindowRent();
                            break;
                    }
                } else {
                    startActivity(new Intent(SelfSupportDetailActivity.this, LoginActivity.class));
                }
                break;
            case R.id.self_support_pop_order_commit://空屋管理下单按钮

                break;
            case R.id.self_support_pop_garden_tv_commit://园艺管理下单按钮

                break;
            case R.id.self_support_pop_rent_tv_commit://房屋租赁下单按钮

                break;
            case R.id.self_support_pop_house_tv_commit://家政服务下单按钮

                break;
            case R.id.self_support_pop_garden_iv_jian://园艺管理-按钮
                gardenNum = Integer.valueOf(tvGardenNum.getText().toString().trim());
                if (gardenNum > 0 && gardenNum != 1) {
                    gardenNum -= 1;
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "GARDEN_MANAGER", "garden_num", gardenNum + "");
                    tvGardenNum.setText(gardenNum + "");
                    ib_jian_garden.setImageResource(R.mipmap.icon_jian_red);
                    if (gardenNum == 1)
                        ib_jian_garden.setImageResource(R.mipmap.icon_jian_grey);
                }
                break;
            case R.id.self_support_pop_garden_iv_jia://园艺管理+按钮
                gardenNum = Integer.valueOf(tvGardenNum.getText().toString().trim());
                if (gardenNum > 0) {
                    gardenNum += 1;
                    tvGardenNum.setText(gardenNum + "");
                    ib_jian_garden.setImageResource(R.mipmap.icon_jian_red);
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "GARDEN_MANAGER", "garden_num", gardenNum + "");
                }
                break;
            case R.id.self_support_pop_house_iv_jian://家政管理-按钮
                houseNum = Integer.valueOf(tvHouseNum.getText().toString().trim());
                if (houseNum > 0 && houseNum != 1) {
                    houseNum -= 1;
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "HOUSER_MANAGER", "houser_manager_num", houseNum + "");
                    tvHouseNum.setText(houseNum + "");
                    ib_jian_house.setImageResource(R.mipmap.icon_jian_red);
                    if (houseNum == 1)
                        ib_jian_house.setImageResource(R.mipmap.icon_jian_grey);
                }
                break;
            case R.id.self_support_pop_house_iv_jia://家政管理+按钮
                houseNum = Integer.valueOf(tvHouseNum.getText().toString().trim());
                if (houseNum > 0) {
                    houseNum += 1;
                    SPUtils.saveVIPString(SelfSupportDetailActivity.this, "HOUSER_MANAGER", "houser_manager_num", houseNum + "");
                    tvHouseNum.setText(houseNum + "");
                    ib_jian_house.setImageResource(R.mipmap.icon_jian_red);
                }
                break;
            case R.id.self_support_pop_rent_ll_rent:
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "HOURSE_RENT", "service_type", "0");
                initRent(1);
                break;
            case R.id.self_support_pop_rent_ll_manage:
                SPUtils.saveVIPString(SelfSupportDetailActivity.this, "HOURSE_RENT", "service_type", "1");
                initRent(2);
                break;
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

}
