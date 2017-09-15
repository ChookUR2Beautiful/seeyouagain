package com.xmniao.xmn.core.seller.service;

import java.util.List;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;

/**
 * 查询帮你挑选店铺接口
* @projectName: xmnService 
* @ClassName: ChooseSellerService    
* @Description:   
* @author: liuzhihao   
* @date: 2016年12月9日 下午9:36:56
 */
public interface ChooseSellerService {

	/**
	 * 查询帮你挑选的店铺(登录用户)
	 * @param kind
	 * @param uid
	 * @param lat
	 * @param lon
	 * @param zoneid
	 * @param tradeid
	 * @param pageNo
	 * @param pageSize
	 * @param sellers
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryChooseSellers(Integer kind,Integer uid,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,List<Integer> sellers,Integer status);
	
	/**
	 * 查询帮你挑选的店铺(未登录用户)
	 * @param lat
	 * @param lon
	 * @param zoneid
	 * @param tradeid
	 * @param pageNo
	 * @param pageSize
	 * @param sellers
	 * @return
	 */
	public List<MSeller> queryChooseSellers(Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,List<Integer> sellers,Double minDistance,Double maxDistance);
	
	/**
	 * 查询与用户相关的店铺
	 * @param uid
	 * @param lat
	 * @param lon
	 * @param zoneid
	 * @param tradeid
	 * @param pageNo
	 * @param pageSize
	 * @param minDistance
	 * @param maxDistance
	 * @param sellers
	 * @return
	 */
	public List<MSeller> getChooseSellersByUid(Integer uid,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,Double minDistance,Double maxDistance,List<Integer> sellers);
	
	/**
	 * 查询与用户无关的店铺
	 * @param lat
	 * @param lon
	 * @param zoneid
	 * @param tradeid
	 * @param pageNo
	 * @param pageSize
	 * @param minDistance
	 * @param maxDistance
	 * @param sellers
	 * @return
	 */
	public List<MSeller> getChooseSellersByZoneId(Integer uid,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,Double minDistance,Double maxDistance,List<Integer> sellers);

	public Long sumChooseCounts(Integer uid,Integer zoneid,Integer tradeid);
}
