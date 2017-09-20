package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveGivedGift
 * 
 * 类描述： 用户购买赠送礼物实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-27 下午1:45:58 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveGivedGift extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6952292465928388085L;

	private Integer id;//业务主键

    private String givedGiftKey;//赠送记录唯一序列号（redis校验）

    private Integer liveRecordId;//直播记录id
    
    private String[] liveRecordIds;//直播记录IDS

    private Integer liverId;//观看直播的用户ID

    private Integer walletRecordId;//钱包扣款记录ID

    private Integer liverUtype;//直播用户类型： 1 主播 2 普通用户

    private Integer anchorId;//主播用户id
    
    private String anchorName;//主播名称

    private String anchorRoomNo;//主播房间编号

    private Integer giftId;//礼物id

    private String giftName;//礼物名称

    private Integer giftNums;//礼物个数

    private Integer giftPrice;//礼物单价（单位鸟币）

    private Integer giftExperience;//礼物代表的经验值

    private Double percentamount;//主播分账金额

	private Boolean status;//扣款状态  1 扣款成功  2 扣款失败

    private Integer advancedStatus;//预处理状态  1 未接受  2 预接收 3 已接收

    private Integer isfailed;//是否接收失败 默认: 0成功，失败1

    private Date createTime;//创建时间
    
    private String createTimeStr;//创建时间字符串

    private Date updateTime;//更新时间

    private Long returnCoin;//返还鸟币

    private String uidRelationChain;//会员关系链，规则:每一级用'',''隔开，包含自身，每一级均为11位uid的字符串，不足11位在uid前以''0''填充。如当前uid为112,其关系链为0000001000,0000002000,00000000112
    
    private Long eUid;//企业级uid，会员关系链包含企业级uid。则该用户属于该企业用户下线会员
    
    private String startTime;//打赏开始时间
    
    private String endTime;//打赏结束时间
    
    private String timeType;//查询时间类型 1昨天,2最近一周,3最近一个月,4自定义时间
    
    //====会员信息
    private Integer uid;//寻蜜鸟 会员id
    
    private String nickname;//用户昵称
    
    private String phone;//手机号码
    
    private String topLevel;//上上级
    
    private String superior;//上级
    
    private Integer enterpriseUid;//企业级uid
    
    private String enterpriseNname;//企业级昵称
    
    private List<String> juniorUids;//下级uid集合
    
    private BigDecimal ledgerRatio;  //分账比例
    
    private List<Integer> uids;  //id搜索条件
    
    private Long giveEgg; //获得鸟蛋数
    
    private Integer saleProductId; 

    public Integer getSaleProductId() {
		return saleProductId;
	}

	public void setSaleProductId(Integer saleProductId) {
		this.saleProductId = saleProductId;
	}

	public Double getPercentamount() {
		return percentamount;
	}

	public void setPercentamount(Double percentamount) {
		this.percentamount = percentamount;
	}
    
    public Long getGiveEgg() {
    	if(giftPrice==null||ledgerRatio==null){
    		return null;
    	}
		return ledgerRatio.multiply(new BigDecimal(giftPrice*100)).longValue();
	} 

	public void setGiveEgg(Long giveEgg) {
		this.giveEgg = giveEgg;
	}

	public List<Integer> getUids() {
		return uids;
	}

	public void setUids(List<Integer> uids) {
		this.uids = uids;
	}

	public BigDecimal getLedgerRatio() {
		return ledgerRatio;
	}

	public void setLedgerRatio(BigDecimal ledgerRatio) {
		this.ledgerRatio = ledgerRatio;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGivedGiftKey() {
        return givedGiftKey;
    }

    public void setGivedGiftKey(String givedGiftKey) {
        this.givedGiftKey = givedGiftKey == null ? null : givedGiftKey.trim();
    }

    public Integer getLiveRecordId() {
        return liveRecordId;
    }

    public void setLiveRecordId(Integer liveRecordId) {
        this.liveRecordId = liveRecordId;
    }
    
    

    /**
	 * @return the liveRecordIds
	 */
	public String[] getLiveRecordIds() {
		return liveRecordIds;
	}

	/**
	 * @param liveRecordIds the liveRecordIds to set
	 */
	public void setLiveRecordIds(String[] liveRecordIds) {
		this.liveRecordIds = liveRecordIds;
	}

	public Integer getLiverId() {
        return liverId;
    }

    public void setLiverId(Integer liverId) {
        this.liverId = liverId;
    }

    public Integer getWalletRecordId() {
        return walletRecordId;
    }

    public void setWalletRecordId(Integer walletRecordId) {
        this.walletRecordId = walletRecordId;
    }

    public Integer getLiverUtype() {
        return liverUtype;
    }

    public void setLiverUtype(Integer liverUtype) {
        this.liverUtype = liverUtype;
    }

    public Integer getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(Integer anchorId) {
        this.anchorId = anchorId;
    }
    
    

    /**
	 * @return the anchorName
	 */
	public String getAnchorName() {
		return anchorName;
	}

	/**
	 * @param anchorName the anchorName to set
	 */
	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}

	public String getAnchorRoomNo() {
        return anchorRoomNo;
    }

    public void setAnchorRoomNo(String anchorRoomNo) {
        this.anchorRoomNo = anchorRoomNo == null ? null : anchorRoomNo.trim();
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName == null ? null : giftName.trim();
    }

    public Integer getGiftNums() {
        return giftNums;
    }

    public void setGiftNums(Integer giftNums) {
        this.giftNums = giftNums;
    }

    public Integer getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(Integer giftPrice) {
        this.giftPrice = giftPrice;
    }

    public Integer getGiftExperience() {
        return giftExperience;
    }

    public void setGiftExperience(Integer giftExperience) {
        this.giftExperience = giftExperience;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAdvancedStatus() {
        return advancedStatus;
    }

    public void setAdvancedStatus(Integer advancedStatus) {
        this.advancedStatus = advancedStatus;
    }

    public Integer getIsfailed() {
        return isfailed;
    }

    public void setIsfailed(Integer isfailed) {
        this.isfailed = isfailed;
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
		return createTimeStr=DateUtil.formatDate(createTime, DateUtil.Y_M_D_HMS);
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

    public Long getReturnCoin() {
        return returnCoin;
    }

    public void setReturnCoin(Long returnCoin) {
        this.returnCoin = returnCoin;
    }

    public String getUidRelationChain() {
        return uidRelationChain;
    }

    public void setUidRelationChain(String uidRelationChain) {
        this.uidRelationChain = uidRelationChain == null ? null : uidRelationChain.trim();
    }

	/**
	 * @return the eUid
	 */
	public Long geteUid() {
		return eUid;
	}

	/**
	 * @param eUid the eUid to set
	 */
	public void seteUid(Long eUid) {
		this.eUid = eUid;
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
	 * @return the timeType
	 */
	public String getTimeType() {
		return timeType;
	}

	/**
	 * @param timeType the timeType to set
	 */
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
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
	 * @return the topLevel
	 */
	public String getTopLevel() {
		return topLevel;
	}

	/**
	 * @param topLevel the topLevel to set
	 */
	public void setTopLevel(String topLevel) {
		this.topLevel = topLevel;
	}

	/**
	 * @return the superior
	 */
	public String getSuperior() {
		return superior;
	}

	/**
	 * @param superior the superior to set
	 */
	public void setSuperior(String superior) {
		this.superior = superior;
	}

	/**
	 * @return the enterpriseUid
	 */
	public Integer getEnterpriseUid() {
		return enterpriseUid;
	}

	/**
	 * @param enterpriseUid the enterpriseUid to set
	 */
	public void setEnterpriseUid(Integer enterpriseUid) {
		this.enterpriseUid = enterpriseUid;
	}

	/**
	 * @return the enterpriseNname
	 */
	public String getEnterpriseNname() {
		return enterpriseNname;
	}

	/**
	 * @param enterpriseNname the enterpriseNname to set
	 */
	public void setEnterpriseNname(String enterpriseNname) {
		this.enterpriseNname = enterpriseNname;
	}
	
	

	/**
	 * @return the juniorUids
	 */
	public List<String> getJuniorUids() {
		return juniorUids;
	}

	/**
	 * @param juniorUids the juniorUids to set
	 */
	public void setJuniorUids(List<String> juniorUids) {
		this.juniorUids = juniorUids;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveGivedGift [id=" + id + ", givedGiftKey=" + givedGiftKey
				+ ", liveRecordId=" + liveRecordId + ", liverId=" + liverId
				+ ", walletRecordId=" + walletRecordId + ", liverUtype="
				+ liverUtype + ", anchorId=" + anchorId + ", anchorName="
				+ anchorName + ", anchorRoomNo=" + anchorRoomNo + ", giftId="
				+ giftId + ", giftName=" + giftName + ", giftNums=" + giftNums
				+ ", giftPrice=" + giftPrice + ", giftExperience="
				+ giftExperience + ", percentamount=" + percentamount
				+ ", status=" + status + ", advancedStatus=" + advancedStatus
				+ ", isfailed=" + isfailed + ", createTime=" + createTime
				+ ", createTimeStr=" + createTimeStr + ", updateTime="
				+ updateTime + ", returnCoin=" + returnCoin
				+ ", uidRelationChain=" + uidRelationChain + ", eUid=" + eUid
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", timeType=" + timeType + ", uid=" + uid + ", nickname="
				+ nickname + ", phone=" + phone + ", topLevel=" + topLevel
				+ ", superior=" + superior + ", enterpriseUid=" + enterpriseUid
				+ ", enterpriseNname=" + enterpriseNname + ", juniorUids="
				+ juniorUids + "]";
	}
    
    
}