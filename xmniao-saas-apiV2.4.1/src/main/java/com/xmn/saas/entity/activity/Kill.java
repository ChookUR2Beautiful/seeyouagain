package com.xmn.saas.entity.activity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;

public class Kill extends Activity{
    private Integer id;  

    private Integer sellerid; //商户id'

    private String name; //活动名称
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;  //开始日期

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;	//结束日期

    @DateTimeFormat(pattern = "HH:mm")
    private Date beginTime;	//开始时间

    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;	//结束时间

    private Integer joinNumber;	//参加人数

    private Integer giveNumber;	//赠品数量

    private BigDecimal secKillAmount;	//秒杀金额

    private Integer secKillNumber;	//秒杀次数

    private Integer limitNumber;	//每人限获（0：不限制，1:限领一次)

    private Integer views;	//曝光次数

    private Integer status;	//状态 0：启用 1：禁用
    
    private Date createTime;	//创建时间'

    private Integer newuserNum;	//新会员数',

    private BigDecimal consumeAmount;	//刺激消费金额
    
    private Date endActivityTime;	//活动终止时间
    
    private AwardRelation[] awardRelations; // 礼物数组
	
	//json格式的礼物数组
	private String sellerCouponDetailsJson;
	
	private Integer activityType=3;		//获得类型 1.免尝  2.大转盘   3.秒杀
	
	private Integer useNumber;	//使用数量
	
	public Integer getUseNumber() {
		return useNumber;
	}

	public void setUseNumber(Integer useNumber) {
		this.useNumber = useNumber;
	}

	public Integer getActivityType() {
		return activityType;
	}
	
	public float getGiveAwardCount() {
		return giveAwardCount==null?0:giveAwardCount;
	}

	public String getSellerCouponDetailsJson() {
		//将每个vehicle对象拼接为json格式的对象,用于命令下发
		ObjectMapper mapper =new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(awardRelations);
			System.out.println(json);
			return json.toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//用于命令下发
		return "";
	}
	
    public Date getEndActivityTime() {
		return endActivityTime;
	}

	public void setEndActivityTime(Date endActivityTime) {
		this.endActivityTime = endActivityTime;
	}

	public AwardRelation[] getAwardRelations() {
		return awardRelations;
	}

	public void setAwardRelations(AwardRelation[] awardRelation) {
		this.awardRelations = awardRelation;
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

    public Integer getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(Integer joinNumber) {
        this.joinNumber = joinNumber;
    }

    public Integer getGiveNumber() {
        return giveNumber;
    }

    public void setGiveNumber(Integer giveNumber) {
        this.giveNumber = giveNumber;
    }

    public BigDecimal getSecKillAmount() {
        return secKillAmount;
    }

    public void setSecKillAmount(BigDecimal secKillAmount) {
        this.secKillAmount = secKillAmount;
    }

    public Integer getSecKillNumber() {
        return secKillNumber;
    }

    public void setSecKillNumber(Integer secKillNumber) {
        this.secKillNumber = secKillNumber;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNewuserNum() {
        return newuserNum;
    }

    public void setNewuserNum(Integer newuserNum) {
        this.newuserNum = newuserNum;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
    
    
}