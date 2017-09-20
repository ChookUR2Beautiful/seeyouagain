package com.xmniao.xmn.core.util.logRecord;

import com.xmniao.xmn.core.system_settings.entity.TLog;

public class LogRecordElement {
	private TLog log;//日志对象
	private String moduleName;//访问模块描述
	private String methodName;//访问模块方法描述
	public LogRecordElement(TLog log,String moduleName,String methodName){
		this.log=log;
		this.moduleName=moduleName;
		this.methodName=methodName;
	}
	public TLog getLog() {
		return log;
	}
	public void setLog(TLog log) {
		this.log = log;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
