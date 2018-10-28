package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

/**
 * Created by ihope007 on 2016/9/12.
 */
public class VIPpageActivity extends BaseActivity {// TODO: 2016/11/29 换顶部图片 中间图片
    User user;
    private String isZhorEn = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_vip_page, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.vip_page_title), Color.parseColor("#FF3C3C3C"));
        findViewById(R.id.vip_page_tv_house_manage).setOnClickListener(this);
        findViewById(R.id.vip_page_tv_my_vip).setOnClickListener(this);
        findViewById(R.id.vip_page_tv_business_order).setOnClickListener(this);
        findViewById(R.id.vip_page_tv_msg).setOnClickListener(this);
        findViewById(R.id.iv_kf).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.vip_page_tv_house_manage://房屋配置
                ActivityTools.goNextActivity(this, VipHouseDesignActivity.class);
                break;
            case R.id.vip_page_tv_my_vip://我的vip
                ActivityTools.goNextActivity(this, VipOrderActivity.class);
                break;
            case R.id.vip_page_tv_business_order://物业账单
                ActivityTools.goNextActivity(this, VipBusinessOrderActivity.class);
                break;
            case R.id.vip_page_tv_msg://消息
                Bundle msg_bundle = new Bundle();
                msg_bundle.putString("flag", "vip_page");
                ActivityTools.goNextActivity(this, MainActivity.class, msg_bundle);
                break;
            case R.id.iv_kf://客服
                Information info = new Information();
                info.setSysNum(Contants.SYSNUM);/* 必填 */
                info.setInitModeType(1);//*****************************暂时之调用机器人-1
                info.setArtificialIntelligence(false);//转为人工true
                info.setArtificialIntelligenceNum(10);//只有人工true时起作用
                info.setUseVoice(true);//使用语音
                info.setColor("#FF0000");/* 选填，默认为"#09aeb0". 可以设置头部背景，提交评价背景，相似问题字体颜色和富文本类型中“阅读全文”字体颜色*/
                info.setUid(user.getUid());/* 选填，设置用户唯一标识 */
                info.setArtificialIntelligence(false);/* 智能转人工按钮，选填. 默认为false. 机器人客服优先模式时, false:显示转人工按钮；true:机器人有未知问题、引导回答时才显示转人工按钮 */
                SobotApi.startSobotChat(VIPpageActivity.this, info);
                break;
        }
    }
}
