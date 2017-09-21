package com.xmniao.domain.live;

import java.util.Date;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：UrsEarningsRank
 * 
 * 类描述： 会员等级表
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年4月6日 下午8:21:53 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class UrsEarningsRank {
    private Long id;

    private Long uid;

    private Integer rankSource;

    private Long rankId;

    private Date modifyTime;

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

    public Integer getRankSource() {
        return rankSource;
    }

    public void setRankSource(Integer rankSource) {
        this.rankSource = rankSource;
    }

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public UrsEarningsRank() {
		super();
	}

	public UrsEarningsRank(Long uid, Long rankId,Integer rankSource) {
		super();
		this.uid = uid;
		this.rankSource = rankSource;
		this.rankId = rankId;
		this.modifyTime = new Date();
	}

	@Override
	public String toString() {
		return "UrsEarningsRank [id=" + id + ", uid=" + uid + ", rankSource="
				+ rankSource + ", rankId=" + rankId + ", modifyTime="
				+ modifyTime + "]";
	}
}