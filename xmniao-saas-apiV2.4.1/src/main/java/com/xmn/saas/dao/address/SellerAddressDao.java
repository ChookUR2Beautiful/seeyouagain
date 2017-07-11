package com.xmn.saas.dao.address;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.address.SellerAddress;

public interface SellerAddressDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerAddress record);

    int insertSelective(SellerAddress record);

    SellerAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerAddress record);

    int updateByPrimaryKey(SellerAddress record);
    
    /**
     * 
     * 方法描述：查询所有地址
     * 创建人：jianming  
     * 创建时间：2016年10月17日 下午5:14:57   
     * @param sellerId
     * @return
     */
	List<SellerAddress> list(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：根据商户地址和主键查询
	 * 创建人：jianming  
	 * 创建时间：2016年10月17日 下午7:49:38   
	 * @param id
	 * @param sellerId
	 * @return
	 */
	SellerAddress selectByKey(@Param("id")Integer id,@Param("sellerId") Integer sellerId);
	
	/**
	 * 
	 * 方法描述：取消默认地址状态
	 * 创建人：jianming  
	 * 创建时间：2016年10月18日 下午5:09:48
	 */
	void cancelDefault(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：改变为删除状态
	 * 创建人：jianming  
	 * 创建时间：2016年10月18日 下午8:05:14   
	 * @param id
	 * @param sellerId
	 */
	void updateStatus(@Param("id")Integer id, @Param("sellerId")Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询默认地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月15日 上午11:54:32   
	 * @param sellerId
	 * @return
	 */
	SellerAddress selectDefault(Integer sellerId);
	
	
}