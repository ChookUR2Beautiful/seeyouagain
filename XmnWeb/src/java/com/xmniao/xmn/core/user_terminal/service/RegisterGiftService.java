package com.xmniao.xmn.core.user_terminal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.coupon.dao.CouponDao;
import com.xmniao.xmn.core.coupon.entity.TCoupon;
import com.xmniao.xmn.core.user_terminal.dao.RegisterGiftDao;
import com.xmniao.xmn.core.user_terminal.entity.TRegisterGift;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：RegisterGiftService
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2016年8月10日上午9:49:16
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class RegisterGiftService {

	private final Logger log = LoggerFactory.getLogger(RegisterGiftService.class);
	@Autowired
	private RegisterGiftDao registerGiftDao;
	
	@Autowired
	private CouponDao couponDao;
	
	public List<TRegisterGift> getGiftList(TRegisterGift registerGift){
		return registerGiftDao.getGiftList(registerGift);
	}
	
	public long getGiftCount(TRegisterGift registerGift){
		
		return registerGiftDao.getGiftCount(registerGift);
	}
	
	public Resultable updateRegisterGift(TRegisterGift registerGift){
		Resultable resultable = new Resultable();
		
		try {
			if(registerGift.getGiftType()==1){//积分
				if(registerGift.getCouponId()==null){
				}else{//原来有
					Integer[] cid = {registerGift.getCouponId()};
					couponDao.delete(cid);
				}
			}else if(registerGift.getGiftType()==2){	//优惠券
				if(registerGift.getCouponId()==null){
					TCoupon coupon = registerGift.getCoupon();
					editCouponfo(coupon);
					couponDao.addReturnId(coupon);
					registerGift.setCouponId(coupon.getCid());
				}else{//原来有
					TCoupon coupon = registerGift.getCoupon();
					editCouponfo(coupon);
					coupon.setCid(registerGift.getCouponId());
					couponDao.update(coupon);
				}
			}
			registerGift.setEdate(new Date());
			registerGiftDao.updateRegisterGift(registerGift);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			log.error("更新失败",e);
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
	
	@Transactional
	public void insterRegisterGift(TRegisterGift registerGift){
		if(registerGift.getGiftType()==1){//积分
			registerGift.setNum(null);
		}else if(registerGift.getGiftType()==2){//优惠券
			TCoupon coupon = registerGift.getCoupon();
			editCouponfo(coupon);
			couponDao.addReturnId(coupon);
			registerGift.setCouponId(coupon.getCid());
			registerGift.setQuota(null);
		}
		registerGift.setEdate(new Date());
		registerGiftDao.addRegisterGift(registerGift);
	}
	
	public Resultable deleteRegisterGift(Integer id){

		Resultable resultable = new Resultable();
		
		try {
			registerGiftDao.deleteRegisterGift(id);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
	
	public TRegisterGift getRegisterGift(Integer id){
		return registerGiftDao.getRegisterGift(id);
	}
	
	/*
	 * 编辑优惠券默认属性
	 */
	private void editCouponfo(TCoupon coupon){
		if(null != coupon){
			coupon.setCname("会员注册赠送"+String.format("%.2f",coupon.getDenomination()) +"元优惠券");
			coupon.setShowAll(1);
			coupon.setIsAllSeller(1);
			coupon.setCtype(0);
		}
	}
	
	public Map<String,String> getRegisterGiftPic(){
		Map<String,String> picMap = new HashMap<String,String>();
		picMap.put("registerImg", "");
		picMap.put("giftImg", "");
		List<Map<String,Object>> picList = registerGiftDao.getRegisterGiftPic();
		for(Map<String,Object> map:picList){
			if(map != null){
				if(map.containsKey("type")){
					if(((int)map.get("type"))==1){
						picMap.put("registerImg", String.valueOf(map.get("img")));
						picMap.put("registerImgId", String.valueOf(map.get("id")));
					}else if(((int)map.get("type"))==2){
						picMap.put("giftImg", String.valueOf(map.get("img")));
						picMap.put("giftImgId", String.valueOf(map.get("id")));
					}
				}
			}
		}
		return picMap;
	}
	
	public int updateRegisterGiftImg(Map<String,Object> imgMap){
		String registerImg = (String)imgMap.get("registerImg");
		String giftImg = (String)imgMap.get("giftImg");
		Integer registerImgId = (Integer)imgMap.get("registerImgId");
		Integer giftImgId = (Integer)imgMap.get("giftImgId");
		
		List<Map<String,Object>> uList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> aList = new ArrayList<Map<String,Object>>();
		if(registerImgId != null){
			Map<String,Object> uMap = new HashMap<String,Object>();
			uMap.put("id", registerImgId);
			uMap.put("img", registerImg);
			uMap.put("edate", new Date());
			uList.add(uMap);
		}else{
			Map<String,Object> aMap = new HashMap<String,Object>();
			aMap.put("type", 1);
			aMap.put("img", registerImg);
			aMap.put("edate", new Date());
			aList.add(aMap);
		}
		if(giftImgId != null){
			Map<String,Object> uMap = new HashMap<String,Object>();
			uMap.put("id", giftImgId);
			uMap.put("img", giftImg);
			uMap.put("edate", new Date());
			uList.add(uMap);
		}else{
			Map<String,Object> aMap = new HashMap<String,Object>();
			aMap.put("type", 2);
			aMap.put("img", giftImg);
			aMap.put("edate", new Date());
			aList.add(aMap);
		}
		if(uList.size()!=0){
			for(Map<String,Object> map:uList){
				registerGiftDao.updateImg(map);
			}
		}
		if(aList.size()!=0){
			registerGiftDao.addImg(aList);
		}
		return 0;
	}
}
