package com.haiwai.housekeeper.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ihope006 on 2016/12/17.
 */

public class OrderConfigEntity {
    /**
     * data : {"balance":"0.00","date":[{"address_info":"Guuii","city":"1","ctime":"1481874798","h_id":"15","housing_type":"9","id":"11","j_difficulty":"1.00","j_num":"5","j_proble":[{"double":"0.00","id":"14","pid":"0","problem":[{"double":"30.00","id":"15","pid":"14","remark":"","type":"2","type1":"2","type2":"1","value":"基础清洁服务\u2014\u2014包括房屋清洁、月度床品更换及洗涤、冰箱整理、绿植照顾、衣服洗涤晾晒折叠","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"选择您需要的服务标准","yremark":"","yvalue":""},{"double":"0.00","id":"17","pid":"0","problem":[{"double":"4.00","id":"20","pid":"17","remark":"","type":"2","type1":"2","type2":"1","value":"每月服务4次","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"服务的频次","yremark":"","yvalue":""},{"double":"5.00","id":"91","pid":"0","problem":[{"double":"1.00","id":"92","pid":"91","remark":"","type":"2","type1":"2","type2":"1","value":"大开间","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"您家中有几间卧室?","yremark":"","yvalue":""},{"double":"5.00","id":"98","pid":"0","problem":[{"double":"1.00","id":"99","pid":"98","remark":"","type":"2","type1":"2","type2":"1","value":"1间","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"您家中有几间卫生间？","yremark":"","yvalue":""}],"j_static":"2","j_wen1":"15","j_wen2":"20","j_wen3":"92","j_wen4":"99","k_num":"5","k_proble":[{"double":"0.00","id":"75","pid":"0","problem":[{"double":"0.00","id":"55","pid":"54","remark":"","type":"4","type1":"2","type2":"1","value":"我需要招租服务","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"您需要哪种外围巡视方式？","yremark":"","yvalue":""},{"double":"0.00","id":"79","pid":"0","problem":[{"double":"0.00","id":"58","pid":"57","remark":"","type":"4","type1":"2","type2":"1","value":"整栋房子","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"您需要哪种室内察视方式？","yremark":"","yvalue":""},{"double":"0.00","id":"83","pid":"0","problem":[{"double":"0.00","id":"84","pid":"83","remark":"","type":"5","type1":"2","type2":"1","value":"取信留存","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"我们提供信箱查阅服务，请问您需要的服务是哪一种？","yremark":"","yvalue":""},{"double":"0.00","id":"86","pid":"0","problem":[{"double":"0.00","id":"87","pid":"86","remark":"","type":"5","type1":"2","type2":"1","value":"代处理水电物业账单\n（将收到的账单电子化，协助用户设置自动付款，有非常情况及时通知用户并协助处理）","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"88","pid":"86","remark":"","type":"5","type1":"2","type2":"1","value":"通知并协助用户处理缴费通知单\n（如地税、市政费、路桥费、保险续费等）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"3","value":"我们还提供信件管理服务，请问您都需要哪种服务？","yremark":"","yvalue":""}],"k_static":"2","k_wen1":"55","k_wen2":"58","k_wen3":"84","k_wen4":"87,88","money":"245.00","static":"1","uid":"28","y_leaf":"1.00","y_num":"5","y_proble":[{"double":"0.00","id":"27","pid":"0","problem":[{"double":"0.00","id":"28","pid":"27","remark":"","type":"3","type1":"2","type2":"1","value":"优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"对于草坪修剪我们有两种方案，您的选择是?","yremark":"","yvalue":""},{"double":"0.00","id":"31","pid":"0","problem":[{"double":"0.80","id":"32","pid":"31","remark":"","type":"3","type1":"2","type2":"1","value":"小(小于1000平方英尺)","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"您的草坪有多大?","yremark":"","yvalue":"How big is the lawn?"},{"double":"0.00","id":"37","pid":"0","problem":[{"double":"0.00","id":"38","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"草坪修边","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"39","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"树木灌木修剪","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"40","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"除杂草","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"3","value":"我们还有其他园艺服务供您选择","yremark":"","yvalue":""},{"double":"0.00","id":"43","pid":"0","problem":[{"double":"0.00","id":"44","pid":"43","remark":"","type":"3","type1":"2","type2":"1","value":"我需要落叶清扫服务（秋季9月-10月，隔周清扫）","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"45","pid":"43","remark":"","type":"3","type1":"2","type2":"1","value":"我需要清雪服务（冬季11月-3月，确保行人步道不积雪）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"3","value":"不同季节我们还提供落叶清扫和清雪服务","yremark":"","yvalue":""},{"double":"0.00","id":"46","pid":"0","problem":[{"double":"0.90","id":"47","pid":"46","remark":"","type":"3","type1":"2","type2":"1","value":"小（面积不到2辆小汽车般大）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"需要清雪的人行步道有多大？","yremark":"","yvalue":""},{"double":"0.00","id":"51","pid":"0","problem":[{"double":"0.00","id":"52","pid":"51","remark":"","type":"3","type1":"2","type2":"1","value":"不需要，不介意我不在场的时候工作","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"庭院维护过程中你是否需要在场?","yremark":"","yvalue":""}],"y_snow":"1.00","y_static":"2","y_wen1":"28","y_wen2":"32","y_wen3":"38,39,40","y_wen4":"44,45","y_wen5":"47","y_wen6":"52","z_proble":[{"double":"0.00","id":"54","pid":"0","problem":[{"double":"0.00","id":"55","pid":"54","remark":"","type":"4","type1":"2","type2":"1","value":"我需要招租服务","yremark":"","yvalue":"","zhi":""}],"remark":"招租是一次性服务，在确认需求后协助您通过最权威的渠道找到合适的租户（招租取费为1个月房租，在租赁协议签订后收取）","type":"4","type1":"1","type2":"1","value":"招租服务","yremark":"","yvalue":""},{"double":"0.00","id":"57","pid":"0","problem":[{"double":"0.00","id":"58","pid":"57","remark":"","type":"4","type1":"2","type2":"1","value":"整栋房子","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"4","type1":"1","type2":"1","value":"想出租房屋的哪些部分","yremark":"","yvalue":""},{"double":"0.00","id":"65","pid":"0","problem":[{"double":"0.00","id":"66","pid":"65","remark":"","type":"4","type1":"2","type2":"2","value":"CAD$/Month","yremark":"","yvalue":"","zhi":"123"}],"remark":"","type":"4","type1":"1","type2":"1","value":"您意向的租金价格是多少？","yremark":"","yvalue":""},{"double":"0.00","id":"68","pid":"0","problem":[{"double":"0.00","id":"69","pid":"68","remark":"","type":"4","type1":"2","type2":"1","value":"我需要租赁管理服务","yremark":"","yvalue":"","zhi":""}],"remark":"招租完成后，您可选择由最权威团队提供的长期租赁管理服务，服务内容如下： 向租客收租，收租信息通过客户端推送给客户记录租客及房屋状况并通过客户端反馈给客户（包括维修记录，发生费用、租户状态等）确认租金转至客户账户后通过客户端传达客户（租赁管理取费为每月房屋租金的8%)","type":"4","type1":"1","type2":"1","value":"租赁管理","yremark":"","yvalue":""},{"double":"0.00","id":"71","pid":"0","problem":[{"double":"0.00","id":"72","pid":"71","remark":"","type":"4","type1":"2","type2":"1","value":"取信留存","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"4","type1":"1","type2":"1","value":"我们提供每月一次取信服务，请问您需要怎样处理您的信件？","yremark":"","yvalue":""}],"z_static":"2","z_wen1":"55","z_wen2":"58","z_wen3":"#66#123","z_wen4":"69","z_wen5":"72"}]}
     * status : 200
     */

