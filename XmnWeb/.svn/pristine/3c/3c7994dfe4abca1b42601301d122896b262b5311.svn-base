package com.xmniao.xmn.core.marketingmanagement.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.marketingmanagement.entity.HotWords;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：HotWordsDao
 * 
 * 类描述：查询热门搜索设置列表
 * 
 * 创建人： caoyingde
 * 
 * 创建时间：2015年03月16日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface HotWordsDao extends BaseDao<HotWords>{
	
	/**
	 * 获取热门搜索设置列表
	 */
	@DataSource("slave")
	public List<HotWords> getHotWordsList(HotWords hotWords);
		
	/**
	 * 获取热门搜索设置列表
	 */
	@DataSource("slave")
	public Long getHotWordsCount(HotWords hotWords);
	/**
	 * 获取同一个区域类关键字总数  （不能超过5个）
	 */
	@DataSource("slave")
	public Long isHotWordsOfArea(HotWords hotWords);
	/**
	 * 获取同一个区域类关键是否有重复过
	 */
	@DataSource("slave")
	public Long isHotWordsOfAreaId(HotWords hotWords);
	/**
	 * 获取同一个区域类排序是否有重复过
	 */
	@DataSource("slave")
	public Long isExistHOrder(HotWords hotWords);
	
	/**
	 * 修改时对比排序是否改变
	 */
	@DataSource("slave")
	public Long isChangeHOrder(HotWords hotWords);
	
	/**
	 * 获取同一个区域类关键是否有重复过
	 */
	@DataSource("slave")
	public String isHotWordsOfHid(HotWords hotWords);
	/**
	 * 获取同一个区域类关键是否有重复过
	 */
	@DataSource("slave")
	public String isHotWordsOfHid2(HotWords hotWords);
	
	/**
	 * 判断城市是否有商圈
	 * @param areaId
	 * @return
	 */
	Long isCityInBusiness(String areaId);
	
	/**
	 * 根据城市编号获取搜索标签
	 * @param areaId
	 * @return
	 */
	List<HotWords> getSearchTags(String areaId);
	
	/**
	 * 根据城市编号与热词状态 查询城市热词 或者 城市热词历史记录
	 * @param hotWords
	 * @return
	 */
	List<HotWords> getHotSearchTags(HotWords hotWords);
	
	/**
	 * 根据城市编号与热词状态 查询城市热词数量 或者 城市热词历史记录数量
	 * @param hotWords
	 * @return
	 */
	Long getHotSearchTagsCount(HotWords hotWords);
	
}
