package com.xmniao.service.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.dao.common.GiveRegisterGiftDao;

/**
 * 发放注册礼包（包含积分和优惠券）
 * @author chenJie
 *
 */
@Service
public class GiveRegisterGift {
	
	//初始化日志类
	private final Logger log =Logger.getLogger(GiveRegisterGift.class);
	
	@Autowired
	private GiveRegisterGiftDao giftDao;
	
	@Autowired
	private CommonServiceImpl commonServiceImpl;
	/**
	 * 新用户注册发放礼包
	 * @param uid
	 * @return
	 */
	public boolean giveRegisterGift(String uid,String phone){
		log.info("新用户注册发放礼包"+uid);
		
		if(StringUtils.isBlank(uid)){
			log.error("uid为空");
			return false;
		}
		//查询注册礼包
		List<Map<String, Object>> registerGift = giftDao.getRegisterGift();
		
		if(registerGift ==null || registerGift.size()<=0){
			log.info("没有注册礼包");
			return true;
		}
		
		Map<String,String> paraMap= new HashMap<>();
		for (Map<String, Object> map : registerGift) {
			if (1 == (int)map.get("giftType")) {//赠送积分
				paraMap.put("uId",uid);
				paraMap.put("userType","1");
				paraMap.put("integral",map.get("quota")+"");
				paraMap.put("rType","28");
				paraMap.put("orderId",System.currentTimeMillis()+"");
				paraMap.put("remark","注册送礼包");
				
				int result = commonServiceImpl.modifyWalletBalance(paraMap);
				if (0 != result) {
					log.error("用户"+uid+"赠送礼包"+map.get("id")+"失败");
					continue;
				}
				log.info("用户"+uid+"赠送礼包"+map.get("id")+"成功");
			}else if(2 == (int)map.get("giftType")){//通用优惠券
				addCoupon(uid,map.get("couponId").toString(), phone);//添加优惠券纪录
			}else {
				log.error("礼包类型有误"+map.get("giftType")+"礼包id"+map.get("id"));
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 用户添加优惠券
	 * @param uid
	 * @param couponId
	 * @return
	 */
	private void addCoupon(String uid,String couponId,String phone){
		try {
			if(couponId == null){
				log.error("用户"+uid+"添加优惠券失败"+"优惠券id为空");
				return;
			}
			//获取优惠券详细信息
			Map<String, Object> coupon = giftDao.getCoupon(couponId);
			if (coupon == null) {
				log.error("用户"+uid+"添加优惠券失败，该优惠券不存在"+couponId);
				return;
			}
			Map<String,String> paraMap = new HashMap<>();
			paraMap.put("cid",couponId);
			paraMap.put("uid",uid);
			
			paraMap.put("denomination",coupon.get("denomination").toString());
			paraMap.put("getway","4");//获取方式直接发放
			paraMap.put("getstatus","0");//获取状态
			paraMap.put("serial",UUID.randomUUID().toString());//优惠券序列码
			paraMap.put("phone",phone);//用户手机号
			paraMap.put("userstatus","0");//用户手机号
			paraMap.put("gettime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//获取时间
			paraMap.put("sdate",new SimpleDateFormat("yyyy-MM-dd").format(coupon.get("sdate")));//起始有效期
			paraMap.put("edate",new SimpleDateFormat("yyyy-MM-dd").format(coupon.get("edate")));//截止有效期
			//添加优惠券纪录
			giftDao.addCouponDetail(paraMap);
			
			log.info("用户"+uid+"添加优惠券"+couponId+"成功");
			return;
		} catch (Exception e) {
			log.error("用户"+uid+"添加优惠券"+couponId+"失败"+e);
			return;
		}
	}
}
