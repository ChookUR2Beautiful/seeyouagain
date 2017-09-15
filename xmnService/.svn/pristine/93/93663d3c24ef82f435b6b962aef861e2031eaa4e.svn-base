package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.service.MessageManageService;

public class LiverEditMessageStatusApi{

private final Logger log = Logger.getLogger(LiverEditMessageStatusApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired 
	private MessageManageService messageManagerService;

	/**
	 * @Description 观众设置接收主播开播信息状态提醒
	 * @author yhl
	 * @param 
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/message/LiverEditMsgStatus" ,method=RequestMethod.GET,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object liverEditMsgStatus(HttpServletRequest request){
		String liverId = request.getParameter("id");
		if (null != liverId && !"".equals(liverId)) {
			return messageManagerService.liverEditMsgStatus(request);
		}else {
			log.info("数据有问题：ID="+liverId);
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
	}
}
