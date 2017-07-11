package com.xmn.saas.controller.api.v1.micro;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.controller.api.v1.micro.vo.MicroCancelRequest;
import com.xmn.saas.controller.api.v1.micro.vo.MicroPayRequest;
import com.xmn.saas.controller.api.v1.micro.vo.MicroQueryRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.micro.MicroService;

/**
 * 主扫反扫支付接口
 * @author zhouxiaojian
 * @Date  2016/11/30
 *
 */
@Controller("api-v1-micro-controller")
@RequestMapping("api/v1/micro")
public class MicroController extends AbstractController{
    
    @Autowired
    private MicroService microService;
    
    @Autowired
    private RedisService redisService;
    
    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(MicroController.class);
    
    @ResponseBody
    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public void pay(@Valid MicroPayRequest request, BindingResult result) throws Exception{
        log.info("【访问扫码支付接口】-【 post /api/v1/micro/pay】参数为："+request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        try {
            SellerAccount sellerAccount =redisService.getSellerAccount(this.getToken());
            Map<String,Object> resultMap = microService.pay(request.converToBean(sellerAccount));
            success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
           failure("访问扫码支付接口失败");
        }
        return ;
        
    }
    
    @ResponseBody
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public void query(@Valid MicroQueryRequest request, BindingResult result) throws Exception{
        log.info("【访问扫码支付查询接口】-【 post /api/v1/micro/query】参数为："+request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        try {
            SellerAccount sellerAccount =redisService.getSellerAccount(this.getToken());
            Map<String,Object> resultMap = microService.query(request.converToBean(sellerAccount));
            success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
           failure("访问扫码支付查询接口失败");
        }
        return ;
        
    }
    
    @ResponseBody
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public void cancel(@Valid MicroCancelRequest request, BindingResult result) throws Exception{
        log.info("【访问扫码支付撤销接口】-【 post /api/v1/micro/cancel】参数为："+request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        try {
            SellerAccount sellerAccount =redisService.getSellerAccount(this.getToken());
            Map<String,Object> resultMap = microService.cancel(request.converToBean(sellerAccount));
            success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
           failure("访问扫码支付撤销接口失败");
        }
        return ;
        
    }
    
    

}
