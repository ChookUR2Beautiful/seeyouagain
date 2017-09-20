package com.xmniao.xmn.core.http.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.HttpUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StringUtils;

/**
 * 推送APP消息接口.
 * 
 * @author Zhiwen Zhang
 * 
 */
public class AppMessageUtil {

	private static Logger logger = LoggerFactory
			.getLogger(AppMessageUtil.class);
	/** 添加推送消息接口 */
	private static final String ADD_PUSH_MESSAGE = PropertiesUtil
			.readValue("http.msg.url") + "/push/addMsg.html";

	/**
	 * 添加推送到APP账户消息
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月25日 下午6:15:02
	 * @param url
	 * @param param
	 * @return
	 * @throws ApplicationException
	 */
	public static JSONObject pushMessageToApp(Map<String, String> param)
			throws ApplicationException {
		logger.info("发出的请求：" + ADD_PUSH_MESSAGE + "?p=" + param);
		JSONObject res = null;
		try {
			if (isUserIdExist(param, "uid")) {
				logger.info("传入的用户编号存在,发送消息->");
				res = HttpUtil.getInstance().postForObject(ADD_PUSH_MESSAGE,
						param);
			} else {
				logger.info("因为传入的用户编号不存在,所以不发送消息。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("消息推送异常");
		}

		return res;
	}

	/**
	 * 判断发送用户是否存在.
	 * 
	 * @param param
	 * @param uid
	 * @return
	 */
	private static boolean isUserIdExist(Map<String, String> param, String uid) {
		return param.containsKey(uid)
				&& StringUtils.hasLength((String) param.get(uid));
	}
}
