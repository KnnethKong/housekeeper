package com.haiwai.housekeeper.activity.user;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.Question;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.FileUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SDPathUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.View_DTQX_10;
import com.haiwai.housekeeper.view.View_DTQX_11;
import com.haiwai.housekeeper.view.View_DTQX_13;
import com.haiwai.housekeeper.view.View_FWBJ_8;
import com.haiwai.housekeeper.view.View_FWBJ_9;
import com.haiwai.housekeeper.view.View_JJYC_1;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import id.zelory.compressor.Compressor;

/**
 * Created by ihope007 on 2016/9/18.
 * 点击pro列表底部——发布需求activity(o2o发布)a
 */
public class IssueRequireJActivity extends BaseActivity {
    private LinearLayout llcontent;
    private SeekBar seekBar;
    private TextView tvprogress;
    private String id = "";
    private String at_uid = "";
    private List<Question> questionList;

    private View_DTQX_10 view_dtqx;
    private View_FWBJ_9 view_abxt;

    private View_FWBJ_8 view_fwwx;
    private View_FWBJ_8 view_yywx;
    private View_DTQX_11 view_snfs;
    private View_FWBJ_9 view_glwx;

    private View_DTQX_13 view_jsjl;
    private View_JJYC_1 view_fcmm;
    private View_JJYC_1 view_lydl;

    //cpxj
    private static int REQUESTCODE = 0;
    private static int REQUESTCODEB = 1;
    private static final int REQUESTCODE_A = 100;
    private static final int REQUESTCODE_B = 101;
    private static final int REQUESTCODE_C = 102;
    private static final int REQUESTCODE_D = 103;
    private static final int REQUESTCODE_E = 104;
    private static final int REQUESTCODE_F = 105;
    private String path1, path2, path3;
    private ImageView scView, zmView, fmView, scdView, zmdView, fmdView;
    private TextView tvtitle;
    private RadioButton rb1, rb2;
    private LinearLayout lladdpic;
    private RadioGroup radioGroup;
    PopupWindow popWindow;
    LinearLayout ll_popup;

    private TextView tvnext;
    private String make_sure = "";

    private String isZhorEn = "";
    private String answer = "";

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_issue_requirement, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setRightVisible(true);
        getRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("isFind".equals(IMKitService.lagMap.get("isGo"))) {
                    Bundle bundle = new Bundle();
                    bundle.putString("uid", IMKitService.lagMap.get("uid"));
                    bundle.putString("nickname", IMKitService.lagMap.get("nickname"));
                    bundle.putString("type", IMKitService.lagMap.get("type"));
                    bundle.putString("choose", IMKitService.lagMap.get("choose"));
                    bundle.putString("oid", IMKitService.lagMap.get("oid"));
                    ActivityTools.goNextActivity(IssueRequireJActivity.this, ProDetailActivity.class, bundle);
                } else if ("isHome".equals(IMKitService.lagMap.get("isGo"))) {

                    if(Integer.valueOf(id)>=19){
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireJActivity.this, O2ODetailActivity2.class, bundle);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireJActivity.this, O2ODetailActivity.class, bundle);
                    }

