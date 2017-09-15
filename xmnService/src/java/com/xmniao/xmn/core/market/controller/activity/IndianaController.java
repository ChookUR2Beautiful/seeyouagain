package com.xmniao.xmn.core.market.controller.activity;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.controller.activity.vo.IndianaPayRequest;
import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
import com.xmniao.xmn.core.market.entity.activity.indiana.*;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.activity.IndianaService;
import com.xmniao.xmn.core.verification.dao.UrsDao;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/2/21.
 */
@RequestMapping("/api/v1/market/activity/indiana")
@Controller("api-v1-market-indiana-controller")
public class IndianaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IndianaService indianaService;
    @Autowired
    private Validator validator;
    @Autowired
    private SessionTokenService sessionTokenService;
    @Autowired
    private UrsDao ursDao;


    /**
     * @name        4.12.1 夺宝号码接口
     * @description 查询用户一期次的夺宝号码
     * @url         /api/v1/market/activity/indiana/bout_number
     * @param       sessiontoken    会话令牌
     * @param       boutId          期次ID,从[4.12.2 用户夺宝记录]中获取[boutId]字段
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "bout_number",method = RequestMethod.POST)
    public void boutNumber(@RequestParam("sessiontoken") String sessiontoken,
                           @RequestParam("boutId") Integer boutId,HttpServletResponse httpResponse) throws Exception {


        logger.info("调用[4.12.1 夺宝号码接口 /api/v1/market/activity/indiana/bout_number POST]接口, 参数: sessiontoken=" + sessiontoken + " boutId=" + boutId);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            List<String> numberList = indianaService.getIndianaNumbers(uid,boutId);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("numberList", numberList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
//                new HashMap<Class<?>, String[]>() {{
//                    put(String.class, new String[]{
//                    });
//                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[4.12.1 夺宝号码接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }


    /**
     * @name        4.12.2 用户夺宝记录
     * @description 获取用户的夺宝记录
     * @url         /api/v1/market/activity/indiana/user_record
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "user_record",method = RequestMethod.POST)
    public void userRecord(@RequestParam("sessiontoken") String sessiontoken,Page page,HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[用户夺宝记录 /api/v1/market/activity/indiana/user_record POST]接口, 参数: sessiontoken=" + sessiontoken);
        try {
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            List<IndianaRecord> recordList = indianaService.userRecord(uid,page);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("recordList", recordList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(IndianaRecord.class, new String[]{
                        "firstTime",    //type:String   首次参与时间
                        "lastTIme",     //type:String   最近参与时间
                        "total",        //type:Integer  一期次总计参与次数
                        "boutId",       //type:Integer  夺宝期次
                        "image",        //type:String   商品缩略图
                        "title",        //type:String   夺宝活动标题
                        "point",        //type:Integer  夺宝总需份数
                        "status",       //type:Integer  夺宝状态 0:夺宝中 1:已被夺 2:已退还库存(机器人获奖) 3:终止 4待开奖
                        "winnerName",   //type:String   获奖者名
                        "winnerUid",    //type:Integer  获奖者id
                        "winnerNum",    //type:Long     获奖夺宝号码
                        "winnerRid",    //type:Integer  真实用户id
                        "saleNum",      //type:Integer  改期次已售份数
                        "veces",        //type:Integer  获奖者参数次数
                        "win",          //type:Boolean  是否中奖
                        "giveType",     //type:Integer  获奖者类型 0:用户  1:机器人
                        "order",        // 获奖订单
                        "indianaId",    // 夺宝活动id
                        "refunded",     // 是否退款
                    });
                    put(BillFreshActivity.class,new String[]{
                        "id",            // 获奖订单id
                        "state",         // 订单状态
                        "receivingConfirm", //type:Integer  '确认收货信息  01未确认  02已确认
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[用户夺宝记录]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }




    /**
     * @name        4.12.3 夺宝活动规则接口
     * @description 获取活动规则说明
     * @url         /api/v1/market/activity/indiana/rule
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "rule",method = RequestMethod.POST)
    public void rule(HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[夺宝活动规则接口 /api/v1/market/activity/indiana/rule POST]接口 ");
        try {
            Rule indianaRule = indianaService.getRule();

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("indianaRule", indianaRule);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(Rule.class, new String[]{
                        "activityRule",     //type:String   活动规则
                        "lotterRule",       //type:String   开奖规则
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[夺宝活动规则接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }

    }

    /**
     * @name        4.12.4 夺宝商品(活动)列表接口
     * @description 分页获取夺宝商品(活动)列表
     * @url         /api/v1/market/activity/indiana/product_list
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "product_list",method = RequestMethod.POST)
    public void productList(final Page page, HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[夺宝商品(活动)列表接口 /api/v1/market/activity/indiana/product_list POST]接口, 参数: page=" + page);
        try {
            // 分页查询夺宝商品(活动)列表
            List<FreshActivityIndiana> indianaList = indianaService.getIndianas(page);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("indianaList", indianaList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(FreshActivityIndiana.class, new String[]{
                        "id",           //type:Integer      夺宝活动id
                        "title",        //type:String       夺宝活动标题
                        "status",       //type:Integer      夺宝活动状态 0:正常  1:终止  2:删除
                        "beginTime",    //type:Date         开始时间
                        "endTime",      //type:Date         结束时间
                        "codeid",       //type:Long         产品编号
                        "pvIds",        //type:String       属性值id
                        "pvValue",      //type:String       规格值
                        "point",        //type:Integer      一期夺宝所需的总份数
                        "product",
                        "currentBout",
                    });
                    put(ProductInfo.class,new String[]{
                        "breviary",     //type:String       产品缩略图
                    });
                    put(FreshActivityIndianaBout.class,new String[]{
                        "id",           //type:Integer      当前期次的期号
                        "saleNum",      //type:Integer      当前期次已售份数
                    });
                }},
                "yyyy-MM-dd HH:mm:ss",
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[夺宝商品(活动)列表接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name        4.12.5 夺宝商品信息接口
     * @description 获取夺宝商品信息
     * @url         /api/v1/market/activity/indiana/product_info
     * @method      POST
     * @param       activityId  夺宝活动ID, 从[夺宝商品(活动)列表接口]获取[id]字段
     * @param       sessiontoken    会话令牌, 非必选
     */
    @ResponseBody
    @RequestMapping(value = "product_info",method = RequestMethod.POST)
    public void productInfo(@RequestParam(value = "sessiontoken",required = false) String sessiontoken,
                            @RequestParam("activityId") Integer activityId, HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[夺宝商品信息接口 /api/v1/market/activity/indiana/product_info POST]接口, 参数: activityId=" + activityId);
        try {


            FreshActivityIndiana indiana = indianaService.getIndiana(activityId);
            HashMap<String, Object> resultMap = new HashMap<>();

            if (sessiontoken != null) {
                Object redisValue = sessionTokenService.getStringForValue(sessiontoken);
                if ( redisValue!= null) {
                    try {
                        String uid = redisValue.toString();
                        resultMap.put("joinCount",indianaService.countUserBoutJoins(uid,indiana.getCurrentBout().getId()));
                    } catch (Exception e) {
                    }
                }
            }

            resultMap.put("indiana", indiana);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(FreshActivityIndiana.class, new String[]{
                        "id",           //type:Integer      夺宝活动id
                        "title",        //type:String       夺宝活动标题
                        "codeid",       //type:Long         产品编号
                        "pvValue",      //type:String       规格值
                        "pointPrice",   //type:Double       单份价格
                        "pvIds",        //type:String       属性值id
                        "point",        //type:Integer      一期夺宝所需的总份数
                        "boutResidue",  //type:Integer      剩余期数
                        "boutNum",      //type:Integer      当前夺宝期数
                        "status",        //状态
                        "product",
                        "currentBout",
                    });
                    put(ProductInfo.class,new String[]{
                        "price",        //type:Double       积分超市商品原价
                        "html",         //type:String       商品图文详情
                        "banner",       //type:List<String> 商品Banner图片
                    });
                    put(FreshActivityIndianaBout.class,new String[]{
                        "id",           //type:Integer      当前期次的期号
                        "saleNum",      //type:Integer      当前期次已售份数
                    });
                }},
                "yyyy-MM-dd HH:mm:ss",
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[夺宝商品信息接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }


    /**
     * @name        4.12.6 夺宝记录接口
     * @description 获取最近该期夺宝的夺宝记录
     * @url         /api/v1/market/activity/indiana/recently_record
     * @method      POST
     * @param       boutId      夺宝期号从[夺宝商品信息接口]获取[currentBout.id]字段
     * @param       pageSize    获取的条数
     */
    @ResponseBody
    @RequestMapping(value = "recently_record",method = RequestMethod.POST)
    public void recentlyRecord(@RequestParam("boutId") Long boutId,Page page,HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[夺宝记录接口 /api/v1/market/activity/indiana/recently_record POST]接口, 参数: boutId=" + boutId + " page=" + page);
        try {
            List<FreshActivityIndianaDduonum> recordList = indianaService.getRecentlyRecord(boutId,page);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("recordList", recordList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(FreshActivityIndianaDduonum.class, new String[]{
                        "count",        //type:Integer      购买份数
                        "createTime",   //type:String       购买时间
                        "nname",        //type:String       用户名
                        "uid",          //type:Integer      用户id
                        "winnerRid",    //type:Integer  真实用户id
                        "giveType",     //type:Integer  获奖者类型 0:用户  1:机器人
                    });
                }},
                "yyyy-MM-dd HH:mm:ss",
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[夺宝记录接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name        4.12.7 夺宝-立即支付
     * @description 立即支付并参与夺宝
     * @url         /api/v1/market/activity/indiana/pay
     * @method      POST
     */
    @ResponseBody
    @RequestMapping(value = "pay",method = RequestMethod.POST)
    public void  pay(IndianaPayRequest request, HttpServletResponse httpResponse) throws IOException {
        // 校验数据
        List<ConstraintViolation> result = validator.validate(request);
        if(result != null && !result.isEmpty()){
            new Response(ResponseCode.DATAERR,"提交的数据有问题").write(httpResponse);
            return;
        }
        logger.info("调用[夺宝-立即支付 /api/v1/market/activity/indiana/pay POST]接口, 参数: request=" + request);
        try {
            String uid = sessionTokenService.getStringForValue(request.getSessiontoken()).toString();
            Integer point = indianaService.payIndiana(Integer.valueOf(uid), request.getBoutId(), request.getPoint());
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("info", "夺宝成功");
            resultMap.put("time",new Date());
            resultMap.put("point", point);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write("yyyy-MM-dd HH:mm:ss",
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[夺宝-立即支付]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败!").write(httpResponse);
        }


    }

    /**
     * @name        4.12.8 夺宝-Banner
     * @description 立即支付并参与夺宝
     * @url         /api/v1/market/activity/indiana/banner
     * @method      POST
     */
    @ResponseBody
    @RequestMapping( value = "/banner" , method = RequestMethod.POST ,
        produces = { "application/json;charset=UTF-8" } )
    public void banner(HttpServletResponse response) throws Exception {
        logger.info("【访问竞拍轮播图】-【 post /api/v1/market/activity/auction/banner】 " );
        try {
            Map<String, Object> resultMap = indianaService.banner();
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }
    

}
