package com.xmniao.xmn.core.seller.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.request.RemoveCouponRequest;
import com.xmniao.xmn.core.common.request.UserCouponRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.seller.service.UserSellerAndPlatCouponSevice;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.dao.CouponDao;

/**
 * 
* @projectName: xmnService 
* @ClassName: UserCouponServiceImpl    
* @Description:用户礼券接口实现类   
* @author: liuzhihao   
* @date: 2016年11月26日 下午3:01:07
 */
@Service
public class UserSellerAndPlatCouponServiceImpl implements UserSellerAndPlatCouponSevice{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(UserSellerAndPlatCouponServiceImpl.class);
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//优惠卷的dao
	@Autowired
	private CouponDao couponDao;
	
	/**
	 * propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入文件头
	 */
	@Autowired
	private String fileUrl;

	@Override
	public List<Map<Object, Object>> queryUserCoupons(UserCouponRequest userCouponRequest) {
		String uid = sessionTokenService.getStringForValue(userCouponRequest.getSessiontoken()).toString();
//		String uid = "338175";//测试数据
		Integer type = userCouponRequest.getType();//查询类型 0 商铺礼券 1 平台礼券  2粉丝卷
		Integer status = userCouponRequest.getStatus();//礼券状态 0 过期 1 未使用
		List<Map<Object,Object>> coupons = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> usedCoupons = new ArrayList<Map<Object,Object>>();//可使用的优惠卷
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("uid", uid);
		map.put("pageNo", userCouponRequest.getPage());
		map.put("pageSize", userCouponRequest.getPageSize());
		switch(type){
			case 0:
				
				//查询商铺优惠卷
				if(status == 1){
					//未使用
					coupons = couponDao.querySellerCouponUsed(map);
					
				}else{
					//过期或者已使用的
					coupons = couponDao.querySellerCouponUnused(map);
				}
				break;
			case 1:
				//平台礼券
				
				if(status == 1){
					//未使用
					coupons = couponDao.queryPlatCouponUsed(map);
				}else{
					//过期或者已使用的
					coupons = couponDao.queryPlatCouponUnused(map);
				}
				
				break;
			case 2:
				//粉丝卷
				map.put("ctype", 2);
				if(status == 1){
					//未使用
					coupons = couponDao.queryPlatCouponUsed(map);
				}else{
					//过期或者已使用的
					coupons = couponDao.queryPlatCouponUnused(map);
				}
				
				break;
		}
		
		
		if(!coupons.isEmpty()){
			for(Map<Object,Object> coupon : coupons){
				int userStatus = Integer.parseInt(coupon.get("useStatus").toString());//实际优惠券使用状态
				
				//使用条件
				if(coupon.get("conditions") != null && Double.parseDouble(coupon.get("conditions").toString()) > 0){
					coupon.put("condition", "买单消费满￥"+coupon.get("conditions")+"时可用");
				}else{
					coupon.put("condition", "消费即可用");
				}
				
				//使用范围
				if (type == 0) {
					//商铺券使用范围
					coupon.put("useRange", coupon.get("sellername") == null?"":coupon.get("sellername").toString());
				}else {
					if (Integer.parseInt(coupon.get("ctype").toString()) == 0) {
						//美食券使用范围
						coupon.put("useRange", coupon.get("sellername")==null?"":coupon.get("sellername").toString() + "买单使用");
						
					}else if (Integer.parseInt(coupon.get("ctype").toString()) == 1) {
						//积分超时券使用范围
						try {
							coupon.put("useRange", propertiesUtil.getValue("integralUseRange", "conf_common.properties"));
						} catch (IOException e) {
							e.printStackTrace();
							coupon.put("useRange", "购买指定的巴马百岁源矿泉水使用");
						}
						
						try {
							//积分超市使用条件
							coupon.put("condition", propertiesUtil.getValue("integralCondition", "conf_common.properties"));
						} catch (IOException e) {
							e.printStackTrace();
							coupon.put("condition", "积分超市购买指定产品时可用");
						}
						
					}else if (Integer.parseInt(coupon.get("ctype").toString()) == 2) {
						//粉丝券使用范围
						coupon.put("useRange", coupon.get("sellername")==null?"":coupon.get("sellername").toString() + "使用");
						
						try {
							//粉丝券使用条件
							coupon.put("condition", propertiesUtil.getValue("fansCondition", "conf_common.properties"));
						} catch (IOException e) {
							e.printStackTrace();
							coupon.put("condition", "使用期限内到店消费使用");
						}
						
					}else if (Integer.parseInt(coupon.get("ctype").toString()) == 3) {
						//预收抵用券使用范围
						try {
							coupon.put("useRange", propertiesUtil.getValue("fansUseRange", "conf_common.properties"));
						} catch (IOException e) {
							e.printStackTrace();
							coupon.put("useRange", "购买直播预售粉丝券使用");
						}
						
					}
					
				}
				
				if(StringUtils.isNotEmpty(ObjectUtils.toString(coupon.get("denomination")))){
					DecimalFormat df = new DecimalFormat("0");//格式化数据
					//格式化金额
					String denomination = Double.parseDouble(coupon.get("denomination").toString())%1==0?df.format(coupon.get("denomination")):new BigDecimal(coupon.get("denomination").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					coupon.put("denomination", denomination);
				}else{
					coupon.put("denomination", 0);
				}
				//格式化时间
				String startDate = ObjectUtils.toString(coupon.get("startDate"));
				if(StringUtils.isNotEmpty(startDate)){
					startDate = DateUtil.format(DateUtil.parse(startDate), "yyyy-MM-dd");
				}else{
					startDate = DateUtil.format(DateUtil.addDay(-1), "yyyy-MM-dd");
				}
				
				String endDate = ObjectUtils.toString(coupon.get("endDate"));
				if(StringUtils.isNotEmpty(endDate)){
					endDate = DateUtil.format(DateUtil.parse(endDate), "yyyy-MM-dd");
				}else{
					endDate = DateUtil.format(DateUtil.yesterday(), "yyyy-MM-dd");
				}
				String validDate = startDate+"至"+endDate;
				coupon.put("validDate", "有效期:"+validDate);//有效期
				
				if (type == 1 && Integer.parseInt(coupon.get("ctype").toString()) == 2) {
					//粉丝券使用时间
					String fansStartDate = coupon.get("startDate")==null?DateUtil.format(DateUtil.addDay(-1), "yyyy-MM-dd HH:mm"):DateUtil.format(DateUtil.parse(coupon.get("startDate").toString()), "yyyy-MM-dd HH:mm");
					String fansEndDate = coupon.get("endDate")==null?DateUtil.format(DateUtil.yesterday(), "yyyy-MM-dd HH:mm"):DateUtil.format(DateUtil.parse(coupon.get("endDate").toString()), "yyyy-MM-dd HH:mm");
					coupon.put("validDate", "使用期限:" + fansStartDate + "-" + fansEndDate);//使用期限
				}
				
				//查询优惠券使用状态 0过期  1未使用
				if (status == 0) {
					//如果实际优惠券使用状态为已使用，则返回已使用，如果为未使用，则返回过期
					if (userStatus == 2) {
						coupon.put("type", 1);//已使用
						coupon.put("typename", "已使用");
					}else {
						coupon.put("type", 2);//未使用
						coupon.put("typename", "已过期");
					}
					
				}else {
					//如果实际优惠券使用状态为已使用，则返回已使用，如果为未使用，则返回去使用
					if (userStatus == 0) {
						coupon.put("type", 0);//未使用
						coupon.put("typename", "去使用");
					}else {
						coupon.put("type", 2);//已使用
						coupon.put("typename", "已使用");
					}
					
				}
				
				//返回集合
				usedCoupons.add(coupon);
			}
			
		}
		
		return usedCoupons;
	}

	/**
	 * 删除
	 */
	@Override
	public int removeCoupon(RemoveCouponRequest removeCouponRequest) {
		int result = 0;
		//从缓存中获取用户id
		String uid = sessionTokenService.getStringForValue(removeCouponRequest.getSessiontoken()).toString();
//		String uid = "338175";//测试数据
		Integer type = removeCouponRequest.getType();
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("id", removeCouponRequest.getId());
		map.put("uid", uid);
		switch(type){
			case 0://删除用户的商家优惠卷
				result = couponDao.removeSellerCouponByUid(map);
				break;
			case 1://删除用户的平台优惠卷
				result = couponDao.removePlatCouponByUid(map);
				break;
		}
		return result;
	}

	/**
	 * 查询推荐的粉丝券 1、根据用户定位，按距离显示附近店铺粉丝券   2、根据可使用时间，由近至远排序
	 */
	@Override
	public List<Map<Object, Object>> queryRecommendFansCoupon(UserCouponRequest userCouponRequest) {
		//结果集
		List<Map<Object, Object>> resultList = new ArrayList<>();
		
		//判断用户是否登录过,登录过则获取uid
		String uid = "";
		Map<Object, Object> liverMap = null;
		if (userCouponRequest != null && StringUtils.isNotEmpty(userCouponRequest.getSessiontoken())) {
			uid = sessionTokenService.getStringForValue(userCouponRequest.getSessiontoken()) + "";
			//如果token过期,则当没有登录处理
			if (StringUtils.isNotEmpty(uid) && !"null".equals(uid)) {
				liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			}
		}
		
		try {
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			if (liverMap != null && liverMap.get("id") != null) {
				//手机号码,排除过滤测试账号
				paramMap.put("phone", liverMap.get("phone"));
			}
			paramMap.put("longitude", userCouponRequest.getLongitude());
			paramMap.put("latitude", userCouponRequest.getLatitude());
			paramMap.put("page", 1);
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("recommend_fans_coupon_nums", "conf_live.properties")));
			
			//查询推荐的粉丝券 1、根据用户定位，按距离显示附近店铺粉丝券   2、根据可使用时间，由近至远排序
			resultList = couponDao.queryRecommendFansCoupon(paramMap);
			//如果没有推荐的粉丝券,则返回空集合
			if (resultList != null && resultList.size() > 0) {
				//格式化粉丝券金额
				DecimalFormat df = new DecimalFormat("0");
				//调整封面地址,粉丝券金额
				for (Map<Object, Object> resultMap : resultList) {
					//格式化金额
					String denomination = Double.parseDouble(resultMap.get("denomination").toString())%1==0?df.format(resultMap.get("denomination")):new BigDecimal(resultMap.get("denomination").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					resultMap.put("denomination", denomination);
					//封面地址
					resultMap.put("cover", resultMap.get("cover")==null?"":(fileUrl + resultMap.get("cover").toString()));
				}
				
			}else {
				resultList = new ArrayList<>();
				
			}
		} catch (Exception e) {
			log.info("查询推荐的粉丝券失败");
		}
		
		return resultList;
	}

}
