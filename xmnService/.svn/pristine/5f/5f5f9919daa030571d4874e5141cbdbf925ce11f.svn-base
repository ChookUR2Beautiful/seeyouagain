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
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveGiftsListRequest;
import com.xmniao.xmn.core.common.request.live.LiverRoomRequest;
import com.xmniao.xmn.core.live.service.LiveAnchorRoomService;
import com.xmniao.xmn.core.live.service.LiveSendProductGiftsService;


/**
 * 
* @Description: 用户或主播进去房间操作
* @return Object    返回类型
* @author yhl
* @throws
* 2016年8月15日11:31:15
 */
@Controller
public class LiveSendProductListApi implements BaseVControlInf {
	
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveSendProductListApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 用户/主播进入房间service
	 * */
	@Autowired
	private LiveSendProductGiftsService liveSendProductGiftsService;
	
	@RequestMapping(value = "/live/sendProduct/list" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryProductList(LiveGiftsListRequest request){
		//日志
		log.info("LiveGiftsListRequest data:"+request.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		//进入房间
		return versionControl(request.getApiversion(), request);
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
	
	private Object versionOne(Object object) {
		LiveGiftsListRequest request = (LiveGiftsListRequest) object;
		return liveSendProductGiftsService.queryMarketProductGiftList(request);
	}
	
}
