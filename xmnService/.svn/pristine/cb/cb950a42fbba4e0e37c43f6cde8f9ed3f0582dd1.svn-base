package com.xmniao.xmn.core.api.controller.xmer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;

/**
 * 
* 项目名称：xmnService   
* 类名称：XmkShareApi   
* 类描述：分享链接分享接口   
* 创建人：liuzhihao   
* 创建时间：2016年7月18日 上午10:52:16   
* @version    
*
 */
@Controller
public class XmkShareApi implements BaseVControlInf{
	
	//日志
	private final Logger log = Logger.getLogger(XmkShareApi.class);
	
	//注入验证
	@Autowired
	private Validator validator;
	
	//注入token
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private String localDomain;
	
	@RequestMapping(value="xmkShare",method={RequestMethod.POST,RequestMethod.GET},produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object xmkShare(BaseRequest baseRequest){
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(baseRequest.getApiversion(),baseRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		String path = localDomain + "/img/shareimg.jpg";
		Map<Object,Object> result = new HashMap<Object,Object>();
		BaseRequest request = (BaseRequest) object;
		if(StringUtils.isEmpty(request.getSessiontoken())){
			return new BaseResponse(ResponseCode.TOKENERR,"无效token，请重新登录");
		}
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()).toString();
		if(StringUtils.isEmpty(uid)){
			return new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录");
		}
		result.put("sharetitle", "我用寻蜜鸟，光省钱还不够，寻蜜客教你赚大钱！");//分享标题
		result.put("sharetext", "我用寻蜜鸟，光省钱还不够，寻蜜客教你赚大钱！加入寻蜜客，从此轻松赚钱！请点击链接:" + localDomain + "/xmkintro?uid="+uid);//分享内容
		result.put("sharetext2", "加入寻蜜客，从此轻松赚钱");//分享内容
		result.put("shareimg", path);//分享logo
		String shareurl = localDomain + "/xmkintro?uid="+uid;//分享链接
		
		//应客户端要求 拼接链接
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("&").append("sharetitle=").append("我用寻蜜鸟，光省钱还不够，寻蜜客教你赚大钱！")
		.append("&").append("sharetext=").append("我用寻蜜鸟，光省钱还不够，寻蜜客教你赚大钱！加入寻蜜客，从此轻松赚钱！请点击链接:" + localDomain + "/xmkintro?uid="+uid)
		.append("&").append("sharetext2=").append("加入寻蜜客，从此轻松赚钱")
		.append("&").append("shareimg=").append("我用寻蜜鸟，光省钱还不够，寻蜜客教你赚大钱！")
		.append("&").append("shareimg=").append(path);
		shareurl= shareurl+sBuffer.toString();
			
		result.put("", path);//分享logo
		result.put("shareurl", shareurl);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
		response.setResponse(result);
		return response;
	}

}
