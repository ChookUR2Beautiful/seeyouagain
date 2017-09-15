package com.xmniao.xmn.core.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.ToGetDebitcardRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.seller.dao.DebitcardSellerDao;
import com.xmniao.xmn.core.seller.entity.DebitcardSeller;
import com.xmniao.xmn.core.seller.service.ToGetDebitcardService;

@Service
public class ToGetDebitcardServiceImpl implements ToGetDebitcardService {

	@Resource
	private SessionTokenService sessionTokenService;
	
	@Resource
	private DebitcardSellerDao debitcardSellerDao;
	
	@Resource
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Override
	public Object queryDebitcardPayOrder(ToGetDebitcardRequest toGetDebitcardRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(toGetDebitcardRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		Integer recordid = toGetDebitcardRequest.getRecordid();
		//查询直播信息
		LiveRecordInfo recordInfo = anchorLiveRecordDao.queryLiveRecordById(recordid);
		//获取开播类型
		Integer liveStartType = recordInfo.getLive_start_type();
		//获取当前商家id
		Long sellerid = recordInfo.getSellerid();
		/*
		 * 判断商家是否开通专享卡，已开通，判断是否充值
		 * 有充值记录，转跳领卡页面。
		 * 没有充值记录， 自定义直播，进入领卡页面。
		 * 			通过直播，进入直播店铺充值页面
		 */
		//获取当前商家是否开通专享卡信息
		DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(""+sellerid);
		
		//返回字段，默认为1 去到领卡页面
		Integer toGetCard = 1;
		Integer cardId = -1;
		Integer debitcardType = -1;
		if(debitcardSeller !=null && sellerid != null){ //有开通专享卡
			//根据sellerid和用户id查询充值专享卡的充值记录
			Integer count = debitcardSellerDao.queryDebitcardPayOrder(sellerid,uid);
			if(count<=0 && liveStartType==0){//没充值且通告开播，去到充值页面
				toGetCard = 0;
			}
			cardId = debitcardSeller.getId();
			debitcardType = debitcardSeller.getSellertype();
		}
		
		
		Map<Object,Object> resultMap = new HashMap<>();
		resultMap.put("uid", uid);
		resultMap.put("sellerid", sellerid);
		resultMap.put("toGetCard", toGetCard);
		resultMap.put("cardId",cardId);
		resultMap.put("debitcardType", debitcardType);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(resultMap);
		return response;
	}

}
