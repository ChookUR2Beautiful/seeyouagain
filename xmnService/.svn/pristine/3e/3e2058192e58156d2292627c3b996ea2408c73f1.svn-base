package com.xmniao.xmn.core.api.controller.urs;

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
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
*      
* 类名称：UrsCollectUrsCancelApi   
* 类描述：   用户取消关注
* 创建人：xiaoxiong   
* 创建时间：2016年11月17日 下午7:12:41     
*
 */
@Controller
@RequestMapping("urs/collect/urs")
public class UrsCollectUrsCancelApi implements BaseVControlInf{
	private final Logger log = Logger.getLogger(UrsCollectSellerCancelApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private UrsService ursService;
	
	@Autowired
	private LiveUserService liveUserService;
	
	@Autowired
	private LiveUserDao liveUserDao;

	@ResponseBody
	@RequestMapping("/cancel")
	public Object cancle(UrsCollectUrsRequest request){
		log.info("SelleridRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题:"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
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
		UrsCollectUrsRequest request = (UrsCollectUrsRequest) object;
		
		String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
		if(uid.equals("")||uid.equals("null")){
			return new BaseResponse(ResponseCode.DATAERR, "token错误或已过期,请重新登入！");
		}
		try {
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
			
			//取消关注
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("liverStrId", liveInfo.getId());
			paramMap.put("liverEndId", cliveInfo.getId());
			
			//查询是否关注过此用户
			Integer focusAnchorResult = liveUserDao.queryFocusAnchor(paramMap);
			if (focusAnchorResult == 0) {
				return new BaseResponse(ResponseCode.FAILURE, "你未关注此用户");
			}
			
			int count = ursService.deleteLiveFocus(liveInfo.getId(),cliveInfo.getId());
			if (count != 1) {
				return new BaseResponse(ResponseCode.FAILURE, "取消关注失败");
			}
			
			//更新用户关注数
			Map<Object, Object> map = new HashMap<>();
			map.put("uid", uid);
			map.put("concernNums", -1);
			Integer viewResult = liveUserDao.modifyLiverByUid(map);
			
			//更新用户的被关注数
			map.clear();
			map.put("uid", request.getCuid());
			map.put("concernedNums", -1);
			Integer anchorResult = liveUserDao.modifyLiverByUid(map);
			
			if (viewResult != 1 || anchorResult != 1) {
				return new BaseResponse(ResponseCode.FAILURE, "更新关注信息失败");
			}
			
			return new BaseResponse(ResponseCode.SUCCESS, "成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.SUCCESS, "错误");
	}
}
