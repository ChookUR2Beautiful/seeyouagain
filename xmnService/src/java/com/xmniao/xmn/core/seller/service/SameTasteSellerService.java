package com.xmniao.xmn.core.seller.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xmniao.xmn.core.base.MongoBaseResponse;

/**
 * 
* @projectName: xmnService 
* @ClassName: SameTasteSellerService    
* @Description:与我口味相似的人   
* @author: liuzhihao   
* @date: 2016年12月9日 下午11:41:43
 */
public interface SameTasteSellerService {

	/**
	 * 查询与用户口味相似的用户(登录)
	 * @param uid
	 * @param kind
	 * @param lon
	 * @param lat
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public MongoBaseResponse<Map<Object,Object>> querySameTasteList(Integer uid,Integer kind,Double lon,Double lat,Integer pageNo,Integer pageSize,boolean isRandom);

	/**
	 * 查询与用户消费过同样的商铺的用户信息
	 * @param uid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Map<Object,Object>> querySameTaseteListBySellerId(Integer uid, Double lon,Double lat,Integer pageNo,Integer pageSize,boolean isRandom);
	
	/**
	 * 
	 * @param uid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Map<Object,Object>> querySameTaseteListByTradeId(Integer uid, Double lon,Double lat,Integer pageNo,Integer pageSize,boolean isRandom);
	
	public List<Map<Object,Object>> querySameTaseteListByZoneId(Integer uid,Double lon,Double lat,Integer pageNo,Integer pageSize,boolean isRandom);
	
	public List<Map<Object,Object>> queryUserInfoByUidList(Integer uid,Set<Integer> uids,Double lon,Double lat);
	
	/**
	 * 查询与用户口味相似的用户(未登录或者新用户)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Map<Object,Object>> querySameTasteList(Integer zoneid, Double lon,Double lat,Integer pageNo,Integer pageSize,boolean isRandom);
	
}
