package com.xmniao.xmn.core.util.logRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.util.ResultUtil;

public class AccessDescriptionRecord implements LogRecord {

	@Override
	public void logRecord(LogRecordElement logRecordElement,
			HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod) {
			TUser u = ResultUtil.getCurrentUser(request);
			/*String.format("账号【%s】的用户操作【 %s  >> %s 】功能", u.getUsername(),logRecordElement.getModuleName(),logRecordElement.getMethodName());
			sb.append("账号【").append(u.getUsername());
			sb.append("】的用户操作【 ");
			sb.append(logRecordElement.getModuleName());
			sb.append(" >> ");
			sb.append(logRecordElement.getMethodName());
			sb.append("】功能");*/
			String note = String.format("账号【%s】的用户操作【 %s >> %s 】功能", u.getUsername(),logRecordElement.getModuleName(),logRecordElement.getMethodName());
			logRecordElement.getLog().setLogNote(note);
	}

}
