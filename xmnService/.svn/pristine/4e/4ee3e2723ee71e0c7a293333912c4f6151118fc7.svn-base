package com.xmniao.xmn.core.personal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.personal.ReceivingAddressListApi;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SendCodeRequest;
import com.xmniao.xmn.core.common.request.TypeRequest;
import com.xmniao.xmn.core.common.request.integral.PageTypeRequest;
import com.xmniao.xmn.core.common.request.live.CheckRoomPwdRequest;
import com.xmniao.xmn.core.common.request.personal.*;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.service.PersonalCenterService;
import com.xmniao.xmn.core.thrift.*;
import com.xmniao.xmn.core.util.*;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.service.SendCodeService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Service
public class SwitchSettingService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SwitchSettingService.class);

	@Autowired
	private PropertiesUtil propertiesUtil;

	/**
	 * 获取开关(实体礼物榜)
	 * @param request
	 * @return
	 */
	public Object switchSettingProperties(BaseRequest request) {
		try {
			Map<Object, Object> switchMap = loadSwitchSettingProperties();
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取开关成功");
			response.setResponse(switchMap);
			return response;
		} catch (Exception e) {
			log.warn("获取开关失败", e);
			return new BaseResponse(ResponseCode.FAILURE, "获取开关失败");
		}
	}

	private Map<Object, Object> loadSwitchSettingProperties() {
		Map<Object, Object> switchMap = new HashMap<Object, Object>();
		try {
			String jsonStr = propertiesUtil.getValue("switchSetting", "conf_common.properties");
			JSONArray jsonArr = JSON.parseArray(jsonStr);
			if (jsonArr != null) {
				for (Object arr : jsonArr) {
					Map<String, Object> obj = (Map<String, Object>) arr;
					String key = (String) obj.get("key");
					Integer value = (Integer) obj.get("value");
					switchMap.put(key, value);
				}
			}
		} catch (IOException e) {
			log.warn("解析配置失败：switchSetting", e);
		}
		return switchMap;
	}

}
