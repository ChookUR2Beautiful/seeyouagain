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
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.service.TlsSendImService;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.CryptDecryptUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CloseLiveApi   
* 类描述：   结束直播(给业务后台关闭直播间)
* 创建人：yezhiyong   
* 创建时间：2016年10月26日 下午2:25:03   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class CloseLiveApi{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(CloseLiveApi.class);
	
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
	 * 注入tlsSendImService
	 */
	@Autowired 
	private TlsSendImService tlsSendImService;
	
	@RequestMapping(value = "/web/closeLive" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object closeLive(String data){
		try {
			//AES 算法key
			String edKey = propertiesUtil.getValue("edKey", "conf_live.properties");
			String edIv = propertiesUtil.getValue("edIv", "conf_live.properties");
			//AES 算法解密
			String decrypt = CryptDecryptUtil.decrypt(data, Base64.getBase64(edKey), Base64.getBase64(edIv));
			//解析获取数据
			JSONObject jsonObj = JSONObject.parseObject(decrypt);
			
			//直播记录id
			String liveRecordId = jsonObj.getString("liveRecordId");
			
			Map<Object, Object> map = new HashMap<>();
			map.put("id", Integer.parseInt(liveRecordId));
			
			//查询直播记录信息
			LiveRecordInfo liveRecord = anchorLiveRecordDao.queryAnchorLiveRecordById(map);
			if (liveRecord == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播记录信息,关闭直播间失败");
			}
			
			log.info("业务后台关闭直播间start");
			//同步主播鸟蛋,清理直播以及观众观看记录,发送IM消息关闭直播间
			tlsSendImService.synAnchorInfo(liveRecord);
			log.info("业务后台关闭直播间成功");
			return new BaseResponse(ResponseCode.SUCCESS, "关闭直接间成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "关闭直播间失败");
		}
	}
	
}
