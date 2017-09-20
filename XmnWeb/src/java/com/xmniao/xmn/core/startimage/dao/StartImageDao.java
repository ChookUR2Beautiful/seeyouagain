package com.xmniao.xmn.core.startimage.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.startimage.entity.StartImage;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：StartImageDao
 * 
 * 类描述： 启动图管理
 * 
 * 创建人： wangzhimin
 * 
 * 创建时间：2015年8月04日14时04分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface StartImageDao extends BaseDao<StartImage> {

	/**
	 * 根据查询条件获取所有的启动图
	 * @param startImage
	 * @return
	 * author：@wangzhimin
	 */
	@DataSource("slave")
	public List<StartImage> getStartImageList(StartImage startImage);

	/**
	 * 根据查询添加获取启动图记录数
	 * @param startImage
	 * @return
	 * author：@wangzhimin
	 */
	@DataSource("slave")
	public long getStartImageListcount(StartImage startImage);

	/**
	 * 查看启动图详细详细页面初始化
	 * @param id
	 * @return
	 * author：@wangzhimin
	 */
	@DataSource("slave")
	public StartImage getStartObject(Integer id);

}
