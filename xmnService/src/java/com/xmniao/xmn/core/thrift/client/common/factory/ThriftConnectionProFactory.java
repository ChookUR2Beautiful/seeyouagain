package com.xmniao.xmn.core.thrift.client.common.factory;

import com.xmniao.xmn.core.thrift.client.common.config.ThriftPoolConfig;

public class ThriftConnectionProFactory extends ThriftConnectionFactory{

	/**连接池配置对象*/
	private ThriftPoolConfig connectionPoolConfig = null;
	
	private ThriftConnectionProFactory(){
		
	}
	
	public ThriftConnectionProFactory(ThriftPoolConfig config){
		this.connectionPoolConfig = config;
		try {
			super.createPool();							/**调用该方法创建连接池对象*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ThriftPoolConfig getThriftPoolConfig() {		
		return connectionPoolConfig;
	}

}
