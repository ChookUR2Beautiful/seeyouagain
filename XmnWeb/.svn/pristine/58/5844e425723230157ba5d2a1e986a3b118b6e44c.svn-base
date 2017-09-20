/**   
 * 文件名：TCommentPic.java   
 *    
 * 日期：2014年11月11日16时05分06秒  
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
 * 类名称：TCommentPic
 * 
 * 类描述：评论图片
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日16时05分06秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TCommentPic extends BaseEntity  {
	
	private static final long serialVersionUID = -1289600401639283179L;
	private Integer id;// 图片ID
	private Integer cid;// 评论ID
	private String picname;// 图片名称
	private String picurl;// 图片URL
	private String picsurl;// 图片缩略图URL
	private Integer pid;// 图片类型
	private Date sdate;// 上传时间
	
	
	//新增虚拟字段
	private Date sdateStart;//上传时间查询条件
	private Date sdateEnd;//上传时间查询条件
	
	/**   
	 * 创建一个新的实例 TCommentPic.   
	 *      
	 */
	public TCommentPic() {
		super();
	}
	
	public TCommentPic(Integer id) {
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
	 * picname
	 * 
	 * @return the picname
	 */
	public String getPicname() {
		return picname;
	}

	/**
	 * @param picname
	 *            the picname to set
	 */
	public void setPicname(String picname) {
		this.picname = picname;
	}
	
	/**
	 * picurl
	 * 
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl
	 *            the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	/**
	 * picsurl
	 * 
	 * @return the picsurl
	 */
	public String getPicsurl() {
		return picsurl;
	}

	/**
	 * @param picsurl
	 *            the picsurl to set
	 */
	public void setPicsurl(String picsurl) {
		this.picsurl = picsurl;
	}
	
	/**
	 * pid
	 * 
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
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
		return "TCommentPic [id=" + id + ", cid=" + cid + ", picname=" + picname + ", picurl=" + picurl + ", picsurl=" + picsurl + ", pid=" + pid + ", sdate=" + sdate + ", ]";
	}
}
