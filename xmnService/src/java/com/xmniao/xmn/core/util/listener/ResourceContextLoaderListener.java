package com.xmniao.xmn.core.util.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 * web容器监听
 * @author Administrator
 *
 */
public class ResourceContextLoaderListener implements ServletContextListener {
	//日志
	private final Logger log = Logger.getLogger(ResourceContextLoaderListener.class);
	
	
	
	/**
	 * web容器销毁
	 * @param sce
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

	/**
	 * web容器启动
	 * @param sce
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//TODO 加载一些缓存，静态，常量等数据
		
	}
	
	

}
