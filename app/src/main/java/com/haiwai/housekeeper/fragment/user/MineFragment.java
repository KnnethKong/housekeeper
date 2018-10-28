package com.haiwai.housekeeper.fragment.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.ProAddressActivity;
import com.haiwai.housekeeper.activity.server.ProMainActivity;
import com.haiwai.housekeeper.activity.server.earnings.MyEarningsActivity;
import com.haiwai.housekeeper.activity.user.BindPayPalAccount;
import com.haiwai.housekeeper.activity.user.MainActivity;
import com.haiwai.housekeeper.activity.user.MyWalletActivity;
import com.haiwai.housekeeper.activity.user.PersonInfoActivity;
import com.haiwai.housekeeper.activity.user.ProDetailActivity;
import com.haiwai.housekeeper.activity.user.RedWalletActivity;
import com.haiwai.housekeeper.activity.user.SetActivity;
import com.haiwai.housekeeper.activity.user.UseGuideActivity;
import com.haiwai.housekeeper.activity.user.VipHouseManageActivity;
import com.haiwai.housekeeper.activity.user.VipOrderActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseFragment;
import com.haiwai.housekeeper.entity.Defaultcontent;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CircleImageView;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.io.InputStream;


/**
 * Created by ihope007 on 2016/9/1.
 */
