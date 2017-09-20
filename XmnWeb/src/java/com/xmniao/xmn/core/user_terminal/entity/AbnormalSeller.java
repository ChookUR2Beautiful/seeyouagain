package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 创建人：chenjie
 * 
 * 创建时间：2016年8月5日下午2:09:52
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class AbnormalSeller extends BaseEntity{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 2511410877634576900L;

	private Integer id;
	
	private Integer type;//举报类型：1商家基础信息有误；2 电话空号；3 地址错误；4 无法支付
	
	private Integer sellerid;//商家id
	
	private String sellername;//商家姓名
	
	private Integer uid;//举报人id
	
	private String uname;//举报人姓名
	
	private String phone;//举报人联系方式
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date sdate;//举报日期
	
	private Integer status;//处理状态。0 待处理;1 已处理；2 虚假举报
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date edate; //处理日期

	private Date sdateStart;
	
	private Date sdateEnd;
	
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}

	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}

	
	public String getStatusStr() {
		String statusStr = "-";
		if(status!=null){
			if(status==0){
				statusStr = "待处理";
			}else if(status==1){
				statusStr ="已处理";
			}else if(status==2){
				statusStr ="虚假举报";
			}
		}
		return statusStr;
	}
	
	public String getTypeStr() {
		String typeStr = "-";
		if(type!=null){
			if(type==1){
				typeStr = "商家基础信息有误";
			}else if(type==2){
				typeStr ="电话空号";
			}else if(type==3){
				typeStr ="地址错误";
			}else if(type==3){
				typeStr =" 无法支付";
			}
		}
		return typeStr;
	}
	
	@Override
	public String toString() {
		return "AbnormalSeller [type=" + type + ", sellerid=" + sellerid
				+ ", sellername=" + sellername + ", uid=" + uid + ", uname="
				+ uname + ", phone=" + phone + ", sdate=" + sdate + ", status="
				+ status + ", edate=" + edate + "]";
	}
	
}
