package com.xmniao.xmn.core.seller.service;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;

/**
 * 
* @projectName: xmnService 
* @ClassName: CustomisedSellerService    
* @Description:专属口味列表   
* @author: liuzhihao   
* @date: 2016年12月10日 上午3:04:43
 */
public interface CustomisedSellerService {

	/**
	 * 专属口味列表(登录用户)
	 * @param kind
	 * @param uid
	 * @param zoneid
	 * @param tradeid
	 * @param minPrice
	 * @param maxPrice
	 * @param type
	 * @param lat
	 * @param lon
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public MongoBaseResponse<MSeller>  queryCustomizedSellers(
		Integer kind,Integer uid,Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Integer type,Double lat,Double lon,Integer pageNo,Integer pageSize);

	/**
	 * 专属口味列表(未登录用户)
	 * @param zoneid
	 * @param tradeid
	 * @param minPrice
	 * @param maxPrice
	 * @param lon
	 * @param lat
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryCustomizedSellers(Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Double lon,Double lat,Integer pageNo,Integer pageSize);

}
