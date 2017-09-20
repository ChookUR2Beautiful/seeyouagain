package com.xmniao.xmn.core.manor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class TManorMarketTransDynamic extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1908155937953678446L;

	private Long id;

    private Integer dialogueType;  //动态类型 1.出价 2.留言

    private Long transId;
    
    private Long priceId;
    
    private Integer pid;

    private Integer uid;

    private Integer receiveUid;

    private Double rulingPrice;

    private String content;  //留言

    private Date createDate;
    
    /*自定义字段开始*/
    private String nname;

    private String phone;
    
    private String reversion;
    
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
    
    public String getReversion() {
		return reversion;
	}

	public void setReversion(String reversion) {
		this.reversion = reversion;
	}
    
    /*自定义字段结束*/


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDialogueType() {
        return dialogueType;
    }

    public void setDialogueType(Integer dialogueType) {
        this.dialogueType = dialogueType;
    }

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }
    
    public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getReceiveUid() {
        return receiveUid;
    }

    public void setReceiveUid(Integer receiveUid) {
        this.receiveUid = receiveUid;
    }

    public Double getRulingPrice() {
        return rulingPrice;
    }

    public void setRulingPrice(Double rulingPrice) {
        this.rulingPrice = rulingPrice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}