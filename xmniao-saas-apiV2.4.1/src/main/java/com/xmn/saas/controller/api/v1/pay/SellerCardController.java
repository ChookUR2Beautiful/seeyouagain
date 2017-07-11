package com.xmn.saas.controller.api.v1.pay;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.pay.vo.PayCheckPayPasswdRequest;
import com.xmn.saas.controller.api.v1.pay.vo.SellerCardCodeRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.pay.PayService;
import com.xmn.saas.service.sellercard.SellerCardService;

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
 * 2017/03/02
 * @author zhouxiaojian
 *
 */
@Controller("api-v1-sellercard-controller")
@RequestMapping("api/v1/sellercard")
public class SellerCardController extends AbstractController{
    
    // 初始化日志信息类
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;
    
    @Autowired
    private SellerCardService sellerCardService;
    


    /**
     * 储值卡扫码
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public void code(@Valid SellerCardCodeRequest request, BindingResult result) throws Exception {
        if (!request.doValidate(result)) {
            return;
        }

        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());

        Map<String,Object> map;
        try {
            map = sellerCardService.code(sellerAccount.getSellerid());
        } catch (Exception e) {
            log.error("调用储值卡扫码接口出现异常:",e);
            failure();
            return;
        }

        new Response(ResponseCode.SUCCESS,"请求成功!",map).write();

    }
}
