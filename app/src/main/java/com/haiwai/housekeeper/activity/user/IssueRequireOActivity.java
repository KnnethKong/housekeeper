package com.haiwai.housekeeper.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.haiwai.housekeeper.view.View_FWBJ_8;
import com.haiwai.housekeeper.view.View_FWBJ_9;
import com.haiwai.housekeeper.view.View_JJYC_1;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/18.
 * 点击pro列表底部——发布需求activity(o2o发布)a
 */
public class IssueRequireOActivity extends BaseActivity {//dtqx最后一步
    private LinearLayout llcontent;
    private SeekBar seekBar;
    private TextView tvprogress;
    private String id = "";
    private String at_uid = "";
    private List<Question> questionList;

    private View_FWBJ_9 view_dtqx;
    private View_JJYC_1 view_jsjl;

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
                    ActivityTools.goNextActivity(IssueRequireOActivity.this, ProDetailActivity.class, bundle);
                } else if ("isHome".equals(IMKitService.lagMap.get("isGo"))) {

                    if(Integer.valueOf(id)>=19){
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireOActivity.this, O2ODetailActivity2.class, bundle);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("id", IMKitService.lagMap.get("id"));
                        ActivityTools.goNextActivity(IssueRequireOActivity.this, O2ODetailActivity.class, bundle);
                    }

//                    Bundle bundle = new Bundle();
//                    bundle.putString("id", IMKitService.lagMap.get("id"));
//                    ActivityTools.goNextActivity(IssueRequireOActivity.this, O2ODetailActivity.class, bundle);
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
            case "3"://地毯清洗
                addView_DTQX();
                seekBar.setProgress(100 * 15 / 16);
                tvprogress.setText(100 * 15 / 16 + "%");
                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 15 / 16)), 5, 0, 0);
                break;
//            case "24"://驾驶教练
//                addView_JSJL();
//                seekBar.setProgress(100 * 15 / 16);
//                tvprogress.setText(100 * 15 / 16 + "%");
//                tvprogress.setPadding(((int) (Float.valueOf(seekbarwidth) * 15 / 16)), 5, 0, 0);
//                break;
        }
    }

    private void addView_DTQX() {
        view_dtqx = new View_FWBJ_9(this);
        if ("zh".equalsIgnoreCase(isZhorEn)) {
            String question = JsonUtils.getZhQuestion(this, 1, 8);
            List<String> answerList = JsonUtils.getZhAnswerList(this, 1, 8);
            if ("1".equals(make_sure)) {
                view_dtqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_dtqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        } else {
            String question = JsonUtils.getENQuestion(this, 1, 8);
            List<String> answerList = JsonUtils.getENAnswerList(this, 1, 8);
            if ("1".equals(make_sure)) {
                view_dtqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2), answer);
            } else {
                view_dtqx.setData(question, answerList.get(0), answerList.get(1), answerList.get(2));
            }
        }
        llcontent.removeAllViews();
        llcontent.addView(view_dtqx);
    }

//    //驾驶教练
//    private void addView_JSJL() {
//        view_jsjl = new View_JJYC_1(this);
//        if ("zh".equalsIgnoreCase(isZhorEn)) {
//            String question = JsonUtils.getZhQuestion(this, 24, 14);
//            List<String> answerList = JsonUtils.getZhAnswerList(this, 24, 14);
//            view_jsjl.setData(question, answerList.get(0));
//        } else {
//            String question = JsonUtils.getENQuestion(this, 24, 14);
//            List<String> answerList = JsonUtils.getENAnswerList(this, 24, 14);
//            view_jsjl.setData(question, answerList.get(0));
//        }
//        llcontent.removeAllViews();
//        llcontent.addView(view_jsjl);
//    }

    @Override
    protected void initData() {
        questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
        LogUtil.e("L-getdata", questionList + "");
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.issue_require_ll_next:
                questionList = (List<Question>) getIntent().getExtras().getSerializable("bundle");
                Question question = new Question();
                //房屋保洁没有了
                if ("3".equals(id)) {
                    if (!view_dtqx.getIsEmpty()) {
                        ToastUtil.shortToast(this, getString(R.string.issue_require_complete));
                        return;
                    }
                    question.setQuestion(view_dtqx.getQuestion());
                    question.setAnswer(view_dtqx.getAnswer());
                    questionList.add(question);
                }
//                else if ("24".equals(id)) {
//                    if (view_jsjl.getIsEmpty()) {
//                        ToastUtil.shortToast(this, getString(R.string.issue_require_complete));
//                        return;
//                    }
//                    question.setQuestion(view_jsjl.getQuestion());
//                    question.setAnswer(view_jsjl.getAnswer());
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
                    if ("3".equals(id)) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bundle", (Serializable) questionList);
                        bundle.putString("id", id);
                        bundle.putString("at_uid", at_uid);
                        ActivityTools.goNextActivity(this, IssueRequireAddrActivity.class, bundle);
                    }
                }
                break;
            case R.id.issue_require_tv_before:
                finish();
                break;
        }
    }

}
