package com.xmniao.xmn.core.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.common.dao.BusinessDao;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.entity.TBusiness;
import com.xmniao.xmn.core.exception.ApplicationException;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessService
 * 
 * 类描述： 商圈
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月12日17时41分48秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BusinessService extends BaseService<TBusiness> {

	@Autowired
	private BusinessDao businessDao;

	@Override
	protected BaseDao getBaseDao() {
		return businessDao;
	}

	public List<TBusiness> selectBusiness(int areaId) {
		List<TBusiness> businessList = businessDao.businessList(areaId);
		return businessList;
	}

	/**
	 * 商圈list
	 * 
	 * @param business
	 * @return
	 */
	public List<TBusiness> getAreaBusinessList(TBusiness business) {
		List<TBusiness> getAreaBusinessList = businessDao
				.getAreaBusinessList(business);
		return getAreaBusinessList;
	}

	/**
	 * 商圈listcount
	 * 
	 * @param business
	 * @return
	 */
	public Long getAreaBusinessListcount(TBusiness business) {
		Long getAreaBusinessListcount = businessDao
				.getAreaBusinessListcount(business);
		return getAreaBusinessListcount;
	}

	/**
	 * 更加bid查询修改
	 * 
	 * @param bid
	 * @return
	 */
	public TBusiness getObjectBybid(Long bid) {
		TBusiness tBusiness = businessDao.getObjectBybid(bid);
		return tBusiness;
	}

	/**
	 * 根据区域id删除商圈
	 * 
	 * @param objects
	 * @return
	 */
	public Integer deletebid(Object[] objects) {
		int deletebid = businessDao.deletebid(objects);
		return deletebid;
	}

	/**
	 * 获取下拉列表数据
	 * @param business
	 * @return
	 */
	public List<TBusiness> getSelect(TBusiness business) {
		return businessDao.getSelect(business);
	}

	/**
	 * 区域管理中开通或者关闭商圈
	 * @param tArea
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateBussinessStatus(TArea tArea) {
		TBusiness business = new TBusiness();
		try{
			business.setBid(Integer.valueOf(tArea.getBid()));
			business.setIs_open(tArea.getStatus());
			businessDao.updateBussinessStatus(business);
		}catch(Exception e){
			throw new ApplicationException("更新商圈状态异常", e, new Object[] { tArea, business}, new String[] { "商圈编号", tArea.getBid().toString(), "状态更新", "状态更新" });
		}
	}
}
