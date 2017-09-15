package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveRoomPrivilegeRequest;
import com.xmniao.xmn.core.live.service.LiveUserService;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveRoomPrivilegeApi   
* 类描述：    进入直播房间权限接口
* 创建人：yezhiyong   
* 创建时间：2016年9月6日 下午3:03:17   
* @version    
*
 */
@Controller
@RequestMapping("/live/room")
public class LiveRoomPrivilegeApi implements BaseVControlInf {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveRoomPrivilegeApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入liveUserService
	 */
	@Autowired 
	private LiveUserService liveUserService;

	/**
	 * 
	* @Title: queryLiverInfo
	* @Description: 获取权限
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/liveRoomPrivilege" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryPrivilege(LiveRoomPrivilegeRequest liveRoomPrivilegeRequest){
		//日志
		log.info("liveRoomPrivilegeRequest data : " + liveRoomPrivilegeRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(liveRoomPrivilegeRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		//ios  3.5.6版本缺陷补救
		if ("3.5.6".equals(liveRoomPrivilegeRequest.getAppversion()) && liveRoomPrivilegeRequest.getSystemversion().toLowerCase().contains("ios")) {
			if (liveRoomPrivilegeRequest.getSessiontoken() == null || StringUtils.isEmpty(liveRoomPrivilegeRequest.getSessiontoken())) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
			}
		}
		
		return versionControl(liveRoomPrivilegeRequest.getApiversion(),liveRoomPrivilegeRequest);
	}
	
	public Object versionOne(Object obj){
		LiveRoomPrivilegeRequest liveRoomPrivilegeRequest = (LiveRoomPrivilegeRequest)obj;
		return liveUserService.queryPrivilege(liveRoomPrivilegeRequest);
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
