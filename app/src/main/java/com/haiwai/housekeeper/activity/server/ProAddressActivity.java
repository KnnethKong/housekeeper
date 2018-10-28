package com.haiwai.housekeeper.activity.server;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.user.NewHousActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.AddressEntity;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.CityLevelEntity;
import com.haiwai.housekeeper.entity.GeoEntity;
import com.haiwai.housekeeper.entity.LatLngEntity;
import com.haiwai.housekeeper.entity.SkillItemEntity;
import com.haiwai.housekeeper.entity.StarEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.CityUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.MySpinner;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.style.sources.GeoJsonOptions;
import com.mapbox.services.android.geocoder.ui.GeocoderAutoCompleteView;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.geocoding.v5.MapboxGeocoding;
import com.mapbox.services.geocoding.v5.models.CarmenFeature;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.haiwai.housekeeper.service.IMKitService.str;

public class ProAddressActivity extends BaseProActivity {
    private TopViewNormalBar top_address_show_bar;
    private MySpinner spinner, spinner1, spinner2;
    private EditText et_email, et_address, et_address_no;
    private Button btn_vip_addr_next;
    private CityEntity mCityEntity;
    private String isZhorEn = "";
    private ArrayList<CityLevelEntity> mCityLevelEntityList;
    private ArrayList<CityLevelEntity> mCityLevelEntityList1;
    private ArrayList<CityLevelEntity> mCityLevelEntityList2;
    MyAdapter mAdapter;
    private String country = "";
    private String province = "";
    private String city = "";
    private String id = "";
    private int mSelectedPosition = -1;
    private boolean isFisrt = false;
    Map<String, String> map = null;
    private String country_name = "";
    private String province_name = "";
    private String city_name = "";
    private String strAddr = "";
    private String zip_code = "";
    User user;
    AddressEntity mAddressEntity;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapboxAccountManager.start(ProAddressActivity.this, Contants.TOKENS);
        setContentView(R.layout.activity_pro_address);

        mapView = ((MapView) findViewById(R.id.new_house_mapview));
        mapView.setStyleUrl(Style.OUTDOORS);
        mapView.onCreate(savedInstanceState);


        top_address_show_bar = (TopViewNormalBar) findViewById(R.id.top_address_show_bar);
        top_address_show_bar.setTitle(getString(R.string.min_set_addr));
        top_address_show_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        top_address_show_bar.setVisible(true);
        top_address_show_bar.setRightText(getString(R.string.btn_set_btn_save));


        initView();
        initData();


