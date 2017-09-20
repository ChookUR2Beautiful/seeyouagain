package com.xmniao.xmn.core.util.listener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionCookieConfig;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.system_settings.dao.AuthorityDao;
import com.xmniao.xmn.core.system_settings.dao.RoleAreaDao;
import com.xmniao.xmn.core.util.SpringBeanUtil;
import com.xmniao.xmn.core.util.handler.AreaHandler;
import com.xmniao.xmn.core.util.handler.AuthorityAreaHandler;
import com.xmniao.xmn.core.util.handler.AuthorityHandler;
import com.xmniao.xmn.core.util.holder.ExecutorServiceHolder;



/**
 * 
*    
* 项目名称：XmnWeb   
* 类名称：ResourceContextLoaderListener   
* 类描述：   项目启动时，预先把区域及行业类别数据查出保存到共享内存中缓存起来，java类或是页面可以直接读取，项目关闭时销毁
* 创建人：Administrator   
* 创建时间：2015年11月12日 上午11:44:02   
* @version    
*
 */
public class ResourceContextLoaderListener implements ServletContextListener {

	private final Logger log = Logger.getLogger(ResourceContextLoaderListener.class);
	
	private final static String AREADAO = "areaDao";
	
	private final static String AUTHORITY="authorityDao";
	
	
	private final static String ROLEAREADAO ="roleAreaDao";
	
	
	private ExecutorServiceHolder executorServiceHolder;
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//sce.getServletContext().getSessionCookieConfig().setMaxAge(0);
		executorServiceHolder.destroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		SessionCookieConfig sessionCookie = sce.getServletContext().getSessionCookieConfig();
		sessionCookie.setPath(sc.getContextPath());
		sessionCookie.setHttpOnly(true);
		sessionCookie.setSecure(false);
		
		executorServiceHolder = new ExecutorServiceHolder();
		executorServiceHolder.init();
		
		SpringBeanUtil.setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(sc));
		
		AreaDao areaDao = (AreaDao)SpringBeanUtil.getBean(AREADAO);
		AuthorityDao authorityDao = (AuthorityDao)SpringBeanUtil.getBean(AUTHORITY);
		log.info("开始处理资源信息--");
		Long s =System.currentTimeMillis();
		AuthorityHandler.getAuthorityHanlde().authorityHanlde(authorityDao);
		Long e =System.currentTimeMillis();
		log.info("处理资源信息结束--耗时--"+ (e-s)+" ms");
		
		
		log.info("开始处理区域信息--");
			 s =System.currentTimeMillis();
			AreaHandler.getAreaHandler().areaHanlde(areaDao);
			 e =System.currentTimeMillis();
		log.info("处理区域信息结束--耗时--"+ (e-s)+" ms");
		RoleAreaDao roleAreaDao = (RoleAreaDao)SpringBeanUtil.getBean(ROLEAREADAO);
		AuthorityAreaHandler.getInstance().handler(roleAreaDao);
		
	}
	
	

}
