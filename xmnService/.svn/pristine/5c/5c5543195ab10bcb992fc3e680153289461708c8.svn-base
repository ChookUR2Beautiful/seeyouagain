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
import com.xmniao.xmn.core.common.request.live.AttentionListRequest;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;


/**
 * 项目描述：XmnService
 * API描述：关注直播记录
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */

@Controller
public class AttentionListApi implements BaseVControlInf{

private final Logger log = Logger.getLogger(AttentionListApi.class);
	
	@Autowired
	private Validator validator;
	
	/**
	 * 
	 * */
	@Autowired 
	private AnchorLiveRecordService anchorLiveRecordService;
	
	/**
	 * @Description 获取关注主播列表的正在直播的记录
	 * @author yhl
	 * @param giftsInfoRequest
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/anchor/attentionList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryAttentionList(AttentionListRequest attentionListRequest){
		//日志
		log.info("attentionListRequest data:"+attentionListRequest.toString());
		List<ConstraintViolation> result = validator.validate(attentionListRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(attentionListRequest.getApiversion(), attentionListRequest);
	}
	
	public Object versionOne(Object obj){
		AttentionListRequest attentionListRequest = (AttentionListRequest)obj;
		return anchorLiveRecordService.queryAttentionList(attentionListRequest);
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
