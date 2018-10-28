package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class HomeTwoEntity {
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
         * home_page : {"img":"http://hwgj.zai0312.com/Uploads/2018-03-29/5abda5c10ed3d.jpg","link":"http://www.landingbook.ca/","share_link":"http://mp.weixin.qq.com/s/dQppTBSXlgJ8j-FyleoKSg"}
         * city : [{"id":"22","name":"加拿大","yname":"Canada","sort":"0","pid":"0","level":"1","class":[{"id":"23","name":"不列颠哥伦比亚省","yname":"British Columbia","sort":"0","pid":"22","level":"2","class":[{"id":"1","name":"安莫尔","yname":"Anmore","sort":"0","pid":"23","level":"3","class":[]},{"id":"14","name":"匹特草原","yname":"Pitt Meadows","sort":"0","pid":"23","level":"3","class":[]},{"id":"15","name":"高贵林港","yname":"Port Coquitlam","sort":"0","pid":"23","level":"3","class":[]},{"id":"16","name":"满地堡","yname":"Port Moody","sort":"0","pid":"23","level":"3","class":[]},{"id":"17","name":"列治文","yname":"Richmond","sort":"0","pid":"23","level":"3","class":[]},{"id":"18","name":"素里","yname":"Surrey","sort":"0","pid":"23","level":"3","class":[]},{"id":"19","name":"温哥华","yname":"Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"20","name":"西温","yname":"West Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"21","name":"白石","yname":"White Rock","sort":"0","pid":"23","level":"3","class":[]},{"id":"13","name":"北温区","yname":"District of North Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"12","name":"北温市","yname":"City of North Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"11","name":"新威斯敏斯特","yname":"New Westminster","sort":"0","pid":"23","level":"3","class":[]},{"id":"2","name":"贝卡拉","yname":"Belcarra","sort":"0","pid":"23","level":"3","class":[]},{"id":"3","name":"宝云岛","yname":"Bowen Island","sort":"0","pid":"23","level":"3","class":[]},{"id":"4","name":"本拿比","yname":"Burnaby","sort":"0","pid":"23","level":"3","class":[]},{"id":"5","name":"高贵林","yname":"Coquitlam","sort":"0","pid":"23","level":"3","class":[]},{"id":"6","name":"三角洲","yname":"Delta","sort":"0","pid":"23","level":"3","class":[]},{"id":"7","name":"兰里市","yname":"Langley City","sort":"0","pid":"23","level":"3","class":[]},{"id":"8","name":"兰里区","yname":"District of Langley","sort":"0","pid":"23","level":"3","class":[]},{"id":"9","name":"狮子湾","yname":"Lions Bay","sort":"0","pid":"23","level":"3","class":[]},{"id":"10","name":"枫树岭","yname":"Maple Ridge","sort":"0","pid":"23","level":"3","class":[]}]}]}]
         * banner : [{"id":"139","image":"http://biliao.zai0312.com/public/images/found_banner@2x.png","link":"http://www.jinse.com/","sort":"1","ctime":"1522307318"},{"id":"143","image":"http://hwgj.zai0312.com/Uploads/2018-03-29/5abda662a87ea.jpg","link":"ces1","sort":"24","ctime":"1522378338"},{"id":"144","image":"http://hwgj.zai0312.com/Uploads/2018-03-29/5abda8a198cf8.jpg","link":"","sort":"0","ctime":"1522378913"}]
         */

        private HomePageBean home_page;
        private List<CityBean> city;
        private List<BannerBean> banner;

        public HomePageBean getHome_page() {
            return home_page;
        }

        public void setHome_page(HomePageBean home_page) {
            this.home_page = home_page;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class HomePageBean {
            /**
             * img : http://hwgj.zai0312.com/Uploads/2018-03-29/5abda5c10ed3d.jpg
             * link : http://www.landingbook.ca/
             * share_link : http://mp.weixin.qq.com/s/dQppTBSXlgJ8j-FyleoKSg
             */

            private String img;
            private String link;
            private String share_link;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getShare_link() {
                return share_link;
            }

            public void setShare_link(String share_link) {
                this.share_link = share_link;
            }
        }

        public static class CityBean {
            /**
             * id : 22
             * name : 加拿大
             * yname : Canada
             * sort : 0
             * pid : 0
             * level : 1
             * class : [{"id":"23","name":"不列颠哥伦比亚省","yname":"British Columbia","sort":"0","pid":"22","level":"2","class":[{"id":"1","name":"安莫尔","yname":"Anmore","sort":"0","pid":"23","level":"3","class":[]},{"id":"14","name":"匹特草原","yname":"Pitt Meadows","sort":"0","pid":"23","level":"3","class":[]},{"id":"15","name":"高贵林港","yname":"Port Coquitlam","sort":"0","pid":"23","level":"3","class":[]},{"id":"16","name":"满地堡","yname":"Port Moody","sort":"0","pid":"23","level":"3","class":[]},{"id":"17","name":"列治文","yname":"Richmond","sort":"0","pid":"23","level":"3","class":[]},{"id":"18","name":"素里","yname":"Surrey","sort":"0","pid":"23","level":"3","class":[]},{"id":"19","name":"温哥华","yname":"Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"20","name":"西温","yname":"West Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"21","name":"白石","yname":"White Rock","sort":"0","pid":"23","level":"3","class":[]},{"id":"13","name":"北温区","yname":"District of North Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"12","name":"北温市","yname":"City of North Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"11","name":"新威斯敏斯特","yname":"New Westminster","sort":"0","pid":"23","level":"3","class":[]},{"id":"2","name":"贝卡拉","yname":"Belcarra","sort":"0","pid":"23","level":"3","class":[]},{"id":"3","name":"宝云岛","yname":"Bowen Island","sort":"0","pid":"23","level":"3","class":[]},{"id":"4","name":"本拿比","yname":"Burnaby","sort":"0","pid":"23","level":"3","class":[]},{"id":"5","name":"高贵林","yname":"Coquitlam","sort":"0","pid":"23","level":"3","class":[]},{"id":"6","name":"三角洲","yname":"Delta","sort":"0","pid":"23","level":"3","class":[]},{"id":"7","name":"兰里市","yname":"Langley City","sort":"0","pid":"23","level":"3","class":[]},{"id":"8","name":"兰里区","yname":"District of Langley","sort":"0","pid":"23","level":"3","class":[]},{"id":"9","name":"狮子湾","yname":"Lions Bay","sort":"0","pid":"23","level":"3","class":[]},{"id":"10","name":"枫树岭","yname":"Maple Ridge","sort":"0","pid":"23","level":"3","class":[]}]}]
             */

            private String id;
            private String name;
            private String yname;
            private String sort;
            private String pid;
            private String level;
            @SerializedName("class")
            private List<ClassBeanX> classX;

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

            public String getYname() {
                return yname;
            }

            public void setYname(String yname) {
                this.yname = yname;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public List<ClassBeanX> getClassX() {
                return classX;
            }

            public void setClassX(List<ClassBeanX> classX) {
                this.classX = classX;
            }

            public static class ClassBeanX {
                /**
                 * id : 23
                 * name : 不列颠哥伦比亚省
                 * yname : British Columbia
                 * sort : 0
                 * pid : 22
                 * level : 2
                 * class : [{"id":"1","name":"安莫尔","yname":"Anmore","sort":"0","pid":"23","level":"3","class":[]},{"id":"14","name":"匹特草原","yname":"Pitt Meadows","sort":"0","pid":"23","level":"3","class":[]},{"id":"15","name":"高贵林港","yname":"Port Coquitlam","sort":"0","pid":"23","level":"3","class":[]},{"id":"16","name":"满地堡","yname":"Port Moody","sort":"0","pid":"23","level":"3","class":[]},{"id":"17","name":"列治文","yname":"Richmond","sort":"0","pid":"23","level":"3","class":[]},{"id":"18","name":"素里","yname":"Surrey","sort":"0","pid":"23","level":"3","class":[]},{"id":"19","name":"温哥华","yname":"Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"20","name":"西温","yname":"West Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"21","name":"白石","yname":"White Rock","sort":"0","pid":"23","level":"3","class":[]},{"id":"13","name":"北温区","yname":"District of North Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"12","name":"北温市","yname":"City of North Vancouver","sort":"0","pid":"23","level":"3","class":[]},{"id":"11","name":"新威斯敏斯特","yname":"New Westminster","sort":"0","pid":"23","level":"3","class":[]},{"id":"2","name":"贝卡拉","yname":"Belcarra","sort":"0","pid":"23","level":"3","class":[]},{"id":"3","name":"宝云岛","yname":"Bowen Island","sort":"0","pid":"23","level":"3","class":[]},{"id":"4","name":"本拿比","yname":"Burnaby","sort":"0","pid":"23","level":"3","class":[]},{"id":"5","name":"高贵林","yname":"Coquitlam","sort":"0","pid":"23","level":"3","class":[]},{"id":"6","name":"三角洲","yname":"Delta","sort":"0","pid":"23","level":"3","class":[]},{"id":"7","name":"兰里市","yname":"Langley City","sort":"0","pid":"23","level":"3","class":[]},{"id":"8","name":"兰里区","yname":"District of Langley","sort":"0","pid":"23","level":"3","class":[]},{"id":"9","name":"狮子湾","yname":"Lions Bay","sort":"0","pid":"23","level":"3","class":[]},{"id":"10","name":"枫树岭","yname":"Maple Ridge","sort":"0","pid":"23","level":"3","class":[]}]
                 */

                private String id;
                private String name;
                private String yname;
                private String sort;
                private String pid;
                private String level;
                @SerializedName("class")
                private List<ClassBean> classX;

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

                public String getYname() {
                    return yname;
                }

                public void setYname(String yname) {
                    this.yname = yname;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public List<ClassBean> getClassX() {
                    return classX;
                }

                public void setClassX(List<ClassBean> classX) {
                    this.classX = classX;
                }

                public static class ClassBean {
                    /**
                     * id : 1
                     * name : 安莫尔
                     * yname : Anmore
                     * sort : 0
                     * pid : 23
                     * level : 3
                     * class : []
                     */

                    private String id;
                    private String name;
                    private String yname;
                    private String sort;
                    private String pid;
                    private String level;
                    @SerializedName("class")
                    private List<?> classX;

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

                    public String getYname() {
                        return yname;
                    }

                    public void setYname(String yname) {
                        this.yname = yname;
                    }

                    public String getSort() {
                        return sort;
                    }

                    public void setSort(String sort) {
                        this.sort = sort;
                    }

                    public String getPid() {
                        return pid;
                    }

                    public void setPid(String pid) {
                        this.pid = pid;
                    }

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
                    }

                    public List<?> getClassX() {
                        return classX;
                    }

                    public void setClassX(List<?> classX) {
                        this.classX = classX;
                    }
                }
            }
        }

        public static class BannerBean {
            /**
             * id : 139
             * image : http://biliao.zai0312.com/public/images/found_banner@2x.png
             * link : http://www.jinse.com/
             * sort : 1
             * ctime : 1522307318
             */

            private String id;
            private String image;
            private String link;
            private String sort;
            private String ctime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
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
