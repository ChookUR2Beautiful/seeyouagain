/**
 * 2016年9月2日 上午9:30:20
 */
package com.xmniao.xmn.core.util;

import java.io.IOException;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类描述：调用业务接口公共类
 * @创建人： yeyu
 * @创建时间 2016年9月2日 上午9:30:20
 * @version
 */
@Service
public class ThriftBusinessUtil {
	
	private    String IP_NUMBER =null;

	private    Integer port = 0;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * TTransport   new ThreadLocal()
	 */
	 private static ThreadLocal<TTransport> socketThreadSafe = new ThreadLocal<TTransport>();
	/**
	 * 
	* @Title: getProtocol
	* @Description: 调用支付接口初始化
	* @return TMultiplexedProtocol
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public  TMultiplexedProtocol getProtocol(String ServiceStr) throws NumberFormatException, IOException{
		IP_NUMBER=propertiesUtil.getValue("ip_number_business", "conf_live.properties");
		port=Integer.parseInt(propertiesUtil.getValue("port_business", "conf_live.properties"));
		System.out.println("===start==");
		TTransport transport = new TSocket(IP_NUMBER,port);
		//将变量放入 threadLocal
		socketThreadSafe.set(transport);
		TFramedTransport frame = new TFramedTransport(transport);
		TProtocol protocol = new TBinaryProtocol(frame);
		TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(protocol,ServiceStr);
		
		return tMultiplexedProtocol;
	}
	/**
	 * 
	* @Title: openTransport
	* @Description: 打开Transport
	* @return void
	 */
	public  void openTransport() throws TTransportException{
			//从当前的threadLocal获取线程
			TTransport transport = socketThreadSafe.get();
			if(transport != null){
				transport.open();
			}
	}
	/**
	 * 
	* @Title: coloseTransport
	* @Description: 关闭TTransport
	* @return void
	 */
	public  void coloseTransport (){
		TTransport transport = socketThreadSafe.get();
		if(transport != null){
			transport.close();
			//移除线程
			socketThreadSafe.remove();
		}
	}
}
