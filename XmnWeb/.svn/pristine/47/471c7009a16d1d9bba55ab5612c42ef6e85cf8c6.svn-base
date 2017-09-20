package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.dao.BLiveFansRankDetailDao;
import com.xmniao.xmn.core.live_anchor.dao.BRankRedPacketDetailDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRankDetail;
import com.xmniao.xmn.core.live_anchor.entity.BRankRedPacketDetail;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BLiveFansRankDetailService
 * 
 * 类描述： 直播粉丝级别返还模式服务Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-25 下午2:24:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BLiveFansRankDetailService {
	
	private final Logger log = Logger.getLogger(getClass());
	
	/**
	 * 注入粉丝级别返还模式Dao
	 */
	@Autowired
	private BLiveFansRankDetailDao fansRankDetailDao; 
	
	/**
	 * 注入粉丝级别返还模式比例配置Dao
	 */
	@Autowired
	private BRankRedPacketDetailDao rankRedPacketDetailDao; 
	
	/**
	 * 
	 * 方法描述：根据主键Id删除返还模式 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-24下午6:13:28 <br/>
	 * @param id
	 * @return
	 */
    public int deleteById(Integer id){
    	return fansRankDetailDao.deleteById(id);
    }
    
    /**
     * 
     * 方法描述：根据主键Id查询返还模式 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:13:32 <br/>
     * @param id
     * @return
     */
    public BLiveFansRankDetail selectById(Integer id){
    	return fansRankDetailDao.selectById(id);
    }
    
    
    /**
     * 
     * 方法描述：获取返还模式列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:32:02 <br/>
     * @param record
     * @return
     */
    public List<BLiveFansRankDetail> getList(BLiveFansRankDetail record){
    	return fansRankDetailDao.getList(record);
    }
    
    /**
     * 
     * 方法描述：获取返还模式数量<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:35:26 <br/>
     * @param record
     * @return
     */
    public long count(BLiveFansRankDetail record){
    	return fansRankDetailDao.count(record);
    }

    /**
     * 
     * 方法描述：添加粉丝级别返还模式<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:13:37 <br/>
     * @param record
     * @return
     */
    public int add(BLiveFansRankDetail record){
    	return fansRankDetailDao.add(record);
    }

    /**
     * 
     * 方法描述：更新粉丝级别返还模式 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:13:41 <br/>
     * @param record
     * @return
     */
    public int update(BLiveFansRankDetail record){
    	 return fansRankDetailDao.update(record);
    }

	/**
	 * 方法描述：保存返还模式详细信息及返还比例配置信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-27下午4:48:11 <br/>
	 * @param fansRankDetail
	 */
	public void saveRankDetailInfo(BLiveFansRankDetail fansRankDetail) {
		try {
			int count = fansRankDetailDao.add(fansRankDetail);
			if(count>0){
				Integer rankDetailId = fansRankDetail.getId();
				List<BRankRedPacketDetail> privateRedPacketDetailList = fansRankDetail.getPrivateRedPacketDetailList();
				if(privateRedPacketDetailList!=null && privateRedPacketDetailList.size()>0){
					for(BRankRedPacketDetail privateRedPacketDetail:privateRedPacketDetailList){
						privateRedPacketDetail.setRankDetailId(rankDetailId);
					}
					rankRedPacketDetailDao.addBatch(privateRedPacketDetailList);
				}
				
				
				List<BRankRedPacketDetail> publicRedPacketDetailList = fansRankDetail.getPublicRedPacketDetailList();
				if(publicRedPacketDetailList!=null && publicRedPacketDetailList.size()>0){
					for(BRankRedPacketDetail publicRedPacketDetail:publicRedPacketDetailList){
						publicRedPacketDetail.setRankDetailId(rankDetailId);
					}
					rankRedPacketDetailDao.addBatch(publicRedPacketDetailList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("保存返还模式详细信息及返还比例配置信息失败："+e.getMessage(), e);
		}
		
	}

	/**
	 * 方法描述：设置级别返还模式信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-27下午6:21:22 <br/>
	 * @param fansRankDetailReq
	 * @return
	 */
	public BLiveFansRankDetail setFansRankDetailInfo( BLiveFansRankDetail fansRankDetailReq) {
		BLiveFansRankDetail liveFansRankDetail=new BLiveFansRankDetail();
		try {
			Integer rankDetailId = fansRankDetailReq.getId();
			if(rankDetailId==null){
				throw new  ApplicationException("获取级别返还模式ID失败");
			}
			liveFansRankDetail = fansRankDetailDao.selectById(rankDetailId);
			BRankRedPacketDetail redPacketDetailPrivateReq=new BRankRedPacketDetail();
			redPacketDetailPrivateReq.setRankDetailId(rankDetailId);
			redPacketDetailPrivateReq.setDividendsRole(LiveConstant.DIVIDENDS_ROLE.DIVIDENDS_PRIVATE);
			List<BRankRedPacketDetail> privateRedPacketDetailList = rankRedPacketDetailDao.getList(redPacketDetailPrivateReq);//内购返还比例
			liveFansRankDetail.setPrivateRedPacketDetailList(privateRedPacketDetailList);
			
			BRankRedPacketDetail redPacketDetailPublicReq=new BRankRedPacketDetail();
			redPacketDetailPublicReq.setRankDetailId(rankDetailId);
			redPacketDetailPublicReq.setDividendsRole(LiveConstant.DIVIDENDS_ROLE.DIVIDENDS_PUBLIC);
			List<BRankRedPacketDetail> publicRedPacketDetailList = rankRedPacketDetailDao.getList(redPacketDetailPublicReq);//外购返还比例
			liveFansRankDetail.setPublicRedPacketDetailList(publicRedPacketDetailList);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("执行BLiveFansRankDetailService——>setFansRankDetailInfo()方法失败:"+e.getMessage(), e);
		}
		return liveFansRankDetail;
	}

	/**
	 * 方法描述：更新返还模式红包返还比例 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-28上午11:05:26 <br/>
	 * @param fansRankDetailReq
	 */
	public void updateRedPacketDetailInfo(BLiveFansRankDetail fansRankDetailReq) {
		try {
			List<BRankRedPacketDetail> privateRedPacketDetailList = fansRankDetailReq.getPrivateRedPacketDetailList();
			List<BRankRedPacketDetail> publicRedPacketDetailList = fansRankDetailReq.getPublicRedPacketDetailList();
			List<BRankRedPacketDetail> redPacketDetailList=new ArrayList<BRankRedPacketDetail>();
			if(privateRedPacketDetailList!=null && privateRedPacketDetailList.size()>0){
				redPacketDetailList.addAll(privateRedPacketDetailList);
			}
			
			if(publicRedPacketDetailList!=null && publicRedPacketDetailList.size()>0){
				redPacketDetailList.addAll(publicRedPacketDetailList);
			}
			
			if(redPacketDetailList!=null && redPacketDetailList.size()>0){
				for(BRankRedPacketDetail rankRedPacketDetail:redPacketDetailList){
					rankRedPacketDetailDao.update(rankRedPacketDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("执行BLiveFansRankDetailService——>updateRedPacketDetailInfo()方法异常:"+e.getMessage(), e);
		}
	}

	/**
	 * 方法描述：获取返还模式列表（包含返还比例配置信息）  <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-28下午1:54:47 <br/>
	 * @param fansRankDetail
	 * @return
	 */
	public List<BLiveFansRankDetail> getListWithRestitution(
			BLiveFansRankDetail fansRankDetail) {
		List<BLiveFansRankDetail> list = fansRankDetailDao.getList(fansRankDetail);
		List<Integer> rankDetailIds=new ArrayList<Integer>();
		if(list!=null && list.size()>0){
			for(BLiveFansRankDetail rankDetail:list){
				Integer rankDetailId = rankDetail.getId();
				rankDetailIds.add(rankDetailId);
			}
			
			BRankRedPacketDetail redPacketDetailReq=new BRankRedPacketDetail();
			redPacketDetailReq.setDividendsRole(LiveConstant.DIVIDENDS_ROLE.DIVIDENDS_PRIVATE);
			redPacketDetailReq.setRankDetailIds(rankDetailIds);
			List<BRankRedPacketDetail> privateRedPacketDetailList = rankRedPacketDetailDao.getList(redPacketDetailReq);//内购返还比例
			redPacketDetailReq.setDividendsRole(LiveConstant.DIVIDENDS_ROLE.DIVIDENDS_PUBLIC);
			List<BRankRedPacketDetail> publicRedPacketDetailList = rankRedPacketDetailDao.getList(redPacketDetailReq);//外购返还比例
			
			for(BLiveFansRankDetail rankDetail:list){
				StringBuffer privateConsumeZone=new StringBuffer();
				StringBuffer privateCashZone=new StringBuffer();
				StringBuffer privateCoinZone=new StringBuffer();
				if(privateRedPacketDetailList!=null && privateRedPacketDetailList.size()>0){
					for(BRankRedPacketDetail privateRedPacketDetail:privateRedPacketDetailList){
						if(rankDetail.getId().compareTo(privateRedPacketDetail.getRankDetailId())==0){
							privateConsumeZone.append(privateRedPacketDetail.getConsumeZone()).append(",");
							privateCashZone.append(privateRedPacketDetail.getCashZone()).append(",");
							privateCoinZone.append(privateRedPacketDetail.getCoinZone()).append(",");
						}
					}
				}
				rankDetail.setPrivateConsumeZone(privateConsumeZone.toString());
				rankDetail.setPrivateCashZone(privateCashZone.toString());
				rankDetail.setPrivateCoinZone(privateCoinZone.toString());
				
				StringBuffer publicConsumeZone=new StringBuffer();
				StringBuffer publicCashZone=new StringBuffer();
				StringBuffer publicCoinZone=new StringBuffer();
				if(publicRedPacketDetailList!=null && publicRedPacketDetailList.size()>0){
					for(BRankRedPacketDetail publicRedPacketDetail:publicRedPacketDetailList){
						if(rankDetail.getId().compareTo(publicRedPacketDetail.getRankDetailId())==0){
							publicConsumeZone.append(publicRedPacketDetail.getConsumeZone()).append(",");
							publicCashZone.append(publicRedPacketDetail.getCashZone()).append(",");
							publicCoinZone.append(publicRedPacketDetail.getCoinZone()).append(",");
						}
					}
				}
				rankDetail.setPublicConsumeZone(publicConsumeZone.toString());
				rankDetail.setPublicCashZone(publicCashZone.toString());
				rankDetail.setPublicCoinZone(publicCoinZone.toString());
				
			}
			
		}
		
		
		return list;
	}

}