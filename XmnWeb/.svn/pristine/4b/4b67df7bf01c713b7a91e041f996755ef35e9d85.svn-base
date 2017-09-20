/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveReferrerCensusDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLiveReferrerCensus;

/**
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveReferrerService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-26 上午10:58:33 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveReferrerService extends BaseService<BLiver> {
	
	/**
	 * 注入直播用户服务
	 */
	@Autowired
	private BLiverDao liverDao;
	
	/**
	 * 注入直播企业级推荐人下线统计数据服务
	 */
	@Autowired
	private TLiveReferrerCensusDao referrerCensusDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liverDao;
	}
	
	/**
     * 
     * 方法描述：获取企业级推荐人列表信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    public List<BLiver> getList(BLiver record){
    	return liverDao.getList(record);
    }
    
    /**
     * 
     * 方法描述：获取企业级推荐人纪录数
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    public Long count(BLiver record){
    	return liverDao.count(record);
    }

	/**
	 * 方法描述：获取企业级推荐人分页列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-26上午11:23:22 <br/>
	 * @param liveAnchor
	 * @param pageable
	 */
	public void getListPage(BLiver liveAnchor, Pageable<BLiver> pageable) {
		
		List<BLiver> referrerList = getReferrerList(liveAnchor);
		Long count = liverDao.count(liveAnchor);
		pageable.setContent(referrerList);
		pageable.setTotal(count);
		
	}
	
	
	/**
	 * 
	 * 方法描述：获取企业级推荐人列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-27上午10:37:58 <br/>
	 * @param liveAnchor
	 * @return
	 */
	public List<BLiver> getReferrerList(BLiver liveAnchor){
		List<BLiver> baseInfoList = liverDao.getList(liveAnchor);//基础信息列表
		
		StringBuffer uids=new StringBuffer();
		if(baseInfoList!=null && baseInfoList.size()>0){
			for(BLiver liver:baseInfoList){
				Integer uid = liver.getUid();
				uids.append(uid).append(",");
			}
			
			Map<String,Object> map=new HashMap<String,Object>();
			String startTime = liveAnchor.getStartTime();
			String endTime = liveAnchor.getEndTime();
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("uids", uids.toString().split(","));
//			List<BLiver> juniorList = countJuniorsGroupByEUid(map);//下线人数
			List<BLiver> juniorList = countJuniorsByEUids(map);//下线人数
			
//			List<TLiveReferrerCensus> juniorInfoList = countJuniorInfoGroupByEuid(map);//下线统计数据
			
			for(BLiver liver:baseInfoList){
				
				if(juniorList!=null && juniorList.size()>0){
					for(BLiver junior:juniorList){
						if(liver.getUid().compareTo(junior.getUid())==0){
							liver.setJuniors(junior.getJuniors());
							break;
						}
					}
				}
				
				//从统计表查询下级充值等信息
				/*if(juniorInfoList!=null && juniorInfoList.size()>0){
					for(TLiveReferrerCensus juniorInfo:juniorInfoList){
						if(liver.getUid().compareTo(juniorInfo.getEnterpriseUid().intValue())==0){
							liver.setJuniorRecharge(juniorInfo.getJuniorRecharge());
							liver.setJuniorReward(juniorInfo.getJuniorReward());
							liver.setJuniorConsume(juniorInfo.getJuniorConsume());
							break;
						}
					}
				}*/
				Integer uid = liver.getUid();
				Map<String,Object> uidMap=new HashMap<String,Object>();
				uidMap.put("uid", uid);
				//查询下级uid集合
				List<Map<String, Object>> listOfMap = getJuniorUidsByUid(uidMap);
				List<String> juniorUids=new ArrayList<String>();
				for(Map<String,Object> mapInfo:listOfMap){
					Object juniorUid = mapInfo.get("uid");
					juniorUids.add(juniorUid.toString());
				}
				
				if(juniorUids!=null && juniorUids.size()>0){
					Map<String,Object> mapParameter=new HashMap<String,Object>();
					mapParameter.put("uids", juniorUids);
					mapParameter.put("startTime", liveAnchor.getStartTime());
					mapParameter.put("endTime", liveAnchor.getEndTime());
					Map<String, Object> juniorRechargeMap = juniorRechargeCountByUids(mapParameter);
					Map<String, Object> juniorRewardMap = juniorRewardCountByUids(mapParameter);
					Map<String, Object> juniorConsumeMap = juniorConsumeCountByUids(mapParameter);
					
					BigDecimal recharge=new BigDecimal(juniorRechargeMap.get("recharge").toString());
					BigDecimal reward=new BigDecimal(juniorRewardMap.get("reward").toString());
					BigDecimal consume=new BigDecimal(juniorConsumeMap.get("consume").toString());
					
					liver.setJuniorRecharge(recharge);
					liver.setJuniorReward(reward);
					liver.setJuniorConsume(consume);
				}
				
			}
		}
		
		return baseInfoList;
		
		
	}
	
	
	 /**
     * 
     * 方法描述：分组统计企业级推荐人下线人数(已作废)<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-26下午4:39:06 <br/>
     * @return
     */
	public List<BLiver> countJuniorsGroupByEUid(Map<String,Object> map){
		return liverDao.countJuniorsGroupByEUid(map);
	}
	
	/**
     * 
     * 方法描述：分组统计企业级推荐人下线人数<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-26下午4:39:06 <br/>
     * @return
     */
	public List<BLiver> countJuniorsByEUids(Map<String,Object> map){
		return liverDao.countJuniorsByEUids(map);
	}
	
	/**
     * 
     * 方法描述：分组统计企业级推荐人下线数据(已作废)<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-26下午4:39:06 <br/>
     * @return
     */
	public List<TLiveReferrerCensus> countJuniorInfoGroupByEuid(Map<String,Object> map){
		return referrerCensusDao.countJuniorInfoGroupByEuid(map);
	}
	
	/**
	 * 
	 * 方法描述：根据uid获取下级uid数组 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:07:23 <br/>
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getJuniorUidsByUid(Map<String,Object> map){
		return referrerCensusDao.getJuniorUidsByUid(map);
	}
	
	/**
	 * 
	 * 方法描述：获取下级uidList <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午5:02:40 <br/>
	 * @param uidStr
	 * @return
	 */
	public List<String> getJuniorUidList(String uidStr){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", uidStr);
		List<String> uidList=new ArrayList<String>();
		List<Map<String, Object>> juniorUidsList = referrerCensusDao.getJuniorUidsByUid(map);
		if(juniorUidsList!=null && juniorUidsList.size()>0){
			for(Map<String,Object> juniorMap:juniorUidsList){
				String uid = juniorMap.get("uid").toString();
				uidList.add(uid);
			}
		}
		
		return uidList;
	}
	
	/**
	 * 
	 * 方法描述：统计下线累计充值 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:11:35 <br/>
	 * @param map
	 * @return
	 */
	public Map<String,Object> juniorRechargeCountByUids(Map<String,Object> map){
		return referrerCensusDao.juniorRechargeCountByUids(map);
	}
	
	
	/**
	 * 
	 * 方法描述：统计下线累计打赏 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:11:35 <br/>
	 * @param map
	 * @return
	 */
	public Map<String,Object> juniorRewardCountByUids(Map<String,Object> map){
		return referrerCensusDao.juniorRewardCountByUids(map);
	}
	
	/**
	 * 
	 * 方法描述：统计下线累计消费 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:11:35 <br/>
	 * @param map
	 * @return
	 */
	public Map<String,Object> juniorConsumeCountByUids(Map<String,Object> map){
		return referrerCensusDao.juniorConsumeCountByUids(map);
	}

	
	/**
	 * 
	 * 方法描述：统计下线充值总金额 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:11:35 <br/>
	 * @param map
	 * @return
	 */
	public Map<String,Object> juniorAmountCountByUids(Map<String,Object> map){
		return referrerCensusDao.juniorAmountCountByUids(map);
	}
}
