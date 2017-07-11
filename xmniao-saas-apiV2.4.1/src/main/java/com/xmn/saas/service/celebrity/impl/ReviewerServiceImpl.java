package com.xmn.saas.service.celebrity.impl;

import com.alibaba.fastjson.JSONObject;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Page;
import com.xmn.saas.dao.celebrity.CelebrityAriticleDao;
import com.xmn.saas.dao.celebrity.CelebrityDao;
import com.xmn.saas.dao.celebrity.CelebrityOrderDao;
import com.xmn.saas.entity.celebrity.Celebrity;
import com.xmn.saas.entity.celebrity.CelebrityAriticle;
import com.xmn.saas.entity.celebrity.CelebrityOrder;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.celebrity.ReviewerService;
import com.xmn.saas.service.wallet.WalletService;
import com.xmn.saas.utils.HttpConnectionUtil;
import com.xmn.saas.utils.MakeOrderNum;
import com.xmn.saas.utils.PropertiesUtil;
import com.xmn.saas.utils.Signature;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ZERO;

/**
 * create 2016/11/30
 *
 * @author yangQiang
 */

@Service
public class ReviewerServiceImpl implements ReviewerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CelebrityDao celebrityDao;

    // 食评文章DAO
    @Autowired
    private CelebrityAriticleDao celebrityAriticleDao;

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Autowired
    private CelebrityOrderDao celebrityOrderDao;

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private WalletService walletService;


    @Override
    public List<Celebrity> queryReviewerList(Page page, Integer order) {
        List<Celebrity> celebrityList = celebrityDao.selectByReviewer(page, order);
        return celebrityList;
    }

    @Override
    public Celebrity queryReviewerDetail(Long reviewerId) {
        Celebrity celebrity = celebrityDao.selectByPrimaryKey(reviewerId);
        return celebrity;
    }

    @Override
    public void submitOrder(CelebrityOrder order) {


        // 设置 订单价格
        Celebrity celebrity = celebrityDao.selectByPrimaryKey(order.getCelebrityId());
        order.setPrice(celebrity.getReviewPrice());

        // 初始化订单参数
        order.setId(null);
        order.setType(1);
        order.setPayStatus(1);
        order.setStatus(1);
        order.setCreateTime(new java.util.Date());
        // 生成订单号
        MakeOrderNum makeOrderNum = MakeOrderNum.getInstance();
        order.setOrderNo(makeOrderNum.makeOrderNum());

        celebrityOrderDao.insertSelective(order);

    }

    @Override
    public Long countReviewer() {
        Long count = celebrityDao.countReviewer();
        return count;
    }

    @Override
    public CelebrityAriticle queryMaxViewsAriticle(Long reviewerId) {
        CelebrityAriticle celebrityAriticle = celebrityAriticleDao.selectByMaxViewAriticel(reviewerId);
        return celebrityAriticle;
    }

    @Override
    public List<CelebrityOrder> queryOrderList(SellerAccount sellerAccount, Page page) {
        // 分页查询 该商户所有的名嘴食评订单
        List<CelebrityOrder> reviewerOrderList = celebrityOrderDao.selectReviewerOrderBySellerId(sellerAccount, page);

        // 查询 名嘴信息 并封装到订单对象
        for (CelebrityOrder celebrityOrder : reviewerOrderList) {
            Celebrity celebrity = celebrityDao.selectByPrimaryKey(celebrityOrder.getCelebrityId());
            celebrity.setAvatar(globalConfig.getImageHost() + celebrity.getAvatar());
            celebrityOrder.setCelebrity(celebrity);
        }

        return reviewerOrderList;
    }

    @Override
    public Integer queryOrderCount(SellerAccount sellerAccount) {
        return celebrityOrderDao.selectCountOrderBySellerId(sellerAccount.getSellerid());
    }

    @Override
    public List<CelebrityAriticle> queryAriticleList(SellerAccount sellerAccount, Page page) {
        CelebrityAriticle record = new CelebrityAriticle();
        record.setSellerId(sellerAccount.getSellerid().longValue());
        List<CelebrityAriticle> ariticleList = celebrityAriticleDao.querySelective(record, page);

        // 封装文章关联信息
        for (CelebrityAriticle ariticle : ariticleList) {
            // 查询名嘴信息
            Celebrity celebrity = celebrityDao.selectByPrimaryKey(ariticle.getCelebrityId());
            celebrity.setAvatar(globalConfig.getImageHost() + celebrity.getAvatar());
            ariticle.setCelebrity(celebrity);
            // 图片拼接域名
            ariticle.setImage(globalConfig.getImageHost() + ariticle.getImage());
        }
        return ariticleList;
    }

    @Override
    public Integer countAriticle(SellerAccount sellerAccount) {
        return celebrityAriticleDao.countAriticelBySellerId(sellerAccount);
    }

    @Override
    public CelebrityOrder queryOrder(Long orderId) {
        return celebrityOrderDao.selectByPrimaryKey(orderId);
    }

    @Override
    public HashMap<String, Object> confirmPay(CelebrityOrder reviewerOrder, SellerAccount sellerAccount) throws IOException {

        if (!reviewerOrder.getSellerId().toString().equals(sellerAccount.getSellerid().toString())) {
            throw new SaasException("无法查询该订单");
        }
        if (reviewerOrder.getPayStatus() != 1) {
            throw new SaasException("该订单无法支付");
        }

        Integer payType = reviewerOrder.getPayType();

        logger.info("支付确认  orderId:" + reviewerOrder.getId());
        logger.info("支付确认  payType:" + payType);


        CelebrityOrder redpacket = queryOrder(reviewerOrder.getId());// 获取订单数据

        if (redpacket == null) {
            throw new SaasException("订单无效");
        }
//        BigDecimal zbalance = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家赠送余额
//        BigDecimal amount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家钱包余额
//        BigDecimal balance = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家分账余额
//        BigDecimal commision = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家佣金余额
//        BigDecimal sellerAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家收益金额

        Integer sellerId = redpacket.getSellerId().intValue();
        Map<String, String> walletMap = walletService.getWalletBalance(sellerAccount);
        if (walletMap.isEmpty()) {
            throw new SaasException("未获取到商家钱包信息");
        }
        BigDecimal zbalance = new BigDecimal(walletMap.get("zbalance")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家赠送余额
        BigDecimal amount = new BigDecimal(walletMap.get("amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家钱包余额
        BigDecimal balance = new BigDecimal(walletMap.get("balance")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家分账余额
        BigDecimal commision = new BigDecimal(walletMap.get("commision")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家佣金余额
        BigDecimal sellerAmount = new BigDecimal(walletMap.get("seller_amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家收益金额

        BigDecimal totalAmount = redpacket.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP);//红包总金额
        Map<String, String> payParamMap = new HashMap<String, String>();

        // 所有传入金额必须格式化为2位小数
        if (payType == 1000000) {
            //扣款顺序： 赠送余额-->钱包余额-->分账余额-->佣金余额-->收益余额
            BigDecimal temp = totalAmount.subtract(zbalance);
            boolean flag = true; //是否足够支付标识
            if (temp.compareTo(ZERO) > 0) {
                temp = temp.subtract(amount);
            } else {
                zbalance = zbalance.add(temp);
                amount = ZERO;
                balance = ZERO;
                commision = ZERO;
                sellerAmount = ZERO;
                flag = false;
            }

            if (temp.compareTo(ZERO) > 0) {
                temp = temp.subtract(balance);
            } else if (flag) {
                amount = amount.add(temp);
                balance = ZERO;
                commision = ZERO;
                sellerAmount = ZERO;
                flag = false;
            }

            if (temp.compareTo(ZERO) > 0) {
                temp = temp.subtract(commision);
            } else if (flag) {
                balance = balance.add(temp);
                commision = ZERO;
                sellerAmount = ZERO;
                flag = false;
            }

            if (temp.compareTo(ZERO) > 0) {
                temp = temp.subtract(sellerAmount);
            } else if (flag) {
                commision = commision.add(temp);
                sellerAmount = ZERO;
                flag = false;
            }

            if (temp.compareTo(ZERO) > 0) {
                throw new SaasException("余额不足!");
            } else if (flag) {
                sellerAmount = temp.add(sellerAmount);
                flag = false;
            }

            // 以下参数可选，值为0时可不传
            if (zbalance.compareTo(ZERO) > 0) {
                payParamMap.put("zbalance", zbalance + "");// 赠送支付金额
            }
            if (amount.compareTo(ZERO) > 0) {
                payParamMap.put("sellerAmount", amount + "");// 钱包支付余额
            }
            if (balance.compareTo(ZERO) > 0) {
                payParamMap.put("sellerBalance", balance + "");// 分账支付余额
            }
            if (commision.compareTo(ZERO) > 0) {
                payParamMap.put("commision", commision + "");// 佣金支付金额
            }
            if (sellerAmount.compareTo(ZERO) > 0) {
                payParamMap.put("profit", sellerAmount + "");// 收益支付金额
            }
        }

        Long currentVersion = redpacket.getVersionLock(); //乐观锁当前版本号

        CelebrityOrder record = new CelebrityOrder();

        record.setId(redpacket.getId());
        record.setVersionLock(currentVersion);
        record.setPayType(payType);
//        record.setPayTime(new Date());
//                int count = celebrityOrderDao.updateByPrimaryKeySelective(record);
        int count = celebrityOrderDao.updateVersionLock(record);

        if (count < 1) {
            throw new SaasException("请勿重复支付");
        }

        payParamMap.put("subject", "名嘴食评");// 订单标题
        payParamMap.put("orderSn", redpacket.getOrderNo());// 订单编号
        payParamMap.put("userType", "2");// 用户类型
        payParamMap.put("uid", String.valueOf(sellerId));// 商家id
        payParamMap.put("amount", totalAmount.toString()); // 订单金额
        payParamMap.put("source", "010");// 订单来源，标识内部业务系统不同类型订单,001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单;003:业务系统-积分商品订单；004:业务系统-物料订单；005:业务系统-直播鸟币购买订单 ； 006:业务系统-红包支付订单
        payParamMap.put("paymentType", payType + "");// 支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:微信公众号支付，1000000：钱包支付
        payParamMap.put("orderType", "2");// 订单类型，目前传固定值2

//                    payParamMap.put("body", "名嘴食评");// 订单描述

        String key = propertiesUtil.getValue("payRedpacketKey", "conf_pay.properties");
        logger.info("key:" + key);
        payParamMap.put("sign", Signature.sign(payParamMap, key));// 签名
        String url = propertiesUtil.getValue("payRedpacketUrl", "conf_pay.properties");
        logger.info("url：" + url);
        String requestParam = "";
        for (Map.Entry<String, String> entry : payParamMap.entrySet()) {
            requestParam += "&" + entry.getKey() + "=" + entry.getValue();
        }
        requestParam = requestParam.substring(1, requestParam.length());
        String payurl = url + "?" + requestParam;
        payurl = payurl.replaceAll(" ", "%20");
        logger.info("payurl:" + payurl);
        String result = HttpConnectionUtil.doPost(payurl, "");// 请求支付接口
        logger.info("result:" + result);
        if (StringUtils.isNotEmpty(result)) {
            HashMap<String, Object> resultMap = new HashMap<String, Object>();
            JSONObject json = JSONObject.parseObject(result);
            logger.info("json:" + json);
            Object state = json.get("state");

            if (!"201".equals(state) && !"200".equals(state)) {
                throw new SaasException("支付失败!");
            }

            if (json.get("result") != null) {
                Object data = json.get("result");
                resultMap.put("data", data);
            }

            logger.info("返回客户端数据:" + resultMap);

            return resultMap;
        } else {
            throw new SaasException("调用支付接口失败!");
        }


    }

    @Override
    public String queryAriticle(Long articleId) {
        String content = celebrityAriticleDao.selectHtmlContentById(articleId);
        return content.replaceAll("&gt;",">").replaceAll("&lt;","<");
    }
}
