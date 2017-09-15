package com.xmniao.xmn.core.market.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.mongodb.util.Hash;
import com.xmniao.xmn.core.market.base.Page;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.controller.product.vo.ProductAttrRequest;
import com.xmniao.xmn.core.market.controller.product.vo.ProductCollectRequest;
import com.xmniao.xmn.core.market.entity.pay.ProductDetails;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.product.ProductService;
import com.xmniao.xmn.core.util.PropertiesUtil;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joney.Yang on 2016/12/28.
 */

@Controller( "api-v1-market-product-controller" )
@RequestMapping( "/api/v1/market/product" )
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductService productService;

    // 验证数据
    @Autowired
    private Validator validator;
    
    @Autowired
    private PropertiesUtil propertiesUtil;

    /**
     * @name 商品列表接口
     * @description 根据条件查询商品列表(分类,最低/最高金额),默认按销量排序
     * @url /api/v1/market/product/list
     * @method POST
     * @param typeId    required:false  分类ID
     * @param maxMoney  required:false  最大金额
     * @param minMoney  required:false  最低金额
     * @param brandId   required:false  品牌id
     * @param pageSize  required:false  分页页面大小
     * @param pageNum   required:false  分页页数
     * @response
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public void list(@RequestParam(value = "typeId", required = false) Long typeId,
                     @RequestParam(value = "brandId", required = false) Long brandId,
                     @RequestParam(value = "maxMoney",required = false) Double maxMoney,
                     @RequestParam(value = "minMoney",required = false) Double minMoney,
                     Page page,
                     HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[商品列表接口 /api/v1/market/product/list POST]接口, 参数: typeId=" + typeId + "maxMoney=" + maxMoney + "  minMoney=" + minMoney + "  page=" + page);
        try {
//            Long productCount = productService.countProductByCondition(typeId,brandId,maxMoney,minMoney);
            List<ProductInfo> productList = productService.queryProductByCondition(typeId,brandId,maxMoney,minMoney,page);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("productList", productList);
//            resultMap.put("productCount",productCount);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(ProductInfo.class,new String[]{
                        "pid",      // type:int     产品的id
                        "pname",    // type:String  产品的具体名称
                        "price",    // type:double  产品原单品价格
                        "breviary", // type:String  产品列表缩略图
                        "choice",   // type:int     是否精选商品  0不是  1是
                        "integral", // type:int     积分支付金额
                        "cash",     // type:double  现金支付金额
                        "codeid",   // type:int     产品唯一标识编号
                        "store",
                        "labels",   // 商品标签id
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[商品列表接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }

    }

    /**
     * 商品详情
     *
     * @param codeId
     * @param response
     * @throws Exception
     */
    @RequestMapping( value = "/detail" , method = RequestMethod.POST )
    public void detail(@RequestParam( value = "codeId" , required = true ) Long codeId,@RequestParam( value = "sessiontoken" , required = false ) String sessiontoken,
            @RequestParam( value = "activityId" , required = false ) Integer activityId, HttpServletResponse response) throws Exception {
        logger.info("【访问商品详情接口】-【 post /api/v1/market/product/detail】  参数：codeId " + codeId.toString());
        try {
            ProductInfo productInfo = productService.detail(activityId,codeId ,sessiontoken);
            if(productInfo==null){
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("msg", "商品已售罄！");
                resultMap.put("code", 1);
                new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write( response);
                return;
            }else if(productInfo.getStore() ==null ||productInfo.getStore()==0){
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("msg", "商品已售罄！");
                resultMap.put("code", 1);
                new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write( response);
                return;
            }
            //从配置文件中读出包邮价
            String freePostage = propertiesUtil.getValue("freePostage", "conf_integral_pay.properties");
            productInfo.setFreePostage(freePostage);
            new Response(ResponseCode.SUCCESS, "请求成功", productInfo).write(// response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(ProductInfo.class, new String[] { "amount" , "brandname" , "cash" ,
                                    "codeid" , "pname" , "discount" , "stock" , "attrIds" ,"store",
                                    "attrVals" , "num" , "integral" , "breviary" , "choice" ,
                                    "deliveryCity" , "type" , "activityId" , "cartId" ,
                                    "detailInfo" ,"collection","banner","html","htmlType", "freePostage"});

                        }
                    }, response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;


    }

    /**
     * 商品收藏
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping( value = "/collect" , method = RequestMethod.POST )
    public void collect(ProductCollectRequest request,HttpServletResponse response) throws Exception {
        logger.info("【访问商品收藏接口】-【 post /api/v1/market/product/collect】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message = "";
            for (ConstraintViolation vo : result) {
                message += vo.getMessage() + ",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length() - 1))
                    .write(response);
            return;
        }
        try {
            Map<String,Integer> resultMap = productService.collect(request);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write( response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;


    }
    
    /**
     * 商品收藏列表
     * @param sessiontoken
     * @param response
     * @throws Exception
     */
    @RequestMapping( value = "/collect_list"  )
    public void collectList(String sessiontoken,HttpServletResponse response) throws Exception {
        logger.info("【访问商品收藏列表接口】-【 post /api/v1/market/product/collect_list】  参数：" + sessiontoken.toString());
        if(StringUtils.isBlank(sessiontoken)){
            new Response(ResponseCode.FAILURE,"sessiontoken不能为空").write(response);
        }
        try {
            Map<Object, Object> map = productService.collectList(sessiontoken);
            new Response(ResponseCode.SUCCESS, "请求成功", map).write(// response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(ProductInfo.class, new String[] { "amount" , "brandname" , "cash" ,
                                    "codeid" , "pname" , "discount" , "stock" , "attrIds" ,
                                    "attrVals" , "num" , "integral" , "breviary" , "choice" ,
                                    "deliveryCity" , "type" , "activityId" , "cartId" ,
                                    "detailInfo" ,"collection","banner","html"});

                        }
                    }, response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
        
    }
    
    
    /**
     * 商品浏览记录列表
     * @param sessiontoken
     * @param response
     * @throws Exception
     */
    @RequestMapping( value = "/history_list"  )
    public void historyList(String sessiontoken,HttpServletResponse response) throws Exception {
        logger.info("【访问商品浏览记录列表接口】-【 post /api/v1/market/product/history_list】  参数：" + sessiontoken.toString());
        if(StringUtils.isBlank(sessiontoken)){
            new Response(ResponseCode.FAILURE,"sessiontoken不能为空").write(response);
        }
        try {
            List<ProductInfo> productInfoList = productService.historyList(sessiontoken);
            Map<String,Object> map  =new HashMap<>();
            map.put("dataList", productInfoList);
            new Response(ResponseCode.SUCCESS, "请求成功", map).write(// response);
                    new HashMap<Class<?>, String[]>() {
                        {
                            put(ProductInfo.class, new String[] { "amount" , "brandname" , "cash" ,
                                    "codeid" , "pname" , "discount" , "stock" , "attrIds" ,"store",
                                    "attrVals" , "num" , "integral" , "breviary" , "choice" ,
                                    "deliveryCity" , "type" , "activityId" , "cartId" ,
                                    "detailInfo" ,"collection"});

                        }
                    }, response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
        
    }

    /**
     * 商品规格接口
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping( value = "/attr" , method = RequestMethod.POST )
    public void attr(ProductAttrRequest request, HttpServletResponse response) throws Exception {
        logger.info("【访问商品规格接口】-【 post /api/v1/market/product/attr】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message = "";
            for (ConstraintViolation vo : result) {
                message += vo.getMessage() + ",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length() - 1))
                    .write(response);
            return;
        }
        try {
            Map<Object, Object> resultMap = productService.attr(request);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;

    }
    
    /**
     * 如何获取积分。
     * @param response
     * @throws Exception
     */
    @RequestMapping( value = "/integral_text" )
    public void text( HttpServletResponse response) throws Exception {
        try {
            String text = productService.text();
            Map<Object, Object> resultMap =new HashMap<>();
            resultMap.put("text", text);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
        
    }



}
