package com.haiwai.housekeeper.activity.user.message;


import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.base.SysMessageActivity;
import com.haiwai.housekeeper.activity.user.ContactActivity;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.entity.User;
import com.haiwai.housekeeper.fragment.user.ConversationListStaticFragment;
import com.haiwai.housekeeper.https.Contants;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.SPUtils;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by range on 2018/1/15.
 */

public class ConversationListStaticActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationliststatic_activity_main);
        initView();
    }




    private void initView() {
        fragmentManager = getSupportFragmentManager();
        fragments.add(new ConversationListStatci1Fragment());
        showFragment();
    }

    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //如果之前没有添加过
        if (!fragments.get(0).isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.main_body, fragments.get(0), "" + 0);  //第三个参数为添加当前的fragment时绑定一个tag
        } else {
            transaction
                    .hide(currentFragment)
                    .show(fragments.get(0));
        }
        currentFragment = fragments.get(0);
        transaction.commit();
    }


}
