package com.xmniao.xmn.core.util.logRecord;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

import com.xmniao.xmn.core.system_settings.entity.TLog;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.StringUtils;

public class AccessUserRecord implements LogRecord {


	@Override
	public void logRecord(LogRecordElement logRecordElement,HttpServletRequest request, HttpServletResponse response,HandlerMethod handlerMethod) {
		TUser u = ResultUtil.getCurrentUser(request);
		TLog log = logRecordElement.getLog();
		log.setLogDate(new Date());
		log.setUserId(u.getUserId());
		log.setLogIp(StringUtils.getIpAddr(request));
		log.setLogState(1);
		
	}

}
