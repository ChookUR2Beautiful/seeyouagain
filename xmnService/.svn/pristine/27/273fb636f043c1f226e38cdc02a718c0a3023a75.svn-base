package com.xmniao.xmn.core.api.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.vstar.dao.VStarPlayerInfoDao;

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
public class XmniaoMatchRetestApi{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(XmniaoMatchRetestApi.class);
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private VStarPlayerInfoDao vStarPlayerInfoDao;
	
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@RequestMapping(value="/xmniao/match/retest",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLoadUrl(BaseRequest request){
		
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		
		//验证请求数据的合法性
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		MapResponse response =  null;
		try {
			Map<Object, Object> resMap = new HashMap<Object, Object>();
			
			//是否开启查询主播实名认证信息
			String retestSwitch = propertiesUtil.getValue("personalMatchRetestSwitch", "conf_live.properties");
			String retestremark = propertiesUtil.getValue("personalMatchRetestRemark", "conf_live.properties") ;
			
			if (retestSwitch.equals("1")) {
				//查询选手是否通过复赛
				int state = 0;
				Map<Object, Object> paramMap = new HashMap<Object, Object>();
				paramMap.put("uid", uid);
				state = vStarPlayerInfoDao.queryVStarRetestState(paramMap);
				if (state == 0) {
					resMap.put("retestSwitch", 0) ;
				}else {
					resMap.put("retestSwitch", 1) ;
				}
			}else {
				resMap.put("retestSwitch", 0) ;
			}
			resMap.put("retestremark", retestremark) ;
			response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
			response.setResponse(resMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "操作异常");
		}
		return response;
	}

}
