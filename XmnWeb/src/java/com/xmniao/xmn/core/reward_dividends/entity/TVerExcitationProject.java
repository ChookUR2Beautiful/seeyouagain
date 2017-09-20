package com.xmniao.xmn.core.reward_dividends.entity;

import java.math.BigDecimal;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

public class TVerExcitationProject  extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6644803552354331421L;

	private Integer id;

    private String projectName;

    private Byte status;

    private Integer rankId;

    private Byte period;

    private BigDecimal singleValue;
    
    private List<TVerExcitationProject>  verExcitationProjectList;
    
    private List<TVerExcitationDetail> verExcitationDetailList;
    
	private String productJson;
	
    private Double worth;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Byte getPeriod() {
        return period;
    }

    public void setPeriod(Byte period) {
        this.period = period;
    }

    public BigDecimal getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(BigDecimal singleValue) {
        this.singleValue = singleValue;
    }
    

	public List<TVerExcitationProject> getVerExcitationProjectList() {
		return verExcitationProjectList;
	}

	public void setVerExcitationProjectList(
			List<TVerExcitationProject> verExcitationProjectList) {
		this.verExcitationProjectList = verExcitationProjectList;
	}

	public List<TVerExcitationDetail> getVerExcitationDetailList() {
		return verExcitationDetailList;
	}

	public void setVerExcitationDetailList(
			List<TVerExcitationDetail> verExcitationDetailList) {
		this.verExcitationDetailList = verExcitationDetailList;
	}

	public String getProductJson() {
		return productJson;
	}

	public void setProductJson(String productJson) {
		this.productJson = productJson;
	}

	public Double getWorth() {
		return worth;
	}

	public void setWorth(Double worth) {
		this.worth = worth;
	}

	@Override
	public String toString() {
		return "TVerExcitationProject [id=" + id + ", projectName="
				+ projectName + ", status=" + status + ", rankId=" + rankId
				+ ", period=" + period + ", singleValue=" + singleValue
				+ ", verExcitationProjectList=" + verExcitationProjectList
				+ ", verExcitationDetailList=" + verExcitationDetailList
				+ ", productJson=" + productJson + ", worth=" + worth + "]";
	}
    
	
	
    
}