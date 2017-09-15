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
import com.xmniao.xmn.core.common.request.live.BlackAnchorRequest;
import com.xmniao.xmn.core.live.service.PersonalCenterService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：BlackPersonListApi
 * @类描述：黑名单人员列表接口
 * @创建人： yeyu
 * @创建时间 2016年8月18日 上午10:43:08
 * @version
 */
@Controller
public class BlackPersonListApi implements BaseVControlInf {

	//日志
	private final Logger log = Logger.getLogger(BlackPersonListApi.class);
		
	//注入services
	@Autowired
	private PersonalCenterService personalcenterService;
		
	//注入validator
	@Autowired
	private Validator validator;
	
	
	
	
	/**
	 * 查询黑名单
	* @Title: getAllBlackList
	* @Description: 根据uid查询关注黑名单用户
	* @return Object
	 */
	@RequestMapping(value="/live/personal/blackList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryAllBlackList(BlackAnchorRequest bacRequest){
		log.info("BlackAnchorRequest Data:"+bacRequest.toString());
		List<ConstraintViolation> result = validator.validate(bacRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(bacRequest.getApiversion(),bacRequest);
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
		BlackAnchorRequest  bacRequest=(BlackAnchorRequest) object;
		return personalcenterService.queryAttentionBlackAnchor(bacRequest.getUid(),bacRequest.getPage());
	}

}
