/**    
 * 文件名：MXmnTrade.java    
 *    
 * 版本信息：    
 * 日期：2016年11月30日    
 * Copyright 足下 Corporation 2016     
 * 版权所有    
 *    
 */
package com.xmniao.domain.message;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：MXmnTrade
 * 
 * 类描述：  用户浏览/消费分类统计
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年11月30日 下午2:01:05 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class MXmnTrade {
	private Integer uid;
	private Integer xmntype;
	private Integer category;
	private Integer genre;
	private Integer operate;
	private String last_time;
	private Integer version;
	private Integer index;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getGenre() {
		return genre;
	}
	public void setGenre(Integer genre) {
		this.genre = genre;
	}
	public Integer getOperate() {
		return operate;
	}
	public void setOperate(Integer operate) {
		this.operate = operate;
	}
	public String getLast_time() {
		return last_time;
	}
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getXmntype() {
		return xmntype;
	}
	public void setXmntype(Integer xmntype) {
		this.xmntype = xmntype;
	}
	
}
