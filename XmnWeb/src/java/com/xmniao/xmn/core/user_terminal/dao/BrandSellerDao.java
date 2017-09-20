package com.xmniao.xmn.core.user_terminal.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.user_terminal.entity.TBrandSeller;

public interface BrandSellerDao extends BaseDao<TBrandSeller> {

	int deleteByIds(Object[] objects);

}
