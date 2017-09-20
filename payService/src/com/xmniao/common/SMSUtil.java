package com.xmniao.common;

import com.alibaba.fastjson.JSON;
import com.xmniao.Template.SMSResultCode;
import com.xmniao.Template.Template;

/**
 * 发送短信验证码
 *
 * @auther LiYuanBo
 * @Create 2017/3/30
 */
public class SMSUtil {

    public final static int CODE_LENGTH_DEFAULT = 6;

    public static SMSResultCode send(String smsURL, Template template) {
        try {
            String result = OKHttp3Helper.post(smsURL + "?p=" + JSON.toJSONString(template));
            return JSON.parseObject(result, SMSResultCode.class);
        } catch (Exception e) {
            System.out.println("-----");
            e.printStackTrace();
        }
        return null;
    }


}
