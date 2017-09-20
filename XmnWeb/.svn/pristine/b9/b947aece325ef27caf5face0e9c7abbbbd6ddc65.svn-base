package com.xmniao.xmn.core.coupon.controller;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.Page;
import com.xmniao.xmn.core.coupon.controller.vo.CouponDetailParams;
import com.xmniao.xmn.core.coupon.service.CouponDetailService;
import com.xmniao.xmn.core.exception.CustomException;
import com.xmniao.xmn.core.manor.entity.HttpResult;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 已发送优惠劵管理
 * Created by yang.qiang on 2017/8/11.
 */
@Controller
@RequestMapping(value = "/coupon/detail")
public class CouponDetailController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CouponDetailService couponDetailService;

    @RequestMapping("init")
    public String init(){
        return "coupon/detail/init";
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public HttpResult couponList(CouponDetailParams params, Page page){
        logger.info("调用[优惠劵-已发送优惠劵-优惠劵列表] 参数: "+ JSON.toJSONString(params));
        HashMap<String, Object> context = new HashMap<>();

        // 查询优惠劵列表
        List<Map<String,Object>> couponDetailList = couponDetailService.queryCouponDetailList(params,page);
        context.put("couponList",couponDetailList);

        return new HttpResult(0,"请求成功!",context);
    }

    @RequestMapping(value = "count")
    @ResponseBody
    public HttpResult count(CouponDetailParams params){
        logger.info("调用[优惠劵-已发送优惠劵-统计优惠劵数量] 参数: "+ JSON.toJSONString(params));

        long couponCount = couponDetailService.countCouponDetail(params);
        HashMap<String, Object> context = new HashMap<>();
        context.put("count",couponCount);
        return new HttpResult(0,"请求成功!",context);

    }

    @RequestMapping(value = "deleteCoupon")
    @ResponseBody
    public HttpResult deleteCoupon(@Param(value = "cdid") Long cdid){
        logger.info("删除优惠劵:" +cdid);
        try {
            couponDetailService.delectCoupon(cdid);
        } catch (CustomException e) {
            return new HttpResult(e.getCode(),e.getMessage());
        }
        return new HttpResult(0,"请求成功!");
    }

}
