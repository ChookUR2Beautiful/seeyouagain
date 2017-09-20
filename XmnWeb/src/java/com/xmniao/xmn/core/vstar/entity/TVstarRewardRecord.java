package com.xmniao.xmn.core.vstar.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarRewardRecord
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-7-17 下午4:07:51 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarRewardRecord extends BaseEntity{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1709105659239370485L;

	private Long id;//业务主键ID

    private Integer playerId;//选手id,(t_vstar_player_info主键)
    
    private Integer playerUid;//选手UID

    private BigDecimal receiveCoin;//获得鸟币

    private Date receiveTime;//获得时间
    
    private String receiveTimeStr;//获得时间

    private Integer receiveStatus;//领取状态，1领取成功，2领取失败

    private Integer readStatus;//阅读状态，1未读，2已读

    private Integer status;//有效状态，1有效，2无效。默认1

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    private String nname;//选手名称
    
    private String phone;//选手手机
    
    private Integer referrerUid;//推荐人UID
    
    private String referrerName;//推荐人名称
    
    private String referrerPhone;//推荐人手机

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
    
    

    /**
	 * @return the playerUid
	 */
	public Integer getPlayerUid() {
		return playerUid;
	}

	/**
	 * @param playerUid the playerUid to set
	 */
	public void setPlayerUid(Integer playerUid) {
		this.playerUid = playerUid;
	}

	public BigDecimal getReceiveCoin() {
        return receiveCoin;
    }

    public void setReceiveCoin(BigDecimal receiveCoin) {
        this.receiveCoin = receiveCoin;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
    
    

    /**
	 * @return the receiveTimeStr
	 */
	public String getReceiveTimeStr() {
		if(receiveTime==null){
			return null;
		}else{
			receiveTimeStr=DateUtil.formatDate(receiveTime, DateUtil.Y_M_D_HM);
		}
		
		return receiveTimeStr;
	}

	/**
	 * @param receiveTimeStr the receiveTimeStr to set
	 */
	public void setReceiveTimeStr(String receiveTimeStr) {
		this.receiveTimeStr = receiveTimeStr;
	}

	public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    

	/**
	 * @return the nname
	 */
	public String getNname() {
		return nname;
	}

	/**
	 * @param nname the nname to set
	 */
	public void setNname(String nname) {
		this.nname = nname;
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
	 * @return the referrerUid
	 */
	public Integer getReferrerUid() {
		return referrerUid;
	}

	/**
	 * @param referrerUid the referrerUid to set
	 */
	public void setReferrerUid(Integer referrerUid) {
		this.referrerUid = referrerUid;
	}

	/**
	 * @return the referrerName
	 */
	public String getReferrerName() {
		return referrerName;
	}

	/**
	 * @param referrerName the referrerName to set
	 */
	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}

	/**
	 * @return the referrerPhone
	 */
	public String getReferrerPhone() {
		return referrerPhone;
	}

	/**
	 * @param referrerPhone the referrerPhone to set
	 */
	public void setReferrerPhone(String referrerPhone) {
		this.referrerPhone = referrerPhone;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVstarRewardRecord [id=" + id + ", playerId=" + playerId
				+ ", receiveCoin=" + receiveCoin + ", receiveTime="
				+ receiveTime + ", receiveStatus=" + receiveStatus
				+ ", readStatus=" + readStatus + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", nname=" + nname + ", phone=" + phone + ", referrerName="
				+ referrerName + ", referrerPhone=" + referrerPhone + "]";
	}
    
    
}