/**
 * 2016年8月17日 下午4:32:28
 */
package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.xmniao.xmn.core.common.request.live.PayBirdCoinListRequest;
import com.xmniao.xmn.core.live.service.UserPayBirdCoinService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PayBirdRecordListApi
 * @类描述：用户充值记录接口
 * @创建人： yeyu
 * @创建时间 2016年8月17日 下午4:32:28
 * @version
 */
@Controller
public class PayBirdRecordListApi implements BaseVControlInf {

	//日志
		private final Logger log = Logger.getLogger(PayBirdRecordListApi.class);
			
		//注入services
		@Autowired
		private UserPayBirdCoinService userpaybirdcoinService;
		
		@Autowired
		private SessionTokenService sessionTokenService;
			
		//注入validator
		@Autowired
		private Validator validator;
		@RequestMapping(value="/live/personal/buyList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object queryUserPayBirdList(PayBirdCoinListRequest pclRequest){
			
			log.info("PayBirdCoinListRequest Data:"+pclRequest.toString());

			List<ConstraintViolation> result = validator.validate(pclRequest);
			if(result.size() >0 && result != null){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
			}
			return versionControl(pclRequest.getApiversion(), pclRequest);
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
			PayBirdCoinListRequest  pclRequest=(PayBirdCoinListRequest) object;
			
			String uid = sessionTokenService.getStringForValue(pclRequest.getSessiontoken()) + "";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
			}
			return userpaybirdcoinService.queryUserPayBirdList(Long.parseLong(uid),pclRequest.getPage());
		}

}
