/**   
 * 文件名：TAppVersion.java   
 *    
 * 日期：2014年11月19日11时51分56秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.common.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TAppVersion
 * 
 * 类描述：版本
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时51分56秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TAppVersion extends BaseEntity {

	private static final long serialVersionUID = 2283732731347564545L;
	private Integer id;// 版本ID
	private Integer apptype;// 1=经销商版|2=商户版|3=用户版|4=商户版(演示)|5=用户版(演示)|6=其他
	private Integer vtype;// 1=Android|2=Ios|3=Wp|4=其他
	private String version;// 更新版本号|1.0.0----9.9.9
	private String inside;// 内部版本号
	private String url;// URL地址
	private String content;// 更新内容
	private Integer activeNo;// 激活数量
	private Integer status;// 1=已发布|2=停止更新
	private Date sdate;// 发布时间
	
	private Integer mustUpdate;//是否强制更新 0不需要 1必须更新
	
	private Date sdateTappVersionStarts;//临时字段（搜索用）
	private Date sdateTappVersionEnd;//临时字段（搜索用）

	/**
	 * 创建一个新的实例 TAppVersion.
	 * 
	 */
	public TAppVersion() {
		super();
	}

	public TAppVersion(Integer id) {
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
	 * apptype
	 * 
	 * @return the apptype
	 */
	public Integer getApptype() {
		return apptype;
	}

	/**
	 * @param apptype
	 *            the apptype to set
	 */
	public void setApptype(Integer apptype) {
		this.apptype = apptype;
	}

	/**
	 * vtype
	 * 
	 * @return the vtype
	 */
	public Integer getVtype() {
		return vtype;
	}

	/**
	 * @param vtype
	 *            the vtype to set
	 */
	public void setVtype(Integer vtype) {
		this.vtype = vtype;
	}

	/**
	 * version
	 * 
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * inside
	 * 
	 * @return the inside
	 */
	public String getInside() {
		return inside;
	}

	/**
	 * @param inside
	 *            the inside to set
	 */
	public void setInside(String inside) {
		this.inside = inside;
	}

	/**
	 * url
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * activeNo
	 * 
	 * @return the activeNo
	 */
	public Integer getActiveNo() {
		return activeNo;
	}

	/**
	 * @param activeNo
	 *            the activeNo to set
	 */
	public void setActiveNo(Integer activeNo) {
		this.activeNo = activeNo;
	}

	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	
	
	public Date getSdateTappVersionStarts() {
		return sdateTappVersionStarts;
	}

	public void setSdateTappVersionStarts(Date sdateTappVersionStarts) {
		this.sdateTappVersionStarts = sdateTappVersionStarts;
	}

	public Date getSdateTappVersionEnd() {
		return sdateTappVersionEnd;
	}

	public void setSdateTappVersionEnd(Date sdateTappVersionEnd) {
		this.sdateTappVersionEnd = sdateTappVersionEnd;
	}

	public Integer getMustUpdate() {
		return mustUpdate;
	}

	public void setMustUpdate(Integer mustUpdate) {
		this.mustUpdate = mustUpdate;
	}

	@Override
	public String toString() {
		return "TAppVersion [id=" + id + ", apptype=" + apptype + ", vtype="
				+ vtype + ", version=" + version + ", inside=" + inside
				+ ", url=" + url + ", content=" + content + ", activeNo="
				+ activeNo + ", status=" + status + ", sdate=" + sdate
				+ ", mustUpdate=" + mustUpdate + ", sdateTappVersionStarts="
				+ sdateTappVersionStarts + ", sdateTappVersionEnd="
				+ sdateTappVersionEnd + "]";
	}
}
