package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.View_ABXT_1;
import com.haiwai.housekeeper.view.View_ABXT_2;
import com.haiwai.housekeeper.view.View_DTQX_2;
import com.haiwai.housekeeper.view.View_DTQX_3;
import com.haiwai.housekeeper.view.View_DTQX_4;
import com.haiwai.housekeeper.view.View_DTQX_5;
import com.haiwai.housekeeper.view.View_FCMM_2;
import com.haiwai.housekeeper.view.View_FLZX_2;
import com.haiwai.housekeeper.view.View_FWBJ_1;
import com.haiwai.housekeeper.view.View_FWBJ_2;
import com.haiwai.housekeeper.view.View_FWBJ_4;
import com.haiwai.housekeeper.view.View_FYGZ_2;
import com.haiwai.housekeeper.view.View_GJFX_2;
import com.haiwai.housekeeper.view.View_GYQX_2;
import com.haiwai.housekeeper.view.View_JDXD_2;
import com.haiwai.housekeeper.view.View_JJYC_1;
import com.haiwai.housekeeper.view.View_LJCL_2;
import com.haiwai.housekeeper.view.View_SWFW_2;
import com.haiwai.housekeeper.view.View_WXJG_2;
import com.haiwai.housekeeper.view.View_YMZX_2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ihope007 on 2016/9/18.
 */
public class IssueRequireBActivity extends BaseActivity {
    private LinearLayout llcontent;
    private SeekBar seekBar;
    private TextView tvprogress;
    private String id = "";
    private String at_uid = "";

    private List<Question> questionList;

    private View_FWBJ_2 view_fwbj;
    private View_DTQX_2 view_dtqx;
    private View_FWBJ_4 view_cpxj;
    private View_GYQX_2 view_gyqx;
    private View_LJCL_2 view_ljcl;
    private View_DTQX_5 view_bybj;
    private View_ABXT_2 view_abxt;
    private View_DTQX_4 view_chxd;

    private View_WXJG_2 view_wxjg;
    private View_DTQX_5 view_fwwx;
    private View_DTQX_5 view_yywx;
    private View_JDXD_2 view_jdxd;
    private View_GJFX_2 view_gjfx;
    private View_ABXT_1 view_snfs;
    private View_FWBJ_1 view_swfs;
    private View_LJCL_2 view_hsxd;
    private View_DTQX_2 view_dgxd;
    private View_FWBJ_1 view_glwx;

    private View_DTQX_5 view_jjyc;
    private View_GYQX_2 view_ptdb;
    private View_DTQX_3 view_bxxd;
    private View_YMZX_2 view_ymzx;
    private View_SWFW_2 view_swfw;
    private View_FWBJ_2 view_jsjl;
    private View_FYGZ_2 view_fygz;
    private View_FLZX_2 view_flzx;
    private View_FCMM_2 view_fcmm;
    private View_DTQX_4 view_lydl;

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
                    ActivityTools.goNextActivity(IssueRequireBActivity.this, ProDetailActivity.class, bundle);
                } else if ("isHome".equals(IMKitService.lagMap.get("isGo"))) {

                    if(Integer.valueOf(id)>=19){
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireBActivity.this, O2ODetailActivity2.class, bundle);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireBActivity.this, O2ODetailActivity.class, bundle);
                    }

