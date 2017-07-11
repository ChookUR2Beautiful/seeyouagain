package com.xmn.saas.controller.api.v1.bill;

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
import com.xmn.saas.controller.api.v1.bill.vo.StatisticalGetByRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.bill.StatisticalService;

@Controller( value = "api-v1-bill-statistical-controller" )
@RequestMapping( "/api/v1/bill/statistical" )
public class StatisticalController extends AbstractController {
    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(StatisticalController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private StatisticalService statisticalService;



    @ResponseBody
    @RequestMapping( value = "/get_by" )
    public void get_by(@Valid StatisticalGetByRequest request, BindingResult result)
            throws Exception {
        log.info("【访问根据条件查询账单金额统计信息接口】-【 post /api/v1/bill/statistical/get_by】");
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<String, Object> map =
                statisticalService.get_by(request.converToBean(sellerAccount, request),
                        sellerAccount.getType());
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

    }

    @ResponseBody
    @RequestMapping( value = "/amount" )
    public void amount() throws Exception {
        log.info("【访问账单金额统计接口】-【 post /api/v1/bill/statistical/amount】");
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<String, Object> map = statisticalService.amount(sellerAccount);
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

    }
}
