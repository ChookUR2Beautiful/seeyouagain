/**
 * 2016年8月18日 上午10:49:34
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
import com.xmniao.xmn.core.common.request.integral.PageTypeRequest;
import com.xmniao.xmn.core.live.service.AnchorPersonService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：BirdEggBlanceApi   
* 类描述：   获取鸟蛋信息和鸟蛋明细接口
* 创建人：yezhiyong   
* 创建时间：2016年12月1日 下午2:08:59   
* @version    
*
 */
@Controller
public class BirdEggBlanceApi implements BaseVControlInf {

	//日志
	private final Logger log = Logger.getLogger(BirdEggBlanceApi.class);
	//注入service
	@Autowired
	private AnchorPersonService anchorpersonService;
	//注入validator
	@Autowired
	private Validator validator;
	
	//注入sessionTokenService
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 
	* @Title: queryBirdEggBlance
	* @Description: 获取鸟蛋信息和鸟蛋明细接口
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/live/anchor/birdEggBlance",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryBirdEggBlance(PageTypeRequest pageTypeRequest){
		List<ConstraintViolation> result = validator.validate(pageTypeRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(pageTypeRequest.getApiversion(), pageTypeRequest);
	}
	
	private Object versionControlOne(Object object) {
		PageTypeRequest  pageTypeRequest = (PageTypeRequest) object;
		return anchorpersonService.queryBirdEggBlance(pageTypeRequest);
		
		//叶熠之前的版本
		/*
		BaseRequest  baseRequest = (BaseRequest) object;
		//获取uid
		String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		return anchorpersonService.queryEggMoneyNum(uid);
		*/
	}
	
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

}
