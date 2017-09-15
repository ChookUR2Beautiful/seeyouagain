/**
 * 2016年8月18日 上午10:43:08
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
import com.xmniao.xmn.core.common.request.live.FocusLiveRequest;
import com.xmniao.xmn.core.live.service.ViewerLiveService;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：FocusLiveApi   
* 类描述： 取消关注/关注主播/想看预告
* 创建人：yezhiyong   
* 创建时间：2016年8月24日 下午4:48:28   
* @version    
*
 */
@Controller
@RequestMapping("/live/viewer")
public class FocusLiveApi implements BaseVControlInf {

	/**
	 * 注入日志
	 */
	private final Logger log = Logger.getLogger(PersonalCenterApi.class);
		
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入viewerLiveService
	 */
	@Autowired
	private ViewerLiveService viewerLiveService;
	
	/**
	 * 
	* @Title: queryAllBlackList
	* @Description: 关注主播/想看预告
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/focusLive",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object insertFocusLive(FocusLiveRequest focusLiveRequest){
		//日志
		log.info("focusLiveRequest data:" + focusLiveRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(focusLiveRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(focusLiveRequest.getApiversion(),focusLiveRequest);
	}
	
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	private Object versionControlOne(Object object) {
		FocusLiveRequest  focusLiveRequest=(FocusLiveRequest) object;
		return viewerLiveService.insertFocusLive(focusLiveRequest);
	}

}
