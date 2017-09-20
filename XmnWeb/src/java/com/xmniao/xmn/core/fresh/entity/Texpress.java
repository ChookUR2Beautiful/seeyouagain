package com.xmniao.xmn.core.fresh.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 *@ClassName:Texpress
 *@Description:快递方式实体
 *@author hls
 *@date:2016年6月27日上午10:27:13
 */
public class Texpress extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3637231697381860281L;
	
	private Integer id;//主键ID
	private String expressName;//快递名称
	private String expressValue;//快递参数
	private Date rdate;//创建时间
	private Date udate;//更新时间
	private Integer dstatus;//数据状态 默认0 0正常 1已删除
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public String getExpressValue() {
		return expressValue;
	}
	public void setExpressValue(String expressValue) {
		this.expressValue = expressValue;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public Integer getDstatus() {
		return dstatus;
	}
	public void setDstatus(Integer dstatus) {
		this.dstatus = dstatus;
	}
}
