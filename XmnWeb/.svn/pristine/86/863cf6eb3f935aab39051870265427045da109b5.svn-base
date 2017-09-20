package com.xmniao.xmn.core.util;

import org.springframework.context.ApplicationContext;

public final class SpringBeanUtil{

	private static ApplicationContext context=null;
	
	 /**
     * 通过spring配置文件中配置的bean id取得bean对象
     * @param id spring bean ID值
     * @return spring bean对象
     */
    public static Object getBean(String id) {
        return context.getBean(id);
    }
	
	public static void setApplicationContext(ApplicationContext applicationContext) {
		if(context==null){
			context = applicationContext;
		}
	}

}
