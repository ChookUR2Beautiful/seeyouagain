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
import com.xmniao.xmn.core.common.request.live.AnchorAnnunciateListRequest;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：AnchorAnnunciateListApi   
* 类描述：   通告/历史通告列表
* 创建人：yezhiyong   
* 创建时间：2016年8月12日 上午11:11:16   
* @version    
*
 */
@Controller
@RequestMapping("/live/anchor")
public class AnchorAnnunciateListApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(AnchorAnnunciateListApi.class);
	
	/**
	 * 注入验证
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
	* @Title: getLiveRecordList
	* @Description: 通告/历史通告列表
	* @return Object    返回类型
	* @author
	* @throws
	 */
	@RequestMapping(value = "/anchorAnnunciateList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryAnchorAnnunciateList(AnchorAnnunciateListRequest anchorAnnunciateListRequest){
		//日志
		log.info("anchorAnnunciateListRequest data:"+anchorAnnunciateListRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(anchorAnnunciateListRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(anchorAnnunciateListRequest.getApiversion(), anchorAnnunciateListRequest);
	}
	
	public Object versionOne(Object obj){
		AnchorAnnunciateListRequest anchorAnnunciateListRequest = (AnchorAnnunciateListRequest)obj;
		return anchorLiveRecordService.queryAnchorAnnunciateList(anchorAnnunciateListRequest);
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
