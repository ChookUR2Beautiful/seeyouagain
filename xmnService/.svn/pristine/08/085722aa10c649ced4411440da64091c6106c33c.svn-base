/**
 * 2016年8月15日 上午11:37:00
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
import com.xmniao.xmn.core.common.request.live.ViewerUserListRequest;
import com.xmniao.xmn.core.live.service.ViewerUserListService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：ViewerUserList
 * @类描述：观众列表接口
 * @创建人： yeyu
 * @创建时间 2016年8月15日 上午11:37:00
 * @version
 */
@Controller
public class ViewerUserListApi implements BaseVControlInf {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ViewerUserListApi.class);

	/**
	 * 注入vieweruserlistService
	 */
	@Autowired
	private ViewerUserListService vieweruserlistService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 
	* @Title: queryViewerUserList
	* @Description: 获取直播间观众列表
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/live/anchor/viewerList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryViewerUserList(ViewerUserListRequest viewerUserListRequest){
		//日志
		log.info("viewerUserListRequest Data:" + viewerUserListRequest.toString());
		//验证参数
		List<ConstraintViolation> result = validator.validate(viewerUserListRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		
		//获取直播间观众列表
		return versionControl(viewerUserListRequest.getApiversion(), viewerUserListRequest);
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
		ViewerUserListRequest viewerUserListRequest = (ViewerUserListRequest) object;
		return vieweruserlistService.queryViewerUserList(viewerUserListRequest.getAnchorId(), viewerUserListRequest.getLiveRecordId(), viewerUserListRequest.getPage());
	}
	
}
