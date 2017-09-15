/**
 * 2016年10月18日 上午11:37:24
 */
package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.AnchorInfoBasicRequest;
import com.xmniao.xmn.core.live.service.PersonalCenterService;

/**
 * @项目名称：xmnService
 * @类名称：AnchorInfoBasicApi
 * @类描述：主播个人信息接口
 * @创建人： yeyu
 * @创建时间 2016年10月18日 上午11:37:24
 * @version
 */
@Controller
public class AnchorInfoBasicApi implements BaseVControlInf {

	//日志
		private final Logger log = Logger.getLogger(AnchorInfoBasicApi.class);
		
		//注入services
		@Autowired
		private PersonalCenterService personalcenterService;
		
		//注入validator
		@Autowired
		private Validator validator;
		
		//注入sessionTokenService
		@Autowired
		private SessionTokenService sessionTokenService;

		/**
		 * 
		* @Title: getPersonInfo
		* @Description: 根据用户uid查询直播用户信息
		* @return Object
		 */
		@RequestMapping(value="/live/anchorInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object queryAnchorInfo(AnchorInfoBasicRequest aibRequest){
			//日志
			log.info("AnchorInfoBasicRequest data:" + aibRequest.toString());
			List<ConstraintViolation> result = validator.validate(aibRequest);
			if(result.size() >0 && result != null){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
			}
			return versionControl(aibRequest.getApiversion(), aibRequest);
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
			AnchorInfoBasicRequest  aibRequest = (AnchorInfoBasicRequest) object;
			return personalcenterService.queryAnchorInfoBasic(aibRequest);
		}

}
