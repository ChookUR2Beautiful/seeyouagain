package com.xmniao.xmn.core.api.controller.live;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.ModifyTlsUserInfoRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.UrsInfo;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ModifyTlsInfoApi   
* 类描述：   调用tls的REST API,设置用户资料
* 创建人：yezhiyong   
* 创建时间：2016年8月4日 上午10:23:01   
* @version    
*
 */
@Controller
@RequestMapping("/live/set")
public class ModifyTlsUserInfoApi {
	
	/**
	 * 日志
	 */
	private static final Logger log = LoggerFactory.getLogger(ModifyTlsUserInfoApi.class);
	
	/**
	 * 注入文件地址
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入ursInfoDao
	 */
	@Autowired
	private UrsInfoDao ursInfoDao;
	
	/**
	 * 注入stringRedisTemplate
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 注入ursDao
	 */
	@Autowired
	private UrsDao ursDao;
	
	/**
	 * 
	* @Title: getTlsSig
	* @Description: 调用tls的REST API,设置用户资料
	* @return Object    返回类型
	* @author
	* @throws
	 */
	@RequestMapping(value="/modifyTlsUserInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object modifyTlsUserInfo(ModifyTlsUserInfoRequest modifyTlsUserInfoRequest){
		//验证参数
		log.info("tlsSigRequest data:"+modifyTlsUserInfoRequest.toString());
		List<ConstraintViolation> result = validator.validate(modifyTlsUserInfoRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		try {
			//上传腾讯云资料
			Map<Object, Object> paramMap = new HashMap<>();
			//应用id
			paramMap.put("sdkAppid", propertiesUtil.getValue("SdkAppid", "conf_live.properties"));
			//管理员账号
			paramMap.put("identifier", propertiesUtil.getValue("identifier", "conf_live.properties"));
			
			//从redis中,获取管理员签名
			String adminSig = stringRedisTemplate.opsForValue().get("adminSig");
			
			String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			String identifier = propertiesUtil.getValue("identifier", "conf_live.properties");
			
			if (adminSig == null) {
				//调用tls,获取管理员tls的sig
				adminSig = TLSUtil.getTLSSig(sdkAppid, identifier);
				stringRedisTemplate.opsForValue().set("adminSig", adminSig);
				stringRedisTemplate.expire("adminSig", 180, TimeUnit.DAYS);
			}
			
			paramMap.put("tlsSig", adminSig);
			//用户账号
			paramMap.put("account", modifyTlsUserInfoRequest.getAccount());
			//昵称
			paramMap.put("nickName", modifyTlsUserInfoRequest.getNickName());
			//头像url
			paramMap.put("image", fileUrl + modifyTlsUserInfoRequest.getImage());
			//个性签名
			paramMap.put("selfSignature", modifyTlsUserInfoRequest.getSelfSignature());
			
			//上传腾讯云资料
			boolean setResult = TLSUtil.setTlsUserInfo(paramMap);
			
			if (!setResult) {
				return new BaseResponse(ResponseCode.TLS_SET_ERROR, "上传腾讯云资料失败");
			}
			
			return new BaseResponse(ResponseCode.SUCCESS, "上传腾讯用户资料成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.TLS_SET_ERROR, "上传腾讯云资料失败");
		}
		
	}
	
	/**
	 * 
	* @Title: getTlsSig
	* @Description: 调用tls的REST API,设置用户资料
	* @return Object    返回类型
	* @author
	* @throws
	 */
	@RequestMapping(value="/modifyTlsUserImage",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object modifyTlsUserImage(BaseRequest baseRequest){
		//验证参数
		log.info("baseRequest data:"+baseRequest.toString());
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//查询用户信息
		Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
		UrsInfo ursInfo = ursInfoDao.queryUrsInfoByUid(Integer.parseInt(uid));
		
		//上传直播用户腾讯云头像信息
		try {
			//上传腾讯云资料
			Map<Object, Object> paramMap = new HashMap<>();
			//应用id
			paramMap.put("sdkAppid", propertiesUtil.getValue("SdkAppid", "conf_live.properties"));
			//管理员账号
			paramMap.put("identifier", propertiesUtil.getValue("identifier", "conf_live.properties"));
			
			//从redis中,获取管理员签名
			String adminSig = stringRedisTemplate.opsForValue().get("adminSig");
			
			String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			String identifier = propertiesUtil.getValue("identifier", "conf_live.properties");
			
			if (adminSig == null) {
				//调用tls,获取管理员tls的sig
				adminSig = TLSUtil.getTLSSig(sdkAppid, identifier);
				stringRedisTemplate.opsForValue().set("adminSig", adminSig);
				stringRedisTemplate.expire("adminSig", 180, TimeUnit.DAYS);
			}
			
			paramMap.put("tlsSig", adminSig);
			//用户账号
			paramMap.put("account", urs.getUname());
			//头像url
			paramMap.put("image", fileUrl + ursInfo.getAvatar()==null?"":ursInfo.getAvatar());
			
			//上传腾讯云资料
			boolean setResult = TLSUtil.setTlsUserInfo(paramMap);
			
			log.info("开始上传直播用户腾讯云头像信息:" + ursInfo.getAvatar() );
			if (!setResult) {
				return new BaseResponse(ResponseCode.TLS_SET_ERROR, "上传直播用户腾讯云头像信息失败");
			}
			log.info("上传直播用户腾讯云头像信息成功:" + ursInfo.getAvatar() );
			
			return new BaseResponse(ResponseCode.SUCCESS, "上传直播用户腾讯云头像信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.TLS_SET_ERROR, "上传直播用户腾讯云头像信息失败");
		}
		
	}

}