//                    Bundle bundle = new Bundle();
//                    bundle.putString("id", IMKitService.lagMap.get("id"));
//                    ActivityTools.goNextActivity(IssueRequireJActivity.this, O2ODetailActivity.class, bundle);
                }
            }
        });
        isZhorEn = AppGlobal.getInstance().getLagStr();

        llcontent = (LinearLayout) findViewById(R.id.issue_requirea_ll_content);
        findViewById(R.id.issue_require_tv_before).setOnClickListener(this);
        seekBar = (SeekBar) findViewById(R.id.issue_requirea_sb);
        tvprogress = (TextView) findViewById(R.id.issue_requirea_tv_progress);
        seekBar.setMax(100);
        id = getIntent().getExtras().get("id").toString();
        at_uid = getIntent().getExtras().get("at_uid").toString();

        setTitle(AssetsUtils.getSkillName(id, isZhorEn), Color.parseColor("#FF3C3C3C"));
        findViewById(R.id.issue_require_ll_next).setOnClickListener(this);
        tvnext = (TextView) findViewById(R.id.issue_require_ll_tv_next);
        make_sure = getIntent().getExtras().get("make_sure").toString();
        if ("1".equals(make_sure)) {
            answer = getIntent().getExtras().get("answer").toString();
            tvnext.setText(getString(R.string.done));
            findViewById(R.id.issue_require_iv_huo).setVisibility(View.INVISIBLE);
            findViewById(R.id.issue_require_tv_before).setVisibility(View.INVISIBLE);
        }
        initQuestion();
    }

    private void initQuestion() {
        int seekbarwidth = getWindowManager().getDefaultDisplay().getWidth() - EvmUtil.dip2px(this, 90);
        switch (id) {
            case "2":
                addView_CPXJ();
                seekBar.setProgress(100 * 10 / 13);
                tvprogress.setText(100 * 10 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 13)), 5, 0, 0);
                break;
            case "3"://地毯清洗
                addView_DTQX();
                seekBar.setProgress(100 * 10 / 16);
                tvprogress.setText(100 * 10 / 16 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 16)), 5, 0, 0);
                break;
            case "6"://搬运搬家
                addView_CPXJ();
                seekBar.setProgress(100 * 10 / 13);
                tvprogress.setText(100 * 10 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 13)), 5, 0, 0);
                break;
            case "7"://安保系统
                addView_ABXT();
                seekBar.setProgress(100 * 10 / 11);
                tvprogress.setText(100 * 10 / 11 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 11)), 5, 0, 0);
                break;
            case "10"://房屋维修
                addView_FWWX();
                seekBar.setProgress(100 * 10 / 12);
                tvprogress.setText(100 * 10 / 12 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 12)), 5, 0, 0);
                break;
            case "11"://园艺维修
                addView_YYWX();
                seekBar.setProgress(100 * 10 / 12);
                tvprogress.setText(100 * 10 / 12 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 12)), 5, 0, 0);
                break;
            case "14"://室内粉刷
                addView_SNFS();
                seekBar.setProgress(100 * 10 / 14);
                tvprogress.setText(100 * 10 / 14 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 14)), 5, 0, 0);
                break;
            case "15"://室外粉刷
                addView_CPXJ();
                seekBar.setProgress(100 * 10 / 13);
                tvprogress.setText(100 * 10 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 13)), 5, 0, 0);
                break;
            case "18"://锅炉维修
                addView_GLWX();
                seekBar.setProgress(100 * 10 / 11);
                tvprogress.setText(100 * 10 / 11 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 11)), 5, 0, 0);
                break;
            case "24"://驾驶教练
                addView_JSJL();
                seekBar.setProgress(100 * 10 / 15);
                tvprogress.setText(100 * 10 / 15 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 15)), 5, 0, 0);
                break;
