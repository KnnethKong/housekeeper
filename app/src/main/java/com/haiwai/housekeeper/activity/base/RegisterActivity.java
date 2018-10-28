package com.haiwai.housekeeper.activity.base;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.activity.user.ShowWebActivity;
import com.haiwai.housekeeper.adapter.MyAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CodesEntity;
import com.haiwai.housekeeper.entity.RegEntity;
import com.haiwai.housekeeper.entity.RyTokenEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.receiver.JpushUtil;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.service.polling.PollingService;
import com.haiwai.housekeeper.service.polling.PollingUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.haiwai.housekeeper.widget.pickerview.OptionsPickerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ihope007 on 2016/9/27.
 * 注册界面
 */
public class RegisterActivity extends BaseActivity {
    private EditText etphone, etcode, etpwd,etname;
    private TextView tvGetCode, tvDone;
    private String isZhorEn = "";
    private TextView mSpinner;
    MyAdapter myAdapter;
    private CodesEntity codesEntity;
    private String code = "";
    private TextView tvProol;
    private ImageView ivAgree;

    private boolean isSelect=false;
    private String weS = "http://landingbook.net/public/yjcssyxy.html";
    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_register, null);
    }

    private User user;;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.login_txt_regist), Color.parseColor("#FF3C3C3C"));
        etphone = (EditText) findViewById(R.id.register_et_phone);
        etcode = (EditText) findViewById(R.id.register_et_code);
        etpwd = (EditText) findViewById(R.id.register_et_pwd);
        etname= (EditText) findViewById(R.id.register_et_name);
        tvGetCode = (TextView) findViewById(R.id.register_tv_get_code);
        tvDone = (TextView) findViewById(R.id.register_tv_done);
        tvGetCode.setOnClickListener(this);
        tvDone.setOnClickListener(this);
        mSpinner = (TextView) findViewById(R.id.mspinner);
        mSpinner.setOnClickListener(this);

        ivAgree = ((ImageView) findViewById(R.id.tv_agree_prool));
        tvProol = ((TextView) findViewById(R.id.tv_user_prool));
        ivAgree.setOnClickListener(this);
        //tvProol.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        LoadDialog.showProgressDialog(this);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(RegisterActivity.this, "secret", ""));
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RegisterActivity.this, Contants.country_code, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code1 = JsonUtils.getJsonInt(response, "status");
                LoadDialog.closeProgressDialog();
                if (code1 == 200) {
                    codesEntity = new Gson().fromJson(response, CodesEntity.class);
//                    code = codesEntity.getData().get(0).getCode();
//                    bindData(codesEntity);
                } else {
                    ToastUtil.longToast(RegisterActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));

        tvProol.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spanableInfo;
        if(isZhorEn.equals("zh")) {
            spanableInfo = new SpannableString("点击注册，即表示您同意《移居手册使用协议》和《移居手册服务提供者协议》");
          spanableInfo.setSpan(new Clickable(clickListener), 11, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          spanableInfo.setSpan(new Clickable(clickListener1), 22, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }else {
          spanableInfo = new SpannableString("By signning up, you agree to landingbook Terms of Use for User and Terms of Use for Pro");
          spanableInfo.setSpan(new Clickable(clickListener), 40, 62, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          spanableInfo.setSpan(new Clickable(clickListener1), 66, 87, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
        tvProol.setText(spanableInfo);
        tvProol.setMovementMethod(LinkMovementMethod.getInstance());

    }
    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this, "点击成功....",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, ShowWebActivity.class);
            intent.putExtra("url","http://landingbook.net/xieyi/userxieyi.html");
            intent.putExtra("title",getString(R.string.use_guide_label_1));
            startActivity(intent);
        }
    };
    private View.OnClickListener clickListener1=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this, "点击成功....",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, ShowWebActivity.class);
            intent.putExtra("url","http://landingbook.net/xieyi/proxieyi.html");
            intent.putExtra("title",getString(R.string.use_guide_label_1));
            startActivity(intent);
        }
    };
    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        /**
         * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
         */
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.blueq));
        }
    }
    private void bindData(final CodesEntity codesEntity) {

//        myAdapter = new MyAdapter<CodesEntity.DataBean>((ArrayList<CodesEntity.DataBean>) codesEntity.getData(), R.layout.hous_adapter_text_item) {
//            @Override
//            public void bindView(ViewHolder holder, CodesEntity.DataBean obj) {
//                    if ("en".equals(isZhorEn)) {
//                        holder.setText(R.id.tv_content, obj.getNameen()+"("+obj.getCode()+")");
//                    } else {
//                        holder.setText(R.id.tv_content, obj.getNamecn()+"("+obj.getCode()+")");
//                    }
//            }
//        };
//
//        mSpinner.setAdapter(myAdapter);
//
//        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                code = codesEntity.getData().get(i).getCode();
//                view.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.mspinner:
                showPicker();
                break;
            case R.id.tv_user_prool:

                break;
            case R.id.tv_agree_prool:
                if(!isSelect){
                    isSelect = true;
                    ivAgree.setBackground(getResources().getDrawable(R.mipmap.white_nike_choose_1));
                }else{
                    isSelect = false;
                    ivAgree.setBackground(getResources().getDrawable(R.mipmap.white_nike_choose_0));
                }
                break;
            case R.id.register_tv_get_code:
                if (!isNetworkAvailable()) {
                    return;
                }
                if (PlatUtils.getEditStr(etphone)) {
                    ToastUtil.shortToast(this, getString(R.string.login_input_phone));
                    return;
                }

                Map<String, String> map = new HashMap<>();
                map.put("type", "1");
                map.put("area",code);
                map.put("mobile", etphone.getText().toString().trim());
                map.put("secret_key", SPUtils.getString(RegisterActivity.this, "secret", ""));
                map.put("version", "en".equals(isZhorEn) ? "1" : "2");
                map.put("registrationid", AppGlobal.getInstance().getRegId() == null ? "" : AppGlobal.getInstance().getRegId());
                mRequestQueue.add(new PlatRequest(this, Contants.get_verify_code, map, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            ToastUtil.shortToast(RegisterActivity.this, getString(R.string.code_tis));
                            PlatUtils.startTimer(tvGetCode);
                        } else {
                            ToastUtil.shortToast(RegisterActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
                break;
            case R.id.register_tv_done:
                if (!isNetworkAvailable()) {
                    return;
                }
                if(code.equals("")){
                    if(AppGlobal.getInstance().getLagStr().equals("en")){
                        ToastUtil.shortToast(this,"Select Area Code");
                    }else{
                        ToastUtil.shortToast(this,"请选择地区(手机区号)");
                    }

                    return;
                }
                if (PlatUtils.getEditStr(etphone)) {
                    ToastUtil.shortToast(this, getString(R.string.login_input_phone));
                    return;
                }
                if (PlatUtils.getEditStr(etcode)) {
                    ToastUtil.shortToast(this, getString(R.string.inp_code));
                    return;
                }
                if (PlatUtils.getEditStr(etpwd)) {
                    ToastUtil.shortToast(this, getString(R.string.login_input_pass));
                    return;
                }
                if(!isSelect){
                    ToastUtil.shortToast(RegisterActivity.this,getString(R.string.note_agree_prool));
                    return ;
                }
                LoadDialog.showProgressDialog(this);
                Map<String, String> map2 = new HashMap<>();
                map2.put("mobile", etphone.getText().toString().trim());
                map2.put("code", etcode.getText().toString().trim());
                map2.put("pwd", etpwd.getText().toString().trim());
                map2.put("area",code);
                map2.put("secret_key", SPUtils.getString(RegisterActivity.this, "secret", ""));
                mRequestQueue.add(new PlatRequest(this, Contants.register, map2, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            RegEntity regEntity = new Gson().fromJson(response, RegEntity.class);

                            AppGlobal.getInstance().setLoginKey(regEntity.getData().getUser_info().getLogin_key());
//                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//                            finish();
                            /**
                             * 已登录的逻辑
                             */
                        login(regEntity);
                        } else {
                            ToastUtil.shortToast(RegisterActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                }));
                break;
        }
    }

    private void login(RegEntity regEntity){
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(RegisterActivity.this, "secret", ""));
        map.put("mobile", etphone.getText().toString().trim());
        map.put("pwd", etpwd.getText().toString().trim());
        map.put("lat", regEntity.getData().getUser_info().getLat());
        map.put("long", regEntity.getData().getUser_info().getLongX());
        map.put("version", "en".equals(isZhorEn) ? "1" : "2");
        map.put("language","en".equals(isZhorEn) ? "1" : "2");
        map.put("registrationid", AppGlobal.getInstance().getRegId() == null ? "" : AppGlobal.getInstance().getRegId());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(RegisterActivity.this, Contants.login, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    /**
                     * 已登录的逻辑
                     */
                    try {
                        JSONObject jsonObject = new JSONObject(response);
//                            LoginEntity loginEntity = new Gson().fromJson(response, LoginEntity.class);
                        JSONObject dataObject = jsonObject.optJSONObject("data");
                        JSONObject userInfoObject = dataObject.optJSONObject("user_info");
                        setAlias(userInfoObject.optString("uid"));
                        AppGlobal.getInstance().setLoginKey(userInfoObject.optString("login_key"));
                        PollingUtils.startPollingService(RegisterActivity.this, 5, PollingService.class, PollingService.ACTION, userInfoObject.optString("uid"));
                        //存储地址信息
                        JSONArray jsonArray = dataObject.optJSONArray("address");
                        if(jsonArray==null||jsonArray.length()==0){
                            SPUtils.saveBoolean(RegisterActivity.this,"isHaveServiceAddress",false);
                        }else{
                            SPUtils.saveBoolean(RegisterActivity.this,"isHaveServiceAddress",true);
                        }

                        if (jsonArray != null && jsonArray.length() > 0) {
                            AppGlobal.getInstance().setAddr(true);
                        } else {
                            AppGlobal.getInstance().setAddr(false);
                        }
                        //删除原头像缓存
                        FileUtils.delFile("user_head.jpeg");
                        SPUtils.saveString(RegisterActivity.this, "local_url", "");
                        //存储用户信息
                        String data = JsonUtils.getJsonStr(jsonObject.optJSONObject("data").toString(), "user_info");
                        AppGlobal.getInstance().setUser(new Gson().fromJson(data, User.class));
                        //设置是否登录
                        mApp.setLoginState(true);
                        getTokenData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    LoadDialog.closeProgressDialog();
                    ToastUtil.shortToast(RegisterActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }



    private OptionsPickerView picker;
    private void showPicker(){
        picker = new OptionsPickerView(RegisterActivity.this);
        final ArrayList<String> mList = new ArrayList<>();
        for(int i=0;i<codesEntity.getData().size();i++){
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                mList.add(codesEntity.getData().get(i).getNameen()+"("+codesEntity.getData().get(i).getCode()+")");
            }else{
                mList.add(codesEntity.getData().get(i).getNamecn()+"("+codesEntity.getData().get(i).getCode()+")");

            }
        }
        picker.setPicker(mList);
        picker.setTitle("");
        picker.setCyclic(false);
        picker.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                mSpinner.setText(mList.get(options1));
                code = codesEntity.getData().get(options1).getCode();
            }
        });
        picker.show();
    }


    private void setAlias(String alias) {
        if (TextUtils.isEmpty(alias)) {
//            Toast.makeText(LoginActivity.this, R.string.error_alias_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (JpushUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
//            JpushUtil.showToast(logs, getApplicationContext());
        }

    };
    Map<String, String> map = new HashMap<>();
    private String mUserId;
    private void getTokenData() {
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            mUserId = user.getUid();
        }
        map.put("uid", mUserId);
        map.put("secret_key", SPUtils.getString(RegisterActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetRequestBody(map);
        Request request = new Request.Builder()
                .url(Contants.getToken)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(RegisterActivity.this, request,
                new OKRequestCallback() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            Log.e("infomation",response);
                            if(JsonUtils.getJsonStr(response,"data").contains("{")){
                                RyTokenEntity ryTokenEntity = new Gson().fromJson(response, RyTokenEntity.class);
                                IMKitService.IM_Token = ryTokenEntity.getData().getToken();

                                SPUtils.saveString(RegisterActivity.this, "IM_Token", IMKitService.IM_Token);
                                if (!TextUtils.isEmpty(IMKitService.IM_Token)) {
                                    connect(IMKitService.IM_Token);
                                } else {
                                    IMKitService.IM_Token = SPUtils.getString(RegisterActivity.this, "IM_Token", "");
                                    if (!TextUtils.isEmpty(IMKitService.IM_Token)) {
                                        connect(IMKitService.IM_Token);
                                    } else {
                                        ToastUtil.longToast(RegisterActivity.this, getString(R.string.login_ry_warn));
                                    }
                                }
                            }else{
                                ToastUtil.shortToast(RegisterActivity.this, ErrorCodeUtils.getRegisterError(220 + ""));
                                LoadDialog.closeProgressDialog();
                            }

                        } else {
                            ToastUtil.shortToast(RegisterActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                });
    }
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(MyApp.getCurProcessName(getApplicationContext()))) {
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    LoadDialog.closeProgressDialog();
                    Log.d("LoginActivity", "--onSuccess" + userid);
                    if (RongIM.getInstance() != null) {//显示用户和头像
                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(user.getUid(), user.getNickname(), Uri.parse(user.getAvatar())));
                    }
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
//                    setResult(RESULT_OK);
//                    finish();
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    intent.putExtra("flag","YaSo");
                    startActivity(intent);
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LoadDialog.closeProgressDialog();
                    Log.d("LoginActivity", "--onError" + errorCode);
                    setResult(RESULT_OK);
                    finish();
                }
            });
        }
    }

}
