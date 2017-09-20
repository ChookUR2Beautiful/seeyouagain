package com.xmniao.xmn.test.thrift.client;


import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.xmniao.xmn.core.thrift.service.ledgerService.ResponseSplitMap;
import com.xmniao.xmn.core.thrift.service.ledgerService.SplitService;
import com.xmniao.xmn.core.thrift.service.ledgerService.SplitService.Client;




/**
 * 测试分账算法接口
 * @author ChenBo
 *
 */
public class LedgerThriftClient {
    //服务端的IP地址
	private static final String IP_NUMBER = "192.168.50.244";
    //private static final String IP_NUMBER = "localhost";
    //服务端的端口号
    private static final int PORT = 7912;
    
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
            TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(protocol, "SplitService");
            SplitService.Client client = new Client(ManagerProtocol);

            //打开端口,开始调用
            transport.open();
            
//            double saasPricee, boolean isReturn, int type, double discount, boolean isSellerArea,
//            boolean isAgentscope, double purchaseDiscount, boolean isOneLevelXmer, boolean isTwoLevelXmer
            ResponseSplitMap  result = client.saasLedger(880.0,true,1,0.80,true,true,0.90,true,true);
            
            System.out.println("结果:"+result);
            
        } catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
        	transport.close();
        }
    }
    
}
