package com.xmniao.xmn.core.api.controller.live;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.xmniao.xmn.core.kscloud.service.LVBChannelInfoService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveShareRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.vod.QcloudApiModuleCenter;
import com.xmniao.xmn.core.vod.Module.Live;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LVBChannelInfoApi   
* 类描述：   获取直播频道详情信息
* 创建人：yezhiyong   
* 创建时间：2016年10月20日 上午11:00:50   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class LVBChannelInfoApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LVBChannelInfoApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	@Autowired
	private LVBChannelInfoService lvbChannelInfoService;

	/**
	 * 
	* @Title: queryLiverInfo
	* @Description: 获取直播记录详情
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/anchor/lvbChannelInfo" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiveRecordInfo(LiveShareRequest liveShareRequest){
		//验证
		List<ConstraintViolation> result = validator.validate(liveShareRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		return versionControl(liveShareRequest.getApiversion(), liveShareRequest);
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
		LiveShareRequest liveShareRequest = (LiveShareRequest) object;
		return lvbChannelInfoService.getLiveChannelInfo(liveShareRequest);
	}



}
