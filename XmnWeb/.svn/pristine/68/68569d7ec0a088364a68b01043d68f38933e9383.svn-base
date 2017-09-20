package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerLandmarkDao;
import com.xmniao.xmn.core.businessman.entity.MSellerLandmark;
import com.xmniao.xmn.core.businessman.entity.TSellerLandmark;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerLandmarkService
 * 
 * 类描述： 商家经纬度信息
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时30分16秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class SellerLandmarkService extends BaseService<TSellerLandmark> {

	@Autowired
	private SellerLandmarkDao sellerLandmarkDao;

	@Override
	protected BaseDao getBaseDao() {
		return sellerLandmarkDao;
	}

	public TSellerLandmark getSellerLandmarkByid(Long sellerid){
		List<TSellerLandmark> TSellerLandmarkList = sellerLandmarkDao.getSellerLandmarkByid(sellerid);
		return TSellerLandmarkList.isEmpty()?null:TSellerLandmarkList.get(0);
	}
	
	/**
	 * 根据商家编号查询坐标
	 * @param sellerid
	 * @return
	 */
	public MSellerLandmark getMSellerLandmarkByid(Long sellerid){
		return sellerLandmarkDao.getMSellerLandmarkByid(sellerid);
	}
}
