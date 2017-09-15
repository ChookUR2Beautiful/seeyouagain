package com.xmniao.xmn.core.catehome.entity.mongo;

import java.io.Serializable;

/**
 * 
* @projectName: xmnService 
* @ClassName: XmnSeller    
* @Description:用户消费/浏览商家记录   
* @author: liuzhihao   
* @date: 2016年11月28日 下午8:46:32
 */
@SuppressWarnings("serial")
public class XmnSeller implements Serializable{

	private Integer uid;//用户ID
	
	private Integer sellerid;//商铺ID
	
	private Integer index;//计数值
	
	private Integer operate;//操作 1 浏览 2 消费
	
	private String last_time;//最新时间
	
	private Integer version;//版本控制
	
	private Integer xmntype;//用户类型 1 普通用户 2 主播

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
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
	
}
