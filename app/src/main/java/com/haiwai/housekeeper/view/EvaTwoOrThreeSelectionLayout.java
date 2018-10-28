package com.haiwai.housekeeper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiwai.housekeeper.R;

import java.util.ArrayList;
import java.util.List;

public class EvaTwoOrThreeSelectionLayout extends LinearLayout {
    private Context context;
    private List<TextView> TV_list = new ArrayList<>();
    boolean flag01 = false, flag02 = false, flag03 = false, flag04 = false, flag05 = false, flag06 = false, flag07 = false;
    public EvaTwoOrThreeSelectionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.evaluate_two_three_star_layout, this);
        setViews();
    }

    private void setViews() {
        TV_list.add((TextView) findViewById(R.id.tv_ev1));
        TV_list.add((TextView) findViewById(R.id.tv_ev2));
        TV_list.add((TextView) findViewById(R.id.tv_ev3));
        TV_list.add((TextView) findViewById(R.id.tv_ev4));
        TV_list.add((TextView) findViewById(R.id.tv_ev5));
        TV_list.add((TextView) findViewById(R.id.tv_ev6));
        TV_list.add((TextView) findViewById(R.id.tv_ev7));
        for (TextView tv : TV_list) {
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.tv_ev1:
                            if (!flag01) {
                                flag01 = true;
                                setSelected(0);
                            } else {
                                flag01 = false;
                                setSelectFalse(0);
                            }
                            break;
                        case R.id.tv_ev2:
                            if (!flag02) {
                                flag02 = true;
                                setSelected(1);
                            } else {
                                flag02 = false;
                                setSelectFalse(1);
                            }
                            break;
                        case R.id.tv_ev3:
                            if (!flag03) {
                                flag03 = true;
                                setSelected(2);
                            } else {
                                flag03 = false;
                                setSelectFalse(2);
                            }
                            break;
                        case R.id.tv_ev4:
                            if (!flag04) {
                                flag04 = true;
                                setSelected(3);
                            } else {
                                flag04 = false;
                                setSelectFalse(3);
                            }
                            break;
                        case R.id.tv_ev5:
                            if (!flag05) {
                                flag05 = true;
                                setSelected(4);
                            } else {
                                flag05 = false;
                                setSelectFalse(4);
                            }
                            break;
                        case R.id.tv_ev6:
                            if (!flag06) {
                                flag06 = true;
                                setSelected(5);
                            } else {
                                flag06 = false;
                                setSelectFalse(5);
                            }
                            break;
                        case R.id.tv_ev7:
                            if (!flag07) {
                                flag07 = true;
                                setSelected(6);
                            } else {
                                flag07 = false;
                                setSelectFalse(6);
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

    private void setSelected(int id) {
        for (int i = 0; i < TV_list.size(); i++) {
            TV_list.get(id).setSelected(true);
        }
    }


    public void setSelectFalse() {
        for (int i = 0; i < TV_list.size(); i++) {
            TV_list.get(i).setSelected(false);
        }
    }

    public void setSelectFalse(int id) {
        for (int i = 0; i < TV_list.size(); i++) {
            TV_list.get(id).setSelected(false);
        }
    }


    public List<String> getSelectList() {
        List<String> list = new ArrayList<String>();
        for (TextView tv : TV_list) {
            if (tv.isSelected()) {
                list.add(tv.getText().toString());
            }
        }
        return list;
    }

    public String getSelectItem() {
        for (TextView tv : TV_list) {
            if (tv.isSelected()) {
                return tv.getText().toString();
            }
        }
        return "";
    }

    /**
     * 检查是否已选中
     */
    public void check(String[] arrs, String[] arr) {
        int j = arr.length;
        if (arr == null || j == 0)
            return;
        for (int i = 0; i < arrs.length; i++) {
            for (int q = 0; q < arr.length; q++) {
                if (arr[q].equals(arrs[i])) {
                    TV_list.get(i).setSelected(true);
                    break;
                }
            }
        }
    }
}
