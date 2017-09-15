package com.xmniao.xmn.core.util;

/**
 * 
* @projectName: xmnService 
* @ClassName: XRankUtil    
* @Description:寻蜜鸟权重排序工具类   
* @author: liuzhihao   
* @date: 2016年11月14日 下午2:53:45
 */
public class XRankUtil {

	/**
	 * 寻蜜鸟权重计算函数
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @param index 指数
	 * @return
	 */
	public static Double getWeightRank(double divisor,double dividend,double index){
		
		Double xrank = ArithUtil.mul(ArithUtil.div(divisor, dividend), index);
		
		return xrank;
	}
}
