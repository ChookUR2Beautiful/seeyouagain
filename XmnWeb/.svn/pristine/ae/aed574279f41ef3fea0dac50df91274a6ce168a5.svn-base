/**   
 * 文件名：TLog.java   
 *    
 * 日期：2014年11月17日17时04分29秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.system_settings.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TLog
 * 
 * 类描述：操作日志
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月17日17时04分29秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLog extends BaseEntity {

	private static final long serialVersionUID = 6731963059669865983L;
	private Long logId;// ID
	private Long userId;// ID
	private String logIp;// IP
	private String logType="系统日志";// 日志类型
	private Integer logState;// 0=失败
	private String logParame;// 参数
	private Date logDate;// 操作时间
	private String logNote;// 备注
	private String logRemark;//操作说明
	
	private String username;// 用户名
	private Date logDateStart;//临时字段（搜索）
	private Date logDateEnd;//临时字段（搜索）
	
	private String errorInfo;
	
	/**
	 * 创建一个新的实例 TLog.
	 * 
	 */
	public TLog() {
		super();
	}

	public TLog(Long logId) {
		this.logId = logId;
	}

	/**
	 * logId
	 * 
	 * @return the logId
	 */
	public Long getLogId() {
		return logId;
	}

	/**
	 * @param logId
	 *            the logId to set
	 */
	public void setLogId(Long logId) {
		this.logId = logId;
	}

	/**
	 * userId
	 * 
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * logIp
	 * 
	 * @return the logIp
	 */
	public String getLogIp() {
		return logIp;
	}

	/**
	 * @param logIp
	 *            the logIp to set
	 */
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	/**
	 * logType
	 * 
	 * @return the logType
	 */
	public String getLogType() {
		return logType;
	}

	/**
	 * @param logType
	 *            the logType to set
	 */
	public void setLogType(String logType) {
		this.logType = logType;
	}

	/**
	 * logState
	 * 
	 * @return the logState
	 */
	public Integer getLogState() {
		return logState;
	}

	/**
	 * @param logState
	 *            the logState to set
	 */
	public void setLogState(Integer logState) {
		this.logState = logState;
	}

	/**
	 * logParame
	 * 
	 * @return the logParame
	 */
	public String getLogParame() {
		return logParame;
	}

	/**
	 * @param logParame
	 *            the logParame to set
	 */
	public void setLogParame(String logParame) {
		this.logParame = logParame;
	}

	/**
	 * logDate
	 * 
	 * @return the logDate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLogDate() {
		return logDate;
	}

	/**
	 * @param logDate
	 *            the logDate to set
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	/**
	 * logNote
	 * 
	 * @return the logNote
	 */
	public String getLogNote() {
		return logNote;
	}

	/**
	 * @param logNote
	 *            the logNote to set
	 */
	public void setLogNote(String logNote) {
		this.logNote = logNote;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getLogDateStart() {
		return logDateStart;
	}

	public void setLogDateStart(Date logDateStart) {
		this.logDateStart = logDateStart;
	}

	public Date getLogDateEnd() {
		return logDateEnd;
	}

	public void setLogDateEnd(Date logDateEnd) {
		this.logDateEnd = logDateEnd;
	}
	


	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	@Override
	public String toString() {
		return "TLog [logId=" + logId + ", userId=" + userId + ", logIp="
				+ logIp + ", logType=" + logType + ", logState=" + logState
				+ ", logParame=" + logParame + ", logDate=" + logDate
				+ ", logNote=" + logNote + ", logRemark=" + logRemark
				+ ", username=" + username + ", logDateStart=" + logDateStart
				+ ", logDateEnd=" + logDateEnd + "]";
	}

	/*@Override
	public String toString() {
		return "TLog [logId=" + logId + ", userId=" + userId + ", logIp=" + logIp + ", logType=" + logType + ", logState=" + logState + ", logParame=" + logParame + ", logDate=" + logDate + ", logNote=" + logNote + ", ]";
	}*/
}
