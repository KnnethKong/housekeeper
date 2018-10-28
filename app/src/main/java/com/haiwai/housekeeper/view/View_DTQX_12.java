package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.utils.PlatUtils;

/**
 * Created by ihope007 on 2016/10/18.
 */
public class View_DTQX_12 extends LinearLayout {// TODO: 2016/8/28 上传图片
    private TextView tvtitle;
    private TextView tv_add;
    private RadioGroup radioGroup;
    private String answer = "";
    private RadioButton rb_no,rb_yes;

//    private static int REQUESTCODE = 0;
//    private static int REQUESTCODEB = 1;
//    private static final int REQUESTCODE_A = 100;
//    private static final int REQUESTCODE_B = 101;
//    private static final int REQUESTCODE_C = 102;
//    private static final int REQUESTCODE_D = 103;
//    private static final int REQUESTCODE_E = 104;
//    private static final int REQUESTCODE_F = 105;
//    private String path1, path2, path3;
//    private ImageView scView, zmView, fmView, scdView, zmdView, fmdView;

    public View_DTQX_12(Context context) {
        this(context, null);
    }

    public View_DTQX_12(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_DTQX_12(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_dtqx_12, this);
        initView();
    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.view_dtqx_12_tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.view_dtqx_12_rg);
        rb_no = (RadioButton) findViewById(R.id.view_dtqx_12_rb_no);
        rb_yes = (RadioButton) findViewById(R.id.view_dtqx_12_rb_yes);
        tv_add = (TextView) findViewById(R.id.view_dtqx_12_tv_add);
        tv_add.setVisibility(GONE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==rb_no.getId()){
                    tv_add.setVisibility(GONE);
                }else if(checkedId==rb_yes.getId()){
                    tv_add.setVisibility(VISIBLE);
                }
            }
        });
    }

    public void setData(String title,String r1,String r2){
        tvtitle.setText(title);
        rb_no.setText(r1);
        rb_yes.setText(r2);
    }

    public String getQuestion() {
        return tvtitle.getText().toString().trim();
    }

    public boolean getIsEmpty(){
        if (radioGroup.getCheckedRadioButtonId()==rb_yes.getId()) {
//            if (PlatUtils.getEditStr(et_yes)) {
//                return true;
//            } else {
                return true;
//            }
        }else{
            return false;
        }
    }

}
