package com.xmniao.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.order.MaterialOrderBean;

/**
 * 物料订单DAO
 * @author Jian
 *
 */
public interface MaterialOrderDao {
	public MaterialOrderBean getByOrderSn(@Param("orderSn")String orderSn);
	
	public int updateForPayComplete(MaterialOrderBean bean);

	public Map<String,Object> getMaterialOrderInfo(@Param("orderSn")String orderSn);
	
	public List<Map<String,Object>> getMaterialOrderItem(@Param("orderSn")String orderSn);
}
