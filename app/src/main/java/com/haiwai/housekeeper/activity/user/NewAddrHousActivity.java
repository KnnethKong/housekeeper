package com.haiwai.housekeeper.activity.user;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.Text;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.RegisterActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CodesEntity;
import com.haiwai.housekeeper.entity.HousAddEntity;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewAddrHousActivity extends AppCompatActivity {
    private TopViewNormalBar top_add_bar;
    private EditText et_input_lxrdh, et_input_lxyx, et_input_bylxr, et_input_email;
    private TextView btn_hous_addr_add;
    private String street,number,housing_type = "", area = "", city = "", address_info = "", zip_code = "",
            lat = "", longx = "", contact_emial = "", contact_number = "", alternate_contact = "", email = "";
    private Map<String, String> map = new HashMap<>();
    private boolean iso2o = false;
    private String isZhorEn = "";
    private String country = "";
    private String province = "";
    boolean isEdit = false;
    boolean isConfig = false;

    private TextView tvSp,tvSp1;

    private TextView tvAreaNo, tvByAreaNO;
    HouseListEntity.DataBean mDataBean;

    private String code1 = "";
    private String code2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_addr_hous);
        top_add_bar = (TopViewNormalBar) findViewById(R.id.top_add_bar);
        top_add_bar.setOnBackListener(mOnClickListener);
        initView();
        initData();
        getCountryCode();
    }

    private CodesEntity codesEntity;

    private List<String> codeList;
    private void getCountryCode() {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(NewAddrHousActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(NewAddrHousActivity.this, Contants.country_code, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int codeRes = JsonUtils.getJsonInt(response, "status");
                if (codeRes == 200) {
                    boolean isArea1 = false;
                    boolean isAlArea = false;
                    codesEntity = new Gson().fromJson(response, CodesEntity.class);
                    codeList = new ArrayList<>();
                    for (int i = 0; i < codesEntity.getData().size(); i++) {
                        codeList.add(codesEntity.getData().get(i).getCode());
                    }
                    if (mDataBean != null) {
                        for (int i = 0; i < codeList.size(); i++) {
                            if(codeList.get(i).equals(mDataBean.contact_area)){
                                tvSp.setText(codeList.get(i));
                                code1 = codeList.get(i);
                                isArea1 = true;
                            }else if(codeList.get(i).equals(mDataBean.alternate_contact_area)){
                                tvSp1.setText(codeList.get(i));
                                code2 = codeList.get(i);
                                isAlArea = true;
                            }
                        }
                        if(!isArea1){
                            tvSp.setText(codeList.get(0));
                        }
                        if(!isAlArea){
                            tvSp1.setText(codeList.get(0));
                        }
                    }
                    if(!isArea1){
                        tvSp.setText("86");
                        code1="86";
                    }
                    if(!isAlArea){
                        tvSp1.setText("86");
                        code2 = "86";
                    }
                } else {
//                    ToastUtil.longToast(NewAddrHousActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }
    private PopupWindow pop;
    private void showPopu(final int from,View view1){
        View view = LayoutInflater.from(this).inflate(R.layout.code_show_popu_view,null);
        LinearLayout ll = ((LinearLayout) view.findViewById(R.id.ll_code_list_view));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,30);
        params.weight =1;
        for(int i=0;i<codeList.size();i++){
            final TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                tv.setText(codesEntity.getData().get(i).getNameen()+"("+codeList.get(i)+")");

            }else{
                tv.setText(codesEntity.getData().get(i).getNamecn()+"("+codeList.get(i)+")");

            }
            tv.setTag(i);
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hidePopu();
                    if(from==1){
                        code1 = codeList.get(Integer.valueOf(tv.getTag().toString()));
                        tvSp.setText(code1);
                    }else{
                        code2 = codeList.get(Integer.valueOf(tv.getTag().toString()));
                        tvSp1.setText(code2);
                    }
                }
            });
            ll.addView(tv);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });
        WindowManager manager = ((WindowManager) getSystemService(WINDOW_SERVICE));
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        pop = new PopupWindow(view, metrics.widthPixels/2,metrics.widthPixels/2+20);
        pop.setOutsideTouchable(false);
        pop.setOutsideTouchable(false);
        pop.showAsDropDown(view1);
    }

    private void hidePopu(){
        if(pop!=null&&pop.isShowing()){
            pop.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(pop!=null&&pop.isShowing()){
                pop.dismiss();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();

        User user = AppGlobal.getInstance().getUser();
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if(isEdit){
            top_add_bar.setTitle(getString(R.string.property_edit));
        }else{
            top_add_bar.setTitle(getString(R.string.add_house_title));
        }
        if (isEdit) {
            if (getIntent().getBundleExtra("bundle").getInt("fromEdit", -1) == 1) {
                btn_hous_addr_add.setText(getString(R.string.save_change));
            }
            mDataBean = (HouseListEntity.DataBean) getIntent().getBundleExtra("bundle").getSerializable("data");
            et_input_lxrdh.setText(mDataBean.getContact_number());
//            et_input_lxyx.setText(mDataBean.getEmail());
            et_input_lxyx.setText(mDataBean.getEmail());

            et_input_bylxr.setText(mDataBean.getAlternate_contact());
//            et_input_email.setText(mDataBean.getEmail());
            et_input_email.setText(mDataBean.alternate_contact_number);
        } else {
            et_input_lxrdh.setText(AppGlobal.getInstance().getUser().getMobile());
        }
        isConfig = getIntent().getBooleanExtra("isConfig", false);
        iso2o = getIntent().getBooleanExtra("iso2o", false);
        housing_type = getIntent().getStringExtra("housing_type");
        area = getIntent().getStringExtra("area");
        country = getIntent().getStringExtra("country");
        province = getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        street = getIntent().getStringExtra("street");
        number = getIntent().getStringExtra("house_number");
        address_info = getIntent().getStringExtra("address_info");
        zip_code = getIntent().getStringExtra("zip_code");
        lat = getIntent().getStringExtra("lat");
        longx = getIntent().getStringExtra("long");
        if (isEdit) {
            map.put("id", mDataBean.getId());
        }
        map.put("uid", user.getUid());
        map.put("country", country);
        map.put("province", province);
        map.put("city", city);
        map.put("housing_type", housing_type);
        map.put("area", area);
        map.put("street",street);
//        map.put("email",getIntent().getStringExtra("email"));
        map.put("house_number",number);
        map.put("address_info", address_info);
        map.put("zip_code", zip_code);
        map.put("lat", lat);
        map.put("long", longx);
    }

    private void initView() {
        tvSp = ((TextView) findViewById(R.id.mspinner1));
        tvSp1 = ((TextView) findViewById(R.id.mSpinner));



        tvByAreaNO = ((TextView) findViewById(R.id.tv_bylxr_area));
        tvAreaNo = ((TextView) findViewById(R.id.tv_contact_area_no));
        et_input_lxrdh = (EditText) findViewById(R.id.et_input_lxr);
        et_input_lxyx = (EditText) findViewById(R.id.et_input_lxdh);
        et_input_bylxr = (EditText) findViewById(R.id.et_input_bylxr);
        et_input_email = (EditText) findViewById(R.id.et_input_email);
        btn_hous_addr_add = (TextView) findViewById(R.id.btn_hous_addr_add);
        btn_hous_addr_add.setOnClickListener(mOnClickListener);
        tvSp.setOnClickListener(mOnClickListener);
        tvSp1.setOnClickListener(mOnClickListener);



    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_add_bar.getBackView()) {
                finish();
            }else if(view.getId() == R.id.mspinner1){
                showPopu(1,view);
            }else if(view.getId() == R.id.mSpinner){
                showPopu(0,view);
            } else if (view.getId() == R.id.btn_hous_addr_add) {
                LoadDialog.showProgressDialog(NewAddrHousActivity.this);
                contact_number = et_input_lxrdh.getText().toString().trim();
                contact_emial = et_input_lxyx.getText().toString().trim();
                alternate_contact = et_input_bylxr.getText().toString().trim();
                email = et_input_email.getText().toString().trim();
//                Log.i("mapInfomation---",
//                        contact_number + "" +
//                                "\n" + contacts + "" +
//                                "\n" + alternate_contact +
//                                "\n" + email +
//                                "\n" + SPUtils.getString(NewAddrHousActivity.this, "secret", "") +
//                                "\n" + AppGlobal.getInstance().getLoginKey() +
//                                "\n" + getIntent().getStringExtra("country") +
//                                "\n" + getIntent().getStringExtra("province") +
//                                "\n" + getIntent().getStringExtra("city") +
//                                "\n" + getIntent().getStringExtra("address_info") +
//
//                                "\n" + getIntent().getStringExtra("housing_type") +
//                                "\n" + getIntent().getStringExtra("area") +
//                                "\n" + getIntent().getStringExtra("lat") +
//                                "\n" + getIntent().getStringExtra("long")+"\n");
//                if (!TextUtils.isEmpty(contact_emial)) {
                if (!TextUtils.isEmpty(contact_number)) {

//                        map.put("uid", AppGlobal.getInstance().getUser().getUid());
//                        map.put("country", getIntent().getStringExtra("country"));
//                        map.put("province", getIntent().getStringExtra("province"));
//                        map.put("city", getIntent().getStringExtra("city"));
//                        map.put("address_info", getIntent().getStringExtra("address_info"));
//                        map.put("housing_type", getIntent().getStringExtra("housing_type"));
//                        map.put("area", getIntent().getStringExtra("area"));
//                        map.put("lat", getIntent().getStringExtra("lat"));
//                        map.put("long", getIntent().getStringExtra("long"));

//                        map.put("contacts", contacts);
                    map.put("contact_area", code1);
                    map.put("contact_number", contact_number);
                    map.put("alternate_contact", alternate_contact);

                    if (!TextUtils.isEmpty(email)) {
                        map.put("alternate_contact_area", code2);
                        map.put("alternate_contact_number", email);
                    }
                    if(!TextUtils.isEmpty(et_input_lxyx.getText().toString().trim())){
                        map.put("email", et_input_lxyx.getText().toString().trim());
                    }
                    map.put("secret_key", SPUtils.getString(NewAddrHousActivity.this, "secret", ""));
                    map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());

                    if (isEdit) {
//                        if (getIntent().getBundleExtra("bundle").getInt("fromEdit", -1) == 1) {
//
//                        }
                        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(NewAddrHousActivity.this, Contants.hous_save, map, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                Log.i(">>>>>>>>>>>>>>>>>>>", response);
                                if (code == 200) {
                                    Toast.makeText(NewAddrHousActivity.this, getString(R.string.tv_finish_a), Toast.LENGTH_SHORT).show();
                                    LoadDialog.closeProgressDialog();
                                    Intent intent = new Intent(NewAddrHousActivity.this, VipHouseManageActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    LoadDialog.closeProgressDialog();
                                    ToastUtil.longToast(NewAddrHousActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        }));

                    } else {
                        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(NewAddrHousActivity.this, Contants.hous_add, map, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                Log.i(">>>>>>>>>>>>>>>>>>>", response);
                                if (code == 200) {

                                    LoadDialog.closeProgressDialog();
                                    if (iso2o) {
                                        Intent intent = new Intent(NewAddrHousActivity.this, IssueRequireAddrActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        HousAddEntity housAddEntity = new Gson().fromJson(response, HousAddEntity.class);
                                        if (isConfig) {
                                            Intent intent = new Intent(NewAddrHousActivity.this, EnvelopeActivity.class);
                                            intent.putExtra("isConfig", true);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("data", housAddEntity.getData());
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            if (getIntent().getBooleanExtra("isNew", false)) {
                                                Intent intent = new Intent(NewAddrHousActivity.this, VipHouseManageActivity.class);
//
                                                startActivity(intent);
                                            } else {
                                                Intent intent = new Intent(NewAddrHousActivity.this, CommonProActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putSerializable("data", housAddEntity.getData());
                                                intent.putExtras(bundle);
                                                intent.putExtra("isNew", true);
                                                startActivity(intent);
                                            }
//                                                Intent intent = new Intent(NewAddrHousActivity.this, VipHouseManageActivity.class);
////                                                Bundle bundle = new Bundle();
////                                                bundle.putSerializable("data", housAddEntity.getData());
////                                                intent.putExtras(bundle);
////                                                intent.putExtra("isNew", true);
//                                                startActivity(intent);
                                            finish();
                                        }
                                    }
                                } else {
                                    LoadDialog.closeProgressDialog();
                                    ToastUtil.longToast(NewAddrHousActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        }));
                    }
                } else {
                    ToastUtil.longToast(NewAddrHousActivity.this, getString(R.string.empty_phone_number));
                }
//                } else {
//                    ToastUtil.longToast(NewAddrHousActivity.this, "联系人不能为空");
//                }
            }
        }
    };
}
