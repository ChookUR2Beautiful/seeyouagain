/**
 * 2016年8月15日 下午3:26:20
 */
package com.xmniao.xmn.core.live.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.UserFeedBackRequest;
import com.xmniao.xmn.core.live.dao.UserFeedBackDao;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：UserFeedBackService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月15日 下午3:26:20
 * @version
 */
@Service
public class UserFeedBackService {
	
	//日志
	private final Logger log = Logger.getLogger(UserFeedBackService.class);
	@Autowired
	private UserFeedBackDao userfeedbackDao;
	@Autowired
	private PersonalCenterService personalcenterService;
	/**
	 * 
	* @Title: addUserFeedBack
	* @Description: 新增反馈信息
	* @return Object
	 */
	public Object addUserFeedBack(String uid,UserFeedBackRequest ufbRequest){
		
		try {
			
			Map<Object,Object> personMap=personalcenterService.queryLiverPersonByUid(Integer.parseInt(uid));
			if(personMap==null || personMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "未获取到个人信息");
			}
			Map<Object,Object> to_personMap=personalcenterService.queryLiverPersonByUid(ufbRequest.getToUid());
			if(to_personMap==null || to_personMap.size()<=0){
				return new BaseResponse(ResponseCode.SUCCESS, "用户举报成功");
//				return new BaseResponse(ResponseCode.FAILURE, "被举报人不存在");
			}
			Map<Object,Object> reportParam=new HashMap<Object,Object>();
			reportParam.put("liver_send_id", personMap.get("anchorid"));//反馈用户ID
			reportParam.put("liver_to_id", to_personMap.get("anchorid"));//被反馈用户ID
			
			Integer reportnum=userfeedbackDao.queryisFeedBack(reportParam);
			if(reportnum>0){
				return new BaseResponse(ResponseCode.SUCCESS, "用户举报成功");
			}
			Map<Object,Object> param=new HashMap<Object,Object>();
			
			param.put("liver_send_id", personMap.get("anchorid"));//反馈用户ID
			param.put("send_utype", personMap.get("utype"));//反馈用户类型： 1 主播 2 普通用户
			param.put("liver_to_id", to_personMap.get("anchorid"));//被反馈用户ID
			param.put("to_utype", to_personMap.get("utype"));//被反馈用户类型： 1 主播 2 普通用户
			param.put("message_type", "1");//反馈类型
			param.put("message_title", null);//反馈标题
			param.put("message_comment", null);//反馈内容
			param.put("create_time", DateUtil.format(new Date()));//创建时间
			param.put("update_time", DateUtil.format(new Date()));//更新时间
			
			Integer insertFeedBackResult =  userfeedbackDao.addUserFeedBack(param);
			if(insertFeedBackResult<=0){
				return new BaseResponse(ResponseCode.FAILURE, "用户举报失败");
			}
			 return new BaseResponse(ResponseCode.SUCCESS, "用户举报成功");
		} catch (Exception e) {
			log.error("新增用户反馈失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "用户举报失败");
		}
		
	}
	
}
