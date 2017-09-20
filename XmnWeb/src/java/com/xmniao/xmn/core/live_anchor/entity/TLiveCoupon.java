package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.NumberUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveCoupon
 * 
 * 类描述： 直播粉丝券实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-26 下午3:35:32 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveCoupon extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4993274035706384801L;

	private Integer cid;//主键

    private String cname;//优惠劵名称

    private BigDecimal denomination;//优惠劵面额
    
    private BigDecimal originalPrice;//优惠劵原价
    
    private Integer isRecom;//是否悬浮显示 0否 1是

    private String validityDesc;//有效期描述/粉丝券描述

    private Date startDate;//优惠劵开始日期
    
    private String startDateStr;//优惠劵开始日期字符串

    private Date endDate;//优惠劵过期时间
    
    private String endDateStr;//优惠劵过期时间字符串

    private BigDecimal condition;//使用时最低金额，0表示无条件使用

    private Integer useNum;//每次可同时使用张数

    private String pic;//优惠劵详情图

    private String breviary;//优惠劵列表图

    private Byte showall;//是否全平台通用，0：否，1：是

    private Integer isAllSeller;//是否所有商家 0:否 1:是

    private Integer dayNum;//有效间隔天数(单位为天)

    private Integer ctype;//优惠券类型 默认 0本地生活类优惠卷(不可使用积分组合支付)      1生鲜类现金卷(可以和积分组合支付)2 粉丝券 3 预售抵用券

    private Integer invalidToday;//是否领取当天可用 0可用 1不可用

    private Integer defaultMaxNum;//粉丝券默认发行最大量
    
    private Integer stock;//发行剩余量：针对粉丝券剩余数量

    private String introduce;//粉丝券默认预告介绍

    private String rule;//优惠劵使用规则
    
    private String useStatus;//使用状态 001未使用,002使用中
    
    private Date updateTime;//更新时间
    
    private String haveFree;//是否赠送抵用券 
    
    private String useTime ;//使用时间描述:两个月,购买当天不可用
    
    private String vouchers;//抵扣券面值描述:￥25,满100可用
    
    private List<TFansCouponIssueRef> voucherList;//抵扣券
    
    //add by ht 2017-02-21
    private Integer sellerid;//商家ID
    
    private Date saleStartTime;//销售开始时间
    
    private String saleStartTimeStr;//销售开始时间字符串
    
    private Date saleEndTime;//销售结束时间
    
    private String saleEndTimeStr;//销售结束时间字符串
    
    private Date forbidStartTime;//不可用开始时间
    
    private String forbidStartTimeStr;//不可用开始时间字符串
    
    private Date forbidEndTime;//不可用结束时间
    
    private String forbidEndTimeStr;//不可用结束时间字符串
    
    private String fansDescription;//粉丝券描述
    
    private Integer status;//状态 1.上架 2.下架 3.售罄
    
    private String sellername;//商家名称
    
    private Integer filterVal;//过滤条件值
    

	/**
	 * @return the startDateStr
	 */
	public String getStartDateStr() {
		if(startDate==null){
			return null;
		}
		startDateStr=DateUtil.formatDate(startDate, DateUtil.Y_M_D_HM);
		return startDateStr;
	}

	/**
	 * @param startDateStr the startDateStr to set
	 */
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	/**
	 * @return the endDateStr
	 */
	public String getEndDateStr() {
		if(endDate==null){
			return null;
		}
		endDateStr=DateUtil.formatDate(endDate, DateUtil.Y_M_D_HM);
		return endDateStr;
	}

	/**
	 * @param endDateStr the endDateStr to set
	 */
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	/**
	 * @return the saleStartTimeStr
	 */
	public String getSaleStartTimeStr() {
		if(saleStartTime==null){
			return null;
		}
		saleStartTimeStr=DateUtil.formatDate(saleStartTime, DateUtil.Y_M_D_HM);
		
		return saleStartTimeStr;
	}

	/**
	 * @param saleStartTimeStr the saleStartTimeStr to set
	 */
	public void setSaleStartTimeStr(String saleStartTimeStr) {
		this.saleStartTimeStr = saleStartTimeStr;
	}

	/**
	 * @return the saleEndTimeStr
	 */
	public String getSaleEndTimeStr() {
		if(saleEndTime==null){
			return null;
		}
		saleEndTimeStr=DateUtil.formatDate(saleEndTime, DateUtil.Y_M_D_HM);
		return saleEndTimeStr;
	}

	/**
	 * @param saleEndTimeStr the saleEndTimeStr to set
	 */
	public void setSaleEndTimeStr(String saleEndTimeStr) {
		this.saleEndTimeStr = saleEndTimeStr;
	}

	/**
	 * @return the forbidStartTimeStr
	 */
	public String getForbidStartTimeStr() {
		if(forbidStartTime==null){
			return null;
		}
		forbidStartTimeStr=DateUtil.formatDate(forbidStartTime, DateUtil.Y_M_D_HM);
		return forbidStartTimeStr;
	}

	/**
	 * @param forbidStartTimeStr the forbidStartTimeStr to set
	 */
	public void setForbidStartTimeStr(String forbidStartTimeStr) {
		this.forbidStartTimeStr = forbidStartTimeStr;
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
        this.cname = cname == null ? null : cname.trim();
    }

    public BigDecimal getDenomination() {
    	if(denomination==null){
    		return null;
    	}else{
    		return denomination=new BigDecimal(NumberUtil.getNumberFixedpoint(denomination, 0));
    	}
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }
    
    

    /**
	 * @return the originalPrice
	 */
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * @param originalPrice the originalPrice to set
	 */
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	

	/**
	 * @return the isRecom
	 */
	public Integer getIsRecom() {
		return isRecom;
	}

	/**
	 * @param isRecom the isRecom to set
	 */
	public void setIsRecom(Integer isRecom) {
		this.isRecom = isRecom;
	}

	public String getValidityDesc() {
        return validityDesc;
    }

    public void setValidityDesc(String validityDesc) {
        this.validityDesc = validityDesc == null ? null : validityDesc.trim();
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
        this.pic = pic == null ? null : pic.trim();
    }

    public String getBreviary() {
        return breviary;
    }

    public void setBreviary(String breviary) {
        this.breviary = breviary == null ? null : breviary.trim();
    }

    public Byte getShowall() {
        return showall;
    }

    public void setShowall(Byte showall) {
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

    public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public Integer getInvalidToday() {
        return invalidToday;
    }

    public void setInvalidToday(Integer invalidToday) {
        this.invalidToday = invalidToday;
    }

    public Integer getDefaultMaxNum() {
        return defaultMaxNum;
    }

    public void setDefaultMaxNum(Integer defaultMaxNum) {
        this.defaultMaxNum = defaultMaxNum;
    }
    
    

    /**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

	/**
	 * @return the useStatus
	 */
	public String getUseStatus() {
		return useStatus;
	}

	/**
	 * @param useStatus the useStatus to set
	 */
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}


	
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the haveFree
	 */
	public String getHaveFree() {
		if(vouchers==null&& (voucherList==null || voucherList.size()==0)){
			haveFree="2";//无抵用券
		}else{
			haveFree="1";//有抵用券
		}
		return haveFree;
	}

	/**
	 * @param haveFree the haveFree to set
	 */
	public void setHaveFree(String haveFree) {
		this.haveFree = haveFree;
	}
	
	

	/**
	 * 使用时间描述：两个月，购买当天不可用
	 * @return the useTime
	 */
	public String getUseTime() {
		StringBuffer sb=new StringBuffer();
		if(dayNum!=null){
			sb.append(dayNum).append("天，").append("购买当天").append(invalidToday==0?"可用":"不可用");
			useTime = sb.toString();
		}
		return useTime ;
	}

	/**
	 * @param useTime the useTime to set
	 */
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	
	

	/**
	 * @return the vouchers
	 */
	public String getVouchers() {
		return vouchers;
	}

	/**
	 * @param vouchers the vouchers to set
	 */
	public void setVouchers(String vouchers) {
		this.vouchers = vouchers;
	}
	
	

	/**
	 * @return the voucherList
	 */
	public List<TFansCouponIssueRef> getVoucherList() {
		return voucherList;
	}

	/**
	 * @param voucherList the voucherList to set
	 */
	public void setVoucherList(List<TFansCouponIssueRef> voucherList) {
		this.voucherList = voucherList;
	}
	
	

	/**
	 * @return the sellerid
	 */
	public Integer getSellerid() {
		return sellerid;
	}

	/**
	 * @param sellerid the sellerid to set
	 */
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	/**
	 * @return the saleStartTime
	 */
	public Date getSaleStartTime() {
		return saleStartTime;
	}

	/**
	 * @param saleStartTime the saleStartTime to set
	 */
	public void setSaleStartTime(Date saleStartTime) {
		this.saleStartTime = saleStartTime;
	}

	/**
	 * @return the saleEndTime
	 */
	public Date getSaleEndTime() {
		return saleEndTime;
	}

	/**
	 * @param saleEndTime the saleEndTime to set
	 */
	public void setSaleEndTime(Date saleEndTime) {
		this.saleEndTime = saleEndTime;
	}

	/**
	 * @return the forbidStartTime
	 */
	public Date getForbidStartTime() {
		return forbidStartTime;
	}

	/**
	 * @param forbidStartTime the forbidStartTime to set
	 */
	public void setForbidStartTime(Date forbidStartTime) {
		this.forbidStartTime = forbidStartTime;
	}

	/**
	 * @return the forbidEndTime
	 */
	public Date getForbidEndTime() {
		return forbidEndTime;
	}

	/**
	 * @param forbidEndTime the forbidEndTime to set
	 */
	public void setForbidEndTime(Date forbidEndTime) {
		this.forbidEndTime = forbidEndTime;
	}

	
	
	/**
	 * @return the forbidEndTimeStr
	 */
	public String getForbidEndTimeStr() {
		if(forbidEndTime==null){
			return null;
		}
		forbidEndTimeStr=DateUtil.formatDate(forbidEndTime, DateUtil.Y_M_D_HM);
		return forbidEndTimeStr;
	}

	/**
	 * @param forbidEndTimeStr the forbidEndTimeStr to set
	 */
	public void setForbidEndTimeStr(String forbidEndTimeStr) {
		this.forbidEndTimeStr = forbidEndTimeStr;
	}

	/**
	 * @return the fansDescription
	 */
	public String getFansDescription() {
		return fansDescription;
	}

	/**
	 * @param fansDescription the fansDescription to set
	 */
	public void setFansDescription(String fansDescription) {
		this.fansDescription = fansDescription;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	
	

	/**
	 * @return the filterVal
	 */
	public Integer getFilterVal() {
		return filterVal;
	}

	/**
	 * @param filterVal the filterVal to set
	 */
	public void setFilterVal(Integer filterVal) {
		this.filterVal = filterVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveCoupon [cid=" + cid + ", cname=" + cname
				+ ", denomination=" + denomination + ", originalPrice="
				+ originalPrice + ", isRecom=" + isRecom + ", validityDesc="
				+ validityDesc + ", startDate=" + startDate + ", startDateStr="
				+ startDateStr + ", endDate=" + endDate + ", endDateStr="
				+ endDateStr + ", condition=" + condition + ", useNum="
				+ useNum + ", pic=" + pic + ", breviary=" + breviary
				+ ", showall=" + showall + ", isAllSeller=" + isAllSeller
				+ ", dayNum=" + dayNum + ", ctype=" + ctype + ", invalidToday="
				+ invalidToday + ", defaultMaxNum=" + defaultMaxNum
				+ ", stock=" + stock + ", introduce=" + introduce + ", rule="
				+ rule + ", useStatus=" + useStatus + ", updateTime="
				+ updateTime + ", haveFree=" + haveFree + ", useTime="
				+ useTime + ", vouchers=" + vouchers + ", voucherList="
				+ voucherList + ", sellerid=" + sellerid + ", saleStartTime="
				+ saleStartTime + ", saleStartTimeStr=" + saleStartTimeStr
				+ ", saleEndTime=" + saleEndTime + ", saleEndTimeStr="
				+ saleEndTimeStr + ", forbidStartTime=" + forbidStartTime
				+ ", forbidStartTimeStr=" + forbidStartTimeStr
				+ ", forbidEndTime=" + forbidEndTime + ", forbidEndTimeStr="
				+ forbidEndTimeStr + ", fansDescription=" + fansDescription
				+ ", status=" + status + ", sellername=" + sellername
				+ ", filterVal=" + filterVal + "]";
	}
	
    
}