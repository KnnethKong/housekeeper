package com.haiwai.housekeeper.activity.server;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haiwai.housekeeper.activity.base.LoginActivity;
import com.haiwai.housekeeper.activity.user.FwZdActivity;
import com.haiwai.housekeeper.activity.user.OrderConfigActivity;
import com.haiwai.housekeeper.activity.user.VipNewHouseChooseActivity;

/**
 * Created by lyj on 2016/9/22.
 */
public class ListDemoActivity extends ListActivity {
    private ClassAndName[] datas = {
//            new ClassAndName(GoogleMapActivity.class, "地图")
            new ClassAndName(VipNewHouseChooseActivity.class,"添加房产"),
            new ClassAndName(FwZdActivity.class,"组合管理"),
            new ClassAndName(OrderConfigActivity.class,"配置订单"),
            new ClassAndName(AnimalActivity.class,"测试动画")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<ClassAndName> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, datas);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ClassAndName mClassAndName = (ClassAndName) l
                .getItemAtPosition(position);
        startActivity(new Intent(this, mClassAndName.clazz));
    }

    class ClassAndName {
        private Class<?> clazz;
        private String name;

        public ClassAndName(Class<?> clazz, String name) {
            this.clazz = clazz;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
