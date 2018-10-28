package com.haiwai.housekeeper.activity.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.translate.HttpCallBack;
import com.baidu.translate.RequestUtils;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.EnOrCnUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * Created by ihope006 on 2017/2/9.
 */

public class CusConversationFragment extends ConversationFragment {
    RongExtension mRongExtension;
    ImageView mImageViewKeyWord, mImageViewVoice,mEmotionView;
    FrameLayout mFrameLayout, mSendToggle;
    Button btn_zh, btn_en;
    View btnView;
    //-----------------------------
    private float mLastTouchY;
    private boolean mUpDirection;
    private float mOffsetLimit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRongExtension = (RongExtension) view.findViewById(R.id.rc_extension);
        mImageViewVoice = (ImageView) mRongExtension.findViewById(R.id.rc_voice_toggle);
        mImageViewKeyWord = (ImageView) mRongExtension.findViewById(R.id.rc_switch_to_menu);
        mFrameLayout = (FrameLayout) mRongExtension.findViewById(R.id.rc_container_layout);
        mSendToggle = (FrameLayout) mRongExtension.findViewById(R.id.rc_send_toggle);
        mEmotionView = ((ImageView) mRongExtension.findViewById(R.id.rc_emoticon_toggle));

        mSendToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = mRongExtension.getInputEditText().getText().toString();
                mRongExtension.getInputEditText().getText().clear();
                mRongExtension.getInputEditText().setText("");
                final String line = "\n-----------------------\n";
                String msg = "";
                String isEnOrZh = EnOrCnUtils.checkString(text);
                if ("en".equals(isEnOrZh)) {
                    RequestUtils requestUtils = new RequestUtils();
                    try {
                        requestUtils.translate(text, "en", "zh", new HttpCallBack() {
                            @Override
                            public Message onSuccess(String result) {
                                if(text.equals(result)){
                                    sendTxtMsg(text);
                                }else{
                                    sendTxtMsg(text + line + result);
                                }
                                return null;
                            }

                            @Override
                            public void onFailure(String exception) {
                                ToastUtil.shortToast(getActivity(), getString(R.string.translate_fail));
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if ("zh".equals(isEnOrZh)) {
                    RequestUtils requestUtils = new RequestUtils();
                    try {
                        requestUtils.translate(text, "zh", "en", new HttpCallBack() {
                            @Override
                            public Message onSuccess(String result) {
                                if(text.equals(result)){
                                    sendTxtMsg(text);
                                }else{
                                    sendTxtMsg(text + line + result);
                                }
                                return null;
                            }

                            @Override
                            public void onFailure(String exception) {
                                ToastUtil.shortToast(getActivity(), getString(R.string.translate_fail));
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        btnView = View.inflate(getActivity(), R.layout.cu_btn_layout, null);
        btn_zh = (Button) btnView.findViewById(R.id.btn_zh);
//        btn_zh.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                onVoiceInputToggleTouch(view, motionEvent);
//                return false;
//            }
//        });
        btn_en = (Button) btnView.findViewById(R.id.btn_en);
        btn_zh.setOnClickListener(mOnClickListener);
        btn_en.setOnClickListener(mOnClickListener);
        mFrameLayout.addView(btnView);
        mImageViewVoice.setVisibility(View.GONE);
        mImageViewKeyWord.setVisibility(View.VISIBLE);
        mImageViewVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRongExtension.getInputEditText().setText("");
                mImageViewVoice.setVisibility(View.GONE);
                mImageViewKeyWord.setVisibility(View.VISIBLE);
                mEmotionView.setVisibility(View.GONE);
                mRongExtension.getInputEditText().setVisibility(View.GONE);
                mFrameLayout.addView(btnView);
            }
        });
//        mImageViewVoice.performClick();
        mImageViewKeyWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageViewKeyWord.setVisibility(View.GONE);
                mImageViewVoice.setVisibility(View.VISIBLE);
                mEmotionView.setVisibility(View.VISIBLE);
                mRongExtension.getInputEditText().setVisibility(View.VISIBLE);
                mFrameLayout.removeView(btnView);
            }
        });


    }

    private void sendTxtMsg(String s) {
        // 构造 TextMessage 实例
        TextMessage myTextMessage = TextMessage.obtain(s);
        Message myMessage = Message.obtain(ConversationActivity.targetId, Conversation.ConversationType.PRIVATE, myTextMessage);
        RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
                //消息本地数据库存储成功的回调
            }

            @Override
            public void onSuccess(Message message) {
                //消息通过网络发送成功的回调
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                //消息发送失败的回调
            }
        });
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_zh) {
                startZhVoice();
            } else if (view.getId() == R.id.btn_en) {
                startEnVoice();
            }
        }
    };


    private void startEnVoice() {
        RecognizerDialog dialog = new RecognizerDialog(getActivity(), null);
        dialog.setParameter(SpeechConstant.LANGUAGE, "en_us");
        dialog.setParameter(SpeechConstant.ACCENT, "mandarin");
//        dialog.setParameter(SpeechConstant.VAD_BOS,m);//设置开始多长时间没有录音，失效
//        dialog.setParameter(SpeechConstant.VAD_EOS,m);//设置多长时间不说话，就表示结束
        // 设置语音前端点
        dialog.setParameter(SpeechConstant.VAD_BOS, "6000");
        // 设置语音后端点
        dialog.setParameter(SpeechConstant.VAD_EOS, "6000");
        dialog.setParameter(SpeechConstant.ASR_PTT, "0");
        dialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                printResult(recognizerResult);
            }

            @Override
            public void onError(SpeechError speechError) {
            }
        });
        dialog.show();
        Toast.makeText(getActivity(), R.string.xf_ti, Toast.LENGTH_SHORT).show();
    }

    private void startZhVoice() {
        RecognizerDialog dialog = new RecognizerDialog(getActivity(), null);
        dialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        dialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 设置语音前端点
        dialog.setParameter(SpeechConstant.VAD_BOS, "6000");
        // 设置语音后端点
        dialog.getWindow().requestFeature(0);

        dialog.setParameter(SpeechConstant.VAD_EOS, "6000");
        dialog.setTitle("");
        dialog.setParameter(SpeechConstant.ASR_PTT, "0");
        dialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                printResult(recognizerResult);
            }

            @Override
            public void onError(SpeechError speechError) {
            }
        });
        dialog.show();
        Toast.makeText(getActivity(),  R.string.xf_ti, Toast.LENGTH_SHORT).show();
    }

    //回调结果：
    private void printResult(RecognizerResult results) {
        final String text = parseIatResult(results.getResultString());
        final String line = "\n-----------------------\n";
        // 自动填写地址
        if (!TextUtils.isEmpty(text)) {
            mFrameLayout.removeView(btnView);
            mImageViewKeyWord.setVisibility(View.GONE);
            mImageViewVoice.setVisibility(View.VISIBLE);
            String isEnOrZh = EnOrCnUtils.checkString(text);
            if ("en".equals(isEnOrZh)) {
                RequestUtils requestUtils = new RequestUtils();
                try {
                    requestUtils.translate(text, "en", "zh", new HttpCallBack() {
                        @Override
                        public Message onSuccess(String result) {
                            if(text.equals(result)){
                                sendTxtMsg(text);
                            }else{
                                sendTxtMsg(text + line + result);
                            }
                            return null;
                        }

                        @Override
                        public void onFailure(String exception) {
                            ToastUtil.shortToast(getActivity(), getString(R.string.translate_fail));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if ("zh".equals(isEnOrZh)) {
                RequestUtils requestUtils = new RequestUtils();
                try {
                    requestUtils.translate(text, "zh", "en", new HttpCallBack() {
                        @Override
                        public Message onSuccess(String result) {
                            if(text.equals(result)){
                                sendTxtMsg(text);
                            }else{
                                sendTxtMsg(text + line + result);
                            }
                            return null;
                        }

                        @Override
                        public void onFailure(String exception) {
                            ToastUtil.shortToast(getActivity(), getString(R.string.translate_fail));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

//    public void onVoiceInputToggleTouch(View v, MotionEvent event) {
//        String[] permissions = new String[]{"android.permission.RECORD_AUDIO"};
//        if (!PermissionCheckUtil.checkPermissions(this.getActivity(), permissions)) {
//            if (event.getAction() == 0) {
//                PermissionCheckUtil.requestPermissions(this, permissions, 100);
//            }
//
//        } else {
//            if (event.getAction() == 0) {//ConversationActivity.targetId, Conversation.ConversationType.PRIVATE,
//                AudioPlayManager.getInstance().stopPlay();
//                AudioRecordManager.getInstance().startRecord(v.getRootView(), Conversation.ConversationType.PRIVATE, ConversationActivity.targetId);
//                this.mLastTouchY = event.getY();
//                this.mUpDirection = false;
//            } else if (event.getAction() == 2) {
//                if (this.mLastTouchY - event.getY() > this.mOffsetLimit && !this.mUpDirection) {
//                    AudioRecordManager.getInstance().willCancelRecord();
//                    this.mUpDirection = true;
//                } else if (event.getY() - this.mLastTouchY > -this.mOffsetLimit && this.mUpDirection) {
//                    AudioRecordManager.getInstance().continueRecord();
//                    this.mUpDirection = false;
//                }
//            } else if (event.getAction() == 1 || event.getAction() == 3) {
//                AudioRecordManager.getInstance().stopRecord();
//            }
//            sendAudioFile();
//        }
//    }
//
//    private void sendAudioFile() {
//        File voiceFile = new File(getActivity().getCacheDir(), "voice.amr");
//        try {
//            // 读取音频文件。
//            InputStream is = getActivity().getAssets().open("BlackBerry.amr");
//            OutputStream os = new FileOutputStream(voiceFile);
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            // 写入缓存文件。
//            while ((bytesRead = is.read(buffer)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//            is.close();
//            os.flush();
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        VoiceMessage vocMsg = VoiceMessage.obtain(Uri.fromFile(voiceFile), 10);
//
//    }

}
