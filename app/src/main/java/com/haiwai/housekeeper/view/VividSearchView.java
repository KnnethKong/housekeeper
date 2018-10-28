/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */
package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.haiwai.housekeeper.R;


/**
 * 修改一下默认的Search view，少一些违和感，并且可以知道search view是否collapse
 *
 * @author abner
 */
public class VividSearchView extends SearchView {

	private boolean searching;

	public static VividSearchView getSearchView(Context context) {
		VividSearchView searchView = new VividSearchView(context);
		searchView.setIconifiedByDefault(false);
		searchView.setQueryHint(context.getString(R.string.vivi_key));
		searchView.setIconified(true);
		int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
		View searchPlate = searchView.findViewById(searchPlateId);
		if (searchPlate != null) {
			// 修改搜索文字的颜色
			int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
			TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
			if (searchText != null) {
				searchText.setTextColor(Color.WHITE);
				searchText.setHintTextColor(Color.WHITE);
			}
		}
		// 修改搜索hint图标，这里有bug，所以不得已搜索的空间又小了
		int searchButtonId = searchView.getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null);
		ImageView searchButton = (ImageView) searchView.findViewById(searchButtonId);
		if (searchButton != null) {
			searchButton.setImageResource(R.mipmap.user_ib_search);
		}
		// 修改清除图标
		int clearId = searchView.getContext().getResources().getIdentifier("android:id/search_close_btn", null, null);
		ImageView closeButton = (ImageView) searchView.findViewById(clearId);
		if (closeButton != null) {
			closeButton.setImageResource(android.R.drawable.ic_delete);
		}
		return searchView;
	}

	public VividSearchView(Context context) {
		super(context);
	}

	@Override
	public void onActionViewExpanded() {
		super.onActionViewExpanded();
		searching = true;
	}

	@Override
	public void onActionViewCollapsed() {
		super.onActionViewCollapsed();
		searching = false;
	}

	public boolean isSearching() {
		return searching;
	}
}
