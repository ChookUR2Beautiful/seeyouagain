package com.xmniao.xmn.core.thrift.client.common.factory;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.xmniao.xmn.core.thrift.client.common.config.ThriftPoolConfig;

public class ThriftConnectionSpringFactory extends ThriftConnectionFactory implements InitializingBean, DisposableBean{

	/**连接池配置对象*/
	ThriftPoolConfig thriftPoolConfig = null;

	/** 服务的IP地址 */
	private String ip;
	/** 服务的端口 */
	private int port;
	/** 连接超时配置 */
	private int conTimeOut;
	/** 可以从缓存池中分配对象的最大数量 */
	private int maxActive = GenericObjectPool.DEFAULT_MAX_ACTIVE;
	/** 缓存池中最大空闲对象数量 */
	private int maxIdle = GenericObjectPool.DEFAULT_MAX_IDLE;
	/** 缓存池中最小空闲对象数量 */
	private int minIdle = GenericObjectPool.DEFAULT_MIN_IDLE;
	/** 阻塞的最大数量 */
	private long maxWait = GenericObjectPool.DEFAULT_MAX_WAIT;

	/** 从缓存池中分配对象，是否执行PoolableObjectFactory.validateObject方法 */
	private boolean testOnBorrow = GenericObjectPool.DEFAULT_TEST_ON_BORROW;
	private boolean testOnReturn = GenericObjectPool.DEFAULT_TEST_ON_RETURN;
	private boolean testWhileIdle = GenericObjectPool.DEFAULT_TEST_WHILE_IDLE;

	@Override
	public void destroy() throws Exception {
		try{
			if(getObjectPool() != null){
				getObjectPool().close();
			}
		}catch (Exception e){
			throw new RuntimeException("erorr destroy()", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(thriftPoolConfig == null){
			thriftPoolConfig = new ThriftPoolConfig();
			thriftPoolConfig.setMaxActive(maxActive);
			thriftPoolConfig.setMaxIdle(maxIdle);
			thriftPoolConfig.setMinIdle(minIdle);
			thriftPoolConfig.setMaxWait(maxWait);
			thriftPoolConfig.setTestOnBorrow(testOnBorrow);
			thriftPoolConfig.setTestOnReturn(testOnReturn);
			thriftPoolConfig.setTestWhileIdle(testWhileIdle);
			
			thriftPoolConfig.setIp(ip);
			thriftPoolConfig.setPort(port);
			thriftPoolConfig.setConTimeOut(conTimeOut);
		}
		super.createPool();
	}
	
	@Override
	public ThriftPoolConfig getThriftPoolConfig() {
		
		return thriftPoolConfig;
	}

	public void setThriftPoolConfig(ThriftPoolConfig thriftPoolConfig) {
		this.thriftPoolConfig = thriftPoolConfig;
	} 	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getConTimeOut() {
		return conTimeOut;
	}
	public void setConTimeOut(int conTimeOut) {
		this.conTimeOut = conTimeOut;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public long getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	public boolean getTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public boolean getTestOnReturn() {
		return testOnReturn;
	}
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	public boolean getTestWhileIdle() {
		return testWhileIdle;
	}
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
}
