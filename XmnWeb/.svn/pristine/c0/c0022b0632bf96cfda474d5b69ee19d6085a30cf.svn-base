package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名：TTopic.java
 * 
 * 类介绍：寻蜜鸟话题实体类
 * 
 * 创建人：yang'xu
 * 
 * 创建时间：2014-12-23 10：28：36
 * 
 * Copyright © 广东寻蜜鸟网络科技有限公司
 *
 */
public class TTopic extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8022241276925351075L;
	private Integer id;				//主键id
	private String  content;		//话题内容
	private Date    time;			//添加话题的时间
	private Integer	status;			//话题状态  --0为不显示，1为显示
	private Integer	commentNum;		//评论数
	private Integer praiseNum;		//点赞数
	private Integer	order;			//排序
	private Integer userId;			//增加用户Id
	
	//拓展字段，方便展示评论数
	private Date edateStart;
	private Date edateEnd;
	//显示评论表里 统计的的评论数量
	private Long commInfo;
	
	public Long getCommInfo() {
		return commInfo;
	}
	public void setCommInfo(Long commInfo) {
		this.commInfo = commInfo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public Integer getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEdateStart() {
		return edateStart;
	}
	public void setEdateStart(Date edateStart) {
		this.edateStart = edateStart;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEdateEnd() {
		return edateEnd;
	}
	public void setEdateEnd(Date edateEnd) {
		this.edateEnd = edateEnd;
	}
	@Override
	public String toString() {
		return "TTopic [id=" + id + ", content=" + content + ", time=" + time
				+ ", status=" + status + ", commentNum=" + commentNum
				+ ", praiseNum=" + praiseNum + ", order=" + order + ", userId="
				+ userId + "]";
	}
	
	
}
