package com.xmn.saas.service.sms;

/**
 * 短信服务接口
 * create 2016/09/27
 * @author yangQiang
 */

public interface SmsService {

    // 验证码类型 : 忘记登录密码
    String CODE_TYPE_LOGIN_PASSWORD = "1" ;
    // 验证码类型 : 忘记支付密码
    String CODE_TYPE_PAY_PASSWORD= "2";
    // 验证码类型 : 忘记手势密码
    String CODE_TYPE_GESTURE_PASSWORD = "3";
    // 验证码类型 : 绑定银行卡
    String CODE_TYPE_BINDING_BANK_CARD = "4";


    // 短信的Redis前缀(namespace)
    String SMS_REDIS_PREFIX = "saas:api:sms";


    /**
     * 发送短信验证码,用sessionToken构建Redis主键,存放在Redis中缓存
     *
     * @param sessionToken
     * @param phone
     * @param codeType
     * @return
     */
    void sendCode(String sessionToken, String phone, String codeType) throws Exception;

    /**
     * 获取短信验证码
     * @param phone     手机号
     * @param codeType  短信类型, 使用SmsService提供的常量
     * @return          短信验证码,如果没有则返回null
     */
    String getSmsCode(String phone, String codeType);

    /**
     * 删除缓存在Redis中短信验证码
     * @param phone     手机号码
     * @param codeType  短信类型
     */
    void deleteSmsCode(String phone, String codeType);
}
