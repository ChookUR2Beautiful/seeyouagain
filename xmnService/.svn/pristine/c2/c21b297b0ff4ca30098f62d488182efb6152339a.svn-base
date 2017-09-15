package com.xmniao.xmn.core.seller.service;

import java.util.List;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;

/**
 * 
* @projectName: xmnService 
* @ClassName: UneatSellerService    
* @Description:我看过不如一尝的店铺   
* @author: liuzhihao   
* @date: 2016年12月9日 下午11:21:46
 */
public interface UneatSellerService {

	/**
	 * 看过不如一尝的店铺
	 * @param uid
	 * @param zoneid
	 * @param tradeid
	 * @param lon
	 * @param lat
	 * @param sellers
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryUneatSellers(Integer uid,Integer zoneid,Integer cityid,Integer tradeid,Double lon,Double lat,List<Integer> sellers,Integer pageNo,Integer pageSize,boolean isRandom,Integer status);
}
