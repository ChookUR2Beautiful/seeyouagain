/**
 * 2016年10月17日 下午4:58:16
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
import com.xmniao.xmn.core.common.request.live.SearchAnchorLiveRequest;
import com.xmniao.xmn.core.live.service.PersonalCenterService;

/**
 * @项目名称：xmnService
 * @类名称：SearchAnchorLiveApi
 * @类描述：搜索主播列表
 * @创建人： yeyu
 * @创建时间 2016年10月17日 下午4:58:16
 * @version
 */
@Controller
public class SearchAnchorLiveApi implements BaseVControlInf {
	
	private final Logger log = Logger.getLogger(SearchAnchorLiveApi.class);
	
	@Autowired
	private PersonalCenterService  personalCenterService;
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value = "/live/serachAnchor" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLivePersonByListName(SearchAnchorLiveRequest searchAnchorLiveRequest){
		//日志
		log.info("SearchAnchorLiveRequest data : " + searchAnchorLiveRequest.toString());
				//验证
		List<ConstraintViolation> result = validator.validate(searchAnchorLiveRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
				
		return versionControl(searchAnchorLiveRequest.getApiversion(),searchAnchorLiveRequest);
	}
	public Object versionOne(Object obj){
		SearchAnchorLiveRequest searchAnchorLiveRequest = (SearchAnchorLiveRequest)obj;
		return personalCenterService.queryLivePersonByListName(searchAnchorLiveRequest.getParameterText(), searchAnchorLiveRequest.getPage());
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


}
