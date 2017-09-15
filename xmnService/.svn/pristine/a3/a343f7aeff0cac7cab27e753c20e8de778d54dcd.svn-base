package com.xmniao.xmn.core.market.controller.activity;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.OrderProcessInfoRequest;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.controller.activity.vo.OrderConfirmAddressRequest;
import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.activity.OrderService;
import com.xmniao.xmn.core.order.service.FreshOrderProcessService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 活动订单Controller
 * Created by yang.qiang on 2017/2/27.
 */
@RequestMapping("/api/v1/market/activity/order")
@Controller("api-v1-market-order-controller")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderService orderService;
    @Autowired
    private SessionTokenService sessionTokenService;
    @Autowired
    private FreshOrderProcessService freshOrderProcessService;
    // 验证数据
    @Autowired
    private Validator validator;
    
    /**
     * @name        4.14.1 活动订单列表
     * @description 活动用户订单列表接口
     * @url         /api/v1/market/activity/order/list
     * @method      POST
     * @param       page        分页页数
     * @param       pageSize    分页页面大小
     * @param       sessiontoken    会话令牌
     * @param       activityType    可不填,活动类型 01:一元夺宝  02:竞拍活动
     */
    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public void list(@RequestParam("sessiontoken") String sessiontoken, @RequestParam(value = "activityType",required = false) Integer activityType, Page page, HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[4.14.1 活动订单列表 /api/v1/market/activity/order/list POST]接口, 参数: sessiontoken=" + sessiontoken);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            List<BillFreshActivity> activityList = orderService.getOrderList(uid,activityType,page);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("activityList", activityList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(BillFreshActivity.class, new String[]{
                        "id",                   //type:String       订单编号
                        "activityType",         //type:Integer      活动类型 01:一元夺宝  02:竞拍活动
                        "activityId",           //type:Long         活动编号
                        "productCodeId",        //type:String       商品编号(codeId)(codeId)
                        "productName",          //type:String       商品名
                        "productNum",           //type:Integer      商品数量
                        "productBreviary",      //type:String       商品缩略图
                        "productPvIds",         //type:String       商品属性值id组合，无序的，","作分隔符
                        "productPvValue",       //type:String       商品规格值用","作分隔符
                        "auctionDeposit",       //type:BigDecimal   竞拍活动-保证金
                        "auctionBalance",       //type:BigDecimal   竞拍活动-尾款
                        "payTime",              //type:Date         支付时间
                        "receivingConfirm",     //type:Integer      确认收货信息  01未确认  02已确认
                        "state",                //type:Integer      订单状态 01待付款 02待收货 03已发货 04已完成 05已关闭
                        "userId",               //type:String       用户id
                        "createTime",           //type:Date         下单时间
                    });
                }},"yyyy-MM-dd HH:mm:ss",
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.1 活动订单列表]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }


    /**
     * @name        4.14.2 活动订单详情
     * @description 订单详情接口
     * @url         /api/v1/market/activity/order/detail
     * @method      POST
     * @param       sessiontoken    会话令牌
     * @param       orderId         订单id,从[4.14.1 活动订单列表]获取
     */
    @ResponseBody
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    public void detail(@RequestParam("sessiontoken") String sessiontoken, @RequestParam("orderId") String orderId,HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[4.14.2 活动订单详情 /api/v1/market/activity/order/detail POST]接口, 参数: orderId=" + orderId + " sessiontoken=" + sessiontoken);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            BillFreshActivity activity = orderService.getDetail(orderId,uid);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("activity", activity);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(BillFreshActivity.class, new String[]{
                        "id",                   //type:String       订单编号
                        "activityType",         //type:Integer      活动类型 01:一元夺宝  02:竞拍活动
                        "activityId",           //type:Long         活动编号
                        "productCodeId",        //type:String       商品编号(codeId)(codeId)
                        "productName",          //type:String       商品名
                        "productNum",           //type:Integer      商品数量
                        "productBreviary",      //type:String       商品缩略图
                        "productPvIds",         //type:String       商品属性值id组合，无序的，","作分隔符
                        "productPvValue",       //type:String       商品规格值用","作分隔符
                        "auctionDeposit",       //type:BigDecimal   竞拍活动-保证金
                        "auctionBalance",       //type:BigDecimal   竞拍活动-尾款
                        "payTime",              //type:Date         支付时间
                        "receivingName",        //type:String       收货人名字
                        "receivingPhone",       //type:String       收货人号码
                        "receivingCity",        //type:String       收货城市
                        "receivingAddress",     //type:String       收货地址
                        "receivingConfirm",     //type:Integer      确认收货信息  01未确认  02已确认
                        "state",                //type:Integer      订单状态 01待付款 02待收货 03已发货 04已完成 05已关闭
                        "logisticsType",        //type:String       物流公司
                        "logisticsNum",         //type:String       物流编号
                        "createTime",           //type:Date         下单时间
                        "updateTime",           //type:Date         更新时间
                        "logisticsRemind",      //type:Integer      是否提醒发货: 0-未提醒  1-已提醒
                    });
                }},"yyyy-MM-dd HH:mm:ss",
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.2 活动订单详情]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name        4.14.3 确认收货地址
     * @description 用户确认收货信息
     * @url         /api/v1/market/activity/order/confirm_address
     * @method      POST
     * @param       sessiontoken        会话令牌
     * @param       receivingName       收货人姓名
     * @param       receivingPhone      收货人手机
     * @param       receivingCity       收货城市
     * @param       receivingAddress    收货地址
     * @param       orderId             订单id
     */
    @ResponseBody
    @RequestMapping(value = "confirm_address",method = RequestMethod.POST)
    public void confirmAddress(OrderConfirmAddressRequest request,HttpServletResponse httpResponse) throws Exception {


        logger.info("调用[4.14.3 确认收货地址 /api/v1/market/activity/order/confirm_address POST]接口, 参数: request=" + request);
        try {
            // 校验数据
            List<ConstraintViolation> result = validator.validate(request);
            if (result != null && !result.isEmpty()) {
                String message ="";
                for(ConstraintViolation vo : result){
                    message+=vo.getMessage()+",";
                }
                new Response(ResponseCode.FAILURE, message.substring(0, message.length()-1)).write(httpResponse);
                return;
            }

            // 提交收货信息
            String uid = sessionTokenService.getStringForValue(request.getSessiontoken()).toString();
            BillFreshActivity order = request.convertToBillFreshActivity();
            orderService.saveAddress(uid,order);
            new Response(ResponseCode.SUCCESS, "请求成功").write(httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.3 确认收货地址]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }

    }

    /**
     * @name        4.14.4 订单提醒接口
     * @description 提醒用户有待处理的订单
     * @url         /api/v1/market/activity/order/remind
     * @method      POST
     * @param       sessiontoken    会话令牌
     * @param       orderType       活动类型 01:一元夺宝  02:竞拍活动
     */
    @ResponseBody
    @RequestMapping(value = "remind",method = RequestMethod.POST)
    public void remind(@RequestParam("sessiontoken") String sessiontoken, @RequestParam(value = "orderType",required = false) Integer orderType, HttpServletResponse httpResponse) throws Exception {

        logger.info("调用[4.14.4 订单提醒接口 /api/v1/market/activity/order/remind POST]接口, 参数: orderType=" + orderType + " sessiontoken="+sessiontoken);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            String message = null;
            List<BillFreshActivity> orders;
            int count = orderService.countAliveOrder(uid);

            // 统计未支付尾款的竞拍订单
            if (orderType !=null && orderType == 2) {
                orders = orderService.getUnliquidatedOrders(uid);
                if (orders.size()>0) {
                    message = "你有"+orders.size()+"个竞拍商品待支付尾款";
                }

            }else if (orderType !=null && orderType == 1){
                // 统计未提交收货信息的订单
                orders = orderService.getUnSubmitAddressOrders(uid,orderType);
                if (orders.size() >0) {
                    message = "你有"+orders.size()+"个夺宝奖品待提交收货信息";
                }
            }

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("aliveOrderCount",count);
            resultMap.put("message", message);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.4 订单提醒接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name        4.14.5 默认收货地址
     * @description 获取用户的默认收货地址信息
     * @url         /api/v1/market/activity/order/default_address
     * @method      POST
     * @param       sessiontoken    会话令牌
     */
    @ResponseBody
    @RequestMapping(value = "default_address",method = RequestMethod.POST)
    public void defaultAddress(@RequestParam("sessiontoken") String sessiontoken, HttpServletResponse httpResponse) throws Exception {


        logger.info("调用[4.14.5 默认收货地址 /api/v1/market/activity/order/default_address POST]接口, 参数: sessiontoken=" + sessiontoken);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            ReceivingAddress defaultAddress = orderService.queryDefaultAddress(uid);


            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("defaultAddress", defaultAddress);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(ReceivingAddress.class, new String[]{
                        "address",      //type:String       详细地址
                        "areaname",     //type:String       区名称
                        "city",         //type:String       市名称
                        "phoneid",      //type:String       手机号码
                        "province",     //type:String       省名称
                        "username",     //type:String       收货人姓名
                    });
                }},
                "yyyy-MM-dd HH:mm:ss",
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.5 默认收货地址]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    
    /**
     * @name        4.14.6 提醒发货
     * @description 提醒商户发货
     * @url         /api/v1/market/activity/order/prompt_shipment
     * @method      POST
     * @param sessiontoken  会话令牌
     * @param orderId       订单编号
     */
    @ResponseBody
    @RequestMapping(value = "prompt_shipment",method = RequestMethod.POST)
    public void promptShipment(@RequestParam("sessiontoken") String sessiontoken,
                       @RequestParam("orderId") String orderId,
                       HttpServletResponse httpResponse) throws Exception {

        logger.info("调用[4.14.6 提醒发货 /api/v1/market/activity/order/prompt_shipment POST]接口, 参数: sessiontoken=" + sessiontoken +"orderId="+orderId);
        try {
            String message = orderService.remind(orderId);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("message",message);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.6 提醒发货]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }
    }
    
    
    /**
     * @name        4.14.7 确认收货接口
     * @description 夺宝.竞拍订单确认收货
     * @url         /api/v1/market/activity/order/confirm
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "confirm",method = RequestMethod.POST)
    public void confirm(@RequestParam("sessiontoken") String sessiontoken,
                        @RequestParam("orderId") String orderId,
                        HttpServletResponse httpResponse) throws Exception {

        logger.info("调用[4.14.7 确认收货接口 /api/v1/market/activity/order/confirm POST]接口, 参数: sessiontoken=" + sessiontoken + "orderId" + orderId);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            orderService.confirm(orderId,uid);
            HashMap<String, Object> resultMap = new HashMap<>();
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.7 确认收货接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }

    }
    
    /**
     * @name        4.14.8 夺宝.竞拍 弹框
     * @description 获取夺宝.竞拍 模块进入时的弹框信息
     * @url         /api/v1/market/activity/order/dialog_info
     * @method      POST
     * @param       sessiontoken    会话令牌
     * @param       orderType       活动类型 01:一元夺宝  02:竞拍活动
     * */
    @ResponseBody
    @RequestMapping(value = "dialog_info",method = RequestMethod.POST)
    public void dialogInfo(@RequestParam(value = "sessiontoken") String sessiontoken,
                            @RequestParam(value = "orderType",required = false) Integer orderType,
                            HttpServletResponse httpResponse) throws Exception {


        logger.info("调用[4.14.8 夺宝.竞拍 弹框 /api/v1/market/activity/order/dialog_info POST]接口, 参数: sessiontoken=" + sessiontoken + "orderType=" + orderType);
        try {

            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            List<BillFreshActivity> orders = orderService.dialogInfo(orderType,uid);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("activityList", orders);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(BillFreshActivity.class, new String[]{
                        "id",                   // 订单号
                        "productName",          // 商品名称
                        "productBreviary",      // 商品缩略图
                        "auctionDeposit",       // 竞拍保证金
                        "auctionBalance",       // 竞拍尾款
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.8 夺宝.竞拍 弹框]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }
    }

    /**
     * @name        4.14.9 弹框确认
     * @description 确认中奖弹框, 确认后后台将不返回该商品的弹框信息
     * @url         /api/v1/market/activity/order/dialog_confirm
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "dialog_confirm",method = RequestMethod.POST)
    public void dialogConfirm(@RequestParam("orderId") String orderId,
                              @RequestParam(value = "sessiontoken") String sessiontoken,
                              HttpServletResponse httpResponse) throws Exception {

        logger.info("调用[4.14.9 弹框确认 /api/v1/market/activity/order/dialog_confirm POST]接口, 参数: sessiontoken=" + sessiontoken+"orderId:"+orderId);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            orderService.dialogConfirm(orderId,uid);
            HashMap<String, Object> resultMap = new HashMap<>();
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.14.9 弹框确认]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }
    
    /**
     * 活动订单物流信息查询
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/activity_view" )
    public Object activityView(OrderProcessInfoRequest request, HttpServletResponse response) throws Exception {
        logger.info("【访问活动订单物流信息查询接口】-【 post /api/v1/express/activity_view】  参数：" + request.toString());
        // 验证数据
        List<ConstraintViolation> result = validator.validate(request);
        if (result != null && !result.isEmpty()) {
            String message ="";
            for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
            new Response(ResponseCode.FAILURE, message.substring(0, message.length()-1)).write(response);
            return null;
        }
        try {
            Object info = freshOrderProcessService.queryActivityOrderExpress(request);
            return info;
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
            return null;
        }
        
    }
}
