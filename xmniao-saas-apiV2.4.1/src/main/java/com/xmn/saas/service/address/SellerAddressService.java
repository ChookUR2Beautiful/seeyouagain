package com.xmn.saas.service.address;

import java.util.List;

import com.xmn.saas.entity.address.SellerAddress;
import com.xmn.saas.entity.common.Area;

public interface SellerAddressService {

	List<SellerAddress> list(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：根据地址主键和商户id查询地址
	 * 创建人：jianming  
	 * 创建时间：2016年10月17日 下午7:48:29   
	 * @param id
	 * @param sellerId
	 * @return
	 */
	SellerAddress selectByKey(Integer id, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：添加方法
	 * 创建人：jianming  
	 * 创建时间：2016年10月17日 下午8:04:28   
	 * @param sellerAddress
	 */
	void save(SellerAddress sellerAddress,Integer sellerId);
	
	
	/**
	 * 
	 * 方法描述：查询区域
	 * 创建人：jianming  
	 * 创建时间：2016年10月18日 上午11:44:20   
	 * @param pid
	 * @return
	 */
	List<Area> listArea(Integer pid);
	
	/**
	 * 
	 * 方法描述：根据id查询区域名
	 * 创建人：jianming  
	 * 创建时间：2016年10月18日 下午5:03:00   
	 * @param provinceId
	 * @return
	 */
	String selectAreaName(Integer provinceId);
	
	/**
	 * 
	 * 方法描述：修改地址
	 * 创建人：jianming  
	 * 创建时间：2016年10月18日 下午6:21:24   
	 * @param sellerAddress
	 */
	void update(SellerAddress sellerAddress,Integer sellerId);

	/**
	 * 
	 * 方法描述：删除地址
	 * 创建人：jianming  
	 * 创建时间：2016年10月18日 下午8:04:15   
	 * @param id
	 * @param sellerId
	 */
	void remove(Integer id, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：统计子级地址数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 下午8:31:02   
	 * @param cityId
	 * @return
	 */
	Integer countLastArea(Integer cityId);
	
	/**
	 * 
	 * 方法描述：获取默认地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月15日 上午11:52:17   
	 * @param integer 
	 * @return
	 */
	SellerAddress getDesfault(Integer sellerId);

}
