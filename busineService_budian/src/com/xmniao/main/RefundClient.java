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

import com.xmniao.thrift.busine.refund.BusiSysOrderService;
import com.xmniao.thrift.busine.refund.LedgerSysOrderService;
import com.xmniao.thrift.busine.refund.RefundOrderService;

public class RefundClient
{
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
            
            //订单退款服务模块
            TMultiplexedProtocol refundOrderProtocol = new TMultiplexedProtocol(
                    protocol, "RefundOrderService");
            RefundOrderService.Client refundClient = new RefundOrderService.Client(refundOrderProtocol);
            
            //分账系统服务模块
            TMultiplexedProtocol ledgerSysOrderProtocol = new TMultiplexedProtocol(
                    protocol, "LedgerSysOrderService");
            LedgerSysOrderService.Client ledgerSysOrderClient = new LedgerSysOrderService.Client(ledgerSysOrderProtocol);
            
            //业务管理服务模块
            TMultiplexedProtocol busiSysOrderProtocol = new TMultiplexedProtocol(
                    protocol, "BusiSysOrderService");
            BusiSysOrderService.Client busiSysOrderClient = new BusiSysOrderService.Client(busiSysOrderProtocol);
            
            //打开端口,开始调用
            transport.open();
            
            //报障退款接口
            Map<String,String> reqMap=new HashMap<String,String>();
            reqMap.put("bid", "150915000037");
            reqMap.put("number", "1224582201201502116199911549");
            reqMap.put("paytype", "1000001");
            reqMap.put("payid", "1502261738563685");
            reqMap.put("samount", "5.00");
            reqMap.put("commision", "2.05");
            reqMap.put("profit", "1.5");
            reqMap.put("giveMoney", "0.03");
            reqMap.put("thirdUid", "");
            reqMap.put("bidtype", "15");
            Map<String,String> resMap=refundClient.payFailRefundOrder(reqMap);
            System.out.println("payFailRefundOrder resMap::"+resMap);
            
            //已分账退款接口
//            Map<String,String> reqMap=new HashMap<String,String>();
//            reqMap.put("bid","150906000060");
//            Map<String,String> resMap=ledgerSysOrderClient.hasLedgerRefundOrder(reqMap);
//            System.out.println("hasLedgerRefundOrder resMap::"+resMap);
            
            //故障单恢复接口
//          Map<String,String> reqMap=new HashMap<String,String>();
//          reqMap.put("bid","150710000071");
//          Map<String,String> resMap=busiSysOrderClient.payFailRecoverOrder(reqMap);
//          System.out.println("payFailRecoverOrder resMap::"+resMap);  
            
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
