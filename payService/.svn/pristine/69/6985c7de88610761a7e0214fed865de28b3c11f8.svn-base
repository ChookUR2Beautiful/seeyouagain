package com.xmniao.test;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import com.xmniao.thrift.ledger.SynthesizeService.Client;
import com.xmniao.thrift.ledger.SynthesizeService;



/**
 * 客户端测试类
 * 
 * @author LiBingBing
 * @version [版本号, 2014-11-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class WalletThriftClient {
	// 服务端的IP地址
	private static final String IP_NUMBER = "localhost";
	// 服务端的端口号
	private static final int PORT = 7911;

	private static TTransport transport = null;

	public static void main(String[] args) {
		try {
			// 设置调用的服务地址为本地，端口为 7911
			transport = new TSocket(IP_NUMBER, PORT);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
					protocol, "SynthesizeService");
			SynthesizeService.Client client = new Client(ManagerProtocol);

			// 打开端口,开始调用
			transport.open();

			long sdate = System.currentTimeMillis();
			
			//验证密码
			int test = client.checkWalletPwd("1234", "e10adc3949ba59abbe56e057f20f883e", 3);
			System.out.println(test);
			
//			修改密码
//			int test = client.updateWalletPwd("1001", "8888888", 1);
//			System.out.println(test);

//			Map<String, String> a = new HashMap<String,String>();
//			a.put("orderNumber", "123493");
//			a.put("status", "9");
//			a.put("userType", "1");
//			int test = client.updateWithdrawalsRecordState(a);
//			System.out.println(test);
			
			long edate = System.currentTimeMillis();

			long result = edate - sdate;

			System.out.println("程序运行时间： " + result + "ms ");
		} catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			transport.close();
		}
	}
}