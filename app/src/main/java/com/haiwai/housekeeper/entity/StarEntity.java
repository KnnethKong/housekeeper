package com.haiwai.housekeeper.entity;

import java.util.List;

/**
 * Created by ihope10 on 2016/11/3.
 */

public class StarEntity {
    /**
     * status : 200
     * data : {"str1":[{"id":"1","name":"60015ea6607652a3","type":"1","sort":"0","ctime":"0"},{"id":"2","name":"6280827a4e0d5408683c","type":"1","sort":"0","ctime":"0"},{"id":"3","name":"5de54f5c672a5b8c6210","type":"1","sort":"0","ctime":"0"},{"id":"4","name":"5de54f5c5b8c62108d2891cf5dee","type":"1","sort":"0","ctime":"0"},{"id":"5","name":"4ef7683c7ea07eb762166b3a8bc8","type":"1","sort":"0","ctime":"0"},{"id":"6","name":"4e2591cd4e0d5b8865f6621665e06545723d7ea6","type":"1","sort":"0","ctime":"0"},{"id":"7","name":"4e3e6b624e0d5f53","type":"1","sort":"0","ctime":"0"},{"id":"8","name":"886377404e0d6574","type":"1","sort":"0","ctime":"0"},{"id":"9","name":"672c4eba4e0e6ce8518c4fe1606f4e0d7b26","type":"1","sort":"0","ctime":"0"}],"str3":[{"id":"21","name":"4e0d5b8865f6","type":"3","sort":"0","ctime":"0"},{"id":"22","name":"4e3e6b624e0d5f53","type":"3","sort":"0","ctime":"0"},{"id":"23","name":"886377404e0d6574","type":"3","sort":"0","ctime":"0"},{"id":"20","name":"65368d394e0d54087406","type":"3","sort":"0","ctime":"0"},{"id":"19","name":"5de54f5c5b8c62108d2891cf5dee","type":"3","sort":"0","ctime":"0"},{"id":"18","name":"6280827a751f758f","type":"3","sort":"0","ctime":"0"},{"id":"17","name":"60015ea651b76de1","type":"3","sort":"0","ctime":"0"}],"str4":[{"id":"24","name":"70ed60c553cb5584","type":"4","sort":"0","ctime":"0"},{"id":"25","name":"6280827a5a34719f","type":"4","sort":"0","ctime":"0"},{"id":"26","name":"65368d39516c9053","type":"4","sort":"0","ctime":"0"},{"id":"27","name":"51c665f65b884fe1","type":"4","sort":"0","ctime":"0"},{"id":"28","name":"4e94661f7ea7670d52a1","type":"4","sort":"0","ctime":"0"},{"id":"29","name":"989c503c72068868","type":"4","sort":"0","ctime":"0"},{"id":"30","name":"76f88c08751a6b22","type":"4","sort":"0","ctime":"0"}],"str5":[{"id":"31","name":"70ed60c553cb5584","type":"5","sort":"0","ctime":"0"},{"id":"32","name":"6280827a5a34719f","type":"5","sort":"0","ctime":"0"},{"id":"33","name":"65368d39516c9053","type":"5","sort":"0","ctime":"0"},{"id":"34","name":"51c665f65b884fe1","type":"5","sort":"0","ctime":"0"},{"id":"35","name":"4e94661f7ea7670d52a1","type":"5","sort":"0","ctime":"0"},{"id":"36","name":"989c503c72068868","type":"5","sort":"0","ctime":"0"},{"id":"37","name":"76f88c08751a6b22","type":"5","sort":"0","ctime":"0"}],"str2":[{"id":"10","name":"60015ea651b76de1","type":"2","sort":"0","ctime":"0"},{"id":"11","name":"6280827a751f758f","type":"2","sort":"0","ctime":"0"},{"id":"12","name":"5de54f5c5b8c62108d2891cf5dee","type":"2","sort":"0","ctime":"0"},{"id":"13","name":"65368d394e0d54087406","type":"2","sort":"0","ctime":"0"},{"id":"14","name":"4e0d5b8865f6","type":"2","sort":"0","ctime":"0"},{"id":"15","name":"4e3e6b624e0d5f53","type":"2","sort":"0","ctime":"0"},{"id":"16","name":"886377404e0d6574","type":"2","sort":"0","ctime":"0"}]}
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
         * id : 1
         * name : 60015ea6607652a3
         * type : 1
         * sort : 0
         * ctime : 0
         */

        private List<Str1Bean> str1;
        /**
         * id : 21
         * name : 4e0d5b8865f6
         * type : 3
         * sort : 0
         * ctime : 0
         */

        private List<Str3Bean> str3;
        /**
         * id : 24
         * name : 70ed60c553cb5584
         * type : 4
         * sort : 0
         * ctime : 0
         */

        private List<Str4Bean> str4;
        /**
         * id : 31
         * name : 70ed60c553cb5584
         * type : 5
         * sort : 0
         * ctime : 0
         */

        private List<Str5Bean> str5;
        /**
         * id : 10
         * name : 60015ea651b76de1
         * type : 2
         * sort : 0
         * ctime : 0
         */

        private List<Str2Bean> str2;

        public List<Str1Bean> getStr1() {
            return str1;
        }

        public void setStr1(List<Str1Bean> str1) {
            this.str1 = str1;
        }

        public List<Str3Bean> getStr3() {
            return str3;
        }

        public void setStr3(List<Str3Bean> str3) {
            this.str3 = str3;
        }

        public List<Str4Bean> getStr4() {
            return str4;
        }

        public void setStr4(List<Str4Bean> str4) {
            this.str4 = str4;
        }

        public List<Str5Bean> getStr5() {
            return str5;
        }

        public void setStr5(List<Str5Bean> str5) {
            this.str5 = str5;
        }

        public List<Str2Bean> getStr2() {
            return str2;
        }

        public void setStr2(List<Str2Bean> str2) {
            this.str2 = str2;
        }

        public static class Str1Bean {
            private String id;
            private String name;
            private String type;
            private String sort;
            private String ctime;
            public String yname;

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }

        public static class Str3Bean {
            private String id;
            private String name;
            private String type;
            private String sort;
            private String ctime;
            public String yname;

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }

        public static class Str4Bean {
            private String id;
            private String name;
            private String type;
            private String sort;
            private String ctime;
            public String yname;

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }

        public static class Str5Bean {
            private String id;
            private String name;
            private String type;
            private String sort;
            private String ctime;
            public String yname;

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }

        public static class Str2Bean {
            private String id;
            private String name;
            private String type;
            private String sort;
            private String ctime;
            public String yname;

            public String getYname() {

                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}
