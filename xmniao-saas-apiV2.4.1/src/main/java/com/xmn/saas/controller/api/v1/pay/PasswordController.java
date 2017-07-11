package com.xmn.saas.controller.api.v1.pay;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.pay.vo.PayCheckPayPasswdRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.pay.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * create 2016/10/26
 *
 * @author yangQiang
 */
@Controller("api-v1-pay-controller")
@RequestMapping("api/v1/pay")
public class PasswordController extends AbstractController{

    @Autowired
    private RedisService redisService;
    @Autowired
    private PayService payService;

    // 初始化日志信息类
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 校验支付密码,输入错误3次 "冻结" 该商户.
     */
    @ResponseBody
    @RequestMapping(value = "check_pay_passwd",method = RequestMethod.POST)
    public void checkPayPasswd(@Valid PayCheckPayPasswdRequest request, BindingResult result) throws Exception {
        if (!request.doValidate(result)) {
            return;
        }

        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        String payPasswd = request.getPayPasswd();

        Map<String,Object> map;
        try {
            map = payService.checkPayPasswd(payPasswd,sellerAccount);
        } catch (Exception e) {
            logger.error("调用校验支付密码接口出现异常:",e);
            failed();
            return;
        }

        new Response(ResponseCode.SUCCESS,"请求成功!",map).write();

    }


    private void failed() throws IOException {
        new Response(ResponseCode.FAILURE,"请求失败").write();
    }
}
