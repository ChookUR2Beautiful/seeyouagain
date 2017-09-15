/**
 * 2016年3月31日 上午10:26:57
 */
package com.xmniao.xmn.core.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：saasService
 * @类名称：BeanMapUtil
 * @类描述：Map 和 java bean相互转换
 * @创建人： zhangchangyuan
 * @创建时间 2016年3月31日 上午10:26:57
 * @version
 */
public class BeanMapUtil {
	
	/**
	 * 
	* @Title: convertBean
	* @Description: 将java bean转为Map 
	* @return Map<String,Object>
	 */
	public static Map<Object, Object> convertBean(Object bean) 
            throws IntrospectionException, IllegalAccessException, InvocationTargetException { 
        Class type = bean.getClass(); 
        Map<Object, Object> returnMap = new HashMap<Object, Object>(); 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (!propertyName.equals("class")) { 
                Method readMethod = descriptor.getReadMethod(); 
                Object result = readMethod.invoke(bean, new Object[0]); 
                if (result != null) { 
                    returnMap.put(propertyName, result); 
                } else { 
                    returnMap.put(propertyName, ""); 
                } 
            } 
        } 
        return returnMap; 
    } 
}
