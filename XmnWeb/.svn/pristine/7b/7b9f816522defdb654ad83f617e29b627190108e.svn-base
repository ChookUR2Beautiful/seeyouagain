package com.xmniao.xmn.core.thrift.client.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

import com.xmniao.xmn.core.thrift.client.common.ConnectionManager;

public class ThriftClientProxy {

	/**
	 * 服务接口
	 */
	private String service;
	
	/**
	 * 服务名称
	 */
	private String serviceName;
	
	/**
	 * 连接池管理类
	 */
	private ConnectionManager connectionManager;
	
	/**
	 * TSocket
	 */
	ThreadLocal<TSocket> socketThreadSafe = new ThreadLocal<TSocket>();

	private Logger log = Logger.getLogger(getClass());
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getClient() {
		Object object = null;
		TFramedTransport transport = null;
		try {
			TSocket socket;
			//取Socket
			try{
			socket = connectionManager.getSocket();
			} catch(Exception e){
				throw e;
			}
			socketThreadSafe.set(socket);
			// 设置传输协议为 TBinaryProtocol
			transport = new TFramedTransport(socket);
	        TProtocol protocol = new TBinaryProtocol(transport);
	        
			if(serviceName == null || "".equals(serviceName.trim())){
				serviceName = service.substring(service.lastIndexOf(".")+1);
			}
			
			/**一个接口多个服务*/
			TProtocol multiplexedProtocol = new TMultiplexedProtocol(protocol,serviceName);

			Class client = Class.forName(getService() + "$Client");

			Constructor con = client.getConstructor(TProtocol.class);
			object = con.newInstance(multiplexedProtocol);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SecurityException e) {			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}finally{
			/*if(transport != null){
				try {
					transport.flush();
				} catch (TTransportException e) {
					e.printStackTrace();
				}
				transport.close();
			}*/
		}
		return object;
	}
	
	public void returnCon() {
		try{
			TSocket socket = socketThreadSafe.get();
			if(socket != null){
				socket.close();
				//将对象放回对象池
				connectionManager.thriftConnectionProvider.returnCon(socket);
				socketThreadSafe.remove();
				this.log.info("关闭连接成功！并将对象放回对象池。");
			}
		}catch (Exception e){
			throw new RuntimeException("错误 returnCon()", e);
		}
	}
	
	public <T> T doClient(String methodName, Map<String, String> map) throws Exception {
		Object client = getClient();
		try {
			Class<?> clz = client.getClass();
			Method method = clz.getMethod(methodName, Map.class);
			return (T) method.invoke(client, map);
		} catch (Exception e) {
			log.error("调用支付服务方法失败", e);
			throw e;
		} finally {
			returnCon();
		}

	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
}
