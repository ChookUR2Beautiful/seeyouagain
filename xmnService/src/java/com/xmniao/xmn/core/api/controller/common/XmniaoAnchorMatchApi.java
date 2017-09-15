package com.xmniao.xmn.core.api.controller.common;

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
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AppStartAdvertisementRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AppLoadUrlApi   
* 类描述：   获取app客户端下载地址
* 创建人：yezhiyong   
* 创建时间：2016年6月16日 下午2:30:54   
* @version    
*
 */
@Controller
public class XmniaoAnchorMatchApi{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(XmniaoAnchorMatchApi.class);
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@RequestMapping(value="/xmniao/anchor/match",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLoadUrl(AppStartAdvertisementRequest appStartAdvertisementRequest){
		//验证请求数据的合法性
		List<ConstraintViolation> result = validator.validate(appStartAdvertisementRequest);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		MapResponse response =  null;
		try {
			Map<Object, Object> resMap = new HashMap<Object, Object>();
			resMap.put("matchUrl",  propertiesUtil.getValue("personalCompetitionEntryUrl", "conf_live.properties")) ;
			resMap.put("matchSwitch", propertiesUtil.getValue("personalCompetitionEntrySwitch", "conf_live.properties")) ;
			resMap.put("matchAction", propertiesUtil.getValue("personalCompetitionEntryAction", "conf_live.properties")) ;
			resMap.put("matchImage", propertiesUtil.getValue("personalCompetitionEntryImage", "conf_live.properties")) ;
			
			response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
			response.setResponse(resMap);
			
		} catch (Exception e) {
			return new MapResponse(ResponseCode.FAILURE, "操作异常");
		}
		return response;
	}

}
