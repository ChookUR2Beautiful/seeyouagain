package com.xmniao.xmn.core.businessman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillDao;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.SellerIncomeFlowDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerIncomeFlow;
import com.xmniao.xmn.core.xmermanagerment.dao.BXmerDao;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmer;

/**
 * 项目名称： XmnWeb
 * 类名称： SellerIncomeFlowService
 * 类描述：商家收入流水
 * 创建人： lifeng
 * 创建时间： 2016年5月25日下午7:37:51
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Service
public class SellerIncomeFlowService extends BaseService<TSellerIncomeFlow> {
	
	@Autowired
	private AllBillDao billDao;
	
	@Autowired
	private BXmerDao xmerDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private SellerIncomeFlowDao sellerIncomeFlowDao;

	@Override
	protected BaseDao<TSellerIncomeFlow> getBaseDao() {
		return sellerIncomeFlowDao;
	}

	/**
	 * @Description: 商家收入流水列表
	 * @param sellerIncomeFlow
	 * @return 
	 */
	public List<TSellerIncomeFlow> getSellerIncomeFlowList(
			TSellerIncomeFlow sellerIncomeFlow) {
		List<TSellerIncomeFlow> sellerIncomeFlowList = new ArrayList<>();
		TSellerIncomeFlow sif = null; 
		/*
		 * 查询条件
		 */
		Bill bill = new Bill();
		/* 分页参数 */
		bill.setPage(sellerIncomeFlow.getPage());
		bill.setStart(sellerIncomeFlow.getStart());
		bill.setLimit(sellerIncomeFlow.getLimit());
		bill.setOrder(sellerIncomeFlow.getOrder());
		
		// 查询状态为2的（已分账的）
		bill.setStatus(2);
		
		if(sellerIncomeFlow.getStringBid().length() > 0){
			bill.setStringBid(sellerIncomeFlow.getStringBid());//订单号
		}
		if(sellerIncomeFlow.getSellerID() != null){
			bill.setSellerid(sellerIncomeFlow.getSellerID());//商家编号
		}
		if(sellerIncomeFlow.getSellerName() != null && !sellerIncomeFlow.getSellerName().equals("")){
			bill.setSellername(sellerIncomeFlow.getSellerName());//商家名称
		}
		if(sellerIncomeFlow.getOrderType() != null){
			bill.setCouponType(sellerIncomeFlow.getOrderType());//订单类型
		}
		List<Bill> billList = billDao.getAccountedBillList(bill);
		for (Long i=0l;i<billList.size();i++) {
			int index = i.intValue();
			sif = new TSellerIncomeFlow();
			sif.setRowNo(i+1);
			sif.setBid(billList.get(index).getBid());
//			sif.setSdate(billList.get(index).getSdate());
			sif.setZdate(billList.get(index).getZdate());
			sif.setSellerID(billList.get(index).getSellerid());
			sif.setSellerName(billList.get(index).getSellername());
			sif.setNname(billList.get(index).getNname());//用户昵称
			sif.setPaytype(billList.get(index).getPaytype());//支付方式
			sif.setOrderType(billList.get(index).getCouponType());//订单类型
			sif.setPhoneid(billList.get(index).getPhoneid());//手机号
//			if(billList.get(index).getXmerUid()!=null){
//				BXmer xmer = xmerDao.getXmer(billList.get(index).getXmerUid());
//				if(xmer!=null){
//					sif.setXmerID(xmer.getId());
//					sif.setXmerName(xmer.getName());
//				}
//			}
			/* 根据商家ID查询商家的信息*/
//			TSeller seller = new TSeller();
//			seller.setSellerid(billList.get(index).getSellerid());//商家编号
//			if(seller!=null){
//				if(sellerDao.getSellerByWhere(seller)!=null){
//					sif.setSellerAddress(sellerDao.getSellerByWhere(seller).getAddress());
//				}
//			}
//			sif.setSellerPrincipalName(billList.get(index).getFullname());
//			sif.setPrincipalPhoneNo(billList.get(index).getPhoneid());
			sif.setMoney(billList.get(index).getMoney());
			sif.setSellerAmount(billList.get(index).getSellerAmount());
			sellerIncomeFlowList.add(sif);
		}
		return sellerIncomeFlowList;
	}

	/**
	 * @Description: 商家收入流水列表总记录数
	 * @param sellerIncomeFlow
	 * @return 总记录数
	 */
	public Long count(TSellerIncomeFlow sellerIncomeFlow) {
		Bill bill = new Bill();
		bill.setStatus(2);
		if(sellerIncomeFlow.getStringBid().length() > 0){
			bill.setStringBid(sellerIncomeFlow.getStringBid());//订单号
		}
		if(sellerIncomeFlow.getSellerID() != null){
			bill.setSellerid(sellerIncomeFlow.getSellerID());//商家编号
		}
		if(sellerIncomeFlow.getSellerName() != null && !sellerIncomeFlow.getSellerName().equals("")){
			bill.setSellername(sellerIncomeFlow.getSellerName());//商家名称
		}
		
		if(sellerIncomeFlow.getOrderType() != null){
			bill.setCouponType(sellerIncomeFlow.getOrderType());//订单类型
		}
		return billDao.accountedBillCount(bill);
	}
	
}
