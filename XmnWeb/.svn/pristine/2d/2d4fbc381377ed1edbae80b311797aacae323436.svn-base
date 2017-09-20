/**   
 * 文件名：TPostPic.java   
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
 * 类名称：TPostPic
 * 
 * 类描述：帖子图片表
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TPostPic extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2143646252834398703L;

	private Integer id;// 图片ID
	private Integer tid;// 帖子ID
	private String picname;// 图片名称
	private String picurl;// 图片URL
	private String picsurl;//图片缩略图URL
	private Date sdate;// 上传时间
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
	public String getPicname() {
		return picname;
	}
	public void setPicname(String picname) {
		this.picname = picname;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getPicsurl() {
		return picsurl;
	}
	public void setPicsurl(String picsurl) {
		this.picsurl = picsurl;
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
		return "TPostPic [id=" + id + ", tid=" + tid + ", picname=" + picname
				+ ", picurl=" + picurl + ", picsurl=" + picsurl + ", sdate="
				+ sdate + "]";
	}
	
	

}
