package com.xmniao.xmn.core.jobmanage.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称： XmnWeb
 * 类名称： TagEntity.java
 * 类描述：标签实体关系
 * 创建人： lifeng
 * 创建时间： 2016年6月13日下午2:24:59
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public class TagEntity extends BaseEntity {

	private static final long serialVersionUID = 3202378038472801840L;

	private Integer id;//关系ID
	private Integer pojoId;//招聘或招聘岗位ID
	private Integer tagId;//标签ID
	private Integer entityType;//类型0：招聘信息福利 1：招聘岗位 岗位要求 2 简历 我做过岗位  3 简历 我想做岗位 4 简历 培训经历 5  简历 自我评价
	private Date updateDate;//修改或删除时间
	
	/**
	 * 标签类型常量
	 * 0：招聘信息福利 1：招聘岗位 岗位要求2 简历 我做过岗位  3 简历 我想做岗位 4 简历 培训经历 5  简历 自我评价
	 */
	public static final Integer RECRUI_INFO_WELFARE = 0;
	public static final Integer RECRUIT_STATION_REQUIRE = 1;
	public static final Integer RESUME_STATION_DONE = 2;
	public static final Integer RESUME_STATION_WANT = 3;
	public static final Integer RESUME_STATION_TRAIN = 4;
	public static final Integer RESUME_EVALUATE = 5;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPojoId() {
		return pojoId;
	}
	public void setPojoId(Integer pojoId) {
		this.pojoId = pojoId;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public Integer getEntityType() {
		return entityType;
	}
	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "TagEntity [id=" + id + ", pojoId=" + pojoId + ", tagId="
				+ tagId + ", entityType=" + entityType + ", updateDate="
				+ updateDate + "]";
	}
	
}
