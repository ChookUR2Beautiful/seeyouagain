package com.xmniao.xmn.core.live.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.request.MyFriendsRequest;
import com.xmniao.xmn.core.live.request.MyIndirectlyFriendsRequest;
import com.xmniao.xmn.core.live.service.MyRichFriendService;
import com.xmniao.xmn.core.market.common.Response;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: MyFriendsController    
* @Description:我的壕友接口   
* @author: liuzhihao   
* @date: 2017年3月1日 下午6:26:43
 */
@Controller
@RequestMapping("friend")
public class MyFriendsController {
	
	@Autowired
	private Validator validator;

	//注入我的壕友service
	@Autowired
	private MyRichFriendService richFriendService;
	
	/**
	 * 查询我的直接壕友
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/direct/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryMyDirectFriends(MyFriendsRequest request){
		//验证数据
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return richFriendService.queryMyDirectFriends(request);
	}
	
	/**
	 * 查询我的间接壕友
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/indirectly/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryMyIndirectlyFriends(MyIndirectlyFriendsRequest request){
		//验证数据
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return richFriendService.queryMyIndirectlyFriends(request);
	}
}
