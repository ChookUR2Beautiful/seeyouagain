package com.xmniao.xmn.core.api.controller.urs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.urs.UrsCollectUrsRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.LiveUserService;
import com.xmniao.xmn.core.verification.service.UrsService;

/**
 * 
*      
* 类名称：UrsCollectUrsInsertApi   
* 类描述：   用户关注
* 创建人：xiaoxiong   
* 创建时间：2016年11月15日 上午9:57:01     
*
 */

@RequestMapping("urs/collect/urs")
@Controller
public class UrsCollectUrsInsertApi implements BaseVControlInf{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(UrsCollectSellerInsertApi.class);
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private UrsService ursServcie;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private LiveUserService liveUserService;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert(UrsCollectUrsRequest request){
		//验证请求数据的合法性
				List<ConstraintViolation> result = validator.validate(request);
				if(result.size() > 0){
					log.info("数据提交有问题"+result.get(0).getMessage());
					return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
				}
				return versionControl(request.getApiversion(), request);
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
		UrsCollectUrsRequest  request = (UrsCollectUrsRequest) object;
		try {
			String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null")){
				return new BaseResponse(ResponseCode.DATAERR, "token错误或已过期，请重新登入!");
			}
			
			//验证是否自己关注自己
			if (Integer.parseInt(uid) == request.getCuid()) {
				return new BaseResponse(ResponseCode.FOCUS_MYSELFT_ERROR, "用户自己不能关注自己");
			}
			
			LiverInfo liveInfo = liveUserService.queryLiverByUid(uid);
			if (liveInfo == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
				
			}
			
			LiverInfo cliveInfo = liveUserService.queryLiverByUid(request.getCuid()+"");
			if(cliveInfo==null){
				//给旧用户创建直播用户信息
				BaseResponse response = (BaseResponse) liveUserService.createTlsUser(request.getCuid() + "");
				if (response.getState() != 100) {
					return response;
				}
				cliveInfo = liveUserService.queryLiverByUid(request.getCuid()+"");
			}
			
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("uid", liveInfo.getId());
			params.put("cuid", cliveInfo.getId());
			params.put("cdate", new Date());
			
			//验证是否关注
			int count = ursServcie.ursColletUrsCount(params);
			if(count>0){
				return new BaseResponse(ResponseCode.FAILURE,"亲，您已关注该用户啦！");
			}
			
			params.put("strUid", Integer.parseInt(uid));
			params.put("endUid", request.getCuid());
			//插入关注信息
			int flag=ursServcie.ursCollectUrsInsert(params);
			if (flag != 1) {
				return new BaseResponse(ResponseCode.FAILURE, "插入关注用户信息失败");
			}
			
			//更新用户关注数
			Map<Object, Object> map = new HashMap<>();
			map.put("uid", uid);
			map.put("concernNums", 1);
			Integer viewResult = liveUserDao.modifyLiverByUid(map);
			
			//更新被关注用户的被关注数
			map.clear();
			map.put("uid", request.getCuid());
			map.put("concernedNums", 1);
			Integer anchorResult = liveUserDao.modifyLiverByUid(map);
			
			if (viewResult != 1 || anchorResult != 1) {
				return new BaseResponse(ResponseCode.FAILURE, "更新关注用户信息失败");
			}
			
			return new BaseResponse(ResponseCode.SUCCESS, "成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
	}
	
	
		
}