    private DataBean data;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * balance : 0.00
         * date : [{"address_info":"Guuii","city":"1","ctime":"1481874798","h_id":"15","housing_type":"9","id":"11","j_difficulty":"1.00","j_num":"5","j_proble":[{"double":"0.00","id":"14","pid":"0","problem":[{"double":"30.00","id":"15","pid":"14","remark":"","type":"2","type1":"2","type2":"1","value":"基础清洁服务\u2014\u2014包括房屋清洁、月度床品更换及洗涤、冰箱整理、绿植照顾、衣服洗涤晾晒折叠","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"选择您需要的服务标准","yremark":"","yvalue":""},{"double":"0.00","id":"17","pid":"0","problem":[{"double":"4.00","id":"20","pid":"17","remark":"","type":"2","type1":"2","type2":"1","value":"每月服务4次","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"服务的频次","yremark":"","yvalue":""},{"double":"5.00","id":"91","pid":"0","problem":[{"double":"1.00","id":"92","pid":"91","remark":"","type":"2","type1":"2","type2":"1","value":"大开间","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"您家中有几间卧室?","yremark":"","yvalue":""},{"double":"5.00","id":"98","pid":"0","problem":[{"double":"1.00","id":"99","pid":"98","remark":"","type":"2","type1":"2","type2":"1","value":"1间","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"您家中有几间卫生间？","yremark":"","yvalue":""}],"j_static":"2","j_wen1":"15","j_wen2":"20","j_wen3":"92","j_wen4":"99","k_num":"5","k_proble":[{"double":"0.00","id":"75","pid":"0","problem":[{"double":"0.00","id":"55","pid":"54","remark":"","type":"4","type1":"2","type2":"1","value":"我需要招租服务","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"您需要哪种外围巡视方式？","yremark":"","yvalue":""},{"double":"0.00","id":"79","pid":"0","problem":[{"double":"0.00","id":"58","pid":"57","remark":"","type":"4","type1":"2","type2":"1","value":"整栋房子","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"您需要哪种室内察视方式？","yremark":"","yvalue":""},{"double":"0.00","id":"83","pid":"0","problem":[{"double":"0.00","id":"84","pid":"83","remark":"","type":"5","type1":"2","type2":"1","value":"取信留存","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"我们提供信箱查阅服务，请问您需要的服务是哪一种？","yremark":"","yvalue":""},{"double":"0.00","id":"86","pid":"0","problem":[{"double":"0.00","id":"87","pid":"86","remark":"","type":"5","type1":"2","type2":"1","value":"代处理水电物业账单\n（将收到的账单电子化，协助用户设置自动付款，有非常情况及时通知用户并协助处理）","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"88","pid":"86","remark":"","type":"5","type1":"2","type2":"1","value":"通知并协助用户处理缴费通知单\n（如地税、市政费、路桥费、保险续费等）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"3","value":"我们还提供信件管理服务，请问您都需要哪种服务？","yremark":"","yvalue":""}],"k_static":"2","k_wen1":"55","k_wen2":"58","k_wen3":"84","k_wen4":"87,88","money":"245.00","static":"1","uid":"28","y_leaf":"1.00","y_num":"5","y_proble":[{"double":"0.00","id":"27","pid":"0","problem":[{"double":"0.00","id":"28","pid":"27","remark":"","type":"3","type1":"2","type2":"1","value":"优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"对于草坪修剪我们有两种方案，您的选择是?","yremark":"","yvalue":""},{"double":"0.00","id":"31","pid":"0","problem":[{"double":"0.80","id":"32","pid":"31","remark":"","type":"3","type1":"2","type2":"1","value":"小(小于1000平方英尺)","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"您的草坪有多大?","yremark":"","yvalue":"How big is the lawn?"},{"double":"0.00","id":"37","pid":"0","problem":[{"double":"0.00","id":"38","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"草坪修边","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"39","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"树木灌木修剪","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"40","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"除杂草","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"3","value":"我们还有其他园艺服务供您选择","yremark":"","yvalue":""},{"double":"0.00","id":"43","pid":"0","problem":[{"double":"0.00","id":"44","pid":"43","remark":"","type":"3","type1":"2","type2":"1","value":"我需要落叶清扫服务（秋季9月-10月，隔周清扫）","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"45","pid":"43","remark":"","type":"3","type1":"2","type2":"1","value":"我需要清雪服务（冬季11月-3月，确保行人步道不积雪）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"3","value":"不同季节我们还提供落叶清扫和清雪服务","yremark":"","yvalue":""},{"double":"0.00","id":"46","pid":"0","problem":[{"double":"0.90","id":"47","pid":"46","remark":"","type":"3","type1":"2","type2":"1","value":"小（面积不到2辆小汽车般大）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"需要清雪的人行步道有多大？","yremark":"","yvalue":""},{"double":"0.00","id":"51","pid":"0","problem":[{"double":"0.00","id":"52","pid":"51","remark":"","type":"3","type1":"2","type2":"1","value":"不需要，不介意我不在场的时候工作","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"庭院维护过程中你是否需要在场?","yremark":"","yvalue":""}],"y_snow":"1.00","y_static":"2","y_wen1":"28","y_wen2":"32","y_wen3":"38,39,40","y_wen4":"44,45","y_wen5":"47","y_wen6":"52","z_proble":[{"double":"0.00","id":"54","pid":"0","problem":[{"double":"0.00","id":"55","pid":"54","remark":"","type":"4","type1":"2","type2":"1","value":"我需要招租服务","yremark":"","yvalue":"","zhi":""}],"remark":"招租是一次性服务，在确认需求后协助您通过最权威的渠道找到合适的租户（招租取费为1个月房租，在租赁协议签订后收取）","type":"4","type1":"1","type2":"1","value":"招租服务","yremark":"","yvalue":""},{"double":"0.00","id":"57","pid":"0","problem":[{"double":"0.00","id":"58","pid":"57","remark":"","type":"4","type1":"2","type2":"1","value":"整栋房子","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"4","type1":"1","type2":"1","value":"想出租房屋的哪些部分","yremark":"","yvalue":""},{"double":"0.00","id":"65","pid":"0","problem":[{"double":"0.00","id":"66","pid":"65","remark":"","type":"4","type1":"2","type2":"2","value":"CAD$/Month","yremark":"","yvalue":"","zhi":"123"}],"remark":"","type":"4","type1":"1","type2":"1","value":"您意向的租金价格是多少？","yremark":"","yvalue":""},{"double":"0.00","id":"68","pid":"0","problem":[{"double":"0.00","id":"69","pid":"68","remark":"","type":"4","type1":"2","type2":"1","value":"我需要租赁管理服务","yremark":"","yvalue":"","zhi":""}],"remark":"招租完成后，您可选择由最权威团队提供的长期租赁管理服务，服务内容如下： 向租客收租，收租信息通过客户端推送给客户记录租客及房屋状况并通过客户端反馈给客户（包括维修记录，发生费用、租户状态等）确认租金转至客户账户后通过客户端传达客户（租赁管理取费为每月房屋租金的8%)","type":"4","type1":"1","type2":"1","value":"租赁管理","yremark":"","yvalue":""},{"double":"0.00","id":"71","pid":"0","problem":[{"double":"0.00","id":"72","pid":"71","remark":"","type":"4","type1":"2","type2":"1","value":"取信留存","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"4","type1":"1","type2":"1","value":"我们提供每月一次取信服务，请问您需要怎样处理您的信件？","yremark":"","yvalue":""}],"z_static":"2","z_wen1":"55","z_wen2":"58","z_wen3":"#66#123","z_wen4":"69","z_wen5":"72"}]
         */

        private String balance;




        private List<DateBean> date;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public List<DateBean> getDate() {
            return date;
        }

        public void setDate(List<DateBean> date) {
            this.date = date;
        }

        public static class DateBean {
            /**
             * address_info : Guuii
             * city : 1
             * ctime : 1481874798
             * h_id : 15
             * housing_type : 9
             * id : 11
             * j_difficulty : 1.00
             * j_num : 5
             * j_proble : [{"double":"0.00","id":"14","pid":"0","problem":[{"double":"30.00","id":"15","pid":"14","remark":"","type":"2","type1":"2","type2":"1","value":"基础清洁服务\u2014\u2014包括房屋清洁、月度床品更换及洗涤、冰箱整理、绿植照顾、衣服洗涤晾晒折叠","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"选择您需要的服务标准","yremark":"","yvalue":""},{"double":"0.00","id":"17","pid":"0","problem":[{"double":"4.00","id":"20","pid":"17","remark":"","type":"2","type1":"2","type2":"1","value":"每月服务4次","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"服务的频次","yremark":"","yvalue":""},{"double":"5.00","id":"91","pid":"0","problem":[{"double":"1.00","id":"92","pid":"91","remark":"","type":"2","type1":"2","type2":"1","value":"大开间","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"您家中有几间卧室?","yremark":"","yvalue":""},{"double":"5.00","id":"98","pid":"0","problem":[{"double":"1.00","id":"99","pid":"98","remark":"","type":"2","type1":"2","type2":"1","value":"1间","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"2","type1":"1","type2":"1","value":"您家中有几间卫生间？","yremark":"","yvalue":""}]
             * j_static : 2
             * j_wen1 : 15
             * j_wen2 : 20
             * j_wen3 : 92
             * j_wen4 : 99
             * k_num : 5
             * k_proble : [{"double":"0.00","id":"75","pid":"0","problem":[{"double":"0.00","id":"55","pid":"54","remark":"","type":"4","type1":"2","type2":"1","value":"我需要招租服务","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"您需要哪种外围巡视方式？","yremark":"","yvalue":""},{"double":"0.00","id":"79","pid":"0","problem":[{"double":"0.00","id":"58","pid":"57","remark":"","type":"4","type1":"2","type2":"1","value":"整栋房子","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"您需要哪种室内察视方式？","yremark":"","yvalue":""},{"double":"0.00","id":"83","pid":"0","problem":[{"double":"0.00","id":"84","pid":"83","remark":"","type":"5","type1":"2","type2":"1","value":"取信留存","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"1","value":"我们提供信箱查阅服务，请问您需要的服务是哪一种？","yremark":"","yvalue":""},{"double":"0.00","id":"86","pid":"0","problem":[{"double":"0.00","id":"87","pid":"86","remark":"","type":"5","type1":"2","type2":"1","value":"代处理水电物业账单\n（将收到的账单电子化，协助用户设置自动付款，有非常情况及时通知用户并协助处理）","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"88","pid":"86","remark":"","type":"5","type1":"2","type2":"1","value":"通知并协助用户处理缴费通知单\n（如地税、市政费、路桥费、保险续费等）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"5","type1":"1","type2":"3","value":"我们还提供信件管理服务，请问您都需要哪种服务？","yremark":"","yvalue":""}]
             * k_static : 2
             * k_wen1 : 55
             * k_wen2 : 58
             * k_wen3 : 84
             * k_wen4 : 87,88
             * money : 245.00
             * static : 1
             * uid : 28
             * y_leaf : 1.00
             * y_num : 5
             * y_proble : [{"double":"0.00","id":"27","pid":"0","problem":[{"double":"0.00","id":"28","pid":"27","remark":"","type":"3","type1":"2","type2":"1","value":"优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"对于草坪修剪我们有两种方案，您的选择是?","yremark":"","yvalue":""},{"double":"0.00","id":"31","pid":"0","problem":[{"double":"0.80","id":"32","pid":"31","remark":"","type":"3","type1":"2","type2":"1","value":"小(小于1000平方英尺)","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"您的草坪有多大?","yremark":"","yvalue":"How big is the lawn?"},{"double":"0.00","id":"37","pid":"0","problem":[{"double":"0.00","id":"38","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"草坪修边","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"39","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"树木灌木修剪","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"40","pid":"37","remark":"","type":"3","type1":"2","type2":"1","value":"除杂草","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"3","value":"我们还有其他园艺服务供您选择","yremark":"","yvalue":""},{"double":"0.00","id":"43","pid":"0","problem":[{"double":"0.00","id":"44","pid":"43","remark":"","type":"3","type1":"2","type2":"1","value":"我需要落叶清扫服务（秋季9月-10月，隔周清扫）","yremark":"","yvalue":"","zhi":""},{"double":"0.00","id":"45","pid":"43","remark":"","type":"3","type1":"2","type2":"1","value":"我需要清雪服务（冬季11月-3月，确保行人步道不积雪）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"3","value":"不同季节我们还提供落叶清扫和清雪服务","yremark":"","yvalue":""},{"double":"0.00","id":"46","pid":"0","problem":[{"double":"0.90","id":"47","pid":"46","remark":"","type":"3","type1":"2","type2":"1","value":"小（面积不到2辆小汽车般大）","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"需要清雪的人行步道有多大？","yremark":"","yvalue":""},{"double":"0.00","id":"51","pid":"0","problem":[{"double":"0.00","id":"52","pid":"51","remark":"","type":"3","type1":"2","type2":"1","value":"不需要，不介意我不在场的时候工作","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"3","type1":"1","type2":"1","value":"庭院维护过程中你是否需要在场?","yremark":"","yvalue":""}]
             * y_snow : 1.00
             * y_static : 2
             * y_wen1 : 28
             * y_wen2 : 32
             * y_wen3 : 38,39,40
             * y_wen4 : 44,45
             * y_wen5 : 47
             * y_wen6 : 52
             * z_proble : [{"double":"0.00","id":"54","pid":"0","problem":[{"double":"0.00","id":"55","pid":"54","remark":"","type":"4","type1":"2","type2":"1","value":"我需要招租服务","yremark":"","yvalue":"","zhi":""}],"remark":"招租是一次性服务，在确认需求后协助您通过最权威的渠道找到合适的租户（招租取费为1个月房租，在租赁协议签订后收取）","type":"4","type1":"1","type2":"1","value":"招租服务","yremark":"","yvalue":""},{"double":"0.00","id":"57","pid":"0","problem":[{"double":"0.00","id":"58","pid":"57","remark":"","type":"4","type1":"2","type2":"1","value":"整栋房子","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"4","type1":"1","type2":"1","value":"想出租房屋的哪些部分","yremark":"","yvalue":""},{"double":"0.00","id":"65","pid":"0","problem":[{"double":"0.00","id":"66","pid":"65","remark":"","type":"4","type1":"2","type2":"2","value":"CAD$/Month","yremark":"","yvalue":"","zhi":"123"}],"remark":"","type":"4","type1":"1","type2":"1","value":"您意向的租金价格是多少？","yremark":"","yvalue":""},{"double":"0.00","id":"68","pid":"0","problem":[{"double":"0.00","id":"69","pid":"68","remark":"","type":"4","type1":"2","type2":"1","value":"我需要租赁管理服务","yremark":"","yvalue":"","zhi":""}],"remark":"招租完成后，您可选择由最权威团队提供的长期租赁管理服务，服务内容如下： 向租客收租，收租信息通过客户端推送给客户记录租客及房屋状况并通过客户端反馈给客户（包括维修记录，发生费用、租户状态等）确认租金转至客户账户后通过客户端传达客户（租赁管理取费为每月房屋租金的8%)","type":"4","type1":"1","type2":"1","value":"租赁管理","yremark":"","yvalue":""},{"double":"0.00","id":"71","pid":"0","problem":[{"double":"0.00","id":"72","pid":"71","remark":"","type":"4","type1":"2","type2":"1","value":"取信留存","yremark":"","yvalue":"","zhi":""}],"remark":"","type":"4","type1":"1","type2":"1","value":"我们提供每月一次取信服务，请问您需要怎样处理您的信件？","yremark":"","yvalue":""}]
             * z_static : 2
             * z_wen1 : 55
             * z_wen2 : 58
             * z_wen3 : #66#123
             * z_wen4 : 69
             * z_wen5 : 72
             */

            private String address_info;
            private String city;
            private String ctime;
            private String h_id;
            private String housing_type;
            private String id;
            private String j_difficulty;
            private String j_num;
            private String j_xia;
            private String j_static;
            private String j_wen1;
            private String j_wen2;
            private String j_wen3;
            private String j_wen4;
            private String k_num;
            private String k_xia;
            public String is_que;
            public String is_xia;
            public String ben_money;
            private String k_static;
            private String k_wen1;
            private String k_wen2;
            private String k_wen3;
            private String k_wen4;
            private String money;
            @SerializedName("static")
            private String staticX;
            private String uid;
            private String y_leaf;
            private String y_num;
            private String y_snow;
            private String y_xia;
            private String y_static;
            private String y_wen1;
            private String y_wen2;
            private String y_wen3;
            private String y_wen4;
            private String y_wen5;
            private String y_wen6;
            private String z_xia;
            private String z_static;
            private String z_wen1;
            private String z_wen2;
            private String z_wen3;
            private String z_wen4;
            private String z_wen5;
            private List<JProbleBean> j_proble;
            private List<KProbleBean> k_proble;
            private List<YProbleBean> y_proble;
            private List<ZProbleBean> z_proble;

            public String getBen_money() {
                return ben_money;
            }

            public void setBen_money(String ben_money) {
                this.ben_money = ben_money;
            }

            public String getAddress_info() {
                return address_info;
            }

            public void setAddress_info(String address_info) {
                this.address_info = address_info;
            }

            public String getJ_xia() {
                return j_xia;
            }

            public void setJ_xia(String j_xia) {
                this.j_xia = j_xia;
            }

            public String getK_xia() {
                return k_xia;
            }

            public void setK_xia(String k_xia) {
                this.k_xia = k_xia;
            }

            public String getZ_xia() {
                return z_xia;
            }

            public void setZ_xia(String z_xia) {
                this.z_xia = z_xia;
            }

            public String getY_xia() {
                return y_xia;
            }

            public void setY_xia(String y_xia) {
                this.y_xia = y_xia;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getH_id() {
                return h_id;
            }

            public void setH_id(String h_id) {
                this.h_id = h_id;
            }

            public String getHousing_type() {
                return housing_type;
            }

            public void setHousing_type(String housing_type) {
                this.housing_type = housing_type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getJ_difficulty() {
                return j_difficulty;
            }

            public void setJ_difficulty(String j_difficulty) {
                this.j_difficulty = j_difficulty;
            }

            public String getJ_num() {
                return j_num;
            }

            public void setJ_num(String j_num) {
                this.j_num = j_num;
            }

            public String getJ_static() {
                return j_static;
            }

            public void setJ_static(String j_static) {
                this.j_static = j_static;
            }

            public String getJ_wen1() {
                return j_wen1;
            }

            public void setJ_wen1(String j_wen1) {
                this.j_wen1 = j_wen1;
            }

            public String getJ_wen2() {
                return j_wen2;
            }

            public void setJ_wen2(String j_wen2) {
                this.j_wen2 = j_wen2;
            }

            public String getJ_wen3() {
                return j_wen3;
            }

            public void setJ_wen3(String j_wen3) {
                this.j_wen3 = j_wen3;
            }

            public String getJ_wen4() {
                return j_wen4;
            }

            public void setJ_wen4(String j_wen4) {
                this.j_wen4 = j_wen4;
            }

            public String getK_num() {
                return k_num;
            }

            public void setK_num(String k_num) {
                this.k_num = k_num;
            }

            public String getK_static() {
                return k_static;
            }

            public void setK_static(String k_static) {
                this.k_static = k_static;
            }

            public String getK_wen1() {
                return k_wen1;
            }

            public void setK_wen1(String k_wen1) {
                this.k_wen1 = k_wen1;
            }

            public String getK_wen2() {
                return k_wen2;
            }

            public void setK_wen2(String k_wen2) {
                this.k_wen2 = k_wen2;
            }

            public String getK_wen3() {
                return k_wen3;
            }

            public void setK_wen3(String k_wen3) {
                this.k_wen3 = k_wen3;
            }

            public String getK_wen4() {
                return k_wen4;
            }

            public void setK_wen4(String k_wen4) {
                this.k_wen4 = k_wen4;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getStaticX() {
                return staticX;
            }

            public void setStaticX(String staticX) {
                this.staticX = staticX;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getY_leaf() {
                return y_leaf;
            }

            public void setY_leaf(String y_leaf) {
                this.y_leaf = y_leaf;
            }

            public String getY_num() {
                return y_num;
            }

            public void setY_num(String y_num) {
                this.y_num = y_num;
            }

            public String getY_snow() {
                return y_snow;
            }

            public void setY_snow(String y_snow) {
                this.y_snow = y_snow;
            }

            public String getY_static() {
                return y_static;
            }

            public void setY_static(String y_static) {
                this.y_static = y_static;
            }

            public String getY_wen1() {
                return y_wen1;
            }

            public void setY_wen1(String y_wen1) {
                this.y_wen1 = y_wen1;
            }

            public String getY_wen2() {
                return y_wen2;
            }

            public void setY_wen2(String y_wen2) {
                this.y_wen2 = y_wen2;
            }

            public String getY_wen3() {
                return y_wen3;
            }

            public void setY_wen3(String y_wen3) {
                this.y_wen3 = y_wen3;
            }

            public String getY_wen4() {
                return y_wen4;
            }

            public void setY_wen4(String y_wen4) {
                this.y_wen4 = y_wen4;
            }

            public String getY_wen5() {
                return y_wen5;
            }

            public void setY_wen5(String y_wen5) {
                this.y_wen5 = y_wen5;
            }

            public String getY_wen6() {
                return y_wen6;
            }

            public void setY_wen6(String y_wen6) {
                this.y_wen6 = y_wen6;
            }

            public String getZ_static() {
                return z_static;
            }

            public void setZ_static(String z_static) {
                this.z_static = z_static;
            }

            public String getZ_wen1() {
                return z_wen1;
            }

            public void setZ_wen1(String z_wen1) {
                this.z_wen1 = z_wen1;
            }

            public String getZ_wen2() {
                return z_wen2;
            }

            public void setZ_wen2(String z_wen2) {
                this.z_wen2 = z_wen2;
            }

            public String getZ_wen3() {
                return z_wen3;
            }

            public void setZ_wen3(String z_wen3) {
                this.z_wen3 = z_wen3;
            }

            public String getZ_wen4() {
                return z_wen4;
            }

            public void setZ_wen4(String z_wen4) {
                this.z_wen4 = z_wen4;
            }

            public String getZ_wen5() {
                return z_wen5;
            }

            public void setZ_wen5(String z_wen5) {
                this.z_wen5 = z_wen5;
            }

            public List<JProbleBean> getJ_proble() {
                return j_proble;
            }

            public void setJ_proble(List<JProbleBean> j_proble) {
                this.j_proble = j_proble;
            }

            public List<KProbleBean> getK_proble() {
                return k_proble;
            }

            public void setK_proble(List<KProbleBean> k_proble) {
                this.k_proble = k_proble;
            }

            public List<YProbleBean> getY_proble() {
                return y_proble;
            }

            public void setY_proble(List<YProbleBean> y_proble) {
                this.y_proble = y_proble;
            }

            public List<ZProbleBean> getZ_proble() {
                return z_proble;
            }

            public void setZ_proble(List<ZProbleBean> z_proble) {
                this.z_proble = z_proble;
            }

            public static class JProbleBean {
                /**
                 * double : 0.00
                 * id : 14
                 * pid : 0
                 * problem : [{"double":"30.00","id":"15","pid":"14","remark":"","type":"2","type1":"2","type2":"1","value":"基础清洁服务\u2014\u2014包括房屋清洁、月度床品更换及洗涤、冰箱整理、绿植照顾、衣服洗涤晾晒折叠","yremark":"","yvalue":"","zhi":""}]
                 * remark :
                 * type : 2
                 * type1 : 1
                 * type2 : 1
                 * value : 选择您需要的服务标准
                 * yremark :
                 * yvalue :
                 */

                @SerializedName("double")
                private String doubleX;
                private String id;
                private String pid;
                private String remark;
                private String type;
                private String type1;
                private String type2;
                private String value;
                private String yremark;
                private String yvalue;
                private List<ProblemBean> problem;

                public String getDoubleX() {
                    return doubleX;
                }

                public void setDoubleX(String doubleX) {
                    this.doubleX = doubleX;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getType1() {
                    return type1;
                }

                public void setType1(String type1) {
                    this.type1 = type1;
                }

                public String getType2() {
                    return type2;
                }

                public void setType2(String type2) {
                    this.type2 = type2;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getYremark() {
                    return yremark;
                }

                public void setYremark(String yremark) {
                    this.yremark = yremark;
                }

                public String getYvalue() {
                    return yvalue;
                }

                public void setYvalue(String yvalue) {
                    this.yvalue = yvalue;
                }

                public List<ProblemBean> getProblem() {
                    return problem;
                }

                public void setProblem(List<ProblemBean> problem) {
                    this.problem = problem;
                }

                public static class ProblemBean {
                    /**
                     * double : 30.00
                     * id : 15
                     * pid : 14
                     * remark :
                     * type : 2
                     * type1 : 2
                     * type2 : 1
                     * value : 基础清洁服务——包括房屋清洁、月度床品更换及洗涤、冰箱整理、绿植照顾、衣服洗涤晾晒折叠
                     * yremark :
                     * yvalue :
                     * zhi :
                     */

                    @SerializedName("double")
                    private String doubleX;
                    private String id;
                    private String pid;
                    private String remark;
                    private String type;
                    private String type1;
                    private String type2;
                    private String value;
                    private String yremark;
                    private String yvalue;
                    private String zhi;

                    public String getDoubleX() {
                        return doubleX;
                    }

                    public void setDoubleX(String doubleX) {
                        this.doubleX = doubleX;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getPid() {
                        return pid;
                    }

                    public void setPid(String pid) {
                        this.pid = pid;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getType1() {
                        return type1;
                    }

                    public void setType1(String type1) {
                        this.type1 = type1;
                    }

                    public String getType2() {
                        return type2;
                    }

                    public void setType2(String type2) {
                        this.type2 = type2;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public String getYremark() {
                        return yremark;
                    }

                    public void setYremark(String yremark) {
                        this.yremark = yremark;
                    }

                    public String getYvalue() {
                        return yvalue;
                    }

                    public void setYvalue(String yvalue) {
                        this.yvalue = yvalue;
                    }

                    public String getZhi() {
                        return zhi;
                    }

                    public void setZhi(String zhi) {
                        this.zhi = zhi;
                    }
                }
            }

            public static class KProbleBean {
                /**
                 * double : 0.00
                 * id : 75
                 * pid : 0
                 * problem : [{"double":"0.00","id":"55","pid":"54","remark":"","type":"4","type1":"2","type2":"1","value":"我需要招租服务","yremark":"","yvalue":"","zhi":""}]
                 * remark :
                 * type : 5
                 * type1 : 1
                 * type2 : 1
                 * value : 您需要哪种外围巡视方式？
                 * yremark :
                 * yvalue :
                 */

                @SerializedName("double")
                private String doubleX;
                private String id;
                private String pid;
                private String remark;
                private String type;
                private String type1;
                private String type2;
                private String value;
                private String yremark;
                private String yvalue;
                private List<ProblemBeanX> problem;

                public String getDoubleX() {
                    return doubleX;
                }

                public void setDoubleX(String doubleX) {
                    this.doubleX = doubleX;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getType1() {
                    return type1;
                }

                public void setType1(String type1) {
                    this.type1 = type1;
                }

                public String getType2() {
                    return type2;
                }

                public void setType2(String type2) {
                    this.type2 = type2;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getYremark() {
                    return yremark;
                }

                public void setYremark(String yremark) {
                    this.yremark = yremark;
                }

                public String getYvalue() {
                    return yvalue;
                }

                public void setYvalue(String yvalue) {
                    this.yvalue = yvalue;
                }

                public List<ProblemBeanX> getProblem() {
                    return problem;
                }

                public void setProblem(List<ProblemBeanX> problem) {
                    this.problem = problem;
                }

                public static class ProblemBeanX {
                    /**
                     * double : 0.00
                     * id : 55
                     * pid : 54
                     * remark :
                     * type : 4
                     * type1 : 2
                     * type2 : 1
                     * value : 我需要招租服务
                     * yremark :
                     * yvalue :
                     * zhi :
                     */

                    @SerializedName("double")
                    private String doubleX;
                    private String id;
                    private String pid;
                    private String remark;
                    private String type;
                    private String type1;
                    private String type2;
                    private String value;
                    private String yremark;
                    private String yvalue;
                    private String zhi;

                    public String getDoubleX() {
                        return doubleX;
                    }

                    public void setDoubleX(String doubleX) {
                        this.doubleX = doubleX;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getPid() {
                        return pid;
                    }

                    public void setPid(String pid) {
                        this.pid = pid;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getType1() {
                        return type1;
                    }

                    public void setType1(String type1) {
                        this.type1 = type1;
                    }

                    public String getType2() {
                        return type2;
                    }

                    public void setType2(String type2) {
                        this.type2 = type2;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public String getYremark() {
                        return yremark;
                    }

                    public void setYremark(String yremark) {
                        this.yremark = yremark;
                    }

                    public String getYvalue() {
                        return yvalue;
                    }

                    public void setYvalue(String yvalue) {
                        this.yvalue = yvalue;
                    }

                    public String getZhi() {
                        return zhi;
                    }

                    public void setZhi(String zhi) {
                        this.zhi = zhi;
                    }
                }
            }

            public static class YProbleBean {
                /**
                 * double : 0.00
                 * id : 27
                 * pid : 0
                 * problem : [{"double":"0.00","id":"28","pid":"27","remark":"","type":"3","type1":"2","type2":"1","value":"优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次","yremark":"","yvalue":"","zhi":""}]
                 * remark :
                 * type : 3
                 * type1 : 1
                 * type2 : 1
                 * value : 对于草坪修剪我们有两种方案，您的选择是?
                 * yremark :
                 * yvalue :
                 */

                @SerializedName("double")
                private String doubleX;
                private String id;
                private String pid;
                private String remark;
                private String type;
                private String type1;
                private String type2;
                private String value;
                private String yremark;
                private String yvalue;
                private List<ProblemBeanXX> problem;

                public String getDoubleX() {
                    return doubleX;
                }

                public void setDoubleX(String doubleX) {
                    this.doubleX = doubleX;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getType1() {
                    return type1;
                }

                public void setType1(String type1) {
                    this.type1 = type1;
                }

                public String getType2() {
                    return type2;
                }

                public void setType2(String type2) {
                    this.type2 = type2;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getYremark() {
                    return yremark;
                }

                public void setYremark(String yremark) {
                    this.yremark = yremark;
                }

                public String getYvalue() {
                    return yvalue;
                }

                public void setYvalue(String yvalue) {
                    this.yvalue = yvalue;
                }

                public List<ProblemBeanXX> getProblem() {
                    return problem;
                }

                public void setProblem(List<ProblemBeanXX> problem) {
                    this.problem = problem;
                }

                public static class ProblemBeanXX {
                    /**
                     * double : 0.00
                     * id : 28
                     * pid : 27
                     * remark :
                     * type : 3
                     * type1 : 2
                     * type2 : 1
                     * value : 优选方案，拥有一个漂亮的草坪，六至八月每月修剪3次，四、五和九月每月修剪2次
                     * yremark :
                     * yvalue :
                     * zhi :
                     */

                    @SerializedName("double")
                    private String doubleX;
                    private String id;
                    private String pid;
                    private String remark;
                    private String type;
                    private String type1;
                    private String type2;
                    private String value;
                    private String yremark;
                    private String yvalue;
                    private String zhi;

                    public String getDoubleX() {
                        return doubleX;
                    }

                    public void setDoubleX(String doubleX) {
                        this.doubleX = doubleX;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getPid() {
                        return pid;
                    }

                    public void setPid(String pid) {
                        this.pid = pid;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getType1() {
                        return type1;
                    }

                    public void setType1(String type1) {
                        this.type1 = type1;
                    }

                    public String getType2() {
                        return type2;
                    }

                    public void setType2(String type2) {
                        this.type2 = type2;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public String getYremark() {
                        return yremark;
                    }

                    public void setYremark(String yremark) {
                        this.yremark = yremark;
                    }

                    public String getYvalue() {
                        return yvalue;
                    }

                    public void setYvalue(String yvalue) {
                        this.yvalue = yvalue;
                    }

                    public String getZhi() {
                        return zhi;
                    }

                    public void setZhi(String zhi) {
                        this.zhi = zhi;
                    }
                }
            }

            public static class ZProbleBean {
                /**
                 * double : 0.00
                 * id : 54
                 * pid : 0
                 * problem : [{"double":"0.00","id":"55","pid":"54","remark":"","type":"4","type1":"2","type2":"1","value":"我需要招租服务","yremark":"","yvalue":"","zhi":""}]
                 * remark : 招租是一次性服务，在确认需求后协助您通过最权威的渠道找到合适的租户（招租取费为1个月房租，在租赁协议签订后收取）
                 * type : 4
                 * type1 : 1
                 * type2 : 1
                 * value : 招租服务
                 * yremark :
                 * yvalue :
                 */

                @SerializedName("double")
                private String doubleX;
                private String id;
                private String pid;
                private String remark;
                private String type;
                private String type1;
                private String type2;
                private String value;
                private String yremark;
                private String yvalue;
                private List<ProblemBeanXXX> problem;

                public String getDoubleX() {
                    return doubleX;
                }

                public void setDoubleX(String doubleX) {
                    this.doubleX = doubleX;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getType1() {
                    return type1;
                }

                public void setType1(String type1) {
                    this.type1 = type1;
                }

                public String getType2() {
                    return type2;
                }

                public void setType2(String type2) {
                    this.type2 = type2;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getYremark() {
                    return yremark;
                }

                public void setYremark(String yremark) {
                    this.yremark = yremark;
                }

                public String getYvalue() {
                    return yvalue;
                }

                public void setYvalue(String yvalue) {
                    this.yvalue = yvalue;
                }

                public List<ProblemBeanXXX> getProblem() {
                    return problem;
                }

                public void setProblem(List<ProblemBeanXXX> problem) {
                    this.problem = problem;
                }

                public static class ProblemBeanXXX {
                    /**
                     * double : 0.00
                     * id : 55
                     * pid : 54
                     * remark :
                     * type : 4
                     * type1 : 2
                     * type2 : 1
                     * value : 我需要招租服务
                     * yremark :
                     * yvalue :
                     * zhi :
                     */

                    @SerializedName("double")
                    private String doubleX;
                    private String id;
                    private String pid;
                    private String remark;
                    private String type;
                    private String type1;
                    private String type2;
                    private String value;
                    private String yremark;
                    private String yvalue;
                    private String zhi;

                    public String getDoubleX() {
                        return doubleX;
                    }

                    public void setDoubleX(String doubleX) {
                        this.doubleX = doubleX;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getPid() {
                        return pid;
                    }

                    public void setPid(String pid) {
                        this.pid = pid;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getType1() {
                        return type1;
                    }

                    public void setType1(String type1) {
                        this.type1 = type1;
                    }

                    public String getType2() {
                        return type2;
                    }

                    public void setType2(String type2) {
                        this.type2 = type2;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public String getYremark() {
                        return yremark;
                    }

                    public void setYremark(String yremark) {
                        this.yremark = yremark;
                    }

                    public String getYvalue() {
                        return yvalue;
                    }

                    public void setYvalue(String yvalue) {
                        this.yvalue = yvalue;
                    }

                    public String getZhi() {
                        return zhi;
                    }

                    public void setZhi(String zhi) {
                        this.zhi = zhi;
                    }
                }
            }
        }
    }
}
