/**
 * 2016年8月16日 上午10:47:55
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
import com.xmniao.xmn.core.common.request.live.PersonalDetailRequest;
import com.xmniao.xmn.core.live.service.PersonalDetailService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PersonalListApi
 * @类描述：鸟币,送出,关注,粉丝,直播列表
 * @创建人： yeyu
 * @创建时间 2016年8月16日 上午10:47:55
 * @version
 */
@Controller
public class PersonalDetailApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(PersonalDetailApi.class);

	@Autowired
	private PersonalDetailService personaldetailService;
	//注入validator
	@Autowired
	private Validator validator;
	@RequestMapping(value="/live/personal/personalList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryPersonalList(PersonalDetailRequest PLRequest){
		log.info("PersonalDetailRequest Data:"+PLRequest.toString());

		List<ConstraintViolation> result = validator.validate(PLRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(PLRequest.getApiversion(), PLRequest);
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
		PersonalDetailRequest  pLRequest=(PersonalDetailRequest) object;
		return personaldetailService.queryPersonList(pLRequest.getUid(), pLRequest.getType(), pLRequest.getPage());
	}

}
