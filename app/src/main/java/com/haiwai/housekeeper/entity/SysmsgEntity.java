package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope006 on 2016/12/20.
 */

public class SysmsgEntity {

    /**
     * data : [{"content":"你好阿","ctime":"1480921159","id":"1","title":"ceshi"},{"content":"到33333","ctime":"1480928776","id":"2","title":"11"},{"content":"123","ctime":"1480933028","id":"3","title":"123"},{"content":"123123","ctime":"1480933358","id":"4","title":"123123"},{"content":"ceeee","ctime":"1480933406","id":"5","title":"123"},{"content":"ceeee","ctime":"1480933416","id":"6","title":"123"},{"content":"ceeee","ctime":"1480933432","id":"7","title":"123"},{"content":"ceeee","ctime":"1480933506","id":"8","title":"123"},{"content":"ceeee","ctime":"1480933594","id":"9","title":"123"},{"content":"ceeee","ctime":"1480934640","id":"10","title":"123"},{"content":"我打上大所大","ctime":"1482221184","id":"11","title":"爱上大叔"},{"content":"飒飒飒飒飒飒","ctime":"1482223099","id":"12","title":"仨飒飒"},{"content":"飒飒飒飒飒飒","ctime":"1482223141","id":"13","title":"仨飒飒"},{"content":"飒飒飒飒飒飒","ctime":"1482223175","id":"14","title":"仨飒飒"},{"content":"阿萨德好咯破","ctime":"1482223300","id":"15","title":"我去"},{"content":"阿萨飒飒","ctime":"1482223492","id":"16","title":"万千瓦群"},{"content":"22222","ctime":"1482223718","id":"17","title":"123123222"},{"content":"222","ctime":"1482223750","id":"18","title":"11"},{"content":"222","ctime":"1482223789","id":"19","title":"11"},{"content":"1223","ctime":"1482223824","id":"20","title":"11"}]
     * status : 200
     */

    private int status;
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

    public static class DataBean {
        /**
         * content : 你好阿
         * ctime : 1480921159
         * id : 1
         * title : ceshi
         */

        private String content;
        private String ctime;
        private String id;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
