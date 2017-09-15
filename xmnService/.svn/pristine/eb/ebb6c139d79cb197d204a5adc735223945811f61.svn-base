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

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.TopicalRankRequest;
import com.xmniao.xmn.core.xmer.service.FriendsInfoService;

/**
 * 
 * @项目名称：xmnService
 * @类名称：CustomerListApi
 * @类描述：查看我的伙伴信息列表口
 * @创建人： lifeng
 * @创建时间 2016年5月23日 上午9:05:56
 * @version
 */
@Controller
public class FriendsInfoListApi implements BaseVControlInf {

		/**
		 * 初始日志类
		 */
		private final Logger log = Logger.getLogger(FriendsInfoListApi.class);

		/**
		 * 注入Service
		 */
		@Autowired
		private FriendsInfoService friendsInfoService;
		
		/**
		 * 数据验证
		 */
		@Autowired
		private Validator validator;
		
		/**
		 * 注入SessionTokenService
		 */
		@Autowired
		private SessionTokenService sessionTokenService;

		@RequestMapping(value = "/partnerList", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
		@ResponseBody
		public Object friendsInfoList(TopicalRankRequest baseRequest) {
			log.info("friendsInfoList data:"+baseRequest.toString());
			//验证请求数据
			List<ConstraintViolation> ret = validator.validate(baseRequest);
			if(ret!=null&&ret.size()>0){
				log.info("提交数据有问题:" + ret.toString());
				return new BaseResponse(ResponseCode.DATAERR,ret.get(0).getMessage());
			}
			return versionControl(baseRequest.getApiversion(),baseRequest);
		}
		public Object versionOne(Object object){
			TopicalRankRequest baseRequest = (TopicalRankRequest)object;
			return friendsInfoService.queryfriendsInfoList(baseRequest);
		}
		@Override
		public Object versionControl(int v, Object object) {
			switch (v) {
			case 1:
				return versionOne(object);
			case 2:
				return versionOne(object);
			default:
				return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
			}
		}

}
