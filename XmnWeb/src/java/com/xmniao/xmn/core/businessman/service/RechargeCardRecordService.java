/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.RechargeCardRecordDao;
import com.xmniao.xmn.core.businessman.entity.RechargeCardRecord;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RechargeCardRecordService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月22日 上午11:55:33 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class RechargeCardRecordService extends BaseService<RechargeCardRecord>{

	@Autowired
	private RechargeCardRecordDao rCardRecordDao;
	
	@Override
	protected BaseDao<RechargeCardRecord> getBaseDao() {
		return rCardRecordDao;
	}
	
	/**
	 * 获取充值记录列表
	 */
	@Override
	public List<RechargeCardRecord> getList(RechargeCardRecord  rCardRecord){
		log.info("获取充值记录列表getList");
		
		if(StringUtils.isNotBlank(rCardRecord.getUserName())||StringUtils.isNotBlank(rCardRecord.getPhone())){//根据用户昵称或手机号查
			Map<String,String> map = new HashMap<>();
			map.put("userName", rCardRecord.getUserName());
			map.put("phone", rCardRecord.getPhone());
			Map<String, Object> usr = rCardRecordDao.getUsr(map);
			if(usr != null){
				rCardRecord.setUid((Integer)usr.get("uid"));
			}else{
				return new ArrayList<RechargeCardRecord>();
			}
		}
		
		List<RechargeCardRecord> recordList = rCardRecordDao.getList(rCardRecord);
		
			for (RechargeCardRecord rRecord : recordList) {
				
				try {
					//获取用户信息
					Map<String, String> liver = rCardRecordDao.getLiver(rRecord.getUid());
					
					if(liver != null){
						rRecord.setPhone(liver.get("phone"));//手机号
						rRecord.setUserLevel(liver.get("rankName"));//粉丝级别
						rRecord.setUserName(liver.get("nname"));//会员名称
					}
					
					String rChain = rRecord.getRelationChain();
					if(rChain != null && rChain != ""){
						String[] split = rChain.split(",");
						
						if(split.length == 2){
							Map<String, String> upliver = rCardRecordDao.getLiver(Integer.valueOf(split[0]));//上级
							rRecord.setUpLevel(upliver.get("nname"));//上级名称
						}
						
						if(split.length==3){
							
							Map<String, String> upliver = rCardRecordDao.getLiver(Integer.valueOf(split[0]));//上级
							rRecord.setUpLevel(upliver.get("nname"));//上级名称
							
							Map<String, String> topliver = rCardRecordDao.getLiver(Integer.valueOf(split[1]));//上上级
							rRecord.setUpLevel(topliver.get("nname"));//上上级名称
						}
					}
//					rRecord.setHquota(rRecord.getQquota().add(rRecord.getQuota()));//充值后额度
					
					//获取分账信息
/*					Map<String, Object> upledger = rCardRecordDao.getLedgerRecord(rRecord.getOrderNo(),2);//上级分账金额
					if(upledger != null){
						rRecord.setUpAmount((BigDecimal)upledger.get("coin"));
					}else {
						rRecord.setUpAmount(BigDecimal.ZERO);
					}
					
					Map<String, Object> topledger = rCardRecordDao.getLedgerRecord(rRecord.getOrderNo(),3);//上上级分账金额
					if(topledger != null){
						rRecord.setUpAmount((BigDecimal)topledger.get("coin"));
					}else {
						rRecord.setUpAmount(BigDecimal.ZERO);
					}*/
				} catch (NumberFormatException e) {
					log.error("获取充值记录异常"+rRecord,e);
					continue;
				}
			}
		return recordList;
	}
	
	@Override
	public Long count(RechargeCardRecord rCardRecord){
		
		if(StringUtils.isNotBlank(rCardRecord.getUserName())||StringUtils.isNotBlank(rCardRecord.getPhone())){//根据用户昵称或手机号查
			Map<String,String> map = new HashMap<>();
			map.put("userName", rCardRecord.getUserName());
			map.put("phone", rCardRecord.getPhone());
			Map<String, Object> usr = rCardRecordDao.getUsr(map);
			if(usr != null){
				rCardRecord.setUid((Integer)usr.get("uid"));
			}else{
				return 0L;
			}
		}
		return rCardRecordDao.count(rCardRecord);
	}
}
