package com.xmniao.xmn.core.thrift.client.common;

import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionManager{
	
	/** 日志记录器 */
	public Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	/** 连接提供池 */
	public ThriftConnectionProvider thriftConnectionProvider;
	
	public ConnectionManager(){
		
	}
	
	public ConnectionManager(ThriftConnectionProvider thriftConnectionProvider){
		this.thriftConnectionProvider = thriftConnectionProvider;
	}
	
	/**
	* 取socket
	* @return TSocket
	*/
	public TSocket getSocket(){
		TSocket socket = null;
		try{
			socket = thriftConnectionProvider.getConnection();
		}catch (Exception e){
			logger.error("error ConnectionManager.invoke()", e);
			throw e;
		}finally{
		}
		return socket;
	}

	public ThriftConnectionProvider getThriftConnectionProvider() {
		return thriftConnectionProvider;
	}

	public void setThriftConnectionProvider(
			ThriftConnectionProvider thriftConnectionProvider) {
		this.thriftConnectionProvider = thriftConnectionProvider;
	}

}

