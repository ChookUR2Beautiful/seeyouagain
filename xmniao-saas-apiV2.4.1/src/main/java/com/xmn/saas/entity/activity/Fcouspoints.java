package com.xmn.saas.entity.activity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Fcouspoints extends Activity{
    private Integer id;

    private Integer sellerid;

    private String name;

    private Date endTime;

    private Integer giveNumber;

    private Integer pointsNumber;

    private BigDecimal fullPrice;

    private Integer isSuposition;

    private Integer countPoints;

    private Integer joinNumber;

    private Integer conversionNumber;

    private Integer status;

    private Date createTime;
    
    private BigDecimal converProportion;	// 转化率
    
    private Integer views; //浏览数
    
    private Integer activityType = 4; // 活动类型 1.免尝 2.大转盘 3.秒杀   4.聚点
    
    private String sellerCouponDetailsJson;
    
    

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate; // 开始日期;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; // 结束日期
    
    private AwardRelation awardRelation;
    
    public String getSellerCouponDetailsJson() {
    	// 将每个vehicle对象拼接为json格式的对象,用于命令下发
    			ObjectMapper mapper = new ObjectMapper();
    			String json;
    			try {
    				json = mapper.writeValueAsString(awardRelation);
    				return json.toString();
    			} catch (JsonProcessingException e) {
    				e.printStackTrace();
    			}
    			// 用于命令下发
    			return "";
	}

	public void setSellerCouponDetailsJson(String sellerCouponDetailsJson) {
		this.sellerCouponDetailsJson = sellerCouponDetailsJson;
	}
    
    public AwardRelation getAwardRelation() {
		return awardRelation;
	}

	public void setAwardRelation(AwardRelation awardRelation) {
		this.awardRelation = awardRelation;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public BigDecimal getConverProportion() {
		return converProportion;
	}

	public void setConverProportion(BigDecimal converProportion) {
		this.converProportion = converProportion;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getGiveNumber() {
        return giveNumber;
    }

    public void setGiveNumber(Integer giveNumber) {
        this.giveNumber = giveNumber;
    }

    public Integer getPointsNumber() {
        return pointsNumber;
    }

    public void setPointsNumber(Integer pointsNumber) {
        this.pointsNumber = pointsNumber;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public Integer getIsSuposition() {
        return isSuposition;
    }

    public void setIsSuposition(Integer isSuposition) {
        this.isSuposition = isSuposition;
    }

    public Integer getCountPoints() {
        return countPoints;
    }

    public void setCountPoints(Integer countPoints) {
        this.countPoints = countPoints;
    }

    public Integer getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(Integer joinNumber) {
        this.joinNumber = joinNumber;
    }

    public Integer getConversionNumber() {
        return conversionNumber;
    }

    public void setConversionNumber(Integer conversionNumber) {
        this.conversionNumber = conversionNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}