/**   
 * 文件名：TPostReport.java   
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
 * 类名称：TPostReport
 * 
 * 类描述：帖子举报表
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TPostReport extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1874186683174453118L;
	
	private Integer id;// 举报ID
	private Integer tid;// 帖子ID
	private Integer uid;// 用户ID
	private String nname;// 用户昵称
	private String reason;// 举报原因，淫秽色情;垃圾信息;泄露隐私;敏感信息    
	private Date sdate;// 举报时间
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
		return "TPostReport [id=" + id + ", tid=" + tid + ", uid=" + uid
				+ ", nname=" + nname + ", reason=" + reason + ", sdate="
				+ sdate + "]";
	}

	
	
}
