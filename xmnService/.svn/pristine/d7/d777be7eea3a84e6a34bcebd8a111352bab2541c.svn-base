/**
 * 2016年10月11日 上午10:35:51
 */
package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import com.xmniao.xmn.core.common.request.live.LiveShareVideoRequest;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveShareInitRequest;
import com.xmniao.xmn.core.live.service.LiveShareInitService;

/**
 * @项目名称：xmnService
 * @类名称：LiveShareInitApi
 * @类描述：直播分享初始化
 * @创建人： yeyu
 * @创建时间 2016年10月11日 上午10:35:51
 * @version
 */
@Controller
public class LiveShareInitApi{
	
	/**
	 * 日志
	 */
	private Logger log=LoggerFactory.getLogger(LiveShareInitApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入liveShareInitService
	 */
	@Autowired
	private LiveShareInitService liveShareInitService;
	
	/**
	 * 注入本地服务地址
	 */
	@Autowired
	private String localDomain;
	/**
	 * 
	* @方法名称: queryLiveShareInit
	* @描述: 直播分享初始化
	* @返回类型 Object
	* @创建时间 2016年10月11日
	* @param liveShareInitRequest
	* @return
	 */
	@RequestMapping(value="/live/shareInit")
	public  String queryLiveShareInit(LiveShareInitRequest liveShareInitRequest,Model model){
		//日志
		log.info("LiveShareInitRequest data:" + liveShareInitRequest.toString());
		List<ConstraintViolation> result = validator.validate(liveShareInitRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			model.addAttribute("initmap", new MapResponse(ResponseCode.DATAERR,"提交的数据有问题,"+result.get(0).getMessage()));
		}else{
			MapResponse response=liveShareInitService.queryLiveShareInit(liveShareInitRequest);
			model.addAttribute("initmap", response);
		}
		model.addAttribute("liveUpLoadUrl", localDomain + "/live/liveUpLoad");
		return "live/play";
	}

	@RequestMapping(value="/live/shareVideoInit")
	public  String queryLiveShareVideoInit(LiveShareVideoRequest liveShareVideoRequest, Model model){
		//日志
		log.info("LiveShareInitRequest data:" + liveShareVideoRequest.toString());
		if(liveShareVideoRequest.getId() == null || liveShareVideoRequest.getId() == 0) {
			log.info("提交的数据有问题, 精彩时刻回放视频ID不能为空");
			model.addAttribute("initmap", new MapResponse(ResponseCode.DATAERR,"提交的数据有问题, 精彩时刻回放视频ID不能为空"));
		} else{
			MapResponse response=liveShareInitService.queryLiveShareVideoInit(liveShareVideoRequest);
			model.addAttribute("initmap", response);
		}
		model.addAttribute("liveUpLoadUrl", localDomain + "/live/liveUpLoad");
		return "live/play";
	}
	
	/**
	 * 
	* @Title: getReferList
	* @Description: 获取直播分享推荐列表
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/live/getReferList",method={RequestMethod.GET, RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getReferList(){
		return liveShareInitService.getReferList();
	}
	
	/**
	 * 
	* @Title: queryBarrageList
	* @Description: 获取直播分享弹幕
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/live/getBarrage",method={RequestMethod.GET, RequestMethod.POST},produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryBarrageList(int zhiboRecordId){
		return liveShareInitService.queryCommonMessage(zhiboRecordId);
	}

}
