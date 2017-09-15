package com.xmniao.xmn.core.common.request;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

public class PushRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9018197446915629873L;

	@NotNull(message="推送消息接受者不能为空")
	private Integer pushUid; //推送消息接受者
	
	@NotNull(message="消息title不能为空")
	private String title; //消息title
	
	@NotNull(message="消息内容不能为空")
	private String content; //消息内容
	
	@NotNull(message="推送时间不能为空")
	private String sendTime; //推送时间
	
	private String url; //跳转地址
	
	private String param; //附带参数
	
	private String appSource;//app来源

	public Integer getPushUid() {
		return pushUid;
	}

	public void setPushUid(Integer pushUid) {
		this.pushUid = pushUid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getAppSource() {
		return appSource;
	}

	public void setAppSource(String appSource) {
		this.appSource = appSource;
	}

	@Override
	public String toString() {
		return "PushRequest [pushUid=" + pushUid + ", title=" + title + ", content=" + content + ", sendTime="
				+ sendTime + ", url=" + url + ", param=" + param + ", appSource=" + appSource + "]";
	}
				
}
