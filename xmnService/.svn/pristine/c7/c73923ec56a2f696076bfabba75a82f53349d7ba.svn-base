package com.xmniao.xmn.core.api.controller.live.room;

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
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.live.service.LiveRoomService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：EnterRoomLoadingApi   
* 类描述：   进入房间初始加载页
* 创建人：yezhiyong   
* 创建时间：2016年12月15日 上午10:55:47   
* @version    
*
 */
@RequestMapping("/live")
@Controller
public class EnterRoomLoadingApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(EnterRoomLoadingApi.class);
	
	/**
	 * 注入直播间处理liveRoomService
	 */
	@Autowired
	private LiveRoomService liveRoomService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 
	* @Title: enterRoomLoading
	* @Description: TODO 进入房间初始加载页
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/enterRoomLoading",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryEnterRoomLoading(IDRequest idRequest){
		//验证
		List<ConstraintViolation> result = validator.validate(idRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		//进入房间预加载页面
		return versionControl(idRequest.getApiversion(), idRequest);
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
		IDRequest idRequest = (IDRequest) object;
		return liveRoomService.queryEnterRoomLoading(idRequest);
	}
}
