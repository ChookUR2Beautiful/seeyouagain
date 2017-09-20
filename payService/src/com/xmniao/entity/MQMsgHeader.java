package com.xmniao.entity;

import java.util.List;

public class MQMsgHeader {
	/* 主题  标签*/
	private String topic;
	private String tags;
	
	public MQMsgHeader() {
		super();
	}

	

	public MQMsgHeader(String topic, String tags) {
		super();
		this.topic = topic;
		this.tags = tags;
	}



	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


	@Override
	public String toString() {
		return "MQMsgHeader [topic=" + topic + ", tags=" + tags + "]";
	}
	
	
}
