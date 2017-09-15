package com.xmniao.xmn.core.seller.service;

import java.util.List;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;

/**
 * 
* @projectName: xmnService 
* @ClassName: PopularSellerService    
* @Description:常去商圈的人气店铺（更名 “商圈人气店铺”）   
* @author: liuzhihao   
* @date: 2016年12月9日 下午9:38:24
 */

public interface PopularSellerService {

	/**
	 * 查询商圈人气店铺
	 * @param kind
	 * @param lat
	 * @param lon
	 * @param zoneid
	 * @param tradeid
	 * @param pageNo
	 * @param pageSize
	 * @param sellers
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryPopularSellers(Integer uid,Integer kind,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,List<Integer> sellers,boolean isRandom,Integer status);

	/**
	 * 查询商圈人气店铺个数
	 * @param zoneid
	 * @param lon
	 * @param lat
	 * @param maxDistance
	 * @return
	 */
	public Integer queryPopularCounts(Integer cityid,Integer zoneid,Double lon,Double lat,Double maxDistance);
	
	/**
	 * 根据商圈ID查询一定范围内的商铺
	 * @param zoneid
	 * @param lon
	 * @param lat
	 * @param maxDistance
	 * @param sellers
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<MSeller> queryPopularSellersByZoneId(Integer cityid,Integer zoneid,Integer tradeid,Double lon,Double lat,Double maxDistance,List<Integer> sellers,Integer pageNo,Integer pageSize,boolean isRandom);
}
