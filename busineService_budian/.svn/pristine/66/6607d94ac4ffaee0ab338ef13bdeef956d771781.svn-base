/**    
 * 文件名：FashionServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2017年5月27日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.vstar;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：VstarServiceImpl
 * 
 * 类描述： 星食尚活动大赛
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年5月27日 上午10:52:30 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.dao.vstar.VstarTicketsDetailDao;
import com.xmniao.dao.vstar.VstarTicketsOrderDao;
import com.xmniao.domain.vstar.VstarTicketsDetail;
import com.xmniao.domain.vstar.VstarTicketsOrder;
import com.xmniao.thrift.busine.vstar.FashionService;
import com.xmniao.thrift.common.FailureException;
import com.xmniao.thrift.common.ResponseData;

@Service("vstarServiceImpl")
public class VstarServiceImpl implements FashionService.Iface{

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private VstarTicketsOrderDao vstarTicketsOrderDao;
	
	@Autowired
	private VstarTicketsDetailDao vstarTicketsDetailDao;
	/**
	 * 购买星食尚大赛活动门票，支付回调
	 */
	@Transactional(rollbackFor={Exception.class})
	@Override
	public ResponseData upateTicketsOrder(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("购买星食尚门票活动传入参数:"+paramMap);
		String orderNo = paramMap.get("orderNo");
		Integer payState = Integer.parseInt(paramMap.get("payState"));
		VstarTicketsOrder order = vstarTicketsOrderDao.getOrder(new VstarTicketsOrder(orderNo));
		if(order==null){
			return new ResponseData(1,"找不到对应订单",null);
		}
		if(order.getPayState().equals(Integer.parseInt(paramMap.get("payState")))){
			return new ResponseData(0,"订单已更新成功",null);
		}
		if(order.getPayState()==1){
			return new ResponseData(1,"已成功订单不能更新为其他状态",null);
		}
		if(!this.get(paramMap, order)){
			return new ResponseData(0,"订单验证失败",null);
		}
		VstarTicketsOrder uOrder = new VstarTicketsOrder(orderNo);
		uOrder.setPayState(payState);
		uOrder.setPayTime(new Date());
		uOrder.setPayId(paramMap.get("payId"));
		vstarTicketsOrderDao.updateOrder(uOrder);

		VstarTicketsDetail  ticketDetail = new VstarTicketsDetail();
		ticketDetail.setOrderNo(orderNo);
		
		if(payState==1){
			List<VstarTicketsDetail> ticketDetailList = vstarTicketsDetailDao.getTicketsDetailList(ticketDetail);
			if(ticketDetailList.size()==0){
				throw new FailureException(1,"找不到用户购买的门票信息");
			}
			for(VstarTicketsDetail detail:ticketDetailList){
				if(detail.getSellStatus()!=1){
					throw new FailureException(1,"购买的门票未锁定");
				}
			}
			ticketDetail.setSellStatus(2);
			vstarTicketsDetailDao.updateTicketsDetail(ticketDetail);
		}if(payState==2 || payState==3){
			vstarTicketsDetailDao.resumeTicketsDetail(ticketDetail);
		}
		return new ResponseData(0, "更新成功!", null);
	}
	
	boolean get(Map<String, String> paramMap,VstarTicketsOrder order){
		if(!order.getUid().equals(Integer.parseInt(paramMap.get("uid")))){
			return false; 
		}
		BigDecimal totalAmount = BigDecimal.valueOf(Double.parseDouble(paramMap.get("totalAmount")));
		BigDecimal cash = BigDecimal.valueOf(Double.parseDouble(paramMap.get("samount")));
		BigDecimal balance  =BigDecimal.valueOf(Double.parseDouble(paramMap.get("walletAmount")));
		BigDecimal coin  =BigDecimal.valueOf(Double.parseDouble(paramMap.get("liveCoin")));
		BigDecimal preferential =BigDecimal.valueOf(Double.parseDouble(paramMap.get("preferential")));
		
		if(totalAmount.compareTo(cash.add(balance).add(coin).add(preferential))!=0){
			log.error("传入参数不对应");
			return false;
		}

		if(order.getCash().compareTo(cash)!=0
			|| order.getBalance().compareTo(balance)!=0
			|| order.getCoin().compareTo(coin)!=0
			|| order.getPreferential().compareTo(preferential)!=0){
			log.error("支付明细不对应");
			return false; 
		}
		return true;
	}

}
