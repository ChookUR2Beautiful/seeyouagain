package com.xmniao.xmn.core.jobmanage.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称： XmnWeb
 * 类名称： RecruitTag.java
 * 类描述：招聘标签实体类
 * 创建人：lifeng
 * 创建时间： 2016年5月30日下午4:37:54
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public class RecruitTag extends BaseEntity {

	private static final long serialVersionUID = 8511752316563150516L;

	private Integer id;//标签ID
	private String  name;//标签名称
	private Integer tagType;//标签类型 0：岗位标签、1：自我评价 2：培训 3：福利：4:岗位要求 
	private Integer tagStatus;//标签状态0系统 1自定义 
	private Date updateDate;//添加修改时间
	private Date sdate;//开始时间
	private Date edate;//结束时间
	private String operator;//操作人
	
	/**
	 * 标签类型常量
	 */
	public static final Integer STATION_NAME = 0;
	public static final Integer SELF_EVALUATE = 1;
	public static final Integer TRAIN = 2;
	public static final Integer WELFARE = 3;
	public static final Integer STATION_REQUIRE = 4;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTagType() {
		return tagType;
	}
	public void setTagType(Integer tagType) {
		this.tagType = tagType;
	}
	public Integer getTagStatus() {
		return tagStatus;
	}
	public void setTagStatus(Integer tagStatus) {
		this.tagStatus = tagStatus;
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
		this.operator = operator;
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
	@Override
	public String toString() {
		return "RecruitTag [id=" + id + ", name=" + name + ", tagType="
				+ tagType + ", tagStatus=" + tagStatus + ", updateDate="
				+ updateDate + ", operator=" + operator + "]";
	}
	
}
