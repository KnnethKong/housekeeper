package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope007 on 2016/10/9.
 */
public class HomeEntity {


    /**
     * status : 200
     * data : {"ziy":[{"id":"33","type":"4","img":"","name":"园艺管理","money":"15","sort":"1","hot_sort":"0","is_hot":"0"},{"id":"32","type":"4","img":"","name":"空屋管理","money":"20","sort":"2","hot_sort":"0","is_hot":"0"},{"id":"34","type":"4","img":"","name":"家政服务","money":"40","sort":"3","hot_sort":"0","is_hot":"0"}],"rem":[{"id":"1","type":"1","img":"","name":"房屋保洁","money":"0","sort":"0","hot_sort":"1","is_hot":"1"},{"id":"2","type":"1","img":"","name":"草坪修剪","money":"0","sort":"0","hot_sort":"2","is_hot":"1"},{"id":"9","type":"2","img":"","name":"维修技工","money":"0","sort":"0","hot_sort":"3","is_hot":"1"},{"id":"10","type":"2","img":"","name":"屋顶维修","money":"0","sort":"0","hot_sort":"4","is_hot":"1"},{"id":"13","type":"2","img":"","name":"改建翻新","money":"0","sort":"0","hot_sort":"5","is_hot":"1"},{"id":"11","type":"2","img":"","name":"园艺修建","money":"0","sort":"0","hot_sort":"6","is_hot":"1"},{"id":"14","type":"2","img":"","name":"室内粉刷","money":"0","sort":"0","hot_sort":"7","is_hot":"1"},{"id":"3","type":"1","img":"","name":"地毯清洗","money":"0","sort":"0","hot_sort":"8","is_hot":"1"},{"id":"4","type":"1","img":"","name":"高压清洗","money":"0","sort":"0","hot_sort":"9","is_hot":"1"},{"id":"17","type":"2","img":"","name":"电工","money":"0","sort":"0","hot_sort":"10","is_hot":"1"}],"jiaj":[{"id":"8","type":"1","img":"","name":"除害","money":"0","sort":"0","hot_sort":"0","is_hot":"0"}],"fanw":[{"id":"18","type":"2","img":"","name":"冷热源","money":"0","sort":"0","hot_sort":"0","is_hot":"0"}],"shenh":[{"id":"28","type":"3","img":"","name":"旅游代理","money":"0","sort":"0","hot_sort":"0","is_hot":"0"}]}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 33
         * type : 4
         * img :
         * name : 园艺管理
         * money : 15
         * sort : 1
         * hot_sort : 0
         * is_hot : 0
         */

        private List<ZiyBean> ziy;
        /**
         * id : 1
         * type : 1
         * img :
         * name : 房屋保洁
         * money : 0
         * sort : 0
         * hot_sort : 1
         * is_hot : 1
         */

        private List<RemBean> rem;
        /**
         * id : 8
         * type : 1
         * img :
         * name : 除害
         * money : 0
         * sort : 0
         * hot_sort : 0
         * is_hot : 0
         */

        private List<JiajBean> jiaj;
        /**
         * id : 18
         * type : 2
         * img :
         * name : 冷热源
         * money : 0
         * sort : 0
         * hot_sort : 0
         * is_hot : 0
         */

        private List<FanwBean> fanw;
        /**
         * id : 28
         * type : 3
         * img :
         * name : 旅游代理
         * money : 0
         * sort : 0
         * hot_sort : 0
         * is_hot : 0
         */

        private List<ShenhBean> shenh;

        public List<ZiyBean> getZiy() {
            return ziy;
        }

        public void setZiy(List<ZiyBean> ziy) {
            this.ziy = ziy;
        }

        public List<RemBean> getRem() {
            return rem;
        }

        public void setRem(List<RemBean> rem) {
            this.rem = rem;
        }

        public List<JiajBean> getJiaj() {
            return jiaj;
        }

        public void setJiaj(List<JiajBean> jiaj) {
            this.jiaj = jiaj;
        }

        public List<FanwBean> getFanw() {
            return fanw;
        }

        public void setFanw(List<FanwBean> fanw) {
            this.fanw = fanw;
        }

        public List<ShenhBean> getShenh() {
            return shenh;
        }

        public void setShenh(List<ShenhBean> shenh) {
            this.shenh = shenh;
        }

        public static class ZiyBean {
            private String id;
            private String type;
            private String img;
            private String name;
            private String money;
            private String sort;
            private String hot_sort;
            private String is_hot;
            private String desc;
            private String yname;
            private String o_num;
            private String o_money;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getHot_sort() {
                return hot_sort;
            }

            public void setHot_sort(String hot_sort) {
                this.hot_sort = hot_sort;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {

                this.is_hot = is_hot;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getO_num() {
                return o_num;
            }

            public void setO_num(String o_num) {
                this.o_num = o_num;
            }

            public String getO_money() {
                return o_money;
            }

            public void setO_money(String o_money) {
                this.o_money = o_money;
            }
        }

        public static class RemBean {
            private String id;
            private String type;
            private String img;
            private String name;
            private String money;
            private String sort;
            private String hot_sort;
            private String is_hot;
            private String desc;
            private String yname;
            private String o_num;
            private String o_money;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getHot_sort() {
                return hot_sort;
            }

            public void setHot_sort(String hot_sort) {
                this.hot_sort = hot_sort;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {

                this.is_hot = is_hot;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getO_num() {
                return o_num;
            }

            public void setO_num(String o_num) {
                this.o_num = o_num;
            }

            public String getO_money() {
                return o_money;
            }

            public void setO_money(String o_money) {
                this.o_money = o_money;
            }
        }

        public static class JiajBean {
            private String id;
            private String type;
            private String img;
            private String name;
            private String money;
            private String sort;
            private String hot_sort;
            private String is_hot;
            private String desc;
            private String yname;
            private String o_num;
            private String o_money;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getHot_sort() {
                return hot_sort;
            }

            public void setHot_sort(String hot_sort) {
                this.hot_sort = hot_sort;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {

                this.is_hot = is_hot;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getO_num() {
                return o_num;
            }

            public void setO_num(String o_num) {
                this.o_num = o_num;
            }

            public String getO_money() {
                return o_money;
            }

            public void setO_money(String o_money) {
                this.o_money = o_money;
            }
        }

        public static class FanwBean {
            private String id;
            private String type;
            private String img;
            private String name;
            private String money;
            private String sort;
            private String hot_sort;
            private String is_hot;
            private String desc;
            private String yname;
            private String o_num;
            private String o_money;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getHot_sort() {
                return hot_sort;
            }

            public void setHot_sort(String hot_sort) {
                this.hot_sort = hot_sort;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getO_num() {
                return o_num;
            }

            public void setO_num(String o_num) {
                this.o_num = o_num;
            }

            public String getO_money() {
                return o_money;
            }

            public void setO_money(String o_money) {
                this.o_money = o_money;
            }
        }

        public static class ShenhBean {
            private String id;
            private String type;
            private String img;
            private String name;
            private String money;
            private String sort;
            private String hot_sort;
            private String is_hot;
            private String desc;
            private String yname;
            private String o_num;
            private String o_money;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getHot_sort() {
                return hot_sort;
            }

            public void setHot_sort(String hot_sort) {
                this.hot_sort = hot_sort;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getO_num() {
                return o_num;
            }

            public void setO_num(String o_num) {
                this.o_num = o_num;
            }

            public String getO_money() {
                return o_money;
            }

            public void setO_money(String o_money) {
                this.o_money = o_money;
            }
        }
    }
}
