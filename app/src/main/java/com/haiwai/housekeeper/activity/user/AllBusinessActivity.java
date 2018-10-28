package com.haiwai.housekeeper.activity.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.activity.server.ProSkillShowbActivity;
import com.haiwai.housekeeper.adapter.AllBusinessItemAdapter;
import com.haiwai.housekeeper.adapter.PopGvAdapter;
import com.haiwai.housekeeper.adapter.ProSkillShowaItemAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseActivity;
import com.haiwai.housekeeper.base.BaseProAdapter;
import com.haiwai.housekeeper.entity.TitleEntity;
import com.haiwai.housekeeper.entity.TitleItem;
import com.haiwai.housekeeper.service.IMKitService;
import com.haiwai.housekeeper.utils.ActivityTools;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.view.MyGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ihope007 on 2016/9/20.
 * 所有业务
 */
public class AllBusinessActivity extends BaseActivity {
    private List<TitleEntity> mTitleList;
    private List<TitleItem> itemList;
    private ListView lv_skill;
    private ProSkillShowaAdapter mProSkillShowaAdapter;
    private AllBusinessItemAdapter mProSkillShowaItemAdapter;
    private String isZhorEn = "";
    private boolean isAll = false;

    @Override
    protected View onCreateLayout(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_all_business, null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle(getString(R.string.all_business_title), Color.parseColor("#FF3C3C3C"));
        isZhorEn = AppGlobal.getInstance().getLagStr();
        isAll = getIntent().getBooleanExtra("isAll", false);
        if ("zh".equalsIgnoreCase(isZhorEn))
            initZhType();
        else
            initEnType();
        lv_skill = (ListView) findViewById(R.id.lv_skill);
        lv_skill.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mProSkillShowaAdapter = new ProSkillShowaAdapter(AllBusinessActivity.this, mTitleList);
        lv_skill.setAdapter(mProSkillShowaAdapter);
        mProSkillShowaAdapter.notifyDataSetChanged();
//        lv_skill.setSelection(lv_skill.getBottom());
//        lv_skill.setSelection(mProSkillShowaAdapter.getCount()-1);
    }

