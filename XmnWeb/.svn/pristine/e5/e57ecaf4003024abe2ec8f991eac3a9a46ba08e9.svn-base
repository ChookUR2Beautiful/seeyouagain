/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.FreshBrandDao;
import com.xmniao.xmn.core.fresh.dao.HotBrandDao;
import com.xmniao.xmn.core.fresh.entity.FreshBrand;
import com.xmniao.xmn.core.fresh.entity.HotBrand;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：HotBrandService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年1月5日 下午4:48:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class HotBrandService extends BaseService<HotBrand> {
	
	@Autowired
	private HotBrandDao hotBrandDao;
	
	@Autowired
	private FreshBrandDao freshBrandDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return hotBrandDao;
	}

	/**
	 * 方法描述：添加品牌
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月5日下午4:55:03 <br/>
	 * @param list
	 */
	public  void saveHotBrand(List<HotBrand> list) {
		hotBrandDao.deleteByType(list.get(0).getTypeId());
		Integer sort=8;
		for (HotBrand hotBrand : list) {
			FreshBrand freshBrand = freshBrandDao.getObject(hotBrand.getBrandId());
			hotBrand.setImageUrl(freshBrand.getLogo());
			hotBrand.setCreateTime(new Date());
			hotBrand.setUpdateTime(new Date());
			hotBrand.setSort(sort--);
			hotBrand.setState(1);
			hotBrandDao.add(hotBrand);
		}
	}

}
