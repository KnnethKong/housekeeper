package com.haiwai.housekeeper.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.ImgViewActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.Question;
import com.haiwai.housekeeper.imageloader.ImageLoader;
import com.haiwai.housekeeper.utils.BimpUtils;
import com.haiwai.housekeeper.utils.JsonUtils;
import com.haiwai.housekeeper.utils.LogUtil;
import com.haiwai.housekeeper.utils.TimeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haiwai.housekeeper.R.id.layout_img;

/**
 * Created by ihope007 on 2016/10/9.
 */
public class IssueMakeSureAdapter extends BaseAdapter {
    private Context mContext;
    private List<Question> list;
    private LayoutInflater inflater;
    private int type;
    private String isZhorEn = "";
    ImageLoader imageLoader;

    private Map<Integer,String> strMap = new HashMap<>();


    public IssueMakeSureAdapter(Context context, List<Question> list, int type, String arrd) {
        this.mContext = context;
        this.list = list;
        this.type = type;
        inflater = LayoutInflater.from(mContext);
        isZhorEn = AppGlobal.getInstance().getLagStr();
        imageLoader = new ImageLoader(context);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_issue_make_sure, null);
            holder = new MyViewHolder();
            holder.tv_question = (TextView) convertView.findViewById(R.id.item_issue_make_sure_tv_question);
            holder.tv_answer = (TextView) convertView.findViewById(R.id.item_issue_make_sure_tv_answer);
            holder.layout_img = (LinearLayout) convertView.findViewById(layout_img);
            holder.ll_item_view = (LinearLayout) convertView.findViewById(R.id.ll_item_view);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        strMap.put(position,list.get(position).getAnswer());

        if(strMap.get(position).contains("jpg")){
            holder.layout_img.setVisibility(View.VISIBLE);
        }else{
            holder.layout_img.setVisibility(View.GONE);
        }
        Log.i("fdsfjskdl", type + "---" + list.get(position).getQuestion() + "---" + list.get(position).getAnswer());
        switch (type) {
            case 1:
                if (position == 6) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 1, 6);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 1, 6);
                    for (int i = 0; i < a.length(); i++) {
                        if ("1".equals(a.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    if (sb.length() > 0) {
                        LogUtil.e("sb", sb + "");
                        sb = sb.deleteCharAt(sb.lastIndexOf("\n"));
                        LogUtil.e("sb", sb + "");
                        holder.tv_answer.setText(sb);
                    } else {
                        holder.tv_answer.setText("");
                    }

                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    Log.i("fdsfjskdl", list.get(position).getQuestion() + "---" + a);
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
//                        if (split.length > 3) {
//                            String ehours = split[3];
//                            sb.append(ehours + "\n");
//                        }
//                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(split[1]);
                    } else if ("5".equals(split[0])) {
                        if (split[1].contains("\n")) {
                            String[] b = split[1].split("\n");
                            String c = b[0] + "\n" + b[1] + "\n" + "x" + b[2];
                            holder.tv_answer.setText(c);
                        } else {
                            holder.tv_answer.setText(split[1]);
                        }

                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 2:



                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 2, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 2, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    if (AppGlobal.getInstance().getLagStr().equals("zh")) {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else if (position == 6) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 2, 6);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 2, 6);
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 2, 7);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 2, 7);
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 9) {//System.out.println("》》》草坪修剪" + answer);
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int x = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(x));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
                } else if (position == 10) {//服务制定时间描述完善
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        if(AppGlobal.getInstance().equals("zh")){
//                            sb.append("服务日期:" + TimeUtils.getDate(split[1]) + "\n");
//                            sb.append("开始时间:" + TimeUtils.getTime(split[2]) + "\n");
//                            if (split.length > 3) {
//                                String ehours = split[3];
//                                sb.append("工作时长:" + ehours + "\n");
//                            }
//                        }else{
//                            sb.append("Start Date:" + TimeUtils.getDate(split[1]) + "\n");
//                            sb.append("Start time:" + TimeUtils.getTime(split[2]) + "\n");
//                            if (split.length > 3) {
//                                String ehours = split[3];
//                                sb.append("time:" + ehours + "\n");
//                            }
//                        }
//                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(split[1]);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 3:
                if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
//                    StringBuffer sb = new StringBuffer();
//                    List<String> answerList = new ArrayList<>();
//                    if ("zh".equalsIgnoreCase(isZhorEn))
//                        answerList = JsonUtils.getZhAnswerList(mContext, 3, 2);
//                    else
//                        answerList = JsonUtils.getENAnswerList(mContext, 3, 2);
//                    if ("1".equals(answer.substring(0, 1)))
//                        sb.append(answerList.get(0) + "\n");
//                    if ("1".equals(answer.substring(1, 2)))
//                        sb.append(answerList.get(1) + "\n");
//                    if ("1".equals(answer.substring(2, 3)))
//                        sb.append(answerList.get(2) + "\n");
//                    if ("1".equals(answer.substring(3, 4)))
//                        sb.append(answer.substring(4, answer.length()) + "\n");
//                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    if (answer.contains("str")) {
                        holder.tv_answer.setText(answer.substring(5, answer.length()));
                    } else {
                        holder.tv_answer.setText(answer.substring(2, answer.length()));
                    }

                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 3, 3);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 3, 3);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answer.substring(5, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 3, 7);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 3, 7);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 9) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 3, 9);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 3, 9);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
