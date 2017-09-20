package com.xmniao.xmn.core.manor.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class TManorMarketTrans extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1365880734195588643L;

	private Long id;

    private Integer transType;

    private Integer uid;

    private String nname;

    private String phone;
    
    private String title;

    private Integer transState;

    private Integer propType;

    private Integer propNumber;

    private Double unitPrice;

    private Double rulingPrice;

    private Date initiateDate;

    private Date updateDate;
    
    /*自定义字段开始*/
    
    private Integer chatNumber;   //信息回复
    
	private String transTypeDesc;  //交易类型 1.出售 2.求购
	
	private String transStateDesc;  //交易状态 0.发布中 1.已结束 2.已删除
	
    private String levelName;
    
    public Integer getChatNumber() {
		return chatNumber;
	}

	public void setChatNumber(Integer chatNumber) {
		this.chatNumber = chatNumber;
	}
	
	public String getTransTypeDesc() {
		if (this.transType == null) {
			return "-";
		}
		if (transType == 1) {// 收支渠道
			return "出售";
		} else if (transType == 2) {
			return "求购";
		} else {
			return "其他";
		}
	}

	public void setTransTypeDesc(String transTypeDesc) {
		this.transTypeDesc = transTypeDesc;
	}

	public String getTransStateDesc() {
		//交易状态 0.发布中 1.已结束 2.已删除
		if (this.transState == null) {
			return "-";
		}
		if (transState == 0) {
			return "发布中";
		} else if (transState == 1) {
			return "已结束";
		} else if (transState == 2) {
			return "已删除";
		} else {
			return "其他";
		}
	}

	public void setTransStateDesc(String transStateDesc) {
		this.transStateDesc = transStateDesc;
	}
	

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

    /*自定义字段结束*/

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTransState() {
        return transState;
    }

    public void setTransState(Integer transState) {
        this.transState = transState;
    }

    public Integer getPropType() {
        return propType;
    }

    public void setPropType(Integer propType) {
        this.propType = propType;
    }

    public Integer getPropNumber() {
        return propNumber;
    }

    public void setPropNumber(Integer propNumber) {
        this.propNumber = propNumber;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getRulingPrice() {
        return rulingPrice;
    }

    public void setRulingPrice(Double rulingPrice) {
        this.rulingPrice = rulingPrice;
    }
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getInitiateDate() {
        return initiateDate;
    }

    public void setInitiateDate(Date initiateDate) {
        this.initiateDate = initiateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	@Override
	public String toString() {
		return "TManorMarketTrans [id=" + id + ", transType=" + transType
				+ ", uid=" + uid + ", nname=" + nname + ", phone=" + phone
				+ ", title=" + title + ", transState=" + transState
				+ ", propType=" + propType + ", propNumber=" + propNumber
				+ ", unitPrice=" + unitPrice + ", rulingPrice=" + rulingPrice
				+ ", initiateDate=" + initiateDate + ", updateDate="
				+ updateDate + ", chatNumber=" + chatNumber
				+ ", transTypeDesc=" + transTypeDesc + ", transStateDesc="
				+ transStateDesc + "]";
	}

    
    
}