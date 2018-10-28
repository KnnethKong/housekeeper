package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.Question;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.EvmUtil;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.SizeUtil;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.View_ABXT_1;
import com.haiwai.housekeeper.view.View_BXXD_1;
import com.haiwai.housekeeper.view.View_CPXJ_8;
import com.haiwai.housekeeper.view.View_DTQX_2;
import com.haiwai.housekeeper.view.View_DTQX_3;
import com.haiwai.housekeeper.view.View_DTQX_3_2;
import com.haiwai.housekeeper.view.View_DTQX_4;
import com.haiwai.housekeeper.view.View_DTQX_5;
import com.haiwai.housekeeper.view.View_FLZX_1;
import com.haiwai.housekeeper.view.View_FWBJ_1;
import com.haiwai.housekeeper.view.View_FWBJ_2;
import com.haiwai.housekeeper.view.View_GLWX_3;
import com.haiwai.housekeeper.view.View_GYQX_1;
import com.haiwai.housekeeper.view.View_GYQX_2;
import com.haiwai.housekeeper.view.View_LJCL_2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/18.
 * 点击pro列表底部——发布需求activity(o2o发布)a
 */
public class IssueRequireAActivity extends BaseActivity {// TODO: 2016/11/30 保险业务第三题只有图无问题
    private LinearLayout llcontent;
    private SeekBar seekBar;
    private TextView tvprogress;
    private String id = "";
    private String at_uid = "";

    private View_FWBJ_1 view_fwbj;
    private View_DTQX_3_2 view_cpxj;
    private View_GYQX_1 view_gyqx;
    private View_DTQX_5 view_ljcl;
    private View_GYQX_2 view_bybj;
    private View_ABXT_1 view_abxt;
    private View_ABXT_1 view_chxd;

    private View_LJCL_2 view_wxjg;
    private View_GLWX_3 view_fwwx;
    private View_DTQX_4 view_yywx;
    private View_FWBJ_2 view_jdxd;
    private View_DTQX_5 view_gjfx;
    private View_DTQX_5 view_snfs;
    private View_DTQX_5 view_swfs;
    private View_GYQX_2 view_hsxd;
    private View_DTQX_4 view_dgxd;
    private View_LJCL_2 view_glwx;

    private View_DTQX_5 view_jjyc;
    private View_DTQX_3 view_ptdb;
    private View_BXXD_1 view_bxxd;
    private View_DTQX_2 view_ymzx;
    private View_DTQX_3 view_swfw;
    private View_DTQX_4 view_jsjl;
    private View_DTQX_3 view_fygz;
    private View_FLZX_1 view_flzx;
    private View_CPXJ_8 view_fcmm;
    private View_DTQX_5 view_lydl;

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
                    ActivityTools.goNextActivity(IssueRequireAActivity.this, ProDetailActivity.class, bundle);
                } else if ("isHome".equals(IMKitService.lagMap.get("isGo"))) {
                    if(Integer.valueOf(id)>=19){
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireAActivity.this, O2ODetailActivity2.class, bundle);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireAActivity.this, O2ODetailActivity.class, bundle);
                    }

                }
            }
        });
        isZhorEn = AppGlobal.getInstance().getLagStr();
        llcontent = (LinearLayout) findViewById(R.id.issue_requirea_ll_content);
        seekBar = (SeekBar) findViewById(R.id.issue_requirea_sb);
        tvprogress = (TextView) findViewById(R.id.issue_requirea_tv_progress);
        id = getIntent().getExtras().get("id").toString();
        at_uid = getIntent().getExtras().get("at_uid").toString();
        findViewById(R.id.issue_require_iv_huo).setVisibility(View.GONE);
        findViewById(R.id.issue_require_tv_before).setVisibility(View.GONE);
        seekBar.setMax(100);
        setTitle(AssetsUtils.getSkillName(id, isZhorEn), Color.parseColor("#FF3C3C3C"));
        findViewById(R.id.issue_require_ll_next).setOnClickListener(this);
        make_sure = getIntent().getExtras().get("make_sure").toString();
        tvnext = (TextView) findViewById(R.id.issue_require_ll_tv_next);
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
            case "1"://房屋保洁
                addView_FWBJ();
                seekBar.setProgress(100 / 10);
                tvprogress.setText(100 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 10)), 5, 0, 0);
