package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

public class TCommentLabel extends BaseEntity {
	private static final long serialVersionUID = 4257434968381693521L;
	public Integer sellerid;//商家id
	public String sellername;//商家名称
	public Integer id;//商家标签表ID
	public String labelname;//标签名称
	public Date sdate;//创建时间
	public String sdateStr;
	public Date udate;//更新时间
	public String udateStr;
	public List<String> labelNameList;//标签名称list
	public Integer znum;//点赞数
	
	public String istype;
	
	public String getIstype() {
		return istype;
	}
	public void setIstype(String istype) {
		this.istype = istype;
	}
	public Integer getZnum() {
		return znum;
	}
	public void setZnum(Integer znum) {
		this.znum = znum;
	}
	public List<String> getLabelNameList() {
		return labelNameList;
	}
	public void setLabelNameList(List<String> labelNameList) {
		this.labelNameList = labelNameList;
	}
	public String getLabelname() {
		return labelname;
	}
	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}
	public String getSdateStr() {
		if(null != sdate) return DateUtil.smartFormat(sdate);
		if(null != sdate) return "--";
		return sdateStr;
	}
	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}
	public String getUdateStr() {
		if(null != udate) return DateUtil.smartFormat(udate);
		if(null != udate) return "--";
		return udateStr;
	}
	public void setUdateStr(String udateStr) {
		this.udateStr = udateStr;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
	
}