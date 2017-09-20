package com.xmniao.xmn.core.billmanagerment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillDao;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.util.BillConstants;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllBillService
 * 
 * 类描述： 所有订单
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月15日16时00分41秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class ClickFarmingBillService extends BaseService<Bill> {

	@Autowired
	private AllBillDao allBillDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return allBillDao;
	}
	/**
	 * 获取商家列表信息（除待支付）
	 * 
	 * @param bill
	 * @return
	 */
	public Pageable<Bill> getClickFarmingBillList(Bill bill) {
		Pageable<Bill> billList = new Pageable<Bill>(bill);
		
		// 订单列表内容
		List<Bill> list=allBillDao.getBillList(bill);
		billList.setContent(list);
		// 总条数
		billList.setTotal(allBillDao.billCount(bill));
		Double totalAount=allBillDao.gettotalAmount(bill);
		if(null !=list && list.size()>0){	
			billList.getContent().get(0).setTotalAmount(totalAount);
		}
		return billList;
	}
	
	public List<Bill>  getBList(Bill bill){
		return allBillDao.getBillList(bill);
	}
	
}
