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
import com.xmniao.xmn.core.common.request.live.LiveShareRequest;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiverRecordInfoApi   
* 类描述：   获取直播记录详情
* 创建人：yezhiyong   
* 创建时间：2016年10月20日 上午10:10:21   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class LiveRecordInfoApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveRecordInfoApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入anchorLiveRecordService
	 */
	@Autowired 
	private AnchorLiveRecordService anchorLiveRecordService;

	/**
	 * 
	* @Title: queryLiverInfo
	* @Description: 获取直播记录详情
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/anchor/liveRecordInfo" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiveRecordInfo(LiveShareRequest liveShareRequest){
		//验证
		List<ConstraintViolation> result = validator.validate(liveShareRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		//获取直播记录详情
		return versionControl(liveShareRequest.getApiversion(), liveShareRequest);
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
	
	private Object versionOne(Object object) {
		LiveShareRequest liveShareRequest = (LiveShareRequest) object;
		return anchorLiveRecordService.queryLiveRecordInfo(liveShareRequest);
	}
	
}
