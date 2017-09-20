/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.live_anchor.dao.TCouponOrderDao;
import com.xmniao.xmn.core.live_anchor.entity.TCouponOrder;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCouponOrderService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-3 下午5:31:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TCouponOrderService extends BaseService<TCouponOrder> {
	
	/**
	 * 注入直播券订单服务
	 */
	@Autowired
	private TCouponOrderDao couponOrderDao;
	
	/**
	 * 注入商家服务
	 */
	@Autowired
	private SellerDao sellerDao;
	
	/**
	 * 注入企业推荐人服务
	 */
	@Autowired
	private TLiveReferrerService referrerService;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return couponOrderDao;
	}

	/**
	 * 方法描述：获取直播粉丝券订单详情 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-21下午2:36:02 <br/>
	 * @param liveCouponOrder
	 * @return
	 */
	public List<TCouponOrder> getDetailInfoList(TCouponOrder liveCouponOrder) {
		List<TCouponOrder> orderDetailInfoList = new ArrayList<TCouponOrder>();
		List<TCouponOrder> detailInfoList = new ArrayList<TCouponOrder>();
		List<String> orderNos=new ArrayList<String>();
		try {
			String eUid = liveCouponOrder.geteUid();
			if(StringUtils.isNotBlank(eUid)){
				List<String> juniorUidList = referrerService.getJuniorUidList(eUid);
				if(juniorUidList!=null && juniorUidList.size()>0){
					liveCouponOrder.setJuniorUids(juniorUidList);
				}
			}
			
			List<TCouponOrder> list = getList(liveCouponOrder);
			for(TCouponOrder couponOrder :list){
				orderNos.add(couponOrder.getOrderSn());
			}
			
			if(orderNos!=null && orderNos.size()>0){
				detailInfoList = couponOrderDao.getDetailInfoListByOrderNo(orderNos.toArray());
			}
			
			for(TCouponOrder baseOrder :list){
				for(TCouponOrder detailOrder:detailInfoList){
					if(baseOrder.getOrderSn().equals(detailOrder.getOrderSn())){
						baseOrder.setUserStatus(detailOrder.getUserStatus());
						baseOrder.setUserTime(detailOrder.getUserTime());
						baseOrder.setLockTime(detailOrder.getLockTime());
						break;
					}
				}
				
				orderDetailInfoList.add(baseOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("进入TCouponOrderService 执行 getDetailInfoList方法异常:"+e.getMessage(), e);
		}
		
		return orderDetailInfoList;
	}
	
	
	/**
	 * 
	 * 方法描述：获取粉丝券统计信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-1上午11:44:59 <br/>
	 * @param couponOrder
	 * @return
	 */
	public Map<String,Object> getTitleInfo(TCouponOrder couponOrder){
		return couponOrderDao.getTitleInfo(couponOrder);
	}
	
	/**
	 * 
	 * 方法描述：获取指定订单ID的粉丝券使用状态，使用时间，分账状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-1上午11:44:59 <br/>
	 * @param couponOrder
	 * @return
	 */
	public TCouponOrder getDetailInfoById(TCouponOrder couponOrder){
		return couponOrderDao.getDetailInfoById(couponOrder);
	}
	

	/**
	 * 
	 * 方法描述：根据商家ID获取商户信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-6下午6:15:34 <br/>
	 * @param id
	 * @return
	 */
	public TSeller getSellerInfoById(long id){
		return sellerDao.getObject(id);
	}
}
