package com.xmniao.xmn.core.seller.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.seller.entity.UrsCollect;
import com.xmniao.xmn.core.util.dataSource.DataSource;
@Repository
public interface UrsCollectDao {
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);
	@DataSource("joint")
    int insert(UrsCollect record);
	@DataSource("joint")
    int insertSelective(UrsCollect record);
	@DataSource("joint")
    UrsCollect selectByPrimaryKey(Integer id);
	@DataSource("joint")
    int updateByPrimaryKeySelective(UrsCollect record);
	@DataSource("joint")
    int updateByPrimaryKey(UrsCollect record);
    
    /**
     * 根据用户id查询收藏信息
     * @param map
     * 		key1:uid
     * 		key2:type
     * @return
     * 		list
     */		
	@DataSource("joint")
    List<UrsCollect> selectByUid(Map<Object,Object> map);
	
	@DataSource("joint")
    int sumCollectsBySellerid(Map<Object,Object> map);
	
	/**
	 * 通过用户id 查询与用户收藏同样一个商铺的用户id
	 * @param map
	 * 		key1:uid 用户id
	 * 		key2:type 收藏类型 0 商铺 1 积分
	 * 		key3:sellerid 商铺id
	 * @return
	 */
	@DataSource("joint")
	List<Integer> querySameCollectSeller(Map<Object,Object> map);
	
	/**
	 * 通过用户id查看用户收藏商铺信息
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Integer> queryCollectSellerByUid(Map<Object,Object> map);
	
	/**
	 * 通过商铺id和用户id查看是否存在该商铺的收藏
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	int queryCollectBySellerIdAndUid(Map<Object,Object> map);
	
	/**
	 * 删除收藏记录
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	int removeCollectByUidAndSellerId(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryCheckUrsCollectSellerList
	* @Description: //查询是否收藏这些店铺
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryCheckUrsCollectSellerList(Map<Object, Object> paramMap);
}