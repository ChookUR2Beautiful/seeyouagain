/**   
 * 文件名：TArea.java   
 *    
 * 日期：2014年11月12日17时37分19秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.common.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 *@ClassName:City
 *@Description:城市实体
 *@author hls
 *@date:2016年7月8日下午12:24:41
 */
public class City extends BaseEntity {

	private static final long serialVersionUID = -295247304772485916L;
	private Integer pid;// 区域父ID
	private Date sdate;// 创建时间
	private String ctitle;// 市
	private Integer pareaId;// 省编号
	private Integer careaId;//市编号
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public Integer getPareaId() {
		return pareaId;
	}
	public void setPareaId(Integer pareaId) {
		this.pareaId = pareaId;
	}
	public Integer getCareaId() {
		return careaId;
	}
	public void setCareaId(Integer careaId) {
		this.careaId = careaId;
	}
	@Override
	public String toString() {
		return "City [pid=" + pid + ", sdate=" + sdate + ", ctitle=" + ctitle
				+ ", pareaId=" + pareaId + ", careaId=" + careaId + "]";
	}
}
