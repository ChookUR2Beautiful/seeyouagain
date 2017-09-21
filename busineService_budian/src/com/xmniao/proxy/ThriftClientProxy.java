/**
 * 
 */
package com.xmniao.proxy;

import java.lang.reflect.Constructor;

import javax.annotation.Resource;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * 
 * 项目名称：busineService_manor
 * 
 * 类名称：ThriftClientProxy
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年7月4日 下午6:08:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public class ThriftClientProxy {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ThreadLocal<TTransport> transportThread = new ThreadLocal<>();
	
	@Autowired
	private String transLedgerIP;
	
	@Resource(name = "transLedgerPort")
	private int transLedgerPort;
	
	@Autowired
	private String ledgerIP;
	
	@Resource(name = "ledgerPort")
	private int ledgerPort;
	
	/**
	 * 
	 * 方法描述：获取支付服务Client方法 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年7月5日下午2:06:03 <br/>
	 * @param serviceClazz
	 * @return
	 */
	public Object getPayServiceClient(Class<?> serviceClazz) {
		return this.thriftServiceOpen(serviceClazz, transLedgerIP, transLedgerPort);
	}
	
	public void returnPayServiceClient() {
		this.thriftServiceClose();
	}
	
	/**
	 * 
	 * 方法描述：获取分账服务Client方法 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年7月5日下午2:06:03 <br/>
	 * @param serviceClazz
	 * @return
	 */
	public Object getLedgerServiceClient(Class<?> serviceClazz) {
		return this.thriftServiceOpen(serviceClazz, ledgerIP, ledgerPort);
	}
	
	public void returnLedgerService() {
		this.thriftServiceClose();
	}
	
	private Object thriftServiceOpen(Class<?> serviceClazz,String serviceIp,Integer servicePort) {
        TTransport transport = null;
        try
        {
            //调用分账系统支付服务的IP和端口号
            transport = new TSocket(serviceIp, servicePort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账系统支付服务的综合服务接口模块
            TMultiplexedProtocol multiplexedProtocol = new TMultiplexedProtocol(protocol, serviceClazz.getSimpleName());
            Class<?> clazz = Class.forName(serviceClazz.getCanonicalName()+"$Client");
            System.out.println(multiplexedProtocol.getClass());
            Constructor<?> constructor = clazz.getConstructor(TProtocol.class);
            Object client = constructor.newInstance(multiplexedProtocol);
            //打开端口,开始调用
            transport.open();
            
            transportThread.set(transport);
            return client;
        }catch(Exception e){
        	log.error("",e);
        	this.thriftServiceClose();
        }
        return null;
	}
	
	private void thriftServiceClose() {
		try{
			TTransport transport= transportThread.get();
			if(transport!=null){
				transportThread.remove();
				if(transport.isOpen()){
					transport.close();
				}
			}
		}catch(Exception e){
			log.error("操作异常");
		}
	}
	
}
