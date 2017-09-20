package com.xmniao.xmn.core.fresh.entity;

import com.xmniao.xmn.core.base.BaseEntity;

public class TFreshLabel extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3689588646486192388L;

	private Integer id;

    private String labelName;

    private String picUrl;

    private Integer status;
    
	private Integer type;

    private Integer sort;
    
    /*  自定义字段区域*/
    private Integer rowNo;

	public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}
	
	/*  自定义字段区域*/

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }
	
	public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TFreshLabel [id=" + id + ", labelName=" + labelName
				+ ", picUrl=" + picUrl + ", status=" + status + ", type="
				+ type + ", sort=" + sort + ", rowNo=" + rowNo + "]";
	}
    
    
}