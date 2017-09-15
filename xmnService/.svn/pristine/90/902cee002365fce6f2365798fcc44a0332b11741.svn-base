package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;
import java.util.Set;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.UidRequest;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CKListApi   
* 类描述：   从redis中查询好友申请信息
* 创建人：xiaoxiong   
* 创建时间：2016年5月26日 上午9:33:49   
* @version    
*
 */
@Controller
public class CKListApi implements BaseVControlInf{
	
	@Autowired
	private SessionTokenService sessionTokenServcie;
	
	private final Logger log = Logger.getLogger(CKListApi.class);
	/**
	 * 注入service
	 */
	@Autowired
	private XmerInfoService xmerInfoService;

	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;

	@ResponseBody
	@RequestMapping(value="ckList")
	public Object CKlist(UidRequest uidRequest){
		//验证传入的数据合法性
		List<ConstraintViolation> result = validator.validate(uidRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题！");
		}
		return versionControl(uidRequest.getApiversion(), uidRequest);
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
		UidRequest uidRequest=(UidRequest)object;
		JSONArray jsonArray=new JSONArray();
		Set<String> set=sessionTokenServcie.getZSetForValue(Constant.FRIEND_APPLICANTS_KEY+uidRequest.getUid(),0,-1);			
		if(set!=null){
			jsonArray=JSONArray.parseArray(set.toString());
		}
		return jsonArray;
	}

}
