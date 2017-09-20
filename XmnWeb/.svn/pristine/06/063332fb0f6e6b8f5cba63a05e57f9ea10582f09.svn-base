package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFreshActivityAuction
 * 
 * 类描述： 小鸟超市竞拍活动实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-1 下午4:58:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TFreshActivityAuction extends BaseEntity{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6629355120070369835L;

	private Integer id;//主键ID

    private String title;//标题

    private Integer state;//状态  0:正常  1:终止  2:删除

    private Date beginTime;//开始时间
    
    private String beginTimeStr;//开始时间

    private Date endTime;//结束时间
    
    private String endTimeStr;

    private Date termTime;//终止时间

    private Long codeid;//产品编号
    
    private Long initCodeId;//初始产品编号
    
    private String pname;//产品名称

    private String pvIds;//属性值id组合，无序的，","作分隔符

    private String pvValue;//规格值

    private BigDecimal basePrice;//商品价格

    private BigDecimal startPrice;//起拍价
    
    private BigDecimal maxPrice;//当前最高金额
    
    private Long peopleNum;//当前参与人数

    private BigDecimal scope;//加价幅度

    private BigDecimal insurancePrice;//保险价

    private Integer productStatus;//状态 0:竞拍中  1:已售出   2:已退还库存(流拍)

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    private String userName;//获奖者名称
    
    private String phone;//获奖者手机号码
    
    private String winner;//获奖者,格式:艾伦(18944562568)
    
    private String queryStartTime;//查询开始时间
    
    private String queryEndTime;//查询结束时间
    
    private String activityType;//查询活动类型,1 全部 、2 未开始活动 、 3进行中活动 、4 已结束活动 、 5 流拍活动 
    
    private Date currentTime;//系统当前时间
    
    private Integer activityId;//竞拍活动ID
    
    private Integer proceedStatus;//进行状态 ( 1:未开始  2:进行中  3:已结束  4:流拍)
    
    private String proceedStatusStr;//进行状态字符串

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    
    
    /**
	 * @return the beginTimeStr
	 */
	public String getBeginTimeStr() {
		if(beginTime==null){
			return null;
		}
		beginTimeStr=DateUtil.formatDate(DateUtil.Y_M_D_HM,beginTime);
		return beginTimeStr;
	}

	/**
	 * @param beginTimeStr the beginTimeStr to set
	 */
	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	/**
	 * @return the endTimeStr
	 */
	public String getEndTimeStr() {
		if(endTime==null){
			return null;
		}
		endTimeStr=DateUtil.formatDate(DateUtil.Y_M_D_HM, endTime);
		return endTimeStr;
	}

	/**
	 * @param endTimeStr the endTimeStr to set
	 */
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Date getTermTime() {
        return termTime;
    }

    public void setTermTime(Date termTime) {
        this.termTime = termTime;
    }

    public Long getCodeid() {
        return codeid;
    }

    public void setCodeid(Long codeid) {
        this.codeid = codeid;
    }
    
    
    

    /**
	 * @return the initCodeId
	 */
	public Long getInitCodeId() {
		return initCodeId;
	}

	/**
	 * @param initCodeId the initCodeId to set
	 */
	public void setInitCodeId(Long initCodeId) {
		this.initCodeId = initCodeId;
	}

	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPvIds() {
        return pvIds;
    }

    public void setPvIds(String pvIds) {
        this.pvIds = pvIds == null ? null : pvIds.trim();
    }

    public String getPvValue() {
        return pvValue;
    }

    public void setPvValue(String pvValue) {
        this.pvValue = pvValue == null ? null : pvValue.trim();
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }
    

    /**
	 * @return the maxPrice
	 */
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	/**
	 * @param maxPrice the maxPrice to set
	 */
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * @return the peopleNum
	 */
	public Long getPeopleNum() {
		return peopleNum;
	}

	/**
	 * @param peopleNum the peopleNum to set
	 */
	public void setPeopleNum(Long peopleNum) {
		this.peopleNum = peopleNum;
	}

	public BigDecimal getScope() {
        return scope;
    }

    public void setScope(BigDecimal scope) {
        this.scope = scope;
    }

    public BigDecimal getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(BigDecimal insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    
    

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	/**
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}

	/**
	 * @return the queryStartTime
	 */
	public String getQueryStartTime() {
		return queryStartTime;
	}

	/**
	 * @param queryStartTime the queryStartTime to set
	 */
	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}
	
	

	/**
	 * @return the activityType
	 */
	public String getActivityType() {
		return activityType;
	}

	/**
	 * @param activityType the activityType to set
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	/**
	 * @return the queryEndTime
	 */
	public String getQueryEndTime() {
		return queryEndTime;
	}

	/**
	 * @param queryEndTime the queryEndTime to set
	 */
	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	
	/**
	 * @return the currentTime
	 */
	public Date getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime the currentTime to set
	 */
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	
	

	/**
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	
	/**
	 * @return the proceedStatus
	 */
	public Integer getProceedStatus() {
		return proceedStatus;
	}

	/**
	 * @param proceedStatus the proceedStatus to set
	 */
	public void setProceedStatus(Integer proceedStatus) {
		this.proceedStatus = proceedStatus;
	}
	
	

	/**
	 * 进行状态( 1:未开始  2:进行中  3:已结束  4:流拍)
	 * @return the proceedStatusStr
	 */
	public String getProceedStatusStr() {
		if(proceedStatus==null){
			return null;
		}
		switch (proceedStatus) {
		case 1:
			proceedStatusStr="未开始";
			break;
		case 2:
			proceedStatusStr="进行中";
			break;
		case 3:
			proceedStatusStr="已结束";
			break;
		case 4:
			proceedStatusStr="流拍";
			break;
		default:
			proceedStatusStr="未开始";
			break;
		}
		return proceedStatusStr;
	}

	/**
	 * @param proceedStatusStr the proceedStatusStr to set
	 */
	public void setProceedStatusStr(String proceedStatusStr) {
		this.proceedStatusStr = proceedStatusStr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TFreshActivityAuction [id=" + id + ", title=" + title
				+ ", state=" + state + ", beginTime=" + beginTime
				+ ", beginTimeStr=" + beginTimeStr + ", endTime=" + endTime
				+ ", endTimeStr=" + endTimeStr + ", termTime=" + termTime
				+ ", codeid=" + codeid + ", pname=" + pname + ", pvIds="
				+ pvIds + ", pvValue=" + pvValue + ", basePrice=" + basePrice
				+ ", startPrice=" + startPrice + ", maxPrice=" + maxPrice
				+ ", peopleNum=" + peopleNum + ", scope=" + scope
				+ ", insurancePrice=" + insurancePrice + ", productStatus="
				+ productStatus + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", userName=" + userName
				+ ", phone=" + phone + ", winner=" + winner
				+ ", queryStartTime=" + queryStartTime + ", queryEndTime="
				+ queryEndTime + ", activityType=" + activityType
				+ ", currentTime=" + currentTime + ", activityId=" + activityId
				+ ", proceedStatus=" + proceedStatus + ", proceedStatusStr="
				+ proceedStatusStr + "]";
	}
    
    
}