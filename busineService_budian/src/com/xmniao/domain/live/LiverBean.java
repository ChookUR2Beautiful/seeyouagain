package com.xmniao.domain.live;

import java.math.BigDecimal;
import java.util.Date;

public class LiverBean {
    private Integer id;

    private Integer uid;

    private Integer utype;

    private String anchorRoomNo;

    private String phone;

    private String weixin;

    private BigDecimal height;

    private BigDecimal weight;

    private Integer age;

    private String threeDimensional;

    private BigDecimal ledgerRatio;

    private Integer rankId;

    private Integer rankNo;

    private String achievement;

    private Integer currentExpe;

    private Integer concernNums;

    private Integer concernedNums;

    private Integer giveGiftsNums;

    private Integer givedGiftsNums;

    private Integer praisedNums;

    private Integer viewDurationDay;

    private Integer liveDurationDay;

    private Integer viewDuration;

    private Integer liveDuration;

    private Boolean status;

    private Integer isinside;

    private Boolean msgStatus;

    private Integer sellerLookStatus;

    private String groupId;

    private String groupName;

    private Date createTime;

    private Date updateTime;

    private String wechatPic;

    private BigDecimal saleCouponRatio;

    private BigDecimal useCouponRation;

    private Integer istlsregister;

    private Integer registerSource;

    private String wechatGroup;

    private Integer sortVal;

    private String introduceMv;

    private String linktype;

    private String officeName;

    private Integer enterpriseUid;
    
    private Integer uidRelationChainLevel;

    private Integer fansRankNo;

    private String fansRankName;

    private Long fansRankId;

    private String referrerType;

    private String uids;	//"uid,uid,uid"
    
    private String selfComment;
    
    
    private Integer redPacketAuthority;
    
    private Integer userPlatform;
    
    private Integer dividendsRole;
        
    private BigDecimal juniorLimitRatio;
    
    private Integer levelId; //等级Id
    
    private Integer rootRole; //来源身份
    
    private LiveLever liveLever; //主播等级
    
    private LiveSalaryData liveSalaryData; //礼物统计
    
    private Integer signType;  //主播类型
    
    private String msg; //统计异常信息
    
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LiveSalaryData getLiveSalaryData() {
		return liveSalaryData;
	}

	public Integer getSignType() {
		return signType;
	}

	public void setSignType(Integer signType) {
		this.signType = signType;
	}

	public void setLiveSalaryData(LiveSalaryData liveSalaryData) {
		this.liveSalaryData = liveSalaryData;
	}

	public LiveLever getLiveLever() {
		return liveLever;
	}

	public void setLiveLever(LiveLever liveLever) {
		this.liveLever = liveLever;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}


	public Integer getRootRole() {
		return rootRole;
	}

