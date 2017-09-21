package com.xmniao.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.order.ActivityProduct;
import com.xmniao.domain.order.ProductInfo;


public interface FreshManageDao{

	/**
	 * 方法描述：修改库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月18日下午1:56:31 <br/>
	 * @param freshInfo
	 */

	public void updateStore(ProductInfo freshInfo);

	/**
	 * 方法描述：转移库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午2:09:11 <br/>
	 * @param activityProduct
	 */
	public void transferStore(ActivityProduct activityProduct);

	/**
	 * 方法描述：修改活动时修改商品库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午2:21:11 <br/>
	 * @param store
	 * @param codeId 
	 */
	public void updateActivityProduct(@Param("store")int store, @Param("codeId")Long codeId);
	
	/**
	 * 
	 * 方法描述：还原规格库存
	 * 创建人：jianming  
	 * 创建时间：2017年3月1日 下午5:06:18   
	 * @param store
	 * @param codeId
	 * @param pvIds
	 * @return 
	 */
	public int updateActivityGroup(@Param("store")int store,@Param("codeId") Long codeId,@Param("pvIds") String pvIds);
	
	
}
