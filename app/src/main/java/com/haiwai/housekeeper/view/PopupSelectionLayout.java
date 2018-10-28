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

public class PopupSelectionLayout extends LinearLayout {
	private Context context;
	private List<TextView> TV_list = new ArrayList<>();
	private List<LinearLayout> LL_list = new ArrayList<>();
	public static int ONE_LINE = 1;
	public static int TWO_LINE = 2;
	public static int THREE_LINE = 3;
	public static int FOUR_LINE = 4;

	public PopupSelectionLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.selection_cate_popup, this);
		setViews();
	}

	private void setViews() {
		LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_01));
		LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_02));
		LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_03));
		LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_04));
		LL_list.add((LinearLayout) findViewById(R.id.liangdian_horizontal_05));
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
		for (TextView tv : TV_list) {
			tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					switch (v.getId()) {
					case R.id.liangdian_item_01:
						setSelected(0);
						break;
					case R.id.liangdian_item_02:
						setSelected(1);
						break;
					case R.id.liangdian_item_03:
						setSelected(2);
						break;
					case R.id.liangdian_item_04:
						setSelected(3);
						break;
					case R.id.liangdian_item_05:
						setSelected(4);
						break;
					case R.id.liangdian_item_06:
						setSelected(5);
						break;
					case R.id.liangdian_item_07:
						setSelected(6);
						break;
					case R.id.liangdian_item_08:
						setSelected(7);
						break;
					case R.id.liangdian_item_09:
						setSelected(8);
						break;
					case R.id.liangdian_item_10:
						setSelected(9);
						break;
					case R.id.liangdian_item_11:
						setSelected(10);
						break;
					case R.id.liangdian_item_12:
						setSelected(11);
						break;
					case R.id.liangdian_item_13:
						setSelected(12);
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
//		if (TV_list.get(id).isSelected()) {
//			TV_list.get(id).setSelected(false);
//			return;
//		}

		setAllSelectFalse();
		if (!TV_list.get(id).isSelected()) {
			TV_list.get(id).setSelected(true);
			return;
		}
//		int j=0;
//		for (int i = 0;i<TV_list.size();i++) {
//			if(TV_list.get(i).isSelected()){
//				if (i==id) {
//				} else {
//					j++;
//					if(j==1){
////						ToastUtil.shortToast(context, "");
//
//						return;
//					}
//				}
//			}
//		}
//		if (TV_list.get(id).isSelected()) {
//			TV_list.get(id).setSelected(false);
//		} else {
//			TV_list.get(id).setSelected(true);
//		}
	}

	public void setAllSelectFalse(){
		for (int i = 0;i<TV_list.size();i++) {
			TV_list.get(i).setSelected(false);
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
		int j=arr.length;
		if(arr==null || j==0)
			return;
		for (int i = 0; i < arrs.length; i++) {
			for(int q=0;q<arr.length;q++){
				if(arr[q].equals(arrs[i])){
					TV_list.get(i).setSelected(true);
					break;
				}
			}
		}
	}
}
