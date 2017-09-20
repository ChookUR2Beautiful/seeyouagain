package com.xmniao.xmn.core.coupon.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：TCouponStatistics 优惠券
 * 
 * @类描述： 优惠券活动统计
 * 
 * @创建人：cao'yingde
 * 
 * @创建时间 ：2015年06月02日 上午9:47:05
 * 
 */
public class TCouponStatistics extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer issueid;
	private String activityName;// 活动名称
	private String activityType;// 活动类型
	private String status;// 活动状态
	private String issueVolume;// 发行量
	private String lockedNum;// 锁定
	private Integer gotNum;// 已领取
	private Integer unUseNum;// 未使用
	private Integer usedNum;// 使用
	private Integer usedAmount;// 使用金额
	private String userMoney;// 使用金额
	private Date dateIssue    ;// 发行时间
	private String orderNo;  // 订单编号
	/*
	 * 按优惠券统计列表字段
	 */
	private Integer cid;
	private String unGetNum;// 活动名称
	private String cname;// 活动类型
	private String denomination;// 
	private BigDecimal denominationLow;// 最低面额
	private BigDecimal denominationTop;// 最高面额
	private String condition;// 发行量
	private String getWay;// 领取方式
	private String getWayText;//  获取方式，1摇一摇，2满返，3短信获取 4直接发放,5刮刮卡获取
	private String showall;// 是否全国显示
	private Date startDate;// 锁定
	private Date endDate;// 已领取

	/*
	 * 未使用统计列表字段
	 */
	private String phone;// 手机号码
	private String uid;// 用户id
	private String serial;// 优惠券sn码
	private String getTime;// 领取时间
	private String lockTime;// 过期时间
	private Integer userStatus;// 使用状态
	private Integer getStatus;// 获取状态
	private Date dateStart;// 领取开始时间
	private Date dateEnd;// 领取结束时间
	private Date userTime;// 应用实践
	private String sellerNum;// 商家数量
	/*
	 * 区域字段
	 */
	private String ccid;
	private String provinces;
	private String city;
	private String provincesText;
    
	public String getGetWayText() {
		if(getWay==null||"".equals(getWay)) return "";
		if("1".equals(getWay)) return "摇一摇";
		if("2".equals(getWay)) return "满返";
		if("3".equals(getWay)) return "短信获取";
		if("4".equals(getWay)) return "直接发放";
		if("5".equals(getWay)) return "刮刮卡获取";
		return "";
	}

	public void setGetWayText(String getWayText) {
		this.getWayText = getWayText;
	}

	public String getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(String userMoney) {
		this.userMoney = userMoney;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(Date dateIssue) {
		this.dateIssue = dateIssue;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUserTime() {
		return userTime;
	}

	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}

	public Integer getGetStatus() {
		return getStatus;
	}

	public void setGetStatus(Integer getStatus) {
		this.getStatus = getStatus;
	}

	public Integer getIssueid() {
		return issueid;
	}

	public void setIssueid(Integer issueid) {
		this.issueid = issueid;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssueVolume() {
		return issueVolume;
	}

	public void setIssueVolume(String issueVolume) {
		this.issueVolume = issueVolume;
	}

	public String getLockedNum() {
		return lockedNum;
	}

	public void setLockedNum(String lockedNum) {
		this.lockedNum = lockedNum;
	}

	public Integer getGotNum() {
		return gotNum;
	}

	public void setGotNum(Integer gotNum) {
		this.gotNum = gotNum;
	}

	public Integer getUnUseNum() {
		return unUseNum;
	}

	public void setUnUseNum(Integer unUseNum) {
		this.unUseNum = unUseNum;
	}

	public Integer getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}

	public Integer getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(Integer usedAmount) {
		this.usedAmount = usedAmount;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getUnGetNum() {
		return unGetNum;
	}

	public void setUnGetNum(String unGetNum) {
		this.unGetNum = unGetNum;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getShowall() {
		return showall;
	}

	public void setShowall(String showall) {
		this.showall = showall;
	}

	public String getGetWay() {
		return getWay;
	}

	public void setGetWay(String getWay) {
		this.getWay = getWay;
	}

	public BigDecimal getDenominationLow() {
		return denominationLow;
	}

	public void setDenominationLow(BigDecimal denominationLow) {
		this.denominationLow = denominationLow;
	}

	public BigDecimal getDenominationTop() {
		return denominationTop;
	}

	public void setDenominationTop(BigDecimal denominationTop) {
		this.denominationTop = denominationTop;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getSellerNum() {
		return sellerNum;
	}

	public void setSellerNum(String sellerNum) {
		this.sellerNum = sellerNum;
	}

	public String getCcid() {
		return ccid;
	}

	public void setCcid(String ccid) {
		this.ccid = ccid;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvincesText() {
		if(StringUtils.hasLength(provinces)){
			provinces = AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(provinces));
		}
		if(StringUtils.hasLength(city)){
			city = AreaHandler.getAreaHandler().getAreaIdByTitle(Integer.parseInt(city));
		}
		provincesText = provinces+"-"+city;
		return provincesText;
	}

	public void setProvincesText(String provincesText) {
		this.provincesText = provincesText;
	}

	@Override
	public String toString() {
		return "TCouponStatistics [issueid=" + issueid + ", activityName="
				+ activityName + ", activityType=" + activityType + ", status="
				+ status + ", issueVolume=" + issueVolume + ", lockedNum="
				+ lockedNum + ", gotNum=" + gotNum + ", gotNum=" + gotNum
				+ ", unUseNum=" + unUseNum + ", usedNum=" + usedNum
				+ ", usedNum=" + usedNum + ", usedNum=" + usedNum
				+ ", sellerNum=" + sellerNum + "]";
	}

}
