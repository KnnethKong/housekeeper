package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haiwai.housekeeper.R;

/**
 * Created by ihope007 on 2016/11/23.
 */
public class ProGradeView extends LinearLayout {
    private ImageView iv1, iv2, iv3, iv4, iv5;

    public ProGradeView(Context context) {
        this(context, null);
    }

    public ProGradeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProGradeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.pro_grade, this);
        initView();
    }

    private void initView() {
        iv1 = (ImageView) findViewById(R.id.pro_grade_iv_1);
        iv2 = (ImageView) findViewById(R.id.pro_grade_iv_2);
        iv3 = (ImageView) findViewById(R.id.pro_grade_iv_3);
        iv4 = (ImageView) findViewById(R.id.pro_grade_iv_4);
        iv5 = (ImageView) findViewById(R.id.pro_grade_iv_5);
    }

    /**
     * 根据返回的proscore设置等级图标显示
     *
     * @param proscore
     */
    public void setNum(int proscore) {
        if (proscore >= 0 && proscore <= 10) {
            iv1.setImageResource(R.mipmap.grade1);
        } else if (proscore >= 11 && proscore <= 40) {
            iv1.setImageResource(R.mipmap.grade1);
            iv2.setImageResource(R.mipmap.grade1);
        } else if (proscore >= 41 && proscore <= 90) {
            iv1.setImageResource(R.mipmap.grade1);
            iv2.setImageResource(R.mipmap.grade1);
            iv3.setImageResource(R.mipmap.grade1);
        } else if (proscore >= 91 && proscore <= 150) {
            iv1.setImageResource(R.mipmap.grade1);
            iv2.setImageResource(R.mipmap.grade1);
            iv3.setImageResource(R.mipmap.grade1);
            iv4.setImageResource(R.mipmap.grade1);
        } else if (proscore >= 151 && proscore <= 250) {
            iv1.setImageResource(R.mipmap.grade1);
            iv2.setImageResource(R.mipmap.grade1);
            iv3.setImageResource(R.mipmap.grade1);
            iv4.setImageResource(R.mipmap.grade1);
            iv5.setImageResource(R.mipmap.grade1);
        } else if (proscore >= 251 && proscore <= 500) {
            iv1.setImageResource(R.mipmap.grade2);
        } else if (proscore >= 501 && proscore <= 1000) {
            iv1.setImageResource(R.mipmap.grade2);
            iv2.setImageResource(R.mipmap.grade2);
        } else if (proscore >= 1001 && proscore <= 2000) {
            iv1.setImageResource(R.mipmap.grade2);
            iv2.setImageResource(R.mipmap.grade2);
            iv3.setImageResource(R.mipmap.grade2);
        } else if (proscore >= 2001 && proscore <= 5000) {
            iv1.setImageResource(R.mipmap.grade2);
            iv2.setImageResource(R.mipmap.grade2);
            iv3.setImageResource(R.mipmap.grade2);
            iv4.setImageResource(R.mipmap.grade2);
        } else if (proscore >= 5001 && proscore <= 10000) {
            iv1.setImageResource(R.mipmap.grade2);
            iv2.setImageResource(R.mipmap.grade2);
            iv3.setImageResource(R.mipmap.grade2);
            iv4.setImageResource(R.mipmap.grade2);
            iv5.setImageResource(R.mipmap.grade2);
        } else if (proscore >= 10001 && proscore <= 20000) {
            iv1.setImageResource(R.mipmap.grade3);
        } else if (proscore >= 20001 && proscore <= 50000) {
            iv1.setImageResource(R.mipmap.grade3);
            iv2.setImageResource(R.mipmap.grade3);
        } else if (proscore >= 50001 && proscore <= 100000) {
            iv1.setImageResource(R.mipmap.grade3);
            iv2.setImageResource(R.mipmap.grade3);
            iv3.setImageResource(R.mipmap.grade3);
        } else if (proscore >= 100001 && proscore <= 200000) {
            iv1.setImageResource(R.mipmap.grade3);
            iv2.setImageResource(R.mipmap.grade3);
            iv3.setImageResource(R.mipmap.grade3);
            iv4.setImageResource(R.mipmap.grade3);
        } else if (proscore >= 200001 && proscore <= 500000) {
            iv1.setImageResource(R.mipmap.grade3);
            iv2.setImageResource(R.mipmap.grade3);
            iv3.setImageResource(R.mipmap.grade3);
            iv4.setImageResource(R.mipmap.grade3);
            iv5.setImageResource(R.mipmap.grade3);
        } else if (proscore >= 500001 && proscore <= 1000000) {
            iv1.setImageResource(R.mipmap.grade4);
        } else if (proscore >= 1000001 && proscore <= 2000000) {
            iv1.setImageResource(R.mipmap.grade4);
            iv2.setImageResource(R.mipmap.grade4);
        } else if (proscore >= 2000001 && proscore <= 5000000) {
            iv1.setImageResource(R.mipmap.grade4);
            iv2.setImageResource(R.mipmap.grade4);
            iv3.setImageResource(R.mipmap.grade4);
        } else if (proscore >= 5000001 && proscore <= 10000000) {
            iv1.setImageResource(R.mipmap.grade4);
            iv2.setImageResource(R.mipmap.grade4);
            iv3.setImageResource(R.mipmap.grade4);
            iv4.setImageResource(R.mipmap.grade4);
        } else if (proscore >= 10000001) {
            iv1.setImageResource(R.mipmap.grade4);
            iv2.setImageResource(R.mipmap.grade4);
            iv3.setImageResource(R.mipmap.grade4);
            iv4.setImageResource(R.mipmap.grade4);
            iv5.setImageResource(R.mipmap.grade4);
        }
    }

}
