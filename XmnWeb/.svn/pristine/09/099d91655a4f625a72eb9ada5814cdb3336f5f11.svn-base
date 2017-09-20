/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.RecommendSellerPackageDao;
import com.xmniao.xmn.core.businessman.entity.RecommendSellerPackage;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RecommendSellerService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月22日 下午5:48:41 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class RecommendSellerService extends BaseService<RecommendSellerPackage>{
	
	@Autowired
	private RecommendSellerPackageDao sellerPackageDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return sellerPackageDao;
	}

	/**
	 * 方法描述：加载下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午7:42:38 <br/>
	 * @param sellerPackage
	 * @return
	 */
	public List<RecommendSellerPackage> getPackageChoose(RecommendSellerPackage sellerPackage) {
		return sellerPackageDao.getPackageChoose(sellerPackage);
	}

	/**
	 * 方法描述：添加套餐推荐
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午7:57:31 <br/>
	 * @param id
	 * @param homeSort
	 * @return
	 */
	public void addPackage(Integer id, Integer homeSort) {
		sellerPackageDao.updateHomeSort(id,homeSort);
	}

	/**
	 * 方法描述：修改排序
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午8:09:01 <br/>
	 * @param id
	 * @param homeSort
	 */
	public void updatePackageSort(Integer id, Integer homeSort) {
		sellerPackageDao.updateHomeSort(id,homeSort);
	}

	/**
	 * 方法描述：删除
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午8:13:41 <br/>
	 * @param id
	 */
	public void deletePackage(Integer id) {
		sellerPackageDao.updateHomeSort(id,null);
	}

}
