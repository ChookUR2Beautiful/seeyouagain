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
import org.apache.thrift.transport.TTransportException;

import com.umpay.api.log.SysOutLogger;
import com.xmniao.common.UpdateLedgerSystem;
import com.xmniao.thrift.ledger.PayAcountService;
import com.xmniao.thrift.ledger.PayAcountService.Client;

/**
 * 客户端测试类
 * 
 * @author LiBingBing
 * @version [版本号, 2014-11-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ThriftClient {
    // 服务端的IP地址
//    private static final String IP_NUMBER = "192.168.50.117";
    private static final String IP_NUMBER = "192.168.30.192";
    // 服务端的端口号
    private static final int PORT = 7912;

    private static TTransport transport = null;

    public static void main(String[] args) {
	try {
	    transport = new TSocket(IP_NUMBER, PORT);
	    TFramedTransport frame = new TFramedTransport(transport);
	    // 设置传输协议为 TBinaryProtocol
	    TProtocol protocol = new TBinaryProtocol(frame);
	    TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
		    protocol, "PayAcountService");
	    PayAcountService.Client client = new Client(ManagerProtocol);
	    // 打开端口,开始调用
	    transport.open();

	    try {
		String orderNumber = String.valueOf("333333382");//orderNumber
		String status = String.valueOf("1");//status
		
		//修改分账服务
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderNumber", orderNumber);
		map.put("status", status);
		map.put("Message", "成功");
		map.put("platform_code", System.currentTimeMillis()+"");
		int result = client.updateWithdrawalsRecordState(map);

		System.out.println(result);
		
		
	    } catch (TException e) {
		e.printStackTrace();
	    }
	} catch (TTransportException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    transport.close();
	}
    }
}