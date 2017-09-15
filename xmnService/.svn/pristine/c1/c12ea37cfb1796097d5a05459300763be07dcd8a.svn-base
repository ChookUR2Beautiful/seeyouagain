package com.xmniao.xmn.core.util;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：DistanceUtil   
* 类描述：   计算距离相关的工具类
* 创建人：yezhiyong   
* 创建时间：2016年8月16日 下午2:13:40   
* @version    
*
 */
public class DistanceUtil {

	/**
	 * 
	* @Title: Distance
	* @Description: 计算地球上任意两点(经纬度)距离 
	* @return double    返回类型
	* @author
	* @throws
	 */
	public static double Distance(double long1, double lat1, double long2,  
	        double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2  
	    		* R  
	            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}
}
