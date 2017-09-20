/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.system_settings.service.UserService;
import com.xmniao.xmn.core.vstar.dao.TicketsDetailDao;
import com.xmniao.xmn.core.vstar.dao.TicketsOrderDao;
import com.xmniao.xmn.core.vstar.entity.TicketsDetail;
import com.xmniao.xmn.core.vstar.entity.TicketsOrder;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TicketsOrderService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月8日 下午5:50:04
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Service
public class TicketsOrderService extends BaseService<TicketsOrder> {

	@Autowired
	private TicketsOrderDao ticketsOrderDao;

	@Autowired
	private TicketsDetailDao ticketsDetailDao;

	@Autowired
	private UserService userService;

	@Override
	protected BaseDao getBaseDao() {
		return ticketsOrderDao;
	}

	@Override
	public List<TicketsOrder> getList(TicketsOrder t) {
		return ticketsOrderDao.getList(t);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月9日上午11:43:12 <br/>
	 * 
	 * @param id
	 * @return
	 */
	public List<TicketsDetail> geTicketDetailsByIds(Integer id) {
		return ticketsDetailDao.getByOrderId(id);
	}

	/**
	 * 方法描述：生成赠送订单 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午5:29:32 <br/>
	 * 
	 * @param id
	 * @param asList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addGiveOrder(Burs urs, List<String> detailIds) {
		List<TicketsDetail> byIds = ticketsDetailDao.getByIds(detailIds);
		Map<Integer, List<TicketsDetail>> map = makeSeatGroup(byIds);
		Set<Entry<Integer, List<TicketsDetail>>> entrySet = map.entrySet();
		Iterator<Entry<Integer, List<TicketsDetail>>> iterator = entrySet.iterator();
		List<TicketsOrder> orders = new ArrayList<>();
		while (iterator.hasNext()) {
			String orderNo = String.valueOf(System.currentTimeMillis()/1000L + get5Random());
			TicketsOrder ticketsOrder = new TicketsOrder();
			Entry<Integer, List<TicketsDetail>> next = iterator.next();
			List<TicketsDetail> value = next.getValue();
			ticketsOrder.setActivityId(next.getKey());
			ticketsOrder.setOrderNo(orderNo);
			ticketsOrder.setUid(urs.getUid());
			ticketsOrder.setNname(urs.getNname());
			ticketsOrder.setPhone(urs.getPhone());
			ticketsOrder.setNum(value.size());
			ticketsOrder.setTotalAmount(BigDecimal.ZERO);
			ticketsOrder.setPayState(1);
			ticketsOrder.setBalance(BigDecimal.ZERO);
			ticketsOrder.setCash(BigDecimal.ZERO);
			ticketsOrder.setCoin(BigDecimal.ZERO);
			ticketsOrder.setPreferential(BigDecimal.ZERO);
			ticketsOrder.setCreateTime(new Date());
			ticketsOrder.setPayTime(new Date());
			ticketsOrder.setDescription("赠送");
			orders.add(ticketsOrder);
			for (TicketsDetail ticketsDetail : value) {
				ticketsDetail.setOrderNo(orderNo);
				ticketsDetail.setUid(urs.getUid());
				int i = ticketsDetailDao.updateToSell(ticketsDetail);
				if (i <= 0) {
					throw new RuntimeException("门票重复购买");
				}
			}
		}
		ticketsOrderDao.addBatch(orders);

	}

	/**
	 * 方法描述：根据大赛分组 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午5:48:20 <br/>
	 * 
	 * @param byIds
	 * @return
	 */
	private Map<Integer, List<TicketsDetail>> makeSeatGroup(List<TicketsDetail> byIds) {
		Map<Integer, List<TicketsDetail>> map = new HashMap<>();
		for (TicketsDetail ticketsDetail : byIds) {
			if (map.get(ticketsDetail.getFid()) == null) {
				ArrayList<TicketsDetail> list = new ArrayList<>();
				list.add(ticketsDetail);
				map.put(ticketsDetail.getFid(), list);
			} else {
				List<TicketsDetail> list = map.get(ticketsDetail.getFid());
				list.add(ticketsDetail);
			}
		}
		return map;
	}
	
	
	public static String get5Random() {
		String str = "";
		str += (int)(Math.random()*9+1);
		for(int i = 0; i < 4; i++){
			str += (int)(Math.random()*10);
		}
		return str;
	}

	
}
