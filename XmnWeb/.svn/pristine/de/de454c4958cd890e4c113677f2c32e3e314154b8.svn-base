package com.xmniao.xmn.core.dataDictionary.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.dataDictionary.entity.BRecruitTag;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BRecruitTagMapper
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-30下午5:39:01
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BRecruitTagDao extends BaseDao<BRecruitTag>{
	
	/**
	 * 新增标签
	 */
	@DataSource("burs")
	public void add(BRecruitTag recruitTag);
	
	/**
	 * 删除标签
	 * 创建人： huang'tao
	 * 创建时间：2016-5-31上午9:46:40
	 * @param id
	 */
	@DataSource("burs")
	public void delete(int id);
	
	/**
	 * 更新标签
	 */
	@DataSource("burs")
	public Integer update(BRecruitTag recruitTag);
	
	/**
	 * 获取标签列表
	 */
	@DataSource("burs")
	public List<BRecruitTag> getList(BRecruitTag recruitTag);
	
	/**
	 * 统计标签数量
	 */
	@DataSource("burs")
	public Long count(BRecruitTag recruitTag);
	
	/**
	 * 获取标签
	 */
	@DataSource("burs")
	public BRecruitTag getRecruitTag(Long id);
	
}