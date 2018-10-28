package com.haiwai.housekeeper.activity.server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.utils.TimeUtils;
import com.haiwai.housekeeper.view.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class TestListActivity extends BaseProActivity implements XListView.IXListViewListener {
    private XListView xlistview_content;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("ceshisjdi" + i);
        }
    }

    private void initView() {
        xlistview_content = (XListView) findViewById(R.id.xlistview_content);
        xlistview_content.setXListViewListener(this);
        xlistview_content.setPullLoadEnable(true);
        xlistview_content.setRefreshTime(System.currentTimeMillis());
        mAdapter = new ArrayAdapter<String>(TestListActivity.this, android.R.layout.simple_list_item_1, list);
        xlistview_content.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        list.clear();
        initData();
    }

    @Override
    public void onLoadMore() {
        for (int i = 0; i < 5; i++) {
            list.add("moooooo" + i);
        }
        mAdapter.notifyDataSetChanged();
    }
}
