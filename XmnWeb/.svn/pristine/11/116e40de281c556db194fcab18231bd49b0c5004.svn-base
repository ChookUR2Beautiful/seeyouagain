/**   
 * 文件名：TCommentReply.java   
 *    
 * 日期：2014年11月11日16时02分54秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TCommentReply
 * 
 * 类描述：评论回复
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日16时02分54秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TCommentReply extends BaseEntity  {
	
	private static final long serialVersionUID = 6866910537503923681L;
	private Integer id;// 回复ID
	private Integer cid;// 评论ID
	private String content;// 回复内容
	private Integer fid;// 父回复ID
	private Date sdate;// 回复时间
	private Integer uid;// 用户ID
	private String nname;// 用户昵称
	
	//新增虚拟字段
	private Date sdateStart;//回复时间查询条件
	private Date sdateEnd;//回复时间查询条件
	/**   
	 * 创建一个新的实例 TCommentReply.   
	 *      
	 */
	public TCommentReply() {
		super();
	}
	
	public TCommentReply(Integer id) {
		this.id = id;
	}

	
	/**
	 * id
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * cid
	 * 
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * @param cid
	 *            the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	/**
	 * content
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * fid
	 * 
	 * @return the fid
	 */
	public Integer getFid() {
		return fid;
	}

	/**
	 * @param fid
	 *            the fid to set
	 */
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	/**
	 * sdate
	 * 
	 * @return the sdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	/**
	 * @param sdate
	 *            the sdate to set
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	/**
	 * uid
	 * 
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	/**
	 * nname
	 * 
	 * @return the nname
	 */
	public String getNname() {
		return nname;
	}

	/**
	 * @param nname
	 *            the nname to set
	 */
	public void setNname(String nname) {
		this.nname = nname;
	}
	
	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}

	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TCommentReply [id=" + id + ", cid=" + cid + ", content=" + content + ", fid=" + fid + ", sdate=" + sdate + ", uid=" + uid + ", nname=" + nname + ", ]";
	}
}
