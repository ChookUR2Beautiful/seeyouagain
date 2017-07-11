package com.xmn.saas.base;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftBuilder {
	private static ThreadLocal<TTransport> TRANSPORT =  new ThreadLocal<TTransport>();
	
	/**
	 * @param host IP
	 * @param port 端口
	 * @param serviceName 服务名称
	 * @param clientClass 服务接口客户端类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T build(String host,int port,String serviceName,Class<T> clientClass) throws Exception{
		TTransport transport = new TSocket(host,port);
		
		TFramedTransport framed = new TFramedTransport(transport);
		TProtocol protocol = new TBinaryProtocol(framed);
        TMultiplexedProtocol multiplexed =  new TMultiplexedProtocol(protocol, serviceName);
        
        T client = clientClass.getDeclaredConstructor(TProtocol.class).newInstance(multiplexed);
        
        TRANSPORT.set(transport);
        
        return client;
	}
	
	public static void open() throws Exception{
		if(isExist() && !TRANSPORT.get().isOpen()){
			TRANSPORT.get().open();
		}
	}
	
	public static void close(){
		if(isExist()){
			if(TRANSPORT.get().isOpen()){
				TRANSPORT.get().close();
			}
			
			TRANSPORT.remove();
		}
	}
	
	public static boolean isExist(){
		return TRANSPORT.get() != null;
	}
	

}
