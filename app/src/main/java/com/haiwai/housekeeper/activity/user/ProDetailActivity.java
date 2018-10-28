package com.haiwai.housekeeper.activity.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.activity.base.MapBoxMapActivity;
import com.haiwai.housekeeper.activity.server.ProSkillDetailActivity;
import com.haiwai.housekeeper.adapter.CommentAdapter;
import com.haiwai.housekeeper.adapter.SkillAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.MyApp;
import com.haiwai.housekeeper.entity.CommnetEntity;
import com.haiwai.housekeeper.entity.Defaultcontent;
import com.haiwai.housekeeper.entity.ImageListEntity;
import com.haiwai.housekeeper.entity.Parameter;
import com.haiwai.housekeeper.entity.ProDetailEntity;
import com.haiwai.housekeeper.entity.SigGogleEntity;
import com.haiwai.housekeeper.entity.StarEntity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.https.PlatRequest;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ErrorCodeUtils;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.HttpManager;
import com.haiwai.housekeeper.utils.ImageLoaderUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LocationUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SPUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.CircleImageView;
import com.haiwai.housekeeper.view.MyListView;
import com.haiwai.housekeeper.view.ProGradeView;
import com.haiwai.housekeeper.view.WheelPopView;
import com.haiwai.housekeeper.widget.CustomDialog;
import com.haiwai.housekeeper.widget.LoadDialog;
import com.haiwai.housekeeper.widget.RoundImageView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.joooonho.SelectableRoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

import static com.haiwai.housekeeper.base.MyApp.context;

/**
 * Created by ihope007 on 2016/9/13.
 */
public class ProDetailActivity extends BaseActivity {
    private ImageView ivlike;
    private ImageButton o2o_collapse_all, o2o_collapse_order;
//    private ObservableScrollView observableScrollView;
    private LinearLayout llFloat;
    private int lasty = 0;
    private LinearLayout llInvite;
    private TextView tvskill, tvevaluate;

    private PopupWindow pop;

    private boolean isLiaoWithThis = false;
    private int isLiaoWithOhter;

    private SelectableRoundedImageView s1, s2, s3;
    private String url1 = "";
    private String url2 = "";
    private String url3 = "";

    private String desc1 = "";
    private String desc2 = "";
    private String desc3 = "";

    private String time1 = "";
    private String time2 = "";
    private String time3 = "";
    //传过来的值
    private String uid;
    private String type;
    private String title;
    private String choose;
    private String oid;
    //个人信息
    private CircleImageView ivhead;
    private TextView tvdanping;
    private LinearLayout llphoto;
    private TextView tviconsf, tviconjn;
    private TextView ivpicmore;
    private boolean isUpload = false;
    //技能
    private MyListView mlvskill;
    private SkillAdapter skillAdapter;
    //评论
    private MyListView mlvcomment;
    private CommentAdapter commentAdapter;
    private ImageView ivcommentmore;

    private String user_id = "";
    private String nickname = "";

    private PullToRefreshScrollView s;
    Map<String, String> starMap = new HashMap<>();
    boolean isServer = false;
    User user;
    Map<String, String> gzMap = new HashMap<>();
    Map<String, String> jbMap = new HashMap<>();
    private String isZhorEn = "";
    ProDetailReceiver receiver;
    private ShareAction mShareAction;
    private ShareBoardConfig config;
    List<ImageListEntity.DataBean> piclist = new ArrayList<>();
    private ProDetailEntity.DataBean.SkillBean mSkill;
    private ProDetailEntity.DataBean.InfoBean mInfo;
    private ProDetailEntity mEntity;
    SigGogleEntity mSigGogleEntity;
    private ImageView iv_level;
    private RelativeLayout rl_pro_main_view;

    private String comType;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_pro_detail, null);
    }

    private TextView tvempty;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitlebarHide(true);

        if(getIntent().getBooleanExtra("fromO2O",false)){
            comType = "2";
        }else{
            comType = "1";
        }
        commentAdapter = new CommentAdapter(this,new ArrayList<CommnetEntity.DataBean>(),starMap);
        mlvcomment = (MyListView) findViewById(R.id.pro_detail_lv_comment);
        tvempty = (TextView) findViewById(R.id.pro_detail_tv_comment_empty);
        mlvcomment.setAdapter(commentAdapter);
        receiver = new ProDetailReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("newData");
        iv_level = ((ImageView) findViewById(R.id.iv_user_de_level));
        registerReceiver(receiver, intentFilter);
        RelativeLayout rl_bg = (RelativeLayout) findViewById(R.id.user_pro_detail_title);
        rl_bg.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, PlatUtils.getImageRario(this, 7, 16)));
        findViewById(R.id.pro_detail_iv_back).setOnClickListener(this);
        o2o_collapse_all = (ImageButton) findViewById(R.id.o2o_collapse_all);
        type = getIntent().getExtras().get("type").toString();
        rl_pro_main_view = ((RelativeLayout) findViewById(R.id.rl_pro_main_view));
        findViewById(R.id.pro_detail_tv_km).setOnClickListener(this);
        mShareAction = new ShareAction(ProDetailActivity.this)
                .withText(Defaultcontent.text)
