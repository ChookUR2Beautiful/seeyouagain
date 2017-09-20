package com.xmniao.xmn.core.billmanagerment.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillDao;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.util.BillConstants;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.orderService.OrderService;


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
public class AllBillService extends BaseService<Bill> {

	@Autowired
	private AllBillDao allBillDao;
	
	@Resource(name = "orderServiceClient")
	private ThriftClientProxy orderServiceClient;
	
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
	public Pageable<Bill> getBillList(Bill bill) {
		Pageable<Bill> billList = new Pageable<Bill>(bill);
		
		// 订单列表内容
		List<Bill> list=allBillDao.getBillList(bill);
		billList.setContent(list);
		// 总条数
		Long totalNum = this.allBillDao.billCount(bill);
		billList.setTotal(totalNum);
		Double totalAmount=allBillDao.gettotalAmount(bill);
		
		if(null !=list && list.size()>0){
			billList.getContent().get(0).setTotalAmount(totalAmount);
			//billList.getContent().get(0).setFakeTotalNumber(fakeBillNum);
			
//			String fakeTotalAmount="";
//			String fakeBillNum="";
//			if(FakeDataUtil.queryDateAfterOctober(FakeDataUtil.formatDate(bill.getZdateStart()),FakeDataUtil.formatDate(bill.getZdateEnd()))){
//				fakeTotalAmount=String.valueOf(totalAmount);
//				fakeBillNum=String.valueOf(totalNum);
//			}else{
//				if(FakeDataUtil.isNeedQueryAfterOctoberData(FakeDataUtil.formatDate(bill.getZdateStart()),FakeDataUtil.formatDate(bill.getZdateEnd()))){
//					Bill objBill=new Bill();
//					objBill.setZdateStart(DateUtil.smartFormat("2015-11-01"));
//					objBill.setZdateEnd(FakeDataUtil.formatDate(bill.getZdateEnd()));
//					Long totalNumAfterOctober = this.allBillDao.billCount(objBill);
//					Double totalAmountOctober=allBillDao.gettotalAmount(objBill);
//					
//					//BigDecimal tempTotalAmount=FakeDataUtil.getQueryBillAmount(FakeDataUtil.formatDate(bill.getZdateStart()),DateUtil.smartFormat("2015-10-31"),bill.getProvince(),bill.getCity(),totalNum);
//					BigDecimal tempTotalAmount=BigDecimal.valueOf(totalAmount);//真实的订单额查询
//					BigDecimal tempFakeBillNum=FakeDataUtil.getQueryBillNumber(FakeDataUtil.formatDate(bill.getZdateStart()),DateUtil.smartFormat("2015-10-31"),bill.getProvince(),bill.getCity(),totalNum);
//					
//					BigDecimal total=tempTotalAmount.add(BigDecimal.valueOf(totalAmountOctober));
//					BigDecimal totalBillNum=tempFakeBillNum.add(BigDecimal.valueOf(totalNumAfterOctober));
//					
//					fakeTotalAmount=String.valueOf(total);
//					fakeBillNum=String.valueOf(totalBillNum);
//				 }else{
//					//fakeTotalAmount=String.valueOf(FakeDataUtil.getQueryBillAmount(FakeDataUtil.formatDate(bill.getZdateStart()),FakeDataUtil.formatDate(bill.getZdateEnd()),bill.getProvince(),bill.getCity(),totalNum));
//					 fakeTotalAmount=String.valueOf(totalAmount);//真实的订单额查询
//					 fakeBillNum=String.valueOf(FakeDataUtil.getQueryBillNumber(FakeDataUtil.formatDate(bill.getZdateStart()),FakeDataUtil.formatDate(bill.getZdateEnd()),bill.getProvince(),bill.getCity(),totalNum));
//				 }
//			}
//			
//			billList.getContent().get(0).setTotalAmount(Double.valueOf(fakeTotalAmount));
//			//billList.getContent().get(0).setFakeTotalNumber(fakeBillNum);
//			
//			if(bill.getZdateStart()==null & bill.getZdateEnd()==null){//c初始化查询是总条数
//				//总条数
//				Long totalNum2 = this.allBillDao.billCount(bill);
//				//5-10份总条数
//				Long totalNum3=this.allBillDao.billMonthCount(bill);
//				//虚拟的5-10月总条数
//				long fakeBillNums=totalNum2-totalNum3+575242;			
//				
//				billList.getContent().get(0).setFakeTotalNumber(String.valueOf(fakeBillNums));
//			}else{
//				DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
//				Date d2=null;
//				try {
//					 d2=df.parse("2015-05-01 00:00:00");
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}				
//				
//				if(bill.getZdateEnd().getTime()< d2.getTime() ){//小于5月
//					billList.getContent().get(0).setFakeTotalNumber(totalNum.toString());
//				}else{
//					billList.getContent().get(0).setFakeTotalNumber(fakeBillNum);
//				}
//				
//			}
			
		}
		return billList;
	}

	/**
	 * 获取商家列表首单信息（除待支付）
	 * 
	 * @param bill
	 * @return
	 */
	public Pageable<Bill> getFirstBillList(Bill bill) {
		Pageable<Bill> billList = new Pageable<Bill>(bill);
		
		// 订单列表内容
		List<Bill> list=allBillDao.getFirstBillList(bill);
		billList.setContent(list);
		// 总条数
		billList.setTotal(allBillDao.firstBillCount(bill));
		Double totalAount=allBillDao.getFirstTotalAmount(bill);
		if(null !=list && list.size()>0){	
			billList.getContent().get(0).setTotalAmount(totalAount);
		}
		
		return billList;
	}
	/**
	 * 获取订单列表(除待支付)
	 * @param seller
	 * @return
	 */
	public List<Bill> getBillListForExport(Bill bill){
		return allBillDao.getFirstBillList(bill);
	}
	
	/**
	 * 获取订单列表(待支付)
	 * @param seller
	 * @return
	 */
	public List<Bill> getNotPayBillListForExport(Bill bill){
		return allBillDao.getBillNotPayList(bill);
	}
		
	/**
	 * 更加bid查询详情
	 * @param bid
	 * @return
	 */
	public Bill getBillObject(Long bid){		
		Bill bill=allBillDao.getBillObject(bid);
		return bill;
	}
	
	/**
	 * 订单金额详情查询
	 * @param bid
	 * @return
	 */
	public Bill getOrderCmount(Long bid){		
		Bill bill=allBillDao.getBillObject(bid);
		return bill;
	}
	
	/**
	 * 获取商家列表信息（待支付）
	 * 
	 * @param bill
	 * @return
	 */
	public Pageable<Bill> getBillNotPayList(Bill bill) {
		Pageable<Bill> billList = new Pageable<Bill>(bill);
		// 订单列表内容
		List<Bill> list=allBillDao.getBillNotPayList(bill);//getBillList(bill);
		billList.setContent(list);
		// 总条数
		billList.setTotal(allBillDao.getBillNotPayListCount(bill));//billCount(bill));
		Double totalAount=allBillDao.getnotpaytotalAmount(bill);
		if(null !=list && list.size()>0){	
			//billList.getContent().get(0).setTotalAmount(totalAount);
			billList.getContent().get(0).setNotpaytotalAmount(totalAount);
		}
		return billList;
	}
	
	
	/**
	 * 获取商家对应的订单列表
	 * 
	 * @param bill
	 * @return
	 */
	public Pageable<Bill> getBillBySellerid(Bill bill) {
		//表示查询商的所有订单
		Integer status=bill.getStatus();
		if(null != status && 0!=status){//选单个状态时
			int s[]={bill.getStatus()};
			bill.setStrStatus(s);
			bill.setQueryFlag(1);
		}else if(null != status && 0==status){//非退款状态
			int s[]=BillConstants.REFUND_OTHER_STATUS;//1,2,3,6,8,9
			bill.setStrStatus(s);
		}else{//所有状态,除待支付外
			int s[]=BillConstants.REFUND_ALL_STATUS;//1,2,3,4,5,6,7,8,9,9,10,11,12,13
			bill.setStrStatus(s);
		}
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
	
	public Map<String,String> liveLedger(Map<String,String> paraMap){
		OrderService.Client client = (OrderService.Client)(orderServiceClient.getClient());//业务服务
		Map<String, String> res = null;
		try {
			res = client.handleLedger(paraMap);
		} catch (Exception e) {
			log.error("手动分账失败", e);
			
		} finally {
			orderServiceClient.returnCon();
		}
		return res;
	}
	
	
	public Long  getBillCount(Bill bill){
		return allBillDao.billCount(bill);
	}
	
	/**
	 * 
	 * 方法描述：根据满减活动id 获取订单列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日下午8:22:08 <br/>
	 * @param bill
	 * @return
	 */
	public List<Bill>  getBList(Bill bill){
		return allBillDao.getBillList(bill);
	}
	
	/**
	 * 
	 * 方法描述：根据满减活动id,统计订单总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月8日下午8:22:35 <br/>
	 * @param bill
	 * @return
	 */
	public Long  countFullReduction(Bill bill){
		return allBillDao.countFullReduction(bill);
	}
	
	
	public List<Bill>  getFullReductionList(Bill bill){
		return allBillDao.getFullReductionList(bill);
	}
}
