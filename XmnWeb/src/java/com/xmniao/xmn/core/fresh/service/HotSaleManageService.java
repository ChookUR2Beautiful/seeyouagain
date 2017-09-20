/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.HotSaleManageDao;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：HotSaleManageService
 * 
 * 类描述： 精选
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月12日 上午9:55:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class HotSaleManageService extends BaseService<ProductInfo>{
	
	@Autowired
	private HotSaleManageDao hotSaleManageDao;

	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseService#getBaseDao()
	 */
	@Override
	protected BaseDao<ProductInfo> getBaseDao() {
		return hotSaleManageDao;
	}
	
	/*
	 * 获取产品列表数据
	 */
	public List<ProductInfo> selectProductInfoList(ProductInfo productInfo) {
		productInfo.setChoice(1);//只查询精选的产品
		List<ProductInfo> productList = null;
		try {
			productList = hotSaleManageDao.getProductList(productInfo);
			for (ProductInfo productInfo2 : productList) {
				if(productInfo2.getChoiceSort()==null){//如果没有设置值，则设置choice的值为sort
					productInfo2.setChoiceSort(productInfo2.getSort());
					updateChoiceSortByPid(productInfo2);
				}
			}
			productList = hotSaleManageDao.getProductList(productInfo);
		} catch (Exception e) {
			log.info("获取积分产品列表异常：" , e);
			e.printStackTrace();
		}
		return productList;
	}
	
	/*
	 * 获取产品总数
	 */
	public long selectProductInfoCount(ProductInfo productInfo) {
		productInfo.setChoice(1);//只查询精选的产品
		return hotSaleManageDao.getProductCount(productInfo);
	}

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： lifeng
	 * 创建时间：2016年8月12日下午2:27:16
	 * @param productInfo
	 */
	public void updateChoiceSortByPid(ProductInfo productInfo) {
		hotSaleManageDao.updateChoiceSortByPid(productInfo);
	}

}
