/**
 * 
 */
package com.xmniao.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.thrift.busine.common.XmnOrderParamV2;
import com.xmniao.thrift.busine.order.OrderService;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：OrderServiceClient
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-9 下午3:47:42 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public class OrderServiceClient {

	 //服务端的IP地址
    private static final String IP_NUMBER = "localhost";
    
    //服务端的端口号
    private static final int PORT = 7911;
    
    private static TTransport transport = null;
    
    public static void main(String[] args) throws InterruptedException
    {
        try
        {
            // 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(IP_NUMBER, PORT);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            
            //开通美食体验卡服务模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(
                    protocol, "OrderService");
            OrderService.Client orderServiceClient = new OrderService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            
            //更新寻蜜鸟订单信息
          XmnOrderParamV2 xmnOrderParamV2 = new XmnOrderParamV2();
          xmnOrderParamV2.setBid("170810000031");				//订单编号id
          xmnOrderParamV2.setStatus("1");						//订单状态 1成功 2 失败 3取消
          xmnOrderParamV2.setZdate("2017-08-12 21:31:56");		//状态更新时间
          xmnOrderParamV2.setUid("607033");						//用户编号
//          xmnOrderParamV2.setPhoneid("19600000010");			//用户手机号
          xmnOrderParamV2.setPayid("101708101412090634");					//支付流水号
          xmnOrderParamV2.setNumber("101708101412090634");					//第三方交易号(第三方支付成功必填)
//          xmnOrderParamV2.setThirdUid(thirdUid);				//第三方支付账号
          xmnOrderParamV2.setPaytype("1000000");				//支付方式编码
          xmnOrderParamV2.setOrdertype("1");					//订单类型  1为订单
          xmnOrderParamV2.setIsbalance("0");					//是否使用寻蜜鸟钱包支付(支付成功必填)
          xmnOrderParamV2.setMoney("100");						//订单总金额
          xmnOrderParamV2.setPreferential("0.00");					//订单优惠金额(支付成功必填)
          xmnOrderParamV2.setPayamount("100.00");					//订单实付金额(支付成功必填)
          xmnOrderParamV2.setSamount("0");					//第三方支付金额(支付成功必填)
          xmnOrderParamV2.setCommision("100");					//佣金支付金额(支付成功必填)
          xmnOrderParamV2.setProfit("0");						//返利支付金额(支付成功必填)
          xmnOrderParamV2.setGiveMoney("0");					//赠送支付金额(支付成功必填)
          xmnOrderParamV2.setLiveCoin("0");					//鸟粉卡支付鸟币
          xmnOrderParamV2.setLiveCoinArrivedMoney("0");		//鸟粉卡实际抵用金额
//          xmnOrderParamV2.setLiveCoinRatio("0");				//鸟粉卡与现金兑换比
          
          xmnOrderParamV2.setSellerCoin("0");					//专享卡支付鸟币
//          xmnOrderParamV2.setDiscounts("0");				    //商家折扣（鸟币支付）
//          xmnOrderParamV2.setBase("0.85");						//基数(如0.7)
          xmnOrderParamV2.setUidMbEcno("19500000030");
          xmnOrderParamV2.setSaasChannel("2");
          String orderNo = orderServiceClient.updateXmnOrderInfoV2(xmnOrderParamV2);
          
          System.out.println(orderNo);
          
//        Map<String,String> paraMap = new HashMap<String,String>();
//        paraMap.put("bid", "161118000074");
//        Map<String,String> resultMap =  orderServiceClient.handleLedger(paraMap);
//        System.out.println("手动分账处理结果："+resultMap);
            
        }catch (TException e)
        {
            e.printStackTrace();
        }
        finally
        {
            transport.close();
        }
    }
}
