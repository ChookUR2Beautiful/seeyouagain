package com.xmniao.xmn.core.api.controller.xmer;

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
import com.xmniao.xmn.core.common.request.UidRequest;
import com.xmniao.xmn.core.xmer.service.FriendsInfoService;

/**   
 * 项目名称：xmnService   
 * 类名称：FriendInfoApi   
 * 类描述：寻蜜客我的伙伴(陌生人)主页详情
 * 创建人：zhengyaowen
 * 创建时间：2016年5月23日 上午10:40:07   
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version     
 */
@Controller
public class FriendInfoApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(FriendInfoApi.class);
	
	/**
	 * 注入service
	 */
	@Autowired
	private FriendsInfoService friendsInfoService;
	
	/**
	 * 数据验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="friendInfo", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object friendInfo(UidRequest uidRequest){
		
		//验证请求数据
		List<ConstraintViolation> ret = validator.validate(uidRequest);
		if(ret!=null&&ret.size()>0){
			log.info("提交数据有问题:" + ret.toString());
			return new BaseResponse(ResponseCode.DATAERR,ret.get(0).getMessage());
		}
		
		return versionControl(uidRequest.getApiversion(), uidRequest);
	}
	
	/**
	 * 控制版本
	 * */
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1 :
				return versionQueryFriendInfo(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确，请重新下载客户端");
		}
	}
	
	public Object versionQueryFriendInfo(Object obj){
		UidRequest uidRequest = (UidRequest)obj;
		return friendsInfoService.queryFriendInfo(uidRequest);
		
	}
}
