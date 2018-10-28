package com.haiwai.housekeeper.activity.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.activity.user.UserProActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.RyTokenEntity;
import com.haiwai.housekeeper.entity.SigGogleEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.receiver.JpushUtil;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.service.polling.PollingService;
import com.haiwai.housekeeper.service.polling.PollingUtils;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.Event;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LocationUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.NetUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.PreferenceUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import de.greenrobot.event.EventBus;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ihope007 on 2016/9/1.
 */
public class LoginActivity extends BaseActivity {
    private EditText etAccount, etPass;
    private TextView tvGetPass, tvRegister, tvNote;
    private Button ibLogin;
    private ImageButton ib_btn_end;
    private CheckBox cb;
    private User user;
    Map<String, String> map = new HashMap<>();
    private String mUserId;
    private String mName;
    private String mPortraitUri;
    InputMethodManager imm;
    private String isZhorEn = "";
    SigGogleEntity mSigGogleEntity;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pro_login, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setTitlebarHide(true);
        ImageView img = ((ImageView) findViewById(R.id.login_logo));
        String logo = "drawable://" + R.mipmap.new_login_logon;
        ImageLoader.getInstance().displayImage(logo, img);
        etAccount = (EditText) findViewById(R.id.et_account);
        etPass = (EditText) findViewById(R.id.et_password);
        tvGetPass = (TextView) findViewById(R.id.tv_get_pass);
        tvGetPass.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tvNote = (TextView) findViewById(R.id.tv_note);
        cb = (CheckBox) findViewById(R.id.cb);
        tvGetPass.setOnClickListener(this);
        tvRegister = (TextView) findViewById(R.id.tv_rigister);
        ibLogin = (Button) findViewById(R.id.ib_login);
        ib_btn_end = (ImageButton) findViewById(R.id.ib_btn_end);
        ib_btn_end.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvNote.setOnClickListener(this);
        ibLogin.setOnClickListener(this);
        LogUtil.e("login", mApp.isLogin() + "");
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        LocationUtils locationUtils = new LocationUtils(LoginActivity.this);
        String str = locationUtils.getGeoStr();
        mSigGogleEntity = LocationUtils.parStr(str);
    }

    @Override
    protected void click(View v) {
        if (v.getId() == R.id.tv_get_pass) {//忘记密码
            startActivity(new Intent(LoginActivity.this, GetPassActivity.class));
        } else if (v.getId() == R.id.tv_rigister) {//注册
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        } else if (v.getId() == R.id.tv_note) {
            ToastUtil.shortToast(LoginActivity.this, getString(R.string.login_look_xy));
        } else if (v.getId() == R.id.ib_btn_end) {
            finish();
        } else if (v.getId() == R.id.ib_login) {
            if (!NetUtil.isNetworkAvailable(LoginActivity.this)) {
                return;
            }
            if (PlatUtils.getEditStr(etAccount)) {
                ToastUtil.shortToast(LoginActivity.this, getString(R.string.login_input_phone));
                return;
            }
            if (PlatUtils.getEditStr(etPass)) {
                ToastUtil.shortToast(LoginActivity.this, getString(R.string.login_input_pass));
                return;
            }
//            if (!cb.isChecked()) {
//                ToastUtil.shortToast(LoginActivity.this, "请选择同意《海外管家用户使用协议》");
//                return;
//            }
            LoadDialog.showProgressDialog(LoginActivity.this);
            Map<String, String> map = new HashMap<>();
            map.put("secret_key", SPUtils.getString(LoginActivity.this, "secret", ""));
            map.put("mobile", etAccount.getText().toString().trim());
            map.put("pwd", etPass.getText().toString().trim());
            map.put("lat", mSigGogleEntity.getLat());
            map.put("long", mSigGogleEntity.getLng());
            map.put("version", "en".equals(isZhorEn) ? "1" : "2");
            map.put("language", "en".equals(isZhorEn) ? "1" : "2");
            map.put("registrationid", AppGlobal.getInstance().getRegId() == null ? "" : AppGlobal.getInstance().getRegId());
            MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(LoginActivity.this, Contants.login, map, null, new Response.Listener<String>() {
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
                            PollingUtils.startPollingService(LoginActivity.this, 5, PollingService.class, PollingService.ACTION, userInfoObject.optString("uid"));
                            //存储地址信息
                            JSONArray jsonArray = dataObject.optJSONArray("address");
                            if (jsonArray == null || jsonArray.length() == 0) {
                                SPUtils.saveBoolean(LoginActivity.this, "isHaveServiceAddress", false);
                            } else {
                                SPUtils.saveBoolean(LoginActivity.this, "isHaveServiceAddress", true);
                            }

                            if (jsonArray != null && jsonArray.length() > 0) {
                                AppGlobal.getInstance().setAddr(true);
                            } else {
                                AppGlobal.getInstance().setAddr(false);
                            }
                            //删除原头像缓存
                            FileUtils.delFile("user_head.jpeg");
                            SPUtils.saveString(LoginActivity.this, "local_url", "");
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
                        ToastUtil.shortToast(LoginActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                }
            }));
        }
    }

    private void getTokenData() {
        user = AppGlobal.getInstance().getUser();
        if (user != null) {
            mUserId = user.getUid();
        }
        map.put("uid", mUserId);
        map.put("secret_key", SPUtils.getString(LoginActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        RequestBody body = OkHttpUtils.getInstance().SetRequestBody(map);
        Request request = new Request.Builder()
                .url(Contants.getToken)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(LoginActivity.this, request,
                new OKRequestCallback() {
                    @Override
                    public void onResponse(String response) {
                        int code = JsonUtils.getJsonInt(response, "status");
                        if (code == 200) {
                            Log.e("infomation", response);
                            if (JsonUtils.getJsonStr(response, "data").contains("{")) {
                                RyTokenEntity ryTokenEntity = new Gson().fromJson(response, RyTokenEntity.class);
                                IMKitService.IM_Token = ryTokenEntity.getData().getToken();

                                SPUtils.saveString(LoginActivity.this, "IM_Token", IMKitService.IM_Token);
                                if (!TextUtils.isEmpty(IMKitService.IM_Token)) {
                                    EventBus.getDefault().post(
                                            new Event("im_token",IMKitService.IM_Token));
                                   // connect(IMKitService.IM_Token);
                                    Intent intent = new Intent();
                                    intent.setAction("jpush");
                                    intent.putExtra("out", true);
                                    sendBroadcast(intent);

                                    Bundle choose_bundle = new Bundle();
                                    choose_bundle.putString("flag", "");
                                    ActivityTools.goNextActivity(LoginActivity.this, MainActivity.class, choose_bundle);
                                    setResult(RESULT_OK);
                                    finish();
//                    startActivity(new Intent(ContactActivity.this, MainActivity.class));
//                    finish();

                                    PreferenceUtils.setPrefBoolean(LoginActivity.this, "IsReLogin", true);
                                } else {
                                    IMKitService.IM_Token = SPUtils.getString(LoginActivity.this, "IM_Token", "");
                                    if (!TextUtils.isEmpty(IMKitService.IM_Token)) {
                                        connect(IMKitService.IM_Token);
                                    } else {
                                        ToastUtil.longToast(LoginActivity.this, getString(R.string.login_ry_warn));
                                    }
                                }
                            } else {
                                ToastUtil.shortToast(LoginActivity.this, ErrorCodeUtils.getRegisterError(220 + ""));
                                LoadDialog.closeProgressDialog();
                            }

                        } else {
                            ToastUtil.shortToast(LoginActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                        }
                    }
                });
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {
         Log.e("sssss--->","1111");
        if (getApplicationInfo().packageName.equals(MyApp.getCurProcessName(getApplicationContext()))) {
            Log.e("sssss--->","222");
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
//                    LoadDialog.closeProgressDialog();
//                   setResult(RESULT_OK);
//                    finish();
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

                    Intent intent = new Intent();
                    intent.setAction("jpush");
                    intent.putExtra("out", true);
                    sendBroadcast(intent);

                    Bundle choose_bundle = new Bundle();
                    choose_bundle.putString("flag", "");
                    ActivityTools.goNextActivity(LoginActivity.this, MainActivity.class, choose_bundle);
                    setResult(RESULT_OK);
                    finish();
//                    startActivity(new Intent(ContactActivity.this, MainActivity.class));
//                    finish();

                    PreferenceUtils.setPrefBoolean(LoginActivity.this, "IsReLogin", true);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
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

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
