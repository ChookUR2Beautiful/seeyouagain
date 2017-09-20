/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TVstarRewardRecordDao;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardRecord;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarRewardRecordService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-7-17 下午6:28:07 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TVstarRewardRecordService extends BaseService<TVstarRewardRecord> {
	
	@Autowired
	private TVstarRewardRecordDao rewardRecordDao;
	
	/**
	 * 注入会员DAO
	 */
	@Autowired
	private BursDao bursDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return rewardRecordDao;
	}

	/**
	 * 方法描述：获取大选推荐奖励列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-15下午2:15:57 <br/>
	 * @param rewardRecord
	 * @return
	 */
	public List<TVstarRewardRecord> getListInfo(TVstarRewardRecord rewardRecord) {
		List<TVstarRewardRecord> list = rewardRecordDao.getList(rewardRecord);
		if(list!=null && list.size()>0){
			List<Integer> uidList=new ArrayList<Integer>();
			for(TVstarRewardRecord recordItem:list){
				Integer referrerUid = recordItem.getReferrerUid();
				uidList.add(referrerUid);
			}
			if(uidList!=null && uidList.size()>0){
				List<Burs> ursList = bursDao.getUrsListByUids(uidList.toArray());
				for(TVstarRewardRecord recordItem:list){
					Integer referrerUid = recordItem.getReferrerUid();
					for(Burs urs:ursList){
						Integer uid = urs.getUid();
						boolean same=referrerUid !=null && uid!=null && referrerUid.compareTo(uid)==0;
						if(same){
							recordItem.setReferrerName(urs.getNname());
							recordItem.setReferrerPhone(urs.getPhone());
							break;
						}
					}
				}
				
			}
		}
		
		return list;
	}

}
