package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSpecialTopic;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;



public interface SpecialTopicDao extends BaseDao<TSpecialTopic> {
    int deleteByPrimaryKey(Integer id);
    
    @DataSource("master")
    int insert(TSpecialTopic record);

    @DataSource("master")
    int insertSelective(TSpecialTopic record);

    TSpecialTopic selectByPrimaryKey(Integer id);

    @DataSource("master")
    int updateByPrimaryKey(TSpecialTopic record);

	/**
	 * 获取专题信息列表
	 */
	@DataSource("slave")
	public List<TSpecialTopic> getSpecialTopicList(TSpecialTopic specialTopic);

	/**
	 * 获取专题信息列表specialTopicCount
	 */
	@DataSource("slave")
	public Long specialTopicCount(TSpecialTopic specialTopic);
}