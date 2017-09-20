/**   
 * 文件名：TArea.java   
 *    
 * 日期：2014年11月12日17时37分19秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.business_area.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessArea
 * 
 * 类描述：商务区域
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月12日17时37分19秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TstaffRanking extends BaseEntity {
        	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4432284029144088892L;
	
	private Integer staffid;//员工id
	private String fullname;// 员工姓名
	private String phoneid;// 手机号码
	private String sellername;//商家名称

	private String corporate;//所属公司
	private String legalperson;// 公司法人
	
	private Integer sellerSignCount;//已签约数
	private Integer sellerNoValidationCount;//未验证数
	private Integer registerRecordCount;//签到数
	private Integer sellerAuditCount;//审核中数
	private Integer sellerNoPassCount;//未通过数
	private Integer sellerCount;//商家总数
	private String  jointName;//所属合作商
	private String cityName;//城市
	private String areaName;//区域
	private Integer jointid;
	
	private Integer tpNameid;
	private Integer cityNameid;	
	private Integer areaNameid;
	private  Date   xdate;//用来放置日期
	private  int    status;
	private Integer sellerid;
    
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getSellerNoPassCount() {
		return sellerNoPassCount;
	}
	public void setSellerNoPassCount(Integer sellerNoPassCount) {
		this.sellerNoPassCount = sellerNoPassCount;
	}
	public String getDate(){
		if(xdate==null){
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.xdate);
	}
    public String getXstatus(){
    	String str=null;
    	switch(this.status){
    	case 0:str="废弃";break;
    	case 1:str="审核中";break;
    	case 2:str="未通过";break;
    	case 3:str="已签约";break;
    	case 4:str="未签约(签到)";break;
    	case 5:str="暂停合作";break;
    	}
    	return  str;
    }
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getXdate() {
		return xdate;
	}
	public void setXdate(Date xdate) {
		this.xdate = xdate;
	}
	public Integer getStaffid() {
		return staffid;
	}
	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public Integer getSellerSignCount() {
		return sellerSignCount;
	}
	public void setSellerSignCount(Integer sellerSignCount) {
		this.sellerSignCount = sellerSignCount;
	}
	public Integer getSellerNoValidationCount() {
		return sellerNoValidationCount;
	}
	public void setSellerNoValidationCount(Integer sellerNoValidationCount) {
		this.sellerNoValidationCount = sellerNoValidationCount;
	}
	public Integer getRegisterRecordCount() {
		return registerRecordCount;
	}
	public void setRegisterRecordCount(Integer registerRecordCount) {
		this.registerRecordCount = registerRecordCount;
	}
	public Integer getSellerAuditCount() {
		return sellerAuditCount;
	}
	public void setSellerAuditCount(Integer sellerAuditCount) {
		this.sellerAuditCount = sellerAuditCount;
	}
	public Integer getSellerCount() {
		return sellerCount;
	}
	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}
	public String getJointName() {
		return jointName;
	}
	public void setJointName(String jointName) {
		this.jointName = jointName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public Integer getCityNameid() {
		return cityNameid;
	}
	public void setCityNameid(Integer cityNameid) {
		this.cityNameid = cityNameid;
	}
	public Integer getTpNameid() {
		return tpNameid;
	}
	public void setTpNameid(Integer tpNameid) {
		this.tpNameid = tpNameid;
	}
	public Integer getAreaNameid() {
		return areaNameid;
	}
	public void setAreaNameid(Integer areaNameid) {
		this.areaNameid = areaNameid;
	}
	@Override
	public String toString() {
		return "TstaffRanking [fullname=" + fullname + ", phoneid=" + phoneid
				+ ", corporate=" + corporate + ", legalperson=" + legalperson
				+ ", sellerSignCount=" + sellerSignCount
				+ ", sellerNoValidationCount=" + sellerNoValidationCount
				+ ", registerRecordCount=" + registerRecordCount
				+ ", sellerAuditCount=" + sellerAuditCount + ", sellerCount="
				+ sellerCount + ", jointName=" + jointName + ", cityName="
				+ cityName + ", areaName=" + areaName + ", cityNameid="
				+ cityNameid + ", tpNameid=" + tpNameid + ", areaNameid="
				+ areaNameid + "]";
	}
	public Integer getJointid() {
		return jointid;
	}
	public void setJointid(Integer jointid) {
		this.jointid = jointid;
	}
		
	
}
