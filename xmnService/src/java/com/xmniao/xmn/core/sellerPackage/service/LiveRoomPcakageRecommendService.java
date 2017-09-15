package com.xmniao.xmn.core.sellerPackage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.Page;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.sellerPackage.LiveRoomPackageRecommendRequest;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageDao;
import com.xmniao.xmn.core.sellerPackage.entity.SellerPackage;

/**
 * 
* @Description: 直播间获取推荐套餐
* @return Object    返回类型
* @author yhl
* @throws
* 2016年8月15日11:31:15
 */
@Service
public class LiveRoomPcakageRecommendService{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveRoomPcakageRecommendService.class);
	
	@Autowired
	private SellerPackageDao sellerPackageDao;

	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private String fileUrl;
	
	/**
	 * 描述：获取直播间推荐的套餐列表   包含悬浮窗，直播间列表
	 * @param LiveRoomPackageRecommendRequest
	 * @return Object
	 * */
	public Object queryLiveRoomPackageList(LiveRoomPackageRecommendRequest recommendRequest){
		MapResponse response = null;
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		try {

			// 直播间是否设置展示其他商家的推荐套餐 0 默认展示 1 不展示
			int showOtherSeller = 0;
			if (recommendRequest.getLiveRecordId() != null && recommendRequest.getLiveRecordId() != 0) {
				showOtherSeller = anchorLiveRecordDao.queryShowOtherSellerByLiveRecordId(recommendRequest.getLiveRecordId());
			}
			// 活动期间显示平台的套餐
			if (recommendRequest.getSelelrId() == null || recommendRequest.getSelelrId() == 0) {
				showOtherSeller = 0;
			}

			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("page", recommendRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			paramMap.put("sellerId", recommendRequest.getSelelrId());
			paramMap.put("position", recommendRequest.getPosition());
			paramMap.put("showOtherSeller", showOtherSeller);
			//获取列表
			List<SellerPackage> sellerPackageList = sellerPackageDao.queryPackageRecommendList(paramMap);
			List<SellerPackage> resList = new ArrayList<SellerPackage>();
			if (sellerPackageList.size()>0) {
				if (recommendRequest.getPosition() == 0) {
					SellerPackage sellerPackage = sellerPackageList.get(0);
					sellerPackage.setPicUrl(fileUrl+sellerPackage.getPicUrl());
					resList.add(sellerPackage);
					resultMap.put("packageList", resList);
				}else {
					for (int i = 0; i < sellerPackageList.size(); i++) {
						SellerPackage sellerPackage = sellerPackageList.get(i);
						sellerPackage.setPicUrl(fileUrl+sellerPackage.getPicUrl());
						resList.add(sellerPackage);
					}
					resultMap.put("packageList", resList);
				}
			}
		} catch (Exception e) {
			log.info("获取推荐套餐失败");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取推荐套餐异常");
		}
		response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
		response.setResponse(resultMap);
		return response;
	}


	void packageList() {

	}
	

}
