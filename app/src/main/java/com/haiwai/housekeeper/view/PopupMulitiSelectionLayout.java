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

public class PopupMulitiSelectionLayout extends LinearLayout {
    private Context context;
    private List<TextView> TV_list = new ArrayList<>();
    private List<LinearLayout> LL_list = new ArrayList<>();
    public static int ONE_LINE = 1;
    public static int TWO_LINE = 2;
    public static int THREE_LINE = 3;
    public static int FOUR_LINE = 4;
    public static int FIVE_LINE = 5;
    public static int SIX_LINE = 6;
    public static int SEAVENLINE = 7;
    public static int ETGHT_LINE = 8;
    public static int NINE_LINE = 9;
    public static int TEN_LINE = 10;
    private boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false, flag8 = false, flag9 = false, flag10 = false,
            flag11 = false, flag12 = false, flag13 = false, flag14 = false, flag15 = false, flag16 = false, flag17 = false, flag18 = false, flag19 = false, flag20 = false,
            flag21 = false, flag22 = false, flag23 = false, flag24 = false, flag25 = false, flag26 = false, flag27 = false, flag28 = false, flag29 = false, flag30 = false;

    public PopupMulitiSelectionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.selection_cate_multi_popup, this);
        setViews();
    }

    private void setViews() {
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_01));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_02));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_03));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_04));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_05));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_06));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_07));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_08));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_09));
        LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_10));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_01));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_02));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_03));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_04));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_05));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_06));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_07));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_08));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_09));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_10));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_11));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_12));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_13));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_14));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_15));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_16));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_17));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_18));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_19));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_20));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_21));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_22));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_23));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_24));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_25));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_26));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_27));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_28));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_29));
        TV_list.add((TextView) findViewById(R.id.liangdian_item_30));
        for (TextView tv : TV_list) {
            tv.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.liangdian_item_01:
                            if (!flag1) {
                                flag1 = true;
                                setSelected(0);
                            } else {
                                flag1 = false;
                                setAllSelectFalse(0);
                            }
                            break;
                        case R.id.liangdian_item_02:
                            if (!flag2) {
                                flag2 = true;
                                setSelected(1);
                            } else {
                                flag2 = false;
                                setAllSelectFalse(1);
                            }
                            break;
                        case R.id.liangdian_item_03:
                            if (!flag3) {
                                flag3 = true;
                                setSelected(2);
                            } else {
                                flag3 = false;
                                setAllSelectFalse(2);
                            }
                            break;
                        case R.id.liangdian_item_04:
                            if (!flag4) {
                                flag4 = true;
                                setSelected(3);
                            } else {
                                flag4 = false;
                                setAllSelectFalse(3);
                            }
                            break;
                        case R.id.liangdian_item_05:
                            if (!flag5) {
                                flag5 = true;
                                setSelected(4);
                            } else {
                                flag5 = false;
                                setAllSelectFalse(4);
                            }
                            break;
                        case R.id.liangdian_item_06:
                            if (!flag6) {
                                flag6 = true;
                                setSelected(5);
                            } else {
                                flag6 = false;
                                setAllSelectFalse(5);
                            }
                            break;
                        case R.id.liangdian_item_07:
                            if (!flag7) {
                                flag7 = true;
                                setSelected(6);
                            } else {
                                flag7 = false;
                                setAllSelectFalse(6);
                            }
                            break;
                        case R.id.liangdian_item_08:
                            if (!flag8) {
                                flag8 = true;
                                setSelected(7);
                            } else {
                                flag8 = false;
                                setAllSelectFalse(7);
                            }
                            break;
                        case R.id.liangdian_item_09:
                            if (!flag9) {
                                flag9 = true;
                                setSelected(8);
                            } else {
                                flag9 = false;
                                setAllSelectFalse(8);
                            }
                            break;
                        case R.id.liangdian_item_10:
                            if (!flag10) {
                                flag10 = true;
                                setSelected(9);
                            } else {
                                flag10 = false;
                                setAllSelectFalse(9);
                            }
                            break;
                        case R.id.liangdian_item_11:
                            if (!flag11) {
                                flag11 = true;
                                setSelected(10);
                            } else {
                                flag11 = false;
                                setAllSelectFalse(10);
                            }
                            break;
                        case R.id.liangdian_item_12:
                            if (!flag12) {
                                flag12 = true;
                                setSelected(11);
                            } else {
                                flag12 = false;
                                setAllSelectFalse(11);
                            }
                            break;
                        case R.id.liangdian_item_13:
                            if (!flag13) {
                                flag13 = true;
                                setSelected(12);
                            } else {
                                flag13 = false;
                                setAllSelectFalse(12);
                            }
                            break;
                        case R.id.liangdian_item_14:
                            if (!flag14) {
                                flag14 = true;
                                setSelected(13);
                            } else {
                                flag14 = false;
                                setAllSelectFalse(13);
                            }
                            break;
                        case R.id.liangdian_item_15:
                            if (!flag15) {
                                flag15 = true;
                                setSelected(14);
                            } else {
                                flag15 = false;
                                setAllSelectFalse(14);
                            }
                            break;
                        case R.id.liangdian_item_16:
                            if (!flag16) {
                                flag16 = true;
                                setSelected(15);
                            } else {
                                flag16 = false;
                                setAllSelectFalse(15);
                            }
                            break;
                        case R.id.liangdian_item_17:
                            if (!flag17) {
                                flag17 = true;
                                setSelected(16);
                            } else {
                                flag17 = false;
                                setAllSelectFalse(16);
                            }
                            break;
                        case R.id.liangdian_item_18:
                            if (!flag18) {
                                flag18 = true;
                                setSelected(17);
                            } else {
                                flag18 = false;
                                setAllSelectFalse(17);
                            }
                            break;
                        case R.id.liangdian_item_19:
                            if (!flag19) {
                                flag19 = true;
                                setSelected(18);
                            } else {
                                flag19 = false;
                                setAllSelectFalse(18);
                            }
                            break;
                        case R.id.liangdian_item_20:
                            if (!flag20) {
                                flag20 = true;
                                setSelected(19);
                            } else {
                                flag20 = false;
                                setAllSelectFalse(19);
                            }
                            break;
                        case R.id.liangdian_item_21:
                            if (!flag21) {
                                flag21 = true;
                                setSelected(20);
                            } else {
                                flag21 = false;
                                setAllSelectFalse(20);
                            }
                            break;
                        case R.id.liangdian_item_22:
                            if (!flag22) {
                                flag22 = true;
                                setSelected(21);
                            } else {
                                flag22 = false;
                                setAllSelectFalse(21);
                            }
                            break;
                        case R.id.liangdian_item_23:
                            if (!flag23) {
                                flag23 = true;
                                setSelected(22);
                            } else {
                                flag23 = false;
                                setAllSelectFalse(22);
                            }
                            break;
                        case R.id.liangdian_item_24:
                            if (!flag24) {
                                flag24 = true;
                                setSelected(23);
                            } else {
                                flag24 = false;
                                setAllSelectFalse(23);
                            }
                            break;
                        case R.id.liangdian_item_25:
                            if (!flag25) {
                                flag25 = true;
                                setSelected(24);
                            } else {
                                flag25 = false;
                                setAllSelectFalse(24);
                            }
                            break;
                        case R.id.liangdian_item_26:
                            if (!flag26) {
                                flag26 = true;
                                setSelected(25);
                            } else {
                                flag26 = false;
                                setAllSelectFalse(25);
                            }
                            break;
                        case R.id.liangdian_item_27:
                            if (!flag27) {
                                flag27 = true;
                                setSelected(26);
                            } else {
                                flag27 = false;
                                setAllSelectFalse(26);
                            }
                            break;
                        case R.id.liangdian_item_28:
                            if (!flag28) {
                                flag28 = true;
                                setSelected(27);
                            } else {
                                flag28 = false;
                                setAllSelectFalse(27);
                            }
                            break;
                        case R.id.liangdian_item_29:
                            if (!flag29) {
                                flag29 = true;
                                setSelected(28);
                            } else {
                                flag29 = false;
                                setAllSelectFalse(28);
                            }
                            break;
                        case R.id.liangdian_item_30:
                            if (!flag30) {
                                flag30 = true;
                                setSelected(29);
                            } else {
                                flag30 = false;
                                setAllSelectFalse(29);
                            }
                            break;
                        default:
                            break;

                    }
                }
            });
        }
    }

    // 设施需要显示的 行数 和item 数量
    public void setVisibleNum(int horizontal_Num, int item_Num) {
        for (int i = 0; i < LL_list.size(); i++) {
            if (i < horizontal_Num) {
                LL_list.get(i).setVisibility(View.VISIBLE);
            } else {
                LL_list.get(i).setVisibility(View.GONE);
            }
        }
        for (int i = 0; i < TV_list.size(); i++) {
            if (i < item_Num) {
                TV_list.get(i).setVisibility(View.VISIBLE);
            } else {
                TV_list.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    // 为每个item 设置名称
    public void setItemName(String[] arrayString) {
        int horizontal_Num;
        if (arrayString.length % 3 == 0) {
            horizontal_Num = arrayString.length / 3;
        } else {
            horizontal_Num = arrayString.length / 3 + 1;
        }
        setVisibleNum(horizontal_Num, arrayString.length);
        for (int i = 0; i < arrayString.length; i++) {
            TV_list.get(i).setText(arrayString[i]);
        }
    }

    private void setSelected(int id) {
        TV_list.get(id).setSelected(true);
    }

    public void setAllSelectFalse(int id) {
        TV_list.get(id).setSelected(false);
    }

    public void setAllSelectFalse() {
        for (int i = 0; i < TV_list.size(); i++) {
            TV_list.get(i).setSelected(false);
        }
    }


    public List<String> getSelectList() {
        List<String> list = new ArrayList<String>();
        list.clear();
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
