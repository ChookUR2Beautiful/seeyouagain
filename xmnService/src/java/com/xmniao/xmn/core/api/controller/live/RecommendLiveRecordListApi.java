package com.xmniao.xmn.core.api.controller.live;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.vod.AnchorVideoRequest;
import com.xmniao.xmn.core.live.service.LiveHomeV2Service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.request.live.RecommendLiveRecordListRequest;
import com.xmniao.xmn.core.live.service.LiveHomeService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：RecommendLiveRecordListApi   
* 类描述：   推荐的直播列表/预告列表/精彩时刻列表
* 创建人：yezhiyong   
* 创建时间：2016年12月3日 下午2:28:00   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class RecommendLiveRecordListApi implements BaseVControlInf {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(RecommendLiveRecordListApi.class);
	
	/**
	 * 注入liveHomeService
	 */
	@Autowired 
	private LiveHomeService liveHomeService;

	@Autowired
	private LiveHomeV2Service liveHomeV2Service;

	/**
	 * 
	* @Title: queryRecommendLiveRecordList
	* @Description: 推荐的直播列表/预告列表/精彩时刻列表
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/recommendLiveRecordList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryRecommendLiveRecordList(RecommendLiveRecordListRequest recommendLiveRecordListRequest){
		//日志
		log.info("recommendLiveRecordListRequest data:"+recommendLiveRecordListRequest.toString());
		// v3.6之前版本， RecommendLiveRecordListRequest没有继承BaseRequest
		Integer api = recommendLiveRecordListRequest.getApiversion();
		api = api == null ? 1: api;
		return versionControl(api, recommendLiveRecordListRequest);
	}


	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1: return versionOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	public Object versionOne(Object obj){
		RecommendLiveRecordListRequest request = (RecommendLiveRecordListRequest) obj;
		//推荐的直播列表/预告列表/精彩时刻列表
		return liveHomeService.queryRecommendLiveRecordList(request);
	}

}
