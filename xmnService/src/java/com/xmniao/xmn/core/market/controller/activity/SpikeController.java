package com.xmniao.xmn.core.market.controller.activity;

import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.activity.spike.FreshSpikeActivity;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.activity.spike.SpikeService;
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
 * Created by yang.qiang on 2017/2/20.
 */
@RequestMapping("/api/v1/market/activity/spike")
@Controller("api-v1-market-apike")
public class SpikeController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpikeService spikeService;


    /**
     * @name    秒杀活动信息接口
     * @description 获取秒杀活动信息
     * @url     /api/v1/market/activity/spike/info
     * @method  POST
     * @param spikeId   秒杀活动id
     */
    @ResponseBody
    @RequestMapping(value = "info", method = RequestMethod.POST)
    public void info(@RequestParam(value = "spikeId") Long spikeId ,HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[秒杀活动信息接口 /api/v1/market/activity/spike/info POST]接口, 参数: spikeId=" + spikeId);
        try {
            FreshSpikeActivity spikeActivity = spikeService.getSpikeActivity(spikeId);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("spikeActivity", spikeActivity);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(FreshSpikeActivity.class, new String[]{
                        "id",           //type:Long     秒杀活动ID
                        "title",        //type:String   秒杀活动标题
//                        "startTime",    //type:Date     秒杀活动开始时间
//                        "endTime",      //type:Date     秒杀活动结束时间
                        "image",        //type:String   秒杀活动Banner图
                        "sessions",    //type:List     活动场次列表
                    });
                    put(FreshActivityCommon.class, new String[]{
                        "id",           //type:Integer  场次id(通用活动id)
                        "beginDate",    //type:Date     开始时间
                        "endDate",      //type:Date     结束时间
                        "distStartTime",//type:Long     距离活动开始时间
                        "distEndTime",  //type:Long     距离活动接口时间
                    });
                }},
                "yyyy-MM-dd HH:mm:ss",
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[秒杀活动信息表]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }


    /**
     * @name        秒杀场次商品列表接口
     * @description 获取秒杀场次商品列表
     * @url         /api/v1/market/activity/spike/product_list
     * @method      POST
     * @param       activityId  活动(场次)id
     * @param       page        分页页数
     * @param       pageSize    分页页面大小
     */
    @ResponseBody
    @RequestMapping(value = "product_list", method = RequestMethod.POST)
    public void proudctList(@RequestParam(value = "activityId")Long activityId,Page page, HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[秒杀场次商品列表接口 /api/v1/market/activity/spike/product_list POST]接口, 参数: activityId=" + activityId + " Page=" + page.toString());
        try {
            List<ProductInfo> productList = spikeService.getActivityProductList(activityId,page);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("productList", productList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(ProductInfo.class, new String[]{
                        "pid",      //type:int  产品的id
                        "pname",    //type:String  产品的具体名称
                        "price",    //type:double  产品原单品价格
                        "breviary", //type:String  产品列表缩略图
                        "choice",   //type:int  是否精选商品  0不是  1是
                        "integral", //type:int  积分支付金额
                        "cash",     //type:double  现金支付金额
                        "codeid",   //type:int     产品唯一标识编号
                        "store",    //type:int     库存
                        "labels",   //      标签集合
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[秒杀场次商品列表接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }


}
