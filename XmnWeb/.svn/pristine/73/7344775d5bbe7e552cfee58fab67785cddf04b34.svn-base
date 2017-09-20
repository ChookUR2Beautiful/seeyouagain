/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.cloud_design.entity.AfterSale;
import com.xmniao.xmn.core.cloud_design.entity.CommonMaterial;
import com.xmniao.xmn.core.cloud_design.entity.CommonMaterialPics;
import com.xmniao.xmn.core.cloud_design.entity.MaterialGroup;
import com.xmniao.xmn.core.cloud_design.entity.MaterialOrder;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：OrderManageDao
 * 
 * 类描述： 物料订单
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月18日 下午4:11:58 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface OrderManageDao {
	
	/**
	 * 
	 * 方法描述：获取物料订单列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月18日下午4:22:41 <br/>
	 * @param materialOrder
	 * @return
	 */
	@DataSource(value="designer")
	List<MaterialOrder> getList(MaterialOrder materialOrder);
	
	/**
	 * 
	 * 方法描述：统计订单总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月18日下午4:47:55 <br/>
	 * @param materialOrder
	 * @return
	 */
	@DataSource(value="designer")
	Long count(MaterialOrder materialOrder);
	
	/**
	 * 
	 * 方法描述：更新订单
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月24日下午2:36:04 <br/>
	 * @param materialOrder
	 * @return
	 */
	@DataSource(value="designer")
	Integer update(MaterialOrder materialOrder);
	
	/**
	 * 
	 * 方法描述：删除已关闭订单
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月24日下午9:20:29 <br/>
	 * @param materialOrder
	 * @return
	 */
	@DataSource(value="designer")
	Integer delete(MaterialOrder materialOrder);
	
	/**
	 * 
	 * 方法描述：更新定制订单定制状态
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月1日上午9:41:27 <br/>
	 * @param materialOrder
	 * @return
	 */
	@DataSource(value="designer")
	Integer updateCustomizeMaterial(String orderNo);
	
	/**
	 * 
	 * 方法描述：更新订单状态
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月2日上午10:02:34 <br/>
	 * @param orderNo
	 * @return
	 */
	@DataSource(value="designer")
	Integer updateOrderStatus(MaterialOrder materialOrder);
	/**
	 * 
	 * 方法描述：发货
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月1日下午5:08:40 <br/>
	 * @param materialOrder
	 * @return
	 */
	@DataSource(value="designer")
	Integer deliverUpdate(MaterialOrder materialOrder);
	
	/**
	 * 
	 * 方法描述：插入售后记录
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月2日上午10:15:27 <br/>
	 * @param afterSale
	 * @return
	 */
	@DataSource(value="designer")
	Integer addAfterSale(AfterSale afterSale);
	
	/**
	 * 
	 * 方法描述：更新售后记录
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月2日上午10:15:39 <br/>
	 * @param afterSale
	 * @return
	 */
	@DataSource(value="designer")
	Integer updateAfterSale(AfterSale afterSale);
	
	/**
	 * 
	 * 方法描述：根据订单号获取售后记录
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月2日下午2:02:10 <br/>
	 * @param orderNo
	 * @return
	 */
	@DataSource(value="designer")
	AfterSale getAfterSale(String orderNo);
	
	/**
	 * 
	 * 方法描述：获取物料订单物料详情
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月5日下午4:01:25 <br/>
	 * @param orderNo
	 * @return
	 */
	@DataSource(value="designer")
	CommonMaterial getCommonMaterial(String orderNo);
	
	
	@DataSource(value="designer")
	List<MaterialGroup> getCommonUrls(String orderNo);
	
	
	@DataSource(value="designer")
	List<CommonMaterialPics> getPicUrls(Integer id);
	
	/**
	 * 
	 * 方法描述：获取定制订单物料详情
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月5日下午4:01:25 <br/>
	 * @param orderNo
	 * @return
	 */
	@DataSource(value="designer")
	CommonMaterial getCustomizeMaterial(String orderNo);
	
	/**
	 * 
	 * 方法描述：获取定制订单物料图片
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月5日下午4:01:25 <br/>
	 * @param orderNo
	 * @return
	 */
	@DataSource(value="designer")
	List<String> getCustomizeUrls(String orderNo);
	
	/**
	 * 
	 * 方法描述：更新设计信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月6日下午4:30:54 <br/>
	 * @param materialOrder
	 * @return
	 */
	@DataSource(value="designer")
	Integer saveMaterialPic(MaterialOrder materialOrder);
	
	/**
	 * 
	 * 方法描述：更新初稿路径
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月6日下午4:30:54 <br/>
	 * @param materialOrder
	 * @return
	 */
	@DataSource(value="designer")
	Integer updateStartUrl(MaterialOrder materialOrder);
}
