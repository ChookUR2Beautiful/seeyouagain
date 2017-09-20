/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TExperienceofficerActivityDao;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerActivity;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerEnrollRecord;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveExperienceofficerActivityService
 * 
 * 类描述：美食体验官活动场次Service
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年5月8日 下午3:22:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class LiveExperienceofficerActivityService extends BaseService<TExperienceofficerActivity>{

	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	private TExperienceofficerActivityDao experienceofficerActivityDao;

	@Override
	protected BaseDao getBaseDao() {
		return experienceofficerActivityDao;
	}
	
	public List<TExperienceofficerActivity> getListByMap(Map<String,Object> map) {
		return experienceofficerActivityDao.getListByMap(map);
	}

	public Long countByMap(Map<String,Object> map) {
		return experienceofficerActivityDao.countByMap(map);
	}

	/*已报名人次*/
	public int countEnroll(Integer activityId){
//		TExperienceofficerEnrollRecord  enroll = new TExperienceofficerEnrollRecord();
//		enroll.setActivityId(activityId);
//		return this.getEnrollRecordList(enroll).size() ;
		TExperienceofficerActivity activity = experienceofficerActivityDao.getObject((long)activityId);
		return activity.getLimitNum()-activity.getRemainderNum();
	}
	
	public List<TExperienceofficerEnrollRecord> getEnrollRecordList(TExperienceofficerEnrollRecord enroll){
		return experienceofficerActivityDao.getEnrollRecordList(enroll);
	}
	
	/**
	 * 
	 * 方法描述：取消并更换已报名的场次
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月18日下午8:41:26 <br/>
	 * @throws Exception 
	 */
	@Transactional(rollbackFor={Exception.class})
	public void cancelActivity(Map<String,Object> cancelActivity) throws Exception{
		Integer activityId = Integer.parseInt(cancelActivity.get("id").toString());
		Integer isChange = cancelActivity.get("isChange")==null||cancelActivity.get("isChange").equals("")?null:Integer.parseInt(cancelActivity.get("isChange").toString());
		Integer newActivityId = cancelActivity.get("newActivityId")==null||cancelActivity.get("newActivityId").equals("")?null:Integer.parseInt(cancelActivity.get("newActivityId").toString());
		Integer isSold = Integer.parseInt(cancelActivity.get("hasSold").toString());	//操作时是否有人报名成功
		
		TExperienceofficerEnrollRecord enroll = new TExperienceofficerEnrollRecord();
		enroll.setActivityId(activityId);
		enroll.setEnrollState(1);
		List<TExperienceofficerEnrollRecord> enrollList= this.getEnrollRecordList(enroll);
		if(enrollList.size()>0 && isSold==0){
			throw new Exception("数据已过时，请重新操作");
		}
		
		TExperienceofficerActivity activity = new TExperienceofficerActivity();
		activity.setId(activityId);
		activity.setActivityState(2);
		activity.setDescription("系统取消该场次");
		int result = experienceofficerActivityDao.update(activity);
		if(result==0){
			throw new Exception("取消场次异常");
		}
		if(enrollList.size()>0){
			if(isChange==0){
				Map<String,Object> uEnrollMap = new HashMap<String, Object>();
				uEnrollMap.put("ctivityId", activityId);
				uEnrollMap.put("enrollState", 1);
				uEnrollMap.put("newEnrollState", 4);
				uEnrollMap.put("description", "系统取消场次及报名成功记录");
				uEnrollMap.put("updateTime", new Date());
				result = experienceofficerActivityDao.updateEnrollRecord(uEnrollMap);	//将已报名成功的记录全都取消掉
				
				//取消掉后，用户的体验卡次数也不加回去（产品-李献洪说的）。
			}else{
				TExperienceofficerActivity newActivity = experienceofficerActivityDao.getObject((long)newActivityId);
				if(newActivity.getRemainderNum()<enrollList.size()){
					throw new Exception("更换的场次剩余名额不足");
				}
				
				TExperienceofficerEnrollRecord selEnroll = new TExperienceofficerEnrollRecord();
				selEnroll.setActivityId(activityId);
				selEnroll.setEnrollState(1);
				List<TExperienceofficerEnrollRecord> newEnrollList= this.getEnrollRecordList(selEnroll);
				for(TExperienceofficerEnrollRecord eRecordOld:enrollList){
					for(TExperienceofficerEnrollRecord eRecordNew:newEnrollList){
						if(eRecordOld.getUid().equals(eRecordNew.getUid())){
							throw new Exception("会员"+eRecordOld.getUid()+"-"+eRecordOld.getPhone()+"之前已成功报名该场次");
						}
					}
					
				}
				
				
				TExperienceofficerActivity uActivity = new TExperienceofficerActivity();
				uActivity.setId(newActivityId);
				uActivity.setRemainderNum(enrollList.size());
				result = experienceofficerActivityDao.updateRemainderNum(uActivity);
				if(result==0){
					throw new Exception("更换场次异常");
				}
				
				Map<String,Object> uEnrollMap = new HashMap<String, Object>();
				uEnrollMap.put("newActivityId", newActivityId);
				uEnrollMap.put("activityId", activityId);
				uEnrollMap.put("enrollState", 1);
				uEnrollMap.put("updateTime", new Date());
				uEnrollMap.put("description", "系统取消场次并更换新场次，旧场次编号："+activityId);
				result = experienceofficerActivityDao.updateEnrollRecord(uEnrollMap);	//更换成新场次
				if(result==0){
					throw new Exception("更换场次异常");
				}
			}

		}
	}
}
