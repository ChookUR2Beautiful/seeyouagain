package com.xmniao.xmn.core.api.controller.live;

import javax.servlet.http.HttpServletRequest;

import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.live.service.MessageManageService;

public class MessageSystemEditApi{

private final Logger log = Logger.getLogger(MessageSystemEditApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired 
	private MessageManageService messageManagerService;

	/**
	 * @Description 修改用户已读消息
	 * @author yhl
	 * @param 
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/message/editMsgStatus" ,method=RequestMethod.GET,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object editMsgStauts(HttpServletRequest request){
		log.info("修改消息为已读："+request.getParameter("tid"));
		return messageManagerService.editMessageStauts(request);
	}
	
	/**
	 * @Description 批量修改用户消息已读
	 * @author yhl
	 * @param 
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/message/editBatchMsgStatus" ,method=RequestMethod.GET,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object editBatchMsgStatus(HttpServletRequest request){
		log.info("批量修改消息为已读："+request.getParameter("type"));
		return messageManagerService.editBatchMessageStauts(request);
	}

}
