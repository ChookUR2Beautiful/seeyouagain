/**
 * 
 */
package com.xmniao.xmn.core.billmanagerment.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.billmanagerment.dao.PackageOrderDao;
import com.xmniao.xmn.core.billmanagerment.entity.PackageOrder;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：PackageOrderService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月24日 上午10:13:31 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class PackageOrderService extends BaseService<PackageOrder>{

	@Autowired
	private PackageOrderDao pOrderDao;
	
	@Override
	protected BaseDao<PackageOrder> getBaseDao() {
		return pOrderDao;
	}
	
	/**
	 * 
	 * 方法描述：获取某个订单发放的所有优惠券
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月25日下午2:12:25 <br/>
	 * @param pOrder
	 * @return
	 */
	public List<Map<String,Object>> getCouponList(PackageOrder pOrder){
		return pOrderDao.getPackageList(pOrder);
	}
	
	/**
	 * 
	 * 方法描述：统计订单总金额，实际支付金额
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月25日下午2:56:16 <br/>
	 * @param pOrder
	 * @return
	 */
	public Map<String,Object> getTitleInfo(PackageOrder porOrder){
		return pOrderDao.titleInfo(porOrder);
	}
	
	/**
	 * 
	 * 方法描述：套餐订单退款
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月23日下午6:26:20 <br/>
	 * @param orderNo
	 * @param description
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class})
	public void refund(String orderNo,String description){
		
		Map<String,String> map = new HashMap<>();
		map.put("orderNo",orderNo);//订单号
		map.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//更新时间
		map.put("description", description);//退款描述
		/**
		 * 1.更新订单状态
		 */
		Integer result = pOrderDao.updateOrderStatus(map);
		if(result !=1){
			log.error("更新订单状态失败");
			throw new RuntimeException("更新订单状态失败");
		}
		
		/**
		 * 2.注销套餐优惠券
		 */
		Integer result2 = pOrderDao.updateGrantStatus(map);
		if(result2 !=1){
			log.error("注销套餐优惠券失败");
			throw new RuntimeException("退款失败，套餐优惠券已使用");
		}
		
		/**
		 * 3.注销套餐抵用券
		 */
		Long count = pOrderDao.countCouponNum(map);
		if(count <1){
			log.info("该订单未发放套餐抵用券");
		}else{
			Integer result3 = pOrderDao.updateCouponStatus(map);
			if(result3 != Integer.valueOf(count.toString())){
				log.error("注销套餐抵用券失败");
				throw new RuntimeException("退款失败，套餐抵用券已使用");
			}
		}
		
	}
}
