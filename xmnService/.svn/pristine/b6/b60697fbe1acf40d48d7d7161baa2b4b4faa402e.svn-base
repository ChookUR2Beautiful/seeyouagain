package com.xmniao.xmn.core.util;

import java.math.BigDecimal;

public class DistanceUtils {
	
	/**距离自动转换为km
	 * @param rangs
	 * @param scale
	 * @return
	 */
	public static String formatDistance(Object rangs,int scale) {
		if (rangs == null) {
			return new BigDecimal(0).setScale(scale, BigDecimal.ROUND_HALF_UP).toString()+"m";
		}
		Double distance = Double.parseDouble(rangs.toString());
		if (distance>1000) {
			distance=distance/1000;
			return new BigDecimal(distance).setScale(scale, BigDecimal.ROUND_HALF_UP).toString()+"km";
		}else {
			return new BigDecimal(distance).setScale(scale, BigDecimal.ROUND_HALF_UP).toString()+"m";	
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DistanceUtils.formatDistance(12563.366d, 2));
	}
}
