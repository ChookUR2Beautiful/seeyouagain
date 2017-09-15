package com.xmniao.xmn.core.seller.service;

import java.util.List;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;

/**
 * 
* @projectName: xmnService 
* @ClassName: InterestSellerService    
* @Description: 感兴趣的新店（更名“猜你喜欢”）  
* @author: liuzhihao   
* @date: 2016年12月9日 下午11:04:33
 */
public interface InterestSellerService {

	/**
	 * 查询感兴趣的新店
	 * @param zoneid
	 * @param tradeid
	 * @param lon
	 * @param lat
	 * @param sellers
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryInterestSellers(Integer cityid,Integer zoneid,Integer tradeid,Double lon,Double lat,List<Integer> sellers,Integer pageNo,Integer pageSize,boolean isRandom);
	
}
