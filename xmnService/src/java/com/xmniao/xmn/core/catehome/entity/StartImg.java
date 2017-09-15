package com.xmniao.xmn.core.catehome.entity;

import java.util.Date;

/**
 * 启动app加载启动图
 * yhl
 * 2017-4-13 18:13:17
 * */
public class StartImg {
	
    private Integer id;
    
    private Integer type;
    
    private Integer atype;
    
    private String status;
    
    private String pic;

    private String remarks;

    private Date sdate;

    private Date edate;

    private Integer index;

    private String picLow;

    private String picMiddle;

    private String startUrl;

    private Integer provinceId;

    private Integer cityId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAtype() {
		return atype;
	}

	public void setAtype(Integer atype) {
		this.atype = atype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getPicLow() {
		return picLow;
	}

	public void setPicLow(String picLow) {
		this.picLow = picLow;
	}

	public String getPicMiddle() {
		return picMiddle;
	}

	public void setPicMiddle(String picMiddle) {
		this.picMiddle = picMiddle;
	}

	public String getStartUrl() {
		return startUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "StartImg [id=" + id + ", type=" + type + ", atype=" + atype
				+ ", status=" + status + ", pic=" + pic + ", remarks="
				+ remarks + ", sdate=" + sdate + ", edate=" + edate
				+ ", index=" + index + ", picLow=" + picLow + ", picMiddle="
				+ picMiddle + ", startUrl=" + startUrl + ", provinceId="
				+ provinceId + ", cityId=" + cityId + "]";
	}
    
    
    
}