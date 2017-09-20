package com.xmniao.xmn.core.thrift.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;



public class ThriftClientUtil {

	private Map<String, String> configMaps;

	private TTransport transport = null;

	public Map<String, String> getConfigMaps() {
		return configMaps;
	}

	public void setConfigMaps(Map<String, String> configMaps) {
		this.configMaps = configMaps;
	}

	/**
	 * 获取 Thrfit 客户端调用 类 TMultiplexedProtocol
	 * 
	 * @param serviceName
	 * @return
	 * @throws Exception
	 */
	public TMultiplexedProtocol getThriftClient(String serviceName) throws Exception {
		TMultiplexedProtocol managerProtocol = null;
		try {
			String ip = this.configMaps.get(serviceName + "_ip").toString();
			Integer port = Integer.valueOf(this.configMaps.get(serviceName + "_port"));
			transport = new TSocket(ip, port, 5000);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			managerProtocol = new TMultiplexedProtocol(protocol, serviceName);
			if (!transport.isOpen()) {
				transport.open();
			}
		} catch (TTransportException e) {
			//throw new XMNException(3, e.getMessage());
			throw new Exception();
		}

		return managerProtocol;

	}
	public Map<String,String> getConfigMapIP(String serviceName){
		Map<String,String> map=new HashMap<String,String>();
		map.put("ip", this.configMaps.get(serviceName + "_ip").toString());
		map.put("port", this.configMaps.get(serviceName + "_port"));
		return map;
	}

	/**
	 * 开启端口
	 * 
	 * @throws TTransportException
	 */
	public void open() throws TTransportException {
		if (!transport.isOpen()) {
			transport.open();
		}
	}

	/**
	 * 关闭端口
	 * 
	 * @throws TTransportException
	 */
	public void close() {
		if (transport.isOpen()) {
			transport.close();
		}
	}
}