	public void setRootRole(Integer rootRole) {
		this.rootRole = rootRole;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    public String getAnchorRoomNo() {
        return anchorRoomNo;
    }

    public void setAnchorRoomNo(String anchorRoomNo) {
        this.anchorRoomNo = anchorRoomNo == null ? null : anchorRoomNo.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getThreeDimensional() {
        return threeDimensional;
    }

    public void setThreeDimensional(String threeDimensional) {
        this.threeDimensional = threeDimensional == null ? null : threeDimensional.trim();
    }

    public BigDecimal getLedgerRatio() {
        return ledgerRatio;
    }

    public void setLedgerRatio(BigDecimal ledgerRatio) {
        this.ledgerRatio = ledgerRatio;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Integer getRankNo() {
        return rankNo;
    }

    public void setRankNo(Integer rankNo) {
        this.rankNo = rankNo;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    public Integer getCurrentExpe() {
        return currentExpe;
    }

    public void setCurrentExpe(Integer currentExpe) {
        this.currentExpe = currentExpe;
    }

    public Integer getConcernNums() {
        return concernNums;
    }

    public void setConcernNums(Integer concernNums) {
        this.concernNums = concernNums;
    }

    public Integer getConcernedNums() {
        return concernedNums;
    }

    public void setConcernedNums(Integer concernedNums) {
        this.concernedNums = concernedNums;
    }

    public Integer getGiveGiftsNums() {
        return giveGiftsNums;
    }

    public void setGiveGiftsNums(Integer giveGiftsNums) {
        this.giveGiftsNums = giveGiftsNums;
    }

    public Integer getGivedGiftsNums() {
        return givedGiftsNums;
    }

    public void setGivedGiftsNums(Integer givedGiftsNums) {
        this.givedGiftsNums = givedGiftsNums;
    }

    public Integer getPraisedNums() {
        return praisedNums;
    }

    public void setPraisedNums(Integer praisedNums) {
        this.praisedNums = praisedNums;
    }

    public Integer getViewDurationDay() {
        return viewDurationDay;
    }

    public void setViewDurationDay(Integer viewDurationDay) {
        this.viewDurationDay = viewDurationDay;
    }

    public Integer getLiveDurationDay() {
        return liveDurationDay;
    }

    public void setLiveDurationDay(Integer liveDurationDay) {
        this.liveDurationDay = liveDurationDay;
    }

    public Integer getViewDuration() {
        return viewDuration;
    }

    public void setViewDuration(Integer viewDuration) {
        this.viewDuration = viewDuration;
    }

    public Integer getLiveDuration() {
        return liveDuration;
    }

    public void setLiveDuration(Integer liveDuration) {
        this.liveDuration = liveDuration;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getIsinside() {
        return isinside;
    }

    public void setIsinside(Integer isinside) {
        this.isinside = isinside;
    }

    public Boolean getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Boolean msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Integer getSellerLookStatus() {
        return sellerLookStatus;
    }

    public void setSellerLookStatus(Integer sellerLookStatus) {
        this.sellerLookStatus = sellerLookStatus;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
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

    public String getWechatPic() {
        return wechatPic;
    }

    public void setWechatPic(String wechatPic) {
        this.wechatPic = wechatPic == null ? null : wechatPic.trim();
    }

    public BigDecimal getSaleCouponRatio() {
        return saleCouponRatio;
    }

    public void setSaleCouponRatio(BigDecimal saleCouponRatio) {
        this.saleCouponRatio = saleCouponRatio;
    }

    public BigDecimal getUseCouponRation() {
        return useCouponRation;
    }

    public void setUseCouponRation(BigDecimal useCouponRation) {
        this.useCouponRation = useCouponRation;
    }

    public Integer getIstlsregister() {
        return istlsregister;
    }

    public void setIstlsregister(Integer istlsregister) {
        this.istlsregister = istlsregister;
    }

    public Integer getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(Integer registerSource) {
        this.registerSource = registerSource;
    }

    public String getWechatGroup() {
        return wechatGroup;
    }

    public void setWechatGroup(String wechatGroup) {
        this.wechatGroup = wechatGroup == null ? null : wechatGroup.trim();
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public String getIntroduceMv() {
        return introduceMv;
    }

    public void setIntroduceMv(String introduceMv) {
        this.introduceMv = introduceMv == null ? null : introduceMv.trim();
    }

    public String getLinktype() {
        return linktype;
    }

    public void setLinktype(String linktype) {
        this.linktype = linktype == null ? null : linktype.trim();
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName == null ? null : officeName.trim();
    }

    public Integer getEnterpriseUid() {
        return enterpriseUid;
    }

    public void setEnterpriseUid(Integer enterpriseUid) {
        this.enterpriseUid = enterpriseUid;
    }

    public Integer getUidRelationChainLevel() {
        return uidRelationChainLevel;
    }

    public void setUidRelationChainLevel(Integer uidRelationChainLevel) {
        this.uidRelationChainLevel = uidRelationChainLevel;
    }

    public Integer getFansRankNo() {
        return fansRankNo;
    }

    public void setFansRankNo(Integer fansRankNo) {
        this.fansRankNo = fansRankNo;
    }

    public String getFansRankName() {
        return fansRankName;
    }

    public void setFansRankName(String fansRankName) {
        this.fansRankName = fansRankName == null ? null : fansRankName.trim();
    }

    public Long getFansRankId() {
        return fansRankId;
    }

    public void setFansRankId(Long fansRankId) {
        this.fansRankId = fansRankId;
    }


    public String getReferrerType() {
        return referrerType;
    }

    public void setReferrerType(String referrerType) {
        this.referrerType = referrerType == null ? null : referrerType.trim();
    }

	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}
	
	public String getSelfComment() {
		return selfComment;
	}

	public void setSelfComment(String selfComment) {
		this.selfComment = selfComment;
	}


	public Integer getRedPacketAuthority() {
		return redPacketAuthority;
	}

	public void setRedPacketAuthority(Integer redPacketAuthority) {
		this.redPacketAuthority = redPacketAuthority;
	}

	public Integer getUserPlatform() {
		return userPlatform;
	}

	public void setUserPlatform(Integer userPlatform) {
		this.userPlatform = userPlatform;
	}


	public Integer getDividendsRole() {
		return dividendsRole;
	}

	public void setDividendsRole(Integer dividendsRole) {
		this.dividendsRole = dividendsRole;
	}


	public BigDecimal getJuniorLimitRatio() {
		return juniorLimitRatio;
	}

	public void setJuniorLimitRatio(BigDecimal juniorLimitRatio) {
		this.juniorLimitRatio = juniorLimitRatio;
	}

	@Override
	public String toString() {
		return "LiverBean [id=" + id + ", uid=" + uid + ", utype=" + utype
				+ ", anchorRoomNo=" + anchorRoomNo + ", phone=" + phone
				+ ", weixin=" + weixin + ", height=" + height + ", weight="
				+ weight + ", age=" + age + ", threeDimensional="
				+ threeDimensional + ", ledgerRatio=" + ledgerRatio
				+ ", rankId=" + rankId + ", rankNo=" + rankNo
				+ ", achievement=" + achievement + ", currentExpe="
				+ currentExpe + ", concernNums=" + concernNums
				+ ", concernedNums=" + concernedNums + ", giveGiftsNums="
				+ giveGiftsNums + ", givedGiftsNums=" + givedGiftsNums
				+ ", praisedNums=" + praisedNums + ", viewDurationDay="
				+ viewDurationDay + ", liveDurationDay=" + liveDurationDay
				+ ", viewDuration=" + viewDuration + ", liveDuration="
				+ liveDuration + ", status=" + status + ", isinside="
				+ isinside + ", msgStatus=" + msgStatus + ", sellerLookStatus="
				+ sellerLookStatus + ", groupId=" + groupId + ", groupName="
				+ groupName + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", wechatPic=" + wechatPic
				+ ", saleCouponRatio=" + saleCouponRatio + ", useCouponRation="
				+ useCouponRation + ", istlsregister=" + istlsregister
				+ ", registerSource=" + registerSource + ", wechatGroup="
				+ wechatGroup + ", sortVal=" + sortVal + ", introduceMv="
				+ introduceMv + ", linktype=" + linktype + ", officeName="
				+ officeName + ", enterpriseUid=" + enterpriseUid
				+ ", uidRelationChainLevel=" + uidRelationChainLevel
				+ ", fansRankNo=" + fansRankNo + ", fansRankName="
				+ fansRankName + ", fansRankId=" + fansRankId
				+ ", referrerType=" + referrerType + ", uids=" + uids
				+ ", selfComment=" + selfComment 
				+ ", redPacketAuthority=" + redPacketAuthority
				+ ", userPlatform=" + userPlatform + ", dividendsRole=" 
				+ dividendsRole + "juniorLimitRatio="+ juniorLimitRatio + "]";
	}

}