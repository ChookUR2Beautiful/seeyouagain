package com.xmniao.xmn.core.startimage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.startimage.dao.StartImageDao;
import com.xmniao.xmn.core.startimage.entity.StartImage;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：StartImageService
 * 
 * 类描述： 启动图管理
 * 
 * 创建人： wangzhimin
 * 
 * 创建时间：2015年8月04日14时04分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class StartImageService extends BaseService<StartImage> {

	@Autowired
	private StartImageDao startImageDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return startImageDao;
	}

	/**
	 * 根据查询条件获取所有的启动图
	 * @param startImage
	 * @return
	 * author：@wangzhimin
	 */
	public List<StartImage> getStartImageList(StartImage startImage) {
		return startImageDao.getStartImageList(startImage);
	}

	/**
	 * 根据查询添加获取启动图记录数
	 * @param startImage
	 * @return
	 * author：@wangzhimin
	 */
	public long getStartImageListcount(StartImage startImage) {
		return startImageDao.getStartImageListcount(startImage);
	}

	/**
	 * 查看启动图详细详细页面初始化
	 * @param id
	 * @return
	 * author：@wangzhimin
	 */
	public StartImage getStartObject(Integer id) {

		return startImageDao.getStartObject(id);
	}
}
