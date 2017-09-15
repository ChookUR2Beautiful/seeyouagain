package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.common.request.MaterialorderRequest;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.PayRequest;
import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface MaterialDao {

	/**
	 * 
	* @Title: materialList
	* @Description: 查询物料列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> materialList(PageRequest pageRequest);

	/**
	 * 
	* @Title: addMaterOrder
	* @Description: 添加订单信息
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void addMaterOrder(MaterialorderRequest materialOrderRequest);

	/**
	 * 
	* @Title: addMaterOrderItems
	* @Description: 添加订单的物料信息
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void addMaterOrderItems(List<Map<Object, Object>> itemList);

	/**删除物料订单
	 * @param ordersn
	 */
	@DataSource("joint")
	public void delMaterial(String ordersn);
	
	/**
	 * 修改物料信息
	* @Title: updateMateril
	* @Description: 
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void updateMateril(PayRequest payRequest);

	/**删除物料商品订单关系表
	 * @param ordersn
	 */
	@DataSource("joint")
	public void delMaterialItems(String ordersn);

	/**查询物料订单表
	 * @param ordersn
	 * @return
	 */
	@DataSource("joint")
	public Map<Object,Object> findOrderMateral(String ordersn);
	
	/**查询物料订单关系表
	 * @param ordersn
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> findMaterialItem(String ordersn);

	/**更新物料商品销售数量
	 * @param items
	 */
	@DataSource("joint")
	public void updateMaterialByQutatity(Map<Object, Object> items);
	
	/**修改物料订单备注信息
	 * @param map
	 */
	@DataSource("joint")
	public void updateMaterialRemark(Map<Object,Object>map);

}
