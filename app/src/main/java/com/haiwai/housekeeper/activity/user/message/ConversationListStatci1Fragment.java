package com.haiwai.housekeeper.activity.user.message;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.SysMessageActivity;
import com.haiwai.housekeeper.activity.user.ContactActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.SPUtils;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by range on 2018/1/15.
 */

public class ConversationListStatci1Fragment extends BaseFragment {
    private LinearLayout ll_ccgj, ll_sys_layout, ll_lxr;
    private ImageView iv_lxr_msg, iv_sys_msg,iv_back_arrow;

    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_fragment_message, container, false);
        initRootView(rootView);
        return rootView;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        iv_back_arrow= (ImageView) view.findViewById(R.id.iv_back_arrow);
        iv_back_arrow.setVisibility(View.VISIBLE);
        iv_back_arrow.setOnClickListener(this);
        ll_ccgj = (LinearLayout) view.findViewById(R.id.ll_ccgj);
        ll_ccgj.setOnClickListener(this);
        ll_sys_layout = (LinearLayout) view.findViewById(R.id.ll_sys_layout);
        ll_sys_layout.setOnClickListener(this);
        ll_lxr = (LinearLayout) view.findViewById(R.id.ll_lxr);
        ll_lxr.setOnClickListener(this);
        view.findViewById(R.id.user_msg_set).setOnClickListener(this);
        iv_lxr_msg = (ImageView) view.findViewById(R.id.iv_lxr_msg);
        iv_sys_msg = (ImageView) view.findViewById(R.id.iv_sys_msg);
    }

    private void initRootView(View rootView) {
        ConversationListFragment fragment = (ConversationListFragment) getChildFragmentManager().findFragmentById(R.id.conversationlist);
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        fragment.setUri(uri);
    }


    @Override
    protected void initData() {
        user = AppGlobal.getInstance().getUser();
        int system = SPUtils.getInt(getActivity(), "system", 0);
        int follow = SPUtils.getInt(getActivity(), "follow", 0);
        int pro_system = SPUtils.getInt(getActivity(), "pro_system", 0);
        if (system > 0 || pro_system > 0) {
            iv_sys_msg.setVisibility(View.VISIBLE);
        } else {
            iv_sys_msg.setVisibility(View.INVISIBLE);
        }
        if (follow > 0) {
            iv_lxr_msg.setVisibility(View.VISIBLE);
        } else {
            iv_lxr_msg.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        int system = SPUtils.getInt(getActivity(), "system", 0);
        int follow = SPUtils.getInt(getActivity(), "follow", 0);
        int pro_system = SPUtils.getInt(getActivity(), "pro_system", 0);
        if (system > 0 || pro_system > 0) {
            iv_sys_msg.setVisibility(View.VISIBLE);
        } else {
            iv_sys_msg.setVisibility(View.INVISIBLE);
        }
        if (follow > 0) {
            iv_lxr_msg.setVisibility(View.VISIBLE);
        } else {
            iv_lxr_msg.setVisibility(View.INVISIBLE);
        }
        super.onResume();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.ll_lxr:
                SPUtils.putInt(getActivity(), "follow", 0);
                SPUtils.putInt(getActivity(), "system", 0);
                SPUtils.putInt(getActivity(), "pro_system", 0);
                ActivityTools.goNextActivity(getActivity(), ContactActivity.class);
                break;
            case R.id.user_msg_set:
                ActivityTools.goNextActivity(getActivity(), ContactActivity.class);
                break;
            case R.id.ll_ccgj:
                Information info = new Information();
                info.setSysNum(Contants.SYSNUM);/* 必填 */
                info.setInitModeType(-1);//*****************************暂时之调用机器人-1
                info.setArtificialIntelligence(false);//转为人工true
                info.setArtificialIntelligenceNum(10);//只有人工true时起作用
                info.setUseVoice(true);//使用语音
                info.setColor("#FF0000");/* 选填，默认为"#09aeb0". 可以设置头部背景，提交评价背景，相似问题字体颜色和富文本类型中“阅读全文”字体颜色*/
                info.setUid(user.getUid());/* 选填，设置用户唯一标识 */
                info.setArtificialIntelligence(false);/* 智能转人工按钮，选填. 默认为false. 机器人客服优先模式时, false:显示转人工按钮；true:机器人有未知问题、引导回答时才显示转人工按钮 */
                SobotApi.startSobotChat(getActivity(), info);
                break;
            case R.id.ll_sys_layout:
                SPUtils.putInt(getActivity(), "system", 0);
                SPUtils.putInt(getActivity(), "pro_system", 0);
                ActivityTools.goNextActivity(getActivity(), SysMessageActivity.class);
                break;
            case R.id.iv_back_arrow:
                getActivity().finish();
                break;
        }
    }
}
