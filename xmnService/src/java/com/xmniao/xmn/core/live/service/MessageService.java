package com.xmniao.xmn.core.live.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.MessageActivityRequest;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.MessageManageDao;
import com.xmniao.xmn.core.order.dao.ExperienceActivityDao;
import com.xmniao.xmn.core.order.service.ExperienceConfigService;
import com.xmniao.xmn.core.util.DateUtil;



/**
 * 项目描述：XmnService
 * API描述：消息处理业务层
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */

@Service
public class MessageService {
	
	private final Logger log = Logger.getLogger(MessageService.class);
	@Autowired
	private LiveGiftsInfoDao giftsInfoDao;
	
	@Autowired
	private StringRedisTemplate stringredisTemplate;
	@Autowired
	private SessionTokenService sessionTokenService;
	@Autowired
	private MessageManageDao  messageManageDao;
	@Autowired
	private ExperienceActivityDao experienceActivityDao;
	
	
	/**修改用户体验卡活动信息已读状态
	 * @param request
	 * @return
	 */
	public Object getUserActivityMessage(MessageActivityRequest request) {
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		try {
		Integer activityId= request.getActivityId();
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("uid", uid);
		map.put("activityId", activityId);
		//map.put("tid", request.getTid());
		Map<Object,Object> message = messageManageDao.findMessagebyActivityId(map);
		if (message==null ||message.isEmpty()) {
			return new BaseResponse(ResponseCode.FAILURE, "无此信息");
		}
		//修改为已读状态
		Integer messageId = (Integer) message.get("tid");
		messageManageDao.updateISshow(messageId);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		return response;
		} catch (Exception e) {
			log.info("查询系统消息出错");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"修改消息失败");
		}
	}
	
	
	/**查看用户系统消息
	 * @param request
	 * @return
	 */
	public Object getUserSystem(PageRequest request) {
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		Integer page = request.getPage();
		Integer pageSize = 10;
		Map<Object,Object> param = new HashMap<Object,Object>();
		param.put("uid", uid);
		param.put("page", page);
		param.put("pageSize", pageSize);
		List<Map<Object,Object>> messageMap = messageManageDao.finMessageByUid(param);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		resultMap.put("allMessage", messageMap);
		response.setResponse(resultMap);
		return response;
	}
	
	
}
