package com.xmniao.xmn.core.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.dao.BusinessDao;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.entity.TBusiness;
import com.xmniao.xmn.core.common.util.commonConstants;
import com.xmniao.xmn.core.exception.ApplicationException;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CityService
 * 
 * 类描述： 城市
 * 
 * 创建人： wangzhimin
 * 
 * 创建时间：2015年09月28日 上午10:41:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class CityService extends BaseService<TArea> {

	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private BusinessDao businessDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return areaDao;
	}

	/**
	 * 获取城市内容和总记录数
	 * @param area
	 * @return
	 */
	public Pageable<TArea> getCityListPageable(TArea area) {
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable.setContent(areaDao.getCityListContent(area));
		pageable.setTotal(areaDao.getCityListTotal(area));
		return pageable;
	}	
	
	/**
	 * 开通或者关闭城市
	 * @param tArea
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateStatus(TArea tArea) {
		try{
			TBusiness business = getTBusiness(tArea);
			areaDao.update(tArea);
			areaDao.updateAreaStatus(tArea);
			businessDao.updateIsOpen(business);
		}catch(Exception e){
			throw new ApplicationException("更新城市状态异常", e, new Object[] { tArea}, new String[] { "城市编号", tArea.getAreaId().toString(), "状态更新", "状态更新" });
		}
	}
	
	/**
	 * 城市关闭后对应的商圈也要关闭
	 * @param tArea
	 * @return
	 */
	private TBusiness getTBusiness(TArea tArea) {
		TBusiness business = new TBusiness();
		Integer status = tArea.getStatus();
		if(commonConstants.IS_LOCK == status){
			business.setIs_open(0);  //未开通
		}else if(commonConstants.IS_OPEN  == status){
			business.setIs_open(1);  //已开通
		}
		business.setAreaId(tArea.getAreaId());
		return business;
	}
}
