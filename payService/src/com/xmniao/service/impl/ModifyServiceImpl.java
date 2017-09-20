package com.xmniao.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilString;
import com.xmniao.common.XmnConstants;
import com.xmniao.dao.WalletRecordMapper;
import com.xmniao.dao.XmerWallerMapper;
import com.xmniao.entity.WalletRecord;
import com.xmniao.service.ModifyService;
import com.xmniao.service.WalletService;
import com.xmniao.service.pay.XmerWalletServiceImpl;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.XmerWalletService;
/**
 * 调单
 * @author Administrator
 *
 */
@Transactional
@Service("modifyService")
public class ModifyServiceImpl implements ModifyService {
	// 初始日志类
	private final Logger log = Logger.getLogger(ModifyServiceImpl.class);
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private WalletRecordMapper walletRecordMapper;
	
	@Autowired
	private XmerWallerMapper xmerWallerMapper;
	
	@Autowired
	private XmerWalletServiceImpl xmerWalletServiceImpl;
	
	private BigDecimal ZERO = BigDecimal.valueOf(0.00);
	
	@Transactional(rollbackFor = {FailureException.class,RuntimeException.class } ,isolation= Isolation.SERIALIZABLE, timeout=100)
	@Override
	public Map<String, String> modifyBalance(Map<String, String> formaerMap,
			Map<String, String> laterMap) throws FailureException {
		
		String orderId = formaerMap.get("orderId");
		
		Map<String,String> resultMap=null;
		List<Map<String,Object>> updateList = new ArrayList<Map<String,Object>>();
		
		
		//1.修改调单前后用户分账金额
		String uId = formaerMap.get("memberId");
		if(UtilString.stringIsNotBlank(uId)){
			updateList.add(getUpdateMap(uId,1,ZERO,
					getAmount(formaerMap.get("memberAmount"),false),ZERO,orderId,19));
			updateList.add(getUpdateMap(uId,1,ZERO,
					getAmount(laterMap.get("memberAmount"),true),ZERO,orderId,20));
		}
		/******************************               消费者钱包调整MAP                        ******************************/	
		
		String formaerSellerId = formaerMap.get("sellerId");
		String formaerMikeId = formaerMap.get("mikeId");
		
		//1.修改调单前商家的营收金额 和寻蜜客分账 金额
		if(UtilString.stringIsNotBlank(formaerSellerId)
				&& UtilString.stringIsNotBlank(formaerMikeId)
				&& formaerSellerId.equals(formaerMikeId)){

			updateList.add(getUpdateMap(formaerSellerId,2,getAmount(formaerMap.get("sellerAmount"),false),ZERO,
					getAmount(formaerMap.get("mikeAmount"),false),orderId,19));
		}else{
			if(true){

				updateList.add(getUpdateMap(formaerSellerId,2,getAmount(formaerMap.get("sellerAmount"),false),ZERO,
						ZERO,orderId,19));
			}
			if(UtilString.stringIsNotBlank(formaerMap.get("mikeId"))){

				updateList.add(getUpdateMap(formaerMikeId,2,ZERO,ZERO,
						getAmount(formaerMap.get("mikeAmount"),false),orderId,19));
			}
		}
		
		String laterSellerId = laterMap.get("sellerId");
		String laterMikeId = laterMap.get("mikeId");
		//1.修改调单后商家的营收金额 和寻蜜客分账 金额
		if(UtilString.stringIsNotBlank(laterSellerId)
				&& UtilString.stringIsNotBlank(laterMikeId)
				&& laterSellerId.equals(laterMikeId)){

			updateList.add(getUpdateMap(laterSellerId,2,getAmount(laterMap.get("sellerAmount"),true),ZERO,
					getAmount(laterMap.get("mikeAmount"),true),orderId,20));
		}else{
			if(true){
				updateList.add(getUpdateMap(laterSellerId,2,getAmount(laterMap.get("sellerAmount"),true),ZERO,
						ZERO,orderId,20));
			}
			if(UtilString.stringIsNotBlank(laterMap.get("mikeId"))){
				updateList.add(getUpdateMap(laterMikeId,2,ZERO,ZERO,
						getAmount(laterMap.get("mikeAmount"),true),orderId,20));
			}
		}
		
		if(!UtilString.stringIsNotBlank(formaerSellerId) || !UtilString.stringIsNotBlank(laterSellerId)){
			throw new RuntimeException("调单前后，商家账号不能为空");
		}
		/******************************              商户及寻蜜客钱包调整MAP                         ******************************/	
		
		
		
		//调单前 用户区域合作商
		String formaerBpartnerId = formaerMap.get("bpartnerId");
		//调单前 消费合作商
		String formaerCpartnerId = formaerMap.get("cpartnerId");
		
		//调单后 用户区域合作商
		String laterBpartnerId = laterMap.get("bpartnerId");
		//调单后 消费合作商
		String laterCpartnerId = laterMap.get("cpartnerId");
		
		if(UtilString.stringIsNotBlank(formaerBpartnerId)
				&& UtilString.stringIsNotBlank(formaerCpartnerId)
				&& formaerBpartnerId.equals(formaerCpartnerId)){
			//同一个
			BigDecimal sellerAmount = BigDecimal.valueOf(0D); 
			//3.修改调单前后用户区域合作商分账金额
			sellerAmount = sellerAmount.add(getAmount(formaerMap.get("bpartnerAmount"),false));
			
			//4.修改调单前后消费区域合作商分账金额
			sellerAmount = sellerAmount.add(getAmount(formaerMap.get("cpartnerAmount"),false));

			updateList.add(getUpdateMap(formaerBpartnerId,3,sellerAmount,ZERO,
					ZERO,orderId,19));

		}else{
		
			//修改调单前用户区域合作商分账金额
			if(UtilString.stringIsNotBlank(formaerBpartnerId)){
				updateList.add(getUpdateMap(formaerBpartnerId,3,getAmount(formaerMap.get("bpartnerAmount"),false),
						ZERO,ZERO,orderId,19));
			}

			//修改调单前消费区域合作商分账金额
			if(UtilString.stringIsNotBlank(formaerCpartnerId)){
				updateList.add(getUpdateMap(formaerCpartnerId,3,getAmount(formaerMap.get("cpartnerAmount"),false),
						ZERO,ZERO,orderId,19));
			}
	
		}
		/******************************               消费合作商及用户合作商调单前钱包调整MAP                  ******************************/	
		

		if(UtilString.stringIsNotBlank(laterBpartnerId)
				&& UtilString.stringIsNotBlank(laterCpartnerId)
				&& laterBpartnerId.equals(laterCpartnerId)){
			
			//同一个
			BigDecimal sellerAmount = BigDecimal.valueOf(0D); 
			//3.修改调单前后用户区域合作商分账金额
			sellerAmount = sellerAmount.add(getAmount(laterMap.get("bpartnerAmount"),true));
			
			//4.修改调单前后消费区域合作商分账金额
			sellerAmount = sellerAmount.add(getAmount(laterMap.get("cpartnerAmount"),true));
			updateList.add(getUpdateMap(laterBpartnerId,3,sellerAmount,ZERO,
					ZERO,orderId,20));
			
		}else{
			
			//3.修改调单前后用户区域合作商分账金额
			if(UtilString.stringIsNotBlank(laterBpartnerId)){
				updateList.add(getUpdateMap(laterBpartnerId,3,getAmount(laterMap.get("bpartnerAmount"),true),
						ZERO,ZERO,orderId,20));
			}
			
			//4.修改调单前后消费区域合作商分账金额
			if(UtilString.stringIsNotBlank(laterCpartnerId)){
				updateList.add(getUpdateMap(laterCpartnerId,3,getAmount(laterMap.get("cpartnerAmount"),true),
						ZERO,ZERO,orderId,20));
			}
		}
		
		List<Map<String,Object>> newList = getUpdateList(updateList);
		log.info("newList.size:"+newList.size()+"\r\ndata:"+newList);
		/******************************               消费合作商及用户合作商调单后钱包调整MAP                    ******************************/
		
		//修改对应钱包
		for(int i=0,size=newList.size();i<size;i++){
			resultMap = walletService.updateWalletAmount2(newList.get(i));
			if(!resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS)){
				throw new RuntimeException("调单"+(((int)newList.get(i).get("rtype"))==19?"前":"后")
						+(((int)newList.get(i).get("userType"))==1?"用户":(((int)newList.get(i).get("userType"))==2?"商户":"合作商"))
						+"ID:"+newList.get(i).get("uId")+resultMap.get("Msg"));
				//throw new FailureException(1,resultMap.get("Msg"));
			}
		}
		
