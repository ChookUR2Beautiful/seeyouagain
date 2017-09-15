package com.xmniao.xmn.core.market.controller.activity;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.entity.activity.indiana.FreshActivityIndiana;
import com.xmniao.xmn.core.market.entity.activity.indiana.FreshActivityIndianaBout;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.activity.IndianaService;
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
import java.util.Map;

/**
 * Created by yang.qiang on 2017/2/25.
 */
@Controller( "api-v1-market-activity-controller" )
@RequestMapping( "/api/v1/market/activity" )
public class ActivityController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SessionTokenService sessionTokenService;
    @Autowired
    private IndianaService indianaService;


    /**
     * @name        4.12.7 户账户信息
     * @description 获取用户可用鸟币
     * @url         /api/v1/market/activity/wallet_info
     * @method      POST
     * @param       sessiontoken
     * @param       boutId      夺宝期次,非必填
     */
    @ResponseBody
    @RequestMapping(value = "wallet_info",method = RequestMethod.POST)
    public void walletInfo(@RequestParam("sessiontoken") String sessiontoken,
                           @RequestParam(value = "boutId",required = false)  Integer boutId,
                           HttpServletResponse httpResponse) throws IOException {
        logger.info("调用[用户账户信息 /api/v1/market/activity/wallet_info POST]接口, 参数: sessiontoken="+sessiontoken +" boutId="+boutId);
        try {
            // 获取用户账户信息
            String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
            Map<String, Object> userWallet = indianaService.getUserWallet(uid);
            FreshActivityIndiana indiana = null;
            if(boutId != null){
                indiana = indianaService.getIndianaByBoutId(boutId);
                FreshActivityIndianaBout bout = indianaService.queryBout(boutId);
                indiana.setResiduePoint(indiana.getPoint() - bout.getSaleNum());
            }

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("commision", userWallet.get("zbalance"));         // 鸟币余额
            resultMap.put("zbalanceCoin",userWallet.get("zbalanceCoin"));   // 可用鸟币
            resultMap.put("balance",userWallet.get("commision"));           // 鸟豆
            resultMap.put("indiana",indiana);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>(){{
                    put(FreshActivityIndiana.class,new String[]{
                        "id",
                        "pointPrice",
                        "residuePoint",
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[用户账户信息]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }


    /**
     * 访问系统时间接口
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/system_time" , method = RequestMethod.POST ,
        produces = { "application/json;charset=UTF-8" } )
    public void systemTime(HttpServletResponse response) throws Exception {
        logger.info("【访问系统时间接口】-【 post /api/v1/market/activity/system_time】 ");
        try {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("time", System.currentTimeMillis());
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(response);
        } catch (CustomException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write(response);
        }
        return;
    }


}
