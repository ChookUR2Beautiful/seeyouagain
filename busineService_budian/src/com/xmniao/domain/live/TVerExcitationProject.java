package com.xmniao.domain.live;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：TVerExcitationProject
 * 
 * 类描述： V客充值奖励方案实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-27 上午10:10:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVerExcitationProject implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 936349853352844910L;

	private Integer id;

    private String projectName;

    private Byte status;

    private Integer rankId;

    private Byte period;

    private BigDecimal singleValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Byte getPeriod() {
        return period;
    }

    public void setPeriod(Byte period) {
        this.period = period;
    }

    public BigDecimal getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(BigDecimal singleValue) {
        this.singleValue = singleValue;
    }
}