package com.xmniao.xmn.core.seller.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.seller.entity.Trade;
import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface TradeDao {
	@DataSource("joint")
    int deleteByPrimaryKey(Integer tid);
	
	@DataSource("joint")
    int insert(Trade record);
	
	@DataSource("joint")
    int insertSelective(Trade record);
	
	@DataSource("joint")
    Trade selectByPrimaryKey(Integer tid);
	
	@DataSource("joint")
    int updateByPrimaryKeySelective(Trade record);
	
	@DataSource("joint")
    int updateByPrimaryKey(Trade record);
	
	@DataSource("joint")
	Trade selectByTradename(Map<Object,Object> map);
	
	//查询所有美食的分类
	@DataSource("joint")
	List<Map<Object,Object>> findAllTradeByDeliciousFood();
	
	/**
	 * 通过父id查询所有商铺分类
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllByPid(Integer pid);

	/**
	 * 查询所有
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAll();

	/**
	 * 
	 * @Title:listFoodCategory
	 * @Description:查询美食二级分类信息列表
	 * @return List<Map<Object,Object>> 美食二级分类
	 * 2017年5月17日下午8:33:18
	 */
	List<Map<Object, Object>> listFoodCategory();
}