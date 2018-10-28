package com.haiwai.housekeeper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope007 on 2016/10/14.
 */
public class CityEntity implements Serializable {
    private String status;
    private List<DataBean> DataBeans;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getDataBeans() {
        return DataBeans;
    }

    public void setDataBeans(List<DataBean> dataBeans) {
        DataBeans = dataBeans;
    }

    public static class DataBean implements Serializable {
        private String id;
        private String name;
        private String yname;
        private String sort;
        private String pid;
        private String level;
        private List<ClassBean> classx;

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

        public List<ClassBean> getClassx() {
            return classx;
        }

        public void setClassx(List<ClassBean> classx) {
            this.classx = classx;
        }


        public static class ClassBean implements Serializable {
            private String id;
            private String name;
            private String yname;
            private String sort;
            private String pid;
            private String level;
            private List<ClasssBean> classxs;

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

            public List<ClasssBean> getClassxs() {
                return classxs;
            }

            public void setClassxs(List<ClasssBean> classxs) {
                this.classxs = classxs;
            }

            public static class ClasssBean implements Serializable {
                private String id;
                private String name;
                private String yname;
                private String sort;
                private String pid;
                private String level;
                private List<ClasssBean> classxs;

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

                public List<ClasssBean> getClassxs() {
                    return classxs;
                }

                public void setClassxs(List<ClasssBean> classxs) {
                    this.classxs = classxs;
                }
            }
        }
    }


}
