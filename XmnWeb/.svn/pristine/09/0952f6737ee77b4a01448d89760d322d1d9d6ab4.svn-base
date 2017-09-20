/**   
 * 文件名：TSeller.java   
 *    
 * 日期：2014年11月11日15时22分21秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSellerSubsidy
 * 
 * 类描述：商家补贴
 * 
 * 创建人： cao'yingde
 * 
 * 创建时间：2015年06月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSellerSubsidy extends BaseEntity {

	private static final long serialVersionUID = -3422913519345379987L;

	private Integer idsubsidy;// ID
	private Integer sellerid;// 商家编号
	private String sellername;// 商家名称
	private String province;// 省编号
	private String city;// 市编号
	private String area;// 区域编号
	private String phoneid;// 服务员帐号（手机号）
	private Integer ordernum;// 满足条件订单数
	private BigDecimal subsidyamount;// 应发店员补贴(元）
	private String creator;// 创建人
	private Date datecreated;// 创建时间
	private String updator;// 修改人
	private Date dateupdated;// 修改时间
	private Integer issuestatus;// 发放状态：0 未发放，1 已发放。
	private Date dateissue;// 发放时间
	private String areatext;//区域组装
	public Integer getIdsubsidy() {
		return idsubsidy;
	}

	public void setIdsubsidy(Integer idsubsidy) {
		this.idsubsidy = idsubsidy;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public BigDecimal getSubsidyamount() {
		return subsidyamount;
	}

	public void setSubsidyamount(BigDecimal subsidyamount) {
		this.subsidyamount = subsidyamount;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	public Integer getIssuestatus() {
		return issuestatus;
	}

	public void setIssuestatus(Integer issuestatus) {
		this.issuestatus = issuestatus;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateissue() {
		return dateissue;
	}

	public void setDateissue(Date dateissue) {
		this.dateissue = dateissue;
	}

	
	
	public String getAreatext() {
			if(StringUtils.hasLength(province)){
				province = AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(province));
			}
			if(StringUtils.hasLength(city)){
				city = AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(city));
			}
			if(StringUtils.hasLength(area)){
				area = AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(area));
			}
			areatext = province+"-"+city+"-"+area;
		return areatext;
	}

	public void setAreatext(String areatext) {
		this.areatext = areatext;
	}

	@Override
	public String toString() {
		return "TSellerSubsidy [idsubsidy=" + idsubsidy + ", sellerid="
				+ sellerid + ", sellername=" + sellername + ", province="
				+ province + ", city=" + city + ", area=" + area + ", phoneid="
				+ phoneid + ", ordernum=" + ordernum + ", subsidyamount="
				+ subsidyamount + ", creator=" + creator + ", datecreated="
				+ datecreated + ", updator=" + updator + ", dateupdated="
				+ dateupdated + ", issuestatus=" + issuestatus + ", dateissue="
				+ dateissue + "]";
	}
}
