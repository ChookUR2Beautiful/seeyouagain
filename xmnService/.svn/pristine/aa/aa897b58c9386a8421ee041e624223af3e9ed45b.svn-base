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
import com.xmniao.xmn.core.common.request.live.GiftsInfoRequest;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;


/**
 * 项目描述：XmnService
 * API描述：直播界面获取礼物列表
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */

@Controller
public class LiveGiftsInfoApi implements BaseVControlInf{

private final Logger log = Logger.getLogger(LiveGiftsInfoApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired 
	private LiveGiftsInfoService liveGiftsInfoService;
	/**
	 * @Description 赠送礼物
	 * @author yhl
	 * @param giftsInfoRequest
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/gifts/giveaway" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object getOrderView(GiftsInfoRequest giftsInfoRequest){
		log.info("提交的数据："+giftsInfoRequest.toString());
		List<ConstraintViolation> result = validator.validate(giftsInfoRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(giftsInfoRequest.getApiversion(),giftsInfoRequest);
	}
	
	public Object versionOne(Object obj){
		GiftsInfoRequest giftsInfoRequest = (GiftsInfoRequest)obj;
		return liveGiftsInfoService.giftsGiveAways(giftsInfoRequest);
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
