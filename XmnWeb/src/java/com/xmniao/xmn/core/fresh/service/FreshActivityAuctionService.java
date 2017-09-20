/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.fresh.dao.TFreshActivityAuctionDao;
import com.xmniao.xmn.core.fresh.entity.TFreshActivityAuction;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshActivityAuctionService
 * 
 * 类描述：积分商城竞拍活动Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-1 下午5:46:33 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class FreshActivityAuctionService extends BaseService<TFreshActivityAuction> {
	
	/**
	 * 注入竞拍活动Dao
	 */
	@Autowired
	private TFreshActivityAuctionDao activityAuctionDao;
	
	/**
	 * 注入积分商城产品Service
	 */
	@Autowired
	private FreshManageService freshManageService;
	

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return activityAuctionDao;
	}
	
	/**
	 * 
	 * 方法描述：根据主键ID删除竞拍活动 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-1下午6:08:27 <br/>
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id){
		return activityAuctionDao.deleteById(id);
	}
	
	/**
	 * 
	 * 方法描述：获取竞拍活动当前最高金额和参与人数等信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-2下午8:54:52 <br/>
	 * @param parameter
	 * @return
	 */
	public List<TFreshActivityAuction> getAuctionRecordList(Map<String,Object> parameter){
		return activityAuctionDao.getAuctionRecordList(parameter);
	}

	/**
	 * 方法描述：获取竞拍活动列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-2下午8:43:13 <br/>
	 * @param freshActivityAuction
	 * @return
	 */
	public List<TFreshActivityAuction> getListInfo( TFreshActivityAuction freshActivityAuction) {
		freshActivityAuction.setCurrentTime(new Date());
		List<TFreshActivityAuction> baseList = getList(freshActivityAuction);
		Map<String,Object> parameter=new HashMap<String,Object>();
		List<Integer> activityIds=new ArrayList<Integer>();
		if(baseList!=null && baseList.size()>0){
			for(TFreshActivityAuction auction:baseList){
				activityIds.add(auction.getId());
			}
			parameter.put("activityIds", activityIds);
			List<TFreshActivityAuction> auctionRecordList = activityAuctionDao.getAuctionRecordList(parameter);
			if(auctionRecordList!=null && auctionRecordList.size()>0){
				for(TFreshActivityAuction auction:baseList){
					Integer id = auction.getId();
					for(TFreshActivityAuction auctionRecord:auctionRecordList){
						Integer activityId = auctionRecord.getActivityId();
						boolean isEqual=id!=null && activityId!=null && id.compareTo(activityId)==0;
						if(isEqual){
							auction.setMaxPrice(auctionRecord.getMaxPrice());
							auction.setPeopleNum(auctionRecord.getPeopleNum());
							break;
						}
					}
				}
			}
		}
		
		return baseList;
	}

	/**
	 * 方法描述：1、保存竞拍活动信息 <br/>
	 * 2、同步商品及商品规格库存 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-3下午2:33:47 <br/>
	 * @param freshActivityAuction
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveInfo(TFreshActivityAuction freshActivityAuction) {
		freshActivityAuction.setCreateTime(new Date());
		try {
			activityAuctionDao.add(freshActivityAuction);
			Long codeid = freshActivityAuction.getCodeid();
			String pvIds = freshActivityAuction.getPvIds();
			freshManageService.updateActivityProductAndGroup(-1, codeid, pvIds);//库存减一
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("保存竞拍活动信息异常");
		}
	}

	/**
	 * 方法描述：立即结束 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-3下午5:41:17 <br/>
	 * @param freshActivityAuction
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void terminate(TFreshActivityAuction freshActivityAuction) throws Exception {
		freshActivityAuction.setUpdateTime(new Date());
		try {
			freshActivityAuction.setState(1);//状态  0:正常  1:终止  2:删除
			activityAuctionDao.update(freshActivityAuction);
			Integer id = freshActivityAuction.getId();
			if(id!=null){
				TFreshActivityAuction activityAuctionDB = activityAuctionDao.getObject(id.longValue());
				Long codeid = activityAuctionDB.getCodeid();
				String pvIds = activityAuctionDB.getPvIds();
				freshManageService.updateActivityProductAndGroup(1, codeid, pvIds);//库存加一
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("结束竞拍活动信息异常");
		}
	}

	/**
	 * 方法描述：删除竞拍活动 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-3下午5:43:59 <br/>
	 * @param freshActivityAuction
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteInfo(TFreshActivityAuction freshActivityAuction) {
		freshActivityAuction.setUpdateTime(new Date());
		try {
			freshActivityAuction.setState(2);//状态  0:正常  1:终止  2:删除
			activityAuctionDao.update(freshActivityAuction);
			Integer id = freshActivityAuction.getId();
			if(id!=null){
				TFreshActivityAuction activityAuctionDB = activityAuctionDao.getObject(id.longValue());
				Long codeid = activityAuctionDB.getCodeid();
				String pvIds = activityAuctionDB.getPvIds();
				freshManageService.updateActivityProductAndGroup(1, codeid, pvIds);//库存加一
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("删除竞拍活动信息异常");
		}
	}

	/**
	 * 方法描述：获取竞拍活动信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-3下午5:55:59 <br/>
	 * @param freshActivityAuction
	 * @return
	 */
	public TFreshActivityAuction getAuctionInfo( TFreshActivityAuction freshActivityAuction) throws Exception {
		TFreshActivityAuction auctionInfo=new TFreshActivityAuction();
		try {
			Integer id = freshActivityAuction.getId();
			if(id!=null){
				auctionInfo=getObject(Long.valueOf(id.toString()));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ApplicationException("获取竞拍活动信息异常");
		}
		return auctionInfo;
	}

	/**
	 * 方法描述：更新竞拍信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-4下午1:46:53 <br/>
	 * @param freshActivityAuction
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateInfo(TFreshActivityAuction freshActivityAuction) {
		try {
			Integer id = freshActivityAuction.getId();
			TFreshActivityAuction activityAuctionDB = activityAuctionDao.getObject(id.longValue());//数据库当前保存的竞拍信息(统一当做重新选择商品处理)
			Long codeidDB = activityAuctionDB.getCodeid();
			String pvIdsDB = activityAuctionDB.getPvIds();
			freshManageService.updateActivityProductAndGroup(1, codeidDB, pvIdsDB);//初始商品库存加一
			Long codeid = freshActivityAuction.getCodeid();
			String pvIds = freshActivityAuction.getPvIds();
			freshManageService.updateActivityProductAndGroup(-1, codeid, pvIds);//新选择商品库存减一
			activityAuctionDao.update(freshActivityAuction);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("更新竞拍活动信息异常");
		}
	}

}
