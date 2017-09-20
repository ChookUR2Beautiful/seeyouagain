package com.xmniao.xmn.core.common.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.common.entity.TBank;

public interface TBankDao extends BaseDao<TBank> {

	public List<TBank> getObject();
	public TBank getTBank(TBank tBank);
	
}
