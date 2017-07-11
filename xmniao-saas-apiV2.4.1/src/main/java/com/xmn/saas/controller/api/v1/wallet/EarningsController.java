package com.xmn.saas.controller.api.v1.wallet;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.wallet.EarningsService;


/**
 * 收益控制类
 * 
 * @author zhouxiaojian
 * @Date 2016-10-21
 *
 */
@Controller( value = "api-v1-wallet-earnings-controller" )
@RequestMapping( "/api/v1/wallet/earnings" )
public class EarningsController extends AbstractController {

    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(EarningsController.class);


    @Autowired
    private RedisService redisService;
    
    @Autowired
    private EarningsService earningsService;


    /**
     * 收益接口
     * 
     * @param type
     * @throws IOException 
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/get_by" )
    public void getBy(@RequestParam Integer type) throws IOException {
        log.info("【访问收益接口】-【 post /api/v1/wallet/earnings/get_by】");
        try {
            if (type == null) {
                throw new SaasException("用户类型不能为空！");
            }
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
            sellerAccount.setType(type);
            Map<Object, Object> resultMap = earningsService.getBy(sellerAccount);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write();

        } catch (SaasException e) {
            new Response(ResponseCode.FAILURE, e.getMessage());
        }

        return;

    }



}
