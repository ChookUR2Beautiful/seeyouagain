/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.IndianaDduonumDao;
import com.xmniao.xmn.core.fresh.entity.IndianaDduonum;
import com.xmniao.xmn.core.fresh.util.FreshConstants;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：IndianaDduonumService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月23日 上午11:01:02 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class IndianaDduonumService extends BaseService<IndianaDduonum>{

	@Autowired
	private IndianaDduonumDao indianaDduonumDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return indianaDduonumDao;
	}

	/**
	 * 方法描述：设置为预设
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午6:30:57 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void setWinner(Long id) {
		IndianaDduonum dduonum = indianaDduonumDao.selectByPrimaryKey(id);
		dduonum.setType(FreshConstants.INDIANA_DDUONUM_WINNER_TYPE);
		dduonum.setId(null);
		indianaDduonumDao.cancelWinner(dduonum);
		dduonum.setId(id);
		indianaDduonumDao.setWinner(dduonum);
	}

	/**
	 * 方法描述：取消预设
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午7:39:10 <br/>
	 * @param id
	 */
	public void cancel(Long id) {
		IndianaDduonum dduonum = indianaDduonumDao.selectByPrimaryKey(id);
		indianaDduonumDao.cancelWinner(dduonum);
	}

	/**
	 * 方法描述：活动最后50条数据
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月7日下午3:18:32 <br/>
	 * @return
	 */
	public List<IndianaDduonum> getLastFifty() {
		return indianaDduonumDao.getLastFifty();
	}

}
