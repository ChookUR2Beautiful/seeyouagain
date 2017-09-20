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

import com.xmniao.thrift.busine.OrderService;
import com.xmniao.thrift.busine.OrderService.Client;




/**
 * 测试李兵兵方面业务系统退款接口
 * @author ChenBo
 *
 */
public class busineThriftClient {
    //服务端的IP地址
    private static final String IP_NUMBER = "192.168.20.245";
    //服务端的端口号
    private static final int PORT = 7911;
    
    private static TTransport transport = null;
    
    public static void main(String[] args)
    {
        try
        {
        	System.out.println("-----1-----");
        	// 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(IP_NUMBER, PORT);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol, "OrderService");
            OrderService.Client client = new Client(ManagerProtocol);

            System.out.println("-----2----");
            //打开端口,开始调用
            transport.open();
            long sdate = System.currentTimeMillis();
            
            System.out.println("-----3----");     
            boolean flag = true;
            
            Map<String,String> paramMap = new HashMap<String,String>();
            
    		paramMap.put("bid", "141210000447");
    		if(flag){	//退款成功
    			paramMap.put("orderStatus", 5+"");
    			paramMap.put("refundStatus", 9+"");
    			paramMap.put("remarks", "");
    		}
    		else{	//退款失败
    			paramMap.put("orderStatus", 4+"");
    			paramMap.put("refundStatus", 10+"");
    			paramMap.put("remarks", "钱包异常");
    		}
    		
            Map resultMap = client.refundOrderInfo(paramMap);
            
            System.out.println("msg:"+resultMap.toString());
            
            long edate = System.currentTimeMillis();
            
            long result = edate - sdate;
            
           System.out.println("程序运行时间： "+result+"ms ");
        } catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
        	transport.close();
        }
    }
}
