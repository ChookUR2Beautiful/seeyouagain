package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarDict
 * 
 * 类描述： 教学分类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-26 上午11:51:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarDict  extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8864669996772430420L;

	private Long id;

    private String name;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}