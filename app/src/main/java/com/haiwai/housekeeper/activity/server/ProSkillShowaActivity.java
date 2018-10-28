package com.haiwai.housekeeper.activity.server;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.adapter.ProSkillShowaItemAdapter;
import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.BaseProActivity;
import com.haiwai.housekeeper.base.BaseProAdapter;
import com.haiwai.housekeeper.entity.SkillItemEntity;
import com.haiwai.housekeeper.entity.TitleEntity;
import com.haiwai.housekeeper.entity.TitleItem;
import com.haiwai.housekeeper.utils.AssetsUtils;
import com.haiwai.housekeeper.utils.ToastUtil;
import com.haiwai.housekeeper.view.MyGridView;
import com.haiwai.housekeeper.view.TopViewNormalBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布技能
 * Created by lyj on 2016/9/21.
 */
public class ProSkillShowaActivity extends BaseProActivity {
    private List<TitleEntity> mTitleList;
    private List<TitleItem> itemList;
    private ListView lv_skill;
    private ProSkillShowaAdapter mProSkillShowaAdapter;
    private ProSkillShowaItemAdapter mProSkillShowaItemAdapter;
    private TopViewNormalBar top_skillShowa_bar;
    private List<SkillItemEntity> typeList;
    private String isZhorEn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_skill_show_zero_layout);
        top_skillShowa_bar = (TopViewNormalBar) findViewById(R.id.top_skillShowa_bar);
        top_skillShowa_bar.setTitle(getString(R.string.skill_adds));
        top_skillShowa_bar.setOnBackListener(mOnClickListener);
        initData();
        initView();
    }

    private void initData() {
        isZhorEn = AppGlobal.getInstance().getLagStr();
        typeList = (List<SkillItemEntity>) ((Bundle) getIntent().getBundleExtra("bundle")).getSerializable("typeList");
        String json = AssetsUtils.getJson(ProSkillShowaActivity.this);
        Log.e("kxflog", "initData: "+json );
        try {
            JSONArray jsonArray = new JSONArray(json);
            mTitleList = new ArrayList<>();
            if ("zh".equalsIgnoreCase(isZhorEn)) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    TitleEntity title = new TitleEntity();
                    if (!"周期管理服务".equals((String) jsonObject.opt("hncn"))) {
                        title.setName((String) jsonObject.opt("hncn"));
                        JSONArray jArray = jsonObject.getJSONArray("hccn");
                        itemList = new ArrayList<>();
                        for (int j = 0; j < jArray.length(); j++) {
                            JSONObject joOb = jArray.getJSONObject(j);
                            TitleItem titleItem = new TitleItem();
                            titleItem.setName((String) joOb.opt("ty"));
                            titleItem.setType((String) joOb.opt("type"));
                            if (typeList != null && typeList.size() > 0) {
                                for (int k = 0; k < typeList.size(); k++) {//-----------断点
                                    if (typeList.get(k).getType().equals(joOb.optString("type"))) {
                                        titleItem.setSelected(true);
                                        titleItem.setStatus(typeList.get(k).getStatus());
                                    }
                                }
                            }
                            itemList.add(titleItem);
                        }
                        title.setItems(itemList);
                        mTitleList.add(title);
                    }
                }
            } else if ("en".equalsIgnoreCase(isZhorEn)) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    TitleEntity title = new TitleEntity();
                    if (!"Cycle service".equals((String) jsonObject.opt("hnen"))) {
                        title.setName((String) jsonObject.opt("hnen"));
                        JSONArray jArray = jsonObject.getJSONArray("hcen");
                        itemList = new ArrayList<>();
                        for (int j = 0; j < jArray.length(); j++) {
                            JSONObject joOb = jArray.getJSONObject(j);
                            TitleItem titleItem = new TitleItem();
                            titleItem.setName((String) joOb.opt("ty"));
                            titleItem.setType((String) joOb.opt("type"));
                            if (typeList != null && typeList.size() > 0) {
                                for (int k = 0; k < typeList.size(); k++) {
                                    if (typeList.get(k).getType().equals(joOb.optString("type"))) {
                                        titleItem.setSelected(true);
                                        titleItem.setStatus(typeList.get(k).getStatus());
                                    }
                                }
                            }
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

    private void initView() {
        lv_skill = (ListView) findViewById(R.id.lv_skill);
        mProSkillShowaAdapter = new ProSkillShowaAdapter(ProSkillShowaActivity.this, mTitleList);
        lv_skill.setAdapter(mProSkillShowaAdapter);
        mProSkillShowaAdapter.notifyDataSetChanged();
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == top_skillShowa_bar.getBackView()) {
                finish();
            }
        }
    };


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
                convertView = View.inflate(mContext, R.layout.pro_skill_show_zero_layout_item, null);
                viewHolder = new ViewHolder();
                viewHolder.tvTite = (TextView) convertView.findViewById(R.id.tv_skill_title);
                viewHolder.mGridView = (MyGridView) convertView.findViewById(R.id.m_gridview);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final TitleEntity title = mTitleList.get(position);
            viewHolder.tvTite.setText(title.getName());
            mProSkillShowaItemAdapter = new ProSkillShowaItemAdapter(mContext, title.getItems());
            mProSkillShowaItemAdapter.setOnItemSelectedListener(
                    new ProSkillShowaItemAdapter.OnItemSelectedListener() {
                        @Override
                        public void onItemSelect(int childPosition) {
                            TitleItem titleItem = mTitleList.get(position).getItems().get(childPosition);
                            if ("0".equals(titleItem.getStatus())) {
                                ToastUtil.longToast(ProSkillShowaActivity.this, getString(R.string.skill_status1));
                            } else if ("1".equals(titleItem.getStatus())) {
                                ToastUtil.longToast(ProSkillShowaActivity.this, getString(R.string.skill_status2));
                            } else if ("2".equals(titleItem.getStatus())) {
                                ToastUtil.longToast(ProSkillShowaActivity.this, getString(R.string.skill_status3));
                            } else {
                                Intent intent = new Intent(ProSkillShowaActivity.this, ProSkillShowbActivity.class);
                                intent.putExtra("titleItem", titleItem);
                                startActivity(intent);
                            }
                        }
                    }

            );
            viewHolder.mGridView.setAdapter(mProSkillShowaItemAdapter);
            return convertView;
        }

        class ViewHolder {
            TextView tvTite;
            MyGridView mGridView;
        }

    }
}