//            case "27"://房产买卖
//                addView_FCMM();
//                seekBar.setProgress(100 * 10 / 11);
//                tvprogress.setText(100 * 10 / 11 + "%");
//                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 11)), 5, 0, 0);
//                break;
//            case "28"://旅游代理
//                addView_LYDL();
//                seekBar.setProgress(100 * 10 / 11);
//                tvprogress.setText(100 * 10 / 11 + "%");
//                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 10 / 11)), 5, 0, 0);
//                break;
        }
    }

    private void addView_DTQX() {
        view_dtqx = new View_DTQX_10(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 3, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 3, 9);
            if ("1".equals(make_sure)) {
                view_dtqx.setData1(question, answerList.get(0), answerList.get(1),answerList.get(2), answer);
            } else {
                view_dtqx.setData1(question, answerList.get(0), answerList.get(1),answerList.get(2));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 3, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 3, 9);
            if ("1".equals(make_sure)) {
                view_dtqx.setData1(question, answerList.get(0), answerList.get(1),answerList.get(2), answer);
            } else {
                view_dtqx.setData1(question, answerList.get(0), answerList.get(1),answerList.get(2));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_dtqx);
    }

    private void addView_CPXJ() {
        View view_dtqx = getLayoutInflater().inflate(R.layout.view_dtqx_12, null);
        tvtitle = (TextView) view_dtqx.findViewById(R.id.view_dtqx_12_tv_title);
        radioGroup = (RadioGroup) view_dtqx.findViewById(R.id.view_dtqx_12_rg);
        rb1 = (RadioButton) view_dtqx.findViewById(R.id.view_dtqx_12_rb_no);
        rb2 = (RadioButton) view_dtqx.findViewById(R.id.view_dtqx_12_rb_yes);
        lladdpic = (LinearLayout) view_dtqx.findViewById(R.id.view_dtqx_12_ll_add_pic);
        lladdpic.setVisibility(View.INVISIBLE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb1.getId()) {
                    lladdpic.setVisibility(View.INVISIBLE);
                } else if (checkedId == rb2.getId()) {
                    lladdpic.setVisibility(View.VISIBLE);
                }
            }
        });
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 2, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 2, 9);
            tvtitle.setText(question);
            rb1.setText(answerList.get(0));
            rb2.setText(answerList.get(1));
        } else {
            String question = JsonUtils.getENQuestion(this, 2, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 2, 9);
            tvtitle.setText(question);
            rb1.setText(answerList.get(0));
            rb2.setText(answerList.get(1));
        }

        scView = (ImageView) view_dtqx.findViewById(R.id.imageView1);
        scView.setOnClickListener(this);
        zmView = (ImageView) view_dtqx.findViewById(R.id.imageView2);
        zmView.setOnClickListener(this);
        fmView = (ImageView) view_dtqx.findViewById(R.id.imageView3);
        fmView.setOnClickListener(this);
        scdView = (ImageView) view_dtqx.findViewById(R.id.imageView4);
        scdView.setOnClickListener(this);
        zmdView = (ImageView) view_dtqx.findViewById(R.id.imageView5);
        zmdView.setOnClickListener(this);
        fmdView = (ImageView) view_dtqx.findViewById(R.id.imageView6);
        fmdView.setOnClickListener(this);

        llcontent.removeAllViews();
        if ("1".equals(make_sure)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..." + answer);
            String[] strs = answer.split("\\|");
            switch (strs[0]) {
                case "1":
                    rb1.setChecked(true);
                    lladdpic.setVisibility(View.INVISIBLE);
                    break;
                case "2":
                    rb2.setChecked(true);
                    if (!"null".equals(strs[1])) {
                        path1 = strs[1];
                        try {
                            scView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path1), 140, 140));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        scdView.setVisibility(View.VISIBLE);
                    }
                    if (!"null".equals(strs[2])) {
                        path2 = strs[2];
                        try {
                            zmView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path2), 140, 140));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        zmdView.setVisibility(View.VISIBLE);
                    }
                    if (!"null".equals(strs[3])) {
                        path3 = strs[3];
                        try {
                            fmView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path3), 140, 140));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fmdView.setVisibility(View.VISIBLE);
                    }
                    lladdpic.setVisibility(View.VISIBLE);
                    break;
            }
        }
        llcontent.addView(view_dtqx);
    }

    //锅炉维修
    private void addView_GLWX() {
        view_glwx = new View_FWBJ_9(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 18, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 18, 9);
            if ("1".equals(make_sure)) {
                view_glwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_glwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 18, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 18, 9);
            if ("1".equals(make_sure)) {
                view_glwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_glwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_glwx);
    }


    //安保系统
    private void addView_ABXT() {
        view_abxt = new View_FWBJ_9(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 7, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 7, 9);
            if ("1".equals(make_sure)) {
                view_abxt.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_abxt.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 7, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 7, 9);
            if ("1".equals(make_sure)) {
                view_abxt.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_abxt.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_abxt);
    }

    //房屋维修
    private void addView_FWWX() {
        view_fwwx = new View_FWBJ_8(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 10, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 10, 9);
            if ("1".equals(make_sure)) {
                view_fwwx.setData(question, answerList, answer);
            } else {
                view_fwwx.setData(question, answerList);
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 10, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 10, 9);
            if ("1".equals(make_sure)) {
                view_fwwx.setData(question, answerList, answer);
            } else {
                view_fwwx.setData(question, answerList);
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_fwwx);
    }

    //园艺维修
    private void addView_YYWX() {
        view_yywx = new View_FWBJ_8(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 11, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 11, 9);
            if ("1".equals(make_sure)) {
                view_yywx.setData(question, answerList, answer);
            } else {
                view_yywx.setData(question, answerList);
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 11, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 11, 9);
            if ("1".equals(make_sure)) {
                view_yywx.setData(question, answerList, answer);
            } else {
                view_yywx.setData(question, answerList);
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_yywx);
    }

    //室内粉刷
    private void addView_SNFS() {
        view_snfs = new View_DTQX_11(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 14, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 14, 9);
            if ("1".equals(make_sure)) {
                view_snfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_snfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 14, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 14, 9);
            if ("1".equals(make_sure)) {
                view_snfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_snfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_snfs);
    }

    //驾驶教练
    private void addView_JSJL() {
        view_jsjl = new View_DTQX_13(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 24, 9);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 24, 9);
            if ("1".equals(make_sure)) {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 24, 9);
            List<String> answerList = JsonUtils.getENAnswerList(this, 24, 9);
            if ("1".equals(make_sure)) {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_jsjl);
    }

    //房产买卖
