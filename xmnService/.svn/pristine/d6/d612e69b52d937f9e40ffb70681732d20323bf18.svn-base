package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.xmer.entity.SellerBrowsed;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerBrowsedDao    
* @Description:用户流浪商铺记录dao   
* @author: liuzhihao   
* @date: 2016年11月14日 下午2:27:26
 */
@Repository
public interface SellerBrowsedDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);
	
	@DataSource("joint")
    int insert(SellerBrowsed record);
	
	@DataSource("joint")
    int insertSelective(SellerBrowsed record);
	
	@DataSource("joint")
    SellerBrowsed selectByPrimaryKey(Integer id);
	
	@DataSource("joint")
    int updateByPrimaryKeySelective(SellerBrowsed record);
	
	@DataSource("joint")
    int updateByPrimaryKey(SellerBrowsed record);
	
	/**
	 * 查询用户浏览过的商铺id
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> selectByUid(Integer uid);
	
	/**
	 * 通过用户id查询我浏览过的商铺id
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Integer> findAllByUid(Map<Object,Object> map);
	
	/**
	 * 通过商铺id和用户id查询商铺浏览记录信息
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	int queryBrowsedBySellerIdAndUid(Map<Object,Object> map);
	
	
	/**
	 * 删除浏览记录
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	int removeBrowseBySelleridAndUid(Map<Object,Object> map);
	
}