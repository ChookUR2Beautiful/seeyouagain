package com.xmniao.xmn.core.xmermanagerment.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BXmerWallet
 * 
 * 类描述： 寻蜜客钱包实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-8 下午6:09:38 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BXmerWallet extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5358185850240557905L;

	private Integer id;//寻蜜客钱包ID

    private Integer uid;//寻蜜客用户ID

    private Date applyDate;//申请日期

    private Date cancelDate;//注销日期

    private Integer status;//状态  1正常  2锁定  3注销

    private BigDecimal profit;//收益余额

    private BigDecimal trunout;//转出总额

    private String sign;//MD5签名数据

    private Date lastDate;//最后一笔交易时间

    private Integer fatherId;//寻蜜客对应会员钱包ID

    private String uname;//寻蜜客用户名

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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getTrunout() {
        return trunout;
    }

    public void setTrunout(BigDecimal trunout) {
        this.trunout = trunout;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }
}