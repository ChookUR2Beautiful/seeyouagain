package com.xmn.saas.controller.api.v1.wallet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.wallet.WalletService;

@Controller( value = "api-v1-wallet-controller" )
@RequestMapping( "/api/v1/wallet" )
public class WalletController extends AbstractController {

    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletService walletService;

    @Autowired
    private RedisService redisService;


    /**
     * 钱包余额
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/balance" )
    public void balance() throws Exception {
        log.info("【访问钱包余额接口】-【 post /api/v1/wallet/balance】");

        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<Object, Object> map = walletService.balance(sellerAccount);
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

    }


}
