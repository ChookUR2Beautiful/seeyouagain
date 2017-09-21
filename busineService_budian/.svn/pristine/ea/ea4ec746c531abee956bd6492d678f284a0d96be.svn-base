/**
 * 
 */
package com.xmniao.main;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.experienceOfficer.ExperienceOfficerOrderService;
import com.xmniao.thrift.busine.refund.BusiSysOrderService;
import com.xmniao.thrift.busine.refund.LedgerSysOrderService;
import com.xmniao.thrift.busine.refund.RefundOrderService;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：ExperienceOfficerOrderClient
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-9 下午3:47:42 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public class ExperienceOfficerOrderClient {

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
                    protocol, "ExperienceOfficerOrderService");
            ExperienceOfficerOrderService.Client experienceOrder = new ExperienceOfficerOrderService.Client(orderProtocol);
            
            //打开端口,开始调用
            transport.open();
            
            //跟新美食体验卡订单接口
            Map<String,String> paraMap=new HashMap<String,String>();
            paraMap.put("orderNo", "0141705251149452090");
            paraMap.put("uid", "607050");
            paraMap.put("amount", "10.00");
            paraMap.put("payType", "10001");
//            paraMap.put("payid", "1705091507264052");
            paraMap.put("liveCoin", "10.00");
            paraMap.put("walletAmount", "0.00");
            paraMap.put("samount", "0.00");
            paraMap.put("payState", "1");
            
            ResponseData responseData = experienceOrder.updateExperienceOfficerOrder(paraMap);
            System.out.println(responseData.toString());
            
            
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
