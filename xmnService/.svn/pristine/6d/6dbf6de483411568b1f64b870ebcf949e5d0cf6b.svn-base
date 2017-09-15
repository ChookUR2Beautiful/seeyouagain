package com.xmniao.xmn.core.coupon.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.personal.UserCouponRequset;
import com.xmniao.xmn.core.live.dao.PersonalCenterDao;
import com.xmniao.xmn.core.live.entity.CouponInfo;

@Service
public class UserCouponService {
	
	//日志
		private final Logger log = Logger.getLogger(UserCouponService.class);
		
		@Autowired
		private StringRedisTemplate stringRedisTemplate;
		
		//注入sessionTokenService
		@Autowired
		private SessionTokenService sessionTokenService;
		
		@Autowired
		private PersonalCenterDao personalCenterDao;
		
		/**
		 * 描述：获取用户优惠券列表 平台优惠 + 粉丝券+ 商家优惠券集合
		 * @param 无
		 * @return object 
		 * */
		public Object queryUserCouponList(UserCouponRequset userCouponRequset){
//			String uid = userCouponRequset.getUid().toString();
			String uid = sessionTokenService.getStringForValue(userCouponRequset.getSessiontoken())+"";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
			}
			MapResponse response = null;
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			
			try {
				Map<Object, Object> paramMap = new HashMap<Object, Object>();
				paramMap.put("page", userCouponRequset.getPage());
				paramMap.put("limit", Constant.PAGE_LIMIT);
				paramMap.put("sellerId", userCouponRequset.getSellerId());
				paramMap.put("uid", uid);
				paramMap.put("payAmount", userCouponRequset.getOrderAmount()==null?"0":userCouponRequset.getOrderAmount());
				if (userCouponRequset.getStatus() == 1) {
					paramMap.put("useStatus", 0);//未使用
				}
				if (userCouponRequset.getStatus() == 2) {
					paramMap.put("useStatus", 1);//已使用
				}
				if (userCouponRequset.getStatus() == 3) {
					paramMap.put("useStatus", 2);//已过期
				}
				
				List<CouponInfo> resultList = new ArrayList<CouponInfo>();
				if (userCouponRequset.getType() == 1) {//查询普通优惠券 商家券 平台券
					
					paramMap.put("version", 0);
					//版本控制 3.5.9  使用优惠券的情况 
					String appversion = userCouponRequset.getAppversion().replace(".", "");
					if (!StringUtils.isEmpty(appversion) || !"null".equalsIgnoreCase(appversion)) {
						int version = Integer.parseInt(appversion);
						if (version<=359) { //标示没有使用优惠券
							paramMap.put("version", 1);
						}
					}
					List<CouponInfo> couponInfoList = personalCenterDao.queryUserCouponList(paramMap);
					
					if (couponInfoList.size()>0) {
						for (int i = 0; i < couponInfoList.size(); i++) {
							CouponInfo info =couponInfoList.get(i);
								
							info.setValidationDate("使用期限："+info.getStartDateStr()+" - "+info.getEndDateStr());
							if (info.getConditions()!=null) {
								if (info.getConditions().compareTo(new BigDecimal(0))>0) {
									info.setUseDescribe("使用条件,买单消费满¥"+info.getConditions()+"使用");
								}else if (info.getConditions().compareTo(new BigDecimal(0))==0){
									info.setUseDescribe("不限制使用");
								}
							}
							resultList.add(info);
						}
					}
					resultMap.put("couponInfoList", couponInfoList);
				}
				
				if (userCouponRequset.getType() == 2) {//查询用户粉丝券
					
					List<CouponInfo> couponInfoList = personalCenterDao.queryUserFansCouponList(paramMap);
					if (couponInfoList.size()>0) {
						for (int i = 0; i < couponInfoList.size(); i++) {
							CouponInfo info =couponInfoList.get(i);
								info.setValidationDate("使用期限："+info.getStartDateStr()+" - "+info.getEndDateStr());
								info.setUseDescribe("请在直播时段到店使用");
								resultList.add(info);
						}
					}
					resultMap.put("couponInfoList", resultList);
				}
				response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
				response.setResponse(resultMap);
				return response;
			} catch (Exception e) {
				log.info("获取优惠券异常");
				e.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE, "获取优惠券异常");
			}
			
		}
}
