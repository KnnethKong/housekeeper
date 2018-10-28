package com.haiwai.housekeeper.activity.user;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.translate.HttpCallBack;
import com.baidu.translate.RequestUtils;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.utils.PlatUtils;
import com.haiwai.housekeeper.utils.SizeUtil;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.TranslateENView;
import com.haiwai.housekeeper.view.TranslateZHView;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.rong.imlib.model.Message;

/**
 * Created by ihope007 on 2016/9/20.
 */
public class TranslateActivity extends BaseActivity {
    private TextView tvbtzh;
    private TextView tvbten;
    private LinearLayout llbottom;
    private LinearLayout llcontainer;

    private PopupWindow pop_zh, pop_en;
    private EditText etcontentzh;
    private TextView tvzhcommit;

    private EditText etcontenten;
    private TextView tvencommit;
    private ImageButton ib_voice, ib_key_ic;
    boolean isVoice = false;
    String voiceType = "zh";
    ScrollView sv_new;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_translate, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.translate_title), Color.parseColor("#FF3C3C3C"));
        tvbtzh = (TextView) findViewById(R.id.translate_tv_bt_zh);
        tvbten = (TextView) findViewById(R.id.translate_tv_bt_en);
        llbottom = (LinearLayout) findViewById(R.id.translate_ll_bottom);
        llcontainer = (LinearLayout) findViewById(R.id.translate_ll_container);
        ib_voice = (ImageButton) findViewById(R.id.ib_voice);
        ib_key_ic = (ImageButton) findViewById(R.id.ib_key_ic);
        sv_new = (ScrollView) findViewById(R.id.sv_new);
        sv_new.fullScroll(ScrollView.FOCUS_DOWN);
        tvbtzh.setOnClickListener(this);
        tvbten.setOnClickListener(this);
        ib_voice.setOnClickListener(this);
        ib_key_ic.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        scrollToBottom(sv_new, llcontainer);
    }

    /**
     * zh
     */
    private void showPopWindowZh() {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.pop_translate, null);
        etcontentzh = (EditText) v.findViewById(R.id.pop_translate_zh_et_content);
        tvzhcommit = (TextView) v.findViewById(R.id.pop_translate_zh_tv_commit);
        tvzhcommit.setOnClickListener(this);
        pop_zh = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_zh.setBackgroundDrawable(new BitmapDrawable());
        pop_zh.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        backgroundAlpha(0.4f);
        pop_zh.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pop_zh.showAtLocation(v, Gravity.BOTTOM, 0, SizeUtil.getViewHeight(llbottom));
