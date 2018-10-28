package com.haiwai.housekeeper.activity.server;

import android.os.Bundle;
import android.view.View;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.view.TopViewNormalBar;

/**
 * 暂时无用，等待修改
 */
public class ProAddSkillActivity extends BaseProActivity {
    private TopViewNormalBar mAddSkillBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_add_skill);
        mAddSkillBar = (TopViewNormalBar) findViewById(R.id.top_add_skill_bar);
        mAddSkillBar.setTitle("发布技能");
        mAddSkillBar.setOnBackListener(mOnClickListener);
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == mAddSkillBar.getBackView()){
                finish();
            }
        }
    };
}
