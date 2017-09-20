package com.xmniao.xmn.core.coupon.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 
 * @项目名称：XmnWeb20150513E
 * 
 * @类名称：TCoupon 优惠券
 * 
 * @类描述： 优惠券
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年5月29日 下午1:47:05
 * 
 */
public class TCoupon extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer cid;//
	private String cname;// 优惠劵名称
	private Double denomination;// 优惠劵面额
	private String validityDesc;// 有效期描述
	private Date startDate;// 优惠劵开始日期
	private Date endDate;// 优惠劵过期时间，将最后一个的一个日期的最后一个时段写入该字段

	private Double condition;// 使用时最低金额，0表示无条件使用
	private Double condition2;// 使用时最低金额，0表示无条件使用（商城优惠劵）
	private Double condition3;// 使用时最低金额，0表示无条件使用（平台通用优惠劵）

	private Integer useNum;// 每次可同时使用张数
	private String pic;// 优惠劵详情图
	private String breviary;// 优惠劵列表图
	private Integer showAll;// 是否全国显示，0不是，1是
	private Integer isAllSeller;// 是否所有商家 0:否 1:是

	private List<TCouponValidity> coupnValidities;// 有效时间
	private List<TCouponCity> couponCities;// 显示区域
	private List<TCouponSeller> includeTrade;// 包含的行业
	private List<TCouponSeller> excludeTrade;// 不包含的行业
	private String includeSellerids; // 商家编号
	private String excludeSellerids; // 商家编号
	private Long countOfSeller;// 商家数量
	private String citysText;// 显示区域
	private Integer province;// 省份
	private Integer city;// 城市
	private Date startDateStart;
	private Date startDateEnd;
	private Double denominationStart;
	private Double denominationEnd;
	private String value;
	private Integer dayNum;// 有效间隔天数(单位为天)
	private Integer dayNum2;// 有效间隔天数(单位为天)--商城优惠劵
	private Integer dayNum3;// 有效间隔天数(单位为天) -- 平台通用劵
	private Integer swichtime;// 启用 1.有效间隔天数 2.有效期 （开关）
	private String rule;
	private Integer ctype;// 优惠券类型(用于接收sql操作结果)，0本地生活类优惠卷(不可使用积分组合支付) 1生鲜类现金卷(可以和积分组合支付)
//	private String ctypeStr;//优惠券类型(用于页面显示)
	
	private String filterVal;//优惠券类型过滤值
	private Integer overlay;

	private Date updateTime;

	private Integer status;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getOverlay() {
		return overlay;
	}

	public void setOverlay(Integer overlay) {
		this.overlay = overlay;
	}

	public Integer getDayNum3() {
		return dayNum3;
	}

	public void setDayNum3(Integer dayNum3) {
		this.dayNum3 = dayNum3;
	}

	public Double getCondition3() {
		return condition3;
	}

	public void setCondition3(Double condition3) {
		this.condition3 = condition3;
	}

	/**
	 *
	 * @return the filterVal
	 */
	public String getFilterVal() {
		return filterVal;
	}

	/**
	 * @param filterVal the filterVal to set
	 */
	public void setFilterVal(String filterVal) {
		this.filterVal = filterVal;
	}

	public Double getCondition2() {
		return condition2;
	}

	public void setCondition2(Double condition2) {
		this.condition2 = condition2;
	}

	public Integer getDayNum2() {
		return dayNum2;
	}

	public void setDayNum2(Integer dayNum2) {
		this.dayNum2 = dayNum2;
	}

	public String getCtypeStr() {
		if(ctype!=null){
		if(ctype==0) return "消费优惠劵";
		if(ctype==1) return "商城优惠劵";
		if(ctype==5) return "平台通用优惠劵";
		}
		return null;
	}

