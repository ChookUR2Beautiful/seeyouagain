package com.xmniao.xmn.core.api.controller.live;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.request.live.LiveHomeRequest;
import com.xmniao.xmn.core.live.service.LiveHomeService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiveHomeApi   
* 类描述：   直播首页(banner图,预告/预售,直播)
* 创建人：yezhiyong   
* 创建时间：2016年12月1日 下午3:20:27   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class LiveHomeApi {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveHomeApi.class);
	
	/**
	 * 注入liveHomeService
	 */
	@Autowired 
	private LiveHomeService liveHomeService;

	/**
	 * 
	* @Title: queryLiveHome
	* @Description: 直播首页(banner图,预告/预售,直播)
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/liveHome" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiveHome(LiveHomeRequest liveHomeRequest){
		//日志
		log.info("liveHomeRequest data:"+liveHomeRequest.toString());
		
		//查询直播首页信息(banner图,预告/预售,直播)
		return liveHomeService.queryLiveHome(liveHomeRequest);
	}
	
}
