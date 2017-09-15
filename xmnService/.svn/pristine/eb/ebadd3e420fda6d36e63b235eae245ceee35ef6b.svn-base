package com.xmniao.xmn.core.api.controller.live;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveShareVideoRequest;
import com.xmniao.xmn.core.live.service.LiveShareVideoService;
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
 *
 */
@Controller
@RequestMapping("/live")
public class LiveShareVideoApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(LiveShareVideoApi.class);

	@Autowired
	private Validator validator;

	@Autowired
	private LiveShareVideoService liveShareVideoService;


	@RequestMapping(value = "/liveShare/video", method = { RequestMethod.POST,RequestMethod.GET }, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public Object liveShare(LiveShareVideoRequest liveShareVideoRequest) {
		//验证
		List<ConstraintViolation> result = validator.validate(liveShareVideoRequest);
		if (result.size() > 0 && result != null) {
			log.info("提交的数据有问题" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据有问题");
		}
		
		return versionControl(liveShareVideoRequest.getApiversion(), liveShareVideoRequest);
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
		LiveShareVideoRequest liveShareVideoRequest = (LiveShareVideoRequest) object;
		return liveShareVideoService.getShareInfo(liveShareVideoRequest);
	}


}
