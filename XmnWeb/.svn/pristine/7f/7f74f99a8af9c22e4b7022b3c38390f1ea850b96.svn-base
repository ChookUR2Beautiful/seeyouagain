package com.xmniao.xmn.core.thrift.client.common.factory;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.transport.TSocket;

import com.xmniao.xmn.core.thrift.client.common.ThriftConnectionProvider;
import com.xmniao.xmn.core.thrift.client.common.config.ThriftPoolConfig;

public abstract class ThriftConnectionFactory implements ThriftConnectionProvider{
	
	/** 对象缓存池 */
	private ObjectPool objectPool = null;
	
	/**连接池配置对象*/
	public abstract ThriftPoolConfig getThriftPoolConfig();
	
	@Override
	public TSocket getConnection() {
		try{
			//从对象池取对象
			TSocket socket = (TSocket) objectPool.borrowObject();
			return socket;
		}catch (Exception e){
			throw new RuntimeException("error getConnection()", e);
		}
	}

	@Override
	public void returnCon(TSocket socket) {
		try{
			//将对象放回对象池
			objectPool.returnObject(socket);
		}catch (Exception e){
			throw new RuntimeException("error returnCon()", e);
		}
	}
	
	public void createPool() throws Exception{
		ThriftPoolConfig poolConfig = getThriftPoolConfig();
		if(poolConfig != null){
			// 对象池
			objectPool = new GenericObjectPool();
			((GenericObjectPool) objectPool).setMaxActive(poolConfig.getMaxActive());
			((GenericObjectPool) objectPool).setMaxIdle(poolConfig.getMaxIdle());
			((GenericObjectPool) objectPool).setMinIdle(poolConfig.getMinIdle());
			((GenericObjectPool) objectPool).setMaxWait(poolConfig.getMaxWait());
			((GenericObjectPool) objectPool).setTestOnBorrow(poolConfig.getTestOnBorrow());
			((GenericObjectPool) objectPool).setTestOnReturn(poolConfig.getTestOnReturn());
			((GenericObjectPool) objectPool).setTestWhileIdle(poolConfig.getTestWhileIdle());
			((GenericObjectPool) objectPool).setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_BLOCK);			
			// 设置factory
			ThriftPoolableObjectFactory thriftPoolableObjectFactory = new ThriftPoolableObjectFactory(
					poolConfig.getIp(), poolConfig.getPort(), poolConfig.getConTimeOut());
			objectPool.setFactory(thriftPoolableObjectFactory);
		}else{
			throw new Exception("连接池对象为空");
		}
	}

	public ObjectPool getObjectPool() {
		return objectPool;
	} 
}
