/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.ValueCardDao;
import com.xmniao.xmn.core.businessman.dao.VipValueCardDao;
import com.xmniao.xmn.core.businessman.entity.ValueCard;
import com.xmniao.xmn.core.businessman.entity.VipValueCard;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.walletCardService.ValueCardService;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：VipValueCardService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月21日 上午11:49:13 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class VipValueCardService extends BaseService<VipValueCard>{

	@Autowired
	private VipValueCardDao vvCardDao;
	
	@Autowired
	private ValueCardDao vCardDao;
	
	@Resource(name = "valueCardServiceClient")
	private ThriftClientProxy clientFactory;
	
	@Override
	protected BaseDao<VipValueCard> getBaseDao() {
		return vvCardDao;
	}
	
	/**
	 * 获取会员储值卡列表
	 */
	@Override
	public List<VipValueCard> getList(VipValueCard vvCard){
		log.info("获取会员储值卡列表:"+vvCard);
		List<VipValueCard> resultList = new ArrayList<>();
		Map<String,String> paraMap = new HashMap<>();
			
		paraMap.put("page",vvCard.getPage().toString());//页码
		paraMap.put("limit",vvCard.getLimit().toString());//页大小
		
		if(vvCard.getSellername() != null && vvCard.getSellername() !=""){
			paraMap.put("sellername",vvCard.getSellername());
		}
		
		if(vvCard.getUid() != null){
			paraMap.put("uid",vvCard.getUid().toString());
		}
		
		if(vvCard.getType() != null){
			paraMap.put("sellertype",vvCard.getType().toString());
		}
		ValueCardService.Client client = (ValueCardService.Client)clientFactory.getClient();
		
			List<Map<String, String>> valueCardList;
			try {
				valueCardList = client.getValueCardList(paraMap);
				for (Map<String, String> map : valueCardList) {
					
					try {
						VipValueCard vipValueCard = new VipValueCard();
						vipValueCard.setUid(Integer.valueOf(map.get("uid")));//uid
						vipValueCard.setTotalLimit(new BigDecimal(map.get("cumulativeQuota")));//充值额度
						vipValueCard.setConsumerLimit(new BigDecimal(map.get("cumulativeQuota")).subtract(new BigDecimal(map.get("quota"))));//已消费额度
						vipValueCard.setPresentLimit(new BigDecimal(map.get("quota")));//当前额度
						vipValueCard.setId(Integer.valueOf(map.get("id")));//卡序号
						
						Integer type = Integer.valueOf((map.get("sellertype")==null?"0":map.get("sellertype")));//会员卡类型			
						vipValueCard.setType(type);//储值卡类型
						
						if(type == 1){
							vipValueCard.setApplySeller(1);
						}else if(type == 2){
							ValueCard valueCard = new ValueCard();
							valueCard.setSellerid(Integer.valueOf(map.get("sellerid")));
							vipValueCard.setApplySeller(vCardDao.countChildSeller(valueCard).intValue());
						}else if(type == 3){
							vipValueCard.setApplySeller(vCardDao.getAliance(Integer.valueOf(map.get("sellerid"))).size());
						}
						
						Map<String, Object> ursMsg = vvCardDao.getUrsMsg(map.get("uid"));
						if(ursMsg != null){
							vipValueCard.setUserName(ursMsg.get("nname")==null?"":ursMsg.get("nname").toString());
							vipValueCard.setAccount(ursMsg.get("phone")==null?"":ursMsg.get("phone").toString());
						}
						
						ValueCard vc = new ValueCard();
						vc.setSellerid(Integer.valueOf(map.get("sellerid")));
						ValueCard vc2 = vCardDao.getValueCard(vc);
						if(vc2!=null){
							vipValueCard.setSellername(vc2.getSellerName());
						}
						
						vipValueCard.setSellerid(map.get("sellerid"));
						
						resultList.add(vipValueCard);
					} catch (Exception e) {
						log.error("拼装数据异常",e);
						continue;
					}
				}
			}catch (TException e) {
				log.error("调用支付服务获取储值卡列表异常：",e);
			}
			log.info("查询vip储值卡成功");
			return resultList;
	}
	
	public Long countValueCard(VipValueCard vvCard){
		Map<String,String> paraMap = new HashMap<>();
		
		if(vvCard.getSellername() != null && vvCard.getSellername() !=""){
			paraMap.put("sellername",vvCard.getSellername());
		}
		
		if(vvCard.getUid() != null){
			paraMap.put("uid",vvCard.getUid().toString());
		}
		
		if(vvCard.getType() != null){
			paraMap.put("sellertype",vvCard.getType().toString());
		}
		ValueCardService.Client client = (ValueCardService.Client)clientFactory.getClient();
		try {
			Map<String, String> count = client.countCardNums(paraMap);
			
			return Long.valueOf(count.get("total"));
		} catch (Exception e) {
			log.error("查询异常",e);
			return 0L;
		}
		
	}
}
