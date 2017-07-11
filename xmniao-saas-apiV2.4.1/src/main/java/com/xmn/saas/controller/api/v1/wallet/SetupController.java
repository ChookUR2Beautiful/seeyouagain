package com.xmn.saas.controller.api.v1.wallet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.wallet.vo.SetupAutoWithdrawalRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.wallet.SetupService;

@Controller( value = "api-v1-wallet-setup-controller" )
@RequestMapping( "/api/v1/wallet/setup" )
public class SetupController extends AbstractController {

    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(SetupController.class);

    @Autowired
    private SetupService setupService;

    @Autowired
    private RedisService redisService;
    
    /**
     * 钱包管理（设置自动分账）
     * @param ledger
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/auto_ledger" )
    public void autoLedger(@RequestParam Integer ledger) throws Exception {
        log.info("【钱包管理（设置自动分账）接口】-【post /wallet/setup/auto_ledger】 ");
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<String,Object> resultMap = setupService.autoLedger(ledger, sellerAccount.getSellerid());
        Response response = new Response(ResponseCode.SUCCESS, "请求成功",resultMap);
        response.write();
        return;

    }
    
    
    /**
     * 钱包管理（设置自动提现）
     * @param ledger
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/auto_withdrawal" )
    public void auto_withdrawal(SetupAutoWithdrawalRequest request, BindingResult result) throws Exception {
        log.info("【钱包管理（设置自动提现）接口】-【post /wallet/setup/auto_withdrawal】  参数为："+request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<String,Object> resultMap = setupService.autoWithdrawal(request.getMention(), sellerAccount.getSellerid(),request.getMoney(),request.getCmAmount());
        Response response = new Response(ResponseCode.SUCCESS, "请求成功",resultMap);
        response.write();
        return;

    }


}
