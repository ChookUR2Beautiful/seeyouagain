package com.xmniao.xmn.core.live.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.util.DateUtil;


public class CouponInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -520684263188049724L;
	
	private Integer cdid ;
	
	private Integer cid ;
	//'优惠券名称'
	private String cname;
	//'优惠券面额'
	private BigDecimal denomination;
	//'有效期描述'
	private String validityDesc;
	//'优惠劵开始日期'
	private Date startDate ;
	private String startDateStr ;
	
	//优惠劵过期时间，将最后一个的一个日期的最后一个时段写入该字段
	private Date endDate;
	private String endDateStr;
	
	//使用时最低金额，0表示无条件使用
	private BigDecimal condition;
	private String conditionStr;
	
	//'每次可同时使用张数',
	private Integer useNum;
	//'优惠劵详情图',
	private String pic;
	//'优惠劵列表图',
	private String breviary;
	//'是否全平台通用，0：否，1：是',
	private Integer showall;
	//'是否所有商家 0:否 1:是',
	private Integer isAllSeller;
	//'有效间隔天数(单位为天)',
	private Integer dayNum;
	//'优惠劵使用规则'
	private String rule;
	//默认为0     0本地生活类优惠卷(不可使用积分组合支付)      1生鲜类现金卷(可以和积分组合支付)
	private Integer ctype;
	//是否可用
	private Integer isAvailable;
	
	private Integer userStatus;
	
	private String sellername;//商家名称
	private String serial;//优惠券序列码
	private Integer anchorCid ;
	private BigDecimal conditions; 
	private Integer couponType; 
	private Integer seats;
	private String validationDate;//有效期描述
	private Integer sellerId;
	private Integer liveRecordId;
	private String useDescribe;
	private String useTimeDesc;
	
	private Integer isTimeout;
	//是否可叠加使用
	private Integer overlay;
	
	public Integer getIsTimeout() {
		return isTimeout;
	}
	public void setIsTimeout(Integer isTimeout) {
		this.isTimeout = isTimeout;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getValidationDate() {
		return validationDate;
	}
	public void setValidationDate(String validationDate) {
		this.validationDate = validationDate;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public BigDecimal getDenomination() {
		return denomination;
	}
	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}
	public String getValidityDesc() {
		return validityDesc;
	}
	public void setValidityDesc(String validityDesc) {
		this.validityDesc = validityDesc;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getCondition() {
		return condition;
	}
	public void setCondition(BigDecimal condition) {
		this.condition = condition;
	}
	public Integer getUseNum() {
		return useNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getBreviary() {
		return breviary;
	}
	public void setBreviary(String breviary) {
		this.breviary = breviary;
	}
	public Integer getShowall() {
		return showall;
	}
	public void setShowall(Integer showall) {
		this.showall = showall;
	}
	public Integer getIsAllSeller() {
		return isAllSeller;
	}
	public void setIsAllSeller(Integer isAllSeller) {
		this.isAllSeller = isAllSeller;
	}
	public Integer getDayNum() {
		return dayNum;
	}
	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public String getStartDateStr() {
		if (startDate!=null) {
			return DateUtil.format(startDate, DateUtil.minuteSimpleFormater);
		}
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		if (endDate!=null) {
			return DateUtil.format(endDate, DateUtil.minuteSimpleFormater);
		}
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public String getConditionStr() {
		return conditionStr;
	}
	public void setConditionStr(String conditionStr) {
		this.conditionStr = conditionStr;
	}
	public Integer getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public Integer getCdid() {
		return cdid;
	}
	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}
	
	
	
	public Integer getAnchorCid() {
		return anchorCid;
	}
	public void setAnchorCid(Integer anchorCid) {
		this.anchorCid = anchorCid;
	}
	public BigDecimal getConditions() {
		return conditions;
	}
	public void setConditions(BigDecimal conditions) {
		this.conditions = conditions;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getLiveRecordId() {
		return liveRecordId;
	}
	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	
	public String getUseDescribe() {
		return useDescribe;
	}
	public void setUseDescribe(String useDescribe) {
		this.useDescribe = useDescribe;
	}
	
	public String getUseTimeDesc() {
		return useTimeDesc;
	}
	public void setUseTimeDesc(String useTimeDesc) {
		this.useTimeDesc = useTimeDesc;
	}
	public Integer getOverlay() {
		return overlay;
	}
	public void setOverlay(Integer overlay) {
		this.overlay = overlay;
	}
	@Override
	public String toString() {
		return "CouponInfo [cdid=" + cdid + ", cid=" + cid + ", cname=" + cname
				+ ", denomination=" + denomination + ", validityDesc="
				+ validityDesc + ", startDate=" + startDate + ", startDateStr="
				+ startDateStr + ", endDate=" + endDate + ", endDateStr="
				+ endDateStr + ", condition=" + condition + ", conditionStr="
				+ conditionStr + ", useNum=" + useNum + ", pic=" + pic
				+ ", breviary=" + breviary + ", showall=" + showall
				+ ", isAllSeller=" + isAllSeller + ", dayNum=" + dayNum
				+ ", rule=" + rule + ", ctype=" + ctype + ", isAvailable="
				+ isAvailable + ", userStatus=" + userStatus + ", sellername="
				+ sellername + ", serial=" + serial + ", anchorCid="
				+ anchorCid + ", conditions=" + conditions + ", couponType="
				+ couponType + ", seats=" + seats + ", validationDate="
				+ validationDate + ", sellerId=" + sellerId + ", liveRecordId="
				+ liveRecordId + ", useDescribe=" + useDescribe
				+ ", useTimeDesc=" + useTimeDesc + ", isTimeout=" + isTimeout
				+ ", overlay=" + overlay + "]";
	}
	
}



