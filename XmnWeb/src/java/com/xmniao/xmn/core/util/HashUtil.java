package com.xmniao.xmn.core.util;

import ch.hsr.geohash.LbsGehash;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：HashUtil
 * 
 * 类描述： 生成LBS hash值
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年12月1日 下午2:58:38
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class HashUtil {

	private static HashUtil hashUtil;
	private LbsGehash lbsGehash = new LbsGehash();

	public HashUtil() {
	}

	public static HashUtil getInstance() {
		if (null == hashUtil) {
			synchronized (HashUtil.class) {
				if (null == hashUtil) {
					hashUtil = new HashUtil();
				}
			}
		}

		return hashUtil;
	}

	/**
	 * latitude 纬度 longitude 经度
	 * 
	 * @Title: getGeoHash
	 * @Description: 依纬度，经度 计算 GeoHash
	 * @return String 返回类型
	 * @throws
	 */
	public String getGeoHash(double latitude, double longitude) {
		String geohash = lbsGehash.getGeoHash(latitude, longitude);
		return geohash;
	}
	
	public static void main(String[] args){
		System.out.println(HashUtil.getInstance().getGeoHash(new Double("22.546054"), new Double("114.025974")));
	}

}
