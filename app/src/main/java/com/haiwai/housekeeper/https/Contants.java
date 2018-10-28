package com.haiwai.housekeeper.https;


import com.google.common.io.BaseEncoding;
import com.haiwai.housekeeper.base.CommonConfig;

public class Contants {
    public static final String TOKENS = "pk.eyJ1IjoibGl1eWoiLCJhIjoiY2l6amhjbWNwMDNucjMybWliaTQwNGl5YSJ9.HmoUCkUzAShnBGbRRkjk5g";
    public static final String APP_ID = "wx352971154270fabc";
    public static final String SYSNUM = "ac817ec0cda84383886cb85484282945";
//    public static final String TOKEN_URL = "http://api.cn.ronghub.com/user/getToken.json?";

    public static final String BASE_URL = CommonConfig.BASE_URL;
//    public static final String BASE_URL ="http://60.205.171.101/hwgj/index.php" ;

    public static final String BASE_URL1 = "http://hwgj.zai0312.com/index.php";

    public static final String cancel_take_order = BASE_URL + "/api/service/order_offer_del?";

    /**
     * 添加paypal账号
     */

    public static final String add_paypal = BASE_URL + "/api/user/paypal_add?";
    /**
     * 修改paypal账号
     */

    public static final String save_paypal = BASE_URL + "/api/user/paypal_save?";
    /**
     * 获取paypal账号
     */
    public static final String get_paypal = BASE_URL + "/api/user/paypal_lst?";

    /**
     * 获取验证码接口
     */
    public static final String get_verify_code = BASE_URL + "/api/user/verify_code?";

    /**
     * 注册接口
     */
    public static final String register = BASE_URL + "/api/user/register?";

    /**
     * 登录接口
     */
    public static final String login = BASE_URL + "/api/user/login?";

    /**
     * 找回密码接口
     */
    public static final String find_back_password = BASE_URL + "/api/user/password?";

    /**
     * 用户信息修改
     */
    public static final String change_info = BASE_URL + "/api/user/user_save?";

    /**
     * 相册上传照片
     */
    public static final String photo_add = BASE_URL + "/api/user/photo_add?";

    /**
     * 相册删除照片
     */
    public static final String photo_del = BASE_URL + "/api/user/photo_del?";

    /**
     * 相册列表
     */
    public static final String photo_list = BASE_URL + "/api/user/photo_lst?";

    /**
     * 用户反馈
     */
    public static final String feedback_add = BASE_URL + "/api/user/feedback_add?";

    /**
     * 业务信息
     */
    public static final String service_type = BASE_URL + "/api/index/service_type?";

    /**
     * 添加房产
     */
    public static final String hous_add = BASE_URL + "/api/User/hous_add?";

    /**
     * 修改房产
     */
    public static final String hous_save = BASE_URL + "/api/User/hous_save?";

    /**
     * 删除房产
     */
    public static final String hous_del = BASE_URL + "/api/User/hous_del?";

    /**
     * 房产列表
     */
    public static final String hous_lst = BASE_URL + "/api/User/hous_lst?";
    /**
     * pro列表
     */
    public static final String pro_lst = BASE_URL + "/api/User/pro_lst?";
    /**
     * pro详情
     */
    public static final String pro_detail = BASE_URL + "/api/User/pro_detail?";
    /**
     * 用户评论列表
     */
    public static final String user_comment = BASE_URL + "/api/User/user_comment?";
    /**
     * 城市列表
     */
    public static final String city_lst = BASE_URL + "/api/User/city_lst?";

//    /**
//     * 首页业务列表(重复)
//     */
//    public static final String sevice_type = BASE_URL + "/api/Index/sevice_type?";

    //支付
    public static final String pay_for = BASE_URL + "/api/service/zhifu?";

    /**
     * 自营下单
     */
    public static final String self = BASE_URL + "/api/index/self?";

    /**
     * 账户充值
     */
    public static final String zhi = BASE_URL + "/api/Pay/zhi?";

    /**
     * 发现列表
     */
    public static final String find_lst = BASE_URL + "/api/User/find_lst?";

    /**
     * (所有类型)下单
     */
    public static final String down_order = BASE_URL + "/api/Order/down_order?";

    /**
     * 物业账单
     */
    public static final String utility_bill = BASE_URL + "/api/Vip/utility_bill?";


    /**
     * 需求列表
     */
    public static final String user_order_lst = BASE_URL + "/api/user/order_lst?";//static  1招标中2待上门3服务中4待支付5待评价

    /**
     * 需求列表--订单详情
     */
    public static final String user_order_detail = BASE_URL + "/api/user/order_detail?";


