package com.xmn.saas.service.sms.impl;

import com.alibaba.fastjson.JSON;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.constants.SaasConsts;
import com.xmn.saas.dao.common.SellerAccountDao;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.sms.SmsService;
import com.xmn.saas.utils.NumberComputeUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 短信服务实现类
 * create 2016/09/27
 *
 * @author yangQiang
 */

@Service("smsService")
public class SmsServiceImpl implements SmsService {

    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    // 商户账户Dao
    @Autowired
    private SellerAccountDao sellerAccountDao;

    // 注入全局配置常量
    @Autowired
    private GlobalConfig globalConfig;

    // 注入Redis服务类,用于操作Redis存取
    @Autowired
    private RedisService redisService;

    /**
     * 组装RedisKey 最终存入Redis中的格式如:  "saas:api:sms:18012312300:1"
     *
     * @param prefix 会话令牌
     * @param type
     * @return
     */
    private static String buildRedisKey(String prefix, String type) {
        return SMS_REDIS_PREFIX + ":" + prefix + ":" + type;
    }


    @Override
    public void sendCode(String sessionToken, String phone, String codeType) throws Exception {
        String type;    // 用于在Redis中区分验证码类型
        String code;    // 验证码
        String content; // 短信内容

        // 判断验证类型,并填充短信内容参数
        switch (codeType) {
            // 判断验证码类型是否为 忘记登陆密码
            case SmsService.CODE_TYPE_LOGIN_PASSWORD:

                // 判断手机号码是否存在
                int count = sellerAccountDao.countAccountByAccount(phone);
                if (count < 1){
                    throw new SaasException("没有"+phone+"这个账号,请重新输入!", ResponseCode.FAILURE);
                }

                type = CODE_TYPE_LOGIN_PASSWORD;
                code = NumberComputeUtil.getRandomNumberStringOfSize(6);    // 生成6为随机数字符串
                // 判断短信模板是否注入成功
                content = globalConfig.getSmsLoginTemplate().replace("{code}",code);

                break;
            // 判断验证码类型是否为 忘记支付密码
            case SmsService.CODE_TYPE_PAY_PASSWORD:
                //  判断手机号码是否为登陆的账号
                if(!checkPhone(sessionToken,phone)) {
                    throw new SaasException("手机号输入错误!", ResponseCode.PARAM_ERROR);
                }

                type = SmsService.CODE_TYPE_PAY_PASSWORD;
                code = NumberComputeUtil.getRandomNumberStringOfSize(6);    // 生成6位随机数 字符串
                // 判断短信模板是否注入成功
                content = globalConfig.getSmsPayTemplate().replace("{code}",code);
                break;

            // 判断验证码类型是否为 忘记手势密码
            case SmsService.CODE_TYPE_GESTURE_PASSWORD:
                //  判断手机号码是否为登陆的账号
                if(!checkPhone(sessionToken,phone)) {
                    throw new SaasException("手机号输入错误!", ResponseCode.PARAM_ERROR);
                }

                type = SmsService.CODE_TYPE_GESTURE_PASSWORD;
                code = NumberComputeUtil.getRandomNumberStringOfSize(6);    // 生成6为随机数字符串
                // 判断短信模板是否注入成功
                content = globalConfig.getSmsGestureTemplate().replace("{code}",code);
                break;

            // 判断验证码类型是否为 绑定银行卡
            case SmsService.CODE_TYPE_BINDING_BANK_CARD:
                type = SmsService.CODE_TYPE_BINDING_BANK_CARD;
                code = NumberComputeUtil.getRandomNumberStringOfSize(6);    // 生成6为随机数字符串
                // 判断短信模板是否注入成功
                content = globalConfig.getBindingBankCard().replace("{code}",code);
                break;
            default:
                throw new SaasException("验证码类型(codeType)有误,不能是:" + codeType, ResponseCode.PARAM_ERROR);
        }

        // 发送短信
        List<String> phoneList = new ArrayList<>();
        phoneList.add(phone);
        sendMessage(phoneList, content);

        // 将验证码存入Redis中, 有效时间为 5 分钟
        // 构建redisKey
        String redisKey = buildRedisKey(phone, type);
        // 将数据存入Redis中
        Calendar calendar = Calendar.getInstance();

        // 从配置文件获取缓存时间
        calendar.add(Calendar.MINUTE, globalConfig.getReidsTimeVerifyCode());
        // 在Redis中存入验证码, 每种验证码都会独立存储
        redisService.setString(redisKey, code, calendar.getTime());

    }

    /**
     * 判断输入的手机号码, 是否匹配登陆账号的手机号
     * @param loggedAccount
     * @param phone
     * @return
     */
    private boolean checkPhone(String sessionToken, String phone) throws SaasException {
        // 检测sessionToken是否传入
        if (StringUtils.isEmpty(sessionToken)) {
            throw new SaasException("必须传入sessionToken", ResponseCode.PARAM_ERROR);
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(sessionToken);
        return sellerAccount.getAccount().equals(phone);
    }


    @Override
    public String getSmsCode(String phone, String codeType) {
        String redisKey = buildRedisKey(phone, codeType);
        String value = redisService.getString(redisKey);
        return value;
    }

    @Override
    public void deleteSmsCode(String phone, String codeType) {
        String redisKey = buildRedisKey(phone, codeType);
        redisService.deleteKey(redisKey);
    }

    /**
     * 发送短信功能
     *
     * @param phoneList 手机号码
     * @param content   短信内容
     */
    public void sendMessage(List<String> phoneList, String content) throws Exception {

        // 封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("appid", SaasConsts.APP_ID);
        params.put("text",content);
        params.put("phones",phoneList);
        String p = JSON.toJSONString(params);

        // 发送请求,返回响应
        Document response = Jsoup.connect(globalConfig.getSmsServiceSendUrl())
                .data("p",p)            // 添加参数p
                .ignoreContentType(true)//
                .post();                // 发送POST请求

        // 获取响应结果Json; 正常返回值:{"state":100,"info":"短信发送成功"}
        String resultJson = response.body().text();

        // 解析响应接口判断状态码
        Map<String,Object> map = JSON.parseObject(resultJson, HashMap.class);
        Object state = map.get("state");
        // 如果返回的状态码不是100,或没有状态信息
        if (state==null || !"100".equals(state.toString())){
            logger.error("调用短信接口出错!短信接口返回值【"+resultJson+"】");
            throw new SaasException("调用短信接口出错!",ResponseCode.FAILURE);
        }
    }

}
