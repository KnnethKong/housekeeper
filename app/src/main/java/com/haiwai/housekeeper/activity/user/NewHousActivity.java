package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.ProAddressActivity;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CityEntity;
import com.haiwai.housekeeper.entity.CityLevelEntity;
import com.haiwai.housekeeper.entity.GeoEntity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.entity.LatLngEntity;
import com.haiwai.housekeeper.entity.NewHousEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.CityUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.MySpinner;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.geocoding.v5.MapboxGeocoding;
import com.mapbox.services.geocoding.v5.models.CarmenFeature;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;

public class NewHousActivity extends AppCompatActivity {
    private TopViewNormalBar top_new_hous_bar;
    private EditText et_address, et_email;
    private Button btn_vip_addr_next;
    private NewHousEntity mNewHousEntity;
    private MySpinner mSpinner, mSpinner1, mSpinner2;
    private String id = "";
    private String id2 = "";
    private String city = "", address_info = "", lat = "116.404017", longx = "39.915239", zip_code = "";
    private CityEntity mCityEntity;
    boolean isNew = false;
    boolean iso2o = false;
    private ArrayList<CityLevelEntity> mCityLevelEntityList;
    private ArrayList<CityLevelEntity> mCityLevelEntityList1;
    private ArrayList<CityLevelEntity> mCityLevelEntityList2;
    MyAdapter mAdapter;
    private String isZhorEn = "";
    private String country = "";
    private String province = "";
    private String country_name = "";
    private String province_name = "";
    private String city_name = "";
    private String strAddr = "";
    private boolean isEdit = false;
    private boolean isConfig = false;
    HouseListEntity.DataBean mDataBean;
    private Button btn_save_house_change;
    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hous);
        top_new_hous_bar = (TopViewNormalBar) findViewById(R.id.top_new_hous_bar);

        top_new_hous_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        et_address = (EditText) findViewById(R.id.et_address);
        et_email = (EditText) findViewById(R.id.et_email);
        etNumber = ((EditText) findViewById(R.id.et_address_no));
        ((SeekBar) findViewById(R.id.issue_addr_sb_bar1)).setProgress(25);
        ((TextView) findViewById(R.id.tv_progress_bar)).setText("25%");
        initView();
        initWtData();
    }

    private void initWtData() {
        LoadDialog.showProgressDialog(NewHousActivity.this);
        isNew = getIntent().getBooleanExtra("isNew", false);
        iso2o = getIntent().getBooleanExtra("iso2o", false);
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        isConfig = getIntent().getBooleanExtra("isConfig", false);
        if(!isEdit){
            top_new_hous_bar.setTitle(getString(R.string.add_house_title));
        }else{
            top_new_hous_bar.setTitle(getString(R.string.property_edit));
        }

//        if (isConfig) {
//
//        }
        if (isEdit) {
            btn_save_house_change.setVisibility(View.VISIBLE);
            mDataBean = (HouseListEntity.DataBean) getIntent().getBundleExtra("bundle").getSerializable("bean");


        }
        if (isNew) {
            Map<String, String> map = new HashMap<>();
            map.put("secret_key", SPUtils.getString(NewHousActivity.this, "secret", ""));
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(NewHousActivity.this, Contants.city_lst, map, null, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(">>>>>>>>>>城市列表>>" + response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        mCityEntity = CityUtils.parseCity(response);
                        geCityData(mCityEntity);
                        initProData();
                    } else {
                        LoadDialog.closeProgressDialog();
                        ToastUtil.longToast(NewHousActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        } else {
            Bundle bundle = getIntent().getExtras();
            mCityEntity = (CityEntity) bundle.getSerializable("mCityEntity");
            geCityData(mCityEntity);
            initProData();
        }

    }

    private void geCityData(CityEntity mCityEntity) {
        mCityLevelEntityList = CityUtils.getLevelList("1", id, mCityEntity);
        country = mCityLevelEntityList.get(0).getId();
        mAdapter = new MyAdapter<CityLevelEntity>(mCityLevelEntityList, R.layout.hous_adapter_text_item) {
            @Override
            public void bindView(ViewHolder holder, CityLevelEntity obj) {
                if ("en".equals(isZhorEn)) {
                    holder.setText(R.id.tv_content, obj.getYname());
                    country_name = mCityLevelEntityList.get(holder.getItemPosition()).getYname();
                } else {
                    holder.setText(R.id.tv_content, obj.getName());
                    country_name = mCityLevelEntityList.get(holder.getItemPosition()).getName();
                }
                country = mCityLevelEntityList.get(holder.getItemPosition()).getId();

            }
        };
        mSpinner.setAdapter(mAdapter);
        mSpinner.setHint("en".equals(isZhorEn) ? mCityLevelEntityList.get(0).getYname() : mCityLevelEntityList.get(0).getName());
        if (isEdit) {
            if (mCityLevelEntityList != null && mCityLevelEntityList.size() > 0 && mDataBean != null) {
                for (int i = 0; i < mCityLevelEntityList.size(); i++) {
                    if (mDataBean.getCountry().equals(mCityLevelEntityList.get(i).getId())) {
                        if ("en".equals(isZhorEn)) {
                            mSpinner.setHint(mCityLevelEntityList.get(i).getYname());
                            country_name = mCityLevelEntityList.get(i).getYname();
                        } else {
                            mSpinner.setHint(mCityLevelEntityList.get(i).getName());
                            country_name = mCityLevelEntityList.get(i).getName();
                        }
                        country = mCityLevelEntityList.get(i).getId();
                    }
                }
            }
        }
        mAdapter.notifyDataSetChanged();

        mCityLevelEntityList1 = CityUtils.getLevelList("2", mCityLevelEntityList.get(0).getId(), mCityEntity);
        province = mCityLevelEntityList1.get(0).getId();

        mAdapter = new MyAdapter<CityLevelEntity>(mCityLevelEntityList1, R.layout.hous_adapter_text_item) {
            @Override
            public void bindView(ViewHolder holder, CityLevelEntity obj) {
                if ("en".equals(isZhorEn)) {
                    holder.setText(R.id.tv_content, obj.getYname());
                    province_name = mCityLevelEntityList1.get(holder.getItemPosition()).getYname();
                } else {
                    holder.setText(R.id.tv_content, obj.getName());
                    province_name = mCityLevelEntityList1.get(holder.getItemPosition()).getName();
                }
                province = mCityLevelEntityList1.get(holder.getItemPosition()).getId();
            }
        };
        mSpinner1.setAdapter(mAdapter);
        mSpinner1.setHint("en".equals(isZhorEn) ? mCityLevelEntityList1.get(0).getYname() : mCityLevelEntityList1.get(0).getName());
        if (isEdit) {
            if (mCityLevelEntityList1 != null && mCityLevelEntityList1.size() > 0 && mDataBean != null) {
                for (int i = 0; i < mCityLevelEntityList1.size(); i++) {
                    if (mDataBean.getProvince().equals(mCityLevelEntityList1.get(i).getId())) {
                        if ("en".equals(isZhorEn)) {
                            mSpinner1.setHint(mCityLevelEntityList1.get(i).getYname());
                            province_name = mCityLevelEntityList1.get(i).getYname();
                        } else {
                            mSpinner1.setHint(mCityLevelEntityList1.get(i).getName());
                            province_name = mCityLevelEntityList1.get(i).getName();
                        }
                        province = mCityLevelEntityList1.get(i).getId();
                    }
                }
            }
        }
        mAdapter.notifyDataSetChanged();

        mCityLevelEntityList2 = CityUtils.getLevelList("3", mCityLevelEntityList1.get(0).getId(), mCityEntity);

        city = mCityLevelEntityList2.get(0).getId();
        mAdapter = new MyAdapter<CityLevelEntity>(mCityLevelEntityList2, R.layout.hous_adapter_text_item) {
            @Override
            public void bindView(ViewHolder holder, CityLevelEntity obj) {
                if ("en".equals(isZhorEn)) {
                    holder.setText(R.id.tv_content, obj.getYname());
                    city_name = mCityLevelEntityList2.get(holder.getItemPosition()).getYname();
                } else {
                    holder.setText(R.id.tv_content, obj.getName());
                    city_name = mCityLevelEntityList2.get(holder.getItemPosition()).getYname();
                }
                city = mCityLevelEntityList2.get(holder.getItemPosition()).getId();

            }
        };
        mSpinner2.setAdapter(mAdapter);
        if (isEdit) {
            if (mCityLevelEntityList2 != null && mCityLevelEntityList2.size() > 0 && mDataBean != null) {
                for (int i = 0; i < mCityLevelEntityList2.size(); i++) {
                    if (mDataBean.getCity().equals(mCityLevelEntityList2.get(i).getId())) {
                        if ("en".equals(isZhorEn)) {
                            mSpinner2.setHint(mCityLevelEntityList2.get(i).getYname());
                            city_name = mCityLevelEntityList2.get(i).getYname();
                        } else {
                            mSpinner2.setHint(mCityLevelEntityList2.get(i).getName());
                            city_name = mCityLevelEntityList2.get(i).getYname();
                        }
                        city = mCityLevelEntityList2.get(i).getId();
                    }
                }
            }
        }
        mAdapter.notifyDataSetChanged();
        if (isEdit) {
            et_address.setText(mDataBean.street);
            etNumber.setText(mDataBean.house_number);
            et_email.setText(mDataBean.getZip_code());
            if (!et_address.getText().toString().equals("")) {
                et_address.setSelection(et_address.getText().toString().length());
            }

            if (!et_email.getText().toString().equals("")) {
                et_email.setSelection(et_email.getText().toString().length());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initProData() {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(NewHousActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(NewHousActivity.this, Contants.hous_problem, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                System.out.println(">>>>>>>>>>房产问题>>" + response);
                if (code == 200) {
                    mNewHousEntity = new Gson().fromJson(response, NewHousEntity.class);
                    LoadDialog.closeProgressDialog();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(NewHousActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }


    private void initView() {
        btn_save_house_change = ((Button) findViewById(R.id.btn_save_house_change));
        btn_save_house_change.setOnClickListener(mOnClickListener);
        mSpinner = (MySpinner) findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSpinner.dismissPop();
                if ("en".equals(isZhorEn)) {
                    mSpinner.setHint(mCityLevelEntityList.get(i).getYname());
                    country_name = mCityLevelEntityList.get(i).getYname();
                } else {
                    mSpinner.setHint(mCityLevelEntityList.get(i).getName());
                    country_name = mCityLevelEntityList.get(i).getName();
                }
                country = mCityLevelEntityList.get(i).getId();

            }
        });
        mSpinner1 = (MySpinner) findViewById(R.id.spinner1);
        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSpinner1.dismissPop();
                if ("en".equals(isZhorEn)) {
                    mSpinner1.setHint(mCityLevelEntityList1.get(i).getYname());
                    province_name = mCityLevelEntityList1.get(i).getYname();
                } else {
                    mSpinner1.setHint(mCityLevelEntityList1.get(i).getName());
                    province_name = mCityLevelEntityList1.get(i).getName();
                }
                province = mCityLevelEntityList1.get(i).getId();

            }
        });
        mSpinner2 = (MySpinner) findViewById(R.id.spinner2);
        mSpinner2.setOnItemSelectedListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSpinner2.dismissPop();
                if ("en".equals(isZhorEn)) {
                    mSpinner2.setHint(mCityLevelEntityList2.get(i).getYname());
                    city_name = mCityLevelEntityList2.get(i).getYname();
                } else {
                    mSpinner2.setHint(mCityLevelEntityList2.get(i).getName());
                    city_name = mCityLevelEntityList2.get(i).getYname();
                }
                city = mCityLevelEntityList2.get(i).getId();

            }
        });

        btn_vip_addr_next = (Button) findViewById(R.id.btn_vip_addr_next);
        btn_vip_addr_next.setOnClickListener(mOnClickListener);
    }

    private boolean isSave = false;
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_new_hous_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.btn_vip_addr_next) {

                if(TextUtils.isEmpty(et_address.getText().toString())){
                    ToastUtil.shortToast(NewHousActivity.this,getString(R.string.input_street));
                    return;
                }
                if(TextUtils.isEmpty(etNumber.getText().toString())){
                    ToastUtil.shortToast(NewHousActivity.this,getString(R.string.input_number));
                    return ;
                }
                isSave = false;
                String strAddrNo = etNumber.getText().toString().trim();
                String strAddress = et_address.getText().toString().trim();

                final String geoAdress = strAddrNo+"+" + strAddress+"+" + city_name;
                LoadDialog.showProgressDialog(NewHousActivity.this);
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
                        LoadDialog.closeProgressDialog();
                        String loaction = response.body().string();
                        LatLngEntity entity = new Gson().fromJson(loaction,LatLngEntity.class);
                        if(entity.getFeatures()!=null&&entity.getFeatures().size()>0){
                            String lng = entity.getFeatures().get(0).getCenter().get(0)+"";
                            String lat = entity.getFeatures().get(0).getCenter().get(1)+"";
                            gotoNetData(lat,lng);
                        }else{
                            String url2 = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+city_name+"+BC+Canada.json?access_token=pk.eyJ1IjoibGl1eWoiLCJhIjoiY2l6amhjbWNwMDNucjMybWliaTQwNGl5YSJ9.HmoUCkUzAShnBGbRRkjk5g";
                            getAddAgain(url2);
                        }

                    }
                });
            } else if (view.getId() == R.id.btn_save_house_change) {
                if(TextUtils.isEmpty(et_address.getText().toString())){
                    ToastUtil.shortToast(NewHousActivity.this,getString(R.string.input_street));
                    return;
                }
                if(TextUtils.isEmpty(etNumber.getText().toString())){
                    ToastUtil.shortToast(NewHousActivity.this,getString(R.string.input_number));
                    return ;
                }
                isSave = true;

                String strAddrNo = etNumber.getText().toString().trim();
                String strAddress = et_address.getText().toString().trim();

                final String geoAdress = strAddrNo+"+" + strAddress+"+" + city_name;
                LoadDialog.showProgressDialog(NewHousActivity.this);
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
                        LoadDialog.closeProgressDialog();
                        String loaction = response.body().string();
                        LatLngEntity entity = new Gson().fromJson(loaction,LatLngEntity.class);
                        if(entity.getFeatures()!=null&&entity.getFeatures().size()>0){
                            String lng = entity.getFeatures().get(0).getCenter().get(0)+"";
                            String lat = entity.getFeatures().get(0).getCenter().get(1)+"";
                            gotoNetData(lat,lng);
                        }else{
                            String url2 = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+city_name+"+BC+Canada.json?access_token=pk.eyJ1IjoibGl1eWoiLCJhIjoiY2l6amhjbWNwMDNucjMybWliaTQwNGl5YSJ9.HmoUCkUzAShnBGbRRkjk5g";
                            getAddAgain(url2);
                        }
                    }
                });
            }
        }
    };


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

    private void changeHouseInformation(String lat, String lng) {
        if(TextUtils.isEmpty(et_address.getText().toString())){
            ToastUtil.shortToast(NewHousActivity.this,getString(R.string.input_street));
            return;
        }
        if(TextUtils.isEmpty(etNumber.getText().toString())){
            ToastUtil.shortToast(NewHousActivity.this,getString(R.string.input_number));
            return ;
        }
//        LoadDialog.showProgressDialog(this);
        Map<String, String> map = new HashMap<>();
        map.put("id", mDataBean.getId());
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("country", country);
        map.put("province", province);
        map.put("city", city);
        map.put("housing_type", mDataBean.getHousing_type());
        map.put("area", mDataBean.getArea());
        map.put("street",et_address.getText().toString());
        map.put("house_number",etNumber.getText().toString());
        map.put("address_info", et_address.getText().toString()+etNumber.getText().toString());
        map.put("zip_code", et_email.getText().toString().trim());
        map.put("lat", lat);
        map.put("long", lng);
        map.put("email",mDataBean.getEmail());
        map.put("contact_area", mDataBean.contact_area);
        map.put("contact_number", mDataBean.getContact_number());
        map.put("alternate_contact", mDataBean.getAlternate_contact());
        map.put("alternate_contact_number", mDataBean.alternate_contact_number);
        map.put("secret_key", SPUtils.getString(NewHousActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(NewHousActivity.this, Contants.hous_save, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                Log.i(">>>>>>>>>>>>>>>>>>>", response);
                if (code == 200) {
                    LoadDialog.closeProgressDialog();
                    Intent intent = new Intent(NewHousActivity.this, VipHouseManageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(NewHousActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }



    private void gotoNetData(String lat ,String lng) {
        if (!isSave) {
            if (TextUtils.isEmpty(et_address.getText().toString().trim())) {
                ToastUtil.longToast(NewHousActivity.this, getString(R.string.tv_deail_addr));
                return;
            }
            Intent intent = new Intent(NewHousActivity.this, NewHousProActivity.class);
            Bundle bundle = new Bundle();
            if (isEdit) {
                intent.putExtra("isEdit", true);
                bundle.putSerializable("data", mDataBean);
                bundle.putInt("fromEdit", getIntent().getBundleExtra("bundle").getInt("fromEdit", -1));
            }
            if (isConfig) {
                intent.putExtra("isConfig", true);
            }
            if (isNew) {
                intent.putExtra("isNew", true);
            }
            Log.i("infomation--", country + "\n" + city + "\n" + province);
            bundle.putSerializable("addrPro", mNewHousEntity);

            intent.putExtra("bundle", bundle);
            intent.putExtra("country", country);
            intent.putExtra("province", province);
            intent.putExtra("city", city);
            intent.putExtra("address_info", et_address.getText().toString()+etNumber.getText().toString());
            intent.putExtra("street",et_address.getText().toString());
            intent.putExtra("house_number",etNumber.getText().toString());
            intent.putExtra("zip_code", et_email.getText().toString().trim());
//            intent.putExtra("email",mDataBean.getEmail()+"");
            intent.putExtra("lat", lat);
            intent.putExtra("long",lng);
            intent.putExtra("iso2o", iso2o);
            startActivity(intent);
        } else {
            changeHouseInformation(lat,lng);
        }

    }

}