    //--------------------------------------------service-----------------------------------------------//
    /**
     * 身份认证
     */
    public static final String id_card = BASE_URL + "/api/user/id_card?";

    /**
     * 发布技能
     */
    public static final String skill_add = BASE_URL + "/api/user/skill_add?";


    /**
     * 技能列表
     */
    public static final String skill_lst = BASE_URL + "/api/User/skill_lst?";

    /**
     * 技能详情
     */
    public static final String skill_detail = BASE_URL + "/api/User/skill_detail?";

    /**
     * 技能认证增加
     */
    public static final String skill_ren = BASE_URL + "/api/user/skill_ren?";

    /**
     * 身份认证信息和技能认证信息
     */
    public static final String ren_lst = BASE_URL + "/api/user/ren_lst?";

    /**
     * 技能修改
     */
    public static final String skill_eduit = BASE_URL + "/api/user/skill_eduit?";

    /**
     * 发布技能_修改技能开启项
     */
    public static final String skill_save = BASE_URL + "/api/User/skill_save?";

    /**
     * 技能删除
     */
    public static final String skill_del = BASE_URL + "/api/user/skill_del?";

    /**
     * 技能激活
     */
    public static final String skill_on = BASE_URL + "/api/user/skill_on?";

    /**
     * 技能关闭
     */
    public static final String skill_off = BASE_URL + "/api/user/skill_off?";

    /**
     * o2o订单管理
     */
    public static final String order_lst = BASE_URL + "/api/service/order_lst?";

    /**
     * 订单详情
     */
    public static final String order_detail = BASE_URL + "/api/service/order_detail?";

    /**
     * 下单报价
     */
    public static final String order_offer = BASE_URL + "/api/service/order_offer?";
    /**
     * 修改报价
     */
    public static final String offer_save = BASE_URL + "/api/service/offer_save?";
    /**
     * 服务商取消订单
     */
    public static final String order_fdel = BASE_URL + "/api/Order/order_fdel";
    /**
     * 用户取消订单
     */
    public static final String order_ydel = BASE_URL + "/api/Order/order_ydel?";
    /**
     * 订单评价标签
     */
    public static final String order_label = BASE_URL + "/api/Order/order_lable?";
    /**
     * 订单修改报价确认
     */
    public static final String offer_saveque = BASE_URL + "/api/Service/offer_saveque?";

