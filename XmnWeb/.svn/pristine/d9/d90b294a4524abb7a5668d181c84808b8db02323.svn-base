/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.dao.BLiveFansRankDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRank;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRechargeCombo;

/**
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：BLiveFansRankService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-19 下午6:07:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class BLiveFansRankService extends BaseService<BLiveFansRank> {
	
	/**
	 * 注入粉丝级别服务
	 */
	@Autowired
	private BLiveFansRankDao liveFansRankDao;
	
	/**
	 * 注入充值服务
	 */
	@Autowired
	private TLiveRechargeComboService rechargeService;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveFansRankDao;
	}
	
	/**
	 * 
	 * 方法描述：根据Id删除纪录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param id
	 * @return
	 */
    public int deleteById(Long id){
    	return liveFansRankDao.deleteById(id);
    }

	/**
	 * 
	 * 方法描述：新增粉丝级别 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param record
	 * @return
	 */
    public void add(BLiveFansRank record){
    	 liveFansRankDao.add(record);
    }

	/**
	 * 
	 * 方法描述：获取纪录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param id
	 * @return
	 */
    public BLiveFansRank getFansRank(Long id){
    	return liveFansRankDao.getFansRank(id);
    }
	
	/**
	 * 
	 * 方法描述：获取纪录列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param id
	 * @return
	 */
    public List<BLiveFansRank> getList(BLiveFansRank record){
    	return liveFansRankDao.getList(record);
    }
	
	/**
	 * 
	 * 方法描述：获取纪录数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19下午5:51:30 <br/>
	 * @param id
	 * @return
	 */
    public Long count(BLiveFansRank record){
    	return liveFansRankDao.count(record);
    }

    /**
     * 更新粉丝级别纪录
     */
    public Integer update(BLiveFansRank record){
    	return liveFansRankDao.update(record);
    }
    
    /**
     * 同步粉丝级别
     */
    public Integer syncRankNo(BLiveFansRank record){
    	return liveFansRankDao.syncRankNo(record);
    }

	/**
	 * 方法描述：批量更新粉丝级别状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-20上午11:05:24 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public Resultable updateBatch(String ids, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-21下午3:36:01 <br/>
	 * @param fansRank
	 * @return
	 */
	public List<BLiveFansRank> getListInfo(BLiveFansRank fansRank) {
		List<BLiveFansRank> list = liveFansRankDao.getList(fansRank);  //直播粉丝级别表
		List<TLiveRechargeCombo> rechargeList = rechargeService.getRechargeOfFansRankId();
		
		for (TLiveRechargeCombo rechargeCombo : rechargeList) {
			if (list != null){
				for (BLiveFansRank fansRankBean : list) {
					String fansRankId = fansRankBean.getId() == null ? "" : fansRankBean.getId().toString();
					String fansRankIdOfCombo = rechargeCombo.getFansRankId() == null ? "" : rechargeCombo.getFansRankId().toString();
					if (fansRankId.equals(fansRankIdOfCombo)) {
						fansRankBean.setRecharges(rechargeCombo.getRecharges());
						break;
					}
				}
			}
			
		}

		
		
		return list;
	}

	/**
	 * 方法描述：保存粉丝级别信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-3下午4:14:05 <br/>
	 * @param fansRank
	 */
	public void saveInfo(BLiveFansRank fansRank) {
		Long parentId = fansRank.getParentId();
		Integer parentRankNo=0;
		BLiveFansRank fansRankParameter=new BLiveFansRank();
		if(parentId!=null){
			BLiveFansRank parentRank = liveFansRankDao.getFansRank(parentId);
			parentRankNo = parentRank.getRankNo();
		}
		fansRankParameter.setRankNo(parentRankNo);
		fansRankParameter.setRankType(fansRank.getRankType());
		liveFansRankDao.syncRankNo(fansRankParameter);
		Integer goalRankNo = parentRankNo+1;
		fansRank.setRankNo(goalRankNo);
		liveFansRankDao.add(fansRank);
	}

}
