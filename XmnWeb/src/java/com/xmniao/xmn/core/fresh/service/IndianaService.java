/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.IndianaDao;
import com.xmniao.xmn.core.fresh.dao.SaleGroupDao;
import com.xmniao.xmn.core.fresh.entity.ActivityGroup;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;
import com.xmniao.xmn.core.fresh.entity.Indiana;
import com.xmniao.xmn.core.fresh.entity.IndianaBout;
import com.xmniao.xmn.core.fresh.entity.TSaleGroup;
import com.xmniao.xmn.core.fresh.util.FreshConstants;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：IndianaService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月23日 上午10:58:46 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class IndianaService extends BaseService<Indiana>{
	
	@Autowired
	private IndianaDao indianaDao;
	
	@Autowired
	private IndianaBoutService indianaBoutService;
	
	@Autowired
	private FreshManageService freshManageService;
	
	@Autowired
	private SaleGroupDao saleGroupDao;
	
	
	@Override
	protected BaseDao getBaseDao() {
		return indianaDao;
	}

	/**
	 * 方法描述：保存夺宝活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月23日上午11:52:45 <br/>
	 * @param indiana
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addIndiana(Indiana indiana) {
		freshManageService.transferStoreAndGroup(indiana.getBoutNum(), indiana.getCodeId(), indiana.getPvIds());
		indiana.setCreateTime(new Date());
		indiana.setUpdateTime(new Date());
		indiana.setBoutResidue(indiana.getBoutNum()-1);
		indianaDao.add(indiana);
		Integer id = indiana.getId();
		IndianaBout indianaBout = new IndianaBout();
		indianaBout.setActivityId(id);
		indianaBout.setCodeId(indiana.getCodeId());
		indianaBout.setBoutTh(1);
		indianaBout.setCreateTime(indiana.getBeginTime());
		indianaBout.setStatus(FreshConstants.INDIANA_BOUT_ING_STATUS);
		indianaBout.setUpdateTime(new Date());
		indianaBoutService.add(indianaBout);
	}

	/**
	 * 方法描述：修改
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月24日上午10:11:37 <br/>
	 * @param indiana
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateIndiana(Indiana indiana) {
		Indiana object = indianaDao.getObject(indiana.getId().longValue()); 
		freshManageService.updateActivityProductAndGroup(object.getBoutNum(), object.getCodeId(), object.getPvIds());
		freshManageService.transferStoreAndGroup(indiana.getBoutNum(), indiana.getCodeId(), indiana.getPvIds());
		indiana.setUpdateTime(new Date());
		indiana.setBoutResidue(indiana.getBoutNum()-1);
		indianaDao.update(indiana);
		IndianaBout indianaBout = indianaBoutService.getFirstIndianaBout(indiana.getId());
		indianaBout.setActivityId(indiana.getId());
		indianaBout.setCodeId(indiana.getCodeId());
		indianaBout.setCreateTime(indiana.getBeginTime());
		indianaBout.setStatus(FreshConstants.INDIANA_BOUT_ING_STATUS);
		indianaBout.setUpdateTime(new Date());
		indianaBoutService.update(indianaBout);
	}
	
	
	/**
	 * 方法描述：终止活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月24日上午11:33:02 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void end(Integer id) {
		IndianaBout indianaBout = indianaBoutService.getObject(id.longValue());
		Indiana indiana = indianaDao.getObject(indianaBout.getActivityId().longValue());
		indianaBout.setStatus(FreshConstants.INDIANA_BOUT_END_STATUS);
		indianaBoutService.update(indianaBout);
		freshManageService.updateActivityProductAndGroup(indiana.getBoutResidue()+1, indiana.getCodeId(), indiana.getPvIds());
		indiana.setBoutResidue(0);
		indiana.setStatus(FreshConstants.INDIANA_END_STATUS);
		indiana.setUpdateTime(new Date());
		indianaDao.update(indiana);
		indianaBoutService.update(indianaBout);
	}

	/**
	 * 方法描述：删除活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月24日下午2:35:00 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id) {
		IndianaBout indianaBout = indianaBoutService.getObject(id.longValue());
		Indiana indiana = indianaDao.getObject(indianaBout.getActivityId().longValue());
		indianaBoutService.update(indianaBout);
		freshManageService.updateActivityProductAndGroup(indiana.getBoutResidue()+1, indiana.getCodeId(), indiana.getPvIds());
		indiana.setBoutResidue(0);
		indiana.setStatus(FreshConstants.INDIANA_DELETE_STATUS);
		indiana.setUpdateTime(new Date());
		indianaDao.update(indiana);
		indianaBoutService.remove(id);
	}
	
}
