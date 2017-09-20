package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb_412
 * 
 * 类名称：BursEarningsRank
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-8 下午4:54:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BursEarningsRank extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6441094370918861844L;

	private Long id;//主键

    private Long uid;//会员UId

    private Integer rankSource;//收益等级来源，0：未知，1:壕赚VIP，2:壕赚直销，3:壕赚商户充值，4:V客

    private Long rankId;//等级id，关联b_live_fans_rank表rank_id字段
    
    private String rankName;//等级名称

    private Date modifyTime;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }


    
    /**
	 * @return the rankSource
	 */
	public Integer getRankSource() {
		return rankSource;
	}

	/**
	 * @param rankSource the rankSource to set
	 */
	public void setRankSource(Integer rankSource) {
		this.rankSource = rankSource;
	}


    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }
    
    

    /**
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BursEarningsRank [id=" + id + ", uid=" + uid + ", rankSource="
				+ rankSource + ", rankId=" + rankId + ", rankName=" + rankName
				+ ", modifyTime=" + modifyTime + "]";
	}
    
    
}