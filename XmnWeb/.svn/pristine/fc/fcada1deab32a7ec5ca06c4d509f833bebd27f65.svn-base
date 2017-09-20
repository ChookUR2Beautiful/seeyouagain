package com.xmniao.xmn.test.thrift.client;


import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;



import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService;
import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService.Client;




/**
 * 测试分账算法接口
 * @author ChenBo
 *
 */
public class PayThriftClient {
    //服务端的IP地址
//	private static final String IP_NUMBER = "192.168.50.244";
	private static final String IP_NUMBER = "192.168.50.110";
    //private static final String IP_NUMBER = "localhost";
    //服务端的端口号
//    private static final int PORT = 7912;
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
            TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol, "SynthesizeService");
            SynthesizeService.Client client = new Client(ManagerProtocol);

            //打开端口,开始调用
            transport.open();
            
            Map<String,String> paramMap=new HashMap<String,String>();
	 			
	 			paramMap.put("uId", String.valueOf("999"));
	 			paramMap.put("userType", "1");//用户类型 1用户 	2商家	3合作商
	 			
				System.out.println("添加寻蜜鸟用户钱包开始，uid：" + String.valueOf("999") + ",userType:1,password:" + paramMap.get("password") + ",name:" + "999");
				Map<String,String> resultMap=client.addWalletMap(paramMap);
            
            System.out.println("结果:"+resultMap.toString());
            
        } catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
        	transport.close();
        }
    }
    
}
