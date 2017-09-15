package com.xmniao.xmn.core.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.CurrentSellerDebitcardRequest;
import com.xmniao.xmn.core.live.dao.UserPayBirdCoinDao;
import com.xmniao.xmn.core.seller.dao.DebitcardSellerDao;
import com.xmniao.xmn.core.seller.entity.DebitcardSeller;
import com.xmniao.xmn.core.seller.service.CurrentSellerDebitcardService;
import com.xmniao.xmn.core.util.PropertiesUtil;
/**
 * 
 * @ClassName:CurrentSellerDebitcardServiceImpl
 * @Description:买单和商家详情页面 和 我的卡券 页面 是否显示 专享卡baner图
 * @Author:xw
 * @Date:2017年4月5日下午3:15:32
 */
@Service
public class CurrentSellerDebitcardServiceImpl implements CurrentSellerDebitcardService {

	@Resource
	private DebitcardSellerDao debitcardSellerDao;
	
	@Resource
	private UserPayBirdCoinDao userPayBirdCoinDao;
	
	@Resource
	private SessionTokenService sessionTokenService;
	
	@Resource
	private PropertiesUtil propertiesUtil;
	
	@Override
	public Object queryCurrentSellerDebitcard(CurrentSellerDebitcardRequest currentSellerDebitcardRequest) throws Exception {
		
//		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(currentSellerDebitcardRequest.getSessiontoken()));
		
		String uid = sessionTokenService.getStringForValue(currentSellerDebitcardRequest.getSessiontoken()) + "";
		
		//结果集map
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		
		//商家是否有专享卡套餐，0没有，1有
		Integer haveDebitcardCombo = 0;
		//用户是否充值过，0没有，1有
		Integer isRecharged = 0;
		
		Long sellerid = currentSellerDebitcardRequest.getSellerid();
		
		//专享卡活动banner图 url
		String debitcardBannerImgUrl = propertiesUtil.getValue("debitcardBannerImgUrl", "conf_live.properties");
		
		//判断当前入口类型，1时我的卡券入口，直接转跳领卡页面
		Integer type = currentSellerDebitcardRequest.getType();
		//添加开关控制，0不显示，1显示
		String showDebitcardBanner = propertiesUtil.getValue("showDebitcardBanner", "conf_common.properties");
		if(type==1){ // 我的卡券入口
			resultMap.put("showBanner", Integer.parseInt(showDebitcardBanner));
		}else {	//商家详情页 和买单 入口
			
			//添加开关控制，0不显示，1显示
			if("0".equals(showDebitcardBanner)){
				resultMap.put("haveDebitcardCombo",Integer.parseInt(showDebitcardBanner));
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"商家没有开通专享卡套餐");
				response.setResponse(resultMap);
				return response;
			}
			
			//获取当前商家开通的储值卡信息，得到对应套餐id数组
			DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(""+sellerid);
			
			if(debitcardSeller==null || debitcardSeller.getRechargeItem()==null){
				resultMap.put("haveDebitcardCombo",haveDebitcardCombo);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"商家没有开通专享卡套餐");
				response.setResponse(resultMap);
				return response;
			}
			String rechargeItem = debitcardSeller.getRechargeItem();
			String[] rechargeComboIds = rechargeItem.split(",");
			
			
			
			
			//根据套餐id数组，查询商家是否有专享卡套餐
			Integer currentSellerDebitcard = userPayBirdCoinDao.queryDebitcardRechargeComboListByIds(rechargeComboIds);
			
			if(currentSellerDebitcard > 0 ){//商家有专享卡套餐 
				haveDebitcardCombo = 1;
				
				//登录的情况下 判断用户是否充值
				if(StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)){
					Integer count = debitcardSellerDao.queryDebitcardPayOrder(sellerid,uid);
					if(count>0){
						isRecharged = 1;
					}
				}
			}
			resultMap.put("cardId",debitcardSeller.getId());
			resultMap.put("debitcardType", debitcardSeller.getSellertype());
			resultMap.put("haveDebitcardCombo", haveDebitcardCombo);
			resultMap.put("isRecharged", isRecharged);
		}
		
		resultMap.put("debitcardBannerImgUrl", debitcardBannerImgUrl);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(resultMap);
		return response;
		
	}

}
