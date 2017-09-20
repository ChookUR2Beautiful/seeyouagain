package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.NumberUtil;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveAnchor
 *
 * 类描述：主播实体类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午3:17:05
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveAnchor extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2595491907386773934L;

	private Integer id;//主播 用户id

    private Integer uid;//主播 会员id

    private Integer anchorRoomNo;//主播房间编号
    
    private String name;//主播真实姓名

    private String nickname;//主播用户昵称

    private String avatar;//头像
    
    private Integer sex;//性别
    
    private String password;///密码

    private String phone;//手机号码

    private String idcard;//身份证
    
    private String birthday;//出生日期

    private String email;//邮箱

    private String weixin;//微信号

    private Integer age;//年龄

    private BigDecimal height;//身高

    private BigDecimal weight;//体重

    private String threeDimensional;//三围
    
    private String selfComment;//主播个人说明
    
    private String selfSignature;//主播个性签名

    private Integer regcity;//注册时所在城市

    private String achievement;//主播头衔名称

    private BigDecimal ledgerRatio;//主播分账比例,0.75
    
    private String ledgerRatioSign;//主播分账比例,75%

	private Integer rankId;//等级ID
    
    private Integer rankNo;//等级

    private Integer concernedNums;//被关注数

    private Integer concernNums;//关注别人数

    private Integer givedGiftsNums;//被送礼物数

    private Integer praisedNums;//被点赞数

    private Integer status;//默认 1启用   0停用
    
    private Integer sellerLookStatus;//商家是否可见： 1是   0否
    
    private Integer tlsSetStatus;//腾讯云资料设置状态 ： 1 已设置   0 未设置

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    private String bursPassword;//寻蜜鸟会员密码

    private boolean isBinding;//是否绑定寻蜜鸟会员信息
    
    private BigDecimal liveMinute;//直播时长(分钟)
    
    private String liveTimeStr;//直播时长(格式： 20小时50分)
    
    private Integer playbackNum;//回放视频数
    
    private Integer recordNum;//通告记录数

    /**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAnchorRoomNo() {
        return anchorRoomNo;
    }

    public void setAnchorRoomNo(Integer anchorRoomNo) {
        this.anchorRoomNo = anchorRoomNo;
    }

    

    /**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
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

	public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }
    
    

    /**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

   

    /**
	 * @return the weixin
	 */
	public String getWeixin() {
		return weixin;
	}

	/**
	 * @param weixin the weixin to set
	 */
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getThreeDimensional() {
        return threeDimensional;
    }

    public void setThreeDimensional(String threeDimensional) {
        this.threeDimensional = threeDimensional == null ? null : threeDimensional.trim();
    }

    public Integer getRegcity() {
        return regcity;
    }

    public void setRegcity(Integer regcity) {
        this.regcity = regcity;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    public BigDecimal getLedgerRatio() {
        return ledgerRatio;
    }

    public void setLedgerRatio(BigDecimal ledgerRatio) {
        this.ledgerRatio = ledgerRatio;
    }

    

    /**
	 * @return the ledgerRatioSign
	 */
	public String getLedgerRatioSign() {
		BigDecimal multiplicand=new BigDecimal(100);
		if(ledgerRatio!=null){
			ledgerRatioSign = NumberUtil.getNumberFixedpoint(ledgerRatio.multiply(multiplicand), 0)+"%";
		}
		return ledgerRatioSign;
	}

	/**
	 * @param ledgerRatioSign the ledgerRatioSign to set
	 */
	public void setLedgerRatioSign(String ledgerRatioSign) {
		this.ledgerRatioSign = ledgerRatioSign;
	}

	/**
	 * @return the rankId
	 */
	public Integer getRankId() {
		return rankId;
	}

	/**
	 * @param rankId the rankId to set
	 */
	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public Integer getConcernedNums() {
        return concernedNums;
    }

    public void setConcernedNums(Integer concernedNums) {
        this.concernedNums = concernedNums;
    }

    public Integer getConcernNums() {
        return concernNums;
    }

    public void setConcernNums(Integer concernNums) {
        this.concernNums = concernNums;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

    /**
	 * @return the selfComment
	 */
	public String getSelfComment() {
		return selfComment;
	}

	/**
	 * @param selfComment the selfComment to set
	 */
	public void setSelfComment(String selfComment) {
		this.selfComment = selfComment;
	}

	/**
	 * @return the sellerLookStatus
	 */
	public Integer getSellerLookStatus() {
		return sellerLookStatus;
	}

	/**
	 * @param sellerLookStatus the sellerLookStatus to set
	 */
	public void setSellerLookStatus(Integer sellerLookStatus) {
		this.sellerLookStatus = sellerLookStatus;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	/**
	 * @return the selfSignature
	 */
	public String getSelfSignature() {
		return selfSignature;
	}

	/**
	 * @param selfSignature the selfSignature to set
	 */
	public void setSelfSignature(String selfSignature) {
		this.selfSignature = selfSignature;
	}

	/**
	 * @return the rankNo
	 */
	public Integer getRankNo() {
		return rankNo;
	}

	/**
	 * @param rankNo the rankNo to set
	 */
	public void setRankNo(Integer rankNo) {
		this.rankNo = rankNo;
	}

	/**
	 * @return the tlsSetStatus
	 */
	public Integer getTlsSetStatus() {
		return tlsSetStatus;
	}

	/**
	 * @param tlsSetStatus the tlsSetStatus to set
	 */
	public void setTlsSetStatus(Integer tlsSetStatus) {
		this.tlsSetStatus = tlsSetStatus;
	}
	
	

	/**
	 * @return the bursPassword
	 */
	public String getBursPassword() {
		return bursPassword;
	}

	/**
	 * @param bursPassword the bursPassword to set
	 */
	public void setBursPassword(String bursPassword) {
		this.bursPassword = bursPassword;
	}

	

	/**
	 * @return the isBinding
	 */
	public boolean isBinding() {
		return isBinding;
	}

	/**
	 * @param isBinding the isBinding to set
	 */
	public void setBinding(boolean isBinding) {
		this.isBinding = isBinding;
	}

	
	/**
	 * @return the liveMinute
	 */
	public BigDecimal getLiveMinute() {
		return liveMinute;
	}

	/**
	 * @param liveMinute the liveMinute to set
	 */
	public void setLiveMinute(BigDecimal liveMinute) {
		this.liveMinute = liveMinute;
	}

	
	/**
	 * @return the liveTimeStr
	 */
	public String getLiveTimeStr() {
		long hours=0;
		Integer minute=0;
		hours = liveMinute.divideToIntegralValue(new BigDecimal(60)).longValue();
		minute = liveMinute.remainder(new BigDecimal(60)).intValue();
		liveTimeStr=(hours==0?"":hours+"小时")+minute+"分钟";
		return liveTimeStr ;
	}

	/**
	 * @param liveTimeStr the liveTimeStr to set
	 */
	public void setLiveTimeStr(String liveTimeStr) {
		this.liveTimeStr = liveTimeStr;
	}

	/**
	 * @return the playbackNum
	 */
	public Integer getPlaybackNum() {
		return playbackNum;
	}

	/**
	 * @param playbackNum the playbackNum to set
	 */
	public void setPlaybackNum(Integer playbackNum) {
		this.playbackNum = playbackNum;
	}

	/**
	 * @return the recordNum
	 */
	public Integer getRecordNum() {
		return recordNum;
	}

	/**
	 * @param recordNum the recordNum to set
	 */
	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveAnchor [id=" + id + ", uid=" + uid + ", anchorRoomNo="
				+ anchorRoomNo + ", name=" + name + ", nickname=" + nickname
				+ ", avatar=" + avatar + ", sex=" + sex + ", password="
				+ password + ", phone=" + phone + ", idcard=" + idcard
				+ ", birthday=" + birthday + ", email=" + email + ", weixin="
				+ weixin + ", age=" + age + ", height=" + height + ", weight="
				+ weight + ", threeDimensional=" + threeDimensional
				+ ", selfComment=" + selfComment + ", selfSignature="
				+ selfSignature + ", regcity=" + regcity + ", achievement="
				+ achievement + ", ledgerRatio=" + ledgerRatio
				+ ", ledgerRatioSign=" + ledgerRatioSign + ", rankId=" + rankId
				+ ", rankNo=" + rankNo + ", concernedNums=" + concernedNums
				+ ", concernNums=" + concernNums + ", givedGiftsNums="
				+ givedGiftsNums + ", praisedNums=" + praisedNums + ", status="
				+ status + ", sellerLookStatus=" + sellerLookStatus
				+ ", tlsSetStatus=" + tlsSetStatus + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", bursPassword="
				+ bursPassword + ", isBinding=" + isBinding + ", liveMinute="
				+ liveMinute + ", liveTimeStr=" + liveTimeStr
				+ ", playbackNum=" + playbackNum + ", recordNum=" + recordNum
				+ "]";
	}
    
    
}