    /**
     * 接单状态列表
     */
    public static final String single_lst = BASE_URL + "/api/Service/single_lst?";
    /**
     * 选人
     */
    public static final String order_choice = BASE_URL + "/api/Service/order_choice?";
    /**
     * 服务商点击订单完成
     */
    public static final String order_complete = BASE_URL + "/api/Service/order_complete?";
    /**
     * 订单评论
     */
    public static final String comment = BASE_URL + "/api/Service/comment?";
    /**
     * 订单支付
     */
    public static final String order_zhi = BASE_URL + "/api/Pay/order_zhi?";// TODO: 2016/11/22  
    /**
     * 查看o2o订单评论
     */
    public static final String order_comment = BASE_URL + "/api/Service/order_comment?";
    /**
     * 获取自己的报价信息
     */
    public static final String user_offer = BASE_URL + "/api/service/user_offer?";
    /**
     * 订单驳回报价
     */
    public static final String offer_bh = BASE_URL + "/api/Service/offer_bh?";
    /**
     * 服务商订单清零
     */
    public static final String order_ql = BASE_URL + "/api/Service/order_ql?";
    /**
     * 服务商确认开工
     */
    public static final String startg = BASE_URL + "/api/Service/startg?";
    /**
     * 自营订单管理
     */
    public static final String self_lst = BASE_URL + "/api/service/self_lst?";
    /**
     * 自营订单详情
     */
    public static final String self_detail = BASE_URL + "/api/service/self_detail?";
    /**
     * 自营订单接单
     */
    public static final String self_offer = BASE_URL + "/api/service/self_offer?";
    /**
     * 房屋巡视反馈1
     */
    public static final String vacancy1 = BASE_URL + "/api/service/vacancy1?";
    /**
     * 房屋巡视反馈2
     */
    public static final String vacancy2 = BASE_URL + "/api/service/vacancy2?";
    /**
     * 房屋巡视反馈3
     */
    public static final String vacancy3 = BASE_URL + "/api/service/vacancy3?";
    /**
     * 家政服务反馈
     */
    public static final String home = BASE_URL + "/api/service/home?";
    /**
     * 园艺管理反馈
     */
    public static final String horticulture = BASE_URL + "/api/service/horticulture?";
    /**
     * VIP-房屋管理列表
     */
    public static final String home_order = BASE_URL + "/api/vip/home_order?";
    /**
     * VIP-房屋管理-房屋的历史月
     */
    public static final String histry_month = BASE_URL + "/api/vip/histry_month?";
    /**
     * VIP-我的vip
     */
    public static final String vip_lst = BASE_URL + "/api/vip/vip_lst?";
    /**
     * 管理方案修改
     */
    public static final String support_save = BASE_URL + "/api/vip/support_save?";
    /**
     * 获取用户审核通过并且激活的o2o技能
     */
    public static final String skill_o2o = BASE_URL + "/api/index/skill_o2o?";
    /**
     * 获取用户审核通过并且激活的自营技能
     */
    public static final String skill_self = BASE_URL + "/api/index/skill_self?";
    /**
     * w
     * 添加服务商的服务地址
     */
    public static final String proservice_add = BASE_URL + "/api/service/proservice_add?";
    /**
     * 获取服务商服务地址列表
     */
    public static final String proservice_lst = BASE_URL + "/api/service/proservice_lst?";
    /**
     * 修改服务商的服务地址
     */
    public static final String proservice_save = BASE_URL + "/api/service/proservice_save?";
    /**
     * 服务商的服务地址删除
     */
    public static final String proservice_del = BASE_URL + "/api/service/proservice_del?";
    /**
     * 设置服务商的默认服务地址
     */
    public static final String proservice_mo = BASE_URL + "/api/service/proservice_mo?";
    /**
     * 获取自营价格
     */
    public static final String self_money = BASE_URL + "/api/service/self_money?";
    /**
     * 自营招租订单详情
     */
    public static final String zhao_detail = BASE_URL + "/api/service/zhao_detail?";
    /**
     * 自营管理订单详情
     */
    public static final String guan_detail = BASE_URL + "/api/service/guan_detail?";
    /**
     * 自营招租成功确认
     */
    public static final String zhao_que = BASE_URL + "/api/service/zhao_que?";
    /**
     * 自营租赁管理-确认租金
     */
    public static final String rent_que = BASE_URL + "/api/service/rent_que?";
    /**
     * 自营租赁管理-第二步房屋和用户反馈
     */
    public static final String home_remark = BASE_URL + "/api/service/home_remark?";
    /**
     * 自营租赁管理-第三步价格清单反馈
     */
    public static final String price_remark = BASE_URL + "/api/service/price_remark?";
    /**
     * 自营租赁管理-查看账单的历史月
     */
    public static final String bill_lst = BASE_URL + "/api/service/bill_lst?";
    /**
     * 自营订单取消接单
     */
    public static final String self_offer_del = BASE_URL + "/api/service/self_offer_del?";
    /**
     * 添加关注
     */
    public static final String follow_add = BASE_URL + "/api/user/follow_add?";
    /**
     * 取消关注
     */
    public static final String follow_del = BASE_URL + "/api/user/follow_del?";
    /**
     * 关注我的人
     */
    public static final String fans_lst = BASE_URL + "/api/user/fans_lst?";
    /**
     * 我关注的人
     */
    public static final String follow_lst = BASE_URL + "/api/user/follow_lst?";
    /**
     * 互相关注的人
     */
    public static final String mutual_concern = BASE_URL + "/api/user/mutual_concern?";
    /**
     * 融云获取Token
     */
    public static final String getToken = BASE_URL + "/api/run/getToken?";
    /**
     * 融云刷新用户
     */
    public static final String refresh = BASE_URL + "/api/run/refresh?";
    /**
     * Paypal充值
     */
    public static final String paypalzhi = BASE_URL + "/api/pay/paypalzhi?";
    /**
     * Paypal提现
     */
    public static final String paypalti = BASE_URL + "/api/pay/paypalti?";
    /**
     * Paypal订单支付
     */
    public static final String paypalzhifu = BASE_URL + "/api/pay/paypalzhifu?";
    /**
     * 支付记录
     */
    public static final String zhi_record = BASE_URL + "/api/user/zhi_record?";
    /**
     * 充值记录
     */
    public static final String chongzhi_record = BASE_URL + "/api/user/chongzhi_record?";
    /**
     * 举报服务商
     */
    public static final String report = BASE_URL + "/api/user/report?";
    /**
     * 房产问题和答案
     */
    public static final String hous_problem = BASE_URL + "/api/user/hous_problem?"; //参数自营问题type=2,3,4，5房屋保洁、庭院管理、房屋租赁、空屋管理
    /**
     * 用户详情
     */
    public static final String user_detail = BASE_URL + "/api/User/user_detail?";

