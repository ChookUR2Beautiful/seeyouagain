/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.ActivityGroupDao;
import com.xmniao.xmn.core.fresh.dao.ActivityProductDao;
import com.xmniao.xmn.core.fresh.dao.FreshActivityCommonDao;
import com.xmniao.xmn.core.fresh.dao.KillDao;
import com.xmniao.xmn.core.fresh.entity.FreshActivityCommon;
import com.xmniao.xmn.core.fresh.entity.Kill;
import com.xmniao.xmn.core.fresh.util.FreshConstants;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：KillService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月20日 下午6:33:00 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class KillService extends BaseService<Kill> {
	
	@Autowired
	private KillDao killDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return killDao;
	}
	
	@Autowired
	private FreshActivityService activityService;
	
	@Autowired
	private ActivityProductDao activityProductDao;
	
	@Autowired
	private ActivityGroupDao activityGroupDao;
	
	@Autowired
	private FreshActivityCommonDao activityCommonDao;

	/**
	 * 方法描述：保存操作
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月21日上午10:33:49 <br/>
	 * @param kill
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Kill kill) {
		kill.setCreateTime(new Date());
		kill.setUpdateTime(new Date());
		killDao.add(kill);
		Long id = kill.getId();
		for (FreshActivityCommon activityCommon : kill.getActivityCommons()) {
			activityCommon.setSpikeId(id);
			activityCommon.setOrderLimit(kill.getOrderLimit());
			activityCommon.setLabelId(kill.getLabelId());
			activityService.saveActivity(activityCommon);
		}
	}



	/**
	 * 方法描述：根据id加载活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月21日下午6:18:33 <br/>
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public Kill getKill(Long id) throws Exception {
		Kill kill = killDao.selectByPrimaryKey(id);
		if(kill==null){
			return null;
		}
		FreshActivityCommon freshActivityCommon = new FreshActivityCommon();
		freshActivityCommon.setSpikeId(id);
		List<FreshActivityCommon> activityCommons = activityCommonDao.selectList(freshActivityCommon);
		for (FreshActivityCommon activityCommon : activityCommons) {
			List<Map<String,Object>> editActivityVo = activityService.getEditActivityVo(activityCommon.getId());
			activityCommon.setActivityProductVo(editActivityVo);
			kill.setLabelId(activityCommon.getLabelId());
		}
		kill.setActivityCommons(activityCommons);
		return kill;
	}

	/**
	 * 方法描述：修改操作
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月21日下午10:08:59 <br/>
	 * @param kill
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateKill(Kill kill) {
		  killDao.update(kill);
		  activityService.deleteByKillUpdate(kill.getId());
		  for (FreshActivityCommon activityCommon : kill.getActivityCommons()) {
				activityCommon.setSpikeId(kill.getId());
				activityCommon.setOrderLimit(kill.getOrderLimit());
				activityCommon.setLabelId(kill.getLabelId());
				activityService.saveActivity(activityCommon);
			}
	}



	/**
	 * 方法描述：终止活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日上午9:33:43 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void end(Long id) {
		killDao.end(id,FreshConstants.ACTIVITY_KILL_END_STATUS);
		activityCommonDao.updateStatus(id,FreshConstants.ACTIVITY_COMMON_DELETE_STATUS);
	}



	/**
	 * 方法描述：删除活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日上午9:50:03 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteActivity(Long id) {
		killDao.end(id,FreshConstants.ACTIVITY_KILL_DELETE_STATUS);
		activityCommonDao.updateStatus(id,FreshConstants.ACTIVITY_COMMON_DELETE_STATUS);
	}



	/**
	 * 方法描述：加载下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月1日上午10:03:20 <br/>
	 * @param kill
	 * @return
	 */
	public List<Kill> getKillChoose(Kill kill) {
		return killDao.getKillChoose(kill);
	}

}
