package com.haiwai.housekeeper.fragment.server;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.user.ContactActivity;
import com.haiwai.housekeeper.base.BaseProFragment;

public class MessageFragment extends BaseProFragment {
    private ImageButton user_msg_set;


    @Override
    protected void init() {

    }

    public View initView() {
        View messageView = View.inflate(context, R.layout.pro_fragment_message, null);
        initView(messageView);
        return messageView;
    }

    @Override
    protected void initData() {

    }

    private void initView(View messageView) {
        user_msg_set = (ImageButton) messageView.findViewById(R.id.user_msg_set);
        user_msg_set.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(context, ContactActivity.class));
        }
    };

}
