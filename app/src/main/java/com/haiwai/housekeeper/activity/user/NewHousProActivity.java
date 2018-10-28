package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.HouseListEntity;
import com.haiwai.housekeeper.entity.NewHousEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.SingleItemView;
import com.haiwai.housekeeper.view.TopViewNormalBar;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewHousProActivity extends AppCompatActivity {
    private TopViewNormalBar top_pro_bar;
    private SingleItemView singleItemView;
    private LinearLayout ll_next_layou;
    private NewHousEntity newHousEntity;
    private int count1 = 0, count2 = 0;
    private List<NewHousEntity.DataBean.DateBean.ProblemBean> mProblem, mProblem1;
    private String title1, title2;
    private Button btn_hous_addr_next;
    private TextView id_pre_step_hous;
    int nextLag = 0;
    private int mCount;
    private String mId;
    private String city = "", address_info = "", lat = "", longx = "",zip_code="",street,number;
    private String country = "";
    private String province = "";
    private boolean iso2o = false;
    private String isZhorEn = "";
    private boolean isEdit = false;
    private boolean isConfig = false;
    HouseListEntity.DataBean mDataBean;
    private Button btn_save_house_change_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hous_pro);
        top_pro_bar = (TopViewNormalBar) findViewById(R.id.top_pro_bar);

        top_pro_bar.setOnBackListener(mOnClickListener);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        ((SeekBar) findViewById(R.id.issue_addr_sb_bar2)).setProgress(50);
        ((TextView) findViewById(R.id.tv_progress_bar2)).setText("50%");
        btn_save_house_change_next = ((Button) findViewById(R.id.btn_save_house_change_next));
        btn_save_house_change_next.setOnClickListener(mOnClickListener);
        initView();
        initData();
    }

    private void initData() {
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if(isEdit){
            top_pro_bar.setTitle(getString(R.string.property_edit));
        }else{
            top_pro_bar.setTitle(getString(R.string.add_house_title));
        }


        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (isEdit) {
            btn_save_house_change_next.setVisibility(View.VISIBLE);
            mDataBean = (HouseListEntity.DataBean) bundle.getSerializable("data");
            if (mDataBean != null) {
                mId = mDataBean.getHousing_type();
            }
        }
        isConfig = getIntent().getBooleanExtra("isConfig", false);
        iso2o = getIntent().getBooleanExtra("iso2o", false);
        country = getIntent().getStringExtra("country");
        province = getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        address_info = getIntent().getStringExtra("address_info");
        street = getIntent().getStringExtra("street");
        number = getIntent().getStringExtra("house_number");
        zip_code = getIntent().getStringExtra("zip_code");
        lat = getIntent().getStringExtra("lat");
        longx = getIntent().getStringExtra("long");
        newHousEntity = (NewHousEntity) bundle.getSerializable("addrPro");
        if (newHousEntity != null) {
            mCount = newHousEntity.getData().getDate().size();
            if (mCount > 0) {

                if(isZhorEn.equals("en")){
                    title1 = newHousEntity.getData().getDate().get(0).getYvalue();
                }else{
                    title1 = newHousEntity.getData().getDate().get(0).getValue();
                }
//                title1 = newHousEntity.getData().getDate().get(0).getYvalue();
                mProblem = newHousEntity.getData().getDate().get(0).getProblem();
                count1 = mProblem.size();
                bindDataView(title1, mProblem, count1);
            }

        }
    }

    private void initView() {
        singleItemView = (SingleItemView) findViewById(R.id.sigleView);
        btn_hous_addr_next = (Button) findViewById(R.id.btn_hous_addr_next);
        btn_hous_addr_next.setOnClickListener(mOnClickListener);
        id_pre_step_hous = (TextView) findViewById(R.id.id_pre_step_hous);
        id_pre_step_hous.setOnClickListener(mOnClickListener);
        ll_next_layou = (LinearLayout) findViewById(R.id.ll_next_layou);
    }

    public void bindDataView(String title, List<NewHousEntity.DataBean.DateBean.ProblemBean> problem, int counts) {
        singleItemView.setVisibleNum(counts);
        singleItemView.setItemName1(problem, isZhorEn);
        singleItemView.setTitleName(title);
        if (isEdit) {
            for (int i = 0; i < counts; i++) {
                if (mId.equals(problem.get(i).getId())) {
                    singleItemView.setSelected(i);
                }
            }
        }

    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == top_pro_bar.getBackView()) {
                finish();
            } else if (view.getId() == R.id.btn_hous_addr_next) {
                if(!singleItemView.isSelectOne()){
                    ToastUtil.shortToast(NewHousProActivity.this,getString(R.string.issue_require_at_least_one));
                    return;
                }
                mId = singleItemView.getSelectItemId();
                Intent intent = new Intent(NewHousProActivity.this, NewHousPro2Activity.class);
                Bundle bundle = new Bundle();
                if (isEdit) {
                    intent.putExtra("isEdit", true);
                    bundle.putSerializable("data", mDataBean);
                    bundle.putString("area",getIntent().getBundleExtra("bundle").getString("area"));
                    bundle.putInt("fromEdit",getIntent().getBundleExtra("bundle").getInt("fromEdit",-1));
                }
                if (isConfig) {
                    intent.putExtra("isConfig", true);
                }
                if(getIntent().getBooleanExtra("isNew",false)){
                    intent.putExtra("isNew",true);
                }
                bundle.putSerializable("addrPro", newHousEntity);

                intent.putExtra("bundle", bundle);
                intent.putExtra("housing_type", mId);
                intent.putExtra("country", country);
                intent.putExtra("province", province);
                intent.putExtra("city", city);
                intent.putExtra("address_info", address_info);
                intent.putExtra("street",street);
//                intent.putExtra("email",getIntent().getStringExtra("email"));
                intent.putExtra("house_number",number);
                intent.putExtra("zip_code",zip_code);
                intent.putExtra("lat", lat);
                intent.putExtra("long", longx);
                intent.putExtra("iso2o", iso2o);
                startActivity(intent);
            } else if (view.getId() == R.id.id_pre_step_hous) {
                Intent intent = new Intent(NewHousProActivity.this, NewHousPro2Activity.class);
                Bundle bundle = new Bundle();
                if (isEdit) {
                    intent.putExtra("isEdit", true);
                    bundle.putSerializable("data", mDataBean);
                }
                if (isConfig) {
                    intent.putExtra("isConfig", true);
                }
                if(getIntent().getBooleanExtra("isNew",false)){
                    intent.putExtra("isNew",true);
                }
                bundle.putSerializable("addrPro", newHousEntity);
                intent.putExtra("bundle", bundle);
                intent.putExtra("housing_type", "");
                intent.putExtra("country", country);
                intent.putExtra("province", province);
                intent.putExtra("city", city);
                intent.putExtra("house_number",number);
                intent.putExtra("street",street);
                intent.putExtra("address_info", address_info);
                intent.putExtra("zip_code",zip_code);
                intent.putExtra("lat", lat);
                intent.putExtra("long", longx);
                intent.putExtra("iso2o", iso2o);
                startActivity(intent);
            }else if(view.getId()==R.id.btn_save_house_change_next){
                changeHouseInformation();
            }
        }
    };


    private void changeHouseInformation() {
        LoadDialog.showProgressDialog(this);
        Map<String, String> map = new HashMap<>();
        map.put("id", mDataBean.getId());
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("country", country);
        map.put("province", province);
        map.put("city", city);
        map.put("housing_type",singleItemView.getSelectItemId());
        map.put("area", mDataBean.getArea());
        map.put("address_info", address_info);
        map.put("street",street);
        map.put("house_number",number);
        map.put("zip_code", zip_code);
        map.put("lat", lat);
        map.put("email",mDataBean.getEmail());
        map.put("long", longx);
        map.put("contact_area", mDataBean.contact_area);
        map.put("contact_number", mDataBean.getContact_number());
        map.put("alternate_contact", mDataBean.getAlternate_contact());
        map.put("alternate_contact_number", mDataBean.alternate_contact_number);
        map.put("secret_key", SPUtils.getString(NewHousProActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());

        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(NewHousProActivity.this, Contants.hous_save, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                Log.i(">>>>>>>>>>>>>>>>>>>", response);
                if (code == 200) {
                    LoadDialog.closeProgressDialog();
                    Intent intent = new Intent(NewHousProActivity.this, VipHouseManageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.longToast(NewHousProActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }
}
