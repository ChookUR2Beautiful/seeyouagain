package com.xmniao.xmn.core.api.controller.live;

import java.util.HashMap;
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
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.service.TlsSendImService;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.CryptDecryptUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SendGroupMsgApi   
* 类描述：   发送直播间群组信息
* 创建人：yezhiyong   
* 创建时间：2016年10月31日 下午6:08:19   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class SendGroupMsgApi{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SendGroupMsgApi.class);
	
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
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 
	* @Title: queryAttentionList
	* @Description: 业务后台发送群组消息
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/web/sendGroupMsg" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryAttentionList(String data){
		String liveRecordId = "";
		String text = "";
		String phone = "";
		try {
			//AES 算法key
			String edKey = propertiesUtil.getValue("edKey", "conf_live.properties");
			String edIv = propertiesUtil.getValue("edIv", "conf_live.properties");
			//AES 算法解密
			String decrypt = CryptDecryptUtil.decrypt(data, Base64.getBase64(edKey), Base64.getBase64(edIv));
			//解析获取数据
			JSONObject jsonObj = JSONObject.parseObject(decrypt);
			
			//直播记录id
			liveRecordId = jsonObj.getString("liveRecordId");
			phone = jsonObj.getString("phone");
			text = jsonObj.getString("text");
			
			Map<Object, Object> map = new HashMap<>();
			map.put("id", Integer.parseInt(liveRecordId));
			
			//查询直播记录信息
			LiveRecordInfo liveRecord = anchorLiveRecordDao.queryAnchorLiveRecordById(map);
			if (liveRecord == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播记录信息,发送直播间群组信息失败");
			}
			
			//查询发送者的信息
			Map<Object, Object> liverMap = liveUserDao.queryAnchorByPhone(phone);
			if (liverMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此发送者用户信息");
			}
			
			log.info("业务后台发送群组消息start,phone=" + phone + ",liveRecordId=" + liveRecordId + ",text=" + text);
			//发送直播间群组消息
			tlsSendImService.sendGroupMsgByWeb(text,liverMap,liveRecord);
			log.info("业务后台发送群组消息成功,phone=" + phone + ",liveRecordId=" + liveRecordId + ",text=" + text);
			
			//响应
			return new BaseResponse(ResponseCode.SUCCESS, "发送直播间群组信息成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("发送直播间群组信息失败,错误信息如下:phone=" + phone + ",liveRecordId=" + liveRecordId + ",text=" + text + "," + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "发送直播间群组信息失败");
		}
	}
	
}
