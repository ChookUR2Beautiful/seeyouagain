package com.xmniao.xmn.core.market.service.pay;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.market.pay.EditAddressRequest;
import com.xmniao.xmn.core.common.request.market.pay.EditIsDefaultRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: ShipingAddressService    
* @Description:收货地址实现接口   
* @author: liuzhihao   
* @date: 2016年12月21日 下午3:12:14
 */
public interface ShipingAddressService {

	/**
	 * 查询收货地址列表
	 * @param baseRequest
	 * @return
	 */
	public List<Map<Object,Object>> findAll(BaseRequest baseRequest);
	
	/**
	 * 查询收货地址详情
	 * @param idRequest
	 * @return
	 */
	public Map<Object,Object> findOneById(IDRequest idRequest);
	
	/**
	 * 新增收货地址
	 * @param editAddressRequest
	 * @return
	 */
	public int add(EditAddressRequest editAddressRequest);
	
	/**
	 * 修改收货地址
	 * @param editAddressRequest
	 * @return
	 */
	public int update(EditAddressRequest editAddressRequest);
	
	/**
	 * 修改收货地址状态
	 * @param editIsDefaultRequest
	 * @return
	 */
	public int updateIsDefault(EditIsDefaultRequest editIsDefaultRequest);
	
	/**
	 * 删除收货地址信息
	 * @param idRequest
	 * @return
	 */
	public int remove(IDRequest idRequest);
}
