package com.xmniao.xmn.core.marketingmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.marketingmanagement.dao.MaterialOrderDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TMaterial;
import com.xmniao.xmn.core.marketingmanagement.entity.TMaterialOrder;
import com.xmniao.xmn.core.xmermanagerment.dao.BXmerDao;

/**
 * 
 * */
@Service
public class MaterialOrderService extends BaseService<TMaterialOrder> {

	@Autowired 
	private MaterialOrderDao materialOrderDao;
	
	@Autowired
	private BXmerDao bxmerDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Override
	protected BaseDao<TMaterialOrder> getBaseDao() {
		return materialOrderDao;
	}
	
	/**
	 * 获取物料列表信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public List<TMaterialOrder>  getMaterialOrderList(TMaterialOrder tMaterial){
		List<TMaterialOrder> mrList = materialOrderDao.getMaterialOrderList(tMaterial);
		return mrList;
	}
	
	
	/**
	 * 获取物料详细信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public Long  getMaterialOrderCount(TMaterialOrder tMaterial){
		Long count = materialOrderDao.getMaterialOrderCount(tMaterial);
		return count;
	}
	
	
	/**
	 * 获取物料列表信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public TMaterialOrder  getMaterialOrderInfo(Long id){
		TMaterialOrder materialOrder = materialOrderDao.getMaterialOrderInfo(id);
		if(materialOrder.getUid()!=null){
			materialOrder.setUserInfoName(bxmerDao.getXmerName(materialOrder.getUid().intValue()));//封装用户名
		}
		return materialOrder;
	}
	
	/**
	 * 获取物料订单商品列表
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public List<TMaterial>  getMaterialOrderForProd(String order_sn){
		return materialOrderDao.getMaterialOrderForProd(order_sn);
	}
	
	/**
	 * 
	 * 方法描述：获取商家信息<br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年6月6日上午11:35:41 <br/>
	 * @param sellerids
	 * @return
	 */
	public List<TSeller> getMaterialOrderForSeller(String sellerids){
		return sellerDao.getSellerBaseList(sellerids);
	}
	
	/**
	 * 保存物流信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public void  updateShipment(Map<Object, Object> map){
		materialOrderDao.updateShipment(map);
	}
	
	/**
	 * 确认收货
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public void  updateGetship(Map<Object, Object> map){
		materialOrderDao.updateGetship(map);
	}
	
	/**
	 * 导出订单
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return map 日期参数
	 * */
	public List<TMaterialOrder> getExportMaterialOrderList(Map<Object, Object> map){
		return materialOrderDao.getExportMaterialOrderList(map);
	}

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： lifeng
	 * 创建时间：2016年8月10日下午2:59:34
	 * @param materialOrder
	 */
	public void updateAddress(TMaterialOrder materialOrder) {
		this.materialOrderDao.updateAddress(materialOrder);
	}
	
	
	
}
