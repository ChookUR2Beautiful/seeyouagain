package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

public class TLiveSalary extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * 主播用户id
     */
    private Integer anchorId;

    /**
     * 主播类型 1-签约主播 2-兼职主播
     */
    private Integer signType;

    /**
     * 状态  0未发放  1已发放
     */
    private Integer status;

    /**
     * 当月统计鸟蛋
     */
    private BigDecimal balance;

    /**
     * 应发工资
     */
    private BigDecimal baseSalary;

    /**
     * 修改后工资
     */
    private BigDecimal afterSalary;

    /**
     * 统计开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM")
    private Date beginDate;

    /**
     * 统计结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM")
    private Date endDate;

    /**
     * 有效场次
     */
    private Integer bout;

    /**
     * 级别薪酬
     */
    private BigDecimal levelIncome;

    /**
     * 浮动绩效
     */
    private BigDecimal floatPerformance;

    /**
     * 收入上限
     */
    private BigDecimal topIncome;

    /**
     * 是否已扣税( 0:否 1:是)
     */
    private Integer isTaxes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
    
    /**
     * 主播名称
     */
    private String name;
    
    /**
     * 主播电话
     */
    private String phone;
    
    /**
     * 统计时间
     */
    private String countTime;
    
    private Integer uid;
    
    private List<Integer> uids;
    
    private String avatar;
    
    private String signTypeStr;
    
    private String beginDateStr;
    
    private String endDateStr;
    
    private String levelName;
    
    private String nameExport;
    
    private String statusStr;
    
    private String timeExport;
    
    private BigDecimal percent;
    
    private String percentStr;
    
    public String getPercentStr() {
    	if(percent==null){
    		return "";
    	}
    	return percent.floatValue()*100+"%";
	}

	public void setPercentStr(String percentStr) {
		this.percentStr = percentStr;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public String getNameExport() {
		return name+"("+levelName+")"+phone;
	}

	public void setNameExport(String nameExport) {
		this.nameExport = nameExport;
	}

	public String getStatusStr() {
		if(status==null){
			return null;
		}
		switch (status) {
		case 0:
			return "未发放";
		case 1:
			return "已发放";
		default:
			break;
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getTimeExport() {
		if(beginDate!=null&&endDate!=null){
			return DateUtil.formatDate(beginDate, DateUtil.Y_M_D)+"至"+DateUtil.formatDate(endDate, DateUtil.Y_M_D);
		}
		return timeExport;
	}

	public void setTimeExport(String timeExport) {
		this.timeExport = timeExport;
	}

	public String getCountTime() {
		return countTime;
	}

	public void setCountTime(String countTime) {
		this.countTime = countTime;
		if(StringUtils.isNotBlank(countTime)){
			Integer year = Integer.valueOf(countTime.substring(0,4));
			Integer month = Integer.valueOf(countTime.substring(4,6));
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.MONTH, month-1);
			String date1 = DateUtil.formatDate(c.getTime(), DateUtil.Y_M_D);
			try {
				this.beginDate=DateUtil.formatStringToDate(date1, DateUtil.Y_M_D);
				c.add(Calendar.MONTH, 1);
				c.set(Calendar.DAY_OF_MONTH, 0);
				String date2 = DateUtil.formatDate(c.getTime(), DateUtil.Y_M_D);
				this.endDate=DateUtil.formatStringToDate(date2, DateUtil.Y_M_D);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getBeginDateStr() {
    	if(beginDate==null){
    		return null;
    	}
		return DateUtil.formatDate(beginDate, DateUtil.Y_M_D);
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getEndDateStr() {
		if(endDate==null){
    		return null;
    	}
		return DateUtil.formatDate(endDate, DateUtil.Y_M_D);
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getSignTypeStr() {
    	if(signType==null){
    		return null;
    	}
    	switch (signType) {
		case 0:
			return "兼职主播";
		case 1:
			return "签约主播";
		case 2:
			return "测试账号";
		default:
			break;
		}
    	return null;
	}
    
	public void setSignTypeStr(String signTypeStr) {
		this.signTypeStr = signTypeStr;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public List<Integer> getUids() {
		return uids;
	}

	public void setUids(List<Integer> uids) {
		this.uids = uids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 主播用户id
     * @return anchor_id 主播用户id
     */
    public Integer getAnchorId() {
        return anchorId;
    }

    /**
     * 主播用户id
     * @param anchorId 主播用户id
     */
    public void setAnchorId(Integer anchorId) {
        this.anchorId = anchorId;
    }

    /**
     * 主播类型 1-签约主播 2-兼职主播
     * @return sign_type 主播类型 1-签约主播 2-兼职主播
     */
    public Integer getSignType() {
        return signType;
    }

    /**
     * 主播类型 1-签约主播 2-兼职主播
     * @param signType 主播类型 1-签约主播 2-兼职主播
     */
    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    /**
     * 状态  0未发放  1已发放
     * @return status 状态  0未发放  1已发放
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态  0未发放  1已发放
     * @param status 状态  0未发放  1已发放
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 当月统计鸟蛋
     * @return balance 当月统计鸟蛋
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 当月统计鸟蛋
     * @param balance 当月统计鸟蛋
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 应发工资
     * @return base_salary 应发工资
     */
    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    /**
     * 应发工资
     * @param baseSalary 应发工资
     */
    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    /**
     * 修改后工资
     * @return after_salary 修改后工资
     */
    public BigDecimal getAfterSalary() {
        return afterSalary;
    }

    /**
     * 修改后工资
     * @param afterSalary 修改后工资
     */
    public void setAfterSalary(BigDecimal afterSalary) {
        this.afterSalary = afterSalary;
    }

    /**
     * 统计开始时间
     * @return begin_date 统计开始时间
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 统计开始时间
     * @param beginDate 统计开始时间
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 统计结束时间
     * @return end_date 统计结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 统计结束时间
     * @param endDate 统计结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 有效场次
     * @return bout 有效场次
     */
    public Integer getBout() {
        return bout;
    }

    /**
     * 有效场次
     * @param bout 有效场次
     */
    public void setBout(Integer bout) {
        this.bout = bout;
    }

    /**
     * 级别薪酬
     * @return level_income 级别薪酬
     */
    public BigDecimal getLevelIncome() {
        return levelIncome;
    }

    /**
     * 级别薪酬
     * @param levelIncome 级别薪酬
     */
    public void setLevelIncome(BigDecimal levelIncome) {
        this.levelIncome = levelIncome;
    }

    /**
     * 浮动绩效
     * @return float_performance 浮动绩效
     */
    public BigDecimal getFloatPerformance() {
        return floatPerformance;
    }

    /**
     * 浮动绩效
     * @param floatPerformance 浮动绩效
     */
    public void setFloatPerformance(BigDecimal floatPerformance) {
        this.floatPerformance = floatPerformance;
    }

    /**
     * 收入上限
     * @return top_income 收入上限
     */
    public BigDecimal getTopIncome() {
        return topIncome;
    }

    /**
     * 收入上限
     * @param topIncome 收入上限
     */
    public void setTopIncome(BigDecimal topIncome) {
        this.topIncome = topIncome;
    }

    /**
     * 是否已扣税( 0:否 1:是)
     * @return is_taxes 是否已扣税( 0:否 1:是)
     */
    public Integer getIsTaxes() {
        return isTaxes;
    }

    /**
     * 是否已扣税( 0:否 1:是)
     * @param isTaxes 是否已扣税( 0:否 1:是)
     */
    public void setIsTaxes(Integer isTaxes) {
        this.isTaxes = isTaxes;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}