//                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
//                    }
                } else if (position == 11) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.pop_no));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);


                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }

//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 12) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 3, 12);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 3, 12);
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 13) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 4:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 4, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 4, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answer.substring(6, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 4, 5);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 4, 5);
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 6) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 5:
                if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 5, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 5, 1);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answerList.get(5) + "\n");
                    if ("1".equals(answer.substring(6, 7)))
                        sb.append(answer.substring(7, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 4) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 6:
                if (position == 4) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 6, 4);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 6, 4);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answer.substring(6, answer.length()) + "\n");
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else {
                        holder.tv_answer.setText("Optional");
                    }
                } else if (position == 9) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 10) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 7:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 7, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 7, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answerList.get(5) + "\n");
                    if ("1".equals(answer.substring(6, 7)))
                        sb.append(answerList.get(6) + "\n");
                    if ("1".equals(answer.substring(7, 8)))
                        sb.append(answerList.get(7) + "\n");
                    if ("1".equals(answer.substring(8, 9)))
                        sb.append(answerList.get(8) + "\n");
                    if ("1".equals(answer.substring(9, 10)))
                        sb.append(answer.substring(10, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 7) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 8) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 8:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 8, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 8, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answerList.get(5) + "\n");
                    if ("1".equals(answer.substring(6, 7)))
                        sb.append(answerList.get(6) + "\n");
                    if ("1".equals(answer.substring(7, 8)))
                        sb.append(answerList.get(7) + "\n");
                    if ("1".equals(answer.substring(8, 9)))
                        sb.append(answerList.get(8) + "\n");
                    if ("1".equals(answer.substring(9, 10)))
                        sb.append(answer.substring(10, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 8, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 8, 1);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answer.substring(5, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 4) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 9:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 9, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 9, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answerList.get(5) + "\n");
                    if ("1".equals(answer.substring(6, 7)))
                        sb.append(answer.substring(7, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 9, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 9, 1);
                    for (int i = 0; i < 16; i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    if ("1".equals(answer.substring(16, 17)))
                        sb.append(answer.substring(17, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    holder.tv_answer.setText(answer);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 9, 3);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 9, 3);
//                    if (answer.length() > 0) {
                        if ("1".equals(answer.substring(0, 1)))
                            sb.append(answerList.get(0) + "\n");
                        if ("1".equals(answer.substring(1, 2)))
                            sb.append(answerList.get(1) + "\n");
                        if ("1".equals(answer.substring(2, 3)))
                            sb.append(answerList.get(2) + "\n");
//                        if (sb.length() > 3) {
                            if ("1".equals(answer.substring(3, 4)))
                                sb.append(answerList.get(3) + "\n");
//                        }
//                    }

//                    if (answer.length() > 4) {
                        if ("1".equals(answer.substring(4, 5)))
                            sb.append(answerList.get(4) + "\n");
                        if ("1".equals(answer.substring(5, 6)))
                            sb.append(answerList.get(5) + "\n");
                        if ("1".equals(answer.substring(6, 7)))
                            sb.append(answerList.get(6) + "\n");
                        if ("1".equals(answer.substring(7, 8)))
                            sb.append(answerList.get(7) + "\n");
                        if ("1".equals(answer.substring(8, 9)))
                            sb.append(answerList.get(8) + "\n");
                        if ("1".equals(answer.substring(9, 10)))
                            sb.append(answer.substring(10, answer.length()) + "\n");
//                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                    }
                    holder.tv_answer.setText(sb);
                } else if (position == 6) {

                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    holder.layout_img.setTag("" + position);
                    if (list.size() != 0) {
                        if (position == Integer.valueOf(holder.layout_img.getTag().toString())) {

                        }
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                final int imgPosition = i;
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);

                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 10:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 10, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 10, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 8) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {

                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
                } else if (position == 9) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");

//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.layout_img.setVisibility(View.GONE);
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 11:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 11, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 11, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answer.substring(5, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 11, 5);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 11, 5);
                    for (int i = 0; i < 11; i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    if ("1".equals(answer.substring(11, 12)))
                        sb.append(answer.substring(12, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 6) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 11, 6);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 11, 6);
                    for (int i = 0; i < 9; i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    if ("1".equals(answer.substring(9, 10)))
                        sb.append(answer.substring(10, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 8) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 9) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.layout_img.setVisibility(View.GONE);
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 12:
                if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 12, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 12, 1);
                    for (int i = 0; i < 7; i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    if ("1".equals(answer.substring(7, 8)))
                        sb.append(answer.substring(8, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 12, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 12, 2);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    if ("".equals(answer)) {
                        holder.tv_answer.setText("");
                    } else {
                        holder.tv_answer.setText(answer);
                    }
                } else if (position == 4) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 13:
                if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split = answer.split("\\|");
                    String answer1 = split[0];
                    String et1 = split[1];
                    String et2 = split[2];
                    String et3 = split[3];
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 13, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 13, 1);
                    if ("1".equals(answer1.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer1.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer1.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer1.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer1.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer1.substring(5, 6))) {
                        if (!"str".equals(et1)) {
                            sb.append(et1.substring(3, et1.length()) + "\n");
                        }
                    }
                    if ("1".equals(answer1.substring(6, 7)))
                        sb.append(answerList.get(6) + "\n");
                    if ("1".equals(answer1.substring(7, 8))) {
                        if (!"str".equals(et2)) {
                            sb.append(et2.substring(3, et2.length()) + "\n");
                        }
                    }
                    if ("1".equals(answer1.substring(8, 9)))
                        sb.append(answerList.get(8) + "\n");
                    if ("1".equals(answer1.substring(9, 10)))
                        sb.append(answerList.get(9) + "\n");
                    if ("1".equals(answer1.substring(10, 11)))
                        sb.append(answerList.get(10) + "\n");
                    if ("1".equals(answer1.substring(11, 12)))
                        sb.append(answerList.get(11) + "\n");
                    if ("1".equals(answer1.substring(12, 13))) {
                        if (!"str".equals(et3)) {
                            sb.append(et3.substring(3, et3.length()) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    if ("".equals(answer)) {
                        holder.tv_answer.setText("Optional");
                    } else {
                        holder.tv_answer.setText(answer);
                    }
                } else if (position == 6) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 14:
                if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 14, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 14, 1);
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
//                                if(i==answer.lastIndexOf("1")){
//                                    if(!"0".equals(answer.substring(i+1,answer.length()))){
//                                        sb.append(answer.substring(i+1,answer.length()) + "\n");
//                                    }
//                                }else{
                            if(i!=10){
                                sb.append(answerList.get(i) + "\n");
                            }else{
                                if ("1".equals(answer.substring(10, 11)))
                                    sb.append(answer.substring(11, answer.length()) + "\n");
                            }

//                                }
                        }
                    }


                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                    }
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 14, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 14, 2);
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {

//                            if(i==answer.lastIndexOf("1")){
//                                if(!"0".equals(answer.substring(i+1,answer.length()))){
//                                    sb.append(answer.substring(i+1,answer.length()) + "\n");
//                                }
//                            }else{

                            if(i!=5){
                                sb.append(answerList.get(i) + "\n");
                            }else{
                                if ("1".equals(answer.substring(5, 6)))
                                    sb.append(answer.substring(6, answer.length()) + "\n");
                            }

//                            sb.append(answerList.get(i) + "\n");
//                            }
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 14, 5);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 14, 5);
                    for (int i = 0; i < a.length(); i++) {
                        if ("1".equals(a.substring(i, i + 1))) {
//                            if(i==a.lastIndexOf("1")){
//                                if(!"0".equals(a.substring(i+1,a.length()))){
//                                    sb.append(a.substring(i+1,a.length()) + "\n");
//                                }
//                            }else{
                            if (i != 6) {
                                sb.append(answerList.get(i) + "\n");
                            } else {
                                sb.append(a.substring(7, a.length()) + "\n");
                            }

//                            }
                        }
                    }
                    if (sb.length() > 0) {
                        sb = sb.deleteCharAt(sb.lastIndexOf("\n"));
                    }
                    holder.tv_answer.setText(sb);
                } else if (position == 10) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 11) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 15:
                if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 15, 3);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 15, 3);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answer.substring(5, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 6) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 15, 6);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 15, 6);
                    for (int i = 0; i < 7; i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    if ("1".equals(answer.substring(7, 8)))
                        sb.append(answer.substring(8, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 8) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 15, 8);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 15, 8);
                    for (int i = 0; i < 7; i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    if ("1".equals(answer.substring(7, 8)))
                        sb.append(answer.substring(8, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 9) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 10) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 16:
                if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 16, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 16, 1);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answerList.get(5) + "\n");
                    if ("1".equals(answer.substring(6, 7)))
                        sb.append(answer.substring(7, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 16, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 16, 2);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answer.substring(6, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 4) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 17:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 17, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 17, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answer.substring(5, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 17, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 17, 2);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answerList.get(5) + "\n");
                    if ("1".equals(answer.substring(6, 7)))
                        sb.append(answer.substring(7, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 6) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 18:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 18, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 18, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answerList.get(5) + "\n");
                    if ("1".equals(answer.substring(6, 7)))
                        sb.append(answer.substring(7, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 18, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 18, 2);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 7) {
                    holder.layout_img.removeAllViews();
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    String[] split1 = answer.split("\\|");
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < split1.length; i++) {
                        if (i == 0) {
                            continue;
                        }
                        if (null != split1[i] && !"".equals(split1[i]) && !"null".equals(split1[i])) {
                            list.add(split1[i]);
                        }
                    }
                    if ("1".equals(answer.substring(0, 1))) {
                        holder.tv_answer.setText(mContext.getString(R.string.no_photo));
                    } else {
                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
                    }
                    if (list.size() != 0) {
                        holder.layout_img.setVisibility(View.VISIBLE);
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                ImageView imageView = new ImageView(mContext);
                                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(120, 120);
                                imageView.setPadding(32, 10, 0, 10);
                                final int imgPosition = i;
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(mContext, ImgViewActivity.class);
                                        intent.putExtra("img", "file:///" + list.get(imgPosition));
                                        mContext.startActivity(intent);
                                    }
                                });
                                imageView.setImageBitmap(BimpUtils.extractMiniThumb(BimpUtils.revitionImageSize(list.get(i)), 140, 140));
                                holder.layout_img.addView(imageView, layoutParam);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        holder.layout_img.setVisibility(View.GONE);
                    }
