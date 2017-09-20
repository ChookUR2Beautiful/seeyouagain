package com.xmniao.xmn.core.businessman.entity;

import com.xmniao.xmn.core.base.BaseEntity;

public class ClassifyTag extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7442208398909391132L;

	/**
     * ID
     */
    private Integer id;

    /**
     * 分类编号
     */
    private Integer classifyId;

    /**
     * 标签名
     */
    private String tagName;
    
    private String classifyName;
    
    private Integer classifyType;
    
    private String  classifyTypeStr;
    
    private String filterVal;//过滤条件 classifyId
    
	public String getClassifyTypeStr() {
		if(classifyType==null){
			return null;
		}
		switch (classifyType) {
		case 1:
			return "商家标签";
		case 2:
			return "主播标签";
		case 3:
			return "直播标签";
		default:
			break;
		}
		return classifyTypeStr;
	}

	public void setClassifyTypeStr(String classifyTypeStr) {
		this.classifyTypeStr = classifyTypeStr;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public Integer getClassifyType() {
		return classifyType;
	}

	public void setClassifyType(Integer classifyType) {
		this.classifyType = classifyType;
	}

	/**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 分类编号
     * @return classify_id 分类编号
     */
    public Integer getClassifyId() {
        return classifyId;
    }

    /**
     * 分类编号
     * @param classifyId 分类编号
     */
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * 标签名
     * @return tag_name 标签名
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 标签名
     * @param tagName 标签名
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }
    
    

	/**
	 * @return the filterVal
	 */
	public String getFilterVal() {
		return filterVal;
	}

	/**
	 * @param filterVal the filterVal to set
	 */
	public void setFilterVal(String filterVal) {
		this.filterVal = filterVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassifyTag [id=" + id + ", classifyId=" + classifyId
				+ ", tagName=" + tagName + ", classifyName=" + classifyName
				+ ", classifyType=" + classifyType + ", classifyTypeStr="
				+ classifyTypeStr + "]";
	}
    
    
}