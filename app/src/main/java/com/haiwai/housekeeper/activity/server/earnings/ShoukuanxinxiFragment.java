package com.haiwai.housekeeper.activity.server.earnings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.activity.user.IssueRequireMakeSureActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.entity.ShouKuangxinxiEntity;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.okhttp.OKRequestCallback;
import com.haiwai.housekeeper.https.okhttp.OkHttpUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by range on 2018/1/18.
 */

public class ShoukuanxinxiFragment extends BaseFragment {

    private EditText phone, email, bankname, payee, banktransit, banktransto, account_number;
    private TextView tv_subs_zhzj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shoukuanxinxi_fragment, container, false);

        return rootView;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ShoukuanxinxiFragment.class.getName(), "onCreate: ");
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        phone = (EditText) view.findViewById(R.id.phone);
        email = (EditText) view.findViewById(R.id.email);
        bankname = (EditText) view.findViewById(R.id.bankname);
        payee = (EditText) view.findViewById(R.id.payee);
        banktransit = (EditText) view.findViewById(R.id.banktransit);
        banktransto = (EditText) view.findViewById(R.id.banktransto);
        account_number = (EditText) view.findViewById(R.id.account_number);
        tv_subs_zhzj = (TextView) view.findViewById(R.id.tv_subs_zhzj);
        tv_subs_zhzj.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        LoadDialog.showProgressDialog(getActivity());
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        RequestBody body = OkHttpUtils.getInstance().SetRequestBody(map);
        Request request = new Request.Builder()
                .url(Contants.Userreceiptsinformationlist)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(getActivity(), request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                try {
                    Gson gson = new Gson();
                    ShouKuangxinxiEntity shouKuangxinxiEntity = gson.fromJson(response, ShouKuangxinxiEntity.class);
                    if (shouKuangxinxiEntity.getStatus() == 200) {
                        phone.setText(shouKuangxinxiEntity.getData().getMobile());
                        email.setText(shouKuangxinxiEntity.getData().getEmail());
                        bankname.setText(shouKuangxinxiEntity.getData().getBankname());
                        payee.setText(shouKuangxinxiEntity.getData().getPayee());
                        banktransit.setText(shouKuangxinxiEntity.getData().getBanktransit());
                        banktransto.setText(shouKuangxinxiEntity.getData().getInstiturion());
                        account_number.setText(shouKuangxinxiEntity.getData().getAccount_number());
                      //  LoadDialog.closeProgressDialog();
                    } else {
                        // ToastUtil.shortToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.tv_subs_zhzj:
                if (PlatUtils.getEditStr(phone)) {
                    ToastUtil.shortToast(getActivity(), getString(R.string.input_email));
                    return;
                }
                if (PlatUtils.getEditStr(email)) {
                    ToastUtil.shortToast(getActivity(), getString(R.string.input_email));
                    return;
                }
                if (PlatUtils.getEditStr(bankname)) {
                    ToastUtil.shortToast(getActivity(), getString(R.string.input_email));
                    return;
                }
                if (PlatUtils.getEditStr(payee)) {
                    ToastUtil.shortToast(getActivity(), getString(R.string.input_email));
                    return;
                }
                if (PlatUtils.getEditStr(banktransit)) {
                    ToastUtil.shortToast(getActivity(), getString(R.string.input_email));
                    return;
                }
                if (PlatUtils.getEditStr(banktransto)) {
                    ToastUtil.shortToast(getActivity(), getString(R.string.input_email));
                    return;
                }
                if (PlatUtils.getEditStr(account_number)) {
                    ToastUtil.shortToast(getActivity(), getString(R.string.input_email));
                    return;
                }

                getHttp(phone.getText().toString(),
                        email.getText().toString(),
                        bankname.getText().toString(),
                        payee.getText().toString(),
                        banktransit.getText().toString(),
                        banktransto.getText().toString(),
                        account_number.getText().toString());
                break;
        }
    }

    private void getHttp(String phone, String email, String bankname, String payee, String banktransit, String banktransto, String account_number) {
        LoadDialog.showProgressDialog(getActivity());
        Map<String, String> map = new HashMap<>();
        map.put("uid", AppGlobal.getInstance().getUser().getUid());
        map.put("mobile", phone);
        map.put("email", email);
        map.put("bankname", bankname);
        map.put("payee", payee);
        map.put("banktransit", banktransit);
        map.put("institurion", banktransto);
        map.put("account_number", account_number);
        RequestBody body = OkHttpUtils.getInstance().SetRequestBody(map);
        Request request = new Request.Builder()
                .url(Contants.Userreceiptsinformation)
                .post(body)
                .build();
        OkHttpUtils.getInstance().execCallback(getActivity(), request, new OKRequestCallback() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.shortToast(getActivity(), getResources().getString(R.string.commit_success));
                    getActivity().finish();
                } else {
                    ToastUtil.shortToast(getActivity(), ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        });
    }
}