//                    holder.tv_question.setText(list.get(position).getQuestion());
//                    String answer = list.get(position).getAnswer();
//                    if ("1".equals(answer.substring(0, 1))) {
//                        holder.tv_answer.setText(mContext.getString(R.string.no));
//                    } else {
//                        holder.tv_answer.setText(mContext.getString(R.string.yes_is));
//                    }
                } else if (position == 8) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else if (position == 11) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 18, 11);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 18, 11);
                    String[] split = a.split("\\|");
                    if ("1".equals(split[0].substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(split[1] + "\n");
                        if ("1".equals(split[0].substring(1, 2))) {
                            sb.append(answerList.get(2) + "\n");
                        }
                    } else {
                        sb.append(answerList.get(1) + "\n");
                        sb.append(split[1] + "\n");
                        sb.append(split[2] + "\n");
                        if ("1".equals(split[0].substring(1, 2))) {
                            sb.append(answerList.get(2) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 12) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    holder.tv_answer.setText(answer);
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 19:
                if (position == 6) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 20:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 20, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 20, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 20, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 20, 2);
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 21:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 21, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 21, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answerList.get(5) + "\n");
                    if ("1".equals(answer.substring(6, 7)))
                        sb.append(answerList.get(6) + "\n");
//                    if ("1".equals(answer.substring(7, 8)))
//                        sb.append(answerList.get(7) + "\n");
                    if (answer.length() > 8) {
                        if ("1".equals(answer.substring(7, 8)))
                            sb.append(answer.substring(8, answer.length()) + "\n");
                    }

                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 21, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 21, 1);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 21, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 21, 2);
                    if ("1".equals(answer.substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(answer.substring(3, answer.length()) + "\n");
                    }
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 22:
                if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 22, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 22, 2);
                    if ("1".equals(answer.substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(answer.substring(2, answer.length()) + "\n");
                    }
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 23:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 23, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 23, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 23, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 23, 1);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answer.substring(3, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 23, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 23, 2);
                    if ("1".equals(answer.substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(answer.substring(3, answer.length()) + "\n");
                    }
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 24:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 24, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 24, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answer.substring(5, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 24, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 24, 2);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 24, 3);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 24, 3);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 4) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    holder.tv_answer.setText(answer);
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 24, 5);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 24, 5);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answerList.get(4) + "\n");
                    if ("1".equals(answer.substring(5, 6)))
                        sb.append(answer.substring(6, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 8) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 24, 8);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 24, 8);
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 9) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 24, 9);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 24, 9);
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 12) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 24, 12);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 24, 12);
                    if ("1".equals(answer.substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(answer.substring(2, answer.length()) + "\n");
                    }
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 25:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 25, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 25, 0);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answer.substring(4, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 25, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 25, 1);
                    if ("1".equals(answer.substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(answer.substring(2, answer.length()) + "\n");
                    }
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 26:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 26, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 26, 0);
                    for (int i = 0; i < 18; i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 26, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 26, 1);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answer.substring(3, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 26, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 26, 2);
                    if ("1".equals(answer.substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(answer.substring(3, answer.length()) + "\n");
                    }
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 3) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    Log.e("result---->",a);
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 27:
                if (position == 0) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 27, 0);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 27, 0);
                    for (int i = 0; i < answer.length(); i++) {
                        if ("1".equals(answer.substring(i, i + 1))) {
                            sb.append(answerList.get(i) + "\n");
                        }
                    }
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 2) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 27, 2);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 27, 2);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answer.substring(5, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 6) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 27, 6);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 27, 6);
                    if ("1".equals(answer.substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(answer.substring(3, answer.length()) + "\n");
                    }
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
            case 28:
                if (position == 1) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 28, 1);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 28, 1);
                    if ("1".equals(answer.substring(0, 1)))
                        sb.append(answerList.get(0) + "\n");
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    if ("1".equals(answer.substring(3, 4)))
                        sb.append(answerList.get(3) + "\n");
                    if ("1".equals(answer.substring(4, 5)))
                        sb.append(answer.substring(5, answer.length()) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 4) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    holder.tv_answer.setText(answer);
                } else if (position == 5) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    holder.tv_answer.setText(answer);
                } else if (position == 6) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String answer = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    List<String> answerList = new ArrayList<>();
                    if ("zh".equalsIgnoreCase(isZhorEn))
                        answerList = JsonUtils.getZhAnswerList(mContext, 28, 6);
                    else
                        answerList = JsonUtils.getENAnswerList(mContext, 28, 6);
                    if ("1".equals(answer.substring(0, 1))) {
                        sb.append(answerList.get(0) + "\n");
                        sb.append(answer.substring(3, answer.length()) + "\n");
                    }
                    if ("1".equals(answer.substring(1, 2)))
                        sb.append(answerList.get(1) + "\n");
                    if ("1".equals(answer.substring(2, 3)))
                        sb.append(answerList.get(2) + "\n");
                    sb.deleteCharAt(sb.lastIndexOf("\n"));
                    holder.tv_answer.setText(sb);
                } else if (position == 7) {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    String a = list.get(position).getAnswer();
                    StringBuffer sb = new StringBuffer();
                    String[] split = a.split("\\|");
                    if ("4".equals(split[0])) {
                        sb.append(split[1] + "\n");
//                        sb.append(TimeUtils.getDate(split[1]) + "\n");
//                        sb.append(TimeUtils.getTime(split[2]) + "\n");
                        if (split.length > 3) {
                            String ehours = split[3];
                            sb.append(ehours + "\n");
                        }
                        sb.deleteCharAt(sb.lastIndexOf("\n"));
                        holder.tv_answer.setText(sb);
                    } else if ("5".equals(split[0])) {
                        holder.tv_answer.setText(split[1]);
                    } else {
                        holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                    }
                } else {
                    holder.tv_question.setText(list.get(position).getQuestion());
                    holder.tv_answer.setText(JsonUtils.getSingleChoiceAnswer(list.get(position).getAnswer()));
                }
                break;
        }
        if (mOnItemClickListener != null) {
            holder.ll_item_view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder.ll_item_view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
        return convertView;
    }


    @Override
    public int getCount() {
        if (list != null) {
            return list.size() > 0 ? list.size() : 0;
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    class MyViewHolder {
        TextView tv_question;
        TextView tv_answer;
        LinearLayout layout_img;
        LinearLayout ll_item_view;
    }


    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
