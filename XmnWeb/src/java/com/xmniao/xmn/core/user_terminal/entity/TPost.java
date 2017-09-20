/**   
 * 文件名：TPost.java   
 *    
 * 日期：2014年12月8日18时22分21秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Arrays;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：Tpost
 * 
 * 类描述：香蜜客圈子发帖表
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TPost extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2895798067665766577L;		
	private Integer tid;// 帖子ID
	private Integer uid;// 用户ID
	private String nname;// 用户昵称
	private String avatar;// 用户头像
	private String content;// 帖子内容
	private Date sdate;// 发帖时间
	private Integer count;// 被评论数
	private Integer number;//点赞数
	private Integer status;//0 正常 1 删除 2 被举报
	
	private Object[] array;// 批量跟新时id集合
	private String tids;// 前台id集合		
	private String reason;// 举报原因，淫秽色情;垃圾信息;泄露隐私;敏感信息 
	private Date lbsdateStart;//发帖时间(帖子列表搜索条件查找)
	private Date lbsdateEnd;//发帖时间(帖子列表搜索条件查找)	
	private Date jbsdateStart;//发帖时间(举报帖子搜索条件查找)
	private Date jbsdateEnd;//发帖时间(举报帖子搜索条件查找)
	private Date scsdateStart;//发帖时间(回收站搜索条件查找)
	private Date scsdateEnd;//发帖时间(回收站搜索条件查找)
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Object[] getArray() {
		return array;
	}
	public void setArray(Object[] array) {
		this.array = array;
	}
	public String getTids() {
		return tids;
	}
	public void setTids(String tids) {
		this.tids = tids;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLbsdateStart() {
		return lbsdateStart;
	}
	public void setLbsdateStart(Date lbsdateStart) {
		this.lbsdateStart = lbsdateStart;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLbsdateEnd() {
		return lbsdateEnd;
	}
	public void setLbsdateEnd(Date lbsdateEnd) {
		this.lbsdateEnd = lbsdateEnd;
	}
	public Date getJbsdateStart() {
		return jbsdateStart;
	}
	public void setJbsdateStart(Date jbsdateStart) {
		this.jbsdateStart = jbsdateStart;
	}
	public Date getJbsdateEnd() {
		return jbsdateEnd;
	}
	public void setJbsdateEnd(Date jbsdateEnd) {
		this.jbsdateEnd = jbsdateEnd;
	}
	public Date getScsdateStart() {
		return scsdateStart;
	}
	public void setScsdateStart(Date scsdateStart) {
		this.scsdateStart = scsdateStart;
	}
	public Date getScsdateEnd() {
		return scsdateEnd;
	}
	public void setScsdateEnd(Date scsdateEnd) {
		this.scsdateEnd = scsdateEnd;
	}
	
	@Override
	public String toString() {
		return "TPost [tid=" + tid + ", uid=" + uid + ", nname=" + nname
				+ ", avatar=" + avatar + ", content=" + content + ", sdate="
				+ sdate + ", count=" + count + ", number=" + number
				+ ", status=" + status + ", array=" + Arrays.toString(array)
				+ ", tids=" + tids + ", reason=" + reason + ", lbsdateStart="
				+ lbsdateStart + ", lbsdateEnd=" + lbsdateEnd
				+ ", jbsdateStart=" + jbsdateStart + ", jbsdateEnd="
				+ jbsdateEnd + "]";
	}
			
}





