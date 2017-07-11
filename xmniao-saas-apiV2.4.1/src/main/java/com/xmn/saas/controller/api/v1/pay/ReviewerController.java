package com.xmn.saas.controller.api.v1.pay;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.constants.PaymentTypeConsts;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.bill.StatisticalService;
import com.xmn.saas.service.celebrity.ReviewerService;
import com.xmn.saas.service.wallet.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * create 2016/12/04
 *
 * @author yangQiang
 */

@RequestMapping("api/v1/pay/reviewer")
@Controller("api-v1-pay-reviewer-controller")
public class ReviewerController extends AbstractController{

    // 初始化日志处理类
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    @Autowired
    private StatisticalService statisticalService;

    @Autowired
    private ReviewerService reviewerService;

    @Autowired
    private WalletService walletService;

    /**
     * 支付页面 信息接口
     */
    @ResponseBody
    @RequestMapping(value = "pay_data",method = RequestMethod.POST)
    public void payData(@RequestParam("orderId")Long orderId) throws IOException {
        logger.info("调用[名嘴点评-支付页面数据接口  api/v1/pay/reviewer/pay_data POST] orderId : "+orderId);
        try {
            // 查询商户信息
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
            // 查询名嘴订单信息
            CelebrityOrder reviewerOrder = reviewerService.queryOrder(orderId);

            // 校验订单数据
            if (reviewerOrder == null) {
                failure("订单号无效!");
                return;
            }
            if (!reviewerOrder.getSellerId().toString().equals(sellerAccount.getSellerid().toString())) {
                failure("无法查询该订单");
                return;
            }
            if(reviewerOrder.getPayStatus() != 1){
                failure("该订单无法支付");
                return;
            }

            // 查询商户钱包余额
//            Map<String, String> walletMap = walletService.getWalletBalance(sellerAccount);
//            HashMap<String,Object> reqMap = new HashMap<>();
//            walletMap.put("uId", sellerAccount.getSellerid() + "");
//            walletMap.put("userType", "2");
//            ResponseData xmnWithdrawAmount = statisticalService.getXmnWithdrawAmount(walletMap);
//            BigDecimal walletBalance = new BigDecimal(walletMap.get("seller_amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家钱包余额

            boolean isEnough = false;
            BigDecimal usableAmount = new BigDecimal("0.00"); //可用余额
            Map<String, String> walletMap = walletService.getWalletBalance(sellerAccount);
            if (walletMap.isEmpty()) {
                new Response(ResponseCode.FAILURE, "未获取到商家钱包信息").write();
                return;
            } else {
                BigDecimal zbalance = new BigDecimal(walletMap.get("zbalance")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家赠送余额
                BigDecimal walletAmount = new BigDecimal(walletMap.get("amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家钱包余额
                BigDecimal balance = new BigDecimal(walletMap.get("balance")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家分账余额
                BigDecimal commision = new BigDecimal(walletMap.get("commision")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家佣金余额
                //BigDecimal integral = new BigDecimal(walletMap.get("integral")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家积分余额
                BigDecimal sellerAmount = new BigDecimal(walletMap.get("seller_amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家收益金额
                usableAmount=walletAmount.add(zbalance).add(balance).add(commision).add(sellerAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (usableAmount.compareTo(reviewerOrder.getPrice()) >= 0) { // 判断商家钱包余额是否足够支付红包总金额
                    isEnough = true;
                }
            }



//            // 计算钱包余额是否足够支付
//            boolean isEnough = false;
//            if (walletBalance.subtract(reviewerOrder.getPrice()).doubleValue() > 0) { // 判断商家钱包余额是否足够支付
//                isEnough = true;
//            }

            // 封装响应数据
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("isEnough",isEnough);
            resultMap.put("sypay_type", PaymentTypeConsts.sypay); // 钱包余额类型
            resultMap.put("wxpay_type", PaymentTypeConsts.wxpay); // 微信支付类型
            resultMap.put("alipay_type", PaymentTypeConsts.alipay); // 支付宝类型
            resultMap.put("title","名嘴食评");
            resultMap.put("totalAmount",reviewerOrder.getPrice());
            resultMap.put("amount",usableAmount);
            resultMap.put("orderNo",reviewerOrder.getId());
            resultMap.put("orderId",reviewerOrder.getId());

            // 返回响应数据
//            success(resultMap);
            success(resultMap,new HashMap<Class<?>, String[]>(){{
                put(CelebrityOrder.class,new String[]{"id",  "orderNo"});
            }});
        } catch (Exception e) {
            logger.error("调用[名嘴点评-支付页面数据接口] 出现异常",e);
            failure();
        }
    }

    /**
     * 确认支付
     */
    @ResponseBody
    @RequestMapping(value = "confirm",method = RequestMethod.POST)
    public void confirm(@RequestParam("orderId")Long orderId,
                        @RequestParam("payType")Integer payType
    ) throws IOException {
        logger.info("调用[确认支付 api/v1/pay/reviewer/confirm POST]");

        try {
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
            CelebrityOrder reviewerOrder = reviewerService.queryOrder(orderId);
            reviewerOrder.setPayType(payType);

            // 校验订单信息
            if (reviewerOrder == null) {      // 未查询到订单
                failure("该订单无效");
                return;
            }

            HashMap<String, Object> resultMap = reviewerService.confirmPay(reviewerOrder, sellerAccount);
            success(resultMap);
        } catch (SaasException e){
            failure(e);
        } catch (Exception e) {
            logger.error("调用[名嘴食评-确认支付]接口出现异常",e);
            failure("支付失败,请稍后再试!");
        }



    }

}
