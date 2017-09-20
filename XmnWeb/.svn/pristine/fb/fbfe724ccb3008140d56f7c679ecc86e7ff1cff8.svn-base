package com.xmniao.xmn.core.businessman.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.SellerMarketingDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.entity.TSellerMarketing;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.service.TActivityService;
import com.xmniao.xmn.core.util.StringUtils;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerPicService
 * 
 * 类描述： 商家环境图片
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时56分48秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class SellerMarketingService extends BaseService<TSellerMarketing> {

	@Autowired
	private SellerMarketingDao sellerMarketingDao;
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private TActivityService activityService;
	@Override
	protected BaseDao getBaseDao() {
		return sellerMarketingDao;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addSellerMarkerings(TSellerMarketing sellerMarketing) {
		String sellerids = sellerMarketing.getSellerids();
		if(StringUtils.hasLength(sellerids)){
			String[] ids = StringUtils.paresToArray(sellerids, ",");
			TSellerMarketing sm= null;
			TSeller seller = null;
			Integer id = null;
			Date date = new Date();
			Integer aid = sellerMarketing.getAid();
			Integer isattend = sellerMarketing.getIsattend();
			Date sdate = sellerMarketing.getSdate();
			Date edate = sellerMarketing.getEdate();
			List<TSellerMarketing> tSellerMarketings = new ArrayList<TSellerMarketing>(ids.length);
			for(String sellerid : ids){
				id = Integer.parseInt(sellerid);
				sm = new TSellerMarketing();
				sm.setAid(aid);
				sm.setSellerid(id);
				sm.setIsattend(isattend);
				sm.setSdate(sdate);
				sm.setEdate(edate);
				tSellerMarketings.add(sm);
				seller = new TSeller();
				seller.setSellerid(id);
				seller.setUdate(date);
				sellerDao.update(seller);
			}
			String[] sellerInfo = {"商家编号",sellerids,"更新修改时间","更新"};
			fireLoginEvent(sellerInfo);
			sellerMarketingDao.addBatch(tSellerMarketings);
			String[]sellerMarketingInfo = {"商家编号",sellerids,"批量添加活动","添加"}; 
			fireLoginEvent(sellerMarketingInfo);
		}
		
	}
	
	/**
	 * 查询指定商家是否指定参加指定活动
	 * @param map {aid:活动编号,item:商家编号(数组)}
	 * @return
	 */
	public List<Integer> getSellerMarketingBySellerid(Map<String, Object> map){
		return sellerMarketingDao.getSellerMarketingBySellerid(map);
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年4月29日 下午3:18:40
	 * @TODO 描述
	 * @param type
	 * @return
	 */
	public TActivity getTActivity(Integer type) {
		TActivity activity = new TActivity();
		activity.setType(type);
		List<TActivity> activitys = activityService.getList(activity);// 根据活动类型查找活动
		if (activitys.size() > SellerConstants.COMMON_PARAM_Z) {
			activity = activitys.get(0);
		}
		return activity;
	}
	
	public Long getActivtyListCount(TSellerMarketing tSellerMarketing){
		return sellerMarketingDao.getActivtyListCount(tSellerMarketing);
	}
}