//	public void setCtypeStr(String ctypeStr) {
//		this.ctypeStr = ctypeStr;
//	}

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

	public Integer getSwichtime() {
		return swichtime;
	}

	public void setSwichtime(Integer swichtime) {
		this.swichtime = swichtime;
	}

	public Integer getDayNum() {
		return dayNum;
	}

	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	public Integer getDenominationInt() {
		if (denomination != null) {
			return denomination.intValue();
		}
		return null;
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

	public Double getDenomination() {
		return denomination;
	}

	public void setDenomination(Double denomination) {
		this.denomination = denomination;
	}

	public String getValidityDesc() {
		return validityDesc;
	}

	public void setValidityDesc(String validityDesc) {
		this.validityDesc = validityDesc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getCondition() {
		return condition;
	}

	public void setCondition(Double condition) {
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

	public Integer getShowAll() {
		return showAll;
	}

	public void setShowAll(Integer showAll) {
		this.showAll = showAll;
	}

	public Integer getIsAllSeller() {
		return isAllSeller;
	}

	public void setIsAllSeller(Integer isAllSeller) {
		this.isAllSeller = isAllSeller;
	}

	public Long getCountOfSeller() {
		return countOfSeller;
	}

	public void setCountOfSeller(Long countOfSeller) {
		this.countOfSeller = countOfSeller;
	}

	public List<TCouponValidity> getCoupnValidities() {
		return coupnValidities;
	}

	public void setCoupnValidities(List<TCouponValidity> coupnValidities) {
		this.coupnValidities = coupnValidities;
	}

	public List<TCouponCity> getCouponCities() {
		return couponCities;
	}

	public void setCouponCities(List<TCouponCity> couponCities) {
		this.couponCities = couponCities;
	}

	public String getCitysText() {
		return citysText;
	}

	public void setCitysText(String citysText) {
		this.citysText = citysText;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Date getStartDateStart() {
		return startDateStart;
	}

	public void setStartDateStart(Date startDateStart) {
		this.startDateStart = startDateStart;
	}

	public Date getStartDateEnd() {
		return startDateEnd;
	}

	public void setStartDateEnd(Date startDateEnd) {
		this.startDateEnd = startDateEnd;
	}

	public Double getDenominationStart() {
		return denominationStart;
	}

	public void setDenominationStart(Double denominationStart) {
		this.denominationStart = denominationStart;
	}

	public Double getDenominationEnd() {
		return denominationEnd;
	}

	public void setDenominationEnd(Double denominationEnd) {
		this.denominationEnd = denominationEnd;
	}

	public List<TCouponSeller> getIncludeTrade() {
		return includeTrade;
	}

	public void setIncludeTrade(List<TCouponSeller> includeTrade) {
		this.includeTrade = includeTrade;
	}

	public List<TCouponSeller> getExcludeTrade() {
		return excludeTrade;
	}

	public void setExcludeTrade(List<TCouponSeller> excludeTrade) {
		this.excludeTrade = excludeTrade;
	}

	public String getIncludeSellerids() {
		return includeSellerids;
	}

	public void setIncludeSellerids(String includeSellerids) {
		this.includeSellerids = includeSellerids;
	}

	public String getExcludeSellerids() {
		return excludeSellerids;
	}

	public void setExcludeSellerids(String excludeSellerids) {
		this.excludeSellerids = excludeSellerids;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TCoupon{" +
				"cid=" + cid +
				", cname='" + cname + '\'' +
				", denomination=" + denomination +
				", validityDesc='" + validityDesc + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", condition=" + condition +
				", condition2=" + condition2 +
				", condition3=" + condition3 +
				", useNum=" + useNum +
				", pic='" + pic + '\'' +
				", breviary='" + breviary + '\'' +
				", showAll=" + showAll +
				", isAllSeller=" + isAllSeller +
				", coupnValidities=" + coupnValidities +
				", couponCities=" + couponCities +
				", includeTrade=" + includeTrade +
				", excludeTrade=" + excludeTrade +
				", includeSellerids='" + includeSellerids + '\'' +
				", excludeSellerids='" + excludeSellerids + '\'' +
				", countOfSeller=" + countOfSeller +
				", citysText='" + citysText + '\'' +
				", province=" + province +
				", city=" + city +
				", startDateStart=" + startDateStart +
				", startDateEnd=" + startDateEnd +
				", denominationStart=" + denominationStart +
				", denominationEnd=" + denominationEnd +
				", value='" + value + '\'' +
				", dayNum=" + dayNum +
				", dayNum2=" + dayNum2 +
				", dayNum3=" + dayNum3 +
				", swichtime=" + swichtime +
				", rule='" + rule + '\'' +
				", ctype=" + ctype +
				", filterVal='" + filterVal + '\'' +
				", overlay=" + overlay +
				", status=" + status +
				'}';
	}
}
