package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

public class IndianaBout extends BaseEntity{
    /**
     * ID
     */
    private Integer id;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 终止时间
     */
    private Date termTime;

    /**
     * 产品编号
     */
    private Long codeId;

    /**
     * 期数(第几期)
     */
    private Integer boutTh;

    /**
     * 已售份数
     */
    private Integer saleNum;

    /**
     * 获奖用户ID/预设获奖用户ID
     */
    private Integer uid;

    /**
     * 获奖机器人ID
     */
    private Integer robotId;

    /**
     * 获奖人类型  0:用户  1:机器人
     */
    private Integer giveType;

    /**
     * 幸运号
     */
    private Long luckynum;

    /**
     * 状态 0:夺宝中 1:已被夺 2:已退还库存(机器人获奖) 3:终止
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    private Integer proceedStatus;
    
    private Date activityEndTime;
    
    private Date beginTime;
    
    private String pname;
    
    private String title;
    
    private Integer point;
    
    private String name;
    
    private String phone;
    
    private Integer proceedStatusWeb;
    
    private String pvIds;
    
    private Integer boutResidue;
    
    private Integer veces;
    
    private String pvValue;
    
    private String breviary;
    
    private BigDecimal basePrice;
    
    
    public String getPvValue() {
		return pvValue;
	}

	public void setPvValue(String pvValue) {
		this.pvValue = pvValue;
	}

	public String getBreviary() {
		return breviary;
	}

	public void setBreviary(String breviary) {
		this.breviary = breviary;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Integer getVeces() {
		return veces;
	}

	public void setVeces(Integer veces) {
		this.veces = veces;
	}

	public Integer getBoutResidue() {
		return boutResidue;
	}

	public void setBoutResidue(Integer boutResidue) {
		this.boutResidue = boutResidue;
	}

	public String getPvIds() {
		return pvIds;
	}

	public void setPvIds(String pvIds) {
		this.pvIds = pvIds;
	}

	public Integer getProceedStatusWeb() {
    	//未开始
    			long time = new Date().getTime();
    			//System.out.println(id+"------------"+activityEndTime);
    			if(createTime.getTime()>time&&status==0&&endTime==null){
    				return 1;
    			}else if(createTime.getTime()<time&&activityEndTime.getTime()>time&&status==0&&endTime==null){
    				return 2;
    			}else{
    				return 3;
     			}
	}

	public void setProceedStatusWeb(Integer proceedStatusWeb) {
		this.proceedStatusWeb = proceedStatusWeb;
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

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getProceedStatus() {
		return proceedStatus;
	}

	public void setProceedStatus(Integer proceedStatus) {
		this.proceedStatus = proceedStatus;
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
     * 活动id
     * @return activity_id 活动id
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 活动id
     * @param activityId 活动id
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 结束时间
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 终止时间
     * @return term_time 终止时间
     */
    public Date getTermTime() {
        return termTime;
    }

    /**
     * 终止时间
     * @param termTime 终止时间
     */
    public void setTermTime(Date termTime) {
        this.termTime = termTime;
    }

    /**
     * 产品编号
     * @return codeId 产品编号
     */
    public Long getCodeId() {
        return codeId;
    }

    /**
     * 产品编号
     * @param codeid 产品编号
     */
    public void setCodeId(Long codeid) {
        this.codeId = codeid;
    }

    /**
     * 期数(第几期)
     * @return bout_th 期数(第几期)
     */
    public Integer getBoutTh() {
        return boutTh;
    }

    /**
     * 期数(第几期)
     * @param boutTh 期数(第几期)
     */
    public void setBoutTh(Integer boutTh) {
        this.boutTh = boutTh;
    }

    /**
     * 已售份数
     * @return sale_num 已售份数
     */
    public Integer getSaleNum() {
        return saleNum;
    }

    /**
     * 已售份数
     * @param saleNum 已售份数
     */
    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * 获奖用户ID/预设获奖用户ID
     * @return uid 获奖用户ID/预设获奖用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 获奖用户ID/预设获奖用户ID
     * @param uid 获奖用户ID/预设获奖用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获奖机器人ID
     * @return robot_id 获奖机器人ID
     */
    public Integer getRobotId() {
        return robotId;
    }

    /**
     * 获奖机器人ID
     * @param robotId 获奖机器人ID
     */
    public void setRobotId(Integer robotId) {
        this.robotId = robotId;
    }

    /**
     * 获奖人类型  0:用户  1:机器人
     * @return give_type 获奖人类型  0:用户  1:机器人
     */
    public Integer getGiveType() {
        return giveType;
    }

    /**
     * 获奖人类型  0:用户  1:机器人
     * @param giveType 获奖人类型  0:用户  1:机器人
     */
    public void setGiveType(Integer giveType) {
        this.giveType = giveType;
    }

    /**
     * 幸运号
     * @return luckynum 幸运号
     */
    public Long getLuckynum() {
        return luckynum;
    }

    /**
     * 幸运号
     * @param luckynum 幸运号
     */
    public void setLuckynum(Long luckynum) {
        this.luckynum = luckynum;
    }

    /**
     * 状态 0:夺宝中 1:已被夺 2:已退还库存(机器人获奖) 3:终止
     * @return status 状态 0:夺宝中 1:已被夺 2:已退还库存(机器人获奖) 3:终止
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 0:夺宝中 1:已被夺 2:已退还库存(机器人获奖) 3:终止
     * @param status 状态 0:夺宝中 1:已被夺 2:已退还库存(机器人获奖) 3:终止
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}