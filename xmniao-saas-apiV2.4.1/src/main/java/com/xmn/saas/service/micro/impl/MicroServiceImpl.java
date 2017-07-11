package com.xmn.saas.service.micro.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.dao.micro.MicroBillDao;
import com.xmn.saas.entity.micro.MicroBill;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.micro.MicroService;
import com.xmn.saas.utils.HttpConnectionUtil;
import com.xmn.saas.utils.MakeOrderNum;
import com.xmn.saas.utils.Signature;

/**
 * 扫码支付服务类
 * @author zhouxiaojian
 *
 */
@Service
public class MicroServiceImpl implements MicroService{
    
    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(MicroServiceImpl.class);
    
    @Autowired
    private GlobalConfig globalConfig;
    
    @Autowired
    private MicroBillDao microBillDao;
     
    @SuppressWarnings( "unchecked" )
    @Override
    public Map<String, Object> pay(MicroBill microBill) throws SaasException{
        Map<String, Object> resultMap =new  HashMap<>();
        //生产订单号
        String orderNumber = MakeOrderNum.getInstance().makeOrderNum();
        microBill.setCreateTime(new Date());
        microBill.setOrderNumber(orderNumber);
        microBill.setPayStatus(0);//支付状态( 0:未支付 1:已支付 2:已取消 )
        //生成订单
        microBillDao.insert(microBill);
        resultMap.put("orderNumber", orderNumber);
        String key = globalConfig.getMicroBillKey();
        log.info("扫码支付的key:"+key);
        
        //拼装参数
        Map<String, String> paramMap =new HashMap<>();
        paramMap.put("orderName", microBill.getOrderName());// 订单名称
        paramMap.put("orderNumber", microBill.getOrderNumber());// 订单号
        paramMap.put("totalAmount", String.format("%.2f",microBill.getTotalAmount()));// 支付金额
        paramMap.put("paymentType", microBill.getPayType()+"");// 支付类型
        paramMap.put("sellerId", microBill.getSellerid()+"");// 商家id
        paramMap.put("clientType", microBill.getClientType()+"");// 客户类型
        paramMap.put("authCode", microBill.getAuthCode());// 验证码
        paramMap.put("source", microBill.getSource());//来源
        paramMap.put("appVersion", microBill.getAppVersion());// 版本
        
        log.info("扫码支付要加密签名的字段："+paramMap.toString());
        paramMap.put("sign", Signature.sign(paramMap, key));// 签名
        
        
        
        //请求支付接口
        String url = HttpConnectionUtil.getUrl(paramMap, globalConfig.getMicroBillHost()+globalConfig.getMicroBillPayment());
        log.info("访问支付接口的拼装url：      "+url);
        String resultStr = HttpConnectionUtil.doPost(url, "");// 请求支付接口
        log.info("请求支付接口返回的resultStr:    "+resultStr);
        if (StringUtils.isNotEmpty(resultStr)) {
            JSONObject json = JSONObject.parseObject(resultStr);
            log.info("格式化请求支付接口返回json:"+json);
            resultMap = (Map<String, Object>) JSON.parse(json.toString());
            if(json.containsKey("result")){
                Object data= json.get("result");
                resultMap.clear();
                resultMap.put("data", data);
            }
            resultMap.put("orderNumber", orderNumber);
            log.info("返回客户端数据:"+resultMap);
        }
        return resultMap;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public Map<String, Object> query(MicroBill microBill) throws SaasException{
        Map<String, Object> resultMap =new  HashMap<>();
      //拼装参数
        Map<String, String> paramMap =new HashMap<>();
        paramMap.put("orderNumber", microBill.getOrderNumber());// 订单号
        //paramMap.put("paymentType", microBill.getPayType()+"");// 支付类型
        
        
        
        //请求支付接口
        String url = HttpConnectionUtil.getUrl(paramMap, globalConfig.getMicroBillHost()+globalConfig.getMicroBillQuery());
        log.info("访问支付查询接口的拼装url：      "+url);
        String resultStr = HttpConnectionUtil.doPost(url, "");// 请求支付接口
        log.info("请求支付查询接口返回的resultStr:    "+resultStr);
        if (StringUtils.isNotEmpty(resultStr)) {
            JSONObject json = JSONObject.parseObject(resultStr);
            log.info("格式化请求支付查询接口返回json:"+json);
            resultMap = (Map<String, Object>) JSON.parse(json.toString());
            if(json.containsKey("result")){
                Object data= json.get("result");
                resultMap.clear();
                resultMap.put("data", data);
            }
            log.info("查询支付接口返回客户端数据:"+resultMap);
        }
        return resultMap;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public Map<String, Object> cancel(MicroBill microBill) throws SaasException{
        Map<String, Object> resultMap =new  HashMap<>();
        String key = globalConfig.getMicroBillKey();
        log.info("扫码支付的key:"+key);
        
        //拼装参数
        Map<String, String> paramMap =new HashMap<>();
        paramMap.put("randomNumber", Math.random()*10+"");// 随机数
        paramMap.put("orderNumber", microBill.getOrderNumber());// 订单号
        paramMap.put("totalAmount", String.format("%.2f",microBill.getTotalAmount()));// 支付金额
        //paramMap.put("paymentType", microBill.getPayType()+"");// 支付类型
        
        log.info("扫码支付要加密签名的字段："+paramMap.toString());
        paramMap.put("sign", Signature.sign(paramMap, key));// 签名
        
        
        
        //请求支付接口
        String url = HttpConnectionUtil.getUrl(paramMap, globalConfig.getMicroBillHost()+globalConfig.getMicroBillCancel());
        log.info("访问支付撤销接口的拼装url：      "+url);
        String resultStr = HttpConnectionUtil.doPost(url, "");// 请求支付接口
        log.info("请求支付撤销接口返回的resultStr:    "+resultStr);
        if (StringUtils.isNotEmpty(resultStr)) {
            JSONObject json = JSONObject.parseObject(resultStr);
            log.info("格式化请求支付撤销接口返回json:"+json);
            resultMap = (Map<String, Object>) JSON.parse(json.toString());
            if(json.containsKey("result")){
                Object data= json.get("result");
                resultMap.clear();
                resultMap.put("data", data);
            }
            log.info("支付撤销返回客户端数据:"+resultMap);
        }
        return resultMap;
    }

}