		return null;
	}
	
	/**
	 * 该单是否已经调单过
	 */
	@Override
	public boolean isModifyed(String orderId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rtype", 19);
		map.put("remarks", orderId);
		return walletRecordMapper.getRecordCount(map)>0?true:false;
	}

	private Map<String,Object> getUpdateMap(String uId,int userType,BigDecimal sellerAmount,BigDecimal balance,BigDecimal commision,String orderId,int rtype){

		Map<String,Object> updateMap = new HashMap<String,Object>();
		updateMap.put("uId", uId);
		updateMap.put("userType", userType);
		updateMap.put("rtype", rtype);
		updateMap.put("remarks",orderId);
		updateMap.put("balance", balance);
		updateMap.put("commision", commision);
		updateMap.put("seller_amount", sellerAmount);
		return updateMap;
	}
	
	//去重
	private List<Map<String,Object>> getUpdateList(List<Map<String,Object>> updateList){
		Set<Integer> delSet=new HashSet<Integer>(); 
		List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
		Map<String,Object> proMap = null;
		Map<String,Object> nextMap = null;
		
		//输出到日志
		for(int i=0,size=updateList.size();i<size;i++){
			log.info("updateList["+i+"]:"+updateList.get(i));
		}
		
		log.info("------------------------------------------------");
		
		//将同一用户对应金额不变的字段填0
		for(int i=0,size=updateList.size();i<size;i++){
			proMap = updateList.get(i);
			for(int j=i+1;j<size;j++){
				nextMap = updateList.get(j);
				walletAmountToUpdate(proMap,nextMap);
			}
		}
		
		//去除空改变金额为0的数据
		Iterator<Map<String,Object>> iter = updateList.iterator();
		 while(iter.hasNext()){
			 Map<String,Object> m = iter.next();
				if(((BigDecimal)m.get("seller_amount")).compareTo(ZERO)==0
						&&((BigDecimal)m.get("balance")).compareTo(ZERO)==0
						&&((BigDecimal)m.get("commision")).compareTo(ZERO)==0){
					iter.remove();
				}
		}
		
		//查询修改前后金额不变的MAP
		for(int i=0,size=updateList.size();i<size;i++){
			proMap = updateList.get(i);
			for(int j=i+1;j<size;j++){
				nextMap = updateList.get(j);
				if(walletAmountEquivalent(proMap,nextMap)){
					delSet.add(i);
					delSet.add(j);
					log.info("-------------"+i+":"+j+"-----------------");
				}
			}
		}
	
		if(delSet.size()==0){
			return updateList;
		}
		
		//返回没有修改前后金额不变的MAP
		for(int i=0;i<updateList.size();i++){
			if(!delSet.contains(i)){
				newList.add(updateList.get(i));
			}else{
				//System.out.println(i+"不复制");
			}
		}
		
		
		return newList;
	}
	
	//判定是否是同一账号且钱包修改金额是否一致
	private boolean walletAmountEquivalent(Map<String,Object> proMap,Map<String,Object> nextMap){
		if(proMap.get("uId").equals(nextMap.get("uId"))
				&& ((BigDecimal)proMap.get("balance")).add((BigDecimal)nextMap.get("balance")).compareTo(ZERO) ==0
				&& ((BigDecimal)proMap.get("seller_amount")).add((BigDecimal)nextMap.get("seller_amount")).compareTo(ZERO)==0
				&& ((BigDecimal)proMap.get("commision")).add((BigDecimal)nextMap.get("commision")).compareTo(ZERO)==0
				&& proMap.get("userType").equals(nextMap.get("userType"))
				&& !proMap.get("rtype").equals(nextMap.get("rtype"))){
			return true;
		}
		return false;
	}
	
	private void walletAmountToUpdate(Map<String,Object> proMap,Map<String,Object> nextMap){
		if(proMap.get("uId").equals(nextMap.get("uId"))
				&& proMap.get("userType").equals(nextMap.get("userType"))
				&& !proMap.get("rtype").equals(nextMap.get("rtype"))){
				/*
				 * 相同字段修改前后金额不变的话，将其填0
				 */
				log.info("\r\nproMap:"+proMap+"\r\nnextMap:"+nextMap);
				if(((BigDecimal)proMap.get("balance")).compareTo(ZERO)!=0
						&&((BigDecimal)proMap.get("balance")).add((BigDecimal)nextMap.get("balance")).compareTo(ZERO) ==0){
					proMap.put("balance", ZERO);
					nextMap.put("balance", ZERO);
				}
				if(((BigDecimal)proMap.get("seller_amount")).compareTo(ZERO)!=0
						&&((BigDecimal)proMap.get("seller_amount")).add((BigDecimal)nextMap.get("seller_amount")).compareTo(ZERO)==0){
					proMap.put("seller_amount", ZERO);
					nextMap.put("seller_amount", ZERO);
				}
				if(((BigDecimal)proMap.get("commision")).compareTo(ZERO)!=0
						&&((BigDecimal)proMap.get("commision")).add((BigDecimal)nextMap.get("commision")).compareTo(ZERO)==0){
					proMap.put("commision", ZERO);
					nextMap.put("commision", ZERO);
				}
		}
	}
	/**
	 * 
	 * @param strAmount
	 * @param add  false 调单后   true 调单前
	 * @return
	 */
	private BigDecimal getAmount(String strAmount,boolean add){
		return UtilString.stringIsNotBlank(strAmount)?(add?new BigDecimal(strAmount):new BigDecimal(strAmount).negate()):ZERO;
	}

	/**
	 * 扣除分账金额
	 */
	@Transactional(rollbackFor = {FailureException.class,RuntimeException.class } ,isolation= Isolation.SERIALIZABLE, timeout=100)
	@Override
	public Map<String, String> deductionsBalance(Map<String, String> deductionMap) throws FailureException {
		
		String orderId = deductionMap.get("orderId");
		
		Map<String,String> resultMap=null;
		List<Map<String,Object>> updateList = new ArrayList<Map<String,Object>>();
		
		
		String mikeId = deductionMap.get("mikeId");
		String mikeType = deductionMap.get("mikeType");
		boolean hasMike = false;
		if(UtilString.stringIsNotBlank(mikeId) && UtilString.stringIsNotBlank(mikeType)){
			hasMike = true;
		}else if(UtilString.stringIsNotBlank(mikeId) && !UtilString.stringIsNotBlank(mikeType)){
			log.error("寻蜜客必须填写寻蜜客类型！");
			throw new RuntimeException("寻蜜客必须填写寻蜜客类型！");
		}
		
		//1.要扣除的用户分账金额
		String uId = deductionMap.get("memberId");
		if(UtilString.stringIsNotBlank(uId)){
			updateList.add(getUpdateMap(uId,1,ZERO,
					getAmount(deductionMap.get("memberAmount"),false),ZERO,orderId,21));
		}
		/******************************               消费者钱包调整MAP                        ******************************/	
		
		//1.要扣除的商家的营收金额
		String sellerId = deductionMap.get("sellerId");
		if(UtilString.stringIsNotBlank(sellerId)){
			updateList.add(getUpdateMap(sellerId,2,getAmount(deductionMap.get("sellerAmount"),false),ZERO,
					ZERO,orderId,21));
		}else{
			throw new RuntimeException("已分账订单，商家账号不能为空");
		}
		/******************************              商户及寻蜜客钱包调整MAP                         ******************************/	
		
		
		
		//用户区域合作商
		String bpartnerId = deductionMap.get("bpartnerId");
		//消费合作商
		String cpartnerId = deductionMap.get("cpartnerId");
		
		if(UtilString.stringIsNotBlank(bpartnerId)
				&& UtilString.stringIsNotBlank(cpartnerId)
				&& bpartnerId.equals(cpartnerId)){
			//同一个
			BigDecimal sellerAmount = BigDecimal.valueOf(0D); 
			//3.要扣除的用户区域合作商分账金额
			sellerAmount = sellerAmount.add(getAmount(deductionMap.get("bpartnerAmount"),false));
			
			//4.要扣除的消费区域合作商分账金额
			sellerAmount = sellerAmount.add(getAmount(deductionMap.get("cpartnerAmount"),false));

			updateList.add(getUpdateMap(bpartnerId,3,sellerAmount,ZERO,
					ZERO,orderId,21));

		}else{
		
			//要扣除的用户区域合作商分账金额
			if(UtilString.stringIsNotBlank(bpartnerId)){
				updateList.add(getUpdateMap(bpartnerId,3,getAmount(deductionMap.get("bpartnerAmount"),false),
						ZERO,ZERO,orderId,21));
			}

			//要扣除的消费区域合作商分账金额
			if(UtilString.stringIsNotBlank(cpartnerId)){
				updateList.add(getUpdateMap(cpartnerId,3,getAmount(deductionMap.get("cpartnerAmount"),false),
						ZERO,ZERO,orderId,21));
			}
	
		}
		/******************************               消费合作商及用户合作商调单前钱包调整MAP                  ******************************/	
		if(hasMike){
			boolean c = false;
			for(Map<String,Object> tmp:updateList){
				System.out.println("uId:"+tmp.get("uId")+",userType:"+tmp.get("userType"));
				if(tmp.get("uId").toString().equals(mikeId) && tmp.get("userType").toString().equals(mikeType)){
					c=true;
//					if(mikeType.equals("1")){
//						tmp.put("commision", ((BigDecimal)tmp.get("commision")).add(getAmount(deductionMap.get("mikeAmount"),false)));
//					}else if(mikeType.equals("2")){
//						tmp.put("commision", ((BigDecimal)tmp.get("commision")).add(getAmount(deductionMap.get("mikeAmount"),false)));
//					}else if(mikeType.equals("3")){
						tmp.put("commision", ((BigDecimal)tmp.get("commision")).add(getAmount(deductionMap.get("mikeAmount"),false)));
//					}
				}
			}
			if(!c){
				updateList.add(getUpdateMap(mikeId,Integer.parseInt(mikeType),ZERO,ZERO,
						getAmount(deductionMap.get("mikeAmount"),false),orderId,21));
			}
		}
		
		List<Map<String,Object>> newList = getUpdateList(updateList);
		log.info("newList.size:"+newList.size()+"\r\ndata:"+newList);
		
		//修改对应钱包
		for(int i=0,size=newList.size();i<size;i++){
			resultMap = walletService.updateWalletAmount2(newList.get(i));
			if(!resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS)){
				throw new RuntimeException("扣除分账金额"
						+(((int)newList.get(i).get("userType"))==1?"用户":(((int)newList.get(i).get("userType"))==2?"商户":"合作商"))
						+"ID:"+newList.get(i).get("uId")+resultMap.get("Msg"));
			}
		}
		
		return null;
	}

	@Override
	public ResponseData deductionsWallet(String orderId)
			throws FailureException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rtype", XmnConstants.RTYPE_0);
		map.put("remarks", orderId + "_%");
		List<WalletRecord> ledgerList = walletRecordMapper.getWalletRecordList(map);
		if(ledgerList==null || ledgerList.size()==0){
			return new ResponseData(1,"该订单尚未进行分账",null);
		}
		
		for(WalletRecord record:ledgerList){
			Map<String,Object> uMap = new HashMap<String,Object>();
			Map<String,String> resultMap = walletService.updateWalletAmount2(uMap);
			if(!resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS)){
				throw new RuntimeException("扣回失败");
			}
		}
		return new ResponseData(0,"订单扣回成功",null);
	}

	@Override
	public ResponseData deductionsWallet2(Map<String,String> param)
			throws FailureException {
		
		String orderId = param.get("orderId");//订单号
		String orderType = param.get("orderType");//订单类型
		
		Map<String,Object> map = new HashMap<>();
		map.put("remarks", orderId);
		
		int xmnRtype = 0;//寻蜜鸟记录表分账返回rtype类型 
		int xmerRtype = 0;//寻蜜客记录表分账返回rtype类型
		int rtype = 0; 
		if(orderType.equals("1")){
			map.put("rtype", XmnConstants.RTYPE_0);
			rtype=0;
			xmnRtype = XmnConstants.RTYPE_21;//寻蜜鸟分账返回记录类型
			xmerRtype = XmnConstants.XMER_RTYPE_3;//寻蜜客分账返回记录类型
			//消费订单
		}else if(orderType.equals("2")){
			//线下积分订单
			map.put("rtype",XmnConstants.RTYPE_22);
			xmnRtype = XmnConstants.RTYPE_33;
//			xmerRtype = XmnConstants.XMER_RTYPE_3; //线下积分订单不进行寻蜜客分账
		}else if(orderType.equals("3")){
			//线上积分订单
		}else{
			return new ResponseData(2,"订单类型错误",null);
		}
		//2.查询该订单是否分账
		List<WalletRecord> walletRecordList = walletRecordMapper.getWalletRecordList(map);
		if(walletRecordList==null || walletRecordList.size()==0){
			log.error("分账退回失败，该订单尚未进行分账"+param);
			return new ResponseData(1,"该订单尚未进行分账",null);
		}
		
		//寻蜜鸟钱包退款
		for (WalletRecord record : walletRecordList) {
			
			Map<String,Object> recordMap = new HashMap<>();
			recordMap.put("accountid",record.getAccountid());
			recordMap.put("balance",record.getBalance().negate());
			recordMap.put("commision",record.getCommision().negate());
			recordMap.put("seller_amount",record.getIncome().negate());
			recordMap.put("zbalance",record.getZbalance().negate());
			recordMap.put("amount",record.getAmount().negate());
			recordMap.put("integral",record.getIntegral().negate());
			recordMap.put("rtype",xmnRtype);
			recordMap.put("remarks",record.getRemarks());
			recordMap.put("description",record.getDescription());
			
			Map<String, String> resultMap = walletService.updateWalletAmount2(recordMap);
			if(!resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS)){
				throw new RuntimeException("扣回失败");
			}
		}
		
		//线下积分订单没有寻蜜客分账
		if(!"2".equals(orderType)){
			//寻蜜客钱包退款
			Map<String,Object> xmerRecordMap = new HashMap<>();
			xmerRecordMap.put("remark",orderId);
			xmerRecordMap.put("rtype",rtype+"");
			List<Map<String, Object>> xmerWalletRecords = xmerWallerMapper.getRecordsByRemarks(xmerRecordMap);
			if(xmerWalletRecords != null && xmerWalletRecords.size()>0){
				for (Map<String, Object> map2 : xmerWalletRecords) {
					Map<String,Object> xmerMap = new HashMap<>();
					xmerMap.put("id",map2.get("xid"));
					xmerMap.put("remark",map2.get("remark"));
					xmerMap.put("profit",map2.get("profit"));
					xmerMap.put("rtype",xmerRtype);
					xmerMap.put("description",map2.get("description"));
					
					Map<String,String> resultMap = xmerWalletServiceImpl.ledgerRefund(xmerMap);
					
					if(!resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS)){
						throw new RuntimeException("扣回失败");
					}
				}
			}
		}
		
		return new ResponseData(0,"订单扣回成功",null);
	}
	
}
