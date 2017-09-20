package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 分账数据Mapper
 * 
 * @author YangJing
 *
 */
public interface LedgerMapper {
	
	/**
	 * 保存分账订单
	 * 
	 * @param list
	 * @return
	 */
	int saveLedgerOrder(List<Map<String, String>> list);
	
	/**
	 * 查询分账订单
	 * 
	 * @return
	 */
	List<Map<String, Object>> getLedgerOrder();
	
	/**
	 * 修改分账订单状态
	 * 
	 * @param map
	 * @return
	 */
	int updateLedgerState(Map<String, Object> map);
	
	/**
	 * 删除重复分账订单
	 * 
	 * @return
	 */
	int deleteRepeatLedger();
	
	/**
	 * 删除分账订单
	 * 
	 * @param orderId
	 * @return
	 */
	int deleteLedger(@Param("orderId") String orderId);
}
