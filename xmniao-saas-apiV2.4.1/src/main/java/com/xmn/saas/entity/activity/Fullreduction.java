package com.xmn.saas.entity.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xmn.saas.controller.h5.coupon.vo.FullreductionResponse;
import com.xmn.saas.utils.CalendarUtil;

public class Fullreduction {
    private Integer id;

    private Integer sellerid;

    private String name;

    private String beginDate;

    private String endDate;

    private Integer limitNumber;

    private Integer status;

    private BigDecimal offsetAmount;

    private Integer isDerate;

    private BigDecimal derateLevel1Amount;

    private BigDecimal consumeAndPay1;

    private BigDecimal derateLevel2Amount;

    private BigDecimal consumeAndPay2;

    private BigDecimal derateLevel3Amount;

    private BigDecimal consumeAndPay3;

    private BigDecimal consumeAndPay;

    private BigDecimal reductionAmount;

    private Integer joinNumber;

    private Integer views;

    private Integer giveNumber;

    private Integer useNumber;

    private Integer newuserNum;

    private BigDecimal consumeAmount;

    private Date createTime;
    
    /**
	 * 格式化满减返回数据
	 * @return List<FullreductionResponse>
	 */
	public static List<FullreductionResponse> getResponseList(List<Fullreduction> beans) throws Exception{
		List<FullreductionResponse> listData = new ArrayList<FullreductionResponse>();
		if(beans==null||beans.size()<1){
			return null;
		}
		for(Fullreduction coupon:beans){
			listData.add(getResponse(coupon));
		}
		return listData;
	}
	
	/**
	 * 格式化满减信息返回
	 * @return FullreductionResponse
	 */
	public static FullreductionResponse getResponse(Fullreduction bean) throws Exception{
		FullreductionResponse response= new FullreductionResponse();
		response.setId(bean.getId());
		response.setCname(bean.getName());
		response.setOffsetAmount(bean.getOffsetAmount());
		response.setJoinNum(bean.getJoinNumber()!=null?bean.getJoinNumber():0);
		response.setStartTime(bean.getBeginDate().substring(0,11));
		response.setEndTime(bean.getEndDate().substring(0,11));
		response.setStatus(bean.getStatus());
		return response;
	}

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
        this.name = name == null ? null : name.trim();
    }


    

    public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(BigDecimal offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public Integer getIsDerate() {
        return isDerate;
    }

    public void setIsDerate(Integer isDerate) {
        this.isDerate = isDerate;
    }

    public BigDecimal getDerateLevel1Amount() {
        return derateLevel1Amount;
    }

    public void setDerateLevel1Amount(BigDecimal derateLevel1Amount) {
        this.derateLevel1Amount = derateLevel1Amount;
    }

    public BigDecimal getConsumeAndPay1() {
        return consumeAndPay1;
    }

    public void setConsumeAndPay1(BigDecimal consumeAndPay1) {
        this.consumeAndPay1 = consumeAndPay1;
    }

    public BigDecimal getDerateLevel2Amount() {
        return derateLevel2Amount;
    }

    public void setDerateLevel2Amount(BigDecimal derateLevel2Amount) {
        this.derateLevel2Amount = derateLevel2Amount;
    }

    public BigDecimal getConsumeAndPay2() {
        return consumeAndPay2;
    }

    public void setConsumeAndPay2(BigDecimal consumeAndPay2) {
        this.consumeAndPay2 = consumeAndPay2;
    }

    public BigDecimal getDerateLevel3Amount() {
        return derateLevel3Amount;
    }

    public void setDerateLevel3Amount(BigDecimal derateLevel3Amount) {
        this.derateLevel3Amount = derateLevel3Amount;
    }

    public BigDecimal getConsumeAndPay3() {
        return consumeAndPay3;
    }

    public void setConsumeAndPay3(BigDecimal consumeAndPay3) {
        this.consumeAndPay3 = consumeAndPay3;
    }

    public BigDecimal getConsumeAndPay() {
        return consumeAndPay;
    }

    public void setConsumeAndPay(BigDecimal consumeAndPay) {
        this.consumeAndPay = consumeAndPay;
    }

    public BigDecimal getReductionAmount() {
        return reductionAmount;
    }

    public void setReductionAmount(BigDecimal reductionAmount) {
        this.reductionAmount = reductionAmount;
    }

    public Integer getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(Integer joinNumber) {
        this.joinNumber = joinNumber;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getGiveNumber() {
        return giveNumber;
    }

    public void setGiveNumber(Integer giveNumber) {
        this.giveNumber = giveNumber;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}