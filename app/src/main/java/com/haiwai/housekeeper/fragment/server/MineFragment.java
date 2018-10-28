package com.haiwai.housekeeper.fragment.server;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.MyWalletActivity;
import com.haiwai.housekeeper.activity.user.PersonInfoActivity;
import com.haiwai.housekeeper.activity.user.RedWalletActivity;
import com.haiwai.housekeeper.activity.user.SetActivity;
import com.haiwai.housekeeper.activity.user.UseGuideActivity;
import com.haiwai.housekeeper.activity.user.VipHouseManageActivity;
import com.haiwai.housekeeper.activity.user.VipOrderActivity;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.utils.ActivityTools;

public class MineFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mineView = inflater.inflate(R.layout.pro_fragment_mine, container, false);
        return mineView;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.user_mine_iv_icon).setOnClickListener(this);
        view.findViewById(R.id.mine_user_ll_name).setOnClickListener(this);
        view.findViewById(R.id.user_mine_tv_set).setOnClickListener(this);
        view.findViewById(R.id.mine_user_ll_person_page).setOnClickListener(this);

        view.findViewById(R.id.user_mine_ll_vip_order).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_user_manage).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_my_wallet).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_red_wallet).setOnClickListener(this);

        view.findViewById(R.id.user_mine_ll_my_paypal).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_use_guide).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_change_pro).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_service).setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.user_mine_ll_my_paypal:
                break;
            case R.id.user_mine_iv_icon:
            case R.id.mine_user_ll_name:
                //都跳个人信息
                ActivityTools.goNextActivity(getActivity(), PersonInfoActivity.class);
                break;
            case R.id.user_mine_tv_set:
                //设置页面
                ActivityTools.goNextActivity(getActivity(), SetActivity.class);
                break;
            case R.id.mine_user_ll_person_page:
                //个人主页
                break;

            case R.id.user_mine_ll_vip_order:
                ActivityTools.goNextActivity(getActivity(), VipOrderActivity.class);
                break;
            case R.id.user_mine_ll_user_manage:
                ActivityTools.goNextActivity(getActivity(), VipHouseManageActivity.class);
                break;
            case R.id.user_mine_ll_my_wallet:
                ActivityTools.goNextActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.user_mine_ll_red_wallet:
                ActivityTools.goNextActivity(getActivity(), RedWalletActivity.class);
                break;
            case R.id.user_mine_ll_use_guide:
                ActivityTools.goNextActivity(getActivity(), UseGuideActivity.class);
                break;
            case R.id.user_mine_ll_change_pro:
                break;
            case R.id.user_mine_ll_service:
                break;
        }
    }
}
