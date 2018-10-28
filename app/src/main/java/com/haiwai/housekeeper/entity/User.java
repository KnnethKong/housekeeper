/*
 * The MIT License (MIT)
 * Copyright (c) 2014 longkai
 * The software shall be used for good, not evil.
 */
package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 微博用户元数据
 *
 * @author longkai
 */
public final class User implements Serializable{//implements CatnutMetadata<JSONObject, ContentValues> {

	private static final String TAG = "User";
	/**
	 * uid : 1
	 * avatar :
	 * nickname :
	 * mobile : 18600713491
	 * name :
	 * introduction :
	 * is_ren : 0
	 * only_label : 27127
	 * order_num : 0
	 * xing_num : 0
	 * address :
	 * lat :
	 * long :
	 * ctime : 0
	 */

	private String uid;
	private String avatar;
	private String nickname;
	private String area;
	private String mobile;
	private String name;
	private String introduction;
	private String is_hou;
	private String is_ren;
	private String pro_score;
	private String user_score;
	private String only_label;
	private String order_num;
	private String xing_num;
	private String address;
	private String lat;
	@SerializedName("long")
	private String longX;
	private String ctime;
	private String ltime;
	private String fans_num;
	private String follow_num;
	private String balance;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIs_ren() {
		return is_ren;
	}

	public void setIs_ren(String is_ren) {
		this.is_ren = is_ren;
	}

	public String getOnly_label() {
		return only_label;
	}

	public void setOnly_label(String only_label) {
		this.only_label = only_label;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getXing_num() {
		return xing_num;
	}

	public void setXing_num(String xing_num) {
		this.xing_num = xing_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongX() {
		return longX;
	}

	public void setLongX(String longX) {
		this.longX = longX;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getIs_hou() {
		return is_hou;
	}

	public void setIs_hou(String is_hou) {
		this.is_hou = is_hou;
	}

	public String getPro_score() {
		return pro_score;
	}

	public void setPro_score(String pro_score) {
		this.pro_score = pro_score;
	}

	public String getUser_score() {
		return user_score;
	}

	public void setUser_score(String user_score) {
		this.user_score = user_score;
	}

	public String getLtime() {
		return ltime;
	}

	public void setLtime(String ltime) {
		this.ltime = ltime;
	}

	public String getFans_num() {
		return fans_num;
	}

	public void setFans_num(String fans_num) {
		this.fans_num = fans_num;
	}

	public String getFollow_num() {
		return follow_num;
	}

	public void setFollow_num(String follow_num) {
		this.follow_num = follow_num;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid='" + uid + '\'' +
				", avatar='" + avatar + '\'' +
				", nickname='" + nickname + '\'' +
				", mobile='" + mobile + '\'' +
				", name='" + name + '\'' +
				", introduction='" + introduction + '\'' +
				", is_hou='" + is_hou + '\'' +
				", is_ren='" + is_ren + '\'' +
				", pro_score='" + pro_score + '\'' +
				", user_score='" + user_score + '\'' +
				", only_label='" + only_label + '\'' +
				", order_num='" + order_num + '\'' +
				", xing_num='" + xing_num + '\'' +
				", address='" + address + '\'' +
				", lat='" + lat + '\'' +
				", longX='" + longX + '\'' +
				", ctime='" + ctime + '\'' +
				", ltime='" + ltime + '\'' +
				", fans_num='" + fans_num + '\'' +
				", follow_num='" + follow_num + '\'' +
				", balance='" + balance + '\'' +
				'}';
	}
}
