/**
 * 2016年10月18日 下午2:16:09
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
import com.xmniao.xmn.core.common.request.live.LiveRecordVedioUrlRequest;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;

/**
 * @项目名称：xmnService
 * @类名称：LiveRecordVedioUrlApi
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年10月18日 下午2:16:09
 * @version
 */
@Controller
public class LiveRecordVedioUrlApi implements BaseVControlInf {

	//日志
			private final Logger log = Logger.getLogger(LiveRecordVedioUrlApi.class);
			
			//注入services
			@Autowired
			private AnchorLiveRecordService anchorLiveRecordService;
			
			//注入validator
			@Autowired
			private Validator validator;
			

		/**
		 * 
		* @方法名称: modiflyLiveRecordUrl
		* @描述: 更新直播记录视频地址
		* @返回类型 Object
		* @创建时间 2016年10月18日
		* @param liveRurlRequest
		* @return
		 */
			@RequestMapping(value="/live/modiflyLiveVedioUrl",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
			@ResponseBody
			public Object modiflyLiveRecordUrl(LiveRecordVedioUrlRequest liveRurlRequest){
				//日志
				log.info("LiveRecordVedioUrlRequest data:" + liveRurlRequest.toString());
				List<ConstraintViolation> result = validator.validate(liveRurlRequest);
				if(result.size() >0 && result != null){
					log.info("提交的数据有问题"+result.get(0).getMessage());
					return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
				}
				return versionControl(liveRurlRequest.getApiversion(), liveRurlRequest);
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
				LiveRecordVedioUrlRequest  lrvRequest = (LiveRecordVedioUrlRequest) object;
				return anchorLiveRecordService.modiflyLiveRecordVedioUrl(lrvRequest.getLiveRecordId(), lrvRequest.getVedioUrl(),lrvRequest.getChannelId());
			}

}
