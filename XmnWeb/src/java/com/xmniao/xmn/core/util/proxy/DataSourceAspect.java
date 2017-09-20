package com.xmniao.xmn.core.util.proxy;

import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.holder.DynamicDataSourceHolder;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DataSourceAspect
 * 
 * 类描述：数据读写分离处理
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-02-10 10:00:17
 * 
 */
public class DataSourceAspect {
		private final Logger log = Logger.getLogger(getClass());
		private static final Class<DataSource> DATA_SOURCE_CLASS = DataSource.class;
		private static final String PROPERTIES_FILE_NAME="conf_config.properties";
		private static final String PROPERTIES__NAME="isOpen";
		private static final String DATASOURCE_JOINT_MASTER="master";
		private static final String DATASOURCE_JOINT_SLAVE="slave";
		private static  boolean IS_OPEN;//是否开启主从库查询
		
		static{
			Properties props = PropertiesUtil.getCustomProperties(PROPERTIES_FILE_NAME);
			String isopen = props.getProperty(PROPERTIES__NAME);
			IS_OPEN =  Boolean.valueOf(isopen).booleanValue();
		}
		
	    public void before(JoinPoint point)
	    {	
	    	if(IS_OPEN){
		    	Object target =point.getTarget();
		        Class<?>[] classz = target.getClass().getInterfaces();
		        String method = point.getSignature().getName();
		        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		        try {		  
		        	Method m = classz[0].getMethod(method, parameterTypes);
		            if (m != null && m.isAnnotationPresent(DATA_SOURCE_CLASS)) {
		                DataSource data = m .getAnnotation(DATA_SOURCE_CLASS);
		                //设置主从
		                DynamicDataSourceHolder.putDataSource(data.value());
		            }
		        } catch (Exception e) { 
		        	DynamicDataSourceHolder.remove();
//		        	log.error("切换数据源异常", e);
		       }
	    	}else{
		    	Object target =point.getTarget();
		        Class<?>[] classz = target.getClass().getInterfaces();
		        String method = point.getSignature().getName();
		        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		        try {		  
		        	Method m = classz[0].getMethod(method, parameterTypes);
		            if (m != null && m.isAnnotationPresent(DATA_SOURCE_CLASS)) {
		                DataSource data = m .getAnnotation(DATA_SOURCE_CLASS);
		                if(isMSSource(data)){
		                	//关闭主从数据源配置时，业务库使用默认业务库数据源
		                }else{
			                //设置主从
			                DynamicDataSourceHolder.putDataSource(data.value());
		                }
		            }
		        } catch (Exception e) { 
		        	DynamicDataSourceHolder.remove();
//		        	log.error("切换数据源异常", e);
		       }
	    	}
	    }
	    
	    /*
	     * 是否使用业务库的主/从数据源
	     */
		private boolean isMSSource(DataSource data){
			String dataName = data.value();
			if(DATASOURCE_JOINT_MASTER.equals(dataName) || DATASOURCE_JOINT_SLAVE.equals(dataName)){
				return true;
			}
			return false;
		}
}
