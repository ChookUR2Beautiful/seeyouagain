package com.xmniao.xmn.core.billmanagerment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillBargainDao;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillDao;
import com.xmniao.xmn.core.billmanagerment.entity.BillBargain;

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
public class BillBargainService extends BaseService<BillBargain> {

	@Autowired
	private AllBillBargainDao billBargainDao;

	@Autowired
	private AllBillDao allBillDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return billBargainDao;
	}

	/**
	 * @Title:getSalesman
	 * @Description:获取商家列表信息（除待支付）
	 * @param billBargain
	 * @return List<BillBargain>
	 * @throw
	 */
	public List<BillBargain> getBillBargainList(BillBargain billBargain) {
		List<BillBargain> billBarginList = billBargainDao.getBillBargainList(billBargain); 
		return billBarginList;
	}
	
	/**
	 * @Title:exportBillBargainList
	 * @Description:导出商家列表信息（除待支付）
	 * @param billBargain
	 * @return List<BillBargain>
	 * @throw
	 */
	public List<BillBargain> exportBillBargainList(BillBargain billBargain) {
		return billBargainDao.exportBillBargainList(billBargain);
	}

	/**
	 * @Title:billBargainCount
	 * @Description:获取爆品订单信息列表条数（除待支付）
	 * @param billBargain
	 * @return Long
	 * @throw
	 */
	public Long billBargainCount(BillBargain billBargain) {
		return billBargainDao.billBargainCount(billBargain);
	}

	/**
	 * @Title:getBillBargain
	 * @Description:根据爆品订单表id查询爆品订单详情
	 * @param bpid
	 * @return BillBargain
	 * @throw
	 */
	public BillBargain getBillBargain(String bid) {
		BillBargain billbargain = billBargainDao.getBillBargain(bid);
		return billbargain;
	}

	/**
	 * 获取订单列表(待支付)
	 * 
	 * @param seller
	 * @return
	 */
	public List<BillBargain> getNotPayBillListForExport(BillBargain billBargain) {
		return billBargainDao.getBillBargainList(billBargain);
	}
	/**
	 * @Title:billbarginRefund
	 * @Description:已分账爆品订单退款接口
	 * @param billBargain
	 * @return Map<String,String>
	 * @throw
	 */
/*	public Map<String, String> billbarginRefund(BillBargain billBargain) {
//		FreshRefundService.Client client = (FreshRefundService.Client)(freshSubRefundServiceClient.getClient());//支付服务
		Map<String, String> result = new HashMap<String,String>();
		try {
//			result = client.FreshRefund(refundRequest);
		} catch (Exception e) {
			log.error("退款失败", e);
		} finally {
//			freshSubRefundServiceClient.returnCon();
		}
		return result;
	}*/
}
