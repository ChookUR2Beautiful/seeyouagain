package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
/**
 * @项目名称：XmnWeb
 * 
 * @类名：TTopicComment.java
 * 
 * @类描述：用户对用户话题的评论
 * 
 * @创建人：yang'xu
 * 
 * @创建时间：2014-12-24 11：03：21
 *
 * @Copyright © 广东寻蜜鸟网络科技有限公司
 * 
 */
public class TTopicComment extends BaseEntity {

	
	private static final long serialVersionUID = 6059467454474938831L;
	
	private Integer id;			//评论id
	private Integer topicId;	//话题Id
	private String 	content;	//评论内容
	private Integer userId;		//评论人Id
	private String  nname;		//评论人昵称
	private Integer type;		//是否系统回复   --0 不是  1是
	private Date	time;		//评论时间
	private Integer status;		//评论状态  --1显示  0不显示
	private Integer pid;		//评论的父ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "TTopicComment [id=" + id + ", topicId=" + topicId
				+ ", content=" + content + ", userId=" + userId + ", nname="
				+ nname + ", type=" + type + ", time=" + time + ", status="
				+ status + ", pid=" + pid + "]";
	}
	
	
	
}