//                    Bundle bundle = new Bundle();
//                    bundle.putString("id", IMKitService.lagMap.get("id"));
//                    ActivityTools.goNextActivity(IssueRequireBActivity.this, O2ODetailActivity.class, bundle);
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
            case "1":
                addView_FWBJ();
                seekBar.setProgress(100 * 2 / 10);
                tvprogress.setText(100 * 2 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 10)), 5, 0, 0);
                break;
            case "2":
                addView_CPXJ();
                seekBar.setProgress(100 * 2 / 13);
                tvprogress.setText(100 * 2 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 13)), 5, 0, 0);
                break;
            case "3"://地毯清洗
                addView_DTQX();
                seekBar.setProgress(100 * 2 / 16);
                tvprogress.setText(100 * 2 / 16 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 16)), 5, 0, 0);
                break;
            case "4"://高压清洗
                addView_GYQX();
                seekBar.setProgress(100 * 2 / 10);
                tvprogress.setText(100 * 2 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 10)), 5, 0, 0);
                break;
            case "5"://垃圾处理
                addView_LJCL();
                seekBar.setProgress(100 * 2 / 7);
                tvprogress.setText(100 * 2 / 7 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 7)), 5, 0, 0);
                break;
            case "6"://搬运搬家
                addView_BYBJ();
                seekBar.setProgress(100 * 2 / 13);
                tvprogress.setText(100 * 2 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 13)), 5, 0, 0);
                break;
            case "7"://安保系统
                addView_ABXT();
                seekBar.setProgress(100 * 2 / 11);
                tvprogress.setText(100 * 2 / 11 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 11)), 5, 0, 0);
                break;
            case "8"://除害下单
                addView_CHXD();
                seekBar.setProgress(100 * 2 / 8);
                tvprogress.setText(100 * 2 / 8 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 8)), 5, 0, 0);
                break;
            case "9"://维修技工
                addView_WXJG();
                seekBar.setProgress(100 * 2 / 10);
                tvprogress.setText(100 * 2 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 10)), 5, 0, 0);
                break;
            case "10"://房屋维修
                addView_FWWX();
                seekBar.setProgress(100 * 2 / 12);
                tvprogress.setText(100 * 2 / 12 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 12)), 5, 0, 0);
                break;
            case "11"://园艺维修
                addView_YYWX();
                seekBar.setProgress(100 * 2 / 12);
                tvprogress.setText(100 * 2 / 12 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 12)), 5, 0, 0);
                break;
            case "12"://家电下单
                addView_JDXD();
                seekBar.setProgress(100 * 2 / 8);
                tvprogress.setText(100 * 2 / 8 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 8)), 5, 0, 0);
                break;
            case "13"://改建翻新
                addView_GJFX();
                seekBar.setProgress(100 * 2 / 10);
                tvprogress.setText(100 * 2 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 10)), 5, 0, 0);
                break;
            case "14"://室内粉刷
                addView_SNFS();
                seekBar.setProgress(100 * 2 / 14);
                tvprogress.setText(100 * 2 / 14 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 14)), 5, 0, 0);
                break;
            case "15"://室外粉刷
                addView_SWFS();
                seekBar.setProgress(100 * 2 / 13);
                tvprogress.setText(100 * 2 / 13 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 13)), 5, 0, 0);
                break;
            case "16"://换锁下单
                addView_HSXD();
                seekBar.setProgress(100 * 2 / 8);
                tvprogress.setText(100 * 2 / 8 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 8)), 5, 0, 0);
                break;
            case "17"://电工下单
                addView_DGXD();
                seekBar.setProgress(100 * 2 / 10);
                tvprogress.setText(100 * 2 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 10)), 5, 0, 0);
                break;
            case "18"://锅炉维修
                addView_GLWX();
                seekBar.setProgress(100 * 2 / 11);
                tvprogress.setText(100 * 2 / 11 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 11)), 5, 0, 0);
                break;
            case "19"://接机用车
                addView_JJYC();
                seekBar.setProgress(100 * 2 / 9);
                tvprogress.setText(100 * 2 / 9 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 9)), 5, 0, 0);
                break;
            case "20"://陪同代办
                addView_PTDB();
                seekBar.setProgress(100 * 2 / 6);
                tvprogress.setText(100 * 2 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 6)), 5, 0, 0);
                break;
            case "21"://保险下单
                addView_BXXD();
                seekBar.setProgress(100 * 2 / 6);
                tvprogress.setText(100 * 2 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 6)), 5, 0, 0);
                break;
            case "22"://移民咨询
                addView_YMZX();
                seekBar.setProgress(100 * 2 / 6);
                tvprogress.setText(100 * 2 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 6)), 5, 0, 0);
                break;
            case "23"://税务服务
                addView_SWFW();
                seekBar.setProgress(100 * 2 / 6);
                tvprogress.setText(100 * 2 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 6)), 5, 0, 0);
                break;
            case "24"://驾驶教练
                addView_JSJL();
                seekBar.setProgress(100 * 2 / 15);
                tvprogress.setText(100 * 2 / 15 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 15)), 5, 0, 0);
                break;
            case "25"://翻译公证
                addView_FYGZ();
                seekBar.setProgress(100 * 2 / 5);
                tvprogress.setText(100 * 2 / 5 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 5)), 5, 0, 0);
                break;
            case "26"://法律咨询
                addView_FLZX();
                seekBar.setProgress(100 * 2 / 6);
                tvprogress.setText(100 * 2 / 6 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 6)), 5, 0, 0);
                break;
            case "27"://房产买卖
                addView_FCMM();
                seekBar.setProgress(100 * 2 / 10);
                tvprogress.setText(100 * 2 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 10)), 5, 0, 0);
                break;
            case "28"://旅游代理
                addView_LYDL();
                seekBar.setProgress(100 * 2 / 10);
                tvprogress.setText(100 * 2 / 10 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 2 / 10)), 5, 0, 0);
                break;
        }
    }

    private void addView_FWBJ() {
        view_fwbj = new View_FWBJ_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 1, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 1, 1);
            if ("1".equals(make_sure)) {
                view_fwbj.setData(question, answerList.get(0), answerList.get(1), answer);
            } else {
                view_fwbj.setData(question, answerList.get(0), answerList.get(1));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 1, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 1, 1);
            if ("1".equals(make_sure)) {
                view_fwbj.setData(question, answerList.get(0), answerList.get(1), answer);
            } else {
                view_fwbj.setData(question, answerList.get(0), answerList.get(1));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_fwbj);
    }

    private void addView_DTQX() {
        view_dtqx = new View_DTQX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 3, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 3, 1);
            if ("1".equals(make_sure)) {
                view_dtqx.setDataMake(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5), answer);
            } else {
                view_dtqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 3, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 3, 1);
            if ("1".equals(make_sure)) {
                view_dtqx.setDataMake(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5), answer);
            } else {
                view_dtqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_dtqx);
    }

    private void addView_CPXJ() {
        view_cpxj = new View_FWBJ_4(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 2, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 2, 1);
            if ("1".equals(make_sure)) {
                view_cpxj.setData5(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4),answerList.get(5),answerList.get(6), answer);
            } else {
                view_cpxj.setData5(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4),answerList.get(5),answerList.get(6));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 2, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 2, 1);
            if ("1".equals(make_sure)) {
                view_cpxj.setData5(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4),answerList.get(5),answerList.get(6), answer);
            } else {
                view_cpxj.setData5(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4),answerList.get(5),answerList.get(6));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_cpxj);
    }

    //高压清洗
    private void addView_GYQX() {
        view_gyqx = new View_GYQX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 4, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 4, 1);
            if ("1".equals(make_sure)) {
                view_gyqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_gyqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 4, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 4, 1);
            if ("1".equals(make_sure)) {
                view_gyqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_gyqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_gyqx);
    }

    //垃圾处理
    private void addView_LJCL() {
        view_ljcl = new View_LJCL_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 5, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 5, 1);
            if ("1".equals(make_sure)) {
                view_ljcl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_ljcl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 5, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 5, 1);
            if ("1".equals(make_sure)) {
                view_ljcl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_ljcl.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_ljcl);
    }

    //搬运搬家
    private void addView_BYBJ() {
        view_bybj = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 6, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 6, 1);
            if ("1".equals(make_sure)) {
                view_bybj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_bybj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 6, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 6, 1);
            if ("1".equals(make_sure)) {
                view_bybj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_bybj.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_bybj);
    }

    //安保系统
    private void addView_ABXT() {
        view_abxt = new View_ABXT_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 7, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 7, 1);
            if ("1".equals(make_sure)) {
                view_abxt.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answer);
            } else {
                view_abxt.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 7, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 7, 1);
            if ("1".equals(make_sure)) {
                view_abxt.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answer);
            } else {
                view_abxt.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_abxt);
    }

    //除害下单
    private void addView_CHXD() {
        view_chxd = new View_DTQX_4(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 8, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 8, 1);
            if ("1".equals(make_sure)) {
                view_chxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_chxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 8, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 8, 1);
            if ("1".equals(make_sure)) {
                view_chxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_chxd.setData3(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_chxd);
    }

    //维修技工
    private void addView_WXJG() {
        view_wxjg = new View_WXJG_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 9, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 9, 1);
            if ("1".equals(make_sure)) {
                view_wxjg.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7),
                        answerList.get(8), answerList.get(9), answerList.get(10), answerList.get(11), answerList.get(12),
                        answerList.get(13), answerList.get(14), answerList.get(15), answer);
            } else {
                view_wxjg.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7),
                        answerList.get(8), answerList.get(9), answerList.get(10), answerList.get(11), answerList.get(12),
                        answerList.get(13), answerList.get(14), answerList.get(15));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 9, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 9, 1);
            if ("1".equals(make_sure)) {
                view_wxjg.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7),
                        answerList.get(8), answerList.get(9), answerList.get(10), answerList.get(11), answerList.get(12),
                        answerList.get(13), answerList.get(14), answerList.get(15), answer);
            } else {
                view_wxjg.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7),
                        answerList.get(8), answerList.get(9), answerList.get(10), answerList.get(11), answerList.get(12),
                        answerList.get(13), answerList.get(14), answerList.get(15));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_wxjg);
    }

    //房屋维修
    private void addView_FWWX() {
        view_fwwx = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 10, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 10, 1);
            if ("1".equals(make_sure)) {
                view_fwwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_fwwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 10, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 10, 1);
            if ("1".equals(make_sure)) {
                view_fwwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_fwwx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_fwwx);
    }

    //园艺维修
    private void addView_YYWX() {
        view_yywx = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 11, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 11, 1);
            if ("1".equals(make_sure)) {
                view_yywx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_yywx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 11, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 11, 1);
            if ("1".equals(make_sure)) {
                view_yywx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answer);
            } else {
                view_yywx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_yywx);
    }

    //家电下单
    private void addView_JDXD() {
        view_jdxd = new View_JDXD_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 12, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 12, 1);
            if ("1".equals(make_sure)) {
                view_jdxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7), answer);
            } else {
                view_jdxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 12, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 12, 1);
            if ("1".equals(make_sure)) {
                view_jdxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7), answer);
            } else {
                view_jdxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_jdxd);
    }

    //改建翻新
    private void addView_GJFX() {
        view_gjfx = new View_GJFX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 13, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 13, 1);
            if ("1".equals(make_sure)) {
                view_gjfx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7),
                        answerList.get(8), answerList.get(9), answerList.get(10), answerList.get(11), answerList.get(12), answer);
            } else {
                view_gjfx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7),
                        answerList.get(8), answerList.get(9), answerList.get(10), answerList.get(11), answerList.get(12));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 13, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 13, 1);
            if ("1".equals(make_sure)) {
                view_gjfx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7),
                        answerList.get(8), answerList.get(9), answerList.get(10), answerList.get(11), answerList.get(12), answer);
            } else {
                view_gjfx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answerList.get(6), answerList.get(7),
                        answerList.get(8), answerList.get(9), answerList.get(10), answerList.get(11), answerList.get(12));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_gjfx);
    }

    //室内粉刷
    private void addView_SNFS() {
        view_snfs = new View_ABXT_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 14, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 14, 1);
            Log.i("make_sure",make_sure);
            if ("1".equals(make_sure)) {
                view_snfs.setData5(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answerList.get(9), answerList.get(10), answer);
            } else {
                view_snfs.setData4(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answerList.get(9), answerList.get(10));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 14, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 14, 1);
            if ("1".equals(make_sure)) {
                view_snfs.setData5(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answerList.get(9), answerList.get(10), answer);
            } else {
                view_snfs.setData4(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answerList.get(9), answerList.get(10));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_snfs);
    }

    //室外粉刷
    private void addView_SWFS() {
        view_swfs = new View_FWBJ_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 15, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 15, 1);
            if ("1".equals(make_sure)) {
                view_swfs.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answer);
            } else {
                view_swfs.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 15, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 15, 1);
            if ("1".equals(make_sure)) {
                view_swfs.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answer);
            } else {
                view_swfs.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_swfs);
    }

    //换锁下单
    private void addView_HSXD() {
        view_hsxd = new View_LJCL_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 16, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 16, 1);
            if ("1".equals(make_sure)) {
                view_hsxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_hsxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 16, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 16, 1);
            if ("1".equals(make_sure)) {
                view_hsxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_hsxd.setData(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answerList.get(4), answerList.get(5));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_hsxd);
    }

    //电工下单
    private void addView_DGXD() {
        view_dgxd = new View_DTQX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 17, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 17, 1);
            if ("1".equals(make_sure)) {
                view_dgxd.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5), answerList.get(6), answer);
            } else {
                view_dgxd.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5), answerList.get(6));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 17, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 17, 1);
            if ("1".equals(make_sure)) {
                view_dgxd.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5), answerList.get(6), answer);
            } else {
                view_dgxd.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),
                        answerList.get(4), answerList.get(5), answerList.get(6));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_dgxd);
    }

    //锅炉维修
    private void addView_GLWX() {
        view_glwx = new View_FWBJ_1(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 18, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 18, 1);
            if ("1".equals(make_sure)) {
                view_glwx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_glwx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 18, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 18, 1);
            if ("1".equals(make_sure)) {
                view_glwx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_glwx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_glwx);
    }

    //接机用车
    private void addView_JJYC() {
        view_jjyc = new View_DTQX_5(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 19, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 19, 1);
            if ("1".equals(make_sure)) {
                view_jjyc.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answer);
            } else {
                view_jjyc.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 19, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 19, 1);
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
        view_ptdb = new View_GYQX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 20, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 20, 1);
            if ("1".equals(make_sure)) {
                view_ptdb.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_ptdb.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 20, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 20, 1);
            if ("1".equals(make_sure)) {
                view_ptdb.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5), answer);
            } else {
                view_ptdb.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_ptdb);
    }

    //保险下单
    private void addView_BXXD() {
        view_bxxd = new View_DTQX_3(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 21, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 21, 1);
            if ("1".equals(make_sure)) {
                view_bxxd.setData6(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_bxxd.setData6(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 21, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 21, 1);
            if ("1".equals(make_sure)) {
                view_bxxd.setData6(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answer);
            } else {
                view_bxxd.setData6(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_bxxd);
    }

    //移民咨询
    private void addView_YMZX() {
        view_ymzx = new View_YMZX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 22, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 22, 1);
            if ("1".equals(make_sure)) {
                view_ymzx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answer);
            } else {
                view_ymzx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 22, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 22, 1);
            if ("1".equals(make_sure)) {
                view_ymzx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8), answer);
            } else {
                view_ymzx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3), answerList.get(4), answerList.get(5),
                        answerList.get(6), answerList.get(7), answerList.get(8));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_ymzx);
    }

    //税务服务
    private void addView_SWFW() {
        view_swfw = new View_SWFW_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 23, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 23, 1);
            if ("1".equals(make_sure)) {
                view_swfw.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_swfw.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 23, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 23, 1);
            if ("1".equals(make_sure)) {
                view_swfw.setData(question, answerList.get(0), answerList.get(1), "", answer);
            } else {
                view_swfw.setData(question, answerList.get(0), answerList.get(1), "");
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_swfw);
    }

    //驾驶教练
    private void addView_JSJL() {
        view_jsjl = new View_FWBJ_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 24, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 24, 1);
            if ("1".equals(make_sure)) {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answer);
            } else {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 24, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 24, 1);
            if ("1".equals(make_sure)) {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1), answer);
            } else {
                view_jsjl.setData(question, answerList.get(0), answerList.get(1));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_jsjl);
    }

    //翻译公证
    private void addView_FYGZ() {
        view_fygz = new View_FYGZ_2(this);
        if("1".equals(make_sure)){
            view_fygz.setData(answer);
        }
        llcontent.removeAllViews();
        llcontent.addView(view_fygz);
    }

    //法律咨询
    private void addView_FLZX() {
        view_flzx = new View_FLZX_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 26, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 26, 1);
            if ("1".equals(make_sure)) {
                view_flzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_flzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 26, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 26, 1);
            if ("1".equals(make_sure)) {
                view_flzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_flzx.setData1(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_flzx);
    }

    //房产买卖
    private void addView_FCMM() {
        view_fcmm = new View_FCMM_2(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 27, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 27, 1);
            if ("1".equals(make_sure)) {
                view_fcmm.setData1(question, answerList.get(0),answerList.get(1), answer);
            } else {
                view_fcmm.setData1(question, answerList.get(0),answerList.get(1));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 27, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 27, 1);
            if ("1".equals(make_sure)) {
                view_fcmm.setData1(question, answerList.get(0),answerList.get(1), answer);
            } else {
                view_fcmm.setData1(question, answerList.get(0),answerList.get(1));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_fcmm);
    }

    //旅游代理
    private void addView_LYDL() {
        view_lydl = new View_DTQX_4(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 28, 1);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 28, 1);
            if ("1".equals(make_sure)) {
                view_lydl.setData6(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answer);
            } else {
                view_lydl.setData2(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3));
            }

        } else {
            String question = JsonUtils.getENQuestion(this, 28, 1);
            List<String> answerList = JsonUtils.getENAnswerList(this, 28, 1);
            if ("1".equals(make_sure)) {
                view_lydl.setData6(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3), answer);
            } else {
                view_lydl.setData2(question, answerList.get(0), answerList.get(1), answerList.get(2),
                        answerList.get(3));
            }

        }
        llcontent.removeAllViews();
        llcontent.addView(view_lydl);
    }

    @Override
    protected void initData() {
        questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
        LogUtil.e("B-getdata", questionList + "");
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.issue_require_ll_next:
                questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
                Question question = new Question();
                if ("1".equals(id)) {
                    if(!view_fwbj.isEmpty()){
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_fwbj.getQuestion());
                    question.setAnswer(view_fwbj.getAnswer());
                    questionList.add(question);
                } else if ("2".equals(id)) {
                    question.setQuestion(view_cpxj.getQuestion());
                    if(view_cpxj.getAnswer5().equals("")){
                        ToastUtil.shortToast(IssueRequireBActivity.this,getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setAnswer(view_cpxj.getAnswer5());
                    questionList.add(question);
                } else if ("3".equals(id)) {
                    if (!view_dtqx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_dtqx.getQuestion());
                    question.setAnswer(view_dtqx.getAnswer3());
                    questionList.add(question);
                } else if ("4".equals(id)) {
                    if (view_gyqx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_gyqx.getQuestion());
                    question.setAnswer(view_gyqx.getAnswer());
                    questionList.add(question);
                } else if ("5".equals(id)) {
                    if (1 == view_ljcl.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_ljcl.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_ljcl.getQuestion());
                    question.setAnswer(view_ljcl.getAnswer());
                    questionList.add(question);
                } else if ("6".equals(id)) {
                    if (!view_bybj.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_bybj.getQuestion());
                    question.setAnswer(view_bybj.getAnswer());
                    questionList.add(question);
                } else if ("7".equals(id)) {
                    if (view_abxt.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_abxt.getQuestion());
                    question.setAnswer(view_abxt.getAnswer());
                    questionList.add(question);
                } else if ("8".equals(id)) {
                    if (1 == view_chxd.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_chxd.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_chxd.getQuestion());
                    question.setAnswer(view_chxd.getAnswer());
                    questionList.add(question);
                } else if ("9".equals(id)) {
                    if (1 == view_wxjg.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_wxjg.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_wxjg.getQuestion());
                    question.setAnswer(view_wxjg.getAnswer());
                    questionList.add(question);
                } else if ("10".equals(id)) {
                    if (!view_fwwx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_fwwx.getQuestion());
                    question.setAnswer(view_fwwx.getAnswer());
                    questionList.add(question);
                } else if ("11".equals(id)) {
                    if (view_yywx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_yywx.getQuestion());
                    question.setAnswer(view_yywx.getAnswer());
                    questionList.add(question);
                } else if ("12".equals(id)) {
                    if (1 == view_jdxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_jdxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_jdxd.getQuestion());
                    question.setAnswer(view_jdxd.getAnswer());
                    questionList.add(question);
                } else if ("13".equals(id)) {
                    if (1 == view_gjfx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_gjfx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_gjfx.getQuestion());
                    question.setAnswer(view_gjfx.getAnswer());
                    questionList.add(question);
                } else if ("14".equals(id)) {
                    if (1 == view_snfs.getIsEmptyState1()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_snfs.getIsEmptyState1()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_snfs.getQuestion());
                    question.setAnswer(view_snfs.getAnswer3());
                    questionList.add(question);
                } else if ("15".equals(id)) {
                    if (!view_swfs.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_swfs.getQuestion());
                    question.setAnswer(view_swfs.getAnswer2());
                    questionList.add(question);
                } else if ("16".equals(id)) {
                    if (1 == view_hsxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_hsxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_hsxd.getQuestion());
                    question.setAnswer(view_hsxd.getAnswer());
                    questionList.add(question);
                } else if ("17".equals(id)) {
                    if (view_dgxd.getIsEmpty2()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_dgxd.getQuestion());
                    question.setAnswer(view_dgxd.getAnswer());
                    questionList.add(question);
                } else if ("18".equals(id)) {
                    if (view_glwx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_glwx.getQuestion());
                    question.setAnswer(view_glwx.getAnswer());
                    questionList.add(question);
                } else if ("19".equals(id)) {
                    if (view_jjyc.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_jjyc.getQuestion());
                    question.setAnswer(view_jjyc.getAnswer());
                    questionList.add(question);
                } else if ("20".equals(id)) {
                    if (view_ptdb.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_ptdb.getQuestion());
                    question.setAnswer(view_ptdb.getAnswer());
                    questionList.add(question);
                } else if ("21".equals(id)) {
                    if (1 == view_bxxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_bxxd.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_bxxd.getQuestion());
                    question.setAnswer(view_bxxd.getAnswer());
                    questionList.add(question);
                } else if ("22".equals(id)) {
                    if (view_ymzx.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_ymzx.getQuestion());
                    question.setAnswer(view_ymzx.getAnswer());
                    questionList.add(question);
                } else if ("23".equals(id)) {
                    if (1 == view_swfw.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_swfw.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_swfw.getQuestion());
                    question.setAnswer(view_swfw.getAnswer());
                    questionList.add(question);
                } else if ("24".equals(id)) {
                    question.setQuestion(view_jsjl.getQuestion());
                    question.setAnswer(view_jsjl.getAnswer());
                    questionList.add(question);
                } else if ("25".equals(id)) {
                    if (1 == view_fygz.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    }
                    question.setQuestion(view_fygz.getQuestion());
                    question.setAnswer(view_fygz.getAnswer());
                    questionList.add(question);
                } else if ("26".equals(id)) {
                    if (1 == view_flzx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_flzx.getIsEmptyState()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_flzx.getQuestion());
                    question.setAnswer(view_flzx.getAnswer());
                    questionList.add(question);
                } else if ("27".equals(id)) {
                    if (view_fcmm.getIsEmpty()) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_fcmm.getQuestion());
                    question.setAnswer(view_fcmm.getAnswer());
                    questionList.add(question);
                } else if ("28".equals(id)) {
                    if (1 == view_lydl.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_at_least_one));
                        return;
                    } else if (2 == view_lydl.getIsEmptyState(0)) {
                        ToastUtil.shortToast(IssueRequireBActivity.this, getString(R.string.issue_require_complete));
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
                    ActivityTools.goNextActivity(this, IssueRequireCActivity.class, bundle);
                }
                break;
            case R.id.issue_require_tv_before:
                finish();
                break;
        }
    }
}
