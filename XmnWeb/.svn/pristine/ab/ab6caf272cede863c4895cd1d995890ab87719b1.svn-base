package com.xmniao.xmn.core.mybatisDemo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * mybatisplus测试表
 * </p>
 *
 * @author ChenBo
 * @since 2017-09-14
 */
@TableName("mybatisplus_demo")
public class MybatisplusDemo extends Model<MybatisplusDemo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商户id
     */
	private Integer sellerid;
    /**
     * 活动名称
     */
	private String name;
    /**
     * 开始日期
     */
	@TableField("begin_date")
	private Date beginDate;
    /**
     * 结束日期
     */
	@TableField("end_date")
	private Date endDate;
    /**
     * 开始时间
     */
	@TableField("begin_time")
	private Date beginTime;
    /**
     * 结束时间
     */
	@TableField("end_time")
	private Date endTime;
    /**
     * 参加人数
     */
	@TableField("join_number")
	private Integer joinNumber;
    /**
     * 赠品数量
     */
	@TableField("give_number")
	private Integer giveNumber;
    /**
     * 秒杀金额
     */
	@TableField("sec_kill_amount")
	private BigDecimal secKillAmount;
    /**
     * 秒杀次数
     */
	@TableField("sec_kill_number")
	private Integer secKillNumber;
    /**
     * 每人限获（0：不限制，1:限领一次)
     */
	@TableField("limit_number")
	private Integer limitNumber;
    /**
     * 曝光次数
     */
	private Integer views;
    /**
     * 状态 0：启用 1：终止
     */
	private Integer status;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 新会员数
     */
	@TableField("newuser_num")
	private Integer newuserNum;
    /**
     * 刺激消费金额
     */
	@TableField("consume_amount")
	private BigDecimal consumeAmount;
    /**
     * 活动终止时间
     */
	@TableField("end_activity_time")
	private Date endActivityTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getJoinNumber() {
		return joinNumber;
	}

	public void setJoinNumber(Integer joinNumber) {
		this.joinNumber = joinNumber;
	}

	public Integer getGiveNumber() {
		return giveNumber;
	}

	public void setGiveNumber(Integer giveNumber) {
		this.giveNumber = giveNumber;
	}

	public BigDecimal getSecKillAmount() {
		return secKillAmount;
	}

	public void setSecKillAmount(BigDecimal secKillAmount) {
		this.secKillAmount = secKillAmount;
	}

	public Integer getSecKillNumber() {
		return secKillNumber;
	}

	public void setSecKillNumber(Integer secKillNumber) {
		this.secKillNumber = secKillNumber;
	}

	public Integer getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
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

	public Integer getNewuserNum() {
		return newuserNum;
	}

	public void setNewuserNum(Integer newuserNum) {
		this.newuserNum = newuserNum;
	}

	public BigDecimal getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(BigDecimal consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public Date getEndActivityTime() {
		return endActivityTime;
	}

	public void setEndActivityTime(Date endActivityTime) {
		this.endActivityTime = endActivityTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MybatisplusDemo{" +
			"id=" + id +
			", sellerid=" + sellerid +
			", name=" + name +
			", beginDate=" + beginDate +
			", endDate=" + endDate +
			", beginTime=" + beginTime +
			", endTime=" + endTime +
			", joinNumber=" + joinNumber +
			", giveNumber=" + giveNumber +
			", secKillAmount=" + secKillAmount +
			", secKillNumber=" + secKillNumber +
			", limitNumber=" + limitNumber +
			", views=" + views +
			", status=" + status +
			", createTime=" + createTime +
			", newuserNum=" + newuserNum +
			", consumeAmount=" + consumeAmount +
			", endActivityTime=" + endActivityTime +
			"}";
	}
}