public class MineFragment extends BaseFragment {// TODO: 2016/11/29 我的图片更换英文
    private TextView tvNickName, tvNickMobile;
    private CircleImageView mCircleImageView;
    private User user;
    private boolean flag = false;
    private TextView tvchangePro;
    private ImageView ivMineHome;
    private ShareAction mShareAction;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                mCircleImageView.setImageResource(R.mipmap.user_icon);
            }
        }
    };
    private Bitmap mBitmap;
    private LinearLayout ll_pro_add;
    private String isZhorEn;
    private ShareBoardConfig config;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_fragment_mine, container, false);
        return rootView;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        tvchangePro = (TextView) view.findViewById(R.id.user_mine_tv_change_pro);
        ll_pro_add = (LinearLayout) view.findViewById(R.id.ll_pro_add);
        if (getActivity() instanceof MainActivity) {
            flag = true;
            ll_pro_add.setVisibility(View.GONE);
            tvchangePro.setText(getString(R.string.main_mine_change_to_pro));
        } else {
            flag = false;
            view.findViewById(R.id.user_mine_ll_my_paypal).setVisibility(View.VISIBLE);
            ll_pro_add.setVisibility(View.VISIBLE);
            tvchangePro.setText(getString(R.string.main_mine_change_to_user));
        }

        mCircleImageView = (CircleImageView) view.findViewById(R.id.user_mine_iv_icon);
        mCircleImageView.setOnClickListener(this);
        tvNickName = (TextView) view.findViewById(R.id.tv_nick_name);
        tvNickMobile = (TextView) view.findViewById(R.id.tv_nick_mobile);
        view.findViewById(R.id.mine_user_ll_name).setOnClickListener(this);
        view.findViewById(R.id.user_mine_tv_set).setOnClickListener(this);
        ivMineHome = (ImageView) view.findViewById(R.id.mine_user_ll_person_page);
        if ("en".equals(isZhorEn)) {
            ivMineHome.setImageResource(R.mipmap.wodezhuye_en);
        } else {
            ivMineHome.setImageResource(R.mipmap.wodezhuye);
        }
        ivMineHome.setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_vip_order).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_user_manage).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_my_wallet).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_red_wallet).setOnClickListener(this);

        view.findViewById(R.id.user_mine_ll_use_guide).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_change_pro).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_service).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_share).setOnClickListener(this);
        view.findViewById(R.id.user_mine_ll_my_paypal).setOnClickListener(this);
        view.findViewById(R.id.main_mine_pro_ll_location).setOnClickListener(this);

        config = new ShareBoardConfig();
        config.setCancelButtonVisibility(true);
        config.setTitleText(getString(R.string.share_title));
        config.setCancelButtonText(getString(R.string.cancel_select));
        UMImage image = new UMImage(getActivity(), R.mipmap.yjsc_logo);//bitmap文件
        mShareAction = new ShareAction(getActivity())
                .withText(Defaultcontent.text)
                .withMedia(image)
                .withTargetUrl(SPUtils.getString(getActivity(),"share_link",""))
                .withTitle(Defaultcontent.title)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new CustomShareListener());
    }

    @Override
    protected void initData() {
        user = AppGlobal.getInstance().getUser();
        if (FileUtils.getBitmap(SPUtils.getString(getActivity(), "local_url", null)) != null) {
            mCircleImageView.setImageBitmap(FileUtils.getBitmap(SPUtils.getString(getActivity(), "local_url", null)));
        } else {
            mCircleImageView.setImageResource(R.mipmap.user_icon);
            final String headurl = AppGlobal.getInstance().getUser().getAvatar();
            if (!TextUtils.isEmpty(headurl)) {
                new DownLoadImage().execute(headurl);
            }
        }
        if (user != null) {
            if(user.getNickname().equals("")){
                tvNickName.setText(getString(R.string.person_info_hint_username));
            }else{
                tvNickName.setText(user.getNickname());
            }
            tvNickMobile.setText(user.getMobile());
        }
    }

    public class DownLoadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap tmpBitmap = null;
            try {
                InputStream is = new java.net.URL(url).openStream();
                tmpBitmap = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tmpBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                mCircleImageView.setImageBitmap(result);
                String local_url = FileUtils.saveBitmap(result, "user_head");
                SPUtils.saveString(getActivity(), "local_url", local_url);
            }
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.user_mine_ll_my_paypal:
               // startActivity(new Intent(getContext(), BindPayPalAccount.class));
                startActivity(new Intent(getContext(), MyEarningsActivity.class));
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
                Intent intent = new Intent(getContext(),ProDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("uid", AppGlobal.getInstance().getUser().getUid());
                bundle.putString("nickname", AppGlobal.getInstance().getUser().getNickname());
//                bundle.putString("type", "1");
                bundle.putString("choose", "0");
                bundle.putString("oid", "");
                if (getActivity() instanceof MainActivity){
                    bundle.putString("type","1");
                }else{
                    bundle.putString("type","2");
                }
                intent.putExtra("fromO2O",true);
                intent.putExtras(bundle);
                startActivity(intent);
//                ActivityTools.goNextActivity(getActivity(), ProDetailActivity.class, bundle);
                break;
            case R.id.user_mine_ll_vip_order://vip订单
                ActivityTools.goNextActivity(getActivity(), VipOrderActivity.class);
                break;
            case R.id.user_mine_ll_user_manage://房产管理
                ActivityTools.goNextActivity(getActivity(), VipHouseManageActivity.class);
                break;
            case R.id.user_mine_ll_my_wallet://我的钱包
                ActivityTools.goNextActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.user_mine_ll_red_wallet://接单券--钱包福利
                ActivityTools.goNextActivity(getActivity(), RedWalletActivity.class);
                break;
            case R.id.user_mine_ll_use_guide:
                Bundle bundlea = new Bundle();
                if(flag){
                    bundlea.putString("flag", "user");
                }else {
                    bundlea.putString("flag", "server");
                }
                ActivityTools.goNextActivity(getActivity(), UseGuideActivity.class,bundlea);
                break;
            case R.id.user_mine_ll_change_pro:
                if (flag) {
//                    if (TextUtils.isEmpty(AppGlobal.getInstance().getAddr().getLat())
//                            && TextUtils.isEmpty(AppGlobal.getInstance().getAddr().getLongX())) {
//                        ActivityTools.goNextActivity(getActivity(), ProAddressActivity.class);
//                    } else {//地址不为空
//                        ToastUtil.shortToast(getContext(), AppGlobal.getInstance().getAddr().getLat() + ">>>>>>>" + AppGlobal.getInstance().getAddr().getLongX());
                    ActivityTools.goNextActivity(getActivity(), ProMainActivity.class);
//                    }
                } else {
                    Bundle serve_bundle = new Bundle();
                    serve_bundle.putString("flag", "serve");
                    ActivityTools.goNextActivity(getActivity(), MainActivity.class, serve_bundle);
                }
                break;
            case R.id.user_mine_ll_service:
                Information info = new Information();
                info.setSysNum(Contants.SYSNUM);/* 必填 */
                info.setInitModeType(1);//*****************************暂时之调用机器人,//1仅机器人 2仅人工 3机器人优先 4人工优先 -1服务器随机分配机器人或者人工服务
                info.setArtificialIntelligence(false);//转为人工true
                info.setArtificialIntelligenceNum(10);//只有人工true时起作用
                info.setUseVoice(true);//使用语音
                info.setColor("#FF0000");/* 选填，默认为"#09aeb0". 可以设置头部背景，提交评价背景，相似问题字体颜色和富文本类型中“阅读全文”字体颜色*/
                info.setUid(user.getUid());/* 选填，设置用户唯一标识 */
                info.setArtificialIntelligence(false);/* 智能转人工按钮，选填. 默认为false. 机器人客服优先模式时, false:显示转人工按钮；true:机器人有未知问题、引导回答时才显示转人工按钮 */
                SobotApi.startSobotChat(getActivity(), info);
                break;
            case R.id.user_mine_ll_share://分享给好友
                mShareAction.open(config);
                break;
            case R.id.main_mine_pro_ll_location:
                ActivityTools.goNextActivity(getActivity(), ProAddressActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private class CustomShareListener implements UMShareListener {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtil.longToast(getActivity(), " 分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable t) {
            ToastUtil.longToast(getActivity(), " 分享失败啦");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            ToastUtil.longToast(getActivity(), " 分享取消了");
        }
    }
}