        top_address_show_bar.setFinishListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savaLocation();
            }
        });

    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(ProAddressActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProAddressActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>>>>城市列表>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    mCityEntity = CityUtils.parseCity(response);
                    geCityData(mCityEntity);
                    initProData();
                } else {
                    initProData();
                    ToastUtil.longToast(ProAddressActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private void initProData() {
        user = AppGlobal.getInstance().getUser();
        map = new HashMap<>();
        LoadDialog.showProgressDialog(this);
        map.put("uid", user.getUid());
        map.put("secret_key", SPUtils.getString(ProAddressActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProAddressActivity.this, Contants.proservice_lst, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>>>>服务地址列表>>" + response);
                int code = JsonUtils.getJsonInt(response, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    mAddressEntity = new Gson().fromJson(response, AddressEntity.class);
                    if (mAddressEntity != null && mAddressEntity.getData().size() > 0) {
                        for (int j = 0; j < mAddressEntity.getData().size(); j++) {
                            if ("1".equals(mAddressEntity.getData().get(j).getIs_mo())) {
                                for (int i = 0; i < mCityLevelEntityList.size(); i++) {
                                    if (mCityLevelEntityList.get(i).getId().equals(mAddressEntity.getData().get(j).getCountry())) {
                                        if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                            spinner.setHint(mCityLevelEntityList.get(i).getYname());
                                        } else {
                                            spinner.setHint(mCityLevelEntityList.get(i).getName());
                                        }

                                        country = mCityLevelEntityList.get(i).getId();
                                    }
                                }
                            }
                        }
                    }
                    if (mAddressEntity != null && mAddressEntity.getData().size() > 0) {
                        for (int j = 0; j < mAddressEntity.getData().size(); j++) {
                            if ("1".equals(mAddressEntity.getData().get(j).getIs_mo())) {
                                for (int i = 0; i < mCityLevelEntityList1.size(); i++) {
                                    if (mCityLevelEntityList1.get(i).getId().equals(mAddressEntity.getData().get(j).getProvince())) {
                                        if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                            spinner1.setHint(mCityLevelEntityList1.get(i).getYname());
                                        } else {
                                            spinner1.setHint(mCityLevelEntityList1.get(i).getName());
                                        }
                                        province = mCityLevelEntityList1.get(i).getId();
                                    }
                                }
                            }
                        }
                    }
                    boolean isPos = false;
                    if (mAddressEntity != null && mAddressEntity.getData().size() > 0) {
                        for (int j = 0; j < mAddressEntity.getData().size(); j++) {
                            if ("1".equals(mAddressEntity.getData().get(j).getIs_mo())) {
                                for (int i = 0; i < mCityLevelEntityList2.size(); i++) {
                                    if (mCityLevelEntityList2.get(i).getId().equals(mAddressEntity.getData().get(j).getCity())) {
                                        if (AppGlobal.getInstance().getLagStr().equals("en")) {
                                            spinner2.setHint(mCityLevelEntityList2.get(i).getYname());
                                            city_name = mCityLevelEntityList2.get(i).getYname();
                                        } else {
                                            spinner2.setHint(mCityLevelEntityList2.get(i).getName());
                                            city_name = mCityLevelEntityList2.get(i).getYname();
                                        }
                                        isPos = true;
                                        city = mCityLevelEntityList2.get(i).getId();
                                        break;
                                    }
                                }
                            }
                            if(isPos){
                                break;
                            }
                        }

                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    ToastUtil.longToast(ProAddressActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
                if (mAddressEntity != null && mAddressEntity.getData().size() > 0) {
                    for (int j = 0; j < mAddressEntity.getData().size(); j++) {
                        if ("1".equals(mAddressEntity.getData().get(j).getIs_mo())) {
                            et_address.setText(mAddressEntity.getData().get(j).street);
                            et_address_no.setText(mAddressEntity.getData().get(j).house_number);
                            et_email.setText(mAddressEntity.getData().get(j).getZip_code());
                            initLocation(mAddressEntity.getData().get(j).getLat()+""
                                    , mAddressEntity.getData().get(j).getLongX()+""
                                    , mAddressEntity.getData().get(j).getAddress());
                            break;
                        }
                    }
                }
            }
        }));
    }

    private void initLocation(final String latitude, final String longitude, final String addr) {

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapBox) {//35.6184395336,112.2816432607
                if(mapBox.getMarkers()!=null&&mapBox.getMarkers().size()>0){
                    for(int i=0;i<mapBox.getMarkers().size();i++){
                        mapBox.removeMarker(mapBox.getMarkers().get(i));
                    }
                }
                mapBox.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)))
                        .title(addr));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)))
                        .zoom(15)
                        .build();
                mapBox.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000, null);
            }
        });
    }


    private void geCityData(CityEntity mCityEntity) {
        mCityLevelEntityList = CityUtils.getLevelList("1", id, mCityEntity);
        country = mCityLevelEntityList.get(0).getId();
        mAdapter = new MyAdapter<CityLevelEntity>(mCityLevelEntityList, R.layout.hous_adapter_text_item) {
            @Override
            public void bindView(ViewHolder holder, CityLevelEntity obj) {
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    holder.setText(R.id.tv_content, obj.getYname());
                } else {
                    holder.setText(R.id.tv_content, obj.getName());
                }

                country = mCityLevelEntityList.get(holder.getItemPosition()).getId();
            }
        };
        spinner.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
        mCityLevelEntityList1 = CityUtils.getLevelList("2", mCityLevelEntityList.get(0).getId(), mCityEntity);

        province = mCityLevelEntityList1.get(0).getId();
        mAdapter = new MyAdapter<CityLevelEntity>(mCityLevelEntityList1, R.layout.hous_adapter_text_item) {
            @Override
            public void bindView(ViewHolder holder, CityLevelEntity obj) {
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    holder.setText(R.id.tv_content, obj.getYname());
                } else {
                    holder.setText(R.id.tv_content, obj.getName());
                }
                province = mCityLevelEntityList1.get(holder.getItemPosition()).getId();
            }
        };
        spinner1.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mCityLevelEntityList2 = CityUtils.getLevelList("3", mCityLevelEntityList1.get(0).getId(), mCityEntity);
        city = mCityLevelEntityList2.get(0).getId();
        mAdapter = new MyAdapter<CityLevelEntity>(mCityLevelEntityList2, R.layout.hous_adapter_text_item) {
            @Override
            public void bindView(ViewHolder holder, CityLevelEntity obj) {
                if (AppGlobal.getInstance().getLagStr().equals("en")) {
                    holder.setText(R.id.tv_content, obj.getYname());
                } else {
                    holder.setText(R.id.tv_content, obj.getName());
                }

                city = mCityLevelEntityList2.get(holder.getItemPosition()).getId();
            }
        };
        spinner2.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        if (AppGlobal.getInstance().getLagStr().equals("en")) {
            spinner.setHint(mCityLevelEntityList.get(0).getYname());
            spinner1.setHint(mCityLevelEntityList1.get(0).getYname());
            spinner2.setHint(mCityLevelEntityList2.get(0).getYname());
        } else {
            spinner.setHint(mCityLevelEntityList.get(0).getName());
            spinner1.setHint(mCityLevelEntityList1.get(0).getName());
            spinner2.setHint(mCityLevelEntityList2.get(0).getName());
        }
    }


    private void initView() {
        et_address_no = ((EditText) findViewById(R.id.et_address_no));
        spinner = (MySpinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.dismissPop();
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    spinner.setHint(mCityLevelEntityList.get(i).getYname());
                    country_name = mCityLevelEntityList.get(i).getYname();
                }else{
                    spinner.setHint(mCityLevelEntityList.get(i).getName());
                    country_name = mCityLevelEntityList.get(i).getName();
                }

                country = mCityLevelEntityList.get(i).getId();

            }
        });
        spinner1 = (MySpinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                spinner1.dismissPop();
                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    spinner1.setHint(mCityLevelEntityList1.get(i).getYname());
                    province_name = mCityLevelEntityList1.get(i).getYname();
                }else{
                    spinner1.setHint(mCityLevelEntityList1.get(i).getName());
                    province_name = mCityLevelEntityList1.get(i).getName();
                }

                province = mCityLevelEntityList1.get(i).getId();

            }
        });
        spinner2 = (MySpinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                spinner2.dismissPop();

                if(AppGlobal.getInstance().getLagStr().equals("en")){
                    spinner2.setHint(mCityLevelEntityList2.get(i).getYname());
                    city_name = mCityLevelEntityList2.get(i).getYname();
                }else{
                    spinner2.setHint(mCityLevelEntityList2.get(i).getName());
                    city_name = mCityLevelEntityList2.get(i).getYname();
                }
                city = mCityLevelEntityList2.get(i).getId();
            }
        });
        et_email = (EditText) findViewById(R.id.et_email);
        et_address = (EditText) findViewById(R.id.et_address);
        btn_vip_addr_next = (Button) findViewById(R.id.btn_vip_addrss_next);
        btn_vip_addr_next.setOnClickListener(mOnClickListener);
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_address_show_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.btn_vip_addrss_next) {
//                ToastUtil.longToast(ProAddressActivity.this, ">>>>>>>>>>>>.");


            }
        }
    };


    private void savaLocation() {
        zip_code = et_email.getText().toString().trim();
        strAddr = et_address.getText().toString().trim();
        final String strAddrNo = et_address_no.getText().toString().trim();
        if (TextUtils.isEmpty(strAddr)) {
            ToastUtil.shortToast(ProAddressActivity.this, getString(R.string.input_street));
            return;
        }
        if (TextUtils.isEmpty(strAddrNo)) {
            ToastUtil.shortToast(ProAddressActivity.this, getString(R.string.input_number));
            return;
        }

        final String geoAdress = strAddrNo+"+" + strAddr+"+" + city_name;
        LoadDialog.showProgressDialog(ProAddressActivity.this);
        String url = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+geoAdress+"+BC+Canada.json?access_token=pk.eyJ1IjoibGl1eWoiLCJhIjoiY2l6amhjbWNwMDNucjMybWliaTQwNGl5YSJ9.HmoUCkUzAShnBGbRRkjk5g";

        Request request = new Request.Builder()
                .url(url)
                .build();
        new OkHttpClient().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String loaction = response.body().string();
                LatLngEntity entity = new Gson().fromJson(loaction,LatLngEntity.class);
                if(entity.getFeatures()!=null&&entity.getFeatures().size()>0){
                    LoadDialog.closeProgressDialog();
                    String lng = entity.getFeatures().get(0).getCenter().get(0)+"";
                    String lat = entity.getFeatures().get(0).getCenter().get(1)+"";
                    initLocation(lat,lng,city_name +" "+strAddr+" "+strAddrNo);
                    gotoNetData(lat,lng);
                }else{
                    String url2 = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+city_name+"+BC+Canada.json?access_token=pk.eyJ1IjoibGl1eWoiLCJhIjoiY2l6amhjbWNwMDNucjMybWliaTQwNGl5YSJ9.HmoUCkUzAShnBGbRRkjk5g";
                    getAddAgain(url2);
                }

            }
        });
    }

    private void getAddAgain(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        new OkHttpClient().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                LoadDialog.closeProgressDialog();
                String loaction = response.body().string();
                LatLngEntity entity = new Gson().fromJson(loaction,LatLngEntity.class);
                if(entity.getFeatures()!=null&&entity.getFeatures().size()>0){
                    String lng = entity.getFeatures().get(0).getCenter().get(0)+"";
                    String lat = entity.getFeatures().get(0).getCenter().get(1)+"";
                    gotoNetData(lat,lng);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    private void gotoNetData(String lat,String lng) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", TextUtils.isEmpty(user.getUid()) ? "" : user.getUid());
        map.put("secret_key", SPUtils.getString(ProAddressActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        map.put("country", country);
        map.put("province", province);
        map.put("city", city);
        map.put("street", strAddr);
        map.put("house_number", et_address_no.getText().toString());
        map.put("zip_code", zip_code);
        map.put("lat", lat);
        map.put("long", lng);
        if (!SPUtils.getBoolean(ProAddressActivity.this,"isHaveServiceAddress",false)) {
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProAddressActivity.this, Contants.proservice_add, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>." + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        SPUtils.saveBoolean(ProAddressActivity.this, "isHaveServiceAddress", true);
//                        ToastUtil.longToast(ProAddressActivity.this, "服务地址添加成功!");
                        if (getIntent().getBooleanExtra("isMine", true)) {
                            AppGlobal.getInstance().setAddr(true);
                            finish();
                        } else {
                            Intent intent = new Intent(ProAddressActivity.this, ProSkillShowaActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("typeList", (Serializable) (List<SkillItemEntity>) ((Bundle) getIntent().getBundleExtra("bundle")).getSerializable("typeList"));
                            intent.putExtra("bundle", bundle);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        ToastUtil.longToast(ProAddressActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        } else {
            map.put("id", mAddressEntity.getData().get(0).getId());
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProAddressActivity.this, Contants.proservice_save, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>." + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
//                        ToastUtil.longToast(ProAddressActivity.this, "服务地址修改成功!");
                        AppGlobal.getInstance().setAddr(true);
                        SPUtils.saveBoolean(ProAddressActivity.this, "isHaveServiceAddress", true);
//                        finish();
                    } else {
                        ToastUtil.longToast(ProAddressActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
