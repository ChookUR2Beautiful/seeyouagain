package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveBuyBirdCoinRequest;
import com.xmniao.xmn.core.live.service.UserPayBirdCoinService;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveBuyBirdCoinComboApi   
* 类描述： 直播购买鸟币套餐
* 创建人：yhl   
* 创建时间：2016年8月12日 下午4:08:40   
* @version    
*
 */
@Controller
public class LiveBuyBirdCoinComboApi  implements BaseVControlInf{
	
	/**
	 * 日志+
	 * 
	 */
	private final Logger log = Logger.getLogger(LiveBuyBirdCoinComboApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入购买鸟币service
	 */
	@Autowired 
	private UserPayBirdCoinService userPayBirdCoinService;
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired 
	private SessionTokenService sessionTokenService;

	/**
	 * @Description 获取直播购买鸟币套餐记录列表
	 * @author yhl
	 * @param giftsInfoRequest
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/buyBirdCoin/combo" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiveRecordList(LiveBuyBirdCoinRequest birdCoinRequest){
		
		//日志
		log.info("birdCoinRequest data : " + birdCoinRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(birdCoinRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!"+result.get(0).getMessage());
		}
			return versionControl(birdCoinRequest.getApiversion(),birdCoinRequest);
		}
		
		public Object versionOne(Object obj){
			LiveBuyBirdCoinRequest birdCoinRequest = (LiveBuyBirdCoinRequest)obj;
			return userPayBirdCoinService.queryBirdCoinComboList(birdCoinRequest);
		}
		
		@Override
		public Object versionControl(int v, Object object) {
			switch(v){
			case 1:
				return versionOne(object);
				default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
			}
		}
	
}
