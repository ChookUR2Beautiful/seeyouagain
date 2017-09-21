/**
 * 2016年4月11日 下午4:46:01
 */
package com.xmniao.util;

import java.math.BigDecimal;

/**
 * @项目名称：saasService
 * @类名称：ArithUtil
 * @类描述：double类型的加减乘除
 * @创建人： zhangchangyuan
 * @创建时间 2016年4月11日 下午4:46:01
 * @version
 */
public class ArithUtil {
	private static final int DEF_DIV_SCALE=2;  
	
	/**
	 * 
	* @Title: add
	* @Description: 加法
	* @return double
	 */
	public static double add(double d1,double d2){  
        BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Double.toString(d2));  
        return b1.add(b2).doubleValue();  
          
    }  
     
	/**
	 * 
	* @Title: sub
	* @Description: 减法
	* @return double
	 */
    public static double sub(double d1,double d2){  
        BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Double.toString(d2));  
        return b1.subtract(b2).doubleValue();  
          
    }  
     
    /**
     * 
    * @Title: mul
    * @Description:乘法 
    * @return double
     */
    public static double mul(double d1,double d2){  
        BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Double.toString(d2));  
        return b1.multiply(b2).setScale(ArithUtil.DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();  
          
    }  
     
    /**
    * @Title: div
    * @Description: 除法
    * @return double
     */
    public static double div(double d1,double d2){  
        return div(d1,d2,DEF_DIV_SCALE);  
    }  	
    public static double div(double d1,double d2,int scale){  
        if(scale<0){  
            throw new IllegalArgumentException("The scale must be a positive integer or zero");  
        }  
        BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Double.toString(d2));  
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();  
          
    }  
    
    /**
     * 
    * @Title: orderRatio
    * @Description: (增长或减少比)
    * @return double    返回类型
    * @throws
     */
    public static double  orderRatio(double o1,double o2){
    	double o3 = sub(o1,o2);
    	return div(o3,o2);
    } 
    
    /**
     * 
    * @Title: oldOrNewOrderRatio
    * @Description: (占比)
    * @return double    返回类型
    * @throws
     */
    public static double oldOrNewOrderRatio(double o1,double o2){
    	return div(o1,o2);
    }
    public static void main(String[] args) {
    	
	}
}
