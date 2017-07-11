package com.xmn.saas.entity.coupon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.taskdefs.SubAnt;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.xmn.saas.controller.h5.coupon.vo.CouponDetailResponse;
import com.xmn.saas.utils.CalendarUtil;

/**
 * 项目名称：xmniao-saas-api    
 * 类描述：  商户优惠券定义详情
 * 创建人：huangk   
 * 创建时间：2016年9月26日 下午3:02:49
 */
public class SellerCouponDetail {
    private Integer cid;//主键
    
    private String cname;//优惠券名称

    private BigDecimal denomination;//面额

    private String startDate;//有效开始时间

    private String endDate;//有效结束时间

    private BigDecimal conditions;//使用时最低订单金额

    private BigDecimal limitAmount;//消费满增限制金额,才能赠送

    private Integer sellerid;//商户id

    private Integer useNumaw;//每次可使用张数(老字段)

    private Integer couponType;//优惠券类型:1 普通优惠券 2 随机优惠券 3现金抵用券 4赠品券

    private Integer sendType;//发送类型(老字段)

    private Integer sendNum;//发放数量

    private Integer maximum;//最大可发送数量(老字段)

    private BigDecimal minM;//(老字段)

    private BigDecimal maxM;//(老字段)

    private BigDecimal amount;//优惠券总额

    private String sendObject;//(老字段)

    private Integer sendStatus;//(老字段)

    private Integer awardNumber;//领取个数

    private Integer awardCondition;//领取条件

    private Integer useNumber;//已使用个数

    private Integer limitNumber;//每人限领数量（0：不限制，1限制）

    private Integer views;//每人限领数量（0：不限制，1限制）

    private Integer status;//状态 0：启用 1：禁用 2：锁定

    private Date createDate;//创建时间

    private int awardState;//是否设为奖品 
    
    private String description;//优惠活动描述
    
    /**
	 * 格式化优惠券返回数据
	 * @return List<CouponDetailResponse>
	 */
	public static List<CouponDetailResponse> getResponseList(List<SellerCouponDetail> beans) throws Exception{
		List<CouponDetailResponse> listData = new ArrayList<CouponDetailResponse>();
		if(beans==null||beans.size()<1){
			return null;
		}
		for(SellerCouponDetail coupon:beans){
			listData.add(getResponse(coupon));
		}
		return listData;
	}
	
	/**
	 * 格式化优惠券信息返回
	 * @return CouponDetailResponse
	 */
	public static CouponDetailResponse getResponse(SellerCouponDetail bean) throws Exception{
		CouponDetailResponse response= new CouponDetailResponse();
		response.setId(bean.getCid());
		response.setCname(bean.getCname());
		response.setAwardNum(bean.getAwardNumber());
		response.setSendNum(bean.getSendNum());
		response.setUseNum(bean.getUseNumber());
		response.setStartTime(bean.getStartDate().substring(0,11));
		response.setEndTime(bean.getEndDate().substring(0,11));
		response.setStatus(bean.getStatus());
		response.setMaximum(bean.getMaximum());
		return response;
	}
    
    public Integer getCid() {
        return cid;
    }

	public int getAwardState() {
		return awardState;
	}


	public void setAwardState(int awardState) {
		this.awardState = awardState;
	}


	public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
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

	public BigDecimal getConditions() {
        return conditions;
    }

    public void setConditions(BigDecimal conditions) {
        this.conditions = conditions;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getUseNumaw() {
		return useNumaw;
	}

	public void setUseNumaw(Integer useNumaw) {
		this.useNumaw = useNumaw;
	}

	public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public BigDecimal getMinM() {
        return minM;
    }

    public void setMinM(BigDecimal minM) {
        this.minM = minM;
    }

    public BigDecimal getMaxM() {
        return maxM;
    }

    public void setMaxM(BigDecimal maxM) {
        this.maxM = maxM;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject == null ? null : sendObject.trim();
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(Integer awardNumber) {
        this.awardNumber = awardNumber;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public Integer getAwardCondition() {
		return awardCondition;
	}

	public void setAwardCondition(Integer awardCondition) {
		this.awardCondition = awardCondition;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public String toString(){
    	return "SellerCouponDetail:{"+
    			"cid="+cid+
    			",sellerid="+sellerid+
    			",cname="+cname+
    			",couponType="+couponType+
    			",startDate="+startDate+
    			",endDate="+endDate+
    			",denomination="+denomination+
    			",conditions="+conditions+
    			",awardCondition="+awardCondition+
    			",awardNumber="+awardNumber+
    			",limitAmount="+limitAmount+
    			",sendNum="+sendNum+
    			",limitNumber="+limitNumber+
    			",amount="+amount+
    			",useNumber="+useNumber+
    			",views="+views+
    			",status="+status+
    			"}";
    }
    
}