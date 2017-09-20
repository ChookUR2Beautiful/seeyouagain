package com.xmn.designer.dao.material;

import org.apache.ibatis.annotations.Param;

import com.xmn.designer.entity.material.OrderMaterial;

public interface OrderMaterialDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderMaterial record);

    int insertSelective(OrderMaterial record);

    OrderMaterial selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderMaterial record);

    int updateByPrimaryKey(OrderMaterial record);
    
    /**
     * 
     * 方法描述：根据订单号查询订单
     * 创建人：jianming  
     * 创建时间：2016年11月16日 下午5:39:29   
     * @param orderNum
     * @return
     */
	OrderMaterial selectByOrderNum(@Param("orderNum")String orderNum);

    OrderMaterial selectByOrderNo(String orderNo);
}