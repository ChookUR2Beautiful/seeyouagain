package com.xmniao.xmn.core.billmanagerment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.dao.BillRecordDao;
import com.xmniao.xmn.core.billmanagerment.entity.BillRecord;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BillRecordService
 * 
 * 类描述： 申诉订单历史
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月15日16时00分41秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class BillRecordService extends BaseService<BillRecord> {

	@Autowired
	private BillRecordDao billRecordDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return billRecordDao;
	}
	/**
	 * 获取商家列表信息
	 * 
	 * @param refund
	 * @return
	 */
	public Pageable<BillRecord> getRefundBillList(BillRecord billRecord) {
		Pageable<BillRecord> refundList = new Pageable<BillRecord>(billRecord);
		// 订单列表内容
		refundList.setContent(billRecordDao.getRefundBillHistoryList(billRecord));
		// 总条数
		refundList.setTotal(billRecordDao.refundBillHistoryCount(billRecord));
		return refundList;
	}
	
}
