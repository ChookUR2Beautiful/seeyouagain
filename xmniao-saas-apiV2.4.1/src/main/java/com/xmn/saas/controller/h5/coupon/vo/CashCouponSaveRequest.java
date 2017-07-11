/**
 * 
 */
package com.xmn.saas.controller.h5.coupon.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.utils.CalendarUtil;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   现金抵用券保存VO
 * 创建人：huangk   
 * 创建时间：2016年9月27日 上午9:31:45      
 */
public class CashCouponSaveRequest {

	@NotNull(message = "名称不能为空")
	private String cname;
	@NotNull(message = "开始时间不能为空")
	private String startDate;
	@NotNull(message = "结束不能为空")
    private String endDate;
	@NotNull(message = "优惠券面额不能为空")
    private String denomination;//抵用券面额
	@NotNull(message = "使用条件不能为空")
    private String conditions;//使用条件
	@NotNull(message = "领取条件不能为空")
	private String awardCondition;
    private String limitAmount;//消费满获取金额
	private String limitNumber;
	@NotNull(message = "发放数量不能为空")
    private Integer sendNum;
	private int status;
	private String description;
	
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SellerCouponDetail convertRequestToBean() throws Exception{
    	SellerCouponDetail coupon = new SellerCouponDetail();
    	coupon.setCname(cname);
    	
    	Date sdate=CalendarUtil.formatDate(startDate);
    	coupon.setStartDate(CalendarUtil.dateFormat(sdate, "yyyy-MM-dd")+" 00:00:00");
    	
    	Date edate=CalendarUtil.formatDate(endDate);
    	coupon.setEndDate(CalendarUtil.dateFormat(edate, "yyyy-MM-dd")+" 23:59:59");
    	coupon.setDenomination(new BigDecimal(denomination));
    	coupon.setConditions(new BigDecimal(conditions));;
    	coupon.setAwardCondition(Integer.valueOf(awardCondition));
    	coupon.setLimitAmount(new BigDecimal(limitAmount));
    	coupon.setSendNum(0);//已发放数量
    	coupon.setSendStatus(1);//创建后默认发送状态为1--发送中
    	coupon.setLimitNumber(Integer.valueOf(limitNumber));
    	coupon.setStatus(0);
    	coupon.setCouponType(3);//现金券
    	coupon.setMaximum(sendNum);//最大发放数量
    	coupon.setAwardNumber(0);
    	coupon.setUseNumber(0);
    	coupon.setViews(0);
    	coupon.setCreateDate(new Date());
    	return coupon;
    }
    
    @Override
    public String toString(){
    	return "CashCouponSaveRequest:{"+
    			"cname="+cname+
    			",startDate="+startDate+
    			",endDate="+endDate+
    			",denomination="+denomination+
    			",conditions="+conditions+
    			",awardCondition="+awardCondition+
    			",limitAmount="+limitAmount+
    			",sendNum="+sendNum+
    			",limitNumber="+limitNumber+
    			"}";
    }
    
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getAwardCondition() {
		return awardCondition;
	}

	public void setAwardCondition(String awardCondition) {
		this.awardCondition = awardCondition;
	}

	public String getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(String limitNumber) {
		this.limitNumber = limitNumber;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

}
