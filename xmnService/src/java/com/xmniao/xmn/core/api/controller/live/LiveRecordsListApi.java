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
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.AnchorAnnunciateListRequest;
import com.xmniao.xmn.core.common.request.live.LiveRecordRequest;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveRecordsListApi   
* 类描述：   预告/直播,回放列表
* 创建人：yezhiyong   
* 创建时间：2016年8月12日 下午4:08:40   
* @version    
*
 */
@Controller
public class LiveRecordsListApi {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveRecordsListApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入anchorLiveRecordService
	 */
	@Autowired 
	private AnchorLiveRecordService anchorLiveRecordService;
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired 
	private SessionTokenService sessionTokenService;

	/**
	 * @Description 获取直播预告或直播记录列表
	 * @author yhl
	 * @param giftsInfoRequest
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/anchor/liveRecordList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiveRecordList(LiveRecordRequest liveRecordRequest){
		//日志
		log.info("liveRecordsRequest data:"+liveRecordRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(liveRecordRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		//登录后的预告列表
		String sessionToken = liveRecordRequest.getSessiontoken();
		if (StringUtils.isNotEmpty(sessionToken) && liveRecordRequest.getType() == 0) {
			AnchorAnnunciateListRequest request = new AnchorAnnunciateListRequest();
			request.setPage(liveRecordRequest.getPage());
			request.setSessiontoken(liveRecordRequest.getSessiontoken());
			request.setType(0);
			return anchorLiveRecordService.queryAnchorAnnunciateList(request);
			
		}else if (StringUtils.isNotEmpty(sessionToken) && liveRecordRequest.getType() == 2) {
			if (liveRecordRequest.getAnchorId() == null || liveRecordRequest.getAnchorId() == 0) {
				return new BaseResponse(ResponseCode.DATAERR, "提交数据不正确");
			}
			//主播个人主页,直播足迹
			return anchorLiveRecordService.LiveTrack(liveRecordRequest.getPage(), liveRecordRequest.getAnchorId(), liveRecordRequest.getSessiontoken());
		}
		
		//没有登录的预告/直播列表
		return anchorLiveRecordService.queryLiveRecordList(liveRecordRequest);
	}
	
}
