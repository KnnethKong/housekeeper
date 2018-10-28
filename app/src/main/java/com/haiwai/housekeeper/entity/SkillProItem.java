package com.haiwai.housekeeper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ihope10 on 2016/10/26.
 */

public class SkillProItem implements Serializable {

    private String protype;
    private String pronameen;
    private String pronamecn;
    private List<ProEn> proEns;
    private List<ProCn> proCns;

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public String getPronameen() {
        return pronameen;
    }

    public void setPronameen(String pronameen) {
        this.pronameen = pronameen;
    }

    public String getPronamecn() {
        return pronamecn;
    }

    public void setPronamecn(String pronamecn) {
        this.pronamecn = pronamecn;
    }

    public List<ProEn> getProEns() {
        return proEns;
    }

    public void setProEns(List<ProEn> proEns) {
        this.proEns = proEns;
    }

    public List<ProCn> getProCns() {
        return proCns;
    }

    public void setProCns(List<ProCn> proCns) {
        this.proCns = proCns;
    }


    public static class ProEn {
        private String protitleen;
        private List<String> proansen;

        public String getProtitleen() {
            return protitleen;
        }

        public void setProtitleen(String protitleen) {
            this.protitleen = protitleen;
        }

        public List<String> getProansen() {
            return proansen;
        }

        public void setProansen(List<String> proansen) {
            this.proansen = proansen;
        }
    }

    public static class ProCn {
        private String protitlecn;
        private List<String> proanscn;

        public String getProtitlecn() {
            return protitlecn;
        }

        public void setProtitlecn(String protitlecn) {
            this.protitlecn = protitlecn;
        }

        public List<String> getProanscn() {
            return proanscn;
        }

        public void setProanscn(List<String> proanscn) {
            this.proanscn = proanscn;
        }
    }



}
