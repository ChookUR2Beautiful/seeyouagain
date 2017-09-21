/**
 * 
 */
package com.xmniao.service.live;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.domain.live.LiveFansRank;
import com.xmniao.domain.live.LiveFansRankDetail;
import com.xmniao.domain.live.UrsEarningsRank;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.urs.dao.LiveFansRankDao;
import com.xmniao.urs.dao.UrsEarningsRankDao;
import com.xmniao.urs.dao.UrsEarningsRelationDao;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：VerMallService
 * 
 * 类描述： 商城V客新玩法Service
 * 
 * 创建人： Administrator
 * 
 * 创建时间：2017年8月10日 下午1:59:02 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class VerMallService {

	private Logger log = Logger.getLogger(getClass());
	
    @Autowired
    private VerRechargeLiveOrder verRechargeLiveOrder;
    
    @Autowired
    private UrsEarningsRelationDao ursRelationDao;
    
    @Autowired
    private LiveFansRankDao liveFansRankDao;
    
	@Autowired
	private UrsEarningsRankDao ursRankDao;
	
    @Autowired
    private DefaultMQProducer producerConnection;
	
    @Autowired
    private String ledgerPayTopic;
    
    @Autowired
    private String mallPackageTags;
	/**
	 * 
	 * 方法描述：商城(套餐)订单与V客之间神秘玄奥而又理不清的关系 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月11日上午9:52:14 <br/>
	 * 	
	 * 流程:	
	 * 0.给这人创建V客信息：等级、关系链、补SAAS(创建寻蜜客)
	 * 1.这人是不是V客，是V客按V客关系链分账
	 * 2.这人是不是黄金庄园用户，是庄园按庄园关系链，查看其上级是不是V客，是V客给其分账，庄园关系链不进V客关系链
	 * 3.这人普通用户，不分账
	 * 4.分账收入区别对之前V客推荐收益
	 * @param uid
	 * @param amount
	 * @param rankId
	 * @param payDate
	 */
	public void mallVerConnect(int uid,double amount,long rankId,String orderno,Date payDate){
		
		try{
			String uidRelationChain = getUidRelationChain(uid);
			initVerLevelWelfare(uid,rankId,payDate);
			String[] uids = uidRelationChain.split(",");
			int length = uids.length;
			Integer directSupervisorUid= uids.length>1?Integer.parseInt(uids[length-2]):null;//直接上级
			Integer indirectSupervisorUid= uids.length>2?Integer.parseInt(uids[length-3]):null;//间接上级
			initVerLedger(uid, directSupervisorUid, indirectSupervisorUid, amount,orderno);
		}catch(Exception e){
			log.error("商城订单V客资格奖励分账异常",e);
		}
		
	}
	
	/**
	 * 
	 * 方法描述：成为V客，并相应奖励 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月10日下午2:01:48 <br/>
	 */
	public void initVerLevelWelfare(Integer uid,Long rankId,Date payDate){
		
		/* 获取该用户的V客信息*/
		Integer uidRankId = ursRankDao.selectRankidByUid(uid, 4);
		
		/* 获取该订单所达到的等级信息 */
		LiveFansRank orderRank = getVerRank(rankId,2,payDate,4);
		
		/*该用户当前的等级信息*/
		LiveFansRank thisRank = null;
		
		UrsEarningsRank rank = new UrsEarningsRank((long)uid,rankId,4);
		if(uidRankId==null){
			log.info("该会员之前不是V客身份，");
			ursRankDao.insertSelective(rank);
			initUidRelation(uid);
		}else{
			if((long)uidRankId != rankId){
				thisRank =getVerRank((long)uidRankId,2,payDate,4);
			}else{
				thisRank=orderRank;
			}
			if(orderRank.getRankNo()>=thisRank.getRankNo()){
				log.info("新的订单能达到的等级超过自身已有的等级");
				ursRankDao.updateSelective(rank);
			}
		}
		
		Integer saasNumer = orderRank.getSaasNumber();
		/*补相应的SAAS名额*/
		int replenishNumber = verRechargeLiveOrder.replenishSaasNumber(uid, 3, saasNumer);
		log.info("本次V客充值订单可获取得SAAS数量:"+replenishNumber+"个");
		
	}
	
	/**
	 * 
	 * 方法描述：给上级分账 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月10日下午8:21:50 <br/>
	 * @param uid
	 * @param directSupervisorUid	直属上级 uid
	 * @param indirectSupervisorUid  间接上级uid
	 * @param amount
	 */
	public void initVerLedger(Integer uid,Integer directSupervisorUid,Integer indirectSupervisorUid,Double amount,String orderno) {
		/*
		 * 奖励规则：
		 * 1.其上级、上上级只有具备V客资格，才给他们进行分账
		 *     
		 * 2.分账金额发放到独立收益钱包，手续费另算
		 */
		double directSupervisorRatio = getSupervisorRatio(directSupervisorUid,true);
		double directSupervisorAmount = PreciseComputeUtil.mul(amount, directSupervisorRatio,2);

		double indirectSupervisorRatio = getSupervisorRatio(indirectSupervisorUid, false);
		double indirectSupervisorAmount = PreciseComputeUtil.mul(amount, indirectSupervisorRatio,2);
	
		sendLedgerMQ(directSupervisorUid,directSupervisorAmount,indirectSupervisorUid,indirectSupervisorAmount,uid,orderno);
	}
	
	/**
	 * 
	 * 方法描述：根据上级UID及上级在当前用户中的关系链位置，获取他的分账比例 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月11日上午10:21:30 <br/>
	 * @param supervisorUid 上级Uid
	 * @param isDirectSupervisor 是否是直属上级
	 * @return
	 */
	public double getSupervisorRatio(Integer supervisorUid,boolean isDirectSupervisor){
		double ratio = 0.0;
		if(supervisorUid==null){
			return ratio;
		}
		UrsEarningsRank ursRank =ursRankDao.selectByUid(supervisorUid, 4);
		if(ursRank==null){
			return ratio;
		}
		LiveFansRank confRank = getVerRank(ursRank.getRankId(),2,ursRank.getModifyTime(),4);
		if(confRank.getLiveFansRankDetail()!=null){
			if(isDirectSupervisor){
				ratio =  PreciseComputeUtil.mul(confRank.getLiveFansRankDetail().getReferrerRatio(),0.01,2);
			}else{
				ratio =  PreciseComputeUtil.mul(confRank.getLiveFansRankDetail().getParentReferrerRatio(),0.01,2);
			}
		}
		return ratio;
	}
	
	/**
	 * 
	 * 方法描述：获取用户分账的关系链 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月10日下午9:02:06 <br/>
	 * @param uid
	 * @return
	 */
	public String getUidRelationChain(Integer uid){
		UrsEarningsRelation selRelation =new UrsEarningsRelation(uid, 4);	//查V客关系链
		UrsEarningsRelation relation = ursRelationDao.getUrsEarningsRelation(selRelation);
		if(relation!=null){
			return relation.getUidRelationChain();
		}
		selRelation.setObjectOriented(9);		//查庄园关系链
		relation = ursRelationDao.getUrsEarningsRelation(selRelation);
		if(relation!=null && relation.getParentUid()!=null &&relation.getParentUid()!=0){
			long parentUid = relation.getParentUid();	//再查询其上级的上级
			long grandfatherUid = 0;
			selRelation.setUid((int)parentUid);
			relation = ursRelationDao.getUrsEarningsRelation(selRelation);
			if(relation!=null && relation.getParentUid()!=null &&relation.getParentUid()!=0){
				grandfatherUid=relation.getParentUid();
			}
			StringBuffer sb = new StringBuffer();
			
			if(grandfatherUid!=0){
				sb.append(String.format("%011d,", grandfatherUid));
			}
			if(parentUid!=0){
				sb.append(String.format("%011d,", parentUid));
			}
			sb.append(String.format("%011d", uid));
			return sb.toString();
		}
		return String.format("%011d", uid);	//默认关系链家
	}
	
	/**
	 * 
	 * 方法描述：获取订单所处的会员分账等级信息
	 * 创建人： ChenBo
	 * 创建时间：2016年12月30日
	 * @param payAmount
	 * @return LiveFansRank
	 * @throws Exception 
	 */
	public LiveFansRank getVerRank(Long rankId,int rankType,Date payDate,int objectOriented){
		LiveFansRank rank = new LiveFansRank(rankId);
		LiveFansRank liveFansRank = liveFansRankDao.getLiveFansRankBase(rank);
		if(liveFansRank != null){
			LiveFansRankDetail detail = new LiveFansRankDetail();
			detail.setRankId(liveFansRank.getId());
			detail.setEffectiveDate(payDate);
			detail.setObjectOriented(objectOriented);
			LiveFansRankDetail liveFansRankDetail = liveFansRankDao.getLiveFansRankDetail(detail);
			liveFansRank.setLiveFansRankDetail(liveFansRankDetail);
		}
		return liveFansRank;
	}
	
	public void initUidRelation(Integer uid) {
		UrsEarningsRelation relation = new UrsEarningsRelation(uid, 4); 
		if(null == ursRelationDao.getUrsEarningsRelation(relation)){
			relation.setCreateTime(new Date());
			relation.setUidRelationChain(String.format("%011d", uid));
			relation.setUidRelationChainLevel(1);
			relation.setUidRelationChainNname("");
			ursRelationDao.insertUrsEarningsRelation(relation);
		}
	}
	
	/**
	 * 
	 * 方法描述：发送分账MQ <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月11日下午3:41:26 <br/>
	 * @param directSupervisorUid
	 * @param directSupervisorAmount
	 * @param indirectSupervisorUid
	 * @param indirectSupervisorAmount
	 * @param uid
	 * @param orderId
	 */
	public void sendLedgerMQ(Integer directSupervisorUid,double directSupervisorAmount,
			Integer indirectSupervisorUid,double indirectSupervisorAmount,
			Integer uid,String orderId){
		log.info(directSupervisorUid+","+directSupervisorAmount+","+
			indirectSupervisorUid+","+indirectSupervisorAmount+","+
			uid+","+orderId);
		if(directSupervisorUid==null && indirectSupervisorUid==null){
			return ;
		}
		
		if(directSupervisorAmount==0.0 && indirectSupervisorAmount==0.0){
			return ;
		}
		Map<String,String> mqMap = new HashMap<>();
		mqMap.put("orderId", orderId);
		if(directSupervisorUid!=null){
			mqMap.put("twoLevelXmerId", directSupervisorUid+"");
			mqMap.put("twoLevelXmerMoney", directSupervisorAmount+"");
		}
		if(indirectSupervisorUid!=null){
			mqMap.put("oneLevelXmerId", indirectSupervisorUid+"");
			mqMap.put("oneLevelXmerMoney", indirectSupervisorAmount+"");
		}
		mqMap.put("remark", uid+"购买商城订单分账");
		log.info("topic:"+ledgerPayTopic+",tag:"+mallPackageTags+",V客购买订单分账:"+JSONObject.toJSONString(mqMap));
		try {
			byte[] body = JSONObject.toJSONString(mqMap).getBytes("utf-8");
			Message msg = new Message(ledgerPayTopic, mallPackageTags, System.currentTimeMillis()+"", body);
			producerConnection.send(msg);
		} catch (Exception e) {
			log.error("发送商城V客分账MQ消息失败！",e);
		}
	}
}