//    private void addView_FCMM() {
//        view_fcmm = new View_JJYC_1(this);
//        if ("zh".equalsIgnoreCase(isZhorEn)) {
//            String question = JsonUtils.getZhQuestion(this, 27, 9);
//            List<String> answerList = JsonUtils.getZhAnswerList(this, 27, 9);
//            view_fcmm.setData(question, answerList.get(0));
//        } else {
//            String question = JsonUtils.getENQuestion(this, 27, 9);
//            List<String> answerList = JsonUtils.getENAnswerList(this, 27, 9);
//            view_fcmm.setData(question, answerList.get(0));
//        }
//        llcontent.removeAllViews();
//        llcontent.addView(view_fcmm);
//    }
//
//    //旅游代理
//    private void addView_LYDL() {
//        view_lydl = new View_JJYC_1(this);
//        if ("zh".equalsIgnoreCase(isZhorEn)) {
//            String question = JsonUtils.getZhQuestion(this, 28, 9);
//            List<String> answerList = JsonUtils.getZhAnswerList(this, 28, 9);
//            view_lydl.setData(question, answerList.get(0));
//        } else {
//            String question = JsonUtils.getENQuestion(this, 28, 9);
//            List<String> answerList = JsonUtils.getENAnswerList(this, 28, 9);
//            view_lydl.setData(question, answerList.get(0));
//        }
//        llcontent.removeAllViews();
//        llcontent.addView(view_lydl);
//    }

    @Override
    protected void initData() {
        questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
        LogUtil.e("J-getdata", questionList + "");
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.issue_require_ll_next:
                questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
                Question question = new Question();
                //房屋保洁没有了
                Log.i("idInformation",id+"");
                if ("3".equals(id)) {
                    if(!view_dtqx.hasSelected()){
                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_dtqx.getQuestion());
                    question.setAnswer(view_dtqx.getAnswer1());
                    questionList.add(question);
                } else if ("2".equals(id)) {
                    LogUtil.e("pic", path1 + "|" + path2 + "|" + path3);
                    if(radioGroup.getCheckedRadioButtonId()!=rb2.getId()&&radioGroup.getCheckedRadioButtonId()!=rb1.getId()){
                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_at_least_one));
                        return ;
                    }
                    if (radioGroup.getCheckedRadioButtonId() == rb2.getId() && path1 == null && path2 == null && path3 == null) {
                        ToastUtil.shortToast(this, getString(R.string.issue_require_upload_pic));
                        return;
                    }
                    String check = rb1.isChecked() ? "1" : "2";
                    question.setQuestion(tvtitle.getText().toString());
                    question.setAnswer(check + "|" + path1 + "|" + path2 + "|" + path3);
                    questionList.add(question);
                } else if ("6".equals(id)) {
                    LogUtil.e("pic", path1 + "|" + path2 + "|" + path3);
                    if(radioGroup.getCheckedRadioButtonId()!=rb1.getId()&&radioGroup.getCheckedRadioButtonId()!=rb2.getId()){
                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    if (radioGroup.getCheckedRadioButtonId() == rb2.getId() && path1 == null && path2 == null && path3 == null) {
                        ToastUtil.shortToast(this, getString(R.string.issue_require_upload_pic));
                        return;
                    }
                    String check = rb1.isChecked() ? "1" : "2";
                    question.setQuestion(tvtitle.getText().toString());
                    question.setAnswer(check + "|" + path1 + "|" + path2 + "|" + path3);
                    questionList.add(question);
                } else if ("7".equals(id)) {
                    if (view_abxt.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_abxt.getQuestion());
                    question.setAnswer(view_abxt.getAnswer());
                    questionList.add(question);
                } else if ("10".equals(id)) {
                    if (view_fwwx.getIsEmpty()) {
                        ToastUtil.shortToast(this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_fwwx.getQuestion());
                    question.setAnswer(view_fwwx.getAnswer());
                    questionList.add(question);
                } else if ("11".equals(id)) {
                    if (view_yywx.getIsEmpty()) {
                        ToastUtil.shortToast(this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_yywx.getQuestion());
                    question.setAnswer(view_yywx.getAnswer());
                    questionList.add(question);
                } else if ("14".equals(id)) {
                    if(!view_snfs.isEmpty()){
                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_snfs.getQuestion());
                    question.setAnswer(view_snfs.getAnswer());
                    questionList.add(question);
                } else if ("15".equals(id)) {
                    if(radioGroup.getCheckedRadioButtonId()!=rb1.getId()&&radioGroup.getCheckedRadioButtonId()!=rb2.getId()){
                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    LogUtil.e("pic", path1 + "|" + path2 + "|" + path3);
                    if (radioGroup.getCheckedRadioButtonId() == rb2.getId() && path1 == null && path2 == null && path3 == null) {
                        ToastUtil.shortToast(this, getString(R.string.issue_require_upload_pic));
                        return;
                    }
                    String check = rb1.isChecked() ? "1" : "2";
                    question.setQuestion(tvtitle.getText().toString());
                    question.setAnswer(check + "|" + path1 + "|" + path2 + "|" + path3);
                    questionList.add(question);
                } else if ("18".equals(id)) {
                    if (view_glwx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_glwx.getQuestion());
                    question.setAnswer(view_glwx.getAnswer());
                    questionList.add(question);
                } else if ("24".equals(id)) {
                    if (1 == view_jsjl.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_jsjl.getQuestion());
                    question.setAnswer(view_jsjl.getAnswer());
                    questionList.add(question);
                }
//                else if ("27".equals(id)) {
//                    if (view_fcmm.getIsEmpty()) {
//                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_complete));
//                        return;
//                    }
//                    question.setQuestion(view_fcmm.getQuestion());
//                    question.setAnswer(view_fcmm.getAnswer());
//                    questionList.add(question);
//                } else if ("28".equals(id)) {
//                    if (view_lydl.getIsEmpty()) {
//                        ToastUtil.shortToast(IssueRequireJActivity.this, getString(R.string.issue_require_complete));
//                        return;
//                    }
//                    question.setQuestion(view_lydl.getQuestion());
//                    question.setAnswer(view_lydl.getAnswer());
//                    questionList.add(question);
//                }

                if ("1".equals(make_sure)) {
                    Intent intent = new Intent();
                    Bundle question_bundle = new Bundle();
                    question_bundle.putSerializable("question", question);
                    intent.putExtras(question_bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    if ("7".equals(id) || "18".equals(id)) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bundle", (Serializable) questionList);
                        bundle.putString("id", id);
                        bundle.putString("at_uid", at_uid);
                        ActivityTools.goNextActivity(this, IssueRequireAddrActivity.class, bundle);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bundle", (Serializable) questionList);
                        bundle.putString("id", id);
                        bundle.putString("at_uid", at_uid);
                        bundle.putString("make_sure", "0");
                        ActivityTools.goNextActivity(this, IssueRequireKActivity.class, bundle);
                    }
                }
                break;
            case R.id.issue_require_tv_before:
                finish();
                break;
            case R.id.imageView1:
                ShowPopupWindow(REQUESTCODE_A, REQUESTCODE_D);
                break;
            case R.id.imageView2:
                ShowPopupWindow(REQUESTCODE_B, REQUESTCODE_E);
                break;
            case R.id.imageView3:
                ShowPopupWindow(REQUESTCODE_C, REQUESTCODE_F);
                break;
            case R.id.imageView4:
                DeleteView(REQUESTCODE_A);
                break;
            case R.id.imageView5:
                DeleteView(REQUESTCODE_B);
                break;
            case R.id.imageView6:
                DeleteView(REQUESTCODE_C);
                break;
            case R.id.item_popupwindows_camera://调用相机拍照
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            0);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        takePhoto();
                        popWindow.dismiss();
                        ll_popup.clearAnimation();
                    }
                }
                break;
            case R.id.item_popupwindows_Photo://调用系统相册
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    chosePhoto();
                    popWindow.dismiss();
                    ll_popup.clearAnimation();
                }
                break;
            case R.id.item_popupwindows_cancel://调用系统相册
                popWindow.dismiss();
                ll_popup.clearAnimation();
                break;
        }
    }

    private void chosePhoto() {
        Intent local = new Intent();
        local.setType("image/*");
        local.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(local, REQUESTCODEB);
        Log.e("resutl=======",REQUESTCODEB+"Ja");
    }

    private void ShowPopupWindow(int a, int b) {
        View view = View
                .inflate(IssueRequireJActivity.this, R.layout.item_popupwindows, null);
        view.startAnimation(AnimationUtils.loadAnimation(IssueRequireJActivity.this,
                R.anim.alpha_fade_in));
        ll_popup = (LinearLayout) view
                .findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(IssueRequireJActivity.this,
                R.anim.translate_bottom_in));
        TextView bt1 = (TextView) view
                .findViewById(R.id.item_popupwindows_camera);
        TextView bt2 = (TextView) view
                .findViewById(R.id.item_popupwindows_Photo);
        TextView bt3 = (TextView) view
                .findViewById(R.id.item_popupwindows_cancel);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        REQUESTCODE = a;
        REQUESTCODEB = b;
    }
    private String picFileName;
    private void takePhoto() {

        picFileName = String.valueOf(System.currentTimeMillis());
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(SDPathUtils.getCachePath(), picFileName + ".jpg")));
        startActivityForResult(openCameraIntent, 100);

