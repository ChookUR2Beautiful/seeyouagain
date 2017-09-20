package com.xmniao.xmn.core.fresh.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;
import com.xmniao.xmn.core.fresh.entity.ProductDetail;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface FreshManageDao extends BaseDao<ProductInfo>{
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
	 * 新增产品信息
	 */
	@DataSource("master")
	public void addInfo(ProductInfo productInfo);
	
	/*
	 * 新增产品详情
	 */
	@DataSource("master")
	public void addDetail(ProductDetail productDeatil);
	
	/*
	 * 批量新增产品详情
	 */
	@DataSource("master")
	public Integer addBatchDetail(List<ProductInfo> list);
	
	
	/*
	 * 查询某个产品的所有信息
	 */
	@DataSource("slave")
	public Map<String,Object> getFreshAllInfo(Integer pid);
	
	/*
	 * 根据codeId更新产品信息
	 */
	@DataSource("master")
	public int updateInfo(ProductInfo productInfo);
	
	/*
	 * 修改codeId更新产品详情
	 */
	@DataSource("master")
	public int updateDetail(ProductDetail productDetail);
	
	/**
	 * @Description: 条件查询某个商品的信息
	 * @Param:productInfo
	 * @return:ProductInfo
	 * @author:lifeng
	 * @time:2016年6月18日下午2:20:05
	 */
	@DataSource("slave")
	public ProductInfo getProductInfoByParam(ProductInfo productInfo);

	/**
	 * 方法描述：批量上线，下线
	 * 创建人： lifeng
	 * 创建时间：2016年7月21日上午11:23:10
	 * @param productInfo
	 * @return
	 */
	@DataSource("master")
	public Integer batch(ProductInfo productInfo);

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： lifeng
	 * 创建时间：2016年8月12日下午4:06:41
	 * @param productInfo
	 */
	@DataSource("master")
	public void updateSortByPid(ProductInfo productInfo);

	/**
	 * 方法描述：获取商品choose列表
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月27日下午4:40:58 <br/>
	 * @param productInfo
	 * @return
	 */
	@DataSource("slave")
	public List<ProductInfo> getProductInfoSelect(ProductInfo productInfo);

	/**
	 * 方法描述：根据codeid查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月28日下午9:13:44 <br/>
	 * @param codeId
	 * @return
	 */
	@DataSource("slave")
	public ProductInfo getByCodeId(Long codeId);

	/**
	 * 方法描述：修改库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月18日下午1:56:31 <br/>
	 * @param freshInfo
	 */
	@DataSource("master")
	public void updateStore(ProductInfo freshInfo);

	/**
	 * 方法描述：转移库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午2:09:11 <br/>
	 * @param activityProduct
	 */
	@DataSource("master")
	public void transferStore(ActivityProduct activityProduct);
	
	@DataSource("master")
	public void transferStore(@Param("sellStore")Integer store,@Param("codeId")Long codeId);

	/**
	 * 方法描述：修改活动时修改商品库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午2:21:11 <br/>
	 * @param store
	 * @param codeId 
	 */
	@DataSource("master")
	public void updateActivityProduct(@Param("store")int store, @Param("codeId")Long codeId);

	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月18日下午6:35:31 <br/>
	 * @param typeId
	 * @return
	 */
	public List<ProductInfo> getProductInfoByType(Integer typeId);

	/**
	 * 方法描述：根据多个codeId获取商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月8日下午1:57:18 <br/>
	 * @param codeIds
	 * @return
	 */
	public List<ProductInfo> getByCodeIds(List<String> codeIds);
	
	
}
