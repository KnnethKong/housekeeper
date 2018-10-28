package com.haiwai.housekeeper.utils;

import com.haiwai.housekeeper.base.AppGlobal;
import com.haiwai.housekeeper.base.MyApp;

/**
 * 错误信息工具类
 *
 * @author Vayne
 */
public class ErrorCodeUtils {

    private static String error = "";

    /**
     * 根据注册时返回的错误码 返回错误信息
     * 200  正常返回
     * 220 接口请求失败
     * 240 支付失败
     * 400  参数错误
     * 401  请求验证错误
     * 403  无权限操作
     * 404  无此文件接口
     * 406  同时操作文件太多
     * 413  文件太大
     * 500  服务器内部错误
     * 10001  验证码不存在或者过期
     * 1101 该用户不存在
     * 1102 该用户已存在
     * 1103 已经认证过了
     * 1104 该技能已经添加过了
     * 1105 该技能已经认证了
     * 1106 该技能不存在
     * 1107 该技能没有审核通过
     * 1108 已报过价
     * 1109 您的账户余额不足5美元不能取消订单
     * 1110 该房产已下过订单了
     * 1111 没有服务商地址不能进服务商端
     * 1112 身份已认证
     * 1113 密码错误
     * 1201 这个订单已经选择服务商了
     * 1202 这个订单不存在
     * 1203 您已经评价过不能在评价了
     * 1204 该订单不能进行支付
     * 1205 该订单不允许执行当前的操作
     * 1301 请先把上一次完成,在完成这次
     * 1302 您没有自营技能不能查看周期订单
     * 1001 验证码已使用或者已过期
     * 1303 自营订单次数最多为4
     * 1304 您没有这个技能不能执行当前操作
     * 1305 余额不足不能下单,请先充值
     * 1306 25号后不能下单
     * 1307 租赁-管理请按照反馈步骤完成
     * 1001 验证码已使用或者已过期
     * 1114 添加过技能,当没有审核通过的技能    订单管理接口新增状态码
     * 1115  用户已被东街诶
     * 1116 该房产有周期订单不能删除
     * 1404 该账号已在其他设备登录
     * 1405 您的信用积分不足，不能取消订单
     * 1406 您的取消次数不足，不能取消订单
     *
     * @param errorCode
     */
    public static String getRegisterError(String errorCode) {
        String isZhorEn = AppGlobal.getInstance().getLagStr();
        if (errorCode.equals("200")) {
            if ("en".equals(isZhorEn)) {
                error = "Back";
            } else {
                error = "正常返回";
            }
        } else if (errorCode.equals("220")) {
            if ("en".equals(isZhorEn)) {
                error = "Interface request failed";
            } else {
                error = "接口请求失败";
            }

        } else if (errorCode.equals("240")) {
            if ("en".equals(isZhorEn)) {
                error = "Fail to pay ";
            } else {
                error = "支付失败";
            }
        } else if (errorCode.equals("400")) {
            if ("en".equals(isZhorEn)) {
                error = "Parameter erro";
            } else {
                error = "参数错误";
            }
        } else if (errorCode.equals("401")) {
            if ("en".equals(isZhorEn)) {
                error = "Request validation error";
            } else {
                error = "请求验证错误";
            }
        } else if (errorCode.equals("403")) {
            if ("en".equals(isZhorEn)) {
                error = "Permission denied";
            } else {
                error = "无权限操作";
            }
        } else if (errorCode.equals("404")) {
            if ("en".equals(isZhorEn)) {
                error = "No file interface";
            } else {
                error = "无此文件接口";
            }
        } else if (errorCode.equals("406")) {
            if ("en".equals(isZhorEn)) {
                error = "Operating too many files at the same time";
            } else {
                error = "同时操作文件太多";
            }
        } else if (errorCode.equals("407")) {
            if ("en".equals(isZhorEn)) {
                error = "Data does not exist";
            } else {
                error = "数据不存在";
            }
        } else if (errorCode.equals("413")) {
            if ("en".equals(isZhorEn)) {
                error = "The file is too big";
            } else {
                error = "文件太大";
            }
        } else if (errorCode.equals("500")) {
            if ("en".equals(isZhorEn)) {
                error = "Server internal error";
            } else {
                error = "服务器内部错误";
            }
        } else if (errorCode.equals("10001")) {
            if ("en".equals(isZhorEn)) {
                error = "Verification code does not exist or expires";
            } else {
                error = "验证码不存在或者过期";
            }
        } else if (errorCode.equals("1101")) {
            if ("en".equals(isZhorEn)) {
                error = "The account or pass is error";
//                error = "The account dosen't exist"
            } else {
                error = "用户名或者密码错误";
//                error = "该用户不存在"
            }
        } else if (errorCode.equals("1102")) {
            if ("en".equals(isZhorEn)) {
                error = "The account has already existed";
            } else {
                error = "该用户已存在";
            }
        } else if (errorCode.equals("1103")) {
            if ("en".equals(isZhorEn)) {
                error = "It has been endorsed";
            } else {
                error = "已经认证过了";
            }
        } else if (errorCode.equals("1104")) {
            if ("en".equals(isZhorEn)) {
                error = "The skill has been added";
            } else {
                error = "该技能已经添加过了";
            }
        } else if (errorCode.equals("1105")) {
            if ("en".equals(isZhorEn)) {
                error = "The skill has been endorsed";
            } else {
                error = "该技能已经认证了";
            }
        } else if (errorCode.equals("1106")) {
            if ("en".equals(isZhorEn)) {
                error = "The skill dosen't exist";
            } else {
                error = "该技能不存在";
            }
        } else if (errorCode.equals("1107")) {
            if ("en".equals(isZhorEn)) {
                error = "The skill is not endorsed";
            } else {
                error = "该技能没有审核通过";
            }
        } else if (errorCode.equals("1108")) {
            if ("en".equals(isZhorEn)) {
                error = "Your quote has been sent";
            } else {
                error = "已接过单";
            }
        } else if (errorCode.equals("1109")) {
            if ("en".equals(isZhorEn)) {
                error = "You can't cancle the order for the balance is less than 5 $";
            } else {
                error = "您的账户余额不足5美元不能取消订单";
            }
        } else if (errorCode.equals("1110")) {
            if ("en".equals(isZhorEn)) {
                error = "You have already placed an order on the Property";
            } else {
                error = "该房产已下过订单了";
            }
        } else if (errorCode.equals("1111")) {
            if ("en".equals(isZhorEn)) {
                error = "No skills";
            } else {
                error = "暂无任何技能";
            }
        } else if (errorCode.equals("1112")) {
            if ("en".equals(isZhorEn)) {
                error = "Identity has been verified";
            } else {
                error = "身份已认证";
            }
        } else if (errorCode.equals("1113")) {
            if ("en".equals(isZhorEn)) {
                error = "Wrong password";
            } else {
                error = "密码错误";
            }
        } else if ("1114".equals(errorCode)) {
            if ("en".equals(isZhorEn)) {
                error = "The skill is being endorsed";
            } else {
                error = "您提交的技能正在审核";
            }
        } else if (errorCode.equals("1201")) {
            if ("en".equals(isZhorEn)) {
                error = "The Pro has been selected";
            } else {
                error = "这个订单已经选择服务商了";
            }
        } else if (errorCode.equals("1202")) {
            if ("en".equals(isZhorEn)) {
                error = "The order dosen't exist";
            } else {
                error = "这个订单不存在";
            }
        } else if (errorCode.equals("1203")) {
            if ("en".equals(isZhorEn)) {
                error = "You have already left reviews";
            } else {
                error = "您已经评价过不能在评价了";
            }
        } else if (errorCode.equals("1204")) {
            if ("en".equals(isZhorEn)) {
                error = "Payment can't be operated";
            } else {
                error = "该订单不能进行支付";
            }
        } else if (errorCode.equals("1205")) {
            if ("en".equals(isZhorEn)) {
                error = "The current operation is not allowed to be performed";
            } else {
                error = "该订单不允许执行当前的操作";
            }
        } else if (errorCode.equals("1301")) {
            error = "请先把上一次完成,在完成这次";
        } else if (errorCode.equals("1302")) {
            error = "您暂无周期技能，请联系客服";
        } else if ("1303".equals(errorCode)) {
            error = "自营订单次数最多为4";
        } else if ("1304".equals(errorCode)) {
            error = "您没有这个技能不能执行当前操作";
        } else if ("1305".equals(errorCode)) {
            error = "余额不足不能下单,请先充值";
        } else if ("1306".equals(errorCode)) {
            error = "25号后不能下单";
        } else if ("1307".equals(errorCode)) {
            error = "租赁-管理请按照反馈步骤完成";
        } else if ("1401".equals(errorCode)) {
            error = "该订单不能支付";
        } else if ("1402".equals(errorCode)) {
            error = "该订单已支付";
        } else if ("1403".equals(errorCode)) {
            error = " 该订单支付金额不对";
        } else if ("1115".equals(errorCode)) {
            error = "用户已被东街诶";
        } else if ("1116".equals(errorCode)) {
            error = "该房产有周期订单不能删除";
        } else if ("1404".equals(errorCode)) {
            if(AppGlobal.getInstance().getLagStr().equals("en")){
                error = "The accoutn has been logged in on antoher device";
            }else{
                error = "该账号已在其他设备登录";
            }

            MyApp.getTingtingApp().setLoginState(false);
        } else if ("1405".equals(errorCode)) {
            error = "您的信用积分不足，不能取消订单";
        } else if ("1406".equals(errorCode)) {
            error = "您的取消次数不足，不能取消订单";
        }
        return error;
    }


    public static String getIMCodeState(int code) {
        String str = "";
        switch (code) {
            case 200:
                str = "成功";
                break;
            case 400:
                str = "错误请求";
                break;
            case 401:
                str = "验证错误";
                break;
            case 403:
                str = "被拒绝";
                break;
            case 404:
                str = "无法找到";
                break;
            case 405:
                str = "群上限";
                break;
            case 429:
                str = "过多的请求";
                break;
            case 500:
                str = "内部服务错误";
                break;
            case 504:
                str = "内部服务响应超时";
                break;
            case 1000:
                str = "服务内部错误";
                break;
            case 1001:
                str = "App Secret 错误";
                break;
            case 1002:
                str = "参数错误";
                break;
            case 1003:
                str = "无 POST 数据";
                break;
            case 1004:
                str = "验证签名错误";
                break;
            case 1005:
                str = "参数长度超限";
                break;
            case 1006:
                str = "App 被锁定或删除";
                break;
            case 1007:
                str = "被限制调用";
                break;
            case 1008:
                str = "调用频率超限";
                break;
            case 1009:
                str = "服务未开通";
                break;
            case 1050:
                str = "内部服务超时";
                break;
            case 2007:
                str = "测试用户数量超限";
                break;
        }

        return str;
    }
}
