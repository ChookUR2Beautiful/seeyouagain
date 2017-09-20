/**   
 * 文件名：TPostComment.java   
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
 * 类描述：帖子评论表
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TPostComment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4313761283568706699L;

	private Integer id;// 评论ID
	private Integer tid;// 帖子ID
	private String content;// 评论内容
	private Integer fid;// 评论父ID
	private Date sdate;// 评论时间
	private Integer uid;// 用户ID
	private String nname;// 用户昵称	
	private String avatar;// 用户头像
	private String remarks;// 备注(如:张三 回复 李四)
	private Integer status;// 是否显示 0不显示1显示
		
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TPostComment [id=" + id + ", tid=" + tid + ", content="
				+ content + ", fid=" + fid + ", sdate=" + sdate + ", uid="
				+ uid + ", nname=" + nname + ", avatar=" + avatar
				+ ", remarks=" + remarks + ", status=" + status + "]";
	}
	
	
}
