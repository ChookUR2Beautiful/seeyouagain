package com.xmniao.xmn.core.util.logRecord;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

import com.xmniao.xmn.core.system_settings.entity.TLog;
import com.xmniao.xmn.core.util.listener.AccessRemarkRecordListener;

public class AccessRemarkRecord implements LogRecord {

	@Override
	public void logRecord(LogRecordElement logRecordElement,HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod) {
		Map<String, Object> map = AccessRemarkRecordListener.get();
		if(map!=null){
			AccessRemarkRecordListener.clean();
			Object actionRemark =map.get("actionRemark");
			Object actionResult =map.get("actionResult");
			Object errorInfo =map.get("errorInfo");
			if(actionRemark!=null&&actionResult!=null){
				TLog log = logRecordElement.getLog();
				log.setLogRemark((String)actionRemark);
				log.setLogState((Integer)actionResult);
				if(errorInfo!=null){
					log.setErrorInfo((String)errorInfo);
				}
			}
			
		}
		
		
	}


}
