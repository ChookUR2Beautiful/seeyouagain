package com.xmniao.proxy;

import java.lang.reflect.Constructor;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 服务端的代理工厂类 用来处理Thrift协议，获取服务端的服务接口
 * 
 * @author LiBingBing
 * @version [版本号, 2014-11-6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ThriftProxyFactory implements Runnable {
	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(ThriftProxyFactory.class);

	/**
	 * 服务端代理类
	 */
	private List<ThriftServiceProxy> proxys;

	/**
	 * 工作线程默认5
	 */
	private Integer workerThreads = 0;

	/**
	 * 服务端的端口
	 */
	private Integer port;

	/**
	 * 可以实现一个端口调用多个服务接口
	 */
	private TMultiplexedProcessor processor = null;

	/**
	 * 初始化TServer
	 */
	private TServer server;

	/**
	 * 构造方法默认端口7910
	 */
	public ThriftProxyFactory() {
		this.setPort(port == null ? 7910 : port);
		this.setWorkerThreads(workerThreads == null ? 5 : workerThreads);
	}

	@Override
	public void run() {
		log.info("服务初始化 ...");
		if (null != proxys && !proxys.isEmpty()) {
			try {
				processor = new TMultiplexedProcessor();
				TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(
						port);
				TProcessor tProcessor = null;
				// 采用反射机制来循环遍历多个服务接口类
				for (ThriftServiceProxy serverProxy : proxys) {
					// 获取接口类中的Iface接口
					Class<?> Processor = Class.forName(serverProxy
							.getServiceInterface() + "$Processor");
					Class<?> Iface = Class.forName(serverProxy
							.getServiceInterface() + "$Iface");
					Constructor<?> con = Processor.getConstructor(Iface);
					// 获取接口实现类
					tProcessor = (TProcessor) con.newInstance(serverProxy
							.getServiceImpl());
					// 获取spring配置文件中的serviceName接口类名字
					String serviceName = serverProxy.getServiceName();
					// 开始注册多个接口的服务实现类
					processor.registerProcessor(serviceName, tProcessor);
				}

				TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory(
						true, true);
				TThreadedSelectorServer.Args serverArgs = new TThreadedSelectorServer.Args(
						serverTransport);
				serverArgs.processor(processor);
				serverArgs.protocolFactory(proFactory);
				serverArgs.workerThreads(workerThreads);// 设置工作线程

				server = new TThreadedSelectorServer(serverArgs);

				log.info("服务已启动...  工作线程数:" + workerThreads + " 端口:" + port);
				server.serve();
			} catch (TTransportException e) {
				log.error("服务端口已占用..." + e);
			} catch (Exception e) {
				log.error("服务启动异常..." + e);
			}
		}
	}

	public int getWorkerThreads() {
		return workerThreads;
	}

	public void setWorkerThreads(int workerThreads) {
		this.workerThreads = workerThreads;
	}

	public List<ThriftServiceProxy> getProxys() {
		return proxys;
	}

	public void setProxys(List<ThriftServiceProxy> proxys) {
		this.proxys = proxys;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
