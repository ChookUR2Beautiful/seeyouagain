package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.xmniao.xmn.core.base.BaseEntity;

public class Indiana extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 状态  0:正常  1:终止  2:删除
     */
    private Integer status;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 终止时间
     */
    private Date termTime;

    /**
     * 产品编号
     */
    private Long codeId;

    /**
     * 属性值id组合，无序的，","作分隔符
     */
    private String pvIds;

    /**
     * 规格值
     */
    private String pvValue;

    /**
     * 商品价格
     */
    private BigDecimal basePrice;

    /**
     * 夺宝份数
     */
    private Integer point;

    /**
     * 一份价格
     */
    private BigDecimal pointPrice;

    /**
     * 夺宝期数
     */
    private Integer boutNum;

    /**
     * 剩余期数
     */
    private Integer boutResidue;

    /**
     * 状态(0：机器人补全 1:人不足时退款 )
     */
    private Integer isReal;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    
    private Integer proceedStatus;

    public Integer getProceedStatus() {
		return proceedStatus;
	}

	public void setProceedStatus(Integer proceedStatus) {
		this.proceedStatus = proceedStatus;
	}

	/**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 状态  0:正常  1:终止  2:删除
     * @return status 状态  0:正常  1:终止  2:删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态  0:正常  1:终止  2:删除
     * @param status 状态  0:正常  1:终止  2:删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 开始时间
     * @return begin_time 开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 开始时间
     * @param beginTime 开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 结束时间
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 终止时间
     * @return term_time 终止时间
     */
    public Date getTermTime() {
        return termTime;
    }

    /**
     * 终止时间
     * @param termTime 终止时间
     */
    public void setTermTime(Date termTime) {
        this.termTime = termTime;
    }

    /**
     * 产品编号
     * @return codeId 产品编号
     */
    public Long getCodeId() {
        return codeId;
    }

    /**
     * 产品编号
     * @param codeId 产品编号
     */
    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    /**
     * 属性值id组合，无序的，","作分隔符
     * @return pv_ids 属性值id组合，无序的，","作分隔符
     */
    public String getPvIds() {
        return pvIds;
    }

    /**
     * 属性值id组合，无序的，","作分隔符
     * @param pvIds 属性值id组合，无序的，","作分隔符
     */
    public void setPvIds(String pvIds) {
        this.pvIds = pvIds == null ? null : pvIds.trim();
    }

    /**
     * 规格值
     * @return pv_value 规格值
     */
    public String getPvValue() {
        return pvValue;
    }

    /**
     * 规格值
     * @param pvValue 规格值
     */
    public void setPvValue(String pvValue) {
        this.pvValue = pvValue == null ? null : pvValue.trim();
    }

    /**
     * 商品价格
     * @return base_price 商品价格
     */
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    /**
     * 商品价格
     * @param basePrice 商品价格
     */
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * 夺宝份数
     * @return point 夺宝份数
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * 夺宝份数
     * @param point 夺宝份数
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * 一份价格
     * @return point_price 一份价格
     */
    public BigDecimal getPointPrice() {
        return pointPrice;
    }

    /**
     * 一份价格
     * @param pointPrice 一份价格
     */
    public void setPointPrice(BigDecimal pointPrice) {
        this.pointPrice = pointPrice;
    }

    /**
     * 夺宝期数
     * @return bout_num 夺宝期数
     */
    public Integer getBoutNum() {
        return boutNum;
    }

    /**
     * 夺宝期数
     * @param boutNum 夺宝期数
     */
    public void setBoutNum(Integer boutNum) {
        this.boutNum = boutNum;
    }

    /**
     * 剩余期数
     * @return bout_residue 剩余期数
     */
    public Integer getBoutResidue() {
        return boutResidue;
    }

    /**
     * 剩余期数
     * @param boutResidue 剩余期数
     */
    public void setBoutResidue(Integer boutResidue) {
        this.boutResidue = boutResidue;
    }

    /**
     * 状态(0：机器人补全 1:人不足时退款 )
     * @return is_real 状态(0：机器人补全 1:人不足时退款 )
     */
    public Integer getIsReal() {
        return isReal;
    }

    /**
     * 状态(0：机器人补全 1:人不足时退款 )
     * @param isReal 状态(0：机器人补全 1:人不足时退款 )
     */
    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}