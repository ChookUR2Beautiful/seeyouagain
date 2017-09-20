package com.xmniao.xmn.core.businessman.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.AllianceAccountDao;
import com.xmniao.xmn.core.businessman.entity.AllianceAccount;
import com.xmniao.xmn.core.util.NMD5;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllianceAccountService
 * 
 * 类描述：商家联盟店帐号Service
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-01-16 11:06:45
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class AllianceAccountService extends BaseService<AllianceAccount>{
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(AllianceAccount t,Long id,Date d) {
		t.setId(id);
		t.setSdate(d);
		t.setUdate(d);
		String p = t.getPhone();
		p =p.substring(p.length()-6);
		t.setPassword(NMD5.Encode(p));
		super.add(t);
	}

	@Autowired
	private AllianceAccountDao allianceAccountDao;
	
	
	@Override
	protected BaseDao getBaseDao() {
		return allianceAccountDao;
	}
	
	public boolean checkAccount(String account){
		return (allianceAccountDao.checkAccount(account)>0L);
	}

}
