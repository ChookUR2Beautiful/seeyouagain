package com.xmniao.xmn.core.api.controller.live;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveWonderfulVideoRequest;
import com.xmniao.xmn.core.live.service.LiveWonderfulVideoService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 获取精彩时刻视频
 */
@Controller
@RequestMapping("/live")
public class LiveWonderfulVideoApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveWonderfulVideoApi.class);

	@Autowired
	private Validator validator;
	@Autowired
	private LiveWonderfulVideoService liveWonderfulVideoService;

	/**
	 * 精彩时刻视频
	 * @param liveWonderfulVideoRequest
	 * @return
	 */
	@RequestMapping(value = "/wonderful/video" ,method = { RequestMethod.GET, RequestMethod.POST}, produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiverInfo(LiveWonderfulVideoRequest liveWonderfulVideoRequest){
		//日志
		log.info("LiveWonderfulVideoRequest data : " + liveWonderfulVideoRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(liveWonderfulVideoRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		//查看用户信息
		return versionControl(liveWonderfulVideoRequest.getApiversion(), liveWonderfulVideoRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionOne(object);  // v3.6版本之前
		case 2: return versionTwo(object);  //v 3.6版本
		default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	private Object versionOne(Object object) {
		LiveWonderfulVideoRequest liveWonderfulVideoRequest = (LiveWonderfulVideoRequest)object;
		return liveWonderfulVideoService.queryWonderfulVideoV1(liveWonderfulVideoRequest);
	}

	private Object versionTwo(Object object) {
		LiveWonderfulVideoRequest liveWonderfulVideoRequest = (LiveWonderfulVideoRequest)object;
		return liveWonderfulVideoService.queryWonderfulVideoV2(liveWonderfulVideoRequest);
	}

}
