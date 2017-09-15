package com.xmniao.xmn.core.thrift.client.common;

import org.apache.thrift.transport.TSocket;

public interface ThriftConnectionProvider {
	
	/**
	* 取链接池中的一个链接
	* @return TSocket
	*/
	public TSocket getConnection();
	
	/**
	* 返回链接
	* @param socket
	*/
	public void returnCon(TSocket socket);
	
}
