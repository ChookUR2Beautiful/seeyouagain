/**
 * 2016年10月21日 上午10:12:25
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
import com.xmniao.xmn.core.common.request.live.LiveTrackRequest;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;

/**
 * @项目名称：xmnService
 * @类名称：LiveTrackApi
 * @类描述：直播记录轨迹
 * @创建人： yeyu
 * @创建时间 2016年10月21日 上午10:12:25
 * @version
 */
@Controller
public class LiveTrackApi implements BaseVControlInf {

private final Logger log = Logger.getLogger(SearchAnchorLiveApi.class);
	
	@Autowired
	private AnchorLiveRecordService anchorLiveRecordService;
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value = "/live/livetrack" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object LiveTrack(LiveTrackRequest liveTrackRequest){
		//日志
		log.info("LiveTrackRequest data : " + liveTrackRequest.toString());
				//验证
		List<ConstraintViolation> result = validator.validate(liveTrackRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
				
		return versionControl(liveTrackRequest.getApiversion(),liveTrackRequest);
	}
	public Object versionOne(Object obj){
		LiveTrackRequest liveTrackRequest = (LiveTrackRequest)obj;
		return anchorLiveRecordService.LiveTrack(liveTrackRequest.getPage(), liveTrackRequest.getAnchorId(), liveTrackRequest.getSessiontoken());
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
