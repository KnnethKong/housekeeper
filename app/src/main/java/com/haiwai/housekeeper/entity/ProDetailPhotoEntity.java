package com.haiwai.housekeeper.entity;

/**
 * Created by ihope007 on 2016/10/19.
 */
public class ProDetailPhotoEntity {
    private String id;
    private String uid;
    private String img;
    private String desc;
    private String ctime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "ProDetailPhotoEntity{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", img='" + img + '\'' +
                ", desc='" + desc + '\'' +
                ", ctime='" + ctime + '\'' +
                '}';
    }
}
