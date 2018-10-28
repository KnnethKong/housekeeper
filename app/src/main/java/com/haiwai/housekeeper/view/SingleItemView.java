package com.haiwai.housekeeper.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.entity.NewHousEntity;
import com.haiwai.housekeeper.widget.LoadDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope006 on 2016/12/6.
 */

public class SingleItemView extends LinearLayout {
    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tvX, tvY;
    private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
    private TextView tvTitle, tv_remark;
    private List<TextView> tvList = new ArrayList<>();
    private List<NewHousEntity.DataBean.DateBean.ProblemBean> strNames;

    public SingleItemView(Context context) {
        this(context, null);
    }

    public SingleItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.single_textview_item_view, this);
        initView();
    }

    private void initView() {
        tvY = ((TextView) findViewById(R.id.tvY));
        tvX = ((TextView) findViewById(R.id.tvX));
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);
        et7 = (EditText) findViewById(R.id.et7);
        et8 = (EditText) findViewById(R.id.et8);
        et9 = (EditText) findViewById(R.id.et9);
        et10 = (EditText) findViewById(R.id.et10);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tv_remark = (TextView) findViewById(R.id.tv_remark);
        tvList.add((TextView) findViewById(R.id.tv1));
        tvList.add((TextView) findViewById(R.id.tv2));
        tvList.add((TextView) findViewById(R.id.tv3));
        tvList.add((TextView) findViewById(R.id.tv4));
        tvList.add((TextView) findViewById(R.id.tv5));
        tvList.add((TextView) findViewById(R.id.tv6));
        tvList.add((TextView) findViewById(R.id.tv7));
        tvList.add((TextView) findViewById(R.id.tv8));
        tvList.add((TextView) findViewById(R.id.tv9));
        tvList.add((TextView) findViewById(R.id.tv10));
        for (TextView tv : tvList) {
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.tv1:
                            setSelected(0);
                            break;
                        case R.id.tv2:
                            setSelected(1);
                            break;
                        case R.id.tv3:
                            setSelected(2);
                            break;
                        case R.id.tv4:
                            setSelected(3);
                            break;
                        case R.id.tv5:
                            setSelected(4);
                            break;
                        case R.id.tv6:
                            setSelected(5);
                            break;
                        case R.id.tv7:
                            setSelected(6);
                            break;
                        case R.id.tv8:
                            setSelected(7);
                            break;
                        case R.id.tv9:
                            setSelected(8);
                            break;
                        case R.id.tv10:
                            setSelected(9);
                            break;
                    }
                }
            });
        }
    }


    // 设施需要显示tem 数量
    public void setVisibleNum(int item_Num) {
        for (int i = 0; i < tvList.size(); i++) {
            if (i < item_Num) {
                tvList.get(i).setVisibility(View.VISIBLE);
            } else {
                tvList.get(i).setVisibility(View.GONE);
            }
        }
    }

    public void setTitleName(String name) {
        tvTitle.setText(name);

    }

    public void setRemark(String str, boolean isF) {
        if (isF) {
            tv_remark.setVisibility(View.VISIBLE);
            tv_remark.setText(str);
        } else {
            tv_remark.setVisibility(View.GONE);
        }

    }

    // 为每个item 设置名称
    public void setItemName(List<NewHousEntity.DataBean.DateBean.ProblemBean> strNames, String isZh) {
        this.strNames = strNames;
        Log.i("infoamtion", 1 + "--" + tvList.size() + "+++" + strNames.get(0).getType2());
        Log.i("infoamtion1", 1 + "--" + tvList.size() + "+++" + strNames.get(1).getType2());
        for (int i = 0; i < strNames.size(); i++) {
            if ("zh".equals(isZh)) {
                tvList.get(i).setText(strNames.get(i).getValue());
            } else {
                tvList.get(i).setText(strNames.get(i).getYvalue());
            }

        }


        if (strNames.size() >= 4) {
            if (strNames.get(3).getType2().equals("5")) {
                tvX.setVisibility(View.VISIBLE);
                String content;
                if ("zh".equals(isZh)) {
                    content = strNames.get(3).getValue();
                } else {
                    content = strNames.get(3).getYvalue();
                }
                String content1 = content.substring(0, content.indexOf("[") + 1);
                String content2 = content.substring(content.indexOf("]"), content.length());
                tvList.get(3).setText(content1);
                tvX.setText(content2);
                et4.setVisibility(View.VISIBLE);
                et4.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
//                        tvList.get(3).setSelected(true);
                        setSelected(3);
                        return false;
                    }
                });
            }
            if (strNames.size() > 6) {
                if (strNames.get(6).getType2().equals("6")) {
                    String content;
                    if ("zh".equals(isZh)) {
                        content = strNames.get(6).getValue();
                    } else {
                        content = strNames.get(6).getYvalue();
                    }
                    tvList.get(6).setText(content.substring(0, content.indexOf("#")));
                }
            }

            if (strNames.get(2).getType2().equals("5")) {
                tvY.setVisibility(View.VISIBLE);
                String content;
                if ("zh".equals(isZh)) {
                    content = strNames.get(2).getValue();
                } else {
                    content = strNames.get(2).getYvalue();
                }
                if (content.contains("[")) {
                    String content1 = content.substring(0, content.indexOf("[") );
                    String content2 = content.substring(content.indexOf("]")+1, content.length());
                    tvList.get(2).setText(content1);
                    tvY.setText(content2);
                    et3.setVisibility(View.VISIBLE);
                    et3.setOnTouchListener(new OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            setSelected(2);

                            return false;
                        }
                    });
                }
            }
        }
    }


        // 为每个item 设置名称
    public void setItemName1(List<NewHousEntity.DataBean.DateBean.ProblemBean> strNames, String isZh) {
        this.strNames = strNames;
        Log.i("infoamtion", 1 + "--" + tvList.size() + "+++" + strNames.get(0).getType2());
        Log.i("infoamtion1", 1 + "--" + tvList.size() + "+++" + strNames.get(1).getType2());
        for (int i = 0; i < strNames.size(); i++) {
            if ("zh".equals(isZh)) {
                if(strNames.get(i).getValue().equals("其他")) {
                    setShowView1(i+1,strNames.get(i).getValue());
                }else{
                        tvList.get(i).setText(strNames.get(i).getValue());
                    }
            }else{
                if(strNames.get(i).getValue().equals("Other")||strNames.get(i).getValue().equals("other")){

                    setShowView1(i+1,strNames.get(i).getYvalue());
                }else{
                    tvList.get(i).setText(strNames.get(i).getYvalue());
                }
            }

            }


            if (strNames.size() >= 4) {
                if (strNames.get(3).getType2().equals("5")) {
                    tvX.setVisibility(View.VISIBLE);
                    String content;
                    if ("zh".equals(isZh)) {
                        content = strNames.get(3).getValue();
                    } else {
                        content = strNames.get(3).getYvalue();
                    }
                    String content1 = content.substring(0, content.indexOf("[") + 1);
                    String content2 = content.substring(content.indexOf("]"), content.length());
                    tvList.get(3).setText(content1);
                    tvX.setText(content2);
                    et4.setVisibility(View.VISIBLE);
                    et4.setOnTouchListener(new OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
//                        tvList.get(3).setSelected(true);
                            setSelected(3);
                            return false;
                        }
                    });
                }
                if (strNames.size() > 6) {
                    if (strNames.get(6).getType2().equals("6")) {
                        String content;
                        if ("zh".equals(isZh)) {
                            content = strNames.get(6).getValue();
                        } else {
                            content = strNames.get(6).getYvalue();
                        }
                        tvList.get(6).setText(content.substring(0, content.indexOf("#")));
                    }
                }

                if (strNames.get(2).getType2().equals("5")) {
                    tvY.setVisibility(View.VISIBLE);
                    String content;
                    if ("zh".equals(isZh)) {
                        content = strNames.get(2).getValue();
                    } else {
                        content = strNames.get(2).getYvalue();
                    }
                    if (content.contains("[")) {
                        String content1 = content.substring(0, content.indexOf("[") );
                        String content2 = content.substring(content.indexOf("]")+1, content.length());
                        tvList.get(2).setText(content1);
                        tvY.setText(content2);
                        et3.setVisibility(View.VISIBLE);
                        et3.setOnTouchListener(new OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                setSelected(2);

                                return false;
                            }
                        });
                    }
                }
            }
        }



    private void setHideView() {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
        et9.setText("");
        et10.setText("");
        et1.setVisibility(View.GONE);
        et2.setVisibility(View.GONE);
        et3.setVisibility(View.GONE);
        et4.setVisibility(View.GONE);
        et5.setVisibility(View.GONE);
        et6.setVisibility(View.GONE);
        et7.setVisibility(View.GONE);
        et8.setVisibility(View.GONE);
        et9.setVisibility(View.GONE);
        et10.setVisibility(View.GONE);
    }



    private void setShowView1(int i,String hint) {
        if (i == 1) {
            et1.setVisibility(View.VISIBLE);
            et1.setHint(hint);
        } else if (i == 2) {
            et2.setVisibility(View.VISIBLE);
            et2.setVisibility(View.VISIBLE);
        } else if (i == 3) {
            et3.setHint(hint);
            et3.setVisibility(View.VISIBLE);
        } else if (i == 4) {
            et4.setHint(hint);
            et4.setVisibility(View.VISIBLE);
        } else if (i == 5) {
            et5.setHint(hint);
            et5.setVisibility(View.VISIBLE);
        } else if (i == 6) {
            et6.setHint(hint);
            et6.setVisibility(View.VISIBLE);
        } else if (i == 7) {
            et7.setHint(hint);
            et7.setVisibility(View.VISIBLE);
        } else if (i == 8) {
            et8.setHint(hint);
            et8.setVisibility(View.VISIBLE);
        } else if (i == 9) {
            et9.setHint(hint);
            et9.setVisibility(View.VISIBLE);
        } else if (i == 10) {
            et10.setHint(hint);
            et10.setVisibility(View.VISIBLE);
        }
    }


    private void setShowView(int i) {
        if (i == 1) {
            et1.setVisibility(View.VISIBLE);
        } else if (i == 2) {
            et2.setVisibility(View.VISIBLE);
        } else if (i == 3) {
            et3.setVisibility(View.VISIBLE);
        } else if (i == 4) {
            et4.setVisibility(View.VISIBLE);
        } else if (i == 5) {
            et5.setVisibility(View.VISIBLE);
        } else if (i == 6) {
            et6.setVisibility(View.VISIBLE);
        } else if (i == 7) {
            et7.setVisibility(View.VISIBLE);
        } else if (i == 8) {
            et8.setVisibility(View.VISIBLE);
        } else if (i == 9) {
            et9.setVisibility(View.VISIBLE);
        } else if (i == 10) {
            et10.setVisibility(View.VISIBLE);
        }
    }


    public void setSelected(int i) {
        setAllSelectFalse();
        Log.i("infoamtion", i + "--" + tvList.size() + "+++" + strNames.get(i).getType2());
        if (!tvList.get(i).isSelected()) {
            tvList.get(i).setSelected(true);
            if ("4".equals(strNames.get(i).getType2())) {
                setShowView(i + 1);
            } else if ("1".equals(strNames.get(i).getType2()) && "其他".equals(strNames.get(i).getValue())) {
//                setShowView(i + 1);
            } else if ("5".equals(strNames.get(i).getType2())) {
                setShowView(3);
            } else if (strNames.get(i).getType2().equals("6")) {
                setShowView(7);
            } else {
//                setHideView();
            }
            return;
        }
    }

    public void setAllSelectFalse() {
        for (int i = 0; i < tvList.size(); i++) {
            tvList.get(i).setSelected(false);
        }
    }


    public List<String> getSelectList() {
        List<String> list = new ArrayList<>();
        for (TextView tv : tvList) {
            if (tv.isSelected()) {
                list.add(tv.getText().toString());
            }
        }
        return list;
    }

    public String getSelectItem() {
        int i=0;

        for (TextView tv : tvList) {
            if (tv.isSelected()) {
                Log.i("typeInfomation",strNames.get(i).getType2());
                if("5".equals(strNames.get(i).getType2())){
                    if(AppGlobal.getInstance().getLagStr().equals("zh")){
                        return "面积有"+et3.getText().toString()+"辆小汽车般大";
                    }else{
                        return "as big as "+et3.getText().toString()+" cars";
                    }
                }else if("6".equals(strNames.get(i).getType2())){
                    if(AppGlobal.getInstance().getLagStr().equals("zh")){
                        return et7.getText().toString();
                    }else{
                        return et7.getText().toString();
                    }
                }else{
                    return tv.getText().toString();
                }

            }
            i++;
        }
        return "";
    }
    public int getSelectNo1() {
        boolean isHave = false;
        int m = 0;
        for (int i = 0; i < tvList.size(); i++) {
            if (tvList.get(i).isSelected()) {
                m=i;
                break;
            }
        }
        return m;
    }


    public int getSelectNo() {
        boolean isHave = false;
        int m = 0;
        for (int i = 0; i < tvList.size(); i++) {
            if (tvList.get(i).isSelected()) {
                m++;
                isHave = true;
                break;
            }
        }
        if (isHave) {
            return m;
        } else {
            return 0;
        }
    }

    public boolean isSelectOne(){
        boolean isSelectOne = false;
        for(TextView tv : tvList){
            if(tv.isSelected()){
                isSelectOne = true;
                break;
            }
        }
        return isSelectOne;
    }

    public String getSelectItemId() {
        int i = 0;
        for (TextView tv : tvList) {
            if (tv.isSelected()) {
                if (TextUtils.isEmpty(getEt(i + 1).getText().toString().trim())) {
                    return strNames.get(i).getId();
                } else {
                    return "#" + strNames.get(i).getId() + "#" + getEt(i + 1).getText().toString().trim();
                }
            }
            ++i;
        }
        return "";
    }



    public EditText getEt(int i) {
        if (i == 1) {
            return et1;
        } else if (i == 2) {
            return et2;
        } else if (i == 3) {
            return et3;
        } else if (i == 4) {
            return et4;
        } else if (i == 5) {
            return et5;
        } else if (i == 6) {
            return et6;
        } else if (i == 7) {
            return et7;
        } else if (i == 8) {
            return et8;
        } else if (i == 9) {
            return et9;
        } else if (i == 10) {
            return et10;
        }
        return null;
    }
}