//        pop_zh.setOnDismissListener(new poponDismissListener());


    }

    /**
     * en
     */
    private void showPopWindowEn() {
        View v = LayoutInflater.from(this)
                .inflate(R.layout.pop_translate_en, null);
        etcontenten = (EditText) v.findViewById(R.id.pop_translate_en_et_content);
        tvencommit = (TextView) v.findViewById(R.id.pop_translate_en_tv_commit);
        tvencommit.setOnClickListener(this);
        pop_en = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        pop_en.setBackgroundDrawable(new BitmapDrawable());
//        backgroundAlpha(0.4f);
        pop_en.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        backgroundAlpha(0.4f);
        pop_en.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pop_en.showAtLocation(v, Gravity.BOTTOM, 0, SizeUtil.getViewHeight(llbottom));
//        pop_en.showAtLocation(v, Gravity.BOTTOM, 0, SizeUtil.getViewHeight(llbottom));


//        pop_zh.setOnDismissListener(new poponDismissListener());
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.translate_tv_bt_zh:
                if (isVoice) {
                    voiceType = "zh";
                    startZhVoice(voiceType);
                } else {
                    if (pop_en != null && pop_en.isShowing())
                        pop_en.dismiss();
                    showPopWindowZh();
                }

                break;
            case R.id.translate_tv_bt_en:
                if (isVoice) {
                    voiceType = "en";
                    startEnVoice(voiceType);
                } else {
                    if (pop_zh != null && pop_zh.isShowing())
                        pop_zh.dismiss();
                    showPopWindowEn();
                }
                break;
            case R.id.pop_translate_zh_tv_commit:
                if (PlatUtils.getEditStr(etcontentzh)) {
                    ToastUtil.shortToast(this, getString(R.string.translate_toast_1));
                    return;
                }
                String content = etcontentzh.getText().toString();
                addZhToEnView(content);
                break;
            case R.id.pop_translate_en_tv_commit:
                if (PlatUtils.getEditStr(etcontenten)) {
                    ToastUtil.shortToast(this, getString(R.string.translate_toast_1));
                    return;
                }
                String content2 = etcontenten.getText().toString();
                addEnToZhView(content2);
                break;
            case R.id.ib_voice:
                ib_key_ic.setVisibility(View.VISIBLE);
                ib_voice.setVisibility(View.GONE);
                isVoice = true;
                break;
            case R.id.ib_key_ic:
                ib_key_ic.setVisibility(View.GONE);
                ib_voice.setVisibility(View.VISIBLE);
                isVoice = false;
                break;
        }
    }

    private void startEnVoice(final String voiceType) {
        RecognizerDialog dialog = new RecognizerDialog(this, null);
        dialog.setParameter(SpeechConstant.LANGUAGE, "en_us");
        dialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 设置语音前端点
        dialog.setParameter(SpeechConstant.VAD_BOS, "6000");
        // 设置语音后端点
        dialog.setParameter(SpeechConstant.VAD_EOS, "6000");
        dialog.setParameter(SpeechConstant.ASR_PTT, "0");
        dialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                printResult(recognizerResult, voiceType);
            }

            @Override
            public void onError(SpeechError speechError) {
            }
        });
        dialog.show();
        Toast.makeText(this, "请开始说话", Toast.LENGTH_SHORT).show();
    }

    private void startZhVoice(String voiceType) {
        RecognizerDialog dialog = new RecognizerDialog(this, null);
        dialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        dialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 设置语音前端点
        dialog.setParameter(SpeechConstant.VAD_BOS, "6000");
        // 设置语音后端点
        dialog.setParameter(SpeechConstant.VAD_EOS, "6000");
        dialog.setParameter(SpeechConstant.ASR_PTT, "0");
        dialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                printResult(recognizerResult, TranslateActivity.this.voiceType);
            }

            @Override
            public void onError(SpeechError speechError) {
            }
        });
        dialog.show();
        Toast.makeText(this, "请开始说话", Toast.LENGTH_SHORT).show();
    }

    private void addZhToEnView(String str) {
        final TranslateZHView zhView = new TranslateZHView(this);
        zhView.setZHText(str);
        RequestUtils requestUtils = new RequestUtils();
        try {
            requestUtils.translate(str, "zh", "en", new HttpCallBack() {
                @Override
                public Message onSuccess(String result) {
                    zhView.setENText(result);
                    return null;
                }

                @Override
                public void onFailure(String exception) {
//                    Toast.makeText(TranslateActivity.this, "翻译出错信息： " + exception, Toast.LENGTH_LONG).show();
                    ToastUtil.shortToast(TranslateActivity.this, getString(R.string.translate_fail));
                }
            });
            llcontainer.addView(zhView);
            initData();
            pop_zh.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addEnToZhView(String str) {
        final TranslateENView enView = new TranslateENView(this);
        enView.setENText(str);
        RequestUtils requestUtils = new RequestUtils();
        try {
            requestUtils.translate(str, "en", "zh", new HttpCallBack() {
                @Override
                public Message onSuccess(String result) {
                    enView.setZHText(result);
                    return null;
                }

                @Override
                public void onFailure(String exception) {
//                    Toast.makeText(TranslateActivity.this, "翻译出错信息： " + exception, Toast.LENGTH_LONG).show();
                    ToastUtil.shortToast(TranslateActivity.this, getString(R.string.translate_fail));
                }
            });
            llcontainer.addView(enView);
            initData();
            pop_en.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //回调结果：
    private void printResult(RecognizerResult results, String voiceType) {
        String text = parseIatResult(results.getResultString());
        // 自动填写地址
        if (!TextUtils.isEmpty(text)) {
            if ("en".equals(voiceType)) {
                addEnToZhView(text);
            } else {
                addZhToEnView(text);
            }
        }

    }

    public static String parseIatResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);
            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++) {
                // 转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret.toString();
    }

    public static void scrollToBottom(final View scroll, final View inner) {

        Handler mHandler = new Handler();

        mHandler.post(new Runnable() {
            public void run() {
                if (scroll == null || inner == null) {
                    return;
                }

                int offset = inner.getMeasuredHeight() - scroll.getHeight();
                if (offset < 0) {
                    offset = 0;
                }

                scroll.scrollTo(0, offset);
            }
        });
    }
}
