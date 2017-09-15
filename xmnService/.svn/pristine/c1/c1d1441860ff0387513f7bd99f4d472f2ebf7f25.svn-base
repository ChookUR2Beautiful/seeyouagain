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
import com.xmniao.xmn.core.common.request.live.GetLiveRedpacketRequest;
import com.xmniao.xmn.core.live.service.LiveRoomService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：getLiveRedpacketApi   
* 类描述：   领取直播间红包
* 创建人：yezhiyong   
* 创建时间：2016年12月22日 下午4:43:54   
* @version    
*
 */
@RequestMapping("/live")
@Controller
public class getLiveRedpacketApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(getLiveRedpacketApi.class);
	
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
	* @Title: sendLiveRedpacket
	* @Description: 领取直播间红包
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/getLiveRedpacket",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getLiveRedpacket(GetLiveRedpacketRequest getLiveRedpacketRequest){
		//日志
		log.info("getLiveRedpacketApi getLiveRedpacketRequest Data:" + getLiveRedpacketRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(getLiveRedpacketRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(getLiveRedpacketRequest.getApiversion(), getLiveRedpacketRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	private Object versionControlOne(Object object) {
		GetLiveRedpacketRequest getLiveRedpacketRequest = (GetLiveRedpacketRequest) object;
		return liveRoomService.getLiveRedpacket(getLiveRedpacketRequest);
	}

}
