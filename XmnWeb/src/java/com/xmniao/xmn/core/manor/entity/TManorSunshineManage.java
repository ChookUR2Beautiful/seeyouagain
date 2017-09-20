package com.xmniao.xmn.core.manor.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TManorSunshineManage extends TManorUserInfo{
    /**
	 *阳光渠道管理 
	 */
	private static final long serialVersionUID = 4261895130033531171L;

	private Integer id;

    private Integer recommendNumber; //推荐奖励获得阳光

    private Integer isSpendEnergy;  // 直属下级消耗

    private Integer spendEnergyNumber;

    private Integer handselNumber;  //园友赠送阳光
    
    //　自定义字段
    private Integer baseNumber;
    
    private BigDecimal profit;
    
    private String sunshineProfitJson;
    
    private Integer uid;
    
    private Integer type;  //操作类型 1.增加 2.减少
    
    private Integer channel;  //收支渠道
    
    private Integer giveUid;  //赠送人
    
    private String giveName;  //赠送人名称
    
    private BigDecimal num;  //数量
    
    private Date createTime;  //记录时间
    
	private String channelDesc;  //收支渠道

	public Integer getBaseNumber() {
		return baseNumber;
	}

	public void setBaseNumber(Integer baseNumber) {
		this.baseNumber = baseNumber;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	public String getSunshineProfitJson() {
		return sunshineProfitJson;
	}

	public void setSunshineProfitJson(String sunshineProfitJson) {
		this.sunshineProfitJson = sunshineProfitJson;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getGiveUid() {
		return giveUid;
	}

	public void setGiveUid(Integer giveUid) {
		this.giveUid = giveUid;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getGiveName() {
		return giveName;
	}

	public void setGiveName(String giveName) {
		this.giveName = giveName;
	}
	
	public String getChannelDesc() {
		if (this.channel == null) {
			return "-";
		}
		if (channel == 17) {// 收支渠道
			return "推荐奖励";
		} else if (channel == 10) {
			return "园友赠送";
		} else if (channel == 15) {
			return "仓库存储收益";
		} else {
			return "其他";
		}
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}

    //　自定义字段结束


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecommendNumber() {
        return recommendNumber;
    }

    public void setRecommendNumber(Integer recommendNumber) {
        this.recommendNumber = recommendNumber;
    }

    public Integer getIsSpendEnergy() {
        return isSpendEnergy;
    }

    public void setIsSpendEnergy(Integer isSpendEnergy) {
        this.isSpendEnergy = isSpendEnergy;
    }

    public Integer getSpendEnergyNumber() {
        return spendEnergyNumber;
    }

    public void setSpendEnergyNumber(Integer spendEnergyNumber) {
        this.spendEnergyNumber = spendEnergyNumber;
    }

    public Integer getHandselNumber() {
        return handselNumber;
    }

    public void setHandselNumber(Integer handselNumber) {
        this.handselNumber = handselNumber;
    }

	@Override
	public String toString() {
		return "TManorSunshineManage [id=" + id + ", recommendNumber="
				+ recommendNumber + ", isSpendEnergy=" + isSpendEnergy
				+ ", spendEnergyNumber=" + spendEnergyNumber
				+ ", handselNumber=" + handselNumber + ", baseNumber="
				+ baseNumber + ", profit=" + profit + ", sunshineProfitJson="
				+ sunshineProfitJson + ", uid=" + uid + ", type=" + type
				+ ", channel=" + channel + ", giveUid=" + giveUid
				+ ", giveName=" + giveName + ", num=" + num + ", createTime="
				+ createTime + ", channelDesc=" + channelDesc + "]";
	}

}