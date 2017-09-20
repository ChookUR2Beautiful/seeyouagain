package com.xmniao.xmn.core.dataDictionary.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BRecruitTag
 * 
 * 类名称：招聘标签类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-30下午4:32:27
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BRecruitTag extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 标签ID

	private String tagname;// 标签名称

	private Integer tagtype;// 标签类型 0：岗位标签、1：自我评价 2：培训 3：福利：4:岗位要求

	private String tagTypeStr;// 标签类型值

	private Byte tagstatus;// 标签状态0系统 1自定义

	private Date updateDate;// 添加修改时间

	private String operator;// 操作人
	
	private Long tagCount;//发布率

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname == null ? null : tagname.trim();
	}

	public Integer getTagtype() {
		return tagtype;
	}

	public void setTagtype(Integer tagtype) {
		this.tagtype = tagtype;
	}

	public Byte getTagstatus() {
		return tagstatus;
	}

	public void setTagstatus(Byte tagstatus) {
		this.tagstatus = tagstatus;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}
	
	

	/**
	 * @return the tagCount
	 */
	public Long getTagCount() {
		return tagCount;
	}

	/**
	 * @param tagCount the tagCount to set
	 */
	public void setTagCount(Long tagCount) {
		this.tagCount = tagCount;
	}

	/**
	 * @return the tagTypeStr
	 */
	public String getTagTypeStr() {
		switch (tagtype) {
		case 0:
			tagTypeStr = "岗位标签";
			break;
		case 1:
			tagTypeStr = "自我评价";
			break;
		case 2:
			tagTypeStr = "培训";
			break;
		case 3:
			tagTypeStr = "福利";
			break;
		case 4:
			tagTypeStr = "岗位要求";
			break;
		default:
			tagTypeStr = "";
		}
		return tagTypeStr;
	}

	/**
	 * @param tagTypeStr
	 *            the tagTypeStr to set
	 */
	public void setTagTypeStr(String tagTypeStr) {
		this.tagTypeStr = tagTypeStr;
	}

}