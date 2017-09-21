/**
 * 
 */
package com.xmniao.service.live;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.ResponseState;
import com.xmniao.dao.live.TVerExcitationDetailDao;
import com.xmniao.dao.live.TVerExcitationProjectDao;
import com.xmniao.dao.live.TVerExcitationReceiveDao;
import com.xmniao.domain.live.ResponseData;
import com.xmniao.domain.live.TVerExcitationDetail;
import com.xmniao.domain.live.TVerExcitationProject;
import com.xmniao.domain.live.TVerExcitationReceive;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：RechargeDividendsService
 * 
 * 类描述： V客充值奖励方案Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-26 下午3:05:16 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class RechargeDividendsService {
	
	// 初始化日志类
	private final Logger log = Logger.getLogger(RechargeDividendsService.class);
	
	private static final int STATUS_UNRECEIVE=0;//待领取
	
	/**
	 * 注入V客充值奖励发放记录DAO
	 */
	@Autowired
	private TVerExcitationReceiveDao verReceiveDao;
	
	/**
	 * 注入V客充值奖励方案DAO
	 */
	@Autowired
	private TVerExcitationProjectDao verProjectDao;
	
	/**
	 * 注入V客充值奖励方案详情
	 */
	@Autowired
	private TVerExcitationDetailDao excitationDetailDao;
	
	/**
	 * 
	 * 方法描述：获取发放记录列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-26下午3:47:46 <br/>
	 * @param record
	 * @return
	 */
	public List<TVerExcitationReceive> getList(TVerExcitationReceive record){
		return verReceiveDao.getList(record);
	}

	/**
	 * 
	 * 方法描述：添加奖励发放记录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-26下午3:48:07 <br/>
	 * @param record
	 * @return
	 */
    public int add(TVerExcitationReceive record){
    	return verReceiveDao.add(record);
    }

    /**
     * 
     * 方法描述：更新奖励发放记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-5-26下午3:49:02 <br/>
     * @param record
     * @return
     */
    public int update(TVerExcitationReceive record){
    	return verReceiveDao.update(record);
    }
	
	/**
	 * 
	 * 方法描述：保存V客充值奖励B方案发放记录信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-26下午4:31:01 <br/>
	 * @param param 
	 * @return
	 * @throws Exception 
	 */
	public ResponseData saveReceiveB(Map<String,String> param) throws Exception{
		this.log.info("保存V客充值奖励B方案发放记录信息:param="+param.toString());
		
		ResponseData response = new ResponseData();
		String orderNo = param.get("orderNo");//订单号
		String uid = param.get("uid");//会员UID
		String projectName = param.get("projectName");//奖励方案名称
		String fansRankId	= param.get("fansRankId");//V客等级ID
		
		if(StringUtils.isBlank(orderNo)){
			throw new Exception("订单号不能为空!");
		}
		
		if(StringUtils.isBlank(uid)){
			throw new Exception("会员UID不能为空!");
		}
		
		if(StringUtils.isBlank(projectName)){
			throw new Exception("奖励方案名称不能为空!");
		}
		
		if(StringUtils.isBlank(fansRankId)){
			throw new Exception("V客等级ID不能为空!");
		}
		
		
		TVerExcitationProject recordReq = new TVerExcitationProject();
		recordReq.setProjectName(projectName);
		recordReq.setRankId(new Integer(fansRankId));
		TVerExcitationProject excitationProject = verProjectDao.getObject(recordReq);
		
		if(excitationProject==null){
			throw new Exception("获取奖励方案信息失败:fansRankId="+fansRankId);
		}
		
		int count = verReceiveDao.countExcitationRecord(orderNo);
		if(count>0){
			log.info("V客充值订单B奖励方案一次性发放，现已检测到该充值订单有过奖励方案，不予发放");
			response.setStatus(ResponseState.SUCCESS);
			response.setMessage("该充值订单"+orderNo+"之前已发放过B奖励方案");
			return response;
		}
		
		Integer pid = excitationProject.getId();
		
		TVerExcitationDetail excitationDetailReq= new TVerExcitationDetail();
		excitationDetailReq.setPid(pid);
		List<TVerExcitationDetail> excitaionDetaillist = excitationDetailDao.getList(excitationDetailReq);
		
		List<TVerExcitationReceive> excitationReceiveList = new ArrayList<TVerExcitationReceive>();
		for(TVerExcitationDetail excitationDetail : excitaionDetaillist){
			TVerExcitationReceive excitationReceive = new TVerExcitationReceive();
			Integer cid = excitationDetail.getCid();
			Integer num = excitationDetail.getNum();
			Byte type = excitationDetail.getType();
			Double worth = excitationDetail.getWorth();
			
			excitationReceive.setOrderNo(orderNo);
			excitationReceive.setUid(new Integer(uid));
			excitationReceive.setProjectName(projectName);
			excitationReceive.setCid(cid);
			excitationReceive.setNum(num==null ? null:num.byteValue());
			excitationReceive.setType(type);
			excitationReceive.setWorth(worth);
			excitationReceive.setStatus(STATUS_UNRECEIVE);
			excitationReceive.setExcitationDate(new Date());
			
			excitationReceiveList.add(excitationReceive);
		}
		
		int addBatch=0;
		if(excitationReceiveList!=null && excitationReceiveList.size()>0){
			addBatch = verReceiveDao.addBatch(excitationReceiveList);
		}
		if(addBatch>0){
			response.setStatus(ResponseState.SUCCESS);
			response.setMessage("成功保存"+addBatch+"条(V客充值奖励B方案)发放记录信息");
		}
		
		return response;
	};

}
