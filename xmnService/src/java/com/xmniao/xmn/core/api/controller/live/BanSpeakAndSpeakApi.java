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
import com.xmniao.xmn.core.common.request.live.BanSpeakAndSpeakRequest;
import com.xmniao.xmn.core.live.service.ViewerLiveService;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：BanSpeakApi   
* 类描述：   主播禁言观众/主播解除观众禁言接口
* 创建人：yezhiyong   
* 创建时间：2016年8月30日 下午5:55:41   
* @version    
*
 */
@Controller
@RequestMapping(value = "/live/room")
public class BanSpeakAndSpeakApi implements BaseVControlInf{

private final Logger log = Logger.getLogger(BanSpeakAndSpeakApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入viewerLiveService
	 */
	@Autowired 
	private ViewerLiveService viewerLiveService;
	
	/**
	 * 
	* @Title: banSpeak
	* @Description: 禁言
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/banSpeakAndSpeak" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object banSpeakAndSpeak(BanSpeakAndSpeakRequest banSpeakAndSpeakRequest){
		//日志
		log.info("banSpeakAndSpeakRequest data : " + banSpeakAndSpeakRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(banSpeakAndSpeakRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}else {
			return versionControl(banSpeakAndSpeakRequest.getApiversion(),banSpeakAndSpeakRequest);
		}
	}
	
	public Object versionOne(Object obj){
		BanSpeakAndSpeakRequest banSpeakAndSpeakRequest = (BanSpeakAndSpeakRequest)obj;
		return viewerLiveService.banSpeakAndSpeak(banSpeakAndSpeakRequest);
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
