package com.xmniao.xmn.core.match.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.match.request.MsgRequest;
import com.xmniao.xmn.core.match.service.StarMatchService;

/** 大赛报名发送手机验证码
 * @author wdh
 * @date 2017年7月28日
 * @decription MsgCodeSend
 */
@Controller
public class MsgCodeSend {

	
	@Autowired
	private StarMatchService starMatchService;
	
	@RequestMapping(value="/sendMatchMsg",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object msgSend(MsgRequest request){
		
		return starMatchService.send(request);
	}
}
