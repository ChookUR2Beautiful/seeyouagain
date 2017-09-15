package com.xmniao.xmn.core.api.controller.live;

import java.util.Map;

import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.service.TlsSendImService;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.CryptDecryptUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiveRadioApi   
* 类描述：   发送直播间系统广播信息
* 创建人：yezhiyong   
* 创建时间：2016年10月27日 下午4:26:35   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class LiveRadioApi{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveRadioApi.class);
	
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
	 * 注入anchorLiveRecordDao
	 */
	@Autowired 
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入TlsService
	 */
	@Autowired 
	private TlsSendImService tlsSendImService;
	
	@RequestMapping(value = "/web/liveRadio" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryAttentionList(String data){
		Integer radioId = null;
		try {
			//AES 算法key
			String edKey = propertiesUtil.getValue("edKey", "conf_live.properties");
			String edIv = propertiesUtil.getValue("edIv", "conf_live.properties");
			//AES 算法解密
			String decrypt = CryptDecryptUtil.decrypt(data, Base64.getBase64(edKey), Base64.getBase64(edIv));
			//解析获取数据
			JSONObject jsonObj = JSONObject.parseObject(decrypt);
			//广播id
			radioId = Integer.parseInt(jsonObj.getString("radioId"));
		
			//查询广播内容
			Map<Object, Object> radioMap = anchorLiveRecordDao.queryLiveBroadcastById(radioId);
			if (radioMap == null || radioMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此广播信息,请重试");
			}
			
			//响应
			return tlsSendImService.sendLiveRadio(radioMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("发送直播间广播信息失败,错误信息如下:radioId=" + radioId + "," + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "发送直播间广播信息失败");
		}
	}
	
}
