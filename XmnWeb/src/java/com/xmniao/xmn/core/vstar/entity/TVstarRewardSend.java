package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TVstarRewardSend
 * 
 * 类描述： 新时尚大赛发放奖励记录实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-8-7 上午9:54:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarRewardSend extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -912164691102153900L;

	private Long id;//主键id

    private Integer uid;//用户UID

    private String phone;//手机

    private String nname;//用户昵称

    private Integer rewardType;//奖励类型,0、消费优惠券，1、商城优惠券，5、平台通用优惠劵，6、鸟币
    
    private String rewardTypeStr;//奖励类型,0、消费优惠券，1、商城优惠券，5、平台通用优惠劵，6、鸟币

    private String rewardDescription;//奖励描述(鸟币金额)
    
    private Integer rewardNum;//奖励份数(reward_type为优惠券时，对应每种优惠券张数。为鸟币时，默认为1)
    
    private Integer cid;//优惠券ID

    private Date createTime;//创建时间
    
    private String createTimeStr;//创建时间

    private Date updateTime;//更新时间

    private Integer status;//有效状态，1 有效 ，2 无效
    
    private String startTime;//查询开始时间
    
    private String endTime;//查询结束时间
    
    private String cname;//优惠券名称
    
    private String rewardComment;//奖励备注
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    
    /**
     * 奖励类型,0、消费优惠券，1、商城优惠券，5、平台通用优惠劵，6、鸟币
	 * @return the rewardTypeStr
	 */
	public String getRewardTypeStr() {
		if(rewardType==null){
			return null;
		}
		
		switch (rewardType) {
		case 0:
			rewardTypeStr="消费优惠券";
			break;
		case 1:
			rewardTypeStr="商场优惠券";
			break;
		case 5:
			rewardTypeStr="平台通用优惠券";
			break;
		case 6:
			rewardTypeStr="鸟币";
			break;
		default:
			rewardTypeStr=null;
			break;
		}
		return rewardTypeStr;
	}

	/**
	 * @param rewardTypeStr the rewardTypeStr to set
	 */
	public void setRewardTypeStr(String rewardTypeStr) {
		this.rewardTypeStr = rewardTypeStr;
	}

	public String getRewardDescription() {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription == null ? null : rewardDescription.trim();
    }
    
    /**
     * 奖励描述
     */
    public String getRewardComment() {
    	if(rewardType!=null && rewardType.intValue()==6){
    		rewardComment=rewardDescription;
    	}else{
    		rewardComment=cname;
    	}
        return rewardComment;
    }

    /**
	 * @return the rewardNum
	 */
	public Integer getRewardNum() {
		return rewardNum;
	}

	/**
	 * @param rewardNum the rewardNum to set
	 */
	public void setRewardNum(Integer rewardNum) {
		this.rewardNum = rewardNum;
	}
	
	

	/**
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    

    /**
	 * @return the createTimeStr
	 */
	public String getCreateTimeStr() {
		if(createTime==null){
			return null;
		}
		createTimeStr=DateUtil.formatDate(createTime, DateUtil.Y_M_D_HM);
		return createTimeStr;
	}

	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVstarRewardSend [id=" + id + ", uid=" + uid + ", phone="
				+ phone + ", nname=" + nname + ", rewardType=" + rewardType
				+ ", rewardTypeStr=" + rewardTypeStr + ", rewardDescription="
				+ rewardDescription + ", rewardNum=" + rewardNum
				+ ", createTime=" + createTime + ", createTimeStr="
				+ createTimeStr + ", updateTime=" + updateTime + ", status="
				+ status + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", cname=" + cname + "]";
	}
    
    
}