//                .withMedia(imageurl)
                .withTargetUrl(SPUtils.getString(ProDetailActivity.this,"share_link",""))
                .withTitle(Defaultcontent.title)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new CustomShareListener());

        config = new ShareBoardConfig();
        config.setCancelButtonText(getString(R.string.cancel_share));
        config.setTitleText(getString(R.string.select_proof));

        o2o_collapse_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WheelPopView wheelPopView = new WheelPopView(ProDetailActivity.this, isZhorEn);
                wheelPopView.openPopWindow(view);
                wheelPopView.setDatePicOnClickListener(new WheelPopView.DatePicOnClickListener() {
                    @Override
                    public void datePicker(final String str) {
                        CustomDialog.Builder customBuilder = new CustomDialog.Builder(ProDetailActivity.this);
                        customBuilder.setMessage(getString(R.string.pro_setc)).setPositiveButton(getString(R.string.message_alert_yes),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        jubfws(str);
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton(getString(R.string.message_alert_no),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).create().show();

                    }
                });
            }
        });
        o2o_collapse_order = (ImageButton) findViewById(R.id.o2o_collapse_order);
        o2o_collapse_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShareAction.open(config);
            }
        });
        ivpicmore = (TextView) findViewById(R.id.pro_detail_more_pic);
        ivpicmore.setOnClickListener(this);
//        observableScrollView = (ObservableScrollView) findViewById(R.id.pro_detail_obscrl);
        llFloat = (LinearLayout) findViewById(R.id.pro_detail_ll_float);
        ivlike = (ImageView) findViewById(R.id.pro_detail_iv_like);
        tvskill = (TextView) findViewById(R.id.pro_detail_tv_skill);
        tvevaluate = (TextView) findViewById(R.id.pro_detail_tv_evaluate);
        initFloat(1);
        findViewById(R.id.pro_detail_iv_msg).setOnClickListener(this);
        findViewById(R.id.pro_detail_ll_like).setOnClickListener(this);
        findViewById(R.id.pro_detail_skill).setOnClickListener(this);
        tviconsf = (TextView) findViewById(R.id.pop_renzheng_tv_sf);
        tviconjn = (TextView) findViewById(R.id.pop_renzheng_tv_jn);
        tviconsf.setOnClickListener(this);
        tviconjn.setOnClickListener(this);

        mlvskill = (MyListView) findViewById(R.id.pro_detail_lv_skill);
        uid = getIntent().getExtras().getString("uid");
        Log.e("uidInformation--",uid);
        if (uid.equals(AppGlobal.getInstance().getUser().getUid())) {
            o2o_collapse_all.setVisibility(View.GONE);
            llFloat.setVisibility(View.GONE);
            findViewById(R.id.pro_detail_skill).setVisibility(View.GONE);
            findViewById(R.id.tv_word_no_skills).setVisibility(View.VISIBLE);
        } else {
            o2o_collapse_all.setVisibility(View.VISIBLE);
            llFloat.setVisibility(View.VISIBLE);
            findViewById(R.id.pro_detail_skill).setVisibility(View.GONE);
            findViewById(R.id.tv_word_no_skills).setVisibility(View.VISIBLE);
        }
//        requestSkill();
        mlvskill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProDetailActivity.this,ProSkillDetailActivity.class);
                if(!AppGlobal.getInstance().getUser().getUid().equals(mEntity.getData().getInfo().getUid())){
                    if(!TextUtils.isEmpty(type)){
                        intent.putExtra("type", skilList.get(i).getType());
                        intent.putExtra("id", skilList.get(i).getId());
                    }else{
                        intent.putExtra("type", skillBeanList.get(i).getType());
                        intent.putExtra("id", skillBeanList.get(i).getId());
                    }
                }else{
                    intent.putExtra("type", skillBeanList.get(i).getType());
                    intent.putExtra("id", skillBeanList.get(i).getId());
                }
                intent.putExtra("isfromSkill",true);
                startActivity(intent);
            }
        });

        llInvite = (LinearLayout) findViewById(R.id.user_pro_detail_ll_bottom);
        llInvite.setOnClickListener(this);

        s1 = (SelectableRoundedImageView) findViewById(R.id.pro_detail_si_1);
        s2 = (SelectableRoundedImageView) findViewById(R.id.pro_detail_si_2);
        s3 = (SelectableRoundedImageView) findViewById(R.id.pro_detail_si_3);
        s1.setOnClickListener(this);
        s2.setOnClickListener(this);
        s3.setOnClickListener(this);

        //个人信息
        ivhead = (CircleImageView) findViewById(R.id.pro_detail_iv_head);
        tvdanping = (TextView) findViewById(R.id.pro_detail_tv_dan_ping);//完成单数/评分
        llphoto = (LinearLayout) findViewById(R.id.pro_detail_ll_photo);//完成单数/评分


        ivcommentmore = (ImageView) findViewById(R.id.pro_detail_iv_comment_more);
        ivcommentmore.setOnClickListener(this);

        s = ((PullToRefreshScrollView) findViewById(R.id.pro_detail_obscrl));

//        s.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);

        s.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_END);
        s.setOnRefreshListener(new com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase<ScrollView> refreshView) {
                s.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase<ScrollView> refreshView) {
                page++;
                getComment();
            }
        });


    }

