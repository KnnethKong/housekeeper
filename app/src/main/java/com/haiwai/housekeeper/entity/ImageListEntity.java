package com.haiwai.housekeeper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope007 on 2016/10/12.
 */
public class ImageListEntity {

    /**
     * status : 200
     * data : [{"id":"6","uid":"1","img":"http://hwgj.zai0312.com/Uploads/2016-10-12/57fe0ce0983a4.png","desc":"第一张测试图片","ctime":"1476267232"}]
     */

    private int status;
    /**
     * id : 6
     * uid : 1
     * img : http://hwgj.zai0312.com/Uploads/2016-10-12/57fe0ce0983a4.png
     * desc : 第一张测试图片
     * ctime : 1476267232
     */

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
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
    }
}
