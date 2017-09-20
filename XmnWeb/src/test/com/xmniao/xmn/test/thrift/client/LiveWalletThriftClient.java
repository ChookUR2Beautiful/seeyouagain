package com.xmniao.xmn.test.thrift.client;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;



import com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService;
import com.xmniao.xmn.core.thrift.service.liveService.ResponseData;
import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService;
import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService.Client;
import com.xmniao.xmn.core.util.PropertiesUtil;




/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveWalletThriftClient
 * 
 * 类描述： 直播钱包ThriftClient
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-5 上午9:45:58 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class LiveWalletThriftClient {
    //thrift配置文件
	private static final String PROPERTIES_FILE_NAME = "conf_thrift.properties";
	// 服务端的IP地址
 	private static String IP_NUMBER = null;
 	// 服务端的端口号 
 	private static int PORT = -1;
    
    private static TTransport transport = null;
    
	static {
		Properties props = PropertiesUtil.getCustomProperties(PROPERTIES_FILE_NAME);
		IP_NUMBER = props.getProperty("thrift.pay.ip");
		PORT = Integer.valueOf(props.getProperty("thrift.pay.port"));
	}
    
    public static void main(String[] args)
    {
        try
        {
        	System.out.println("-----test start-----");
        	// 设置调用的服务地址为本地，端口为 7911
            transport = new TSocket(IP_NUMBER, PORT);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
//            SynthesizeService.Client client = new Client(ManagerProtocol);
            
            LiveWalletService.Client liveWalletClient = new LiveWalletService.Client(ManagerProtocol);

            //打开端口,开始调用
            transport.open();
            
            
            Map<String, String> walletMap = new HashMap<String,String>();
            walletMap.put("id", "4");
            walletMap.put("uid", "340709");
            walletMap.put("status", "2");
			ResponseData responseData = liveWalletClient.changeWalletState(walletMap);
			int state = responseData.state;
			String msg = responseData.msg;
			System.out.println(state+msg);
            
        } catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
        	transport.close();
        }
    }
    
}
