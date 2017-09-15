package com.xmniao.xmn.core.market.controller.activity;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.controller.activity.vo.*;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionBidding;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.activity.AuctionService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller( "api-v1-market-auction-controller" )
@RequestMapping( "/api/v1/market/activity/auction" )
public class AuctionController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private AuctionService auctionService;
    
    @Autowired
    private SessionTokenService sessionTokenService;
    
    // 验证数据
    @Autowired
    private Validator validator;
    
    /**
     * 竞拍规则
     * @param response
     * @throws Exception
     */
    @RequestMapping( value = "/auction_text" )
    public void text(AuctionTextRequest request, HttpServletResponse response) throws Exception {
        try {
            String text = auctionService.text(request.getType());
            Map<Object, Object> resultMap =new HashMap<>();
            resultMap.put("text", text);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
        
    }

    /**
     * 竞拍列表
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/list" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    public void list(AuctionListRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问竞拍列表接口】-【 post /api/v1/market/activity/auction/list】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message ="";
            for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length()-1)).write(response);
            return;
        }
        try {
            Map<String, Object> resultMap = auctionService.list(request.converToBean());
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(//response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(FreshActivityAuction.class, new String[] { 
                                "id",
                                "basePrice",
                                "codeid",
                                "endTime",
                                "insurancePrice",
                                "productStatus",
                                "pvIds",
                                "pvValue",
                                "scope",
                                "startPrice",
                                "state",
                                "nowPrice",
                                "breviary",
                                "title"
                            });

                        }
                    }, response);
        } catch (CustomException e) { 
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    /**
     * 竞拍记录
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/bid_record" , method = RequestMethod.POST ,
    produces = { "application/json;charset=UTF-8" } )
    public void record(AuctionRecordRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问竞拍记录接口】-【 post /api/v1/market/activity/auction/record】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message ="";
            for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length()-1)).write(response);
            return;
        }
        try {
            String uid =
                    ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
            Map<String, Object> resultMap = auctionService.record(request.converToBean(Integer.valueOf(uid)));
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
//                    new HashMap<Class<?>, String[]>() {
//                        {
//                            put(FreshActivityAuction.class, new String[] { 
//                                "id",
//                                "basePrice",
//                                "codeid",
//                                "endTime",
//                                "insurancePrice",
//                                "productStatus",
//                                "pvIds",
//                                "pvValue",
//                                "scope",
//                                "startPrice",
//                                "state",
//                                "nowPrice",
//                                "breviary",
//                                "title"
//                            });
//                            
//                        }
//                    }, response);
        } catch (CustomException e) { 
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    
    /**
     * 竞拍详情
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/detail" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    public void detail(AuctionDetailRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问竞拍详情接口】-【 post /api/v1/market/activity/auction/detail】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message ="";
            for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length()-1)).write(response);
            return;
        }
        try {
            String uid =
                    ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
            Map<String, Object> resultMap = auctionService.detail(request.getId(),uid);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(//response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(FreshActivityAuction.class, new String[] { 
                                "id",
                                "basePrice",
                                "codeid",
                                "endTime",
                                "insurancePrice",
                                "productStatus",
                                "pvIds",
                                "pvValue",
                                "scope",
                                "startPrice",
                                "state",
                                "title",
                                "bannerList",
                                "nowPrice",
                                "myPrice",
                                "html"
                            });

                        }
                    }, response);
        } catch (CustomException e) { 
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    /**
     * 竞拍
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/bid" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    public void list(AuctionBidRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问竞拍接口】-【 post /api/v1/market/activity/auction/bid】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message ="";
            for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length()-1)).write(response);
            return;
        }
        try {
            String uid =
                    ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
            Map<String, Object> resultMap = auctionService.bid(request.converToBean(Integer.valueOf(uid)));
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) { 
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    
    /**
     * 竞拍支付尾款
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/bid_pay" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    public void pay(AuctionPayRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问竞拍支付尾款接口】-【 post /api/v1/market/activity/auction/bid_pay】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message ="";
            for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length()-1)).write(response);
            return;
        }
        try {
            String uid =
                    ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
            Map<String, Object> resultMap = auctionService.pay(request.converToBean(Integer.valueOf(uid)));
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) { 
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    /**
     * 竞拍排名接口
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/bid_list" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    public void list(AuctionBidListRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问竞拍排名】-【 post /api/v1/market/activity/auction/bid_list】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message ="";
            for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length()-1)).write(response);
            return;
        }
        try {
            String uid =
                    ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
            Map<String, Object> resultMap = auctionService.bidList(uid,request.getActivityId());
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(//response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(FreshActivityAuctionBidding.class, new String[] { 
                                "createTime",
                                "risePrice",
                                "name",
                                "uid",
                                "utype"
                            });

                        }
                    }, "yyyy.MM.dd HH:mm:ss", response);
        } catch (CustomException e) { 
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    /**
     * 竞拍轮播图
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/banner" , method = RequestMethod.POST ,
    produces = { "application/json;charset=UTF-8" } )
    public void list(HttpServletResponse response) throws Exception {
        log.info("【访问竞拍轮播图】-【 post /api/v1/market/activity/auction/banner】 " );
        try {
            Map<String, Object> resultMap = auctionService.banner();
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) { 
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }


    /**
     * @name        保证金金额
     * @description 获取竞拍保证金金额
     * @url         /api/v1/market/activity/auction/price
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "price",method = RequestMethod.POST)
    public void price(HttpServletResponse httpResponse) throws Exception {


        log.info("调用[保证金金额 /api/v1/market/activity/auction/price POST]接口,");
        try {
            BigDecimal price = auctionService.queryAuctionPirce();
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("price", price);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
//                new HashMap<Class<?>, String[]>() {{
//                    put(. class,new String[]{
//                    });
//                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            log.error("调用[保证金金额]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }


}
