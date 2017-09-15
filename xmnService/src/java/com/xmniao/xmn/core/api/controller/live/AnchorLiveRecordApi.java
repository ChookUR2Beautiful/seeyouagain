/**
 * 2016年8月29日 上午11:02:27
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
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.AnchorLiveRecordRequest;
import com.xmniao.xmn.core.live.service.PersonalDetailService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：AnchorLiveRecordApi
 * @类描述：直播记录接口
 * @创建人： yeyu
 * @创建时间 2016年8月29日 上午11:02:27
 * @version
 */
@Controller
public class AnchorLiveRecordApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(AnchorLiveRecordApi.class);

	@Autowired
	private PersonalDetailService personaldetailService;
	@Autowired
	private SessionTokenService sessionTokenService;
	//注入validator
	@Autowired
	private Validator validator;
	@RequestMapping(value="/live/personal/anchorLiveRecord",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryAnchorLiveRecord(AnchorLiveRecordRequest aPLRequest){
		//日志
		log.info("AnchorLiveRecordRequest Data:"+aPLRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(aPLRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(aPLRequest.getApiversion(), aPLRequest);
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
		AnchorLiveRecordRequest  apLRequest=(AnchorLiveRecordRequest) object;
		return personaldetailService.queryAchorLiveRecord(apLRequest);
	}


}