//                tvprogress.setPadding(80,0,0,0);
                break;
            case "2":
                addView_CPXJ();
                seekBar.setProgress(100 / 13);
                tvprogress.setText(100 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 13)), 5, 0, 0);
                break;
            case "3"://地毯清洗
                addView_FWBJ();
                seekBar.setProgress(100 / 16);
                tvprogress.setText(100 / 16 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 16)), 5, 0, 0);
                break;
            case "4"://高压清洗
                addView_GYQX();
                seekBar.setProgress(100 / 10);
                tvprogress.setText(100 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 10)), 5, 0, 0);
                break;
            case "5"://垃圾处理
                addView_LJCL();
                seekBar.setProgress(100 / 7);
                tvprogress.setText(100 / 7 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 7)), 5, 0, 0);
                break;
            case "6"://搬运搬家
                addView_BYBJ();
                seekBar.setProgress(100 / 13);
                tvprogress.setText(100 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 13)), 5, 0, 0);
                break;
            case "7"://安保系统
                addView_ABXT();
                seekBar.setProgress(100 / 11);
                tvprogress.setText(100 / 11 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 11)), 5, 0, 0);
                break;
            case "8"://除害下单
                addView_CHXD();
                seekBar.setProgress(100 / 8);
                tvprogress.setText(100 / 8 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 8)), 5, 0, 0);
                break;
            case "9"://维修技工
                addView_WXJG();
                seekBar.setProgress(100 / 10);
                tvprogress.setText(100 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 10)), 5, 0, 0);
                break;
            case "10"://房屋维修
                addView_FWWX();
                seekBar.setProgress(100 / 12);
                tvprogress.setText(100 / 12 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 12)), 5, 0, 0);
                break;
            case "11"://园艺维修
                addView_YYWX();
                seekBar.setProgress(100 / 12);
                tvprogress.setText(100 / 12 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 12)), 5, 0, 0);
                break;
            case "12"://家电下单
                addView_JDXD();
                seekBar.setProgress(100 / 8);
                tvprogress.setText(100 / 8 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 8)), 5, 0, 0);
                break;
            case "13"://改建翻新
                addView_GJFX();
                seekBar.setProgress(100 / 10);
                tvprogress.setText(100 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 10)), 5, 0, 0);
                break;
            case "14"://室内粉刷
                addView_SNFS();
                seekBar.setProgress(100 / 14);
                tvprogress.setText(100 / 14 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 14)), 5, 0, 0);
                break;
            case "15"://室外粉刷
                addView_SWFS();
                seekBar.setProgress(100 / 13);
                tvprogress.setText(100 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 13)), 5, 0, 0);
                break;
            case "16"://换锁下单
                addView_HSXD();
                seekBar.setProgress(100 / 8);
                tvprogress.setText(100 / 8 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 8)), 5, 0, 0);
                break;
            case "17"://电工下单
                addView_DGXD();
                seekBar.setProgress(100 / 10);
                tvprogress.setText(100 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 10)), 5, 0, 0);
                break;
            case "18"://锅炉维修
                addView_GLWX();
                seekBar.setProgress(100 / 11);
                tvprogress.setText(100 / 11 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 11)), 5, 0, 0);
                break;
            case "19"://接机用车
                addView_JJYC();
                seekBar.setProgress(100 / 9);
                tvprogress.setText(100 / 9 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 9)), 5, 0, 0);
                break;
            case "20"://陪同代办
                addView_PTDB();
                seekBar.setProgress(100 / 6);
                tvprogress.setText(100 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 6)), 5, 0, 0);
                break;
            case "21"://保险下单
                addView_BXXD();
                seekBar.setProgress(100 / 6);
                tvprogress.setText(100 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 6)), 5, 0, 0);
                break;
            case "22"://移民咨询
                addView_YMZX();
                seekBar.setProgress(100 / 6);
                tvprogress.setText(100 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 6)), 5, 0, 0);
                break;
            case "23"://税务服务
                addView_SWFW();
                seekBar.setProgress(100 / 6);
                tvprogress.setText(100 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 6)), 5, 0, 0);
                break;
            case "24"://驾驶教练
                addView_JSJL();
                seekBar.setProgress(100 / 15);
                tvprogress.setText(100 / 15 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 15)), 5, 0, 0);
                break;
            case "25"://翻译公证
                addView_FYGZ();
                seekBar.setProgress(100 / 5);
                tvprogress.setText(100 / 5 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 5)), 5, 0, 0);
                break;
            case "26"://法律咨询
                addView_FLZX();
                seekBar.setProgress(100 / 6);
                tvprogress.setText(100 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 6)), 5, 0, 0);
                break;
            case "27"://房产买卖
                addView_FCMM();
                seekBar.setProgress(100 / 10);
                tvprogress.setText(100 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 10)), 5, 0, 0);
                break;
            case "28"://旅游代理
                addView_LYDL();
                seekBar.setProgress(100 / 10);
                tvprogress.setText(100 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) / 10)), 5, 0, 0);
                break;
        }
    }

    //房屋保洁+地毯清洗
    private void addView_FWBJ() {
        view_fwbj = new View_FWBJ_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 1, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 1, 0);
            if ("1".equals(make_sure)) {
                view_fwbj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),answerList.get(4), answer);
            } else {
                view_fwbj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),answerList.get(4));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 1, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 1, 0);

            if ("1".equals(make_sure)) {
                view_fwbj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),answerList.get(4), answer);
            } else {
                view_fwbj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),answerList.get(4));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_fwbj);
    }

    //草坪修剪
    private void addView_CPXJ() {
        view_cpxj = new View_DTQX_3_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 2, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 2, 0);
            if ("1".equals(make_sure)) {
                view_cpxj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), "", answer);
            } else {
                view_cpxj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), "");
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 2, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 2, 0);
            if ("1".equals(make_sure)) {
                view_cpxj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), "", answer);
            } else {
                view_cpxj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), "");
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_cpxj);
    }

    //高压清洗
    private void addView_GYQX() {//// TODO: 2016/8/31 图片选项未给
        view_gyqx = new View_GYQX_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 4, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 4, 0);
            if ("1".equals(make_sure)) {
                view_gyqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_gyqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 4, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 4, 0);
            if ("1".equals(make_sure)) {
                view_gyqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_gyqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_gyqx);
    }

    //垃圾处理
    private void addView_LJCL() {
        view_ljcl = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 5, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 5, 0);
            if ("1".equals(make_sure)) {
                view_ljcl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_ljcl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 5, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 5, 0);
            if ("1".equals(make_sure)) {
                view_ljcl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_ljcl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_ljcl);
    }

    //搬运搬家
    private void addView_BYBJ() {
        view_bybj = new View_GYQX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 6, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 6, 0);
            if ("1".equals(make_sure)) {
                view_bybj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_bybj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 6, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 6, 0);
            if ("1".equals(make_sure)) {
                view_bybj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_bybj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_bybj);
    }

    //安保系统
    private void addView_ABXT() {
        view_abxt = new View_ABXT_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 7, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 7, 0);
            if ("1".equals(make_sure)) {
                view_abxt.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answer);
            } else {
                view_abxt.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 7, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 7, 0);
            if ("1".equals(make_sure)) {
                view_abxt.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answer);
            } else {
                view_abxt.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_abxt);
    }

    //除害下单
    private void addView_CHXD() {
        view_chxd = new View_ABXT_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 8, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 8, 0);
            if ("1".equals(make_sure)) {
                view_chxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answer);
            } else {
                view_chxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 8, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 8, 0);
            if ("1".equals(make_sure)) {
                view_chxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answer);
            } else {
                view_chxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_chxd);
    }

    //维修技工
    private void addView_WXJG() {
        view_wxjg = new View_LJCL_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 9, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 9, 0);
            if ("1".equals(make_sure)) {
                view_wxjg.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_wxjg.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 9, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 9, 0);
            if ("1".equals(make_sure)) {
                view_wxjg.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_wxjg.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_wxjg);
    }

    //房屋维修
    private void addView_FWWX() {
        view_fwwx = new View_GLWX_3(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 10, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 10, 0);
            if ("1".equals(make_sure)) {
                view_fwwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_fwwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 10, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 10, 0);
            if ("1".equals(make_sure)) {
                view_fwwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_fwwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_fwwx);
    }

    //园艺维修
    private void addView_YYWX() {
        view_yywx = new View_DTQX_4(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 11, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 11, 0);
            if ("1".equals(make_sure)) {
                view_yywx.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_yywx.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 11, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 11, 0);
            if ("1".equals(make_sure)) {
                view_yywx.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_yywx.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_yywx);
    }

    //家电下单
    private void addView_JDXD() {
        view_jdxd = new View_FWBJ_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 12, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 12, 0);
            if ("1".equals(make_sure)) {
                view_jdxd.setData(question, answerList.get(0), answerList.get(1), answer);
            } else {
                view_jdxd.setData(question, answerList.get(0), answerList.get(1));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 12, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 12, 0);
            if ("1".equals(make_sure)) {
                view_jdxd.setData(question, answerList.get(0), answerList.get(1), answer);
            } else {
                view_jdxd.setData(question, answerList.get(0), answerList.get(1));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_jdxd);
    }

    //改建翻新
    private void addView_GJFX() {
        view_gjfx = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 13, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 13, 0);
            if ("1".equals(make_sure)) {
                view_gjfx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_gjfx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 13, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 13, 0);
            if ("1".equals(make_sure)) {
                view_gjfx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_gjfx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_gjfx);
    }

    //室内粉刷
    private void addView_SNFS() {
        view_snfs = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 14, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 14, 0);
            if ("1".equals(make_sure)) {
                view_snfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_snfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 14, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 14, 0);
            if ("1".equals(make_sure)) {
                view_snfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_snfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_snfs);
    }

    //室外粉刷
    private void addView_SWFS() {
        view_swfs = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 15, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 15, 0);
            if ("1".equals(make_sure)) {
                view_swfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_swfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 15, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 15, 0);
            if ("1".equals(make_sure)) {
                view_swfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_swfs.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_swfs);
    }

    //换锁下单
    private void addView_HSXD() {
        view_hsxd = new View_GYQX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 16, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 16, 0);
            if ("1".equals(make_sure)) {
                view_hsxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5), answer);
            } else {
                view_hsxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 16, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 16, 0);
            if ("1".equals(make_sure)) {
                view_hsxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5), answer);
            } else {
                view_hsxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_hsxd);
    }

    //电工下单
    private void addView_DGXD() {
        view_dgxd = new View_DTQX_4(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 17, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 17, 0);
            if ("1".equals(make_sure)) {
                view_dgxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_dgxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 17, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 17, 0);
            if ("1".equals(make_sure)) {
                view_dgxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_dgxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_dgxd);
    }

    //锅炉维修
    private void addView_GLWX() {
        view_glwx = new View_LJCL_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 18, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 18, 0);
            if ("1".equals(make_sure)) {
                view_glwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_glwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 18, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 18, 0);
            if ("1".equals(make_sure)) {
                view_glwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_glwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_glwx);
    }

    //接机用车
    private void addView_JJYC() {
        view_jjyc = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 19, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 19, 0);
            if ("1".equals(make_sure)) {
                view_jjyc.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_jjyc.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 19, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 19, 0);
            if ("1".equals(make_sure)) {
                view_jjyc.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_jjyc.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_jjyc);
    }

    //陪同代办
    private void addView_PTDB() {
        view_ptdb = new View_DTQX_3(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 20, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 20, 0);
            if ("1".equals(make_sure)) {
                view_ptdb.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), "", answer);
            } else {
                view_ptdb.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), "");
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 20, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 20, 0);
            if ("1".equals(make_sure)) {
                view_ptdb.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), "", answer);
            } else {
                view_ptdb.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), "");
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_ptdb);
    }

    //保险下单
    private void addView_BXXD() {
        view_bxxd = new View_BXXD_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 21, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 21, 0);
            if ("1".equals(make_sure)) {
                view_bxxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answer);
            } else {
                view_bxxd.setData2(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 21, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 21, 0);
            if ("1".equals(make_sure)) {
                view_bxxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answer);
            } else {
                view_bxxd.setData2(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_bxxd);
    }

    //移民咨询
    private void addView_YMZX() {
        view_ymzx = new View_DTQX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 22, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 22, 0);
            if ("1".equals(make_sure)) {
                view_ymzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answer);
            } else {
                view_ymzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 22, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 22, 0);
            if ("1".equals(make_sure)) {
                view_ymzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answer);
            } else {
                view_ymzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_ymzx);
    }

    //税务服务
    private void addView_SWFW() {
        view_swfw = new View_DTQX_3(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 23, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 23, 0);
            if ("1".equals(make_sure)) {
                view_swfw.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_swfw.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 23, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 23, 0);
            if ("1".equals(make_sure)) {
                view_swfw.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_swfw.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_swfw);
    }

    //驾驶教练
    private void addView_JSJL() {
        view_jsjl = new View_DTQX_4(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 24, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 24, 0);
            if ("1".equals(make_sure)) {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 24, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 24, 0);
            if ("1".equals(make_sure)) {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_jsjl);
    }

    //翻译公证
    private void addView_FYGZ() {
        view_fygz = new View_DTQX_3(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 25, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 25, 0);
            if ("1".equals(make_sure)) {
                view_fygz.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_fygz.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 25, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 25, 0);
            if ("1".equals(make_sure)) {
                view_fygz.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_fygz.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_fygz);
    }

    //法律咨询
    private void addView_FLZX() {
        view_flzx = new View_FLZX_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 26, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 26, 0);
            if ("1".equals(make_sure)) {
                view_flzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5),  answer);
            } else {
                view_flzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 26, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 26, 0);
            if ("1".equals(make_sure)) {
                view_flzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5),  answer);
            } else {
                view_flzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_flzx);
    }

    //房产买卖
    private void addView_FCMM() {
        view_fcmm = new View_CPXJ_8(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 27, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 27, 0);
            if ("1".equals(make_sure)) {
                view_fcmm.setData2(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_fcmm.setData2(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 27, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 27, 0);
            if ("1".equals(make_sure)) {
                view_fcmm.setData2(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_fcmm.setData2(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_fcmm);
    }

    //旅游代理
    private void addView_LYDL() {
        view_lydl = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 28, 0);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 28, 0);
            if ("1".equals(make_sure)) {
                view_lydl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_lydl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 28, 0);
            List<String> answerList = JsonUtils.getENAnswerList(this, 28, 0);
            if ("1".equals(make_sure)) {
                view_lydl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_lydl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_lydl);
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.issue_require_ll_next:
                List<Question> questionList = new ArrayList<>();
                Question question = new Question();
                if ("1".equals(id)) {
                    question.setQuestion(view_fwbj.getQuestion());
                    if(!view_fwbj.getIsEmpty()){
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setAnswer(view_fwbj.getAnswer());
                    questionList.add(question);
                } else if ("2".equals(id)) {
                    if (1 == view_cpxj.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_cpxj.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_cpxj.getQuestion());
                    question.setAnswer(view_cpxj.getAnswer());
                    questionList.add(question);
                } else if ("3".equals(id)) {
                    if (!view_fwbj.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_fwbj.getQuestion());
                    question.setAnswer(view_fwbj.getAnswer());
                    questionList.add(question);
                } else if ("4".equals(id)) {
                    if (1 == view_gyqx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_gyqx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_gyqx.getQuestion());
                    question.setAnswer(view_gyqx.getAnswer());
                    questionList.add(question);
                } else if ("5".equals(id)) {
                    if (view_ljcl.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_ljcl.getQuestion());
                    question.setAnswer(view_ljcl.getAnswer());
                    questionList.add(question);
                } else if ("6".equals(id)) {
                    if (!view_bybj.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_bybj.getQuestion());
                    question.setAnswer(view_bybj.getAnswer());
                    questionList.add(question);
                } else if ("7".equals(id)) {
                    if (1 == view_abxt.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_abxt.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_abxt.getQuestion());
                    question.setAnswer(view_abxt.getAnswer());
                    questionList.add(question);
                } else if ("8".equals(id)) {
                    if (1 == view_chxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_chxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_chxd.getQuestion());
                    question.setAnswer(view_chxd.getAnswer());
                    questionList.add(question);
                } else if ("9".equals(id)) {
                    if (1 == view_wxjg.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_wxjg.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_wxjg.getQuestion());
                    question.setAnswer(view_wxjg.getAnswer());
                    questionList.add(question);
                } else if ("10".equals(id)) {
//                    if (view_fwwx.getIsEmpty()) {
//                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
//                        return;
//                    }
                    if (1 == view_fwwx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_fwwx.getQuestion());
                    question.setAnswer(view_fwwx.getAnswer());
                    questionList.add(question);
                } else if ("11".equals(id)) {
                    if (1 == view_yywx.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_yywx.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_yywx.getQuestion());
                    question.setAnswer(view_yywx.getAnswer());
                    questionList.add(question);
                } else if ("12".equals(id)) {
                    if(!view_jdxd.isEmpty()){
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_jdxd.getQuestion());
                    question.setAnswer(view_jdxd.getAnswer());
                    questionList.add(question);
                } else if ("13".equals(id)) {
                    if (view_gjfx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_gjfx.getQuestion());
                    question.setAnswer(view_gjfx.getAnswer());
                    questionList.add(question);
                } else if ("14".equals(id)) {
                    if (!view_snfs.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_snfs.getQuestion());
                    question.setAnswer(view_snfs.getAnswer());
                    questionList.add(question);
                } else if ("15".equals(id)) {
                    if (!view_swfs.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_swfs.getQuestion());
                    question.setAnswer(view_swfs.getAnswer());
                    questionList.add(question);
                } else if ("16".equals(id)) {
                    if (!view_hsxd.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_hsxd.getQuestion());
                    question.setAnswer(view_hsxd.getAnswer());
                    questionList.add(question);
                } else if ("17".equals(id)) {
                    if (1 == view_dgxd.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_dgxd.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_dgxd.getQuestion());
                    question.setAnswer(view_dgxd.getAnswer());
                    questionList.add(question);
                } else if ("18".equals(id)) {
                    if (1 == view_glwx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_glwx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_glwx.getQuestion());
                    question.setAnswer(view_glwx.getAnswer());
                    questionList.add(question);
                } else if ("19".equals(id)) {
                    if (view_jjyc.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_jjyc.getQuestion());
                    question.setAnswer(view_jjyc.getAnswer());
                    questionList.add(question);
                } else if ("20".equals(id)) {
                    if (1 == view_ptdb.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_ptdb.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_ptdb.getQuestion());
                    question.setAnswer(view_ptdb.getAnswer());
                    questionList.add(question);
                } else if ("21".equals(id)) {
                    if (1 == view_bxxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_bxxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_bxxd.getQuestion());
                    question.setAnswer(view_bxxd.getAnswer1());
                    questionList.add(question);
                } else if ("22".equals(id)) {
                    if (view_ymzx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_ymzx.getQuestion());
                    question.setAnswer(view_ymzx.getAnswer());
                    questionList.add(question);
                } else if ("23".equals(id)) {
                    if (1 == view_swfw.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_swfw.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_swfw.getQuestion());
                    question.setAnswer(view_swfw.getAnswer());
                    questionList.add(question);
                } else if ("24".equals(id)) {
                    if (1 == view_jsjl.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_jsjl.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_jsjl.getQuestion());
                    question.setAnswer(view_jsjl.getAnswer());
                    questionList.add(question);
                } else if ("25".equals(id)) {
                    if (1 == view_fygz.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_fygz.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_fygz.getQuestion());
                    question.setAnswer(view_fygz.getAnswer());
                    questionList.add(question);
                } else if ("26".equals(id)) {
                    if (1 == view_flzx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_flzx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_flzx.getQuestion());
                    question.setAnswer(view_flzx.getAnswer());
                    questionList.add(question);
                } else if ("27".equals(id)) {
                    if (1 == view_fcmm.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_fcmm.getQuestion());
                    question.setAnswer(view_fcmm.getAnswer());
                    questionList.add(question);
                } else if ("28".equals(id)) {
                    if (!view_lydl.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireAActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_lydl.getQuestion());
                    question.setAnswer(view_lydl.getAnswer());
                    questionList.add(question);
                }

                if ("1".equals(make_sure)) {
                    Intent intent = new Intent();
                    Bundle question_bundle = new Bundle();
                    question_bundle.putSerializable("question", question);
                    intent.putExtras(question_bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bundle", (Serializable) questionList);
                    bundle.putString("id", id);
                    bundle.putString("at_uid", at_uid);
                    bundle.putString("make_sure", "0");
                    ActivityTools.goNextActivity(this, IssueRequireBActivity.class, bundle);
                }
                break;
        }
    }
}
