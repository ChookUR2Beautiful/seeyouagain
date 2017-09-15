/**
 * 2016年8月19日 上午10:49:33
 */
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
import com.xmniao.xmn.core.common.request.live.SendBarrageRequest;
import com.xmniao.xmn.core.live.service.SendBarrageService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SendBarrageApi
 * @类描述：发送弹幕接口
 * @创建人： yeyu
 * @创建时间 2016年8月19日 上午10:49:33
 * @version
 */
@Controller
public class SendBarrageApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(SendBarrageApi.class);

	@Autowired
	private SendBarrageService sendbarrageService;
	//注入validator
	@Autowired
	private Validator validator;
	@RequestMapping(value="/live/message/sendBarrage",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object SendBarrage(SendBarrageRequest sbRequest){
		log.info("SendBarrageRequest Data:"+sbRequest.toString());

		List<ConstraintViolation> result = validator.validate(sbRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(sbRequest.getApiversion(), sbRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	private Object versionControlOne(Object object) {
		SendBarrageRequest  sendbarrageRequest=(SendBarrageRequest) object;
		return sendbarrageService.addSendBarrage(sendbarrageRequest);
	}


}
