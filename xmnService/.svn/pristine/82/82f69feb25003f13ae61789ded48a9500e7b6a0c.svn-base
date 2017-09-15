package com.xmniao.xmn.core.xmer.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PayResultRequest;
import com.xmniao.xmn.core.xmer.dao.PayTypeDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PayTypeService   
* 类描述：   跳转支付页面service
* 创建人：yezhiyong   
* 创建时间：2016年6月6日 下午5:34:38   
* @version    
*
 */
@Service
public class PayTypeService {

	// 注入支付跳转dao
	@Autowired
	private PayTypeDao payTypeDao;

	// 注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	/**
	 * 
	* @Title: payType
	* @Description: 查看支付信息
	* @return Object    返回类型
	* @throws
	 */
	public Object payType(PayResultRequest payResultRequest) {
		String uid = sessionTokenService.getStringForValue(payResultRequest.getSessiontoken()).toString();
		if(uid.equals("") || uid.equals("null")){
			return new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录");
		}
		try{
			Map<Object,Object> map = payTypeDao.querySoldOrederInfoByOrdersn(payResultRequest.getOrderid());
			if(map == null){
				return new BaseResponse(ResponseCode.FAILURE,"订单不存在，请确认后再试！");
			}
			map.put("paymentType", "1000003"); //微信支付
			map.put("orderType", "2");	//订单类型，目前传固定值2
			map.put("source", "002");	//订单来源
			map.put("subject", "SAAS");	//订单标题
			
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误，请联系管理员");
		}
	}
	
	public Map<Object,Object> getSaasSoldOrder(String ordersn){
		return payTypeDao.querySoldOrederInfoByOrdersn(ordersn);
	}

}
