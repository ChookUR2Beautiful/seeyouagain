package com.xmniao.xmn.core.marketingmanagement.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TMaterial;
import com.xmniao.xmn.core.marketingmanagement.entity.TMaterialOrder;


/**
* 
* @项目名称：XmnWeb
* 
* @类名称：MaterialOrderDao
* 
* @类描述： 物料订单
* 
* @创建人：yhl
* 
* @创建时间：2016年7月15日14:34:32
* 
* @Copyright©广东寻蜜鸟网络技术有限公司
*/
public interface MaterialOrderDao extends BaseDao<TMaterialOrder> {

	/**
	 * 获取物料列表信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterialOrder>
	 * */
	public List<TMaterialOrder>  getMaterialOrderList(TMaterialOrder tMaterial);
	
	/**
	 * 获取物料列表信息   总数
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterialOrder
	 * @return Long
	 * */
	public Long  getMaterialOrderCount(TMaterialOrder tMaterial);
	
	/**
	 * 获取物料 详细信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterialOrder
	 * @return List<TMaterialOrder>
	 * */
	public TMaterialOrder  getMaterialOrderInfo(Long id);
	
	
	/**
	 * 获取物料商品列表
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterialOrder
	 * @return List<TMaterialOrder>
	 * */
	public List<TMaterial>  getMaterialOrderForProd(String order_sn);
	
	/**
	 * 保存物流信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterialOrder
	 * @return Long
	 * */
	public void  updateShipment(Map<Object, Object> map);
	
	/**
	 * 收货确认
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterialOrder
	 * @return Long
	 * */
	public void  updateGetship(Map<Object, Object> map);
	
	/**
	 * 收货确认
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterialOrder
	 * @return Long
	 * */
	public List<TMaterialOrder> getExportMaterialOrderList(Map<Object, Object> map);

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： lifeng
	 * 创建时间：2016年8月10日下午2:59:56
	 * @param materialOrder
	 */
	public void updateAddress(TMaterialOrder materialOrder);
	
	
}
