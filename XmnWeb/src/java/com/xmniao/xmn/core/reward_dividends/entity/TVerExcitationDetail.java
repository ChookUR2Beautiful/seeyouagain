package com.xmniao.xmn.core.reward_dividends.entity;

import com.xmniao.xmn.core.base.BaseEntity;

public class TVerExcitationDetail  extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7450763503572962180L;

	private Integer did;

    private Integer pid;

    private Integer type;

    private Integer cid;

    private Integer num;

    private Double worth;
    
    private Integer rankId;
    
    private String projectName;
    
	private String productJson;
	
	private String cname;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getWorth() {
        return worth;
    }

    public void setWorth(Double worth) {
        this.worth = worth;
    }

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProductJson() {
		return productJson;
	}

	public void setProductJson(String productJson) {
		this.productJson = productJson;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "TVerExcitationDetail [did=" + did + ", pid=" + pid + ", type="
				+ type + ", cid=" + cid + ", num=" + num + ", worth=" + worth
				+ ", rankId=" + rankId + ", projectName=" + projectName
				+ ", productJson=" + productJson + ", cname=" + cname + "]";
	}



    
    
}