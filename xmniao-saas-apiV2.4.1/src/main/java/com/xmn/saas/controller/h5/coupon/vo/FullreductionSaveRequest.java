/**
 * 
 */
package com.xmn.saas.controller.h5.coupon.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.xmn.saas.entity.activity.Fullreduction;
import com.xmn.saas.utils.CalendarUtil;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   满减促销优惠保存请求参数
 * 创建人：huangk   
 * 创建时间：2016年9月29日 上午10:12:58      
 */
@SuppressWarnings("serial")
public class FullreductionSaveRequest implements Serializable {

	@NotNull(message="满减名称不能为空")
    private String name;//满减名称
    
	@NotNull(message="消费满金额不能为空")
    private String consumeAndPay;//消费并支付满

	@NotNull(message="开始日期不能为空")
    private String beginDate;//开始日期

	@NotNull(message="结束日期不能为空")
    private String endDate;//结束日期
    
	@NotNull(message="减免金额不能为空")
    private String offsetAmount;//减免金额

    private String limitNumber;//是否限制每人参与一次
    
    private String isDerate;//是否开启三级减免
    
    private String derateLevel1Amount;//一级减免金额

    private String consumeAndPay1;//一级消费并支付满

    private String derateLevel2Amount;//二级减免金额

    private String consumeAndPay2;//二级消费并支付满

    private String derateLevel3Amount;//三级减免金额

    private String consumeAndPay3;//三级消费并支付满
    
    public Fullreduction convertRequestToBean() throws Exception{
    	Fullreduction fullreduction = new Fullreduction();
    	fullreduction.setName(name);
    	fullreduction.setConsumeAndPay(new BigDecimal(consumeAndPay));
       	Date sdate=CalendarUtil.formatDate(beginDate);
       	fullreduction.setBeginDate(CalendarUtil.dateFormat(sdate, "yyyy-MM-dd")+" 00:00:00");
    	Date edate=CalendarUtil.formatDate(endDate);
    	fullreduction.setEndDate(CalendarUtil.dateFormat(edate, "yyyy-MM-dd")+" 23:59:59");
    	fullreduction.setOffsetAmount(new BigDecimal(offsetAmount));
    	fullreduction.setLimitNumber(Integer.valueOf(limitNumber));
    	fullreduction.setIsDerate(Integer.valueOf(isDerate));
    	//开启三级递减，保存递减规则
    	fullreduction.setDerateLevel1Amount(new BigDecimal(derateLevel1Amount));
    	fullreduction.setConsumeAndPay1(new BigDecimal(consumeAndPay1));
    	fullreduction.setDerateLevel2Amount(new BigDecimal(derateLevel2Amount));
    	fullreduction.setConsumeAndPay2(new BigDecimal(consumeAndPay2));
    	fullreduction.setDerateLevel3Amount(new BigDecimal(derateLevel3Amount));
    	fullreduction.setConsumeAndPay3(new BigDecimal(consumeAndPay3));
    	fullreduction.setJoinNumber(0);
    	fullreduction.setViews(0);
    	fullreduction.setNewuserNum(0);
    	fullreduction.setConsumeAmount(new BigDecimal(0.00));
    	fullreduction.setReductionAmount(new BigDecimal(0.00));
    	fullreduction.setReductionAmount(new BigDecimal(consumeAndPay2));
    	fullreduction.setStatus(0);
    	fullreduction.setCreateTime(new Date());
    	return fullreduction;
    }
    
    @Override
    public String toString(){
    	return "Fullreduction:{name="+name+",consumeAndPay="+consumeAndPay+
    			",beginDate="+beginDate+",endDate="+endDate+
    			",offsetAmount="+offsetAmount+",limitNumber="+limitNumber+
    			",isDerate="+isDerate+",derateLevel1Amount="+derateLevel1Amount+
    			",consumeAndPay1="+consumeAndPay1+",derateLevel2Amount="+derateLevel2Amount+
    			",consumeAndPay2="+consumeAndPay2+",derateLevel3Amount="+derateLevel3Amount+
    			",consumeAndPay3="+consumeAndPay3+"}";
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConsumeAndPay() {
		return consumeAndPay;
	}

	public void setConsumeAndPay(String consumeAndPay) {
		this.consumeAndPay = consumeAndPay;
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

	public String getOffsetAmount() {
		return offsetAmount;
	}

	public void setOffsetAmount(String offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	public String getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(String limitNumber) {
		this.limitNumber = limitNumber;
	}

	public String getIsDerate() {
		return isDerate;
	}

	public void setIsDerate(String isDerate) {
		this.isDerate = isDerate;
	}

	public String getDerateLevel1Amount() {
		return derateLevel1Amount;
	}

	public void setDerateLevel1Amount(String derateLevel1Amount) {
		this.derateLevel1Amount = derateLevel1Amount;
	}

	public String getConsumeAndPay1() {
		return consumeAndPay1;
	}

	public void setConsumeAndPay1(String consumeAndPay1) {
		this.consumeAndPay1 = consumeAndPay1;
	}

	public String getDerateLevel2Amount() {
		return derateLevel2Amount;
	}

	public void setDerateLevel2Amount(String derateLevel2Amount) {
		this.derateLevel2Amount = derateLevel2Amount;
	}

	public String getConsumeAndPay2() {
		return consumeAndPay2;
	}

	public void setConsumeAndPay2(String consumeAndPay2) {
		this.consumeAndPay2 = consumeAndPay2;
	}

	public String getDerateLevel3Amount() {
		return derateLevel3Amount;
	}

	public void setDerateLevel3Amount(String derateLevel3Amount) {
		this.derateLevel3Amount = derateLevel3Amount;
	}

	public String getConsumeAndPay3() {
		return consumeAndPay3;
	}

	public void setConsumeAndPay3(String consumeAndPay3) {
		this.consumeAndPay3 = consumeAndPay3;
	}

}
