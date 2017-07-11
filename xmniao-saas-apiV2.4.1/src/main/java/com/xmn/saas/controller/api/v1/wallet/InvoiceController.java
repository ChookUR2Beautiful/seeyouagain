package com.xmn.saas.controller.api.v1.wallet;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.wallet.vo.InvoiceApplyRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.wallet.InvoiceService;

@Controller( value = "api-v1-wallet-invoice-controller" )
@RequestMapping( "/api/v1/wallet/invoice" )
public class InvoiceController extends AbstractController {

    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private RedisService redisService;


    @ResponseBody
    @RequestMapping( value = "/apply" )
    public void apply(@Valid InvoiceApplyRequest request, BindingResult result) throws Exception {
        log.info("【访问申请发票接口】-【 post /api/v1/wallet/invoice/apply】，参数 : " + request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        boolean flag = invoiceService.apply(request.converToBean(request),sellerAccount);
        Map<String,Object> resultMap =new HashMap<>();
        
        if (flag) {
            resultMap.put("code", 0);
            resultMap.put("msg", "申请成功");
            Response response = new Response(ResponseCode.SUCCESS, "请求成功",resultMap);
            response.write();
            return;
        } else {
            resultMap.put("code", 1);
            resultMap.put("msg", "申请失败");
            Response response = new Response(ResponseCode.FAILURE, "请求失败");
            response.write();
            return;
        }

    }


}
