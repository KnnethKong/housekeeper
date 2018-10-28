package com.haiwai.housekeeper.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haiwai.housekeeper.R;
import com.haiwai.housekeeper.entity.NewHousEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihope006 on 2016/12/6.
 */

public class MultiItemView extends LinearLayout {
    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
    private TextView tv1d, tv2dd, tv3d, tv4d, tv5d, tv6d, tv7d, tv8d, tv9d, tv10d;
    public ImageView iv_th_red;
    private boolean isb1 = false, isb2 = false, isb3 = false, isb4 = false,
            isb5 = false, isb6 = false, isb7 = false, isb8 = false, isb9 = false, isb10 = false;
    private TextView tvTitle, tv_mremark;
    private List<TextView> tvList = new ArrayList<>();
    private List<TextView> desList = new ArrayList<>();
    private List<NewHousEntity.DataBean.DateBean.ProblemBean> strNames;

    public MultiItemView(Context context) {
        this(context, null);
    }

    public MultiItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.multi_textview_item_view, this);
        initView();
    }

    private void initView() {
        iv_th_red = (ImageView) findViewById(R.id.iv_th_red);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tv_mremark = (TextView) findViewById(R.id.tv_mremark);
        desList.add((TextView) findViewById(R.id.tv1d));
        desList.add((TextView) findViewById(R.id.tv2d));
        desList.add((TextView) findViewById(R.id.tv3d));
        desList.add((TextView) findViewById(R.id.tv4d));
        desList.add((TextView) findViewById(R.id.tv5d));
        desList.add((TextView) findViewById(R.id.tv6d));
        desList.add((TextView) findViewById(R.id.tv7d));
        desList.add((TextView) findViewById(R.id.tv8d));
        desList.add((TextView) findViewById(R.id.tv9d));
        desList.add((TextView) findViewById(R.id.tv10d));

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
                            if (!isb1) {
                                isb1 = true;
                                tvList.get(0).setSelected(true);
                            } else {
                                isb1 = false;
                                tvList.get(0).setSelected(false);
                            }

                            break;
                        case R.id.tv2:
                            if (!isb2) {
                                isb2 = true;
                                tvList.get(1).setSelected(true);
                            } else {
                                isb2 = false;
                                tvList.get(1).setSelected(false);
                            }
                            break;
                        case R.id.tv3:
                            if (!isb3) {
                                isb3 = true;
                                tvList.get(2).setSelected(true);
                            } else {
                                isb3 = false;
                                tvList.get(2).setSelected(false);
                            }
                            break;
                        case R.id.tv4:
                            if (!isb4) {
                                isb4 = true;
                                tvList.get(3).setSelected(true);
                            } else {
                                isb4 = false;
                                tvList.get(3).setSelected(false);
                            }
                            break;
                        case R.id.tv5:
                            if (!isb5) {
                                isb5 = true;
                                tvList.get(4).setSelected(true);
                            } else {
                                isb5 = false;
                                tvList.get(4).setSelected(false);
                            }
                            break;
                        case R.id.tv6:
                            if (!isb6) {
                                isb6 = true;
                                tvList.get(5).setSelected(true);
                            } else {
                                isb6 = false;
                                tvList.get(5).setSelected(false);
                            }
                            break;
                        case R.id.tv7:
                            if (!isb7) {
                                isb7 = true;
                                tvList.get(6).setSelected(true);
                            } else {
                                isb7 = false;
                                tvList.get(6).setSelected(false);
                            }
                            break;
                        case R.id.tv8:
                            if (!isb8) {
                                isb8 = true;
                                tvList.get(7).setSelected(true);
                            } else {
                                isb8 = false;
                                tvList.get(7).setSelected(false);
                            }
                            break;
                        case R.id.tv9:
                            if (!isb9) {
                                isb9 = true;
                                tvList.get(8).setSelected(true);
                            } else {
                                isb9 = false;
                                tvList.get(8).setSelected(false);
                            }
                            break;
                        case R.id.tv10:
                            if (!isb10) {
                                isb10 = true;
                                tvList.get(9).setSelected(true);
                            } else {
                                isb10 = false;
                                tvList.get(9).setSelected(false);
                            }
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

    public ImageView getIv_th_red() {
        return iv_th_red;
    }

    public void setImVisible(boolean isV) {
        if (isV) {
            iv_th_red.setVisibility(View.VISIBLE);
        } else {
            iv_th_red.setVisibility(View.GONE);
        }
    }

    // 设施需要显示tem 数量
    public void setVisibleNum(List<NewHousEntity.DataBean.DateBean.ProblemBean> strNames, int item_Num) {
        for (int i = 0; i < tvList.size(); i++) {
            if (i < item_Num) {
                tvList.get(i).setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(strNames.get(i).getRemark())) {
                    desList.get(i).setVisibility(View.GONE);
                } else {
                    desList.get(i).setVisibility(View.VISIBLE);

                }
            } else {
                tvList.get(i).setVisibility(View.GONE);
                desList.get(i).setVisibility(View.GONE);
            }
        }
    }

    public void setTitleName(String name) {
        tvTitle.setText(name);
        if(name.contains("不同季节我们还提供落叶清扫和清雪服务")){
            iv_th_red.setVisibility(View.VISIBLE);
            iv_th_red.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"此处两种服务都不选当前视为“跳过”",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setRemark(String str, boolean isF) {
        if (isF) {
            tv_mremark.setVisibility(View.VISIBLE);
            tv_mremark.setText(str);
        } else {
            tv_mremark.setVisibility(View.GONE);
        }

    }

    // 为每个item 设置名称
    public void setItemName(List<NewHousEntity.DataBean.DateBean.ProblemBean> strNames, String type) {
        this.strNames = strNames;
        for (int i = 0; i < strNames.size(); i++) {
            if ("zh".equals(type)) {
                tvList.get(i).setText(strNames.get(i).getValue());
                desList.get(i).setText(strNames.get(i).getRemark());
            } else {
                tvList.get(i).setText(strNames.get(i).getYvalue());
                desList.get(i).setText(strNames.get(i).getYremark());
            }

        }
    }

    public void setAllSelectFalse() {
        for (int i = 0; i < tvList.size(); i++) {
            tvList.get(i).setSelected(false);
        }
    }


    public List<String> getSelectList() {
        List<String> list = new ArrayList<String>();
        for (TextView tv : tvList) {
            if (tv.isSelected()) {
                list.add(tv.getText().toString());
            }
        }
        return list;
    }

    public String getSelectItem() {
        for (TextView tv : tvList) {
            if (tv.isSelected()) {
                return tv.getText().toString();
            }
        }
        return "";
    }

    public List<String> getSelectIdList() {
        List<String> list = new ArrayList<String>();
        int i = 0;
        for (TextView tv : tvList) {
            if (tv.isSelected()) {
                list.add(strNames.get(i).getId());
            }
            ++i;
        }
        return list;
    }

    public String getSelectItemId() {
        int i = 0;
        for (TextView tv : tvList) {
            if (tv.isSelected()) {
                return strNames.get(i).getId();
            }
            ++i;
        }
        return "";
    }
}
