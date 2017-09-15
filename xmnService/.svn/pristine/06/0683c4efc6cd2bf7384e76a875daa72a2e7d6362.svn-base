package com.xmniao.xmn.core.market.controller.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.controller.cart.vo.CartAddRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartDeleteRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartEditAttrRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartListRequest;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.cart.CartService;



/**
 * 购物车增加商品
 * 
 * @author zhouxiaojian
 *
 */
@Controller("api-v1-cart-controller")
@RequestMapping( "/api/v1/market/cart" )
public class CartController {

    // 日志
    private final Logger log = Logger.getLogger(CartController.class);

    // 验证数据
    @Autowired
    private Validator validator;

    @Autowired
    private CartService cartService;


    @RequestMapping( value = "/add" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    @ResponseBody
    public void add(CartAddRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问购物车增加接口】-【 post /api/v1/market/cart/add】  参数：" + request.toString());
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
            Map<Object, Object> resultMap = cartService.add(request);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(//response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(ProductInfo.class, new String[] { "amount" , "brandname" , "cash" ,
                                    "codeid" , "pname","discount","stock","attrIds","attrVals", "num","integral","breviary","choice","deliveryCity" ,"type","activityId","cartId"});

                        }
                    }, response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    
    
    @RequestMapping( value = "/delete" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    @ResponseBody
    public void delete(CartDeleteRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问购物车删除接口】-【 post /api/v1/market/cart/delete】  参数：" + request.toString());
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
            Map<Object, Object> resultMap = cartService.delete(request);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(//response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(ProductInfo.class, new String[] { "amount" , "brandname" , "cash" ,
                                    "codeid" , "pname","discount","stock","attrIds","attrVals", "num","integral","breviary","choice","deliveryCity","type","activityId","cartId" });

                        }
                    }, response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    
    @RequestMapping( value = "/list" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    @ResponseBody
    public void list(CartListRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问购物车列表接口】-【 post /api/v1/market/cart/list】  参数：" + request.toString());
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
            Map<Object, Object> resultMap = cartService.list(request);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(//response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(ProductInfo.class, new String[] { "amount" , "brandname" , "cash" ,
                                    "codeid" , "pname","discount","stock","attrIds","attrVals", "num","integral","breviary","choice","deliveryCity","type" ,"activityId","cartId","expweight", "freePostage"});

                        }
                    }, response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    
    @ResponseBody
    @RequestMapping( value = "/cart_count" ,produces = { "application/json;charset=UTF-8" } )
    public void cartCount(CartListRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问购物车商品数量接口】-【 post /api/v1/market/cart/cart_count】  参数：" + request.toString());
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
            Map<Object, Object> resultMap = cartService.list(request);
            List<ProductInfo> invalidList = (List<ProductInfo>) resultMap.get("invalidList");
            List<ProductInfo> validList  = (List<ProductInfo>)resultMap.get("validList");
            Map<Object, Object> map  =new HashMap<>();
            Integer count= 0;
            if(invalidList!=null){
                count +=invalidList.size();
            }
            if(validList!=null){
                count +=validList.size();
            }
            map.put("count", count);
            new Response(ResponseCode.SUCCESS, "请求成功", map).write(response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    
    
    @RequestMapping( value = "/edit_attr" , method = RequestMethod.POST ,
            produces = { "application/json;charset=UTF-8" } )
    @ResponseBody
    public void editAttr(CartEditAttrRequest request, HttpServletResponse response) throws Exception {
        log.info("【访问购物车编辑规格接口】-【 post /api/v1/market/cart/edit_attr】  参数：" + request.toString());
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
            Map<Object, Object> resultMap = cartService.editAttr(request);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }


}
