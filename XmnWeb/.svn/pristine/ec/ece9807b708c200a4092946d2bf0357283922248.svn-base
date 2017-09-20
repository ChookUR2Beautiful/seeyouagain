package com.xmniao.xmn.core.service_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.service_management.dao.PhoneRecordDao;
import com.xmniao.xmn.core.service_management.entity.Member;
import com.xmniao.xmn.core.service_management.entity.Order;
import com.xmniao.xmn.core.service_management.entity.PhoneRecord;

/**
 * 电话呼入控制
 * 
 * @author zhj
 * 
 * 
 */
@Service
public class PhoneRecordService extends BaseService<PhoneRecord> {
	@Autowired
	private PhoneRecordDao phoneRecordDao;
	

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return phoneRecordDao;
	}
	public Pageable<Order> getOrderList(Order order) {
		Pageable<Order> orderList = new Pageable<Order>(order);
		// 订单列表内容
		List<Order> list=phoneRecordDao.getOrderList(order);
		orderList.setContent(list);
		// 总条数
		orderList.setTotal(phoneRecordDao.orderCount(order));
		
		return orderList;
	}
	
	public Pageable<Member> getMerberList(Member member) {
		Pageable<Member> memberList = new Pageable<Member>(member);
		// 获取会员信息
		List<Member> list=phoneRecordDao.getMemberList(member);
		memberList.setContent(list);
		// 总条数
		memberList.setTotal(phoneRecordDao.memberCount(member));
		
		return memberList;
	}
	
	

}