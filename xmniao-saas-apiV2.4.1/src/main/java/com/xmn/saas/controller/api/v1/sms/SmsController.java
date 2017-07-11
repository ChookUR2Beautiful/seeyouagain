package com.xmn.saas.controller.api.v1.sms;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.sms.vo.SendCodeRequest;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.sms.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller("api-v1-sms-controller")
@RequestMapping("/api/v1/sms")
public class SmsController extends AbstractController {
    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisService redisService;

    /**
     * 发送短信验证码接口
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "send_code",method = RequestMethod.POST)
    public void sendCode(@Valid SendCodeRequest request, BindingResult result) throws Exception {
        logger.info("[调用发送短信验证码接口] - [post /api/v1/sms/send_code] 参数:" + request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        try {
            smsService.sendCode(this.getToken(),request.getPhone(), request.getCodeType());
            new Response(ResponseCode.SUCCESS, "短信发送成功!").write();
            return;
        }catch (SaasException e){
            new Response(e.getCode(),e.getMessage()).write();
            return;
        } catch (Exception e) {
            logger.error("调用发送短信接口出现错误!",e);
            new Response(ResponseCode.FAILURE,"操作失败!").write();
            return;
        }
    }

}
