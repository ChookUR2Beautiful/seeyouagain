package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import com.xmniao.xmn.core.common.request.BannerRequest;
import com.xmniao.xmn.core.common.service.CommonService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.service.LiveRoomService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：GetLiveRoomBannerApi   
* 类描述：   查询直播间广告
* 创建人：yezhiyong   
* 创建时间：2017年2月16日 下午3:33:58   
* @version    
*
 */
@Controller
public class LiveRoomBannerApi implements BaseVControlInf{
	
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveRoomBannerApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private LiveRoomService liveRoomService;


	/**
	 * 
	* @Title: queryLiveRoomBanner
	* @Description: 查询直播间banner广告
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/live/liveRoomBanner" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiveRoomBanner(BaseRequest baseRequest){
		//验证
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(baseRequest.getApiversion(), baseRequest);
	}
	
	public Object versionOne(Object obj){
		BaseRequest baseRequest = (BaseRequest)obj;
		return liveRoomService.queryLiveRoomBanner(baseRequest);
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
