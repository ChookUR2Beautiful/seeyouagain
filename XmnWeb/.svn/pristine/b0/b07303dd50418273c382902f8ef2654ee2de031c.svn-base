/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.GroupLevelDao;
import com.xmniao.xmn.core.live_anchor.entity.GroupLevel;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：GroupLevelService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年4月25日 上午10:25:52
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class GroupLevelService extends BaseService<GroupLevel> {

	@Autowired
	private GroupLevelDao groupLevelDao;

	@Override
	protected BaseDao getBaseDao() {
		return groupLevelDao;
	}

	/**
	 * 方法描述：获取没有绑定的等级 创建人： jianming <br/>
	 * 创建时间：2017年4月25日下午5:43:44 <br/>
	 * 
	 * @param groupLevel
	 * @return
	 */
	public List<GroupLevel> getLastLevels(GroupLevel groupLevel) {
		return groupLevelDao.getLastLevels(groupLevel);
	}

	/**
	 * 方法描述：根据上一级查找 创建人： jianming <br/>
	 * 创建时间：2017年4月25日下午5:55:45 <br/>
	 * 
	 * @param lastLevelId
	 * @return
	 */
	public GroupLevel getLastLevel(Long lastLevelId) {
		List<GroupLevel> gs = groupLevelDao.getLastLevel(lastLevelId);
		if (!gs.isEmpty()) {
			return gs.get(0);
		}
		return null;
	}

	/**
	 * 方法描述：更新,如果修改了等级要批量修改他的下级 创建人： jianming <br/>
	 * 创建时间：2017年4月26日上午10:59:22 <br/>
	 * 
	 * @param groupLevel
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateLevel(GroupLevel groupLevel) {
		
		if(groupLevel.getLastLevelId()==null){
			//顶级
			List<GroupLevel> lastLevel = groupLevelDao.getLastLevel(0L);
			groupLevelDao.upLevel(0);
			groupLevel.setLevel(1);
			groupLevel.setLastLevelId(0L);
			groupLevelDao.update(groupLevel);
			if(!lastLevel.isEmpty()){
				GroupLevel groupLevel2 = lastLevel.get(0);
				groupLevel2.setLastLevelId(groupLevel.getId());
				groupLevel2.setLevel(groupLevel.getLevel()+1);
				groupLevelDao.update(groupLevel2);
			}
			return;
		}
		
		GroupLevel before = groupLevelDao.getObject(groupLevel.getId());
		//查询上级
		GroupLevel last = groupLevelDao.getObject(before.getLastLevelId());
		//查询下级
		List<GroupLevel> lastLevel = groupLevelDao.getLastLevel(before.getId());
		if(!lastLevel.isEmpty()){
			//下级接上下级,下级等级-1
			GroupLevel xia = lastLevel.get(0);
			xia.setLastLevelId(last.getId());
			groupLevelDao.downLevel(before.getLevel());
			xia.setLevel(last.getLevel()+1);
			groupLevelDao.update(xia);
		}
		//查询目标上级
		GroupLevel newLast = groupLevelDao.getObject(groupLevel.getLastLevelId());
		//查询目标下级
		List<GroupLevel> newLastLevel = groupLevelDao.getLastLevel(newLast.getId());
		
		if(!newLastLevel.isEmpty()){
			GroupLevel level = newLastLevel.get(0);
			groupLevelDao.upLevel(level.getLevel());
			groupLevel.setLastLevelId(newLast.getId());
			groupLevel.setLevel(newLast.getLevel()+1);
			level.setLastLevelId(groupLevel.getId());
			level.setLevel(groupLevel.getLevel()+1);
			groupLevelDao.update(level);
			groupLevelDao.update(groupLevel);
			
		}
		
		//帮定到目标上下级,下级+1
	}


	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月26日上午11:52:53 <br/>
	 * @param groupLevel
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addLevel(GroupLevel groupLevel) {
		//找出原来下级
		GroupLevel lastLevel = groupLevelDao.getObject(groupLevel.getLastLevelId());  //上级
		
		if(lastLevel==null){
			//查询顶级
			groupLevelDao.upLevel(0);
			List<GroupLevel> maxLevel = groupLevelDao.getLastLevel(0L);  //原来顶级
			groupLevelDao.add(groupLevel);
			if(!maxLevel.isEmpty()){
				GroupLevel groupLevel2 = maxLevel.get(0);
				groupLevel2.setLastLevelId(groupLevel.getId());
				groupLevelDao.update(groupLevel2);
			}
			return;
		}
		
		List<GroupLevel> lastLevel2 = groupLevelDao.getLastLevel(lastLevel.getId());
		
		//将原来下级绑定到新增
		//把新增的绑定到下级
		if(!lastLevel2.isEmpty()){
			//下级以下的级别等级+1
			groupLevelDao.upLevel(groupLevel.getLevel());
			GroupLevel groupLevel2 = lastLevel2.get(0);
			groupLevel.setLastLevelId(lastLevel.getId());
			groupLevel.setLevel(lastLevel.getLevel()+1);
			groupLevelDao.add(groupLevel);
			groupLevel2.setLevel(groupLevel.getLevel()+1);
			groupLevel2.setLastLevelId(groupLevel.getId());
			groupLevelDao.update(groupLevel2);
		}else{
			groupLevel.setLevel(lastLevel.getLevel()+1);
			groupLevel.setLastLevelId(lastLevel.getId());
			groupLevelDao.add(groupLevel);
		}
		
	}

}
