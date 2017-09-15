package com.xmniao.xmn.core.catehome.response;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @projectName: xmnService 
* @ClassName: SpecilTopicResponse    
* @Description:专题详情返回结果类   
* @author: liuzhihao   
* @date: 2017年2月17日 下午2:22:35
 */
@SuppressWarnings("serial")
public class SpecilTopicResponse implements Serializable{

	private Integer fid;//专题ID
	
	private String title;//专题标题
	
	private String description;//专题描述
	
	private String content;//专题内容
	
	private String videoUrl;//专题视频url
	
	private Integer topicType;//专题类型
	
	private List<String> topicImages;//专题banner图片
	
	private List<SpecilTopicSubInfoResponse> sssrs;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getTopicType() {
		return topicType;
	}

	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}

	public List<String> getTopicImages() {
		return topicImages;
	}

	public void setTopicImages(List<String> topicImages) {
		this.topicImages = topicImages;
	}

	public List<SpecilTopicSubInfoResponse> getSssrs() {
		return sssrs;
	}

	public void setSssrs(List<SpecilTopicSubInfoResponse> sssrs) {
		this.sssrs = sssrs;
	}

	@Override
	public String toString() {
		return "SpecilTopicResponse [fid=" + fid + ", title=" + title + ", description=" + description + ", content=" + content
			+ ", videoUrl=" + videoUrl + ", topicType=" + topicType + ", topicImages=" + topicImages + ", sssrs=" + sssrs + "]";
	}
	
}