//    private void CheckLiao() {
//        LoadDialog.showProgressDialog(ProDetailActivity.this);
//        Map<String, String> map = new HashMap<>();
//        map.put("pro_id", uid);
//        map.put("duid", user.getUid());
//        map.put("secret_key", SPUtils.getString(ProDetailActivity.this, "secret", ""));
//        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
//        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProDetailActivity.this, Contants.liao, map, null, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                LoadDialog.closeProgressDialog();
//                int code = JsonUtils.getJsonInt(response, "status");
//                if (code == 200) {
//                    int liao = JsonUtils.getJsonInt(response, "liao");
//                    if (liao == 0) {
//                        ToastUtil.longToast(ProDetailActivity.this, getString(R.string.nede_ti));
//                    } else {
//                        String targetId = uid;
//                        String title = nickname;
//                        if (RongIM.getInstance() != null) {
//                            /**
//                             * 启动单聊界面。
//                             *
//                             * @param context      应用上下文。
//                             * @param targetUserId 要与之聊天的用户 Id。
//                             * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
//                             */
//                            RongIM.getInstance().startPrivateChat(ProDetailActivity.this, targetId, title);
//                            RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));
//                            RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));
//                        }
//                    }
//
//                } else {
//                    LoadDialog.closeProgressDialog();
//                    ToastUtil.longToast(ProDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
//                }
//            }
//        }));
//    }



    private void jubfws(String str) {
        jbMap.put("j_uid", user.getUid());//举报人id
        jbMap.put("uid", uid);//服务商id
        jbMap.put("type", str);
        jbMap.put("content", "");
        jbMap.put("secret_key", SPUtils.getString(ProDetailActivity.this, "secret", ""));
        jbMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProDetailActivity.this, Contants.report, jbMap, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");
                if (code == 200) {
                    ToastUtil.longToast(ProDetailActivity.this, getString(R.string.jb_se));
                } else {
                    ToastUtil.longToast(ProDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private List<ProDetailEntity.DataBean.SkillBean> skillBeanList;
    private List<ProDetailEntity.DataBean.SkillBean> skilList;



    private void requestSkill() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("secret_key", SPUtils.getString(ProDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(ProDetailActivity.this, Contants.user_detail, map, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    Log.i("skillResponseInfomation",response);
                    int code = JsonUtils.getJsonInt(response, "status");
                    if (code == 200) {
                        skillBeanList = JsonUtils.parseProSkill(response);
                        skilList = new ArrayList<>();
                      if(AppGlobal.getInstance().getUser().getUid().equals(mEntity.getData().getInfo().getUid())){
                          skillAdapter = new SkillAdapter(ProDetailActivity.this, skillBeanList);
                        }else{
                          if(!TextUtils.isEmpty(type)){
                              if(skillBeanList.size()>0){
                                  for(int i=0;i<skillBeanList.size();i++){
                                      if(type.equals(skillBeanList.get(i).getType())){
                                          skilList.add(skillBeanList.get(i));
                                      }
                                  }
                                  skillAdapter = new SkillAdapter(ProDetailActivity.this, skilList);
                              }else{
                                  skillAdapter = new SkillAdapter(ProDetailActivity.this, skillBeanList);
                              }
                          }else{
                              skillAdapter = new SkillAdapter(ProDetailActivity.this, skillBeanList);
                          }
                      }
                        mlvskill.setAdapter(skillAdapter);

                    } else {
                        ToastUtil.shortToast(ProDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (lasty == msg.what) {
//                if (llFloat.getVisibility() != View.VISIBLE)
//                    llFloat.setVisibility(View.VISIBLE);
//            } else {
//                if (llFloat.getVisibility() != View.GONE)
//                    llFloat.setVisibility(View.GONE);
//            }
//        }
//    };

    private void initFloat(int i) {
        ivlike.setSelected(i == 2 ? true : false);
    }


    private void getData(){
        initLocationData();
        isZhorEn = AppGlobal.getInstance().getLagStr();
        user = AppGlobal.getInstance().getUser();
        uid = getIntent().getExtras().get("uid").toString();
        if(getIntent().getExtras().get("nickname")!=null) {
            nickname = getIntent().getExtras().get("nickname").toString();
        }
        gzMap.put("fuid", user.getUid());
        gzMap.put("uid", uid);


        isLiaoWithOhter = getIntent().getExtras().getInt("isLiao",-1);
        if(isLiaoWithOhter==-1){
            isLiaoWithThis = false;

        }else{
            isLiaoWithThis = true;

        }

        gzMap.put("secret_key", SPUtils.getString(ProDetailActivity.this, "secret", ""));
        gzMap.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());

        choose = getIntent().getExtras().get("choose").toString();
        oid = getIntent().getExtras().get("oid").toString();
        IMKitService.lagMap.put("uid", uid);
        IMKitService.lagMap.put("nickname", nickname);
        IMKitService.lagMap.put("type", type);
        IMKitService.lagMap.put("choose", choose);
        IMKitService.lagMap.put("oid", oid);
        isServer = getIntent().getExtras().getBoolean("isServer", false);
        requestPrDetail();
        if (isServer) {
            llInvite.setVisibility(View.INVISIBLE);
        } else {
            llInvite.setVisibility(View.VISIBLE);
        }

        if(getIntent().getExtras().getString("isFromContact","-1").equals("1")){
            findViewById(R.id.user_pro_detail_ll_bottom).setVisibility(View.GONE);
        }

        if ("1".equals(choose)) {
            ((TextView) findViewById(R.id.o2o_detail_tv_bottom)).setText("选TA");
        }
        title = AssetsUtils.getSkillName(type, isZhorEn);
        if (uid.equals(AppGlobal.getInstance().getUser().getUid())) {
            llInvite.setVisibility(View.GONE);
            llFloat.setVisibility(View.GONE);
            tvskill.setText(getString(R.string.pro_detail_my_skill));
            tvevaluate.setText(getString(R.string.pro_detail_my_evaluate));
        }
    }

    @Override
    protected void initData() {

        getData();

    }



    private void initLocationData() {
        LocationUtils locationUtils = new LocationUtils(ProDetailActivity.this);
        String str = locationUtils.getGeoStr();
        mSigGogleEntity = LocationUtils.parStr(str);
    }

    public void requestPrDetail() {
        LogUtil.e("type", type);
        LoadDialog.showProgressDialog(this);
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("duid", TextUtils.isEmpty(user.getUid()) ? "" : user.getUid());
        map.put("type", type);
        map.put("lat",  mSigGogleEntity.getLat());
        map.put("long", mSigGogleEntity.getLng());
        map.put("secret_key", SPUtils.getString(ProDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(ProDetailActivity.this, Contants.pro_detail, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(">>>>>>>>>>>pro>>>>>>" + response);
                try {
                    s.onRefreshComplete();
                    int code = JsonUtils.getJsonInt(response, "status");

                    if (code == 200) {
                        mEntity = JsonUtils.parseProDetail(response);
                        if(mEntity.getData().getLiao()==1){
                            ((ImageView) findViewById(R.id.pro_detail_iv_msg)).setImageResource(R.mipmap.pro_detail_float_talk_1);
                        }else{
                            ((ImageView) findViewById(R.id.pro_detail_iv_msg)).setImageResource(R.mipmap.pro_detail_float_talk_0);
                        }
                        String level = new JSONObject(response).getJSONObject("data").getJSONObject("info").getString("s");

                        if (level.equals("1")) {
                            ImageLoader.getInstance().displayImage("drawable://" + R.mipmap.v_icon, iv_level);
                        } else if (level.equals("2")) {
                            ImageLoader.getInstance().displayImage("drawable://" + R.mipmap.s_icon, iv_level);
                        }else{
                            ProGradeView proGradeView = (ProGradeView) findViewById(R.id.pro_detail_v_prograde);
                            proGradeView.setNum(Integer.valueOf(mEntity.getData().getInfo().getPro_score()));
                        }
                        bindDataToView(mEntity);
                        requestSkill();
                        requestLabel(mEntity);
                    } else {
                        ToastUtil.shortToast(ProDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void requestLabel(final ProDetailEntity entity) {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", SPUtils.getString(ProDetailActivity.this, "secret", ""));
        map.put("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey());
        mRequestQueue.add(new PlatRequest(this, Contants.order_label, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int code = JsonUtils.getJsonInt(response, "status");// TODO: 2016/9/12 标签 更换
                if (code == 200) {
                    StarEntity starEntity = new Gson().fromJson(response, StarEntity.class);
                    starMap = JsonUtils.parseEvaluateLabel(starEntity);
                    getComment();
                } else {
                    ToastUtil.shortToast(ProDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));

    }

    private int page=1;
    private void getComment(){
        if(AppGlobal.getInstance().getUser().getUid()!=null){
            if(AppGlobal.getInstance().getUser().getUid().equals(mEntity.getData().getInfo().getUid())){
                comType = "2";
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("uid",getIntent().getStringExtra("uid"));
        map.put("page",page+"");
        map.put("page_size","10");
        map.put("type",comType);
        map.put("secret_key", SPUtils.getString(ProDetailActivity.this, "secret", ""));
        mRequestQueue.add(new PlatRequest(this, Contants.user_comment, map, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LoadDialog.closeProgressDialog();
                s.onRefreshComplete();
                int code = JsonUtils.getJsonInt(response, "status");// TODO: 2016/9/12 标签 更换
                if (code == 200) {
                    CommnetEntity entity = new Gson().fromJson(response,CommnetEntity.class);
                        bindData(entity);
                } else {
                    ToastUtil.shortToast(ProDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }
        }));
    }

    private List<CommnetEntity.DataBean> comment = new ArrayList<>();

    private void bindData(CommnetEntity entity) {
        //comment
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                s.getRefreshableView().smoothScrollTo(0,0);
                rl_pro_main_view.setVisibility(View.VISIBLE);
            }
        });
        comment.addAll(entity.getData());
        if(entity.getData().size()!=0){
            commentAdapter.setListData(entity.getData(),starMap);
        }
        s.getRefreshableView().scrollTo(0,0);
        s.getRefreshableView().smoothScrollTo(0,0);
        if(comment.size()==0){

        }
        Log.i("tvEmptyInformation",comment.size()+"");
        if(comment.size()==0){
            tvempty.setVisibility(View.VISIBLE);
        }else{
            tvempty.setVisibility(View.GONE);
        }
    }

    private void bindDataToView(ProDetailEntity entity) {
        user_id = entity.getData().getInfo().getUid();
        //info
        mInfo = entity.getData().getInfo();
        if (mInfo != null) {
//            if (mInfo.s.equals("1")) {
//                ImageLoader.getInstance().displayImage("drawable://" + R.mipmap.v_icon, iv_level);
//            } else if (mInfo.s.equals("2")) {
//                ImageLoader.getInstance().displayImage("drawable://" + R.mipmap.s_icon, iv_level);
//            }
            ImageLoader.getInstance().displayImage(mInfo.getAvatar(), ivhead, ImageLoaderUtils.getAvatarOptions());
            if (!TextUtils.isEmpty(mInfo.getNickname()))
                ((TextView) findViewById(R.id.pro_detail_tv_nickname)).setText(mInfo.getNickname());
            if (!TextUtils.isEmpty(mInfo.getAddress()))
                ((TextView) findViewById(R.id.pro_detail_tv_addr)).setText(mInfo.getAddress());
            if(mInfo.getUid().equals(AppGlobal.getInstance().getUser().getUid())){
                ((TextView) findViewById(R.id.pro_detail_tv_km)).setText("0.0km");
            }else{
                if (mInfo.getKm() != null && !"null".equals(mInfo.getKm()))
                    ((TextView) findViewById(R.id.pro_detail_tv_km)).setText(mInfo.getKm()+ "km");
                else
                    ((TextView) findViewById(R.id.pro_detail_tv_km)).setText("0.0km");
            }

            if (TextUtils.isEmpty(mInfo.getIntroduction())) {
                ((TextView) findViewById(R.id.pro_detail_tv_intro)).setText(R.string.ts);
            } else {
                ((TextView) findViewById(R.id.pro_detail_tv_intro)).setText(mInfo.getIntroduction());
            }
            if (mInfo.getIs_guan() == 0) {
                ivlike.setSelected(false);
            } else if (mInfo.getIs_guan() == 1) {
                ivlike.setSelected(true);
            }
//            ProGradeView proGradeView = (ProGradeView) findViewById(R.id.pro_detail_v_prograde);
//            proGradeView.setNum(Integer.valueOf(mInfo.getPro_score()));
            if(user_id.equals(AppGlobal.getInstance().getUser().getUid())){
                if (TextUtils.isEmpty(mInfo.getPro_onum()) || "0".equals(mInfo.getPro_onum())) {
                    tvdanping.setText(getString(R.string.pro_detail_no_evaluate));
                } else {
                    String pingFen="";
                    if(Integer.valueOf(mInfo.getPro_onum())==0){
                        pingFen = "0.0";

                    }else{
                        pingFen = String.format("%.1f",Float.valueOf(mInfo.getPro_xing()) / Integer.valueOf(mInfo.getPro_onum()));

                    }
                    if(Integer.parseInt(mInfo.getPro_onum())>1){
                        tvdanping.setText(getString(R.string.o2o_detail_has_done) + mInfo.getPro_onum() + getString(R.string.o2o_detail_dan_pingjias) + pingFen);// TODO: 2016/10/19 技能右上角是什么  还有评价是哪个字段
                    }else {
                        tvdanping.setText(getString(R.string.o2o_detail_has_done) + mInfo.getPro_onum() + getString(R.string.o2o_detail_dan_pingjia) + pingFen);// TODO: 2016/10/19 技能右上角是什么  还有评价是哪个字段
                    }
                }
            }else{
                if(!getIntent().getBooleanExtra("fromO2O",false)){
                    if (TextUtils.isEmpty(mInfo.getUser_onum()) || "0".equals(mInfo.getUser_onum())) {
                        tvdanping.setText(getString(R.string.o2o_detail_has_done) + "0" + getString(R.string.pro_detail_no_evaluate));
                    } else {
                        String pingFen = String.format("%.1f",Float.valueOf(mInfo.getUser_xing()) / Integer.valueOf(mInfo.getUser_onum()));
                       if(Integer.parseInt(mInfo.getUser_onum())>1){
                           tvdanping.setText(getString(R.string.o2o_detail_has_done) + mInfo.getUser_onum() + getString(R.string.o2o_detail_dan_pingjias) + pingFen);// TODO: 2016/10/19 技能右上角是什么  还有评价是哪个字段
                       }else {
                           tvdanping.setText(getString(R.string.o2o_detail_has_done) + mInfo.getUser_onum() + getString(R.string.o2o_detail_dan_pingjia) + pingFen);// TODO: 2016/10/19 技能右上角是什么  还有评价是哪个字段
                       }
                    }
                }else{
                    if (TextUtils.isEmpty(mInfo.getPro_onum()) || "0".equals(mInfo.getPro_onum())) {
                        tvdanping.setText(getString(R.string.o2o_detail_has_done) + "0" + getString(R.string.pro_detail_no_evaluate));
                    } else {
                        String pingFen = String.format("%.1f",Float.valueOf(mInfo.getPro_xing()) / Integer.valueOf(mInfo.getPro_onum()));
                       if(Integer.parseInt(mInfo.getPro_onum())>1){
                           tvdanping.setText(getString(R.string.o2o_detail_has_done) + mInfo.getPro_onum() + getString(R.string.o2o_detail_dan_pingjias) + pingFen);// TODO: 2016/10/19 技能右上角是什么  还有评价是哪个字段
                       }else {
                           tvdanping.setText(getString(R.string.o2o_detail_has_done) + mInfo.getPro_onum() + getString(R.string.o2o_detail_dan_pingjia) + pingFen);// TODO: 2016/10/19 技能右上角是什么  还有评价是哪个字段
                       }
                    }
                }
            }





            if (!TextUtils.isEmpty(mInfo.getIs_ren()) && "1".equals(mInfo.getIs_ren())) {
                tviconjn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.pro_detail_jnrz), null, null);
                tviconjn.setCompoundDrawablePadding(EvmUtil.dip2px(ProDetailActivity.this, 15));
                tviconjn.setClickable(true);
            } else {
                tviconjn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.pro_detail_jnrz_grey), null, null);
                tviconjn.setCompoundDrawablePadding(EvmUtil.dip2px(ProDetailActivity.this, 15));
                tviconjn.setClickable(false);
            }

        }

        //skill
        mSkill = entity.getData().getSkill();
        if (mSkill != null) {
            ((TextView) findViewById(R.id.pro_detail_tv_type)).setText(title);
            ((TextView) findViewById(R.id.pro_detail_tv_price)).setText("$" + mSkill.getMoney_p() + getString(R.string.o2o_detail_xie_pingmi));// TODO: 2016/10/19 价钱为0显示另一句话
            if (mSkill.getClassX() != null)
                ((TextView) findViewById(R.id.pro_detail_tv_class)).setText(mSkill.getClassX());
            Log.i("isRen-information--",mSkill.getIs_audit()+"---"+mSkill.getIs_ren());

        }
        //idcard
        ProDetailEntity.DataBean.IdcardBean idcard = entity.getData().getIdcard();

        if (idcard != null) {
            if ("1".equals(idcard.getIdcard_ren())) {
                tviconsf.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.pro_detail_sfrz), null, null);
                tviconsf.setCompoundDrawablePadding(EvmUtil.dip2px(ProDetailActivity.this, 15));
                tviconsf.setClickable(true);
            } else {
                tviconsf.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.pro_detail_sfrz_grey), null, null);
                tviconsf.setCompoundDrawablePadding(EvmUtil.dip2px(ProDetailActivity.this, 15));
                tviconsf.setClickable(false);
            }
        } else {
            tviconsf.setClickable(false);
        }

        //photo
        List<ProDetailEntity.DataBean.PhotoBean> photo = entity.getData().getPhoto();
        LogUtil.e("photo", photo + "");
        if (photo == null || photo.size() == 0) {//没有图片
            if (!uid.equals(AppGlobal.getInstance().getUser().getUid())) {
                isUpload = false;
                llphoto.setVisibility(View.GONE);
//                findViewById(R.id.pro_detail_tv_km).setVisibility(View.GONE);
            } else {
                isUpload = true;
                findViewById(R.id.pro_detail_ll_photo_inside).setVisibility(View.GONE);
                ivpicmore.setCompoundDrawables(null, null, null, null);
                ivpicmore.setText(R.string.issue_require_upload_pic);
                findViewById(R.id.pro_detail_tv_km).setVisibility(View.VISIBLE);
            }
        } else {
            if (photo.size() == 1) {//一张图片
                findViewById(R.id.pro_detail_ll_photo_inside).setVisibility(View.VISIBLE);
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.INVISIBLE);
                s3.setVisibility(View.INVISIBLE);
                url1 = photo.get(0).getImg();
                time1 = photo.get(0).getCtime();
                desc1 = photo.get(0).getDesc();
                ImageLoader.getInstance().displayImage(url1, s1);
                if (!uid.equals(AppGlobal.getInstance().getUser().getUid())) {
                    ivpicmore.setVisibility(View.GONE);
                } else {
                    ivpicmore.setVisibility(View.VISIBLE);
                    Drawable img = getResources().getDrawable(R.mipmap.pic_right_arrow);
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    ivpicmore.setCompoundDrawables(null, null, img, null);
                    ivpicmore.setText(R.string.more);
                    isUpload = false;
                }
            } else if (photo.size() == 2) {//两张图片
                findViewById(R.id.pro_detail_ll_photo_inside).setVisibility(View.VISIBLE);
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.INVISIBLE);
                url1 = photo.get(0).getImg();
                time1 = photo.get(0).getCtime();
                desc1 = photo.get(0).getDesc();
                url2 = photo.get(1).getImg();
                time2 = photo.get(1).getCtime();
                desc2 = photo.get(1).getDesc();
                ImageLoader.getInstance().displayImage(url1, s1);
                ImageLoader.getInstance().displayImage(url2, s2);
                if (!uid.equals(AppGlobal.getInstance().getUser().getUid())) {
                    ivpicmore.setVisibility(View.GONE);
                } else {
                    ivpicmore.setVisibility(View.VISIBLE);
                    Drawable img = getResources().getDrawable(R.mipmap.pic_right_arrow);
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    ivpicmore.setCompoundDrawables(null, null, img, null);
                    ivpicmore.setText(R.string.more);
                    isUpload = false;
                }
            } else if (photo.size() == 3) {//三张图片
                findViewById(R.id.pro_detail_ll_photo_inside).setVisibility(View.VISIBLE);
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);
                url1 = photo.get(0).getImg();
                time1 = photo.get(0).getCtime();
                desc1 = photo.get(0).getDesc();
                url2 = photo.get(1).getImg();
                time2 = photo.get(1).getCtime();
                desc2 = photo.get(1).getDesc();
                url3 = photo.get(2).getImg();
                time3 = photo.get(2).getCtime();
                desc3 = photo.get(2).getDesc();
                ImageLoader.getInstance().displayImage(url1, s1);
                ImageLoader.getInstance().displayImage(url2, s2);
                ImageLoader.getInstance().displayImage(url3, s3);
                if (!uid.equals(AppGlobal.getInstance().getUser().getUid())) {
                    ivpicmore.setVisibility(View.GONE);
                } else {
                    ivpicmore.setVisibility(View.VISIBLE);
                    Drawable img = getResources().getDrawable(R.mipmap.pic_right_arrow);
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    ivpicmore.setCompoundDrawables(null, null, img, null);
                    ivpicmore.setText(R.string.more);
                    isUpload = false;
                }
            } else {//大于三张图片
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);
                url1 = photo.get(0).getImg();
                time1 = photo.get(0).getCtime();
                desc1 = photo.get(0).getDesc();
                url2 = photo.get(1).getImg();
                time2 = photo.get(1).getCtime();
                desc2 = photo.get(1).getDesc();
                url3 = photo.get(2).getImg();
                time3 = photo.get(2).getCtime();
                desc3 = photo.get(2).getDesc();
                ImageLoader.getInstance().displayImage(url1, s1);
                ImageLoader.getInstance().displayImage(url2, s2);
                ImageLoader.getInstance().displayImage(url3, s3);
                ivpicmore.setVisibility(View.VISIBLE);
                Drawable img = getResources().getDrawable(R.mipmap.pic_right_arrow);
                img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                ivpicmore.setCompoundDrawables(null, null, img, null);
                ivpicmore.setText(R.string.more);
                isUpload = false;
            }
            piclist.clear();
            for (int i = 0; i < photo.size(); i++) {
                ImageListEntity.DataBean dataBean = new ImageListEntity.DataBean();
                dataBean.setId(photo.get(i).getId());
                dataBean.setUid(photo.get(i).getUid());
                dataBean.setImg(photo.get(i).getImg());
                dataBean.setDesc(photo.get(i).getDesc());
                dataBean.setCtime(photo.get(i).getCtime());
                piclist.add(dataBean);
            }
        }


    }

    public void requestChooseServe() {

        List<Parameter> list = new ArrayList<>();
        list.add(new Parameter("oid", oid));
        list.add(new Parameter("uid", AppGlobal.getInstance().getUser().getUid()));
        list.add(new Parameter("j_uid", uid));
        list.add(new Parameter("secret_key", SPUtils.getString(ProDetailActivity.this, "secret", "")));
        list.add(new Parameter("login_key", AppGlobal.getInstance().getLoginKey() == null ? "" : AppGlobal.getInstance().getLoginKey()));
        list.add(new Parameter("deng_uid", AppGlobal.getInstance().getUser().getUid()));
        HttpManager.getInstance().post(list, Contants.order_choice, 100, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpRequestSuccess(int requestCode, int resultCode, String resultData, String resultJson) {
                int code = JsonUtils.getJsonInt(resultJson, "status");
                LoadDialog.closeProgressDialog();
                if (code == 200) {
                    ToastUtil.shortToast(ProDetailActivity.this, getString(R.string.commit_success));
                    Bundle choose_bundle = new Bundle();
                    choose_bundle.putString("flag", "order_success");
                    ActivityTools.goNextActivity(ProDetailActivity.this, MainActivity.class, choose_bundle);
                } else {
                    ToastUtil.shortToast(context, ErrorCodeUtils.getRegisterError(code + ""));
                }
            }

            @Override
            public void onHttpRequestError(int requestCode, Exception exception) {

            }
        });
    }

    /**
     * 技能认证弹窗
     */
    public void showPopWindow(View view, int type) {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.pop_skill, null);
        TextView tv = (TextView) v.findViewById(R.id.pop_skill_tv);
        if (1 == type) {
            tv.setText(getString(R.string.pro_detail_has_sfrz_book));
        } else {
            tv.setText(getString(R.string.pro_detail_has_jnrz_book));
        }
        pop = new PopupWindow(v, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.showAsDropDown(view);
    }

    @Override
    protected void click(View v) {
        Bundle pic_bundle;
        switch (v.getId()) {
            case R.id.pro_detail_tv_km:
                Intent in = new Intent(ProDetailActivity.this, MapBoxMapActivity.class);
                in.putExtra("lat",mInfo.getLat());
                in.putExtra("lng",mInfo.getLongX());
                in.putExtra("isMap",true);
                startActivity(in);
                break;
            case R.id.pro_detail_iv_msg:
                if(!isLiaoWithThis){
                    if (mEntity.getData().getLiao()!=0) {
                        String targetId = uid;
                        String title = nickname;
                        if (RongIM.getInstance() != null) {
                            /**
                             * 启动单聊界面。
                             *
                             * @param context      应用上下文。
                             * @param targetUserId 要与之聊天的用户 Id。
                             * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                             */
                            RongIM.getInstance().startPrivateChat(ProDetailActivity.this, targetId, title);
                            RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));
                            RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));
                        }
                    } else {
//                    CheckLiao();
                        if(AppGlobal.getInstance().getLagStr().equals("en")){
                            if(AppGlobal.getInstance().getLagStr().equals("en")){
                                ToastUtil.longToast(ProDetailActivity.this, "You can\'t chat with hime/her unless  you have followed each other or there is an order in process.");
                            }else{
                                ToastUtil.longToast(ProDetailActivity.this, "互相关注或订单正在进行，才可以聊天");
                            }

                        }else{

                        }
                    }
                }else{
                    if(isLiaoWithOhter!=0) {
                        String targetId = uid;
                        String title = nickname;
                        if (RongIM.getInstance() != null) {
                            /**
                             * 启动单聊界面。
                             *
                             * @param context      应用上下文。
                             * @param targetUserId 要与之聊天的用户 Id。
                             * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                             */
                            RongIM.getInstance().startPrivateChat(ProDetailActivity.this, targetId, title);
                            RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));
                            RongIM.getInstance().refreshUserInfoCache(new UserInfo(targetId, title, Uri.parse(mInfo.getAvatar())));
                        } else {
                            ToastUtil.longToast(ProDetailActivity.this, "互相关注或订单正在进行，才可以聊天");
                        }
                    }
                }


                break;
            case R.id.pro_detail_ll_like:
                if (MyApp.getTingtingApp().isLogin()) {
                    if (ivlike.isSelected()) {//取消关注
                        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProDetailActivity.this, Contants.follow_del, gzMap, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                if (code == 200) {
                                    ToastUtil.longToast(ProDetailActivity.this, getString(R.string.pro_cancel_watch_success));
                                    ivlike.setSelected(false);
                                } else {
                                    ToastUtil.shortToast(ProDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));

                                }
                            }
                        }));
                    } else {//进行关注
                        MyApp.getTingtingApp().getRequestQueue().add(new PlatRequest(ProDetailActivity.this, Contants.follow_add, gzMap, null, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int code = JsonUtils.getJsonInt(response, "status");
                                if (code == 200) {
                                    ToastUtil.longToast(ProDetailActivity.this, getString(R.string.pro_detail_watch_success));
                                    ivlike.setSelected(true);
                                } else {
                                    ToastUtil.shortToast(ProDetailActivity.this, ErrorCodeUtils.getRegisterError(code + ""));
                                }
                            }
                        }));
                    }
                } else {
                    startActivity(new Intent(ProDetailActivity.this, LoginActivity.class));
                }
                break;
            case R.id.pro_detail_iv_back:
                finish();
                break;
            case R.id.pro_detail_skill:
                Bundle skill_bundle = new Bundle();
                skill_bundle.putString("id", mSkill.getId());
                skill_bundle.putString("uid", mSkill.getUid());
                skill_bundle.putString("type", mSkill.getType());
                ActivityTools.goNextActivity(this, ProSkillActivity.class, skill_bundle);
                break;
            case R.id.pro_detail_more_pic://更多照片
                if (!isUpload) {
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", uid);
                    ActivityTools.goNextActivity(this, PicListActivity.class, bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("flag", "prodetail");
                    ActivityTools.goNextActivity(this, UpLoadPicActivity.class, bundle);
                }
                break;
            case R.id.pop_renzheng_tv_sf:
                showPopWindow(v, 1);
                break;
            case R.id.pop_renzheng_tv_jn:
                showPopWindow(v, 2);
                break;
            case R.id.pro_detail_iv_comment_more:
                //更多评论
                Bundle comment_bundle = new Bundle();
                comment_bundle.putString("uid", user_id);
                ActivityTools.goNextActivity(this, CommentActivity.class, comment_bundle);
                break;
            case R.id.pro_detail_si_1://一张图片
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putInt("position", 0);
                bundle.putString("uid", uid);
                bundle.putSerializable("data", (Serializable) piclist);
                ActivityTools.goNextActivityForResult(ProDetailActivity.this, ImageShowActivity.class, bundle, 100);
                break;
            case R.id.pro_detail_si_2:
                bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putInt("position", 1);
                bundle.putString("uid", uid);
                bundle.putSerializable("data", (Serializable) piclist);
                ActivityTools.goNextActivityForResult(ProDetailActivity.this, ImageShowActivity.class, bundle, 100);
                break;
            case R.id.pro_detail_si_3:
                bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putInt("position", 2);
                bundle.putString("uid", uid);
                bundle.putSerializable("data", (Serializable) piclist);
                ActivityTools.goNextActivityForResult(ProDetailActivity.this, ImageShowActivity.class, bundle, 100);
                break;
            case R.id.user_pro_detail_ll_bottom:
                LogUtil.e("userid", user_id);
                if ("1".equals(choose)) {//take serve list 选人
                    requestChooseServe();
                } else {
                    if (mApp.isLogin() && !"".equals(user_id)) {
                        IMKitService.lagMap.put("isGo", "isFind");
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("id", type);
                        bundle2.putString("make_sure", "0");
                        bundle2.putString("at_uid", user_id);
                        ActivityTools.goNextActivity(this, IssueRequireAActivity.class, bundle2);
                    } else {
                        ActivityTools.goNextActivity(this, LoginActivity.class);
                    }
                }
                break;
        }
    }

    public class ProDetailReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("newData".equals(intent.getAction())) {
                requestPrDetail();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


    private class CustomShareListener implements UMShareListener {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtil.longToast(ProDetailActivity.this, getString(R.string.share_s));
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable t) {
            ToastUtil.longToast(ProDetailActivity.this, getString(R.string.share_f));
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            ToastUtil.longToast(ProDetailActivity.this, getString(R.string.share_c));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
