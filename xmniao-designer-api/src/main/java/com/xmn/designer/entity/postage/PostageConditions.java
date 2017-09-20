package com.xmn.designer.entity.postage;

import java.math.BigDecimal;

public class PostageConditions {
    private Long id;

    private Long templateId;

    private BigDecimal firstItem;

    private Integer firstNum;

    private BigDecimal continuedItem;

    private Integer continuedNum;

    private String deliveryCityNo;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public BigDecimal getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(BigDecimal firstItem) {
        this.firstItem = firstItem;
    }

    public Integer getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(Integer firstNum) {
		this.firstNum = firstNum;
	}

	public BigDecimal getContinuedItem() {
        return continuedItem;
    }

    public void setContinuedItem(BigDecimal continuedItem) {
        this.continuedItem = continuedItem;
    }

    public Integer getContinuedNum() {
        return continuedNum;
    }

    public void setContinuedNum(Integer continuedNum) {
        this.continuedNum = continuedNum;
    }

    public String getDeliveryCityNo() {
        return deliveryCityNo;
    }

    public void setDeliveryCityNo(String deliveryCityNo) {
        this.deliveryCityNo = deliveryCityNo == null ? null : deliveryCityNo.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}