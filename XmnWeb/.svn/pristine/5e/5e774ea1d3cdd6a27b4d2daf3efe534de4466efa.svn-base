/**   
 * 文件名：TPraiseRecord.java   
 *    
 * 日期：2014年12月8日18时22分21秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TPostComment
 * 
 * 类描述：圈子点赞记录表
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TPraiseRecord extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6658060757089877247L;

	private Integer id;// 记录ID
	private Integer tid;// 帖子ID
	private Integer uid;// 用户ID
	private String nname;// 用户昵称
	private String avatar;// 用户头像
	private Date sdate;// 点赞时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	@Override
	public String toString() {
		return "TPraiseRecord [id=" + id + ", tid=" + tid + ", uid=" + uid
				+ ", nname=" + nname + ", avatar=" + avatar + ", sdate="
				+ sdate + "]";
	}
	
	
}
