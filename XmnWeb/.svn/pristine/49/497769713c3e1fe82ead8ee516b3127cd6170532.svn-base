package com.xmniao.xmn.core.user_terminal.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class TUserProposal extends BaseEntity {
	private Integer id;// 主键ID
	private Integer uid;// 用户编号
	private String phoneid;// 用户账号（手机号）
	private Integer object;// 类型为1时储存商家编号，为0时储存吐槽对象。（吐槽对象定义：0：产品狗 1：设计狗 2：攻城狮 3：CEO）
	private String content;// 一级类别编号
	private Integer status;// 处理状态：0：待处理，1：已处理
	private Date dateCreated;// 创建时间
	private Date dateCreatedStart;// 创建时间  开始差选条件
	private Date dateCreatedEnd;// 创建时间   结束查询条件
	private String result;// 处理结果
	private Integer personHandlingNo;// 处理人编号
	private String personHandling;// 处理人
	private Date dateHandling;// 创建时间
	private Integer type;// 类型 0:用户吐槽 1：用户投诉
	
	public Date getDateCreatedStart() {
		return dateCreatedStart;
	}
	public void setDateCreatedStart(Date dateCreatedStart) {
		this.dateCreatedStart = dateCreatedStart;
	}
	public Date getDateCreatedEnd() {
		return dateCreatedEnd;
	}
	public void setDateCreatedEnd(Date dateCreatedEnd) {
		this.dateCreatedEnd = dateCreatedEnd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	public Integer getObject() {
		return object;
	}
	public String getObjectText() {
		if(object==null) return "";
		switch (object) {
		case 0:
			return "产品狗";
		case 1:
			return "设计狗";
		case 2:
			return "攻城狮";
		case 3:
			return "CEO";
		default:
			return "";
		}
	}
	public void setObject(Integer object) {
		this.object = object;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Integer getPersonHandlingNo() {
		return personHandlingNo;
	}
	public void setPersonHandlingNo(Integer personHandlingNo) {
		this.personHandlingNo = personHandlingNo;
	}
	public String getPersonHandling() {
		return personHandling;
	}
	public void setPersonHandling(String personHandling) {
		this.personHandling = personHandling;
	}
	public Date getDateHandling() {
		return dateHandling;
	}
	public void setDateHandling(Date dateHandling) {
		this.dateHandling = dateHandling;
	}
	public Integer getStatus() {
		return status;
	}
	public String getStatusText() {
		if(status == null) return "";
		switch (status) {
		case 0:
			return "待处理";
		case 1:
			return "已处理";
		default:
			return "";
		}
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public String getTypeText() {
		if(type == null) return "";
		switch (type) {
		case 0:
			return "用户吐槽";
		case 1:
			return "用户投诉";
		default:
			return "";
		}
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
