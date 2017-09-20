package com.xmniao.xmn.core.navigate.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.navigate.entity.CategoryNavigate;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CategoryNavigateDao
 * 
 * 类描述： 用户端管理 ==> 分类导航
 * 
 * 创建人： wangzhimin
 * 
 * 创建时间：2015年9月22日14时04分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface CategoryNavigateDao extends BaseDao<CategoryNavigate> {

	/**
	 * 获取导航列表信息
	 * @param categoryNavigate
	 * @return
	 */
	@DataSource("slave")
	public List<CategoryNavigate> getNavigateList(CategoryNavigate categoryNavigate);

	/**
	 * 获取导航列表记录数
	 * @param categoryNavigate
	 * @return
	 */
	@DataSource("slave")
	public Long getNavigateListcount(CategoryNavigate categoryNavigate);

	/**
	 * 查看分类导航详细页面初始化
	 * @param nId
	 * @return
	 */
	@DataSource("slave")
	public CategoryNavigate getNavigateObject(Integer nId);

	/**
	 * 根据 类别 获取最大的排序序号
	 * @param oneLevelNaviogate
	 * @return
	 */
	@DataSource("slave")
	public Integer getMaxOrderByType(CategoryNavigate categoryNavigate);

	/**
	 * 修改初始化
	 * @param valueOf
	 * @return
	 */
	@DataSource("slave")
	public Object getNavigateObject(Long nId);

	/**
	 * 上移的话，就更新之前行的order+1
	 * @param categoryNavigate
	 */
	public void updateBeforeOrder(CategoryNavigate categoryNavigate);

	/**
	 * 下移的话，就更新之后行的order-1
	 * @param categoryNavigate
	 */
	public void updateAfterOrder(CategoryNavigate categoryNavigate);
}
