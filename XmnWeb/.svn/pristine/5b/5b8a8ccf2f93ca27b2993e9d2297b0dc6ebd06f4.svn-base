package com.xmniao.xmn.core.user_terminal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.user_terminal.dao.BrandSellerDao;
import com.xmniao.xmn.core.user_terminal.entity.TBrandSeller;

@Service
public class BrandSellerService extends BaseService<TBrandSeller> {

	@Autowired
	private BrandSellerDao brandSellerDao;

	@Override
	protected BaseDao<TBrandSeller> getBaseDao() {
		return brandSellerDao;
	}

	public int deleteByIds(String ids) {
		int rows = 0;
		if (null != ids) {
			rows = delete(ids.split(","));
			brandSellerDao.deleteByIds(ids.split(","));
		}
		return rows;
	}

	/**
	 * 上线品牌店.
	 * 
	 * @param brandSeller
	 */
	@Transactional
	public void online(TBrandSeller brandSeller) {
		brandSeller.setIsBrand(TBrandSeller.IS_BRAND_ONLINE);
		update(brandSeller);
	}

	/**
	 * 下线品牌店.
	 * 
	 * @param brandSeller
	 */
	@Transactional
	public void offline(TBrandSeller brandSeller) {
		brandSeller.setIsBrand(TBrandSeller.IS_BRAND_OFFLINE);
		update(brandSeller);
	}

}
