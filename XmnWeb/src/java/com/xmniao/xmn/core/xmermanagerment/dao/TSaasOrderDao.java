package com.xmniao.xmn.core.xmermanagerment.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmermanagerment.entity.TSaasOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaasOrderDao
 * 
 * 类描述： 寻蜜客Saas套餐DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月3日 下午2:39:13 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TSaasOrderDao extends BaseDao<TSaasOrder>{

	/**
	 * 
	 * @Title: getSaasOrder 
	 * @Description:获取Saas套餐订单信息
	 */
	@DataSource("savle")
	TSaasOrder getSaasOrder(TSaasOrder saasOrder);
	
	/**
	 * 
	 * @Title: updateSaasOrderSaleInfo 
	 * @Description:更新Saas套餐订单销售情况
	 */
	@DataSource("savle")
	int updateSaasOrderSaleInfo(Map<String,Object> uMap);

	/**
	 * @Description: 
	 * @Param:saasOrder
	 * @return:List<TSaasOrder>
	 * @author:lifeng
	 * @time:2016年6月15日上午11:31:47
	 */
	@DataSource("joint")
	List<TSaasOrder> getSaasOrderList(TSaasOrder saasOrder);

	/**
	 * @Description: 
	 * @Param:saasOrder
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月15日上午11:32:05
	 */
	@DataSource("joint")
	Long getCountByParam(TSaasOrder saasOrder);

	@DataSource("joint")
	String selectOrderRelations(@Param("saasOrdersn") String saasOrdersn);
	
	/**
	 * 
	 * 方法描述：根据签店商家及寻蜜客信息，查询签店时所用SAAS来源订单 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月8日下午6:26:12 <br/>
	 * @param sellerid
	 * @param uid
	 * @return
	 */
	TSaasOrder getSellerBelongSassOrder(@Param("sellerid")Integer sellerid,@Param("uid")Integer uid);
	
	/**
	 * 
	 * 方法描述：扣除一个V客赠送主播SAAS名额 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月10日下午3:51:06 <br/>
	 * @param ordersn
	 * @return
	 */
	int reduceAnchorSaasOrder(String ordersn);
	
	/**
	 * 
	 * 方法描述：获取最近一个已卖出至少一套的订单 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月10日下午3:53:50 <br/>
	 * @param uid
	 * @return
	 */
	TSaasOrder getVerSaasOrer(Integer uid);
	
	/**
	 * 方法描述：退还V客赠送SAAS给主播名额数 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月11日下午4:52:49 <br/>
	 * @param ordersn
	 * @return
	 */
	int returnVkeSaasOrderNums(Map<String,Object> uMap);
	
	
	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月11日下午5:28:38 <br/>
	 * @param ordersn
	 * @return
	 */
	int updataLiveSaasOrderNums(Map<String,Object> uMap);
	
}