package com.haiwai.housekeeper.utils;


import android.support.v4.widget.SwipeRefreshLayout;

import com.haiwai.housekeeper.R;

/**
 * ����ˢ�µ��������
 * @author Vayne
 *
 */
public class SwipeRefreshUtils {

	public static void setSwipeRefresh(SwipeRefreshLayout srl,SwipeRefreshLayout.OnRefreshListener onRefreshListener){
		/**
		 * setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener):�������ƻ�����������
		 * setProgressBackgroundColor(int colorRes):���ý���Ȧ�ı���ɫ��
		 * setColorSchemeResources(int�� colorResIds):���ý��ȶ�������ɫ��
		 * setRefreshing(Boolean refreshing):���������ˢϴ״̬��
		 * setSize(int size):���ý���Ȧ�Ĵ�С��ֻ������ֵ��DEFAULT��LARGE
		 */
		srl.setOnRefreshListener(onRefreshListener);
		srl.setColorSchemeResources(R.color.theme);
	}
}
