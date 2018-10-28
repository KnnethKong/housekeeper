package com.haiwai.housekeeper.entity;

/**
 * Created by Ivan on 15/9/25.
 */
public class Tab {

    private  int title;
    private  int icon;
    private Class fragment;
    private int position;
    private String tag;

    public Tab(Class fragment,int title, int icon,int position,String tag) {
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
        this.position = position;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