    /**
     * 系统消息
     */
    public static final String sys_push = BASE_URL + "/api/User/sys_push?";
    /**
     * 版本更新
     */
    public static final String update_version = BASE_URL + "/api/User/edition?";
    /**
     * 首页数据
     */
    public static final String home_page = BASE_URL1 + "/api/User/home_page?";
    /**
     * 获取房屋保洁服务概要的价格
     */
    public static final String house_clean = BASE_URL + "/api/order/house_clean?";
    /**
     * 获取庭院管理服务概要
     */
    public static final String yard_management = BASE_URL + "/api/order/yard_management?";
    /**
     * 判断是否有代金券
     */
    public static final String coupon = BASE_URL + "/api/service/coupon?";
    /**
     * 使用代金卷进行付款
     */
    public static final String offer_coupon = BASE_URL + "/api/service/offer_coupon?";
    /**
     * 接单paypal支付
     */
    public static final String paypaljiedan = BASE_URL + "/api/Pay/paypaljiedan?";
    /**
     * 接单paypal支付
     */
    public static final String self_dingzhi = BASE_URL + "/api/service/self_dingzhi?";
    /**
     * 获取套餐的价格和月
     */
    public static final String support_price = BASE_URL + "/api/service/support_price?";


    public static final String ding_support_pirce = BASE_URL + "/api/service/self_dingzhi_detail?";

    /**
     * 接单券
     */

    public static final String coupon_userlst = BASE_URL + "/api/service/coupon_userlst?";
    /**
     * 获取订单的支付金额
     */
    public static final String order_paymoney = BASE_URL1 + "/api/user/order_paymoney?";
    /**
     * 原价不变
     */
    public static final String offer_que = BASE_URL + "/api/service/offer_que?";
    /**
     * 上门确认
     */
    public static final String que_sm = BASE_URL + "/api/service/que_sm?";
    /**
     * 支付宝
     */
    public static final String alipay = BASE_URL1 + "/api/Pay/alipay?";
    /**
     * 支付宝支付
     */
    public static final String alipay_zhifu = BASE_URL + "/api/Pay/alipay_zhifu?";
    /**
     * 支付宝接单
     */
    public static final String alipay_jiedan = BASE_URL + "/api/Pay/alipay_jiedan?";
    /**
     * 微信充值
     */
    public static final String wxpay = BASE_URL1 + "/api/Pay/wxpay?";
    /**
     * 微信支付
     */
    public static final String wxpay_zhifu = BASE_URL1 + "/api/Pay/wxpay_zhifu?";
    /**
     * 微信接单
     */
    public static final String wxpay_jiedan = BASE_URL + "/api/Pay/wxpay_jiedan?";
    /**
     * 获取余额
     */
    public static final String balance = BASE_URL + "/api/User/balance?";
    /**
     * 判读是否可以聊天
     */
    public static final String liao = BASE_URL + "/api/User/liao?";
    /**
     * 获取未读消息
     */
    public static final String unread_message = BASE_URL + "/api/User/unread_message?";

    /**
     * google解析
     */
//    public static final String googleapi = "https://maps.googleapis.com/maps/api/geocode/json?";
    public static final String googleapi = "https://maps.google.cn/maps/api/geocode/json?";

    /**
     * 国家区码
     */
    public static final String country_code = BASE_URL + "/api/User/country_code?";
    /**
     * 汇率换算
     */
    public static final String exchange_rate = BASE_URL1 + "/api/User/exchange_rate?";
    /**
     * 获取秘钥接口
     */
    public static final String secret_key = BASE_URL + "/api/User/secret_key?";

    /*
    我的收入
    */
    public static final String My_income = BASE_URL1 + "/api/user/wo_shouru?";
    /*
    我的收入详情
    */
    public static final String My_income_details = BASE_URL1 + "/api/user/wo_shouru_xq?";

    /*
   订单支付（微信）
   */
    public static final String OrderWXPay = BASE_URL1 + "/api/pay/o2o_wxpay_app?";
    /*
 订单支付（支付宝）
 */
    public static final String OrderAliPay = BASE_URL1 + "/api/pay/o2o_alipay_app?";
    /*
获取平台佣金比例
*/
    public static final String Commission = BASE_URL1 + "/api/user/exchange?";
    /*
    获取o2o订单手续费
    */
    public static final String ServiceCharge = BASE_URL1 + "/api/service/self_money?";
    /*
     设置用户收款信息
   */
    public static final String Userreceiptsinformation = BASE_URL1 + "/api/user/user_shouk?";
    /*
        设置用户收款信息
      */
    public static final String Userreceiptsinformationlist = BASE_URL1 + "/api/user/user_shouk_lst?";
}
