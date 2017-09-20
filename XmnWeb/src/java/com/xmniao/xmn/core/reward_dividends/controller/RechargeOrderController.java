package com.xmniao.xmn.core.reward_dividends.controller;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.Page;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.controller.RecommendController;
import com.xmniao.xmn.core.reward_dividends.controller.vo.RechargeOrderListRequest;
import com.xmniao.xmn.core.reward_dividends.entity.TReceivingAddress;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationGoodsOrder;
import com.xmniao.xmn.core.reward_dividends.service.RechargeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * V客充值奖励订单 Controller
 * Created by yang.qiang on 2017/5/31.
 */
@RequestMapping("recharge/order")
@Controller
public class RechargeOrderController {
    private final Logger logger = LoggerFactory.getLogger(RecommendController.class);
    @Autowired
    private RechargeOrderService rechargeOrderService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String init(){
        return "reward_dividends/rechargeRewardOrderManager";
    }


    /**
     * 处理SpringMVC Date时间
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    /**
     * V客充值奖励订单-获取订单列表
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Pageable list(RechargeOrderListRequest param, Page page){
        Pageable pageable = new Pageable();
        logger.info("调用[V客充值奖励订单-获取订单列表], 参数:"+ JSON.toJSONString(param));
        try {
            long orderCount = rechargeOrderService.countOrders(param);
            List<TVerExcitationGoodsOrder> orderList = rechargeOrderService.queryOrders(param,page);

            pageable.setContent(orderList);
            pageable.setTotal(orderCount);
            pageable.setPage(page.getPage());
        System.out.println("tset");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageable;

    }

    /**
     * V客充值奖励订单-查询订单的地址
     */
    @RequestMapping(value = "address",method = RequestMethod.GET)
    @ResponseBody
    public Object address(@RequestParam(value = "rid") Integer rid){
        logger.info("调用[V客充值奖励订单-查询订单的地址] cid="+ rid);
        try {
            TReceivingAddress address = rechargeOrderService.queryAddress(rid);
            return JSON.toJSON(address);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * V客充值奖励订单-确认订单
     */
    @RequestMapping(value = "confirm",method = RequestMethod.POST)
    @ResponseBody
    public void confirm(@RequestParam(value = "status") Integer status,
                        @RequestParam(value = "orderId") Integer orderId){
        logger.info("调用[V客充值奖励订单-确认订单] status="+status +"  orderId="+orderId);
        try {
            rechargeOrderService.updateStatus(status,orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
