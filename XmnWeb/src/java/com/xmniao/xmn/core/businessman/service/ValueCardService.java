/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.print.resources.serviceui;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.ValueCardDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.ValueCard;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.exception.FailureException;
import com.xmniao.xmn.core.thrift.service.ResponseData;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ValueCardService
 * 
 * 类描述： 储值卡服务接口
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月17日 上午10:52:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class ValueCardService extends BaseService<ValueCard>{
	
	@Autowired
	private ValueCardDao vCardDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Resource(name = "valueCardServiceClient")
	private ThriftClientProxy clientFactory;
	
	@Override
	protected BaseDao<ValueCard> getBaseDao() {
		return vCardDao;
	}
	
	/**
	 * 新增商家储值卡
	 */
	@Override
	public void add(ValueCard vCard) {
		
		Integer sType = vCard.getSellerType();
		
		ValueCard valueCard = vCardDao.getValueCard(vCard);
		
		if(valueCard != null){
			log.info("商家："+vCard.getSellerid()+"已开通储值卡");
			return;
		}
		
		if(1==sType||2==sType){//1.普通商家 2.连锁总店 3 区域代理'
			
			TSeller tSeller = new TSeller();
			tSeller.setSellerid(vCard.getSellerid());
			
			TSeller seller = sellerDao.getSellerByWhere(tSeller);
			
			vCard.setSellerName(seller.getSellername());
			
		}else if(3==sType){//3 区域代理
			
			Map<String, Object> alliance = vCardDao.getAllianceById(vCard.getSellerid());
			vCard.setSellerName(alliance.get("allianceName")+"");
			
		}else {
			log.error("商家类型错误");
			throw new RuntimeException("商家类型错误");
		}
		
		vCard.setUpdateTime(new Date());
		vCard.setRelationStore(vCard.getSellerid().toString());
		vCardDao.add(vCard);
	}
	
	/**
	 * 获取储值卡列表
	 */
	@Override
	public List<ValueCard> getList(ValueCard vCard) {
		List<ValueCard> list = vCardDao.getList(vCard);
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i<list.size();i++) {
			sb.append(list.get(i).getSellerid().toString());
			if(i<list.size()-1){
				sb.append(",");
			}
		}
		
		Map<String,String> map = new HashMap<>();
		map.put("sellerid",sb.toString());
		
		for (ValueCard valueCard : list) {
			try {
				StringBuilder sBuilder = new StringBuilder();
				String[] split = valueCard.getComboId().split(",");
				for (int i =0;i<split.length;i++) {
					Map<String, Object> rechargeValue = vCardDao.getRechargeValue(split[i]);
					sBuilder.append(rechargeValue.get("rechAmount"));
					if(i<split.length-1){
						sBuilder.append(",");
					}
				}
				valueCard.setCombo(sBuilder.toString());//可充值金额
			} catch (Exception e) {
				log.error(valueCard.getSellerid()+"设置可充值金额异常");
			}
		}
		
		try {
			com.xmniao.xmn.core.thrift.service.walletCardService.ValueCardService.Client client = (com.xmniao.xmn.core.thrift.service.walletCardService.ValueCardService.Client)clientFactory.getClient();
			
			Map<String, String> resultMap = client.getValueCardBalance(map);
			
			for (ValueCard valueCard : list) {
				putParam(valueCard, resultMap);
			}
		} catch (Exception e) {
			log.info("调用支付服务查询异常",e);
		}
		
		return list;
	}
	
	
	/**
	 * 释放商家储值卡
	 * @throws TException 
	 * @throws FailureException 
	 */
	
	@Transactional(rollbackFor={Exception.class,FailureException.class})
	public boolean updateStatus(ValueCard vCard) throws FailureException, TException {
		
		//更新商家储值卡状态
		Integer update = this.update(vCard);
		
		if(update != 1){
			log.error("更新商家储值卡状态失败");
			throw new RuntimeException("更新商家储值卡状态失败,更新异常");
		}
		
		/**
		 * 调用支付服务，注销所有会员卡，并释放额度
		 */
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("sellerid",vCard.getSellerid().toString());
		paraMap.put("sellertype",vCard.getSellerType().toString());
		
		com.xmniao.xmn.core.thrift.service.walletCardService.ValueCardService.Client client = (com.xmniao.xmn.core.thrift.service.walletCardService.ValueCardService.Client)clientFactory.getClient();
		
		ResponseData response;
		try {
			response = client.updateCardStatus(paraMap);
			
			if(response.getState() != 0){
				log.error("调用支付服务注销会员储值卡失败");
				throw new RuntimeException("调用支付服务注销会员储值卡失败");
			}
		} catch (Exception e) {
			log.error("调用支付服务注销用户储值卡异常",e);
			throw new RuntimeException("调用支付服务注销会员储值卡失败");
		}
	
		log.info("释放商家储值卡成功");
		
		return true;
	}

	private void putParam(ValueCard valueCard,Map<String,String> paraMap){
		try {
			String sellerid = valueCard.getSellerid().toString();
			String balance = paraMap.get(sellerid);
			String[] split = balance.split(",");
			valueCard.setTotalRecharge(new BigDecimal(split[0]));
			valueCard.setTotalSurplus(new BigDecimal(split[1]));
			valueCard.setRechargeNum(Integer.valueOf(split[2]));
			
			if(valueCard.getSellerType() == 1){
				valueCard.setChildSeller(1);
			}
			
		} catch (NumberFormatException e) {
			log.error("支付服务传回参数有误");
		}
	}
	
	public List<ValueCard> getSellerList(ValueCard vCard){
		return vCardDao.getSellerList(vCard);
	}
	
	/**
	 * 
	 * 方法描述：获取区域代理列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月18日上午11:08:06 <br/>
	 * @param vCard
	 * @return
	 */
	public List<ValueCard> getAreaAgency(ValueCard vCard){
		return vCardDao.getAreaAgency(vCard);
	}
	
	/**
	 * 
	 * 方法描述：获取所有子商家
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月20日下午3:17:04 <br/>
	 * @param vCard
	 * @return
	 */
	public List<TSeller> getChildSellerList(ValueCard vCard){
		
		ValueCard sellerCard = vCardDao.getValueCard(vCard);
		
		List<TSeller> childSeller;
		if(sellerCard != null){
			
			String subSellerid = sellerCard.getRelationStore();
			
			if(vCard.getSellerType() == 3){//区域代理
				childSeller = new ArrayList<TSeller>();
				List<String> aliance = vCardDao.getAliance(vCard.getSellerid());
				for (String sellerid : aliance) {
						TSeller childSeller2 = vCardDao.getSellers(sellerid);
						if(childSeller2 != null ){
							if(StringUtils.isNotBlank(subSellerid)&&subSellerid.contains(childSeller2.getSellerid()+"")){
								childSeller2.setUseValueCard(1);
							}
							childSeller.add(childSeller2);
						}else {
							log.error("查询不到商家："+sellerid+"信息");
						}
				}
			}else if(vCard.getSellerType() == 2){//连锁店
				childSeller = vCardDao.getChildSeller(vCard.getSellerid());
				
				if(StringUtils.isNotBlank(subSellerid)){
					for (TSeller tSeller : childSeller) {
						if(subSellerid.contains(tSeller.getSellerid()+"")){
							tSeller.setUseValueCard(1);
						}
					}
				}
			}else{//单店
				TSeller tSeller = vCardDao.getSellers(vCard.getSellerid().toString());
				childSeller = new ArrayList<>();
				childSeller.add(tSeller);
			}
		}else{
			log.info("数据异常,该商家尚未开通储值卡sellerid:"+vCard.getSellerid());
			childSeller = new ArrayList<>();
		}
		return childSeller;
	}
	
	/**
	 * 
	 * 方法描述：统计子商家数量
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月20日下午5:12:10 <br/>
	 * @param vCard
	 * @return
	 */
	public Long countChildSellerNum(ValueCard vCard){
		Long num = 0l;
		if(vCard.getSellerType()==3){
			List<String> aliance = vCardDao.getAliance(vCard.getSellerid());
			num = Long.valueOf(aliance.size()+"");
		}else{
			num = vCardDao.countChildSeller(vCard);
		}
		return num;
	}
	
	/**
	 * 
	 * 方法描述：解除或限制商户
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月21日上午9:39:49 <br/>
	 * @param seller
	 */
	public void limitSeller(TSeller seller){
		
		Integer useStatus = seller.getUseValueCard();
		
		ValueCard valueCard = new ValueCard();
		valueCard.setSellerid(seller.getFatherid());
		
		ValueCard vCard = vCardDao.getValueCard(valueCard);
		
		ArrayList<Object> list = new ArrayList<>();
		
		if(vCard.getRelationStore() != null && vCard.getRelationStore() !=""){
			String subStore = vCard.getRelationStore();
			
			String[] subArray = subStore.split(",");
			
			for (String str : subArray) {
				list.add(str);
			}
		}
		
		list.remove("");
		
		if(useStatus == 0){
			list.remove(seller.getSellerid().toString().trim());
		}else if(useStatus == 1){
			list.add(seller.getSellerid().toString());
		}else {
			log.error("商户状态异常");
			throw new RuntimeException();
		}
		
		valueCard.setRelationStore(list.toString().replace("[","").replace("]","").replaceAll(" ",""));
		
		Integer limitSeller = vCardDao.limitSeller(valueCard);
		if(limitSeller != 1){
			log.error("更新商户使用状态异常");
			throw new RuntimeException();
		}
	}
	
	/**
	 * 
	 * 方法描述：获取商家充值套餐列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月3日上午10:06:46 <br/>
	 * @return
	 */
	public List<Map<String,Object>> getRechargeValue(){
		return vCardDao.getRechargeValueList();
	}
}