    public void initZhType() {
        String json = AssetsUtils.getJson(AllBusinessActivity.this);
        try {
            JSONArray jsonArray = new JSONArray(json);
            mTitleList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (isAll) {
                    TitleEntity title = new TitleEntity();
                    title.setName((String) jsonObject.opt("hncn"));
                    JSONArray jArray = jsonObject.getJSONArray("hccn");
                    itemList = new ArrayList<>();
                    for (int j = 0; j < jArray.length(); j++) {
                        JSONObject joOb = jArray.getJSONObject(j);
                        TitleItem titleItem = new TitleItem();
                        if (Integer.valueOf((String) joOb.opt("type"))<29){
                            titleItem.setName((String) joOb.opt("ty"));
                            titleItem.setType((String) joOb.opt("type"));
                            itemList.add(titleItem);
                        }
                    }
                    title.setItems(itemList);
                    mTitleList.add(title);
                } else {
                    if (!"周期服务".equals((String) jsonObject.opt("hncn"))) {
                        TitleEntity title = new TitleEntity();
                        title.setName((String) jsonObject.opt("hncn"));
                        JSONArray jArray = jsonObject.getJSONArray("hccn");
                        itemList = new ArrayList<>();
                        for (int j = 0; j < jArray.length(); j++) {
                            JSONObject joOb = jArray.getJSONObject(j);
                            TitleItem titleItem = new TitleItem();
                            titleItem.setName((String) joOb.opt("ty"));
                            titleItem.setType((String) joOb.opt("type"));
                            itemList.add(titleItem);
                        }
                        title.setItems(itemList);
                        mTitleList.add(title);
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initEnType() {
        String json = AssetsUtils.getJson(AllBusinessActivity.this);
        try {
            JSONArray jsonArray = new JSONArray(json);
            mTitleList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TitleEntity title = new TitleEntity();
                if (isAll) {
                    title.setName((String) jsonObject.opt("hnen"));
                    JSONArray jArray = jsonObject.getJSONArray("hcen");
                    itemList = new ArrayList<>();
                    for (int j = 0; j < jArray.length(); j++) {
                        JSONObject joOb = jArray.getJSONObject(j);
                        TitleItem titleItem = new TitleItem();
                        titleItem.setName((String) joOb.opt("ty"));
                        titleItem.setType((String) joOb.opt("type"));
                        itemList.add(titleItem);
                    }
                    title.setItems(itemList);
                    mTitleList.add(title);
                } else {
                    if (!"Cycle service".equals((String) jsonObject.opt("hnen"))) {
                        title.setName((String) jsonObject.opt("hnen"));
                        JSONArray jArray = jsonObject.getJSONArray("hcen");
                        itemList = new ArrayList<>();
                        for (int j = 0; j < jArray.length(); j++) {
                            JSONObject joOb = jArray.getJSONObject(j);
                            TitleItem titleItem = new TitleItem();
                            titleItem.setName((String) joOb.opt("ty"));
                            titleItem.setType((String) joOb.opt("type"));
                            itemList.add(titleItem);
                        }
                        title.setItems(itemList);
                        mTitleList.add(title);
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void click(View v) {

    }

    class ProSkillShowaAdapter extends BaseProAdapter<TitleEntity> {
        private Context mContext;
        private List<TitleEntity> mTitleList;

        public ProSkillShowaAdapter(Context context, List<TitleEntity> lists) {
            super(context, lists);
            this.mContext = context;
            this.mTitleList = lists;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_all_business, null);
                viewHolder = new ViewHolder();
                viewHolder.tvTite = (TextView) convertView.findViewById(R.id.tv_skill_title);
                viewHolder.mGridView = (MyGridView) convertView.findViewById(R.id.m_gridview);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            TitleEntity title = mTitleList.get(position);
            if(!"周期管理服务".equals(title.getName())&&!"Cycle service".equals(title.getName())){
                viewHolder.tvTite.setText(title.getName());
            }else{
                viewHolder.tvTite.setVisibility(View.GONE);
            }
            mProSkillShowaItemAdapter = new AllBusinessItemAdapter(mContext, title.getItems());
            mProSkillShowaItemAdapter.setOnItemSelectedListener(new AllBusinessItemAdapter.OnItemSelectedListener() {
                @Override
                public void onItemSelect(int childPosition) {
                    TitleItem titleItem = mTitleList.get(position).getItems().get(childPosition);
                    if (Integer.valueOf(titleItem.getType()) < 29) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", titleItem.getType());
                        ActivityTools.goNextActivity(AllBusinessActivity.this, O2ODetailActivity.class, bundle);
                    } else if (Integer.valueOf(titleItem.getType()) == 29) {
                        gotoOtherActivity("5");
                    } else if (Integer.valueOf(titleItem.getType()) == 20) {
                        gotoOtherActivity("2");
                    } else if (Integer.valueOf(titleItem.getType()) == 31 || Integer.valueOf(titleItem.getType()) == 31) {
                        gotoOtherActivity("4");
                    } else if (Integer.valueOf(titleItem.getType()) == 32 || Integer.valueOf(titleItem.getType()) == 33 || Integer.valueOf(titleItem.getType()) == 34) {
                        gotoOtherActivity("3");
                    }
                }
            });
            viewHolder.mGridView.setAdapter(mProSkillShowaItemAdapter);
            return convertView;
        }

        class ViewHolder {
            TextView tvTite;
            MyGridView mGridView;
        }

    }

    private void gotoOtherActivity(String type) {
        IMKitService.isConfig = false;
        AppGlobal.getInstance().setIsFirst(true);
        AppGlobal.getInstance().setIsHome(true);
        Intent intent = new Intent(AllBusinessActivity.this, VipNewHouseChooseActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
