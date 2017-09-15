package com.xmniao.xmn.core.catehome.entity.mongo;

import java.io.Serializable;

/**
 * 
* @projectName: xmnService 
* @ClassName: XmnTrade    
* @Description:用户消费/浏览分类记录
* @author: liuzhihao   
* @date: 2016年11月28日 下午8:42:52
 */
@SuppressWarnings("serial")
public class XmnTrade implements Serializable{

	private Integer uid;
	
	private Integer category;//商铺一级分类ID
	
	private Integer genre;//商铺二级分类ID
	
	private Integer index;//计数值
	
	private Integer operate;//操作类型 1 浏览 2 消费
	
	private String last_time;//最新时间
	
	private Integer version;//版本控制

	private Integer xmntype;//用户类型 1 普通用户 2 主播
	
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
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

	
	
	public Integer getXmntype() {
		return xmntype;
	}

	public void setXmntype(Integer xmntype) {
		this.xmntype = xmntype;
	}

	@Override
	public String toString() {
		return "XmnTrade [uid=" + uid + ", category=" + category + ", genre=" + genre + ", index=" + index + ", operate=" + operate
			+ ", last_time=" + last_time + ", version=" + version + "]";
	}
	
}
