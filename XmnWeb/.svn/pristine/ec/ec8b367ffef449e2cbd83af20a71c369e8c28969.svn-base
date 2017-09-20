package com.xmniao.xmn.core.marketingmanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.SellerMarketingDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerMarketing;
import com.xmniao.xmn.core.marketingmanagement.dao.TActivityApplyDao;
import com.xmniao.xmn.core.marketingmanagement.dao.TActivityDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityApply;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.StringUtils;

@Service
public class ActivityApplyService extends BaseService<TActivityApply> {
	
	@Autowired
	private TActivityApplyDao tActivityApplyDao;
	
	@Autowired
	private SellerMarketingDao sellerMarketingDao;
	
	@Autowired
	private TActivityDao tActivityDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return tActivityApplyDao;
	}

	@Transactional
	public void updateBatch(TActivityApply tActivityApply){
		/*
		 * 更新申请记录
		 */
		if(tActivityApply==null){
//			return;
//			throw new Exception("更新失败");
		}
		Date nowTime = new Date();
		tActivityApply.setEdate(nowTime);
		tActivityApply.setUpdateDate(nowTime);
		tActivityApplyDao.updateBatchByIds(tActivityApply);
		
		/*
		 * 更新活动营销关系表
		 */
		StringBuilder sellerIds = new StringBuilder();
		List<TSellerMarketing> tSellerMarketingList = new ArrayList<TSellerMarketing>();
		List<TActivityApply> tActivityApplyList = tActivityApplyDao.getListByIds(tActivityApply);
		for(TActivityApply ap:tActivityApplyList){
			if(ap.getType()==2 || tActivityApply.getStatus()==2){	
				//商家自定义活动申请，审核后暂不做处理。
				//所有活动申请，审核不通过后，不做其他处理
				continue;
			}
			TSellerMarketing sm = new TSellerMarketing();
			sm.setAid(ap.getActivityId());
			sm.setSellerid(ap.getSellerid());
			TActivity tActivity = tActivityDao.getObject(new Long(ap.getActivityId()));
			sm.setSdate(DateUtil.smartFormat(tActivity.getStartDate()+" "+tActivity.getStartTime()));	//活动开始结束日期+时间
			sm.setEdate(DateUtil.smartFormat(tActivity.getEndDate()+" "+tActivity.getEndTime()));
			sm.setIsattend(0);
			sm.setRdate(nowTime);	
			tSellerMarketingList.add(sm);
			
			sellerIds.append(ap.getSellerid()).append(",");
		}
		int length = sellerIds.lastIndexOf(",");
		if(length!=-1){
			sellerIds.setLength(length);
			
			TSeller tSeller = new TSeller();
			tSeller.setIds(sellerIds.toString());
			tSeller.setUdate(nowTime);
			sellerDao.updateSellerDate(tSeller);
			String[] sellerInfo = {"商家编号",sellerIds.toString(),"更新修改时间","更新"};
			fireLoginEvent(sellerInfo);
			
			sellerMarketingDao.addBatch(tSellerMarketingList);
			String[]sellerMarketingInfo = {"商家编号",sellerIds.toString(),"批量添加活动","添加"}; 
			fireLoginEvent(sellerMarketingInfo);
		}

	}

	public TActivityApply getActivityApply(Integer id){
		return tActivityApplyDao.getActivityApply(id);
	}
}