//        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(openCameraIntent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK && data != null) {//身份证
            String fileName = String.valueOf(System.currentTimeMillis());
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            FileUtils.saveBitmap(bm, fileName);
            if (REQUESTCODE == REQUESTCODE_A) {
                path1 = FileUtils.SDPATH + fileName + ".JPEG";
                scView.setImageBitmap(bm);
                scdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODE == REQUESTCODE_B) {
                path2 = FileUtils.SDPATH + fileName + ".JPEG";
                zmView.setImageBitmap(bm);
                zmdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODE == REQUESTCODE_C) {
                path3 = FileUtils.SDPATH + fileName + ".JPEG";
                fmView.setImageBitmap(bm);
                fmdView.setVisibility(View.VISIBLE);
            }
        } else if (requestCode == 100) {
            if (REQUESTCODE == REQUESTCODE_A) {//身份发布
                Bitmap compressedImageBitmap = Compressor.getDefault(this).compressToBitmap(new File(SDPathUtils.getCachePath(), picFileName + ".jpg"));
                path1 = SDPathUtils.getCachePath()+picFileName + ".jpg";
                scView.setImageBitmap(compressedImageBitmap);
//                Uri uri = data.getData();
//                path1 = FileUtils.getPath(IssueRequireJActivity.this, uri);
//                try {
//                    scView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path1), 140, 140));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                scdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODE == REQUESTCODE_B) {
                Bitmap compressedImageBitmap = Compressor.getDefault(this).compressToBitmap(new File(SDPathUtils.getCachePath(), picFileName + ".jpg"));
                path2 = SDPathUtils.getCachePath()+picFileName + ".jpg";
                zmView.setImageBitmap(compressedImageBitmap);
//                Uri uri = data.getData();
//                path2 = FileUtils.getPath(IssueRequireJActivity.this, uri);
//                try {
//                    zmView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path2), 140, 140));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                zmdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODE == REQUESTCODE_C) {
                Bitmap compressedImageBitmap = Compressor.getDefault(this).compressToBitmap(new File(SDPathUtils.getCachePath(), picFileName + ".jpg"));
                path3 = SDPathUtils.getCachePath()+picFileName + ".jpg";
                fmView.setImageBitmap(compressedImageBitmap);
//                Uri uri = data.getData();
//                path3 = FileUtils.getPath(IssueRequireJActivity.this, uri);
//                try {
//                    fmView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path3), 140, 140));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                fmdView.setVisibility(View.VISIBLE);
            }
        }else if(requestCode == REQUESTCODEB && resultCode == RESULT_OK && data != null){
            if (REQUESTCODEB == REQUESTCODE_D) {
                Uri uri = data.getData();
                path1 = FileUtils.getPath(IssueRequireJActivity.this, uri);
                try {
                    scView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path1), 140, 140));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                scdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODEB == REQUESTCODE_E) {
                Uri uri = data.getData();
                path2 = FileUtils.getPath(IssueRequireJActivity.this, uri);
                try {
                    zmView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path2), 140, 140));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                zmdView.setVisibility(View.VISIBLE);
            } else if (REQUESTCODEB == REQUESTCODE_F) {
                Uri uri = data.getData();
                path3 = FileUtils.getPath(IssueRequireJActivity.this, uri);
                try {
                    fmView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(path3), 140, 140));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fmdView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void DeleteView(int requestcode) {
        if (requestcode == REQUESTCODE_A) {
            path1 = null;
            scView.setImageResource(R.mipmap.pic_add);
            scdView.setVisibility(View.INVISIBLE);
        } else if (requestcode == REQUESTCODE_B) {
            path2 = null;
            zmView.setImageResource(R.mipmap.pic_add);
            zmdView.setVisibility(View.INVISIBLE);
        } else if (requestcode == REQUESTCODE_C) {
            path3 = null;
            fmView.setImageResource(R.mipmap.pic_add);
            fmdView.setVisibility(View.INVISIBLE);
        }
    }
}
