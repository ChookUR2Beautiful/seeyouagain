package com.xmniao.xmn.core.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.common.dao.AdvertisingDao;
import com.xmniao.xmn.core.common.entity.TAdvertising;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AdvertisingService
 * 
 * 类描述： 广告轮播
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时27分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class AdvertisingService extends BaseService<TAdvertising> {

	@Autowired
	private AdvertisingDao advertisingDao;

	@Override
	protected BaseDao getBaseDao() {
		return advertisingDao;
	}

	/*
	 * 重写客户端数据展示方法，只取客户端和蜜客圈数据 author:@yangxu
	 */
	public List<TAdvertising> getUserADList(TAdvertising tAdvertising) {
		return advertisingDao.getUserADList(tAdvertising);
	}

	/*
	 * 重写客户端数据计数方法，只取客户端和蜜客圈数据，用于分页操作 author:@yangxu
	 */
	public long getUserADListcount(TAdvertising tAdvertising) {
		return advertisingDao.getUserADListcount(tAdvertising);
	}

	/**
	 * 获取商家的轮播图列表
	 * 
	 * @param advertising
	 * @return
	 */
	public List<TAdvertising> getSellerADList(TAdvertising advertising) {
		return advertisingDao.getSellerADList(advertising);
	}

	/**
	 * 用户端广告轮番图片管理
	 * 
	 * @param advertising
	 * @return author：@wangzhimin
	 */
	public List<TAdvertising> getADListForUser(TAdvertising advertising) {
		return advertisingDao.getADListForUser(advertising);
	}

	/**
	 * 上线用户端广告轮番图.
	 * 
	 * @param advertising
	 */
	public void onLineUserAdvertising(TAdvertising advertising) {
		advertising.getId();
		advertising.setIsshow(TAdvertising.Isshow.ONLINE.getIndex());
		advertisingDao.update(advertising);
	}

	/**
	 * 下线用户端广告轮番图.
	 * 
	 * @param advertising
	 */
	public void offLineUserAdvertising(TAdvertising advertising) {
		advertising.getId();
		advertising.setIsshow(TAdvertising.Isshow.OFFLINE.getIndex());
		advertisingDao.update(advertising);
	}
}
