package com.xmniao.xmn.core.fresh.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.ProductDetail;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface HotSaleManageDao extends BaseDao<ProductInfo>{
	/*
	 * 按需求查询产品列表
	 */
	@DataSource("slave")
	public List<ProductInfo> getProductList(ProductInfo productInfo);
	
	/*
	 * 按产品编号查询产品详情
	 */
	@DataSource("slave")
	public ProductDetail getProductDetail(int codeId);
	
	/*
	 * 查询符合需求的产品列表总数
	 */
	@DataSource("slave")
	public long getProductCount(ProductInfo productInfo);
	
	/*
	 * 查询某个产品的所有信息
	 */
	@DataSource("slave")
	public Map<String,Object> getFreshAllInfo(Integer pid);

	/**
	 * 方法描述：修改精选产品的排序
	 * 创建人： lifeng
	 * 创建时间：2016年8月12日下午2:30:36
	 * @param productInfo
	 */
	public void updateChoiceSortByPid(ProductInfo productInfo);
	
	
}
