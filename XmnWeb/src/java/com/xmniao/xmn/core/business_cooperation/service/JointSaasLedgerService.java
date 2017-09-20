package com.xmniao.xmn.core.business_cooperation.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_cooperation.dao.JointSaasLedgerDao;
import com.xmniao.xmn.core.business_cooperation.entity.TJointSaasLedger;

/**
 * 项目名称： XmnWeb
 * 类名称： JointSaasLedgerService.java
 * 类描述：经销商旗下商家签约上线后分账
 * 创建人： lifeng
 * 创建时间： 2016年6月29日下午5:29:42
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Service
public class JointSaasLedgerService extends BaseService<TJointSaasLedger> {
	
	@Autowired
	private JointSaasLedgerDao jointSaasLedgerDao;

	@Override
	protected BaseDao<TJointSaasLedger> getBaseDao() {
		return jointSaasLedgerDao;
	}
	
	/**
	 * 保存一条数据
	 */
	public Integer addJointSaasLedger(TJointSaasLedger tJointSaasLedger){
		tJointSaasLedger.setDate(new Date());
		return jointSaasLedgerDao.addJointSaasLedger(tJointSaasLedger);
	}

}
