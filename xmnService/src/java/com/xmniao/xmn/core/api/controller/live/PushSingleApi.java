/**
 * 2016年9月13日 下午2:29:39
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

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.PushSingleRequest;
import com.xmniao.xmn.core.common.request.live.UserFeedBackRequest;
import com.xmniao.xmn.core.live.service.PushSingleService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PushSingleApi
 * @类描述：推送消息
 * @创建人： yeyu
 * @创建时间 2016年9月13日 下午2:29:39
 * @version
 */
@Controller
public class PushSingleApi implements BaseVControlInf {

	private Logger log=Logger.getLogger(PushSingleApi.class);
	@Autowired
	private PushSingleService  pushSingleService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/live/PushSingle",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object addUserFeedBack(PushSingleRequest psRequest){
		log.info("PushSingleRequest"+psRequest.toString());
		List<ConstraintViolation> result = validator.validate(psRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return pushSingleService.pushLiveMesgge(psRequest,1);
	}

	
	@Override
	public Object versionControl(int v, Object object) {
	return null;
	}



}